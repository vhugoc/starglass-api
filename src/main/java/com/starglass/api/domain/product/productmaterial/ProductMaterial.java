package com.starglass.api.domain.product.productmaterial;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.starglass.api.domain.merchant.Merchant;
import com.starglass.api.domain.product.Product;
import com.starglass.api.domain.product.material.Material;
import com.starglass.api.infra.entity.BaseMerchantEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.ToString;

@Entity(name = "product_materials")
@Getter
@ToString
public class ProductMaterial extends BaseMerchantEntity<ProductMaterial, ProductMaterial.Builder> {

    @ManyToOne
    @JoinColumn(name = "product_id")
    @NotNull
    @JsonIgnore
    private Product product;

    @ManyToOne
    @JoinColumn(name = "material_id")
    @NotNull
    private Material material;

    private int quantity = 1;

    public ProductMaterial() {
    }

    protected ProductMaterial(Builder builder) {
        super(builder);
        this.product = builder.product;
        this.material = builder.material;
        this.quantity = builder.quantity;
    }

    @Override
    public Builder toBuilder() {
        return new Builder(this);
    }

    public static Builder of(Merchant merchant) {
        return new Builder(merchant);
    }

    public static Builder of(ProductMaterial material) {
        return new Builder(material);
    }

    @Getter
    public static class Builder extends BaseMerchantEntity.Builder<ProductMaterial, Builder> {

        @NotNull
        private Product product;

        @NotNull
        private Material material;

        private int quantity;

        public Builder() {
        }

        public Builder(Merchant merchant) {
            super(merchant);
        }

        public Builder(ProductMaterial productMaterial) {
            super(productMaterial);
            this.product = productMaterial.product;
            this.material = productMaterial.material;
            this.quantity = productMaterial.quantity;
        }

        public ProductMaterial build() {
            return new ProductMaterial(this);
        }

        public Builder withProduct(Product product) {
            this.product = product;
            return this;
        }

        public Builder withMaterial(Material material) {
            this.material = material;
            return this;
        }

        public Builder withQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

    }

}
