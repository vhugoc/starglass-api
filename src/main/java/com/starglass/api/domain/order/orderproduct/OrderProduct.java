package com.starglass.api.domain.order.orderproduct;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.starglass.api.args.Dimensions;
import com.starglass.api.domain.order.Order;
import com.starglass.api.domain.product.Product;
import com.starglass.api.infra.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.ToString;

@Entity
@Getter
@ToString
public class OrderProduct extends BaseEntity<OrderProduct, OrderProduct.Builder> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Order order;

    @ManyToOne
    private Product product;

    private int quantity = 1;

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
        this.unitValue = 0F;
        this.totalValue = 0F;
    }

    @Override
    public Builder toBuilder() {
        return new Builder(this);
    }

    public static Builder of(OrderProduct order) {
        return new Builder(order);
    }

    @Getter
    public static class Builder extends BaseEntity.Builder<OrderProduct, Builder> {

        private Order order;

        private Product product;

        private int quantity;

        private Dimensions dimensions;

        public Builder() {
        }

        public Builder(OrderProduct orderProduct) {
            super(orderProduct);
            this.order = orderProduct.order;
            this.product = orderProduct.product;
            this.quantity = orderProduct.quantity;
            this.dimensions = orderProduct.dimensions;
        }

        public OrderProduct build() {
            return new OrderProduct(this);
        }

        public Builder withOrder(Order order) {
            this.order = order;
            return this;
        }

    }

}
