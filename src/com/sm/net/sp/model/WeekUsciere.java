package com.sm.net.sp.model;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;

import javax.crypto.SecretKey;

import org.json.JSONObject;

import com.sm.net.project.Language;
import com.sm.net.sp.view.home.user.menu.usciere.UsciereEditor;
import com.sm.net.util.DateUtil;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

public class WeekUsciere {

	private IntegerProperty week;
	private ObjectProperty<LocalDate> from;
	private ObjectProperty<LocalDate> to;
	private StringProperty key;

	private IntegerProperty spUscID;
	private IntegerProperty spInf1;
	private IntegerProperty spInf2;
	private IntegerProperty spInf3;
	private IntegerProperty spInf4;
	private IntegerProperty spInf5;
	private IntegerProperty spInf6;
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

	private StringProperty z1Midweek;
	private StringProperty z2Midweek;
	private StringProperty z3Midweek;
	private StringProperty z1Weekend;
	private StringProperty z2Weekend;
	private StringProperty z3Weekend;

	public WeekUsciere(LocalDate day, Language language) {
		super();

		this.week = new SimpleIntegerProperty(DateUtil.getWeekOfYears(day));
		this.from = new SimpleObjectProperty<LocalDate>(DateUtil.getFirstDayOfWeek(day));
		this.to = new SimpleObjectProperty<LocalDate>(DateUtil.getLastDayOfWeek(day));
		this.key = new SimpleStringProperty(WeekUsciere.buildKey(day));
	}

	public WeekUsciere(int id, int spInf1, int spInf2, int spInf3, int spInf4, int spInf5, int spInf6, int spInf7,
			int spInf8, int spInf9, int spInf10, int spInf11, int spInf12, int spInf13, int spInf14, int spInf15,
			int spInf16, int spInf17, int spInf18, int spInf19) {

		this.week = null;
		this.from = null;
		this.to = null;
		this.key = null;

		this.spUscID = new SimpleIntegerProperty(id);
		this.spInf1 = new SimpleIntegerProperty(spInf1);
		this.spInf2 = new SimpleIntegerProperty(spInf2);
		this.spInf3 = new SimpleIntegerProperty(spInf3);
		this.spInf4 = new SimpleIntegerProperty(spInf4);
		this.spInf5 = new SimpleIntegerProperty(spInf5);
		this.spInf6 = new SimpleIntegerProperty(spInf6);
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

		this.z1Midweek = new SimpleStringProperty("");
		this.z2Midweek = new SimpleStringProperty("");
		this.z3Midweek = new SimpleStringProperty("");
		this.z1Weekend = new SimpleStringProperty("");
		this.z2Weekend = new SimpleStringProperty("");
		this.z3Weekend = new SimpleStringProperty("");
	}

	public static String buildKey(LocalDate date) {

		NumberFormat nfYear = new DecimalFormat("0000");
		NumberFormat nfMonth = new DecimalFormat("00");

		int year = date.getYear();
		int monthValue = date.getMonthValue();
		int dayOfMonth = date.getDayOfMonth();

		if (monthValue == 1)
			if (dayOfMonth < 4)
				year = year - 1;

		String yearFormat = nfYear.format(year);
		int weekOfYears = DateUtil.getWeekOfYears(date);
		String weekOfYearsFormat = nfMonth.format(weekOfYears);

		return (date != null) ? String.format("%s%s", yearFormat, weekOfYearsFormat) : "";
	}

