package com.starglass.api.domain.payment;

import com.starglass.api.infra.repository.BaseMerchantRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository<T extends Payment> extends BaseMerchantRepository<T>, QuerydslPredicateExecutor<T> {

}
