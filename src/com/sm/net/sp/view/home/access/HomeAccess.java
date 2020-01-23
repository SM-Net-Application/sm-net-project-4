package com.sm.net.sp.view.home.access;

import javax.crypto.SecretKey;

import com.sm.net.easy.html.EasyHtml;
import com.sm.net.javafx.AlertDesigner;
import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.actions.Actions;
import com.sm.net.sp.settings.Settings;
import com.sm.net.sp.view.SupportPlannerView;
import com.sm.net.util.Crypt;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class HomeAccess {

	@FXML
	private ImageView logoImageView;
	@FXML
	private Button accessButton;

	@FXML
	private Label accessFastLabel;
	@FXML
	private Label accessFastDescrLabel;
	@FXML
	private Label accessNormalLabel;

	@FXML
	private Label usernameLabel;
	@FXML
	private Label passwordLabel;
	@FXML
	private TextField usernameTextField;
	@FXML
	private PasswordField passwordPasswordField;

	@FXML
	private Button accessNormalButton;

	private Settings settings;
	private Language language;
	private SupportPlannerView application;
	private Stage ownerStage;

	@FXML
	private void initialize() {
		styleClasses();
	}

	private void styleClasses() {

		accessButton.getStyleClass().add("button_image_001");

		accessFastLabel.getStyleClass().add("label_002");
		accessNormalLabel.getStyleClass().add("label_002");
		accessFastDescrLabel.getStyleClass().add("label_001");

		usernameLabel.getStyleClass().add("label_set_001");
		passwordLabel.getStyleClass().add("label_set_001");
		usernameTextField.getStyleClass().add("text_field_001");
		passwordPasswordField.getStyleClass().add("text_field_001");

		accessNormalButton.getStyleClass().add("button_image_001");
	}

	public void objectInitialize() {
		listeners();
		viewUpdate();
	}

	private void viewUpdate() {

		this.language = settings.getLanguage();

		logoImageView.setFitWidth(524);
		logoImageView.setFitHeight(150);
		logoImageView.setImage(Meta.Resources.getImageFromResources(Meta.Resources.LOGO, 524, 150));

		accessFastLabel.setText(language.getString("sp.access.fast"));
		accessNormalLabel.setText(language.getString("sp.access.normal"));
		accessFastDescrLabel.setText(language.getStringWithNewLine("sp.access.fastdescr"));

		usernameLabel.setText(language.getString("sp.access.username"));
		passwordLabel.setText(language.getString("sp.access.password"));

		accessButton.setText("");
		accessButton.setGraphic(Meta.Resources.imageViewForButton(Meta.Resources.HOME_ACCESS));
		accessButton.setGraphicTextGap(25.0);

		accessNormalButton.setText("");
		accessNormalButton.setGraphic(Meta.Resources.imageViewForButton(Meta.Resources.HOME_ACCESS));
		accessNormalButton.setGraphicTextGap(25.0);
	}

	private void listeners() {
		listenerAccessButton();
		listenerAccessNormalButton();
	}

	private void listenerAccessNormalButton() {

		accessNormalButton.setOnAction(event -> {

			if (checkRequiredData()) {

				SecretKey databaseKey = settings.getDatabaseSecretKey();
				if (databaseKey != null) {

					String usernameDecrypted = usernameTextField.getText();
					String passwordDecrypted = passwordPasswordField.getText();

					String usernameEncrypted = Crypt.encrypt(usernameDecrypted, databaseKey);
					String userPasswordReEncrypted = Crypt.encrypt(passwordDecrypted, databaseKey);

					if (usernameEncrypted != null && userPasswordReEncrypted != null) {

						Actions.checkUser(settings.getDatabaseUrl(), usernameEncrypted, userPasswordReEncrypted,
								settings, ownerStage, application, application);

					} else

						application.getAlertBuilder().error(ownerStage, language.getString("sp.access.error1")).show();

				}

			} else
				application.getAlertBuilder().error(ownerStage, language.getString("sp.access.error1")).show();

		});
	}

	private void listenerAccessButton() {

		accessButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				if (checkSettings()) {

					SecretKey applicationKey = settings.getApplicationKey();
					if (applicationKey != null) {

						String usernameEncrypted = settings.getUsernameEncrypted();
						String userPasswordReEncrypted = settings.getUserPasswordReEncrypted();

						Actions.checkUser(settings.getDatabaseUrl(), usernameEncrypted, userPasswordReEncrypted,
								settings, ownerStage, application, application);
					}

				} else
					new AlertDesigner(language.getStringWithNewLine("MEX004"), application.getViewSupportPlannerStage(),
							AlertType.ERROR, Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").show();

			}
		});

	}

	private boolean checkSettings() {

		boolean status = true;

		status = checkDatabaseURL();
		if (status)
			status = (settings.getDatabaseSecretKey() != null);

		if (status)
			status = (settings.getUsernameEncrypted() != null);

		if (status)
			status = (settings.getUserPasswordReEncrypted() != null);

		return status;
	}

	private boolean checkRequiredData() {

		boolean status = true;

		status = checkDatabaseURL();
		if (status)
			status = (settings.getDatabaseSecretKey() != null);

		if (status)
			status = !usernameTextField.getText().isEmpty();

		if (status)
			status = !passwordPasswordField.getText().isEmpty();

		return status;
	}

	private boolean checkDatabaseURL() {
		return EasyHtml.isValidUrl(this.settings.getDatabaseUrl());
	}

	public Settings getSettings() {
		return settings;
	}

	public void setSettings(Settings settings) {
		this.settings = settings;
	}

	public SupportPlannerView getSupportPlannerViewCtrl() {
		return application;
	}

	public void setSupportPlannerViewCtrl(SupportPlannerView supportPlannerViewCtrl) {
		this.application = supportPlannerViewCtrl;
	}

	public Stage getViewSupportPlannerStage() {
		return ownerStage;
	}

	public void setViewSupportPlannerStage(Stage viewSupportPlannerStage) {
		this.ownerStage = viewSupportPlannerStage;
	}

}
