package com.sm.net.sp.view.menu.settings.database;

import java.io.File;
import java.io.IOException;

import javax.crypto.SecretKey;

import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.actions.Actions;
import com.sm.net.sp.model.User;
import com.sm.net.sp.settings.Settings;
import com.sm.net.sp.view.SupportPlannerView;
import com.sm.net.util.Crypt;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class SettingDatabase {

	@FXML
	private ImageView databaseImageView;
	@FXML
	private Label titleLabel;
	@FXML
	private Label decryptionKeyLabel;
	@FXML
	private PasswordField decryptionKeyPasswordField;

	@FXML
	private Label mysqlHostLabel;
	@FXML
	private Label mysqlDBNameLabel;
	@FXML
	private Label mysqlDBUserNameLabel;
	@FXML
	private Label mysqlDBUserPassLabel;

	@FXML
	private TextField mysqlHostTextField;
	@FXML
	private TextField mysqlDBNameTextField;
	@FXML
	private TextField mysqlDBUserNameTextField;
	@FXML
	private PasswordField mysqlDBUserPassPasswordField;

	@FXML
	private Button createConfigButton;

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

		decryptionKeyLabel.getStyleClass().add("label_set_001");
		mysqlHostLabel.getStyleClass().add("label_set_001");
		mysqlDBNameLabel.getStyleClass().add("label_set_001");
		mysqlDBUserNameLabel.getStyleClass().add("label_set_001");
		mysqlDBUserPassLabel.getStyleClass().add("label_set_001");

		decryptionKeyPasswordField.getStyleClass().add("text_field_001");

		mysqlHostTextField.getStyleClass().add("text_field_001");
		mysqlDBNameTextField.getStyleClass().add("text_field_001");
		mysqlDBUserNameTextField.getStyleClass().add("text_field_001");
		mysqlDBUserPassPasswordField.getStyleClass().add("text_field_001");

		createConfigButton.getStyleClass().add("button_image_001");
	}

	private void viewUpdate() {

		this.language = settings.getLanguage();

		databaseImageView.setFitWidth(50);
		databaseImageView.setFitHeight(50);
		databaseImageView.setImage(Meta.Resources.getImageLogo(Meta.Resources.MENU_SETTINGS_DB, 50, 50));

		titleLabel.setText(language.getString("VIEW006MEN001"));

		decryptionKeyLabel.setText(language.getString("VIEW005LAB003"));

		mysqlHostLabel.setText(language.getString("sp.settings.database.mysqlhost"));
		mysqlDBNameLabel.setText(language.getString("sp.settings.database.mysqldbname"));
		mysqlDBUserNameLabel.setText(language.getString("sp.settings.database.mysqldbusername"));
		mysqlDBUserPassLabel.setText(language.getString("sp.settings.database.mysqldbuserpass"));

		createConfigButton.setText(language.getString("sp.settings.database.createconfig"));
		createConfigButton.setGraphic(Meta.Resources.imageViewForButton(Meta.Resources.DBCONFIG));

		if (this.loggedUser != null) {

			this.decryptionKeyPasswordField.setEditable(false);
			this.mysqlHostTextField.setEditable(false);
			this.mysqlDBNameTextField.setEditable(false);
			this.mysqlDBUserNameTextField.setEditable(false);
			this.mysqlDBUserPassPasswordField.setEditable(false);
		}
	}

	private void listeners() {
		listenerDecryptionKeyPasswordField();

		listenerMysqlHostTextField();
		listenerMysqlDBNameTextField();
		listenerMysqlDBUserNameTextField();
		listenerMysqlDBUserPassPasswordField();

		listenerCreateConfigButton();
	}

	private void listenerCreateConfigButton() {

		this.createConfigButton.setOnAction(event -> {

			if (this.application.getAlertBuilder().confirm(this.ownerStage,
					this.language.getString("sp.settings.database.createconfig.confirm"))) {

				String host = this.mysqlHostTextField.getText();
				String dbname = this.mysqlDBNameTextField.getText();
				String dbusername = this.mysqlDBUserNameTextField.getText();
				String dbpassword = this.mysqlDBUserPassPasswordField.getText();

				DirectoryChooser dc = new DirectoryChooser();
				dc.setTitle(Meta.Application.getFullTitle());
				File directory;
				if ((directory = dc.showDialog(ownerStage)) != null)
					Actions.createConfigPHPFile(host, dbname, dbusername, dbpassword, directory, this.ownerStage,
							this.settings, this.application);

			}
		});
	}

	private void listenerMysqlHostTextField() {

		mysqlHostTextField.focusedProperty().addListener((observable, oldValue, newValue) -> {

			if (!newValue) {
				try {

					SecretKey applicationKey = settings.getApplicationKey();
					if (applicationKey != null) {
						String encrypt = Crypt.encrypt(mysqlHostTextField.getText(), applicationKey);

						settings.setMysqlHostEncrypted(encrypt);
						settings.save();
					}
				} catch (IOException e) {
				}
			}
		});
	}

	private void listenerMysqlDBNameTextField() {

		mysqlDBNameTextField.focusedProperty().addListener((observable, oldValue, newValue) -> {

			if (!newValue) {
				try {
					SecretKey applicationKey = settings.getApplicationKey();
					if (applicationKey != null) {
						String encrypt = Crypt.encrypt(mysqlDBNameTextField.getText(), applicationKey);

						settings.setMysqlDBNameEncrypted(encrypt);
						settings.save();
					}
				} catch (IOException e) {
				}
			}
		});
	}

	private void listenerMysqlDBUserNameTextField() {

		mysqlDBUserNameTextField.focusedProperty().addListener((observable, oldValue, newValue) -> {

			if (!newValue) {
				try {

					SecretKey applicationKey = settings.getApplicationKey();
					if (applicationKey != null) {
						String encrypt = Crypt.encrypt(mysqlDBUserNameTextField.getText(), applicationKey);

						settings.setMysqlDBUserNameEncrypted(encrypt);
						settings.save();
					}
				} catch (IOException e) {
				}
			}
		});
	}

	private void listenerMysqlDBUserPassPasswordField() {

		mysqlDBUserPassPasswordField.focusedProperty().addListener((observable, oldValue, newValue) -> {

			if (!newValue) {
				try {

					SecretKey applicationKey = settings.getApplicationKey();
					if (applicationKey != null) {
						String encrypt = Crypt.encrypt(mysqlDBUserPassPasswordField.getText(), applicationKey);

						settings.setMysqlDBUserPasswordEncrypted(encrypt);
						settings.save();
					}
				} catch (IOException e) {
				}
			}
		});
	}

	private void listenerDecryptionKeyPasswordField() {

		decryptionKeyPasswordField.focusedProperty().addListener((observable, oldValue, newValue) -> {

			if (!newValue) {
				try {
					SecretKey applicationKey = settings.getApplicationKey();
					if (applicationKey != null) {
						String encrypt = Crypt.encrypt(decryptionKeyPasswordField.getText(), applicationKey);

						settings.setDatabaseKeyEncrypted(encrypt);
						settings.save();
					}
				} catch (IOException e) {
				}
			}
		});
	}

	private void loadSettings() {

		SecretKey applicationKey = settings.getApplicationKey();
		if (applicationKey != null) {

			String decryptedDatabaseKey = Crypt.decrypt(settings.getDatabaseKeyEncrypted(), applicationKey);
			if (decryptedDatabaseKey != null)
				decryptionKeyPasswordField.setText(decryptedDatabaseKey);
		}

		this.mysqlHostTextField.setText(this.settings.getMysqlHostDecrypted());
		this.mysqlDBNameTextField.setText(this.settings.getMysqlDBNameDecrypted());
		this.mysqlDBUserNameTextField.setText(this.settings.getMysqlDBUserNameDerypted());
		this.mysqlDBUserPassPasswordField.setText(this.settings.getMysqlDBUserPasswordDecrypted());
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
