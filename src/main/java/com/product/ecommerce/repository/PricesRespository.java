package com.product.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.product.ecommerce.persistence.Prices;

public interface PricesRespository extends JpaRepository<Prices, Long> {
	
	/**
	 * Find Prices in Database by productId and brandId
	 * @param productId Parameter to filter the query
	 * @param brandId Parameter to filter the query
	 * @return A List with the selected Prices 
	 */
	public List<Prices> findByProductIdAndBrandIdOrderByPriorityDesc(Long productId, Long brandId);

}
