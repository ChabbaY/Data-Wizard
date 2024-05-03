package cloud.englert.bigdata_datenaufbereitung.nlp;

/**
 * Apache OpenNLP classifier
 *
 * @author Linus Englert
 */
public class Classifier extends AbstractClassificator {
    @Override
    protected String getTrainingFilePath() {
        return "src/main/resources/classification.train";
    }

    @Override
    protected String getModelPath() {
        return "src/main/resources/models/classifier.bin";
    }
}