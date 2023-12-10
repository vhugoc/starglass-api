package com.starglass.api.domain.customer;

import com.starglass.api.args.Address;
import com.starglass.api.domain.merchant.Merchant;
import com.starglass.api.infra.entity.BaseMerchantEntity;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@Entity
@Getter
@ToString
public class Customer extends BaseMerchantEntity<Customer, Customer.Builder> {

    @NotEmpty
    private String name;

    private Address address;

    private Date birthDate;

    @NotNull
    private String phone;

    private String email;

    public Customer() {
    }

    protected Customer(Builder builder) {
        super(builder);
        this.name = builder.name;
        this.address = builder.address;
        this.birthDate = builder.birthDate;
        this.phone = builder.phone;
        this.email = builder.email;
    }

    @Override
    public Builder toBuilder() {
        return new Builder(this);
    }

    public static Builder of(Merchant merchant) {
        return new Builder(merchant);
    }

    public static Builder of(Customer customer) {
        return new Builder(customer);
    }

    @Getter
    public static class Builder extends BaseMerchantEntity.Builder<Customer, Builder> {

        @NotEmpty
        private String name;

        private Address address;

        private Date birthDate;

        @NotNull
        private String phone;

        private String email;

        public Builder() {
        }

        public Builder(Merchant merchant) {
            super(merchant);
        }

        public Builder(Customer customer) {
            super(customer);
            this.name = customer.name;
            this.address = customer.address;
            this.birthDate = customer.birthDate;
            this.phone = customer.phone;
            this.email = customer.email;
        }

        public Customer build() {
            return new Customer(this);
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withAddress(Address address) {
            this.address = address;
            return this;
        }

        public Builder withBirthDate(Date birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public Builder withPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

    }

}
