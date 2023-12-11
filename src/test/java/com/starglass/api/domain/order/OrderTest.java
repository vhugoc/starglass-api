package com.starglass.api.domain.order;

import com.starglass.api.args.Address;
import com.starglass.api.infra.exception.custom.BuilderException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class OrderTest {

    @Test
    @DisplayName("Order - Build open order")
    public void testBuildOpenOrder() {
        Order.Builder order = OrderProvider.provide(0F, 0F).toBuilder()
                .withStatus(OrderStatus.OPEN);

        Assertions.assertInstanceOf(Order.class, order.build());
    }

    @Test
    @DisplayName("Order - Build closed order")
    public void testBuildClosedOrder() {
        Order.Builder order = OrderProvider.provide(0F, 0F).toBuilder()
                .withInstallDate(LocalDateTime.of(2024, 1, 1, 15, 0))
                .withInstallAddress(Address.of().withPostalCode("00000-000").build())
                .withStatus(OrderStatus.CLOSED);

        Assertions.assertInstanceOf(Order.class, order.build());
    }

    @Test
    @DisplayName("Order - Build closed order with no installment address and date")
    public void testBuildClosedOrderWithNoAddressAndDate() {
        Order.Builder order = OrderProvider.provide(0F, 0F).toBuilder()
                .withStatus(OrderStatus.CLOSED);

        Assertions.assertThrowsExactly(BuilderException.class, order::build);
    }

    @Test
    @DisplayName("Order - Build closed order with no installment address")
    public void testBuildClosedOrderWithNoAddress() {
        Order.Builder order = OrderProvider.provide(0F, 0F).toBuilder()
                .withInstallDate(LocalDateTime.of(2024, 1, 1, 0, 0))
                .withStatus(OrderStatus.CLOSED);

        Assertions.assertThrowsExactly(BuilderException.class, order::build);
    }

    @Test
    @DisplayName("Order - Build closed order with no installment date")
    public void testBuildClosedOrderWithNoDate() {
        Order.Builder order = OrderProvider.provide(0F, 0F).toBuilder()
                .withInstallAddress(Address.of().withPostalCode("00000-000").build())
                .withStatus(OrderStatus.CLOSED);

        Assertions.assertThrowsExactly(BuilderException.class, order::build);
    }

}
