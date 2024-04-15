package cloud.englert.bigdata_datenaufbereitung.validators;

import cloud.englert.bigdata_datenaufbereitung.datastructures.Validation;

/**
 * Validation of numeric values
 *
 * @author Linus Englert
 */
public class NumberValidator {
    public static Validation<Integer> isInteger(String value) {
        try {
            int i = Integer.parseInt(value);
            return new Validation<>(true, i);
        } catch (NumberFormatException ignored) {}
        return new Validation<>(false, 0);
    }

    public static Validation<Double> isDouble(String value) {
        try {
            double d = Double.parseDouble(value);
            return new Validation<>(true, d);
        } catch (NumberFormatException ignored) {}
        return new Validation<>(false, 0d);
    }
}