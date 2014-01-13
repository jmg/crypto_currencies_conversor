package com.bitcoin.calculator;

import java.util.ArrayList;

import com.bitcoin.conversor.R;

import android.graphics.Color;
import android.os.AsyncTask;
import android.view.Gravity;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LoadCurrenciesTask extends AsyncTask<Void, Void, ArrayList<CurrencyPrice>> {
	
	private CoinCalculator coinCalculator;
	private MainActivity mainActivity;
	static final String EXTRA_CURRENCY_INDEX = "com.bitcoin.calculator.EXTRA_CURRENCY_INDEX";
	
	public LoadCurrenciesTask(MainActivity mainActivity) {
		
		this.mainActivity = mainActivity;
		this.coinCalculator = CoinCalculator.getInstance(mainActivity.getApplicationContext());
	}
	
	@Override
	protected ArrayList<CurrencyPrice> doInBackground(Void... params) {
		
    	return coinCalculator.getCryptoCurrencies();
    }
    
    public LinearLayout addCurrencyView(CurrencyPrice currency, int index, int size) {
    	
    	LinearLayout currencyContainer = new LinearLayout(this.mainActivity);
    	currencyContainer.setOrientation(1);
    	currencyContainer.setPadding(0, 5, 0, 5);
    	currencyContainer.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
    	    	
    	TextView labelTextView = new TextView(this.mainActivity);
        labelTextView.setTextSize(16);
        labelTextView.setText(currency.getName() + " - USD");
        labelTextView.setGravity(Gravity.CENTER_HORIZONTAL);
        labelTextView.setPadding(0, 5, 0, 5);
        
        String value = this.coinCalculator.getFormattedPrice(currency.getId());        

        TextView priceTextView = new TextView(this.mainActivity);
        priceTextView.setTextSize(20);
    	priceTextView.setText(value);
    	priceTextView.setGravity(Gravity.CENTER_HORIZONTAL);
    	priceTextView.setPadding(0, 5, 0, 5);
    	
    	String color = getRowColor(index);
    	
    	currencyContainer.setBackgroundColor(Color.parseColor(color));
    	
    	currencyContainer.addView(labelTextView);
    	currencyContainer.addView(priceTextView);
    	
    	currencyContainer.setOnClickListener(new CurrenciesOnTouchListener(this.mainActivity, currencyContainer, index));
    	
    	return currencyContainer;
    }

	private String getRowColor(int index) {
		
		if (index % 2 == 0) {
    		return "#B1C9DF";
    	} 
    	
		return "#CDD6DE";
	}
    
    protected void onPostExecute(ArrayList<CurrencyPrice> currencies) {
            	
    	LinearLayout container = (LinearLayout) this.mainActivity.findViewById(R.id.currencies_conatiner_lyt);
        
    	int index = 0;
    	int size = currencies.size();
    	for (CurrencyPrice currency: currencies) {
    		
    		LinearLayout layout = this.addCurrencyView(currency, index,  size);
    		container.addView(layout);
    		index++;
    	}
    }

}
