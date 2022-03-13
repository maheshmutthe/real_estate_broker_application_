package com.reba.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "broker_inherit_jwt")
@DiscriminatorValue("B")
public class Broker extends User{

	private static final long serialVersionUID = 1L;
	
	@OneToMany(mappedBy = "broker")
	private Set<Property> properties = new HashSet<>();

	public Set<Property> getProperties() {
		return properties;
	}

	public void setProperties(Set<Property> properties) {
		this.properties = properties;
	}
	
}
