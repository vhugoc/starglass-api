package com.starglass.api.domain.payment.stripe;

import com.starglass.api.domain.merchant.Merchant;
import com.starglass.api.domain.payment.Payment;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.ToString;

@Entity
@Getter
@ToString
public class StripePayment extends Payment {

    public StripePayment() {
    }

    protected StripePayment(Builder builder) {
        super(builder);
    }

    @Override
    public Builder toBuilder() {
        return new Builder(this);
    }

    public static Builder of(Merchant merchant) {
        return new Builder(merchant);
    }

    public static Builder of(StripePayment payment) {
        return new Builder(payment);
    }

    @Getter
    public static class Builder extends Payment.Builder {

        public Builder() {
        }

        public Builder(Merchant merchant) {
            super(merchant);
        }

        public Builder(StripePayment payment) {
            super(payment);
        }

        public StripePayment build() {
            return new StripePayment(this);
        }

    }

}
