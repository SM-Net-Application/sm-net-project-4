package com.sm.net.sp.model;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;

import javax.crypto.SecretKey;

import org.json.JSONObject;

import com.sm.net.project.Language;
import com.sm.net.sp.settings.Settings;
import com.sm.net.sp.view.home.user.menu.memorial.MemorialEditor;
import com.sm.net.util.Crypt;
import com.sm.net.util.DateUtil;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

public class WeekMemorial {

	private IntegerProperty week;
	private ObjectProperty<LocalDate> from;
	private ObjectProperty<LocalDate> to;
	private StringProperty key;

	private IntegerProperty spMemorialID;
	private IntegerProperty spInf1;
	private StringProperty spInf2;
	private StringProperty spInf3;
	private IntegerProperty spInf4;
	private IntegerProperty spInf5;
	private IntegerProperty spInf6;
	private StringProperty spInf7;
	private StringProperty spInf8;
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
	private IntegerProperty spInf20;
	private IntegerProperty spInf21;
	private IntegerProperty spInf22;
	private IntegerProperty spInf23;
	private StringProperty spInf24;
	private IntegerProperty spInf25;
	private IntegerProperty spInf26;
	private IntegerProperty spInf27;
	private IntegerProperty spInf28;
	private IntegerProperty spInf29;
	private IntegerProperty spInf30;
	private IntegerProperty spInf31;
	private IntegerProperty spInf32;
	private IntegerProperty spInf33;
	private IntegerProperty spInf34;
	private IntegerProperty spInf35;
	private IntegerProperty spInf36;
	
	private StringProperty spInf37;
	private StringProperty spInf38;

	public WeekMemorial(LocalDate day, Language language) {
		super();

		week = new SimpleIntegerProperty(DateUtil.getWeekOfYears(day));
		from = new SimpleObjectProperty<LocalDate>(DateUtil.getFirstDayOfWeek(day));
		to = new SimpleObjectProperty<LocalDate>(DateUtil.getLastDayOfWeek(day));
		key = new SimpleStringProperty(WeekMemorial.buildKey(day));
	}

