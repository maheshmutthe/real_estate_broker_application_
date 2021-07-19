package com.reba.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.reba.entities.Broker;
import com.reba.entities.Property;
import com.reba.exception.BrokerNotFoundException;
import com.reba.repo.BrokerRepository;
import com.reba.repo.PropertyRepository;

@Service("brokerService")
public class BrokerServiceImpl implements BrokerService{

	@Autowired
	BrokerRepository brokerRepo;
	
	@Autowired
	PropertyRepository propRepo;
	
	@Autowired
	PasswordEncoder bcryptEncoder;
	
	Logger logger = LoggerFactory.getLogger(BrokerServiceImpl.class);
	
	/*
	 * This method is for adding a broker. If broker could not be added it will 
	 * 	throw an exception.
	 */
	
	@Override
	public Broker addBroker(Broker broker) {
		logger.info("Entering service addBroker()");
		broker.setUserPassword(bcryptEncoder.encode(broker.getUserPassword()));
		Broker brokerE = brokerRepo.saveAndFlush(broker);
		if(brokerE == null) {
			throw new BrokerNotFoundException("No Broker Found");
		}
		return brokerE;
	}

	/*
	 * This method is for editing a broker. If broker could not be edited then it
	 * 	will throw an exception.
	 */
	
	@Override
	public Broker editBroker(Broker broker) {
		logger.info("Entering service editBroker()");
		broker.setUserPassword(bcryptEncoder.encode(broker.getUserPassword()));
		Broker brokerE = brokerRepo.saveAndFlush(broker);
		if(brokerE == null) {
			throw new BrokerNotFoundException("No Broker Found");
		}
		return brokerE;
	}

	/*
	 * This method is for removing a broker. If broker is not found, will throw an 
	 * 	exception. 
	 * If the brokers property is sold, then it will just set the broker field in
	 * 	property table as null and then delete the broker. If property is unsold,
	 * 	then it will be deleted along with the broker.
	 */
	
	@Override
	public Broker removeBroker(int brokerId) {
		logger.info("Entering service removeBroker()");
		Optional<Broker> brokerOpt = brokerRepo.findById(brokerId);
		if(brokerOpt.isEmpty()) {
			throw new BrokerNotFoundException("No Broker Found");
		}
		Broker broker = brokerOpt.get();
		Set<Property> broProps = broker.getProperties();
		for(Property prop: broProps) {
			if(prop.isStatus()) {
				propRepo.deleteById(prop.getPropId());
			} else {
				prop.setBroker(null);
			}
		}
		brokerRepo.deleteById(brokerId);
		return broker;
	}

	/*
	 * This method is for viewing a broker. If broker not found, then an exception is
	 * 	thrown.
	 */
	
	@Override
	public Broker viewBroker(int brokerId) {
		logger.info("Entering service viewBroker()");
		Optional<Broker> brokerOpt = brokerRepo.findById(brokerId);
		if(brokerOpt.isEmpty()) {
			throw new BrokerNotFoundException("No Broker Found");
		}
		return brokerOpt.get();
	}

	/*
	 * This method is to list all brokers. If not found will throw an exception.
	 */
	
	@Override
	public List<Broker> listAllBrokers() {
		logger.info("Entering service listAllBrokers()");
		List<Broker> brokerList = brokerRepo.findAll();
		if(brokerList.isEmpty()) {
			throw new BrokerNotFoundException("No Broker Found");
		}
		return brokerList;
	}

	
}
