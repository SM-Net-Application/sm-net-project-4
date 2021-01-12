package com.sm.net.sp.jasper.model;

import com.sm.net.project.Language;

public enum JRWeekSongType {

	NO_INFO("jasper.layout.meeting.songtype.noinfo"),

	ONLY_NUMBER("jasper.layout.meeting.songtype.onlynumber"),

	ONLY_TITLE(""),

	BOTH("jasper.layout.meeting.songtype.both");

	private String text;

	private JRWeekSongType(String text) {
		this.text = text;
	}

	public static JRWeekSongType checkType(String song, String title) {

		if (song.trim().isEmpty() && title.trim().isEmpty())
			return NO_INFO;

		if (!song.trim().isEmpty() && title.trim().isEmpty())
			return ONLY_NUMBER;

		if (song.trim().isEmpty() && !title.trim().isEmpty())
			return ONLY_TITLE;

		return BOTH;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public static String checkText(Language language, JRWeekSongType type, String song, String title) {

		switch (type) {
		case NO_INFO:
			return language.getString(type.getText());
		case ONLY_NUMBER:
			return String.format(language.getString(type.getText()), song);
		case ONLY_TITLE:
			return title;
		case BOTH:
			return String.format(language.getString(type.getText()), song, title);
		}

		return "";
	}
}
