package com.sm.net.sp.view.home.user.menu.memorial;

import java.io.IOException;
import java.time.LocalDate;

import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.model.EnumConventionType;
import com.sm.net.sp.model.WeekConvention;
import com.sm.net.sp.settings.Settings;
import com.sm.net.sp.view.SupportPlannerView;
import com.sm.net.sp.view.home.user.menu.conven.task.WeekConventionDeleteTask;
import com.sm.net.sp.view.home.user.menu.conven.task.WeekConventionLoadTask;
import com.sm.net.util.DateUtil;
import com.smnet.core.task.TaskManager;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Memorial {

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
	private TableColumn<WeekConvention, String> typeColumn;
	@FXML
	private TableColumn<WeekConvention, String> themeColumn;
	@FXML
	private Button deleteWeekButton;

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
		this.typeColumn.setCellValueFactory(cellData -> {
			IntegerProperty spInf1Property = cellData.getValue().spInf1Property();
			if (spInf1Property != null) {

				EnumConventionType type = EnumConventionType.getByID(spInf1Property.get());
				if (type != null)
					return new SimpleStringProperty(language.getString(type.getKey()));
			}
			return null;
		});
		this.themeColumn.setCellValueFactory(cellData -> cellData.getValue().spInf3Property());
	}

	private void styleClasses() {

		this.headerLabel.getStyleClass().add("label_header_001");

		this.tabPane.getStyleClass().add("tab_pane_001");

		this.calendarTab.getStyleClass().add("tab_001");
		this.weekTableView.getStyleClass().add("table_view_001");

		this.weekTableColumn.getStyleClass().add("table_column_002");
		this.fromTableColumn.getStyleClass().add("table_column_002");
		this.toTableColumn.getStyleClass().add("table_column_002");

		this.deleteWeekButton.getStyleClass().add("button_image_001");
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
		this.weekTableColumn.setMinWidth(100);
		this.weekTableColumn.setMaxWidth(100);

		this.fromTableColumn.setText(this.language.getString("TEXT0077"));
		this.fromTableColumn.setMinWidth(100);
		this.fromTableColumn.setMaxWidth(100);

		this.toTableColumn.setText(this.language.getString("TEXT0078"));
		this.toTableColumn.setMinWidth(100);
		this.toTableColumn.setMaxWidth(100);

		this.typeColumn.setText(this.language.getString("convention.tablecolumn.type"));
		this.themeColumn.setText(this.language.getString("convention.tablecolumn.theme"));

		Tooltip deleteTooltip = new Tooltip(language.getString("convention.tooltip.delete"));
		deleteTooltip.getStyleClass().add("tooltip_001");
		this.deleteWeekButton.setTooltip(deleteTooltip);
		this.deleteWeekButton.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.DELETE));
		this.deleteWeekButton.setText(null);
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

		String waitMessage = this.language.getString("convention.task.load");

//		TaskManager.run(this.application.getAlertBuilder2(), this.ownerStage, waitMessage,
//				new WeekConventionLoadTask(this.application.getAlertBuilder2(), this.settings, this.ownerStage, this));

	}

	private void listeners() {

		listenerWeekTableView();
		this.deleteWeekButton.setOnAction(event -> delete());
	}

	private void delete() {

		if (this.weekTableView.getSelectionModel().getSelectedIndex() > -1) {

			WeekConvention item = this.weekTableView.getSelectionModel().getSelectedItem();
			if (item.spConvenIDProperty() != null) {

				if (this.application.getAlertBuilder2().confirm(this.ownerStage,
						this.language.getString("convention.delete.confirm"))) {

					int id = item.getConvenID();

					String waitMessage = this.language.getString("convention.task.delete");

//					TaskManager.run(this.application.getAlertBuilder2(), this.ownerStage, waitMessage,
//							new WeekConventionDeleteTask(this.application.getAlertBuilder2(), this.settings,
//									this.ownerStage, this, id));
				}
			} else {

				this.application.getAlertBuilder2().error(this.ownerStage,
						this.language.getString("convention.delete.noconvention"));

			}

		} else {
			this.application.getAlertBuilder2().error(this.ownerStage,
					this.language.getString("convention.delete.noselection"));
		}
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
				fxmlLoader.setLocation(Meta.Views.MEMORIAL_EDITOR_FXML_URL);
				AnchorPane layout = (AnchorPane) fxmlLoader.load();

				MemorialEditor ctrl = (MemorialEditor) fxmlLoader.getController();
				ctrl.setSettings(this.settings);
				ctrl.setOwnerStage(ownerStage);
				ctrl.setOwnerCtrl(this);
				ctrl.setSelectedWeek(week);
				ctrl.setCalendar(this.calendar);
				ctrl.setApplication(this.application);

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

	public ImageView getHeaderImageView() {
		return headerImageView;
	}

	public Label getHeaderLabel() {
		return headerLabel;
	}

	public TabPane getTabPane() {
		return tabPane;
	}

	public Tab getCalendarTab() {
		return calendarTab;
	}

	public TableView<WeekConvention> getWeekTableView() {
		return weekTableView;
	}

	public TableColumn<WeekConvention, Integer> getWeekTableColumn() {
		return weekTableColumn;
	}

	public TableColumn<WeekConvention, LocalDate> getFromTableColumn() {
		return fromTableColumn;
	}

	public TableColumn<WeekConvention, LocalDate> getToTableColumn() {
		return toTableColumn;
	}

	public TableColumn<WeekConvention, String> getOverseerColumn() {
		return typeColumn;
	}

	public TableColumn<WeekConvention, String> getVisitNumberColumn() {
		return themeColumn;
	}

	public void setHeaderImageView(ImageView headerImageView) {
		this.headerImageView = headerImageView;
	}

	public void setHeaderLabel(Label headerLabel) {
		this.headerLabel = headerLabel;
	}

	public void setTabPane(TabPane tabPane) {
		this.tabPane = tabPane;
	}

	public void setCalendarTab(Tab calendarTab) {
		this.calendarTab = calendarTab;
	}

	public void setWeekTableView(TableView<WeekConvention> weekTableView) {
		this.weekTableView = weekTableView;
	}

	public void setWeekTableColumn(TableColumn<WeekConvention, Integer> weekTableColumn) {
		this.weekTableColumn = weekTableColumn;
	}

	public void setFromTableColumn(TableColumn<WeekConvention, LocalDate> fromTableColumn) {
		this.fromTableColumn = fromTableColumn;
	}

	public void setToTableColumn(TableColumn<WeekConvention, LocalDate> toTableColumn) {
		this.toTableColumn = toTableColumn;
	}

	public void setOverseerColumn(TableColumn<WeekConvention, String> overseerColumn) {
		this.typeColumn = overseerColumn;
	}

	public void setVisitNumberColumn(TableColumn<WeekConvention, String> visitNumberColumn) {
		this.themeColumn = visitNumberColumn;
	}

}
