package com.starglass.api.infra.rest;

import com.starglass.api.infra.entity.BaseMerchantEntity;
import com.starglass.api.infra.service.BaseMerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public abstract class BaseMerchantCrud<T extends BaseMerchantEntity, B extends BaseMerchantEntity.Builder> extends BaseCrud {

    private final BaseMerchantService<T, B> service;

    @Autowired
    public BaseMerchantCrud(BaseMerchantService<T, B> service) {
        super(service);
        this.service = service;
    }

}
