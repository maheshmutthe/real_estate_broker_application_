package com.reba.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.reba.entities.Broker;
import com.reba.entities.Property;
import com.reba.exception.PropertyNotFoundExceptions;
import com.reba.pojo.PropertyCriteria;
import com.reba.repo.BrokerRepository;
import com.reba.repo.PropertyCriteriaRepository;
import com.reba.repo.PropertyRepository;

@Service("propService")
public class PropertyServiceImpl implements PropertyService{

	@Autowired
	PropertyRepository propRepo;
	
	@Autowired
	PropertyCriteriaRepository propCriteriaRepo;
	
	@Autowired 
	BrokerRepository brokerRepo;
	
	Logger logger = LoggerFactory.getLogger(PropertyServiceImpl.class);
	
	/*
	 * This method is for adding a property.
	 * The property entity is passed to this function as well as brokerId.
	 * Broker Id is passed so that when property will be added to database,
	 * the respective broker will be mapped to it.
	*/
	
	@Override
	public Property addProperty(Property property,int broId) {
		logger.info("Entering service addProperty()");
		Broker broker = brokerRepo.findById(broId).get();
		property.setBroker(broker);
		property.setStatus(true);
		Property propertyE = propRepo.saveAndFlush(property);
		if(propertyE == null) {
			throw new PropertyNotFoundExceptions("Property Could Not Be Added");
		}
		return propertyE;
	}
	
	/*
	 * This method will edit the property. It will first fetch the required property
	 * from the repository. 
	 * Some fields are cannot be edited here like property Id, status and broker who
	 * listed the property
	 */

	@Override
	public Property editProperty(Property property) {
		logger.info("Entering service editProperty()");
		Optional<Property> propertyOpt = propRepo.findById(property.getPropId());
		if(propertyOpt.isEmpty()) {
			throw new PropertyNotFoundExceptions("Property Could Not Be Edited");
		}
		Property propertyE = propertyOpt.get();
		propertyE.setPropConfig(property.getPropConfig());
		propertyE.setOfferType(property.getOfferType());
		propertyE.setOfferCost(property.getOfferCost());
		propertyE.setAreaSqft(property.getAreaSqft());
		propertyE.setAddress(property.getAddress());
		propertyE.setStreet(property.getStreet());
		propertyE.setCity(property.getCity());
		return propRepo.saveAndFlush(propertyE);
	}
	
	/*
	 * This method will remove a property whose id we pass as argument.
	 * It will first check if given property is present in repository, if not
	 * 	it will throw an exception.
	 * If property is present, then it will check its status, if it is already sold 
	 * 	then the broker cannot delete the property from database, only the broker field 
	 * 	in property entity will be null.
	 * If it is not sold, then the broker can delete the property listed by him from
	 * 	the database.
	 */

	@Override
	public Property removeProperty(int propId) {
		logger.info("Entering service removeProperty()");
		Optional<Property> propOpt = propRepo.findById(propId);
		if(propOpt.isEmpty()) {
			throw new PropertyNotFoundExceptions("Property Could Not Be Deleted");
		}
		Property property = propOpt.get();
		if(!property.isStatus()) {
			throw new PropertyNotFoundExceptions("Property Cannot Be Deleted As It Is Sold");
		}
		propRepo.deleteById(propId);
		return property;
	}
	
	/*
	 * This method will show the property details corresponding to the property id.
	 * If given property is not found, then an exception is thrown.
	 */

	@Override
	public Property viewProperty(int propId) {
		logger.info("Entering service viewProperty()");
		Optional<Property> propOpt = propRepo.findById(propId);
		if(propOpt.isEmpty()) {
			throw new PropertyNotFoundExceptions("No Property Found");
		}
		return propOpt.get();
	}
	
	/*
	 * This method will list all the properties in the database.
	 * If no properties are present, it will throw an exception.
	 */

	@Override
	public List<Property> listAllProperties() {
		logger.info("Entering service listAllProperties()");
		List<Property> propList = propRepo.findAll();
		if(propList == null || propList.isEmpty()) {
			throw new PropertyNotFoundExceptions("No Properties Found");
		}
		return propList;
	}
	
	@Override
	public List<Property> listAllUnsoldProperties() {
		logger.info("Entering service listAllProperties()");
		List<Property> propList = propRepo.getAllUnsold();
		if(propList == null || propList.isEmpty()) {
			throw new PropertyNotFoundExceptions("No Properties Found");
		}
		return propList;
	}
	
	/*
	 * The next two methods are for filtering properties according to some user
	 * 	defined criteria.
	 * The first method accepts the propertycriteria pojo, and it is passed to the 
	 * 	second function which then builds the query depending on the criterias mentioned
	 * 	in the propertycriteria pojo and returns the list of properties.
	 * If no properties are found, it will throw an exception.
	 */

	@Override
	public List<Property> listPropertyByCriteria(PropertyCriteria propertyCriteria) {
		logger.info("Entering service listPropertyByCriteria()");
		String config = propertyCriteria.getConfig();
		String offer = propertyCriteria.getOffer();
		String city = propertyCriteria.getCity();
		double minCost = propertyCriteria.getMinCost();
		double maxCost = propertyCriteria.getMaxCost();
		
		List<Property> propList = findByCriteria(config,offer,city,minCost,maxCost);
		if(propList == null || propList.isEmpty()) {
			throw new PropertyNotFoundExceptions("No Property Found");
		}
		List<Property> finalPropList = new ArrayList<>();
		for(Property prop: propList) {
			if(prop.isStatus()) {
				finalPropList.add(prop);
			}
		}
		return finalPropList;
	}
	
	public List<Property> findByCriteria(String config,String offer,String city,double minCost,double maxCost){
		return propCriteriaRepo.findAll(new Specification<Property>() {
			@Override
			public Predicate toPredicate(Root<Property> root,CriteriaQuery<?> query,CriteriaBuilder criteriaBuilder) {
				List<Predicate> predicates = new ArrayList<>();
				if(config != null) {
					predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("propConfig"), config)));
				}
				if(offer != null) {
					predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("offerType"), offer)));
				}
				if(city != null) {
					predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("city"),city)));
				}
				if(minCost >= 0.0 && maxCost > minCost) {
					predicates.add(criteriaBuilder.between(root.get("offerCost"),minCost,maxCost));
				}
				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		});
	}

}
