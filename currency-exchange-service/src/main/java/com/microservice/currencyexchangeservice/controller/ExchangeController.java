package com.microservice.currencyexchangeservice.controller;

import java.math.BigDecimal;
import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.microservice.currencyexchangeservice.bean.CurrencyExchange;
import com.microservice.currencyexchangeservice.exception.ResourceNotFoundException;
import com.microservice.currencyexchangeservice.repository.ExchangeRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;


@RestController
@RequestMapping("currency-exchange")
public class ExchangeController {
	
	@Autowired
	private ExchangeRepository exchangeRepo;
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    private Environment env;
	
	@GetMapping("/{exchangecountrycode}")
	@HystrixCommand(fallbackMethod = "fallbackgetExchangeValue")
	public CurrencyExchange getExchangeValue(@PathVariable String exchangecountrycode){
		log.info("Currency conversion service Get");
		if(exchangeRepo.findByexchangeCurrencyCode(exchangecountrycode) == null)
			throw new ResourceNotFoundException("Requested Exchange Country currenct "+ exchangecountrycode + " is not found");
		CurrencyExchange result = exchangeRepo.findByexchangeCurrencyCode(exchangecountrycode);
		 result.setPort(env.getProperty("local.server.port"));
		return result;
			
	}
	
	public CurrencyExchange fallbackgetExchangeValue(@PathVariable String exchangecountrycode)
	{
		log.info("Fall Back Method");
		CurrencyExchange result = new CurrencyExchange("INR",exchangecountrycode,BigDecimal.valueOf(-1));
		result.setPort(env.getProperty("local.server.port"));
		return result;
	}
	
	@PostMapping("/{exchangecountrycode}/{currencyFactor}")
	public ResponseEntity<Object> addExchangeValue(@PathVariable String exchangecountrycode,
			@PathVariable BigDecimal currencyFactor) {
		log.info("Currency conversion service Post");
		if( exchangeRepo.findByexchangeCurrencyCode(exchangecountrycode) == null) {
			exchangeRepo.save(new CurrencyExchange("INR",exchangecountrycode,currencyFactor));
		}
		else {
			throw new ResourceNotFoundException("Requested Exchange Country currenct "+ exchangecountrycode + " is not found");
		}
		
//		URI location = ServletUriComponentsBuilder.
//				       fromCurrentRequest().
//				       buildAndExpand(exchangeRepo.findByexchangeCurrencyCode(exchangecountrycode)).toUri();
		
		URI location = ServletUriComponentsBuilder.
				        fromPath("//currency-exchange//"+exchangecountrycode).
				          buildAndExpand().toUri();
		return ResponseEntity.created(location).build();
			
	}
	
	@PutMapping("/{exchangecountrycode}/{currencyFactor}")
	public ResponseEntity<Object> updateExchangeValue(@PathVariable String exchangecountrycode,
			@PathVariable BigDecimal currencyFactor) {
		log.info("Currency conversion service Put");
		CurrencyExchange exchangeCurrency = exchangeRepo.findByexchangeCurrencyCode(exchangecountrycode); 
		if(exchangeCurrency == null)
		    throw new ResourceNotFoundException("Requested Exchange Country currenct "+ exchangecountrycode + " is not found");
		exchangeCurrency.setExchangeFactor(currencyFactor);
		exchangeRepo.save(exchangeCurrency);
		URI location = ServletUriComponentsBuilder.
		        fromPath("//currency-exchange//"+exchangecountrycode).
		          buildAndExpand().toUri();
        return ResponseEntity.created(location).build();
		
	}

}
