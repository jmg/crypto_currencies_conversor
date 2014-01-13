package com.bitcoin.calculator;

import java.util.ArrayList;

import com.bitcoin.conversor.R;

import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


public class LoadCalculatorCombosTask extends AsyncTask<Void, Void, ArrayList<CurrencyPrice>> {
	
	private CoinCalculator coinCalculator;
	private CalculatorActivity calculatorActivity;
	static final String EXTRA_CURRENCY_INDEX = "com.bitcoin.calculator.EXTRA_CURRENCY_INDEX";

	public LoadCalculatorCombosTask(CalculatorActivity calculatorActivity) {
		
		this.calculatorActivity = calculatorActivity;
		this.coinCalculator = CoinCalculator.getInstance(calculatorActivity.getApplicationContext());
	}

	@Override
	protected ArrayList<CurrencyPrice> doInBackground(Void... params) {
		
		return coinCalculator.getCurrencies();
	}
	
	protected void onPostExecute(ArrayList<CurrencyPrice> currencies) {
		
		this.setCombosCurriencies(currencies);		
		this.calculatorActivity.convert();
    }
	
	private void setCombosCurriencies(ArrayList<CurrencyPrice> currencies) {
		
		ArrayList<String> arraySpinner = new ArrayList<String>();
    	
        for (CurrencyPrice currency : currencies) {
        	
        	arraySpinner.add(currency.getName());
        }
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.calculatorActivity, android.R.layout.simple_spinner_item, arraySpinner);
        Spinner fromCombo = (Spinner) this.calculatorActivity.findViewById(R.id.to_combo);
        fromCombo.setAdapter(adapter);
        
        Integer currencyIndex = this.calculatorActivity.getIntent().getIntExtra(EXTRA_CURRENCY_INDEX, -1);
        if (currencyIndex != -1) {
        	fromCombo.setSelection(currencyIndex);
        } else {
        	fromCombo.setSelection(0);
        }
        
        Spinner toCombo = (Spinner) this.calculatorActivity.findViewById(R.id.from_combo);
        toCombo.setAdapter(adapter);
        toCombo.setSelection(toCombo.getCount() -1);
        
        fromCombo.setOnItemSelectedListener(new ConvertOnItemSelected(this.calculatorActivity));
        toCombo.setOnItemSelectedListener(new ConvertOnItemSelected(this.calculatorActivity));
	}
}
