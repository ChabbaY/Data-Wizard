package cloud.englert.bigdata_datenaufbereitung.validators;

import cloud.englert.bigdata_datenaufbereitung.datastructures.Validation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Validating dates and related data types.
 *
 * @author Linus Englert
 */
public class DateValidator {
    private final static List<SimpleDateFormat> dateFormats = new ArrayList<>() {{
        add(new SimpleDateFormat("M/dd/yyyy"));
        add(new SimpleDateFormat("dd.M.yyyy"));
        add(new SimpleDateFormat("dd.MM.yyyy"));
        add(new SimpleDateFormat("dd.MM.yy"));
        add(new SimpleDateFormat("MM/dd/yyyy"));
        add(new SimpleDateFormat("M/dd/yyyy hh:mm:ss"));
        add(new SimpleDateFormat("dd.M.yyyy hh:mm:ss"));
        add(new SimpleDateFormat("dd.MMM.yyyy"));
        add(new SimpleDateFormat("dd-MMM-yyyy"));
        add(new SimpleDateFormat("dd. MM yyyy"));
        add(new SimpleDateFormat("yyyy-MM-dd"));
    }};

    public static Validation<Date> isDate(String value) {
        for (SimpleDateFormat format : dateFormats) {
            try {
                format.setLenient(false); // exact match required
                Date date = format.parse(value);
                return new Validation<>(true, date);
            } catch (ParseException ignored) {}
        }
        return new Validation<>(false, null);
    }

    public static Validation<Integer> isYear(String value) {
        var numberValidation = NumberValidator.isInteger(value);
        if (numberValidation.isValid()) {
            int year = numberValidation.value();
            if ((year >= 0) && (year < 9999)) {
                return new Validation<>(true, year);
            }
        }
        return new Validation<>(false, 0);
    }
}