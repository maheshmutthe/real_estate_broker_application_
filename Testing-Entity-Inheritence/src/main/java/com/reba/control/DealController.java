package com.reba.control;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reba.converters.DealConverter;
import com.reba.entities.Deal;
import com.reba.pojo.DealDetailPojo;
import com.reba.pojo.DealPojo;
import com.reba.service.DealService;

@CrossOrigin
@RestController
@RequestMapping("/deal")
public class DealController {

	@Autowired
	DealService dealService;
	
	Logger logger = LoggerFactory.getLogger(DealController.class);
	
	/*
	 * Here methods are defined to add a deal and view all deals.
	 * Add deal method takes customer id and property id and returns a deal pojo.
	 * List all deals will return a list of deal pojos.
	 */
	
	@PostMapping("/saveDeal")
	public ResponseEntity<?> addDeal(@RequestBody DealDetailPojo detail){
		logger.info("Entering addDeal()");
		Deal deal = dealService.addDeal(detail.getPropId(),detail.getCustId());
		DealPojo dealPojo = DealConverter.convert(deal);
		return ResponseEntity.ok(dealPojo);
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<?> findAll(){
		logger.info("Entering findAll()");
		List<Deal> deals = dealService.listAllDeals();
		List<DealPojo> dealPojos = new ArrayList<>();
		for(Deal deal: deals) {
			dealPojos.add(DealConverter.convert(deal));
		}
		return ResponseEntity.ok(dealPojos);
	}
	
}
