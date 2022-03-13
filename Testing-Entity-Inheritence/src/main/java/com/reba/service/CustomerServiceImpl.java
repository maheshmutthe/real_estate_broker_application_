package com.reba.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.reba.entities.Customer;
import com.reba.entities.Deal;
import com.reba.entities.Property;
import com.reba.exception.CustomerNotFoundException;
import com.reba.repo.CustomerRepository;
import com.reba.repo.DealRepository;
import com.reba.repo.PropertyRepository;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepo;
	
	@Autowired
	PropertyRepository propRepo;
	
	@Autowired
	DealRepository dealRepo;
	
	@Autowired
	PasswordEncoder bcryptEncoder;
	
	Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);
	
	/*
	 * This method is for adding a customer. If customer could not be added it will 
	 * 	throw an exception.
	 */

	@Override
	public Customer addCustomer(Customer customer) {
		logger.info("Entering service addCustomer()");
		customer.setUserPassword(bcryptEncoder.encode(customer.getUserPassword()));
		Customer customerE = customerRepo.saveAndFlush(customer);
		if(customerE == null) {
			throw new CustomerNotFoundException("No Customer Found");
		}
		return customerE;
	}

	/*
	 * This method is for editing a customer. If Customer could not be edited then it
	 * 	will throw an exception.
	 */
	
	@Override
	public Customer editCustomer(Customer customer) {
		logger.info("Entering service editCustomer()");
		customer.setUserPassword(bcryptEncoder.encode(customer.getUserPassword()));
		Customer customerE = customerRepo.saveAndFlush(customer);
		if(customerE == null) {
			throw new CustomerNotFoundException("No Customer Found");
		}
		return customerE;
	}

	/*
	 * This method is for removing a customer. The moment a customer is removed, its 
	 * 	associated properties and deals will also be removed. If customer not found,
	 * 	it will throw an exception.
	 */
	
	@Override
	public Customer removeCustomer(int custId) {
		logger.info("Entering service removeCustomer()");
		Optional<Customer> customerOpt = customerRepo.findById(custId);
		if(customerOpt.isEmpty()) {
			throw new CustomerNotFoundException("Customer Not Found");
		}
		Customer customer = customerOpt.get();
		Set<Deal> custDeals = customer.getDeals();
		for(Deal deal: custDeals) {
			dealRepo.deleteById(deal.getDealId());
		}
		
		Set<Property> custProps = customer.getProperties();
		for(Property prop: custProps) {
			propRepo.deleteById(prop.getPropId());
		}
		customerRepo.deleteById(custId);
		return customer;
	}
	
	/*
	 * This method is for viewing a customer. If no customer present it
	 * will throw an exception. 
	 */

	@Override
	public Customer viewCustomer(int custId) {
		logger.info("Entering service viewCustomer()");
		Optional<Customer> customerOpt = customerRepo.findById(custId);
		if(customerOpt.isEmpty()) {
			throw new CustomerNotFoundException("Customer Not Found");
		}
		return customerOpt.get();
	}

	/*
	 * This method is for viewing all customers. If no customer present it
	 * will throw an exception. 
	 */
	
	@Override
	public List<Customer> listAllCustomer() {
		logger.info("Entering service listAllCustomer()");
		List<Customer> custList = customerRepo.findAll();
		if(custList == null || custList.isEmpty()) {
			throw new CustomerNotFoundException("No Customer Found");
		}
		return custList;
	}
	
	

}
