package com.sm.net.sp.model;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;

import javax.crypto.SecretKey;

import org.json.JSONObject;

import com.sm.net.project.Language;
import com.sm.net.sp.settings.Settings;
import com.sm.net.sp.view.home.user.menu.conven.ConventionEditor;
import com.sm.net.util.Crypt;
import com.sm.net.util.DateUtil;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

public class WeekConvention {

	private IntegerProperty week;
	private ObjectProperty<LocalDate> from;
	private ObjectProperty<LocalDate> to;
	private StringProperty key;

	private IntegerProperty spConvenID;
	private IntegerProperty spInf1;
	private IntegerProperty spInf2;
	private StringProperty spInf3;
	private StringProperty spInf4;
	private StringProperty spInf5;
	private StringProperty spInf6;
	private IntegerProperty spInf7;
	private IntegerProperty spInf8;
	private IntegerProperty spInf9;
	private IntegerProperty spInf10;
	private IntegerProperty spInf11;
	private IntegerProperty spInf12;
	private IntegerProperty spInf13;
	private IntegerProperty spInf14;
	private IntegerProperty spInf15;
	private IntegerProperty spInf16;
	private IntegerProperty spInf17;
	private IntegerProperty spInf18;
	private IntegerProperty spInf19;
	private StringProperty spInf20;
	private StringProperty spInf21;
	private StringProperty spInf22;
	private StringProperty spInf23;
	private StringProperty spInf24;
	private StringProperty spInf25;
	private StringProperty spInf26;
	private StringProperty spInf27;
	private StringProperty spInf28;
	private StringProperty spInf29;
	private StringProperty spInf30;
	private IntegerProperty spInf31;

	public WeekConvention(LocalDate day, Language language) {
		super();

		week = new SimpleIntegerProperty(DateUtil.getWeekOfYears(day));
		from = new SimpleObjectProperty<LocalDate>(DateUtil.getFirstDayOfWeek(day));
		to = new SimpleObjectProperty<LocalDate>(DateUtil.getLastDayOfWeek(day));
		key = new SimpleStringProperty(WeekConvention.buildKey(day));
	}

	public WeekConvention(JSONObject jsonObject, Settings settings) {

//		this.spWeekOvID = new SimpleIntegerProperty(jsonObject.getInt("spWeekOvID"));
//		this.spInf1 = new SimpleIntegerProperty(jsonObject.getInt("spInf1" + suffix));
//		this.spInf2 = new SimpleIntegerProperty(jsonObject.getInt("spInf2" + suffix));
//		this.spInf3 = new SimpleStringProperty(
//				Crypt.decrypt(jsonObject.getString("spInf3" + suffix), settings.getDatabaseSecretKey()));
//		this.spInf4 = new SimpleStringProperty(
//				Crypt.decrypt(jsonObject.getString("spInf4" + suffix), settings.getDatabaseSecretKey()));
//		this.spInf5 = new SimpleStringProperty(
//				Crypt.decrypt(jsonObject.getString("spInf5" + suffix), settings.getDatabaseSecretKey()));
//		this.spInf6 = new SimpleStringProperty(
//				Crypt.decrypt(jsonObject.getString("spInf6" + suffix), settings.getDatabaseSecretKey()));
//		this.spInf7 = new SimpleStringProperty(
//				Crypt.decrypt(jsonObject.getString("spInf7" + suffix), settings.getDatabaseSecretKey()));
//		this.spInf8 = new SimpleStringProperty(
//				Crypt.decrypt(jsonObject.getString("spInf8" + suffix), settings.getDatabaseSecretKey()));
//		this.spInf9 = new SimpleStringProperty(
//				Crypt.decrypt(jsonObject.getString("spInf9" + suffix), settings.getDatabaseSecretKey()));
//		this.spInf10 = new SimpleStringProperty(
//				Crypt.decrypt(jsonObject.getString("spInf10" + suffix), settings.getDatabaseSecretKey()));
//		this.spInf11 = new SimpleStringProperty(
//				Crypt.decrypt(jsonObject.getString("spInf11" + suffix), settings.getDatabaseSecretKey()));
//		this.spInf12 = new SimpleStringProperty(
//				Crypt.decrypt(jsonObject.getString("spInf12" + suffix), settings.getDatabaseSecretKey()));
//		this.spInf13 = new SimpleStringProperty(
//				Crypt.decrypt(jsonObject.getString("spInf13" + suffix), settings.getDatabaseSecretKey()));
//		this.spInf14 = new SimpleStringProperty(
//				Crypt.decrypt(jsonObject.getString("spInf14" + suffix), settings.getDatabaseSecretKey()));
//		this.spInf15 = new SimpleStringProperty(
//				Crypt.decrypt(jsonObject.getString("spInf15" + suffix), settings.getDatabaseSecretKey()));
//		this.spInf16 = new SimpleStringProperty(
//				Crypt.decrypt(jsonObject.getString("spInf16" + suffix), settings.getDatabaseSecretKey()));
//		this.spInf17 = new SimpleStringProperty(
//				Crypt.decrypt(jsonObject.getString("spInf17" + suffix), settings.getDatabaseSecretKey()));
//		this.spInf18 = new SimpleStringProperty(
//				Crypt.decrypt(jsonObject.getString("spInf18" + suffix), settings.getDatabaseSecretKey()));
//		this.spInf19 = new SimpleStringProperty(
//				Crypt.decrypt(jsonObject.getString("spInf19" + suffix), settings.getDatabaseSecretKey()));
//		this.spInf20 = new SimpleIntegerProperty(jsonObject.getInt("spInf20" + suffix));

	}

