package com.sm.net.sp.view.home.user.menu.publicmeetings;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Optional;
import java.util.stream.StreamSupport;

import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.actions.Actions;
import com.sm.net.sp.model.DateAndTime;
import com.sm.net.sp.model.Member;
import com.sm.net.sp.model.Place;
import com.sm.net.sp.model.Song;
import com.sm.net.sp.model.UpdateDataAdapter;
import com.sm.net.sp.model.Week;
import com.sm.net.sp.model.WeekAudio;
import com.sm.net.sp.model.WeekUsciere;
import com.sm.net.sp.settings.Settings;
import com.sm.net.sp.utils.AlertBuilderOld;
import com.sm.net.sp.view.SupportPlannerView;
import com.sm.net.sp.view.home.user.menu.publicmeetings.task.PublicMeetingsInitDataLoadTask;
import com.sm.net.util.Crypt;
import com.sm.net.util.DateUtil;
import com.smnet.core.task.TaskManager;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class UserMenuPublicMeetings extends UpdateDataAdapter {

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
	private TableColumn<Week, String> typeColumn;
	@FXML
	private TableColumn<Week, String> presidentColumn;
	@FXML
	private TableColumn<Week, String> speakerColumn;
	@FXML
	private TableColumn<Week, String> speakerCongregationColumn;
	@FXML
	private TableColumn<Week, String> talkColumn;
//	@FXML
//	private Button printButton;

	@FXML
	private TextField filterTextField;

	private ObservableList<Song> songList;
	private boolean configSongTitleLoad;

	private Settings settings;
	private Language language;
	private Stage ownerStage;
	private ObservableList<Week> calendar;
	private ObservableList<Week> databaseWeeks;
	private ObservableList<Member> membersList;

	private AlertBuilderOld alertBuilder;

	private SupportPlannerView application;

	private ObservableList<DateAndTime> dateAndTimeList;
	private ObservableList<Place> placesList;
	private HashMap<String, String> configs;
	private ObservableList<WeekAudio> audio;
	private ObservableList<WeekUsciere> usciere;

//	private String congregationName;

	@FXML
	private void initialize() {
		styleClasses();
		cellValueFactory();
	}

	private void cellValueFactory() {

		weekTableColumn.setCellValueFactory(cellData -> cellData.getValue().weekProperty().asObject());
		fromTableColumn.setCellValueFactory(cellData -> cellData.getValue().fromProperty());
		toTableColumn.setCellValueFactory(cellData -> cellData.getValue().toProperty());
		typeColumn.setCellValueFactory(cellData -> cellData.getValue().getWeekTypeTranslated().nameProperty());

		presidentColumn.setCellValueFactory(cellData -> {
			IntegerProperty spInf30Property = cellData.getValue().spInf30Property();
			if (spInf30Property != null) {
				if (this.membersList != null) {

					Optional<Member> find = StreamSupport.stream(this.membersList.spliterator(), false)
							.filter(m -> m.getSpMemberID() == spInf30Property.get()).findFirst();

					if (find.isPresent())
						return new SimpleStringProperty(find.get().getNameStyle1());
				}
			}
			return null;
		});

		speakerColumn.setCellValueFactory(cellData -> {

			if (cellData.getValue().spInf66Property() != null) {

				int internSpeaker = cellData.getValue().getSpInf66();
				if (internSpeaker > 0) {

					Optional<Member> find = StreamSupport.stream(this.membersList.spliterator(), false)
							.filter(member -> (internSpeaker == member.getSpMemberID())).findFirst();
					if (find.isPresent()) {

						Member member = find.get();
						return new SimpleStringProperty(member.getNameStyle1());

					}

				}
			}

			return cellData.getValue().spInf33Property();
		});

		speakerCongregationColumn.setCellValueFactory(cellData -> cellData.getValue().spInf34Property());
		talkColumn.setCellValueFactory(cellData -> cellData.getValue().spInf32Property());
	}

	private void styleClasses() {

		headerLabel.getStyleClass().add("label_header_001");

		tabPane.getStyleClass().add("tab_pane_001");

		calendarTab.getStyleClass().add("tab_001");
		weekTableView.getStyleClass().add("table_view_001");

		this.weekTableColumn.getStyleClass().add("table_column_002");
		this.fromTableColumn.getStyleClass().add("table_column_002");
		this.toTableColumn.getStyleClass().add("table_column_002");

		this.filterTextField.getStyleClass().add("text_field_001");

//		printButton.getStyleClass().add("button_image_001");
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
		headerImageView.setImage(Meta.Resources.getImageLogo(Meta.Resources.PUBLIC_TALK, 50, 50));

		headerLabel.setText(language.getString("sp.menu.publictalk"));

		calendarTab.setText(language.getString("TEXT0075"));
		calendarTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.CALENDAR));

		weekTableColumn.setText("");
		weekTableColumn.setMinWidth(50.0);
		weekTableColumn.setMaxWidth(50.0);

		fromTableColumn.setText(language.getString("TEXT0077"));
		fromTableColumn.setMinWidth(100.0);
		fromTableColumn.setMaxWidth(100.0);

		toTableColumn.setText(language.getString("TEXT0078"));
		toTableColumn.setMinWidth(100.0);
		toTableColumn.setMaxWidth(100.0);

		typeColumn.setText(language.getString("TEXT0091"));

		presidentColumn.setText(language.getString("sp.meetings.presidentpublicmeeting"));
		speakerColumn.setText(language.getString("sp.meetings.publictalktalker"));
		speakerCongregationColumn.setText(language.getString("sp.meetings.publictalktalkercongr"));

		talkColumn.setText(language.getString("sp.meetings.publictalktheme"));
		talkColumn.setMinWidth(300.0);

