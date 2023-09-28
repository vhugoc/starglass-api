package com.starglass.api.infra.service;

import com.starglass.api.infra.entity.BaseUserEntity;
import org.springframework.stereotype.Service;

@Service
public interface BaseUserService<T extends BaseUserEntity, B extends BaseUserEntity.Builder> extends BaseService<T, B> {

}
