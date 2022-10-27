package com.example.accountservice.common.web;

import com.example.accountservice.common.exception.HousingErrors;
import com.example.accountservice.common.exception.constant.HousingBusinessError;
import com.example.accountservice.common.exception.constant.HousingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Slf4j
@Component
public abstract class ServiceClient {
    @Autowired
    protected WebClient webClient;

    protected String ServiceName() {
        var parts = this.getClass().getName().split("\\.");
        if (parts.length > 0) {
            return parts[parts.length - 1];
        }
        return this.getClass().getName();
    }

    public <T, E extends Resource<T>> T get(String uri, Class<E> responseType, String basicAuth) {

        var monoResp =
                this.webClient
                        .get()
                        .uri(uri)
                        .accept(MediaType.APPLICATION_JSON)
                        .headers(a -> a.setBasicAuth(basicAuth))
                        .retrieve()
                        .onStatus(
                                HttpStatus::isError,
                                response -> {
                                    log.error("{} call get path {} got error {}", ServiceName(), uri, response);
                                    return response.bodyToMono(responseType).map(resp -> {
                                        if (Objects.nonNull(resp.getMeta())) {
                                            var status = response.statusCode();
                                            throw new HousingException(new HousingBusinessError(
                                                    resp.getMeta().getCode(),
                                                    resp.getMeta().getMessage(),
                                                    status
                                            ));
                                        }
                                        throw new HousingException(new HousingBusinessError(resp.getMeta().getCode(), resp.getMeta().getMessage(), response.statusCode()));
                                    });
                                }
                        )
                        .bodyToMono(responseType);

        var resp = monoResp.block();
        if (resp == null || resp.getData() == null) {
            throw new HousingException(new HousingBusinessError(HousingErrors.INTERNAL_SERVER_ERROR.getCode(), String.format("%s call get %s then get exception: unknown", ServiceName(), uri), HttpStatus.INTERNAL_SERVER_ERROR));
        }
        return resp.getData();
    }

    public <T, B, R extends Resource<T>> T post(String uri, B body, Class<B> bClass, Class<R> responseType) {
        var monoResp =
                this.webClient
                        .post()
                        .uri(uri)
                        .accept(MediaType.APPLICATION_JSON)
                        .headers(a -> a.setBasicAuth("user", "password"))
                        .body(Mono.just(body), bClass)
                        .retrieve()
                        .onStatus(
                                HttpStatus::isError,
                                response -> {
                                    log.error("{} post path {}, body {} got error {}", ServiceName(), uri, body, response);
                                    return response.bodyToMono(responseType).map(resp -> {
                                        if (Objects.nonNull(resp.getMeta())) {
                                            var status = response.statusCode();
                                            throw new HousingException(new HousingBusinessError(
                                                    resp.getMeta().getCode(),
                                                    resp.getMeta().getMessage(),
                                                    status
                                            ));
                                        }
                                        throw new HousingException(new HousingBusinessError(resp.getMeta().getCode(), resp.getMeta().getMessage(), response.statusCode()));
                                    });
                                }
                        )
                        .bodyToMono(responseType);

        var resp = monoResp.block();
        if (resp == null || resp.getData() == null) {
            throw new HousingException(new HousingBusinessError(HousingErrors.INTERNAL_SERVER_ERROR.getCode(), String.format("%s call post %s then get exception: unknown", ServiceName(), uri), HttpStatus.INTERNAL_SERVER_ERROR));
        }
        return resp.getData();
    }

    public <T, B, R extends Resource<T>> T put(String uri, B body, Class<B> bClass, Class<R> responseType) {
        Mono<R> monoResp;
        monoResp = this.webClient
                .put()
                .uri(uri)
                .headers(a -> a.setBasicAuth("user", "password"))
                .body(Mono.just(body), bClass)
                .retrieve()
                .onStatus(
                        HttpStatus::isError,
                        response -> {
                            log.error("{} post path {}, body {} got error {}", ServiceName(), uri, body, response);
                            return response.bodyToMono(responseType).map(resp -> {
                                if (Objects.nonNull(resp.getMeta())) {
                                    var status = response.statusCode();
                                    throw new HousingException(new HousingBusinessError(
                                            resp.getMeta().getCode(),
                                            resp.getMeta().getMessage(),
                                            status
                                    ));
                                }
                                throw new HousingException(new HousingBusinessError(resp.getMeta().getCode(), resp.getMeta().getMessage(), response.statusCode()));
                            });
                        }
                )
                .bodyToMono(responseType);

        var resp = monoResp.block();
        if (resp == null || resp.getData() == null) {
            throw new HousingException(new HousingBusinessError(HousingErrors.INTERNAL_SERVER_ERROR.getCode(), String.format("%s call post %s then get exception: unknown", ServiceName(), uri), HttpStatus.INTERNAL_SERVER_ERROR));
        }
        return resp.getData();
    }

    public <T, E extends Resource<T>> T delete(String uri, Class<E> responseType) {
        var monoResp =
                this.webClient
                        .delete()
                        .uri(uri)
                        .retrieve()
                        .onStatus(
                                HttpStatus::isError,
                                response -> {
                                    log.error("{} call delete path {} got error {}", ServiceName(), uri, response);
                                    return response.bodyToMono(responseType).map(resp -> {
                                        if (Objects.nonNull(resp.getMeta())) {
                                            resp.getMeta().getCode();
                                            var status = response.statusCode();
                                            throw new HousingException(new HousingBusinessError(
                                                    resp.getMeta().getCode(),
                                                    resp.getMeta().getMessage(),
                                                    status
                                            ));
                                        }
                                        throw new HousingException(new HousingBusinessError(resp.getMeta().getCode(), resp.getMeta().getMessage(), response.statusCode()));
                                    });
                                }
                        )
                        .bodyToMono(responseType);

        var resp = monoResp.block();
        if (resp == null || resp.getData() == null) {
            throw new HousingException(new HousingBusinessError(HousingErrors.INTERNAL_SERVER_ERROR.getCode(), String.format("%s call delete %s then get exception: unknown", ServiceName(), uri), HttpStatus.INTERNAL_SERVER_ERROR));
        }
        return resp.getData();
    }
}
