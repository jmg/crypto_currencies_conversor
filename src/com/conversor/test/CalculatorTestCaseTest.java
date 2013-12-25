package com.conversor.test;

import static org.junit.Assert.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import com.conversor.bitcoin.CoinCalculator;

public class CalculatorTestCaseTest {
	
	private CoinCalculator coinCalculator = null;
	
	@Before
	public void setUp() {
		
		this.coinCalculator = new CoinCalculator();
	}

	@Test
	public void testGetPrices(){				
		
		Type expectedType = new ArrayList<HashMap<String, String>>().getClass();	
		assertEquals(expectedType, coinCalculator.getPrices().getClass());
	}
	
	@Test
	public void testGetPrice(){				
		
		String currency = "btc/usd";
		
		assertEquals(String.class, coinCalculator.getPrice(currency).getClass());
		assertTrue(coinCalculator.getFloatPrice(currency) > 0.0);
		
		currency = "ltc/usd";
		
		assertEquals(String.class, coinCalculator.getPrice(currency).getClass());
		assertTrue(coinCalculator.getFloatPrice(currency) > 0.0);
	}
	
	@Test
	public void testGetInvalidPrice(){				
		
		String currency = "ars/usd";
		
		assertEquals(String.class, coinCalculator.getPrice(currency).getClass());
		assertTrue(coinCalculator.getFloatPrice(currency) == 0.0);		
	}

}
