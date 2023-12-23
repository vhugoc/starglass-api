package com.starglass.api.infra.service;

import com.starglass.api.infra.entity.BaseEntity;
import com.starglass.api.args.Pagination;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BaseService<T extends BaseEntity, B extends BaseEntity.Builder> {

    B beforeSave(B entityBuilder);

    T afterSave(T saved);

    BaseServiceResponse<List<T>> findAll();

    BaseServiceResponse<PageImpl<T>> findAll(Pagination pagination);

    BaseServiceResponse<T> findById(String id);

    BaseServiceResponse<T> save(B entity);

    BaseServiceResponse<T> update(String id, B entity);

    BaseServiceResponse<T> delete(String id);

}
