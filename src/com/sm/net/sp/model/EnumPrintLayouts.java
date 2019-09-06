package com.sm.net.sp.model;

import com.sm.net.project.Language;

public enum EnumPrintLayouts {

	MEETING_MIDWEEK_NAME_EXTENDED("sp.printlayout.midweek.ext"),

	MEETING_MIDWEEK_NAME_SHORT("sp.printlayout.midweek.short"),

	NATURAL_DISASTER_LIST("sp.printlayout.naturaldisaster");

	private String key;

	private EnumPrintLayouts(String key) {
		this.key = key;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public static PrintLayoutTranslated getPrintLayoutTranslated(Language language, EnumPrintLayouts layout) {

		for (EnumPrintLayouts enumLayout : EnumPrintLayouts.values())
			if (enumLayout == layout)
				return new PrintLayoutTranslated(enumLayout, language.getString(enumLayout.getKey()));

		return null;
	}
}