//		printButton.setGraphic(Meta.Resources.imageViewForButton(Meta.Resources.PRINT));
//		printButton.setText(null);
	}

	public void checkConfigs() {
		String inf19 = Crypt.decrypt(this.configs.get("inf19"), this.application.getSettings().getDatabaseSecretKey());
		this.configSongTitleLoad = inf19.equals("1");
	}

	private void initData() {
		this.weekTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
//		loadGeneralInfo();
		loadCalendar();
		updateMembers();

		String waitMessage = this.language.getString("datetime.wait.load");

		TaskManager.run(this.application.getAlertBuilder2(), this.ownerStage, waitMessage,
				new PublicMeetingsInitDataLoadTask(this.application.getAlertBuilder2(), this.settings, this.ownerStage,
						this));
	}

//	private void loadGeneralInfo() {
//		Actions.getUserMenuMeetingsInfo(settings, ownerStage, this);
//	}
//
//	@Override
//	public void updateInfo(Info info) {
//		super.updateInfo(info);
//
//		this.congregationName = info.getCongr();
//	}

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
				if (countMonth < 8)
					calendar.add(new Week(day.plusDays(6), language));
			}

		} while (countMonth < 8);

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
	}

	private void listeners() {

		listenerWeekTableView();

		this.filterTextField.textProperty().addListener((observable, oldValue, newValue) -> updateFilter(newValue));

//		listenerPrintButton();
	}

	private void updateFilter(String newValue) {

		if (newValue.isEmpty())
			this.weekTableView.setItems(this.calendar);
		else {
			ObservableList<Week> filteredList = buildList(newValue);
			this.weekTableView.setItems(filteredList);
		}

		this.weekTableView.refresh();
	}

	private ObservableList<Week> buildList(String filter) {

		ObservableList<Week> list = FXCollections.observableArrayList();

		StreamSupport.stream(this.calendar.spliterator(), false).filter(obj -> matchFilter(obj, filter))
				.forEach(obj -> list.add(obj));

		return list;
	}

	private boolean matchFilter(Week obj, String match) {

		String filter = match.toLowerCase();

		String president = "";

		if (obj.spWeekIDProperty() != null) {
			if (obj.spInf30Property() != null) {

				int spInf30 = obj.getSpInf30();
				if (this.membersList != null) {

					Optional<Member> find = StreamSupport.stream(this.membersList.spliterator(), false)
							.filter(m -> m.getSpMemberID() == spInf30).findFirst();

					if (find.isPresent())
						president = find.get().getNameStyle1();
				}
			}

			String talker = obj.getSpInf33();
			String congregation = obj.getSpInf34();
			String theme = obj.getSpInf32();

			return president.toLowerCase().contains(filter) || talker.toLowerCase().contains(filter)
					|| congregation.toLowerCase().contains(filter) || theme.toLowerCase().contains(filter);

		}

		return false;
	}

//	private void listenerPrintButton() {
//		this.printButton.setOnAction(event -> printWeeks());
//	}

//	private void printWeeks() {
//
//		if (this.weekTableView.getSelectionModel().getSelectedIndex() > -1) {
//
//			ArrayList<Week> printableWeeks = new ArrayList<>();
//
//			ObservableList<Week> weeks = this.weekTableView.getSelectionModel().getSelectedItems();
//			for (Week week : weeks)
//				if (week.spWeekIDProperty() != null)
//					printableWeeks.add(week);
//
//			if (printableWeeks.size() > 0) {
//
//				EnumPrintLayouts selectedLayout = PrintLayout.dialogPrintLayout(this.ownerStage, language);
//
//				if (selectedLayout != null) {
//
////					switch (selectedLayout) {
//
////					case MEETING_COMPLETE_NAME_EXTENDED:
////						Actions.printWeekComplete(printableWeeks, membersList, congregationName, settings, ownerStage,
////								language, true);
////						break;
//
////					default:
////						break;
////					}
//
//				}
//			}
//		}
//	}

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
				fxmlLoader.setLocation(Meta.Views.HOME_USER_MENU_PUBLICMEETINGS_EDITOR);
				AnchorPane layout = (AnchorPane) fxmlLoader.load();

				UserMenuPublicMeetingsEditor ctrl = (UserMenuPublicMeetingsEditor) fxmlLoader.getController();
				ctrl.setApplication(this.application);
				ctrl.setMemberList(this.membersList);
				ctrl.setSettings(this.settings);
				ctrl.setOwnerStage(ownerStage);
				ctrl.setOwnerCtrl(this);
				ctrl.setSelectedWeek(week);
				ctrl.setDatabaseWeeks(this.databaseWeeks);
				ctrl.setDatabaseWeeksAudio(this.audio);
				ctrl.setDatabaseWeeksUsciere(this.usciere);
				ctrl.setConfigSongTitleLoad(this.configSongTitleLoad);
				ctrl.setSongList(this.songList);

				Tab newTab = new Tab(week.getFrom().toString(), layout);
				newTab.setClosable(true);
				newTab.getStyleClass().add("tab_001");
				newTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.CALENDAR));

				ctrl.setOwnerTabPane(tabPane);
				ctrl.setThisTab(newTab);
				ctrl.setAlertBuilder(this.alertBuilder);

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

//	public String getCongregationName() {
//		return congregationName;
//	}
//
//	public void setCongregationName(String congregationName) {
//		this.congregationName = congregationName;
//	}

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

	public ObservableList<Song> getSongList() {
		return songList;
	}

	public void setSongList(ObservableList<Song> songList) {
		this.songList = songList;
	}

	public boolean isConfigSongTitleLoad() {
		return configSongTitleLoad;
	}

	public void setConfigSongTitleLoad(boolean configSongTitleLoad) {
		this.configSongTitleLoad = configSongTitleLoad;
	}
}
