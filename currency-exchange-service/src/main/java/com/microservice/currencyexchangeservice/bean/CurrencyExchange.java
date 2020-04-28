package com.microservice.currencyexchangeservice.bean;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class CurrencyExchange {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(name="currency_code")
	private String currencyCode;
	@Column(name="exchange_currency_code")
	private String exchangeCurrencyCode;
	@Column(name="exchange_factor")
	private BigDecimal exchangeFactor;
	
	@Transient 
	private String port;

	public CurrencyExchange() {
		super();
	}

	public CurrencyExchange(String currencyCode, String exchangeCurrencyCode,
			BigDecimal exchangeFactor) {
		super();
		this.currencyCode = currencyCode;
		this.exchangeCurrencyCode = exchangeCurrencyCode;
		this.exchangeFactor = exchangeFactor;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getExchangeCurrencyCode() {
		return exchangeCurrencyCode;
	}

	public void setExchangeCurrencyCode(String exchangeCurrencyCode) {
		this.exchangeCurrencyCode = exchangeCurrencyCode;
	}


	public BigDecimal getExchangeFactor() {
		return exchangeFactor;
	}
	public void setExchangeFactor(BigDecimal exchangeFactor) {
		this.exchangeFactor = exchangeFactor;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}


	@Override
	public String toString() {
		return "CurrencyExchangeDetails [id=" + id + ", currencyCode=" + currencyCode + ", exchangeCurrencyCode="
				+ exchangeCurrencyCode + ", exchangeFactor=" + exchangeFactor + "]";
	}
	
	
	
}
