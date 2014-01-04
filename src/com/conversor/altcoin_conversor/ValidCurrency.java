package com.conversor.altcoin_conversor;

public class ValidCurrency {
	
	private String id;
	private String name;
	
	public ValidCurrency(String id, String name) {
		super();
		this.id = id;
		this.setName(name);
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	
}
