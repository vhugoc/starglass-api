package com.starglass.api.domain.product.material;

import com.starglass.api.domain.merchant.Merchant;
import com.starglass.api.infra.entity.BaseMerchantEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.ToString;

@Entity
@Getter
@ToString
public class Material extends BaseMerchantEntity<Material, Material.Builder> {

    @NotNull
    @Enumerated(EnumType.STRING)
    private MaterialType type;

    @NotNull
    private String name;

    @Enumerated(EnumType.STRING)
    private MaterialColor color;

    private String thickness;

    @NotNull
    private Float value;

    public Material() {
    }

    protected Material(Builder builder) {
        super(builder);
        this.type = builder.type;
        this.name = builder.name;
        this.color = builder.color;
        this.thickness = builder.thickness;
        this.value = builder.value;
    }

    @Override
    public Builder toBuilder() {
        return new Builder(this);
    }

    public static Builder of(Merchant merchant) {
        return new Builder(merchant);
    }

    public static Builder of(Material material) {
        return new Builder(material);
    }

    @Getter
    public static class Builder extends BaseMerchantEntity.Builder<Material, Builder> {

        private MaterialType type;

        private String name;

        private MaterialColor color;

        private String thickness;

        private Float value;

        public Builder() {
        }

        public Builder(Merchant merchant) {
            super(merchant);
        }

        public Builder(Material material) {
            super(material);
            this.type = material.type;
            this.name = material.name;
            this.color = material.color;
            this.thickness = material.thickness;
            this.value = material.value;
        }

        public Material build() {
            return new Material(this);
        }

    }

}
