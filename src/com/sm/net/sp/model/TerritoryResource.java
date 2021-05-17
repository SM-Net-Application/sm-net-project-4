package com.sm.net.sp.model;

public class TerritoryResource {

	private int type;
	private String resourceName;
	private String resourceURL;

	public TerritoryResource(int type, String resourceName, String resourceURL) {
		super();
		this.type = type;
		this.resourceName = resourceName;
		this.resourceURL = resourceURL;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getResourceURL() {
		return resourceURL;
	}

	public void setResourceURL(String resourceURL) {
		this.resourceURL = resourceURL;
	}

}