	public static WeekUsciere newInstanceByView(UsciereEditor editor) {

		Language language = editor.getLanguage();

		WeekUsciere selectedWeek = editor.getSelectedWeek();

		// TODO

		int spInf1 = Integer.valueOf(selectedWeek.getKey());
		int spInf2 = editor.getZ1u1MidweekComboBox().getSelectionModel().getSelectedItem().getSpMemberID();
		int spInf3 = editor.getZ1u2MidweekComboBox().getSelectionModel().getSelectedItem().getSpMemberID();
		int spInf4 = editor.getZ1u3MidweekComboBox().getSelectionModel().getSelectedItem().getSpMemberID();
		int spInf5 = editor.getZ2u1MidweekComboBox().getSelectionModel().getSelectedItem().getSpMemberID();
		int spInf6 = editor.getZ2u2MidweekComboBox().getSelectionModel().getSelectedItem().getSpMemberID();
		int spInf7 = editor.getZ2u3MidweekComboBox().getSelectionModel().getSelectedItem().getSpMemberID();
		int spInf8 = editor.getZ3u1MidweekComboBox().getSelectionModel().getSelectedItem().getSpMemberID();
		int spInf9 = editor.getZ3u2MidweekComboBox().getSelectionModel().getSelectedItem().getSpMemberID();
		int spInf10 = editor.getZ3u3MidweekComboBox().getSelectionModel().getSelectedItem().getSpMemberID();
		int spInf11 = editor.getZ1u1WeekendComboBox().getSelectionModel().getSelectedItem().getSpMemberID();
		int spInf12 = editor.getZ1u2WeekendComboBox().getSelectionModel().getSelectedItem().getSpMemberID();
		int spInf13 = editor.getZ1u3WeekendComboBox().getSelectionModel().getSelectedItem().getSpMemberID();
		int spInf14 = editor.getZ2u1WeekendComboBox().getSelectionModel().getSelectedItem().getSpMemberID();
		int spInf15 = editor.getZ2u2WeekendComboBox().getSelectionModel().getSelectedItem().getSpMemberID();
		int spInf16 = editor.getZ2u3WeekendComboBox().getSelectionModel().getSelectedItem().getSpMemberID();
		int spInf17 = editor.getZ3u1WeekendComboBox().getSelectionModel().getSelectedItem().getSpMemberID();
		int spInf18 = editor.getZ3u2WeekendComboBox().getSelectionModel().getSelectedItem().getSpMemberID();
		int spInf19 = editor.getZ3u3WeekendComboBox().getSelectionModel().getSelectedItem().getSpMemberID();

		WeekUsciere week = new WeekUsciere(selectedWeek.getFrom(), language);

		if (selectedWeek != null)
			if (selectedWeek.spUscIDProperty() != null)
				week.setUscID(selectedWeek.getUscID());

		week.setSpInf1(spInf1);
		week.setSpInf2(spInf2);
		week.setSpInf3(spInf3);
		week.setSpInf4(spInf4);
		week.setSpInf5(spInf5);
		week.setSpInf6(spInf6);
		week.setSpInf7(spInf7);
		week.setSpInf8(spInf8);
		week.setSpInf9(spInf9);
		week.setSpInf10(spInf10);
		week.setSpInf11(spInf11);
		week.setSpInf12(spInf12);
		week.setSpInf13(spInf13);
		week.setSpInf14(spInf14);
		week.setSpInf15(spInf15);
		week.setSpInf16(spInf16);
		week.setSpInf17(spInf17);
		week.setSpInf18(spInf18);
		week.setSpInf19(spInf19);

		return week;
	}

	public static WeekUsciere newInstanceByJSONObject(JSONObject json, SecretKey secretKey) {

		int spUscID = json.getInt("spUscID");
		int spInf1 = json.getInt("spInf1");
		int spInf2 = json.getInt("spInf2");
		int spInf3 = json.getInt("spInf3");
		int spInf4 = json.getInt("spInf4");
		int spInf5 = json.getInt("spInf5");
		int spInf6 = json.getInt("spInf6");
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

		return new WeekUsciere(spUscID, spInf1, spInf2, spInf3, spInf4, spInf5, spInf6, spInf7, spInf8, spInf9, spInf10,
				spInf11, spInf12, spInf13, spInf14, spInf15, spInf16, spInf17, spInf18, spInf19);
	}

