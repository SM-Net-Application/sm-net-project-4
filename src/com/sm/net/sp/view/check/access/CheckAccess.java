package com.sm.net.sp.view.check.access;

import javax.crypto.SecretKey;

import com.sm.net.javafx.AlertDesigner;
import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.settings.Settings;
import com.sm.net.sp.view.SupportPlannerView;
import com.sm.net.util.Crypt;
import com.smnet.api.security.PasswordUtils;
import com.smnet.api.security.PasswordUtils.PasswordSecurityStatus;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.ImageView;

public class CheckAccess {

	@FXML
	private ImageView logoImageView;
	@FXML
	private Label softwareNameLabel;
	@FXML
	private Label passwordLabel;
	@FXML
	private PasswordField passwordPasswordField;
	@FXML
	private Button accessButton;

	private Settings settings;
	private Language language;
	private SupportPlannerView ctrlViewSupportPlanner;

	@FXML
	private void initialize() {
		styleClasses();
	}

	public void objectInitialize() {
		language = settings.getLanguage();
		listeners();
		viewUpdate();
	}

	private void styleClasses() {

		softwareNameLabel.getStyleClass().add("label_software_name");

		passwordLabel.getStyleClass().add("label_header_001");
		passwordPasswordField.getStyleClass().add("text_field_001");
		accessButton.getStyleClass().add("button_image_001");
	}

	private void viewUpdate() {

		softwareNameLabel.setText(Meta.Application.getTitle());

		logoImageView.setFitWidth(100);
		logoImageView.setFitHeight(100);
		logoImageView.setImage(Meta.Resources.getImageApplicationLogo());

		passwordLabel.setText(language.getString("VIEW003LAB001"));

		accessButton.setText(language.getString("VIEW003BUT001"));
		accessButton.setMinWidth(200);
		accessButton.setGraphic(Meta.Resources.imageViewForButton(Meta.Resources.OK));
		accessButton.setDefaultButton(true);
		passwordPasswordField.setAlignment(Pos.CENTER);
	}

	private void listeners() {
		listenerAccessButton();
	}

	private void listenerAccessButton() {
		accessButton.setOnAction(event -> checkAccess());
	}

	private void checkAccess() {

		if (checkFields())
			ctrlViewSupportPlanner.viewSupportPlannerHome();
		else
			new AlertDesigner(language.getStringWithNewLine("MEX001"),
					ctrlViewSupportPlanner.getViewSupportPlannerStage(), AlertType.ERROR,
					Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
					Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").show();
	}

	private boolean checkFields() {

		String password = passwordPasswordField.getText();

		if (!password.isEmpty()) {

			if (PasswordUtils.isSecure(password) == PasswordSecurityStatus.OK) {

				SecretKey key = Crypt.generateKey(password);
				String passwordEncrypted = Crypt.encrypt(password, key);
				if (passwordEncrypted.compareTo(settings.getPasswordEncrypted()) == 0) {
					settings.setApplicationKey(key);
					return true;
				}
			}
		}

		return false;
	}

	public Settings getSettings() {
		return settings;
	}

	public void setSettings(Settings settings) {
		this.settings = settings;
	}

	public SupportPlannerView getCtrlViewSupportPlanner() {
		return ctrlViewSupportPlanner;
	}

	public void setCtrlViewSupportPlanner(SupportPlannerView ctrlViewSupportPlanner) {
		this.ctrlViewSupportPlanner = ctrlViewSupportPlanner;
	}

}
