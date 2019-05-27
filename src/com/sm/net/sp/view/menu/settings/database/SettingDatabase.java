package com.sm.net.sp.view.menu.settings.database;

import java.io.IOException;

import javax.crypto.SecretKey;

import com.sm.net.javafx.AlertDesigner;
import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.actions.Actions;
import com.sm.net.sp.settings.Settings;
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

	private Settings settings;
	private Language language;
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
		urlLabel.getStyleClass().add("label_set_001");
		decryptionKeyLabel.getStyleClass().add("label_set_001");
		urlTextField.getStyleClass().add("text_field_001");
		decryptionKeyPasswordField.getStyleClass().add("text_field_001");
		userSUButton.getStyleClass().add("button_image_001");
	}

	private void viewUpdate() {

		this.language = settings.getLanguage();

		databaseImageView.setFitWidth(100);
		databaseImageView.setFitHeight(100);
		databaseImageView.setImage(Meta.Resources.MENU_SETTINGS_DB);

		titleLabel.setText(language.getString("VIEW005LAB001"));

		urlLabel.setText(language.getString("VIEW005LAB002"));
		decryptionKeyLabel.setText(language.getString("VIEW005LAB003"));

		userSUButton.setText(language.getString("TEXT0008"));
		userSUButton.setGraphic(new ImageView(Meta.Resources.SUPERUSER));
	}

	private void listeners() {
		listenerUrlTextField();
		listenerDecryptionKeyPasswordField();
		listenerUserSUButton();
	}

	private void listenerUserSUButton() {
		userSUButton.setOnAction(event -> Actions.checkNoUsers(settings, ownerStage, this));
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

	@Override
	public void usernameExists() {
		new AlertDesigner(settings.getLanguage().getString("TEXT0009"), ownerStage, AlertType.ERROR,
				Meta.Application.getFullTitle(), Meta.Resources.ICON).showAndWait();
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
			stage.getIcons().add(Meta.Resources.ICON);

			stage.setResizable(false);
			stage.setMinWidth(400);
			stage.setMaxWidth(400);
			stage.setMinHeight(400);
			stage.setMaxHeight(400);

			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(ownerStage);

			ctrl.setThisStage(stage);

			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}

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

	public Stage getOwnerStage() {
		return ownerStage;
	}

	public void setOwnerStage(Stage ownerStage) {
		this.ownerStage = ownerStage;
	}

}
