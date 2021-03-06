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

public class MemberHistory {

	private Member member;
	private LocalDate lastDate;
	private Privileges privilege;
	private Week selectedWeek;
	private String status;

	public MemberHistory(Member member, Privileges privilege, Week selectedWeek) {
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

	public void checkLastDate(ObservableList<Week> databaseWeeks) {

		databaseWeeks.forEach(week -> checkDatabaseWeek(week, this.selectedWeek));
		updateStatus(this.selectedWeek);
	}

	public void checkEditorLastDate(Week editorWeek) {
		checkWeek(editorWeek);
		updateStatus(this.selectedWeek);
	}

	private void updateStatus(Week selectedWeek) {

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

	private void checkDatabaseWeek(Week week, Week selectedWeek) {

		if (selectedWeek.spInf1Property() != null)
			if (week.getSpInf1() != selectedWeek.getSpInf1())
				checkWeek(week);
	}

	private void checkWeek(Week week) {

		LocalDate from = null;

		switch (this.privilege) {
		case MIDWEEK_PRESIDENT:
			if (this.member.getSpMemberID() == week.getSpInf3())
				from = checkLastDate(from, week.getSpInf1());
			break;
		case MIDWEEK_PRAY_START:
		case MIDWEEK_PRAY_END:
		case WEEKEND_PRAY_END:
			if (this.member.getSpMemberID() == week.getSpInf4() || this.member.getSpMemberID() == week.getSpInf27()
					|| this.member.getSpMemberID() == week.getSpInf40())
				from = checkLastDate(from, week.getSpInf1());
			break;
		case MIDWEEK_CONGRBIBLESTUDY_READER:
		case WEEKEND_WATCHTOWER_READER:
			if (this.member.getSpMemberID() == week.getSpInf29() || this.member.getSpMemberID() == week.getSpInf38())
				from = checkLastDate(from, week.getSpInf1());
			break;
		case MIDWEEK_TALK:
			if (this.member.getSpMemberID() == week.getSpInf11())
				from = checkLastDate(from, week.getSpInf1());
			break;
		case MIDWEEK_DIGGING:
			if (this.member.getSpMemberID() == week.getSpInf14())
				from = checkLastDate(from, week.getSpInf1());
			break;
		case MIDWEEK_CONGRBIBLESTUDY:
			if (this.member.getSpMemberID() == week.getSpInf23())
				from = checkLastDate(from, week.getSpInf1());
			break;
		case MIDWEEK_CHRISTIAN_LIFE:
			ObservableList<ChristiansPart> christiansPartList = week.getChristiansPartList();
			for (ChristiansPart christiansPart : christiansPartList) {
				if (this.member.getSpMemberID() == christiansPart.getTeacher().getSpMemberID())
					from = checkLastDate(from, week.getSpInf1());
			}
			break;
		case WEEKEND_PRESIDENT:
			if (this.member.getSpMemberID() == week.getSpInf30())
				from = checkLastDate(from, week.getSpInf1());
			break;
		case WEEKEND_WATCHTOWER:
			if (this.member.getSpMemberID() == week.getSpInf37())
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

	public Week getSelectedWeek() {
		return selectedWeek;
	}

	public void setSelectedWeek(Week selectedWeek) {
		this.selectedWeek = selectedWeek;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
