package com.starglass.api.infra.service;

import com.starglass.api.infra.entity.BaseEntity;
import com.starglass.api.infra.entity.EntityValidator;
import com.starglass.api.infra.exception.custom.RestException;
import com.starglass.api.infra.exception.custom.ValidationException;
import com.starglass.api.infra.repository.BaseRepository;
import com.starglass.api.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

public class BaseServiceImpl<T extends BaseEntity, B extends BaseEntity.Builder> implements BaseService<T, B> {

    @Autowired
    private BaseRepository<T> repository;

    @Autowired
    TokenService tokenService;

    @Autowired
    EntityValidator<T> entityValidator;

    @Override
    public BaseServiceResponse<List<T>> findAll() {
        List<T> list = repository.findAllByIsActiveTrue();
        return BaseServiceResponse.<List<T>>builder()
                .withStatusCode(HttpStatus.OK)
                .withData(list)
                .build();
    }

    @Override
    public BaseServiceResponse<T> findById(String id) {
        Optional<T> entity = repository.findById(id);
        BaseServiceResponse.BaseServiceResponseBuilder<T> baseServiceResponseBuilder = BaseServiceResponse.builder();
        if (entity.isPresent()) {
            baseServiceResponseBuilder.withStatusCode(HttpStatus.OK).withData(entity.get());
        } else {
            baseServiceResponseBuilder.withStatusCode(HttpStatus.BAD_REQUEST);
        }
        return baseServiceResponseBuilder.build();
    }

    @Override
    public BaseServiceResponse<T> save(B entityBuilder) {
        entityBuilder = this.beforeSave(entityBuilder);
        T entity = (T) entityBuilder.build();
        try {
            entityValidator.validate(entity);
            T saved = repository.save(entity);
            saved = this.afterSave(saved);
            return BaseServiceResponse.<T>builder()
                    .withStatusCode(HttpStatus.CREATED)
                    .withData(saved)
                    .build();
        } catch (ValidationException e) {
            throw new ValidationException(e.getMessage());
        } catch (Exception e) {
            throw new RestException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public BaseServiceResponse<T> update(String id, B entityBuilder) {
        entityBuilder = this.beforeSave(entityBuilder);
        T entity = (T) entityBuilder.withId(id).build();
        try {
            if (entity == null || entity.getId() == null) throw new RestException("Not found", HttpStatus.BAD_REQUEST);
            entityValidator.validate(entity);
            T updated = repository.save(entity);
            updated = this.afterSave(entity);
            return BaseServiceResponse.<T>builder()
                    .withStatusCode(HttpStatus.OK)
                    .withData(updated)
                    .build();
        } catch (ValidationException e) {
            throw new ValidationException(e.getMessage());
        } catch (Exception e) {
            throw new RestException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public BaseServiceResponse<T> delete(String id) {
        try {
            Optional<T> entity = repository.findById(id);
            if (entity.isPresent()) {
                return this.update(id, (B) entity.get().toBuilder().withIsActive(false));
            } else {
                return BaseServiceResponse.<T>builder()
                        .withStatusCode(HttpStatus.BAD_REQUEST)
                        .withMessage("Entity does not exists")
                        .build();
            }
        } catch (Exception e) {
            throw new RestException(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public B beforeSave(B entityBuilder) {
        return entityBuilder;
    }

    @Override
    public T afterSave(T saved) {
        return saved;
    }

}
