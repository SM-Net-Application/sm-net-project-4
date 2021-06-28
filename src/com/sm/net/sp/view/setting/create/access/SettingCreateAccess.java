package com.sm.net.sp.view.setting.create.access;

import com.sm.net.javafx.AlertDesigner;
import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.view.SupportPlannerView;
import com.smnet.api.security.PasswordUtils;
import com.smnet.api.security.PasswordUtils.PasswordSecurityStatus;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.ImageView;

public class SettingCreateAccess {

	@FXML
	private ImageView logoImageView;
	@FXML
	private Label softwareNameLabel;
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

		softwareNameLabel.getStyleClass().add("label_software_name");

		labelTitle.getStyleClass().add("label_header_001");
		labelPassword.getStyleClass().add("label_set_001");
		labelPasswordConfirm.getStyleClass().add("label_set_001");
		passwordFieldPassword.getStyleClass().add("text_field_001");
		passwordFiedlPasswordConfirm.getStyleClass().add("text_field_001");
		buttonCreate.getStyleClass().add("button_image_001");
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
		status = (PasswordUtils.isSecure(password) == PasswordSecurityStatus.OK);

		if (!status) {
			new AlertDesigner(language.getStringWithNewLine("MEX001"), language.getStringWithNewLine("MEX002"),
					supportPlannerViewCtrl.getViewSupportPlannerStage(), AlertType.ERROR,
					Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
					Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").show();
		} else {
			String passwordConfirm = passwordFiedlPasswordConfirm.getText();
			status = passwordConfirm.equals(password);
			if (!status)
				new AlertDesigner(language.getStringWithNewLine("MEX001"), language.getStringWithNewLine("MEX003"),
						supportPlannerViewCtrl.getViewSupportPlannerStage(), AlertType.ERROR,
						Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
						Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").show();
		}
		return status;
	}

	private void viewUpdate() {

		softwareNameLabel.setText(Meta.Application.getTitle());

		logoImageView.setFitWidth(100);
		logoImageView.setFitHeight(100);
		logoImageView.setImage(Meta.Resources.getImageLogo(Meta.Resources.ICON, 100, 100));

		labelTitle.setText(language.getString("VIEW002LAB001"));
		labelPassword.setText(language.getString("VIEW002LAB002"));
		labelPasswordConfirm.setText(language.getString("VIEW002LAB003"));

		buttonCreate.setText(language.getString("VIEW002BUT001"));
		buttonCreate.setMinWidth(200);
		buttonCreate.setGraphic(Meta.Resources.imageViewForButton(Meta.Resources.OK));
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
