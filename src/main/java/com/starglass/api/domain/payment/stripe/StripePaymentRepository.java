package com.starglass.api.domain.payment.stripe;

import com.starglass.api.domain.payment.PaymentRepository;
import com.starglass.api.infra.repository.BaseMerchantRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface StripePaymentRepository extends PaymentRepository<StripePayment> {

}
