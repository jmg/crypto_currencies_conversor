package com.bitcoin.conversor;

import java.util.ArrayList;

import com.bitcoin.conversor.R;

import android.os.AsyncTask;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LoadCurrenciesTask extends AsyncTask<Void, Void, ArrayList<CurrencyPrice>> {
	
	private CoinCalculator coinCalculator;
	private MainActivity mainActivity;
	
	public LoadCurrenciesTask(MainActivity mainActivity) {
		
		this.mainActivity = mainActivity;
		this.coinCalculator = CoinCalculator.getInstance(mainActivity.getApplicationContext());
	}
	
	@Override
	protected ArrayList<CurrencyPrice> doInBackground(Void... params) {
		
    	return coinCalculator.getCryptoCurrencies();
    }
    
    public LinearLayout addCurrencyView(CurrencyPrice currency) {
    	
    	LinearLayout currencyContainer = new LinearLayout(this.mainActivity);
    	currencyContainer.setOrientation(1);
    	currencyContainer.setPadding(0, 0, 0, 5);
    	    	
    	TextView labelTextView = new TextView(this.mainActivity);
        labelTextView.setTextSize(16);
        labelTextView.setText(currency.getName() + " - USD");
        labelTextView.setGravity(Gravity.CENTER_HORIZONTAL);
        labelTextView.setPadding(0, 0, 0, 10);
        
        String value = this.coinCalculator.getFloatPrice(currency.getId()).toString();

        TextView priceTextView = new TextView(this.mainActivity);
        priceTextView.setTextSize(20);
    	priceTextView.setText("$" + value);
    	priceTextView.setGravity(Gravity.CENTER_HORIZONTAL);
    	priceTextView.setPadding(0, 0, 0, 10);
    	
    	currencyContainer.addView(labelTextView);
    	currencyContainer.addView(priceTextView);
    	
    	return currencyContainer;
    }
    
    protected void onPostExecute(ArrayList<CurrencyPrice> currencies) {
            	
    	LinearLayout container = (LinearLayout) this.mainActivity.findViewById(R.id.currencies_conatiner_lyt); 
        
    	for (CurrencyPrice currency: currencies) {
    		
    		LinearLayout layout = this.addCurrencyView(currency);
    		container.addView(layout);
    	}
    }

}
