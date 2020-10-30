package com.sm.net.sp.view.home.user.menu.usciere;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;

import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.actions.Actions;
import com.sm.net.sp.model.Member;
import com.sm.net.sp.model.UpdateDataAdapter;
import com.sm.net.sp.model.WeekUsciere;
import com.sm.net.sp.settings.Settings;
import com.sm.net.sp.view.SupportPlannerView;
import com.sm.net.sp.view.home.user.menu.usciere.task.UsciereInitDataLoadTask;
import com.sm.net.sp.view.home.user.menu.usciere.task.WeekUsciereDeleteTask;
import com.sm.net.sp.view.home.user.menu.usciere.task.WeekUsciereLoadTask;
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

public class Usciere extends UpdateDataAdapter {

	@FXML
	private ImageView headerImageView;
	@FXML
	private Label headerLabel;
	@FXML
	private TabPane tabPane;
	@FXML
	private Tab calendarTab;
	@FXML
	private TableView<WeekUsciere> weekTableView;
	@FXML
	private TableColumn<WeekUsciere, Integer> weekTableColumn;
	@FXML
	private TableColumn<WeekUsciere, LocalDate> fromTableColumn;
	@FXML
	private TableColumn<WeekUsciere, LocalDate> toTableColumn;
	@FXML
	private TableColumn<WeekUsciere, String> posMidweekColumn;
	@FXML
	private TableColumn<WeekUsciere, String> micMidweekColumn;
	@FXML
	private TableColumn<WeekUsciere, String> posWeekendColumn;
	@FXML
	private TableColumn<WeekUsciere, String> micWeekendColumn;
	@FXML
	private Button deleteWeekButton;

	private SupportPlannerView application;

	private Settings settings;
	private Language language;
	private Stage ownerStage;

	private ObservableList<WeekUsciere> calendar;

	private ObservableList<Member> membersList;
	private HashMap<String, String> configs;

	@FXML
	private void initialize() {
		styleClasses();
		cellValueFactory();
	}

