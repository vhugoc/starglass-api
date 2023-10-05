package com.starglass.api.domain.product;

import com.starglass.api.domain.product.material.Material;
import com.starglass.api.domain.product.material.MaterialService;
import com.starglass.api.domain.product.productmaterial.ProductMaterial;
import com.starglass.api.domain.product.productmaterial.ProductMaterialService;
import com.starglass.api.infra.exception.custom.RestException;
import com.starglass.api.infra.service.BaseMerchantServiceImpl;
import com.starglass.api.infra.service.BaseServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ProductService extends BaseMerchantServiceImpl<Product, Product.Builder> {

    private final ProductMaterialService productMaterialService;

    private final MaterialService materialService;

    @Autowired
    public ProductService(ProductMaterialService productMaterialService, MaterialService materialService) {
        this.productMaterialService = productMaterialService;
        this.materialService = materialService;
    }

    @Override
    public BaseServiceResponse<Product> save(Product.Builder builder) {
        BaseServiceResponse<Product> savedProduct = super.save(builder);

        for (ProductMaterial productMaterial : builder.getMaterials()) {
            Material material = this.materialService.findById(productMaterial.getId()).getData();

            if (material == null) throw new RestException("Material does not exists", HttpStatus.BAD_REQUEST);

            ProductMaterial.Builder productMaterialBuilder = productMaterial.toBuilder()
                    .withProduct(savedProduct.getData())
                    .withMaterial(material);

            if (productMaterial.getQuantity() > 0)
                productMaterialBuilder.withQuantity(productMaterial.getQuantity());

            productMaterialService.save(productMaterialBuilder);
        }

        return savedProduct;
    }

}
