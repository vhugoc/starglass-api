package com.starglass.api.domain.order.orderproduct;

import com.starglass.api.domain.product.ProductService;
import com.starglass.api.domain.product.material.Material;
import com.starglass.api.domain.product.material.MaterialType;
import com.starglass.api.domain.product.productmaterial.ProductMaterial;
import com.starglass.api.infra.service.BaseMerchantServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderProductService extends BaseMerchantServiceImpl<OrderProduct, OrderProduct.Builder> {

    private final ProductService productService;

    @Autowired
    public OrderProductService(ProductService productService) {
        this.productService = productService;
    }

    public void calculate(List<OrderProduct.Builder> orderProducts) {
        for (OrderProduct.Builder orderProduct : orderProducts) {
            List<ProductMaterial> productMaterials = productService.findAllProductMaterials(orderProduct.getProduct().getId());
            orderProduct.withUnitValue(0F);
            for (ProductMaterial productMaterial : productMaterials) {
                Material material = productMaterial.getMaterial();
                orderProduct.sumUnitValue(material.getType().calculate(productMaterial, orderProduct.getDimensions()));
            }
            orderProduct.calculateTotalValue();
        }
    }

}
