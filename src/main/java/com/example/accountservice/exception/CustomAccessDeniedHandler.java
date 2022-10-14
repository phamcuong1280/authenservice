package com.example.applicationgateway.exception;

import com.example.applicationgateway.config.rest.BaseResponse;
import com.example.applicationgateway.exception.constant.HousingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);

//        final Map<String, Object> body = new HashMap<>();
//        body.put("status", HttpServletResponse.SC_FORBIDDEN);
//        body.put("error", "Authorization");
//        body.put("message", accessDeniedException.getMessage());
//        body.put("path", request.getServletPath());

        final ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), BaseResponse.ofFailed(new HousingException(HousingErrors.FORBIDDEN_ERROR)));

    }
}
