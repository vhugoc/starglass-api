package com.starglass.api.domain.customer;

import com.starglass.api.infra.repository.BaseMerchantRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends BaseMerchantRepository<Customer>, QuerydslPredicateExecutor<Customer> {

}
