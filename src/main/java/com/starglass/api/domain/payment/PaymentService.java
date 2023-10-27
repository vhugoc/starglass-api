package com.starglass.api.domain.payment;

import com.starglass.api.infra.service.BaseMerchantServiceImpl;
import org.springframework.stereotype.Service;

@Service
public abstract class PaymentService<T extends Payment, B extends Payment.Builder> extends BaseMerchantServiceImpl<T, B> {

}
