package com.reba.pojo;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PropertyPojo {
	
	private int propId;
	
	@NotNull
	@Size(min = 3, max = 20)
	private String propConfig;
	
	@NotNull
	@Size(min = 3, max = 20)
	private String offerType;
	
	@DecimalMin("100.0")
	private double offerCost;
	
	@DecimalMin("30.0")
	private double areaSqft;
	
	@NotNull
	@Size(min = 3, max = 20)
	private String address;
	
	@NotNull
	@Size(min = 3, max = 20)
	private String street;
	
	@NotNull
	@Size(min = 3, max = 20)
	private String city;
	
	private boolean status;
	
	private BrokerPojoWl brokerPojo;
	//private int brokerId;
	
	//private CustomerPojo customerPojo;
	private int customerId;
	
	//private DealPojo dealPojo;
	private int dealId;
	
	public int getDealId() {
		return dealId;
	}

	public void setDealId(int dealId) {
		this.dealId = dealId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getPropId() {
		return propId;
	}

	public void setPropId(int propId) {
		this.propId = propId;
	}

	public String getPropConfig() {
		return propConfig;
	}

	public void setPropConfig(String propConfig) {
		this.propConfig = propConfig;
	}

	public String getOfferType() {
		return offerType;
	}

	public void setOfferType(String offerType) {
		this.offerType = offerType;
	}

	public double getOfferCost() {
		return offerCost;
	}

	public void setOfferCost(double offerCost) {
		this.offerCost = offerCost;
	}

	public double getAreaSqft() {
		return areaSqft;
	}

	public void setAreaSqft(double areaSqft) {
		this.areaSqft = areaSqft;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public BrokerPojoWl getBrokerPojo() {
		return brokerPojo;
	}

	public void setBrokerPojo(BrokerPojoWl brokerPojo) {
		this.brokerPojo = brokerPojo;
	}

	
}
