package com.sm.net.sp.model;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;

import org.json.JSONArray;
import org.json.JSONObject;

import com.sm.net.project.Language;
import com.sm.net.sp.settings.Settings;
import com.sm.net.sp.view.home.user.menu.meetings.UserMenuMeetingsEditor;
import com.sm.net.sp.view.home.user.menu.publicmeetings.UserMenuPublicMeetingsEditor;
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

	private IntegerProperty spInf29;
	private IntegerProperty spInf30;
	private StringProperty spInf31;
	private StringProperty spInf32;
	private StringProperty spInf33;
	private StringProperty spInf34;
	private StringProperty spInf35;
	private StringProperty spInf36;
	private IntegerProperty spInf37;
	private IntegerProperty spInf38;
	private StringProperty spInf39;
	private IntegerProperty spInf40;

	private IntegerProperty spInf41;
	private StringProperty spInf42;
	private StringProperty spInf43;
	private IntegerProperty spInf44;
	private IntegerProperty spInf45;
	private IntegerProperty spInf46;
	private IntegerProperty spInf47;
	private IntegerProperty spInf48;
	private IntegerProperty spInf49;
	private StringProperty spInf50;
	private IntegerProperty spInf51;
	private StringProperty spInf52;
	private IntegerProperty spInf53;

	private StringProperty spInf54;
	private StringProperty spInf55;
	private IntegerProperty spInf56;
	private IntegerProperty spInf57;
	private IntegerProperty spInf58;

	private StringProperty spInf59;
	private StringProperty spInf60;
	private StringProperty spInf61;
	private StringProperty spInf62;
	private StringProperty spInf63;
	private StringProperty spInf64;
	private IntegerProperty spInf65;
	private IntegerProperty spInf66;

	private ObjectProperty<ObservableList<MinistryPart>> ministryPartList;
	private ObjectProperty<ObservableList<ChristiansPart>> christiansPartList;

	private ObjectProperty<WeekTypeTranslated> weekTypeTranslated;

	private WeekOverseer weekOverseer;

	public Week() {
	}

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

		this.spInf29 = new SimpleIntegerProperty(jsonObject.getInt("spInf29"));
		this.spInf30 = new SimpleIntegerProperty(jsonObject.getInt("spInf30"));
		this.spInf31 = new SimpleStringProperty(jsonObject.getString("spInf31"));
		this.spInf32 = new SimpleStringProperty(jsonObject.getString("spInf32"));
		this.spInf33 = new SimpleStringProperty(jsonObject.getString("spInf33"));
		this.spInf34 = new SimpleStringProperty(jsonObject.getString("spInf34"));
		this.spInf35 = new SimpleStringProperty(jsonObject.getString("spInf35"));
		this.spInf36 = new SimpleStringProperty(jsonObject.getString("spInf36"));
		this.spInf37 = new SimpleIntegerProperty(jsonObject.getInt("spInf37"));
		this.spInf38 = new SimpleIntegerProperty(jsonObject.getInt("spInf38"));
		this.spInf39 = new SimpleStringProperty(jsonObject.getString("spInf39"));
		this.spInf40 = new SimpleIntegerProperty(jsonObject.getInt("spInf40"));

		this.spInf41 = new SimpleIntegerProperty(jsonObject.getInt("spInf41"));
		this.spInf42 = new SimpleStringProperty(jsonObject.getString("spInf42"));
		this.spInf43 = new SimpleStringProperty(jsonObject.getString("spInf43"));
		this.spInf44 = new SimpleIntegerProperty(jsonObject.getInt("spInf44"));
		this.spInf45 = new SimpleIntegerProperty(jsonObject.getInt("spInf45"));
		this.spInf46 = new SimpleIntegerProperty(jsonObject.getInt("spInf46"));
		this.spInf47 = new SimpleIntegerProperty(jsonObject.getInt("spInf47"));
		this.spInf48 = new SimpleIntegerProperty(jsonObject.getInt("spInf48"));
		this.spInf49 = new SimpleIntegerProperty(jsonObject.getInt("spInf49"));
		this.spInf50 = new SimpleStringProperty(jsonObject.getString("spInf50"));
		this.spInf51 = new SimpleIntegerProperty(jsonObject.getInt("spInf51"));
		this.spInf52 = new SimpleStringProperty(jsonObject.getString("spInf52"));
		this.spInf53 = new SimpleIntegerProperty(jsonObject.getInt("spInf53"));

		this.spInf54 = new SimpleStringProperty(jsonObject.getString("spInf54"));
		this.spInf55 = new SimpleStringProperty(jsonObject.getString("spInf55"));
		this.spInf56 = new SimpleIntegerProperty(jsonObject.getInt("spInf56"));
		this.spInf57 = new SimpleIntegerProperty(jsonObject.getInt("spInf57"));
		this.spInf58 = new SimpleIntegerProperty(jsonObject.getInt("spInf58"));

		this.spInf59 = new SimpleStringProperty(jsonObject.getString("spInf59"));
		this.spInf60 = new SimpleStringProperty(jsonObject.getString("spInf60"));
		this.spInf61 = new SimpleStringProperty(jsonObject.getString("spInf61"));
		this.spInf62 = new SimpleStringProperty(jsonObject.getString("spInf62"));
		this.spInf63 = new SimpleStringProperty(jsonObject.getString("spInf63"));
		this.spInf64 = new SimpleStringProperty(jsonObject.getString("spInf64"));
		this.spInf65 = new SimpleIntegerProperty(jsonObject.getInt("spInf65"));
		this.spInf66 = new SimpleIntegerProperty(jsonObject.getInt("spInf66"));

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

				this.setSpInf29(week.getSpInf29());
				this.setSpInf30(week.getSpInf30());
				this.setSpInf31(Crypt.decrypt(week.getSpInf31(), settings.getDatabaseSecretKey()));
				this.setSpInf32(Crypt.decrypt(week.getSpInf32(), settings.getDatabaseSecretKey()));
				this.setSpInf33(Crypt.decrypt(week.getSpInf33(), settings.getDatabaseSecretKey()));
				this.setSpInf34(Crypt.decrypt(week.getSpInf34(), settings.getDatabaseSecretKey()));
				this.setSpInf35(Crypt.decrypt(week.getSpInf35(), settings.getDatabaseSecretKey()));
				this.setSpInf36(Crypt.decrypt(week.getSpInf36(), settings.getDatabaseSecretKey()));
				this.setSpInf37(week.getSpInf37());
				this.setSpInf38(week.getSpInf38());
				this.setSpInf39(Crypt.decrypt(week.getSpInf39(), settings.getDatabaseSecretKey()));
				this.setSpInf40(week.getSpInf40());

				this.setSpInf41(week.getSpInf41());
				this.setSpInf42(Crypt.decrypt(week.getSpInf42(), settings.getDatabaseSecretKey()));
				this.setSpInf43(Crypt.decrypt(week.getSpInf43(), settings.getDatabaseSecretKey()));
				this.setSpInf44(week.getSpInf44());
				this.setSpInf45(week.getSpInf45());
				this.setSpInf46(week.getSpInf46());
				this.setSpInf47(week.getSpInf47());
				this.setSpInf48(week.getSpInf48());
				this.setSpInf49(week.getSpInf49());
				this.setSpInf50(Crypt.decrypt(week.getSpInf50(), settings.getDatabaseSecretKey()));
				this.setSpInf51(week.getSpInf51());
				this.setSpInf52(Crypt.decrypt(week.getSpInf52(), settings.getDatabaseSecretKey()));
				this.setSpInf53(week.getSpInf53());

				this.setSpInf54(Crypt.decrypt(week.getSpInf54(), settings.getDatabaseSecretKey()));
				this.setSpInf55(Crypt.decrypt(week.getSpInf55(), settings.getDatabaseSecretKey()));
				this.setSpInf56(week.getSpInf56());
				this.setSpInf57(week.getSpInf57());
				this.setSpInf58(week.getSpInf58());

				this.setSpInf59(Crypt.decrypt(week.getSpInf59(), settings.getDatabaseSecretKey()));
				this.setSpInf60(Crypt.decrypt(week.getSpInf60(), settings.getDatabaseSecretKey()));
				this.setSpInf61(Crypt.decrypt(week.getSpInf61(), settings.getDatabaseSecretKey()));
				this.setSpInf62(Crypt.decrypt(week.getSpInf62(), settings.getDatabaseSecretKey()));
				this.setSpInf63(Crypt.decrypt(week.getSpInf63(), settings.getDatabaseSecretKey()));
				this.setSpInf64(Crypt.decrypt(week.getSpInf64(), settings.getDatabaseSecretKey()));
				this.setSpInf65(week.getSpInf65());
				this.setSpInf66(week.getSpInf66());

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

		week.setSpInf29(editorMeeting.getCongregationBibleStudyReaderComboBox().getSelectionModel().getSelectedItem()
				.getSpMemberID());
		week.setSpInf30(editorMeeting.getPresidentPublicMeetingComboBox().getSelectionModel().getSelectedItem()
				.getSpMemberID());
		week.setSpInf37(editorMeeting.getWatchtowerStudyConductorComboBox().getSelectionModel().getSelectedItem()
				.getSpMemberID());
		week.setSpInf38(
				editorMeeting.getWatchtowerStudyReaderComboBox().getSelectionModel().getSelectedItem().getSpMemberID());
		week.setSpInf40(
				editorMeeting.getWatchtowerStudyPray2ComboBox().getSelectionModel().getSelectedItem().getSpMemberID());

		week.setChristiansPartList(editorMeeting.getChristiansPartList());

		return week;
	}

	public static Week buildPublicMeetingEditorWeek(UserMenuPublicMeetingsEditor editorMeeting) {

		Week editorMeetingSelectedWeek = editorMeeting.getSelectedWeek();
		Week week = null;

		if (editorMeetingSelectedWeek.spInf1Property() != null)
			week = new Week(editorMeetingSelectedWeek);
		else {
			week = new Week(editorMeetingSelectedWeek.getFrom(), editorMeeting.getLanguage());
			week.setSpInf1(Integer.valueOf(week.getKey()));
		}

		week.setSpInf3(0);
		week.setSpInf4(0);
		week.setSpInf11(0);
		week.setSpInf14(0);
		week.setSpInf23(0);
		week.setSpInf27(0);
		week.setSpInf29(0);

		week.setSpInf30(editorMeeting.getPresidentPublicMeetingComboBox().getSelectionModel().getSelectedItem()
				.getSpMemberID());

		week.setSpInf37(0);
		week.setSpInf38(0);
		week.setSpInf40(0);

		week.setChristiansPartList(FXCollections.observableArrayList());

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

	public final IntegerProperty spInf29Property() {
		return this.spInf29;
	}

	public final int getSpInf29() {
		return this.spInf29Property().get();
	}

	public final void setSpInf29(final int spInf29) {
		if (this.spInf29Property() == null)
			this.spInf29 = new SimpleIntegerProperty();

		this.spInf29Property().set(spInf29);
	}

	public final IntegerProperty spInf30Property() {
		return this.spInf30;
	}

	public final int getSpInf30() {
		return this.spInf30Property().get();
	}

	public final void setSpInf30(final int spInf30) {
		if (this.spInf30Property() == null)
			this.spInf30 = new SimpleIntegerProperty();

		this.spInf30Property().set(spInf30);
	}

	public final StringProperty spInf31Property() {
		return this.spInf31;
	}

	public final String getSpInf31() {
		return this.spInf31Property().get();
	}

	public final void setSpInf31(final String spInf31) {
		if (this.spInf31Property() == null)
			this.spInf31 = new SimpleStringProperty();

		this.spInf31Property().set(spInf31);
	}

	public final StringProperty spInf32Property() {
		return this.spInf32;
	}

	public final String getSpInf32() {
		return this.spInf32Property().get();
	}

	public final void setSpInf32(final String spInf32) {
		if (this.spInf32Property() == null)
			this.spInf32 = new SimpleStringProperty();

		this.spInf32Property().set(spInf32);
	}

	public final StringProperty spInf33Property() {
		return this.spInf33;
	}

	public final String getSpInf33() {
		return this.spInf33Property().get();
	}

	public final void setSpInf33(final String spInf33) {
		if (this.spInf33Property() == null)
			this.spInf33 = new SimpleStringProperty();

		this.spInf33Property().set(spInf33);
	}

	public final StringProperty spInf34Property() {
		return this.spInf34;
	}

	public final String getSpInf34() {
		return this.spInf34Property().get();
	}

	public final void setSpInf34(final String spInf34) {
		if (this.spInf34Property() == null)
			this.spInf34 = new SimpleStringProperty();

		this.spInf34Property().set(spInf34);
	}

	public final StringProperty spInf35Property() {
		return this.spInf35;
	}

	public final String getSpInf35() {
		return this.spInf35Property().get();
	}

	public final void setSpInf35(final String spInf35) {
		if (this.spInf35Property() == null)
			this.spInf35 = new SimpleStringProperty();

		this.spInf35Property().set(spInf35);
	}

	public final StringProperty spInf36Property() {
		return this.spInf36;
	}

	public final String getSpInf36() {
		return this.spInf36Property().get();
	}

	public final void setSpInf36(final String spInf36) {
		if (this.spInf36Property() == null)
			this.spInf36 = new SimpleStringProperty();

		this.spInf36Property().set(spInf36);
	}

	public final IntegerProperty spInf37Property() {
		return this.spInf37;
	}

	public final int getSpInf37() {
		return this.spInf37Property().get();
	}

	public final void setSpInf37(final int spInf37) {
		if (this.spInf37Property() == null)
			this.spInf37 = new SimpleIntegerProperty();

		this.spInf37Property().set(spInf37);
	}

	public final IntegerProperty spInf38Property() {
		return this.spInf38;
	}

	public final int getSpInf38() {
		return this.spInf38Property().get();
	}

	public final void setSpInf38(final int spInf38) {
		if (this.spInf38Property() == null)
			this.spInf38 = new SimpleIntegerProperty();

		this.spInf38Property().set(spInf38);
	}

	public final StringProperty spInf39Property() {
		return this.spInf39;
	}

	public final String getSpInf39() {
		return this.spInf39Property().get();
	}

	public final void setSpInf39(final String spInf39) {
		if (this.spInf39Property() == null)
			this.spInf39 = new SimpleStringProperty();

		this.spInf39Property().set(spInf39);
	}

	public final IntegerProperty spInf40Property() {
		return this.spInf40;
	}

	public final int getSpInf40() {
		return this.spInf40Property().get();
	}

	public final void setSpInf40(final int spInf40) {
		if (this.spInf40Property() == null)
			this.spInf40 = new SimpleIntegerProperty();

		this.spInf40Property().set(spInf40);
	}

	public final IntegerProperty spInf41Property() {
		return this.spInf41;
	}

	public final int getSpInf41() {
		return this.spInf41Property().get();
	}

	public final void setSpInf41(final int spInf41) {
		if (this.spInf41Property() == null)
			this.spInf41 = new SimpleIntegerProperty();

		this.spInf41Property().set(spInf41);
	}

	public final StringProperty spInf42Property() {
		return this.spInf42;
	}

	public final String getSpInf42() {
		return this.spInf42Property().get();
	}

	public final void setSpInf42(final String spInf42) {
		if (this.spInf42Property() == null)
			this.spInf42 = new SimpleStringProperty();

		this.spInf42Property().set(spInf42);
	}

	public final StringProperty spInf43Property() {
		return this.spInf43;
	}

	public final String getSpInf43() {
		return this.spInf43Property().get();
	}

	public final void setSpInf43(final String spInf43) {
		if (this.spInf43Property() == null)
			this.spInf43 = new SimpleStringProperty();

		this.spInf43Property().set(spInf43);
	}

	public final IntegerProperty spInf44Property() {
		return this.spInf44;
	}

	public final int getSpInf44() {
		return this.spInf44Property().get();
	}

	public final void setSpInf44(final int spInf44) {
		if (this.spInf44Property() == null)
			this.spInf44 = new SimpleIntegerProperty();

		this.spInf44Property().set(spInf44);
	}

	public final IntegerProperty spInf45Property() {
		return this.spInf45;
	}

	public final int getSpInf45() {
		return this.spInf45Property().get();
	}

	public final void setSpInf45(final int spInf45) {
		if (this.spInf45Property() == null)
			this.spInf45 = new SimpleIntegerProperty();

		this.spInf45Property().set(spInf45);
	}

	public final IntegerProperty spInf46Property() {
		return this.spInf46;
	}

	public final int getSpInf46() {
		return this.spInf46Property().get();
	}

	public final void setSpInf46(final int spInf46) {
		if (this.spInf46Property() == null)
			this.spInf46 = new SimpleIntegerProperty();

		this.spInf46Property().set(spInf46);
	}

	public final IntegerProperty spInf47Property() {
		return this.spInf47;
	}

	public final int getSpInf47() {
		return this.spInf47Property().get();
	}

	public final void setSpInf47(final int spInf47) {
		if (this.spInf47Property() == null)
			this.spInf47 = new SimpleIntegerProperty();

		this.spInf47Property().set(spInf47);
	}

	public final IntegerProperty spInf48Property() {
		return this.spInf48;
	}

	public final int getSpInf48() {
		return this.spInf48Property().get();
	}

	public final void setSpInf48(final int spInf48) {
		if (this.spInf48Property() == null)
			this.spInf48 = new SimpleIntegerProperty();

		this.spInf48Property().set(spInf48);
	}

	public final IntegerProperty spInf49Property() {
		return this.spInf49;
	}

	public final int getSpInf49() {
		return this.spInf49Property().get();
	}

	public final void setSpInf49(final int spInf49) {
		if (this.spInf49Property() == null)
			this.spInf49 = new SimpleIntegerProperty();

		this.spInf49Property().set(spInf49);
	}

	public final StringProperty spInf50Property() {
		return this.spInf50;
	}

	public final String getSpInf50() {
		return this.spInf50Property().get();
	}

	public final void setSpInf50(final String spInf50) {
		if (this.spInf50Property() == null)
			this.spInf50 = new SimpleStringProperty();

		this.spInf50Property().set(spInf50);
	}

	public final IntegerProperty spInf51Property() {
		return this.spInf51;
	}

	public final int getSpInf51() {
		return this.spInf51Property().get();
	}

	public final void setSpInf51(final int spInf51) {
		if (this.spInf51Property() == null)
			this.spInf51 = new SimpleIntegerProperty();

		this.spInf51Property().set(spInf51);
	}

	public final StringProperty spInf52Property() {
		return this.spInf52;
	}

	public final String getSpInf52() {
		return this.spInf52Property().get();
	}

	public final void setSpInf52(final String spInf52) {
		if (this.spInf52Property() == null)
			this.spInf52 = new SimpleStringProperty();

		this.spInf52Property().set(spInf52);
	}

	public final IntegerProperty spInf53Property() {
		return this.spInf53;
	}

	public final int getSpInf53() {
		return this.spInf53Property().get();
	}

	public final void setSpInf53(final int spInf53) {
		if (this.spInf53Property() == null)
			this.spInf53 = new SimpleIntegerProperty();

		this.spInf53Property().set(spInf53);
	}

	public WeekOverseer getWeekOverseer() {
		return weekOverseer;
	}

	public void setWeekOverseer(WeekOverseer weekOverseer) {
		this.weekOverseer = weekOverseer;
	}

	public final StringProperty spInf54Property() {
		return this.spInf54;
	}

	public final String getSpInf54() {
		return this.spInf54Property().get();
	}

	public final void setSpInf54(final String spInf54) {
		if (this.spInf54Property() == null)
			this.spInf54 = new SimpleStringProperty();

		this.spInf54Property().set(spInf54);
	}

	public final StringProperty spInf55Property() {
		return this.spInf55;
	}

	public final String getSpInf55() {
		return this.spInf55Property().get();
	}

	public final void setSpInf55(final String spInf55) {
		if (this.spInf55Property() == null)
			this.spInf55 = new SimpleStringProperty();

		this.spInf55Property().set(spInf55);
	}

	public final IntegerProperty spInf56Property() {
		return this.spInf56;
	}

	public final int getSpInf56() {
		return this.spInf56Property().get();
	}

	public final void setSpInf56(final int spInf56) {
		if (this.spInf56Property() == null)
			this.spInf56 = new SimpleIntegerProperty();

		this.spInf56Property().set(spInf56);
	}

	public final IntegerProperty spInf57Property() {
		return this.spInf57;
	}

	public final int getSpInf57() {
		return this.spInf57Property().get();
	}

	public final void setSpInf57(final int spInf57) {
		if (this.spInf57Property() == null)
			this.spInf57 = new SimpleIntegerProperty();

		this.spInf57Property().set(spInf57);
	}

	public final IntegerProperty spInf58Property() {
		return this.spInf58;
	}

	public final int getSpInf58() {
		return this.spInf58Property().get();
	}

	public final void setSpInf58(final int spInf58) {
		if (this.spInf58Property() == null)
			this.spInf58 = new SimpleIntegerProperty();

		this.spInf58Property().set(spInf58);
	}

	public final StringProperty spInf59Property() {
		return this.spInf59;
	}

	public final String getSpInf59() {
		return this.spInf59Property().get();
	}

	public final void setSpInf59(final String spInf59) {
		if (this.spInf59Property() == null)
			this.spInf59 = new SimpleStringProperty();

		this.spInf59Property().set(spInf59);
	}

	public final StringProperty spInf60Property() {
		return this.spInf60;
	}

	public final String getSpInf60() {
		return this.spInf60Property().get();
	}

	public final void setSpInf60(final String spInf60) {
		if (this.spInf60Property() == null)
			this.spInf60 = new SimpleStringProperty();

		this.spInf60Property().set(spInf60);
	}

	public final StringProperty spInf61Property() {
		return this.spInf61;
	}

	public final String getSpInf61() {
		return this.spInf61Property().get();
	}

	public final void setSpInf61(final String spInf61) {
		if (this.spInf61Property() == null)
			this.spInf61 = new SimpleStringProperty();

		this.spInf61Property().set(spInf61);
	}

	public final StringProperty spInf62Property() {
		return this.spInf62;
	}

	public final String getSpInf62() {
		return this.spInf62Property().get();
	}

	public final void setSpInf62(final String spInf62) {
		if (this.spInf62Property() == null)
			this.spInf62 = new SimpleStringProperty();

		this.spInf62Property().set(spInf62);
	}

	public final StringProperty spInf63Property() {
		return this.spInf63;
	}

	public final String getSpInf63() {
		return this.spInf63Property().get();
	}

	public final void setSpInf63(final String spInf63) {
		if (this.spInf63Property() == null)
			this.spInf63 = new SimpleStringProperty();

		this.spInf63Property().set(spInf63);
	}

	public final StringProperty spInf64Property() {
		return this.spInf64;
	}

	public final String getSpInf64() {
		return this.spInf64Property().get();
	}

	public final void setSpInf64(final String spInf64) {
		if (this.spInf64Property() == null)
			this.spInf64 = new SimpleStringProperty();

		this.spInf64Property().set(spInf64);
	}

	public final IntegerProperty spInf65Property() {
		return this.spInf65;
	}

	public final int getSpInf65() {
		return this.spInf65Property().get();
	}

	public final void setSpInf65(final int spInf65) {
		if (this.spInf65Property() == null)
			this.spInf65 = new SimpleIntegerProperty();

		this.spInf65Property().set(spInf65);
	}

	public final IntegerProperty spInf66Property() {
		return this.spInf66;
	}

	public final int getSpInf66() {
		return this.spInf66Property().get();
	}

	public final void setSpInf66(final int spInf66) {
		if (this.spInf66Property() == null)
			this.spInf66 = new SimpleIntegerProperty();

		this.spInf66Property().set(spInf66);
	}

	public static Week newInstance(JSONObject jsonObject) {

		Week week = new Week();

		week.spWeekID = new SimpleIntegerProperty(jsonObject.getInt("spWeekID"));
		week.spInf1 = new SimpleIntegerProperty(jsonObject.getInt("spInf1"));
		week.spInf2 = new SimpleIntegerProperty(jsonObject.getInt("spInf2"));

		week.spInf3 = new SimpleIntegerProperty(jsonObject.getInt("spInf3"));
		week.spInf4 = new SimpleIntegerProperty(jsonObject.getInt("spInf4"));
		week.spInf5 = new SimpleStringProperty(jsonObject.getString("spInf5"));
		week.spInf6 = new SimpleStringProperty(jsonObject.getString("spInf6"));
		week.spInf7 = new SimpleStringProperty(jsonObject.getString("spInf7"));
		week.spInf8 = new SimpleStringProperty(jsonObject.getString("spInf8"));
		week.spInf9 = new SimpleStringProperty(jsonObject.getString("spInf9"));
		week.spInf10 = new SimpleStringProperty(jsonObject.getString("spInf10"));
		week.spInf11 = new SimpleIntegerProperty(jsonObject.getInt("spInf11"));
		week.spInf12 = new SimpleStringProperty(jsonObject.getString("spInf12"));
		week.spInf13 = new SimpleStringProperty(jsonObject.getString("spInf13"));
		week.spInf14 = new SimpleIntegerProperty(jsonObject.getInt("spInf14"));
		week.spInf15 = new SimpleStringProperty(jsonObject.getString("spInf15"));
		week.spInf16 = new SimpleStringProperty(jsonObject.getString("spInf16"));
		week.spInf17 = new SimpleStringProperty(jsonObject.getString("spInf17"));
		week.spInf18 = new SimpleIntegerProperty(jsonObject.getInt("spInf18"));
		week.spInf19 = new SimpleStringProperty(jsonObject.getString("spInf19"));
		week.spInf20 = new SimpleStringProperty(jsonObject.getString("spInf20"));
		week.spInf21 = new SimpleStringProperty(jsonObject.getString("spInf21"));
		week.spInf22 = new SimpleStringProperty(jsonObject.getString("spInf22"));
		week.spInf23 = new SimpleIntegerProperty(jsonObject.getInt("spInf23"));
		week.spInf24 = new SimpleStringProperty(jsonObject.getString("spInf24"));
		week.spInf25 = new SimpleStringProperty(jsonObject.getString("spInf25"));
		week.spInf26 = new SimpleStringProperty(jsonObject.getString("spInf26"));
		week.spInf27 = new SimpleIntegerProperty(jsonObject.getInt("spInf27"));
		week.spInf28 = new SimpleIntegerProperty(jsonObject.getInt("spInf28"));

		week.spInf29 = new SimpleIntegerProperty(jsonObject.getInt("spInf29"));
		week.spInf30 = new SimpleIntegerProperty(jsonObject.getInt("spInf30"));
		week.spInf31 = new SimpleStringProperty(jsonObject.getString("spInf31"));
		week.spInf32 = new SimpleStringProperty(jsonObject.getString("spInf32"));
		week.spInf33 = new SimpleStringProperty(jsonObject.getString("spInf33"));
		week.spInf34 = new SimpleStringProperty(jsonObject.getString("spInf34"));
		week.spInf35 = new SimpleStringProperty(jsonObject.getString("spInf35"));
		week.spInf36 = new SimpleStringProperty(jsonObject.getString("spInf36"));
		week.spInf37 = new SimpleIntegerProperty(jsonObject.getInt("spInf37"));
		week.spInf38 = new SimpleIntegerProperty(jsonObject.getInt("spInf38"));
		week.spInf39 = new SimpleStringProperty(jsonObject.getString("spInf39"));
		week.spInf40 = new SimpleIntegerProperty(jsonObject.getInt("spInf40"));

		week.spInf41 = new SimpleIntegerProperty(jsonObject.getInt("spInf41"));
		week.spInf42 = new SimpleStringProperty(jsonObject.getString("spInf42"));
		week.spInf43 = new SimpleStringProperty(jsonObject.getString("spInf43"));
		week.spInf44 = new SimpleIntegerProperty(jsonObject.getInt("spInf44"));
		week.spInf45 = new SimpleIntegerProperty(jsonObject.getInt("spInf45"));
		week.spInf46 = new SimpleIntegerProperty(jsonObject.getInt("spInf46"));
		week.spInf47 = new SimpleIntegerProperty(jsonObject.getInt("spInf47"));
		week.spInf48 = new SimpleIntegerProperty(jsonObject.getInt("spInf48"));
		week.spInf49 = new SimpleIntegerProperty(jsonObject.getInt("spInf49"));
		week.spInf50 = new SimpleStringProperty(jsonObject.getString("spInf50"));
		week.spInf51 = new SimpleIntegerProperty(jsonObject.getInt("spInf51"));
		week.spInf52 = new SimpleStringProperty(jsonObject.getString("spInf52"));
		week.spInf53 = new SimpleIntegerProperty(jsonObject.getInt("spInf53"));

		week.spInf54 = new SimpleStringProperty(jsonObject.getString("spInf54"));
		week.spInf55 = new SimpleStringProperty(jsonObject.getString("spInf55"));
		week.spInf56 = new SimpleIntegerProperty(jsonObject.getInt("spInf56"));
		week.spInf57 = new SimpleIntegerProperty(jsonObject.getInt("spInf57"));
		week.spInf58 = new SimpleIntegerProperty(jsonObject.getInt("spInf58"));

		week.spInf59 = new SimpleStringProperty(jsonObject.getString("spInf59"));
		week.spInf60 = new SimpleStringProperty(jsonObject.getString("spInf60"));
		week.spInf61 = new SimpleStringProperty(jsonObject.getString("spInf61"));
		week.spInf62 = new SimpleStringProperty(jsonObject.getString("spInf62"));
		week.spInf63 = new SimpleStringProperty(jsonObject.getString("spInf63"));
		week.spInf64 = new SimpleStringProperty(jsonObject.getString("spInf64"));
		week.spInf65 = new SimpleIntegerProperty(jsonObject.getInt("spInf65"));
		week.spInf66 = new SimpleIntegerProperty(jsonObject.getInt("spInf66"));

		return week;
	}
}
