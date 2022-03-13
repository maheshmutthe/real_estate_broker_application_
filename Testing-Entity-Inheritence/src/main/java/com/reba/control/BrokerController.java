package com.reba.control;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reba.converters.BrokerConverter;
import com.reba.entities.Broker;
import com.reba.pojo.BrokerPojo;
import com.reba.service.BrokerService;

@CrossOrigin
@RestController
@RequestMapping("/broker")
public class BrokerController {

	@Autowired
	BrokerService brokerService;
	
	Logger logger = LoggerFactory.getLogger(BrokerController.class);
	
	/*
	 * Here controller methods are defined for add, edit, delete, view and 
	 * 	list all brokers.
	 */
	
	@PostMapping("/add")
	public ResponseEntity<?> addBroker(@Valid @RequestBody BrokerPojo brokerP){
		logger.info("Entering addBroker()");
		Broker brokerEntity = BrokerConverter.convertToEntity(brokerP);
		Broker brokerE = brokerService.addBroker(brokerEntity);
		BrokerPojo brokerPojo = BrokerConverter.convert(brokerE);
		return ResponseEntity.ok(brokerPojo);
	}
	
	@PostMapping("/edit")
	public ResponseEntity<?> editBroker(@Valid @RequestBody BrokerPojo brokerP){
		logger.info("Entering editBroker()");
		Broker brokerEntity = BrokerConverter.convertToEntity(brokerP);
		Broker brokerE = brokerService.editBroker(brokerEntity);
		BrokerPojo brokerPojo = BrokerConverter.convert(brokerE);
		return ResponseEntity.ok(brokerPojo);
	}
	
	@PostMapping("/delete")
	public ResponseEntity<?> removeBroker(@RequestBody int broId){
		logger.info("Entering removeBroker()");
		Broker brokerE = brokerService.removeBroker(broId);
		BrokerPojo brokerPojo = BrokerConverter.convert(brokerE);
		return ResponseEntity.ok(brokerPojo);
	}
	
	@PostMapping("/find")
	public ResponseEntity<?> findBroker(@RequestBody int broId) {
		logger.info("Entering findBroker()");
		Broker brokerE = brokerService.viewBroker(broId);
		BrokerPojo brokerPojo = BrokerConverter.convert(brokerE);
		return ResponseEntity.ok(brokerPojo);
	}
	
	
	@GetMapping("/findAll")
	public ResponseEntity<?> findAllBroker() {
		logger.info("Entering findAllBroker()");
		List<Broker> brokerE = brokerService.listAllBrokers();
		List<BrokerPojo> brokerPojos = new ArrayList<>();
		for(Broker broker : brokerE) {
			brokerPojos.add(BrokerConverter.convert(broker));
		}
		return ResponseEntity.ok(brokerPojos);
	}
	
}
