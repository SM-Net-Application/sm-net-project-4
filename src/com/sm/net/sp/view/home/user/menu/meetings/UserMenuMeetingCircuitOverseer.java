package com.sm.net.sp.view.home.user.menu.meetings;

import com.sm.net.project.Language;
import com.sm.net.sp.model.UpdateDataAdapter;
import com.sm.net.sp.model.WeekOverseer;
import com.sm.net.sp.settings.Settings;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UserMenuMeetingCircuitOverseer extends UpdateDataAdapter {

	@FXML
	private Label overseerLabel;
	@FXML
	private Label overseerNameLabel;
	@FXML
	private Label overseerShortNameLabel;
	@FXML
	private Label overseerSurnameLabel;
	@FXML
	private Label talk1Label;
	@FXML
	private Label talk1SongLabel;
	@FXML
	private Label talk1MinLabel;
	@FXML
	private Label talk1ThemeLabel;

	@FXML
	private TextField overseerNameTextField;
	@FXML
	private TextField overseerShortNameTextField;
	@FXML
	private TextField overseerSurnameTextField;
	@FXML
	private TextField talk1SongTextField;
	@FXML
	private TextField talk1MinTextField;
	@FXML
	private TextField talk1ThemeTextField;

	private Settings settings;
	private Language language;
	private Stage ownerStage;
	private UserMenuMeetingsEditor ownerCtrl;
	private WeekOverseer selectedWeek;
	private TabPane ownerTabPane;
	private Tab thisTab;

	@FXML
	private void initialize() {
		styleClasses();
	}

	private void styleClasses() {

		overseerLabel.getStyleClass().add("label_002");
		talk1Label.getStyleClass().add("label_002");

		overseerNameLabel.getStyleClass().add("label_set_001");
		overseerShortNameLabel.getStyleClass().add("label_set_001");
		overseerSurnameLabel.getStyleClass().add("label_set_001");
		talk1SongLabel.getStyleClass().add("label_set_001");
		talk1MinLabel.getStyleClass().add("label_set_001");
		talk1ThemeLabel.getStyleClass().add("label_set_001");

		talk1SongTextField.getStyleClass().add("text_field_002");
		talk1MinTextField.getStyleClass().add("text_field_002");

		overseerNameTextField.getStyleClass().add("text_field_001");
		overseerShortNameTextField.getStyleClass().add("text_field_001");
		overseerSurnameTextField.getStyleClass().add("text_field_001");
		talk1ThemeTextField.getStyleClass().add("text_field_001");
	}

	private void viewUpdate() {

		this.language = settings.getLanguage();

		overseerLabel.setText(language.getString("TEXT0037"));
		talk1Label.setText(language.getString("TEXT0141"));
		overseerNameLabel.setText(language.getString("TEXT0014"));
		overseerShortNameLabel.setText(language.getString("TEXT0018"));
		overseerSurnameLabel.setText(language.getString("TEXT0013"));
		talk1SongLabel.setText(language.getString("TEXT0144"));
		talk1MinLabel.setText(language.getString("TEXT0093"));
		talk1ThemeLabel.setText(language.getString("TEXT0094"));

		overseerNameTextField.setEditable(false);
		overseerShortNameTextField.setEditable(false);
		overseerSurnameTextField.setEditable(false);
		talk1SongTextField.setEditable(false);
		talk1MinTextField.setEditable(false);
		talk1ThemeTextField.setEditable(false);
	}

	public void objectInitialize() {
		viewUpdate();
		initData();
		listeners();
	}

	private void initData() {
		loadSelectedWeek();
	}

	private void loadSelectedWeek() {

		if (this.selectedWeek != null)
			if (this.selectedWeek.spWeekOvIDProperty() != null) {

				this.overseerNameTextField.setText(this.selectedWeek.getSpInf3());
				this.overseerShortNameTextField.setText(this.selectedWeek.getSpInf4());
				this.overseerSurnameTextField.setText(this.selectedWeek.getSpInf5());
				this.talk1SongTextField.setText(this.selectedWeek.getSpInf8());
				this.talk1MinTextField.setText(this.selectedWeek.getSpInf9());
				this.talk1ThemeTextField.setText(this.selectedWeek.getSpInf10());
			}
	}

	private void listeners() {
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

	public UserMenuMeetingsEditor getOwnerCtrl() {
		return ownerCtrl;
	}

	public void setOwnerCtrl(UserMenuMeetingsEditor ownerCtrl) {
		this.ownerCtrl = ownerCtrl;
	}

	public WeekOverseer getSelectedWeek() {
		return selectedWeek;
	}

	public void setSelectedWeek(WeekOverseer selectedWeek) {
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