	public WeekConvention(int spConvenID, int spInf1, int spInf2, String spInf3, String spInf4, String spInf5,
			String spInf6, int spInf7, int spInf8, int spInf9, int spInf10, int spInf11, int spInf12, int spInf13,
			int spInf14, int spInf15, int spInf16, int spInf17, int spInf18, int spInf19, String spInf20,
			String spInf21, String spInf22, String spInf23, String spInf24, String spInf25, String spInf26,
			String spInf27, String spInf28, String spInf29, String spInf30, int spInf31) {

		this.week = null;
		this.from = null;
		this.to = null;
		this.key = null;

		this.spConvenID = new SimpleIntegerProperty(spConvenID);
		this.spInf1 = new SimpleIntegerProperty(spInf1);
		this.spInf2 = new SimpleIntegerProperty(spInf2);
		this.spInf3 = new SimpleStringProperty(spInf3);
		this.spInf4 = new SimpleStringProperty(spInf4);
		this.spInf5 = new SimpleStringProperty(spInf5);
		this.spInf6 = new SimpleStringProperty(spInf6);
		this.spInf7 = new SimpleIntegerProperty(spInf7);
		this.spInf8 = new SimpleIntegerProperty(spInf8);
		this.spInf9 = new SimpleIntegerProperty(spInf9);
		this.spInf10 = new SimpleIntegerProperty(spInf10);
		this.spInf11 = new SimpleIntegerProperty(spInf11);
		this.spInf12 = new SimpleIntegerProperty(spInf12);
		this.spInf13 = new SimpleIntegerProperty(spInf13);
		this.spInf14 = new SimpleIntegerProperty(spInf14);
		this.spInf15 = new SimpleIntegerProperty(spInf15);
		this.spInf16 = new SimpleIntegerProperty(spInf16);
		this.spInf17 = new SimpleIntegerProperty(spInf17);
		this.spInf18 = new SimpleIntegerProperty(spInf18);
		this.spInf19 = new SimpleIntegerProperty(spInf19);
		this.spInf20 = new SimpleStringProperty(spInf20);
		this.spInf21 = new SimpleStringProperty(spInf21);
		this.spInf22 = new SimpleStringProperty(spInf22);
		this.spInf23 = new SimpleStringProperty(spInf23);
		this.spInf24 = new SimpleStringProperty(spInf24);
		this.spInf25 = new SimpleStringProperty(spInf25);
		this.spInf26 = new SimpleStringProperty(spInf26);
		this.spInf27 = new SimpleStringProperty(spInf27);
		this.spInf28 = new SimpleStringProperty(spInf28);
		this.spInf29 = new SimpleStringProperty(spInf29);
		this.spInf30 = new SimpleStringProperty(spInf30);
		this.spInf31 = new SimpleIntegerProperty(spInf31);
	}

