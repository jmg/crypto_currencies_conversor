package com.conversor.bitcoin;

public class Currency {
	
	String id;
	String name;
	String price;
	Integer layoutId;
	
	public Currency(String id, String price, String name) {
		super();
		this.id = id;
		this.price = price;
		this.name = name;
	}
	
	public Integer getLayoutId() {
		return layoutId;
	}

	public void setLayoutId(Integer layoutId) {
		this.layoutId = layoutId;
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
	
	public String getPrice() {
		return price;
	}
	
	public void setPrice(String price) {
		this.price = price;
	}

	public Float getFloatPrice() {

		return Float.parseFloat(this.getPrice());
	}
}
