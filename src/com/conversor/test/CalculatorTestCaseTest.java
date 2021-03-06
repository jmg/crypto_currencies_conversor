package com.conversor.test;

import static org.junit.Assert.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import com.bitcoin.calculator.CoinCalculator;


public class CalculatorTestCaseTest {
	
	private CoinCalculator coinCalculator = null;
	
	@Before
	public void setUp() {
		
		this.coinCalculator = CoinCalculator.getInstance(null);
	}

	@Test
	public void testGetPrices(){				
		
		Type expectedType = new ArrayList<HashMap<String, String>>().getClass();	
		assertEquals(expectedType, coinCalculator.getCoinApi().getPrices(null).getClass());
	}
	
	@Test
	public void testGetPrice(){				
		
		String currency = "btc_usd";
		
		assertEquals(String.class, coinCalculator.getPrice(currency).getClass());
		assertTrue(coinCalculator.getFloatPrice(currency) > 0.0);
		
		currency = "ltc_usd";
		
		assertEquals(String.class, coinCalculator.getPrice(currency).getClass());
		assertTrue(coinCalculator.getFloatPrice(currency) > 0.0);
	}
	
	@Test
	public void testGetInvalidPrice(){				
		
		String currency = "ars_usd";
		
		assertEquals(String.class, coinCalculator.getPrice(currency).getClass());
		assertTrue(coinCalculator.getFloatPrice(currency) == 0.0);		
	}

}
