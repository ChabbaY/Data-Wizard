package cloud.englert.bigdata_datenaufbereitung.validators;

import com.cybozu.labs.langdetect.DetectorFactory;
import com.cybozu.labs.langdetect.LangDetectException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Language detection
 *
 * @author Linus Englert
 */
public class LanguageValidator {
    private final static String PATH = "src/main/resources/languages/";
    private final static String[] LANGUAGES = {"af","ar","bg","bn","cs","da","de","el","en","es","et","fa","fi","fr",
            "gu","he","hi","hr","hu","id","it","ja","kn","ko","lt","lv","mk","ml","mr","ne","nl","no","pa","pl","pt",
            "ro","ru","sk","sl","so","sq","sv","sw","ta","te","th","tl","tr","uk","ur","vi","zh-cn","zh-tw"};
    private final HashMap<String, String> lang;

    public LanguageValidator() {
        lang = new HashMap<>();
        loadLang();
        if (!DetectorFactory.getLangList().isEmpty()) return;
        try {
            var jsonProfiles = new ArrayList<String>();
            for (String language : LANGUAGES) {
                var br = new BufferedReader(new FileReader(PATH + language));
                String line;
                while ((line = br.readLine()) != null) {
                    jsonProfiles.add(line);
                }
                br.close();
            }
            DetectorFactory.loadProfile(jsonProfiles);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Detects the language of a String
     * @param value String to interpret
     * @return the 2-character language code as in LANGUAGES, e.g. 'de'
     */
    public String getLanguage(String value) {
        if ((value == null) || value.isEmpty()) return "";
        try {
            var detector = DetectorFactory.create();
            detector.append(value);
            var langCode = detector.detect();
            return lang.get(langCode);
        } catch (LangDetectException e) {
            e.printStackTrace();
        }
        return "";
    }

    private void loadLang() {
        lang.put("af", "Afrikaans");
        lang.put("ar", "Arabisch");
        lang.put("bg", "Bulgarisch");
        lang.put("bn", "Bengalisch");
        lang.put("cs", "Tschechisch");
        lang.put("da", "Dänisch");
        lang.put("de", "Deutsch");
        lang.put("el", "Griechisch");
        lang.put("en", "Englisch");
        lang.put("es", "Spanisch");
        lang.put("et", "Estnisch");
        lang.put("fa", "Persisch");
        lang.put("fi", "Finnisch");
        lang.put("fr", "Französisch");
        lang.put("gu", "Gujarati");
        lang.put("he", "Hebräisch");
        lang.put("hi", "Hindi");
        lang.put("hr", "Kroatisch");
        lang.put("hu", "Ungarisch");
        lang.put("id", "Indonesisch");
        lang.put("it", "Italienisch");
        lang.put("ja", "Japanisch");
        lang.put("kn", "Kanadisch");
        lang.put("ko", "Koreanisch");
        lang.put("lt", "Litauisch");
        lang.put("lv", "Lettisch");
        lang.put("mk", "Mazedonisch");
        lang.put("ml", "Malayalam");
        lang.put("mr", "Marathi");
        lang.put("ne", "Nepalesisch");
        lang.put("nl", "Niederländisch");
        lang.put("no", "Norwegisch");
        lang.put("pa", "Punjabi");
        lang.put("pl", "Polnisch");
        lang.put("pt", "Portugiesisch");
        lang.put("ro", "Rumänisch");
        lang.put("ru", "Russisch");
        lang.put("sk", "Slowakisch");
        lang.put("sl", "Slowenisch");
        lang.put("so", "Somalisch");
        lang.put("sq", "Albanisch");
        lang.put("sv", "Schwedisch");
        lang.put("sw", "Swahili");
        lang.put("ta", "Tamil");
        lang.put("te", "Telugu");
        lang.put("th", "Thailändisch");
        lang.put("tl", "Tagalog");
        lang.put("tr", "Türkisch");
        lang.put("uk", "Ukrainisch");
        lang.put("ur", "Urdu");
        lang.put("vi", "Vietnamesisch");
        lang.put("zh-cn", "Vereinfachtes Chinesisch");
        lang.put("zh-tw", "Traditionelles Chinesisch");
    }
}