package com.bitcoin.calculator;

import java.util.Locale;

public class CurrencyPrice {
	
	String id;
	String name;
	String price;
	
	public CurrencyPrice(String id, String price, String name) {
		super();
		this.id = id;
		this.price = price;
		this.name = name;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPrice() {
		return price;
	}
	
	public void setPrice(String price) {
		this.price = price;
	}

	public Float getFloatPrice() {
		
		try {
			return Float.parseFloat(this.getPrice());
		}
		catch (Exception e) {
			return Float.valueOf(0);
		}		
	}

	public String getSymbol() {
		
		String[] parts = this.getId().split("_");
		return parts[0].toUpperCase(Locale.getDefault());
	}
}
