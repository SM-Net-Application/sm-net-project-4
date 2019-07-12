package com.sm.net.sp.model;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;

import org.json.JSONObject;

import com.sm.net.project.Language;
import com.sm.net.sp.settings.Settings;
import com.sm.net.util.Crypt;
import com.sm.net.util.DateUtil;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

public class WeekOverseer {

	private IntegerProperty week;
	private ObjectProperty<LocalDate> from;
	private ObjectProperty<LocalDate> to;
	private StringProperty key;
	private StringProperty overseer;
	private StringProperty visitNumber;

	private IntegerProperty spWeekOvID;
	private IntegerProperty spInf1;
	private IntegerProperty spInf2;
	private StringProperty spInf3;
	private StringProperty spInf4;
	private StringProperty spInf5;
	private StringProperty spInf6;
	private StringProperty spInf7;
	private StringProperty spInf8;
	private StringProperty spInf9;
	private StringProperty spInf10;
	private StringProperty spInf11;
	private StringProperty spInf12;
	private StringProperty spInf13;
	private StringProperty spInf14;
	private StringProperty spInf15;

	public WeekOverseer(LocalDate day, Language language) {
		super();

		week = new SimpleIntegerProperty(DateUtil.getWeekOfYears(day));
		from = new SimpleObjectProperty<LocalDate>(DateUtil.getFirstDayOfWeek(day));
		to = new SimpleObjectProperty<LocalDate>(DateUtil.getLastDayOfWeek(day));
		key = new SimpleStringProperty(WeekOverseer.buildKey(day));
		visitNumber = new SimpleStringProperty("");
	}

	public WeekOverseer(JSONObject jsonObject, Language language, Settings settings, boolean forMeeting) {

		String suffix = "";
		if (forMeeting)
			suffix = "_ov";

		this.spWeekOvID = new SimpleIntegerProperty(jsonObject.getInt("spWeekOvID"));
		this.spInf1 = new SimpleIntegerProperty(jsonObject.getInt("spInf1" + suffix));
		this.spInf2 = new SimpleIntegerProperty(jsonObject.getInt("spInf2" + suffix));
		this.spInf3 = new SimpleStringProperty(
				Crypt.decrypt(jsonObject.getString("spInf3" + suffix), settings.getDatabaseSecretKey()));
		this.spInf4 = new SimpleStringProperty(
				Crypt.decrypt(jsonObject.getString("spInf4" + suffix), settings.getDatabaseSecretKey()));
		this.spInf5 = new SimpleStringProperty(
				Crypt.decrypt(jsonObject.getString("spInf5" + suffix), settings.getDatabaseSecretKey()));
		this.spInf6 = new SimpleStringProperty(
				Crypt.decrypt(jsonObject.getString("spInf6" + suffix), settings.getDatabaseSecretKey()));
		this.spInf7 = new SimpleStringProperty(
				Crypt.decrypt(jsonObject.getString("spInf7" + suffix), settings.getDatabaseSecretKey()));
		this.spInf8 = new SimpleStringProperty(
				Crypt.decrypt(jsonObject.getString("spInf8" + suffix), settings.getDatabaseSecretKey()));
		this.spInf9 = new SimpleStringProperty(
				Crypt.decrypt(jsonObject.getString("spInf9" + suffix), settings.getDatabaseSecretKey()));
		this.spInf10 = new SimpleStringProperty(
				Crypt.decrypt(jsonObject.getString("spInf10" + suffix), settings.getDatabaseSecretKey()));
		this.spInf11 = new SimpleStringProperty(
				Crypt.decrypt(jsonObject.getString("spInf11" + suffix), settings.getDatabaseSecretKey()));
		this.spInf12 = new SimpleStringProperty(
				Crypt.decrypt(jsonObject.getString("spInf12" + suffix), settings.getDatabaseSecretKey()));
		this.spInf13 = new SimpleStringProperty(
				Crypt.decrypt(jsonObject.getString("spInf13" + suffix), settings.getDatabaseSecretKey()));
		this.spInf14 = new SimpleStringProperty(
				Crypt.decrypt(jsonObject.getString("spInf14" + suffix), settings.getDatabaseSecretKey()));
		this.spInf15 = new SimpleStringProperty(
				Crypt.decrypt(jsonObject.getString("spInf15" + suffix), settings.getDatabaseSecretKey()));

		this.setVisitNumber(String.valueOf(this.getSpInf2()));
		this.setOverseer(this.getSpInf5().concat(", ").concat(this.getSpInf3()));
	}

