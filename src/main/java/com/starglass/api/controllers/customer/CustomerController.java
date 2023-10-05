package com.starglass.api.controllers.customer;

import com.starglass.api.domain.customer.Customer;
import com.starglass.api.domain.customer.CustomerRepository;
import com.starglass.api.domain.customer.CustomerService;
import com.starglass.api.infra.rest.BaseMerchantCrud;
import com.starglass.api.infra.rest.RestURL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping(RestURL.CUSTOMER)
public class CustomerController extends BaseMerchantCrud<Customer, Customer.Builder> {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerController(CustomerRepository customerRepository, CustomerService customerService) {
        super(customerService);
        this.customerRepository = customerRepository;
    }

}
