package com.bitcoin.conversor;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;

public class ConvertOnItemSelected implements OnItemSelectedListener {
	
	CalculatorActivity calculatorActivity;

	public ConvertOnItemSelected(CalculatorActivity calculatorActivity) {
		
		this.calculatorActivity = calculatorActivity;
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		
		this.calculatorActivity.convert();
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {

		this.calculatorActivity.convert();
	}
}