	public void updateInfo(WeekUsciere w) {

		this.setUscID(w.getUscID());
		this.setSpInf1(w.getSpInf1());
		this.setSpInf2(w.getSpInf2());
		this.setSpInf3(w.getSpInf3());
		this.setSpInf4(w.getSpInf4());
		this.setSpInf5(w.getSpInf5());
		this.setSpInf6(w.getSpInf6());
		this.setSpInf7(w.getSpInf7());
		this.setSpInf8(w.getSpInf8());
		this.setSpInf9(w.getSpInf9());
		this.setSpInf10(w.getSpInf10());
		this.setSpInf11(w.getSpInf11());
		this.setSpInf12(w.getSpInf12());
		this.setSpInf13(w.getSpInf13());
		this.setSpInf14(w.getSpInf14());
		this.setSpInf15(w.getSpInf15());
		this.setSpInf16(w.getSpInf16());
		this.setSpInf17(w.getSpInf17());
		this.setSpInf18(w.getSpInf18());
		this.setSpInf19(w.getSpInf19());

		this.setZ1Midweek(w.getZ1Midweek());
		this.setZ2Midweek(w.getZ2Midweek());
		this.setZ3Midweek(w.getZ3Midweek());
		this.setZ1Weekend(w.getZ1Weekend());
		this.setZ2Weekend(w.getZ2Weekend());
		this.setZ3Weekend(w.getZ3Weekend());
	}

	public void deleteInfo() {

		this.spUscID = null;
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

		this.z1Midweek = null;
		this.z2Midweek = null;
		this.z3Midweek = null;
		this.z1Weekend = null;
		this.z2Weekend = null;
		this.z3Weekend = null;
	}

	public static WeekUsciere buildEditorWeek(UsciereEditor editor) {

		WeekUsciere editorSelectedWeek = editor.getSelectedWeek();
		WeekUsciere week = null;

		if (editorSelectedWeek.spInf1Property() != null)
			week = new WeekUsciere(editorSelectedWeek);
		else {
			week = new WeekUsciere(editorSelectedWeek.getFrom(), editor.getLanguage());
			week.setSpInf1(Integer.valueOf(week.getKey()));
		}

		week.setSpInf2(editor.getZ1u1MidweekComboBox().getSelectionModel().getSelectedItem().getSpMemberID());
		week.setSpInf3(editor.getZ1u2MidweekComboBox().getSelectionModel().getSelectedItem().getSpMemberID());
		week.setSpInf4(editor.getZ1u3MidweekComboBox().getSelectionModel().getSelectedItem().getSpMemberID());

		week.setSpInf5(editor.getZ2u1MidweekComboBox().getSelectionModel().getSelectedItem().getSpMemberID());
		week.setSpInf6(editor.getZ2u2MidweekComboBox().getSelectionModel().getSelectedItem().getSpMemberID());
		week.setSpInf7(editor.getZ2u3MidweekComboBox().getSelectionModel().getSelectedItem().getSpMemberID());

		week.setSpInf8(editor.getZ3u1MidweekComboBox().getSelectionModel().getSelectedItem().getSpMemberID());
		week.setSpInf9(editor.getZ3u2MidweekComboBox().getSelectionModel().getSelectedItem().getSpMemberID());
		week.setSpInf10(editor.getZ3u3MidweekComboBox().getSelectionModel().getSelectedItem().getSpMemberID());

		week.setSpInf11(editor.getZ1u1WeekendComboBox().getSelectionModel().getSelectedItem().getSpMemberID());
		week.setSpInf12(editor.getZ1u2WeekendComboBox().getSelectionModel().getSelectedItem().getSpMemberID());
		week.setSpInf13(editor.getZ1u3WeekendComboBox().getSelectionModel().getSelectedItem().getSpMemberID());

		week.setSpInf14(editor.getZ2u1WeekendComboBox().getSelectionModel().getSelectedItem().getSpMemberID());
		week.setSpInf15(editor.getZ2u2WeekendComboBox().getSelectionModel().getSelectedItem().getSpMemberID());
		week.setSpInf16(editor.getZ2u3WeekendComboBox().getSelectionModel().getSelectedItem().getSpMemberID());

		week.setSpInf17(editor.getZ3u1WeekendComboBox().getSelectionModel().getSelectedItem().getSpMemberID());
		week.setSpInf18(editor.getZ3u2WeekendComboBox().getSelectionModel().getSelectedItem().getSpMemberID());
		week.setSpInf19(editor.getZ3u3WeekendComboBox().getSelectionModel().getSelectedItem().getSpMemberID());

		return week;
	}

