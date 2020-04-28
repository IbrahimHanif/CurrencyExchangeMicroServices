package com.microservice.currencyconversionservice.controller;

import java.math.BigDecimal;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.microservice.currencyconversionservice.bean.CurrencyExchange;
import com.microservice.currencyconversionservice.feign.ExchangeServiceFeign;

@RestController
@RequestMapping("currency-conversion")
public class CurrentConversionController {
	
	@Autowired
	private ExchangeServiceFeign exchangeServiceProxy;
	
	private Logger log = LoggerFactory.getLogger(this.getClass());

	
	@GetMapping("/{exchangeCountryCode}/{amount}")
	public CurrencyExchange getCurrencyConversion(@PathVariable String exchangeCountryCode, @PathVariable BigDecimal amount)
	{
		// replace by Feign
		/*
		Map<String, String> urivairables = new HashMap<>();
		urivairables.put("exchangeCountryCode", exchangeCountryCode);
		
		ResponseEntity<CurrencyExchange> responseEntity = new RestTemplate()
				.getForEntity("http://localhost:8000/currency-exchange-service/{exchangeCountryCode}", CurrencyExchange.class, urivairables);
		
		
		CurrencyExchange currencyExchange = responseEntity.getBody();
		*/
		log.info("Currency conversion service get");
		CurrencyExchange currencyExchange = exchangeServiceProxy.getExchangeValue(exchangeCountryCode);
		BigDecimal calculateConversion = amount.multiply(currencyExchange.getExchangeFactor());
		currencyExchange.setConvertedAmount(calculateConversion);
		return currencyExchange;
	}
	
	@PostMapping("/{exchangecountrycode}/{currencyFactor}")
	public ResponseEntity<Object> addExchangeValue(@PathVariable String exchangecountrycode,
			@PathVariable BigDecimal currencyFactor) {
		log.info("Currency conversion service Post");
		ResponseEntity<Object> response = exchangeServiceProxy.addExchangeValue(exchangecountrycode,currencyFactor);
		URI location = ServletUriComponentsBuilder.
				        fromPath("//currency-conversion//"+exchangecountrycode).
				          buildAndExpand().toUri();
		return ResponseEntity.created(location).build();
			
	}
	
	@PutMapping("/{exchangecountrycode}/{currencyFactor}")
	public ResponseEntity<Object> updateExchangeValue(@PathVariable String exchangecountrycode,
			@PathVariable BigDecimal currencyFactor) {
		log.info("Currency conversion service Post");
		ResponseEntity<Object> response = exchangeServiceProxy.updateExchangeValue(exchangecountrycode,currencyFactor);
		URI location = ServletUriComponentsBuilder.
				        fromPath("//currency-conversion//"+exchangecountrycode).
				          buildAndExpand().toUri();
		return ResponseEntity.created(location).build();
		
	}
	

}
