package com.bitcoin.conversor;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;


public class CoinCalculator {
	
	private CoinApi coinApi = new CoinApi();
	private ArrayList<CurrencyPrice> currenciesPrices = new ArrayList<CurrencyPrice>();
	private ArrayList<Currency> currencies = new ArrayList<Currency>();
	private Context context;
	
	private static CoinCalculator instance;	
	
	public static CoinCalculator getInstance(Context context) {
		
		if (instance == null) {
			instance = new CoinCalculator();
		}
		instance.context = context;
		
		return instance;
	}

	private CoinCalculator() {
		
		this.currencies.add(new Currency("btc_usd", "Bitcoin"));
		this.currencies.add(new Currency("ltc_usd", "Litecoin"));
		this.currencies.add(new Currency("ppc_usd", "Peercoin"));
		this.currencies.add(new Currency("nmc_usd", "Namecoin"));
		this.currencies.add(new Currency("xpm_usd", "Primecoin"));
		this.currencies.add(new Currency("ftc_usd", "Feathercoin"));
		this.currencies.add(new Currency("wdc_usd", "Worldcoin"));
		
		setCurrencies();
	}
	
	private void setCurrencies() {
		
		this.currenciesPrices = new ArrayList<CurrencyPrice>();
		
		this.setAPICurrencies();
		
		this.currenciesPrices.add(new CurrencyPrice("usd", "1", "Dollars"));
	}
	
	private void setAPICurrencies() {
		
		for (HashMap<String, String> map : coinApi.getPrices(context)) {
			
			String id = map.get("id").replace("/", "_");
			
			Currency validCurrency = this.getValidCurrency(id);
			
			if (validCurrency != null) {
								
				String price = map.get("price");
			
				CurrencyPrice currency = new CurrencyPrice(id, price, validCurrency.getName());
				this.currenciesPrices.add(currency);
			}
		}
	}
	
	private Currency getValidCurrency(String currency) {
		
		for (Currency validCurrency : this.currencies) {
			
			if (validCurrency.getId().equals(currency)) {
				return validCurrency;
			}
		}
		
		return null;
	}
	
	public ArrayList<CurrencyPrice> getCurrencies() {
				
    	return this.currenciesPrices;
	}
	
	public ArrayList<CurrencyPrice> getCryptoCurrencies() {
		
		ArrayList<CurrencyPrice> currencies = new ArrayList<CurrencyPrice>();
	    
    	for (CurrencyPrice currency : this.getCurrencies()) {
    			
    		if (currency.getId().equals("usd")) {
    			continue;
    		}
    		    		
        	currencies.add(currency);
        }
		
		return currencies;
	}
	
	public String getPrice(CurrencyPrice currency) {
		
		if (currency == null) {
			return "0";
		}
		
		return currency.getPrice();
	}
	
	public CurrencyPrice getCurrencyById(String id) {
		
		for (CurrencyPrice currencyObj : currenciesPrices) {
			
			if(id.equals(currencyObj.getId())) {
				return currencyObj;
			}
		}
		
		return null;
	}
	
	public CurrencyPrice getCurrencyByName(String name) {
		
		for (CurrencyPrice currencyObj : currenciesPrices) {
			
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
		
		CurrencyPrice currencyObj = this.getCurrencyById(currency);
		if (currencyObj == null) {
			return Float.valueOf(0);
		}
		
		return currencyObj.getFloatPrice();
	}

	public String convert(String from, String to, String amount) {
		
		CurrencyPrice currencyFrom = this.getCurrencyByName(from);
		CurrencyPrice currencyTo = this.getCurrencyByName(to);
		
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
