package com.starglass.api.domain.order;

import com.starglass.api.domain.customer.CustomerProvider;
import com.starglass.api.domain.merchant.MerchantProvider;
import com.starglass.api.domain.order.orderproduct.OrderProductProvider;
import com.starglass.api.domain.payment.Payment;
import com.starglass.api.domain.payment.PaymentStatus;
import com.starglass.api.domain.payment.PaymentType;
import com.starglass.api.domain.product.ProductProvider;

import java.util.UUID;

public class OrderProvider {

    public static Order provide(Float profitMargin, Float discount) {
        return Order.of(MerchantProvider.provide())
                .withId(UUID.randomUUID().toString())
                .withProducts(OrderProductProvider.provide(ProductProvider.transparentDoor(), 2))
                .withCustomer(CustomerProvider.provide())
                .withStatus(OrderStatus.OPEN)
                .withProfitMargin(profitMargin)
                .withDiscount(discount)
                .withPayment(Payment.of().withStatus(PaymentStatus.PENDING).withType(PaymentType.BOLETO))
                .build();
    }

}
