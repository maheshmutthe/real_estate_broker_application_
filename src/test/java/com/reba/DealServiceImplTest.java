package com.reba;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.reba.entities.Customer;
import com.reba.entities.Deal;
import com.reba.entities.Property;
import com.reba.exception.DealNotFoundException;
import com.reba.repo.CustomerRepository;
import com.reba.repo.DealRepository;
import com.reba.repo.PropertyRepository;
import com.reba.service.DealServiceImpl;

@SpringBootTest
public class DealServiceImplTest {
	@InjectMocks
	DealServiceImpl dealService;

	@Mock
	DealRepository dealRepo;
	
	@Mock
	CustomerRepository custRepo;
	
	@Mock
	PropertyRepository propRepo;

	@Test
	public void testListAllDeals() {
		Deal deal1 = new Deal();
		deal1.setDealId(2000);
		deal1.setDealCost(20000);
		deal1.setDate(null);
		deal1.setCustomer(null);
		deal1.setProperty(null);

		Deal deal2 = new Deal();
		deal2.setDealId(3000);
		deal2.setDealCost(30000);
		deal2.setDate(null);
		deal2.setCustomer(null);
		deal2.setProperty(null);

		List<Deal> dealList = new ArrayList<>();
		dealList.add(deal1);
		dealList.add(deal2);

		Mockito.when(dealRepo.findAll()).thenReturn(dealList);
		assertEquals(dealList,dealService.listAllDeals());

	}

	// Test Case for addDeal in service layer

	@Test
	@DisplayName("Testing Add Deal")
	public void testAddDeal() {
		Customer customer = new Customer();
		customer.setUserId(500);
		
		Property property = new Property();
		property.setPropId(1300);
		property.setStatus(true);
		
		Deal deal = new Deal();
		deal.setDealId(2000);
		deal.setDealCost(20000);
		deal.setDate(new Date());
		deal.setCustomer(customer);
		deal.setProperty(property);

		Mockito.when(custRepo.findById(500)).thenReturn(Optional.of(customer));
		Mockito.when(propRepo.findById(1300)).thenReturn(Optional.of(property));
		Mockito.when(dealRepo.saveAndFlush(deal)).thenReturn(deal);
		assertEquals(deal,dealService.addDeal(1300,500));
	}

	@Test
	@DisplayName("Testing List All Deals Exception")
	public void testListAllDealsException() {
		List<Deal> dealList = new ArrayList<>();

		Mockito.when(dealRepo.findAll()).thenReturn(dealList);

		Exception exception = assertThrows(DealNotFoundException.class, () -> dealService.listAllDeals());
		assertEquals("No Deals Found", exception.getMessage());
	}
}
