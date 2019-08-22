package com.sm.net.sp.model;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Activities {

	private Privileges privilege;
	private LocalDate date;

	public Activities(Privileges privilege, LocalDate date) {
		super();
		this.privilege = privilege;
		this.date = date;
	}

	public String getLastDateText() {
		return (this.date != null) ? this.date.toString() : "-";
	}

	public static ObservableList<Activities> parseJSONResult(JSONObject json) {

		int memberID = json.getInt("memberID");

		ObservableList<Activities> list = FXCollections.observableArrayList();

		JSONArray weeks = json.getJSONArray("weeks");
		weeks.forEach(week -> checkWeek(week, memberID, list));

		JSONArray ministries = json.getJSONArray("ministry");
		ministries.forEach(ministry -> checkMinistry(ministry, memberID, list));

		JSONArray christians = json.getJSONArray("christians");
		christians.forEach(christian -> checkChristian(christian, memberID, list));

		list.sort((a1, a2) -> a1.getDate().compareTo(a2.getDate()));

		return list;
	}

	private static void checkWeek(Object weekObj, int memberID, ObservableList<Activities> list) {

		JSONObject week = (JSONObject) weekObj;

		int weekCode = week.getInt("spInf1");
		LocalDate weekDate = keyWeekToLocalDate(weekCode);

		if (memberID == week.getInt("spInf3"))
			list.add(new Activities(Privileges.PRESIDENT_MIDWEEK, weekDate));

		if (memberID == week.getInt("spInf4"))
			list.add(new Activities(Privileges.PRAY1_MIDWEEK, weekDate));

		if (memberID == week.getInt("spInf11"))
			list.add(new Activities(Privileges.TALK_MIDWEEK, weekDate));

		if (memberID == week.getInt("spInf14"))
			list.add(new Activities(Privileges.DIGGING_MIDWEEK, weekDate));

		if (memberID == week.getInt("spInf18"))
			list.add(new Activities(Privileges.BIBLE_READING_A, weekDate));

		if (memberID == week.getInt("spInf23"))
			list.add(new Activities(Privileges.CONGRBIBLESTUDY_MIDWEEK, weekDate));

		if (memberID == week.getInt("spInf27"))
			list.add(new Activities(Privileges.PRAY2_MIDWEEK, weekDate));

		if (memberID == week.getInt("spInf28"))
			list.add(new Activities(Privileges.BIBLE_READING_B, weekDate));
	}

	private static void checkMinistry(Object ministryObj, int memberID, ObservableList<Activities> list) {

		JSONObject ministry = (JSONObject) ministryObj;

		int weekCode = ministry.getInt("spInf1");
		LocalDate weekDate = keyWeekToLocalDate(weekCode);

		if (memberID == ministry.getInt("spInf7"))
			list.add(new Activities(Privileges.MINISTRY_STUDENT_1, weekDate));

		if (memberID == ministry.getInt("spInf8"))
			list.add(new Activities(Privileges.MINISTRY_ASSISTANT_1, weekDate));

		if (memberID == ministry.getInt("spInf9"))
			list.add(new Activities(Privileges.MINISTRY_STUDENT_2, weekDate));

		if (memberID == ministry.getInt("spInf10"))
			list.add(new Activities(Privileges.MINISTRY_ASSISTANT_2, weekDate));
	}

	private static void checkChristian(Object christianObj, int memberID, ObservableList<Activities> list) {

		JSONObject christian = (JSONObject) christianObj;

		int weekCode = christian.getInt("spInf1");
		LocalDate weekDate = keyWeekToLocalDate(weekCode);

		if (memberID == christian.getInt("spInf6"))
			list.add(new Activities(Privileges.CHRISTIAN_LIFE, weekDate));
	}

	public static LocalDate keyWeekToLocalDate(int keyWeek) {

		String keyWeekString = String.valueOf(keyWeek);

		String yearText = keyWeekString.substring(0, 4);
		String weekNumberText = keyWeekString.substring(4, 6);

		return getFirstDayOfWeekOfYear(Integer.valueOf(yearText), Integer.valueOf(weekNumberText));
	}

	public static LocalDate getFirstDayOfWeekOfYear(int year, int weekNumber) {

		GregorianCalendar cal = new GregorianCalendar();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.WEEK_OF_YEAR, weekNumber);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

		return dateToLocalDate(cal.getTime());
	}

	public static LocalDate dateToLocalDate(Date data) {

		Instant instant = data.toInstant();
		ZoneId systemDefault = ZoneId.systemDefault();
		ZonedDateTime zonedDateTime = instant.atZone(systemDefault);
		LocalDate localDate = zonedDateTime.toLocalDate();

		return localDate;
	}

	public Privileges getPrivilege() {
		return privilege;
	}

	public void setPrivilege(Privileges privilege) {
		this.privilege = privilege;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
}
