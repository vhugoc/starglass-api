package com.starglass.api.infra.entity;

import com.starglass.api.infra.exception.custom.ValidationException;

public interface EntityValidator<T> {

    void validate(T entity) throws ValidationException;

}
