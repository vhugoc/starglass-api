package com.starglass.api.domain.product;

import com.google.common.collect.Lists;
import com.starglass.api.domain.merchant.Merchant;
import com.starglass.api.domain.product.productmaterial.ProductMaterial;
import com.starglass.api.infra.entity.BaseMerchantEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@ToString
public class Product extends BaseMerchantEntity<Product, Product.Builder> {

    @NotNull
    private String name;

    private String description;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<ProductMaterial> materials = Lists.newLinkedList();

    public Product() {
    }

    protected Product(Builder builder) {
        super(builder);
        this.name = builder.name;
        this.description = builder.description;
        this.materials = builder.materials.stream().map(pm -> pm.build(this)).collect(Collectors.toList());
    }

    @Override
    public Builder toBuilder() {
        return new Builder(this);
    }

    public static Builder of(Merchant merchant) {
        return new Builder(merchant);
    }

    public static Builder of(Product product) {
        return new Builder(product);
    }

    @Getter
    public static class Builder extends BaseMerchantEntity.Builder<Product, Builder> {

        private String name;

        private String description;

        private List<ProductMaterial.Builder> materials = Lists.newLinkedList();

        public Builder() {
        }

        public Builder(Merchant merchant) {
            super(merchant);
        }

        public Builder(Product product) {
            super(product);
            this.name = product.name;
            this.description = product.description;
            this.materials = product.materials.stream().map(ProductMaterial::toBuilder).collect(Collectors.toList());
        }

        public Product build() {
            return new Product(this);
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withMaterials(List<ProductMaterial.Builder> materials) {
            this.materials = materials;
            return this;
        }

    }

}
