package com.microservice.currencyexchangeservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.currencyexchangeservice.bean.CurrencyExchange;

public interface ExchangeRepository extends JpaRepository<CurrencyExchange, Long>{
	
	CurrencyExchange findByexchangeCurrencyCode(String exchangeCurrencyCode);

}
