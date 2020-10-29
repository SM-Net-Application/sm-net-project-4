package com.sm.net.sp.view.home.user.menu.users;

import java.io.IOException;

import com.sm.net.javafx.AlertDesigner;
import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.actions.Actions;
import com.sm.net.sp.model.UpdateDataAdapter;
import com.sm.net.sp.model.User;
import com.sm.net.sp.settings.Settings;
import com.sm.net.sp.view.SupportPlannerView;
import com.sm.net.sp.view.home.user.menu.users.task.UserSaveTask;
import com.smnet.core.task.TaskManager;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class HomeUserMenuUsersList extends UpdateDataAdapter {

	@FXML
	private ImageView usersImageView;
	@FXML
	private Label userLabel;
	@FXML
	private TableView<User> userTableView;
	@FXML
	private TableColumn<User, Integer> userTableColumnID;
	@FXML
	private TableColumn<User, String> userTableColumnName;

	@FXML
	private Button saveButton;
	@FXML
	private Label selectedUserLabel;
	@FXML
	private CheckBox authUsersCheckBox;
	@FXML
	private CheckBox authCongregationsCheckBox;
	@FXML
	private CheckBox authServiceGroupsCheckBox;
	@FXML
	private CheckBox authMeetingsCheckBox;
	@FXML
	private CheckBox authPublicTalkCheckBox;
	@FXML
	private CheckBox authOverseerCheckBox;
	@FXML
	private CheckBox authNaturalDisasterCheckBox;
	@FXML
	private CheckBox authMonitorCheckBox;

	@FXML
	private CheckBox authDateAndTimeCheckBox;
	@FXML
	private CheckBox authPlacesCheckBox;
	@FXML
	private CheckBox authConventionsCheckBox;
	@FXML
	private CheckBox authMemorialCheckBox;

	@FXML
	private CheckBox authGeneralInfoCheckBox;

	@FXML
	private CheckBox authAudioCheckBox;
	@FXML
	private CheckBox authUsciereCheckBox;

	@FXML
	private Button userAddButton;
	@FXML
	private Button userDeleteButton;
	@FXML
	private Button userPrintButton;

	private Settings settings;
	private Language language;
	private Stage stageSupportPlannerView;

	private SupportPlannerView application;

	@FXML
	private void initialize() {
		styleClasses();
		tableColumnsCellValueFactory();
	}

	private void tableColumnsCellValueFactory() {
		userTableColumnID.setCellValueFactory(cellData -> cellData.getValue().getUserID().asObject());
		userTableColumnName.setCellValueFactory(cellData -> cellData.getValue().getUsernameProperty());
	}

	private void styleClasses() {

		usersImageView.setFitWidth(50);
		usersImageView.setFitHeight(50);
		usersImageView.setImage(Meta.Resources.getImageLogo(Meta.Resources.USER_MENU_USERS, 50, 50));

		userLabel.getStyleClass().add("label_header_001");

		userTableView.getStyleClass().add("table_view_001");

		userTableColumnID.getStyleClass().add("table_column_002");

		userAddButton.getStyleClass().add("button_image_001");
		userDeleteButton.getStyleClass().add("button_image_001");
		userPrintButton.getStyleClass().add("button_image_001");

		this.saveButton.getStyleClass().add("button_image_001");

		this.selectedUserLabel.getStyleClass().add("label_002");

		this.authUsersCheckBox.getStyleClass().add("check_box_001");
		this.authCongregationsCheckBox.getStyleClass().add("check_box_001");
		this.authServiceGroupsCheckBox.getStyleClass().add("check_box_001");
		this.authMeetingsCheckBox.getStyleClass().add("check_box_001");
		this.authOverseerCheckBox.getStyleClass().add("check_box_001");
		this.authNaturalDisasterCheckBox.getStyleClass().add("check_box_001");
		this.authMonitorCheckBox.getStyleClass().add("check_box_001");
		this.authPublicTalkCheckBox.getStyleClass().add("check_box_001");
		this.authDateAndTimeCheckBox.getStyleClass().add("check_box_001");
		this.authPlacesCheckBox.getStyleClass().add("check_box_001");
		this.authConventionsCheckBox.getStyleClass().add("check_box_001");
		this.authMemorialCheckBox.getStyleClass().add("check_box_001");

		this.authGeneralInfoCheckBox.getStyleClass().add("check_box_001");

		this.authAudioCheckBox.getStyleClass().add("check_box_001");
		this.authUsciereCheckBox.getStyleClass().add("check_box_001");
	}

	public void objectInitialize() {
		this.language = settings.getLanguage();
		listeners();
		viewUpdate();

		updateUsers();
	}

	private void viewUpdate() {

		this.language = settings.getLanguage();

		userLabel.setText(language.getString("TEXT0001"));

		userTableColumnID.setText(language.getString("TEXT0005"));
		userTableColumnName.setText(language.getString("VIEW007LAB002"));

		userTableView.setEditable(true);
		userTableColumnID.setMinWidth(50);
		userTableColumnID.setMaxWidth(50);
		userTableColumnID.setResizable(false);

		Tooltip userAddTooltip = new Tooltip(this.language.getString("users.tooltip.add"));
		userAddTooltip.getStyleClass().add("tooltip_001");
		this.userAddButton.setTooltip(userAddTooltip);
		this.userAddButton.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.USER_MENU_USERS_ADD));
		this.userAddButton.setText(null);

		Tooltip userDeleteTooltip = new Tooltip(language.getString("users.tooltip.delete"));
		userDeleteTooltip.getStyleClass().add("tooltip_001");
		this.userDeleteButton.setTooltip(userDeleteTooltip);
		this.userDeleteButton.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.USER_MENU_USERS_DEL));
		this.userDeleteButton.setText(null);

		Tooltip userPrintTooltip = new Tooltip(language.getString("users.tooltip.print"));
		userPrintTooltip.getStyleClass().add("tooltip_001");
		this.userPrintButton.setTooltip(userPrintTooltip);
		this.userPrintButton.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.PRINT));
		this.userPrintButton.setText(null);

		Tooltip saveTooltip = new Tooltip(language.getString("users.tooltip.save"));
		saveTooltip.getStyleClass().add("tooltip_001");
		this.saveButton.setTooltip(saveTooltip);
		this.saveButton.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.SAVE));
		this.saveButton.setText(null);

		this.selectedUserLabel.setText(this.language.getString("users.selected.empty"));

		this.authUsersCheckBox.setText(language.getString("users.auth.users"));
		this.authUsersCheckBox.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.USER_MENU_USERS));

		this.authCongregationsCheckBox.setText(language.getString("users.auth.congregation"));
		this.authCongregationsCheckBox.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.USER_MENU_CONGR));

		this.authServiceGroupsCheckBox.setText(language.getString("users.auth.servicegroups"));
		this.authServiceGroupsCheckBox
				.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.USER_MENU_SERVICEGROUPS));

		this.authMeetingsCheckBox.setText(language.getString("users.auth.meetings"));
		this.authMeetingsCheckBox.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.USER_MENU_MEETINGS));

		this.authOverseerCheckBox.setText(language.getString("users.auth.overseer"));
		this.authOverseerCheckBox
				.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.USER_MENU_CIRCUITOVERSEER));

		this.authNaturalDisasterCheckBox.setText(language.getString("users.auth.naturaldisaster"));
		this.authNaturalDisasterCheckBox
				.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.USER_MENU_NATURALDISASTER));

		this.authMonitorCheckBox.setText(language.getString("users.auth.monitor"));
		this.authMonitorCheckBox.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.USER_MENU_MONITOR));

		this.authPublicTalkCheckBox.setText(language.getString("users.auth.publictalk"));
		this.authPublicTalkCheckBox.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.PUBLIC_TALK));

		this.authDateAndTimeCheckBox.setText(language.getString("users.auth.dateandtime"));
		this.authDateAndTimeCheckBox.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.TIME));
		this.authPlacesCheckBox.setText(language.getString("users.auth.places"));
		this.authPlacesCheckBox.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.PLACES));
		this.authConventionsCheckBox.setText(language.getString("users.auth.conventions"));
		this.authConventionsCheckBox.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.CONVENTIONS));
		this.authMemorialCheckBox.setText(language.getString("users.auth.memorial"));
		this.authMemorialCheckBox.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.MEMORIAL));

		this.authGeneralInfoCheckBox.setText(language.getString("users.auth.generalinfo"));
		this.authGeneralInfoCheckBox.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.GENERALINFO));
		
		this.authAudioCheckBox.setText(language.getString("users.auth.audio"));
		this.authAudioCheckBox.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.AUDIO));
		
		this.authUsciereCheckBox.setText(language.getString("users.auth.usciere"));
		this.authUsciereCheckBox.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.USCIERE));
	}

	@Override
	public void updateUsers() {
		Actions.getAllUsers(this.settings.getDatabaseUrl(), this.settings, this.stageSupportPlannerView, this);
	}

	@Override
	public void updateUsers(ObservableList<User> listUser) {
		this.userTableView.setItems(listUser);
		this.resetAuth();
	}

	private void listeners() {

		listenerUserPrintButton();
		listenerUserAddButton();
		listenerUserDeleteButton();

		this.userTableView.getSelectionModel().selectedIndexProperty().addListener((obs, old, newV) -> {

			if (newV.intValue() > -1) {

				resetAuth();
				User user = this.userTableView.getSelectionModel().getSelectedItem();

				this.selectedUserLabel.setText(user.getUsername());

				this.authUsersCheckBox.setSelected(user.isSpInf1());
				this.authCongregationsCheckBox.setSelected(user.isSpInf2());
				this.authServiceGroupsCheckBox.setSelected(user.isSpInf3());
				this.authMeetingsCheckBox.setSelected(user.isSpInf4());
				this.authOverseerCheckBox.setSelected(user.isSpInf5());
				this.authNaturalDisasterCheckBox.setSelected(user.isSpInf6());
				this.authMonitorCheckBox.setSelected(user.isSpInf7());
				this.authPublicTalkCheckBox.setSelected(user.isSpInf8());
				this.authDateAndTimeCheckBox.setSelected(user.isSpInf9());
				this.authPlacesCheckBox.setSelected(user.isSpInf10());
				this.authConventionsCheckBox.setSelected(user.isSpInf11());
				this.authMemorialCheckBox.setSelected(user.isSpInf12());

				this.authGeneralInfoCheckBox.setSelected(user.isSpInf13());
				
				this.authAudioCheckBox.setSelected(user.isSpInf14());
				this.authUsciereCheckBox.setSelected(user.isSpInf15());
			}

		});

		this.saveButton.setOnAction(event -> save());
	}

	private void save() {

		if (this.userTableView.getSelectionModel().getSelectedIndex() > -1) {

			User user = this.userTableView.getSelectionModel().getSelectedItem();

			String header = this.application.getSettings().getLanguage().getString("users.save.confirm");
			String content = user.getUsername();

			if (this.application.getAlertBuilder2().confirm(this.stageSupportPlannerView, header, content)) {

				int userID = user.getUserID().get();

				int spInf1 = this.authUsersCheckBox.isSelected() ? 1 : 0;
				int spInf2 = this.authCongregationsCheckBox.isSelected() ? 1 : 0;
				int spInf3 = this.authServiceGroupsCheckBox.isSelected() ? 1 : 0;
				int spInf4 = this.authMeetingsCheckBox.isSelected() ? 1 : 0;
				int spInf5 = this.authOverseerCheckBox.isSelected() ? 1 : 0;
				int spInf6 = this.authNaturalDisasterCheckBox.isSelected() ? 1 : 0;
				int spInf7 = this.authMonitorCheckBox.isSelected() ? 1 : 0;
				int spInf8 = this.authPublicTalkCheckBox.isSelected() ? 1 : 0;
				int spInf9 = this.authDateAndTimeCheckBox.isSelected() ? 1 : 0;
				int spInf10 = this.authPlacesCheckBox.isSelected() ? 1 : 0;
				int spInf11 = this.authConventionsCheckBox.isSelected() ? 1 : 0;
				int spInf12 = this.authMemorialCheckBox.isSelected() ? 1 : 0;
				int spInf13 = this.authGeneralInfoCheckBox.isSelected() ? 1 : 0;
				int spInf14 = this.authAudioCheckBox.isSelected() ? 1 : 0;
				int spInf15 = this.authUsciereCheckBox.isSelected() ? 1 : 0;
				int spInf16 = 0;
				int spInf17 = 0;
				int spInf18 = 0;
				int spInf19 = 0;
				int spInf20 = 0;

				String waitMessage = this.language.getString("users.save.wait");

				TaskManager.run(this.application.getAlertBuilder2(), this.stageSupportPlannerView, waitMessage,
						new UserSaveTask(this.application.getAlertBuilder2(), this.settings,
								this.stageSupportPlannerView, userID, spInf1, spInf2, spInf3, spInf4, spInf5, spInf6,
								spInf7, spInf8, spInf9, spInf10, spInf11, spInf12, spInf13, spInf14, spInf15, spInf16,
								spInf17, spInf18, spInf19, spInf20, this));
			}
		}
	}

	private void resetAuth() {

		this.selectedUserLabel.setText(this.language.getString("users.selected.empty"));

		this.authCongregationsCheckBox.setSelected(false);
		this.authMeetingsCheckBox.setSelected(false);
		this.authMonitorCheckBox.setSelected(false);
		this.authNaturalDisasterCheckBox.setSelected(false);
		this.authOverseerCheckBox.setSelected(false);
		this.authPublicTalkCheckBox.setSelected(false);
		this.authServiceGroupsCheckBox.setSelected(false);
		this.authUsersCheckBox.setSelected(false);

		this.authDateAndTimeCheckBox.setSelected(false);
		this.authPlacesCheckBox.setSelected(false);
		this.authConventionsCheckBox.setSelected(false);
		this.authMemorialCheckBox.setSelected(false);

		this.authGeneralInfoCheckBox.setSelected(false);
		
		this.authAudioCheckBox.setSelected(false);
		this.authUsciereCheckBox.setSelected(false);
	}

	private void listenerUserPrintButton() {
		this.userPrintButton.setOnAction(event -> printUser());
	}

	private void printUser() {

		if (userTableView.getSelectionModel().getSelectedIndex() > -1) {
			User user = userTableView.getSelectionModel().getSelectedItem();
			Actions.printUser(user, settings, stageSupportPlannerView, language);
		}
	}

	private void listenerUserDeleteButton() {
		userDeleteButton.setOnAction(event -> {

			if (userTableView.getSelectionModel().getSelectedIndex() > -1) {

				User selectedUser = userTableView.getSelectionModel().getSelectedItem();

				Alert alert = new AlertDesigner(language.getString("TEXT0006"), selectedUser.getUsername(),
						stageSupportPlannerView, AlertType.CONFIRMATION, Meta.Application.getFullTitle(),
						Meta.Resources.getImageApplicationIcon(), Meta.Themes.SUPPORTPLANNER_THEME, "alert_001");
				if (alert.showAndWait().get() == ButtonType.OK)
					Actions.deleteUser(String.valueOf(selectedUser.getUserID().get()), settings,
							stageSupportPlannerView, this);
			}

		});
	}

	private void listenerUserAddButton() {
		userAddButton.setOnAction(event -> {

			try {

				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(Meta.Views.HOME_USER_MENU_USER_ADD);
				AnchorPane layout = (AnchorPane) fxmlLoader.load();

				MenuUsersAdd ctrl = (MenuUsersAdd) fxmlLoader.getController();
				ctrl.setSettings(this.settings);
				ctrl.setOwnerStage(stageSupportPlannerView);
				ctrl.setOwnerCtrl(this);
				ctrl.objectInitialize();

				Scene scene = new Scene(layout);
				scene.getStylesheets().add(Meta.Themes.SUPPORTPLANNER_THEME);

				Stage stage = new Stage();
				stage.setScene(scene);
				stage.setTitle(Meta.Application.getFullTitle());
				stage.getIcons().add(Meta.Resources.getImageApplicationIcon());

				stage.setResizable(false);
				stage.setMinWidth(500);
				stage.setMaxWidth(Double.MAX_VALUE);
				stage.setWidth(500);
				stage.setMinHeight(400);
				stage.setMaxHeight(Double.MAX_VALUE);
				stage.setHeight(400);
				stage.setResizable(false);
				stage.setMaximized(false);

				stage.initModality(Modality.WINDOW_MODAL);
				stage.initOwner(stageSupportPlannerView);

				ctrl.setThisStage(stage);
				stage.show();

			} catch (IOException e) {
				e.printStackTrace();
			}

		});
	}

	public Settings getSettings() {
		return settings;
	}

	public void setSettings(Settings settings) {
		this.settings = settings;
	}

	public Stage getStageSupportPlannerView() {
		return stageSupportPlannerView;
	}

	public void setStageSupportPlannerView(Stage stageSupportPlannerView) {
		this.stageSupportPlannerView = stageSupportPlannerView;
	}

	public SupportPlannerView getApplication() {
		return application;
	}

	public void setApplication(SupportPlannerView application) {
		this.application = application;
	}

}
