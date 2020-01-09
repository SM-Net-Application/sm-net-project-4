package com.sm.net.sp.view.menu.settings.user;

import java.io.IOException;

import javax.crypto.SecretKey;

import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.model.User;
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
	@FXML
	private Label passwordMonitorLabel;
	@FXML
	private PasswordField passwordMonitorPasswordField;

	private Settings settings;
	private Language language;
	private User loggedUser;

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
		passwordMonitorLabel.getStyleClass().add("label_set_001");
		passwordMonitorPasswordField.getStyleClass().add("text_field_001");
	}

	private void viewUpdate() {

		this.language = settings.getLanguage();

		userImageView.setFitWidth(100);
		userImageView.setFitHeight(100);
		userImageView.setImage(Meta.Resources.getImageLogo(Meta.Resources.MENU_SETTINGS_USER, 100, 100));

		titleLabel.setText(language.getString("VIEW007LAB001"));
		usernameLabel.setText(language.getString("VIEW007LAB002"));
		passwordLabel.setText(language.getString("VIEW002LAB002"));

		passwordMonitorLabel.setText(language.getString("sp.settings.passwordmonitor"));

		if (this.loggedUser != null) {

			this.usernameTextField.setEditable(false);
			this.passwordField.setEditable(false);
			this.passwordMonitorPasswordField.setEditable(false);
		}
	}

	private void listeners() {
		listenerUsernameTextField();
		listenerPasswordField();
		listenerPasswordMonitorField();
	}

	private void listenerUsernameTextField() {

		usernameTextField.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				try {

					String username = "";
					SecretKey applicationKey = settings.getApplicationKey();
					if (applicationKey != null)
						username = Crypt.encrypt(usernameTextField.getText(), applicationKey);

					settings.setUsername(username);
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

	private void listenerPasswordMonitorField() {

		passwordMonitorPasswordField.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {

				try {
					settings.setUserPasswordMonitorEncrypted(decryptPasswordMonitor());
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

	private String decryptPasswordMonitor() {

		SecretKey applicationKey = settings.getApplicationKey();
		if (applicationKey != null)
			return Crypt.encrypt(passwordMonitorPasswordField.getText(), applicationKey);

		return "";
	}

	private void loadSettings() {

		SecretKey applicationKey = settings.getApplicationKey();
		if (applicationKey != null) {

			String decryptedUsername = Crypt.decrypt(settings.getUsername(), applicationKey);
			if (decryptedUsername != null)
				usernameTextField.setText(decryptedUsername);

			String decryptedPassword = Crypt.decrypt(settings.getUserPasswordEncrypted(), applicationKey);
			if (decryptedPassword != null)
				passwordField.setText(decryptedPassword);

			String decryptedPasswordMonitor = Crypt.decrypt(settings.getUserPasswordMonitorEncrypted(), applicationKey);
			if (decryptedPasswordMonitor != null)
				passwordMonitorPasswordField.setText(decryptedPasswordMonitor);
		}
	}

	public Settings getSettings() {
		return settings;
	}

	public void setSettings(Settings settings) {
		this.settings = settings;
	}

	public User getLoggedUser() {
		return loggedUser;
	}

	public void setLoggedUser(User loggedUser) {
		this.loggedUser = loggedUser;
	}
}
