package com.conversor.bitcoin;

import java.util.ArrayList;

import com.example.bitcoin.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;


public class MainActivity extends Activity {
		
	private CoinCalculator coinCalculator = new CoinCalculator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        this.initialize();
    }

    private void initialize() {
    	
    	ArrayList<String> arraySpinner = new ArrayList<String>();
    	
        for (Currency currency : coinCalculator.getCurrencies()) {
        	
        	arraySpinner.add(currency.getName());
        	
        	if (currency.getLayoutId() == null) {
        		continue;
        	}

        	final TextView textView = (TextView) findViewById(currency.getLayoutId());
        	String value = this.coinCalculator.getFloatPrice(currency.getId()).toString();
        	textView.setText("$" + value);
        }
        
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arraySpinner);
        Spinner fromCombo = (Spinner) findViewById(R.id.from_combo);
        fromCombo.setAdapter(adapter);
        fromCombo.setSelection(1);
        	
        Spinner toCombo = (Spinner) findViewById(R.id.to_combo);
        toCombo.setAdapter(adapter);
        toCombo.setSelection(2);
        
        this.convertBtnClick(null);
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
	
	public void convertBtnClick(View view) {
		
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
		
		ProgressBar bar = (ProgressBar) findViewById(R.id.progressBar1);
		
		bar.setVisibility(View.VISIBLE);
		
		this.coinCalculator.update();
		
		bar.setVisibility(View.INVISIBLE);
		
	}
}
