package com.starglass.api.domain.merchant;

import com.starglass.api.infra.entity.BaseEntity;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.ToString;

@Entity
@Getter
@ToString
public class Merchant extends BaseEntity<Merchant, Merchant.Builder> {

    private String name;

    private String email;

    private String documentNumber;

    private String photo;

    public Merchant() {
    }

    protected Merchant(Builder builder) {
        super(builder);
        this.name = builder.name;
        this.email = builder.email;
        this.documentNumber = builder.documentNumber;
        this.photo = builder.photo;
    }

    @Override
    public Builder toBuilder() {
        return new Builder(this);
    }

    public static Builder of() {
        return new Builder();
    }

    public static Builder of(Merchant merchant) {
        return new Builder(merchant);
    }

    @Getter
    public static class Builder extends BaseEntity.Builder<Merchant, Builder> {

        private String name;

        private String email;

        private String documentNumber;

        private String photo;

        public Builder() {
        }

        public Builder(Merchant merchant) {
            super(merchant);
            this.name = merchant.name;
            this.email = merchant.email;
            this.documentNumber = merchant.documentNumber;
            this.photo = merchant.photo;
        }

        public Merchant build() {
            return new Merchant(this);
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

    }

}
