package com.starglass.api.infra.service;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder(setterPrefix = "with")
public class BaseServiceResponse<T> {

    private HttpStatus statusCode;

    private String message;

    private T data;

}
