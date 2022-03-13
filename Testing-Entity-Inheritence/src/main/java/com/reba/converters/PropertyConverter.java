package com.reba.converters;

import com.reba.entities.Broker;
import com.reba.entities.Customer;
import com.reba.entities.Deal;
import com.reba.entities.Property;
import com.reba.pojo.PropertyPojo;

public class PropertyConverter {
	
	/*
	 * Two methods are defined in this converter.
	 * One method converts property entity into pojo.
	 * Another one converts property pojo into entity.
	 */
	
	public static PropertyPojo convert(Property property) {
		PropertyPojo propPojo = new PropertyPojo();
		propPojo.setPropId(property.getPropId());
		propPojo.setPropConfig(property.getPropConfig());
		propPojo.setOfferType(property.getOfferType());
		propPojo.setOfferCost(property.getOfferCost());
		propPojo.setAreaSqft(property.getAreaSqft());
		propPojo.setAddress(property.getAddress());
		propPojo.setCity(property.getCity());
		propPojo.setStreet(property.getStreet());
		propPojo.setStatus(property.isStatus());
		
		Broker broker = property.getBroker();
		if(broker != null) {
			propPojo.setBrokerPojo(BrokerConverter.converWithoutList(broker));
		}
		
		Customer customer = property.getCustomer();
		if(customer != null) {
			propPojo.setCustomerId(customer.getUserId());
		}
		
		Deal deal = property.getDeal();
		if(deal != null) {
			propPojo.setDealId(deal.getDealId());
		}
		
		return propPojo;
	}
	
	public static Property convertToEntity(PropertyPojo propertyPojo) {
		Property property = new Property();
		
		if(propertyPojo.getPropId() != 0) {
			property.setPropId(propertyPojo.getPropId());
		}
		property.setPropConfig(propertyPojo.getPropConfig());
		property.setOfferType(propertyPojo.getOfferType());
		property.setOfferCost(propertyPojo.getOfferCost());
		property.setAreaSqft(propertyPojo.getAreaSqft());
		property.setAddress(propertyPojo.getAddress());
		property.setStreet(propertyPojo.getStreet());
		property.setCity(propertyPojo.getCity());
		
		return property;
	}
	
}
