package com.bitcoin.calculator;

import java.text.DecimalFormat;
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
	
	public static CoinCalculator getInstance() {
		
		if (instance == null) {
			instance = new CoinCalculator();
		}
		
		return instance;
	}

	private CoinCalculator() {
		
		this.currencies = CoinConfig.getInstance().getCurrencies();		
		setCurrenciesPrices();
	}
	
	private void setCurrenciesPrices() {
		
		this.currenciesPrices = new ArrayList<CurrencyPrice>();
		
		this.setAPICurrencies();
		
		this.currenciesPrices.add(new CurrencyPrice("usd", "1", "Dollars"));
	}
	
	private void setAPICurrencies() {
		
		CurrencyPrice btcCurrency = null;
		
		for (HashMap<String, String> map : coinApi.getPrices(context)) {
			
			String id = map.get("id").replace("/", "_");			
			Currency validCurrency = this.getValidCurrency(id);
			
			if (validCurrency != null) {
								
				String price = map.get("price");
				CurrencyPrice currency = null;

				if (!validCurrency.isBTCExchangeCurrency()) {
					
					currency = new CurrencyPrice(id, price, validCurrency.getName());
					
					if (validCurrency.isBTCtoUSD()) {
						btcCurrency = currency;
					}
					
				} else {
					currency = new CurrencyPriceBTC(id, price, validCurrency.getName(), btcCurrency);
				}
				
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

	public CoinApi getCoinApi() {

		return coinApi;
	}

	public void update() {
		
		this.setCurrenciesPrices();
	}

	public String convert(CurrencyPrice currencyFrom, CurrencyPrice currencyTo, String amount) {
		
		Float famount = Float.parseFloat(amount);
		Float result = famount * currencyTo.getFloatPrice() / currencyFrom.getFloatPrice();
		
		return this.formatAmount(result, false);
	}

	public String getFormattedPrice(String currency) {
		
		Float price = this.getFloatPrice(currency);
						
		return this.formatAmount(price, true);		
	}
	
	public String formatAmount(Float amount, boolean withDolarSign) {
		
		if (amount == 0) {
			return "-";
		}	
		
		String decimals;
		if (amount < 0.001) {
			decimals = "######";
		} else if (amount < 0.01) {
			decimals = "#####";
		} else {
			decimals = "####";
		}
		DecimalFormat decFormat = new DecimalFormat("#." + decimals);
		
		String result = decFormat.format(amount);
		if (withDolarSign) {
			return "$" + result;
		}
		return result;
	}

}
