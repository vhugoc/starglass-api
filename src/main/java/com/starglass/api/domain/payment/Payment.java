package com.starglass.api.domain.payment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.starglass.api.domain.order.Order;
import com.starglass.api.infra.entity.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.ToString;

@Entity
@Getter
@ToString
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Payment extends BaseEntity<Payment, Payment.Builder> {

    @OneToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Order order;

    @NotNull
    private PaymentStatus status;

    @NotNull
    private PaymentType type;

    private Float value;

    private String link;

    public Payment() {
    }

    protected Payment(Builder builder) {
        super(builder);
        this.order = builder.order;
        this.status = builder.status;
        this.type = builder.type;
        this.value = builder.value;
        this.link = builder.link;
    }

    @Override
    public abstract Builder toBuilder();

    @Getter
    public abstract static class Builder extends BaseEntity.Builder<Payment, Builder> {

        private Order order;

        @NotNull
        private PaymentStatus status;

        @NotNull
        private PaymentType type;

        @NotEmpty
        private Float value;

        private String link;

        public Builder() {
        }

        public Builder(Payment payment) {
            super(payment);
            this.order = payment.order;
            this.status = payment.status;
            this.type = payment.type;
            this.value = payment.value;
            this.link = payment.link;
        }

        public abstract Payment build();

        public Builder withOrder(Order order) {
            this.order = order;
            return this;
        }

    }

}
