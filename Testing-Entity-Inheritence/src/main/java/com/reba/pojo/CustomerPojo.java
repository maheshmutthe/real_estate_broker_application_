package com.reba.pojo;

import java.util.List;

public class CustomerPojo extends UserPojo{
	
	private List<PropertyPojo> custProp;

	private List<DealPojo> dealPojo;
	
	public List<DealPojo> getDealPojo() {
		return dealPojo;
	}

	public void setDealPojo(List<DealPojo> dealPojo) {
		this.dealPojo = dealPojo;
	}

	public List<PropertyPojo> getCustProp() {
		return custProp;
	}

	public void setCustProp(List<PropertyPojo> custProp) {
		this.custProp = custProp;
	}
	
	
}
