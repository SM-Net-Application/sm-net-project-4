package com.sm.net.sp.model;

import javax.crypto.SecretKey;

import org.json.JSONObject;

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

	private BooleanProperty spInf1;
	private BooleanProperty spInf2;
	private BooleanProperty spInf3;
	private BooleanProperty spInf4;
	private BooleanProperty spInf5;
	private BooleanProperty spInf6;
	private BooleanProperty spInf7;
	private BooleanProperty spInf8;

	private BooleanProperty spInf9;
	private BooleanProperty spInf10;
	private BooleanProperty spInf11;
	private BooleanProperty spInf12;
	private BooleanProperty spInf13;
	private BooleanProperty spInf14;
	private BooleanProperty spInf15;
	private BooleanProperty spInf16;
	private BooleanProperty spInf17;
	private BooleanProperty spInf18;
	private IntegerProperty spInf19;
	private BooleanProperty spInf20;

	private StringProperty passwordEncrypted;

	public User(JSONObject jsonObject, SecretKey secretKey) {
		super();
		defaultCostructor(jsonObject, secretKey);
	}

	public User(JSONObject jsonObject, SecretKey secretKey, Settings settings, Stage ownerStage, UpdateData callback) {
		super();
		defaultCostructor(jsonObject, secretKey);

//		this.spInf1.addListener(listenerUpdateRules(settings, ownerStage, callback));
//		this.spInf2.addListener(listenerUpdateRules(settings, ownerStage, callback));
//		this.spInf3.addListener(listenerUpdateRules(settings, ownerStage, callback));
//		this.spInf4.addListener(listenerUpdateRules(settings, ownerStage, callback));
//		this.spInf5.addListener(listenerUpdateRules(settings, ownerStage, callback));
//		this.spInf6.addListener(listenerUpdateRules(settings, ownerStage, callback));
//		this.spInf7.addListener(listenerUpdateRules(settings, ownerStage, callback));
//		this.spInf8.addListener(listenerUpdateRules(settings, ownerStage, callback));
	}

//	private ChangeListener<? super Boolean> listenerUpdateRules(Settings settings, Stage ownerStage,
//			UpdateData callback) {
//
//		return (observable, oldValue, newValue) -> Actions.updateUserRules(getSpUserID(), getSpRole(spInf1.get()),
//				getSpRole(spInf2.get()), getSpRole(spInf3.get()), getSpRole(spInf4.get()), getSpRole(spInf5.get()),
//				getSpRole(spInf6.get()), getSpRole(spInf7.get()), getSpRole(spInf8.get()), settings, ownerStage,
//				callback);
//	}

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

		this.spInf9 = new SimpleBooleanProperty(checkBoolean(jsonObject.getInt("spInf9")));
		this.spInf10 = new SimpleBooleanProperty(checkBoolean(jsonObject.getInt("spInf10")));
		this.spInf11 = new SimpleBooleanProperty(checkBoolean(jsonObject.getInt("spInf11")));
		this.spInf12 = new SimpleBooleanProperty(checkBoolean(jsonObject.getInt("spInf12")));
		this.spInf13 = new SimpleBooleanProperty(checkBoolean(jsonObject.getInt("spInf13")));
		this.spInf14 = new SimpleBooleanProperty(checkBoolean(jsonObject.getInt("spInf14")));
		this.spInf15 = new SimpleBooleanProperty(checkBoolean(jsonObject.getInt("spInf15")));
		this.spInf16 = new SimpleBooleanProperty(checkBoolean(jsonObject.getInt("spInf16")));
		this.spInf17 = new SimpleBooleanProperty(checkBoolean(jsonObject.getInt("spInf17")));
		this.spInf18 = new SimpleBooleanProperty(checkBoolean(jsonObject.getInt("spInf18")));
		this.spInf19 = new SimpleIntegerProperty(jsonObject.getInt("spInf19"));
		this.spInf20 = new SimpleBooleanProperty(checkBoolean(jsonObject.getInt("spInf20")));

		this.passwordEncrypted = new SimpleStringProperty(jsonObject.getString("spUserPassword"));
	}

//	private String getSpRole(boolean role) {
//		return role ? "1" : "0";
//	}
//
//	private String getSpUserID() {
//		return String.valueOf(this.userID.get());
//	}

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

	public final BooleanProperty spInf9Property() {
		return this.spInf9;
	}

	public final boolean isSpInf9() {
		return this.spInf9Property().get();
	}

	public final void setSpInf9(final boolean spInf9) {
		this.spInf9Property().set(spInf9);
	}

	public final BooleanProperty spInf10Property() {
		return this.spInf10;
	}

	public final boolean isSpInf10() {
		return this.spInf10Property().get();
	}

	public final void setSpInf10(final boolean spInf10) {
		this.spInf10Property().set(spInf10);
	}

	public final BooleanProperty spInf11Property() {
		return this.spInf11;
	}

	public final boolean isSpInf11() {
		return this.spInf11Property().get();
	}

	public final void setSpInf11(final boolean spInf11) {
		this.spInf11Property().set(spInf11);
	}

	public final BooleanProperty spInf12Property() {
		return this.spInf12;
	}

	public final boolean isSpInf12() {
		return this.spInf12Property().get();
	}

	public final void setSpInf12(final boolean spInf12) {
		this.spInf12Property().set(spInf12);
	}

	public final BooleanProperty spInf13Property() {
		return this.spInf13;
	}

	public final boolean isSpInf13() {
		return this.spInf13Property().get();
	}

	public final void setSpInf13(final boolean spInf13) {
		this.spInf13Property().set(spInf13);
	}

	public final BooleanProperty spInf14Property() {
		return this.spInf14;
	}

	public final boolean isSpInf14() {
		return this.spInf14Property().get();
	}

	public final void setSpInf14(final boolean spInf14) {
		this.spInf14Property().set(spInf14);
	}

	public final BooleanProperty spInf15Property() {
		return this.spInf15;
	}

	public final boolean isSpInf15() {
		return this.spInf15Property().get();
	}

	public final void setSpInf15(final boolean spInf15) {
		this.spInf15Property().set(spInf15);
	}

	public final BooleanProperty spInf16Property() {
		return this.spInf16;
	}

	public final boolean isSpInf16() {
		return this.spInf16Property().get();
	}

	public final void setSpInf16(final boolean spInf16) {
		this.spInf16Property().set(spInf16);
	}

	public final BooleanProperty spInf17Property() {
		return this.spInf17;
	}

	public final boolean isSpInf17() {
		return this.spInf17Property().get();
	}

	public final void setSpInf17(final boolean spInf17) {
		this.spInf17Property().set(spInf17);
	}

	public final BooleanProperty spInf18Property() {
		return this.spInf18;
	}

	public final boolean isSpInf18() {
		return this.spInf18Property().get();
	}

	public final void setSpInf18(final boolean spInf18) {
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

	public final BooleanProperty spInf20Property() {
		return this.spInf20;
	}

	public final boolean isSpInf20() {
		return this.spInf20Property().get();
	}

	public final void setSpInf20(final boolean spInf20) {
		this.spInf20Property().set(spInf20);
	}
}