	public WeekUsciere(WeekUsciere editorSelectedWeek) {
		this.spInf1 = new SimpleIntegerProperty(editorSelectedWeek.getSpInf1());
	}

	public String nameListZ1Midweek(ObservableList<Member> members, boolean extendedName) {
		return nameList(members, extendedName, this.spInf2, this.spInf3, this.spInf4);
	}

	public String nameListZ2Midweek(ObservableList<Member> members, boolean extendedName) {
		return nameList(members, extendedName, this.spInf5, this.spInf6, this.spInf7);
	}

	public String nameListZ3Midweek(ObservableList<Member> members, boolean extendedName) {
		return nameList(members, extendedName, this.spInf8, this.spInf9, this.spInf10);
	}

	public String nameListZ1Weekend(ObservableList<Member> members, boolean extendedName) {
		return nameList(members, extendedName, this.spInf11, this.spInf12, this.spInf13);
	}

	public String nameListZ2Weekend(ObservableList<Member> members, boolean extendedName) {
		return nameList(members, extendedName, this.spInf14, this.spInf15, this.spInf16);
	}

	public String nameListZ3Weekend(ObservableList<Member> members, boolean extendedName) {
		return nameList(members, extendedName, this.spInf17, this.spInf18, this.spInf19);
	}

	private String nameList(ObservableList<Member> members, boolean extendedName, IntegerProperty int1,
			IntegerProperty int2, IntegerProperty int3) {

		if (this.spUscID == null)
			return "";

		int id1 = int1.get();
		int id2 = int2.get();
		int id3 = int3.get();

		String text = "";

		String name1 = memberName(members, extendedName, id1);
		String name2 = memberName(members, extendedName, id2);
		String name3 = memberName(members, extendedName, id3);

		if (!name1.isEmpty())
			text += name1;

		if (!name2.isEmpty()) {
			if (!text.isEmpty())
				text += ", ";
			text += name2;
		}

		if (!name3.isEmpty()) {
			if (!text.isEmpty())
				text += ", ";
			text += name3;
		}

		return text.trim();
	}

	private String memberName(ObservableList<Member> members, boolean extendedName, int id) {

		for (Member m : members)
			if (m.getSpMemberID() == id)
				return extendedName ? m.getNameStyle3() : m.getNameStyle4();

		return "";
	}

	@Override
	public boolean equals(Object obj) {

		if (obj != null)
			if (obj instanceof WeekUsciere) {
				WeekUsciere onlineWeek = (WeekUsciere) obj;

				if (onlineWeek.spUscIDProperty() != null)
					if (onlineWeek.spInf1Property() != null) {
						String onlineWeekKey = String.valueOf(onlineWeek.getSpInf1());
						if (onlineWeekKey != null)
							if (!onlineWeekKey.isEmpty()) {
								if (getKey().equals(onlineWeekKey))
									return true;
							}
					}
			}
		return false;
	}

