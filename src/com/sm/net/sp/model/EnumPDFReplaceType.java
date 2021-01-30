package com.sm.net.sp.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public enum EnumPDFReplaceType {

	REGEX(1, "enum.conf.pdfreplace.regex"), TEXT(2, "enum.conf.pdfreplace.testo");

	private int id;
	private StringProperty textKey;

	private EnumPDFReplaceType(int id, String textKey) {
		this.id = id;
		this.textKey = new SimpleStringProperty(textKey);
	}

	public static EnumPDFReplaceType valueByID(int type) {

		for (EnumPDFReplaceType t : EnumPDFReplaceType.values())
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