package com.sv.io.service;

import com.sv.io.util.MessageCode;
import com.sv.io.util.ResultCode;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * Service Class for generic response
 *
 * @author atequer_rahman
 */
@Slf4j
@Service
public class GenericResponseService {

    public static MessageSource messageSource;

    public GenericResponseService(MessageSource messageSource){this.messageSource = messageSource;}

    @Data
    @Builder
    public static class SuccessResponse {
        @JsonProperty("success")
        boolean success;

        @JsonProperty("status")
        int status;

        @JsonProperty("result_code")
        String resultCode;

        @JsonProperty("message")
        String message;

        @JsonProperty("data")
        Object data;
    }

    @Data
    @Builder
    public static class ErrorResponse {

        @JsonProperty("success")
        Boolean success;

        @JsonProperty("status")
        int status;

        @JsonProperty("result_code")
        String resultCode;

        @JsonProperty("message")
        String message;
    }

    @Data
    @Builder
    public static class ErrorResponseWithFieldsDefaultMessage {

        @JsonProperty("success")
        Boolean success;

        @JsonProperty("status")
        int status;

        @JsonProperty("result_code")
        String resultCode;

        @JsonProperty("message")
        String message;

        @JsonProperty("error_count")
        int errorCount;

        @JsonProperty("errors")
        List<FieldValidationErrorsResponse> errors;
    }

    @Data
    @Builder
    @AllArgsConstructor
    public static class FieldValidationErrorsResponse {

        @NotBlank
        @JsonProperty("param")
        String param;

        @NotBlank
        @JsonProperty("message")
        String message;

        @JsonIgnore
        Object object;

        @JsonIgnore
        public long getLineNo(){
            return Long.parseLong(this.getObject().toString());
        }
    }

    public static ResponseEntity<Object> generateSuccessResponse(int status, ResultCode resultCode, MessageCode messageCode, Object response) {
        return ResponseEntity
                .status(status)
                .contentType(MediaType.APPLICATION_JSON)
                .body(
                        SuccessResponse.builder()
                                .success(true)
                                .status(status)
                                .resultCode(resultCode.name())
                                .message(messageCode.getLocalizedMessage())
                                .data(response)
                                .build()
                );
    }

    public static ResponseEntity<Object> generateErrorResponse(int status, Object response) {
        return ResponseEntity
                .status(status)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

    public static void logServerError(int status, String resultCode, String errorMessage, Exception ex) {

        String error = String.format("STATUS: %s | EXCEPTION: %s | MESSAGE: %s ", status, resultCode, errorMessage);

        if(resultCode.startsWith("WARN")) {
            log.warn(error, ex);
        } else {
            log.error(error, ex);
        }
    }

    public static void logServerError(int status, String resultCode, String errorMessage, List<FieldValidationErrorsResponse> errors, Exception ex) {

        String fieldErrorMessages = "";
        if (!errors.isEmpty()) {
            fieldErrorMessages = String.format("\nParameter validation errors: %s error(s) occurred.\n",errors.size());
            for (FieldValidationErrorsResponse fieldError : errors) {
                fieldErrorMessages = fieldErrorMessages.concat(String.format("[PARAM: %s | MESSAGE: %s]\n", fieldError.getParam(), fieldError.getMessage()));
            }
        }

        String error = String.format("STATUS: %s | EXCEPTION: %s | MESSAGE: %s ", status, resultCode, errorMessage.concat(fieldErrorMessages));

        if(resultCode.startsWith("WARN")) {
            log.warn(error, ex);
        } else {
            log.error(error, ex);
        }
    }
}
