package com.starglass.api.domain.product;

import com.google.common.collect.Lists;
import com.starglass.api.domain.merchant.Merchant;
import com.starglass.api.domain.product.material.Material;
import com.starglass.api.infra.entity.BaseMerchantEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Entity
@Getter
@ToString
public class Product extends BaseMerchantEntity<Product, Product.Builder> {

    @NotNull
    private String name;

    private String description;

    @ManyToMany
    @JoinTable(
            name = "product_materials",
            joinColumns = @JoinColumn(name = "product_fk"),
            inverseJoinColumns = @JoinColumn(name = "material_fk")
    )
    private List<Material> materials = Lists.newLinkedList();

    public Product() {
    }

    protected Product(Builder builder) {
        super(builder);
        this.name = builder.name;
        this.description = builder.description;
        this.materials = builder.materials;
    }

    @Override
    public Builder toBuilder() {
        return new Builder(this);
    }

    public static Builder of(Merchant merchant) {
        return new Builder(merchant);
    }

    public static Builder of(Product material) {
        return new Builder(material);
    }

    @Getter
    public static class Builder extends BaseMerchantEntity.Builder<Product, Builder> {

        private String name;

        private String description;

        private List<Material> materials = Lists.newLinkedList();

        public Builder() {
        }

        public Builder(Merchant merchant) {
            super(merchant);
        }

        public Builder(Product product) {
            super(product);
            this.name = product.name;
            this.description = product.description;
            this.materials = product.materials;
        }

        public Product build() {
            return new Product(this);
        }

    }

}
