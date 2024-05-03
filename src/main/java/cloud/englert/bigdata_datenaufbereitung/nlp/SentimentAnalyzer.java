package cloud.englert.bigdata_datenaufbereitung.nlp;

/**
 * Apache OpenNLP Sentiment Analysis
 *
 * @author Linus Englert
 */
public class SentimentAnalyzer extends AbstractClassificator {
    @Override
    protected String getTrainingFilePath() {
        return "src/main/resources/sentiment.train";
    }

    @Override
    protected String getModelPath() {
        return "src/main/resources/models/sentiment.bin";
    }

    public String classifyByWordList(String value) {
        int good = 0, bad = 0;

        for (String badWord : badWords) {
            if (value.toUpperCase().contains(badWord.toUpperCase())) bad++;
        }
        for (String goodWord : goodWords) {
            if (value.toUpperCase().contains(goodWord.toUpperCase())) good++;
        }

        return (bad > good) ? "Schlecht" : "Gut";
    }

    public static String[] badWords = { "abgeneigt", "abwesend", "aggressiv", "ahnungslos", "alamiert", "allein",
            "angeekelt", "angespannt", "ängstlich", "angstvoll", "antriebsarm", "ärgerlich", "ausgenutzt",
            "ausgestossen", "bedrängt", "bedroht", "bedrückt", "belastet", "besorgt", "bestürzt", "betroffen",
            "betrogen", "betrübt", "beunruhigt", "bitter", "dpressiv", "deprimiert", "desinteressiert", "desorientiert",
            "distanziert", "düster", "eifersüchtig", "eingeengt", "einsam", "ekelhaft", "elend", "empört", "entrüstet",
            "enttäuscht", "erkältet", "ermüdet", "erschöpft", "erstarrt", "ertappt", "fade", "fassungslos", "faul",
            "feindselig", "freudlos", "friedlos", "frustriert", "fürchterlich", "gehässig", "geknickt", "gelangweilt",
            "gemobt", "genervt", "gequält", "gereizt", "geschockt", "gestresst", "gezwungen", "grantig", "grausam",
            "hart", "hasserfüllt", "hilflos", "hintergangen", "hoffnungslos", "instabil", "kalt", "kraftlos", "krank",
            "kühl", "kummervoll", "labil", "leblos", "leer", "lustlos", "matt", "melancholisch", "miserabel",
            "missmutig", "müde", "mutlos", "nervös", "ohnmächtig", "orientierungslos", "panisch", "ratlos", "sauer",
            "Schmerz", "schrecklich", "schutzlos", "schwach", "skeptisch", "sorgenvoll", "tot", "tod", "teilnahmslos",
            "träge", "traurig", "trostlos", "trübsinnig", "überarbeitet", "überlastet", "unangenehm", "unbehaglich",
            "unentschlossen", "ungemütlich", "unglücklich", "unsicher", "unverstanden", "unwillig", "unwohl",
            "unzufrieden", "verärgert", "verflucht", "verstört", "verzweifelt", "widerwillig", "wütend", "zornig",
            "schlecht" };

    public static String[] goodWords = { "bewundernswert", "hochwertig", "unbezahlbar", "bezaubernd", "makellos",
            "unerreicht", "brilliant", "meisterhaft", "unschätzbar", "einfach", "Klasse", "mustergültig",
            "unüberbietbar", "einwandfrei", "optimal", "unübertrefflich", "erfreulich", "perfekt", "unvergleichlich",
            "erstklassig", "phänomenal", "vollendet", "fabelhaft", "prächtig", "vollkommen", "famos", "prachtvoll",
            "vorbildlich", "fantastisch", "schön", "vortrefflich", "fehlerfrei", "spektakulär", "vorzüglich", "genial",
            "super", "wertvoll", "glänzend", "tadellos", "wunderbar", "angenehm", "anmutig", "anregend",
            "anspruchsvoll", "anziehend", "atemberaubend", "attraktiv", "aufreizend", "ausgelassen", "außergewöhnlich",
            "bedeutend", "befreiend", "belebt", "berauschend", "besonders", "bewundernswerd", "duftend", "edel",
            "einfühlsam", "ekstatisch", "eleganz", "empfehlenswert", "entzückend", "erstaunlich", "erstklassig",
            "exzellent", "fein", "freundlich", "fröhlich", "gigantisch" };
}