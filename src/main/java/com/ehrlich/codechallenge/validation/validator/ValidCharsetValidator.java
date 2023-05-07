package com.ehrlich.codechallenge.validation.validator;

import com.ehrlich.codechallenge.util.DetectorUtil;
import com.ehrlich.codechallenge.validation.annotation.ValidCharset;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * Validator for validating file is Utf8 Encoded or not
 *
 * @author atequer_rahman
 */
@Slf4j
public class ValidCharsetValidator implements ConstraintValidator<ValidCharset, MultipartFile> {

  private String charset;

  @Override
  public void initialize(ValidCharset constraintAnnotation) {
    charset = constraintAnnotation.value();
  }

  @Override
  public boolean isValid(MultipartFile csvFile, ConstraintValidatorContext context) {
    if (Objects.requireNonNull(Objects.requireNonNull(csvFile).getOriginalFilename()).isEmpty()) {return true;}
    if (!csvFile.getContentType().equals("text/csv")) {return true;}
    try {
      String charset = DetectorUtil.getCharsetName(csvFile);
      if (!charset.equals(StandardCharsets.UTF_8.name())){
        return false;
      }
    } catch (IOException e) {
      return false;
    }
    return true;
  }
}
