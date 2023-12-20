package com.starglass.api.domain.order;

import com.starglass.api.domain.order.orderproduct.OrderProductService;
import com.starglass.api.infra.service.BaseMerchantServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService extends BaseMerchantServiceImpl<Order, Order.Builder> {

    private final OrderProductService orderProductService;

    @Autowired
    public OrderService(OrderProductService orderProductService) {
        this.orderProductService = orderProductService;
    }

    @Override
    public Order.Builder beforeSave(Order.Builder builder) {
        return this.calculatePaymentValues(builder);
    }

    public Order.Builder calculatePaymentValues(Order.Builder builder) {
        orderProductService.calculate(builder.getProducts());
        builder.profitMargin().discount();
        builder.getPayment().withRawValue(builder.getTotalValue());
        return builder;
    }

    public Float calculateDiscount(Float value, Float discount) {
        return value - ((discount / 100) * value);
    }

    public Float calculateProfitMargin(Float value, Float profit) {
        return value + ((profit / 100) * value);
    }

}
