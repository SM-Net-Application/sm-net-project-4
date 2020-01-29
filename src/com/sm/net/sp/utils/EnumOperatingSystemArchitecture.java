package com.sm.net.sp.utils;

public enum EnumOperatingSystemArchitecture {

	BIT32(0, "32 bit"), BIT64(1, "64 bit");

	private Integer id;
	private String text;

	private EnumOperatingSystemArchitecture(Integer id, String text) {
		this.id = id;
		this.text = text;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
