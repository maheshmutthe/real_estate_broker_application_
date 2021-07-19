package com.reba.service;

import java.util.List;

import com.reba.entities.Customer;

public interface CustomerService {

	public Customer addCustomer(Customer customer);
	
	public Customer editCustomer(Customer customer);
	
	public Customer removeCustomer(int custId);
	
	public Customer viewCustomer(int custId);
	
	public List<Customer> listAllCustomer();
}
