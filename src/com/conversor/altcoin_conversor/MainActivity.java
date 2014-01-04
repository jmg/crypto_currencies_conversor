package com.conversor.altcoin_conversor;

import com.example.atlcoin_conversor.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;


public class MainActivity extends Activity {	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);      
        
    	new LoadCurrenciesTask(this).execute();    
    }        
    
    public void openCalculatorClick(View view) {
		
		Intent intent = new Intent(this, CalculatorActivity.class);	
		this.startActivity(intent);
    }	
}
