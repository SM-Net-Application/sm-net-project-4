package com.sm.net.sp.model;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;

import javax.crypto.SecretKey;

import org.json.JSONObject;

import com.sm.net.project.Language;
import com.sm.net.sp.view.home.user.menu.audio.AudioEditor;
import com.sm.net.util.DateUtil;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

public class WeekAudio {

	private IntegerProperty week;
	private ObjectProperty<LocalDate> from;
	private ObjectProperty<LocalDate> to;
	private StringProperty key;

	private IntegerProperty spAudioID;
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

	private StringProperty posMidweek;
	private StringProperty micMidweek;
	private StringProperty posWeekend;
	private StringProperty micWeekend;

	public WeekAudio(LocalDate day, Language language) {
		super();

		this.week = new SimpleIntegerProperty(DateUtil.getWeekOfYears(day));
		this.from = new SimpleObjectProperty<LocalDate>(DateUtil.getFirstDayOfWeek(day));
		this.to = new SimpleObjectProperty<LocalDate>(DateUtil.getLastDayOfWeek(day));
		this.key = new SimpleStringProperty(WeekAudio.buildKey(day));
	}

	public WeekAudio(int id, int spInf1, int spInf2, int spInf3, int spInf4, int spInf5, int spInf6, int spInf7,
			int spInf8, int spInf9, int spInf10, int spInf11, int spInf12, int spInf13) {

		this.week = null;
		this.from = null;
		this.to = null;
		this.key = null;

		this.spAudioID = new SimpleIntegerProperty(id);
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

		this.posMidweek = new SimpleStringProperty("");
		this.micMidweek = new SimpleStringProperty("");
		this.posWeekend = new SimpleStringProperty("");
		this.micWeekend = new SimpleStringProperty("");
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

	public static WeekAudio newInstanceByView(AudioEditor editor) {

		Language language = editor.getLanguage();

		WeekAudio selectedWeek = editor.getSelectedWeek();

		int spInf1 = Integer.valueOf(selectedWeek.getKey());
		int spInf2 = editor.getPos1MidweekComboBox().getSelectionModel().getSelectedItem().getSpMemberID();
		int spInf3 = editor.getPos2MidweekComboBox().getSelectionModel().getSelectedItem().getSpMemberID();
		int spInf4 = editor.getPos3MidweekComboBox().getSelectionModel().getSelectedItem().getSpMemberID();
		int spInf5 = editor.getMic1MidweekComboBox().getSelectionModel().getSelectedItem().getSpMemberID();
		int spInf6 = editor.getMic2MidweekComboBox().getSelectionModel().getSelectedItem().getSpMemberID();
		int spInf7 = editor.getMic3MidweekComboBox().getSelectionModel().getSelectedItem().getSpMemberID();
		int spInf8 = editor.getPos1WeekendComboBox().getSelectionModel().getSelectedItem().getSpMemberID();
		int spInf9 = editor.getPos2WeekendComboBox().getSelectionModel().getSelectedItem().getSpMemberID();
		int spInf10 = editor.getPos3WeekendComboBox().getSelectionModel().getSelectedItem().getSpMemberID();
		int spInf11 = editor.getMic1WeekendComboBox().getSelectionModel().getSelectedItem().getSpMemberID();
		int spInf12 = editor.getMic2WeekendComboBox().getSelectionModel().getSelectedItem().getSpMemberID();
		int spInf13 = editor.getMic3WeekendComboBox().getSelectionModel().getSelectedItem().getSpMemberID();

		WeekAudio week = new WeekAudio(selectedWeek.getFrom(), language);

		if (selectedWeek != null)
			if (selectedWeek.spAudioIDProperty() != null)
				week.setAudioID(selectedWeek.getAudioID());

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

		return week;
	}

	public static WeekAudio newInstanceByJSONObject(JSONObject json, SecretKey secretKey) {

		int spAudioID = json.getInt("spAudioID");
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

		return new WeekAudio(spAudioID, spInf1, spInf2, spInf3, spInf4, spInf5, spInf6, spInf7, spInf8, spInf9, spInf10,
				spInf11, spInf12, spInf13);
	}

	public void updateInfo(WeekAudio w) {

		this.setAudioID(w.getAudioID());
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

		this.setPosMidweek(w.getPosMidweek());
		this.setMicMidweek(w.getMicMidweek());
		this.setPosWeekend(w.getPosWeekend());
		this.setMicWeekend(w.getMicWeekend());
	}

	public void deleteInfo() {

		this.spAudioID = null;
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

		this.posMidweek = null;
		this.micMidweek = null;
		this.posWeekend = null;
		this.micWeekend = null;
	}

	public static WeekAudio buildEditorWeek(AudioEditor editor) {

		WeekAudio editorSelectedWeek = editor.getSelectedWeek();
		WeekAudio week = null;

		if (editorSelectedWeek.spInf1Property() != null)
			week = new WeekAudio(editorSelectedWeek);
		else {
			week = new WeekAudio(editorSelectedWeek.getFrom(), editor.getLanguage());
			week.setSpInf1(Integer.valueOf(week.getKey()));
		}

		week.setSpInf2(editor.getPos1MidweekComboBox().getSelectionModel().getSelectedItem().getSpMemberID());
		week.setSpInf3(editor.getPos2MidweekComboBox().getSelectionModel().getSelectedItem().getSpMemberID());
		week.setSpInf4(editor.getPos3MidweekComboBox().getSelectionModel().getSelectedItem().getSpMemberID());

		week.setSpInf5(editor.getMic1MidweekComboBox().getSelectionModel().getSelectedItem().getSpMemberID());
		week.setSpInf6(editor.getMic2MidweekComboBox().getSelectionModel().getSelectedItem().getSpMemberID());
		week.setSpInf7(editor.getMic3MidweekComboBox().getSelectionModel().getSelectedItem().getSpMemberID());

		week.setSpInf8(editor.getPos1WeekendComboBox().getSelectionModel().getSelectedItem().getSpMemberID());
		week.setSpInf9(editor.getPos2WeekendComboBox().getSelectionModel().getSelectedItem().getSpMemberID());
		week.setSpInf10(editor.getPos3WeekendComboBox().getSelectionModel().getSelectedItem().getSpMemberID());

		week.setSpInf11(editor.getMic1WeekendComboBox().getSelectionModel().getSelectedItem().getSpMemberID());
		week.setSpInf12(editor.getMic1WeekendComboBox().getSelectionModel().getSelectedItem().getSpMemberID());
		week.setSpInf13(editor.getMic1WeekendComboBox().getSelectionModel().getSelectedItem().getSpMemberID());

		return week;
	}

	public WeekAudio(WeekAudio editorSelectedWeek) {
		this.spInf1 = new SimpleIntegerProperty(editorSelectedWeek.getSpInf1());
	}

	public String nameListPosMidweek(ObservableList<Member> members, boolean extendedName) {

		return nameList(members, extendedName, this.spInf2, this.spInf3, this.spInf4);
	}

	public String nameListMicMidweek(ObservableList<Member> members, boolean extendedName) {

		return nameList(members, extendedName, this.spInf5, this.spInf6, this.spInf7);
	}

	public String nameListPosWeekend(ObservableList<Member> members, boolean extendedName) {

		return nameList(members, extendedName, this.spInf8, this.spInf9, this.spInf10);
	}

	public String nameListMicWeekend(ObservableList<Member> members, boolean extendedName) {

		return nameList(members, extendedName, this.spInf11, this.spInf12, this.spInf13);
	}

	private String nameList(ObservableList<Member> members, boolean extendedName, IntegerProperty int1,
			IntegerProperty int2, IntegerProperty int3) {

		if (this.spAudioID == null)
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
			if (obj instanceof WeekAudio) {
				WeekAudio onlineWeek = (WeekAudio) obj;

				if (onlineWeek.spAudioIDProperty() != null)
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

		this.setPosMidweek(nameListPosMidweek(membersList, false));
		this.setMicMidweek(nameListMicMidweek(membersList, false));
		this.setPosWeekend(nameListPosWeekend(membersList, false));
		this.setMicWeekend(nameListMicWeekend(membersList, false));
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

	public final IntegerProperty spAudioIDProperty() {
		return this.spAudioID;
	}

	public final int getAudioID() {
		return this.spAudioID.get();
	}

	public final void setAudioID(final int audioID) {
		if (this.spAudioIDProperty() == null)
			this.spAudioID = new SimpleIntegerProperty();

		this.spAudioIDProperty().set(audioID);
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

	public final StringProperty posMidweekProperty() {
		return this.posMidweek;
	}

	public final String getPosMidweek() {
		return this.posMidweekProperty().get();
	}

	public final void setPosMidweek(final String posMidweek) {
		if (this.posMidweekProperty() == null)
			this.posMidweek = new SimpleStringProperty();

		this.posMidweekProperty().set(posMidweek);
	}

	public final StringProperty micMidweekProperty() {
		return this.micMidweek;
	}

	public final String getMicMidweek() {
		return this.micMidweekProperty().get();
	}

	public final void setMicMidweek(final String micMidweek) {
		if (this.micMidweekProperty() == null)
			this.micMidweek = new SimpleStringProperty();

		this.micMidweekProperty().set(micMidweek);
	}

	public final StringProperty posWeekendProperty() {
		return this.posWeekend;
	}

	public final String getPosWeekend() {
		return this.posWeekendProperty().get();
	}

	public final void setPosWeekend(final String posWeekend) {
		if (this.posWeekendProperty() == null)
			this.posWeekend = new SimpleStringProperty();

		this.posWeekendProperty().set(posWeekend);
	}

	public final StringProperty micWeekendProperty() {
		return this.micWeekend;
	}

	public final String getMicWeekend() {
		return this.micWeekendProperty().get();
	}

	public final void setMicWeekend(final String micWeekend) {
		if (this.micWeekendProperty() == null)
			this.micWeekend = new SimpleStringProperty();

		this.micWeekendProperty().set(micWeekend);
	}
}
