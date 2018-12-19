package com.sm.net.sp.view.setting.create.access;

import com.sm.net.auth.Authenticator;
import com.sm.net.auth.ValidationType;
import com.sm.net.javafx.AlertDesigner;
import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.view.SupportPlannerView;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

public class SettingCreateAccess {

	@FXML
	private Label labelTitle;

	@FXML
	private Label labelPassword;

	@FXML
	private Label labelPasswordConfirm;

	@FXML
	private PasswordField passwordFieldPassword;

	@FXML
	private PasswordField passwordFiedlPasswordConfirm;

	@FXML
	private Button buttonCreate;

	private Language language;
	private SupportPlannerView supportPlannerViewCtrl;

	@FXML
	private void initialize() {
		styleClasses();
	}

	public void objectInitialize() {
		listeners();
		viewUpdate();
	}

	private void styleClasses() {
		labelTitle.getStyleClass().add("labelStyle2");
		labelPassword.getStyleClass().add("labelStyle1");
		labelPasswordConfirm.getStyleClass().add("labelStyle1");
		passwordFieldPassword.getStyleClass().add("textFieldStyle1");
		passwordFiedlPasswordConfirm.getStyleClass().add("textFieldStyle1");
		buttonCreate.getStyleClass().add("buttonStyle1");
	}

	private void listeners() {
		listenerButtonCreate();
	}

	private void listenerButtonCreate() {
		buttonCreate.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (checkFields())
					supportPlannerViewCtrl.settingsCreateNewFile(language, passwordFieldPassword.getText());
			}
		});
	}

	private boolean checkFields() {

		boolean status = true;

		String password = passwordFieldPassword.getText();
		status = Authenticator.isValid(password, ValidationType.VERY_STRONG);

		if (!status) {
			new AlertDesigner(language.getStringWithNewLine("MEX001"), language.getStringWithNewLine("MEX002"),
					supportPlannerViewCtrl.getViewSupportPlannerStage(), AlertType.ERROR,
					Meta.Application.getFullTitle(), Meta.Resources.ICON).show();
		} else {
			String passwordConfirm = passwordFiedlPasswordConfirm.getText();
			status = passwordConfirm.equals(password);
			if (!status)
				new AlertDesigner(language.getStringWithNewLine("MEX001"), language.getStringWithNewLine("MEX003"),
						supportPlannerViewCtrl.getViewSupportPlannerStage(), AlertType.ERROR,
						Meta.Application.getFullTitle(), Meta.Resources.ICON).show();
		}
		return status;
	}

	private void viewUpdate() {

		labelTitle.setText(language.getString("VIEW002LAB001"));
		labelPassword.setText(language.getString("VIEW002LAB002"));
		labelPasswordConfirm.setText(language.getString("VIEW002LAB003"));
		buttonCreate.setText(language.getString("VIEW002BUT001"));

		buttonCreate.setDefaultButton(true);
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public Language getLanguage() {
		return language;
	}

	public SupportPlannerView getSupportPlannerViewCtrl() {
		return supportPlannerViewCtrl;
	}

	public void setSupportPlannerViewCtrl(SupportPlannerView supportPlannerViewCtrl) {
		this.supportPlannerViewCtrl = supportPlannerViewCtrl;
	}
}
