package com.starglass.api.domain.order.orderproduct;

import com.google.common.collect.ImmutableList;
import com.starglass.api.args.Dimensions;
import com.starglass.api.domain.merchant.MerchantProvider;
import com.starglass.api.domain.product.Product;

import java.util.List;

public class OrderProductProvider {

    public static List<OrderProduct.Builder> provide(Product product, Integer quantity) {
        return OrderProductProvider.provide(product, quantity, new Dimensions(1000F, 1000F));
    }

    public static List<OrderProduct.Builder> provide(Product product, Integer quantity, Dimensions dimensions) {
        return ImmutableList.of(
                OrderProduct.of(MerchantProvider.provide())
                        .withId("1")
                        .withQuantity(quantity)
                        .withProduct(product)
                        .withDimensions(dimensions)
        );
    }

}
