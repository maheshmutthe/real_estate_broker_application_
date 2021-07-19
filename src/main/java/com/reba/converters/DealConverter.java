package com.reba.converters;

import com.reba.entities.Deal;
import com.reba.pojo.DealPojo;

public class DealConverter {
	
	/*
	 * In this converter, one static method is present which converts a deal entity
	 * 	into a deal pojo.
	 */
	
	public static DealPojo convert(Deal deal) {
		DealPojo dealPojo = new DealPojo();
		dealPojo.setDealId(deal.getDealId());
		dealPojo.setDealCost(deal.getDealCost());
		dealPojo.setDate(deal.getDate());
		dealPojo.setCustomerId(deal.getCustomer().getUserId());
		dealPojo.setPropertyId(deal.getProperty().getPropId());
		return dealPojo;
	}
	
}
