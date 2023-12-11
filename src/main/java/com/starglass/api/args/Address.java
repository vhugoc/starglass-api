package com.starglass.api.args;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public final class Address {

    private String postalCode;

    private String street;

    private String number;

    private String complement;

    private String neighborhood;

    private String city;

    private String state;

    public Address() {
    }

    private Address(Builder builder) {
        this.postalCode = builder.postalCode;
        this.street = builder.street;
        this.number = builder.number;
        this.complement = builder.complement;
        this.neighborhood = builder.neighborhood;
        this.city = builder.city;
        this.state = builder.state;
    }

    public static Builder of() {
        return new Builder();
    }

    public static Builder of(Address address) {
        return new Builder(address);
    }

    public static class Builder {

        private String postalCode;

        private String street;

        private String number;

        private String complement;

        private String neighborhood;

        private String city;

        private String state;

        public Builder() {
        }

        public Builder(Address address) {
            this.postalCode = address.postalCode;
            this.street = address.street;
            this.number = address.number;
            this.complement = address.complement;
            this.neighborhood = address.neighborhood;
            this.city = address.city;
            this.state = address.state;
        }

        public Address build() {
            return new Address(this);
        }

        public Builder withPostalCode(String postalCode) {
            this.postalCode = postalCode;
            return this;
        }

        public Builder withStreet(String street) {
            this.street = street;
            return this;
        }

        public Builder withNumber(String number) {
            this.number = number;
            return this;
        }

        public Builder withComplement(String complement) {
            this.complement = complement;
            return this;
        }

        public Builder withNeighborhood(String neighborhood) {
            this.neighborhood = neighborhood;
            return this;
        }

        public Builder withCity(String city) {
            this.city = city;
            return this;
        }

        public Builder withState(String state) {
            this.state = state;
            return this;
        }

    }

}