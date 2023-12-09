package com.starglass.api.domain.order.orderproduct;

import com.starglass.api.args.Dimensions;
import com.starglass.api.domain.product.Product;
import com.starglass.api.domain.product.ProductProvider;
import com.starglass.api.domain.product.ProductService;
import com.starglass.api.domain.user.UserProvider;
import com.starglass.api.infra.security.TokenService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.junit.jupiter.api.*;

import java.util.List;

@SpringBootTest
public class OrderProductServiceTest {

    @MockBean
    private TokenService tokenService;

    @Autowired
    private OrderProductService orderProductService;

    @MockBean
    private ProductService productService;

    @Test
    @DisplayName("Order Product Calculate")
    public void testCalculate() {
        Integer productsQuantity = 2;
        Product product = ProductProvider.transparentDoor();
        List<OrderProduct.Builder> orderProducts = OrderProductProvider.provide(product, productsQuantity, new Dimensions(1000F, 1000F));

        Mockito.when(tokenService.getAuthenticatedUserFromContext()).thenReturn(UserProvider.provide());
        Mockito.when(productService.findAllProductMaterials(Mockito.anyString())).thenReturn(product.getMaterials());

        orderProductService.calculate(orderProducts);

        Assertions.assertAll(
                () -> Assertions.assertEquals(280F, orderProducts.get(0).getUnitValue(), 0.01),
                () -> Assertions.assertEquals(productsQuantity * orderProducts.get(0).getUnitValue(), orderProducts.get(0).getTotalValue(), 0.01)
        );

    }

}
