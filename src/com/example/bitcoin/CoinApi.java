package com.example.bitcoin;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class CoinApi {
	
	private String url = "http://www.cryptocoincharts.info/v2/api/tradingPairs";
	
	public String getPrices() {
		
	    String data = makeRequest(this.url, "");
	    
	    return data;
	}

	private String makeRequest(String _url, String urlParameters) {
		URL url;
	    HttpURLConnection connection = null;
	    
	    try {
	        url = new URL(_url);
	        connection = (HttpURLConnection)url.openConnection();
	        connection.setRequestMethod("POST");
	        connection.setRequestProperty("Content-Type", 
	             "application/x-www-form-urlencoded");
	  			
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
