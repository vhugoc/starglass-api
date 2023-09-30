package com.starglass.api.infra.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.starglass.api.domain.merchant.Merchant;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@MappedSuperclass
@Getter
public abstract class BaseMerchantEntity<T extends BaseMerchantEntity, B extends BaseMerchantEntity.Builder> extends BaseEntity<T, B> {

    @ManyToOne
    @JoinColumn(name = "merchant_id")
    @JsonIgnore
    protected Merchant merchant;

    protected BaseMerchantEntity() {
    }

    protected BaseMerchantEntity(Builder builder) {
        super(builder);
        this.merchant = builder.merchant;
    }

    @Getter
    public abstract static class Builder<T extends BaseMerchantEntity, B extends Builder> extends BaseEntity.Builder<T, B> {

        private Merchant merchant;

        protected Builder() {
        }

        protected Builder(Merchant merchant) {
            super();
            this.merchant = merchant;
        }

        protected Builder(T baseMerchantEntity) {
            super(baseMerchantEntity);
            this.merchant = baseMerchantEntity.merchant;
        }

        public abstract T build();

        public B withMerchant(Merchant merchant) {
            this.merchant = merchant;
            return (B) this;
        }

    }

}
