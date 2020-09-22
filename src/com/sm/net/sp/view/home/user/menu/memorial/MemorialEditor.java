package com.sm.net.sp.view.home.user.menu.memorial;

import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.model.EnumDays;
import com.sm.net.sp.model.Member;
import com.sm.net.sp.model.UpdateDataAdapter;
import com.sm.net.sp.model.WeekConvention;
import com.sm.net.sp.settings.Settings;
import com.sm.net.sp.view.SupportPlannerView;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;

public class MemorialEditor extends UpdateDataAdapter {

	@FXML
	private Button saveButton;

	@FXML
	private TabPane tabPane;
	@FXML
	private Tab generalTab;
	@FXML
	private Tab podiumTab;
	@FXML
	private Tab emblemsTab;
	@FXML
	private Tab emblemsBrothersTab;

	@FXML
	private Label dayLabel;
	@FXML
	private Label timeLabel;
	@FXML
	private Label timeSeparatorLabel;
	@FXML
	private Label placeLabel;

	@FXML
	private ComboBox<EnumDays> dayComboBox;
	@FXML
	private ComboBox<Integer> hourComboBox;
	@FXML
	private ComboBox<Integer> minuteComboBox;
	@FXML
	private TextField placeTextField;
	@FXML
	private Button placeSelectButton;

	@FXML
	private Label songStartLabel;
	@FXML
	private Label prayStartLabel;
	@FXML
	private Label presidentLabel;
	@FXML
	private Label talkMinLabel;
	@FXML
	private Label talkThemeLabel;
	@FXML
	private Label talkBrotherLabel;
	@FXML
	private Label prayBreadLabel;
	@FXML
	private Label prayWineLabel;
	@FXML
	private Label songEndLabel;
	@FXML
	private Label prayEndLabel;

	@FXML
	private TextField songStartTextField;
	@FXML
	private ComboBox<Member> prayStartComboBox;
	@FXML
	private ComboBox<Member> presidentComboBox;
	@FXML
	private TextField talkMinTextField;
	@FXML
	private TextField talkThemeTextField;
	@FXML
	private ComboBox<Member> talkBrotherComboBox;
	@FXML
	private ComboBox<Member> prayBreadComboBox;
	@FXML
	private ComboBox<Member> prayWineComboBox;
	@FXML
	private TextField songEndTextField;
	@FXML
	private ComboBox<Member> prayEndComboBox;

	private Settings settings;
	private Language language;
	private Stage ownerStage;

	private Memorial ownerCtrl;
	private WeekConvention selectedWeek;

	private TabPane ownerTabPane;
	private Tab thisTab;

	private ObservableList<WeekConvention> calendar;

	private SupportPlannerView application;

	@FXML
	private void initialize() {
		styleClasses();
	}

	private void styleClasses() {

		this.tabPane.getStyleClass().add("tab_pane_003");

		this.generalTab.getStyleClass().add("tab_001");
		this.podiumTab.getStyleClass().add("tab_001");
		this.emblemsTab.getStyleClass().add("tab_001");
		this.emblemsBrothersTab.getStyleClass().add("tab_001");

		this.dayLabel.getStyleClass().add("label_set_001");
		this.timeLabel.getStyleClass().add("label_set_001");
		this.timeSeparatorLabel.getStyleClass().add("label_001");
		this.placeLabel.getStyleClass().add("label_set_001");

		this.dayComboBox.getStyleClass().add("combo_box_001");
		this.hourComboBox.getStyleClass().add("combo_box_002");
		this.minuteComboBox.getStyleClass().add("combo_box_002");
		this.placeTextField.getStyleClass().add("text_field_001");
		this.placeSelectButton.getStyleClass().add("button_image_001");

//		this.day1CircuitAssemblyLabel.getStyleClass().add("label_001");
//		this.day1RegionalConventionLabel.getStyleClass().add("label_001");
//		this.day2RegionalConventionLabel.getStyleClass().add("label_001");
//		this.day3RegionalConventionLabel.getStyleClass().add("label_001");

//		this.scriptureDay1Label.getStyleClass().add("label_set_001");
//		this.timeDay1Label.getStyleClass().add("label_set_001");
//		this.timeSeparator1Day1Label.getStyleClass().add("label_001");
//		this.timeSeparator2Day1Label.getStyleClass().add("label_001");
//		this.timeSeparator3Day1Label.getStyleClass().add("label_001");

//		this.scriptureDay1TextField.getStyleClass().add("text_field_001");
//		this.startHourDay1ComboBox.getStyleClass().add("combo_box_002");
//		this.startMinuteDay1ComboBox.getStyleClass().add("combo_box_002");
//		this.endHourDay1ComboBox.getStyleClass().add("combo_box_002");
//		this.endMinuteDay1ComboBox.getStyleClass().add("combo_box_002");

//		this.scriptureDay2Label.getStyleClass().add("label_set_001");
//		this.timeDay2Label.getStyleClass().add("label_set_001");
//		this.timeSeparator1Day2Label.getStyleClass().add("label_001");
//		this.timeSeparator2Day2Label.getStyleClass().add("label_001");
//		this.timeSeparator3Day2Label.getStyleClass().add("label_001");
//
//		this.scriptureDay2TextField.getStyleClass().add("text_field_001");
//		this.startHourDay2ComboBox.getStyleClass().add("combo_box_002");
//		this.startMinuteDay2ComboBox.getStyleClass().add("combo_box_002");
//		this.endHourDay2ComboBox.getStyleClass().add("combo_box_002");
//		this.endMinuteDay2ComboBox.getStyleClass().add("combo_box_002");
//
//		this.scriptureDay3Label.getStyleClass().add("label_set_001");
//		this.timeDay3Label.getStyleClass().add("label_set_001");
//		this.timeSeparator1Day3Label.getStyleClass().add("label_001");
//		this.timeSeparator2Day3Label.getStyleClass().add("label_001");
//		this.timeSeparator3Day3Label.getStyleClass().add("label_001");
//
//		this.scriptureDay3TextField.getStyleClass().add("text_field_001");
//		this.startHourDay3ComboBox.getStyleClass().add("combo_box_002");
//		this.startMinuteDay3ComboBox.getStyleClass().add("combo_box_002");
//		this.endHourDay3ComboBox.getStyleClass().add("combo_box_002");
//		this.endMinuteDay3ComboBox.getStyleClass().add("combo_box_002");

		this.saveButton.getStyleClass().add("button_image_001");
	}

