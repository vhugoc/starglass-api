package com.starglass.api.controllers.order;

import com.starglass.api.domain.order.Order;
import com.starglass.api.domain.order.OrderRepository;
import com.starglass.api.domain.order.OrderService;
import com.starglass.api.infra.rest.BaseMerchantCrud;
import com.starglass.api.infra.rest.RestURL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping(RestURL.ORDER)
public class OrderController extends BaseMerchantCrud<Order, Order.Builder> {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderController(OrderRepository orderRepository, OrderService orderService) {
        super(orderService);
        this.orderRepository = orderRepository;
    }

}
