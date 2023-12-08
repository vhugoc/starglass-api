package com.starglass.api.domain.product;

import com.querydsl.core.BooleanBuilder;
import com.starglass.api.domain.user.User;
import com.starglass.api.infra.entity.EntityValidator;
import com.starglass.api.infra.exception.custom.ValidationException;
import com.starglass.api.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductValidator implements EntityValidator<Product> {

    private final ProductRepository productRepository;

    private final TokenService tokenService;

    @Autowired
    public ProductValidator(ProductRepository productRepository, TokenService tokenService) {
        this.productRepository = productRepository;
        this.tokenService = tokenService;
    }

    @Override
    public void validate(Product entity) throws ValidationException {
        this.validateNotExists(entity);
    }

    public void validateNotExists(Product entity) {
        User authenticatedUser = tokenService.getAuthenticatedUserFromContext();
        boolean isUpdate = entity.getId() != null;

        QProduct qProduct = QProduct.product;
        BooleanBuilder queryBuilder = new BooleanBuilder();

        queryBuilder.and(qProduct.merchant.eq(authenticatedUser.getMerchant()));
        queryBuilder.and(qProduct.name.eq(entity.getName()));

        if (isUpdate) queryBuilder.and(qProduct.id.ne(entity.getId()));

        if (this.productRepository.count(queryBuilder) > 0)
            throw new ValidationException("This product already exists");

    }

}
