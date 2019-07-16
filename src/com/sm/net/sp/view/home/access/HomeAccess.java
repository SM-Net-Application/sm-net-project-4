package com.sm.net.sp.view.home.access;

import com.sm.net.easy.html.EasyHtml;
import com.sm.net.javafx.AlertDesigner;
import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.actions.Actions;
import com.sm.net.sp.settings.Settings;
import com.sm.net.sp.view.SupportPlannerView;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HomeAccess {

	@FXML
	private Button accessButton;

	private Settings settings;
	private Language language;
	private SupportPlannerView supportPlannerViewCtrl;
	private Stage viewSupportPlannerStage;

	@FXML
	private void initialize() {
		styleClasses();
	}

	private void styleClasses() {
		accessButton.getStyleClass().add("button_image_001");
	}

	public void objectInitialize() {
		listeners();
		viewUpdate();
	}

	private void viewUpdate() {

		this.language = settings.getLanguage();

		accessButton.setText(language.getString("VIEW008BUT001"));
		accessButton.setGraphic(Meta.Resources.imageViewForButton(Meta.Resources.HOME_ACCESS));
		accessButton.setGraphicTextGap(25.0);
	}

	private void listeners() {
		listenerAccessButton();
	}

	private void listenerAccessButton() {

		accessButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				if (checkSettings()) {

					Actions.checkUser(settings.getDatabaseUrl(), settings.getUsernameEncrypted(),
							settings.getUserPasswordReEncrypted(), settings, viewSupportPlannerStage,
							supportPlannerViewCtrl);

				} else
					new AlertDesigner(language.getStringWithNewLine("MEX004"),
							supportPlannerViewCtrl.getViewSupportPlannerStage(), AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(), Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").show();

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
		return supportPlannerViewCtrl;
	}

	public void setSupportPlannerViewCtrl(SupportPlannerView supportPlannerViewCtrl) {
		this.supportPlannerViewCtrl = supportPlannerViewCtrl;
	}

	public Stage getViewSupportPlannerStage() {
		return viewSupportPlannerStage;
	}

	public void setViewSupportPlannerStage(Stage viewSupportPlannerStage) {
		this.viewSupportPlannerStage = viewSupportPlannerStage;
	}

}
