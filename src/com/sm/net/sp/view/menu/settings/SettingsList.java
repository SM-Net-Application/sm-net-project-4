package com.sm.net.sp.view.menu.settings;

import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.settings.Settings;
import com.sm.net.sp.view.SupportPlannerView;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.util.Callback;

public class SettingsList {

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

		settingsLabel.getStyleClass().add("labelStyle1");
		settingsListView.getStyleClass().add("listViewStyle1");
		settingsButton.getStyleClass().add("buttonStyle2");
	}

	private void listeners() {
		listenerSettingsListView();
		listenerSettingsButton();
	}

	private void listenerSettingsButton() {
		settingsButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				ctrlViewSupportPlanner.loadHome();
			}
		});
	}

	private void listenerSettingsListView() {

		settingsListView.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

				if (newValue.intValue() > -1)
					loadSettings();
			}
		});
	}

	private void loadSettings() {

		EnumSettingsList menuItem = settingsListView.getSelectionModel().getSelectedItem();

		switch (menuItem) {
		case DATABASE:
			ctrlViewSupportPlanner.loadMenuSettingDatabase();
			break;
		case USER:
			ctrlViewSupportPlanner.loadMenuSettingUser();
			break;
		}

	}

	private void cellFactory() {
		cellFactorySettingsListView();
	}

	private void cellFactorySettingsListView() {

		settingsListView.setCellFactory(new Callback<ListView<EnumSettingsList>, ListCell<EnumSettingsList>>() {

			@Override
			public ListCell<EnumSettingsList> call(ListView<EnumSettingsList> param) {
				return new SettingsMenuItem(language);
			}
		});

	}

	private void viewUpdate() {

		this.language = settings.getLanguage();

		settingsLabel.setText(language.getString("VIEW004MEN002"));
		// settingsLabel.setGraphic(new
		// ImageView(Meta.Resources.MENU_APP_SETTINGS));

		settingsButton.setText("");
		settingsButton.setGraphic(new ImageView(Meta.Resources.MENU_BACK));

		settingsListView.getItems().addAll((EnumSettingsList.values()));
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
				setGraphic(new ImageView(item.getImage()));
				setGraphicTextGap(25);
			}
		}
	}
}
