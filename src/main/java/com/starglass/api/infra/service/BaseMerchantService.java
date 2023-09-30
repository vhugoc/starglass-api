package com.starglass.api.infra.service;

import com.starglass.api.infra.entity.BaseMerchantEntity;
import org.springframework.stereotype.Service;

@Service
public interface BaseMerchantService<T extends BaseMerchantEntity, B extends BaseMerchantEntity.Builder> extends BaseService<T, B> {

}
