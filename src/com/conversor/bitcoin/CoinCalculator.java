package com.conversor.bitcoin;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.bitcoin.R;

public class CoinCalculator {
	
	private CoinApi coinApi = new CoinApi();
	private ArrayList<HashMap<String, String>> prices = null;

	public CoinCalculator() {
		
		this.prices = coinApi.getPrices();
	}
	
	public HashMap<String, Integer> getCurrencies() {
		
		HashMap<String,Integer> currencies = new HashMap<String, Integer>();
    	currencies.put("btc", R.id.bitcoin_value);
    	currencies.put("ltc", R.id.litecoin_value);
    	
    	return currencies;
	}
	
	private String searchPrice(String currency){
		
		for (HashMap<String, String> obj : prices) {
			
			if (obj.get("id").equals(currency)) {
				return obj.get("price");
			}
		}
		
		return "0";
	}

	public String getPrice(String currency) {
		
		return this.searchPrice(currency);
	}
	
	public Float getFloatPrice(String currency) {
		
		String price = this.getPrice(currency);
		return Float.parseFloat(price);
	}

	public ArrayList<HashMap<String, String>> getPrices() {

		return this.prices;
	}

}
