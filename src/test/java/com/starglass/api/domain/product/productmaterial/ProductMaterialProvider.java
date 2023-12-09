package com.starglass.api.domain.product.productmaterial;

import com.google.common.collect.ImmutableList;
import com.starglass.api.domain.merchant.MerchantProvider;
import com.starglass.api.domain.product.material.MaterialProvider;

import java.util.List;
import java.util.UUID;

public class ProductMaterialProvider {

    public static List<ProductMaterial.Builder> transparentDoorMaterials() {
        return ImmutableList.of(
                ProductMaterial.of(MerchantProvider.provide())
                        .withId(UUID.randomUUID().toString())
                        .withQuantity(1)
                        .withMaterial(MaterialProvider.defaultGlass()),
                ProductMaterial.of(MerchantProvider.provide())
                        .withId(UUID.randomUUID().toString())
                        .withQuantity(1)
                        .withMaterial(MaterialProvider.aluminium()),
                ProductMaterial.of(MerchantProvider.provide())
                        .withId(UUID.randomUUID().toString())
                        .withQuantity(1)
                        .withMaterial(MaterialProvider.unitary())
        );
    }

}
