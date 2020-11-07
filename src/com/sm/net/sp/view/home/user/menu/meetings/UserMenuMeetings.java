package com.sm.net.sp.view.home.user.menu.meetings;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.actions.Actions;
import com.sm.net.sp.model.DateAndTime;
import com.sm.net.sp.model.EnumDays;
import com.sm.net.sp.model.EnumPrintLayouts;
import com.sm.net.sp.model.Family;
import com.sm.net.sp.model.Info;
import com.sm.net.sp.model.Member;
import com.sm.net.sp.model.Place;
import com.sm.net.sp.model.UpdateDataAdapter;
import com.sm.net.sp.model.Week;
import com.sm.net.sp.model.WeekAudio;
import com.sm.net.sp.model.WeekConvention;
import com.sm.net.sp.model.WeekMemorial;
import com.sm.net.sp.model.WeekUsciere;
import com.sm.net.sp.settings.Settings;
import com.sm.net.sp.utils.AlertBuilderOld;
import com.sm.net.sp.view.SupportPlannerView;
import com.sm.net.sp.view.home.user.menu.meetings.task.MeetingsInitDataLoadTask;
import com.sm.net.sp.view.printlayout.PrintLayout;
import com.sm.net.util.DateUtil;
import com.smnet.core.task.TaskManager;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class UserMenuMeetings extends UpdateDataAdapter {

	@FXML
	private ImageView headerImageView;
	@FXML
	private Label headerLabel;
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
	@FXML
	private TableColumn<Week, String> meeting1Column;
	@FXML
	private TableColumn<Week, String> bibleChaptersColumn;
	@FXML
	private TableColumn<Week, String> meeting2Column;
	@FXML
	private TableColumn<Week, String> typeColumn;
	@FXML
	private Button printButton;

	private Settings settings;
	private Language language;
	private Stage ownerStage;
	private ObservableList<Week> calendar;
	private ObservableList<Week> databaseWeeks;
	private ObservableList<Member> membersList;
	private AlertBuilderOld alertBuilder;

	private ObservableList<DateAndTime> dateAndTimeList;
	private ObservableList<Place> placesList;
	private HashMap<String, String> configs;
	private ObservableList<WeekConvention> convention;
	private ObservableList<WeekMemorial> memorial;
	private ObservableList<Family> familiesList;
	private ObservableList<WeekAudio> audio;
	private ObservableList<WeekUsciere> usciere;

	private SupportPlannerView application;

	private String congregationName;

	@FXML
	private void initialize() {
		styleClasses();
		cellValueFactory();
	}

	private void cellValueFactory() {

		weekTableColumn.setCellValueFactory(cellData -> cellData.getValue().weekProperty().asObject());
		fromTableColumn.setCellValueFactory(cellData -> cellData.getValue().fromProperty());
		toTableColumn.setCellValueFactory(cellData -> cellData.getValue().toProperty());

		this.meeting1Column.setCellValueFactory(cellData -> {

			Week week = cellData.getValue();

			if (week.spWeekIDProperty() != null) {

				int dayID = week.getSpInf44();
				if (dayID > 0) {

					EnumDays day = EnumDays.getByID(dayID);

					int hour = week.getSpInf45();
					int minute = week.getSpInf46();

					String format = this.language.getString("meeting.tablecolumn.patternday");

					return new SimpleStringProperty(
							String.format(format, this.language.getString(day.getKey()), hour, minute));
				}
			}

			return null;

		});

		this.meeting2Column.setCellValueFactory(cellData -> {

			Week week = cellData.getValue();

			if (week.spWeekIDProperty() != null) {

				int dayID = week.getSpInf47();
				if (dayID > 0) {

					EnumDays day = EnumDays.getByID(dayID);

					int hour = week.getSpInf48();
					int minute = week.getSpInf49();

					String format = this.language.getString("meeting.tablecolumn.patternday");

					return new SimpleStringProperty(
							String.format(format, this.language.getString(day.getKey()), hour, minute));
				}
			}

			return null;

		});

		this.bibleChaptersColumn.setCellValueFactory(cellData -> cellData.getValue().spInf6Property());

		typeColumn.setCellValueFactory(cellData -> cellData.getValue().getWeekTypeTranslated().nameProperty());
	}

	private void styleClasses() {

		headerLabel.getStyleClass().add("label_header_001");

		tabPane.getStyleClass().add("tab_pane_001");

		calendarTab.getStyleClass().add("tab_001");
		weekTableView.getStyleClass().add("table_view_001");

		this.weekTableColumn.getStyleClass().add("table_column_002");
		this.fromTableColumn.getStyleClass().add("table_column_002");
		this.toTableColumn.getStyleClass().add("table_column_002");

		printButton.getStyleClass().add("button_image_001");
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
		headerImageView.setImage(Meta.Resources.getImageLogo(Meta.Resources.USER_MENU_MEETINGS, 50, 50));

		headerLabel.setText(language.getString("USERMENU004"));

		calendarTab.setText(language.getString("TEXT0075"));
		calendarTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.CALENDAR));
		weekTableColumn.setText(language.getString("TEXT0076"));
		fromTableColumn.setText(language.getString("TEXT0077"));
		toTableColumn.setText(language.getString("TEXT0078"));

		this.meeting1Column.setText(this.language.getString("meetings.tablecolumn.meeting1"));
		this.bibleChaptersColumn.setText(this.language.getString("meetings.tablecolumn.biblechapters"));
		this.meeting2Column.setText(this.language.getString("meetings.tablecolumn.meeting2"));

		typeColumn.setText(language.getString("TEXT0091"));

		this.weekTableColumn.setMinWidth(100);
		this.weekTableColumn.setMaxWidth(100);
		this.fromTableColumn.setMinWidth(100);
		this.fromTableColumn.setMaxWidth(100);
		this.toTableColumn.setMinWidth(100);
		this.toTableColumn.setMaxWidth(100);

		Tooltip printTooltip = new Tooltip(language.getString("meetings.tooltip.print"));
		printTooltip.getStyleClass().add("tooltip_001");
		this.printButton.setTooltip(printTooltip);
		this.printButton.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.PRINT));
		this.printButton.setText(null);
	}

	private void initData() {
		this.weekTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		loadGeneralInfo();
		loadCalendar();
		updateMembers();
	}

	private void loadGeneralInfo() {
		Actions.getUserMenuMeetingsInfo(settings, ownerStage, this);
	}

	@Override
	public void updateInfo(Info info) {
		super.updateInfo(info);

		this.congregationName = info.getCongr();
	}

	private void loadCalendar() {

		calendar = FXCollections.observableArrayList();

		LocalDate now = LocalDate.now();

		int month = now.getMonthValue();
		int year = (month == 1) ? (now.getYear() - 1) : now.getYear();
		month = (month == 1) ? 12 : (month - 1);

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
				calendar.add(new Week(day.plusDays(6), language));
			else {
				countMonth = countMonth + 1;
				if (countMonth < 4)
					calendar.add(new Week(day.plusDays(6), language));
			}

		} while (countMonth < 4);

		weekTableView.setItems(calendar);
	}

	@Override
	public void updateMembers() {
		super.updateMembers();

		Actions.getAllMembers(settings, ownerStage, this);
	}

	@Override
	public void updateMembers(ObservableList<Member> list) {
		super.updateMembers(list);

		if (list != null)
			this.membersList = list;
		else
			this.membersList = FXCollections.observableArrayList();

		updateFamilies();
	}

	@Override
	public void updateFamilies() {
		Actions.getAllFamilies(settings, ownerStage, this);
	}

	@Override
	public void updateFamilies(ObservableList<Family> list) {

		if (list != null) {

			this.familiesList = list;
			this.familiesList.sort((a, b) -> a.getSpInf1Decrypted().compareTo(b.getSpInf1Decrypted()));
		} else
			this.membersList = FXCollections.observableArrayList();

		updateWeeks();
	}

	@Override
	public void updateWeeks() {
		super.updateWeeks();

		if (this.calendar != null)
			if (this.calendar.size() > 0) {
				Week weekStart = this.calendar.get(0);
				Week weekEnd = this.calendar.get(this.calendar.size() - 1);

				Actions.getAllWeeks(weekStart, weekEnd, this.settings, this.ownerStage, this.membersList, this);
			}
	}

	@Override
	public void updateWeeks(ObservableList<Week> list) {
		super.updateWeeks(list);

		this.databaseWeeks = null;
		if (list != null) {
			this.databaseWeeks = list;
			for (Week week : this.calendar)
				week.updateOnlineWeekInfo(list, this.language, this.settings);

			this.weekTableView.refresh();
		}

		// Carica programmazione e luoghi

		String waitMessage = this.language.getString("datetime.wait.load");

		TaskManager.run(this.application.getAlertBuilder2(), this.ownerStage, waitMessage, new MeetingsInitDataLoadTask(
				this.application.getAlertBuilder2(), this.settings, this.ownerStage, this));
	}

	private void listeners() {

		listenerWeekTableView();
		listenerPrintButton();
	}

	private void listenerPrintButton() {
		this.printButton.setOnAction(event -> printWeeks());
	}

	private void printWeeks() {

		if (this.weekTableView.getSelectionModel().getSelectedIndex() > -1) {

			ArrayList<Week> printableWeeks = new ArrayList<>();

			ObservableList<Week> weeks = this.weekTableView.getSelectionModel().getSelectedItems();
			for (Week week : weeks)
				if (week.spWeekIDProperty() != null)
					printableWeeks.add(week);

			if (printableWeeks.size() > 0) {

				EnumPrintLayouts selectedLayout = PrintLayout.dialogPrintLayout(this.ownerStage, language, null,
						EnumPrintLayouts.MEETING_COMPLETE_NAME_EXTENDED, EnumPrintLayouts.MEETING_COMPLETE_NAME_SHORT);

				if (selectedLayout != null) {

					switch (selectedLayout) {

					case MEETING_COMPLETE_NAME_EXTENDED:
						Actions.printWeekComplete(printableWeeks, membersList, this.familiesList, this.convention,
								this.memorial, this.audio, this.usciere, this.configs, congregationName, settings,
								ownerStage, language, true);
						break;

					case MEETING_COMPLETE_NAME_SHORT:
						Actions.printWeekComplete(printableWeeks, membersList, this.familiesList, this.convention,
								this.memorial, this.audio, this.usciere, this.configs, congregationName, settings,
								ownerStage, language, false);
						break;

					default:
						break;
					}
				}
			}
		}
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
				ctrl.setMemberList(this.membersList);
				ctrl.setSettings(this.settings);
				ctrl.setOwnerStage(ownerStage);
				ctrl.setOwnerCtrl(this);
				ctrl.setSelectedWeek(week);
				ctrl.setDatabaseWeeks(this.databaseWeeks);
				ctrl.setAlertBuilder(this.alertBuilder);
				ctrl.setApplication(this.application);
				ctrl.setDatabaseWeeksAudio(this.audio);
				ctrl.setDatabaseWeeksUsciere(this.usciere);
				ctrl.setConfigs(this.configs);

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

	public ObservableList<Week> getCalendar() {
		return calendar;
	}

	public void setCalendar(ObservableList<Week> calendar) {
		this.calendar = calendar;
	}

	public ObservableList<Member> getMembersList() {
		return membersList;
	}

	public void setMembersList(ObservableList<Member> membersList) {
		this.membersList = membersList;
	}

	public String getCongregationName() {
		return congregationName;
	}

	public void setCongregationName(String congregationName) {
		this.congregationName = congregationName;
	}

	public ObservableList<Week> getDatabaseWeeks() {
		return databaseWeeks;
	}

	public void setDatabaseWeeks(ObservableList<Week> databaseWeeks) {
		this.databaseWeeks = databaseWeeks;
	}

	public AlertBuilderOld getAlertBuilder() {
		return alertBuilder;
	}

	public void setAlertBuilder(AlertBuilderOld alertBuilder) {
		this.alertBuilder = alertBuilder;
	}

	public SupportPlannerView getApplication() {
		return application;
	}

	public void setApplication(SupportPlannerView application) {
		this.application = application;
	}

	public ObservableList<DateAndTime> getDateAndTimeList() {
		return dateAndTimeList;
	}

	public void setDateAndTimeList(ObservableList<DateAndTime> dateAndTimeList) {
		this.dateAndTimeList = dateAndTimeList;
	}

	public ObservableList<Place> getPlacesList() {
		return placesList;
	}

	public void setPlacesList(ObservableList<Place> placesList) {
		this.placesList = placesList;
	}

	public HashMap<String, String> getConfigs() {
		return configs;
	}

	public void setConfigs(HashMap<String, String> configs) {
		this.configs = configs;
	}

	public ObservableList<WeekConvention> getConvention() {
		return convention;
	}

	public void setConvention(ObservableList<WeekConvention> convention) {
		this.convention = convention;
	}

	public ObservableList<WeekMemorial> getMemorial() {
		return memorial;
	}

	public void setMemorial(ObservableList<WeekMemorial> memorial) {
		this.memorial = memorial;
	}

	public ObservableList<WeekAudio> getAudio() {
		return audio;
	}

	public void setAudio(ObservableList<WeekAudio> audio) {
		this.audio = audio;
	}

	public ObservableList<WeekUsciere> getUsciere() {
		return usciere;
	}

	public void setUsciere(ObservableList<WeekUsciere> usciere) {
		this.usciere = usciere;
	}
}
