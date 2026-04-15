package com.product.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.product.ecommerce.persistence.Prices;

public interface PricesRespository extends JpaRepository<Prices, Long> {
	
	public List<Prices> findByProductIdBrandIdOrderByPriceListAsc(Long productId, String brandId);

}
