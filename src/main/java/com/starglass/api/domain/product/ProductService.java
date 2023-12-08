package com.starglass.api.domain.product;

import com.starglass.api.domain.product.productmaterial.ProductMaterial;
import com.starglass.api.infra.service.BaseMerchantServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService extends BaseMerchantServiceImpl<Product, Product.Builder> {

    public List<ProductMaterial> findAllProductMaterials(String id) {
        return this.findById(id).getData().getMaterials();
    }

}
