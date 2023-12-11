package com.starglass.api.infra.exception.handler;

import com.starglass.api.infra.exception.custom.BuilderException;
import com.starglass.api.infra.exception.custom.RestException;
import com.starglass.api.infra.exception.custom.ValidationException;
import com.starglass.api.infra.service.BaseServiceResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
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

    @ExceptionHandler(ValidationException.class)
    @ResponseBody
    public ResponseEntity<BaseServiceResponse<?>> handleValidationException(ValidationException ex) {
        BaseServiceResponse<?> response = BaseServiceResponse.<Object>builder()
                .withStatusCode(HttpStatus.BAD_REQUEST)
                .withMessage(ex.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(BuilderException.class)
    @ResponseBody
    public ResponseEntity<BaseServiceResponse<?>> handleBuilderException(BuilderException ex) {
        BaseServiceResponse<?> response = BaseServiceResponse.<Object>builder()
                .withStatusCode(HttpStatus.BAD_REQUEST)
                .withMessage(ex.getMessage())
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<BaseServiceResponse<?>> handleArgumentNotValidException(MethodArgumentNotValidException ex) {
        BaseServiceResponse<?> response = BaseServiceResponse.<Object>builder()
                .withStatusCode(HttpStatus.BAD_REQUEST)
                .withMessage("Invalid payload")
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

}
