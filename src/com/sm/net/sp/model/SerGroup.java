package com.sm.net.sp.model;

import javax.crypto.SecretKey;

import org.json.JSONObject;

import com.sm.net.project.Language;
import com.sm.net.util.Crypt;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SerGroup {

	private IntegerProperty spSerGrID;
	private StringProperty spInf1Encrypted;
	private StringProperty spInf1Decrypted;

	private IntegerProperty spInf2;
	private IntegerProperty spInf3;

	private StringProperty overseer;
	private StringProperty assistant;

	private IntegerProperty spSerGroupFamilies;
	private IntegerProperty spSerGroupMembers;

	public SerGroup(JSONObject jsonObject, SecretKey secretKey) {
		super();
		defaultCostructor(jsonObject, secretKey);
	}

	public SerGroup() {
	}

	private void defaultCostructor(JSONObject jsonObject, SecretKey secretKey) {

		this.spSerGrID = new SimpleIntegerProperty(jsonObject.getInt("spSerGrID"));

		this.spInf1Encrypted = new SimpleStringProperty(jsonObject.getString("spInf1"));
		this.spInf1Decrypted = new SimpleStringProperty(Crypt.decrypt(this.spInf1Encrypted.get(), secretKey));

		String overseerName = Crypt.decrypt(jsonObject.getString("O1"), secretKey);
		String overseerSurname = Crypt.decrypt(jsonObject.getString("O2"), secretKey);
		if (!overseerName.isEmpty() && !overseerSurname.isEmpty())
			this.overseer = new SimpleStringProperty(overseerSurname + ", " + overseerName);

		String assistantName = Crypt.decrypt(jsonObject.getString("A1"), secretKey);
		String assistantSurname = Crypt.decrypt(jsonObject.getString("A2"), secretKey);
		if (!assistantName.isEmpty() && !assistantSurname.isEmpty())
			this.assistant = new SimpleStringProperty(assistantSurname + ", " + assistantName);

		this.spInf2 = new SimpleIntegerProperty(jsonObject.getInt("spInf2"));
		this.spInf3 = new SimpleIntegerProperty(jsonObject.getInt("spInf3"));

		this.spSerGroupFamilies = new SimpleIntegerProperty(jsonObject.getInt("spSerGroupFamilies"));
		this.spSerGroupMembers = new SimpleIntegerProperty(jsonObject.getInt("spSerGroupMembers"));
	}

	public final IntegerProperty spSerGrIDProperty() {
		return this.spSerGrID;
	}

	public final int getSpSerGrID() {
		return this.spSerGrIDProperty().get();
	}

	public final void setSpSerGrID(final int spSerGrID) {
		this.spSerGrIDProperty().set(spSerGrID);
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

	public final StringProperty spInf1DecryptedProperty() {
		return this.spInf1Decrypted;
	}

	public final String getSpInf1Decrypted() {
		return this.spInf1DecryptedProperty().get();
	}

	public final void setSpInf1Decrypted(final String spInf1Decrypted) {
		this.spInf1DecryptedProperty().set(spInf1Decrypted);
	}

	public final IntegerProperty spInf2Property() {
		return this.spInf2;
	}

	public final int getSpInf2() {
		return this.spInf2Property().get();
	}

	public final void setSpInf2(final int spInf2) {
		this.spInf2Property().set(spInf2);
	}

	public final IntegerProperty spInf3Property() {
		return this.spInf3;
	}

	public final int getSpInf3() {
		return this.spInf3Property().get();
	}

	public final void setSpInf3(final int spInf3) {
		this.spInf3Property().set(spInf3);
	}

	public final IntegerProperty spSerGroupFamiliesProperty() {
		return this.spSerGroupFamilies;
	}

	public final int getSpSerGroupFamilies() {
		return this.spSerGroupFamiliesProperty().get();
	}

	public final void setSpSerGroupFamilies(final int spSerGroupFamilies) {
		this.spSerGroupFamiliesProperty().set(spSerGroupFamilies);
	}

	public final IntegerProperty spSerGroupMembersProperty() {
		return this.spSerGroupMembers;
	}

	public final int getSpSerGroupMembers() {
		return this.spSerGroupMembersProperty().get();
	}

	public final void setSpSerGroupMembers(final int spSerGroupMembers) {
		this.spSerGroupMembersProperty().set(spSerGroupMembers);
	}

	public final StringProperty overseerProperty() {
		return this.overseer;
	}

	public final String getOverseer() {
		return this.overseerProperty().get();
	}

	public final void setOverseer(final String overseer) {
		this.overseerProperty().set(overseer);
	}

	public final StringProperty assistantProperty() {
		return this.assistant;
	}

	public final String getAssistant() {
		return this.assistantProperty().get();
	}

	public final void setAssistant(final String assistant) {
		this.assistantProperty().set(assistant);
	}

	public static SerGroup emptySerGroup(Language language) {

		SerGroup serGroup = new SerGroup();

		serGroup.spSerGrID = new SimpleIntegerProperty(0);
		serGroup.spInf1Encrypted = new SimpleStringProperty("");
		serGroup.spInf1Decrypted = new SimpleStringProperty(language.getString("TEXT0096"));
		serGroup.spInf2 = new SimpleIntegerProperty(0);
		serGroup.spInf3 = new SimpleIntegerProperty(0);
		serGroup.overseer = new SimpleStringProperty("");
		serGroup.assistant = new SimpleStringProperty("");
		serGroup.spSerGroupFamilies = new SimpleIntegerProperty(0);
		serGroup.spSerGroupFamilies = new SimpleIntegerProperty(0);

		return serGroup;
	}

	@Override
	public String toString() {
		return this.spInf1Decrypted.get();
	}
}
