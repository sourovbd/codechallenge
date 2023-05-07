package com.ehrlich.codechallenge.exception;

import com.ehrlich.codechallenge.service.GenericResponseService;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * Exception Class for Custom and Generic Exceptions
 *
 * @author atequer_rahman
 */
@Slf4j
@Getter
public class GlobalException extends RuntimeException {

    private Boolean success;

    private int status;

    private String resultCode;

    private String message;

    private int errorCount;

    private List<GenericResponseService.FieldValidationErrorsResponse> fieldErrors;

    public GlobalException(int status, String resultCode, String message) {
        super(resultCode);
        this.success = false;
        this.status = status;
        this.resultCode = resultCode;
        this.message = message;
    }

    public GlobalException(int status, String resultCode, String message,
                           List<GenericResponseService.FieldValidationErrorsResponse> fieldErrors, int errorCount) {
        super(resultCode);
        this.success = false;
        this.status = status;
        this.resultCode = resultCode;
        this.message = message;
        this.fieldErrors = fieldErrors;
        this.errorCount = errorCount;
    }

}
