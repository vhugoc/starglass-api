package com.starglass.api.domain.merchant;

public class MerchantProvider {

    public static Merchant provide() {
        return Merchant.of()
                .withId("1")
                .withName("Vidraçaria Starglass")
                .withEmail("vidracariastarglass@starglass.io")
                .build();
    }

}
