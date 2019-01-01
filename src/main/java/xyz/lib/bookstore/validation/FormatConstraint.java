package xyz.lib.bookstore.validation;

import xyz.lib.bookstore.validation.custom.FormatValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * PROJECT   : bookstore
 * PACKAGE   : xyz.lib.bookstore.validation
 * USER      : sean
 * DATE      : 01-Tue-Jan-2019
 * TIME      : 09:09
 * E-MAIL    : kudzai@bcs.org
 * CELL      : +27-64-906-8809
 */
@Documented
@Constraint(validatedBy = FormatValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface FormatConstraint {
    String message() default "Invalid format option";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
