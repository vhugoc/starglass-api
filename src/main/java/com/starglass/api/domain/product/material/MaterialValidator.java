package com.starglass.api.domain.product.material;

import com.starglass.api.infra.entity.EntityValidator;
import com.starglass.api.infra.exception.custom.ValidationException;
import org.springframework.stereotype.Service;

@Service
public class MaterialValidator implements EntityValidator<Material> {

    @Override
    public void validate(Material entity) throws ValidationException {

    }

}
