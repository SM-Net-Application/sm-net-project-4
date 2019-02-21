package com.sm.net.sp.view.home.user.menu.meetings;

import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.model.Member;
import com.sm.net.sp.model.Week;
import com.sm.net.sp.settings.Settings;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UserMenuMeetingsEditor {

	@FXML
	private TabPane tabPane;
	@FXML
	private Tab treasuresTab;
	@FXML
	private Tab ministryTab;
	@FXML
	private Tab christiansTab;

	@FXML
	private Label generalLabel;
	@FXML
	private Label treasuresLabel;
	@FXML
	private Label openingCommentsLabel;
	@FXML
	private Label talkLabel;
	@FXML
	private Label diggingLabel;
	@FXML
	private Label bibleReadingLabel;

	@FXML
	private Label presidentLabel;
	@FXML
	private ComboBox<Member> presidentComboBox;
	@FXML
	private Label bibleChaptersLabel;
	@FXML
	private TextField bibleChaptersTextField;
	@FXML
	private Label song1Label;
	@FXML
	private TextField song1TextField;
	@FXML
	private Label pray1Label;
	@FXML
	private ComboBox<Member> pray1ComboBox;

	@FXML
	private TextField openingCommentsMinTextField;
	@FXML
	private Label openingCommentsMinLabel;
	@FXML
	private TextField openingCommentsTextTextField;

	@FXML
	private TextField talkMinTextField;
	@FXML
	private Label talkMinLabel;
	@FXML
	private TextField talkTextTextField;
	@FXML
	private ComboBox<Member> talkComboBox;

	@FXML
	private TextField diggingMinTextField;
	@FXML
	private Label diggingMinLabel;
	@FXML
	private TextField diggingTextTextField;
	@FXML
	private ComboBox<Member> diggingComboBox;

	@FXML
	private TextField bibleReadingMinTextField;
	@FXML
	private Label bibleReadingMinLabel;
	@FXML
	private TextField bibleReadingTextTextField;
	@FXML
	private TextField bibleReadingMaterialsTextField;
	@FXML
	private ComboBox<Member> bibleReadingComboBox;

	private Settings settings;
	private Language language;
	private Stage ownerStage;
	private UserMenuMeetings ownerCtrl;
	private Week selectedWeek;
	private TabPane ownerTabPane;
	private Tab thisTab;

	@FXML
	private void initialize() {
		styleClasses();
	}

	private void styleClasses() {
		tabPane.getStyleClass().add("tabPaneStyle2");
		treasuresTab.getStyleClass().add("tabStyle1");
		ministryTab.getStyleClass().add("tabStyle1");
		christiansTab.getStyleClass().add("tabStyle1");

		generalLabel.getStyleClass().add("labelStyle2");
		treasuresLabel.getStyleClass().add("labelStyle2");

		presidentLabel.getStyleClass().add("labelStyle3");
		presidentComboBox.getStyleClass().add("comboBoxStyle2");

		bibleChaptersLabel.getStyleClass().add("labelStyle3");
		bibleChaptersTextField.getStyleClass().add("textFieldStyle3");

		song1Label.getStyleClass().add("labelStyle3");
		song1TextField.getStyleClass().add("textFieldStyle2");

		pray1Label.getStyleClass().add("labelStyle3");
		pray1ComboBox.getStyleClass().add("comboBoxStyle2");

		openingCommentsLabel.getStyleClass().add("labelStyle3");
		openingCommentsMinTextField.getStyleClass().add("textFieldStyle2");
		openingCommentsMinLabel.getStyleClass().add("labelStyle3");
		openingCommentsTextTextField.getStyleClass().add("textFieldStyle3");

		talkLabel.getStyleClass().add("labelStyle1");
		talkMinTextField.getStyleClass().add("textFieldStyle2");
		talkMinLabel.getStyleClass().add("labelStyle3");
		talkTextTextField.getStyleClass().add("textFieldStyle3");
		talkComboBox.getStyleClass().add("comboBoxStyle2");

		diggingLabel.getStyleClass().add("labelStyle1");
		diggingMinTextField.getStyleClass().add("textFieldStyle2");
		diggingMinLabel.getStyleClass().add("labelStyle3");
		diggingTextTextField.getStyleClass().add("textFieldStyle3");
		diggingComboBox.getStyleClass().add("comboBoxStyle2");

		bibleReadingLabel.getStyleClass().add("labelStyle1");
		bibleReadingMinTextField.getStyleClass().add("textFieldStyle2");
		bibleReadingMinLabel.getStyleClass().add("labelStyle3");
		bibleReadingTextTextField.getStyleClass().add("textFieldStyle3");
		bibleReadingMaterialsTextField.getStyleClass().add("textFieldStyle3");
		bibleReadingComboBox.getStyleClass().add("comboBoxStyle2");
	}

	public void objectInitialize() {
		listeners();
		viewUpdate();
	}

	private void listeners() {
		// listenerVarName();
	}

	private void viewUpdate() {

		this.language = settings.getLanguage();

		treasuresTab.setText(language.getString("TEXT0080"));
		treasuresTab.setGraphic(Meta.Resources.createTabIcon(Meta.Resources.USER_MENU_MEETINGS_TREASURES));
		ministryTab.setText(language.getString("TEXT0081"));
		ministryTab.setGraphic(Meta.Resources.createTabIcon(Meta.Resources.USER_MENU_MEETINGS_MINISTRY));
		christiansTab.setText(language.getString("TEXT0082"));
		christiansTab.setGraphic(Meta.Resources.createTabIcon(Meta.Resources.USER_MENU_MEETINGS_CHRISTIANS));

		generalLabel.setText(language.getString("TEXT0079"));
		treasuresLabel.setText(language.getString("TEXT0080"));

		presidentLabel.setText(language.getString("TEXT0083"));
		bibleChaptersLabel.setText(language.getString("TEXT0086"));
		song1Label.setText(language.getString("TEXT0085"));
		pray1Label.setText(language.getString("TEXT0084"));
		openingCommentsLabel.setText(language.getString("TEXT0087"));
		openingCommentsMinLabel.setText(language.getString("TEXT0089"));
		talkLabel.setText(language.getString("TEXT0051"));
		talkMinLabel.setText(language.getString("TEXT0089"));
		diggingLabel.setText(language.getString("TEXT0088"));
		diggingMinLabel.setText(language.getString("TEXT0089"));

		bibleReadingLabel.setText(language.getString("TEXT0047"));
		bibleReadingMinLabel.setText(language.getString("TEXT0089"));
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

	public UserMenuMeetings getOwnerCtrl() {
		return ownerCtrl;
	}

	public void setOwnerCtrl(UserMenuMeetings ownerCtrl) {
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

}
