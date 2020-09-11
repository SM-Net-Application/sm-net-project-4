package com.sm.net.sp.view.home.user.menu.conven;

import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.model.UpdateDataAdapter;
import com.sm.net.sp.model.Week;
import com.sm.net.sp.model.WeekConvention;
import com.sm.net.sp.settings.Settings;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;

public class ConventionEditor extends UpdateDataAdapter {

	@FXML
	private Button saveButton;

	@FXML
	private TabPane tabPane;
	@FXML
	private Tab generalTab;
	@FXML
	private Tab day1Tab;
	@FXML
	private Tab day2Tab;
	@FXML
	private Tab day3Tab;
	@FXML
	private Tab questionsTab;

	private Settings settings;
	private Language language;
	private Stage ownerStage;

	private Convention ownerCtrl;
	private WeekConvention selectedWeek;

	private TabPane ownerTabPane;
	private Tab thisTab;

	private ObservableList<WeekConvention> calendar;

	@FXML
	private void initialize() {
		styleClasses();
	}

	private void styleClasses() {

		this.tabPane.getStyleClass().add("tab_pane_003");

		this.generalTab.getStyleClass().add("tab_001");
		this.day1Tab.getStyleClass().add("tab_001");
		this.day2Tab.getStyleClass().add("tab_001");
		this.day3Tab.getStyleClass().add("tab_001");
		this.questionsTab.getStyleClass().add("tab_001");

		this.saveButton.getStyleClass().add("button_image_001");
	}

	private void viewUpdate() {

		this.language = settings.getLanguage();

		this.tabPane.setTabMinHeight(75);
		this.tabPane.setTabMaxHeight(75);

		Tooltip generalTabTooltip = new Tooltip(this.language.getString("conventioneditor.tooltip.tab.general"));
		generalTabTooltip.getStyleClass().add("tooltip_001");
		this.generalTab.setTooltip(generalTabTooltip);
		this.generalTab.setText("");
		this.generalTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.INFO));

		Tooltip day1TabTooltip = new Tooltip(this.language.getString("conventioneditor.tooltip.tab.day1"));
		day1TabTooltip.getStyleClass().add("tooltip_001");
		this.day1Tab.setTooltip(day1TabTooltip);
		this.day1Tab.setText("");
		this.day1Tab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.ONE));

		Tooltip day2TabTooltip = new Tooltip(this.language.getString("conventioneditor.tooltip.tab.day2"));
		day2TabTooltip.getStyleClass().add("tooltip_001");
		this.day2Tab.setTooltip(day2TabTooltip);
		this.day2Tab.setText("");
		this.day2Tab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.TWO));

		Tooltip day3TabTooltip = new Tooltip(this.language.getString("conventioneditor.tooltip.tab.day3"));
		day3TabTooltip.getStyleClass().add("tooltip_001");
		this.day3Tab.setTooltip(day3TabTooltip);
		this.day3Tab.setText("");
		this.day3Tab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.THREE));

		Tooltip questionsTabTooltip = new Tooltip(this.language.getString("conventioneditor.tooltip.tab.questions"));
		questionsTabTooltip.getStyleClass().add("tooltip_001");
		this.questionsTab.setTooltip(questionsTabTooltip);
		this.questionsTab.setText("");
		this.questionsTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.QUESTION));

		Tooltip saveButtonTooltip = new Tooltip(this.language.getString("conventioneditor.tooltip.button.save"));
		saveButtonTooltip.getStyleClass().add("tooltip_001");
		this.saveButton.setTooltip(saveButtonTooltip);
		this.saveButton.setText("");
		this.saveButton.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.SAVE));
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

