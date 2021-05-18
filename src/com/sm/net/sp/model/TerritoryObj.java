package com.sm.net.sp.model;

import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.crypto.SecretKey;

import org.json.JSONObject;

import com.sm.net.sp.view.home.user.menu.territory.TerritoryEditor;
import com.sm.net.util.Crypt;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TerritoryObj {

	private IntegerProperty spTerritoryID;
	private StringProperty spInf1;
	private StringProperty spInf2;
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
		this.spInf5 = new SimpleStringProperty();
		this.spInf6 = new SimpleStringProperty();
		this.spInf7 = new SimpleStringProperty();
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

	public static TerritoryObj newInstanceByView(TerritoryEditor editor, SecretKey sk) {

		String emptyStringEncrypted = Crypt.encrypt("", sk);

		String spInf1 = Crypt.encrypt(editor.getTerritoryLandTextField().getText(), sk);
		String spInf2 = Crypt.encrypt(editor.getTerritoryLandkreisTextField().getText(), sk);
		String spInf3 = Crypt.encrypt(editor.getTerritoryKreisstadtTextField().getText(), sk);
		String spInf4 = Crypt.encrypt(editor.getTerritoryOrtTextField().getText(), sk);
		String spInf5 = Crypt.encrypt(editor.getTerritoryZipCodeTextField().getText(), sk);
		String spInf6 = Crypt.encrypt(editor.getTerritoryOrtsteilTextField().getText(), sk);

		BigDecimal territoryNumber = new BigDecimal(editor.getTerritoryNumberTextField().getText().replace(',', '.'));
		String spInf7 = Crypt.encrypt(territoryNumber.toString(), sk);

		String spInf8 = Crypt.encrypt(editor.getTerritoryNameTextField().getText(), sk);
		String spInf9 = Crypt.encrypt(editor.getTerritoryCoordinatesTextField().getText(), sk);
		String spInf10 = editor.getTerritoryMyMapsIDTextField().getText();
		String spInf11 = Crypt.encrypt(editor.getImage1TextField().getText(), sk);
		String spInf12 = Crypt.encrypt(editor.getImage1TitleTextField().getText(), sk);
		String spInf13 = Crypt.encrypt(editor.getImage2TextField().getText(), sk);
		String spInf14 = Crypt.encrypt(editor.getImage2TitleTextField().getText(), sk);
		String spInf15 = Crypt.encrypt(editor.getImage3TextField().getText(), sk);
		String spInf16 = Crypt.encrypt(editor.getImage3TitleTextField().getText(), sk);
		String spInf17 = Crypt.encrypt(editor.getImage4TextField().getText(), sk);
		String spInf18 = Crypt.encrypt(editor.getImage4TitleTextField().getText(), sk);
		String spInf19 = Crypt.encrypt(editor.getImage5TextField().getText(), sk);
		String spInf20 = Crypt.encrypt(editor.getImage5TitleTextField().getText(), sk);
		String spInf21 = Crypt.encrypt(editor.getDoc1TextField().getText(), sk);
		String spInf22 = Crypt.encrypt(editor.getDoc1TitleTextField().getText(), sk);
		String spInf23 = Crypt.encrypt(editor.getDoc2TextField().getText(), sk);
		String spInf24 = Crypt.encrypt(editor.getDoc2TitleTextField().getText(), sk);
		String spInf25 = Crypt.encrypt(editor.getDoc3TextField().getText(), sk);
		String spInf26 = Crypt.encrypt(editor.getDoc3TitleTextField().getText(), sk);
		String spInf27 = Crypt.encrypt(editor.getDoc4TextField().getText(), sk);
		String spInf28 = Crypt.encrypt(editor.getDoc4TitleTextField().getText(), sk);
		String spInf29 = Crypt.encrypt(editor.getDoc5TextField().getText(), sk);
		String spInf30 = Crypt.encrypt(editor.getDoc5TitleTextField().getText(), sk);

		// Not encrypted
		String spInf31 = editor.getTerritoryViewerIDTextField().getText();

		String spInf32 = emptyStringEncrypted;
		String spInf33 = emptyStringEncrypted;
		String spInf34 = emptyStringEncrypted;
		String spInf35 = emptyStringEncrypted;
		String spInf36 = emptyStringEncrypted;
		String spInf37 = emptyStringEncrypted;
		String spInf38 = emptyStringEncrypted;
		String spInf39 = emptyStringEncrypted;
		String spInf40 = emptyStringEncrypted;

		// Set data

		TerritoryObj territory = new TerritoryObj();

		territory.setSpTerritoryID(-1);
		if (editor.getSelectedTerritory() != null)
			territory.setSpTerritoryID(editor.getSelectedTerritory().getSpTerritoryID());

		territory.setSpInf1(spInf1);
		territory.setSpInf2(spInf2);
		territory.setSpInf3(spInf3);
		territory.setSpInf4(spInf4);
		territory.setSpInf5(spInf5);
		territory.setSpInf6(spInf6);
		territory.setSpInf7(spInf7);
		territory.setSpInf8(spInf8);
		territory.setSpInf9(spInf9);
		territory.setSpInf10(spInf10);
		territory.setSpInf11(spInf11);
		territory.setSpInf12(spInf12);
		territory.setSpInf13(spInf13);
		territory.setSpInf14(spInf14);
		territory.setSpInf15(spInf15);
		territory.setSpInf16(spInf16);
		territory.setSpInf17(spInf17);
		territory.setSpInf18(spInf18);
		territory.setSpInf19(spInf19);
		territory.setSpInf20(spInf20);
		territory.setSpInf21(spInf21);
		territory.setSpInf22(spInf22);
		territory.setSpInf23(spInf23);
		territory.setSpInf24(spInf24);
		territory.setSpInf25(spInf25);
		territory.setSpInf26(spInf26);
		territory.setSpInf27(spInf27);
		territory.setSpInf28(spInf28);
		territory.setSpInf29(spInf29);
		territory.setSpInf30(spInf30);
		territory.setSpInf31(spInf31);
		territory.setSpInf32(spInf32);
		territory.setSpInf33(spInf33);
		territory.setSpInf34(spInf34);
		territory.setSpInf35(spInf35);
		territory.setSpInf36(spInf36);
		territory.setSpInf37(spInf37);
		territory.setSpInf38(spInf38);
		territory.setSpInf39(spInf39);
		territory.setSpInf40(spInf40);

		return territory;
	}

	public static TerritoryObj newInstanceByJSONObject(JSONObject json, SecretKey secretKey) {

		TerritoryObj territory = new TerritoryObj();

		int id = json.getInt("id");
		String spInf1 = Crypt.decrypt(json.getString("spInf1"), secretKey);
		String spInf2 = Crypt.decrypt(json.getString("spInf2"), secretKey);
		String spInf3 = Crypt.decrypt(json.getString("spInf3"), secretKey);
		String spInf4 = Crypt.decrypt(json.getString("spInf4"), secretKey);
		String spInf5 = Crypt.decrypt(json.getString("spInf5"), secretKey);
		String spInf6 = Crypt.decrypt(json.getString("spInf6"), secretKey);
		String spInf7 = Crypt.decrypt(json.getString("spInf7"), secretKey);
		String spInf8 = Crypt.decrypt(json.getString("spInf8"), secretKey);
		String spInf9 = Crypt.decrypt(json.getString("spInf9"), secretKey);
		String spInf10 = json.getString("spInf10");
		String spInf11 = Crypt.decrypt(json.getString("spInf11"), secretKey);
		String spInf12 = Crypt.decrypt(json.getString("spInf12"), secretKey);
		String spInf13 = Crypt.decrypt(json.getString("spInf13"), secretKey);
		String spInf14 = Crypt.decrypt(json.getString("spInf14"), secretKey);
		String spInf15 = Crypt.decrypt(json.getString("spInf15"), secretKey);
		String spInf16 = Crypt.decrypt(json.getString("spInf16"), secretKey);
		String spInf17 = Crypt.decrypt(json.getString("spInf17"), secretKey);
		String spInf18 = Crypt.decrypt(json.getString("spInf18"), secretKey);
		String spInf19 = Crypt.decrypt(json.getString("spInf19"), secretKey);
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
		String spInf31 = json.getString("spInf31");
		String spInf32 = Crypt.decrypt(json.getString("spInf32"), secretKey);
		String spInf33 = Crypt.decrypt(json.getString("spInf33"), secretKey);
		String spInf34 = Crypt.decrypt(json.getString("spInf34"), secretKey);
		String spInf35 = Crypt.decrypt(json.getString("spInf35"), secretKey);
		String spInf36 = Crypt.decrypt(json.getString("spInf36"), secretKey);
		String spInf37 = Crypt.decrypt(json.getString("spInf37"), secretKey);
		String spInf38 = Crypt.decrypt(json.getString("spInf38"), secretKey);
		String spInf39 = Crypt.decrypt(json.getString("spInf39"), secretKey);
		String spInf40 = Crypt.decrypt(json.getString("spInf40"), secretKey);

		territory.setSpTerritoryID(id);
		territory.setSpInf1(spInf1);
		territory.setSpInf2(spInf2);
		territory.setSpInf3(spInf3);
		territory.setSpInf4(spInf4);
		territory.setSpInf5(spInf5);
		territory.setSpInf6(spInf6);
		territory.setSpInf7(spInf7);
		territory.setSpInf8(spInf8);
		territory.setSpInf9(spInf9);
		territory.setSpInf10(spInf10);
		territory.setSpInf11(spInf11);
		territory.setSpInf12(spInf12);
		territory.setSpInf13(spInf13);
		territory.setSpInf14(spInf14);
		territory.setSpInf15(spInf15);
		territory.setSpInf16(spInf16);
		territory.setSpInf17(spInf17);
		territory.setSpInf18(spInf18);
		territory.setSpInf19(spInf19);
		territory.setSpInf20(spInf20);
		territory.setSpInf21(spInf21);
		territory.setSpInf22(spInf22);
		territory.setSpInf23(spInf23);
		territory.setSpInf24(spInf24);
		territory.setSpInf25(spInf25);
		territory.setSpInf26(spInf26);
		territory.setSpInf27(spInf27);
		territory.setSpInf28(spInf28);
		territory.setSpInf29(spInf29);
		territory.setSpInf30(spInf30);
		territory.setSpInf31(spInf31);
		territory.setSpInf32(spInf32);
		territory.setSpInf33(spInf33);
		territory.setSpInf34(spInf34);
		territory.setSpInf35(spInf35);
		territory.setSpInf36(spInf36);
		territory.setSpInf37(spInf37);
		territory.setSpInf38(spInf38);
		territory.setSpInf39(spInf39);
		territory.setSpInf40(spInf40);

		return territory;
	}

	public File buildTargetDirectory() {

		String userHome = System.getProperty("user.home");

		String territoryNr = this.getSpInf7();
		String territoryName = this.getSpInf8();
		String territoryID = String.valueOf(this.getSpTerritoryID());

		File target = Paths.get(userHome, "SupportPlanner", "TerritoryResources",
				String.format("%s-%s(ID-%s)", territoryNr, territoryName, territoryID)).toFile();

		if (!target.exists())
			target.mkdirs();

		return target;
	}

	public ArrayList<TerritoryResource> getResources() {

		ArrayList<TerritoryResource> resourceList = new ArrayList<>();

		// ImageList
		checkResource(resourceList, 0, this.getSpInf11(), this.getSpInf12());
		checkResource(resourceList, 0, this.getSpInf13(), this.getSpInf14());
		checkResource(resourceList, 0, this.getSpInf15(), this.getSpInf16());
		checkResource(resourceList, 0, this.getSpInf17(), this.getSpInf18());
		checkResource(resourceList, 0, this.getSpInf19(), this.getSpInf20());

		// DocList
		checkResource(resourceList, 1, this.getSpInf21(), this.getSpInf22());
		checkResource(resourceList, 1, this.getSpInf23(), this.getSpInf24());
		checkResource(resourceList, 1, this.getSpInf25(), this.getSpInf26());
		checkResource(resourceList, 1, this.getSpInf27(), this.getSpInf28());
		checkResource(resourceList, 1, this.getSpInf29(), this.getSpInf30());

		return resourceList;
	}

	private void checkResource(ArrayList<TerritoryResource> resourceList, int type, String url, String title) {
		if (!url.isEmpty() && !title.isEmpty())
			resourceList.add(new TerritoryResource(type, title, url));
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

	public final StringProperty spInf5Property() {
		return this.spInf5;
	}

	public final String getSpInf5() {
		return this.spInf5.get();
	}

	public final void setSpInf5(final String spInf5) {
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

	public final StringProperty spInf7Property() {
		return this.spInf7;
	}

	public final String getSpInf7() {
		return this.spInf7.get();
	}

	public final void setSpInf7(final String spInf7) {
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
