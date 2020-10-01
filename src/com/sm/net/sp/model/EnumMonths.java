package com.sm.net.sp.model;

public enum EnumMonths {

	EMPTY(0, ""), GEN(1, "time.months.1"), FEB(2, "time.months.2"), MAR(3, "time.months.3"), APR(4, "time.months.4"),
	MAG(5, "time.months.5"), GIU(6, "time.months.6"), LUG(7, "time.months.7"), AGO(8, "time.months.8"),
	SET(9, "time.months.9"), OTT(10, "time.months.10"), NOV(11, "time.months.11"), DIC(12, "time.months.12");

	private int id;
	private String key;

	private EnumMonths(int id, String key) {
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

	public static EnumMonths getByID(int id) {

		for (EnumMonths month : EnumMonths.values())
			if (month.getId() == id)
				return month;

		return EnumMonths.EMPTY;
	}

}