	private void viewUpdate() {

		this.language = settings.getLanguage();

		this.tabPane.setTabMinHeight(75);
		this.tabPane.setTabMaxHeight(75);

		this.dayLabel.setText(this.language.getString("memorialeditor.day"));
		this.timeLabel.setText(this.language.getString("memorialeditor.time"));
		this.timeSeparatorLabel.setText(this.language.getString("memorialeditor.timeseparator"));
		this.placeLabel.setText(this.language.getString("memorialeditor.place"));

		int width = 100;
		this.hourComboBox.setMinWidth(width);
		this.hourComboBox.setMaxWidth(width);
		this.minuteComboBox.setMinWidth(width);
		this.minuteComboBox.setMaxWidth(width);

		this.placeSelectButton.setText(null);
		this.placeSelectButton.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.SEARCH));

//		this.day1CircuitAssemblyLabel
//				.setText(this.language.getString("conventioneditor.circuitassembly").toUpperCase());
//		this.day1RegionalConventionLabel
//				.setText(this.language.getString("conventioneditor.regionalconvention").toUpperCase());
//		this.day2RegionalConventionLabel
//				.setText(this.language.getString("conventioneditor.regionalconvention").toUpperCase());
//		this.day3RegionalConventionLabel
//				.setText(this.language.getString("conventioneditor.regionalconvention").toUpperCase());
//
//		this.scriptureDay1Label.setText(this.language.getString("conventioneditor.scripture"));
//		this.timeDay1Label.setText(this.language.getString("conventioneditor.time"));
//		this.timeSeparator1Day1Label.setText(this.language.getString("conventioneditor.separator1"));
//		this.timeSeparator2Day1Label.setText(this.language.getString("conventioneditor.separator2"));
//		this.timeSeparator3Day1Label.setText(this.language.getString("conventioneditor.separator1"));
//
//		this.scriptureDay2Label.setText(this.language.getString("conventioneditor.scripture"));
//		this.timeDay2Label.setText(this.language.getString("conventioneditor.time"));
//		this.timeSeparator1Day2Label.setText(this.language.getString("conventioneditor.separator1"));
//		this.timeSeparator2Day2Label.setText(this.language.getString("conventioneditor.separator2"));
//		this.timeSeparator3Day2Label.setText(this.language.getString("conventioneditor.separator1"));
//
//		this.scriptureDay3Label.setText(this.language.getString("conventioneditor.scripture"));
//		this.timeDay3Label.setText(this.language.getString("conventioneditor.time"));
//		this.timeSeparator1Day3Label.setText(this.language.getString("conventioneditor.separator1"));
//		this.timeSeparator2Day3Label.setText(this.language.getString("conventioneditor.separator2"));
//		this.timeSeparator3Day3Label.setText(this.language.getString("conventioneditor.separator1"));
//
//		this.startHourDay1ComboBox.setMinWidth(width);
//		this.startHourDay2ComboBox.setMinWidth(width);
//		this.startHourDay3ComboBox.setMinWidth(width);
//		this.endHourDay1ComboBox.setMinWidth(width);
//		this.endHourDay2ComboBox.setMinWidth(width);
//		this.endHourDay3ComboBox.setMinWidth(width);
//		this.startMinuteDay1ComboBox.setMinWidth(width);
//		this.startMinuteDay2ComboBox.setMinWidth(width);
//		this.startMinuteDay3ComboBox.setMinWidth(width);
//		this.endMinuteDay1ComboBox.setMinWidth(width);
//		this.endMinuteDay2ComboBox.setMinWidth(width);
//		this.endMinuteDay3ComboBox.setMinWidth(width);
//		this.startHourDay1ComboBox.setMaxWidth(width);
//		this.startHourDay2ComboBox.setMaxWidth(width);
//		this.startHourDay3ComboBox.setMaxWidth(width);
//		this.endHourDay1ComboBox.setMaxWidth(width);
//		this.endHourDay2ComboBox.setMaxWidth(width);
//		this.endHourDay3ComboBox.setMaxWidth(width);
//		this.startMinuteDay1ComboBox.setMaxWidth(width);
//		this.startMinuteDay2ComboBox.setMaxWidth(width);
//		this.startMinuteDay3ComboBox.setMaxWidth(width);
//		this.endMinuteDay1ComboBox.setMaxWidth(width);
//		this.endMinuteDay2ComboBox.setMaxWidth(width);
//		this.endMinuteDay3ComboBox.setMaxWidth(width);

		Tooltip generalTabTooltip = new Tooltip(this.language.getString("memorialeditor.tooltip.tab.general"));
		generalTabTooltip.getStyleClass().add("tooltip_001");
		this.generalTab.setTooltip(generalTabTooltip);
		this.generalTab.setText("");
		this.generalTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.INFO));

		Tooltip day1TabTooltip = new Tooltip(this.language.getString("memorialeditor.tooltip.tab.podium"));
		day1TabTooltip.getStyleClass().add("tooltip_001");
		this.podiumTab.setTooltip(day1TabTooltip);
		this.podiumTab.setText("");
		this.podiumTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.PODIUM));

		Tooltip day2TabTooltip = new Tooltip(this.language.getString("memorialeditor.tooltip.tab.emblems"));
		day2TabTooltip.getStyleClass().add("tooltip_001");
		this.emblemsTab.setTooltip(day2TabTooltip);
		this.emblemsTab.setText("");
		this.emblemsTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.EMBLEMS));

		Tooltip day3TabTooltip = new Tooltip(this.language.getString("memorialeditor.tooltip.tab.emblemsbrothers"));
		day3TabTooltip.getStyleClass().add("tooltip_001");
		this.emblemsBrothersTab.setTooltip(day3TabTooltip);
		this.emblemsBrothersTab.setText("");
		this.emblemsBrothersTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.EMBLEMSBROTHER));

		Tooltip saveButtonTooltip = new Tooltip(this.language.getString("memorialeditor.tooltip.button.save"));
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

		initFields();
		loadSelectedWeek();
	}

	private void initFields() {

//		Callback<ListView<EnumConventionType>, ListCell<EnumConventionType>> callbackConventionType = callbackForConventionTypeComboBox();
//		this.typeComboBox.setButtonCell(callbackConventionType.call(null));
//		this.typeComboBox.setCellFactory(callbackConventionType);

//		Callback<ListView<EnumDays>, ListCell<EnumDays>> callbackDay = callbackForDayComboBox();
//		this.dayComboBox.setButtonCell(callbackDay.call(null));
//		this.dayComboBox.setCellFactory(callbackDay);

		this.dayComboBox.getItems().addAll(EnumDays.LUN, EnumDays.MAR, EnumDays.MER, EnumDays.GIO, EnumDays.VEN,
				EnumDays.SAB, EnumDays.DOM);

		this.dayComboBox.getSelectionModel().selectFirst();

		this.initIntegers(this.hourComboBox, 24);
		this.initIntegers(this.minuteComboBox, 60);

		this.hourComboBox.getSelectionModel().selectFirst();
		this.minuteComboBox.getSelectionModel().selectFirst();
	}

	private void initIntegers(ComboBox<Integer> cb, int size) {

		for (int i = 0; i < size; i++)
			cb.getItems().add(i);
	}

