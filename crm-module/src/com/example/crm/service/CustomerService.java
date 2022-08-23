package com.example.crm.service;

import java.util.Optional;

import com.example.crm.entity.Customer;

public interface CustomerService {
	Optional<Customer> findCustomerByIdentity(String identity);
}
