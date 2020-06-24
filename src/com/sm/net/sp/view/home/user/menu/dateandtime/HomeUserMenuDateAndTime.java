package com.sm.net.sp.view.home.user.menu.dateandtime;

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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class HomeUserMenuDateAndTime extends UpdateDataAdapter {

	@FXML
	private ImageView headerImageView;
	@FXML
	private Label headerLabel;
	@FXML
	private TableView<User> dateTimeTableView;
	@FXML
	private TableColumn<User, Integer> dateTimeStartDayTableColumn;
	@FXML
	private TableColumn<User, String> dateTimeDay1TableColumn;
	@FXML
	private TableColumn<User, String> dateTimeHours1TableColumn;
	@FXML
	private TableColumn<User, String> dateTimeDay2TableColumn;
	@FXML
	private TableColumn<User, String> dateTimeHours2TableColumn;
	@FXML
	private Button addButton;
	@FXML
	private Button deleteButton;

	private Settings settings;
	private Language language;
	private Stage stageSupportPlannerView;

	@FXML
	private void initialize() {
		styleClasses();
		tableColumnsCellValueFactory();
	}

	private void tableColumnsCellValueFactory() {
		this.dateTimeStartDayTableColumn.setCellValueFactory(cellData -> cellData.getValue().getUserID().asObject());
		dateTimeDay1TableColumn.setCellValueFactory(cellData -> cellData.getValue().getUsernameProperty());
		dateTimeHours1TableColumn.setCellValueFactory(cellData -> cellData.getValue().getUsernameProperty());
		dateTimeDay2TableColumn.setCellValueFactory(cellData -> cellData.getValue().getUsernameProperty());
		dateTimeHours2TableColumn.setCellValueFactory(cellData -> cellData.getValue().getUsernameProperty());
	}

	private void styleClasses() {

		this.headerImageView.setFitWidth(50);
		this.headerImageView.setFitHeight(50);
		this.headerImageView.setImage(Meta.Resources.getImageLogo(Meta.Resources.TIME, 50, 50));

		this.headerLabel.getStyleClass().add("label_header_001");

		this.dateTimeTableView.getStyleClass().add("table_view_001");

		addButton.getStyleClass().add("button_image_001");
		deleteButton.getStyleClass().add("button_image_001");

//		dateTimeHours1TableColumn.getStyleClass().add("check_box_001");
//		dateTimeDay2TableColumn.getStyleClass().add("check_box_001");
//		dateTimeHours2TableColumn.getStyleClass().add("check_box_001");
	}

	public void objectInitialize() {
		this.language = settings.getLanguage();
		listeners();
		viewUpdate();

		updateUsers();
	}

	private void viewUpdate() {

		this.language = settings.getLanguage();

		this.headerLabel.setText(language.getString("sp.menu.dateandtime"));

		this.dateTimeStartDayTableColumn.setText(language.getString("datetime.table.column.start"));
		this.dateTimeDay1TableColumn.setText(language.getString("datetime.table.column.day1"));
		this.dateTimeHours1TableColumn.setText(language.getString("datetime.table.column.hours1"));
		this.dateTimeDay2TableColumn.setText(language.getString("datetime.table.column.day2"));
		this.dateTimeHours2TableColumn.setText(language.getString("datetime.table.column.hours2"));

		this.addButton.setText(null);
		this.addButton.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.ADD));

		this.deleteButton.setText(null);
		this.deleteButton.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.REMOVE));
	}

	@Override
	public void updateUsers() {
		Actions.getAllUsers(settings.getDatabaseUrl(), settings, stageSupportPlannerView, this);
	}

	private void listeners() {
		listenerUserAddButton();
		listenerUserDeleteButton();
	}

	private void listenerUserDeleteButton() {
		deleteButton.setOnAction(event -> {

			if (this.dateTimeTableView.getSelectionModel().getSelectedIndex() > -1) {

				User selectedUser = this.dateTimeTableView.getSelectionModel().getSelectedItem();

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
		addButton.setOnAction(event -> {

			try {

				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(Meta.Views.HOME_USER_MENU_DATEANDTIME_ADD);
				AnchorPane layout = (AnchorPane) fxmlLoader.load();

				MenuDateAndTimeAdd ctrl = (MenuDateAndTimeAdd) fxmlLoader.getController();
				ctrl.setSettings(this.settings);
				ctrl.setOwnerStage(this.stageSupportPlannerView);
				ctrl.setOwnerCtrl(this);
				ctrl.objectInitialize();

				Scene scene = new Scene(layout);
				scene.getStylesheets().add(Meta.Themes.SUPPORTPLANNER_THEME);

				Stage stage = new Stage();
				stage.setScene(scene);
				stage.setTitle(Meta.Application.getFullTitle());
				stage.getIcons().add(Meta.Resources.getImageApplicationIcon());

				stage.setResizable(false);
//				stage.setMinWidth(500);
//				stage.setMaxWidth(Double.MAX_VALUE);
//				stage.setWidth(500);
//				stage.setMinHeight(400);
//				stage.setMaxHeight(Double.MAX_VALUE);
//				stage.setHeight(400);
//				stage.setResizable(false);
//				stage.setMaximized(false);

				stage.initModality(Modality.WINDOW_MODAL);
				stage.initOwner(this.stageSupportPlannerView);

				ctrl.setThisStage(stage);
				stage.show();

			} catch (IOException e) {
				e.printStackTrace();
			}

		});
	}

	@Override
	public void updateUsers(ObservableList<User> listUser) {
		this.dateTimeTableView.setItems(listUser);
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
