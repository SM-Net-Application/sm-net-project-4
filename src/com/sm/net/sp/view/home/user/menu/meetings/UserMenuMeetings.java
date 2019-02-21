package com.sm.net.sp.view.home.user.menu.meetings;

import java.io.IOException;
import java.time.LocalDate;

import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.model.Week;
import com.sm.net.sp.settings.Settings;
import com.sm.net.util.DateUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class UserMenuMeetings {

	@FXML
	private TabPane tabPane;
	@FXML
	private Tab calendarTab;
	@FXML
	private TableView<Week> weekTableView;
	@FXML
	private TableColumn<Week, Integer> weekTableColumn;
	@FXML
	private TableColumn<Week, LocalDate> fromTableColumn;
	@FXML
	private TableColumn<Week, LocalDate> toTableColumn;

	private Settings settings;
	private Language language;
	private Stage ownerStage;
	private ObservableList<Week> calendar;

	@FXML
	private void initialize() {
		styleClasses();
		cellValueFactory();
	}

	private void cellValueFactory() {

		weekTableColumn.setCellValueFactory(cellData -> cellData.getValue().weekProperty().asObject());
		fromTableColumn.setCellValueFactory(cellData -> cellData.getValue().fromProperty());
		toTableColumn.setCellValueFactory(cellData -> cellData.getValue().toProperty());
	}

	private void styleClasses() {

		tabPane.getStyleClass().add("tabPaneStyle1");

		calendarTab.getStyleClass().add("tabStyle1");
		weekTableView.getStyleClass().add("tableViewStyle1");
	}

	public void objectInitialize() {
		listeners();
		viewUpdate();
		initData();
	}

	private void initData() {
		loadCalendar();
	}

	private void loadCalendar() {

		calendar = FXCollections.observableArrayList();

		LocalDate now = LocalDate.now();

		int month = now.getMonthValue();
		int year = (month == 1) ? (now.getYear() - 1) : now.getYear();
		month = (month == 1) ? 12 : (month - 1);

		LocalDate day = LocalDate.of(year, month, 1);
		if (DateUtil.getFirstDayOfWeek(day).getMonthValue() != day.getMonthValue())
			day = day.plusDays(7);

		int countMonth = 0;
		boolean first = true;

		do {

			int lastMonth = day.getMonthValue();

			day = first ? day : day.plusDays(7);
			if (first)
				first = false;

			int currentMonth = day.getMonthValue();

			if (lastMonth == currentMonth)
				calendar.add(new Week(day));
			else {
				countMonth = countMonth + 1;
				if (countMonth < 4)
					calendar.add(new Week(day));
			}

		} while (countMonth < 4);

		weekTableView.setItems(calendar);
	}

	private void listeners() {
		listenerWeekTableView();
	}

	private void listenerWeekTableView() {
		weekTableView.setRowFactory(param -> {
			TableRow<Week> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (!row.isEmpty()))
					editWeek(row.getItem());
			});
			return row;
		});
	}

	private void editWeek(Week week) {

		if (!isAlreadyOpen(week.getFrom().toString())) {

			try {

				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(Meta.Views.HOME_USER_MENU_MEETINGS_EDITOR);
				AnchorPane layout = (AnchorPane) fxmlLoader.load();

				UserMenuMeetingsEditor ctrl = (UserMenuMeetingsEditor) fxmlLoader.getController();
				ctrl.setSettings(this.settings);
				ctrl.setOwnerStage(ownerStage);
				ctrl.setOwnerCtrl(this);
				ctrl.setSelectedWeek(week);

				Tab newTab = new Tab(week.getFrom().toString(), layout);
				newTab.setClosable(true);
				newTab.getStyleClass().add("tabStyle1");
				newTab.setGraphic(new ImageView(Meta.Resources.USER_MENU_MEETINGS));

				ctrl.setOwnerTabPane(tabPane);
				ctrl.setThisTab(newTab);

				tabPane.getTabs().add(newTab);
				tabPane.getSelectionModel().select(newTab);

				ctrl.objectInitialize();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	private boolean isAlreadyOpen(String label) {

		for (Tab tab : tabPane.getTabs())
			if (tab.getText().equals(label)) {
				tabPane.getSelectionModel().select(tab);
				return true;
			}

		return false;
	}

	private void viewUpdate() {

		this.language = settings.getLanguage();

		calendarTab.setText(language.getString("TEXT0075"));
		weekTableColumn.setText(language.getString("TEXT0076"));
		fromTableColumn.setText(language.getString("TEXT0077"));
		toTableColumn.setText(language.getString("TEXT0078"));
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

	public Stage getOwnerStage() {
		return ownerStage;
	}

	public void setOwnerStage(Stage ownerStage) {
		this.ownerStage = ownerStage;
	}

	public ObservableList<Week> getCalendar() {
		return calendar;
	}

	public void setCalendar(ObservableList<Week> calendar) {
		this.calendar = calendar;
	}
}
