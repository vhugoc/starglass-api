package com.starglass.api.domain.order;

import com.google.common.collect.Lists;
import com.starglass.api.args.Address;
import com.starglass.api.domain.customer.Customer;
import com.starglass.api.domain.merchant.Merchant;
import com.starglass.api.domain.order.orderproduct.OrderProduct;
import com.starglass.api.domain.payment.Payment;
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

    @OneToOne(cascade = CascadeType.ALL)
    private Payment payment;

    public Order() {
    }

    protected Order(Builder builder) {
        super(builder);
        this.products = builder.products.stream().map(p -> p.build(this)).collect(Collectors.toList());
        this.customer = builder.customer;
        this.status = builder.status;
        this.installDate = builder.installDate;
        this.installAddress = builder.installAddress;
        this.profitMargin = builder.profitMargin;
        this.discount = builder.discount;
        this.payment = builder.payment.build(this.getMerchant());
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

        private Payment.Builder payment;

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
            this.status.validate(this);
            return new Order(this);
        }

        public Builder withProducts(List<OrderProduct.Builder> products) {
            this.products = products;
            return this;
        }

        public Builder withCustomer(Customer customer) {
            this.customer = customer;
            return this;
        }

        public Builder withStatus(OrderStatus status) {
            this.status = status;
            return this;
        }

        public Builder withInstallDate(LocalDateTime installDate) {
            this.installDate = installDate;
            return this;
        }

        public Builder withInstallAddress(Address address) {
            this.installAddress = address;
            return this;
        }

        public Builder withProfitMargin(Float profitMargin) {
            this.profitMargin = profitMargin;
            return this;
        }

        public Builder withDiscount(Float discount) {
            this.discount = discount;
            return this;
        }

        public Builder withPayment(Payment.Builder payment) {
            this.payment = payment;
            return this;
        }

        public Float getTotalValue() {
            Float value = 0F;
            for (OrderProduct.Builder orderProduct : this.products) {
                value += orderProduct.getTotalValue();
            }
            return value;
        }

    }

}
