package com.sv.io.validation.validator;

import com.sv.io.validation.annotation.ValidMediaType;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

/**
 * Validator for validating media type
 *
 * @author atequer_rahman
 */
public class MediaTypeValidator implements ConstraintValidator<ValidMediaType, MultipartFile> {

  private String allowed;

  @Override
  public void initialize(ValidMediaType constraintAnnotation) {
    allowed = constraintAnnotation.value();
  }

  @Override
  public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
    if (Objects.requireNonNull(Objects.requireNonNull(file).getOriginalFilename()).isEmpty()) { return true; }
    return allowed.equals(file.getContentType());
  }
}
