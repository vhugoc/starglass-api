package com.starglass.api.domain.customer;

import com.querydsl.core.BooleanBuilder;
import com.starglass.api.domain.user.User;
import com.starglass.api.infra.entity.EntityValidator;
import com.starglass.api.infra.exception.custom.ValidationException;
import com.starglass.api.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerValidator implements EntityValidator<Customer> {

    private final CustomerRepository customerRepository;

    private final TokenService tokenService;

    @Autowired
    public CustomerValidator(CustomerRepository customerRepository, TokenService tokenService) {
        this.customerRepository = customerRepository;
        this.tokenService = tokenService;
    }

    @Override
    public void validate(Customer entity) throws ValidationException {
        this.validateNotExists(entity);
    }

    public void validateNotExists(Customer entity) {
        User authenticatedUser = tokenService.getAuthenticatedUserFromContext();
        boolean isUpdate = entity.getId() != null;

        QCustomer qCustomer = QCustomer.customer;
        BooleanBuilder queryBuilder = new BooleanBuilder();

        queryBuilder.and(qCustomer.merchant.eq(authenticatedUser.getMerchant()));

        if (entity.getEmail() != null) {
            queryBuilder.and(qCustomer.phone.eq(entity.getPhone()).or(qCustomer.email.eq(entity.getEmail())));
        } else {
            queryBuilder.and(qCustomer.phone.eq(entity.getPhone()));
        }

        if (isUpdate) queryBuilder.and(qCustomer.id.ne(entity.getId()));

        if (this.customerRepository.count(queryBuilder) > 0)
            throw new ValidationException("This customer already exists");

    }

}
