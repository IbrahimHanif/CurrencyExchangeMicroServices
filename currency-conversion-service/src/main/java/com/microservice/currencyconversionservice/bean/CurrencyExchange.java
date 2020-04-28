package com.microservice.currencyconversionservice.bean;

import java.math.BigDecimal;


public class CurrencyExchange {
	
	private Long id;
	
	private String currencyCode;
	private String exchangeCurrencyCode;
	private BigDecimal exchangeFactor;
	private BigDecimal convertedAmount;
	private String port;
	
	public CurrencyExchange() {
		super();
	}

	public CurrencyExchange(Long id, String currencyCode, String exchangeCurrencyCode, BigDecimal exchangeFactor,
			BigDecimal convertedAmount, String port) {
		super();
		this.id = id;
		this.currencyCode = currencyCode;
		this.exchangeCurrencyCode = exchangeCurrencyCode;
		this.exchangeFactor = exchangeFactor;
		this.convertedAmount = convertedAmount;
		this.port = port;
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

	public BigDecimal getConvertedAmount() {
		return convertedAmount;
	}

	public void setConvertedAmount(BigDecimal convertedAmount) {
		this.convertedAmount = convertedAmount;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	@Override
	public String toString() {
		return "CurrencyExchange [id=" + id + ", currencyCode=" + currencyCode + ", exchangeCurrencyCode="
				+ exchangeCurrencyCode + ", exchangeFactor=" + exchangeFactor + ", convertedAmount=" + convertedAmount
				+ ", port=" + port + "]";
	}

	
}
