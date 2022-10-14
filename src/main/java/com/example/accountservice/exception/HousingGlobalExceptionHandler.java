package com.example.accountservice.exception;


import com.example.accountservice.config.rest.BaseResponse;
import com.example.accountservice.config.rest.FieldViolation;
import com.example.accountservice.exception.constant.HousingBusinessError;
import com.example.accountservice.exception.constant.HousingException;
import com.google.common.base.CaseFormat;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.core.codec.DecodingException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.ServerWebInputException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.List;
import java.util.concurrent.CompletionException;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class HousingGlobalExceptionHandler {
    @ExceptionHandler(HousingException.class)
    public ResponseEntity<BaseResponse<Void>> handleBusinessException(HousingException exception) {
        var data = this.getFailedResponse(exception.getErrorCode());
        return this.getResponseEntity(exception.getErrorCode(), data, exception);
    }

    @ExceptionHandler(value = NoHandlerFoundException.class)
    public ResponseEntity<BaseResponse<Void>> handleResourceNotFoundException(HttpClientErrorException.NotFound exception) {
        var errorCode = HousingErrors.NOT_FOUND;
        var data = this.getFailedResponse(errorCode);
        return this.getResponseEntity(errorCode, data, exception);
    }

    @ExceptionHandler(value = HttpClientErrorException.class)
    public ResponseEntity<BaseResponse<Void>> handleServletNotFoundException(HttpClientErrorException.NotFound exception) {
        var errorCode = HousingErrors.NOT_FOUND;
        var data = this.getFailedResponse(errorCode);
        return this.getResponseEntity(errorCode, data, exception);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponse<Void>> handleBusinessException(MethodArgumentNotValidException exception) {
        var errors = this.getFieldViolations(exception.getBindingResult());
        var errorCode = HousingErrors.INVALID_PARAMETERS;
        var errorMessage = "Invalid parameters of object: " + exception.getBindingResult().getObjectName();
        var data = this.getFailedResponse(errorCode, errorMessage, errors);

        return this.getResponseEntity(errorCode, data, exception);
    }

    @ExceptionHandler(CompletionException.class)
    public ResponseEntity<BaseResponse<Void>> handleCompletionException(HttpMessageNotReadableException exception) {
        var cause = exception.getCause();
        if (cause instanceof HousingException) {
            return handleBusinessException((HousingException) cause);
        }
        return handleException((Exception) cause);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<BaseResponse<Void>> handleHttpMessageNotReadableException(HttpMessageNotReadableException exception) {
        var errorMessage = this.getNormalizeErrorMessage(exception);
        var errorCode = HousingErrors.INVALID_PARAMETERS;
        var data = this.getFailedResponse(errorCode, errorMessage);
        return this.getResponseEntity(errorCode, data, exception);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse<Void>> handleException(Exception exception) {
        log.error(exception.getMessage(), exception);
        var errorCode = HousingErrors.INTERNAL_SERVER_ERROR;
        var data = this.getFailedResponse(errorCode);
        return this.getResponseEntity(errorCode, data, exception);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<BaseResponse<Void>> handleIllegalArgumentException(Exception exception) {
        var errorCode = HousingErrors.INVALID_PARAMETERS;
        var data = this.getFailedResponse(errorCode);
        return this.getResponseEntity(errorCode, data, exception);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<BaseResponse<Void>> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception) {
        return handleBusinessException(resolve(exception));
    }

    private HousingException resolve(MethodArgumentTypeMismatchException exception) {
        return new HousingException(HousingErrors.INVALID_PARAMETERS, exception.getMessage(), exception);
    }

    private String getNormalizeErrorMessage(HttpMessageNotReadableException exception) {
        var errorMessage = exception.getMessage();
        if (errorMessage == null) return Strings.EMPTY;
        int idx = errorMessage.indexOf(':');
        return idx > 0 ? errorMessage.substring(0, idx) : errorMessage;
    }


    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<BaseResponse<Void>> handleAccessDeniedException(AccessDeniedException exception) {
        var errorCode = HousingErrors.FORBIDDEN_ERROR;
        var data = this.getFailedResponse(errorCode);
        return this.getResponseEntity(errorCode, data, exception);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<BaseResponse<Void>> handleAuthenticationException(AuthenticationException exception) {
        var errorCode = HousingErrors.USER_NOT_UNAUTHORIZED;
        var data = this.getFailedResponse(errorCode);
        return this.getResponseEntity(errorCode, data, exception);
    }

    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<BaseResponse<Void>> handleException(WebExchangeBindException exception) {
        var errors = this.getFieldViolations(exception.getBindingResult());
        var errorCode = HousingErrors.INVALID_PARAMETERS;
        var errorMessage = "Invalid parameters of object: " + exception.getBindingResult().getObjectName();
        var data = this.getFailedResponse(errorCode, errorMessage, errors);
        return this.getResponseEntity(errorCode, data, exception);

    }

    private List<FieldViolation> getFieldViolations(BindingResult bindingResult) {
        return bindingResult.getFieldErrors().stream()
                .map(e -> new FieldViolation(CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, e.getField()), e.getDefaultMessage()))
                .collect(Collectors.toList());
    }

    @ExceptionHandler(ServerWebInputException.class)
    public ResponseEntity<BaseResponse<Void>> handleException(ServerWebInputException exception) {

        var errorCode = HousingErrors.INVALID_PARAMETERS;
        var data = this.getFailedResponse(errorCode, exception.getCause().getMessage());
        return this.getResponseEntity(errorCode, data, exception);
    }

    @ExceptionHandler(DecodingException.class)
    public ResponseEntity<BaseResponse<Void>> handleDecodingException(DecodingException exception) {
        var errorCode = HousingErrors.MAX_FILE_SIZE;
        var data = this.getFailedResponse(errorCode);
        return this.getResponseEntity(errorCode, data, exception);
    }

    private ResponseEntity<BaseResponse<Void>> getResponseEntity(
            HousingBusinessError errorCode,
            BaseResponse<Void> data,
            Throwable exception) {
        var status = errorCode.getHttpStatus();
        log.error("exception: {} | code: {} | message: {}", exception.getClass().getSimpleName(), errorCode.getCode(),
                errorCode.getMessage(), exception);
        return new ResponseEntity<>(data, status);
    }

    private BaseResponse<Void> getFailedResponse(HousingBusinessError errorCode, String errorMessage, List<FieldViolation> errors) {
        return BaseResponse.ofFailed(errorCode, errorMessage, errors);
    }

    private BaseResponse<Void> getFailedResponse(HousingBusinessError errorCode) {
        return BaseResponse.ofFailed(errorCode, errorCode.getMessage());
    }

    private BaseResponse<Void> getFailedResponse(HousingBusinessError errorCode, String
            errorMessage) {
        return BaseResponse.ofFailed(errorCode, errorMessage);
    }

//    @ExceptionHandler(AssignmentPolicyException.class)
//    public ResponseEntity<BaseResponse<Void>> handleAssignmentPolicyException(AssignmentPolicyException exception) {
//        BaseResponse<Void> resp = BaseResponse.ofFailed(exception.getErrorCode(), exception.getMessage());
//        return this.getResponseEntity(exception.getErrorCode(), resp, exception);
//    }
}
