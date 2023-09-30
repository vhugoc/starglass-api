package com.starglass.api.infra.repository;

import com.starglass.api.infra.entity.BaseMerchantEntity;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
@Repository
public interface BaseMerchantRepository<T extends BaseMerchantEntity> extends BaseRepository<T> {

    List<T> findAllByMerchantIdAndIsActiveTrue(String merchantId);

    Optional<T> findByIdAndMerchantId(String id, String merchantId);

    Optional<T> findByIdAndMerchantIdAndIsActiveTrue(String id, String merchantId);

}
