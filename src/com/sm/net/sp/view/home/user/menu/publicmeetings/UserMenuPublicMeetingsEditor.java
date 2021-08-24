package com.sm.net.sp.view.home.user.menu.publicmeetings;

import java.io.IOException;
import java.util.HashMap;

import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.actions.Actions;
import com.sm.net.sp.model.ChristiansPart;
import com.sm.net.sp.model.DateAndTime;
import com.sm.net.sp.model.EnumPlaceType;
import com.sm.net.sp.model.Member;
import com.sm.net.sp.model.Place;
import com.sm.net.sp.model.Privileges;
import com.sm.net.sp.model.Song;
import com.sm.net.sp.model.UpdateDataAdapter;
import com.sm.net.sp.model.Week;
import com.sm.net.sp.model.WeekAudio;
import com.sm.net.sp.model.WeekType;
import com.sm.net.sp.model.WeekUsciere;
import com.sm.net.sp.settings.Settings;
import com.sm.net.sp.utils.AlertBuilderOld;
import com.sm.net.sp.utils.DateAndTimeUtils;
import com.sm.net.sp.utils.PlaceUtils;
import com.sm.net.sp.view.SupportPlannerView;
import com.sm.net.sp.view.history.History;
import com.sm.net.sp.view.history.UpgradeableComboBoxSelection;
import com.sm.net.util.Crypt;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class UserMenuPublicMeetingsEditor extends UpdateDataAdapter implements UpgradeableComboBoxSelection {

	@FXML
	private Button saveWeekButton;

	@FXML
	private Label publicTalkLabel;
	@FXML
	private Label presidentPublicMeetingLabel;
	@FXML
	private ComboBox<Member> presidentPublicMeetingComboBox;
	@FXML
	private Label publicTalkSongLabel;
	@FXML
	private TextField publicTalkSongTextField;
	@FXML
	private Label publicTalkThemeLabel;
	@FXML
	private TextField publicTalkThemeTextField;
	@FXML
	private Label publicTalkTalkerLabel;
	@FXML
	private TextField publicTalkTalkerTextField;
	@FXML
	private Label publicTalkTalkerCongrLabel;
	@FXML
	private TextField publicTalkTalkerCongrTextField;

	@FXML
	private CheckBox presidentOnlyPrayPublicMeetingLabel;
	@FXML
	private Label publicTalkMinLabel;
	@FXML
	private TextField publicTalkMinTextField;

	@FXML
	private TextField song1TextField;
	@FXML
	private CheckBox specialTalkCheckBox;
	@FXML
	private Label internSpeakerLabel;
	@FXML
	private ComboBox<Member> internSpeakerComboBox;

	private Settings settings;
	private Language language;
	private Stage ownerStage;
	private UserMenuPublicMeetings ownerCtrl;
	private Week selectedWeek;
	private TabPane ownerTabPane;
	private Tab thisTab;

	private ObservableList<Member> memberList;
	private ObservableList<Member> presidentPublicMeetingList;
	private ObservableList<Member> internSpeakerList;
	private ObservableList<Week> databaseWeeks;
	private ObservableList<WeekAudio> databaseWeeksAudio;
	private ObservableList<WeekUsciere> databaseWeeksUsciere;

	private boolean configSongTitleLoad;
	private ObservableList<Song> songList;

	private AlertBuilderOld alertBuilder;

	private SupportPlannerView application;

	@FXML
	private void initialize() {
		styleClasses();
	}

	private void styleClasses() {

		publicTalkLabel.getStyleClass().add("label_002");

		presidentPublicMeetingLabel.getStyleClass().add("label_set_001");
		publicTalkSongLabel.getStyleClass().add("label_set_001");
		publicTalkThemeLabel.getStyleClass().add("label_set_001");
		publicTalkTalkerLabel.getStyleClass().add("label_set_001");
		presidentPublicMeetingComboBox.getStyleClass().add("combo_box_001");
		publicTalkSongTextField.getStyleClass().add("text_field_002");
		publicTalkThemeTextField.getStyleClass().add("text_field_001");
		publicTalkTalkerTextField.getStyleClass().add("text_field_001");
		publicTalkTalkerCongrLabel.getStyleClass().add("label_set_001");
		publicTalkTalkerCongrTextField.getStyleClass().add("text_field_001");

		saveWeekButton.getStyleClass().add("button_image_001");

		this.presidentOnlyPrayPublicMeetingLabel.getStyleClass().add("check_box_001");
		this.publicTalkMinLabel.getStyleClass().add("label_set_001");
		this.publicTalkMinTextField.getStyleClass().add("text_field_002");

		this.song1TextField.getStyleClass().add("text_field_001");
		this.specialTalkCheckBox.getStyleClass().add("check_box_001");
		this.internSpeakerLabel.getStyleClass().add("label_set_001");
		this.internSpeakerComboBox.getStyleClass().add("combo_box_001");

	}

	private void viewUpdate() {

		this.language = settings.getLanguage();

		this.publicTalkLabel.setText(language.getString("sp.meetings.publictalk"));
		this.presidentPublicMeetingLabel.setText(language.getString("sp.meetings.presidentpublicmeeting"));
		this.publicTalkSongLabel.setText(language.getString("sp.meetings.song1publicmeeting"));
		this.publicTalkThemeLabel.setText(language.getString("sp.meetings.publictalktheme"));
		this.publicTalkTalkerLabel.setText(language.getString("sp.meetings.publictalktalker"));
		this.publicTalkTalkerCongrLabel.setText(language.getString("sp.meetings.publictalktalkercongr"));

		Tooltip saveTooltip = new Tooltip(this.language.getString("publicmeeting.editor.tooltip.save"));
		saveTooltip.getStyleClass().add("tooltip_001");
		this.saveWeekButton.setTooltip(saveTooltip);
		this.saveWeekButton.setText("");
		this.saveWeekButton.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.SAVE));

		this.presidentOnlyPrayPublicMeetingLabel
				.setText(this.language.getString("meetings.presidentpublicmeeting.onlypray"));
		this.publicTalkMinLabel.setText(this.language.getString("meetings.publictalk.min"));

		this.specialTalkCheckBox.setText(this.language.getString("meetings.editor.specialtalk"));
		this.internSpeakerLabel.setText(this.language.getString("meetings.editor.internspeaker"));
	}

	public void objectInitialize() {
		viewUpdate();
		contextMenu();
		initData();
		listeners();
	}

	private void contextMenu() {

		this.presidentPublicMeetingComboBox
				.setContextMenu(this.createPrivilegeRegisterContextMenu(Privileges.WEEKEND_PRESIDENT));
	}

	private ContextMenu createPrivilegeRegisterContextMenu(Privileges privilege) {

		String menuItemText = String.format(language.getString("sp.meetings.history"),
				privilege.getTranslatedName(language));

		String historyTitle = String.format(language.getString("sp.history.title"), menuItemText,
				this.selectedWeek.getFrom().toString(), this.selectedWeek.getWeek());

		StackPane graphic = Meta.Resources.imageInStackPaneForMenu(Meta.Resources.SEARCH);

		MenuItem menuItem = new MenuItem(menuItemText, graphic);
		menuItem.getStyleClass().add("menu_item_001");
		menuItem.setOnAction(event -> openHistory(privilege, historyTitle));

		ContextMenu contextMenu = new ContextMenu(menuItem);

		return contextMenu;
	}

	private void openHistory(Privileges privilege, String title) {

		if (this.selectedWeek.spInf1Property() != null) {

			try {

				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(Meta.Views.HISTORY);
				AnchorPane layout = (AnchorPane) fxmlLoader.load();

				Scene scene = new Scene(layout);
				scene.getStylesheets().add(Meta.Themes.SUPPORTPLANNER_THEME);

				Stage stage = new Stage();
				stage.setScene(scene);
				stage.initModality(Modality.WINDOW_MODAL);
				stage.initOwner(ownerStage);
				stage.setTitle(title);
				stage.getIcons().add(Meta.Resources.imageForWindowsIcon(Meta.Resources.SEARCH));

				stage.setMinWidth(1050);
				stage.setMinHeight(500);

				History ctrl = (History) fxmlLoader.getController();
				ctrl.setLayout(layout);
				ctrl.setPrivilege(privilege);
				ctrl.setMembers(memberList);
				ctrl.setLanguage(language);
				ctrl.setDatabaseWeeks(this.databaseWeeks);
				ctrl.setSelectedWeek(this.selectedWeek);
				ctrl.setEditorWeek(Week.buildPublicMeetingEditorWeek(this));
				ctrl.setEditor(this);
				ctrl.setThisStage(stage);
				ctrl.setAlertBuilder(this.alertBuilder);
				ctrl.setDatabaseWeeksAudio(this.databaseWeeksAudio);
				ctrl.setDatabaseWeeksUsciere(this.databaseWeeksUsciere);

				ctrl.objectInitialize();

				stage.show();

			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {

			this.application.getAlertBuilder2().error(this.ownerStage,
					this.language.getString("sp.history.weeknotsaved"));
		}
	}

	private void initData() {

		if (memberList == null)
			memberList = FXCollections.observableArrayList();

		presidentPublicMeetingList = FXCollections.observableArrayList();
		this.internSpeakerList = FXCollections.observableArrayList();

		addEmptyMember();

		presidentPublicMeetingComboBox.setItems(presidentPublicMeetingList);
		this.internSpeakerComboBox.setItems(this.internSpeakerList);

		selectFirst();

		updateLists();
		loadSelectedWeek();
	}

	private void setMemberComboBoxIndex(ComboBox<Member> cb, int id) {

		cb.getSelectionModel().selectFirst();

		for (int i = 0; i < cb.getItems().size(); i++) {

			Member member = cb.getItems().get(i);
			if (member.getSpMemberID() == id) {
				cb.getSelectionModel().select(i);
				break;
			}
		}
	}

	private void updateLists() {
		resetLists();
		addEmptyMember();
		setLists();
		selectFirst();
	}

	private void selectFirst() {
		presidentPublicMeetingComboBox.getSelectionModel().selectFirst();
		this.internSpeakerComboBox.getSelectionModel().selectFirst();
	}

	private void setLists() {
		for (Member member : this.memberList) {
			if (member.getSpInf36() == 1)
				this.presidentPublicMeetingList.add(member);
			if (member.getSpInf45() == 1)
				this.internSpeakerList.add(member);
		}

		orderLists();
	}

	private void orderLists() {
		this.presidentPublicMeetingList.sort((o1, o2) -> o1.getNameStyle1().compareTo(o2.getNameStyle1()));
		this.internSpeakerList.sort((o1, o2) -> o1.getNameStyle1().compareTo(o2.getNameStyle1()));
	}

	private void addEmptyMember() {
		this.presidentPublicMeetingList.add(Member.emptyMember(language));
		this.internSpeakerList.add(Member.emptyMember(language));
	}

	private void resetLists() {
		this.presidentPublicMeetingList.clear();
		this.internSpeakerList.clear();
	}

	private void loadSelectedWeek() {

		if (this.selectedWeek != null)
			if (this.selectedWeek.spWeekIDProperty() != null) {

				setMemberComboBoxIndex(this.presidentPublicMeetingComboBox, this.selectedWeek.getSpInf30());
				this.publicTalkSongTextField.setText(this.selectedWeek.getSpInf31());
				this.publicTalkThemeTextField.setText(this.selectedWeek.getSpInf32());
				this.publicTalkTalkerTextField.setText(this.selectedWeek.getSpInf33());
				this.publicTalkTalkerCongrTextField.setText(this.selectedWeek.getSpInf34());

				this.presidentOnlyPrayPublicMeetingLabel.setSelected(this.selectedWeek.getSpInf41() == 1);
				this.publicTalkMinTextField.setText(this.selectedWeek.getSpInf42());

				this.song1TextField.setText(this.selectedWeek.getSpInf62());
				this.specialTalkCheckBox.setSelected(this.selectedWeek.getSpInf65() == 1);
				setMemberComboBoxIndex(this.internSpeakerComboBox, this.selectedWeek.getSpInf66());

			} else {

				// Minuti discorso pubblico
				String publicTalkMin = this.ownerCtrl.getConfigs().get("inf2");
				if (publicTalkMin != null) {
					publicTalkMin = Crypt.decrypt(publicTalkMin, this.settings.getDatabaseSecretKey());
					this.publicTalkMinTextField.setText(publicTalkMin);
				}
			}
	}

	private void listeners() {

		listenerDisableMouseSecondary();
		listenerSaveWeekButton();

		this.publicTalkSongTextField.focusedProperty().addListener((obs, oldV, newV) -> {
			if (!newV)
				checkSongTitle(this.publicTalkSongTextField, this.song1TextField);
		});
	}

	private void checkSongTitle(TextField tfSong, TextField tfTitle) {

		String songText = tfSong.getText();
		if (songText != null) {

			try {

				Integer song = Integer.valueOf(songText);
				tfTitle.setText(checkSongTitleByNumber(song));

			} catch (Exception e) {
				tfTitle.setText("");
			}
		} else
			tfTitle.setText("");
	}

	private String checkSongTitleByNumber(Integer song) {

		if (song != null)
			for (Song s : this.songList)
				if (s.getNumber().intValue() == song.intValue())
					return s.getTitle();

		return "";
	}

	private void listenerDisableMouseSecondary() {
		presidentPublicMeetingComboBox.addEventFilter(MouseEvent.MOUSE_RELEASED, event -> disableMouseSecondary(event));
	}

	private void disableMouseSecondary(MouseEvent event) {
		if (event.getButton() == MouseButton.SECONDARY)
			event.consume();
	}

	private void listenerSaveWeekButton() {
		saveWeekButton.setOnAction(event -> saveWeek());
	}

	private void saveWeek() {

		if (checkFields()) {

			String spInf2 = String.valueOf(WeekType.STANDARD.getOrdinal());

			String emptyTextEncrypted = Crypt.encrypt("", settings.getDatabaseSecretKey());

			String spInf3 = "0";
			String spInf4 = "0";
			String spInf5 = emptyTextEncrypted;
			String spInf6 = emptyTextEncrypted;
			String spInf7 = emptyTextEncrypted;
			String spInf8 = emptyTextEncrypted;
			String spInf9 = emptyTextEncrypted;
			String spInf10 = emptyTextEncrypted;
			String spInf11 = "0";
			String spInf12 = emptyTextEncrypted;
			String spInf13 = emptyTextEncrypted;
			String spInf14 = "0";
			String spInf15 = emptyTextEncrypted;
			String spInf16 = emptyTextEncrypted;
			String spInf17 = emptyTextEncrypted;
			String spInf18 = "0";
			String spInf19 = emptyTextEncrypted;
			String spInf20 = emptyTextEncrypted;
			String spInf21 = emptyTextEncrypted;
			String spInf22 = emptyTextEncrypted;
			String spInf23 = "0";
			String spInf24 = emptyTextEncrypted;
			String spInf25 = emptyTextEncrypted;
			String spInf26 = emptyTextEncrypted;
			String spInf27 = "0";
			String spInf28 = "0";

			String spInf29 = "0";

			String spInf30 = String
					.valueOf(this.presidentPublicMeetingComboBox.getSelectionModel().getSelectedItem().getSpMemberID());
			String spInf31 = Crypt.encrypt(this.publicTalkSongTextField.getText(), settings.getDatabaseSecretKey());
			String spInf32 = Crypt.encrypt(this.publicTalkThemeTextField.getText(), settings.getDatabaseSecretKey());
			String spInf33 = Crypt.encrypt(this.publicTalkTalkerTextField.getText(), settings.getDatabaseSecretKey());
			String spInf34 = Crypt.encrypt(this.publicTalkTalkerCongrTextField.getText(),
					settings.getDatabaseSecretKey());

			String spInf35 = emptyTextEncrypted;
			String spInf36 = emptyTextEncrypted;
			String spInf37 = "0";
			String spInf38 = "0";
			String spInf39 = emptyTextEncrypted;
			String spInf40 = "0";

			int spInf41 = this.presidentOnlyPrayPublicMeetingLabel.isSelected() ? 1 : 0;

			String spInf42 = Crypt.encrypt(this.publicTalkMinTextField.getText(), this.settings.getDatabaseSecretKey());

			String spInf43 = emptyTextEncrypted;

			int spInf44 = 1;
			int spInf45 = 0;
			int spInf46 = 0;

			int spInf47 = 6;
			int spInf48 = 0;
			int spInf49 = 0;

			ObservableList<DateAndTime> dateAndTimeList = this.ownerCtrl.getDateAndTimeList();
			DateAndTime dateAndTime = DateAndTimeUtils.check(dateAndTimeList, this.selectedWeek.getFrom());

			if (dateAndTime != null) {

				spInf44 = dateAndTime.getDay1().get();
				spInf45 = dateAndTime.getHour1().get();
				spInf46 = dateAndTime.getMinute1().get();

				spInf47 = dateAndTime.getDay2().get();
				spInf48 = dateAndTime.getHour2().get();
				spInf49 = dateAndTime.getMinute2().get();
			}

			String place = initPlace();

			String spInf50 = place.isEmpty() ? emptyTextEncrypted
					: Crypt.encrypt(place, this.settings.getDatabaseSecretKey());

			int spInf51 = 0;

			String spInf52 = place.isEmpty() ? emptyTextEncrypted
					: Crypt.encrypt(place, this.settings.getDatabaseSecretKey());

			int spInf53 = 0;

			// 0.11.0

			String spInf54 = emptyTextEncrypted;
			String spInf55 = emptyTextEncrypted;
			int spInf56 = 0;
			int spInf57 = 0;
			int spInf58 = 0;

			// 1.1

			String spInf59 = emptyTextEncrypted;
			String spInf60 = emptyTextEncrypted;
			String spInf61 = emptyTextEncrypted;
			String spInf62 = Crypt.encrypt(this.song1TextField.getText(), this.settings.getDatabaseSecretKey());
			String spInf63 = emptyTextEncrypted;
			String spInf64 = emptyTextEncrypted;
			int spInf65 = this.specialTalkCheckBox.isSelected() ? 1 : 0;
			int spInf66 = this.internSpeakerComboBox.getSelectionModel().getSelectedItem().getSpMemberID();

			// ------------------------------------------------

			String spInfMinistryParts = "";
			String spInfChristiansParts = "";

			if (this.selectedWeek.spWeekIDProperty() != null) {
				// editWeek

				String spWeekID = String.valueOf(selectedWeek.getSpWeekID());

				Actions.updatePublicMeeting(spWeekID, spInf30, spInf31, spInf32, spInf33, spInf34, spInf62, spInf65,
						spInf66, settings, ownerStage, ownerTabPane, thisTab, ownerCtrl);

			} else {
				// newWeek

				String spInf1 = Week.buildKey(this.selectedWeek.getTo());

				Actions.insertWeek(spInf1, spInf2, spInf3, spInf4, spInf5, spInf6, spInf7, spInf8, spInf9, spInf10,
						spInf11, spInf12, spInf13, spInf14, spInf15, spInf16, spInf17, spInf18, spInf19, spInf20,
						spInf21, spInf22, spInf23, spInf24, spInf25, spInf26, spInf27, spInf28, spInf29, spInf30,
						spInf31, spInf32, spInf33, spInf34, spInf35, spInf36, spInf37, spInf38, spInf39, spInf40,
						spInf41, spInf42, spInf43, spInf44, spInf45, spInf46, spInf47, spInf48, spInf49, spInf50,
						spInf51, spInf52, spInf53, spInf54, spInf55, spInf56, spInf57, spInf58, spInf59, spInf60,
						spInf61, spInf62, spInf63, spInf64, spInf65, spInf66, spInfMinistryParts, spInfChristiansParts,
						settings, ownerStage, ownerTabPane, thisTab, ownerCtrl);
			}
		}
	}

	private String initPlace() {

		ObservableList<Place> placesList = this.ownerCtrl.getPlacesList();
		Place found = null;
		for (Place place : placesList)
			if (place.getType().get() == EnumPlaceType.KINGDOMHALL)
				if (place.getDef().get()) {
					found = place;
					break;
				}

		if (found != null)
			return placeToText(found);

		return "";
	}

	private String placeToText(Place found) {

		String addr = "";

		HashMap<String, String> configs = this.ownerCtrl.getConfigs();
		String pattern = configs.get("inf1");
		if (pattern != null) {
			pattern = Crypt.decrypt(pattern, this.settings.getDatabaseSecretKey());
			addr = PlaceUtils.toText(found, pattern);
		} else
			addr = PlaceUtils.toText(found);

		return addr;
	}

	private boolean checkFields() {

		boolean status = true;

		int internSpeakerID = -1;
		Member internSpeaker = this.internSpeakerComboBox.getSelectionModel().getSelectedItem();
		if (internSpeaker != null)
			internSpeakerID = internSpeaker.getSpMemberID();

		String externSpeaker = this.publicTalkTalkerTextField.getText();
		if (!externSpeaker.isEmpty() && internSpeakerID > 0) {
			status = false;
			this.application.getAlertBuilder2().error(this.ownerStage,
					this.language.getString("meetings.editor.error.speaker"));
		}

		return status;
	}

	@Override
	public void updateSelectedComboBox(Privileges privilege, int memberID) {

		switch (privilege) {
		case WEEKEND_PRESIDENT:
			updateComboBox(this.presidentPublicMeetingComboBox, memberID);
			break;
		default:
			break;
		}
	}

	public void updateComboBox(ComboBox<Member> cb, int memberID) {

		int index = -1;

		for (int i = 0; i < cb.getItems().size(); i++) {
			if (cb.getItems().get(i).getSpMemberID() == memberID) {
				index = i;
				break;
			}
		}

		cb.getSelectionModel().select(index);
	}

	@Override
	public void updateSelectedChristianPart(ChristiansPart christiansPart, int memberID) {

		// Public Meeting non contiene la tabella Vita Cristiana
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

	public UserMenuPublicMeetings getOwnerCtrl() {
		return ownerCtrl;
	}

	public void setOwnerCtrl(UserMenuPublicMeetings ownerCtrl) {
		this.ownerCtrl = ownerCtrl;
	}

	public Week getSelectedWeek() {
		return selectedWeek;
	}

	public void setSelectedWeek(Week selectedWeek) {
		this.selectedWeek = selectedWeek;
	}

	public TabPane getOwnerTabPane() {
		return ownerTabPane;
	}

	public void setOwnerTabPane(TabPane ownerTabPane) {
		this.ownerTabPane = ownerTabPane;
	}

	public Tab getThisTab() {
		return thisTab;
	}

	public void setThisTab(Tab thisTab) {
		this.thisTab = thisTab;
	}

	public ObservableList<Member> getMemberList() {
		return memberList;
	}

	public void setMemberList(ObservableList<Member> memberList) {
		this.memberList = memberList;
	}

	public ObservableList<Week> getDatabaseWeeks() {
		return databaseWeeks;
	}

	public void setDatabaseWeeks(ObservableList<Week> databaseWeeks) {
		this.databaseWeeks = databaseWeeks;
	}

	public ComboBox<Member> getPresidentPublicMeetingComboBox() {
		return presidentPublicMeetingComboBox;
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

	public ObservableList<WeekAudio> getDatabaseWeeksAudio() {
		return databaseWeeksAudio;
	}

	public void setDatabaseWeeksAudio(ObservableList<WeekAudio> databaseWeeksAudio) {
		this.databaseWeeksAudio = databaseWeeksAudio;
	}

	public ObservableList<WeekUsciere> getDatabaseWeeksUsciere() {
		return databaseWeeksUsciere;
	}

	public void setDatabaseWeeksUsciere(ObservableList<WeekUsciere> databaseWeeksUsciere) {
		this.databaseWeeksUsciere = databaseWeeksUsciere;
	}

	public boolean isConfigSongTitleLoad() {
		return configSongTitleLoad;
	}

	public void setConfigSongTitleLoad(boolean configSongTitleLoad) {
		this.configSongTitleLoad = configSongTitleLoad;
	}

	public ObservableList<Song> getSongList() {
		return songList;
	}

	public void setSongList(ObservableList<Song> songList) {
		this.songList = songList;
	}

}
