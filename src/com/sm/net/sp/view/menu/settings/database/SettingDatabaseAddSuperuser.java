package com.sm.net.sp.view.menu.settings.database;

import javax.crypto.SecretKey;

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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SettingDatabaseAddSuperuser {

	@FXML
	private AnchorPane anchorPane;
	@FXML
	private ImageView superuserImageView;
	@FXML
	private Label superuserLabel;
	@FXML
	private Label usernameLabel;
	@FXML
	private Label passwordLabel;
	@FXML
	private Label keyLabel;
	@FXML
	private TextField usernameTextField;
	@FXML
	private PasswordField passwordField;
	@FXML
	private PasswordField keyPasswordField;
	@FXML
	private Button createButton;

	private Settings settings;
	private Language language;
	private Stage thisStage;

	@FXML
	private void initialize() {
		styleClasses();
	}

	private void styleClasses() {

		anchorPane.getStyleClass().add("main_color_001");

		superuserLabel.getStyleClass().add("label_setting_name");

		usernameLabel.getStyleClass().add("label_set_001");
		passwordLabel.getStyleClass().add("label_set_001");
		keyLabel.getStyleClass().add("label_set_001");

		usernameTextField.getStyleClass().add("text_field_001");
		passwordField.getStyleClass().add("text_field_001");
		keyPasswordField.getStyleClass().add("text_field_001");

		createButton.getStyleClass().add("button_image_001");
	}

	public void objectInitialize() {
		listeners();
		viewUpdate();
	}

	private void viewUpdate() {

		this.language = settings.getLanguage();

		superuserImageView.setFitWidth(100);
		superuserImageView.setFitHeight(100);
		superuserImageView.setImage(Meta.Resources.getImageLogo(Meta.Resources.SUPERUSER, 100, 100));

		superuserLabel.setText(language.getString("TEXT0008"));

		usernameLabel.setText(language.getString("VIEW007LAB002"));
		passwordLabel.setText(language.getString("VIEW002LAB002"));
		keyLabel.setText(language.getString("VIEW005LAB003"));

		createButton.setMinWidth(200);
		createButton.setText(language.getString("VIEW002BUT001"));
		createButton.setGraphic(Meta.Resources.imageViewForButton(Meta.Resources.SAVE));
	}

	private void listeners() {
		listenerCreateButton();
	}

	private void listenerCreateButton() {

		createButton.setOnAction(event -> createSuperuser());
	}

	private void createSuperuser() {

		String user = usernameTextField.getText();
		String password = passwordField.getText();
		String key = keyPasswordField.getText();

		if (checkFields(user, password, key)) {

			SecretKey secretKey = Crypt.generateKey(key);

			if (secretKey != null) {

				String userEnc = Crypt.encrypt(user, secretKey);
				String passwordEnc = Crypt.encrypt(password, secretKey);

				if (userEnc != null && passwordEnc != null)
					Actions.insertRootUser(userEnc, passwordEnc, settings, thisStage);

			}
		} else
			new AlertDesigner(language.getStringWithNewLine("TEXT0004"), language.getStringWithNewLine("MEX002"),
					thisStage, AlertType.ERROR, Meta.Application.getFullTitle(),
					Meta.Resources.getImageApplicationIcon(), Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").show();
	}

	protected boolean checkFields(String user, String password, String key) {

		boolean check = true;
		check = Authenticator.isValid(user, ValidationType.VERY_STRONG);
		if (check)
			check = Authenticator.isValid(password, ValidationType.VERY_STRONG);
		if (check)
			check = Authenticator.isValid(key, ValidationType.VERY_STRONG);

		return check;
	}

	public Settings getSettings() {
		return settings;
	}

	public void setSettings(Settings settings) {
		this.settings = settings;
	}

	public Stage getThisStage() {
		return thisStage;
	}

	public void setThisStage(Stage thisStage) {
		this.thisStage = thisStage;
	}
}