	public static String buildKey(LocalDate date) {

		NumberFormat nfYear = new DecimalFormat("0000");
		NumberFormat nfMonth = new DecimalFormat("00");

		return (date != null)
				? String.format("%s%s", nfYear.format(date.getYear()), nfMonth.format(DateUtil.getWeekOfYears(date)))
				: "";
	}

	public void updateOnlineWeekInfo(ObservableList<WeekConvention> list, Settings settings) {

		for (WeekConvention week : list)
			if (this.equals(week)) {

//				this.setSpWeekOvID(week.getSpWeekOvID());
//				this.setSpInf1(week.getSpInf1());
//				this.setSpInf2(week.getSpInf2());
//
//				this.setSpInf3(week.getSpInf3());
//				this.setSpInf4(week.getSpInf4());
//				this.setSpInf5(week.getSpInf5());
//				this.setSpInf6(week.getSpInf6());
//				this.setSpInf7(week.getSpInf7());
//				this.setSpInf8(week.getSpInf8());
//				this.setSpInf9(week.getSpInf9());
//				this.setSpInf10(week.getSpInf10());
//				this.setSpInf11(week.getSpInf11());
//				this.setSpInf12(week.getSpInf12());
//				this.setSpInf13(week.getSpInf13());
//				this.setSpInf14(week.getSpInf14());
//				this.setSpInf15(week.getSpInf15());
//
//				this.setSpInf16(week.getSpInf16());
//				this.setSpInf17(week.getSpInf17());
//				this.setSpInf18(week.getSpInf18());
//				this.setSpInf19(week.getSpInf19());
//				this.setSpInf20(week.getSpInf20());
//
//				this.setVisitNumber(String.valueOf(this.getSpInf2()));
//				this.setOverseer(this.getSpInf5().concat(", ").concat(this.getSpInf3()));

				break;
			}
	}

