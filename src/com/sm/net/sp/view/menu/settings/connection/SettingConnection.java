package com.sm.net.sp.view.menu.settings.connection;

import java.io.IOException;

import javax.crypto.SecretKey;

import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.actions.Actions;
import com.sm.net.sp.model.User;
import com.sm.net.sp.settings.Settings;
import com.sm.net.sp.view.SupportPlannerView;
import com.sm.net.util.Crypt;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class SettingConnection {

	@FXML
	private ImageView imageView;
	@FXML
	private Label titleLabel;
	@FXML
	private Label urlLabel;
	@FXML
	private TextField urlTextField;
	@FXML
	private Button testConnectionButton;

	private Settings settings;
	private Language language;
	private Stage ownerStage;
	private User loggedUser;
	private SupportPlannerView application;

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
		urlLabel.getStyleClass().add("label_set_001");
		urlTextField.getStyleClass().add("text_field_001");
		testConnectionButton.getStyleClass().add("button_image_001");
	}

	private void viewUpdate() {

		this.language = settings.getLanguage();

		imageView.setFitWidth(50);
		imageView.setFitHeight(50);
		imageView.setImage(Meta.Resources.getImageLogo(Meta.Resources.MENU_SETTINGS_CONNECTION, 50, 50));

		titleLabel.setText(language.getString("sp.settings.connection"));

		urlLabel.setText(language.getString("VIEW005LAB002"));

		testConnectionButton.setText(language.getString("sp.settings.connection.test"));
		testConnectionButton.setGraphic(Meta.Resources.imageViewForButton(Meta.Resources.CONNECTION_TEST));

		if (this.loggedUser != null) {

			this.urlTextField.setEditable(false);
		}
	}

	private void listeners() {
		listenerUrlTextField();
		listenerTestConnectionButton();
	}

	private void listenerTestConnectionButton() {

		this.testConnectionButton.setOnAction(event -> {

			if (this.application.getAlertBuilder().confirm(ownerStage,
					language.getString("sp.settings.connection.test2"))) {

				Actions.checkConnection(settings.getDatabaseUrl(), settings, ownerStage, application);
			}
		});
	}

	private void listenerUrlTextField() {

		urlTextField.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {

				if (!newValue.booleanValue()) {
					try {

						String url = "";
						SecretKey applicationKey = settings.getApplicationKey();
						if (applicationKey != null)
							url = Crypt.encrypt(urlTextField.getText(), applicationKey);

						settings.setDatabaseUrl(url);
						settings.save();

					} catch (IOException e) {
					}
				}
			}
		});
	}

	private void loadSettings() {

		SecretKey applicationKey = settings.getApplicationKey();
		if (applicationKey != null) {

			urlTextField.setText(settings.getDatabaseUrl());
		}
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
}
