package com.example.accountservice.common.exception;


import com.example.accountservice.common.exception.constant.ExceptionCustom;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private ExceptionCustom resolve(MethodArgumentTypeMismatchException exception) {
        return new ExceptionCustom(MyErrors.INVALID_PARAMETERS, exception.getMessage(), exception);
    }

    private String getNormalizeErrorMessage(HttpMessageNotReadableException exception) {
        var errorMessage = exception.getMessage();
        if (errorMessage == null) return Strings.EMPTY;
        int idx = errorMessage.indexOf(':');
        return idx > 0 ? errorMessage.substring(0, idx) : errorMessage;
    }


//    @ExceptionHandler(AssignmentPolicyException.class)
//    public ResponseEntity<BaseResponse<Void>> handleAssignmentPolicyException(AssignmentPolicyException exception) {
//        BaseResponse<Void> resp = BaseResponse.ofFailed(exception.getErrorCode(), exception.getMessage());
//        return this.getResponseEntity(exception.getErrorCode(), resp, exception);
//    }
}
