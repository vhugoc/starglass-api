package com.starglass.api.domain.order.orderproduct;

import com.starglass.api.infra.repository.BaseMerchantRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderProductRepository extends BaseMerchantRepository<OrderProduct>, QuerydslPredicateExecutor<OrderProduct> {

}
