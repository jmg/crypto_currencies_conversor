package com.bitcoin.calculator;

import java.util.Locale;

public class Currency {
	
	private String id;
	private String name;
	
	public Currency(String id, String name) {
		super();
		this.id = id;
		this.setName(name);
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

	public boolean isBTCtoUSD() {
		
		String id = this.getId();
		return id.substring(0, 7).toUpperCase(Locale.getDefault()).equals("BTC_USD");
	}

	public boolean isBTCExchangeCurrency() {
		
		String[] parts = this.getId().split("_");
		return parts[1].toUpperCase(Locale.getDefault()).equals("BTC");
	}	
}
