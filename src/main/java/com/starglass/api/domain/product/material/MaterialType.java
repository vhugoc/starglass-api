package com.starglass.api.domain.product.material;

import com.starglass.api.args.Dimensions;
import com.starglass.api.domain.product.productmaterial.ProductMaterial;

public enum MaterialType {

    DEFAULT_GLASS {
        @Override
        public Double calculate(ProductMaterial productMaterial, Dimensions dimensions) {
            return calculateSquareMeter(productMaterial, dimensions);
        }
    },

    ALUMINIUM {
        @Override
        public Double calculate(ProductMaterial productMaterial, Dimensions dimensions) {
            Float linearMeters = dimensions.getLargestDimension();
            return productMaterial.getQuantity()
                    * (linearMeters / 1000)
                    * productMaterial.getMaterial().getValue();
        }
    },

    GLASS {
        @Override
        public Double calculate(ProductMaterial productMaterial, Dimensions dimensions) {
            return calculateSquareMeter(productMaterial, dimensions);
        }
    },

    UNITARY {
        @Override
        public Double calculate(ProductMaterial productMaterial, Dimensions dimensions) {
            return productMaterial.getQuantity() * productMaterial.getMaterial().getValue();
        }
    };

    public abstract Double calculate(ProductMaterial productMaterial, Dimensions dimensions);

    public Double calculateSquareMeter(ProductMaterial productMaterial, Dimensions dimensions) {
        return productMaterial.getQuantity()
                * ((dimensions.getWidth() / 1000) * (dimensions.getHeight() / 1000))
                * productMaterial.getMaterial().getValue();
    }

}