	public static WeekConvention newInstanceByView(ConventionEditor conventionEditor) {

		Language language = conventionEditor.getLanguage();
		SecretKey secretKey = conventionEditor.getSettings().getDatabaseSecretKey();

		WeekConvention selectedWeek = conventionEditor.getSelectedWeek();

		int spInf1 = conventionEditor.getTypeComboBox().getSelectionModel().getSelectedItem().getId();
		int spInf2 = Integer.valueOf(conventionEditor.getYearTextField().getText());
		String spInf3 = Crypt.encrypt(conventionEditor.getThemeTextField().getText(), secretKey);
		String spInf4 = Crypt.encrypt(conventionEditor.getScriptureDay1TextField().getText(), secretKey);
		String spInf5 = Crypt.encrypt(conventionEditor.getScriptureDay2TextField().getText(), secretKey);
		String spInf6 = Crypt.encrypt(conventionEditor.getScriptureDay3TextField().getText(), secretKey);
		int spInf7 = conventionEditor.getDayComboBox().getSelectionModel().getSelectedItem().getId();
		int spInf8 = conventionEditor.getStartHourDay1ComboBox().getSelectionModel().getSelectedItem();
		int spInf9 = conventionEditor.getStartMinuteDay1ComboBox().getSelectionModel().getSelectedItem();
		int spInf10 = conventionEditor.getEndHourDay1ComboBox().getSelectionModel().getSelectedItem();
		int spInf11 = conventionEditor.getEndMinuteDay1ComboBox().getSelectionModel().getSelectedItem();
		int spInf12 = conventionEditor.getStartHourDay2ComboBox().getSelectionModel().getSelectedItem();
		int spInf13 = conventionEditor.getStartMinuteDay2ComboBox().getSelectionModel().getSelectedItem();
		int spInf14 = conventionEditor.getEndHourDay2ComboBox().getSelectionModel().getSelectedItem();
		int spInf15 = conventionEditor.getEndMinuteDay2ComboBox().getSelectionModel().getSelectedItem();
		int spInf16 = conventionEditor.getStartHourDay3ComboBox().getSelectionModel().getSelectedItem();
		int spInf17 = conventionEditor.getStartMinuteDay3ComboBox().getSelectionModel().getSelectedItem();
		int spInf18 = conventionEditor.getEndHourDay3ComboBox().getSelectionModel().getSelectedItem();
		int spInf19 = conventionEditor.getEndMinuteDay3ComboBox().getSelectionModel().getSelectedItem();
		String spInf20 = Crypt.encrypt(conventionEditor.getQuestionHeaderTextField().getText(), secretKey);
		String spInf21 = Crypt.encrypt(conventionEditor.getQuestion1TextField().getText(), secretKey);
		String spInf22 = Crypt.encrypt(conventionEditor.getQuestion2TextField().getText(), secretKey);
		String spInf23 = Crypt.encrypt(conventionEditor.getQuestion3TextField().getText(), secretKey);
		String spInf24 = Crypt.encrypt(conventionEditor.getQuestion4TextField().getText(), secretKey);
		String spInf25 = Crypt.encrypt(conventionEditor.getQuestion5TextField().getText(), secretKey);
		String spInf26 = Crypt.encrypt(conventionEditor.getQuestion6TextField().getText(), secretKey);
		String spInf27 = Crypt.encrypt(conventionEditor.getQuestion7TextField().getText(), secretKey);
		String spInf28 = Crypt.encrypt(conventionEditor.getQuestion8TextField().getText(), secretKey);
		String spInf29 = Crypt.encrypt(conventionEditor.getQuestion9TextField().getText(), secretKey);
		String spInf30 = Crypt.encrypt(conventionEditor.getQuestion10TextField().getText(), secretKey);
		int spInf31 = Integer.valueOf(conventionEditor.getSelectedWeek().getKey());

		WeekConvention weekConvention = new WeekConvention(selectedWeek.getFrom(), language);

		if (selectedWeek != null)
			if (selectedWeek.spConvenIDProperty() != null)
				weekConvention.setConvenID(selectedWeek.getConvenID());

		weekConvention.setSpInf1(spInf1);
		weekConvention.setSpInf2(spInf2);
		weekConvention.setSpInf3(spInf3);
		weekConvention.setSpInf4(spInf4);
		weekConvention.setSpInf5(spInf5);
		weekConvention.setSpInf6(spInf6);
		weekConvention.setSpInf7(spInf7);
		weekConvention.setSpInf8(spInf8);
		weekConvention.setSpInf9(spInf9);
		weekConvention.setSpInf10(spInf10);
		weekConvention.setSpInf11(spInf11);
		weekConvention.setSpInf12(spInf12);
		weekConvention.setSpInf13(spInf13);
		weekConvention.setSpInf14(spInf14);
		weekConvention.setSpInf15(spInf15);
		weekConvention.setSpInf16(spInf16);
		weekConvention.setSpInf17(spInf17);
		weekConvention.setSpInf18(spInf18);
		weekConvention.setSpInf19(spInf19);
		weekConvention.setSpInf20(spInf20);
		weekConvention.setSpInf21(spInf21);
		weekConvention.setSpInf22(spInf22);
		weekConvention.setSpInf23(spInf23);
		weekConvention.setSpInf24(spInf24);
		weekConvention.setSpInf25(spInf25);
		weekConvention.setSpInf26(spInf26);
		weekConvention.setSpInf27(spInf27);
		weekConvention.setSpInf28(spInf28);
		weekConvention.setSpInf29(spInf29);
		weekConvention.setSpInf30(spInf30);
		weekConvention.setSpInf31(spInf31);

		return weekConvention;
	}

