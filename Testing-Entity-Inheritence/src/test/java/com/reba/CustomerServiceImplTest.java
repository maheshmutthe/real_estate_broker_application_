package com.reba;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.reba.entities.Customer;
import com.reba.entities.Property;
import com.reba.exception.CustomerNotFoundException;
import com.reba.repo.CustomerRepository;
import com.reba.repo.DealRepository;
import com.reba.repo.PropertyRepository;
import com.reba.service.CustomerServiceImpl;

@SpringBootTest
public class CustomerServiceImplTest {
	
	@InjectMocks
	CustomerServiceImpl customerService;

	@Mock
	CustomerRepository customerRepo;
	
	@Mock
	PropertyRepository propRepo;
	
	@Mock
	DealRepository dealRepo;
	
	private Customer customer;
	
	@BeforeEach
	public void init() {
		customer = new Customer();
		customer.setUserPassword("ram6");
		customer.setUserId(1300);
		customer.setUserRole("Customer");
		customer.setUserMobile("8754126");
		customer.setUserCity("Kolkata");
		customer.setUserName("Patil");
	}
	
	@AfterEach
	public void clear() {
		customer = null;
	}

	@Test
	@DisplayName("Testing Add Customer")
	public void testAddCustomer() {
		Mockito.when(customerRepo.saveAndFlush(customer)).thenReturn(customer);
		assertEquals(customer,customerService.addCustomer(customer));
	}

	@Test
	@DisplayName("Testing Add Customer Not Equal")
	public void testAddCustomerNotEqual() {
		Customer customerSamp = new Customer();
		customerSamp.setUserPassword("Nirav96");
		customerSamp.setUserId(1000);
		customerSamp.setUserRole("Broker");
		customerSamp.setUserMobile("858785");
		customerSamp.setUserCity("Kolkata");
		customerSamp.setUserName("Patel");

		Mockito.when(customerRepo.saveAndFlush(customerSamp)).thenReturn(customerSamp);
		assertNotEquals(customer,customerService.addCustomer(customerSamp));
	}

	@Test
	@DisplayName("Testing Exception In Add Customer")
	public void testExceptionInAddCustomer() {
		Mockito.when(customerRepo.saveAndFlush(customer)).thenReturn(null);
		Exception exception = assertThrows(CustomerNotFoundException.class, () -> customerService.addCustomer(customer));
		assertEquals("No Customer Found",exception.getMessage());
	}
	
	@Test
	@DisplayName("Testing Edit Customer")
	public void testEditCustomer() {
		Mockito.when(customerRepo.findById(1300)).thenReturn(Optional.of(customer));
		customer.setUserCity("Delhi");
		Mockito.when(customerRepo.saveAndFlush(customer)).thenReturn(customer);
		assertEquals(customer,customerService.editCustomer(customer));
	}

	@Test
	@DisplayName("Testing Remove Customer")
	public void testRemoveCustomer() {
		Set<Property> propSet = new HashSet<>();
		Property property = new Property();
		property.setPropId(500);
		propSet.add(property);
		customer.setProperties(propSet);
		
		Mockito.when(customerRepo.findById(1300)).thenReturn(Optional.of(customer));
		Mockito.when(propRepo.findById(500)).thenReturn(Optional.of(property));
		assertEquals(customer,customerService.removeCustomer(1300));
	}
	
	@Test
	@DisplayName("Testing View Customer")
	public void testViewCustomer() {
		Mockito.when(customerRepo.findById(1300)).thenReturn(Optional.of(customer));
		assertEquals(customer,customerService.viewCustomer(1300));
	}
	
	@Test
	@DisplayName("Testing List All Customer")
	public void testListAllCustomer() {
		Customer customer1 = new Customer();
		customer1.setUserPassword("Nirav96");
		customer1.setUserRole("Customer");
		customer1.setUserMobile("858785");
		customer1.setUserCity("Kolkata");
		customer1.setUserName("Patel");

		List<Customer> customerList = new ArrayList<>();
		customerList.add(customer);
		customerList.add(customer1);

		Mockito.when(customerRepo.findAll()).thenReturn(customerList);
		assertEquals(customerList,customerService.listAllCustomer());

	}

	@Test
	@DisplayName("Testing List All Customer Not Equal")
	public void testListAllCustomersNotEqual() {
		Customer customer1 = new Customer();
		customer1.setUserPassword("Nirav96");
		customer1.setUserRole("Broker");
		customer1.setUserMobile("858785");
		customer1.setUserCity("Kolkata");
		customer1.setUserName("Patel");

		List<Customer> customerList = new ArrayList<>();
		customerList.add(customer);
		customerList.add(customer1);

		Mockito.when(customerRepo.findAll()).thenReturn(customerList);
		assertEquals(customerList,customerService.listAllCustomer());
		assertNotEquals(1, customerService.listAllCustomer().size());
	}

	@Test
	@DisplayName("Testing List All Customer Exception")
	public void testListAllCustomerException() {
		List<Customer> customerList = new ArrayList<>();

		Mockito.when(customerRepo.findAll()).thenReturn(customerList);
		Exception exception = assertThrows(CustomerNotFoundException.class, () -> customerService.listAllCustomer());
		assertEquals("No Customer Found", exception.getMessage());
	}


	@Test
	public void testViewCustomerByIdException() {
		Mockito.when(customerRepo.findById(1300)).thenReturn(Optional.of(customer));
		Exception exception = assertThrows(CustomerNotFoundException.class, () -> customerService.viewCustomer(1050));
		assertEquals("Customer Not Found", exception.getMessage());
	}
	
}
