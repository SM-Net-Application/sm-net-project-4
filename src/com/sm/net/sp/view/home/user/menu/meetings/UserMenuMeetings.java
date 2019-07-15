package com.sm.net.sp.view.home.user.menu.meetings;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.actions.Actions;
import com.sm.net.sp.model.EnumPrintLayouts;
import com.sm.net.sp.model.Info;
import com.sm.net.sp.model.Member;
import com.sm.net.sp.model.UpdateDataAdapter;
import com.sm.net.sp.model.Week;
import com.sm.net.sp.settings.Settings;
import com.sm.net.sp.view.printlayout.PrintLayout;
import com.sm.net.util.DateUtil;

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
	// @FXML
	// private Tab generalTab;
	// @FXML
	// private Label meetingLabel;
	// @FXML
	// private Label meetingMidweekLabel;
	// @FXML
	// private Label meetingWeekendLabel;
	// @FXML
	// private RadioButton mondayRadioButton;
	// @FXML
	// private RadioButton tuesdayRadioButton;
	// @FXML
	// private RadioButton wednesdayRadioButton;
	// @FXML
	// private RadioButton thursdayRadioButton;
	// @FXML
	// private RadioButton fridayRadioButton;
	// @FXML
	// private RadioButton saturdayRadioButton;
	// @FXML
	// private RadioButton sundayRadioButton;
	// @FXML
	// private TextField midweekTimeHourTextField;
	// @FXML
	// private TextField midweekTimeMinutesTextField;
	// @FXML
	// private TextField weekendTimeHourTextField;
	// @FXML
	// private TextField weekendTimeMinutesTextField;
	// @FXML
	// private Label kingdomHallLabel;
	// @FXML
	// private Label kingdomHallStreetLabel;
	// @FXML
	// private Label kingdomHallNumberLabel;
	// @FXML
	// private Label kingdomHallPostcodeLabel;
	// @FXML
	// private Label kingdomHallCityLabel;
	// @FXML
	// private TextField kingdomHallStreetTextField;
	// @FXML
	// private TextField kingdomHallNumberTextField;
	// @FXML
	// private TextField kingdomHallPostcodeTextField;
	// @FXML
	// private TextField kingdomHallCityTextField;
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
	private Button printButton;

	private Settings settings;
	private Language language;
	private Stage ownerStage;
	private ObservableList<Week> calendar;
	private ObservableList<Week> databaseWeeks;
	private ObservableList<Member> membersList;

	private String congregationName;

	// private ToggleGroup midweekToggleGroup;
	// private ToggleGroup weekendToggleGroup;

	// private String bufferMidweekTimeHour;
	// private String bufferMidweekTimeMinutes;
	// private String bufferWeekendTimeHour;
	// private String bufferWeekendTimeMinutes;
	//
	// private String bufferKingdomHallStreet;
	// private String bufferKingdomHallNumber;
	// private String bufferKingdomHallPostCode;
	// private String bufferKingdomHallCity;

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
	}

	private void styleClasses() {

		headerLabel.getStyleClass().add("label_header_001");

		tabPane.getStyleClass().add("tab_pane_001");

		// generalTab.getStyleClass().add("tab_001");
		calendarTab.getStyleClass().add("tab_001");
		weekTableView.getStyleClass().add("table_view_001");

		// meetingLabel.getStyleClass().add("label_001");
		// meetingMidweekLabel.getStyleClass().add("label_001");
		// meetingWeekendLabel.getStyleClass().add("label_001");
		// kingdomHallLabel.getStyleClass().add("label_001");
		// kingdomHallStreetLabel.getStyleClass().add("label_001");
		// kingdomHallNumberLabel.getStyleClass().add("label_001");
		// kingdomHallPostcodeLabel.getStyleClass().add("label_001");
		// kingdomHallCityLabel.getStyleClass().add("label_001");

		// mondayRadioButton.getStyleClass().add("radioButtonStyle1");
		// tuesdayRadioButton.getStyleClass().add("radioButtonStyle1");
		// wednesdayRadioButton.getStyleClass().add("radioButtonStyle1");
		// thursdayRadioButton.getStyleClass().add("radioButtonStyle1");
		// fridayRadioButton.getStyleClass().add("radioButtonStyle1");
		// saturdayRadioButton.getStyleClass().add("radioButtonStyle1");
		// sundayRadioButton.getStyleClass().add("radioButtonStyle1");

		// midweekTimeHourTextField.getStyleClass().add("text_field_001");
		// midweekTimeMinutesTextField.getStyleClass().add("text_field_001");
		// weekendTimeHourTextField.getStyleClass().add("text_field_001");
		// weekendTimeMinutesTextField.getStyleClass().add("text_field_001");
		// kingdomHallStreetTextField.getStyleClass().add("text_field_001");
		// kingdomHallNumberTextField.getStyleClass().add("text_field_001");
		// kingdomHallPostcodeTextField.getStyleClass().add("text_field_001");
		// kingdomHallCityTextField.getStyleClass().add("text_field_001");

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

		// generalTab.setText(language.getString("TEXT0043"));
		// generalTab.setGraphic(Meta.Resources.createTabIcon(Meta.Resources.INFO));
		//
		// meetingLabel.setText(language.getString("TEXT0055"));
		// meetingMidweekLabel.setText(language.getString("TEXT0057"));
		// meetingWeekendLabel.setText(language.getString("TEXT0059"));
		//
		// mondayRadioButton.setText(language.getString("TEXT0123"));
		// tuesdayRadioButton.setText(language.getString("TEXT0124"));
		// wednesdayRadioButton.setText(language.getString("TEXT0125"));
		// thursdayRadioButton.setText(language.getString("TEXT0126"));
		// fridayRadioButton.setText(language.getString("TEXT0127"));
		// saturdayRadioButton.setText(language.getString("TEXT0128"));
		// sundayRadioButton.setText(language.getString("TEXT0129"));

		calendarTab.setText(language.getString("TEXT0075"));
		calendarTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.CALENDAR));
		weekTableColumn.setText(language.getString("TEXT0076"));
		fromTableColumn.setText(language.getString("TEXT0077"));
		toTableColumn.setText(language.getString("TEXT0078"));
		typeColumn.setText(language.getString("TEXT0091"));

		// kingdomHallLabel.setText(language.getString("TEXT0130"));
		// kingdomHallStreetLabel.setText(language.getString("TEXT0027"));
		// kingdomHallNumberLabel.setText(language.getString("TEXT0028"));
		// kingdomHallPostcodeLabel.setText(language.getString("TEXT0029"));
		// kingdomHallCityLabel.setText(language.getString("TEXT0030"));

		printButton.setGraphic(Meta.Resources.imageViewForButton(Meta.Resources.PRINT));
		printButton.setText(null);
	}

	private void initData() {
		this.weekTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		// setRadioButtonGroup();
		loadGeneralInfo();
		loadCalendar();
		updateMembers();
		// updateWeeks();
	}

	// private void setRadioButtonGroup() {

	// midweekToggleGroup = new ToggleGroup();
	// weekendToggleGroup = new ToggleGroup();
	//
	// mondayRadioButton.setToggleGroup(midweekToggleGroup);
	// mondayRadioButton.setUserData("1");
	// tuesdayRadioButton.setToggleGroup(midweekToggleGroup);
	// tuesdayRadioButton.setUserData("2");
	// wednesdayRadioButton.setToggleGroup(midweekToggleGroup);
	// wednesdayRadioButton.setUserData("3");
	// thursdayRadioButton.setToggleGroup(midweekToggleGroup);
	// thursdayRadioButton.setUserData("4");
	// fridayRadioButton.setToggleGroup(midweekToggleGroup);
	// fridayRadioButton.setUserData("5");
	//
	// saturdayRadioButton.setToggleGroup(weekendToggleGroup);
	// saturdayRadioButton.setUserData("6");
	// sundayRadioButton.setToggleGroup(weekendToggleGroup);
	// sundayRadioButton.setUserData("7");
	// }

	private void loadGeneralInfo() {
		Actions.getUserMenuMeetingsInfo(settings, ownerStage, this);
	}

	@Override
	public void updateInfo(Info info) {
		super.updateInfo(info);

		this.congregationName = info.getCongr();
	}
	//
	// String dayWeekendMeeting = info.getDayWeekendMeeting();
	// if (dayWeekendMeeting != null) {
	// switch (dayWeekendMeeting) {
	// case "6":
	// weekendToggleGroup.selectToggle(saturdayRadioButton);
	// break;
	// case "7":
	// weekendToggleGroup.selectToggle(sundayRadioButton);
	// break;
	// default:
	// weekendToggleGroup.selectToggle(null);
	// break;
	// }
	// }
	//
	// setTextField(midweekTimeHourTextField, info.getHourMidweekMeeting());
	// setTextField(midweekTimeMinutesTextField,
	// info.getMinuteMidweekMeeting());
	// setTextField(weekendTimeHourTextField, info.getHourWeekendMeeting());
	// setTextField(weekendTimeMinutesTextField,
	// info.getMinuteWeekendMeeting());
	//
	// setTextField(kingdomHallStreetTextField,
	// info.getKingdomHallLocationStreet());
	// setTextField(kingdomHallNumberTextField,
	// info.getKingdomHallLocationNumber());
	// setTextField(kingdomHallPostcodeTextField,
	// info.getKingdomHallLocationPostcode());
	// setTextField(kingdomHallCityTextField,
	// info.getKingdomHallLocationCity());
	// }

	// private void setTextField(TextField tf, String text) {
	// if (text != null)
	// tf.setText(text);
	// else
	// tf.setText("");
	// }

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
				calendar.add(new Week(day, language));
			else {
				countMonth = countMonth + 1;
				if (countMonth < 4)
					calendar.add(new Week(day, language));
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

		// listenerMidweekToggleGroup();
		// listenerWeekendToogleGroup();
		//
		// listenerMidweekTimeHourTextField();
		// listenerMidweekTimeMinutesTextField();
		// listenerWeekendTimeHourTextField();
		// listenerWeekendTimeMinutesTextField();
		//
		// listenerKingdomHallStreetTextField();
		// listenerKingdomHallNumberTextField();
		// listenerKingdomHallPostcodeTextField();
		// listenerKingdomHallCityTextField();

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

				EnumPrintLayouts selectedLayout = PrintLayout.dialogPrintLayout(this.ownerStage, language,
						EnumPrintLayouts.MEETING_MIDWEEK_NAME_EXTENDED, EnumPrintLayouts.MEETING_MIDWEEK_NAME_SHORT);

				if (selectedLayout != null) {

					switch (selectedLayout) {

					case MEETING_MIDWEEK_NAME_EXTENDED:
						Actions.printWeek(printableWeeks, membersList, congregationName, settings, ownerStage, language,
								true);
						break;

					case MEETING_MIDWEEK_NAME_SHORT:
						Actions.printWeek(printableWeeks, membersList, congregationName, settings, ownerStage, language,
								false);
						break;

					default:
						break;
					}

				}
			}
		}
	}

	// private void listenerMidweekToggleGroup() {
	// midweekToggleGroup.selectedToggleProperty()
	// .addListener((observable, oldValue, newValue) ->
	// midweekChanged(newValue));
	// }
	//
	// private void listenerWeekendToogleGroup() {
	// weekendToggleGroup.selectedToggleProperty()
	// .addListener((observable, oldValue, newValue) ->
	// weekendChanged(newValue));
	// }
	//
	// private void midweekChanged(Toggle newValue) {
	// if (newValue != null) {
	// String value = (String) newValue.getUserData();
	// if (value != null)
	// if (!value.isEmpty())
	// Info.runAction(EnumActions.SAVE, Info.KEYS.DAYMIDWEEKMEETING,
	// Crypt.encrypt(value, settings.getDatabaseSecretKey()), settings,
	// ownerStage);
	// }
	// }
	//
	// private void weekendChanged(Toggle newValue) {
	// if (newValue != null) {
	// String value = (String) newValue.getUserData();
	// if (value != null)
	// if (!value.isEmpty())
	// Info.runAction(EnumActions.SAVE, Info.KEYS.DAYWEEKENDMEETING,
	// Crypt.encrypt(value, settings.getDatabaseSecretKey()), settings,
	// ownerStage);
	// }
	// }
	//
	// private void listenerKingdomHallStreetTextField() {
	// kingdomHallStreetTextField.focusedProperty()
	// .addListener((observable, oldValue, newValue) ->
	// checkKingdomHallStreetTextField(newValue));
	// }
	//
	// private void listenerKingdomHallNumberTextField() {
	// kingdomHallNumberTextField.focusedProperty()
	// .addListener((observable, oldValue, newValue) ->
	// checkKingdomHallNumberTextField(newValue));
	// }
	//
	// private void listenerKingdomHallPostcodeTextField() {
	// kingdomHallPostcodeTextField.focusedProperty()
	// .addListener((observable, oldValue, newValue) ->
	// checkKingdomHallPostcodeTextField(newValue));
	// }
	//
	// private void listenerKingdomHallCityTextField() {
	// kingdomHallCityTextField.focusedProperty()
	// .addListener((observable, oldValue, newValue) ->
	// checkKingdomHallCityTextField(newValue));
	// }
	//
	// private void checkKingdomHallStreetTextField(Boolean focused) {
	//
	// if (focused.booleanValue())
	// this.bufferKingdomHallStreet = kingdomHallStreetTextField.getText();
	// else {
	// String newText = kingdomHallStreetTextField.getText();
	// if (!newText.equals(this.bufferKingdomHallStreet))
	// Info.runAction(EnumActions.SAVE, Info.KEYS.KINGDOMHALLLOCATIONSTREET,
	// Crypt.encrypt(newText, settings.getDatabaseSecretKey()), settings,
	// ownerStage);
	// }
	// }
	//
	// private void checkKingdomHallNumberTextField(Boolean focused) {
	//
	// if (focused.booleanValue())
	// this.bufferKingdomHallNumber = kingdomHallNumberTextField.getText();
	// else {
	// String newText = kingdomHallNumberTextField.getText();
	// if (!newText.equals(this.bufferKingdomHallNumber))
	// Info.runAction(EnumActions.SAVE, Info.KEYS.KINGDOMHALLLOCATIONNUMBER,
	// Crypt.encrypt(newText, settings.getDatabaseSecretKey()), settings,
	// ownerStage);
	// }
	// }
	//
	// private void checkKingdomHallPostcodeTextField(Boolean focused) {
	//
	// if (focused.booleanValue())
	// this.bufferKingdomHallPostCode = kingdomHallPostcodeTextField.getText();
	// else {
	// String newText = kingdomHallPostcodeTextField.getText();
	// if (!newText.equals(this.bufferKingdomHallPostCode))
	// Info.runAction(EnumActions.SAVE, Info.KEYS.KINGDOMHALLLOCATIONPOSTCODE,
	// Crypt.encrypt(newText, settings.getDatabaseSecretKey()), settings,
	// ownerStage);
	// }
	// }
	//
	// private void checkKingdomHallCityTextField(Boolean focused) {
	//
	// if (focused.booleanValue())
	// this.bufferKingdomHallCity = kingdomHallCityTextField.getText();
	// else {
	// String newText = kingdomHallCityTextField.getText();
	// if (!newText.equals(this.bufferKingdomHallCity))
	// Info.runAction(EnumActions.SAVE, Info.KEYS.KINGDOMHALLLOCATIONCITY,
	// Crypt.encrypt(newText, settings.getDatabaseSecretKey()), settings,
	// ownerStage);
	// }
	// }
	//
	// private void listenerMidweekTimeHourTextField() {
	// midweekTimeHourTextField.focusedProperty()
	// .addListener((observable, oldValue, newValue) ->
	// checkMidweekTimeHour(newValue));
	// }
	//
	// private void listenerMidweekTimeMinutesTextField() {
	// midweekTimeMinutesTextField.focusedProperty()
	// .addListener((observable, oldValue, newValue) ->
	// checkMidweekTimeMinutes(newValue));
	// }
	//
	// private void listenerWeekendTimeHourTextField() {
	// weekendTimeHourTextField.focusedProperty()
	// .addListener((observable, oldValue, newValue) ->
	// checkWeekendTimeHour(newValue));
	// }
	//
	// private void listenerWeekendTimeMinutesTextField() {
	// weekendTimeMinutesTextField.focusedProperty()
	// .addListener((observable, oldValue, newValue) ->
	// checkWeekendTimeMinutes(newValue));
	// }
	//
	// private void checkMidweekTimeHour(Boolean focused) {
	//
	// if (focused.booleanValue())
	// this.bufferMidweekTimeHour = midweekTimeHourTextField.getText();
	// else {
	// String newText = midweekTimeHourTextField.getText();
	// if (!newText.isEmpty())
	// if (!isValidHour(newText))
	// midweekTimeHourTextField.setText(this.bufferMidweekTimeHour);
	// else {
	// if (!newText.equals(this.bufferMidweekTimeHour))
	// Info.runAction(EnumActions.SAVE, Info.KEYS.HOURMIDWEEKMEETING,
	// Crypt.encrypt(newText, settings.getDatabaseSecretKey()), settings,
	// ownerStage);
	// }
	// }
	// }
	//
	// private void checkMidweekTimeMinutes(Boolean focused) {
	//
	// if (focused.booleanValue())
	// this.bufferMidweekTimeMinutes = midweekTimeMinutesTextField.getText();
	// else {
	// String newText = midweekTimeMinutesTextField.getText();
	// if (!newText.isEmpty())
	// if (!isValidMinutes(newText))
	// midweekTimeMinutesTextField.setText(this.bufferMidweekTimeMinutes);
	// else {
	// if (!newText.equals(this.bufferMidweekTimeMinutes))
	// Info.runAction(EnumActions.SAVE, Info.KEYS.MINUTEMIDWEEKMEETING,
	// Crypt.encrypt(newText, settings.getDatabaseSecretKey()), settings,
	// ownerStage);
	// }
	// }
	// }
	//
	// private void checkWeekendTimeHour(Boolean focused) {
	//
	// if (focused.booleanValue())
	// this.bufferWeekendTimeHour = weekendTimeHourTextField.getText();
	// else {
	// String newText = weekendTimeHourTextField.getText();
	// if (!newText.isEmpty())
	// if (!isValidHour(newText))
	// weekendTimeHourTextField.setText(this.bufferWeekendTimeHour);
	// else {
	// if (!newText.equals(this.bufferWeekendTimeHour))
	// Info.runAction(EnumActions.SAVE, Info.KEYS.HOURWEEKENDMEETING,
	// Crypt.encrypt(newText, settings.getDatabaseSecretKey()), settings,
	// ownerStage);
	// }
	// }
	// }
	//
	// private void checkWeekendTimeMinutes(Boolean focused) {
	//
	// if (focused.booleanValue())
	// this.bufferWeekendTimeMinutes = weekendTimeMinutesTextField.getText();
	// else {
	// String newText = weekendTimeMinutesTextField.getText();
	// if (!newText.isEmpty())
	// if (!isValidMinutes(newText))
	// weekendTimeMinutesTextField.setText(this.bufferWeekendTimeMinutes);
	// else {
	// if (!newText.equals(this.bufferWeekendTimeMinutes))
	// Info.runAction(EnumActions.SAVE, Info.KEYS.MINUTEWEEKENDMEETING,
	// Crypt.encrypt(newText, settings.getDatabaseSecretKey()), settings,
	// ownerStage);
	// }
	// }
	// }
	//
	// private boolean isValidHour(String text) {
	//
	// try {
	// Integer hour = Integer.valueOf(text);
	// if (!(hour.intValue() < 0) && !(hour.intValue() > 23))
	// return true;
	// } catch (NumberFormatException e) {
	// }
	//
	// return false;
	// }
	//
	// private boolean isValidMinutes(String text) {
	//
	// try {
	// Integer minutes = Integer.valueOf(text);
	// if (!(minutes.intValue() < 0) && !(minutes.intValue() > 59))
	// return true;
	// } catch (NumberFormatException e) {
	// }
	//
	// return false;
	// }

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
}
