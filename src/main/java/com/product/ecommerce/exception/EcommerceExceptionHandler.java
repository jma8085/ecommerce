package com.product.ecommerce.exception;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class EcommerceExceptionHandler {
	
	@ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    /**
     * Catch MissingServletRequestParameterException to control the returned message
     * @param ex Exception to catch
     * @return The controlled HTTP-Response
     */
    public ResponseEntity<String> handleMissingParams(MissingServletRequestParameterException ex) {
		
		List<String> paramsController = Arrays.asList("brandId", "date", "productId");
		
		String message = "Unrecognized sent parameter.";
		
        if (paramsController.contains(ex.getParameterName())) {
            message = "Parameter '" + ex.getParameterName()+ "' is required.";
        }

        return ResponseEntity.badRequest().body(message);
    }

}
