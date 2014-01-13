package com.bitcoin.calculator;

import java.util.ArrayList;


public class CoinConfig {
	
	private ArrayList<Currency> currencies = new ArrayList<Currency>();
	private static CoinConfig instance;	
	
	public static CoinConfig getInstance() {
		
		if (instance == null) {
			instance = new CoinConfig();
			instance.initialize();
		}
		
		return instance;
	}
	
	private void initialize() {
		
		this.currencies.add(new Currency("btc_usd", "Bitcoin"));
		this.currencies.add(new Currency("ltc_usd", "Litecoin"));
		this.currencies.add(new Currency("ppc_usd", "Peercoin"));
		this.currencies.add(new Currency("nmc_usd", "Namecoin"));
		this.currencies.add(new Currency("xpm_usd", "Primecoin"));
		this.currencies.add(new Currency("ftc_usd", "Feathercoin"));
		this.currencies.add(new Currency("wdc_usd", "Worldcoin"));
		
		//BTC currencies
		this.currencies.add(new Currency("qrk_btc", "Quarkcoin"));
		this.currencies.add(new Currency("fst_btc", "Fastcoin"));
		this.currencies.add(new Currency("pts_btc", "Protoshares"));
		this.currencies.add(new Currency("doge_btc", "Dogecoin"));
		this.currencies.add(new Currency("nxt_btc", "Nextcoin"));
		this.currencies.add(new Currency("msc_btc", "Mastercoin"));
		this.currencies.add(new Currency("mec_btc", "Megacoin"));
		this.currencies.add(new Currency("fst_btc", "Fastcoin"));
	}
	
	public ArrayList<Currency> getCurrencies() {
		
		return this.currencies;
	}
	
	public ArrayList<String> getCurrenciesIds() {
		
		ArrayList<String> ids = new ArrayList<String>();
		for (Currency currency : this.currencies) {
			ids.add(currency.getId());
		}
		
		return ids;
	}
}
