package com.starglass.api.domain.order;

import com.google.common.collect.Lists;
import com.starglass.api.args.Address;
import com.starglass.api.domain.customer.Customer;
import com.starglass.api.domain.merchant.Merchant;
import com.starglass.api.domain.order.orderproduct.OrderProduct;
import com.starglass.api.domain.payment.Payment;
import com.starglass.api.domain.payment.PaymentStatus;
import com.starglass.api.infra.entity.BaseMerchantEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@ToString
public class Order extends BaseMerchantEntity<Order, Order.Builder> {

    @OneToMany(mappedBy = "order")
    private List<OrderProduct> products = Lists.newLinkedList();

    @ManyToOne
    private Customer customer;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private LocalDateTime installDate;

    private Address installAddress;

    private Float profitMargin = 0F;

    private Float discount = 0F;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Payment payment;

    public Order() {
    }

    protected Order(Builder builder) {
        super(builder);
        this.products = builder.products;
        this.customer = builder.customer;
        this.status = builder.status;
        this.installDate = builder.installDate;
        this.installAddress = builder.installAddress;
        this.profitMargin = builder.profitMargin;
        this.discount = builder.discount;
        this.payment = builder.payment;
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

        private List<OrderProduct> products = Lists.newLinkedList();

        private Customer customer;

        @Enumerated(EnumType.STRING)
        private OrderStatus status;

        private LocalDateTime installDate;

        private Address installAddress;

        private Float profitMargin = 0F;

        private Float discount = 0F;

        private Payment payment;

        public Builder() {
        }

        public Builder(Merchant merchant) {
            super(merchant);
        }

        public Builder(Order order) {
            super(order);
            this.products = order.products;
            this.customer = order.customer;
            this.status = order.status;
            this.installDate = order.installDate;
            this.installAddress = order.installAddress;
            this.profitMargin = order.profitMargin;
            this.discount = order.discount;
            this.payment = order.payment;
        }

        public Order build() {
            return new Order(this);
        }

    }

}
