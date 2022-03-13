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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reba.converters.PropertyConverter;
import com.reba.entities.Property;
import com.reba.pojo.PropertyCriteria;
import com.reba.pojo.PropertyPojo;
import com.reba.service.PropertyService;

@CrossOrigin
@RestController
@RequestMapping("/property")
public class PropertyController {

	@Autowired
	PropertyService propService;
	
	Logger logger = LoggerFactory.getLogger(PropertyController.class);
	
	/*
	 * Methods defined here are for adding,editing,removing,viewing, viewing all and
	 * 	view by criteria for properties.
	 * Add and Edit methods accept a valid pojo, convert to entity and pass to service 
	 * 	layer, and then convert back the returned entity to pojo and return the pojo.
	 * Remove and find method take property id argument and return a pojo.
	 * Find all method doesnt take any argument and return a list of property pojos.
	 * Find by criteria takes a criteria pojo, and returns a list of property pojo.
	 */
	
	@PostMapping("/add/{broId}")
	public ResponseEntity<?> addProperty(@Valid @RequestBody PropertyPojo propertyP, @PathVariable("broId") int broId){
		logger.info("Entering addProperty()");
		Property propertyEntity = PropertyConverter.convertToEntity(propertyP);
		Property propertyE = propService.addProperty(propertyEntity,broId);
		PropertyPojo propPojo = PropertyConverter.convert(propertyE);
		return ResponseEntity.ok(propPojo);
	}
	
	@PostMapping("/edit")
	public ResponseEntity<?> editProperty(@Valid @RequestBody PropertyPojo propertyP){
		logger.info("Entering editProperty()");
		Property propertyEntity = PropertyConverter.convertToEntity(propertyP);
		Property propertyE = propService.editProperty(propertyEntity);
		PropertyPojo propPojo = PropertyConverter.convert(propertyE);
		return ResponseEntity.ok(propPojo);
	}
	
	@PostMapping("/remove")
	public ResponseEntity<?> removeProperty(@RequestBody int propId){
		logger.info("Entering removeProperty()");
		Property propertyE = propService.removeProperty(propId);
		PropertyPojo propPojo = PropertyConverter.convert(propertyE);
		return ResponseEntity.ok(propPojo);
	}
	
	@PostMapping("/find")
	public ResponseEntity<?> viewProperty(@RequestBody int propId){
		logger.info("Entering viewProperty()");
		Property propertyE = propService.viewProperty(propId);
		PropertyPojo propPojo = PropertyConverter.convert(propertyE);
		return ResponseEntity.ok(propPojo);
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<?> listAllProperty(){
		logger.info("Entering listAllProperty()");
		List<Property> propertyE = propService.listAllProperties();
		List<PropertyPojo> propPojo = new ArrayList<>();
		for(Property property: propertyE) {
			propPojo.add(PropertyConverter.convert(property));
		}
		return ResponseEntity.ok(propPojo);
	}
	
	@GetMapping("/findAllUnsold")
	public ResponseEntity<?> listAllUnsoldProperty(){
		logger.info("Entering listAllProperty()");
		List<Property> propertyE = propService.listAllUnsoldProperties();
		List<PropertyPojo> propPojo = new ArrayList<>();
		for(Property property: propertyE) {
			propPojo.add(PropertyConverter.convert(property));
		}
		return ResponseEntity.ok(propPojo);
	}
	
	@PostMapping("/findByCriteria")
	public ResponseEntity<?> findByCriteria(@RequestBody PropertyCriteria propertyCriteria){
		logger.info("Entering findByCriteria()");
		List<Property> propertyE = propService.listPropertyByCriteria(propertyCriteria);
		List<PropertyPojo> propPojo = new ArrayList<>();
		for(Property property: propertyE) {
			propPojo.add(PropertyConverter.convert(property));
		}
		return ResponseEntity.ok(propPojo);
	}
	
	
	
}
