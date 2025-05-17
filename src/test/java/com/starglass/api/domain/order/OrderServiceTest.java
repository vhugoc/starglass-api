package com.starglass.api.domain.order;

import com.google.common.collect.Lists;
import com.starglass.api.domain.order.orderproduct.OrderProductService;
import com.starglass.api.domain.product.ProductProvider;
import com.starglass.api.domain.product.ProductService;
import com.starglass.api.domain.user.UserProvider;
import com.starglass.api.infra.security.TokenService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class OrderServiceTest {

    @MockBean
    private TokenService tokenService;

    @MockBean
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderProductService orderProductService;

    @Test
    @DisplayName("Order - Calculate payment values")
    public void testCalculatePaymentValues() {
        Order.Builder orderBuilder = OrderProvider.provide(50.0, 10.0).toBuilder();
        applyMocks();

        orderService.calculatePaymentValues(orderBuilder);

        Assertions.assertAll(
                () -> Assertions.assertEquals(560.0, orderBuilder.getPayment().getRawValue()),
                () -> Assertions.assertEquals(50.0, orderBuilder.getProfitMargin()),
                () -> Assertions.assertEquals(10.0, orderBuilder.getDiscount()),
                () -> Assertions.assertEquals(756.0, orderBuilder.getPayment().getValue())
        );
    }

    @Test
    @DisplayName("Order - Calculate payment with no products")
    public void testCalculatePaymentWithNoProducts() {
        Order.Builder orderBuilder = OrderProvider.provide(50.0, 10.0).toBuilder();
        applyMocks();
        orderBuilder.withProducts(Lists.newLinkedList());

        orderService.calculatePaymentValues(orderBuilder);

        Assertions.assertAll(
                () -> Assertions.assertEquals(0.0, orderBuilder.getPayment().getRawValue()),
                () -> Assertions.assertEquals(50.0, orderBuilder.getProfitMargin()),
                () -> Assertions.assertEquals(10.0, orderBuilder.getDiscount()),
                () -> Assertions.assertEquals(0.0, orderBuilder.getPayment().getValue())
        );
    }

    @Test
    @DisplayName("Order - Calculate payment with no discount and profit")
    public void testCalculatePaymentWithNoDiscountAndProfit() {
        Order.Builder orderBuilder = OrderProvider.provide(0.0, 0.0).toBuilder();
        applyMocks();

        orderService.calculatePaymentValues(orderBuilder);

        Assertions.assertAll(
                () -> Assertions.assertEquals(560.0, orderBuilder.getPayment().getRawValue()),
                () -> Assertions.assertEquals(0.0, orderBuilder.getProfitMargin()),
                () -> Assertions.assertEquals(0.0, orderBuilder.getDiscount()),
                () -> Assertions.assertEquals(560.0, orderBuilder.getPayment().getValue())
        );
    }

    @Test
    @DisplayName("Order - Calculate profit margin")
    public void testCalculateProfitMargin() {
        Order.Builder orderBuilder = OrderProvider.provide(50.0, 10.0).toBuilder();
        applyMocks();
        orderProductService.calculate(orderBuilder.getProducts());

        Double valueWithProfitMargin = orderBuilder.profitMargin().getPayment().getValue();

        Assertions.assertAll(
                () -> Assertions.assertEquals(560.0, orderBuilder.getTotalValue()),
                () -> Assertions.assertEquals(50.0, orderBuilder.getProfitMargin()),
                () -> Assertions.assertEquals(840.0, valueWithProfitMargin)
        );
    }

    @Test
    @DisplayName("Order - Calculate discount")
    public void testCalculateDiscount() {
        Order.Builder orderBuilder = OrderProvider.provide(50.0, 10.0).toBuilder();
        applyMocks();
        orderProductService.calculate(orderBuilder.getProducts());

        Double valueWithDiscount = orderBuilder.discount().getPayment().getValue();

        Assertions.assertAll(
                () -> Assertions.assertEquals(560.0, orderBuilder.getTotalValue()),
                () -> Assertions.assertEquals(10.0, orderBuilder.getDiscount()),
                () -> Assertions.assertEquals(504.0, valueWithDiscount)
        );
    }

    public void applyMocks() {
        Mockito.when(tokenService.getAuthenticatedUserFromContext()).thenReturn(UserProvider.provide());
        Mockito.when(productService.findAllProductMaterials(Mockito.anyString())).thenReturn(ProductProvider.transparentDoor().getMaterials());
    }

}
