package com.sm.net.sp.view.menu.settings.database;

import java.io.IOException;

import javax.crypto.SecretKey;

import com.sm.net.javafx.AlertDesigner;
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
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SettingDatabase implements SettingsDatabaseCallback {

	@FXML
	private ImageView databaseImageView;
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
	@FXML
	private Button userSUButton;
	@FXML
	private Button userSUPrintAccessDataButton;

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

		titleLabel.getStyleClass().add("label_setting_name");
		urlLabel.getStyleClass().add("label_set_001");
		decryptionKeyLabel.getStyleClass().add("label_set_001");
		urlTextField.getStyleClass().add("text_field_001");
		decryptionKeyPasswordField.getStyleClass().add("text_field_001");
		userSUButton.getStyleClass().add("button_image_001");
		userSUPrintAccessDataButton.getStyleClass().add("button_image_001");
	}

	private void viewUpdate() {

		this.language = settings.getLanguage();

		databaseImageView.setFitWidth(100);
		databaseImageView.setFitHeight(100);
		databaseImageView.setImage(Meta.Resources.getImageLogo(Meta.Resources.MENU_SETTINGS_DB, 100, 100));

		titleLabel.setText(language.getString("VIEW005LAB001"));

		urlLabel.setText(language.getString("VIEW005LAB002"));
		decryptionKeyLabel.setText(language.getString("VIEW005LAB003"));

		userSUButton.setText(language.getString("TEXT0008"));
		userSUButton.setGraphic(Meta.Resources.imageViewForButton(Meta.Resources.SUPERUSER));

		userSUPrintAccessDataButton.setText(language.getString("sp.settings.database.superuserprint"));
		userSUPrintAccessDataButton.setGraphic(Meta.Resources.imageViewForButton(Meta.Resources.SUPERUSER_PRINT));

		if (this.loggedUser != null) {

			this.urlTextField.setEditable(false);
			this.decryptionKeyPasswordField.setEditable(false);
		}
	}

	private void listeners() {
		listenerUrlTextField();
		listenerDecryptionKeyPasswordField();
		listenerUserSUButton();
		listenerUserSUPrintButton();
	}

	private void listenerUserSUPrintButton() {

		// TODO: Stampa dati di accesso superuser
		
	}

	private void listenerUserSUButton() {
		userSUButton.setOnAction(event -> {

			if (this.loggedUser == null)
				if (this.application.getAlertBuilder().confirm(this.ownerStage,
						this.language.getString("sp.settings.database.superuser1"),
						this.language.getString("sp.settings.database.superuser2")))
					Actions.checkNoUsers(settings, ownerStage, this);
		});
	}

	private void listenerDecryptionKeyPasswordField() {

		decryptionKeyPasswordField.focusedProperty().addListener((observable, oldValue, newValue) -> {
			try {
				settings.setDatabaseKeyEncrypted(encryptKey());
				settings.save();
			} catch (IOException e) {
			}
		});
	}

	@Override
	public void usernameExists() {
		new AlertDesigner(settings.getLanguage().getString("TEXT0009"), ownerStage, AlertType.ERROR,
				Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
				Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
	}

	@Override
	public void usernameNotExists() {

		try {

			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(Meta.Views.MENU_SETTING_DB_ROOT);
			AnchorPane layout = (AnchorPane) fxmlLoader.load();

			SettingDatabaseAddSuperuser ctrl = (SettingDatabaseAddSuperuser) fxmlLoader.getController();
			ctrl.setSettings(this.settings);
			ctrl.objectInitialize();

			Scene scene = new Scene(layout);
			scene.getStylesheets().add(Meta.Themes.SUPPORTPLANNER_THEME);

			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setTitle(Meta.Application.getFullTitle());
			stage.getIcons().add(Meta.Resources.getImageApplicationIcon());

			stage.setMinWidth(500);
			stage.setMaxWidth(Double.MAX_VALUE);
			stage.setWidth(500);
			stage.setMinHeight(500);
			stage.setMaxHeight(Double.MAX_VALUE);
			stage.setHeight(500);
			stage.setMaximized(false);
			stage.setResizable(false);

			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(ownerStage);

			ctrl.setThisStage(stage);

			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private String encryptKey() {

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

			String decryptedURL = Crypt.decrypt(settings.getDatabaseUrl(), applicationKey);
			if (decryptedURL != null)
				urlTextField.setText(decryptedURL);

			String decryptedDatabaseKey = Crypt.decrypt(settings.getDatabaseKeyEncrypted(), applicationKey);
			if (decryptedDatabaseKey != null)
				decryptionKeyPasswordField.setText(decryptedDatabaseKey);
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
