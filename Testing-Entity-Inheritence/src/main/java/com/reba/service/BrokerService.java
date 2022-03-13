package com.reba.service;

import java.util.List;

import com.reba.entities.Broker;

public interface BrokerService {
	
	public Broker addBroker(Broker broker);
	
	public Broker editBroker(Broker broker);
	
	public Broker removeBroker(int brokerId);
	
	public Broker viewBroker(int brokerId);
	
	public List<Broker> listAllBrokers();
}