	public void updateText(ObservableList<Member> membersList) {

		this.setZ1Midweek(nameListZ1Midweek(membersList, false));
		this.setZ2Midweek(nameListZ2Midweek(membersList, false));
		this.setZ3Midweek(nameListZ3Midweek(membersList, false));

		this.setZ1Weekend(nameListZ1Weekend(membersList, false));
		this.setZ2Weekend(nameListZ2Weekend(membersList, false));
		this.setZ3Weekend(nameListZ3Weekend(membersList, false));
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

	public final IntegerProperty spUscIDProperty() {
		return this.spUscID;
	}

	public final int getUscID() {
		return this.spUscID.get();
	}

	public final void setUscID(final int uscID) {
		if (this.spUscIDProperty() == null)
			this.spUscID = new SimpleIntegerProperty();

		this.spUscIDProperty().set(uscID);
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

	public final IntegerProperty spInf3Property() {
		return this.spInf3;
	}

	public final int getSpInf3() {
		return this.spInf3Property().get();
	}

	public final void setSpInf3(final int spInf3) {
		if (this.spInf3Property() == null)
			this.spInf3 = new SimpleIntegerProperty();

		this.spInf3Property().set(spInf3);
	}

	public final IntegerProperty spInf4Property() {
		return this.spInf4;
	}

	public final int getSpInf4() {
		return this.spInf4Property().get();
	}

	public final void setSpInf4(final int spInf4) {
		if (this.spInf4Property() == null)
			this.spInf4 = new SimpleIntegerProperty();

		this.spInf4Property().set(spInf4);
	}

	public final IntegerProperty spInf5Property() {
		return this.spInf5;
	}

	public final int getSpInf5() {
		return this.spInf5Property().get();
	}

	public final void setSpInf5(final int spInf5) {
		if (this.spInf5Property() == null)
			this.spInf5 = new SimpleIntegerProperty();

		this.spInf5Property().set(spInf5);
	}

	public final IntegerProperty spInf6Property() {
		return this.spInf6;
	}

	public final int getSpInf6() {
		return this.spInf6Property().get();
	}

	public final void setSpInf6(final int spInf6) {
		if (this.spInf6Property() == null)
			this.spInf6 = new SimpleIntegerProperty();

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

	public final StringProperty z1MidweekProperty() {
		return this.z1Midweek;
	}

	public final String getZ1Midweek() {
		return this.z1MidweekProperty().get();
	}

	public final void setZ1Midweek(final String z1Midweek) {
		if (this.z1MidweekProperty() == null)
			this.z1Midweek = new SimpleStringProperty();

		this.z1MidweekProperty().set(z1Midweek);
	}

	public final StringProperty z2MidweekProperty() {
		return this.z2Midweek;
	}

	public final String getZ2Midweek() {
		return this.z2MidweekProperty().get();
	}

	public final void setZ2Midweek(final String z2Midweek) {
		if (this.z2MidweekProperty() == null)
			this.z2Midweek = new SimpleStringProperty();

		this.z2MidweekProperty().set(z2Midweek);
	}

	public final StringProperty z3MidweekProperty() {
		return this.z3Midweek;
	}

	public final String getZ3Midweek() {
		return this.z3MidweekProperty().get();
	}

	public final void setZ3Midweek(final String z3Midweek) {
		if (this.z3MidweekProperty() == null)
			this.z3Midweek = new SimpleStringProperty();

		this.z3MidweekProperty().set(z3Midweek);
	}

	public final StringProperty z1WeekendProperty() {
		return this.z1Weekend;
	}

	public final String getZ1Weekend() {
		return this.z1WeekendProperty().get();
	}

	public final void setZ1Weekend(final String z1Weekend) {
		if (this.z1WeekendProperty() == null)
			this.z1Weekend = new SimpleStringProperty();

		this.z1WeekendProperty().set(z1Weekend);
	}

	public final StringProperty z2WeekendProperty() {
		return this.z2Weekend;
	}

	public final String getZ2Weekend() {
		return this.z2WeekendProperty().get();
	}

	public final void setZ2Weekend(final String z2Weekend) {
		if (this.z2WeekendProperty() == null)
			this.z2Weekend = new SimpleStringProperty();

		this.z2WeekendProperty().set(z2Weekend);
	}

	public final StringProperty z3WeekendProperty() {
		return this.z3Weekend;
	}

	public final String getZ3Weekend() {
		return this.z3WeekendProperty().get();
	}

	public final void setZ3Weekend(final String z3Weekend) {
		if (this.z3WeekendProperty() == null)
			this.z3Weekend = new SimpleStringProperty();

		this.z3WeekendProperty().set(z3Weekend);
	}
}
