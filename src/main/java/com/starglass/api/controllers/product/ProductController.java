package com.starglass.api.controllers.product;

import com.starglass.api.domain.product.Product;
import com.starglass.api.domain.product.ProductRepository;
import com.starglass.api.domain.product.ProductService;
import com.starglass.api.domain.product.material.Material;
import com.starglass.api.domain.product.material.MaterialRepository;
import com.starglass.api.domain.product.material.MaterialService;
import com.starglass.api.infra.rest.BaseMerchantCrud;
import com.starglass.api.infra.rest.RestURL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping(RestURL.PRODUCT)
public class ProductController extends BaseMerchantCrud<Product, Product.Builder> {

    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository, ProductService productService) {
        super(productService);
        this.productRepository = productRepository;
    }

}
