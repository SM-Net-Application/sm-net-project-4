package com.sm.net.sp.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import com.sm.net.util.DateUtil;

public class DateUtils {

	public static String getWeekNr(LocalDate date) {

		NumberFormat nfYear = new DecimalFormat("0000");
		NumberFormat nfMonth = new DecimalFormat("00");

		int year = date.getYear();
		int monthValue = date.getMonthValue();
		int dayOfMonth = date.getDayOfMonth();

		if (monthValue == 1)
			if (dayOfMonth < 4)
				year = year - 1;

		return (date != null)
				? String.format("%s%s", nfYear.format(year), nfMonth.format(DateUtil.getWeekOfYears(date)))
				: "";
	}

	public static LocalDate getLocalDateByWeekKey(int weekKey) {

		String weekKeyText = String.valueOf(weekKey);
		Integer year = Integer.valueOf(weekKeyText.substring(0, 4));
		Integer week = Integer.valueOf(weekKeyText.substring(4));

		Calendar calendar = Calendar.getInstance();
		calendar.setWeekDate(year, week, Calendar.MONDAY);
		String monday = new SimpleDateFormat("dd.MM.yyyy").format(calendar.getTime());

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
		return LocalDate.parse(monday, formatter);
	}

}
