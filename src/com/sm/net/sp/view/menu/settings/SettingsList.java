package com.sm.net.sp.view.menu.settings;

import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.settings.Settings;
import com.sm.net.sp.view.SupportPlannerView;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;

public class SettingsList {

	@FXML
	private ImageView settingsImageView;
	@FXML
	private Label settingsLabel;
	@FXML
	private ListView<EnumSettingsList> settingsListView;
	@FXML
	private Button settingsButton;

	private Settings settings;
	private Language language;
	private SupportPlannerView ctrlViewSupportPlanner;

	@FXML
	private void initialize() {
		styleClasses();
	}

	public void objectInitialize() {
		listeners();
		cellFactory();
		viewUpdate();
	}

	private void styleClasses() {

		settingsLabel.getStyleClass().add("label_001");
		settingsListView.getStyleClass().add("list_view_001");
		settingsButton.getStyleClass().add("button_image_001");
	}

	private void viewUpdate() {

		this.language = settings.getLanguage();

		settingsImageView.setFitWidth(50);
		settingsImageView.setFitHeight(50);
		settingsImageView.setImage(Meta.Resources.getImageLogo(Meta.Resources.MENU_APP_SETTINGS, 50, 50));

		settingsLabel.setText(language.getString("VIEW004MEN002"));

		settingsButton.setText("");
		settingsButton.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.MENU_BACK));

		settingsListView.getItems().addAll((EnumSettingsList.values()));
	}

	private void listeners() {
		listenerSettingsListView();
		listenerSettingsButton();
	}

	private void listenerSettingsButton() {
		settingsButton.setOnAction(event -> ctrlViewSupportPlanner.loadHome());
	}

	private void listenerSettingsListView() {

		settingsListView.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue.intValue() > -1)
				loadSettings();
		});
	}

	private void loadSettings() {

		EnumSettingsList menuItem = settingsListView.getSelectionModel().getSelectedItem();

		switch (menuItem) {
		case CONNECTION:
			ctrlViewSupportPlanner.loadMenuSettingConnection();
			break;
		case DATABASE:
			ctrlViewSupportPlanner.loadMenuSettingDatabase();
			break;
		case USER:
			ctrlViewSupportPlanner.loadMenuSettingUser();
			break;
		case MONITOR:
			ctrlViewSupportPlanner.loadMenuSettingMonitor();
			break;
		case MODULES:
			ctrlViewSupportPlanner.loadMenuSettingModules();
			break;
		}

	}

	private void cellFactory() {
		cellFactorySettingsListView();
	}

	private void cellFactorySettingsListView() {
		settingsListView.setCellFactory(param -> new SettingsMenuItem(language));
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

	static class SettingsMenuItem extends ListCell<EnumSettingsList> {

		private Language language;

		public SettingsMenuItem(Language language) {
			super();
			this.language = language;
		}

		@Override
		protected void updateItem(EnumSettingsList item, boolean empty) {
			super.updateItem(item, empty);

			if (item != null) {
				setText(language.getString(item.getName()));
				setGraphic(Meta.Resources.imageForButtonSmall(item.getImageName()));
				setGraphicTextGap(25);
			}
		}
	}
}
