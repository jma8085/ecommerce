package com.product.ecommerce.control;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.product.ecommerce.persistence.Prices;
import com.product.ecommerce.repository.PricesRespository;

import tools.jackson.databind.ObjectMapper;

@RequestMapping("/ecommerce/api")
public class EcommerceController {
	
	private final ObjectMapper mapper = new ObjectMapper();
	
	@Autowired
	PricesRespository pricesRepository;
	
	@GetMapping("/productinfo")
	public ResponseEntity<String> getProductInfoPrice(@RequestParam String date, @RequestParam Long productId, @RequestParam String brandId) {
		
		String result = validRequiredParameters(date, productId, brandId);
		
		if (result != null) {
			return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}
		
		Date dateIn = validAndParseDate(date);
		
		if (dateIn == null) {
			return new ResponseEntity<>("Param 'date' is malformed", HttpStatus.BAD_REQUEST);
		}
		
		List<Prices> prices = pricesRepository.findByProductIdBrandIdOrderByPriceListAsc(productId, brandId);
		Optional<Prices> selectPrices = prices.stream().filter(price -> dateIn.after(price.getStartDate()) && dateIn.before(price.getEndDate())).findFirst();
		
		if (selectPrices.isPresent()) {
			return new ResponseEntity<>(mapper.writeValueAsString(selectPrices.get()), HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
	private String validRequiredParameters(String date, Long productId, String brandId) {
		String var = date==null? "date" : productId == null? "productId" : brandId == null? "branId" : null;
		return var == null? null : "Param " + var + " is required";
	}
	
	private Date validAndParseDate(String date) {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss");
			LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
			return Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());
		} 
		catch(DateTimeParseException e) {
			// LOGGER
			return null;
		}
	}

}