//				this.visitNumberTextField.setText(this.selectedWeek.getVisitNumber());
//				this.overseerNameTextField.setText(this.selectedWeek.getSpInf3());
//				this.overseerShortNameTextField.setText(this.selectedWeek.getSpInf4());
//				this.overseerSurnameTextField.setText(this.selectedWeek.getSpInf5());
//				this.wifeNameTextField.setText(this.selectedWeek.getSpInf6());
//				this.wifeShortNameTextField.setText(this.selectedWeek.getSpInf7());
//				this.talk1SongTextField.setText(this.selectedWeek.getSpInf8());
//				this.talk1MinTextField.setText(this.selectedWeek.getSpInf9());
//				this.talk1ThemeTextField.setText(this.selectedWeek.getSpInf10());
//				this.talk2SongTextField.setText(this.selectedWeek.getSpInf11());
//				this.talk2MinTextField.setText(this.selectedWeek.getSpInf12());
//				this.talk2ThemeTextField.setText(this.selectedWeek.getSpInf13());
//				this.talk3MinTextField.setText(this.selectedWeek.getSpInf14());
//				this.talk3ThemeTextField.setText(this.selectedWeek.getSpInf15());
//
//				this.phoneOverseerTextField.setText(this.selectedWeek.getSpInf16());
//				this.mailOverseerTextField.setText(this.selectedWeek.getSpInf17());
//				this.phoneWifeTextField.setText(this.selectedWeek.getSpInf18());
//				this.mailWifeTextField.setText(this.selectedWeek.getSpInf19());
//
//				this.substituteCheckBox.setSelected((this.selectedWeek.getSpInf20() == 1));

			} else {

				// TODO : Aggiungere le altre informazioni

				int visitNumber = 1;
				String overseerName = "";
				String overseerShortName = "";
				String overseerSurname = "";
				String wifeName = "";
				String wifeShortName = "";
				String phoneOverseer = "";
				String mailOverseer = "";
				String phoneWife = "";
				String mailWife = "";

				for (WeekConvention wo : this.calendar) {

					if (wo.spWeekOvIDProperty() != null) {

						visitNumber = wo.getSpInf2();
						if (wo.getSpInf20() == 0) {

							overseerName = wo.getSpInf3();
							overseerShortName = wo.getSpInf4();
							overseerSurname = wo.getSpInf5();
							wifeName = wo.getSpInf6();
							wifeShortName = wo.getSpInf7();

							phoneOverseer = wo.getSpInf16();
							mailOverseer = wo.getSpInf17();
							phoneWife = wo.getSpInf18();
							mailWife = wo.getSpInf19();
						}
					}
				}

				visitNumber = (visitNumber == 6) ? 1 : (visitNumber + 1);

//				this.visitNumberTextField.setText(String.valueOf(visitNumber));
//				this.overseerNameTextField.setText(overseerName);
//				this.overseerShortNameTextField.setText(overseerShortName);
//				this.overseerSurnameTextField.setText(overseerSurname);
//				this.wifeNameTextField.setText(wifeName);
//				this.wifeShortNameTextField.setText(wifeShortName);
//
//				this.phoneOverseerTextField.setText(phoneOverseer);
//				this.mailOverseerTextField.setText(mailOverseer);
//				this.phoneWifeTextField.setText(phoneWife);
//				this.mailWifeTextField.setText(mailWife);
			}
	}

	private void listeners() {

		this.saveButton.setOnAction(event -> saveWeek());
	}

	private void saveWeek() {

		if (checkFields()) {

//			String spInf2 = visitNumberTextField.getText();
//			String spInf3 = Crypt.encrypt(overseerNameTextField.getText(), settings.getDatabaseSecretKey());
//			String spInf4 = Crypt.encrypt(overseerShortNameTextField.getText(), settings.getDatabaseSecretKey());
//			String spInf5 = Crypt.encrypt(overseerSurnameTextField.getText(), settings.getDatabaseSecretKey());
//			String spInf6 = Crypt.encrypt(wifeNameTextField.getText(), settings.getDatabaseSecretKey());
//			String spInf7 = Crypt.encrypt(wifeShortNameTextField.getText(), settings.getDatabaseSecretKey());
//			String spInf8 = Crypt.encrypt(talk1SongTextField.getText(), settings.getDatabaseSecretKey());
//			String spInf9 = Crypt.encrypt(talk1MinTextField.getText(), settings.getDatabaseSecretKey());
//			String spInf10 = Crypt.encrypt(talk1ThemeTextField.getText(), settings.getDatabaseSecretKey());
//			String spInf11 = Crypt.encrypt(talk2SongTextField.getText(), settings.getDatabaseSecretKey());
//			String spInf12 = Crypt.encrypt(talk2MinTextField.getText(), settings.getDatabaseSecretKey());
//			String spInf13 = Crypt.encrypt(talk2ThemeTextField.getText(), settings.getDatabaseSecretKey());
//			String spInf14 = Crypt.encrypt(talk3MinTextField.getText(), settings.getDatabaseSecretKey());
//			String spInf15 = Crypt.encrypt(talk3ThemeTextField.getText(), settings.getDatabaseSecretKey());
//
//			String spInf16 = Crypt.encrypt(phoneOverseerTextField.getText(), settings.getDatabaseSecretKey());
//			String spInf17 = Crypt.encrypt(mailOverseerTextField.getText(), settings.getDatabaseSecretKey());
//			String spInf18 = Crypt.encrypt(phoneWifeTextField.getText(), settings.getDatabaseSecretKey());
//			String spInf19 = Crypt.encrypt(mailWifeTextField.getText(), settings.getDatabaseSecretKey());
//
//			String spInf20 = !this.substituteCheckBox.isSelected() ? "0" : "1";

			if (this.selectedWeek.spWeekOvIDProperty() != null) {
				// editWeek

				String spWeekOvID = String.valueOf(selectedWeek.getSpWeekOvID());
				String spInf1 = String.valueOf(selectedWeek.getSpInf1());

//				Actions.updateOverseerWeek(spWeekOvID, spInf1, spInf2, spInf3, spInf4, spInf5, spInf6, spInf7, spInf8,
//						spInf9, spInf10, spInf11, spInf12, spInf13, spInf14, spInf15, spInf16, spInf17, spInf18,
//						spInf19, spInf20, settings, ownerStage, ownerTabPane, thisTab, ownerCtrl);

			} else {
				// newWeek

				String spInf1 = Week.buildKey(this.selectedWeek.getTo());

//				Actions.insertOverseerWeek(spInf1, spInf2, spInf3, spInf4, spInf5, spInf6, spInf7, spInf8, spInf9,
//						spInf10, spInf11, spInf12, spInf13, spInf14, spInf15, spInf16, spInf17, spInf18, spInf19,
//						spInf20, settings, ownerStage, ownerTabPane, thisTab, ownerCtrl);
			}
		}
	}

	private boolean checkFields() {

		// TODO: Check all fields
		boolean status = true;

		return status;
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

	public Convention getOwnerCtrl() {
		return ownerCtrl;
	}

	public void setOwnerCtrl(Convention ownerCtrl) {
		this.ownerCtrl = ownerCtrl;
	}

	public WeekConvention getSelectedWeek() {
		return selectedWeek;
	}

	public void setSelectedWeek(WeekConvention selectedWeek) {
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

	public ObservableList<WeekConvention> getCalendar() {
		return calendar;
	}

	public void setCalendar(ObservableList<WeekConvention> calendar) {
		this.calendar = calendar;
	}
}
