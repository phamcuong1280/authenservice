package com.example.accountservice.common.config.rest;

import com.example.accountservice.common.exception.constant.HousingBusinessError;
import com.example.accountservice.common.exception.constant.HousingException;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.collect.Lists;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Slf4j
public class BaseResponse<T> {
    public static final String OK_CODE = "200";
    private T data;
    private Metadata<T> meta = new Metadata<>();

    public static <T> BaseResponse<T> ofSucceeded(T data) {
        BaseResponse<T> response = new BaseResponse<>();
        response.data = data;
        response.meta.code = OK_CODE;
        return response;
    }

    public static <T> BaseResponse<List<T>> ofSucceeded(Page<T> data) {
        BaseResponse<List<T>> response = new BaseResponse<>();
        response.data = data.getContent();
        response.meta.code = OK_CODE;
        response.meta.page = data.getPageable().getPageNumber();
        response.meta.size = data.getPageable().getPageSize();
        response.meta.total = data.getTotalElements();
        return response;
    }

    public static <T> BaseResponse<T> ofSucceeded() {
        BaseResponse<T> response = new BaseResponse<>();
        response.meta.code = OK_CODE;
        return response;
    }

    public static BaseResponse<Void> ofFailed(HousingBusinessError errorCode) {
        return ofFailed(errorCode, null);
    }

    public static <T> BaseResponse<T> ofFailed(HousingBusinessError errorCode, String message) {
        return ofFailed(errorCode, message, null);
    }

    public static <T> BaseResponse<T> ofFailed(HousingBusinessError errorCode, String message, List<FieldViolation> errors) {
        BaseResponse<T> response = new BaseResponse<>();
        response.meta.code = String.valueOf(errorCode.getCode());
        response.meta.message = (message != null) ? message : errorCode.getMessage();
        if (Objects.nonNull(errorCode.getErrorData())) {
            response.meta.data = (T) errorCode.getErrorData();
        }
        response.meta.errors = (errors != null) ? Lists.newArrayList(errors) : null;
        return response;
    }

    public static <T> BaseResponse<T> ofFailed(HousingException exception) {
        return ofFailed(exception.getErrorCode(), exception.getMessage());
    }

    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Metadata<T> {
        private String code;
        private Integer page;
        private Integer size;
        private Long total;
        private T data;
        private List<FieldViolation> errors;
        private String message;
        private String requestId;
        // for api : opportunities/expiration-soon
        private Long expirationDays;
        // for api : /notifications
        private Long unreadCount;

        // filters meta
        private Map<String, Object> filters;
        private Map<String, Object> referenceData;
    }
}
