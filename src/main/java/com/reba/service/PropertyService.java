package com.reba.service;

import java.util.List;

import com.reba.entities.Property;
import com.reba.pojo.PropertyCriteria;

public interface PropertyService {

	public Property addProperty(Property property,int broId);
	
	public Property editProperty(Property property);
	
	public Property removeProperty(int propId);
	
	public Property viewProperty(int propId);
	
	public List<Property> listAllProperties();
	
	public List<Property> listAllUnsoldProperties();
	
	public List<Property> listPropertyByCriteria(PropertyCriteria propertyCriteria);
}
