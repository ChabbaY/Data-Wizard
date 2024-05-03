package cloud.englert.bigdata_datenaufbereitung.validators;

import com.cybozu.labs.langdetect.DetectorFactory;
import com.cybozu.labs.langdetect.LangDetectException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Apache OpenNLP Language Detection
 * <a href="https://opennlp.apache.org/docs/2.3.3/manual/opennlp.html#tools.langdetect">Docs</a>
 */
public class LanguageValidator {
    private final static String PATH = "src/main/resources/languages/";
    private final static String[] LANGUAGES = {"af","ar","bg","bn","cs","da","de","el","en","es","et","fa","fi","fr",
            "gu","he","hi","hr","hu","id","it","ja","kn","ko","lt","lv","mk","ml","mr","ne","nl","no","pa","pl","pt",
            "ro","ru","sk","sl","so","sq","sv","sw","ta","te","th","tl","tr","uk","ur","vi","zh-cn","zh-tw"};

    public LanguageValidator() {
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

    public String getLanguage(String value) {
        try {
            var detector = DetectorFactory.create();
            detector.append(value);
            return detector.detect();
        } catch (LangDetectException e) {
            e.printStackTrace();
        }
        return "";
    }
}