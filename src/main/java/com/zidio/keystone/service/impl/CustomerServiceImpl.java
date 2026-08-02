package com.zidio.keystone.service.impl;

import com.zidio.keystone.dto.CustomerRequest;
import com.zidio.keystone.dto.CustomerResponse;
import com.zidio.keystone.service.CustomerService;
import org.springframework.stereotype.Service;
import com.zidio.keystone.entity.Customer;
import com.zidio.keystone.repository.CustomerRepository;

import java.util.List;
import com.zidio.keystone.exception.DuplicateResourceException;
import com.zidio.keystone.exception.ResourceNotFoundException;
import com.zidio.keystone.mapper.CustomerMapper;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerResponse createCustomer(CustomerRequest request) {
        if (customerRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("A customer with this email already exists");
        }
        return CustomerMapper.toResponse(customerRepository.save(CustomerMapper.toEntity(request)));
    }

    @Override
    public List<CustomerResponse> getAllCustomers() {
        return customerRepository.findAll().stream().map(CustomerMapper::toResponse).toList();
    }

    @Override
    public CustomerResponse getCustomerById(Long id) {
        return CustomerMapper.toResponse(findCustomer(id));
    }

    @Override
    public CustomerResponse updateCustomer(Long id, CustomerRequest request) {
        Customer customer = findCustomer(id);
        if (!customer.getEmail().equalsIgnoreCase(request.getEmail()) && customerRepository.existsByEmail(request.getEmail())) {
            throw new DuplicateResourceException("A customer with this email already exists");
        }
        Customer updatedCustomer = CustomerMapper.toEntity(request);
        customer.setCustomerName(updatedCustomer.getCustomerName());
        customer.setCompanyName(updatedCustomer.getCompanyName());
        customer.setEmail(updatedCustomer.getEmail());
        customer.setPhone(updatedCustomer.getPhone());
        customer.setAddress(updatedCustomer.getAddress());
        return CustomerMapper.toResponse(customerRepository.save(customer));
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.delete(findCustomer(id));
    }

    private Customer findCustomer(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer", id));
    }
}
