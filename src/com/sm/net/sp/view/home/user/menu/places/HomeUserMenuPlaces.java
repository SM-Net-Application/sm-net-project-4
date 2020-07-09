package com.sm.net.sp.view.home.user.menu.places;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.model.DateAndTime;
import com.sm.net.sp.settings.Settings;
import com.sm.net.sp.view.SupportPlannerView;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class HomeUserMenuPlaces {

	@FXML
	private ImageView headerImageView;
	@FXML
	private Label headerLabel;
	@FXML
	private TableView<DateAndTime> dateTimeTableView;
	@FXML
	private TableColumn<DateAndTime, LocalDate> dateTimeStartDayTableColumn;
	@FXML
	private TableColumn<DateAndTime, String> dateTimeDay1TableColumn;
	@FXML
	private TableColumn<DateAndTime, LocalTime> dateTimeHours1TableColumn;
	@FXML
	private TableColumn<DateAndTime, String> dateTimeDay2TableColumn;
	@FXML
	private TableColumn<DateAndTime, LocalTime> dateTimeHours2TableColumn;
	@FXML
	private Button addButton;
	@FXML
	private Button deleteButton;

	private Settings settings;
	private Language language;
	private Stage stageSupportPlannerView;
	private SupportPlannerView application;

	@FXML
	private void initialize() {
		styleClasses();
		tableColumnsCells();
	}

	private void tableColumnsCells() {

		this.dateTimeStartDayTableColumn.setCellFactory(param -> tableCellForLocalDate());
		this.dateTimeDay1TableColumn.setCellFactory(param -> tableCellForDayText());
		this.dateTimeDay2TableColumn.setCellFactory(param -> tableCellForDayText());

		this.dateTimeStartDayTableColumn.setCellValueFactory(cellData -> cellData.getValue().getDate());
		this.dateTimeDay1TableColumn.setCellValueFactory(cellData -> cellData.getValue().getDay1Text());
		this.dateTimeHours1TableColumn.setCellValueFactory(cellData -> cellData.getValue().getTime1());
		this.dateTimeDay2TableColumn.setCellValueFactory(cellData -> cellData.getValue().getDay2Text());
		this.dateTimeHours2TableColumn.setCellValueFactory(cellData -> cellData.getValue().getTime2());
	}

	private TableCell<DateAndTime, String> tableCellForDayText() {

		return new TableCell<DateAndTime, String>() {
			@Override
			protected void updateItem(String item, boolean empty) {
				super.updateItem(item, empty);

				if (empty)
					setText(null);
				else
					setText(language.getString(item));
			}
		};
	}

	private TableCell<DateAndTime, LocalDate> tableCellForLocalDate() {

		return new TableCell<DateAndTime, LocalDate>() {
			@Override
			protected void updateItem(LocalDate item, boolean empty) {
				super.updateItem(item, empty);

				if (empty)
					setText(null);
				else {
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
					setText(dtf.format(item));
				}
			}
		};
	}

	private void styleClasses() {

		this.headerImageView.setFitWidth(50);
		this.headerImageView.setFitHeight(50);
		this.headerImageView.setImage(Meta.Resources.getImageLogo(Meta.Resources.PLACES, 50, 50));

		this.headerLabel.getStyleClass().add("label_header_001");

		this.dateTimeTableView.getStyleClass().add("table_view_001");

		this.addButton.getStyleClass().add("button_image_001");
		this.deleteButton.getStyleClass().add("button_image_001");
	}

	public void objectInitialize() {

		this.language = this.settings.getLanguage();

		listeners();
		viewUpdate();
		loadList();
	}

	private void viewUpdate() {

		this.headerLabel.setText(this.language.getString("sp.menu.places"));

		this.dateTimeStartDayTableColumn.setText(this.language.getString("datetime.table.column.start"));
		this.dateTimeDay1TableColumn.setText(this.language.getString("datetime.table.column.day1"));
		this.dateTimeHours1TableColumn.setText(this.language.getString("datetime.table.column.hours1"));
		this.dateTimeDay2TableColumn.setText(this.language.getString("datetime.table.column.day2"));
		this.dateTimeHours2TableColumn.setText(this.language.getString("datetime.table.column.hours2"));

		this.addButton.setText(null);
		this.addButton.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.ADD));

		this.deleteButton.setText(null);
		this.deleteButton.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.REMOVE));
	}

	public void loadList() {

		String waitMessage = this.language.getString("datetime.wait.load");

//		TaskManager.run(this.application.getAlertBuilder2(), this.stageSupportPlannerView, waitMessage,
//				new DateAndTimeLoadTask(this.application.getAlertBuilder2(), this.settings,
//						this.stageSupportPlannerView, this));

	}

	private void listeners() {
		listenerUserAddButton();
		listenerUserDeleteButton();
	}

	private void listenerUserDeleteButton() {

		this.deleteButton.setOnAction(event -> {

			if (this.dateTimeTableView.getSelectionModel().getSelectedIndex() > -1) {

				DateAndTime item = this.dateTimeTableView.getSelectionModel().getSelectedItem();
				LocalDate localDate = item.getDate().get();
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");

				if (this.application.getAlertBuilder2().confirm(this.stageSupportPlannerView,
						this.language.getString("datetime.delete.confirm"), dtf.format(localDate))) {

					String waitMessage = this.language.getString("datetime.wait.delete");

//					TaskManager.run(this.application.getAlertBuilder2(), this.stageSupportPlannerView, waitMessage,
//							new DateAndTimeDeleteTask(this.application.getAlertBuilder2(), this.settings,
//									this.stageSupportPlannerView, item, this));
				}
			}

		});
	}

	private void listenerUserAddButton() {

		addButton.setOnAction(event -> {

			try {

				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(Meta.Views.HOME_USER_MENU_PLACES_ADD);
				AnchorPane layout = (AnchorPane) fxmlLoader.load();

				PlacesAdd ctrl = (PlacesAdd) fxmlLoader.getController();
				ctrl.setSettings(this.settings);
				ctrl.setOwnerStage(this.stageSupportPlannerView);
				ctrl.setOwnerCtrl(this);
				ctrl.setApplication(this.application);
				ctrl.objectInitialize();

				Scene scene = new Scene(layout);
				scene.getStylesheets().add(Meta.Themes.SUPPORTPLANNER_THEME);

				Stage stage = new Stage();
				stage.setScene(scene);
				stage.setTitle(Meta.Application.getFullTitle());
				stage.getIcons().add(Meta.Resources.getImageApplicationIcon());

				stage.setResizable(false);

				stage.initModality(Modality.WINDOW_MODAL);
				stage.initOwner(this.stageSupportPlannerView);

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

	public ImageView getHeaderImageView() {
		return headerImageView;
	}

	public void setHeaderImageView(ImageView headerImageView) {
		this.headerImageView = headerImageView;
	}

	public Label getHeaderLabel() {
		return headerLabel;
	}

	public void setHeaderLabel(Label headerLabel) {
		this.headerLabel = headerLabel;
	}

	public Button getAddButton() {
		return addButton;
	}

	public void setAddButton(Button addButton) {
		this.addButton = addButton;
	}

	public Button getDeleteButton() {
		return deleteButton;
	}

	public void setDeleteButton(Button deleteButton) {
		this.deleteButton = deleteButton;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public TableView<DateAndTime> getDateTimeTableView() {
		return dateTimeTableView;
	}

	public void setDateTimeTableView(TableView<DateAndTime> dateTimeTableView) {
		this.dateTimeTableView = dateTimeTableView;
	}

	public TableColumn<DateAndTime, LocalDate> getDateTimeStartDayTableColumn() {
		return dateTimeStartDayTableColumn;
	}

	public void setDateTimeStartDayTableColumn(TableColumn<DateAndTime, LocalDate> dateTimeStartDayTableColumn) {
		this.dateTimeStartDayTableColumn = dateTimeStartDayTableColumn;
	}

	public TableColumn<DateAndTime, String> getDateTimeDay1TableColumn() {
		return dateTimeDay1TableColumn;
	}

	public void setDateTimeDay1TableColumn(TableColumn<DateAndTime, String> dateTimeDay1TableColumn) {
		this.dateTimeDay1TableColumn = dateTimeDay1TableColumn;
	}

	public TableColumn<DateAndTime, LocalTime> getDateTimeHours1TableColumn() {
		return dateTimeHours1TableColumn;
	}

	public void setDateTimeHours1TableColumn(TableColumn<DateAndTime, LocalTime> dateTimeHours1TableColumn) {
		this.dateTimeHours1TableColumn = dateTimeHours1TableColumn;
	}

	public TableColumn<DateAndTime, String> getDateTimeDay2TableColumn() {
		return dateTimeDay2TableColumn;
	}

	public void setDateTimeDay2TableColumn(TableColumn<DateAndTime, String> dateTimeDay2TableColumn) {
		this.dateTimeDay2TableColumn = dateTimeDay2TableColumn;
	}

	public TableColumn<DateAndTime, LocalTime> getDateTimeHours2TableColumn() {
		return dateTimeHours2TableColumn;
	}

	public void setDateTimeHours2TableColumn(TableColumn<DateAndTime, LocalTime> dateTimeHours2TableColumn) {
		this.dateTimeHours2TableColumn = dateTimeHours2TableColumn;
	}

}
