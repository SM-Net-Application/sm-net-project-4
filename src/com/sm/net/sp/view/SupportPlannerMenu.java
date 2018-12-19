package com.sm.net.sp.view;

import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.settings.Settings;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;

public class SupportPlannerMenu {

	@FXML
	private MenuBar menuBar;

	@FXML
	private Menu configMenuApp;

	@FXML
	private MenuItem configSettingsMenuItem;

	private Settings settings;
	private Language language;
	private SupportPlannerView ctrlSupportPlannerView;

	@FXML
	private void initialize() {
		styleClasses();
	}

	public void objectInitialize() {
		listeners();
		viewUpdate();
	}

	private void styleClasses() {
		menuBar.getStyleClass().add("menuBarStyle1");
	}

	private void viewUpdate() {

		this.language = settings.getLanguage();

		configMenuApp.setText(language.getString("VIEW004MEN001"));
		configMenuApp.setGraphic(new ImageView(Meta.Resources.MENU_APP));
		configSettingsMenuItem.setText(language.getString("VIEW004MEN002"));
		configSettingsMenuItem.setGraphic(new ImageView(Meta.Resources.MENU_APP_SETTINGS));
	}

	private void listeners() {
		listenerConfigDatabaseMenuItem();
	}

	private void listenerConfigDatabaseMenuItem() {

		configSettingsMenuItem.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				ctrlSupportPlannerView.loadMenuSettingsList();
			}
		});
	}

	public Settings getSettings() {
		return settings;
	}

	public void setSettings(Settings settings) {
		this.settings = settings;
	}

	public SupportPlannerView getCtrlSupportPlannerView() {
		return ctrlSupportPlannerView;
	}

	public void setCtrlSupportPlannerView(SupportPlannerView ctrlSupportPlannerView) {
		this.ctrlSupportPlannerView = ctrlSupportPlannerView;
	}
}
