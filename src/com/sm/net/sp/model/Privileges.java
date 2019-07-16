package com.sm.net.sp.model;

import com.sm.net.project.Language;

public enum Privileges {

	PRESIDENT_MIDWEEK("TEXT0134"), PRAY1_MIDWEEK("TEXT0084"), TALK_MIDWEEK("TEXT0051"), DIGGING_MIDWEEK(
			"TEXT0088"), CONGRBIBLESTUDY_MIDWEEK("TEXT0061"), PRAY2_MIDWEEK("TEXT0102"), CHRISTIAN_LIFE("TEXT0082");

	private String name;

	private Privileges(String name) {
		this.name = name;
	}

	public String getTranslatedName(Language language) {
		return language.getString(this.getName());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}