	public static String buildKey(LocalDate date) {

		NumberFormat nfYear = new DecimalFormat("0000");
		NumberFormat nfMonth = new DecimalFormat("00");

		return (date != null)
				? String.format("%s%s", nfYear.format(date.getYear()), nfMonth.format(DateUtil.getWeekOfYears(date)))
				: "";
	}

	public void updateOnlineWeekInfo(ObservableList<WeekOverseer> list, Language language, Settings settings) {

		for (WeekOverseer week : list)
			if (this.equals(week)) {

				this.setSpWeekOvID(week.getSpWeekOvID());
				this.setSpInf1(week.getSpInf1());
				this.setSpInf2(week.getSpInf2());
				
				this.setSpInf3(week.getSpInf3());
				this.setSpInf4(week.getSpInf4());
				this.setSpInf5(week.getSpInf5());
				this.setSpInf6(week.getSpInf6());
				this.setSpInf7(week.getSpInf7());
				this.setSpInf8(week.getSpInf8());
				this.setSpInf9(week.getSpInf9());
				this.setSpInf10(week.getSpInf10());
				this.setSpInf11(week.getSpInf11());
				this.setSpInf12(week.getSpInf12());
				this.setSpInf13(week.getSpInf13());
				this.setSpInf14(week.getSpInf14());
				this.setSpInf15(week.getSpInf15());

//				this.setSpInf3(Crypt.decrypt(week.getSpInf3(), settings.getDatabaseSecretKey()));
//				this.setSpInf4(Crypt.decrypt(week.getSpInf4(), settings.getDatabaseSecretKey()));
//				this.setSpInf5(Crypt.decrypt(week.getSpInf5(), settings.getDatabaseSecretKey()));
//				this.setSpInf6(Crypt.decrypt(week.getSpInf6(), settings.getDatabaseSecretKey()));
//				this.setSpInf7(Crypt.decrypt(week.getSpInf7(), settings.getDatabaseSecretKey()));
//				this.setSpInf8(Crypt.decrypt(week.getSpInf8(), settings.getDatabaseSecretKey()));
//				this.setSpInf9(Crypt.decrypt(week.getSpInf9(), settings.getDatabaseSecretKey()));
//				this.setSpInf10(Crypt.decrypt(week.getSpInf10(), settings.getDatabaseSecretKey()));
//				this.setSpInf11(Crypt.decrypt(week.getSpInf11(), settings.getDatabaseSecretKey()));
//				this.setSpInf12(Crypt.decrypt(week.getSpInf12(), settings.getDatabaseSecretKey()));
//				this.setSpInf13(Crypt.decrypt(week.getSpInf13(), settings.getDatabaseSecretKey()));
//				this.setSpInf14(Crypt.decrypt(week.getSpInf14(), settings.getDatabaseSecretKey()));
//				this.setSpInf15(Crypt.decrypt(week.getSpInf15(), settings.getDatabaseSecretKey()));
				
				this.setVisitNumber(String.valueOf(this.getSpInf2()));
				this.setOverseer(this.getSpInf5().concat(", ").concat(this.getSpInf3()));

				break;
			}
	}

