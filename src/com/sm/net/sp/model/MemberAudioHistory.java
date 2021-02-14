package com.sm.net.sp.model;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.sm.net.sp.Meta;

import javafx.collections.ObservableList;

public class MemberAudioHistory {

	private Member member;
	private LocalDate lastDate;
	private Privileges privilege;
	private WeekAudio selectedWeek;
	private String status;

	public MemberAudioHistory(Member member, Privileges privilege, WeekAudio selectedWeek) {
		super();
		this.member = member;
		this.privilege = privilege;
		this.selectedWeek = selectedWeek;
		this.status = Meta.Resources.NOTHING;
		this.lastDate = null;
	}

	public String getLastDateText() {
		return (this.lastDate != null) ? this.lastDate.toString() : "-";
	}

	public void checkLastDate(ObservableList<WeekAudio> databaseWeeksAudio) {

		databaseWeeksAudio.forEach(week -> checkDatabaseWeek(week, this.selectedWeek));
		updateStatus(this.selectedWeek);
	}

	public void checkEditorLastDate(WeekAudio editorWeek) {
		checkWeek(editorWeek);
		updateStatus(this.selectedWeek);
	}

	private void updateStatus(WeekAudio selectedWeek) {

		if (this.lastDate != null) {

			LocalDate from = selectedWeek.getFrom();
			int compare = this.lastDate.compareTo(from);
			if (compare < 0)
				this.status = Meta.Resources.PAST;
			else if (compare > 0)
				this.status = Meta.Resources.FUTURE;
			else
				this.status = Meta.Resources.PRESENT;
		}
	}

	private void checkDatabaseWeek(WeekAudio week, WeekAudio selectedWeek) {

		if (selectedWeek.spInf1Property() != null)
			if (week.getSpInf1() != selectedWeek.getSpInf1())
				checkWeek(week);
	}

	private void checkWeek(WeekAudio week) {

		LocalDate from = null;

		switch (this.privilege) {
		case MIDWEEK_AUDIO_POS1:
		case WEEKEND_AUDIO_POS1:
			if (this.member.getSpMemberID() == week.getSpInf2() || this.member.getSpMemberID() == week.getSpInf8())
				from = checkLastDate(from, week.getSpInf1());
			break;
		case MIDWEEK_AUDIO_POS2:
		case WEEKEND_AUDIO_POS2:
			if (this.member.getSpMemberID() == week.getSpInf3() || this.member.getSpMemberID() == week.getSpInf9())
				from = checkLastDate(from, week.getSpInf1());
			break;
		case MIDWEEK_AUDIO_POS3:
		case WEEKEND_AUDIO_POS3:
			if (this.member.getSpMemberID() == week.getSpInf4() || this.member.getSpMemberID() == week.getSpInf10())
				from = checkLastDate(from, week.getSpInf1());
			break;
		case MIDWEEK_AUDIO_MIC1:
		case MIDWEEK_AUDIO_MIC2:
		case MIDWEEK_AUDIO_MIC3:
		case WEEKEND_AUDIO_MIC1:
		case WEEKEND_AUDIO_MIC2:
		case WEEKEND_AUDIO_MIC3:
			if (this.member.getSpMemberID() == week.getSpInf5() || this.member.getSpMemberID() == week.getSpInf6()
					|| this.member.getSpMemberID() == week.getSpInf7()
					|| this.member.getSpMemberID() == week.getSpInf11()
					|| this.member.getSpMemberID() == week.getSpInf12()
					|| this.member.getSpMemberID() == week.getSpInf13())
				from = checkLastDate(from, week.getSpInf1());
			break;
		default:
			break;
		}

		if (from != null)
			setLastDate(from);
	}

	private LocalDate checkLastDate(LocalDate from, int keyWeek) {

		String keyWeekString = String.valueOf(keyWeek);

		String yearText = keyWeekString.substring(0, 4);
		String weekNumberText = keyWeekString.substring(4, 6);

		LocalDate firstDayOfWeekOfYear = getFirstDayOfWeekOfYear(Integer.valueOf(yearText),
				Integer.valueOf(weekNumberText));

//		if (from != null)
//			return (firstDayOfWeekOfYear.compareTo(from) > 0) ? firstDayOfWeekOfYear : from;

		if (this.lastDate != null)
			return (firstDayOfWeekOfYear.compareTo(this.lastDate) > 0) ? firstDayOfWeekOfYear : this.lastDate;
		
		return firstDayOfWeekOfYear;
	}

	public LocalDate getFirstDayOfWeekOfYear(int year, int weekNumber) {

		GregorianCalendar cal = new GregorianCalendar();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.WEEK_OF_YEAR, weekNumber);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);

		return dateToLocalDate(cal.getTime());
	}

	public LocalDate dateToLocalDate(Date data) {

		Instant instant = data.toInstant();
		ZoneId systemDefault = ZoneId.systemDefault();
		ZonedDateTime zonedDateTime = instant.atZone(systemDefault);
		LocalDate localDate = zonedDateTime.toLocalDate();

		return localDate;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public LocalDate getLastDate() {
		return lastDate;
	}

	public void setLastDate(LocalDate lastDate) {
		this.lastDate = lastDate;
	}

	public Privileges getPrivilege() {
		return privilege;
	}

	public void setPrivilege(Privileges privilege) {
		this.privilege = privilege;
	}

	public WeekAudio getSelectedWeek() {
		return selectedWeek;
	}

	public void setSelectedWeek(WeekAudio selectedWeek) {
		this.selectedWeek = selectedWeek;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
