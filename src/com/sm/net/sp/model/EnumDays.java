package com.sm.net.sp.model;

public enum EnumDays {

	EMPTY(0, ""), LUN(1, "TEXT0123"), MAR(2, "TEXT0124"), MER(3, "TEXT0125"), GIO(4, "TEXT0126"), VEN(5, "TEXT0127"),
	SAB(6, "TEXT0128"), DOM(7, "TEXT0129");

	private int id;
	private String key;

	private EnumDays(int id, String key) {
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

	public static EnumDays getByID(int id) {

		for (EnumDays day : EnumDays.values())
			if (day.getId() == id)
				return day;

		return EnumDays.EMPTY;
	}

}
