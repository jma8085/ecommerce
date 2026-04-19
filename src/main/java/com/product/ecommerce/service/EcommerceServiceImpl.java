package com.product.ecommerce.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.ecommerce.persistence.Prices;
import com.product.ecommerce.repository.PricesRespository;

@Service
public class EcommerceServiceImpl implements EcommerceService {
	
	@Autowired
	PricesRespository pricesRepository;
	
	public Prices getProductInfoPrice(Date dateIn, Long productId, Long brandId) {
		
		List<Prices> prices = pricesRepository.findByProductIdAndBrandIdOrderByPriorityDesc(productId, brandId);
		Optional<Prices> selectPrices = prices.stream().filter(price -> dateIn.after(price.getStartDate()) && dateIn.before(price.getEndDate())).findFirst();
		
		if (selectPrices.isPresent()) {
			return selectPrices.get();
		}
		
		return null;
		
	}
}
