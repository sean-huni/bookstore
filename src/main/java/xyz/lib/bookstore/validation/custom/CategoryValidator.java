package xyz.lib.bookstore.validation.custom;

import xyz.lib.bookstore.enums.CategoryEnum;
import xyz.lib.bookstore.validation.CategoryConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Optional;

/**
 * PROJECT   : bookstore
 * PACKAGE   : xyz.lib.bookstore.validation
 * USER      : sean
 * DATE      : 31-Mon-Dec-2018
 * TIME      : 09:06
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
public class CategoryValidator implements ConstraintValidator<CategoryConstraint, String> {

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
    public void initialize(CategoryConstraint constraintAnnotation) {

    }

    /**
     * Implements the validation logic.
     * The state of {@code value} must not be altered.
     * <p>
     * This method can be accessed concurrently, thread-safety must be ensured
     * by the implementation.
     *
     * @param categoryField object to validate
     * @param ctx           context in which the constraint is evaluated
     * @return {@code false} if {@code value} does not pass the constraint
     */
    @Override
    public boolean isValid(String categoryField, ConstraintValidatorContext ctx) {
        Optional<String> optionalCat = Optional.ofNullable(categoryField);
        return optionalCat.isPresent() && isValid(categoryField);
    }

    /**
     * Validate supplied value against all enum values.
     *
     * @param categoryField supplied value;
     * @return true if it exists in the enum, otherwise false.
     */
    private boolean isValid(String categoryField) {
        return Arrays.stream(CategoryEnum.values()).anyMatch(categoryEnum -> categoryEnum.name().equalsIgnoreCase(categoryField));
    }
}
