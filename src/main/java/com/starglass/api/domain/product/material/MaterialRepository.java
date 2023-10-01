package com.starglass.api.domain.product.material;

import com.starglass.api.infra.repository.BaseMerchantRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialRepository extends BaseMerchantRepository<Material>, QuerydslPredicateExecutor<Material> {

}
