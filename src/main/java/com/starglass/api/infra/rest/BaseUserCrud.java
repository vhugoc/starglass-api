package com.starglass.api.infra.rest;

import com.starglass.api.infra.entity.BaseUserEntity;
import com.starglass.api.infra.service.BaseUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public abstract class BaseUserCrud<T extends BaseUserEntity, B extends BaseUserEntity.Builder> extends BaseCrud {

    private final BaseUserService<T, B> service;

    @Autowired
    public BaseUserCrud(BaseUserService<T, B> service) {
        super(service);
        this.service = service;
    }

}
