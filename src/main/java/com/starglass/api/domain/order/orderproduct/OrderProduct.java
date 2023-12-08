package com.starglass.api.domain.order.orderproduct;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.starglass.api.args.Dimensions;
import com.starglass.api.domain.merchant.Merchant;
import com.starglass.api.domain.order.Order;
import com.starglass.api.domain.product.Product;
import com.starglass.api.infra.entity.BaseMerchantEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.ToString;

@Entity
@Getter
@ToString
public class OrderProduct extends BaseMerchantEntity<OrderProduct, OrderProduct.Builder> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Order order;

    @ManyToOne
    private Product product;

    private Integer quantity = 1;

    private Dimensions dimensions;

    private Float unitValue;

    private Float totalValue;

    public OrderProduct() {
    }

    protected OrderProduct(Builder builder) {
        super(builder);
        this.order = builder.order;
        this.product = builder.product;
        this.quantity = builder.quantity;
        this.dimensions = builder.dimensions;
        this.unitValue = builder.unitValue;
        this.totalValue = builder.totalValue;
    }

    @Override
    public Builder toBuilder() {
        return new Builder(this);
    }

    public static OrderProduct.Builder of(Merchant merchant) {
        return new OrderProduct.Builder(merchant);
    }

    public static Builder of(OrderProduct order) {
        return new Builder(order);
    }

    @Getter
    public static class Builder extends BaseMerchantEntity.Builder<OrderProduct, Builder> {

        private Order order;

        private Product product;

        private Integer quantity;

        private Dimensions dimensions;

        private Float unitValue;

        private Float totalValue;

        public Builder() {
        }

        public Builder(Merchant merchant) {
            super(merchant);
        }

        public Builder(OrderProduct orderProduct) {
            super(orderProduct);
            this.order = orderProduct.order;
            this.product = orderProduct.product;
            this.quantity = orderProduct.quantity;
            this.dimensions = orderProduct.dimensions;
        }

        public OrderProduct build() {
            throw new RuntimeException("Should build with order");
        }

        public OrderProduct build(Order order) {
            this.order = order;
            this.withMerchant(order.getMerchant());
            return new OrderProduct(this);
        }

        public void sumUnitValue(Float value) {
            this.unitValue += value;
        }

        public void calculateTotalValue() {
            this.totalValue = this.unitValue * this.quantity;
        }

        public Builder withUnitValue(Float unitValue) {
            this.unitValue = unitValue;
            return this;
        }

    }

}
