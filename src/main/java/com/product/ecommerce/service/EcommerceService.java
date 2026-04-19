package com.product.ecommerce.service;

import java.util.Date;

import com.product.ecommerce.persistence.Prices;

public interface EcommerceService {

	/**
	 * Get the price of the product service
	 * @param dateIn Parameter to define the moment of the price. Format yyyy-mm-ddTHH:MM:SSZ
	 * @param prudctId Product identification number.
	 * @param brandId Product brand identification number.
	 * @return Prices product related with the query information. Or Null if there is not data related.
	 */
	public Prices getProductInfoPrice(Date dateIn, Long productId, Long brandId);

}
