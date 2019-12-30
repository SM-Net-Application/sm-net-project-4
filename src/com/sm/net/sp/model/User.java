package com.sm.net.sp.model;

import javax.crypto.SecretKey;

import org.json.JSONObject;

import com.sm.net.sp.actions.Actions;
import com.sm.net.sp.settings.Settings;
import com.sm.net.util.Crypt;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.stage.Stage;

public class User {

	private IntegerProperty userID;
	private boolean spUserSU;
	private String usernameEncrypted;
	private StringProperty usernameProperty;

	private BooleanProperty spInf1;
	private BooleanProperty spInf2;
	private BooleanProperty spInf3;
	private BooleanProperty spInf4;
	private BooleanProperty spInf5;
	private BooleanProperty spInf6;
	private BooleanProperty spInf7;
	private BooleanProperty spInf8;

	private StringProperty passwordEncrypted;

	public User(JSONObject jsonObject, SecretKey secretKey) {
		super();
		defaultCostructor(jsonObject, secretKey);
	}

	public User(JSONObject jsonObject, SecretKey secretKey, Settings settings, Stage ownerStage, UpdateData callback) {
		super();
		defaultCostructor(jsonObject, secretKey);

		this.spInf1.addListener(listenerUpdateRules(settings, ownerStage, callback));
		this.spInf2.addListener(listenerUpdateRules(settings, ownerStage, callback));
		this.spInf3.addListener(listenerUpdateRules(settings, ownerStage, callback));
		this.spInf4.addListener(listenerUpdateRules(settings, ownerStage, callback));
		this.spInf5.addListener(listenerUpdateRules(settings, ownerStage, callback));
		this.spInf6.addListener(listenerUpdateRules(settings, ownerStage, callback));
		this.spInf7.addListener(listenerUpdateRules(settings, ownerStage, callback));
		this.spInf8.addListener(listenerUpdateRules(settings, ownerStage, callback));
	}

	private ChangeListener<? super Boolean> listenerUpdateRules(Settings settings, Stage ownerStage,
			UpdateData callback) {

		return (observable, oldValue, newValue) -> Actions.updateUserRules(getSpUserID(), getSpRole(spInf1.get()),
				getSpRole(spInf2.get()), getSpRole(spInf3.get()), getSpRole(spInf4.get()), getSpRole(spInf5.get()),
				getSpRole(spInf6.get()), getSpRole(spInf7.get()), getSpRole(spInf8.get()), settings, ownerStage,
				callback);
	}

	private void defaultCostructor(JSONObject jsonObject, SecretKey secretKey) {

		this.userID = new SimpleIntegerProperty(jsonObject.getInt("spUserID"));
		this.spUserSU = checkBoolean(jsonObject.getInt("spUserSU"));
		this.usernameEncrypted = jsonObject.getString("spUserName");
		this.usernameProperty = new SimpleStringProperty(usernameDecrypt(secretKey));

		this.spInf1 = new SimpleBooleanProperty(checkBoolean(jsonObject.getInt("spInf1")));
		this.spInf2 = new SimpleBooleanProperty(checkBoolean(jsonObject.getInt("spInf2")));
		this.spInf3 = new SimpleBooleanProperty(checkBoolean(jsonObject.getInt("spInf3")));
		this.spInf4 = new SimpleBooleanProperty(checkBoolean(jsonObject.getInt("spInf4")));
		this.spInf5 = new SimpleBooleanProperty(checkBoolean(jsonObject.getInt("spInf5")));
		this.spInf6 = new SimpleBooleanProperty(checkBoolean(jsonObject.getInt("spInf6")));
		this.spInf7 = new SimpleBooleanProperty(checkBoolean(jsonObject.getInt("spInf7")));
		this.spInf8 = new SimpleBooleanProperty(checkBoolean(jsonObject.getInt("spInf8")));

		this.passwordEncrypted = new SimpleStringProperty(jsonObject.getString("spUserPassword"));
	}

	private String getSpRole(boolean role) {
		return role ? "1" : "0";
	}

	private String getSpUserID() {
		return String.valueOf(this.userID.get());
	}

	private boolean checkBoolean(int role) {
		return (role == 0) ? false : true;
	}

	private String usernameDecrypt(SecretKey secretKey) {
		return Crypt.decrypt(this.usernameEncrypted, secretKey);
	}

	public String getUsernameCrypted() {
		return usernameEncrypted;
	}

	public void setUsernameCrypted(String usernameCrypted) {
		this.usernameEncrypted = usernameCrypted;
	}

	public String getUsername() {
		return usernameProperty.get();
	}

	public void setUsername(String username) {
		this.usernameProperty.set(username);
	}

	public StringProperty getUsernameProperty() {
		return usernameProperty;
	}

	public void setUsernameProperty(StringProperty usernameProperty) {
		this.usernameProperty = usernameProperty;
	}

	public IntegerProperty getUserID() {
		return userID;
	}

	public void setUserID(IntegerProperty userID) {
		this.userID = userID;
	}

	public boolean isSpUserSU() {
		return spUserSU;
	}

	public void setSpUserSU(boolean spUserSU) {
		this.spUserSU = spUserSU;
	}

	public final BooleanProperty spInf1Property() {
		return this.spInf1;
	}

	public final boolean isSpInf1() {
		return this.spInf1Property().get();
	}

	public final void setSpInf1(final boolean spInf1) {
		this.spInf1Property().set(spInf1);
	}

	public final BooleanProperty spInf2Property() {
		return this.spInf2;
	}

	public final boolean isSpInf2() {
		return this.spInf2Property().get();
	}

	public final void setSpInf2(final boolean spInf2) {
		this.spInf2Property().set(spInf2);
	}

	public final BooleanProperty spInf3Property() {
		return this.spInf3;
	}

	public final boolean isSpInf3() {
		return this.spInf3Property().get();
	}

	public final void setSpInf3(final boolean spInf3) {
		this.spInf3Property().set(spInf3);
	}

	public final BooleanProperty spInf4Property() {
		return this.spInf4;
	}

	public final boolean isSpInf4() {
		return this.spInf4Property().get();
	}

	public final void setSpInf4(final boolean spInf4) {
		this.spInf4Property().set(spInf4);
	}

	public final StringProperty passwordEncryptedProperty() {
		return this.passwordEncrypted;
	}

	public final String getPasswordEncrypted() {
		return this.passwordEncryptedProperty().get();
	}

	public final void setPasswordEncrypted(final String passwordEncrypted) {
		this.passwordEncryptedProperty().set(passwordEncrypted);
	}

	public final BooleanProperty spInf5Property() {
		return this.spInf5;
	}

	public final boolean isSpInf5() {
		return this.spInf5Property().get();
	}

	public final void setSpInf5(final boolean spInf5) {
		this.spInf5Property().set(spInf5);
	}

	public final BooleanProperty spInf6Property() {
		return this.spInf6;
	}

	public final boolean isSpInf6() {
		return this.spInf6Property().get();
	}

	public final void setSpInf6(final boolean spInf6) {
		this.spInf6Property().set(spInf6);
	}

	public final BooleanProperty spInf7Property() {
		return this.spInf7;
	}

	public final boolean isSpInf7() {
		return this.spInf7Property().get();
	}

	public final void setSpInf7(final boolean spInf7) {
		this.spInf7Property().set(spInf7);
	}

	public final BooleanProperty spInf8Property() {
		return this.spInf8;
	}

	public final boolean isSpInf8() {
		return this.spInf8Property().get();
	}

	public final void setSpInf8(final boolean spInf8) {
		this.spInf8Property().set(spInf8);
	}
}
