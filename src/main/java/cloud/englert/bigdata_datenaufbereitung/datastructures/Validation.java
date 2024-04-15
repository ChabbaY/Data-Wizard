package cloud.englert.bigdata_datenaufbereitung.datastructures;

/**
 * Record to store validation information of validators.
 *
 * @param isValid the boolean result of the validation method
 * @param value the parsed value if possible (isValid), else a default value
 * @param <T> the type of value that is validated
 *
 * @author Linus Englert
 */
public record Validation<T>(boolean isValid, T value) {}