package com.starglass.api.infra.exception.handler;

import com.starglass.api.infra.exception.custom.RestException;
import com.starglass.api.infra.service.BaseServiceResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(RestException.class)
    @ResponseBody
    public ResponseEntity<BaseServiceResponse<?>> handleRestException(RestException ex) {
        BaseServiceResponse<?> response = BaseServiceResponse.<Object>builder()
                .withStatusCode(ex.getHttpStatus())
                .withMessage(ex.getMessage())
                .build();

        return ResponseEntity.status(ex.getHttpStatus()).body(response);
    }

}
