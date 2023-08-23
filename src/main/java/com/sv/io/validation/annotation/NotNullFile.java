package com.sv.io.validation.annotation;

import com.sv.io.validation.validator.NotNullFileValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Annotation for validating file exists or not
 *
 * @author atequer_rahman
 */
@Documented
@Constraint(validatedBy = NotNullFileValidator.class)
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotNullFile {

  String message() default "File missing.";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
