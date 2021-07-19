package com.reba.pojo;

import java.util.Date;


public class DealPojo {

	private int dealId;
	
	private Date date;
	
	private double dealCost;
	
	//private CustomerPojo customer;
	private int customerId;

	//private PropertyPojo propertyPojo;
	private int propertyId;
	
	

//	public PropertyPojo getPropertyPojo() {
//		return propertyPojo;
//	}
//
//	public void setPropertyPojo(PropertyPojo propertyPojo) {
//		this.propertyPojo = propertyPojo;
//	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(int propertyId) {
		this.propertyId = propertyId;
	}

//	public CustomerPojo getCustomer() {
//		return customer;
//	}
//
//	public void setCustomer(CustomerPojo customer) {
//		this.customer = customer;
//	}

	public int getDealId() {
		return dealId;
	}

	public void setDealId(int dealId) {
		this.dealId = dealId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getDealCost() {
		return dealCost;
	}

	public void setDealCost(double dealCost) {
		this.dealCost = dealCost;
	}
}
