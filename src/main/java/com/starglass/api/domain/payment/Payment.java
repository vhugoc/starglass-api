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
public abstract class Payment extends BaseMerchantEntity<Payment, Payment.Builder> {

    @NotNull
    private PaymentStatus status;

    @NotNull
    private PaymentType type;

    @NotEmpty
    private Float value;

    private String link;

    public Payment() {
    }

    protected Payment(Builder builder) {
        super(builder);
        this.status = builder.status;
        this.type = builder.type;
        this.value = builder.value;
        this.link = builder.link;
    }

    @Override
    public abstract Builder toBuilder();

    @Getter
    public abstract static class Builder extends BaseMerchantEntity.Builder<Payment, Builder> {

        @NotNull
        private PaymentStatus status;

        @NotNull
        private PaymentType type;

        @NotEmpty
        private Float value;

        private String link;

        public Builder() {
        }

        public Builder(Merchant merchant) {
            super(merchant);
        }

        public Builder(Payment payment) {
            super(payment);
            this.status = payment.status;
            this.type = payment.type;
            this.value = payment.value;
            this.link = payment.link;
        }

        public abstract Payment build();

    }

}
