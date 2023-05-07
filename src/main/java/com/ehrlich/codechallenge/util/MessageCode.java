package com.ehrlich.codechallenge.util;

import com.ehrlich.codechallenge.service.GenericResponseService;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Locale;

/**
 * String Codes for custom messages in message properties
 *
 * @author atequer_rahman
 */
public enum MessageCode {

    // HTTP response status code

    OK,
    BAD_REQUEST,

    // Custom success response status code - prefix: INFO

    // Custom warning response status code - prefix: WARN

    WARN_INPUT_PARAM,
    WARN_INPUT_PARAM_FILE_MISSING,
    WARN_REQUEST_FAILED_FILE_UPLOAD;

    // Custom error response status code - prefix: ERR


    // Exceptions Message

    public String getLocalizedMessage() {
        return GenericResponseService.messageSource.getMessage(this.name(), null, LocaleContextHolder.getLocale());
    }

    public String getDefaultMessage() {
        return GenericResponseService.messageSource.getMessage(this.name(), null, Locale.getDefault());
    }
}
