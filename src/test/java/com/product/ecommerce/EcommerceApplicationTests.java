package com.product.ecommerce;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class EcommerceApplicationTests {
	
	private final String baseAPIUri = "/ecommerce/api/productinfo";
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	/**
	 * Test - Use the service  with a bad format date
	 * @throws Exception Throw any unexpected error
	 */
	void dateFormatProductoInfoTest() throws Exception {
		mockMvc.perform(get(baseAPIUri + "?date=2020-06-14-10:00:00Z&productId=35455&brandId=1"))
		.andExpect(status().isBadRequest())
		.andExpect(content().string("Parameter 'date' is malformed."));
	}
	
	@Test
	/**
	 * Test - Use the service  without required parameter 'date'
	 * @throws Exception Throw any unexpected error
	 */
	void dateRequiredProductoInfoTest() throws Exception {		
		mockMvc.perform(get(baseAPIUri + "?productId=3545&brandId=1"))
		.andExpect(status().isBadRequest())
		.andExpect(content().string("Parameter 'date' is required."));
	}

	@Test
	/**
	 * Test - Use the service  without required parameter 'brandId'
	 * @throws Exception Throw any unexpected error
	 */
	void branIdRequiredProductoInfoTest() throws Exception {		
		mockMvc.perform(get(baseAPIUri + "?date=2020-06-14T10:00:00Z&productId=35455"))
		.andExpect(status().isBadRequest())
		.andExpect(content().string("Parameter 'brandId' is required."));
	}
	
	@Test
	/**
	 * Test - Use the service  without required parameter 'productId'
	 * @throws Exception Throw any unexpected error
	 */
	void productIdRequiredProductoInfoTest() throws Exception {	
		mockMvc.perform(get(baseAPIUri + "?date=2020-06-14T10:00:00Z&brandId=1"))
		.andExpect(status().isBadRequest())
		.andExpect(content().string("Parameter 'productId' is required."));
	}
	
	@Test
	/**
	 * Test - Use the service  with a productId that not exists
	 * @throws Exception Throw any unexpected error
	 */
	void noContentByProductIdProductoInfoTest() throws Exception {	
		mockMvc.perform(get(baseAPIUri + "?date=2020-06-14T10:00:00Z&brandId=1&productId=35456"))
		.andExpect(status().isNoContent());
	}
	
	@Test
	/**
	 * Test - Use the service  with a brandId that not exists
	 * @throws Exception Throw any unexpected error
	 */
	void noContentBybrandIdProductoInfoTest() throws Exception {	
		mockMvc.perform(get(baseAPIUri + "?date=2020-06-14T10:00:00Z&brandId=2&productId=35455"))
		.andExpect(status().isNoContent());
	}
	
	@Test
	/**
	 * Test - Use the service  with a date that not exists
	 * @throws Exception Throw any unexpected error
	 */
	void noContentByDateProductoInfoTest() throws Exception {	
		mockMvc.perform(get(baseAPIUri + "?date=2026-06-14T10:00:00Z&brandId=1&productId=35455"))
		.andExpect(status().isNoContent());
	}
	
	@Test
	/**
	 * Test - Use the service with a query date 2020-06-14T10:00:00Z
	 * @throws Exception Throw any unexpected error
	 */
	void getProductoInfoFrom14At10Test() throws Exception {
		mockMvc.perform(get(baseAPIUri + "?date=2020-06-14T10:00:00Z&productId=35455&brandId=1"))
		.andExpect(status().isOk())
		.andExpect(content().json("{'productId':35455, 'brandId':1, 'priceList':1, 'startDate':'2020-06-14T00:00:00.000Z', 'endDate':'2020-12-31T23:59:59.000Z', 'price':35.5}"));
	}

	@Test
	/**
	 * Test - Use the service  with a query date 2020-06-14T16:00:00Z
	 * @throws Exception Throw any unexpected error
	 */
	void getProductoInfoFrom14At16Test() throws Exception {		
		mockMvc.perform(get(baseAPIUri + "?date=2020-06-14T16:00:00Z&productId=35455&brandId=1"))
		.andExpect(status().isOk())
		.andExpect(content().json("{'productId':35455, 'brandId':1, 'priceList':2, 'startDate':'2020-06-14T15:00:00.000Z', 'endDate':'2020-06-14T18:30:00.000Z', 'price': 25.45}"));
	}

	@Test
	/**
	 * Test - Use the service  with a query date 2020-06-14T21:00:00Z
	 * @throws Exception Throw any unexpected error
	 */
	void getProductoInfoFrom14At21Test() throws Exception {		
		mockMvc.perform(get(baseAPIUri + "?date=2020-06-14T21:00:00Z&productId=35455&brandId=1"))
		.andExpect(status().isOk())
		.andExpect(content().json("{'productId':35455, 'brandId':1, 'priceList':1, 'startDate':'2020-06-14T00:00:00.000Z', 'endDate':'2020-12-31T23:59:59.000Z', 'price':35.5}"));
	}

	@Test
	/**
	 * Test - Use the service  with a query date 2020-06-15T10:00:00Z
	 * @throws Exception Throw any unexpected error
	 */
	void getProductoInfoFrom15At10Test() throws Exception {				
		mockMvc.perform(get(baseAPIUri + "?date=2020-06-15T10:00:00Z&productId=35455&brandId=1"))
		.andExpect(status().isOk())
		.andExpect(content().json("{'productId':35455, 'brandId':1, 'priceList':3, 'startDate':'2020-06-15T00:00:00.000Z', 'endDate':'2020-06-15T11:00:00.000Z', 'price':30.5}"));
	}


	@Test
	/**
	 * Test - Use the service  with a query date 2020-06-16T21:00:00Z
	 * @throws Exception Throw any unexpected error
	 */
	void getProductoInfoFrom16At21Test() throws Exception {				
		mockMvc.perform(get(baseAPIUri + "?date=2020-06-16T21:00:00Z&productId=35455&brandId=1"))
		.andExpect(status().isOk())
		.andExpect(content().json("{'productId':35455, 'brandId':1, 'priceList':4, 'startDate':'2020-06-15T16:00:00.000Z', 'endDate':'2020-12-31T23:59:59.000Z', 'price': 38.95}"));
	}

}
