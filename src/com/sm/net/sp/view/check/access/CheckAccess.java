package com.sm.net.sp.view.check.access;

import javax.crypto.SecretKey;

import com.sm.net.auth.Authenticator;
import com.sm.net.auth.ValidationType;
import com.sm.net.javafx.AlertDesigner;
import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.settings.Settings;
import com.sm.net.sp.view.SupportPlannerView;
import com.sm.net.util.Crypt;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

public class CheckAccess {

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
		passwordLabel.getStyleClass().add("labelStyle2");
		passwordPasswordField.getStyleClass().add("textFieldStyle1");
		accessButton.getStyleClass().add("buttonStyle1");
	}

	private void viewUpdate() {
		passwordLabel.setText(language.getString("VIEW003LAB001"));
		accessButton.setText(language.getString("VIEW003BUT001"));
		accessButton.setDefaultButton(true);
		passwordPasswordField.setAlignment(Pos.CENTER);
		passwordPasswordField.setMinWidth(500);
	}

	private void listeners() {
		listenerAccessButton();
	}

	private void listenerAccessButton() {
		accessButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				if (checkFields())
					ctrlViewSupportPlanner.viewSupportPlannerHome();
				else
					new AlertDesigner(language.getStringWithNewLine("MEX001"),
							ctrlViewSupportPlanner.getViewSupportPlannerStage(), AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.ICON).show();
			}
		});
	}

	private boolean checkFields() {

		String password = passwordPasswordField.getText();

		if (!password.isEmpty()) {
			if (Authenticator.isValid(password, ValidationType.VERY_STRONG)) {
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
