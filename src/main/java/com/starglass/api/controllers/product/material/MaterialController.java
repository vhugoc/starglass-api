package com.starglass.api.controllers.product.material;

import com.starglass.api.domain.product.material.Material;
import com.starglass.api.domain.product.material.MaterialRepository;
import com.starglass.api.domain.product.material.MaterialService;
import com.starglass.api.infra.rest.BaseMerchantCrud;
import com.starglass.api.infra.rest.RestURL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping(RestURL.PRODUCT_MATERIAL)
public class MaterialController extends BaseMerchantCrud<Material, Material.Builder> {

    private final MaterialRepository materialRepository;

    @Autowired
    public MaterialController(MaterialRepository materialRepository, MaterialService materialService) {
        super(materialService);
        this.materialRepository = materialRepository;
    }

}
