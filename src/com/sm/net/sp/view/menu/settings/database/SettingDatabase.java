package com.sm.net.sp.view.menu.settings.database;

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

public class SettingDatabase {

	@FXML
	private Label titleLabel;

	@FXML
	private Label urlLabel;

	@FXML
	private TextField urlTextField;

	@FXML
	private Label decryptionKeyLabel;

	@FXML
	private PasswordField decryptionKeyPasswordField;

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

	private void listeners() {
		listenerUrlTextField();
		listenerDecryptionKeyPasswordField();
	}

	private void listenerDecryptionKeyPasswordField() {

		decryptionKeyPasswordField.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				try {
					settings.setDatabaseKeyEncrypted(decryptKey());
					settings.save();
				} catch (IOException e) {
				}
			}

		});
	}

	private String decryptKey() {

		SecretKey applicationKey = settings.getApplicationKey();
		if (applicationKey != null)
			return Crypt.encrypt(decryptionKeyPasswordField.getText(), applicationKey);

		return "";
	}

	private void listenerUrlTextField() {

		urlTextField.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {

				if (!newValue.booleanValue()) {
					try {
						settings.setDatabaseUrl(urlTextField.getText());
						settings.save();
					} catch (IOException e) {
					}
				}
			}
		});
	}

	private void styleClasses() {

		titleLabel.getStyleClass().add("labelStyle2");
		urlLabel.getStyleClass().add("labelStyle1");
		urlTextField.getStyleClass().add("textFieldStyle1");
		decryptionKeyLabel.getStyleClass().add("labelStyle1");
		decryptionKeyPasswordField.getStyleClass().add("textFieldStyle1");
	}

	private void viewUpdate() {

		this.language = settings.getLanguage();

		titleLabel.setText(language.getString("VIEW005LAB001"));
		titleLabel.setGraphic(new ImageView(Meta.Resources.MENU_SETTINGS_DB));
		titleLabel.setGraphicTextGap(25);
		urlLabel.setText(language.getString("VIEW005LAB002"));
		decryptionKeyLabel.setText(language.getString("VIEW005LAB003"));
	}

	private void loadSettings() {

		urlTextField.setText(settings.getDatabaseUrl());

		SecretKey applicationKey = settings.getApplicationKey();
		if (applicationKey != null)
			decryptionKeyPasswordField.setText(Crypt.decrypt(settings.getDatabaseKeyEncrypted(), applicationKey));
	}

	public Settings getSettings() {
		return settings;
	}

	public void setSettings(Settings settings) {
		this.settings = settings;
	}

}
