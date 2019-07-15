package com.sm.net.sp.model;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.sm.net.sp.Meta;

public class PrivilegeHistory {

	private Privileges privilege;
	private LocalDate lastDate;
	private String status;

	public PrivilegeHistory(Privileges privilege, int keyPrivilegeDate, int keyWeek) {
		super();
		this.privilege = privilege;

		LocalDate privilegeDate = keyWeekToLocalDate(keyPrivilegeDate);

		this.lastDate = privilegeDate;
		this.status = Meta.Resources.NOTHING;

		LocalDate firstDayOfWeekOfYear = keyWeekToLocalDate(keyWeek);
		updateStatus(privilegeDate, firstDayOfWeekOfYear);
	}

	private void updateStatus(LocalDate privilegeDate, LocalDate selectedWeek) {

		if (privilegeDate.compareTo(selectedWeek) > 0)
			this.status = Meta.Resources.FUTURE;
		else if (privilegeDate.compareTo(selectedWeek) < 0)
			this.status = Meta.Resources.PAST;
		else
			this.status = Meta.Resources.PRESENT;
	}

	public void checkLastDate(int keyPrivilegeDate, int keyWeek) {

		LocalDate privilegeDate = keyWeekToLocalDate(keyPrivilegeDate);
		LocalDate firstDayOfWeekOfYear = keyWeekToLocalDate(keyWeek);

		if (privilegeDate.compareTo(this.lastDate) > 0) {

			this.lastDate = privilegeDate;
			updateStatus(privilegeDate, firstDayOfWeekOfYear);
		}
	}

	public LocalDate keyWeekToLocalDate(int keyWeek) {

		String keyWeekString = String.valueOf(keyWeek);

		String yearText = keyWeekString.substring(0, 4);
		String weekNumberText = keyWeekString.substring(4, 6);

		return getFirstDayOfWeekOfYear(Integer.valueOf(yearText), Integer.valueOf(weekNumberText));
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

	public String getLastDateText() {
		return (this.lastDate != null) ? this.lastDate.toString() : "-";
	}

	public Privileges getPrivilege() {
		return privilege;
	}

	public void setPrivilege(Privileges privilege) {
		this.privilege = privilege;
	}

	public LocalDate getLastDate() {
		return lastDate;
	}

	public void setLastDate(LocalDate lastDate) {
		this.lastDate = lastDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
