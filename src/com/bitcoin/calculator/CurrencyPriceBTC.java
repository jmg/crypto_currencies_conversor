package com.bitcoin.calculator;

public class CurrencyPriceBTC extends CurrencyPrice {

	private CurrencyPrice btcCurrency;

	public CurrencyPriceBTC(String id, String price, String name, CurrencyPrice btcCurrency) {
		
		super(id, price, name);
		this.btcCurrency = btcCurrency;
	}
	
	@Override
	public String getPrice() {
		
		if (this.btcCurrency == null) {
			return "-";
		}
		
		Float price = Float.parseFloat(this.price) * this.btcCurrency.getFloatPrice(); 
		return price.toString();
	}
}
