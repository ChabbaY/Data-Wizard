package cloud.englert.bigdata_datenaufbereitung.nlp;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import opennlp.tools.doccat.DoccatFactory;
import opennlp.tools.doccat.DoccatModel;
import opennlp.tools.doccat.DocumentCategorizerME;
import opennlp.tools.doccat.DocumentSampleStream;
import opennlp.tools.util.MarkableFileInputStreamFactory;
import opennlp.tools.util.PlainTextByLineStream;
import opennlp.tools.util.TrainingParameters;

/**
 * Abstract classificator, needs a reference on training data
 * <a href="https://opennlp.apache.org/docs/2.3.3/manual/opennlp.html#tools.doccat.classifying">Docs</a>
 *
 * @author Linus Englert
 */
public abstract class AbstractClassificator {
    protected abstract String getTrainingFilePath();
    protected abstract String getModelPath();

    protected DoccatModel train() {
        DoccatModel model = null;
        try (var os = new PlainTextByLineStream(
                new MarkableFileInputStreamFactory(
                        new File(getTrainingFilePath())),
                StandardCharsets.UTF_8)) {
            var trainingStream = new DocumentSampleStream(os);

            model = DocumentCategorizerME.train("de", trainingStream,
                    TrainingParameters.defaultParams(), new DoccatFactory());
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
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (model == null) {
            model = train();
        }

        if (model != null) {
            var categorizer = new DocumentCategorizerME(model);
            var outcomes = categorizer.categorize(new String[]{value});

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
}