	@Override
	public boolean equals(Object obj) {

		if (obj != null)
			if (obj instanceof WeekOverseer) {
				WeekOverseer onlineWeek = (WeekOverseer) obj;

				if (onlineWeek.spWeekOvIDProperty() != null)
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

	public final IntegerProperty spWeekOvIDProperty() {
		return this.spWeekOvID;
	}

	public final int getSpWeekOvID() {
		return this.spWeekOvIDProperty().get();
	}

	public final void setSpWeekOvID(final int spWeekID) {
		if (this.spWeekOvIDProperty() == null)
			this.spWeekOvID = new SimpleIntegerProperty();

		this.spWeekOvIDProperty().set(spWeekID);
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

	public final StringProperty spInf3Property() {
		return this.spInf3;
	}

	public final String getSpInf3() {
		return this.spInf3Property().get();
	}

	public final void setSpInf3(final String spInf3) {
		if (this.spInf3Property() == null)
			this.spInf3 = new SimpleStringProperty();

		this.spInf3Property().set(spInf3);
	}

	public final StringProperty spInf4Property() {
		return this.spInf4;
	}

	public final String getSpInf4() {
		return this.spInf4Property().get();
	}

	public final void setSpInf4(final String spInf4) {
		if (this.spInf4Property() == null)
			this.spInf4 = new SimpleStringProperty();

		this.spInf4Property().set(spInf4);
	}

	public final StringProperty spInf5Property() {
		return this.spInf5;
	}

	public final String getSpInf5() {
		return this.spInf5Property().get();
	}

	public final void setSpInf5(final String spInf5) {
		if (this.spInf5Property() == null)
			this.spInf5 = new SimpleStringProperty();

		this.spInf5Property().set(spInf5);
	}

	public final StringProperty spInf6Property() {
		return this.spInf6;
	}

	public final String getSpInf6() {
		return this.spInf6Property().get();
	}

	public final void setSpInf6(final String spInf6) {
		if (this.spInf6Property() == null)
			this.spInf6 = new SimpleStringProperty();

		this.spInf6Property().set(spInf6);
	}

	public final StringProperty spInf7Property() {
		return this.spInf7;
	}

	public final String getSpInf7() {
		return this.spInf7Property().get();
	}

	public final void setSpInf7(final String spInf7) {
		if (this.spInf7Property() == null)
			this.spInf7 = new SimpleStringProperty();

		this.spInf7Property().set(spInf7);
	}

	public final StringProperty spInf8Property() {
		return this.spInf8;
	}

	public final String getSpInf8() {
		return this.spInf8Property().get();
	}

	public final void setSpInf8(final String spInf8) {
		if (this.spInf8Property() == null)
			this.spInf8 = new SimpleStringProperty();

		this.spInf8Property().set(spInf8);
	}

	public final StringProperty spInf9Property() {
		return this.spInf9;
	}

	public final String getSpInf9() {
		return this.spInf9Property().get();
	}

	public final void setSpInf9(final String spInf9) {
		if (this.spInf9Property() == null)
			this.spInf9 = new SimpleStringProperty();

		this.spInf9Property().set(spInf9);
	}

	public final StringProperty spInf10Property() {
		return this.spInf10;
	}

	public final String getSpInf10() {
		return this.spInf10Property().get();
	}

	public final void setSpInf10(final String spInf10) {
		if (this.spInf10Property() == null)
			this.spInf10 = new SimpleStringProperty();

		this.spInf10Property().set(spInf10);
	}

	public final StringProperty spInf11Property() {
		return this.spInf11;
	}

	public final String getSpInf11() {
		return this.spInf11Property().get();
	}

	public final void setSpInf11(final String spInf11) {
		if (this.spInf11Property() == null)
			this.spInf11 = new SimpleStringProperty();

		this.spInf11Property().set(spInf11);
	}

	public final StringProperty spInf12Property() {
		return this.spInf12;
	}

	public final String getSpInf12() {
		return this.spInf12Property().get();
	}

	public final void setSpInf12(final String spInf12) {
		if (this.spInf12Property() == null)
			this.spInf12 = new SimpleStringProperty();

		this.spInf12Property().set(spInf12);
	}

	public final StringProperty spInf13Property() {
		return this.spInf13;
	}

	public final String getSpInf13() {
		return this.spInf13Property().get();
	}

	public final void setSpInf13(final String spInf13) {
		if (this.spInf13Property() == null)
			this.spInf13 = new SimpleStringProperty();

		this.spInf13Property().set(spInf13);
	}

	public final StringProperty spInf14Property() {
		return this.spInf14;
	}

	public final String getSpInf14() {
		return this.spInf14Property().get();
	}

	public final void setSpInf14(final String spInf14) {
		if (this.spInf14Property() == null)
			this.spInf14 = new SimpleStringProperty();

		this.spInf14Property().set(spInf14);
	}

	public final StringProperty spInf15Property() {
		return this.spInf15;
	}

	public final String getSpInf15() {
		return this.spInf15Property().get();
	}

	public final void setSpInf15(final String spInf15) {
		if (this.spInf15Property() == null)
			this.spInf15 = new SimpleStringProperty();

		this.spInf15Property().set(spInf15);
	}

	public final StringProperty visitNumberProperty() {
		return this.visitNumber;
	}

	public final String getVisitNumber() {
		return this.visitNumberProperty().get();
	}

	public final void setVisitNumber(final String visitNumber) {
		if (this.visitNumber == null)
			this.visitNumber = new SimpleStringProperty();

		this.visitNumberProperty().set(visitNumber);
	}

	public final StringProperty overseerProperty() {
		return this.overseer;
	}

	public final String getOverseer() {
		return this.overseerProperty().get();
	}

	public final void setOverseer(final String overseer) {
		if (this.overseer == null)
			this.overseer = new SimpleStringProperty();

		this.overseerProperty().set(overseer);
	}

}
