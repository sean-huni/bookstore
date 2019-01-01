package xyz.lib.bookstore.validation.custom;

import xyz.lib.bookstore.enums.FormatEnum;
import xyz.lib.bookstore.validation.FormatConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Optional;

/**
 * PROJECT   : bookstore
 * PACKAGE   : xyz.lib.bookstore.validation.custom
 * USER      : sean
 * DATE      : 01-Tue-Jan-2019
 * TIME      : 09:10
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
public class FormatValidator implements ConstraintValidator<FormatConstraint, String> {
    /**
     * Initializes the validator in preparation for
     * {@link #isValid(Object, ConstraintValidatorContext)} calls.
     * The constraint annotation for a given constraint declaration
     * is passed.
     * <p>
     * This method is guaranteed to be called before any use of this instance for
     * validation.
     * <p>
     * The default implementation is a no-op.
     *
     * @param constraintAnnotation annotation instance for a given constraint declaration
     */
    @Override
    public void initialize(FormatConstraint constraintAnnotation) {

    }

    /**
     * Implements the validation logic.
     * The state of {@code value} must not be altered.
     * <p>
     * This method can be accessed concurrently, thread-safety must be ensured
     * by the implementation.
     *
     * @param formatField object to validate
     * @param context     context in which the constraint is evaluated
     * @return {@code false} if {@code value} does not pass the constraint
     */
    @Override
    public boolean isValid(String formatField, ConstraintValidatorContext context) {
        Optional<String> optionalCat = Optional.ofNullable(formatField);
        return optionalCat.isPresent() && isValid(formatField);
    }

    /**
     * Validate supplied value against all enum values.
     *
     * @param formatField supplied value;
     * @return true if it exists in the enum, otherwise false.
     */
    private boolean isValid(String formatField) {
        return Arrays.stream(FormatEnum.values()).anyMatch(categoryEnum -> categoryEnum.name().equalsIgnoreCase(formatField));
    }
}
