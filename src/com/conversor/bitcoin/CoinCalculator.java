package com.conversor.bitcoin;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.bitcoin.R;

public class CoinCalculator {
	
	private CoinApi coinApi = new CoinApi();
	private ArrayList<Currency> currencies = new ArrayList<Currency>();
	private ArrayList<ValidCurrency> validCurrencies = new ArrayList<ValidCurrency>();

	public CoinCalculator() {
		
		this.validCurrencies.add(new ValidCurrency("btc_usd", R.id.btc_usd, "Bitcoin"));
		this.validCurrencies.add(new ValidCurrency("ltc_usd", R.id.ltc_usd, "Litecoin"));		
		
		for (HashMap<String, String> map : coinApi.getPrices()) {
			
			String id = map.get("id").replace("/", "_");
			
			ValidCurrency validCurrency = this.getValidCurrency(id);
			
			if (validCurrency != null) {
								
				String price = map.get("price");
			
				Currency currency = new Currency(id, price, validCurrency.getName());
				currency.setLayoutId(validCurrency.getLayoutId());
				
				this.currencies.add(currency);
			}
		}
		
		this.currencies.add(new Currency("usd", "1", "Dollars"));
	}
	
	private ValidCurrency getValidCurrency(String currency) {
		
		for (ValidCurrency validCurrency : this.validCurrencies) {
			
			if (validCurrency.getId().equals(currency)) {
				return validCurrency;
			}
		}
		
		return null;
	}
	
	public ArrayList<Currency> getCurrencies() {
				
    	return this.currencies;
	}
	
	private String searchPrice(Currency otherCurrency){
		
		for (Currency currency : currencies) {
			
			if (currency.equals(otherCurrency)) {
				return currency.getPrice();
			}
		}
		
		return "0";
	}

	public String getPrice(Currency currency) {
		
		return this.searchPrice(currency);
	}
	
	public Currency getCurrencyById(String id) {
		
		for (Currency currencyObj : currencies) {
			
			if(id.equals(currencyObj.getId())) {
				return currencyObj;
			}
		}
		
		return null;
	}
	
	public Currency getCurrencyByName(String name) {
		
		for (Currency currencyObj : currencies) {
			
			if(name.equals(currencyObj.getName())) {
				return currencyObj;
			}
		}
		
		return null;
	}
	
	public String getPrice(String currency) {
				
		return this.getPrice(this.getCurrencyById(currency));
	}
		
	public Float getFloatPrice(String currency) {
		
		return this.getCurrencyById(currency).getFloatPrice();
	}

	public String convert(String from, String to, String amount) {
		
		Currency currencyFrom = this.getCurrencyByName(from);
		Currency currencyTo = this.getCurrencyByName(to);
		
		Float famount = Float.parseFloat(amount);
		
		Float result = famount / currencyFrom.getFloatPrice();
		
		return result.toString();
	}

	public CoinApi getCoinApi() {

		return coinApi;
	}

}
