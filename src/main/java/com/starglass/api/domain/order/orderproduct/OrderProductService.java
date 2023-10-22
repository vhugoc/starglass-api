package com.starglass.api.domain.order.orderproduct;

import com.starglass.api.domain.product.Product;
import com.starglass.api.domain.product.ProductService;
import com.starglass.api.domain.product.material.Material;
import com.starglass.api.domain.product.material.MaterialType;
import com.starglass.api.domain.product.productmaterial.ProductMaterial;
import com.starglass.api.infra.service.BaseMerchantServiceImpl;
import jakarta.transaction.Transactional;
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

    public List<OrderProduct.Builder> calculate(List<OrderProduct.Builder> orderProducts) {
        for (OrderProduct.Builder orderProduct : orderProducts) {
            List<ProductMaterial> productMaterials = productService.findAllProductMaterials(orderProduct.getProduct().getId());
            orderProduct.withUnitValue(0F);
            for (ProductMaterial productMaterial : productMaterials) {
                Material material = productMaterial.getMaterial();
                if (material.getType().equals(MaterialType.DEFAULT_GLASS)) {
                    orderProduct.sumUnitValue(productMaterial.getQuantity() * ((orderProduct.getDimensions().getWidth() / 1000) * (orderProduct.getDimensions().getHeight() / 1000)) * material.getValue());
                }
            }
            orderProduct.calculateTotalValue();
        }
        return orderProducts;
    }

}
