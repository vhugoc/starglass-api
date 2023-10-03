package com.starglass.api.infra.entity;

import com.starglass.api.infra.exception.custom.ValidationException;
import org.springframework.stereotype.Service;

@Service
public class EntityValidatorImpl<T> implements EntityValidator<T> {

    @Override
    public void validate(T entity) throws ValidationException {

    }

}
