package com.sm.net.sp.view;

import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.settings.Settings;

import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;

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
		menuBar.getStyleClass().add("menu_bar_001");
		// configMenuApp.getStyleClass().add("menu_001");
		configSettingsMenuItem.getStyleClass().add("menu_item_001");
	}

	private void viewUpdate() {

		this.language = settings.getLanguage();

		configMenuApp.setText(language.getString("VIEW004MEN001"));
		configMenuApp.setGraphic(Meta.Resources.imageForMenu(Meta.Resources.MENU_APP));

		configSettingsMenuItem.setText(language.getString("VIEW004MEN002"));
		configSettingsMenuItem.setGraphic(Meta.Resources.imageForMenu(Meta.Resources.MENU_APP_SETTINGS));
		configSettingsMenuItem.setAccelerator(new KeyCodeCombination(KeyCode.I, KeyCombination.CONTROL_DOWN));
	}

	private void listeners() {
		listenerConfigMenuApp();
	}

	private void listenerConfigMenuApp() {
		configSettingsMenuItem.setOnAction(event -> ctrlSupportPlannerView.loadMenuSettingsList());
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
