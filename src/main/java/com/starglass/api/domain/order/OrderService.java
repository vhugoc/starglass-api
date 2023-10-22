package com.starglass.api.domain.order;

import com.starglass.api.domain.order.orderproduct.OrderProduct;
import com.starglass.api.domain.order.orderproduct.OrderProductService;
import com.starglass.api.infra.service.BaseMerchantServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService extends BaseMerchantServiceImpl<Order, Order.Builder> {

    private final OrderProductService orderProductService;

    @Autowired
    public OrderService(OrderProductService orderProductService) {
        this.orderProductService = orderProductService;
    }

    @Override
    public Order.Builder beforeSave(Order.Builder builder) {
        orderProductService.calculate(builder.getProducts());
        builder.getPayment().withValue(builder.getTotalValue());
        return builder;
    }

}
