package com.sm.net.sp.model;

import com.sm.net.project.Language;

public enum Privileges {

	MIDWEEK_PRESIDENT("sp.privileges.midweek.president", "talk.png"),

	MIDWEEK_PRAY_START("sp.privileges.midweek.praystart", "talk.png"),

	MIDWEEK_PRAY_END("sp.privileges.midweek.prayend", "talk.png"),

	MIDWEEK_TALK("sp.privileges.midweek.talk", "talk.png"),

	MIDWEEK_DIGGING("sp.privileges.midweek.digging", "talk.png"),

	MIDWEEK_BIBLE_READING_A("sp.privileges.midweek.biblereading.a", "bible_monitor.png"),

	MIDWEEK_BIBLE_READING_B("sp.privileges.midweek.biblereading.b", "bible_monitor.png"),

	MIDWEEK_MINISTRY_STUDENT_1("sp.privileges.midweek.task.a", "ministry_monitor.png"),

	MIDWEEK_MINISTRY_ASSISTANT_1("sp.privileges.midweek.taskassistent.a", "ministry_monitor.png"),

	MIDWEEK_MINISTRY_STUDENT_2("sp.privileges.midweek.task.b", "ministry_monitor.png"),

	MIDWEEK_MINISTRY_ASSISTANT_2("sp.privileges.midweek.taskassistent.b", "ministry_monitor.png"),

	MIDWEEK_CHRISTIAN_LIFE("sp.privileges.midweek.christianlife", "talk.png"),

	MIDWEEK_CONGRBIBLESTUDY("sp.privileges.midweek.congrbiblestudy", "talk.png"),

	MIDWEEK_CONGRBIBLESTUDY_READER("sp.privileges.midweek.congrbiblestudy.reader", "talk.png"),

	WEEKEND_PRESIDENT("sp.privileges.weekend.president", "talk.png"),

	WEEKEND_WATCHTOWER("sp.privileges.weekend.watchtower", "talk.png"),

	WEEKEND_WATCHTOWER_READER("sp.privileges.weekend.watchtower.reader", "talk.png"),

	WEEKEND_PRAY_END("sp.privileges.weekend.prayend", "talk.png"),

	MIDWEEK_AUDIO_POS1("sp.privileges.midweek.audiopos", "talk.png"),

	MIDWEEK_AUDIO_POS2("sp.privileges.midweek.audiopos", "talk.png"),

	MIDWEEK_AUDIO_POS3("sp.privileges.midweek.audiopos", "talk.png"),

	MIDWEEK_AUDIO_MIC("sp.privileges.midweek.audiomic", "talk.png"),

	WEEKEND_AUDIO_POS1("sp.privileges.weekend.audiopos", "talk.png"),

	WEEKEND_AUDIO_POS2("sp.privileges.weekend.audiopos", "talk.png"),

	WEEKEND_AUDIO_POS3("sp.privileges.weekend.audiopos", "talk.png"),

	WEEKEND_AUDIO_MIC("sp.privileges.weekend.audiomic", "talk.png");

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