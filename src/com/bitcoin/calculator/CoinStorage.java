package com.bitcoin.calculator;

import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import android.content.Context;

public class CoinStorage {
	
	String FILENAME = "coins_prices";
	FileOutputStream fos;
	FileInputStream fis;

	public String getLastPrices(Context context) {
		
		String content = "";
		
		try {
			fis = context.openFileInput(FILENAME);
			content = new Scanner(fis).useDelimiter("\\Z").next();
			fis.close();
			
		} catch (IOException e) {

			e.printStackTrace();
		}		
		return content;
	}

	public void setLastPrices(String data, Context context) {		
		
		try {
			fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
			fos.write(data.getBytes());
			fos.close();
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}

}
