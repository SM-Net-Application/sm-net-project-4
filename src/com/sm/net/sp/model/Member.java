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

}
