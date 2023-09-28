package com.starglass.api.infra.service;

import com.starglass.api.domain.user.User;
import com.starglass.api.infra.entity.BaseUserEntity;
import com.starglass.api.infra.repository.BaseUserRepository;
import com.starglass.api.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

public class BaseUserServiceImpl<T extends BaseUserEntity, B extends BaseUserEntity.Builder> extends BaseServiceImpl<T, B> implements BaseUserService<T, B> {

    @Autowired
    private BaseUserRepository<T> repository;

    @Autowired
    TokenService tokenService;

    @Override
    public BaseServiceResponse<List<T>>findAll() {
        String merchantId = tokenService.getAuthenticatedUserFromContext().getMerchant().getId();
        List<T> list = repository.findAllByMerchantIdAndIsActiveTrue(merchantId);
        return BaseServiceResponse.<List<T>>builder()
                .withStatusCode(HttpStatus.OK)
                .withData(list)
                .build();
    }

    @Override
    public BaseServiceResponse<T>findById(String id) {
        String merchantId = tokenService.getAuthenticatedUserFromContext().getMerchant().getId();
        Optional<T> entity = repository.findByIdAndMerchantId(id, merchantId);
        BaseServiceResponse.BaseServiceResponseBuilder<T> baseServiceResponseBuilder = BaseServiceResponse.builder();
        if (entity.isPresent()) {
            baseServiceResponseBuilder.withStatusCode(HttpStatus.OK).withData(entity.get());
        } else {
            baseServiceResponseBuilder.withStatusCode(HttpStatus.BAD_REQUEST).withMessage("Entity does not exists");
        }
        return baseServiceResponseBuilder.build();
    }

    @Override
    public BaseServiceResponse<T> save(B entityBuilder) {
        User user = tokenService.getAuthenticatedUserFromContext();
        return super.save((B) entityBuilder.withUser(user));
    }

    @Override
    public BaseServiceResponse<T> update(String id, B entityBuilder) {
        User user = tokenService.getAuthenticatedUserFromContext();
        Optional<T> entity = repository.findByIdAndMerchantId(id, user.getId());
        if (entity.isPresent()) {
            return super.update(id, (B) entityBuilder.withUser(user));
        } else {
            return BaseServiceResponse.<T>builder()
                    .withStatusCode(HttpStatus.BAD_REQUEST)
                    .withMessage("Entity does not exists")
                    .build();
        }
    }

    @Override
    public BaseServiceResponse<T> delete(String id) {
        User user = tokenService.getAuthenticatedUserFromContext();
        Optional<T> entity = repository.findByIdAndMerchantId(id, user.getMerchant().getId());
        if (entity.isPresent()) {
            return super.update(id, (B) entity.get().toBuilder().withIsActive(false));
        } else {
            return BaseServiceResponse.<T>builder()
                    .withStatusCode(HttpStatus.BAD_REQUEST)
                    .withMessage("Entity does not exists")
                    .build();
        }
    }

}
