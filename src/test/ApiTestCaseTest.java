package test;

import static org.junit.Assert.*;

import org.junit.Test;
import com.example.bitcoin.CoinApi;

public class ApiTestCaseTest {

	@Test
	public void test(){
		
		CoinApi coinApi = new CoinApi();
		assertEquals(String.class, coinApi.getPrices().getClass());
	}

}
