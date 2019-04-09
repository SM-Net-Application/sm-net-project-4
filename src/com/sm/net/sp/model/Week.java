package com.sm.net.sp.model;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;

import org.json.JSONObject;

import com.sm.net.project.Language;
import com.sm.net.util.DateUtil;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

public class Week {

	private IntegerProperty week;
	private ObjectProperty<LocalDate> from;
	private ObjectProperty<LocalDate> to;
	private StringProperty key;

	private IntegerProperty spWeekID;
	private IntegerProperty spInf1;
	private IntegerProperty spInf2;

	private ObjectProperty<WeekTypeTranslated> weekTypeTranslated;

	public Week(LocalDate day, Language language) {
		super();

		week = new SimpleIntegerProperty(DateUtil.getWeekOfYears(day));
		from = new SimpleObjectProperty<LocalDate>(DateUtil.getFirstDayOfWeek(day));
		to = new SimpleObjectProperty<LocalDate>(DateUtil.getLastDayOfWeek(day));
		key = new SimpleStringProperty(Week.buildKey(day));

		weekTypeTranslated = new SimpleObjectProperty<WeekTypeTranslated>(
				new WeekTypeTranslated(WeekType.EMPTY, language));
	}

	public Week(JSONObject jsonObject, Language language) {

		this.spWeekID = new SimpleIntegerProperty(jsonObject.getInt("spWeekID"));
		this.spInf1 = new SimpleIntegerProperty(jsonObject.getInt("spInf1"));
		this.spInf2 = new SimpleIntegerProperty(jsonObject.getInt("spInf2"));

		weekTypeTranslated = new SimpleObjectProperty<WeekTypeTranslated>(
				new WeekTypeTranslated(WeekType.EMPTY, language));
	}

	public static String buildKey(LocalDate date) {

		NumberFormat nfYear = new DecimalFormat("0000");
		NumberFormat nfMonth = new DecimalFormat("00");

		return (date != null)
				? String.format("%s%s", nfYear.format(date.getYear()), nfMonth.format(DateUtil.getWeekOfYears(date)))
				: "";
	}

	public void updateOnlineWeekInfo(ObservableList<Week> list, Language language) {

		for (Week week : list)
			if (this.equals(week)) {

				this.setSpWeekID(week.getSpWeekID());
				this.setSpInf1(week.getSpInf1());
				this.setSpInf2(week.getSpInf2());

				this.weekTypeTranslated = new SimpleObjectProperty<WeekTypeTranslated>(
						new WeekTypeTranslated(WeekType.getFromOrdinal(week.getSpInf2()), language));

				break;
			}
	}

	@Override
	public boolean equals(Object obj) {

		if (obj != null)
			if (obj instanceof Week) {
				Week onlineWeek = (Week) obj;

				if (onlineWeek.spWeekIDProperty() != null)
					if (onlineWeek.spInf1Property() != null) {
						String onlineWeekKey = String.valueOf(onlineWeek.spInf1.get());
						if (onlineWeekKey != null)
							if (!onlineWeekKey.isEmpty()) {
								if (getKey().equals(onlineWeekKey))
									return true;
							}
					}
			}
		return false;
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

	public final IntegerProperty spWeekIDProperty() {
		return this.spWeekID;
	}

	public final int getSpWeekID() {
		return this.spWeekIDProperty().get();
	}

	public final void setSpWeekID(final int spWeekID) {
		if (this.spWeekIDProperty() == null)
			this.spWeekID = new SimpleIntegerProperty();

		this.spWeekIDProperty().set(spWeekID);
	}

	public final IntegerProperty spInf1Property() {
		return this.spInf1;
	}

	public final int getSpInf1() {
		return this.spInf1Property().get();
	}

	public final void setSpInf1(final int spInf1) {
		if (this.spInf1Property() == null)
			this.spInf1 = new SimpleIntegerProperty();

		this.spInf1Property().set(spInf1);
	}

	public final IntegerProperty spInf2Property() {
		return this.spInf2;
	}

	public final int getSpInf2() {
		return this.spInf2Property().get();
	}

	public final void setSpInf2(final int spInf2) {
		if (this.spInf2Property() == null)
			this.spInf2 = new SimpleIntegerProperty();

		this.spInf2Property().set(spInf2);
	}

	public final ObjectProperty<WeekTypeTranslated> weekTypeTranslatedProperty() {
		return this.weekTypeTranslated;
	}

	public final WeekTypeTranslated getWeekTypeTranslated() {
		return this.weekTypeTranslatedProperty().get();
	}

	public final void setWeekTypeTranslated(final WeekTypeTranslated weekTypeTranslated) {
		this.weekTypeTranslatedProperty().set(weekTypeTranslated);
	}

}
