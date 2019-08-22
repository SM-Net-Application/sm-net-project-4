package com.sm.net.sp.model;

import com.sm.net.project.Language;

public enum Privileges {

	PRESIDENT_MIDWEEK("TEXT0134", "talk.png"),

	PRAY1_MIDWEEK("TEXT0084", "talk.png"),

	TALK_MIDWEEK("TEXT0051", "talk.png"),

	DIGGING_MIDWEEK("TEXT0088", "talk.png"),

	CONGRBIBLESTUDY_MIDWEEK("TEXT0061", "talk.png"),

	PRAY2_MIDWEEK("TEXT0102", "talk.png"),

	CHRISTIAN_LIFE("TEXT0082", "talk.png"),

	BIBLE_READING_A("sp.privileges.biblereading.a", "bible_monitor.png"),

	BIBLE_READING_B("sp.privileges.biblereading.b", "bible_monitor.png"),

	MINISTRY_STUDENT_1("sp.privileges.task.a", "ministry_monitor.png"),

	MINISTRY_ASSISTANT_1("sp.privileges.taskassistent.a", "ministry_monitor.png"),

	MINISTRY_STUDENT_2("sp.privileges.task.b", "ministry_monitor.png"),

	MINISTRY_ASSISTANT_2("sp.privileges.taskassistent.b", "ministry_monitor.png");

	private String name;
	private String imageName;

	private Privileges(String name, String imageName) {
		this.name = name;
		this.imageName = imageName;
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

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
}