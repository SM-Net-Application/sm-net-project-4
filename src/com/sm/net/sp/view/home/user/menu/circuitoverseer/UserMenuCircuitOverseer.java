package com.sm.net.sp.view.home.user.menu.circuitoverseer;

import java.io.IOException;
import java.time.LocalDate;

import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.actions.Actions;
import com.sm.net.sp.model.UpdateDataAdapter;
import com.sm.net.sp.model.WeekOverseer;
import com.sm.net.sp.settings.Settings;
import com.sm.net.sp.view.SupportPlannerView;
import com.sm.net.sp.view.home.user.menu.circuitoverseer.task.WeekOverseerDeleteTask;
import com.sm.net.util.DateUtil;
import com.smnet.core.task.TaskManager;

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

public class UserMenuCircuitOverseer extends UpdateDataAdapter {

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
	private TableColumn<WeekOverseer, String> visitNumberColumn;
	@FXML
	private TableColumn<WeekOverseer, String> overseerColumn;
	@FXML
	private TableColumn<WeekOverseer, String> overseerPhoneColumn;
	@FXML
	private TableColumn<WeekOverseer, String> overseerWifeColumn;
	@FXML
	private TableColumn<WeekOverseer, String> overseerWifePhoneColumn;
	@FXML
	private Button deleteWeekButton;

	private Settings settings;
	private Language language;
	private Stage ownerStage;
	private SupportPlannerView application;
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
		visitNumberColumn.setCellValueFactory(cellData -> cellData.getValue().visitNumberProperty());
		overseerColumn.setCellValueFactory(cellData -> cellData.getValue().overseerProperty());
		this.overseerPhoneColumn.setCellValueFactory(cellData -> cellData.getValue().spInf16Property());
		this.overseerWifeColumn.setCellValueFactory(cellData -> cellData.getValue().overseerWifeProperty());
		this.overseerWifePhoneColumn.setCellValueFactory(cellData -> cellData.getValue().spInf18Property());
	}

	private void styleClasses() {

		headerLabel.getStyleClass().add("label_header_001");

		tabPane.getStyleClass().add("tab_pane_001");

		calendarTab.getStyleClass().add("tab_001");
		weekTableView.getStyleClass().add("table_view_001");

		this.weekTableColumn.getStyleClass().add("table_column_002");
		this.fromTableColumn.getStyleClass().add("table_column_002");
		this.toTableColumn.getStyleClass().add("table_column_002");
		this.visitNumberColumn.getStyleClass().add("table_column_002");

		this.deleteWeekButton.getStyleClass().add("button_image_001");
	}

	public void objectInitialize() {
		viewUpdate();
		initData();
		listeners();
	}

	private void viewUpdate() {

		this.language = settings.getLanguage();

		headerImageView.setFitWidth(50);
		headerImageView.setFitHeight(50);
		headerImageView.setImage(Meta.Resources.getImageLogo(Meta.Resources.USER_MENU_CIRCUITOVERSEER, 50, 50));

		headerLabel.setText(language.getString("USERMENU005"));

		calendarTab.setText(language.getString("TEXT0075"));
		calendarTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.CALENDAR));

		weekTableColumn.setText(language.getString("TEXT0076"));

		fromTableColumn.setText(language.getString("TEXT0077"));

		toTableColumn.setText(language.getString("TEXT0078"));

		this.overseerPhoneColumn.setText(this.language.getString("overseer.tablecolumn.overseerphone"));

		this.overseerWifeColumn.setText(this.language.getString("overseer.tablecolumn.overseerwife"));
		this.overseerWifePhoneColumn.setText(this.language.getString("overseer.tablecolumn.overseerwifephone"));

		this.weekTableColumn.setMinWidth(100);
		this.weekTableColumn.setMaxWidth(100);

		this.fromTableColumn.setMinWidth(100);
		this.fromTableColumn.setMaxWidth(100);

		this.toTableColumn.setMinWidth(100);
		this.toTableColumn.setMaxWidth(100);

		this.visitNumberColumn.setMinWidth(150);
		this.visitNumberColumn.setMaxWidth(150);

		overseerColumn.setText(language.getString("TEXT0037"));
		visitNumberColumn.setText(language.getString("TEXT0139"));

		Tooltip deleteTooltip = new Tooltip(language.getString("overseer.tooltip.delete"));
		deleteTooltip.getStyleClass().add("tooltip_001");
		this.deleteWeekButton.setTooltip(deleteTooltip);
		this.deleteWeekButton.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.DELETE));
		this.deleteWeekButton.setText(null);
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
		this.deleteWeekButton.setOnAction(event -> delete());
	}

	private void delete() {

		if (this.weekTableView.getSelectionModel().getSelectedIndex() > -1) {

			WeekOverseer item = this.weekTableView.getSelectionModel().getSelectedItem();
			if (item.spWeekOvIDProperty() != null) {

				if (this.application.getAlertBuilder2().confirm(this.ownerStage,
						this.language.getString("overseer.delete.confirm"))) {

					int id = item.getSpWeekOvID();

					String waitMessage = this.language.getString("overseer.task.delete");

					TaskManager.run(this.application.getAlertBuilder2(), this.ownerStage, waitMessage,
							new WeekOverseerDeleteTask(this.application.getAlertBuilder2(), this.settings,
									this.ownerStage, this, id));
				}
			} else {

				this.application.getAlertBuilder2().error(this.ownerStage,
						this.language.getString("overseer.delete.nooverseer"));

			}

		} else {
			this.application.getAlertBuilder2().error(this.ownerStage,
					this.language.getString("overseer.delete.noselection"));
		}
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

				UserMenuCircuitOverviewEditor ctrl = (UserMenuCircuitOverviewEditor) fxmlLoader.getController();
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
