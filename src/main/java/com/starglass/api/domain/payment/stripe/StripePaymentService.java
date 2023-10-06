package com.starglass.api.domain.payment.stripe;

import com.starglass.api.domain.payment.PaymentService;
import org.springframework.stereotype.Service;

@Service
public abstract class StripePaymentService extends PaymentService<StripePayment, StripePayment.Builder> {

}
