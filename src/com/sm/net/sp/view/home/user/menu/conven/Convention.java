package com.sm.net.sp.view.home.user.menu.conven;

import java.io.IOException;
import java.time.LocalDate;

import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.model.WeekConvention;
import com.sm.net.sp.settings.Settings;
import com.sm.net.sp.view.SupportPlannerView;
import com.sm.net.util.DateUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Convention {

	@FXML
	private ImageView headerImageView;
	@FXML
	private Label headerLabel;
	@FXML
	private TabPane tabPane;
	@FXML
	private Tab calendarTab;
	@FXML
	private TableView<WeekConvention> weekTableView;
	@FXML
	private TableColumn<WeekConvention, Integer> weekTableColumn;
	@FXML
	private TableColumn<WeekConvention, LocalDate> fromTableColumn;
	@FXML
	private TableColumn<WeekConvention, LocalDate> toTableColumn;
	@FXML
	private TableColumn<WeekConvention, String> overseerColumn;
	@FXML
	private TableColumn<WeekConvention, String> visitNumberColumn;

	private SupportPlannerView application;

	private Settings settings;
	private Language language;
	private Stage ownerStage;
	private ObservableList<WeekConvention> calendar;

	@FXML
	private void initialize() {
		styleClasses();
		cellValueFactory();
	}

	private void cellValueFactory() {

		this.weekTableColumn.setCellValueFactory(cellData -> cellData.getValue().weekProperty().asObject());
		this.fromTableColumn.setCellValueFactory(cellData -> cellData.getValue().fromProperty());
		this.toTableColumn.setCellValueFactory(cellData -> cellData.getValue().toProperty());
		this.overseerColumn.setCellValueFactory(cellData -> cellData.getValue().overseerProperty());
		this.visitNumberColumn.setCellValueFactory(cellData -> cellData.getValue().visitNumberProperty());
	}

	private void styleClasses() {

		this.headerLabel.getStyleClass().add("label_header_001");

		this.tabPane.getStyleClass().add("tab_pane_001");

		this.calendarTab.getStyleClass().add("tab_001");
		this.weekTableView.getStyleClass().add("table_view_001");
	}

	public void objectInitialize() {

		this.settings = this.application.getSettings();

		viewUpdate();
		initData();
		listeners();
	}

	private void viewUpdate() {

		this.language = settings.getLanguage();

		this.headerImageView.setFitWidth(50);
		this.headerImageView.setFitHeight(50);
		this.headerImageView.setImage(Meta.Resources.getImageLogo(Meta.Resources.CONVENTIONS, 50, 50));

		this.headerLabel.setText(this.language.getString("convention.header"));

		this.calendarTab.setText(this.language.getString("TEXT0075"));
		this.calendarTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.CALENDAR));

		this.weekTableColumn.setText(this.language.getString("TEXT0076"));
		this.fromTableColumn.setText(this.language.getString("TEXT0077"));
		this.toTableColumn.setText(this.language.getString("TEXT0078"));
		this.overseerColumn.setText(this.language.getString("TEXT0037"));
		this.visitNumberColumn.setText(this.language.getString("TEXT0139"));
	}

	private void initData() {

		this.loadCalendar();
		this.updateWeeksData();
	}

	private void loadCalendar() {

		this.calendar = FXCollections.observableArrayList();

		// Data di oggi
		LocalDate now = LocalDate.now();

		int month = now.getMonthValue();
		int year = now.getYear();

		// Primo giorno del mese (determinato da now)
		LocalDate day = LocalDate.of(year, month, 1);

		// Primo giorno della settimana contenente il primo giorno del mese
		LocalDate firstDayOfWeek = DateUtil.getFirstDayOfWeek(day);

		// Se il lunedi ricade nel mese precedente, inizio dalla settimana successiva
		if (firstDayOfWeek.getMonthValue() != day.getMonthValue())
			day = firstDayOfWeek.plusDays(7);

		// Generazione del calendario
		int countMonth = 0;
		boolean first = true;

		int monthsToGenerate = 12;

		do {

			int lastMonth = day.getMonthValue();

			day = first ? day : day.plusDays(7);
			if (first)
				first = false;

			int currentMonth = day.getMonthValue();

			if (lastMonth == currentMonth)
				this.calendar.add(new WeekConvention(day.plusDays(6), this.language));
			else {
				countMonth = countMonth + 1;
				if (countMonth < monthsToGenerate)
					this.calendar.add(new WeekConvention(day.plusDays(6), this.language));
			}

		} while (countMonth < monthsToGenerate);

		this.weekTableView.setItems(this.calendar);
	}

	public void updateWeeksData() {

		if (this.calendar != null)
			if (this.calendar.size() > 0) {
				WeekConvention weekStart = this.calendar.get(0);
				WeekConvention weekEnd = this.calendar.get(this.calendar.size() - 1);

				// Actions.getAllCircuitOverseerWeeks(weekStart, weekEnd, this.settings,
				// this.ownerStage, this);
			}
	}

//	public void updateWeeksOverseer(ObservableList<WeekOverseer> list) {
//
//		if (list != null) {
////			for (WeekOverseer week : this.calendar)
////				week.updateOnlineWeekInfo(list, this.language, this.settings);
//
//			this.weekTableView.refresh();
//		}
//	}

	private void listeners() {

		listenerWeekTableView();
	}

	private void listenerWeekTableView() {

		this.weekTableView.setRowFactory(param -> {

			TableRow<WeekConvention> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (!row.isEmpty()))
					editWeek(row.getItem());
			});

			return row;
		});
	}

	private void editWeek(WeekConvention week) {

		if (!isAlreadyOpen(week.getFrom().toString())) {

			try {

				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(Meta.Views.CONVENTIONS_EDITOR_FXML_URL);
				AnchorPane layout = (AnchorPane) fxmlLoader.load();

				ConventionEditor ctrl = (ConventionEditor) fxmlLoader.getController();
				ctrl.setSettings(this.settings);
				ctrl.setOwnerStage(ownerStage);
				ctrl.setOwnerCtrl(this);
				ctrl.setSelectedWeek(week);
				ctrl.setCalendar(this.calendar);

				Tab newTab = new Tab(week.getFrom().toString(), layout);
				newTab.setClosable(true);
				newTab.getStyleClass().add("tab_001");
				newTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.CALENDAR));

				ctrl.setOwnerTabPane(tabPane);
				ctrl.setThisTab(newTab);

				this.tabPane.getTabs().add(newTab);
				this.tabPane.getSelectionModel().select(newTab);

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

	public ObservableList<WeekConvention> getCalendar() {
		return calendar;
	}

	public void setCalendar(ObservableList<WeekConvention> calendar) {
		this.calendar = calendar;
	}

	public SupportPlannerView getApplication() {
		return application;
	}

	public void setApplication(SupportPlannerView application) {
		this.application = application;
	}
}
