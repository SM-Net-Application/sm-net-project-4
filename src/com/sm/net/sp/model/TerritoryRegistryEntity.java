package com.sm.net.sp.model;

import java.time.LocalDate;

import javax.crypto.SecretKey;

import org.json.JSONObject;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

public class TerritoryRegistryEntity {

	private IntegerProperty id;
	private IntegerProperty spInf1;
	private IntegerProperty spInf2;
	private StringProperty spInf3;
	private StringProperty spInf4;

	private LocalDate startDate;
	private LocalDate endDate;

	private Member publisher;

	public TerritoryRegistryEntity() {
		super();

		this.id = new SimpleIntegerProperty();

		this.spInf1 = new SimpleIntegerProperty();
		this.spInf2 = new SimpleIntegerProperty();
		this.spInf3 = new SimpleStringProperty();
		this.spInf4 = new SimpleStringProperty();
	}

//	public static TerritoryRegistryEntity newInstanceByView(TerritoryEditor editor, SecretKey sk) {
//
//		String emptyStringEncrypted = Crypt.encrypt("", sk);
//
//		String spInf1 = Crypt.encrypt(editor.getTerritoryLandTextField().getText(), sk);
//		String spInf2 = Crypt.encrypt(editor.getTerritoryLandkreisTextField().getText(), sk);
//		String spInf3 = Crypt.encrypt(editor.getTerritoryKreisstadtTextField().getText(), sk);
//		String spInf4 = Crypt.encrypt(editor.getTerritoryOrtTextField().getText(), sk);
//		String spInf5 = Crypt.encrypt(editor.getTerritoryZipCodeTextField().getText(), sk);
//		String spInf6 = Crypt.encrypt(editor.getTerritoryOrtsteilTextField().getText(), sk);
//
//		BigDecimal territoryNumber = new BigDecimal(editor.getTerritoryNumberTextField().getText().replace(',', '.'));
//		String spInf7 = Crypt.encrypt(territoryNumber.toString(), sk);
//
//		String spInf8 = Crypt.encrypt(editor.getTerritoryNameTextField().getText(), sk);
//		String spInf9 = Crypt.encrypt(editor.getTerritoryCoordinatesTextField().getText(), sk);
//		String spInf10 = editor.getTerritoryMyMapsIDTextField().getText();
//		String spInf11 = Crypt.encrypt(editor.getImage1TextField().getText(), sk);
//		String spInf12 = Crypt.encrypt(editor.getImage1TitleTextField().getText(), sk);
//		String spInf13 = Crypt.encrypt(editor.getImage2TextField().getText(), sk);
//		String spInf14 = Crypt.encrypt(editor.getImage2TitleTextField().getText(), sk);
//		String spInf15 = Crypt.encrypt(editor.getImage3TextField().getText(), sk);
//		String spInf16 = Crypt.encrypt(editor.getImage3TitleTextField().getText(), sk);
//		String spInf17 = Crypt.encrypt(editor.getImage4TextField().getText(), sk);
//		String spInf18 = Crypt.encrypt(editor.getImage4TitleTextField().getText(), sk);
//		String spInf19 = Crypt.encrypt(editor.getImage5TextField().getText(), sk);
//		String spInf20 = Crypt.encrypt(editor.getImage5TitleTextField().getText(), sk);
//		String spInf21 = Crypt.encrypt(editor.getDoc1TextField().getText(), sk);
//		String spInf22 = Crypt.encrypt(editor.getDoc1TitleTextField().getText(), sk);
//		String spInf23 = Crypt.encrypt(editor.getDoc2TextField().getText(), sk);
//		String spInf24 = Crypt.encrypt(editor.getDoc2TitleTextField().getText(), sk);
//		String spInf25 = Crypt.encrypt(editor.getDoc3TextField().getText(), sk);
//		String spInf26 = Crypt.encrypt(editor.getDoc3TitleTextField().getText(), sk);
//		String spInf27 = Crypt.encrypt(editor.getDoc4TextField().getText(), sk);
//		String spInf28 = Crypt.encrypt(editor.getDoc4TitleTextField().getText(), sk);
//		String spInf29 = Crypt.encrypt(editor.getDoc5TextField().getText(), sk);
//		String spInf30 = Crypt.encrypt(editor.getDoc5TitleTextField().getText(), sk);
//
//		// Not encrypted
//		String spInf31 = editor.getTerritoryViewerIDTextField().getText();
//
//		String spInf32 = emptyStringEncrypted;
//		String spInf33 = emptyStringEncrypted;
//		String spInf34 = emptyStringEncrypted;
//		String spInf35 = emptyStringEncrypted;
//		String spInf36 = emptyStringEncrypted;
//		String spInf37 = emptyStringEncrypted;
//		String spInf38 = emptyStringEncrypted;
//		String spInf39 = emptyStringEncrypted;
//		String spInf40 = emptyStringEncrypted;
//
//		// Set data
//
//		TerritoryRegistryEntity territory = new TerritoryRegistryEntity();
//
//		territory.setSpTerritoryID(-1);
//		if (editor.getSelectedTerritory() != null)
//			territory.setSpTerritoryID(editor.getSelectedTerritory().getSpTerritoryID());
//
//		territory.setSpInf1(spInf1);
//		territory.setSpInf2(spInf2);
//		territory.setSpInf3(spInf3);
//		territory.setSpInf4(spInf4);
//		territory.setSpInf5(spInf5);
//		territory.setSpInf6(spInf6);
//		territory.setSpInf7(spInf7);
//		territory.setSpInf8(spInf8);
//		territory.setSpInf9(spInf9);
//		territory.setSpInf10(spInf10);
//		territory.setSpInf11(spInf11);
//		territory.setSpInf12(spInf12);
//		territory.setSpInf13(spInf13);
//		territory.setSpInf14(spInf14);
//		territory.setSpInf15(spInf15);
//		territory.setSpInf16(spInf16);
//		territory.setSpInf17(spInf17);
//		territory.setSpInf18(spInf18);
//		territory.setSpInf19(spInf19);
//		territory.setSpInf20(spInf20);
//		territory.setSpInf21(spInf21);
//		territory.setSpInf22(spInf22);
//		territory.setSpInf23(spInf23);
//		territory.setSpInf24(spInf24);
//		territory.setSpInf25(spInf25);
//		territory.setSpInf26(spInf26);
//		territory.setSpInf27(spInf27);
//		territory.setSpInf28(spInf28);
//		territory.setSpInf29(spInf29);
//		territory.setSpInf30(spInf30);
//		territory.setSpInf31(spInf31);
//		territory.setSpInf32(spInf32);
//		territory.setSpInf33(spInf33);
//		territory.setSpInf34(spInf34);
//		territory.setSpInf35(spInf35);
//		territory.setSpInf36(spInf36);
//		territory.setSpInf37(spInf37);
//		territory.setSpInf38(spInf38);
//		territory.setSpInf39(spInf39);
//		territory.setSpInf40(spInf40);
//
//		return territory;
//	}

