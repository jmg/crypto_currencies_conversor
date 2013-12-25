package com.conversor.bitcoin;

public class ValidCurrency {
	
	private String id;
	private Integer layoutId;
	private String name;
	
	public ValidCurrency(String id, Integer layoutId, String name) {
		super();
		this.id = id;
		this.layoutId = layoutId;
		this.setName(name);
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public Integer getLayoutId() {
		return layoutId;
	}
	
	public void setLayoutId(Integer layoutId) {
		this.layoutId = layoutId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	
}
