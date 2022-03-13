package com.reba.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reba.entities.Customer;
import com.reba.entities.Deal;
import com.reba.entities.Property;
import com.reba.exception.CustomerNotFoundException;
import com.reba.exception.DealCouldNotBeMadeException;
import com.reba.exception.DealNotFoundException;
import com.reba.exception.PropertyNotFoundExceptions;
import com.reba.repo.CustomerRepository;
import com.reba.repo.DealRepository;
import com.reba.repo.PropertyRepository;

@Service("dealService")
public class DealServiceImpl implements DealService{

	@Autowired
	DealRepository dealRepo;
	
	@Autowired 
	PropertyRepository propRepo;
	
	@Autowired
	CustomerRepository custRepo;
	
	Logger logger = LoggerFactory.getLogger(DealServiceImpl.class);
	
	/*
	 * The add deal method is executed when a customer wants to buy a property.
	 * It takes the customer id and property id as arguments.
	 * First it will see if the customer exists, if not it throws an exception.
	 * Then it checks if property exists, if not throws an exception.
	 * Then it checks if the property is sold already, if not it throws an exception.
	 * If all the above checks are cleared, only then a deal is made and saved.
	 */
	
	@Override
	public Deal addDeal(int propId, int custId) {
		logger.info("Entering service addDeal()");
		Optional<Customer> customerOpt = custRepo.findById(custId);
		if(customerOpt.isEmpty()) {
			throw new CustomerNotFoundException("Customer Not FOund");
		}
		Customer customer = customerOpt.get();
		
		Optional<Property> propertyOpt = propRepo.findById(propId);
		if(propertyOpt.isEmpty()) {
			throw new PropertyNotFoundExceptions("Property Not Found");
		}
		Property property = propertyOpt.get();
		
		if(!property.isStatus()) {
			throw new DealCouldNotBeMadeException("Deal Could Not Be Made");
		}
		
		Deal deal = new Deal();
		property.setStatus(false);
		property.setCustomer(customer);
		
		Date date = new Date();
		deal.setDate(date);
		
		deal.setCustomer(customer);
		deal.setProperty(property);
		deal.setDealCost(property.getOfferCost());
		return dealRepo.saveAndFlush(deal);
	}
	
	/*
	 * The list all deals method will find all the deals in the database.
	 * If there are no deals, it will throw an exception.
	 */
	
	@Override
	public List<Deal> listAllDeals() {
		logger.info("Entering service listAllDeals()");
		List<Deal> deals = dealRepo.findAll();
		if(deals == null || deals.isEmpty()) {
			throw new DealNotFoundException("No Deals Found");
		}
		return deals;
	}

}
