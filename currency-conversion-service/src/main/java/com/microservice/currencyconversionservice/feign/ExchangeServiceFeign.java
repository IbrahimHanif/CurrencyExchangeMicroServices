package com.microservice.currencyconversionservice.feign;

import java.math.BigDecimal;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.microservice.currencyconversionservice.bean.CurrencyExchange;


//@FeignClient(name="currency-exchange-service",url="localhost:8000")
// changed the following to route the request through api gateway
//@FeignClient(name="currency-exchange-service")
@FeignClient(name="api-gateway-service")
@RibbonClient(name="currency-exchange-service")
// @RequestMapping("currency-exchange")
// api gate way requirred application name before the URI
@RequestMapping("currency-exchange-service/currency-exchange")
public interface ExchangeServiceFeign  {
	@GetMapping("/{exchangecountrycode}")
	public CurrencyExchange getExchangeValue(@PathVariable String exchangecountrycode);
	
	@PostMapping("/{exchangecountrycode}/{currencyFactor}")
	public ResponseEntity<Object> addExchangeValue(@PathVariable String exchangecountrycode,
			@PathVariable BigDecimal currencyFactor);
	
	@PutMapping("/{exchangecountrycode}/{currencyFactor}")
	public ResponseEntity<Object> updateExchangeValue(@PathVariable String exchangecountrycode,
			@PathVariable BigDecimal currencyFactor);

}