	public static WeekConvention newInstanceByJSONObject(JSONObject json, SecretKey secretKey) {

		int spConvenID = json.getInt("spConvenID");
		int spInf1 = json.getInt("spInf1");
		int spInf2 = json.getInt("spInf2");
		String spInf3 = Crypt.decrypt(json.getString("spInf3"), secretKey);
		String spInf4 = Crypt.decrypt(json.getString("spInf4"), secretKey);
		String spInf5 = Crypt.decrypt(json.getString("spInf5"), secretKey);
		String spInf6 = Crypt.decrypt(json.getString("spInf6"), secretKey);
		int spInf7 = json.getInt("spInf7");
		int spInf8 = json.getInt("spInf8");
		int spInf9 = json.getInt("spInf9");
		int spInf10 = json.getInt("spInf10");
		int spInf11 = json.getInt("spInf11");
		int spInf12 = json.getInt("spInf12");
		int spInf13 = json.getInt("spInf13");
		int spInf14 = json.getInt("spInf14");
		int spInf15 = json.getInt("spInf15");
		int spInf16 = json.getInt("spInf16");
		int spInf17 = json.getInt("spInf17");
		int spInf18 = json.getInt("spInf18");
		int spInf19 = json.getInt("spInf19");
		String spInf20 = Crypt.decrypt(json.getString("spInf20"), secretKey);
		String spInf21 = Crypt.decrypt(json.getString("spInf21"), secretKey);
		String spInf22 = Crypt.decrypt(json.getString("spInf22"), secretKey);
		String spInf23 = Crypt.decrypt(json.getString("spInf23"), secretKey);
		String spInf24 = Crypt.decrypt(json.getString("spInf24"), secretKey);
		String spInf25 = Crypt.decrypt(json.getString("spInf25"), secretKey);
		String spInf26 = Crypt.decrypt(json.getString("spInf26"), secretKey);
		String spInf27 = Crypt.decrypt(json.getString("spInf27"), secretKey);
		String spInf28 = Crypt.decrypt(json.getString("spInf28"), secretKey);
		String spInf29 = Crypt.decrypt(json.getString("spInf29"), secretKey);
		String spInf30 = Crypt.decrypt(json.getString("spInf30"), secretKey);
		int spInf31 = json.getInt("spInf31");

		return new WeekConvention(spConvenID, spInf1, spInf2, spInf3, spInf4, spInf5, spInf6, spInf7, spInf8, spInf9,
				spInf10, spInf11, spInf12, spInf13, spInf14, spInf15, spInf16, spInf17, spInf18, spInf19, spInf20,
				spInf21, spInf22, spInf23, spInf24, spInf25, spInf26, spInf27, spInf28, spInf29, spInf30, spInf31);
	}

