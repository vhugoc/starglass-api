package com.starglass.api.domain.order;

import com.google.common.collect.Lists;
import com.starglass.api.args.Address;
import com.starglass.api.domain.customer.Customer;
import com.starglass.api.domain.merchant.Merchant;
import com.starglass.api.domain.order.orderproduct.OrderProduct;
import com.starglass.api.domain.payment.stripe.StripePayment;
import com.starglass.api.infra.entity.BaseMerchantEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Entity(name = "orders")
@Getter
@ToString
public class Order extends BaseMerchantEntity<Order, Order.Builder> {

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderProduct> products = Lists.newLinkedList();

    @ManyToOne
    private Customer customer;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private LocalDateTime installDate;

    private Address installAddress;

    private Float profitMargin = 0F;

    private Float discount = 0F;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private StripePayment payment;

    public Order() {
    }

    protected Order(Builder builder) {
        super(builder);
        this.products = builder.products.stream().map(p -> p.withOrder(this).build()).collect(Collectors.toList());
        this.customer = builder.customer;
        this.status = builder.status;
        this.installDate = builder.installDate;
        this.installAddress = builder.installAddress;
        this.profitMargin = builder.profitMargin;
        this.discount = builder.discount;
        this.payment = builder.payment.withOrder(this).build();
    }

    @Override
    public Builder toBuilder() {
        return new Builder(this);
    }

    public static Builder of(Merchant merchant) {
        return new Builder(merchant);
    }

    public static Builder of(Order order) {
        return new Builder(order);
    }

    @Getter
    public static class Builder extends BaseMerchantEntity.Builder<Order, Builder> {

        private List<OrderProduct.Builder> products = Lists.newLinkedList();

        private Customer customer;

        @Enumerated(EnumType.STRING)
        private OrderStatus status;

        private LocalDateTime installDate;

        private Address installAddress;

        private Float profitMargin = 0F;

        private Float discount = 0F;

        private StripePayment.Builder payment;

        public Builder() {
        }

        public Builder(Merchant merchant) {
            super(merchant);
        }

        public Builder(Order order) {
            super(order);
            this.products = order.products.stream().map(OrderProduct::toBuilder).collect(Collectors.toList());
            this.customer = order.customer;
            this.status = order.status;
            this.installDate = order.installDate;
            this.installAddress = order.installAddress;
            this.profitMargin = order.profitMargin;
            this.discount = order.discount;
            this.payment = order.payment.toBuilder();
        }

        public Order build() {
            return new Order(this);
        }

    }

}
