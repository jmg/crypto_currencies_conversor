package com.conversor.bitcoin;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class CoinApi {
	
	private String url = "http://www.cryptocoincharts.info/v2/api/tradingPairs";
	
	public ArrayList<HashMap<String,String>> getPrices() {
		
	    String data = makeRequest(this.url, "");
	    
	    Type listType = new TypeToken<ArrayList<HashMap<String,String>>>(){}.getType();
	    ArrayList<HashMap<String,String>> prices = new Gson().fromJson(data, listType);

	    return prices;
	}

	private String makeRequest(String _url, String urlParameters) {
		
		URL url;
	    HttpURLConnection connection = null;
	    
	    try {
	        url = new URL(_url);
	        connection = (HttpURLConnection)url.openConnection();
	        connection.setRequestMethod("POST");
	  			
	        connection.setRequestProperty("Content-Length", "" + 
	                 Integer.toString(urlParameters.getBytes().length));
	        connection.setRequestProperty("Content-Language", "en-US");  
	  			
	        connection.setUseCaches (false);
	        connection.setDoInput(true);
	        connection.setDoOutput(true);

	        //Send request
	        DataOutputStream wr = new DataOutputStream (
	                    connection.getOutputStream ());
	        wr.writeBytes (urlParameters);
	        wr.flush ();
	        wr.close ();

	        //Get Response	
	        InputStream is = connection.getInputStream();
	        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
	        String line;
	        StringBuffer response = new StringBuffer(); 
	        while((line = rd.readLine()) != null) {
	        	response.append(line);
	        	response.append('\r');
	        }
	        rd.close();
	        return response.toString();
	        
	    } catch (Exception e) {

	        e.printStackTrace();
	        return null;

	    } finally { 

	        if(connection != null) {
	        	connection.disconnect(); 
	        }
	    }
	}
}
