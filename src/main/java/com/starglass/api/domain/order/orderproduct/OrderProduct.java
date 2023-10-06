package com.starglass.api.domain.order.orderproduct;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.starglass.api.args.Dimensions;
import com.starglass.api.args.Address;
import com.starglass.api.domain.merchant.Merchant;
import com.starglass.api.domain.order.Order;
import com.starglass.api.domain.product.Product;
import com.starglass.api.infra.entity.BaseMerchantEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@Entity
@Getter
@ToString
public class OrderProduct extends BaseMerchantEntity<OrderProduct, OrderProduct.Builder> {

    @ManyToOne
    @JsonIgnore
    private Order order;

    @ManyToOne
    private Product product;

    private int quantity;

    private Dimensions dimensions;

    private Float unitValue;

    private Float totalValue;

    public OrderProduct() {
    }

    protected OrderProduct(Builder builder) {
        super(builder);
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

    public static Builder of(Merchant merchant) {
        return new Builder(merchant);
    }

    public static Builder of(OrderProduct order) {
        return new Builder(order);
    }

    @Getter
    public static class Builder extends BaseMerchantEntity.Builder<OrderProduct, Builder> {

        private Product product;

        private int quantity;

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
            this.product = orderProduct.product;
            this.quantity = orderProduct.quantity;
            this.dimensions = orderProduct.dimensions;
            this.unitValue = orderProduct.unitValue;
            this.totalValue = orderProduct.totalValue;
        }

        public OrderProduct build() {
            return new OrderProduct(this);
        }

    }

}
