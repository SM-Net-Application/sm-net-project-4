package com.sm.net.sp.view.home.user.menu.publicmeetings;

import java.io.IOException;

import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.actions.Actions;
import com.sm.net.sp.model.Member;
import com.sm.net.sp.model.Privileges;
import com.sm.net.sp.model.UpdateDataAdapter;
import com.sm.net.sp.model.Week;
import com.sm.net.sp.model.WeekType;
import com.sm.net.sp.settings.Settings;
import com.sm.net.sp.utils.AlertBuilder;
import com.sm.net.sp.view.history.History;
import com.sm.net.sp.view.history.UpgradeableComboBoxSelection;
import com.sm.net.util.Crypt;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
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

	private Settings settings;
	private Language language;
	private Stage ownerStage;
	private UserMenuPublicMeetings ownerCtrl;
	private Week selectedWeek;
	private TabPane ownerTabPane;
	private Tab thisTab;

	private ObservableList<Member> memberList;
	private ObservableList<Member> presidentPublicMeetingList;
	private ObservableList<Week> databaseWeeks;

	private AlertBuilder alertBuilder;

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
	}

	private void viewUpdate() {

		this.language = settings.getLanguage();

		this.publicTalkLabel.setText(language.getString("sp.meetings.publictalk"));
		this.presidentPublicMeetingLabel.setText(language.getString("sp.meetings.presidentpublicmeeting"));
		this.publicTalkSongLabel.setText(language.getString("sp.meetings.song1publicmeeting"));
		this.publicTalkThemeLabel.setText(language.getString("sp.meetings.publictalktheme"));
		this.publicTalkTalkerLabel.setText(language.getString("sp.meetings.publictalktalker"));
		this.publicTalkTalkerCongrLabel.setText(language.getString("sp.meetings.publictalktalkercongr"));

		this.saveWeekButton.setText(null);
		this.saveWeekButton.setGraphic(Meta.Resources.imageViewForButton(Meta.Resources.SAVE));
	}

	public void objectInitialize() {
		viewUpdate();
		contextMenu();
		initData();
		listeners();
	}

	private void contextMenu() {

		presidentPublicMeetingComboBox.setContextMenu(createPrivilegeRegisterContextMenu(Privileges.WEEKEND_PRESIDENT));
	}

	private ContextMenu createPrivilegeRegisterContextMenu(Privileges privilege) {

		String menuItemText = String.format(language.getString("sp.meetings.history"),
				privilege.getTranslatedName(language));

		String historyTitle = String.format(language.getString("sp.history.title"), menuItemText,
				this.selectedWeek.getFrom().toString());

		StackPane graphic = Meta.Resources.imageInStackPaneForMenu(Meta.Resources.SEARCH);

		MenuItem menuItem = new MenuItem(menuItemText, graphic);
		menuItem.getStyleClass().add("menu_item_001");
		menuItem.setOnAction(event -> openHistory(privilege, historyTitle));

		ContextMenu contextMenu = new ContextMenu(menuItem);

		return contextMenu;
	}

	private void openHistory(Privileges privilege, String title) {

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

			ctrl.objectInitialize();

			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void initData() {

		if (memberList == null)
			memberList = FXCollections.observableArrayList();

		presidentPublicMeetingList = FXCollections.observableArrayList();
		addEmptyMember();
		presidentPublicMeetingComboBox.setItems(presidentPublicMeetingList);

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
	}

	private void setLists() {
		for (Member member : this.memberList)
			if (member.getSpInf36() == 1)
				this.presidentPublicMeetingList.add(member);

		orderLists();
	}

	private void orderLists() {
		this.presidentPublicMeetingList.sort((o1, o2) -> o1.getNameStyle1().compareTo(o2.getNameStyle1()));
	}

	private void addEmptyMember() {
		this.presidentPublicMeetingList.add(Member.emptyMember(language));
	}

	private void resetLists() {
		this.presidentPublicMeetingList.clear();
	}

	private void loadSelectedWeek() {

		if (this.selectedWeek != null)
			if (this.selectedWeek.spWeekIDProperty() != null) {

				setMemberComboBoxIndex(this.presidentPublicMeetingComboBox, this.selectedWeek.getSpInf30());
				this.publicTalkSongTextField.setText(this.selectedWeek.getSpInf31());
				this.publicTalkThemeTextField.setText(this.selectedWeek.getSpInf32());
				this.publicTalkTalkerTextField.setText(this.selectedWeek.getSpInf33());
				this.publicTalkTalkerCongrTextField.setText(this.selectedWeek.getSpInf34());
			}
	}

	private void listeners() {

		listenerDisableMouseSecondary();
		listenerSaveWeekButton();
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

			String spInfMinistryParts = "";
			String spInfChristiansParts = "";

			if (this.selectedWeek.spWeekIDProperty() != null) {
				// editWeek

				String spWeekID = String.valueOf(selectedWeek.getSpWeekID());

				Actions.updatePublicMeeting(spWeekID, spInf30, spInf31, spInf32, spInf33, spInf34, settings, ownerStage,
						ownerTabPane, thisTab, ownerCtrl);

			} else {
				// newWeek

				String spInf1 = Week.buildKey(this.selectedWeek.getTo());

				Actions.insertWeek(spInf1, spInf2, spInf3, spInf4, spInf5, spInf6, spInf7, spInf8, spInf9, spInf10,
						spInf11, spInf12, spInf13, spInf14, spInf15, spInf16, spInf17, spInf18, spInf19, spInf20,
						spInf21, spInf22, spInf23, spInf24, spInf25, spInf26, spInf27, spInf28, spInf29, spInf30,
						spInf31, spInf32, spInf33, spInf34, spInf35, spInf36, spInf37, spInf38, spInf39, spInf40,
						spInfMinistryParts, spInfChristiansParts, settings, ownerStage, ownerTabPane, thisTab,
						ownerCtrl);
			}
		}
	}

	private boolean checkFields() {

		// TODO: Check all fields
		boolean status = true;

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

	public AlertBuilder getAlertBuilder() {
		return alertBuilder;
	}

	public void setAlertBuilder(AlertBuilder alertBuilder) {
		this.alertBuilder = alertBuilder;
	}
}
