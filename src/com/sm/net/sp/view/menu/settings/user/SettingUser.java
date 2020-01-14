package com.sm.net.sp.view.menu.settings.user;

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
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SettingUser implements SettingsUserCallback {

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
	@FXML
	private Button userSUButton;
	@FXML
	private Button userSUPrintAccessDataButton;

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

		titleLabel.getStyleClass().add("label_setting_name");
		usernameLabel.getStyleClass().add("label_set_001");
		passwordLabel.getStyleClass().add("label_set_001");
		usernameTextField.getStyleClass().add("text_field_001");
		passwordField.getStyleClass().add("text_field_001");
		passwordMonitorLabel.getStyleClass().add("label_set_001");
		passwordMonitorPasswordField.getStyleClass().add("text_field_001");

		userSUButton.getStyleClass().add("button_image_001");
		userSUPrintAccessDataButton.getStyleClass().add("button_image_001");
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

		userSUButton.setText(language.getString("TEXT0008"));
		userSUButton.setGraphic(Meta.Resources.imageViewForButton(Meta.Resources.SUPERUSER));

		userSUPrintAccessDataButton.setText(language.getString("sp.settings.database.superuserprint"));
		userSUPrintAccessDataButton.setGraphic(Meta.Resources.imageViewForButton(Meta.Resources.SUPERUSER_PRINT));

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
		listenerUserSUButton();
		listenerUserSUPrintButton();
	}

	private void listenerUserSUPrintButton() {

		this.userSUPrintAccessDataButton.setOnAction(event -> {

			if (this.loggedUser != null) {
				if (this.loggedUser.isSpUserSU())
					Actions.printUser(this.loggedUser, settings, ownerStage, language);
				else
					this.application.getAlertBuilder().error(ownerStage, this.language.getString("sp.settings.nosu"))
							.show();
			} else
				this.application.getAlertBuilder().error(ownerStage, this.language.getString("sp.settings.nosu"))
						.show();

		});

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

	@Override
	public void usernameExists() {
		this.application.getAlertBuilder().error(ownerStage, settings.getLanguage().getString("TEXT0009")).show();
	}

	@Override
	public void usernameNotExists() {

		try {

			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(Meta.Views.MENU_SETTING_DB_ROOT);
			AnchorPane layout = (AnchorPane) fxmlLoader.load();

			SettingUserAddSuperuser ctrl = (SettingUserAddSuperuser) fxmlLoader.getController();
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
			ctrl.setSettingUserCallback(this);

			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateSettings(String username, String password, String key) throws IOException {

		this.usernameTextField.setText(username);
		this.passwordField.setText(password);

		SecretKey applicationKey = settings.getApplicationKey();
		if (applicationKey != null) {

			String usernameSetting = Crypt.encrypt(username, applicationKey);
			String passwordSetting = Crypt.encrypt(password, applicationKey);
			String keySetting = Crypt.encrypt(key, applicationKey);

			this.settings.setUsername(usernameSetting);
			this.settings.setUserPasswordEncrypted(passwordSetting);
			this.settings.setDatabaseKeyEncrypted(keySetting);
			this.settings.save();
		}
	}
}