//	private Callback<ListView<EnumConventionType>, ListCell<EnumConventionType>> callbackForConventionTypeComboBox() {
//		return param -> new EnumConventionTypeComboBoxListCell(this.getSettings().getLanguage());
//	}

//	private Callback<ListView<EnumDays>, ListCell<EnumDays>> callbackForDayComboBox() {
//		return param -> new EnumDaysComboBoxListCell(this.getSettings().getLanguage());
//	}

	private void loadSelectedWeek() {

		if (this.selectedWeek != null)
			if (this.selectedWeek.spConvenIDProperty() != null) {

//				this.startHourDay1ComboBox.getSelectionModel().select(this.selectedWeek.getSpInf8());
//				this.startMinuteDay1ComboBox.getSelectionModel().select(this.selectedWeek.getSpInf9());
//				this.endHourDay1ComboBox.getSelectionModel().select(this.selectedWeek.getSpInf10());
//				this.endMinuteDay1ComboBox.getSelectionModel().select(this.selectedWeek.getSpInf11());

				EnumDays day = EnumDays.getByID(this.selectedWeek.getSpInf7());
				this.dayComboBox.getSelectionModel().select(day);

//				this.scriptureDay1TextField.setText(this.selectedWeek.getSpInf4());
//
//				this.startHourDay2ComboBox.getSelectionModel().select(this.selectedWeek.getSpInf12());
//				this.startMinuteDay2ComboBox.getSelectionModel().select(this.selectedWeek.getSpInf13());
//				this.endHourDay2ComboBox.getSelectionModel().select(this.selectedWeek.getSpInf14());
//				this.endMinuteDay2ComboBox.getSelectionModel().select(this.selectedWeek.getSpInf15());
//
//				this.scriptureDay2TextField.setText(this.selectedWeek.getSpInf5());
//
//				this.startHourDay3ComboBox.getSelectionModel().select(this.selectedWeek.getSpInf16());
//				this.startMinuteDay3ComboBox.getSelectionModel().select(this.selectedWeek.getSpInf17());
//				this.endHourDay3ComboBox.getSelectionModel().select(this.selectedWeek.getSpInf18());
//				this.endMinuteDay3ComboBox.getSelectionModel().select(this.selectedWeek.getSpInf19());
//
//				this.scriptureDay3TextField.setText(this.selectedWeek.getSpInf6());

			}
	}

	private void listeners() {

		this.saveButton.setOnAction(event -> save());
	}

	private void save() {

		if (checkFields()) {

			if (this.selectedWeek.spConvenIDProperty() != null) {

				// editWeek

//				WeekConvention weekConvention = WeekConvention.newInstanceByView(this);
//				weekConvention.setConvenID(this.selectedWeek.getConvenID());

				String waitMessage = this.language.getString("datetime.new.wait.save");
//				TaskManager.run(this.application.getAlertBuilder2(), this.ownerStage, waitMessage,
//						new WeekConventionSaveTask(this.application.getAlertBuilder2(), this.settings, this.ownerStage,
//								weekConvention, this.ownerCtrl, this.thisTab));

			} else {

				// newWeek

//				WeekConvention weekConvention = WeekConvention.newInstanceByView(this);

				String waitMessage = this.language.getString("datetime.new.wait.save");
//				TaskManager.run(this.application.getAlertBuilder2(), this.ownerStage, waitMessage,
//						new WeekConventionSaveTask(this.application.getAlertBuilder2(), this.settings, this.ownerStage,
//								weekConvention, this.ownerCtrl, this.thisTab));
			}
		}
	}

	private boolean checkFields() {

		return true;
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

	public Memorial getOwnerCtrl() {
		return ownerCtrl;
	}

	public void setOwnerCtrl(Memorial ownerCtrl) {
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

	public SupportPlannerView getApplication() {
		return application;
	}

	public void setApplication(SupportPlannerView application) {
		this.application = application;
	}

	public Button getSaveButton() {
		return saveButton;
	}

	public TabPane getTabPane() {
		return tabPane;
	}

	public Tab getGeneralTab() {
		return generalTab;
	}

	public Tab getDay1Tab() {
		return podiumTab;
	}

	public Tab getDay2Tab() {
		return emblemsTab;
	}

	public Tab getDay3Tab() {
		return emblemsBrothersTab;
	}

	public Label getDayLabel() {
		return dayLabel;
	}

	public ComboBox<EnumDays> getDayComboBox() {
		return dayComboBox;
	}

	public void setSaveButton(Button saveButton) {
		this.saveButton = saveButton;
	}

	public void setTabPane(TabPane tabPane) {
		this.tabPane = tabPane;
	}

	public void setGeneralTab(Tab generalTab) {
		this.generalTab = generalTab;
	}

	public void setDay1Tab(Tab day1Tab) {
		this.podiumTab = day1Tab;
	}

	public void setDay2Tab(Tab day2Tab) {
		this.emblemsTab = day2Tab;
	}

	public void setDay3Tab(Tab day3Tab) {
		this.emblemsBrothersTab = day3Tab;
	}

	public void setDayLabel(Label dayLabel) {
		this.dayLabel = dayLabel;
	}

	public void setDayComboBox(ComboBox<EnumDays> dayComboBox) {
		this.dayComboBox = dayComboBox;
	}
}
