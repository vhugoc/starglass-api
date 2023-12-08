package com.starglass.api.domain.product.material;

import com.querydsl.core.BooleanBuilder;
import com.starglass.api.domain.user.User;
import com.starglass.api.infra.entity.EntityValidator;
import com.starglass.api.infra.exception.custom.ValidationException;
import com.starglass.api.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MaterialValidator implements EntityValidator<Material> {

    private final MaterialRepository materialRepository;

    private final TokenService tokenService;

    @Autowired
    public MaterialValidator(MaterialRepository materialRepository, TokenService tokenService) {
        this.materialRepository = materialRepository;
        this.tokenService = tokenService;
    }

    @Override
    public void validate(Material entity) throws ValidationException {
        this.validateNotExists(entity);
    }

    public void validateNotExists(Material entity) {
        User authenticatedUser = tokenService.getAuthenticatedUserFromContext();
        boolean isUpdate = entity.getId() != null;

        QMaterial qMaterial = QMaterial.material;
        BooleanBuilder queryBuilder = new BooleanBuilder();

        queryBuilder.and(qMaterial.merchant.eq(authenticatedUser.getMerchant()));
        queryBuilder.and(qMaterial.name.eq(entity.getName()).and(qMaterial.type.eq(entity.getType())));
        if (entity.getColor() != null) queryBuilder.and(qMaterial.color.eq(entity.getColor()));
        if (entity.getThickness() != null) queryBuilder.and(qMaterial.thickness.eq(entity.getThickness()));

        if (isUpdate) queryBuilder.and(qMaterial.id.ne(entity.getId()));

        if (this.materialRepository.count(queryBuilder) > 0)
            throw new ValidationException("This material already exists");

    }

}
