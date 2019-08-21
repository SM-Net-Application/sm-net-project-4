package com.sm.net.sp.view.home.user.menu;

import java.util.Optional;

import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.model.User;
import com.sm.net.sp.settings.Settings;
import com.sm.net.sp.view.SupportPlannerView;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;

public class HomeUserMenuList {

	@FXML
	private ImageView userImageView;
	@FXML
	private Label menuLabel;
	@FXML
	private ListView<EnumHomeUserMenuList> menuListView;
	@FXML
	private Button menuButton;

	private Settings settings;
	private Language language;
	private SupportPlannerView ctrlSupportPlannerView;
	private Stage stageSupportPlannerView;
	private User user;

	@FXML
	private void initialize() {
		styleClasses();
	}

	public void objectInitialize() {
		this.language = settings.getLanguage();
		listeners();
		cellFactory();
		viewUpdate();
	}

	private void styleClasses() {

		menuLabel.getStyleClass().add("label_001");
		menuListView.getStyleClass().add("list_view_001");
		menuButton.getStyleClass().add("button_image_001");
	}

	private void viewUpdate() {

		this.language = settings.getLanguage();

		userImageView.setFitWidth(50);
		userImageView.setFitHeight(50);
		userImageView.setImage(Meta.Resources.getImageLogo(Meta.Resources.MENU_SETTINGS_USER, 50, 50));

		menuLabel.setText(setMenuLabel());

		menuButton.setText("");
		menuButton.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.USER_MENU_LOGOUT));

		buildMenuList();
	}

	private void listeners() {
		listenerUserMenuListView();
		listenerMenuButton();
	}

	private void listenerMenuButton() {

		menuButton.setOnAction(value -> {

			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

			DialogPane dialogPane = alert.getDialogPane();
			dialogPane.getStylesheets().add(Meta.Themes.SUPPORTPLANNER_THEME);
			dialogPane.getStyleClass().add("alert_001");

			alert.initOwner(stageSupportPlannerView);
			alert.setTitle(Meta.Application.getFullTitle());
			alert.setHeaderText("Logout");
			alert.setContentText(this.language.getString("MEX009"));
			Stage stage = (Stage) dialogPane.getScene().getWindow();
			stage.getIcons().add(Meta.Resources.getImageApplicationIcon());
			Optional<ButtonType> button = alert.showAndWait();
			if (button != null)
				if (button.get() == ButtonType.OK) {
					ctrlSupportPlannerView.setUserlogout();
					ctrlSupportPlannerView.viewSupportPlannerHome();
				}
		});
	}

	private void listenerUserMenuListView() {

		menuListView.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

				if (newValue.intValue() > -1)
					loadSettings();
			}
		});
	}

	private void cellFactory() {
		cellFactorySettingsListView();
	}

	private void cellFactorySettingsListView() {

		menuListView.setCellFactory(new Callback<ListView<EnumHomeUserMenuList>, ListCell<EnumHomeUserMenuList>>() {

			@Override
			public ListCell<EnumHomeUserMenuList> call(ListView<EnumHomeUserMenuList> param) {
				return new UserMenuItem(language);
			}
		});

	}

	private String setMenuLabel() {

		// String menuLabel = language.getString("USERMENU000");
		// menuLabel += ": " + user.getUsername();

		String menuLabel = user.getUsername();

		if (user.isSpUserSU())
			menuLabel += " (" + language.getString("TEXT0010") + ")";

		return menuLabel;
	}

	private void buildMenuList() {
		if (this.user.isSpUserSU())
			menuListView.getItems().addAll(EnumHomeUserMenuList.values());
		else {
			if (this.user.isSpInf1())
				menuListView.getItems().add(EnumHomeUserMenuList.USERS);
			if (this.user.isSpInf2())
				menuListView.getItems().add(EnumHomeUserMenuList.CONGREGATION);
			if (this.user.isSpInf3())
				menuListView.getItems().add(EnumHomeUserMenuList.SERVICEGROUPS);
			if (this.user.isSpInf4())
				menuListView.getItems().add(EnumHomeUserMenuList.MEETINGS);
			if (this.user.isSpInf5())
				menuListView.getItems().add(EnumHomeUserMenuList.CIRCUITOVERSEERS);
		}
	}

	private void loadSettings() {

		EnumHomeUserMenuList menuItem = menuListView.getSelectionModel().getSelectedItem();

		switch (menuItem) {
		case USERS:
			ctrlSupportPlannerView.viewHomeUserMenuUsers();
			break;
		case CONGREGATION:
			ctrlSupportPlannerView.viewHomeUserMenuCongregation();
			break;
		case SERVICEGROUPS:
			ctrlSupportPlannerView.viewHomeUserMenuSerGroups();
			break;
		case MEETINGS:
			ctrlSupportPlannerView.viewHomeUserMenuMeetings();
			break;
		case CIRCUITOVERSEERS:
			ctrlSupportPlannerView.viewHomeUserMenuCircuitOverseer();
			break;
		case MONITOR:
			ctrlSupportPlannerView.viewHomeUserMenuMonitor();
			break;
		}
	}

	static class UserMenuItem extends ListCell<EnumHomeUserMenuList> {

		private Language language;

		public UserMenuItem(Language language) {
			super();
			this.language = language;
		}

		@Override
		protected void updateItem(EnumHomeUserMenuList item, boolean empty) {
			super.updateItem(item, empty);

			if (item != null) {
				setText(language.getString(item.getName()));
				setGraphic(Meta.Resources.imageInStackPaneForMenu(item.getImageName()));
				setGraphicTextGap(25);
			}
		}
	}

	public Settings getSettings() {
		return settings;
	}

	public void setSettings(Settings settings) {
		this.settings = settings;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public SupportPlannerView getCtrlSupportPlannerView() {
		return ctrlSupportPlannerView;
	}

	public void setCtrlSupportPlannerView(SupportPlannerView ctrlSupportPlannerView) {
		this.ctrlSupportPlannerView = ctrlSupportPlannerView;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Stage getStageSupportPlannerView() {
		return stageSupportPlannerView;
	}

	public void setStageSupportPlannerView(Stage stageSupportPlannerView) {
		this.stageSupportPlannerView = stageSupportPlannerView;
	}

}
