package com.starglass.api.infra.service;

import com.starglass.api.domain.user.User;
import com.starglass.api.infra.entity.BaseMerchantEntity;
import com.starglass.api.infra.repository.BaseMerchantRepository;
import com.starglass.api.args.Pagination;
import com.starglass.api.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

public class BaseMerchantServiceImpl<T extends BaseMerchantEntity, B extends BaseMerchantEntity.Builder> extends BaseServiceImpl<T, B> implements BaseMerchantService<T, B> {

    @Autowired
    private BaseMerchantRepository<T> repository;

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
    public BaseServiceResponse<PageImpl<T>>findAll(Pagination pagination) {
        String merchantId = tokenService.getAuthenticatedUserFromContext().getMerchant().getId();
        PageImpl<T> list = repository.findAllByMerchantIdAndIsActiveTrue(merchantId, pagination.pageable());
        return BaseServiceResponse.<PageImpl<T>>builder()
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
        return super.save((B) entityBuilder.withMerchant(user.getMerchant()));
    }

    @Override
    public BaseServiceResponse<T> update(String id, B entityBuilder) {
        User user = tokenService.getAuthenticatedUserFromContext();
        Optional<T> entity = repository.findByIdAndMerchantId(id, user.getMerchant().getId());
        if (entity.isPresent()) {
            return super.update(id, (B) entityBuilder.withMerchant(user.getMerchant()));
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