	public WeekMemorial(JSONObject jsonObject, Settings settings) {

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

	public WeekMemorial(int spConvenID, int spInf1, String spInf2, String spInf3, int spInf4, int spInf5, int spInf6,
			String spInf7, String spInf8, int spInf9, int spInf10, int spInf11, int spInf12, int spInf13, int spInf14,
			int spInf15, int spInf16, int spInf17, int spInf18, int spInf19, int spInf20, int spInf21, int spInf22,
			int spInf23, String spInf24, int spInf25, int spInf26, int spInf27, int spInf28, int spInf29, int spInf30,
			int spInf31, int spInf32, int spInf33, int spInf34, int spInf35, int spInf36, String spInf37, String spInf38) {

		this.week = null;
		this.from = null;
		this.to = null;
		this.key = null;

		this.spMemorialID = new SimpleIntegerProperty(spConvenID);
		this.spInf1 = new SimpleIntegerProperty(spInf1);
		this.spInf2 = new SimpleStringProperty(spInf2);
		this.spInf3 = new SimpleStringProperty(spInf3);
		this.spInf4 = new SimpleIntegerProperty(spInf4);
		this.spInf5 = new SimpleIntegerProperty(spInf5);
		this.spInf6 = new SimpleIntegerProperty(spInf6);
		this.spInf7 = new SimpleStringProperty(spInf7);
		this.spInf8 = new SimpleStringProperty(spInf8);
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
		this.spInf20 = new SimpleIntegerProperty(spInf20);
		this.spInf21 = new SimpleIntegerProperty(spInf21);
		this.spInf22 = new SimpleIntegerProperty(spInf22);
		this.spInf23 = new SimpleIntegerProperty(spInf23);
		this.spInf24 = new SimpleStringProperty(spInf24);
		this.spInf25 = new SimpleIntegerProperty(spInf25);
		this.spInf26 = new SimpleIntegerProperty(spInf26);
		this.spInf27 = new SimpleIntegerProperty(spInf27);
		this.spInf28 = new SimpleIntegerProperty(spInf28);
		this.spInf29 = new SimpleIntegerProperty(spInf29);
		this.spInf30 = new SimpleIntegerProperty(spInf30);
		this.spInf31 = new SimpleIntegerProperty(spInf31);
		this.spInf32 = new SimpleIntegerProperty(spInf32);
		this.spInf33 = new SimpleIntegerProperty(spInf33);
		this.spInf34 = new SimpleIntegerProperty(spInf34);
		this.spInf35 = new SimpleIntegerProperty(spInf35);
		this.spInf36 = new SimpleIntegerProperty(spInf36);
		
		this.spInf37 = new SimpleStringProperty(spInf37);
		this.spInf38 = new SimpleStringProperty(spInf38);
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

	public void updateOnlineWeekInfo(ObservableList<WeekMemorial> list, Settings settings) {

		for (WeekMemorial week : list)
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

	public static WeekMemorial newInstanceByView(MemorialEditor memorialEditor) {

		Language language = memorialEditor.getLanguage();
		SecretKey secretKey = memorialEditor.getSettings().getDatabaseSecretKey();

		WeekMemorial selectedWeek = memorialEditor.getSelectedWeek();

		int spInf1 = Integer.valueOf(memorialEditor.getSelectedWeek().getKey());
		String spInf2 = Crypt.encrypt(memorialEditor.getSongStartTextField().getText(), secretKey);
		String spInf3 = Crypt.encrypt(memorialEditor.getSongEndTextField().getText(), secretKey);
		int spInf4 = memorialEditor.getPresidentComboBox().getSelectionModel().getSelectedItem().getSpMemberID();
		int spInf5 = memorialEditor.getPrayStartComboBox().getSelectionModel().getSelectedItem().getSpMemberID();
		int spInf6 = memorialEditor.getTalkBrotherComboBox().getSelectionModel().getSelectedItem().getSpMemberID();
		String spInf7 = Crypt.encrypt(memorialEditor.getTalkThemeTextField().getText(), secretKey);
		String spInf8 = Crypt.encrypt(memorialEditor.getTalkMinTextField().getText(), secretKey);
		int spInf9 = memorialEditor.getPrayBreadComboBox().getSelectionModel().getSelectedItem().getSpMemberID();
		int spInf10 = memorialEditor.getPrayWineComboBox().getSelectionModel().getSelectedItem().getSpMemberID();
		int spInf11 = memorialEditor.getBreadFamily1ComboBox().getSelectionModel().getSelectedItem().getSpFamID();
		int spInf12 = memorialEditor.getBreadFamily2ComboBox().getSelectionModel().getSelectedItem().getSpFamID();
		int spInf13 = memorialEditor.getBreadFamily3ComboBox().getSelectionModel().getSelectedItem().getSpFamID();
		int spInf14 = memorialEditor.getBreadFamily4ComboBox().getSelectionModel().getSelectedItem().getSpFamID();
		int spInf15 = memorialEditor.getBreadFamily5ComboBox().getSelectionModel().getSelectedItem().getSpFamID();
		int spInf16 = memorialEditor.getWineFamily1ComboBox().getSelectionModel().getSelectedItem().getSpFamID();
		int spInf17 = memorialEditor.getWineFamily2ComboBox().getSelectionModel().getSelectedItem().getSpFamID();
		int spInf18 = memorialEditor.getWineFamily3ComboBox().getSelectionModel().getSelectedItem().getSpFamID();
		int spInf19 = memorialEditor.getWineFamily4ComboBox().getSelectionModel().getSelectedItem().getSpFamID();
		int spInf20 = memorialEditor.getWineFamily5ComboBox().getSelectionModel().getSelectedItem().getSpFamID();
		int spInf21 = memorialEditor.getDayComboBox().getSelectionModel().getSelectedItem().getId();
		int spInf22 = memorialEditor.getHourComboBox().getSelectionModel().getSelectedItem();
		int spInf23 = memorialEditor.getMinuteComboBox().getSelectionModel().getSelectedItem();
		String spInf24 = Crypt.encrypt(memorialEditor.getPlaceTextField().getText(), secretKey);
		int spInf25 = memorialEditor.getEmblemsBrother1ComboBox().getSelectionModel().getSelectedItem().getSpMemberID();
		int spInf26 = memorialEditor.getEmblemsBrother2ComboBox().getSelectionModel().getSelectedItem().getSpMemberID();
		int spInf27 = memorialEditor.getEmblemsBrother3ComboBox().getSelectionModel().getSelectedItem().getSpMemberID();
		int spInf28 = memorialEditor.getEmblemsBrother4ComboBox().getSelectionModel().getSelectedItem().getSpMemberID();
		int spInf29 = memorialEditor.getEmblemsBrother5ComboBox().getSelectionModel().getSelectedItem().getSpMemberID();
		int spInf30 = memorialEditor.getEmblemsBrother6ComboBox().getSelectionModel().getSelectedItem().getSpMemberID();
		int spInf31 = memorialEditor.getEmblemsBrother7ComboBox().getSelectionModel().getSelectedItem().getSpMemberID();
		int spInf32 = memorialEditor.getEmblemsBrother8ComboBox().getSelectionModel().getSelectedItem().getSpMemberID();
		int spInf33 = memorialEditor.getEmblemsBrother9ComboBox().getSelectionModel().getSelectedItem().getSpMemberID();
		int spInf34 = memorialEditor.getEmblemsBrother10ComboBox().getSelectionModel().getSelectedItem()
				.getSpMemberID();
		int spInf35 = memorialEditor.getPrayStartPresidentCheckBox().isSelected() ? 1 : 0;
		int spInf36 = memorialEditor.getPrayEndComboBox().getSelectionModel().getSelectedItem().getSpMemberID();

		String spInf37 = Crypt.encrypt(memorialEditor.getSong1TextField().getText(), secretKey);
		String spInf38 = Crypt.encrypt(memorialEditor.getSong2TextField().getText(), secretKey);

		WeekMemorial weekMemorial = new WeekMemorial(selectedWeek.getFrom(), language);

		if (selectedWeek != null)
			if (selectedWeek.spMemorialIDProperty() != null)
				weekMemorial.setMemorialID(selectedWeek.getMemorialID());

		weekMemorial.setSpInf1(spInf1);
		weekMemorial.setSpInf2(spInf2);
		weekMemorial.setSpInf3(spInf3);
		weekMemorial.setSpInf4(spInf4);
		weekMemorial.setSpInf5(spInf5);
		weekMemorial.setSpInf6(spInf6);
		weekMemorial.setSpInf7(spInf7);
		weekMemorial.setSpInf8(spInf8);
		weekMemorial.setSpInf9(spInf9);
		weekMemorial.setSpInf10(spInf10);
		weekMemorial.setSpInf11(spInf11);
		weekMemorial.setSpInf12(spInf12);
		weekMemorial.setSpInf13(spInf13);
		weekMemorial.setSpInf14(spInf14);
		weekMemorial.setSpInf15(spInf15);
		weekMemorial.setSpInf16(spInf16);
		weekMemorial.setSpInf17(spInf17);
		weekMemorial.setSpInf18(spInf18);
		weekMemorial.setSpInf19(spInf19);
		weekMemorial.setSpInf20(spInf20);
		weekMemorial.setSpInf21(spInf21);
		weekMemorial.setSpInf22(spInf22);
		weekMemorial.setSpInf23(spInf23);
		weekMemorial.setSpInf24(spInf24);
		weekMemorial.setSpInf25(spInf25);
		weekMemorial.setSpInf26(spInf26);
		weekMemorial.setSpInf27(spInf27);
		weekMemorial.setSpInf28(spInf28);
		weekMemorial.setSpInf29(spInf29);
		weekMemorial.setSpInf30(spInf30);
		weekMemorial.setSpInf31(spInf31);
		weekMemorial.setSpInf32(spInf32);
		weekMemorial.setSpInf33(spInf33);
		weekMemorial.setSpInf34(spInf34);
		weekMemorial.setSpInf35(spInf35);
		weekMemorial.setSpInf36(spInf36);
		
		weekMemorial.setSpInf37(spInf37);
		weekMemorial.setSpInf38(spInf38);

		return weekMemorial;
	}

	public static WeekMemorial newInstanceByJSONObject(JSONObject json, SecretKey secretKey) {

		int spMemorialID = json.getInt("spMemorialID");
		int spInf1 = json.getInt("spInf1");

		String spInf2 = Crypt.decrypt(json.getString("spInf2"), secretKey);
		String spInf3 = Crypt.decrypt(json.getString("spInf3"), secretKey);

		int spInf4 = json.getInt("spInf4");
		int spInf5 = json.getInt("spInf5");
		int spInf6 = json.getInt("spInf6");

		String spInf7 = Crypt.decrypt(json.getString("spInf7"), secretKey);
		String spInf8 = Crypt.decrypt(json.getString("spInf8"), secretKey);

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
		int spInf20 = json.getInt("spInf20");
		int spInf21 = json.getInt("spInf21");
		int spInf22 = json.getInt("spInf22");
		int spInf23 = json.getInt("spInf23");

		String spInf24 = Crypt.decrypt(json.getString("spInf24"), secretKey);

		int spInf25 = json.getInt("spInf25");
		int spInf26 = json.getInt("spInf26");
		int spInf27 = json.getInt("spInf27");
		int spInf28 = json.getInt("spInf28");
		int spInf29 = json.getInt("spInf29");
		int spInf30 = json.getInt("spInf30");
		int spInf31 = json.getInt("spInf31");
		int spInf32 = json.getInt("spInf32");
		int spInf33 = json.getInt("spInf33");
		int spInf34 = json.getInt("spInf34");
		int spInf35 = json.getInt("spInf35");
		int spInf36 = json.getInt("spInf36");

		String spInf37 = Crypt.decrypt(json.getString("spInf37"), secretKey);
		String spInf38 = Crypt.decrypt(json.getString("spInf38"), secretKey);
		
		return new WeekMemorial(spMemorialID, spInf1, spInf2, spInf3, spInf4, spInf5, spInf6, spInf7, spInf8, spInf9,
				spInf10, spInf11, spInf12, spInf13, spInf14, spInf15, spInf16, spInf17, spInf18, spInf19, spInf20,
				spInf21, spInf22, spInf23, spInf24, spInf25, spInf26, spInf27, spInf28, spInf29, spInf30, spInf31,
				spInf32, spInf33, spInf34, spInf35, spInf36, spInf37, spInf38);
	}

	public void updateInfo(WeekMemorial wm) {

		this.setMemorialID(wm.getMemorialID());
		this.setSpInf1(wm.getSpInf1());
		this.setSpInf2(wm.getSpInf2());
		this.setSpInf3(wm.getSpInf3());
		this.setSpInf4(wm.getSpInf4());
		this.setSpInf5(wm.getSpInf5());
		this.setSpInf6(wm.getSpInf6());
		this.setSpInf7(wm.getSpInf7());
		this.setSpInf8(wm.getSpInf8());
		this.setSpInf9(wm.getSpInf9());
		this.setSpInf10(wm.getSpInf10());
		this.setSpInf11(wm.getSpInf11());
		this.setSpInf12(wm.getSpInf12());
		this.setSpInf13(wm.getSpInf13());
		this.setSpInf14(wm.getSpInf14());
		this.setSpInf15(wm.getSpInf15());
		this.setSpInf16(wm.getSpInf16());
		this.setSpInf17(wm.getSpInf17());
		this.setSpInf18(wm.getSpInf18());
		this.setSpInf19(wm.getSpInf19());
		this.setSpInf20(wm.getSpInf20());
		this.setSpInf21(wm.getSpInf21());
		this.setSpInf22(wm.getSpInf22());
		this.setSpInf23(wm.getSpInf23());
		this.setSpInf24(wm.getSpInf24());
		this.setSpInf25(wm.getSpInf25());
		this.setSpInf26(wm.getSpInf26());
		this.setSpInf27(wm.getSpInf27());
		this.setSpInf28(wm.getSpInf28());
		this.setSpInf29(wm.getSpInf29());
		this.setSpInf30(wm.getSpInf30());
		this.setSpInf31(wm.getSpInf31());
		this.setSpInf32(wm.getSpInf32());
		this.setSpInf33(wm.getSpInf33());
		this.setSpInf34(wm.getSpInf34());
		this.setSpInf35(wm.getSpInf35());
		this.setSpInf36(wm.getSpInf36());
		
		this.setSpInf37(wm.getSpInf37());
		this.setSpInf38(wm.getSpInf38());
	}

	public void deleteInfo() {

		this.spMemorialID = null;
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
		this.spInf32 = null;
		this.spInf33 = null;
		this.spInf34 = null;
		this.spInf35 = null;
		this.spInf36 = null;
		
		this.spInf37 = null;
		this.spInf38 = null;
	}

	@Override
	public boolean equals(Object obj) {

		if (obj != null)
			if (obj instanceof WeekMemorial) {
				WeekMemorial onlineWeek = (WeekMemorial) obj;

				if (onlineWeek.spMemorialIDProperty() != null)
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

	public final IntegerProperty spMemorialIDProperty() {
		return this.spMemorialID;
	}

	public final int getMemorialID() {
		return this.spMemorialID.get();
	}

	public final void setMemorialID(final int memorialID) {
		if (this.spMemorialIDProperty() == null)
			this.spMemorialID = new SimpleIntegerProperty();

		this.spMemorialIDProperty().set(memorialID);
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

	public final StringProperty spInf2Property() {
		return this.spInf2;
	}

	public final String getSpInf2() {
		return this.spInf2Property().get();
	}

	public final void setSpInf2(final String spInf2) {
		if (this.spInf2Property() == null)
			this.spInf2 = new SimpleStringProperty();

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

	public final IntegerProperty spInf20Property() {
		return this.spInf20;
	}

	public final int getSpInf20() {
		return this.spInf20Property().get();
	}

	public final void setSpInf20(final int spInf20) {
		if (this.spInf20Property() == null)
			this.spInf20 = new SimpleIntegerProperty();

		this.spInf20Property().set(spInf20);
	}

	public final IntegerProperty spInf21Property() {
		return this.spInf21;
	}

	public final int getSpInf21() {
		return this.spInf21Property().get();
	}

	public final void setSpInf21(final int spInf21) {
		if (this.spInf21Property() == null)
			this.spInf21 = new SimpleIntegerProperty();

		this.spInf21Property().set(spInf21);
	}

	public final IntegerProperty spInf22Property() {
		return this.spInf22;
	}

	public final int getSpInf22() {
		return this.spInf22Property().get();
	}

	public final void setSpInf22(final int spInf22) {
		if (this.spInf22Property() == null)
			this.spInf22 = new SimpleIntegerProperty();

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

	public final IntegerProperty spInf25Property() {
		return this.spInf25;
	}

	public final int getSpInf25() {
		return this.spInf25Property().get();
	}

	public final void setSpInf25(final int spInf25) {
		if (this.spInf25Property() == null)
			this.spInf25 = new SimpleIntegerProperty();

		this.spInf25Property().set(spInf25);
	}

	public final IntegerProperty spInf26Property() {
		return this.spInf26;
	}

	public final int getSpInf26() {
		return this.spInf26Property().get();
	}

	public final void setSpInf26(final int spInf26) {
		if (this.spInf26Property() == null)
			this.spInf26 = new SimpleIntegerProperty();

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

	public final IntegerProperty spInf32Property() {
		return this.spInf32;
	}

	public final int getSpInf32() {
		return this.spInf32Property().get();
	}

	public final void setSpInf32(final int spInf32) {
		if (this.spInf32Property() == null)
			this.spInf32 = new SimpleIntegerProperty();

		this.spInf32Property().set(spInf32);
	}

	public final IntegerProperty spInf33Property() {
		return this.spInf33;
	}

	public final int getSpInf33() {
		return this.spInf33Property().get();
	}

	public final void setSpInf33(final int spInf33) {
		if (this.spInf33Property() == null)
			this.spInf33 = new SimpleIntegerProperty();

		this.spInf33Property().set(spInf33);
	}

	public final IntegerProperty spInf34Property() {
		return this.spInf34;
	}

	public final int getSpInf34() {
		return this.spInf34Property().get();
	}

	public final void setSpInf34(final int spInf34) {
		if (this.spInf34Property() == null)
			this.spInf34 = new SimpleIntegerProperty();

		this.spInf34Property().set(spInf34);
	}

	public final IntegerProperty spInf35Property() {
		return this.spInf35;
	}

	public final int getSpInf35() {
		return this.spInf35Property().get();
	}

	public final void setSpInf35(final int spInf35) {
		if (this.spInf35Property() == null)
			this.spInf35 = new SimpleIntegerProperty();

		this.spInf35Property().set(spInf35);
	}

	public final IntegerProperty spInf36Property() {
		return this.spInf36;
	}

	public final int getSpInf36() {
		return this.spInf36Property().get();
	}

	public final void setSpInf36(final int spInf36) {
		if (this.spInf36Property() == null)
			this.spInf36 = new SimpleIntegerProperty();

		this.spInf36Property().set(spInf36);
	}
	
	public final StringProperty spInf37Property() {
		return this.spInf37;
	}

	public final String getSpInf37() {
		return this.spInf37Property().get();
	}

	public final void setSpInf37(final String spInf37) {
		if (this.spInf37Property() == null)
			this.spInf37 = new SimpleStringProperty();

		this.spInf37Property().set(spInf37);
	}
	
	public final StringProperty spInf38Property() {
		return this.spInf38;
	}

	public final String getSpInf38() {
		return this.spInf38Property().get();
	}

	public final void setSpInf38(final String spInf38) {
		if (this.spInf38Property() == null)
			this.spInf38 = new SimpleStringProperty();

		this.spInf38Property().set(spInf38);
	}
}
