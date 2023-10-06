package com.starglass.api.domain.order;

import com.starglass.api.infra.repository.BaseMerchantRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends BaseMerchantRepository<Order>, QuerydslPredicateExecutor<Order> {

}
