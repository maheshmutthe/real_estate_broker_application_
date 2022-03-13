package com.reba.converters;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.reba.entities.Customer;
import com.reba.entities.Deal;
import com.reba.entities.Property;
import com.reba.pojo.CustomerPojo;
import com.reba.pojo.DealPojo;
import com.reba.pojo.PropertyPojo;

public class CustomerConverter {

	public static CustomerPojo convert(Customer customer) {
		CustomerPojo customerPojo = new CustomerPojo();
		customerPojo.setUserId(customer.getUserId());
		customerPojo.setUserName(customer.getUserName());
		customerPojo.setUserEmail(customer.getUserEmail());
		customerPojo.setUserPassword(customer.getUserPassword());
		customerPojo.setUserMobile(customer.getUserMobile());
		customerPojo.setUserRole(customer.getUserRole());
		customerPojo.setUserCity(customer.getUserCity());
		
		Set<Property> custPropSet = customer.getProperties();
		List<PropertyPojo> custProperties = new ArrayList<>();
		for(Property prop : custPropSet) {
			custProperties.add(PropertyConverter.convert(prop));
		}
		customerPojo.setCustProp(custProperties);
		
		Set<Deal> custDeals = customer.getDeals();
		List<DealPojo> custDealPojo = new ArrayList<>();
		for(Deal deal: custDeals) {
			custDealPojo.add(DealConverter.convert(deal));
		}
		customerPojo.setDealPojo(custDealPojo);
		return customerPojo;
	}
	
	public static Customer convertToEntity(CustomerPojo customerPojo) {
		Customer customer = new Customer();
		if(customerPojo.getUserId() != 0) {
			customer.setUserId(customerPojo.getUserId());
		}
		customer.setUserName(customerPojo.getUserName());
		customer.setUserEmail(customerPojo.getUserEmail());
		customer.setUserPassword(customerPojo.getUserPassword());
		customer.setUserMobile(customerPojo.getUserMobile());
		customer.setUserRole(customerPojo.getUserRole());
		customer.setUserCity(customerPojo.getUserCity());
		return customer;
	}
	
}
