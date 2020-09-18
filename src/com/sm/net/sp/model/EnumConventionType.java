package com.sm.net.sp.model;

public enum EnumConventionType {

	CIRCUITASSEMBLY_BRANCH(1, "enum.conventiontype.circuitassembly.branch"),

	CIRCUITASSEMBLY_OVERSEER(2, "enum.conventiontype.circuitassembly.overseer"),

	REGIONALCONVENTION(3, "enum.conventiontype.regionalconvention");

	private int id;
	private String key;

	private EnumConventionType(int id, String key) {
		this.id = id;
		this.key = key;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public static EnumConventionType getByID(int id) {

		for (EnumConventionType type : EnumConventionType.values())
			if (type.getId() == id)
				return type;

		return null;
	}
}
