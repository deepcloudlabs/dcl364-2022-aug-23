package com.example.crm.service.business;

import java.util.Optional;

import com.example.crm.entity.Customer;
import com.example.crm.service.CustomerService;

public class StandardCustomerService implements CustomerService {

	@Override
	public Optional<Customer> findCustomerByIdentity(String identity) {
		return Optional.of(
				new Customer(identity, "kate austen", "kate@example.com", 
						"+90 555 5555")
		);
	}

}
