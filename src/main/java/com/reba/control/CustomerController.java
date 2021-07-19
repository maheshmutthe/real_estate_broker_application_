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

import com.reba.converters.CustomerConverter;
import com.reba.entities.Customer;
import com.reba.pojo.CustomerPojo;
import com.reba.service.CustomerService;

@CrossOrigin
@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	CustomerService customerService;
	
	Logger logger = LoggerFactory.getLogger(CustomerController.class);
	
	/*
	 * Here controller methods are defined for add, edit, view, remove and 
	 * 	list all customer.
	 */
	
	@PostMapping("/add")
	public ResponseEntity<?> addCustomer(@Valid @RequestBody CustomerPojo customerP){
		logger.info("Entering addCustomer()");
		Customer customerEntity = CustomerConverter.convertToEntity(customerP);
		Customer customerE = customerService.addCustomer(customerEntity);
		CustomerPojo customerPojo = CustomerConverter.convert(customerE);
		return ResponseEntity.ok(customerPojo);
	}
	
	@PostMapping("/edit")
	public ResponseEntity<?> editCustomer(@Valid @RequestBody CustomerPojo customerP){
		logger.info("Entering editCustomer()");
		Customer customerEntity = CustomerConverter.convertToEntity(customerP);
		Customer customerE = customerService.editCustomer(customerEntity);
		CustomerPojo customerPojo = CustomerConverter.convert(customerE);
		return ResponseEntity.ok(customerPojo);
	}
	
	@PostMapping("/delete")
	public ResponseEntity<?> removeCustomer(@RequestBody int custId){
		logger.info("Entering removeCustomer()");
		Customer customerE = customerService.removeCustomer(custId);
		CustomerPojo customerPojo = CustomerConverter.convert(customerE);
		return ResponseEntity.ok(customerPojo);
	}
	
	@PostMapping("/find")
	public ResponseEntity<?> findCustomer(@RequestBody int custId){
		logger.info("Entering findCustomer()");
		Customer customerE = customerService.viewCustomer(custId);
		CustomerPojo customerPojo = CustomerConverter.convert(customerE);
		return ResponseEntity.ok(customerPojo);
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<?> findAllCustomer(){
		logger.info("Entering findAllCustomer()");
		List<Customer> customerE = customerService.listAllCustomer();
		List<CustomerPojo> customerPojo = new ArrayList<>();
		for(Customer customer: customerE) {
			customerPojo.add(CustomerConverter.convert(customer));
		}
		return ResponseEntity.ok(customerPojo);
	}
	
}
