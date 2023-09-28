package com.starglass.api.infra.service;

import com.starglass.api.infra.entity.BaseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BaseService<T extends BaseEntity, B extends BaseEntity.Builder> {

    BaseServiceResponse<List<T>> findAll();

    BaseServiceResponse<T> findById(String id);

    BaseServiceResponse<T> save(B entity);

    BaseServiceResponse<T> update(String id, B entity);

    BaseServiceResponse<T> delete(String id);

}
