package cloud.englert.bigdata_datenaufbereitung.nlp;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import opennlp.tools.doccat.*;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.util.MarkableFileInputStreamFactory;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.TrainingParameters;
import opennlp.tools.util.model.ModelUtil;

/**
 * Abstract classificator, needs a reference on training data
 * <a href="https://opennlp.apache.org/docs/2.3.3/manual/opennlp.html#tools.doccat.classifying">Docs</a>
 *
 * @author Linus Englert
 */
public abstract class AbstractClassificator {
    protected abstract String getTrainingFilePath();
    protected abstract String getModelPath();
    private final String tokenizer_model = "src/main/resources/models/de-token.bin";

    protected DoccatModel train() {
        DoccatModel model = null;
        try (var os = new PlainTextByLineStream(
                new MarkableFileInputStreamFactory(
                        new File(getTrainingFilePath())), StandardCharsets.UTF_8)) {
            var trainingStream = new DocumentSampleStream(os);

            // Logging the training data
            System.out.println("Training data:");
            DocumentSample sample;
            while ((sample = trainingStream.read()) != null) {
                System.out.println(sample.getCategory() + "\t" + String.join(" ", sample.getText()));
            }

            // Reset the stream after logging
            os.reset();

            // set cut_off to 0 (few samples), each word will be a feature
            TrainingParameters params = ModelUtil.createDefaultTrainingParameters();
            params.put(TrainingParameters.CUTOFF_PARAM, 0);
            DoccatFactory factory = new DoccatFactory(new FeatureGenerator[]{ new BagOfWordsFeatureGenerator() });

            model = DocumentCategorizerME.train("de", trainingStream, params, factory);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (model != null) saveModel(model);
        return model;
    }

    public String classify(String value) {
        DoccatModel model = null;

        try (var is = new FileInputStream(getModelPath())) {
            model = new DoccatModel(is);
        } catch (IOException ignored) {}

        if (model == null) {
            model = train();
        }

        if (model != null) {
            var categorizer = new DocumentCategorizerME(model);
            var outcomes = categorizer.categorize(tokenize(value));
            System.out.println(Arrays.toString(tokenize(value)));

            // Log the probabilities for each category
            for (int i = 0; i < categorizer.getNumberOfCategories(); i++) {
                System.out.printf("Category: %s, Probability: %.4f%n", categorizer.getCategory(i), outcomes[i]);
            }

            return categorizer.getBestCategory(outcomes);
        }
        return "";
    }

    protected void saveModel(DoccatModel model) {
        try (var os = new BufferedOutputStream(new FileOutputStream(getModelPath()))) {
            model.serialize(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String[] tokenize(String sentence) {
        try (var is = new FileInputStream(tokenizer_model)) {
            var model = new TokenizerModel(is);
            var tokenizer = new TokenizerME(model);
            return tokenizer.tokenize(sentence);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String[0];
    }
}