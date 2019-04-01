package com.sm.net.sp.model;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;

import com.sm.net.util.DateUtil;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Week {

	private IntegerProperty week;
	private ObjectProperty<LocalDate> from;
	private ObjectProperty<LocalDate> to;
	private StringProperty key;

	public Week(LocalDate day) {
		super();

		week = new SimpleIntegerProperty(DateUtil.getWeekOfYears(day));
		from = new SimpleObjectProperty<LocalDate>(DateUtil.getFirstDayOfWeek(day));
		to = new SimpleObjectProperty<LocalDate>(DateUtil.getLastDayOfWeek(day));
		key = new SimpleStringProperty(Week.buildKey(day));
	}

	public static String buildKey(LocalDate date) {

		NumberFormat nfYear = new DecimalFormat("0000");
		NumberFormat nfMonth = new DecimalFormat("00");

		return (date != null)
				? String.format("%s%s", nfYear.format(date.getYear()), nfMonth.format(DateUtil.getWeekOfYears(date)))
				: "";
	}

	public final IntegerProperty weekProperty() {
		return this.week;
	}

	public final int getWeek() {
		return this.weekProperty().get();
	}

	public final void setWeek(final int week) {
		this.weekProperty().set(week);
	}

	public final ObjectProperty<LocalDate> fromProperty() {
		return this.from;
	}

	public final LocalDate getFrom() {
		return this.fromProperty().get();
	}

	public final void setFrom(final LocalDate from) {
		this.fromProperty().set(from);
	}

	public final ObjectProperty<LocalDate> toProperty() {
		return this.to;
	}

	public final LocalDate getTo() {
		return this.toProperty().get();
	}

	public final void setTo(final LocalDate to) {
		this.toProperty().set(to);
	}

	public final StringProperty keyProperty() {
		return this.key;
	}

	public final String getKey() {
		return this.keyProperty().get();
	}

	public final void setKey(final String key) {
		this.keyProperty().set(key);
	}

}
