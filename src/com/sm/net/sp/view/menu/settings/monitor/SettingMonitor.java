package com.sm.net.sp.view.menu.settings.monitor;

import java.io.IOException;

import javax.crypto.SecretKey;

import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.model.User;
import com.sm.net.sp.settings.Settings;
import com.sm.net.sp.view.SupportPlannerView;
import com.sm.net.util.Crypt;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class SettingMonitor {

	@FXML
	private ImageView imageView;
	@FXML
	private Label titleLabel;
	@FXML
	private Label passwordMonitorLabel;
	@FXML
	private PasswordField passwordMonitorPasswordField;

	private Settings settings;
	private Language language;
	private User loggedUser;

	private SupportPlannerView application;
	private Stage ownerStage;

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

		titleLabel.getStyleClass().add("label_header_001");
		passwordMonitorLabel.getStyleClass().add("label_set_001");
		passwordMonitorPasswordField.getStyleClass().add("text_field_001");
	}

	private void viewUpdate() {

		this.language = settings.getLanguage();

		imageView.setFitWidth(50);
		imageView.setFitHeight(50);
		imageView.setImage(Meta.Resources.getImageLogo(Meta.Resources.USER_MENU_MONITOR, 50, 50));

		titleLabel.setText(language.getString("sp.settings.monitor"));

		passwordMonitorLabel.setText(language.getString("sp.settings.passwordmonitor"));

		if (this.loggedUser != null) {

			this.passwordMonitorPasswordField.setEditable(false);
		}
	}

	private void listeners() {
		listenerPasswordMonitorField();
	}

	private void listenerPasswordMonitorField() {

		passwordMonitorPasswordField.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {

				if (!newValue) {

					try {
						settings.setUserPasswordMonitorEncrypted(decryptPasswordMonitor());
						settings.save();
					} catch (IOException e) {
					}
				}
			}
		});

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

	public SupportPlannerView getApplication() {
		return application;
	}

	public void setApplication(SupportPlannerView application) {
		this.application = application;
	}

	public Stage getOwnerStage() {
		return ownerStage;
	}

	public void setOwnerStage(Stage ownerStage) {
		this.ownerStage = ownerStage;
	}
}
