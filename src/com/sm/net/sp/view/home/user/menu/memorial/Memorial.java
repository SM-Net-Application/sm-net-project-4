package com.sm.net.sp.view.home.user.menu.memorial;

import java.io.IOException;
import java.time.LocalDate;

import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.actions.Actions;
import com.sm.net.sp.model.UpdateDataAdapter;
import com.sm.net.sp.model.WeekOverseer;
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

public class Memorial extends UpdateDataAdapter {

	@FXML
	private ImageView headerImageView;
	@FXML
	private Label headerLabel;
	@FXML
	private TabPane tabPane;
	@FXML
	private Tab calendarTab;
	@FXML
	private TableView<WeekOverseer> weekTableView;
	@FXML
	private TableColumn<WeekOverseer, Integer> weekTableColumn;
	@FXML
	private TableColumn<WeekOverseer, LocalDate> fromTableColumn;
	@FXML
	private TableColumn<WeekOverseer, LocalDate> toTableColumn;
	@FXML
	private TableColumn<WeekOverseer, String> overseerColumn;
	@FXML
	private TableColumn<WeekOverseer, String> visitNumberColumn;

	private SupportPlannerView application;

	private Settings settings;
	private Language language;
	private Stage ownerStage;
	private ObservableList<WeekOverseer> calendar;

	@FXML
	private void initialize() {
		styleClasses();
		cellValueFactory();
	}

	private void cellValueFactory() {

		weekTableColumn.setCellValueFactory(cellData -> cellData.getValue().weekProperty().asObject());
		fromTableColumn.setCellValueFactory(cellData -> cellData.getValue().fromProperty());
		toTableColumn.setCellValueFactory(cellData -> cellData.getValue().toProperty());
		overseerColumn.setCellValueFactory(cellData -> cellData.getValue().overseerProperty());
		visitNumberColumn.setCellValueFactory(cellData -> cellData.getValue().visitNumberProperty());
	}

	private void styleClasses() {

		headerLabel.getStyleClass().add("label_header_001");

		tabPane.getStyleClass().add("tab_pane_001");

		calendarTab.getStyleClass().add("tab_001");
		weekTableView.getStyleClass().add("table_view_001");
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
		this.headerImageView.setImage(Meta.Resources.getImageLogo(Meta.Resources.MEMORIAL, 50, 50));

		this.headerLabel.setText(this.language.getString("memorial.header"));

		this.calendarTab.setText(this.language.getString("TEXT0075"));
		this.calendarTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.CALENDAR));
		this.weekTableColumn.setText(this.language.getString("TEXT0076"));
		this.fromTableColumn.setText(this.language.getString("TEXT0077"));
		this.toTableColumn.setText(this.language.getString("TEXT0078"));
		this.overseerColumn.setText(this.language.getString("TEXT0037"));
		this.visitNumberColumn.setText(this.language.getString("TEXT0139"));
	}

	private void initData() {
		loadCalendar();
		updateWeeksOverseer();
	}

	private void loadCalendar() {

		calendar = FXCollections.observableArrayList();

		LocalDate now = LocalDate.now();

		int month = now.getMonthValue();
		int year = now.getYear();

		LocalDate day = LocalDate.of(year, month, 1);
		LocalDate firstDayOfWeek = DateUtil.getFirstDayOfWeek(day);
		if (firstDayOfWeek.getMonthValue() != day.getMonthValue())
			day = firstDayOfWeek.plusDays(7);

		int countMonth = 0;
		boolean first = true;

		do {

			int lastMonth = day.getMonthValue();

			day = first ? day : day.plusDays(7);
			if (first)
				first = false;

			int currentMonth = day.getMonthValue();

			if (lastMonth == currentMonth)
				calendar.add(new WeekOverseer(day.plusDays(6), language));
			else {
				countMonth = countMonth + 1;
				if (countMonth < 12)
					calendar.add(new WeekOverseer(day.plusDays(6), language));
			}

		} while (countMonth < 12);

		weekTableView.setItems(calendar);
	}

	@Override
	public void updateWeeksOverseer() {
		super.updateWeeksOverseer();

		if (this.calendar != null)
			if (this.calendar.size() > 0) {
				WeekOverseer weekStart = this.calendar.get(0);
				WeekOverseer weekEnd = this.calendar.get(this.calendar.size() - 1);

				Actions.getAllCircuitOverseerWeeks(weekStart, weekEnd, this.settings, this.ownerStage, this);
			}
	}

	@Override
	public void updateWeeksOverseer(ObservableList<WeekOverseer> list) {
		super.updateWeeksOverseer(list);

		if (list != null) {
			for (WeekOverseer week : this.calendar)
				week.updateOnlineWeekInfo(list, this.language, this.settings);

			this.weekTableView.refresh();
		}
	}

	private void listeners() {

		listenerWeekTableView();
	}

	private void listenerWeekTableView() {
		weekTableView.setRowFactory(param -> {
			TableRow<WeekOverseer> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (!row.isEmpty()))
					editWeek(row.getItem());
			});
			return row;
		});
	}

	private void editWeek(WeekOverseer week) {

		if (!isAlreadyOpen(week.getFrom().toString())) {

			try {

				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(Meta.Views.HOME_USER_MENU_CIRCUITOVERSEER_EDITOR);
				AnchorPane layout = (AnchorPane) fxmlLoader.load();

				MemorialEditor ctrl = (MemorialEditor) fxmlLoader.getController();
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

	public ObservableList<WeekOverseer> getCalendar() {
		return calendar;
	}

	public void setCalendar(ObservableList<WeekOverseer> calendar) {
		this.calendar = calendar;
	}

	public SupportPlannerView getApplication() {
		return application;
	}

	public void setApplication(SupportPlannerView application) {
		this.application = application;
	}
}
