package com.example.applicationgateway.config.rest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.function.Consumer;

public final class RestUtils {

    public static final String NEW_USER_ID_HEADER = "User-ID";

    private RestUtils() {
    }

    public static HttpHeaders createHeadersWithBasicAuth(String username, String password) {
        var headers = new HttpHeaders();
        headers.setBasicAuth(username, password);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        return headers;
    }

    public static Consumer<HttpHeaders> setUserIdToConsumerHeaders(HttpHeaders headers, String actorUuid) {
        return httpHeaders -> {
            headers.add(NEW_USER_ID_HEADER, actorUuid);
        };
    }

    public static HttpHeaders createHeadersWithoutBasicAuth() {
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        return headers;
    }
}
