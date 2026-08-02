package com.zidio.keystone.mapper;

import com.zidio.keystone.dto.CustomerRequest;
import com.zidio.keystone.dto.CustomerResponse;
import com.zidio.keystone.entity.Customer;

public final class CustomerMapper {

    private CustomerMapper() {
    }

    public static Customer toEntity(CustomerRequest request) {
        return Customer.builder()
                .customerName(request.getCustomerName())
                .companyName(request.getCompanyName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .address(request.getAddress())
                .build();
    }

    public static CustomerResponse toResponse(Customer customer) {
        return CustomerResponse.builder()
                .id(customer.getId())
                .customerName(customer.getCustomerName())
                .companyName(customer.getCompanyName())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .address(customer.getAddress())
                .build();
    }
}
