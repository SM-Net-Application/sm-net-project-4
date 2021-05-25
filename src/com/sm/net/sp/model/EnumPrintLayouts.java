package com.sm.net.sp.model;

import com.sm.net.project.Language;

public enum EnumPrintLayouts {

	MEETING_MIDWEEK_NAME_EXTENDED("sp.printlayout.midweek.ext"),

	MEETING_MIDWEEK_NAME_SHORT("sp.printlayout.midweek.short"),

	MEETING_COMPLETE_NAME_EXTENDED("sp.printlayout.meeting.completeext"),

	MEETING_COMPLETE_NAME_SHORT("sp.printlayout.meeting.completeshort"),

	NATURAL_DISASTER_LIST("sp.printlayout.naturaldisaster"),

	NATURAL_DISASTER_SERVICEGROUPS("sp.printlayout.naturaldisaster.servicegroup"),

	NATURAL_DISASTER_SERVICEGROUPS_1("sp.printlayout.naturaldisaster.servicegroup"),
	NATURAL_DISASTER_SERVICEGROUPS_2("sp.printlayout.naturaldisaster.servicegroup"),
	NATURAL_DISASTER_SERVICEGROUPS_3("sp.printlayout.naturaldisaster.servicegroup"),
	NATURAL_DISASTER_SERVICEGROUPS_4("sp.printlayout.naturaldisaster.servicegroup"),
	NATURAL_DISASTER_SERVICEGROUPS_5("sp.printlayout.naturaldisaster.servicegroup"),
	NATURAL_DISASTER_SERVICEGROUPS_6("sp.printlayout.naturaldisaster.servicegroup"),
	NATURAL_DISASTER_SERVICEGROUPS_7("sp.printlayout.naturaldisaster.servicegroup"),
	NATURAL_DISASTER_SERVICEGROUPS_8("sp.printlayout.naturaldisaster.servicegroup"),
	NATURAL_DISASTER_SERVICEGROUPS_9("sp.printlayout.naturaldisaster.servicegroup"),
	NATURAL_DISASTER_SERVICEGROUPS_10("sp.printlayout.naturaldisaster.servicegroup"),

	INFOTABLE_POST("sp.printlayout.infotable"),
	
	INFOTABLE_POST_EVENTS("sp.printlayout.infotableevents"),
	
	MEMBER_PASSWORD_MONITOR("sp.printlayout.passwordmonitor"),

	SERVICE_GROUP("sp.printlayout.servicegroup"),

	SERVICE_GROUP_WITHOUT_EXCLUSIONS("sp.printlayout.servicegroupexcl"),
	
	MODULE_S13("sp.printlayout.module.s13");

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

	public static PrintLayoutTranslated getPrintLayoutTranslated(Language language, EnumPrintLayouts layout,
			SerGroup sg) {

		for (EnumPrintLayouts enumLayout : EnumPrintLayouts.values())
			if (enumLayout == layout)
				return new PrintLayoutTranslated(enumLayout, language.getString(enumLayout.getKey()), sg);

		return null;
	}
}
