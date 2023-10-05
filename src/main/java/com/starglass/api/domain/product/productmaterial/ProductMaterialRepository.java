package com.starglass.api.domain.product.productmaterial;

import com.starglass.api.infra.repository.BaseMerchantRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductMaterialRepository extends BaseMerchantRepository<ProductMaterial>, QuerydslPredicateExecutor<ProductMaterial> {

}