	public void updateInfo(WeekConvention wc) {

		this.setConvenID(wc.getConvenID());
		this.setSpInf1(wc.getSpInf1());
		this.setSpInf2(wc.getSpInf2());
		this.setSpInf3(wc.getSpInf3());
		this.setSpInf4(wc.getSpInf4());
		this.setSpInf5(wc.getSpInf5());
		this.setSpInf6(wc.getSpInf6());
		this.setSpInf7(wc.getSpInf7());
		this.setSpInf8(wc.getSpInf8());
		this.setSpInf9(wc.getSpInf9());
		this.setSpInf10(wc.getSpInf10());
		this.setSpInf11(wc.getSpInf11());
		this.setSpInf12(wc.getSpInf12());
		this.setSpInf13(wc.getSpInf13());
		this.setSpInf14(wc.getSpInf14());
		this.setSpInf15(wc.getSpInf15());
		this.setSpInf16(wc.getSpInf16());
		this.setSpInf17(wc.getSpInf17());
		this.setSpInf18(wc.getSpInf18());
		this.setSpInf19(wc.getSpInf19());
		this.setSpInf20(wc.getSpInf20());
		this.setSpInf21(wc.getSpInf21());
		this.setSpInf22(wc.getSpInf22());
		this.setSpInf23(wc.getSpInf23());
		this.setSpInf24(wc.getSpInf24());
		this.setSpInf25(wc.getSpInf25());
		this.setSpInf26(wc.getSpInf26());
		this.setSpInf27(wc.getSpInf27());
		this.setSpInf28(wc.getSpInf28());
		this.setSpInf29(wc.getSpInf29());
		this.setSpInf30(wc.getSpInf30());
		this.setSpInf31(wc.getSpInf31());
	}

	public void deleteInfo() {

		this.spConvenID = null;
		this.spInf1 = null;
		this.spInf2 = null;
		this.spInf3 = null;
		this.spInf4 = null;
		this.spInf5 = null;
		this.spInf6 = null;
		this.spInf7 = null;
		this.spInf8 = null;
		this.spInf9 = null;
		this.spInf10 = null;
		this.spInf11 = null;
		this.spInf12 = null;
		this.spInf13 = null;
		this.spInf14 = null;
		this.spInf15 = null;
		this.spInf16 = null;
		this.spInf17 = null;
		this.spInf18 = null;
		this.spInf19 = null;
		this.spInf20 = null;
		this.spInf21 = null;
		this.spInf22 = null;
		this.spInf23 = null;
		this.spInf24 = null;
		this.spInf25 = null;
		this.spInf26 = null;
		this.spInf27 = null;
		this.spInf28 = null;
		this.spInf29 = null;
		this.spInf30 = null;
		this.spInf31 = null;
	}

