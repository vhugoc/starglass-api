package com.starglass.api.domain.customer;

import com.starglass.api.domain.merchant.MerchantProvider;

import java.sql.Date;
import java.util.UUID;

public class CustomerProvider {

    public static Customer provide() {
        return Customer.of(MerchantProvider.provide())
                .withId(UUID.randomUUID().toString())
                .withName("Victor Correa")
                .withEmail("vhuugoc@gmail.com")
                .withBirthDate(Date.valueOf("2000-08-20"))
                .withPhone("5543999875305")
                .build();
    }

}
