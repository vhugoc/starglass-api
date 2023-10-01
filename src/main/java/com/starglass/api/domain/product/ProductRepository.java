package com.starglass.api.domain.product;

import com.starglass.api.infra.repository.BaseMerchantRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends BaseMerchantRepository<Product>, QuerydslPredicateExecutor<Product> {

}
