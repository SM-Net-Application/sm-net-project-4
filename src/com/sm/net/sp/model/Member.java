package com.sm.net.sp.model;

import javax.crypto.SecretKey;

import org.json.JSONObject;

import com.sm.net.util.Crypt;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Member {

	private IntegerProperty spMemberID;
	private StringProperty spInf1Encrypted;
	private StringProperty spInf2Encrypted;
	private StringProperty spInf3Encrypted;
	private StringProperty spInf1Decrypted;
	private StringProperty spInf2Decrypted;
	private StringProperty spInf3Decrypted;
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
	private IntegerProperty spInf20;
	private IntegerProperty spInf21;
	private IntegerProperty spInf22;
	private IntegerProperty spInf23;
	private IntegerProperty spInf24;
	private IntegerProperty spInf25;
	private IntegerProperty spInf26;
	private IntegerProperty spInf27;
	private IntegerProperty spInf28;
	private IntegerProperty spInf29;

	public Member(JSONObject jsonObject, SecretKey secretKey) {
		super();
		defaultCostructor(jsonObject, secretKey);
	}

	private void defaultCostructor(JSONObject jsonObject, SecretKey secretKey) {

		this.spMemberID = new SimpleIntegerProperty(jsonObject.getInt("spMemberID"));
		this.spInf1Encrypted = new SimpleStringProperty(jsonObject.getString("spInf1"));
		this.spInf2Encrypted = new SimpleStringProperty(jsonObject.getString("spInf2"));
		this.spInf3Encrypted = new SimpleStringProperty(jsonObject.getString("spInf3"));
		this.spInf1Decrypted = new SimpleStringProperty(Crypt.decrypt(this.spInf1Encrypted.get(), secretKey));
		this.spInf2Decrypted = new SimpleStringProperty(Crypt.decrypt(this.spInf2Encrypted.get(), secretKey));
		this.spInf3Decrypted = new SimpleStringProperty(Crypt.decrypt(this.spInf3Encrypted.get(), secretKey));
		this.spInf4 = new SimpleIntegerProperty(jsonObject.getInt("spInf4"));
		this.spInf5 = new SimpleIntegerProperty(jsonObject.getInt("spInf5"));
		this.spInf6 = new SimpleIntegerProperty(jsonObject.getInt("spInf6"));
		this.spInf7 = new SimpleIntegerProperty(jsonObject.getInt("spInf7"));
		this.spInf8 = new SimpleIntegerProperty(jsonObject.getInt("spInf8"));
		this.spInf9 = new SimpleIntegerProperty(jsonObject.getInt("spInf9"));
		this.spInf10 = new SimpleIntegerProperty(jsonObject.getInt("spInf10"));
		this.spInf11 = new SimpleIntegerProperty(jsonObject.getInt("spInf11"));
		this.spInf12 = new SimpleIntegerProperty(jsonObject.getInt("spInf12"));
		this.spInf13 = new SimpleIntegerProperty(jsonObject.getInt("spInf13"));
		this.spInf14 = new SimpleIntegerProperty(jsonObject.getInt("spInf14"));
		this.spInf15 = new SimpleIntegerProperty(jsonObject.getInt("spInf15"));
		this.spInf16 = new SimpleIntegerProperty(jsonObject.getInt("spInf16"));
		this.spInf17 = new SimpleIntegerProperty(jsonObject.getInt("spInf17"));
		this.spInf18 = new SimpleIntegerProperty(jsonObject.getInt("spInf18"));
		this.spInf19 = new SimpleIntegerProperty(jsonObject.getInt("spInf19"));
		this.spInf20 = new SimpleIntegerProperty(jsonObject.getInt("spInf20"));
		this.spInf21 = new SimpleIntegerProperty(jsonObject.getInt("spInf21"));
		this.spInf22 = new SimpleIntegerProperty(jsonObject.getInt("spInf22"));
		this.spInf23 = new SimpleIntegerProperty(jsonObject.getInt("spInf23"));
		this.spInf24 = new SimpleIntegerProperty(jsonObject.getInt("spInf24"));
		this.spInf25 = new SimpleIntegerProperty(jsonObject.getInt("spInf25"));
		this.spInf26 = new SimpleIntegerProperty(jsonObject.getInt("spInf26"));
		this.spInf27 = new SimpleIntegerProperty(jsonObject.getInt("spInf27"));
		this.spInf28 = new SimpleIntegerProperty(jsonObject.getInt("spInf28"));
		this.spInf29 = new SimpleIntegerProperty(jsonObject.getInt("spInf29"));
	}

	public final IntegerProperty spMemberIDProperty() {
		return this.spMemberID;
	}

	public final int getSpMemberID() {
		return this.spMemberIDProperty().get();
	}

	public final void setSpMemberID(final int spMemberID) {
		this.spMemberIDProperty().set(spMemberID);
	}

	public final StringProperty spInf1EncryptedProperty() {
		return this.spInf1Encrypted;
	}

	public final String getSpInf1Encrypted() {
		return this.spInf1EncryptedProperty().get();
	}

	public final void setSpInf1Encrypted(final String spInf1Encrypted) {
		this.spInf1EncryptedProperty().set(spInf1Encrypted);
	}

	public final StringProperty spInf2EncryptedProperty() {
		return this.spInf2Encrypted;
	}

	public final String getSpInf2Encrypted() {
		return this.spInf2EncryptedProperty().get();
	}

	public final void setSpInf2Encrypted(final String spInf2Encrypted) {
		this.spInf2EncryptedProperty().set(spInf2Encrypted);
	}

	public final StringProperty spInf3EncryptedProperty() {
		return this.spInf3Encrypted;
	}

	public final String getSpInf3Encrypted() {
		return this.spInf3EncryptedProperty().get();
	}

	public final void setSpInf3Encrypted(final String spInf3Encrypted) {
		this.spInf3EncryptedProperty().set(spInf3Encrypted);
	}

	public final StringProperty spInf1DecryptedProperty() {
		return this.spInf1Decrypted;
	}

	public final String getSpInf1Decrypted() {
		return this.spInf1DecryptedProperty().get();
	}

	public final void setSpInf1Decrypted(final String spInf1Decrypted) {
		this.spInf1DecryptedProperty().set(spInf1Decrypted);
	}

	public final StringProperty spInf2DecryptedProperty() {
		return this.spInf2Decrypted;
	}

	public final String getSpInf2Decrypted() {
		return this.spInf2DecryptedProperty().get();
	}

	public final void setSpInf2Decrypted(final String spInf2Decrypted) {
		this.spInf2DecryptedProperty().set(spInf2Decrypted);
	}

	public final StringProperty spInf3DecryptedProperty() {
		return this.spInf3Decrypted;
	}

	public final String getSpInf3Decrypted() {
		return this.spInf3DecryptedProperty().get();
	}

	public final void setSpInf3Decrypted(final String spInf3Decrypted) {
		this.spInf3DecryptedProperty().set(spInf3Decrypted);
	}

	public final IntegerProperty spInf4Property() {
		return this.spInf4;
	}

	public final int getSpInf4() {
		return this.spInf4Property().get();
	}

	public final void setSpInf4(final int spInf4) {
		this.spInf4Property().set(spInf4);
	}

	public final String getNameStyle1() {
		return this.getSpInf2Decrypted() + ", " + this.getSpInf1Decrypted();
	}

	public final String getNameStyle2() {
		return this.getSpInf2Decrypted() + ", " + this.getSpInf3Decrypted();
	}

	public final IntegerProperty spInf5Property() {
		return this.spInf5;
	}

	public final int getSpInf5() {
		return this.spInf5Property().get();
	}

	public final void setSpInf5(final int spInf5) {
		this.spInf5Property().set(spInf5);
	}

	public final IntegerProperty spInf6Property() {
		return this.spInf6;
	}

	public final int getSpInf6() {
		return this.spInf6Property().get();
	}

	public final void setSpInf6(final int spInf6) {
		this.spInf6Property().set(spInf6);
	}

	public final IntegerProperty spInf7Property() {
		return this.spInf7;
	}

	public final int getSpInf7() {
		return this.spInf7Property().get();
	}

	public final void setSpInf7(final int spInf7) {
		this.spInf7Property().set(spInf7);
	}

	public final IntegerProperty spInf8Property() {
		return this.spInf8;
	}

	public final int getSpInf8() {
		return this.spInf8Property().get();
	}

	public final void setSpInf8(final int spInf8) {
		this.spInf8Property().set(spInf8);
	}

	public final IntegerProperty spInf9Property() {
		return this.spInf9;
	}

	public final int getSpInf9() {
		return this.spInf9Property().get();
	}

	public final void setSpInf9(final int spInf9) {
		this.spInf9Property().set(spInf9);
	}

	public final IntegerProperty spInf10Property() {
		return this.spInf10;
	}

	public final int getSpInf10() {
		return this.spInf10Property().get();
	}

	public final void setSpInf10(final int spInf10) {
		this.spInf10Property().set(spInf10);
	}

	public final IntegerProperty spInf11Property() {
		return this.spInf11;
	}

	public final int getSpInf11() {
		return this.spInf11Property().get();
	}

	public final void setSpInf11(final int spInf11) {
		this.spInf11Property().set(spInf11);
	}

	public final IntegerProperty spInf12Property() {
		return this.spInf12;
	}

	public final int getSpInf12() {
		return this.spInf12Property().get();
	}

	public final void setSpInf12(final int spInf12) {
		this.spInf12Property().set(spInf12);
	}

	public final IntegerProperty spInf13Property() {
		return this.spInf13;
	}

	public final int getSpInf13() {
		return this.spInf13Property().get();
	}

	public final void setSpInf13(final int spInf13) {
		this.spInf13Property().set(spInf13);
	}

	public final IntegerProperty spInf14Property() {
		return this.spInf14;
	}

	public final int getSpInf14() {
		return this.spInf14Property().get();
	}

	public final void setSpInf14(final int spInf14) {
		this.spInf14Property().set(spInf14);
	}

	public final IntegerProperty spInf15Property() {
		return this.spInf15;
	}

	public final int getSpInf15() {
		return this.spInf15Property().get();
	}

	public final void setSpInf15(final int spInf15) {
		this.spInf15Property().set(spInf15);
	}

	public final IntegerProperty spInf16Property() {
		return this.spInf16;
	}

	public final int getSpInf16() {
		return this.spInf16Property().get();
	}

	public final void setSpInf16(final int spInf16) {
		this.spInf16Property().set(spInf16);
	}

	public final IntegerProperty spInf17Property() {
		return this.spInf17;
	}

	public final int getSpInf17() {
		return this.spInf17Property().get();
	}

	public final void setSpInf17(final int spInf17) {
		this.spInf17Property().set(spInf17);
	}

	public final IntegerProperty spInf18Property() {
		return this.spInf18;
	}

	public final int getSpInf18() {
		return this.spInf18Property().get();
	}

	public final void setSpInf18(final int spInf18) {
		this.spInf18Property().set(spInf18);
	}

	public final IntegerProperty spInf19Property() {
		return this.spInf19;
	}

	public final int getSpInf19() {
		return this.spInf19Property().get();
	}

	public final void setSpInf19(final int spInf19) {
		this.spInf19Property().set(spInf19);
	}

	public final IntegerProperty spInf20Property() {
		return this.spInf20;
	}

	public final int getSpInf20() {
		return this.spInf20Property().get();
	}

	public final void setSpInf20(final int spInf20) {
		this.spInf20Property().set(spInf20);
	}

	public final IntegerProperty spInf21Property() {
		return this.spInf21;
	}

	public final int getSpInf21() {
		return this.spInf21Property().get();
	}

	public final void setSpInf21(final int spInf21) {
		this.spInf21Property().set(spInf21);
	}

	public final IntegerProperty spInf22Property() {
		return this.spInf22;
	}

	public final int getSpInf22() {
		return this.spInf22Property().get();
	}

	public final void setSpInf22(final int spInf22) {
		this.spInf22Property().set(spInf22);
	}

	public final IntegerProperty spInf23Property() {
		return this.spInf23;
	}

	public final int getSpInf23() {
		return this.spInf23Property().get();
	}

	public final void setSpInf23(final int spInf23) {
		this.spInf23Property().set(spInf23);
	}

	public final IntegerProperty spInf24Property() {
		return this.spInf24;
	}

	public final int getSpInf24() {
		return this.spInf24Property().get();
	}

	public final void setSpInf24(final int spInf24) {
		this.spInf24Property().set(spInf24);
	}

	public final IntegerProperty spInf25Property() {
		return this.spInf25;
	}

	public final int getSpInf25() {
		return this.spInf25Property().get();
	}

	public final void setSpInf25(final int spInf25) {
		this.spInf25Property().set(spInf25);
	}

	public final IntegerProperty spInf26Property() {
		return this.spInf26;
	}

	public final int getSpInf26() {
		return this.spInf26Property().get();
	}

	public final void setSpInf26(final int spInf26) {
		this.spInf26Property().set(spInf26);
	}

	public final IntegerProperty spInf27Property() {
		return this.spInf27;
	}

	public final int getSpInf27() {
		return this.spInf27Property().get();
	}

	public final void setSpInf27(final int spInf27) {
		this.spInf27Property().set(spInf27);
	}

	public final IntegerProperty spInf28Property() {
		return this.spInf28;
	}

	public final int getSpInf28() {
		return this.spInf28Property().get();
	}

	public final void setSpInf28(final int spInf28) {
		this.spInf28Property().set(spInf28);
	}

	public final IntegerProperty spInf29Property() {
		return this.spInf29;
	}

	public final int getSpInf29() {
		return this.spInf29Property().get();
	}

	public final void setSpInf29(final int spInf29) {
		this.spInf29Property().set(spInf29);
	}

}
