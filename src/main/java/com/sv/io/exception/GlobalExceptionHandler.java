package com.sv.io.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;

import static com.sv.io.service.GenericResponseService.generateErrorResponse;
import static com.sv.io.service.GenericResponseService.logServerError;
import static com.sv.io.service.GenericResponseService.ErrorResponse;
import static com.sv.io.service.GenericResponseService.ErrorResponseWithFieldsDefaultMessage;



/**
 * Exception Class for Custom and Generic Exception Handler
 *
 * @author atequer_rahman
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    private static BindingResult bindingResult;

    @ExceptionHandler({GlobalException.class, RuntimeException.class})
    public ResponseEntity<Object> handleGlobalException(GlobalException ex) {
        if (Objects.nonNull(ex.getFieldErrors())) {
            ErrorResponseWithFieldsDefaultMessage error = ErrorResponseWithFieldsDefaultMessage.builder()
                    .success(false)
                    .status(ex.getStatus())
                    .resultCode(ex.getResultCode())
                    .message(ex.getLocalizedMessage())
                    .errorCount(ex.getFieldErrors().size())
                    .errors(ex.getFieldErrors())
                    .build();

            logServerError(error.getStatus(), error.getResultCode(), error.getMessage(), error.getErrors(), ex);
            return generateErrorResponse(error.getStatus(), error);
        } else {
            ErrorResponse error = ErrorResponse.builder()
                    .success(ex.getSuccess())
                    .status(ex.getStatus())
                    .resultCode(ex.getResultCode())
                    .message(ex.getMessage())
                    .build();
            logServerError(error.getStatus(), error.getResultCode(), error.getMessage(), ex);
            return generateErrorResponse(ex.getStatus(), error);
        }
    }

//    @ExceptionHandler(MultipartException.class)
//    public ResponseEntity<Object> handleMultipartException(MultipartException ex) {
//
//        ErrorResponse error = ErrorResponse.builder()
//                .success(false)
//                .status(HttpStatus.BAD_REQUEST.value())
//                .resultCode(ResultCode.WARN_INPUT_PARAM.name())
//                .message(MessageCode.EX_MISSING_MULTIPART_FILE.getLocalizedMessage())
//                .build();
//
//        logServerError(error.getStatus(), error.getResultCode(), error.getMessage(), ex);
//        return generateErrorResponse(HttpStatus.BAD_REQUEST.value(), error);
//    }

//    @ExceptionHandler(MissingServletRequestParameterException.class)
//    public ResponseEntity<Object> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
//
//        ErrorResponse error = ErrorResponse.builder()
//                .success(false)
//                .status(HttpStatus.BAD_REQUEST.value())
//                .resultCode(ResultCode.WARN_INPUT_PARAM.name())
//                .message(ex.getLocalizedMessage())
//                .build();
//
//        logServerError(error.getStatus(), error.getResultCode(), error.getMessage(), ex);
//        return generateErrorResponse(HttpStatus.BAD_REQUEST.value(), error);
//    }


//    @ExceptionHandler(HttpMessageNotReadableException.class)
//    public ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
//
//        ErrorResponse error = ErrorResponse.builder()
//                .success(false)
//                .status(HttpStatus.BAD_REQUEST.value())
//                .resultCode(ResultCode.WARN_INPUT_PARAM.name())
//                .message(MessageCode.WARN_INPUT_PARAM.getLocalizedMessage())
//                .build();
//
//        logServerError(error.getStatus(), error.getResultCode(), ex.getMessage(), ex);
//        return generateErrorResponse(HttpStatus.BAD_REQUEST.value(), error);
//    }

//    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
//    public ResponseEntity<Object> handleMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex) {
//
//        ErrorResponse error = ErrorResponse.builder()
//                .success(false)
//                .status(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
//                .resultCode(ResultCode.WARN_INPUT_PARAM.name())
//                .message(MessageCode.EX_MEDIA_TYPE_NOT_SUPPORTED.getLocalizedMessage()+": "+ex.getLocalizedMessage())
//                .build();
//
//        logServerError(error.getStatus(), error.getResultCode(), error.getMessage(), ex);
//        return generateErrorResponse(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(), error);
//    }


//    @ExceptionHandler(IOException.class)
//    public ResponseEntity<Object> handleIOException(IOException ex) {
//
//        ErrorResponse error = ErrorResponse.builder()
//                .success(false)
//                .status(HttpStatus.BAD_REQUEST.value())
//                .resultCode(ResultCode.ERR_FILE_IO.name())
//                .message(MessageCode.EX_IO_EXCEPTION.getLocalizedMessage())
//                .build();
//
//        logServerError(error.getStatus(), error.getResultCode(), error.getMessage(), ex);
//        return generateErrorResponse(HttpStatus.BAD_REQUEST.value(), error);
//    }

//    @ExceptionHandler(HttpClientErrorException.class)
//    public ResponseEntity<Object> handleHttpClientErrorException(HttpClientErrorException ex) {
//
//        ErrorResponse error = ErrorResponse.builder()
//                .success(false)
//                .status(HttpStatus.UNAUTHORIZED.value())
//                .resultCode(ResultCode.ERR_INVALID_TOKEN.name())
//                .message(MessageCode.ERR_INVALID_TOKEN.getLocalizedMessage())
//                .build();
//
//        logServerError(error.getStatus(), error.getResultCode(), error.getMessage(), ex);
//        return generateErrorResponse(HttpStatus.BAD_REQUEST.value(), error);
//    }

//    @ExceptionHandler(JsonProcessingException.class)
//    public ResponseEntity<Object> handleJsonProcessingException(JsonProcessingException ex) {
//
//        ErrorResponse error = ErrorResponse.builder()
//                .success(false)
//                .status(HttpStatus.BAD_REQUEST.value())
//                .resultCode(ResultCode.ERR_PROCESSING_JSON.name())
//                .message(MessageCode.ERR_PROCESSING_JSON.getLocalizedMessage())
//                .build();
//
//        logServerError(error.getStatus(), error.getResultCode(), error.getMessage(), ex);
//        return generateErrorResponse(HttpStatus.BAD_REQUEST.value(), error);
//    }


//    @ExceptionHandler(CsvConstraintViolationException.class)
//    public ResponseEntity<Object> handleCsvConstraintViolationException(CsvConstraintViolationException ex) {
//        ErrorResponse error = ErrorResponse.builder()
//                .success(false)
//                .status(HttpStatus.BAD_REQUEST.value())
//                .resultCode(ResultCode.WARN_INPUT_PARAM.name())
//                .message(ex.getLocalizedMessage())
//                .build();
//
//        logServerError(error.getStatus(), error.getResultCode(), error.getMessage(), ex);
//        return generateErrorResponse(HttpStatus.BAD_REQUEST.value(), error);
//    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<Object> handleUnknownException(Exception ex) {
//
//        ErrorResponse error = ErrorResponse.builder()
//                .success(false)
//                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
//                .resultCode(ResultCode.ERR_UNKNOWN.name())
//                .message(MessageCode.ERR_UNKNOWN.getLocalizedMessage())
//                .build();
//
//        logServerError(error.getStatus(), error.getResultCode(), error.getMessage(), ex);
//        return generateErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), error);
//    }

}
