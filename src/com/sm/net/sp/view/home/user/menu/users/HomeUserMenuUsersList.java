package com.sm.net.sp.view.home.user.menu.users;

import java.io.IOException;

import com.sm.net.javafx.AlertDesigner;
import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.actions.Actions;
import com.sm.net.sp.model.UpdateDataAdapter;
import com.sm.net.sp.model.User;
import com.sm.net.sp.settings.Settings;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
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
	private TableColumn<User, Boolean> userTableColumnUsers;
	@FXML
	private TableColumn<User, Boolean> userTableColumnCongregations;
	@FXML
	private TableColumn<User, Boolean> userTableColumnServiceGroups;
	@FXML
	private TableColumn<User, Boolean> userTableColumnMeetings;
	@FXML
	private TableColumn<User, Boolean> userTableColumnOverseer;
	@FXML
	private TableColumn<User, Boolean> userTableColumnNaturalDisaster;
	@FXML
	private TableColumn<User, Boolean> userTableColumnMonitor;
	@FXML
	private Button userAddButton;
	@FXML
	private Button userDeleteButton;
	@FXML
	private Button userPrintButton;

	private Settings settings;
	private Language language;
	private Stage stageSupportPlannerView;

	@FXML
	private void initialize() {
		styleClasses();
		tableColumnsCellFactory();
		tableColumnsCellValueFactory();
	}

	private void tableColumnsCellFactory() {
		userTableColumnUsers.setCellFactory(CheckBoxTableCell.forTableColumn(userTableColumnUsers));
		userTableColumnCongregations.setCellFactory(CheckBoxTableCell.forTableColumn(userTableColumnCongregations));
		userTableColumnServiceGroups.setCellFactory(CheckBoxTableCell.forTableColumn(userTableColumnServiceGroups));
		userTableColumnMeetings.setCellFactory(CheckBoxTableCell.forTableColumn(userTableColumnMeetings));
		userTableColumnOverseer.setCellFactory(CheckBoxTableCell.forTableColumn(userTableColumnOverseer));
		userTableColumnNaturalDisaster.setCellFactory(CheckBoxTableCell.forTableColumn(userTableColumnNaturalDisaster));
		userTableColumnMonitor.setCellFactory(CheckBoxTableCell.forTableColumn(userTableColumnMonitor));
	}

	private void tableColumnsCellValueFactory() {
		userTableColumnID.setCellValueFactory(cellData -> cellData.getValue().getUserID().asObject());
		userTableColumnName.setCellValueFactory(cellData -> cellData.getValue().getUsernameProperty());
		userTableColumnUsers.setCellValueFactory(cellData -> cellData.getValue().spInf1Property());
		userTableColumnCongregations.setCellValueFactory(cellData -> cellData.getValue().spInf2Property());
		userTableColumnServiceGroups.setCellValueFactory(cellData -> cellData.getValue().spInf3Property());
		userTableColumnMeetings.setCellValueFactory(cellData -> cellData.getValue().spInf4Property());
		userTableColumnOverseer.setCellValueFactory(cellData -> cellData.getValue().spInf5Property());
		userTableColumnNaturalDisaster.setCellValueFactory(cellData -> cellData.getValue().spInf6Property());
		userTableColumnMonitor.setCellValueFactory(cellData -> cellData.getValue().spInf7Property());
	}

	private void styleClasses() {

		usersImageView.setFitWidth(50);
		usersImageView.setFitHeight(50);
		usersImageView.setImage(Meta.Resources.getImageLogo(Meta.Resources.USER_MENU_USERS, 50, 50));

		userLabel.getStyleClass().add("label_header_001");

		userTableView.getStyleClass().add("table_view_001");

		userAddButton.getStyleClass().add("button_image_001");
		userDeleteButton.getStyleClass().add("button_image_001");
		userPrintButton.getStyleClass().add("button_image_001");

		userTableColumnUsers.getStyleClass().add("check_box_001");
		userTableColumnCongregations.getStyleClass().add("check_box_001");
		userTableColumnServiceGroups.getStyleClass().add("check_box_001");
		userTableColumnMeetings.getStyleClass().add("check_box_001");
		userTableColumnOverseer.getStyleClass().add("check_box_001");
		userTableColumnNaturalDisaster.getStyleClass().add("check_box_001");
		userTableColumnMonitor.getStyleClass().add("check_box_001");
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
		userTableColumnUsers.setText(language.getString("USERMENU001"));
		userTableColumnCongregations.setText(language.getString("USERMENU002"));
		userTableColumnServiceGroups.setText(language.getString("USERMENU003"));
		userTableColumnMeetings.setText(language.getString("USERMENU004"));
		userTableColumnOverseer.setText(language.getString("USERMENU005"));
		userTableColumnNaturalDisaster.setText(language.getString("sp.menu.naturaldisaster"));
		userTableColumnMonitor.setText(language.getString("sp.menu.monitor"));

		userTableView.setEditable(true);
		userTableColumnID.setMinWidth(50);
		userTableColumnID.setMaxWidth(50);
		userTableColumnID.setResizable(false);
		userTableColumnName.setMinWidth(350);
		userTableColumnName.setMaxWidth(350);
		userTableColumnName.setResizable(false);
		userTableColumnUsers.setEditable(true);
		userTableColumnCongregations.setEditable(true);
		userTableColumnServiceGroups.setEditable(true);
		userTableColumnMeetings.setEditable(true);
		userTableColumnNaturalDisaster.setEditable(true);
		userTableColumnMonitor.setEditable(true);

		userAddButton.setGraphic(Meta.Resources.imageViewForButton(Meta.Resources.USER_MENU_USERS_ADD));
		userAddButton.setText(null);

		userDeleteButton.setGraphic(Meta.Resources.imageViewForButton(Meta.Resources.USER_MENU_USERS_DEL));
		userDeleteButton.setText(null);

		userPrintButton.setGraphic(Meta.Resources.imageViewForButton(Meta.Resources.PRINT));
		userPrintButton.setText(null);
	}

	@Override
	public void updateUsers() {
		Actions.getAllUsers(settings.getDatabaseUrl(), settings, stageSupportPlannerView, this);
	}

	private void listeners() {
		listenerUserPrintButton();
		listenerUserAddButton();
		listenerUserDeleteButton();
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
}
