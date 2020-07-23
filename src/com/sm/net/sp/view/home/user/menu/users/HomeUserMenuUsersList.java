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

//	@FXML
//	private TableColumn<User, Boolean> userTableColumnUsers;
//	@FXML
//	private TableColumn<User, Boolean> userTableColumnCongregations;
//	@FXML
//	private TableColumn<User, Boolean> userTableColumnServiceGroups;
//	@FXML
//	private TableColumn<User, Boolean> userTableColumnMeetings;
//	@FXML
//	private TableColumn<User, Boolean> userTableColumnPublicTalk;
//	@FXML
//	private TableColumn<User, Boolean> userTableColumnOverseer;
//	@FXML
//	private TableColumn<User, Boolean> userTableColumnNaturalDisaster;
//	@FXML
//	private TableColumn<User, Boolean> userTableColumnMonitor;
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
//		tableColumnsCellFactory();
		tableColumnsCellValueFactory();
	}

	private void tableColumnsCellFactory() {
//		userTableColumnUsers.setCellFactory(CheckBoxTableCell.forTableColumn(userTableColumnUsers));
//		userTableColumnCongregations.setCellFactory(CheckBoxTableCell.forTableColumn(userTableColumnCongregations));
//		userTableColumnServiceGroups.setCellFactory(CheckBoxTableCell.forTableColumn(userTableColumnServiceGroups));
//		userTableColumnMeetings.setCellFactory(CheckBoxTableCell.forTableColumn(userTableColumnMeetings));
//		userTableColumnOverseer.setCellFactory(CheckBoxTableCell.forTableColumn(userTableColumnOverseer));
//		userTableColumnNaturalDisaster.setCellFactory(CheckBoxTableCell.forTableColumn(userTableColumnNaturalDisaster));
//		userTableColumnMonitor.setCellFactory(CheckBoxTableCell.forTableColumn(userTableColumnMonitor));
//		userTableColumnPublicTalk.setCellFactory(CheckBoxTableCell.forTableColumn(userTableColumnPublicTalk));
	}

	private void tableColumnsCellValueFactory() {
		userTableColumnID.setCellValueFactory(cellData -> cellData.getValue().getUserID().asObject());
		userTableColumnName.setCellValueFactory(cellData -> cellData.getValue().getUsernameProperty());
//		userTableColumnUsers.setCellValueFactory(cellData -> cellData.getValue().spInf1Property());
//		userTableColumnCongregations.setCellValueFactory(cellData -> cellData.getValue().spInf2Property());
//		userTableColumnServiceGroups.setCellValueFactory(cellData -> cellData.getValue().spInf3Property());
//		userTableColumnMeetings.setCellValueFactory(cellData -> cellData.getValue().spInf4Property());
//		userTableColumnOverseer.setCellValueFactory(cellData -> cellData.getValue().spInf5Property());
//		userTableColumnNaturalDisaster.setCellValueFactory(cellData -> cellData.getValue().spInf6Property());
//		userTableColumnMonitor.setCellValueFactory(cellData -> cellData.getValue().spInf7Property());
//		userTableColumnPublicTalk.setCellValueFactory(cellData -> cellData.getValue().spInf8Property());
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

//		userTableColumnUsers.getStyleClass().add("check_box_001");
//		userTableColumnCongregations.getStyleClass().add("check_box_001");
//		userTableColumnServiceGroups.getStyleClass().add("check_box_001");
//		userTableColumnMeetings.getStyleClass().add("check_box_001");
//		userTableColumnOverseer.getStyleClass().add("check_box_001");
//		userTableColumnNaturalDisaster.getStyleClass().add("check_box_001");
//		userTableColumnMonitor.getStyleClass().add("check_box_001");
//		userTableColumnPublicTalk.getStyleClass().add("check_box_001");
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
//		userTableColumnUsers.setText(language.getString("USERMENU001"));
//		userTableColumnCongregations.setText(language.getString("USERMENU002"));
//		userTableColumnServiceGroups.setText(language.getString("USERMENU003"));
//		userTableColumnMeetings.setText(language.getString("USERMENU004"));
//		userTableColumnOverseer.setText(language.getString("USERMENU005"));
//		userTableColumnNaturalDisaster.setText(language.getString("sp.menu.naturaldisaster"));
//		userTableColumnMonitor.setText(language.getString("sp.menu.monitor"));
//		userTableColumnPublicTalk.setText(language.getString("sp.menu.publictalk"));

		userTableView.setEditable(true);
		userTableColumnID.setMinWidth(50);
		userTableColumnID.setMaxWidth(50);
		userTableColumnID.setResizable(false);
//		userTableColumnName.setMinWidth(350);
//		userTableColumnName.setMaxWidth(350);
//		userTableColumnName.setResizable(false);
//		userTableColumnUsers.setEditable(true);
//		userTableColumnCongregations.setEditable(true);
//		userTableColumnServiceGroups.setEditable(true);
//		userTableColumnMeetings.setEditable(true);
//		userTableColumnNaturalDisaster.setEditable(true);
//		userTableColumnMonitor.setEditable(true);
//		userTableColumnPublicTalk.setEditable(true);

		userAddButton.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.USER_MENU_USERS_ADD));
		userAddButton.setText(null);

		userDeleteButton.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.USER_MENU_USERS_DEL));
		userDeleteButton.setText(null);

		userPrintButton.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.PRINT));
		userPrintButton.setText(null);

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
	}

	@Override
	public void updateUsers() {
		Actions.getAllUsers(settings.getDatabaseUrl(), settings, stageSupportPlannerView, this);
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

			}
		}
	}

	private void resetAuth() {

		this.authCongregationsCheckBox.setSelected(false);
		this.authMeetingsCheckBox.setSelected(false);
		this.authMonitorCheckBox.setSelected(false);
		this.authNaturalDisasterCheckBox.setSelected(false);
		this.authOverseerCheckBox.setSelected(false);
		this.authPublicTalkCheckBox.setSelected(false);
		this.authServiceGroupsCheckBox.setSelected(false);
		this.authUsersCheckBox.setSelected(false);

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

	@Override
	public void updateUsers(ObservableList<User> listUser) {
		userTableView.setItems(listUser);
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
