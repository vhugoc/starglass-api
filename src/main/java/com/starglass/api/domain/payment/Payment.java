package com.starglass.api.domain.payment;

import com.starglass.api.domain.merchant.Merchant;
import com.starglass.api.infra.entity.BaseMerchantEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.ToString;

@Entity
@Getter
@ToString
public class Payment extends BaseMerchantEntity<Payment, Payment.Builder> {

    @NotNull
    private PaymentStatus status;

    @NotNull
    private PaymentType type;

    private Float value;

    private Float rawValue;

    private String link;

    public Payment() {
    }

    protected Payment(Builder builder) {
        super(builder);
        this.status = builder.status;
        this.type = builder.type;
        this.value = builder.value;
        this.rawValue = builder.rawValue;
        this.link = builder.link;
    }

    @Override
    public Builder toBuilder() {
        return new Builder(this);
    }

    public static Builder of() {
        return new Builder();
    }

    public static Builder of(Payment payment) {
        return new Builder(payment);
    }

    @Getter
    public static class Builder extends BaseMerchantEntity.Builder<Payment, Builder> {

        @NotNull
        protected PaymentStatus status;

        @NotNull
        protected PaymentType type;

        @NotEmpty
        protected Float value;

        @NotEmpty
        private Float rawValue;

        protected String link;

        public Builder() {
        }

        public Builder(Payment payment) {
            super(payment);
            this.status = payment.status;
            this.type = payment.type;
            this.value = payment.value;
            this.rawValue = payment.rawValue;
            this.link = payment.link;
        }

        @Override
        public Payment build() {
            throw new RuntimeException("Should build with merchant");
        }

        public Payment build(Merchant merchant) {
            this.withMerchant(merchant);
            return new Payment(this);
        }

        public Builder withStatus(PaymentStatus status) {
            this.status = status;
            return this;
        }

        public Builder withType(PaymentType type) {
            this.type = type;
            return this;
        }

        public Builder withValue(Float value) {
            this.value = value;
            return this;
        }

        public Builder withRawValue(Float rawValue) {
            this.rawValue = rawValue;
            return this;
        }

    }

}