	@Override
	public boolean equals(Object obj) {

		if (obj != null)
			if (obj instanceof WeekConvention) {
				WeekConvention onlineWeek = (WeekConvention) obj;

				if (onlineWeek.spConvenIDProperty() != null)
					if (onlineWeek.spInf31Property() != null) {
						String onlineWeekKey = String.valueOf(onlineWeek.getSpInf31());
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

	public final IntegerProperty spConvenIDProperty() {
		return this.spConvenID;
	}

	public final int getConvenID() {
		return this.spConvenID.get();
	}

	public final void setConvenID(final int convenID) {
		if (this.spConvenIDProperty() == null)
			this.spConvenID = new SimpleIntegerProperty();

		this.spConvenIDProperty().set(convenID);
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

	public final IntegerProperty spInf7Property() {
		return this.spInf7;
	}

	public final int getSpInf7() {
		return this.spInf7Property().get();
	}

	public final void setSpInf7(final int spInf7) {
		if (this.spInf7Property() == null)
			this.spInf7 = new SimpleIntegerProperty();

		this.spInf7Property().set(spInf7);
	}

	public final IntegerProperty spInf8Property() {
		return this.spInf8;
	}

	public final int getSpInf8() {
		return this.spInf8Property().get();
	}

	public final void setSpInf8(final int spInf8) {
		if (this.spInf8Property() == null)
			this.spInf8 = new SimpleIntegerProperty();

		this.spInf8Property().set(spInf8);
	}

	public final IntegerProperty spInf9Property() {
		return this.spInf9;
	}

	public final int getSpInf9() {
		return this.spInf9Property().get();
	}

	public final void setSpInf9(final int spInf9) {
		if (this.spInf9Property() == null)
			this.spInf9 = new SimpleIntegerProperty();

		this.spInf9Property().set(spInf9);
	}

	public final IntegerProperty spInf10Property() {
		return this.spInf10;
	}

	public final int getSpInf10() {
		return this.spInf10Property().get();
	}

	public final void setSpInf10(final int spInf10) {
		if (this.spInf10Property() == null)
			this.spInf10 = new SimpleIntegerProperty();

		this.spInf10Property().set(spInf10);
	}

	public final IntegerProperty spInf11Property() {
		return this.spInf11;
	}

	public final int getSpInf11() {
		return this.spInf11Property().get();
	}

	public final void setSpInf11(final int spInf11) {
		if (this.spInf11Property() == null)
			this.spInf11 = new SimpleIntegerProperty();

		this.spInf11Property().set(spInf11);
	}

	public final IntegerProperty spInf12Property() {
		return this.spInf12;
	}

	public final int getSpInf12() {
		return this.spInf12Property().get();
	}

	public final void setSpInf12(final int spInf12) {
		if (this.spInf12Property() == null)
			this.spInf12 = new SimpleIntegerProperty();

		this.spInf12Property().set(spInf12);
	}

	public final IntegerProperty spInf13Property() {
		return this.spInf13;
	}

	public final int getSpInf13() {
		return this.spInf13Property().get();
	}

	public final void setSpInf13(final int spInf13) {
		if (this.spInf13Property() == null)
			this.spInf13 = new SimpleIntegerProperty();

		this.spInf13Property().set(spInf13);
	}

	public final IntegerProperty spInf14Property() {
		return this.spInf14;
	}

	public final int getSpInf14() {
		return this.spInf14Property().get();
	}

	public final void setSpInf14(final int spInf14) {
		if (this.spInf14Property() == null)
			this.spInf14 = new SimpleIntegerProperty();

		this.spInf14Property().set(spInf14);
	}

	public final IntegerProperty spInf15Property() {
		return this.spInf15;
	}

	public final int getSpInf15() {
		return this.spInf15Property().get();
	}

	public final void setSpInf15(final int spInf15) {
		if (this.spInf15Property() == null)
			this.spInf15 = new SimpleIntegerProperty();

		this.spInf15Property().set(spInf15);
	}

	public final IntegerProperty spInf16Property() {
		return this.spInf16;
	}

	public final int getSpInf16() {
		return this.spInf16Property().get();
	}

	public final void setSpInf16(final int spInf16) {
		if (this.spInf16Property() == null)
			this.spInf16 = new SimpleIntegerProperty();

		this.spInf16Property().set(spInf16);
	}

	public final IntegerProperty spInf17Property() {
		return this.spInf17;
	}

	public final int getSpInf17() {
		return this.spInf17Property().get();
	}

	public final void setSpInf17(final int spInf17) {
		if (this.spInf17Property() == null)
			this.spInf17 = new SimpleIntegerProperty();

		this.spInf17Property().set(spInf17);
	}

	public final IntegerProperty spInf18Property() {
		return this.spInf18;
	}

	public final int getSpInf18() {
		return this.spInf18Property().get();
	}

	public final void setSpInf18(final int spInf18) {
		if (this.spInf18Property() == null)
			this.spInf18 = new SimpleIntegerProperty();

		this.spInf18Property().set(spInf18);
	}

	public final IntegerProperty spInf19Property() {
		return this.spInf19;
	}

	public final int getSpInf19() {
		return this.spInf19Property().get();
	}

	public final void setSpInf19(final int spInf19) {
		if (this.spInf19Property() == null)
			this.spInf19 = new SimpleIntegerProperty();

		this.spInf19Property().set(spInf19);
	}

	public final StringProperty spInf20Property() {
		return this.spInf20;
	}

	public final String getSpInf20() {
		return this.spInf20Property().get();
	}

	public final void setSpInf20(final String spInf20) {
		if (this.spInf20Property() == null)
			this.spInf20 = new SimpleStringProperty();

		this.spInf20Property().set(spInf20);
	}

	public final StringProperty spInf21Property() {
		return this.spInf21;
	}

	public final String getSpInf21() {
		return this.spInf21Property().get();
	}

	public final void setSpInf21(final String spInf21) {
		if (this.spInf21Property() == null)
			this.spInf21 = new SimpleStringProperty();

		this.spInf21Property().set(spInf21);
	}

	public final StringProperty spInf22Property() {
		return this.spInf22;
	}

	public final String getSpInf22() {
		return this.spInf22Property().get();
	}

	public final void setSpInf22(final String spInf22) {
		if (this.spInf22Property() == null)
			this.spInf22 = new SimpleStringProperty();

		this.spInf22Property().set(spInf22);
	}

	public final StringProperty spInf23Property() {
		return this.spInf23;
	}

	public final String getSpInf23() {
		return this.spInf23Property().get();
	}

	public final void setSpInf23(final String spInf23) {
		if (this.spInf23Property() == null)
			this.spInf23 = new SimpleStringProperty();

		this.spInf23Property().set(spInf23);
	}

	public final StringProperty spInf24Property() {
		return this.spInf24;
	}

	public final String getSpInf24() {
		return this.spInf24Property().get();
	}

	public final void setSpInf24(final String spInf24) {
		if (this.spInf24Property() == null)
			this.spInf24 = new SimpleStringProperty();

		this.spInf24Property().set(spInf24);
	}

	public final StringProperty spInf25Property() {
		return this.spInf25;
	}

	public final String getSpInf25() {
		return this.spInf25Property().get();
	}

	public final void setSpInf25(final String spInf25) {
		if (this.spInf25Property() == null)
			this.spInf25 = new SimpleStringProperty();

		this.spInf25Property().set(spInf25);
	}

	public final StringProperty spInf26Property() {
		return this.spInf26;
	}

	public final String getSpInf26() {
		return this.spInf26Property().get();
	}

	public final void setSpInf26(final String spInf26) {
		if (this.spInf26Property() == null)
			this.spInf26 = new SimpleStringProperty();

		this.spInf26Property().set(spInf26);
	}

	public final StringProperty spInf27Property() {
		return this.spInf27;
	}

	public final String getSpInf27() {
		return this.spInf27Property().get();
	}

	public final void setSpInf27(final String spInf27) {
		if (this.spInf27Property() == null)
			this.spInf27 = new SimpleStringProperty();

		this.spInf27Property().set(spInf27);
	}

	public final StringProperty spInf28Property() {
		return this.spInf28;
	}

	public final String getSpInf28() {
		return this.spInf28Property().get();
	}

	public final void setSpInf28(final String spInf28) {
		if (this.spInf28Property() == null)
			this.spInf28 = new SimpleStringProperty();

		this.spInf28Property().set(spInf28);
	}

	public final StringProperty spInf29Property() {
		return this.spInf29;
	}

	public final String getSpInf29() {
		return this.spInf29Property().get();
	}

	public final void setSpInf29(final String spInf29) {
		if (this.spInf29Property() == null)
			this.spInf29 = new SimpleStringProperty();

		this.spInf29Property().set(spInf29);
	}

	public final StringProperty spInf30Property() {
		return this.spInf30;
	}

	public final String getSpInf30() {
		return this.spInf30Property().get();
	}

	public final void setSpInf30(final String spInf30) {
		if (this.spInf30Property() == null)
			this.spInf30 = new SimpleStringProperty();

		this.spInf30Property().set(spInf30);
	}

	public final IntegerProperty spInf31Property() {
		return this.spInf31;
	}

	public final int getSpInf31() {
		return this.spInf31Property().get();
	}

	public final void setSpInf31(final int spInf31) {
		if (this.spInf31Property() == null)
			this.spInf31 = new SimpleIntegerProperty();

		this.spInf31Property().set(spInf31);
	}
}
