package cloud.englert.bigdata_datenaufbereitung.validators;

import cloud.englert.bigdata_datenaufbereitung.datastructures.Validation;

/**
 * Validation of geo information data types.
 *
 * @author Linus Englert
 */
public class GeoValidator {
    public static Validation<Double> isLongLatFormat(String value) {
        String regEx = "-?\\d{1,3}\\.\\d{0,8}";
        if (value.matches(regEx)) {
            var doubleValidation = NumberValidator.isDouble(value);
            if (doubleValidation.isValid()) {
                double d = doubleValidation.value();
                if ((d <= 180) && (d >= -180)) {
                    return new Validation<>(true, d);
                }
            }
        }
        return new Validation<>(false, 0d);
    }
}