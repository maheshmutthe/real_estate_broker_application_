package com.reba.service;

import java.util.List;

import com.reba.entities.Deal;

public interface DealService {
	
	public Deal addDeal(int propId, int custId);
	
	public List<Deal> listAllDeals();
}
