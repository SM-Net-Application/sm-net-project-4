package com.sm.net.sp.model;

public enum EnumPlaceType {

	KINGDOMHALL(1, "enum.places.kingdomhall"), CONVENTIONS(2, "enum.places.convention"), OTHER(3, "enum.places.other");

	private int id;
	private String textKey;

	private EnumPlaceType(int id, String textKey) {
		this.id = id;
		this.textKey = textKey;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTextKey() {
		return textKey;
	}

	public void setTextKey(String textKey) {
		this.textKey = textKey;
	}
}