	public static TerritoryRegistryEntity newInstanceByJSONObject(JSONObject json, SecretKey secretKey) {

		TerritoryRegistryEntity territoryRegistryEntity = new TerritoryRegistryEntity();

		int id = json.getInt("id");
		int spInf1 = json.getInt("spInf1");
		int spInf2 = json.getInt("spInf2");
//		String spInf3 = Crypt.decrypt(json.getString("spInf3"), secretKey);
//		String spInf4 = Crypt.decrypt(json.getString("spInf4"), secretKey);
		String spInf3 = json.getString("spInf3");
		String spInf4 = json.getString("spInf4");

		territoryRegistryEntity.setID(id);
		territoryRegistryEntity.setSpInf1(spInf1);
		territoryRegistryEntity.setSpInf2(spInf2);
		territoryRegistryEntity.setSpInf3(spInf3);
		territoryRegistryEntity.setSpInf4(spInf4);

		territoryRegistryEntity.processData();

		return territoryRegistryEntity;
	}

	private void processData() {

		if (!this.getSpInf3().isEmpty()) {
			this.setStartDate(LocalDate.parse(getSpInf3()));
		}

		if (!this.getSpInf4().isEmpty()) {
			this.setEndDate(LocalDate.parse(getSpInf4()));
		}
	}

	public void updatePublisher(ObservableList<Member> membersList) {

		for (Member member : membersList) {
			if (member.getSpMemberID() == this.spInf2.get()) {
				this.setPublisher(member);
				break;
			}
		}
	}

	public final IntegerProperty idProperty() {
		return this.id;
	}

	public final int getID() {
		return this.id.get();
	}

	public final void setID(final int id) {
		this.idProperty().set(id);
	}

	public final IntegerProperty spInf1Property() {
		return this.spInf1;
	}

	public final Integer getSpInf1() {
		return this.spInf1.get();
	}

	public final void setSpInf1(final int spInf1) {
		this.spInf1Property().set(spInf1);
	}

	public final IntegerProperty spInf2Property() {
		return this.spInf2;
	}

	public final Integer getSpInf2() {
		return this.spInf2.get();
	}

	public final void setSpInf2(final int spInf2) {
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

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public Member getPublisher() {
		return publisher;
	}

	public void setPublisher(Member publisher) {
		this.publisher = publisher;
	}
}
