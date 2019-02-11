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
import javafx.stage.Stage;

public class User {

	private IntegerProperty userID;
	private boolean spUserSU;
	private String usernameEncrypted;
	private StringProperty usernameProperty;
	private BooleanProperty roleAdminProperty;

	public User(JSONObject jsonObject, SecretKey secretKey) {
		super();
		defaultCostructor(jsonObject, secretKey);
	}

	public User(JSONObject jsonObject, SecretKey secretKey, Settings settings, Stage ownerStage, UpdateData callback) {
		super();
		defaultCostructor(jsonObject, secretKey);
		this.roleAdminProperty.addListener((observable, oldValue, newValue) -> Actions.updateUserRules(getSpUserID(),
				getSpRole(roleAdminProperty.get()), settings, ownerStage, callback));
	}

	private void defaultCostructor(JSONObject jsonObject, SecretKey secretKey) {

		this.userID = new SimpleIntegerProperty(jsonObject.getInt("spUserID"));
		this.spUserSU = checkBoolean(jsonObject.getInt("spUserSU"));
		this.usernameEncrypted = jsonObject.getString("spUserName");
		this.usernameProperty = new SimpleStringProperty(usernameDecrypt(secretKey));
		this.roleAdminProperty = new SimpleBooleanProperty(checkBoolean(jsonObject.getInt("spRoleAdmin")));
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

	public boolean isRoleAdmin() {
		return roleAdminProperty.get();
	}

	public void setRoleAdmin(boolean roleAdmin) {
		this.roleAdminProperty.set(roleAdmin);
	}

	public StringProperty getUsernameProperty() {
		return usernameProperty;
	}

	public void setUsernameProperty(StringProperty usernameProperty) {
		this.usernameProperty = usernameProperty;
	}

	public BooleanProperty getRoleAdminProperty() {
		return roleAdminProperty;
	}

	public void setRoleAdminProperty(BooleanProperty roleAdminProperty) {
		this.roleAdminProperty = roleAdminProperty;
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

}
