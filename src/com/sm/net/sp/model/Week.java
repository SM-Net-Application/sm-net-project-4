package com.sm.net.sp.model;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;

import org.json.JSONArray;
import org.json.JSONObject;

import com.sm.net.project.Language;
import com.sm.net.sp.settings.Settings;
import com.sm.net.sp.view.home.user.menu.meetings.UserMenuMeetingsEditor;
import com.sm.net.util.Crypt;
import com.sm.net.util.DateUtil;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Week {

	private IntegerProperty week;
	private ObjectProperty<LocalDate> from;
	private ObjectProperty<LocalDate> to;
	private StringProperty key;

	private IntegerProperty spWeekID;
	private IntegerProperty spInf1;
	private IntegerProperty spInf2;
	private IntegerProperty spInf3;
	private IntegerProperty spInf4;
	private StringProperty spInf5;
	private StringProperty spInf6;
	private StringProperty spInf7;
	private StringProperty spInf8;
	private StringProperty spInf9;
	private StringProperty spInf10;
	private IntegerProperty spInf11;
	private StringProperty spInf12;
	private StringProperty spInf13;
	private IntegerProperty spInf14;
	private StringProperty spInf15;
	private StringProperty spInf16;
	private StringProperty spInf17;
	private IntegerProperty spInf18;
	private StringProperty spInf19;
	private StringProperty spInf20;
	private StringProperty spInf21;
	private StringProperty spInf22;
	private IntegerProperty spInf23;
	private StringProperty spInf24;
	private StringProperty spInf25;
	private StringProperty spInf26;
	private IntegerProperty spInf27;
	private IntegerProperty spInf28;

	private ObjectProperty<ObservableList<MinistryPart>> ministryPartList;
	private ObjectProperty<ObservableList<ChristiansPart>> christiansPartList;

	private ObjectProperty<WeekTypeTranslated> weekTypeTranslated;

	private WeekOverseer weekOverseer;

	public Week(LocalDate day, Language language) {
		super();

		week = new SimpleIntegerProperty(DateUtil.getWeekOfYears(day));
		from = new SimpleObjectProperty<LocalDate>(DateUtil.getFirstDayOfWeek(day));
		to = new SimpleObjectProperty<LocalDate>(DateUtil.getLastDayOfWeek(day));
		key = new SimpleStringProperty(Week.buildKey(day));

		weekTypeTranslated = new SimpleObjectProperty<WeekTypeTranslated>(
				new WeekTypeTranslated(WeekType.EMPTY, language));
	}

	public Week(JSONObject jsonObject, Language language, Settings settings, ObservableList<Member> membersList) {

		this.spWeekID = new SimpleIntegerProperty(jsonObject.getInt("spWeekID"));
		this.spInf1 = new SimpleIntegerProperty(jsonObject.getInt("spInf1"));
		this.spInf2 = new SimpleIntegerProperty(jsonObject.getInt("spInf2"));

		this.spInf3 = new SimpleIntegerProperty(jsonObject.getInt("spInf3"));
		this.spInf4 = new SimpleIntegerProperty(jsonObject.getInt("spInf4"));
		this.spInf5 = new SimpleStringProperty(jsonObject.getString("spInf5"));
		this.spInf6 = new SimpleStringProperty(jsonObject.getString("spInf6"));
		this.spInf7 = new SimpleStringProperty(jsonObject.getString("spInf7"));
		this.spInf8 = new SimpleStringProperty(jsonObject.getString("spInf8"));
		this.spInf9 = new SimpleStringProperty(jsonObject.getString("spInf9"));
		this.spInf10 = new SimpleStringProperty(jsonObject.getString("spInf10"));
		this.spInf11 = new SimpleIntegerProperty(jsonObject.getInt("spInf11"));
		this.spInf12 = new SimpleStringProperty(jsonObject.getString("spInf12"));
		this.spInf13 = new SimpleStringProperty(jsonObject.getString("spInf13"));
		this.spInf14 = new SimpleIntegerProperty(jsonObject.getInt("spInf14"));
		this.spInf15 = new SimpleStringProperty(jsonObject.getString("spInf15"));
		this.spInf16 = new SimpleStringProperty(jsonObject.getString("spInf16"));
		this.spInf17 = new SimpleStringProperty(jsonObject.getString("spInf17"));
		this.spInf18 = new SimpleIntegerProperty(jsonObject.getInt("spInf18"));
		this.spInf19 = new SimpleStringProperty(jsonObject.getString("spInf19"));
		this.spInf20 = new SimpleStringProperty(jsonObject.getString("spInf20"));
		this.spInf21 = new SimpleStringProperty(jsonObject.getString("spInf21"));
		this.spInf22 = new SimpleStringProperty(jsonObject.getString("spInf22"));
		this.spInf23 = new SimpleIntegerProperty(jsonObject.getInt("spInf23"));
		this.spInf24 = new SimpleStringProperty(jsonObject.getString("spInf24"));
		this.spInf25 = new SimpleStringProperty(jsonObject.getString("spInf25"));
		this.spInf26 = new SimpleStringProperty(jsonObject.getString("spInf26"));
		this.spInf27 = new SimpleIntegerProperty(jsonObject.getInt("spInf27"));
		this.spInf28 = new SimpleIntegerProperty(jsonObject.getInt("spInf28"));

		this.ministryPartList = new SimpleObjectProperty<ObservableList<MinistryPart>>(
				getMinistryPartsList(jsonObject, language, settings, membersList));

		this.christiansPartList = new SimpleObjectProperty<ObservableList<ChristiansPart>>(
				getChristiansPartsList(jsonObject, language, settings, membersList));

		weekTypeTranslated = new SimpleObjectProperty<WeekTypeTranslated>(
				new WeekTypeTranslated(WeekType.EMPTY, language));

		String spWeekOvID = jsonObject.getString("spWeekOvID");
		if (!spWeekOvID.isEmpty())
			weekOverseer = new WeekOverseer(jsonObject, language, settings, true);
	}

	public Week(Week editorMeetingSelectedWeek) {
		this.spInf1 = new SimpleIntegerProperty(editorMeetingSelectedWeek.getSpInf1());
	}

	private ObservableList<MinistryPart> getMinistryPartsList(JSONObject jsonObject, Language language,
			Settings settings, ObservableList<Member> membersList) {

		ObservableList<MinistryPart> list = FXCollections.observableArrayList();

		JSONArray jsonArray = jsonObject.getJSONArray("spInfMIN");
		for (Object o : jsonArray)
			if (o != null)
				if (o instanceof JSONObject)
					list.add(new MinistryPart((JSONObject) o, language, settings, membersList));

		return list;
	}

	private ObservableList<ChristiansPart> getChristiansPartsList(JSONObject jsonObject, Language language,
			Settings settings, ObservableList<Member> membersList) {

		ObservableList<ChristiansPart> list = FXCollections.observableArrayList();

		JSONArray jsonArray = jsonObject.getJSONArray("spInfCR");
		for (Object o : jsonArray)
			if (o != null)
				if (o instanceof JSONObject)
					list.add(new ChristiansPart((JSONObject) o, language, settings, membersList));

		return list;
	}

	public static String buildKey(LocalDate date) {

		NumberFormat nfYear = new DecimalFormat("0000");
		NumberFormat nfMonth = new DecimalFormat("00");

		return (date != null)
				? String.format("%s%s", nfYear.format(date.getYear()), nfMonth.format(DateUtil.getWeekOfYears(date)))
				: "";
	}

	public void updateOnlineWeekInfo(ObservableList<Week> list, Language language, Settings settings) {

		for (Week week : list)
			if (this.equals(week)) {

				this.setSpWeekID(week.getSpWeekID());
				this.setSpInf1(week.getSpInf1());
				this.setSpInf2(week.getSpInf2());
				this.setSpInf3(week.getSpInf3());
				this.setSpInf4(week.getSpInf4());
				this.setSpInf5(Crypt.decrypt(week.getSpInf5(), settings.getDatabaseSecretKey()));
				this.setSpInf6(Crypt.decrypt(week.getSpInf6(), settings.getDatabaseSecretKey()));
				this.setSpInf7(Crypt.decrypt(week.getSpInf7(), settings.getDatabaseSecretKey()));
				this.setSpInf8(Crypt.decrypt(week.getSpInf8(), settings.getDatabaseSecretKey()));
				this.setSpInf9(Crypt.decrypt(week.getSpInf9(), settings.getDatabaseSecretKey()));
				this.setSpInf10(Crypt.decrypt(week.getSpInf10(), settings.getDatabaseSecretKey()));
				this.setSpInf11(week.getSpInf11());
				this.setSpInf12(Crypt.decrypt(week.getSpInf12(), settings.getDatabaseSecretKey()));
				this.setSpInf13(Crypt.decrypt(week.getSpInf13(), settings.getDatabaseSecretKey()));
				this.setSpInf14(week.getSpInf14());
				this.setSpInf15(Crypt.decrypt(week.getSpInf15(), settings.getDatabaseSecretKey()));
				this.setSpInf16(Crypt.decrypt(week.getSpInf16(), settings.getDatabaseSecretKey()));
				this.setSpInf17(Crypt.decrypt(week.getSpInf17(), settings.getDatabaseSecretKey()));
				this.setSpInf18(week.getSpInf18());
				this.setSpInf19(Crypt.decrypt(week.getSpInf19(), settings.getDatabaseSecretKey()));
				this.setSpInf20(Crypt.decrypt(week.getSpInf20(), settings.getDatabaseSecretKey()));
				this.setSpInf21(Crypt.decrypt(week.getSpInf21(), settings.getDatabaseSecretKey()));
				this.setSpInf22(Crypt.decrypt(week.getSpInf22(), settings.getDatabaseSecretKey()));
				this.setSpInf23(week.getSpInf23());
				this.setSpInf24(Crypt.decrypt(week.getSpInf24(), settings.getDatabaseSecretKey()));
				this.setSpInf25(Crypt.decrypt(week.getSpInf25(), settings.getDatabaseSecretKey()));
				this.setSpInf26(Crypt.decrypt(week.getSpInf26(), settings.getDatabaseSecretKey()));
				this.setSpInf27(week.getSpInf27());
				this.setSpInf28(week.getSpInf28());

				this.weekTypeTranslated = new SimpleObjectProperty<WeekTypeTranslated>(
						new WeekTypeTranslated(WeekType.getFromOrdinal(week.getSpInf2()), language));

				this.setMinistryPartList(week.getMinistryPartList());
				this.setChristiansPartList(week.getChristiansPartList());

				this.weekOverseer = week.getWeekOverseer();

				break;
			}
	}

	public static Week buildMeetingEditorWeek(UserMenuMeetingsEditor editorMeeting) {

		Week editorMeetingSelectedWeek = editorMeeting.getSelectedWeek();
		Week week = null;

		if (editorMeetingSelectedWeek.spInf1Property() != null)
			week = new Week(editorMeetingSelectedWeek);
		else {
			week = new Week(editorMeetingSelectedWeek.getFrom(), editorMeeting.getLanguage());
			week.setSpInf1(Integer.valueOf(week.getKey()));
		}

		week.setSpInf3(editorMeeting.getPresidentComboBox().getSelectionModel().getSelectedItem().getSpMemberID());
		week.setSpInf4(editorMeeting.getPray1ComboBox().getSelectionModel().getSelectedItem().getSpMemberID());
		week.setSpInf11(editorMeeting.getTalkComboBox().getSelectionModel().getSelectedItem().getSpMemberID());
		week.setSpInf14(editorMeeting.getDiggingComboBox().getSelectionModel().getSelectedItem().getSpMemberID());
		week.setSpInf23(editorMeeting.getCongregationBibleStudyComboBox().getSelectionModel().getSelectedItem()
				.getSpMemberID());
		week.setSpInf27(editorMeeting.getPray2ComboBox().getSelectionModel().getSelectedItem().getSpMemberID());

		week.setChristiansPartList(editorMeeting.getChristiansPartList());

		return week;
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

	public final StringProperty spInf16Property() {
		return this.spInf16;
	}

	public final String getSpInf16() {
		return this.spInf16Property().get();
	}

	public final void setSpInf16(final String spInf16) {
		if (this.spInf16Property() == null)
			this.spInf16 = new SimpleStringProperty();

		this.spInf16Property().set(spInf16);
	}

	public final StringProperty spInf17Property() {
		return this.spInf17;
	}

	public final String getSpInf17() {
		return this.spInf17Property().get();
	}

	public final void setSpInf17(final String spInf17) {
		if (this.spInf17Property() == null)
			this.spInf17 = new SimpleStringProperty();

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

	public final StringProperty spInf19Property() {
		return this.spInf19;
	}

	public final String getSpInf19() {
		return this.spInf19Property().get();
	}

	public final void setSpInf19(final String spInf19) {
		if (this.spInf19Property() == null)
			this.spInf19 = new SimpleStringProperty();

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

	public final IntegerProperty spInf23Property() {
		return this.spInf23;
	}

	public final int getSpInf23() {
		return this.spInf23Property().get();
	}

	public final void setSpInf23(final int spInf23) {
		if (this.spInf23Property() == null)
			this.spInf23 = new SimpleIntegerProperty();

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

	public final IntegerProperty spInf27Property() {
		return this.spInf27;
	}

	public final int getSpInf27() {
		return this.spInf27Property().get();
	}

	public final void setSpInf27(final int spInf27) {
		if (this.spInf27Property() == null)
			this.spInf27 = new SimpleIntegerProperty();

		this.spInf27Property().set(spInf27);
	}

	public final ObjectProperty<ObservableList<MinistryPart>> ministryPartListProperty() {
		return this.ministryPartList;
	}

	public final ObservableList<MinistryPart> getMinistryPartList() {
		return this.ministryPartListProperty().get();
	}

	public final void setMinistryPartList(final ObservableList<MinistryPart> ministryPartList) {
		if (this.ministryPartListProperty() == null)
			this.ministryPartList = new SimpleObjectProperty<ObservableList<MinistryPart>>();

		this.ministryPartListProperty().set(ministryPartList);
	}

	public final ObjectProperty<ObservableList<ChristiansPart>> christiansPartListProperty() {
		return this.christiansPartList;
	}

	public final ObservableList<ChristiansPart> getChristiansPartList() {
		return this.christiansPartListProperty().get();
	}

	public final void setChristiansPartList(final ObservableList<ChristiansPart> christiansPartList) {
		if (this.christiansPartListProperty() == null)
			this.christiansPartList = new SimpleObjectProperty<ObservableList<ChristiansPart>>();

		this.christiansPartListProperty().set(christiansPartList);
	}

	public final IntegerProperty spInf28Property() {
		return this.spInf28;
	}

	public final int getSpInf28() {
		return this.spInf28Property().get();
	}

	public final void setSpInf28(final int spInf28) {
		if (this.spInf28Property() == null)
			this.spInf28 = new SimpleIntegerProperty();

		this.spInf28Property().set(spInf28);
	}

	public WeekOverseer getWeekOverseer() {
		return weekOverseer;
	}

	public void setWeekOverseer(WeekOverseer weekOverseer) {
		this.weekOverseer = weekOverseer;
	}
}
