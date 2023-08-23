package com.sv.io.validation.annotation;


import com.sv.io.validation.validator.ValidCharsetValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Annotation for validating file is Utf8 Encoded or not
 *
 * @author atequer_rahman
 */
@Documented
@Constraint(validatedBy = ValidCharsetValidator.class)
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCharset {

  String value() default "UTF-8";

  String message() default "File is not UTF-8 encoded.";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
