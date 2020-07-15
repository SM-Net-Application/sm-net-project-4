package com.sm.net.sp.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public enum EnumPlaceType {

	KINGDOMHALL(1, "enum.places.kingdomhall"), CONVENTIONS(2, "enum.places.convention"), OTHER(3, "enum.places.other");

	private int id;
	private StringProperty textKey;

	private EnumPlaceType(int id, String textKey) {
		this.id = id;
		this.textKey = new SimpleStringProperty(textKey);
	}

	public static EnumPlaceType valueByID(int type) {

		for (EnumPlaceType t : EnumPlaceType.values())
			if (t.getId() == type)
				return t;

		return null;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public StringProperty getTextKey() {
		return textKey;
	}

	public void setTextKey(StringProperty textKey) {
		this.textKey = textKey;
	}
}
