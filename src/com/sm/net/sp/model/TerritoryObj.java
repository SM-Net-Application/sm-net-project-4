package com.sm.net.sp.model;

import java.math.BigDecimal;

import javax.crypto.SecretKey;

import org.json.JSONObject;

import com.sm.net.sp.view.home.user.menu.territory.TerritoryEditor;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TerritoryObj {

	private IntegerProperty spTerritoryID;
	private StringProperty spInf1;
	private StringProperty spInf2;
	private StringProperty spInf3;
	private StringProperty spInf4;
	private IntegerProperty spInf5;
	private StringProperty spInf6;
	private ObjectProperty<BigDecimal> spInf7;
	private StringProperty spInf8;
	private StringProperty spInf9;
	private StringProperty spInf10;
	private StringProperty spInf11;
	private StringProperty spInf12;
	private StringProperty spInf13;
	private StringProperty spInf14;
	private StringProperty spInf15;
	private StringProperty spInf16;
	private StringProperty spInf17;
	private StringProperty spInf18;
	private StringProperty spInf19;
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
	private StringProperty spInf31;
	private StringProperty spInf32;
	private StringProperty spInf33;
	private StringProperty spInf34;
	private StringProperty spInf35;
	private StringProperty spInf36;
	private StringProperty spInf37;
	private StringProperty spInf38;
	private StringProperty spInf39;
	private StringProperty spInf40;

	public TerritoryObj() {
		super();

		this.spTerritoryID = new SimpleIntegerProperty();
		this.spInf1 = new SimpleStringProperty();

		this.spInf2 = new SimpleStringProperty();
		this.spInf3 = new SimpleStringProperty();
		this.spInf4 = new SimpleStringProperty();
		this.spInf5 = new SimpleIntegerProperty();
		this.spInf6 = new SimpleStringProperty();
		this.spInf7 = new SimpleObjectProperty<>();
		this.spInf8 = new SimpleStringProperty();
		this.spInf9 = new SimpleStringProperty();
		this.spInf10 = new SimpleStringProperty();
		this.spInf11 = new SimpleStringProperty();
		this.spInf12 = new SimpleStringProperty();
		this.spInf13 = new SimpleStringProperty();
		this.spInf14 = new SimpleStringProperty();
		this.spInf15 = new SimpleStringProperty();
		this.spInf16 = new SimpleStringProperty();
		this.spInf17 = new SimpleStringProperty();
		this.spInf18 = new SimpleStringProperty();
		this.spInf19 = new SimpleStringProperty();
		this.spInf20 = new SimpleStringProperty();
		this.spInf21 = new SimpleStringProperty();
		this.spInf22 = new SimpleStringProperty();
		this.spInf23 = new SimpleStringProperty();
		this.spInf24 = new SimpleStringProperty();
		this.spInf25 = new SimpleStringProperty();
		this.spInf26 = new SimpleStringProperty();
		this.spInf27 = new SimpleStringProperty();
		this.spInf28 = new SimpleStringProperty();
		this.spInf29 = new SimpleStringProperty();
		this.spInf30 = new SimpleStringProperty();
		this.spInf31 = new SimpleStringProperty();
		this.spInf32 = new SimpleStringProperty();
		this.spInf33 = new SimpleStringProperty();
		this.spInf34 = new SimpleStringProperty();
		this.spInf35 = new SimpleStringProperty();
		this.spInf36 = new SimpleStringProperty();
		this.spInf37 = new SimpleStringProperty();
		this.spInf38 = new SimpleStringProperty();
		this.spInf39 = new SimpleStringProperty();
		this.spInf40 = new SimpleStringProperty();
	}

	public static TerritoryObj newInstanceByView(TerritoryEditor editor) {

		// Territory selectedWeek = editor.getSelectedWeek();

		// TODO

//		int spInf1 = Integer.valueOf(selectedWeek.getKey());
//		int spInf2 = editor.getZ1u1MidweekComboBox().getSelectionModel().getSelectedItem().getSpMemberID();
//		int spInf3 = editor.getZ1u2MidweekComboBox().getSelectionModel().getSelectedItem().getSpMemberID();
//		int spInf4 = editor.getZ1u3MidweekComboBox().getSelectionModel().getSelectedItem().getSpMemberID();
//		int spInf5 = editor.getZ2u1MidweekComboBox().getSelectionModel().getSelectedItem().getSpMemberID();
//		int spInf6 = editor.getZ2u2MidweekComboBox().getSelectionModel().getSelectedItem().getSpMemberID();
//		int spInf7 = editor.getZ2u3MidweekComboBox().getSelectionModel().getSelectedItem().getSpMemberID();
//		int spInf8 = editor.getZ3u1MidweekComboBox().getSelectionModel().getSelectedItem().getSpMemberID();
//		int spInf9 = editor.getZ3u2MidweekComboBox().getSelectionModel().getSelectedItem().getSpMemberID();
//		int spInf10 = editor.getZ3u3MidweekComboBox().getSelectionModel().getSelectedItem().getSpMemberID();
//		int spInf11 = editor.getZ1u1WeekendComboBox().getSelectionModel().getSelectedItem().getSpMemberID();
//		int spInf12 = editor.getZ1u2WeekendComboBox().getSelectionModel().getSelectedItem().getSpMemberID();
//		int spInf13 = editor.getZ1u3WeekendComboBox().getSelectionModel().getSelectedItem().getSpMemberID();
//		int spInf14 = editor.getZ2u1WeekendComboBox().getSelectionModel().getSelectedItem().getSpMemberID();
//		int spInf15 = editor.getZ2u2WeekendComboBox().getSelectionModel().getSelectedItem().getSpMemberID();
//		int spInf16 = editor.getZ2u3WeekendComboBox().getSelectionModel().getSelectedItem().getSpMemberID();
//		int spInf17 = editor.getZ3u1WeekendComboBox().getSelectionModel().getSelectedItem().getSpMemberID();
//		int spInf18 = editor.getZ3u2WeekendComboBox().getSelectionModel().getSelectedItem().getSpMemberID();
//		int spInf19 = editor.getZ3u3WeekendComboBox().getSelectionModel().getSelectedItem().getSpMemberID();

		TerritoryObj territory = new TerritoryObj();
//
//		if (selectedWeek != null)
//			if (selectedWeek.spUscIDProperty() != null)
//				week.setUscID(selectedWeek.getUscID());
//
//		week.setSpInf1(spInf1);
//		week.setSpInf2(spInf2);
//		week.setSpInf3(spInf3);
//		week.setSpInf4(spInf4);
//		week.setSpInf5(spInf5);
//		week.setSpInf6(spInf6);
//		week.setSpInf7(spInf7);
//		week.setSpInf8(spInf8);
//		week.setSpInf9(spInf9);
//		week.setSpInf10(spInf10);
//		week.setSpInf11(spInf11);
//		week.setSpInf12(spInf12);
//		week.setSpInf13(spInf13);
//		week.setSpInf14(spInf14);
//		week.setSpInf15(spInf15);
//		week.setSpInf16(spInf16);
//		week.setSpInf17(spInf17);
//		week.setSpInf18(spInf18);
//		week.setSpInf19(spInf19);

		return territory;
	}

	public static TerritoryObj newInstanceByJSONObject(JSONObject json, SecretKey secretKey) {

		TerritoryObj territory = new TerritoryObj();

//		int spUscID = json.getInt("spUscID");
//		int spInf1 = json.getInt("spInf1");
//		int spInf2 = json.getInt("spInf2");
//		int spInf3 = json.getInt("spInf3");
//		int spInf4 = json.getInt("spInf4");
//		int spInf5 = json.getInt("spInf5");
//		int spInf6 = json.getInt("spInf6");
//		int spInf7 = json.getInt("spInf7");
//		int spInf8 = json.getInt("spInf8");
//		int spInf9 = json.getInt("spInf9");
//		int spInf10 = json.getInt("spInf10");
//		int spInf11 = json.getInt("spInf11");
//		int spInf12 = json.getInt("spInf12");
//		int spInf13 = json.getInt("spInf13");
//		int spInf14 = json.getInt("spInf14");
//		int spInf15 = json.getInt("spInf15");
//		int spInf16 = json.getInt("spInf16");
//		int spInf17 = json.getInt("spInf17");
//		int spInf18 = json.getInt("spInf18");
//		int spInf19 = json.getInt("spInf19");
//

		// TODO: set Territory

		return territory;
	}

	public final IntegerProperty spTerritoryIDProperty() {
		return this.spTerritoryID;
	}

	public final int getSpTerritoryID() {
		return this.spTerritoryID.get();
	}

	public final void setSpTerritoryID(final int spTerritoryID) {
		this.spTerritoryIDProperty().set(spTerritoryID);
	}

	public final StringProperty spInf1Property() {
		return this.spInf1;
	}

	public final String getSpInf1() {
		return this.spInf1.get();
	}

	public final void setSpInf1(final String spInf1) {
		this.spInf1Property().set(spInf1);
	}

	public final StringProperty spInf2Property() {
		return this.spInf2;
	}

	public final String getSpInf2() {
		return this.spInf2.get();
	}

	public final void setSpInf2(final String spInf2) {
		this.spInf2Property().set(spInf2);
	}

	public final StringProperty spInf3Property() {
		return this.spInf3;
	}

	public final String getSpInf3() {
		return this.spInf3.get();
	}

	public final void setSpInf3(final String spInf3) {
		this.spInf3Property().set(spInf3);
	}

	public final StringProperty spInf4Property() {
		return this.spInf4;
	}

	public final String getSpInf4() {
		return this.spInf4.get();
	}

	public final void setSpInf4(final String spInf4) {
		this.spInf4Property().set(spInf4);
	}

	public final IntegerProperty spInf5Property() {
		return this.spInf5;
	}

	public final int getSpInf5() {
		return this.spInf5.get();
	}

	public final void setSpInf5(final int spInf5) {
		this.spInf5Property().set(spInf5);
	}

	public final StringProperty spInf6Property() {
		return this.spInf6;
	}

	public final String getSpInf6() {
		return this.spInf6.get();
	}

	public final void setSpInf6(final String spInf6) {
		this.spInf6Property().set(spInf6);
	}

	public final ObjectProperty<BigDecimal> spInf7Property() {
		return this.spInf7;
	}

	public final BigDecimal getSpInf7() {
		return this.spInf7.get();
	}

	public final void setSpInf7(final BigDecimal spInf7) {
		this.spInf7Property().set(spInf7);
	}

	public final StringProperty spInf8Property() {
		return this.spInf8;
	}

	public final String getSpInf8() {
		return this.spInf8.get();
	}

	public final void setSpInf8(final String spInf8) {
		this.spInf8Property().set(spInf8);
	}

	public final StringProperty spInf9Property() {
		return this.spInf9;
	}

	public final String getSpInf9() {
		return this.spInf9.get();
	}

	public final void setSpInf9(final String spInf9) {
		this.spInf9Property().set(spInf9);
	}

	public final StringProperty spInf10Property() {
		return this.spInf10;
	}

	public final String getSpInf10() {
		return this.spInf10.get();
	}

	public final void setSpInf10(final String spInf10) {
		this.spInf10Property().set(spInf10);
	}

	public final StringProperty spInf11Property() {
		return this.spInf11;
	}

	public final String getSpInf11() {
		return this.spInf11.get();
	}

	public final void setSpInf11(final String spInf11) {
		this.spInf11Property().set(spInf11);
	}

	public final StringProperty spInf12Property() {
		return this.spInf12;
	}

	public final String getSpInf12() {
		return this.spInf12.get();
	}

	public final void setSpInf12(final String spInf12) {
		this.spInf12Property().set(spInf12);
	}

	public final StringProperty spInf13Property() {
		return this.spInf13;
	}

	public final String getSpInf13() {
		return this.spInf13.get();
	}

	public final void setSpInf13(final String spInf13) {
		this.spInf13Property().set(spInf13);
	}

	public final StringProperty spInf14Property() {
		return this.spInf14;
	}

	public final String getSpInf14() {
		return this.spInf14.get();
	}

	public final void setSpInf14(final String spInf14) {
		this.spInf14Property().set(spInf14);
	}

	public final StringProperty spInf15Property() {
		return this.spInf15;
	}

	public final String getSpInf15() {
		return this.spInf15.get();
	}

	public final void setSpInf15(final String spInf15) {
		this.spInf15Property().set(spInf15);
	}

	public final StringProperty spInf16Property() {
		return this.spInf16;
	}

	public final String getSpInf16() {
		return this.spInf16.get();
	}

	public final void setSpInf16(final String spInf16) {
		this.spInf16Property().set(spInf16);
	}

	public final StringProperty spInf17Property() {
		return this.spInf17;
	}

	public final String getSpInf17() {
		return this.spInf17.get();
	}

	public final void setSpInf17(final String spInf17) {
		this.spInf17Property().set(spInf17);
	}

	public final StringProperty spInf18Property() {
		return this.spInf18;
	}

	public final String getSpInf18() {
		return this.spInf18.get();
	}

	public final void setSpInf18(final String spInf18) {
		this.spInf18Property().set(spInf18);
	}

	public final StringProperty spInf19Property() {
		return this.spInf19;
	}

	public final String getSpInf19() {
		return this.spInf19.get();
	}

	public final void setSpInf19(final String spInf19) {
		this.spInf19Property().set(spInf19);
	}

	public final StringProperty spInf20Property() {
		return this.spInf20;
	}

	public final String getSpInf20() {
		return this.spInf20.get();
	}

	public final void setSpInf20(final String spInf20) {
		this.spInf20Property().set(spInf20);
	}

	public final StringProperty spInf21Property() {
		return this.spInf21;
	}

	public final String getSpInf21() {
		return this.spInf21.get();
	}

	public final void setSpInf21(final String spInf21) {
		this.spInf21Property().set(spInf21);
	}

	public final StringProperty spInf22Property() {
		return this.spInf22;
	}

	public final String getSpInf22() {
		return this.spInf22.get();
	}

	public final void setSpInf22(final String spInf22) {
		this.spInf22Property().set(spInf22);
	}

	public final StringProperty spInf23Property() {
		return this.spInf23;
	}

	public final String getSpInf23() {
		return this.spInf23.get();
	}

	public final void setSpInf23(final String spInf23) {
		this.spInf23Property().set(spInf23);
	}

	public final StringProperty spInf24Property() {
		return this.spInf24;
	}

	public final String getSpInf24() {
		return this.spInf24.get();
	}

	public final void setSpInf24(final String spInf24) {
		this.spInf24Property().set(spInf24);
	}

	public final StringProperty spInf25Property() {
		return this.spInf25;
	}

	public final String getSpInf25() {
		return this.spInf25.get();
	}

	public final void setSpInf25(final String spInf25) {
		this.spInf25Property().set(spInf25);
	}

	public final StringProperty spInf26Property() {
		return this.spInf26;
	}

	public final String getSpInf26() {
		return this.spInf26.get();
	}

	public final void setSpInf26(final String spInf26) {
		this.spInf26Property().set(spInf26);
	}

	public final StringProperty spInf27Property() {
		return this.spInf27;
	}

	public final String getSpInf27() {
		return this.spInf27.get();
	}

	public final void setSpInf27(final String spInf27) {
		this.spInf27Property().set(spInf27);
	}

	public final StringProperty spInf28Property() {
		return this.spInf28;
	}

	public final String getSpInf28() {
		return this.spInf28.get();
	}

	public final void setSpInf28(final String spInf28) {
		this.spInf28Property().set(spInf28);
	}

	public final StringProperty spInf29Property() {
		return this.spInf29;
	}

	public final String getSpInf29() {
		return this.spInf29.get();
	}

	public final void setSpInf29(final String spInf29) {
		this.spInf29Property().set(spInf29);
	}

	public final StringProperty spInf30Property() {
		return this.spInf30;
	}

	public final String getSpInf30() {
		return this.spInf30.get();
	}

	public final void setSpInf30(final String spInf30) {
		this.spInf30Property().set(spInf30);
	}

	public final StringProperty spInf31Property() {
		return this.spInf31;
	}

	public final String getSpInf31() {
		return this.spInf31.get();
	}

	public final void setSpInf31(final String spInf31) {
		this.spInf31Property().set(spInf31);
	}

	public final StringProperty spInf32Property() {
		return this.spInf32;
	}

	public final String getSpInf32() {
		return this.spInf32.get();
	}

	public final void setSpInf32(final String spInf32) {
		this.spInf32Property().set(spInf32);
	}

	public final StringProperty spInf33Property() {
		return this.spInf33;
	}

	public final String getSpInf33() {
		return this.spInf33.get();
	}

	public final void setSpInf33(final String spInf33) {
		this.spInf33Property().set(spInf33);
	}

	public final StringProperty spInf34Property() {
		return this.spInf34;
	}

	public final String getSpInf34() {
		return this.spInf34.get();
	}

	public final void setSpInf34(final String spInf34) {
		this.spInf34Property().set(spInf34);
	}

	public final StringProperty spInf35Property() {
		return this.spInf35;
	}

	public final String getSpInf35() {
		return this.spInf35.get();
	}

	public final void setSpInf35(final String spInf35) {
		this.spInf35Property().set(spInf35);
	}

	public final StringProperty spInf36Property() {
		return this.spInf36;
	}

	public final String getSpInf36() {
		return this.spInf36.get();
	}

	public final void setSpInf36(final String spInf36) {
		this.spInf36Property().set(spInf36);
	}

	public final StringProperty spInf37Property() {
		return this.spInf37;
	}

	public final String getSpInf37() {
		return this.spInf37.get();
	}

	public final void setSpInf37(final String spInf37) {
		this.spInf37Property().set(spInf37);
	}

	public final StringProperty spInf38Property() {
		return this.spInf38;
	}

	public final String getSpInf38() {
		return this.spInf38.get();
	}

	public final void setSpInf38(final String spInf38) {
		this.spInf38Property().set(spInf38);
	}

	public final StringProperty spInf39Property() {
		return this.spInf39;
	}

	public final String getSpInf39() {
		return this.spInf39.get();
	}

	public final void setSpInf39(final String spInf39) {
		this.spInf39Property().set(spInf39);
	}

	public final StringProperty spInf40Property() {
		return this.spInf40;
	}

	public final String getSpInf40() {
		return this.spInf40.get();
	}

	public final void setSpInf40(final String spInf40) {
		this.spInf40Property().set(spInf40);
	}

}
