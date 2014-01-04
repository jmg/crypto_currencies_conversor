package com.conversor.altcoin_conversor;

import java.util.ArrayList;
import java.util.HashMap;


public class CoinCalculator {
	
	private CoinApi coinApi = new CoinApi();
	private ArrayList<Currency> currencies = new ArrayList<Currency>();
	private ArrayList<ValidCurrency> validCurrencies = new ArrayList<ValidCurrency>();

	private static CoinCalculator instance;
	
	public static CoinCalculator getInstance() {
		
		if (instance == null) {
			instance = new CoinCalculator();
		}
		return instance;
	}

	private CoinCalculator() {
		
		this.validCurrencies.add(new ValidCurrency("btc_usd", "Bitcoin"));
		this.validCurrencies.add(new ValidCurrency("ltc_usd", "Litecoin"));
		this.validCurrencies.add(new ValidCurrency("ppc_usd", "Peercoin"));
		this.validCurrencies.add(new ValidCurrency("nmc_usd", "Namecoin"));
		this.validCurrencies.add(new ValidCurrency("xpm_usd", "Primecoin"));
		this.validCurrencies.add(new ValidCurrency("ftc_usd", "Feathercoin"));
		this.validCurrencies.add(new ValidCurrency("wdc_usd", "Worldcoin"));
		
		setCurrencies();
	}
	
	private void setCurrencies() {
		
		this.currencies = new ArrayList<Currency>();
		
		this.setAPICurrencies();
		
		this.currencies.add(new Currency("usd", "1", "Dollars"));
	}
	
	private void setAPICurrencies() {
		
		for (HashMap<String, String> map : coinApi.getPrices()) {
			
			String id = map.get("id").replace("/", "_");
			
			ValidCurrency validCurrency = this.getValidCurrency(id);
			
			if (validCurrency != null) {
								
				String price = map.get("price");
			
				Currency currency = new Currency(id, price, validCurrency.getName());
				this.currencies.add(currency);
			}
		}
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
	
	public ArrayList<Currency> getCryptoCurrencies() {
		
		ArrayList<Currency> currencies = new ArrayList<Currency>();
	    
    	for (Currency currency : this.getCurrencies()) {
    			
    		if (currency.getId().equals("usd")) {
    			continue;
    		}
    		    		
        	currencies.add(currency);
        }
		
		return currencies;
	}
	
	public String getPrice(Currency currency) {
		
		if (currency == null) {
			return "0";
		}
		
		return currency.getPrice();
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
		
		Currency currencyObj = this.getCurrencyById(currency);
		if (currencyObj == null) {
			return Float.valueOf(0);
		}
		
		return currencyObj.getFloatPrice();
	}

	public String convert(String from, String to, String amount) {
		
		Currency currencyFrom = this.getCurrencyByName(from);
		Currency currencyTo = this.getCurrencyByName(to);
		
		Float famount = Float.parseFloat(amount);
		
		Float result = famount * currencyTo.getFloatPrice() / currencyFrom.getFloatPrice();
		
		return result.toString();
	}

	public CoinApi getCoinApi() {

		return coinApi;
	}

	public void update() {
		
		this.setCurrencies();
	}

}
