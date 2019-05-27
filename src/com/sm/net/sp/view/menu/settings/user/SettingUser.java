package com.sm.net.sp.view.menu.settings.user;

import java.io.IOException;

import javax.crypto.SecretKey;

import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.settings.Settings;
import com.sm.net.util.Crypt;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class SettingUser {

	@FXML
	private ImageView userImageView;
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

	private Settings settings;
	private Language language;

	@FXML
	private void initialize() {
		styleClasses();
	}

	public void objectInitialize() {
		listeners();
		viewUpdate();
		loadSettings();
	}

	private void styleClasses() {

		titleLabel.getStyleClass().add("label_setting_name");
		usernameLabel.getStyleClass().add("label_set_001");
		passwordLabel.getStyleClass().add("label_set_001");
		usernameTextField.getStyleClass().add("text_field_001");
		passwordField.getStyleClass().add("text_field_001");
	}

	private void viewUpdate() {

		this.language = settings.getLanguage();

		userImageView.setFitWidth(100);
		userImageView.setFitHeight(100);
		userImageView.setImage(Meta.Resources.MENU_SETTINGS_USER);

		titleLabel.setText(language.getString("VIEW007LAB001"));
		usernameLabel.setText(language.getString("VIEW007LAB002"));
		passwordLabel.setText(language.getString("VIEW002LAB002"));
	}

	private void listeners() {
		listenerUsernameTextField();
		listenerPasswordField();
	}

	private void listenerUsernameTextField() {

		usernameTextField.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				try {
					settings.setUsername(usernameTextField.getText());
					settings.save();
				} catch (IOException e) {
				}
			}
		});

	}

	private void listenerPasswordField() {

		passwordField.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {

				try {
					settings.setUserPasswordEncrypted(decryptPassword());
					settings.save();
				} catch (IOException e) {
				}
			}
		});

	}

	private String decryptPassword() {

		SecretKey applicationKey = settings.getApplicationKey();
		if (applicationKey != null)
			return Crypt.encrypt(passwordField.getText(), applicationKey);

		return "";
	}

	private void loadSettings() {

		usernameTextField.setText(settings.getUsername());

		SecretKey applicationKey = settings.getApplicationKey();
		if (applicationKey != null)
			passwordField.setText(Crypt.decrypt(settings.getUserPasswordEncrypted(), applicationKey));
	}

	public Settings getSettings() {
		return settings;
	}

	public void setSettings(Settings settings) {
		this.settings = settings;
	}

}
