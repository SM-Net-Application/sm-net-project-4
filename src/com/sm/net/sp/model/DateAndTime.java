package com.sm.net.sp.model;

import java.time.LocalDate;
import java.time.LocalTime;

import org.json.JSONObject;

import com.sm.net.sp.view.home.user.menu.dateandtime.MenuDateAndTimeAdd;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class DateAndTime {

	private IntegerProperty id;
	private ObjectProperty<LocalDate> date;
	private IntegerProperty day1;
	private IntegerProperty hour1;
	private IntegerProperty minute1;
	private IntegerProperty day2;
	private IntegerProperty hour2;
	private IntegerProperty minute2;

	private StringProperty day1Text;
	private ObjectProperty<LocalTime> time1;
	private StringProperty day2Text;
	private ObjectProperty<LocalTime> time2;

	public DateAndTime(LocalDate date, Integer day1, Integer hour1, Integer minute1, Integer day2, Integer hour2,
			Integer minute2) {
		super();

		this.date = new SimpleObjectProperty<LocalDate>(date);
		this.day1 = new SimpleIntegerProperty(day1);
		this.hour1 = new SimpleIntegerProperty(hour1);
		this.minute1 = new SimpleIntegerProperty(minute1);
		this.day2 = new SimpleIntegerProperty(day2);
		this.hour2 = new SimpleIntegerProperty(hour2);
		this.minute2 = new SimpleIntegerProperty(minute2);

		LocalTime time1 = LocalTime.of(hour1, minute1);
		LocalTime time2 = LocalTime.of(hour2, minute2);

		this.time1 = new SimpleObjectProperty<LocalTime>(time1);
		this.time2 = new SimpleObjectProperty<LocalTime>(time2);

		switch (day1) {
		case 1:
			this.day1Text = new SimpleStringProperty("TEXT0123");
			break;
		case 2:
			this.day1Text = new SimpleStringProperty("TEXT0124");
			break;
		case 3:
			this.day1Text = new SimpleStringProperty("TEXT0125");
			break;
		case 4:
			this.day1Text = new SimpleStringProperty("TEXT0126");
			break;
		case 5:
			this.day1Text = new SimpleStringProperty("TEXT0127");
			break;
		default:
			this.day1Text = new SimpleStringProperty("TEXT0123");
			break;
		}

		switch (day2) {
		case 6:
			this.day2Text = new SimpleStringProperty("TEXT0128");
			break;
		case 7:
			this.day2Text = new SimpleStringProperty("TEXT0129");
			break;
		default:
			this.day2Text = new SimpleStringProperty("TEXT0128");
			break;
		}
	}

	public DateAndTime(Integer id, LocalDate date, Integer day1, Integer hour1, Integer minute1, Integer day2,
			Integer hour2, Integer minute2) {

		this(date, day1, hour1, minute1, day2, hour2, minute2);

		this.id = new SimpleIntegerProperty(id);
	}

	public static DateAndTime newInstanceByView(MenuDateAndTimeAdd view) {

		// date
		LocalDate date = view.getDatePicker().getValue();

		// day1
		boolean day1check = view.getDay1CheckBox().isSelected();
		boolean day2check = view.getDay2CheckBox().isSelected();
		boolean day3check = view.getDay3CheckBox().isSelected();
		boolean day4check = view.getDay4CheckBox().isSelected();
		boolean day5check = view.getDay5CheckBox().isSelected();

		Integer day1 = 0;
		if (day1check)
			day1 = 1;
		else if (day2check)
			day1 = 2;
		else if (day3check)
			day1 = 3;
		else if (day4check)
			day1 = 4;
		else if (day5check)
			day1 = 5;

		boolean day6check = view.getDay6CheckBox().isSelected();
		boolean day7check = view.getDay7CheckBox().isSelected();

		Integer hour1 = view.getHours1ComboBox().getSelectionModel().getSelectedItem();
		Integer minute1 = view.getMinute1ComboBox().getSelectionModel().getSelectedItem();

		// day2
		Integer day2 = 0;
		if (day6check)
			day2 = 6;
		else if (day7check)
			day2 = 7;

		Integer hour2 = view.getHours2ComboBox().getSelectionModel().getSelectedItem();
		Integer minute2 = view.getMinute2ComboBox().getSelectionModel().getSelectedItem();

		return new DateAndTime(date, day1, hour1, minute1, day2, hour2, minute2);
	}

	public static DateAndTime newInstanceByJSONObject(JSONObject json) {

		int spDateTimeID = json.getInt("spDateTimeID");
		String dateString = json.getString("spInf1");
		int day1 = json.getInt("spInf2");
		int hour1 = json.getInt("spInf3");
		int minute1 = json.getInt("spInf4");
		int day2 = json.getInt("spInf5");
		int hour2 = json.getInt("spInf6");
		int minute2 = json.getInt("spInf7");

		LocalDate date = LocalDate.parse(dateString);

		return new DateAndTime(spDateTimeID, date, day1, hour1, minute1, day2, hour2, minute2);
	}

	public ObjectProperty<LocalDate> getDate() {
		return date;
	}

	public void setDate(ObjectProperty<LocalDate> date) {
		this.date = date;
	}

	public IntegerProperty getDay1() {
		return day1;
	}

	public void setDay1(IntegerProperty day1) {
		this.day1 = day1;
	}

	public IntegerProperty getHour1() {
		return hour1;
	}

	public void setHour1(IntegerProperty hour1) {
		this.hour1 = hour1;
	}

	public IntegerProperty getMinute1() {
		return minute1;
	}

	public void setMinute1(IntegerProperty minute1) {
		this.minute1 = minute1;
	}

	public IntegerProperty getDay2() {
		return day2;
	}

	public void setDay2(IntegerProperty day2) {
		this.day2 = day2;
	}

	public IntegerProperty getHour2() {
		return hour2;
	}

	public void setHour2(IntegerProperty hour2) {
		this.hour2 = hour2;
	}

	public IntegerProperty getMinute2() {
		return minute2;
	}

	public void setMinute2(IntegerProperty minute2) {
		this.minute2 = minute2;
	}

	public IntegerProperty getId() {
		return id;
	}

	public void setId(IntegerProperty id) {
		this.id = id;
	}

	public ObjectProperty<LocalTime> getTime1() {
		return time1;
	}

	public void setTime1(ObjectProperty<LocalTime> time1) {
		this.time1 = time1;
	}

	public ObjectProperty<LocalTime> getTime2() {
		return time2;
	}

	public void setTime2(ObjectProperty<LocalTime> time2) {
		this.time2 = time2;
	}

	public StringProperty getDay1Text() {
		return day1Text;
	}

	public void setDay1Text(StringProperty day1Text) {
		this.day1Text = day1Text;
	}

	public StringProperty getDay2Text() {
		return day2Text;
	}

	public void setDay2Text(StringProperty day2Text) {
		this.day2Text = day2Text;
	}
}
