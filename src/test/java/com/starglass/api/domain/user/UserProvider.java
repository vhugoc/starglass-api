package com.starglass.api.domain.user;

import com.starglass.api.domain.merchant.MerchantProvider;

public class UserProvider {

    public static User provide() {
        return User.of()
                .withMerchant(MerchantProvider.provide())
                .withId("1")
                .withFirstName("Victor")
                .withLastName("Corrêa")
                .build();
    }

}
