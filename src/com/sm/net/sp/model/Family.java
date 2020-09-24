package com.sm.net.sp.model;

import javax.crypto.SecretKey;

import org.json.JSONObject;

import com.sm.net.project.Language;
import com.sm.net.util.Crypt;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Family {

	private IntegerProperty spFamID;
	private StringProperty spInf1Encrypted;
	private StringProperty spInf2Encrypted;
	private StringProperty spInf3Encrypted;
	private StringProperty spInf4Encrypted;
	private StringProperty spInf5Encrypted;
	private StringProperty spInf1Decrypted;
	private StringProperty spInf2Decrypted;
	private StringProperty spInf3Decrypted;
	private StringProperty spInf4Decrypted;
	private StringProperty spInf5Decrypted;

	private IntegerProperty spInf6;

	private IntegerProperty spFamMembers;

	private StringProperty spInf7Encrypted;
	private StringProperty spInf7Decrypted;

	private IntegerProperty spInf8;

	private StringProperty spInf9Encrypted;
	private StringProperty spInf9Decrypted;

	private IntegerProperty spInf10;

	public Family(JSONObject jsonObject, SecretKey secretKey) {
		super();
		defaultCostructor(jsonObject, secretKey);
	}

	public Family() {
	}

	private void defaultCostructor(JSONObject jsonObject, SecretKey secretKey) {

		this.spFamID = new SimpleIntegerProperty(jsonObject.getInt("spFamID"));

		this.spInf1Encrypted = new SimpleStringProperty(jsonObject.getString("spInf1"));
		this.spInf2Encrypted = new SimpleStringProperty(jsonObject.getString("spInf2"));
		this.spInf3Encrypted = new SimpleStringProperty(jsonObject.getString("spInf3"));
		this.spInf4Encrypted = new SimpleStringProperty(jsonObject.getString("spInf4"));
		this.spInf5Encrypted = new SimpleStringProperty(jsonObject.getString("spInf5"));

		this.spInf1Decrypted = new SimpleStringProperty(Crypt.decrypt(this.spInf1Encrypted.get(), secretKey));
		this.spInf2Decrypted = new SimpleStringProperty(Crypt.decrypt(this.spInf2Encrypted.get(), secretKey));
		this.spInf3Decrypted = new SimpleStringProperty(Crypt.decrypt(this.spInf3Encrypted.get(), secretKey));
		this.spInf4Decrypted = new SimpleStringProperty(Crypt.decrypt(this.spInf4Encrypted.get(), secretKey));
		this.spInf5Decrypted = new SimpleStringProperty(Crypt.decrypt(this.spInf5Encrypted.get(), secretKey));

		this.spFamMembers = new SimpleIntegerProperty(jsonObject.getInt("spFamMembers"));

		this.spInf6 = new SimpleIntegerProperty(jsonObject.getInt("spInf6"));

		this.spInf7Encrypted = new SimpleStringProperty(jsonObject.getString("spInf7"));
		this.spInf7Decrypted = new SimpleStringProperty(Crypt.decrypt(this.spInf7Encrypted.get(), secretKey));

		this.spInf8 = new SimpleIntegerProperty(jsonObject.getInt("spInf8"));

		this.spInf9Encrypted = new SimpleStringProperty(jsonObject.getString("spInf9"));
		this.spInf9Decrypted = new SimpleStringProperty(Crypt.decrypt(this.spInf9Encrypted.get(), secretKey));

		this.spInf10 = new SimpleIntegerProperty(jsonObject.getInt("spInf10"));
	}

	public static Family emptyFamily(Language language) {

		Family family = new Family();

		family.spFamID = new SimpleIntegerProperty(0);
		family.spInf1Encrypted = new SimpleStringProperty("");
		family.spInf1Decrypted = new SimpleStringProperty(language.getString("TEXT0096"));
		family.spInf2Encrypted = new SimpleStringProperty("");
		family.spInf2Decrypted = new SimpleStringProperty("");
		family.spInf3Encrypted = new SimpleStringProperty("");
		family.spInf3Decrypted = new SimpleStringProperty("");
		family.spInf4Encrypted = new SimpleStringProperty("");
		family.spInf4Decrypted = new SimpleStringProperty("");
		family.spInf5Encrypted = new SimpleStringProperty("");
		family.spInf5Decrypted = new SimpleStringProperty("");
		family.spInf6 = new SimpleIntegerProperty(0);
		family.spInf7Encrypted = new SimpleStringProperty("");
		family.spInf7Decrypted = new SimpleStringProperty("");
		family.spInf8 = new SimpleIntegerProperty(0);
		family.spInf9Encrypted = new SimpleStringProperty("");
		family.spInf9Decrypted = new SimpleStringProperty("");
		family.spInf10 = new SimpleIntegerProperty(0);

		return family;
	}

	@Override
	public String toString() {
		return this.getSpInf1Decrypted();
	}

	public final IntegerProperty spFamIDProperty() {
		return this.spFamID;
	}

	public final int getSpFamID() {
		return this.spFamIDProperty().get();
	}

	public final void setSpFamID(final int spFamID) {
		this.spFamIDProperty().set(spFamID);
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

	public final StringProperty spInf4EncryptedProperty() {
		return this.spInf4Encrypted;
	}

	public final String getSpInf4Encrypted() {
		return this.spInf4EncryptedProperty().get();
	}

	public final void setSpInf4Encrypted(final String spInf4Encrypted) {
		this.spInf4EncryptedProperty().set(spInf4Encrypted);
	}

	public final StringProperty spInf5EncryptedProperty() {
		return this.spInf5Encrypted;
	}

	public final String getSpInf5Encrypted() {
		return this.spInf5EncryptedProperty().get();
	}

	public final void setSpInf5Encrypted(final String spInf5Encrypted) {
		this.spInf5EncryptedProperty().set(spInf5Encrypted);
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

	public final StringProperty spInf4DecryptedProperty() {
		return this.spInf4Decrypted;
	}

	public final String getSpInf4Decrypted() {
		return this.spInf4DecryptedProperty().get();
	}

	public final void setSpInf4Decrypted(final String spInf4Decrypted) {
		this.spInf4DecryptedProperty().set(spInf4Decrypted);
	}

	public final StringProperty spInf5DecryptedProperty() {
		return this.spInf5Decrypted;
	}

	public final String getSpInf5Decrypted() {
		return this.spInf5DecryptedProperty().get();
	}

	public final void setSpInf5Decrypted(final String spInf5Decrypted) {
		this.spInf5DecryptedProperty().set(spInf5Decrypted);
	}

	public final IntegerProperty spFamMembersProperty() {
		return this.spFamMembers;
	}

	public final int getSpFamMembers() {
		return this.spFamMembersProperty().get();
	}

	public final void setSpFamMembers(final int spFamMembers) {
		this.spFamMembersProperty().set(spFamMembers);
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

	public final StringProperty spInf7EncryptedProperty() {
		return this.spInf7Encrypted;
	}

	public final String getSpInf7Encrypted() {
		return this.spInf7EncryptedProperty().get();
	}

	public final void setSpInf7Encrypted(final String spInf7Encrypted) {
		this.spInf7EncryptedProperty().set(spInf7Encrypted);
	}

	public final StringProperty spInf7DecryptedProperty() {
		return this.spInf7Decrypted;
	}

	public final String getSpInf7Decrypted() {
		return this.spInf7DecryptedProperty().get();
	}

	public final void setSpInf7Decrypted(final String spInf7Decrypted) {
		this.spInf7DecryptedProperty().set(spInf7Decrypted);
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

	public final StringProperty spInf9EncryptedProperty() {
		return this.spInf9Encrypted;
	}

	public final String getSpInf9Encrypted() {
		return this.spInf9EncryptedProperty().get();
	}

	public final void setSpInf9Encrypted(final String spInf9Encrypted) {
		this.spInf9EncryptedProperty().set(spInf9Encrypted);
	}

	public final StringProperty spInf9DecryptedProperty() {
		return this.spInf9Decrypted;
	}

	public final String getSpInf9Decrypted() {
		return this.spInf9DecryptedProperty().get();
	}

	public final void setSpInf9Decrypted(final String spInf9Decrypted) {
		this.spInf9DecryptedProperty().set(spInf9Decrypted);
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

}
