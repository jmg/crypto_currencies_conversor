package com.conversor.altcoin_conversor;

import java.util.ArrayList;

import com.example.atlcoin_conversor.R;

import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


public class LoadCalculatorCombosTask extends AsyncTask<Void, Void, ArrayList<Currency>> {
	
	private CoinCalculator coinCalculator = CoinCalculator.getInstance();
	private CalculatorActivity calculatorActivity;

	public LoadCalculatorCombosTask(CalculatorActivity calculatorActivity) {
		
		this.calculatorActivity = calculatorActivity;
	}

	@Override
	protected ArrayList<Currency> doInBackground(Void... params) {
		
		return coinCalculator.getCurrencies();
	}
	
	protected void onPostExecute(ArrayList<Currency> currencies) {
		
		this.setCombosCurriencies(currencies);		
		this.calculatorActivity.convert();
    }
	
	private void setCombosCurriencies(ArrayList<Currency> currencies) {
		
		ArrayList<String> arraySpinner = new ArrayList<String>();
    	
        for (Currency currency : currencies) {
        	
        	arraySpinner.add(currency.getName());
        }
		
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.calculatorActivity, android.R.layout.simple_spinner_item, arraySpinner);
        Spinner fromCombo = (Spinner) this.calculatorActivity.findViewById(R.id.to_combo);
        fromCombo.setAdapter(adapter);
        fromCombo.setSelection(0);
        	
        Spinner toCombo = (Spinner) this.calculatorActivity.findViewById(R.id.from_combo);
        toCombo.setAdapter(adapter);
        toCombo.setSelection(toCombo.getCount() -1);
        
        fromCombo.setOnItemSelectedListener(new ConvertOnItemSelected(this.calculatorActivity));
        toCombo.setOnItemSelectedListener(new ConvertOnItemSelected(this.calculatorActivity));
	}
}
