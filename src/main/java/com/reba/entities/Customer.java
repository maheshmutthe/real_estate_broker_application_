package com.reba.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "customer_inherit_jwt")
@DiscriminatorValue("C")
public class Customer extends User{

	private static final long serialVersionUID = 1L;
	
	@OneToMany(mappedBy = "customer")
	private Set<Property> properties = new HashSet<>();
	
	@OneToMany(mappedBy = "customer")
	private Set<Deal> deals = new HashSet<>();

	public Set<Deal> getDeals() {
		return deals;
	}

	public void setDeals(Set<Deal> deals) {
		this.deals = deals;
	}

	public Set<Property> getProperties() {
		return properties;
	}

	public void setProperties(Set<Property> properties) {
		this.properties = properties;
	}
	
}
