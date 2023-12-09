package com.starglass.api.domain.product.material;

import com.starglass.api.domain.merchant.MerchantProvider;

import java.util.UUID;

public class MaterialProvider {

    public static Material defaultGlass() {
        return Material.of(MerchantProvider.provide())
                .withId(UUID.randomUUID().toString())
                .withType(MaterialType.DEFAULT_GLASS)
                .withName("Vidro Incolor 10mm")
                .withColor(MaterialColor.TRANSPARENT)
                .withThickness("10")
                .withValue(100F).build();
    }

    public static Material aluminium() {
        return Material.of(MerchantProvider.provide())
                .withId(UUID.randomUUID().toString())
                .withType(MaterialType.ALUMINIUM)
                .withName("Perfil Preto")
                .withColor(MaterialColor.BLACK)
                .withValue(30F).build();
    }

    public static Material glass() {
        return Material.of(MerchantProvider.provide())
                .withId(UUID.randomUUID().toString())
                .withType(MaterialType.GLASS)
                .withName("Espelho Jateado")
                .withValue(120F).build();
    }

    public static Material unitary() {
        return Material.of(MerchantProvider.provide())
                .withId(UUID.randomUUID().toString())
                .withType(MaterialType.UNITARY)
                .withName("Kit Pivotante Cromado")
                .withColor(MaterialColor.CHROME)
                .withValue(150F).build();
    }

}
