package com.ehrlich.codechallenge.validation.validator;

import com.ehrlich.codechallenge.validation.annotation.NotNullFile;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

/**
 * Annotation for validating file exists or not
 *
 * @author atequer_rahman
 */
public class NotNullFileValidator implements ConstraintValidator<NotNullFile, MultipartFile> {

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        if (!Objects.requireNonNull(Objects.requireNonNull(file).getOriginalFilename()).isEmpty()) {
            return true;
        }
        return false;
    }
}
