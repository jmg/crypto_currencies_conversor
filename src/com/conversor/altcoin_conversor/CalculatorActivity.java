package com.conversor.altcoin_conversor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.atlcoin_conversor.R;

public class CalculatorActivity extends Activity {	
	
	private CoinCalculator coinCalculator = CoinCalculator.getInstance();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.calculator);	   
	    
	    new LoadCalculatorCombosTask(this).execute();
	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
	
	public void convertBtnClick(View view) {
		
		this.convert();
	}

	public void convert() {
		
		final TextView resultsView = (TextView) findViewById(R.id.results_value);
		final TextView amountView = (TextView) findViewById(R.id.amount_text);
		
		Spinner fromCombo = (Spinner) findViewById(R.id.from_combo);
		Spinner toCombo = (Spinner) findViewById(R.id.to_combo);
		
		String result = null;
		try {
			result = this.coinCalculator.convert(fromCombo.getSelectedItem().toString(), toCombo.getSelectedItem().toString(), amountView.getText().toString());
		} catch (Exception e) {
			result = "Please insert a valid amount.";
		}
				
		resultsView.setText("$" + result);
	}
	
	public void updateBtnClick(View view) {
		
		this.coinCalculator.update();		
	}
	
	public void exchangeRateBtnClick(View view) {
		
		Intent intent = new Intent(this, MainActivity.class);	
		this.startActivity(intent);
	}
}
