package com.product.ecommerce;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import com.product.ecommerce.control.EcommerceController;

@SpringBootTest
@WebMvcTest(EcommerceController.class)
class EcommerceApplicationTests {
	
	private final String baseAPIUri = "/ecommerce/api/productinfo";
	
	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
	}
	
	@Test
	void dateFormatProductoInfoTest() throws Exception {
		mockMvc.perform(get(baseAPIUri + "?date=2020-06-14-10:00:00&productId=35455&brandId=1"))
		.andExpect(status().isBadRequest())
		.andExpect(content().string("Param date is malformed"));
	}
	
	@Test
	void dateRequiredProductoInfoTest() throws Exception {		
		mockMvc.perform(get(baseAPIUri + "?productId=3545&brandId=1"))
		.andExpect(status().isBadRequest())
		.andExpect(content().string("Param date is required"));
	}

	@Test
	void branIdRequiredProductoInfoTest() throws Exception {		
		mockMvc.perform(get(baseAPIUri + "?date=2020-06-14-10.00.00&productId=35455"))
		.andExpect(status().isBadRequest())
		.andExpect(content().string("Param brandId is required"));
	}
	
	@Test
	void productIdRequiredProductoInfoTest() throws Exception {	
		mockMvc.perform(get(baseAPIUri + "?date=2020-06-14-10.00.00&brandId=1"))
		.andExpect(status().isBadRequest())
		.andExpect(content().string("Param productId is required"));
	}
	
	@Test
	void noContentByProductIdProductoInfoTest() throws Exception {	
		mockMvc.perform(get(baseAPIUri + "?date=2020-06-14-10.00.00&brandId=1&productId=35456"))
		.andExpect(status().isNoContent());
	}
	
	@Test
	void noContentBybrandIdProductoInfoTest() throws Exception {	
		mockMvc.perform(get("/api/product?date=2020-06-14-10.00.00&brandId=2&productId=35455"))
		.andExpect(status().isNoContent());
	}
	
	@Test
	void noContentByDateProductoInfoTest() throws Exception {	
		mockMvc.perform(get(baseAPIUri + "?date=2026-06-14-10.00.00&brandId=1&productId=35455"))
		.andExpect(status().isNoContent());
	}
	
	@Test
	void getProductoInfoTest() throws Exception {
		mockMvc.perform(get(baseAPIUri + "?date=2020-06-14-10.00.00&productId=35455&brandId=1"))
		.andExpect(status().isOk())
		.andExpect(content().string("{'productId': 35455, 'brandId': 1, 'priceList': 1, startDate: '2020-06-14-00.00.00', endDate: '2020-12-31-23.59.59', price: 35.50}"));
		
		mockMvc.perform(get(baseAPIUri + "?date=2020-06-14-16.00.00&productId=35455&brandId=1"))
		.andExpect(status().isOk())
		.andExpect(content().string("{'productId': 35455, 'brandId': 1, 'priceList': 2, startDate: '2020-06-14-15.00.00', endDate: '2020-06-14-18.30.00', price: 25.45}"));
		
		mockMvc.perform(get(baseAPIUri + "?date=2020-06-14-21.00.00&productId=35455&brandId=1"))
		.andExpect(status().isOk())
		.andExpect(content().string("{'productId': 35455, 'brandId': 1, 'priceList': 1, startDate: '2020-06-14-00.00.00', endDate: '2020-12-31-23.59.59', price: 35.50}"));
		
		mockMvc.perform(get(baseAPIUri + "?date=2020-06-15-10.00.00&productId=35455&brandId=1"))
		.andExpect(status().isOk())
		.andExpect(content().string("{'productId': 35455, 'brandId': 1, 'priceList': 3, startDate: '2020-06-14-00.00.00', endDate: '2020-06-15-10.00.00', price: 30.50}"));
		
		mockMvc.perform(get(baseAPIUri + "?date=2020-06-16-21.00.00&productId=35455&brandId=1"))
		.andExpect(status().isOk())
		.andExpect(content().string("{'productId': 35455, 'brandId': 1, 'priceList': 4, startDate: '2020-06-15-16.00.00', endDate: '2020-12-31-23.59.59', price: 38.95}"));
	}

}
