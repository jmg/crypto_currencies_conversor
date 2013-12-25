package com.example.bitcoin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
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
    	
    	HashMap<String,Integer> currencies = new HashMap<String, Integer>();
    	currencies.put("btc", R.id.bitcoin_value);
    	currencies.put("ltc", R.id.litecoin_value);
    	
    	Iterator<Map.Entry<String, Integer>> it = currencies.entrySet().iterator();
    	
        while (it.hasNext()) {
        	
            Map.Entry<String, Integer> pairs = (Map.Entry<String, Integer>)it.next();
            
    		String currency = pairs.getKey();
    		Integer id = pairs.getValue();

        	final TextView textView = (TextView) findViewById(id);
        	textView.setText(this.coinCalculator.getPrice(currency));	
        }
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
