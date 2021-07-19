package com.reba.converters;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.reba.entities.Broker;
import com.reba.entities.Property;
import com.reba.pojo.BrokerPojo;
import com.reba.pojo.BrokerPojoWl;
import com.reba.pojo.PropertyPojo;

public class BrokerConverter {
	public static BrokerPojo convert(Broker broker) {
		BrokerPojo broPojo = new BrokerPojo();
		broPojo.setUserId(broker.getUserId());
		broPojo.setUserName(broker.getUserName());
		broPojo.setUserEmail(broker.getUserEmail());
		broPojo.setUserPassword(broker.getUserPassword());
		broPojo.setUserMobile(broker.getUserMobile());
		broPojo.setUserRole(broker.getUserRole());
		broPojo.setUserCity(broker.getUserCity());
		Set<Property> broPropSet = broker.getProperties();
		List<PropertyPojo> broProperties = new ArrayList<>();
		for(Property prop : broPropSet) {
			broProperties.add(PropertyConverter.convert(prop));
		}
		broPojo.setBroProp(broProperties);
		return broPojo;
	}
	
	public static BrokerPojoWl converWithoutList(Broker broker) {
		BrokerPojoWl broPojo = new BrokerPojoWl();
		broPojo.setUserId(broker.getUserId());
		broPojo.setUserName(broker.getUserName());
		broPojo.setUserEmail(broker.getUserEmail());
		broPojo.setUserPassword(broker.getUserPassword());
		broPojo.setUserMobile(broker.getUserMobile());
		broPojo.setUserRole(broker.getUserRole());
		broPojo.setUserCity(broker.getUserCity());
		return broPojo;
	}
	
	public static Broker convertToEntity(BrokerPojo brokerPojo) {
		Broker broker = new Broker();
		if(brokerPojo.getUserId() != 0) {
			broker.setUserId(brokerPojo.getUserId());
		}
		broker.setUserName(brokerPojo.getUserName());
		broker.setUserEmail(brokerPojo.getUserEmail());
		broker.setUserPassword(brokerPojo.getUserPassword());
		broker.setUserMobile(brokerPojo.getUserMobile());
		broker.setUserRole(brokerPojo.getUserRole());
		broker.setUserCity(brokerPojo.getUserCity());
		return broker;
	}
}
