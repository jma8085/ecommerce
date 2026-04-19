package com.product.ecommerce.control;

import java.time.Instant;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.product.ecommerce.persistence.Prices;
import com.product.ecommerce.service.EcommerceService;

import tools.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/ecommerce/api")
public class EcommerceController {
	
	private final ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	EcommerceService ecommerceService;
	
	/**
	 * Get the price of the product
	 * @param date Parameter to define the moment of the price. Format yyyy-mm-ddTHH:MM:SSZ. This parameter is required
	 * @param prudctId Product identification number. This parameter is required
	 * @param brandId Product brand identification number. This parameter is required
	 * @return JSON product related with the query information. HTTP-Error in other cases
	 */
	@GetMapping("/productinfo")
	public ResponseEntity<String> getProductInfoPrice(@RequestParam String date, @RequestParam Long productId, @RequestParam Long brandId) {
		
		Date dateIn = validAndParseDate(date);
		
		if (dateIn == null) {
			return new ResponseEntity<>("Parameter 'date' is malformed.", HttpStatus.BAD_REQUEST);
		}
		
		Prices prices = ecommerceService.getProductInfoPrice(dateIn, productId, brandId);

		
		if (prices != null) {
			return new ResponseEntity<>(mapper.writeValueAsString(prices), HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	private Date validAndParseDate(String date) {
		try {
			Instant instant = Instant.parse(date);
			return Date.from(instant);
		} 
		catch(DateTimeParseException e) {
			// LOGGER
			return null;
		}
	}

}
