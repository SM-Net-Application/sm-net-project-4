package com.sm.net.sp.view.home.user.menu.users;

import com.sm.net.auth.Authenticator;
import com.sm.net.auth.ValidationType;
import com.sm.net.javafx.AlertDesigner;
import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.actions.Actions;
import com.sm.net.sp.settings.Settings;
import com.sm.net.util.Crypt;

import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MenuUsersAdd {

	@FXML
	private Label titleLabel;

	@FXML
	private Label usernameLabel;

	@FXML
	private Label passwordLabel;

	@FXML
	private TextField usernameTextField;

	@FXML
	private PasswordField passwordField;

	@FXML
	private Button createUserButton;

	private Settings settings;
	private Language language;
	private Stage ownerStage;
	private HomeUserMenuUsersList ownerCtrl;
	private Stage thisStage;

	@FXML
	private void initialize() {
		styleClasses();
	}

	private void styleClasses() {

		titleLabel.getStyleClass().add("labelStyle2");
		usernameLabel.getStyleClass().add("labelStyle1");
		passwordLabel.getStyleClass().add("labelStyle1");

		usernameTextField.getStyleClass().add("textFieldStyle1");
		passwordField.getStyleClass().add("textFieldStyle1");

		createUserButton.getStyleClass().add("buttonStyle1");
	}

	public void objectInitialize() {
		listeners();
		viewUpdate();
	}

	private void listeners() {
		listenerCreateUserButton();
	}

	private void listenerCreateUserButton() {

		createUserButton.setOnAction(event -> {

			String user = usernameTextField.getText();
			String password = passwordField.getText();

			if (checkFields(user, password)) {

				String userEncrypted = Crypt.encrypt(user, settings.getDatabaseSecretKey());
				String passwordEncrypted = Crypt.encrypt(password, settings.getDatabaseSecretKey());

				Actions.insertNewUser(settings.getDatabaseUrl(), userEncrypted, passwordEncrypted, settings, thisStage,
						ownerCtrl);

			} else
				new AlertDesigner(language.getStringWithNewLine("TEXT0004"), language.getStringWithNewLine("MEX002"),
						thisStage, AlertType.ERROR, Meta.Application.getFullTitle(), Meta.Resources.ICON).show();
		});
	}

	private boolean checkFields(String user, String password) {

		boolean check = true;
		check = checkUsername(user);
		if (check)
			check = checkPassword(password);

		return check;
	}

	private boolean checkUsername(String user) {

		boolean check = true;
		if (!Authenticator.isValid(user, ValidationType.VERY_STRONG))
			check = false;
		return check;
	}

	private boolean checkPassword(String pwd) {

		boolean check = true;
		if (!Authenticator.isValid(pwd, ValidationType.VERY_STRONG))
			check = false;
		return check;
	}

	private void viewUpdate() {

		this.language = settings.getLanguage();

		titleLabel.setText(language.getString("TEXT0003"));
		usernameLabel.setText(language.getString("VIEW007LAB002"));
		passwordLabel.setText(language.getString("VIEW002LAB002"));
		createUserButton.setText(language.getString("VIEW002BUT001"));
	}

	public Settings getSettings() {
		return settings;
	}

	public void setSettings(Settings settings) {
		this.settings = settings;
	}

	public Stage getOwnerStage() {
		return ownerStage;
	}

	public void setOwnerStage(Stage ownerStage) {
		this.ownerStage = ownerStage;
	}

	public HomeUserMenuUsersList getOwnerCtrl() {
		return ownerCtrl;
	}

	public void setOwnerCtrl(HomeUserMenuUsersList ownerCtrl) {
		this.ownerCtrl = ownerCtrl;
	}

	public Stage getThisStage() {
		return thisStage;
	}

	public void setThisStage(Stage thisStage) {
		this.thisStage = thisStage;
	}
}
