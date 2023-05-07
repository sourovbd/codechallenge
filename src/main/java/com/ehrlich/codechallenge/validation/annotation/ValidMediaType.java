package com.ehrlich.codechallenge.validation.annotation;

import com.ehrlich.codechallenge.validation.validator.MediaTypeValidator;
import org.springframework.http.MediaType;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Annotation for validating media type
 *
 * @author atequer_rahman
 */
@Documented
@Constraint(validatedBy = MediaTypeValidator.class)
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidMediaType {

  String value() default MediaType.ALL_VALUE;

  String message() default "Media file type is not allowed.";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
