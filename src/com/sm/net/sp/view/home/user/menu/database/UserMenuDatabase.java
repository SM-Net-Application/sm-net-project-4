package com.sm.net.sp.view.home.user.menu.database;

import java.io.File;

import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.actions.Actions;
import com.sm.net.sp.settings.Settings;
import com.sm.net.sp.view.SupportPlannerView;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class UserMenuDatabase {

	@FXML
	private Label titleLabel;
	@FXML
	private ImageView titleImageView;

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
	private Label backupRestoreLabel;
	@FXML
	private Label cleanLabel;

	@FXML
	private Button backupButton;
	@FXML
	private Button restoreButton;
	@FXML
	private Label backupLabel;
	@FXML
	private Label restoreLabel;

	@FXML
	private Button cleanDBButton;
	@FXML
	private Label cleanDBLabel;

	private Settings settings;
	private Language language;
	private Stage ownerStage;
	private SupportPlannerView application;

	@FXML
	private void initialize() {
		styleClasses();
		listeners();
	}

	public void objectInitialize() {
		viewUpdate();
		loadData();
	}

	private void listeners() {
		listenerBackupButton();
		listenerRestoreButton();
		listenerCleanDBButton();
	}

	private void styleClasses() {

		titleLabel.getStyleClass().add("label_header_001");

		mysqlHostLabel.getStyleClass().add("label_set_001");
		mysqlDBNameLabel.getStyleClass().add("label_set_001");
		mysqlDBUserNameLabel.getStyleClass().add("label_set_001");
		mysqlDBUserPassLabel.getStyleClass().add("label_set_001");

		mysqlHostTextField.getStyleClass().add("text_field_001");
		mysqlDBNameTextField.getStyleClass().add("text_field_001");
		mysqlDBUserNameTextField.getStyleClass().add("text_field_001");
		mysqlDBUserPassPasswordField.getStyleClass().add("text_field_001");

		backupRestoreLabel.getStyleClass().add("label_002");
		cleanLabel.getStyleClass().add("label_002");

		backupButton.getStyleClass().add("button_image_001");
		restoreButton.getStyleClass().add("button_image_001");
		backupLabel.getStyleClass().add("label_001");
		restoreLabel.getStyleClass().add("label_001");

		cleanDBButton.getStyleClass().add("button_image_001");
		cleanDBLabel.getStyleClass().add("label_001");
	}

	private void viewUpdate() {

		this.language = settings.getLanguage();

		titleImageView.setFitWidth(50);
		titleImageView.setFitHeight(50);
		titleImageView.setImage(Meta.Resources.getImageLogo(Meta.Resources.DATABASE, 50, 50));

		titleLabel.setText(language.getString("sp.menu.database"));

		mysqlHostLabel.setText(language.getString("sp.settings.database.mysqlhost"));
		mysqlDBNameLabel.setText(language.getString("sp.settings.database.mysqldbname"));
		mysqlDBUserNameLabel.setText(language.getString("sp.settings.database.mysqldbusername"));
		mysqlDBUserPassLabel.setText(language.getString("sp.settings.database.mysqldbuserpass"));

		backupRestoreLabel.setText(language.getString("sp.database.backuprestore"));
		cleanLabel.setText(language.getString("sp.database.clean"));

		backupButton.setText("");
		backupButton.setGraphic(Meta.Resources.imageViewForButton(Meta.Resources.BACKUP));

		restoreButton.setText("");
		restoreButton.setGraphic(Meta.Resources.imageViewForButton(Meta.Resources.RESTORE));

		backupLabel.setText(language.getString("sp.database.backup"));
		restoreLabel.setText(language.getString("sp.database.restore"));

		cleanDBButton.setText("");
		cleanDBButton.setGraphic(Meta.Resources.imageViewForButton(Meta.Resources.CLEAN));
		cleanDBLabel.setText(language.getString("sp.database.cleandb"));
	}

	private void loadData() {

		String host = this.settings.getMysqlHostDecrypted();
		String dbname = this.settings.getMysqlDBNameDecrypted();
		String dbusername = this.settings.getMysqlDBUserNameDerypted();
		String dbpassword = this.settings.getMysqlDBUserPasswordDecrypted();

		this.mysqlHostTextField.setText(host);
		this.mysqlDBNameTextField.setText(dbname);
		this.mysqlDBUserNameTextField.setText(dbusername);
		this.mysqlDBUserPassPasswordField.setText(dbpassword);
	}

	private void listenerBackupButton() {

		this.backupButton.setOnAction(event -> {

			String host = this.mysqlHostTextField.getText();
			String dbname = this.mysqlDBNameTextField.getText();
			String dbusername = this.mysqlDBUserNameTextField.getText();
			String dbpassword = this.mysqlDBUserPassPasswordField.getText();

			if (host.isEmpty() || dbname.isEmpty() || dbusername.isEmpty() || dbpassword.isEmpty())

				this.application.getAlertBuilder().error(ownerStage, language.getString("sp.database.backup.error"))
						.show();

			else {

				if (!this.application.getMysqlDump().exists())
					this.application.getAlertBuilder()
							.error(ownerStage, language.getString("sp.database.backup.errorfiledump")).show();
				else {

					if (this.application.getAlertBuilder().confirm(ownerStage,
							language.getString("sp.database.backup.confirm"))) {

						DirectoryChooser dc = new DirectoryChooser();
						dc.setTitle(Meta.Application.getFullTitle());
						File directory;
						if ((directory = dc.showDialog(ownerStage)) != null)
							Actions.createBackupDatabase(host, dbname, dbusername, dbpassword, directory,
									this.ownerStage, this.settings, this.application);
					}
				}
			}
		});
	}

	private void listenerRestoreButton() {

		this.restoreButton.setOnAction(event -> {

			String host = this.mysqlHostTextField.getText();
			String dbname = this.mysqlDBNameTextField.getText();
			String dbusername = this.mysqlDBUserNameTextField.getText();
			String dbpassword = this.mysqlDBUserPassPasswordField.getText();

			if (host.isEmpty() || dbname.isEmpty() || dbusername.isEmpty() || dbpassword.isEmpty())

				this.application.getAlertBuilder().error(ownerStage, language.getString("sp.database.restore.error"))
						.show();

			else {

				if (!this.application.getMysqlRestore().exists())
					this.application.getAlertBuilder()
							.error(ownerStage, language.getString("sp.database.restore.errorfilerestore")).show();
				else {

					if (this.application.getAlertBuilder().confirm(ownerStage,
							language.getString("sp.database.restore.confirm"))) {

						FileChooser fc = new FileChooser();
						fc.getExtensionFilters()
								.add(new FileChooser.ExtensionFilter("SupportPlanner Backup", "*.splan"));
						fc.setTitle(Meta.Application.getFullTitle());
						File file;
						if ((file = fc.showOpenDialog(ownerStage)) != null)
							Actions.startRestoreDatabase(host, dbname, dbusername, dbpassword, file, this.ownerStage,
									this.settings, this.application);
					}
				}
			}
		});
	}

	private void listenerCleanDBButton() {

		this.cleanDBButton.setOnAction(event -> {

			if (this.application.getAlertBuilder().confirm(ownerStage,
					language.getString("sp.database.clean.confirm"))) {

				Actions.cleanDatabase(this.settings, this.ownerStage, this.application);

			}
		});
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

	public SupportPlannerView getApplication() {
		return application;
	}

	public void setApplication(SupportPlannerView application) {
		this.application = application;
	}

}