	private void cellValueFactory() {

		this.weekTableColumn.setCellValueFactory(cellData -> cellData.getValue().weekProperty().asObject());
		this.fromTableColumn.setCellValueFactory(cellData -> cellData.getValue().fromProperty());
		this.toTableColumn.setCellValueFactory(cellData -> cellData.getValue().toProperty());

		this.posMidweekColumn.setCellValueFactory(cellData -> cellData.getValue().z1MidweekProperty());
		this.micMidweekColumn.setCellValueFactory(cellData -> cellData.getValue().z2MidweekProperty());
		this.posWeekendColumn.setCellValueFactory(cellData -> cellData.getValue().z3MidweekProperty());
		this.micWeekendColumn.setCellValueFactory(cellData -> cellData.getValue().z1WeekendProperty());
		
		//TODO altre 2 colonne
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
		this.headerImageView.setImage(Meta.Resources.getImageLogo(Meta.Resources.USCIERE, 50, 50));

		this.headerLabel.setText(this.language.getString("usciere.header"));

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

		this.posMidweekColumn.setText(this.language.getString("audio.tablecolumn.pos"));
		this.micMidweekColumn.setText(this.language.getString("audio.tablecolumn.mic"));
		this.posWeekendColumn.setText(this.language.getString("audio.tablecolumn.pos"));
		this.micWeekendColumn.setText(this.language.getString("audio.tablecolumn.mic"));

		Tooltip deleteTooltip = new Tooltip(language.getString("memorial.tooltip.delete"));
		deleteTooltip.getStyleClass().add("tooltip_001");
		this.deleteWeekButton.setTooltip(deleteTooltip);
		this.deleteWeekButton.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.DELETE));
		this.deleteWeekButton.setText(null);
	}

	private void initData() {

		this.loadCalendar();
		this.membersList = FXCollections.observableArrayList();

		updateMembers();
	}

	@Override
	public void updateMembers() {
		Actions.getAllMembers(settings, ownerStage, this);
	}

	@Override
	public void updateMembers(ObservableList<Member> list) {

		this.membersList = list;
		this.membersList.sort((a, b) -> (a.getSpInf2Decrypted().concat(a.getSpInf1Decrypted())
				.compareTo(b.getSpInf2Decrypted().concat(b.getSpInf1Decrypted()))));

		String waitMessage = this.language.getString("audio.wait.load");
		TaskManager.run(this.application.getAlertBuilder2(), this.ownerStage, waitMessage,
				new UsciereInitDataLoadTask(this.application.getAlertBuilder2(), this.settings, this.ownerStage, this));
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
				this.calendar.add(new WeekUsciere(day.plusDays(6), this.language));
			else {
				countMonth = countMonth + 1;
				if (countMonth < monthsToGenerate)
					this.calendar.add(new WeekUsciere(day.plusDays(6), this.language));
			}

		} while (countMonth < monthsToGenerate);

		this.weekTableView.setItems(this.calendar);
	}

	public void updateWeeksData() {

		String waitMessage = this.language.getString("audio.wait.load");

		TaskManager.run(this.application.getAlertBuilder2(), this.ownerStage, waitMessage, new WeekUsciereLoadTask(
				this.application.getAlertBuilder2(), this.settings, this.ownerStage, this, this.membersList));

	}

	private void listeners() {

		listenerWeekTableView();
		this.deleteWeekButton.setOnAction(event -> delete());
	}

	private void delete() {

		if (this.weekTableView.getSelectionModel().getSelectedIndex() > -1) {

			WeekUsciere item = this.weekTableView.getSelectionModel().getSelectedItem();
			if (item.spUscIDProperty() != null) {

				if (this.application.getAlertBuilder2().confirm(this.ownerStage,
						this.language.getString("audio.delete.confirm"))) {

					int id = item.getUscID();

					String waitMessage = this.language.getString("audio.task.delete");

					TaskManager.run(this.application.getAlertBuilder2(), this.ownerStage, waitMessage,
							new WeekUsciereDeleteTask(this.application.getAlertBuilder2(), this.settings,
									this.ownerStage, this, id));
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

			TableRow<WeekUsciere> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (!row.isEmpty()))
					editWeek(row.getItem());
			});

			return row;
		});
	}

	private void editWeek(WeekUsciere week) {

		if (!isAlreadyOpen(week.getFrom().toString())) {

			try {

				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(Meta.Views.USCIERE_EDITOR_FXML_URL);
				AnchorPane layout = (AnchorPane) fxmlLoader.load();

				UsciereEditor ctrl = (UsciereEditor) fxmlLoader.getController();
				ctrl.setSettings(this.settings);
				ctrl.setOwnerStage(ownerStage);
				ctrl.setOwnerCtrl(this);
				ctrl.setSelectedWeek(week);
				ctrl.setCalendar(this.calendar);
				ctrl.setApplication(this.application);
				ctrl.setMembersList(this.membersList);
				ctrl.setConfigs(this.configs);

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

	public ObservableList<WeekUsciere> getCalendar() {
		return calendar;
	}

	public void setCalendar(ObservableList<WeekUsciere> calendar) {
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

	public TableView<WeekUsciere> getWeekTableView() {
		return weekTableView;
	}

	public TableColumn<WeekUsciere, Integer> getWeekTableColumn() {
		return weekTableColumn;
	}

	public TableColumn<WeekUsciere, LocalDate> getFromTableColumn() {
		return fromTableColumn;
	}

	public TableColumn<WeekUsciere, LocalDate> getToTableColumn() {
		return toTableColumn;
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

	public void setWeekTableView(TableView<WeekUsciere> weekTableView) {
		this.weekTableView = weekTableView;
	}

	public void setWeekTableColumn(TableColumn<WeekUsciere, Integer> weekTableColumn) {
		this.weekTableColumn = weekTableColumn;
	}

	public void setFromTableColumn(TableColumn<WeekUsciere, LocalDate> fromTableColumn) {
		this.fromTableColumn = fromTableColumn;
	}

	public void setToTableColumn(TableColumn<WeekUsciere, LocalDate> toTableColumn) {
		this.toTableColumn = toTableColumn;
	}

	public HashMap<String, String> getConfigs() {
		return configs;
	}

	public void setConfigs(HashMap<String, String> configs) {
		this.configs = configs;
	}
}
