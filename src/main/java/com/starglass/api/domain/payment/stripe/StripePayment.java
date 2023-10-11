package com.starglass.api.domain.payment.stripe;

import com.starglass.api.domain.order.Order;
import com.starglass.api.domain.payment.Payment;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.ToString;

@Entity
@Getter
@ToString
@DiscriminatorValue("STRIPE_PAYMENT")
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

    public static Builder of(StripePayment payment) {
        return new Builder(payment);
    }

    @Getter
    public static class Builder extends Payment.Builder {

        public Builder() {
        }

        public Builder(StripePayment payment) {
            super(payment);
        }

        public StripePayment build() {
            return new StripePayment(this);
        }

        public Builder withOrder(Order order) {
            super.withOrder(order);
            return this;
        }

    }

}