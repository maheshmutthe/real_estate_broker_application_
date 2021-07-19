package com.reba;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.reba.entities.Broker;
import com.reba.entities.Property;
import com.reba.exception.PropertyNotFoundExceptions;
import com.reba.pojo.PropertyCriteria;
import com.reba.repo.BrokerRepository;
import com.reba.repo.PropertyCriteriaRepository;
import com.reba.repo.PropertyRepository;
import com.reba.service.PropertyServiceImpl;

@SpringBootTest
public class PropertyServiceImplTest {
	
	@InjectMocks
	PropertyServiceImpl propService;
	
	@Mock
	PropertyRepository propRepo;
	
	@Mock
	BrokerRepository brokerRepo;
	
	@Mock
	PropertyCriteriaRepository propCriteriaRepo;
	
	private Property property;
	
	@BeforeEach
	public void init() {
		property = new Property(1500,"House","50 off",500.0,250.0,"La la land","La la","Ababa");
	}
	
	@AfterEach
	public void clean() {
		property = null;
	}
	
	@Test
	@DisplayName("Testing Add Property")
	public void testAddProperty() {
		Broker broker = new Broker();
		broker.setUserId(50);
		property.setBroker(broker);
		
		Mockito.when(brokerRepo.findById(50)).thenReturn(Optional.of(broker));
		Mockito.when(propRepo.saveAndFlush(property)).thenReturn(property);
		assertEquals(property,propService.addProperty(property, 50));
	}
	
	@Test
	@DisplayName("Test Edit Service")
	public void testEditProperty() {
		Mockito.when(propRepo.findById(1500)).thenReturn(Optional.of(property));
		property.setAddress("Yo Yo Land");
		Mockito.when(propRepo.saveAndFlush(property)).thenReturn(property);
		assertEquals(property,propService.editProperty(property));
	}
	
	@Test
	@DisplayName("Testing Edit Property Exception")
	public void testEditPropertyException() {
		Mockito.when(propRepo.findById(1500)).thenReturn(Optional.empty());
		Exception exception = assertThrows(PropertyNotFoundExceptions.class,() -> propService.editProperty(property));
		assertEquals("Property Could Not Be Edited", exception.getMessage());
	}
	
	@Test
	@DisplayName("Testing Remove Property")
	public void testRemoveProperty() {
		property.setStatus(true);
		Mockito.when(propRepo.findById(1500)).thenReturn(Optional.of(property));
		assertEquals(property, propService.removeProperty(1500));
	}
	
	@Test
	@DisplayName("Testing View Property")
	public void testViewProperty() {
		Mockito.when(propRepo.findById(1500)).thenReturn(Optional.of(property));
		assertEquals(property,propService.viewProperty(1500));
	}
	
	@Test
	@DisplayName("Testing List All Properties")
	public void testListAllProperties() {
		Property property2 = new Property(1501,"House","50 off",500.0,250.0,"La la land","La la","Ababa");
		Property property3 = new Property(1501,"House","50 off",500.0,250.0,"La la land","La la","Ababa");
		List<Property> propList = new ArrayList<>();
		propList.add(property);
		propList.add(property2);
		propList.add(property3);
		
		Mockito.when(propRepo.findAll()).thenReturn(propList);
		assertEquals(propList, propService.listAllProperties());
	}
	
	@Test
	@DisplayName("Testing Find Property By Criteria")
	public void testFindPropertyByCriteria() {
		PropertyCriteria propCriteria = new PropertyCriteria();
		propCriteria.setConfig("House");
		propCriteria.setCity("Ababa");
		propCriteria.setOffer("50 off");
		propCriteria.setMinCost(20.0);
		propCriteria.setMaxCost(600.0);
		
		List<Property> propList = new ArrayList<>();
		propList.add(property);
		
		Mockito.when(propCriteriaRepo.findAll()).thenReturn(propList);
		assertEquals(propList,propService.listPropertyByCriteria(propCriteria));
	}
}
