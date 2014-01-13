package com.bitcoin.calculator;
import android.R;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;


public class CurrenciesOnTouchListener implements OnClickListener {
	
	private MainActivity mainActivity;
	private int index;
	private LinearLayout currencyContainer;
	static final String EXTRA_CURRENCY_INDEX = "com.bitcoin.calculator.EXTRA_CURRENCY_INDEX";

	public CurrenciesOnTouchListener(MainActivity mainActivity, LinearLayout currencyContainer, int index) {
		
		this.mainActivity = mainActivity;
		this.index = index;
		this.currencyContainer = currencyContainer;
	}
	
	@Override
	public void onClick(View v) {
		
		this.currencyContainer.setBackgroundColor(Color.parseColor("#1E90FF"));
		
		for (int i=0; i < this.currencyContainer.getChildCount(); i++) {
			TextView view = (TextView) this.currencyContainer.getChildAt(i);
			view.setTextColor(Color.WHITE);
		}
		
		Intent intent = new Intent(this.mainActivity, CalculatorActivity.class);
		intent.putExtra(EXTRA_CURRENCY_INDEX, index);
		mainActivity.startActivity(intent);	
	}

}
