package com.reba;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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
import com.reba.exception.BrokerNotFoundException;
import com.reba.repo.BrokerRepository;
import com.reba.repo.PropertyRepository;
import com.reba.service.BrokerServiceImpl;

@SpringBootTest
public class BrokerServiceImplTest {

	@InjectMocks
	BrokerServiceImpl brokerService;

	@Mock
	BrokerRepository brokerRepo;
	
	@Mock
	PropertyRepository propRepo;
	
	private Broker broker;
	
	@BeforeEach
	public void init() {
		broker = new Broker();
		broker.setUserPassword("Nirav96");
		broker.setUserId(1000);
		broker.setUserRole("Broker");
		broker.setUserMobile("858785");
		broker.setUserCity("Kolkata");
		broker.setUserName("Patel");
	}
	
	@AfterEach
	public void clear() {
		broker = null;
	}

	@Test
	@DisplayName("Testing Add Broker")
	public void testAddBroker() {
		Mockito.when(brokerRepo.saveAndFlush(broker)).thenReturn(broker);
		assertEquals(broker,brokerService.addBroker(broker));
	}

	@Test
	@DisplayName("Testing Add Broker Not Equal")
	public void testAddBrokerNotEqual() {
		Broker bro = new Broker();

		Mockito.when(brokerRepo.saveAndFlush(bro)).thenReturn(broker);
		assertNotEquals(bro,brokerService.addBroker(bro));
	}
	
	@Test
	@DisplayName("Testing Edit Broker")
	public void testEditBroker() {
		Broker broker2 = new Broker();
		broker2.setUserPassword("sohel65");
		broker2.setUserId(1000);
		broker2.setUserRole("Broker");
		broker2.setUserMobile("457878");
		broker2.setUserCity("Chennai");
		broker2.setUserName("ImranKhan");
		broker2.setUserCity("Delhi");

		Mockito.when(brokerRepo.saveAndFlush(broker2)).thenReturn(broker2);
		assertEquals(broker2,brokerService.editBroker(broker2));
	}

	@Test
	@DisplayName("Testing Remove Broker")
	public void testRemoveBroker() {
		Mockito.when(brokerRepo.findById(1000)).thenReturn(Optional.of(broker));
		assertEquals(broker,brokerService.removeBroker(1000));
	}
	
	@Test
	@DisplayName("Testing View Broker")
	public void testViewBroker() {
		Mockito.when(brokerRepo.findById(1000)).thenReturn(Optional.of(broker));
		assertEquals(broker,brokerService.viewBroker(1000));
	}

	@Test
	@DisplayName("Testing View Broker Exception")
	public void testViewBrokerByIdException() {
		Mockito.when(brokerRepo.findById(1000)).thenReturn(Optional.of(broker));
		Exception exception = assertThrows(BrokerNotFoundException.class, () -> brokerService.viewBroker(1050));
		assertEquals("No Broker Found", exception.getMessage());
	}
	
	@Test
	@DisplayName("Testing List All Brokers")
	public void testListAllBrokers() {
		Broker broker2 = new Broker();
		broker2.setUserPassword("sohel65");
		broker2.setUserRole("Broker");
		broker2.setUserMobile("457878");
		broker2.setUserCity("Chennai");
		broker2.setUserName("Khan");

		List<Broker> brokerList = new ArrayList<>();
		brokerList.add(broker);
		brokerList.add(broker2);

		Mockito.when(brokerRepo.findAll()).thenReturn(brokerList);
		assertEquals(brokerList,brokerService.listAllBrokers());
		assertEquals(2, brokerService.listAllBrokers().size());
	}

	@Test
	@DisplayName("Testing List All Brokers Not Equal")
	public void testListAllBrokersNotEqual() {

		Broker broker2 = new Broker();
		broker2.setUserPassword("sohel65");
		broker2.setUserRole("Broker");
		broker2.setUserMobile("457878");
		broker2.setUserCity("Chennai");
		broker2.setUserName("Khan");

		List<Broker> brokerList = new ArrayList<>();
		brokerList.add(broker);
		brokerList.add(broker2);
		
		List<Broker> brokerList2 = new ArrayList<>();
		brokerList2.add(broker2);

		Mockito.when(brokerRepo.findAll()).thenReturn(brokerList);
		assertNotEquals(brokerList2,brokerService.listAllBrokers());
		assertNotEquals(1, brokerService.listAllBrokers().size());
	}

	@Test
	@DisplayName("Testing List All Broker Exception")
	public void testListAllBrokerException() {
		List<Broker> brokerList = new ArrayList<>();

		Mockito.when(brokerRepo.findAll()).thenReturn(brokerList);

		Exception exception = assertThrows(BrokerNotFoundException.class, () -> brokerService.listAllBrokers());
		assertEquals("No Broker Found", exception.getMessage());
	}

}
