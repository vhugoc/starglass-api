package com.starglass.api.domain.product;

import com.starglass.api.domain.merchant.MerchantProvider;
import com.starglass.api.domain.product.productmaterial.ProductMaterialProvider;

import java.util.UUID;

public class ProductProvider {

    public static Product transparentDoor() {
        return Product.of(MerchantProvider.provide())
                .withId(UUID.randomUUID().toString())
                .withName("Porta Incolor 2 Folhas")
                .withMaterials(ProductMaterialProvider.transparentDoorMaterials())
                .build();
    }

}
