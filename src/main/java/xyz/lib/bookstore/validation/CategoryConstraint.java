package xyz.lib.bookstore.validation;

import xyz.lib.bookstore.validation.custom.CategoryValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * PROJECT   : bookstore
 * PACKAGE   : xyz.lib.bookstore.validation
 * USER      : sean
 * DATE      : 31-Mon-Dec-2018
 * TIME      : 09:07
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
@Documented
@Constraint(validatedBy = CategoryValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CategoryConstraint {
    String message() default "Invalid category option";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
