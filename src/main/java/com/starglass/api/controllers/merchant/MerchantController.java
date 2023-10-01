package com.starglass.api.controllers.merchant;

import com.starglass.api.domain.merchant.Merchant;
import com.starglass.api.domain.merchant.MerchantRepository;
import com.starglass.api.domain.merchant.MerchantService;
import com.starglass.api.infra.rest.BaseCrud;
import com.starglass.api.infra.rest.RestURL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping(RestURL.MERCHANT)
public class MerchantController extends BaseCrud<Merchant, Merchant.Builder> {

    private final MerchantRepository merchantRepository;

    @Autowired
    public MerchantController(MerchantRepository merchantRepository, MerchantService merchantService) {
        super(merchantService);
        this.merchantRepository = merchantRepository;
    }

}
