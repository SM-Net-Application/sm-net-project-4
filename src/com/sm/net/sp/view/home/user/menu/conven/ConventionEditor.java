package com.sm.net.sp.view.home.user.menu.conven;

import java.time.LocalDate;
import java.util.HashMap;

import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.dialogs.place.PlaceDialog;
import com.sm.net.sp.model.EnumConventionType;
import com.sm.net.sp.model.EnumDays;
import com.sm.net.sp.model.EnumPlaceType;
import com.sm.net.sp.model.Place;
import com.sm.net.sp.model.UpdateDataAdapter;
import com.sm.net.sp.model.WeekConvention;
import com.sm.net.sp.settings.Settings;
import com.sm.net.sp.utils.PlaceUtils;
import com.sm.net.sp.view.SupportPlannerView;
import com.sm.net.sp.view.home.user.menu.conven.task.WeekConventionSaveTask;
import com.sm.net.util.Crypt;
import com.smnet.core.task.TaskManager;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import javafx.util.Callback;

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

	@FXML
	private Label typeLabel;
	@FXML
	private Label yearLabel;
	@FXML
	private Label themeLabel;

	@FXML
	private ComboBox<EnumConventionType> typeComboBox;
	@FXML
	private TextField yearTextField;
	@FXML
	private TextField themeTextField;

	@FXML
	private Label day1CircuitAssemblyLabel;
	@FXML
	private Label day1RegionalConventionLabel;
	@FXML
	private Label day2RegionalConventionLabel;
	@FXML
	private Label day3RegionalConventionLabel;
	@FXML
	private Label questionsCircuitAssemblyLabel;

	@FXML
	private Label scriptureDay1Label;
	@FXML
	private Label dayLabel;
	@FXML
	private Label timeDay1Label;
	@FXML
	private Label timeSeparator1Day1Label;
	@FXML
	private Label timeSeparator2Day1Label;
	@FXML
	private Label timeSeparator3Day1Label;

	@FXML
	private TextField scriptureDay1TextField;
	@FXML
	private ComboBox<EnumDays> dayComboBox;
	@FXML
	private ComboBox<Integer> startHourDay1ComboBox;
	@FXML
	private ComboBox<Integer> startMinuteDay1ComboBox;
	@FXML
	private ComboBox<Integer> endHourDay1ComboBox;
	@FXML
	private ComboBox<Integer> endMinuteDay1ComboBox;

	@FXML
	private Label scriptureDay2Label;
	@FXML
	private Label timeDay2Label;
	@FXML
	private Label timeSeparator1Day2Label;
	@FXML
	private Label timeSeparator2Day2Label;
	@FXML
	private Label timeSeparator3Day2Label;

	@FXML
	private TextField scriptureDay2TextField;
	@FXML
	private ComboBox<Integer> startHourDay2ComboBox;
	@FXML
	private ComboBox<Integer> startMinuteDay2ComboBox;
	@FXML
	private ComboBox<Integer> endHourDay2ComboBox;
	@FXML
	private ComboBox<Integer> endMinuteDay2ComboBox;

	@FXML
	private Label scriptureDay3Label;
	@FXML
	private Label timeDay3Label;
	@FXML
	private Label timeSeparator1Day3Label;
	@FXML
	private Label timeSeparator2Day3Label;
	@FXML
	private Label timeSeparator3Day3Label;

	@FXML
	private TextField scriptureDay3TextField;
	@FXML
	private ComboBox<Integer> startHourDay3ComboBox;
	@FXML
	private ComboBox<Integer> startMinuteDay3ComboBox;
	@FXML
	private ComboBox<Integer> endHourDay3ComboBox;
	@FXML
	private ComboBox<Integer> endMinuteDay3ComboBox;

	@FXML
	private Label questionHeaderLabel;
	@FXML
	private Label question1Label;
	@FXML
	private Label question2Label;
	@FXML
	private Label question3Label;
	@FXML
	private Label question4Label;
	@FXML
	private Label question5Label;
	@FXML
	private Label question6Label;
	@FXML
	private Label question7Label;
	@FXML
	private Label question8Label;
	@FXML
	private Label question9Label;
	@FXML
	private Label question10Label;

	@FXML
	private TextField questionHeaderTextField;
	@FXML
	private TextField question1TextField;
	@FXML
	private TextField question2TextField;
	@FXML
	private TextField question3TextField;
	@FXML
	private TextField question4TextField;
	@FXML
	private TextField question5TextField;
	@FXML
	private TextField question6TextField;
	@FXML
	private TextField question7TextField;
	@FXML
	private TextField question8TextField;
	@FXML
	private TextField question9TextField;
	@FXML
	private TextField question10TextField;

	@FXML
	private Label placeLabel;
	@FXML
	private TextField placeTextField;
	@FXML
	private Button placeSelectButton;

	private Settings settings;
	private Language language;
	private Stage ownerStage;

	private Convention ownerCtrl;
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
		this.day1Tab.getStyleClass().add("tab_001");
		this.day2Tab.getStyleClass().add("tab_001");
		this.day3Tab.getStyleClass().add("tab_001");
		this.questionsTab.getStyleClass().add("tab_001");

		this.typeLabel.getStyleClass().add("label_set_001");
		this.yearLabel.getStyleClass().add("label_set_001");
		this.themeLabel.getStyleClass().add("label_set_001");

		this.typeComboBox.getStyleClass().add("combo_box_002");
		this.yearTextField.getStyleClass().add("text_field_002");
		this.themeTextField.getStyleClass().add("text_field_001");

		this.day1CircuitAssemblyLabel.getStyleClass().add("label_001");
		this.day1RegionalConventionLabel.getStyleClass().add("label_001");
		this.day2RegionalConventionLabel.getStyleClass().add("label_001");
		this.day3RegionalConventionLabel.getStyleClass().add("label_001");
		this.questionsCircuitAssemblyLabel.getStyleClass().add("label_001");

		this.scriptureDay1Label.getStyleClass().add("label_set_001");
		this.dayLabel.getStyleClass().add("label_set_001");
		this.timeDay1Label.getStyleClass().add("label_set_001");
		this.timeSeparator1Day1Label.getStyleClass().add("label_001");
		this.timeSeparator2Day1Label.getStyleClass().add("label_001");
		this.timeSeparator3Day1Label.getStyleClass().add("label_001");

		this.scriptureDay1TextField.getStyleClass().add("text_field_001");
		this.dayComboBox.getStyleClass().add("combo_box_001");
		this.startHourDay1ComboBox.getStyleClass().add("combo_box_002");
		this.startMinuteDay1ComboBox.getStyleClass().add("combo_box_002");
		this.endHourDay1ComboBox.getStyleClass().add("combo_box_002");
		this.endMinuteDay1ComboBox.getStyleClass().add("combo_box_002");

		this.scriptureDay2Label.getStyleClass().add("label_set_001");
		this.timeDay2Label.getStyleClass().add("label_set_001");
		this.timeSeparator1Day2Label.getStyleClass().add("label_001");
		this.timeSeparator2Day2Label.getStyleClass().add("label_001");
		this.timeSeparator3Day2Label.getStyleClass().add("label_001");

		this.scriptureDay2TextField.getStyleClass().add("text_field_001");
		this.startHourDay2ComboBox.getStyleClass().add("combo_box_002");
		this.startMinuteDay2ComboBox.getStyleClass().add("combo_box_002");
		this.endHourDay2ComboBox.getStyleClass().add("combo_box_002");
		this.endMinuteDay2ComboBox.getStyleClass().add("combo_box_002");

		this.scriptureDay3Label.getStyleClass().add("label_set_001");
		this.timeDay3Label.getStyleClass().add("label_set_001");
		this.timeSeparator1Day3Label.getStyleClass().add("label_001");
		this.timeSeparator2Day3Label.getStyleClass().add("label_001");
		this.timeSeparator3Day3Label.getStyleClass().add("label_001");

		this.scriptureDay3TextField.getStyleClass().add("text_field_001");
		this.startHourDay3ComboBox.getStyleClass().add("combo_box_002");
		this.startMinuteDay3ComboBox.getStyleClass().add("combo_box_002");
		this.endHourDay3ComboBox.getStyleClass().add("combo_box_002");
		this.endMinuteDay3ComboBox.getStyleClass().add("combo_box_002");

		this.questionHeaderLabel.getStyleClass().add("label_set_001");
		this.question1Label.getStyleClass().add("label_set_001");
		this.question2Label.getStyleClass().add("label_set_001");
		this.question3Label.getStyleClass().add("label_set_001");
		this.question4Label.getStyleClass().add("label_set_001");
		this.question5Label.getStyleClass().add("label_set_001");
		this.question6Label.getStyleClass().add("label_set_001");
		this.question7Label.getStyleClass().add("label_set_001");
		this.question8Label.getStyleClass().add("label_set_001");
		this.question9Label.getStyleClass().add("label_set_001");
		this.question10Label.getStyleClass().add("label_set_001");

		this.questionHeaderTextField.getStyleClass().add("text_field_001");
		this.question1TextField.getStyleClass().add("text_field_001");
		this.question2TextField.getStyleClass().add("text_field_001");
		this.question3TextField.getStyleClass().add("text_field_001");
		this.question4TextField.getStyleClass().add("text_field_001");
		this.question5TextField.getStyleClass().add("text_field_001");
		this.question6TextField.getStyleClass().add("text_field_001");
		this.question7TextField.getStyleClass().add("text_field_001");
		this.question8TextField.getStyleClass().add("text_field_001");
		this.question9TextField.getStyleClass().add("text_field_001");
		this.question10TextField.getStyleClass().add("text_field_001");

		this.saveButton.getStyleClass().add("button_image_001");

		this.placeLabel.getStyleClass().add("label_set_001");
		this.placeTextField.getStyleClass().add("text_field_001");
		this.placeSelectButton.getStyleClass().add("button_image_001");
	}

	private void viewUpdate() {

		this.language = settings.getLanguage();

		this.tabPane.setTabMinHeight(75);
		this.tabPane.setTabMaxHeight(75);

		this.typeLabel.setText(this.language.getString("conventioneditor.type"));
		this.yearLabel.setText(this.language.getString("conventioneditor.year"));
		this.themeLabel.setText(this.language.getString("conventioneditor.theme"));

		this.day1CircuitAssemblyLabel
				.setText(this.language.getString("conventioneditor.circuitassembly").toUpperCase());
		this.day1RegionalConventionLabel
				.setText(this.language.getString("conventioneditor.regionalconvention").toUpperCase());
		this.day2RegionalConventionLabel
				.setText(this.language.getString("conventioneditor.regionalconvention").toUpperCase());
		this.day3RegionalConventionLabel
				.setText(this.language.getString("conventioneditor.regionalconvention").toUpperCase());
		this.questionsCircuitAssemblyLabel
				.setText(this.language.getString("conventioneditor.circuitassembly").toUpperCase());

		this.scriptureDay1Label.setText(this.language.getString("conventioneditor.scripture"));
		this.dayLabel.setText(this.language.getString("conventioneditor.day"));
		this.timeDay1Label.setText(this.language.getString("conventioneditor.time"));
		this.timeSeparator1Day1Label.setText(this.language.getString("conventioneditor.separator1"));
		this.timeSeparator2Day1Label.setText(this.language.getString("conventioneditor.separator2"));
		this.timeSeparator3Day1Label.setText(this.language.getString("conventioneditor.separator1"));

		this.scriptureDay2Label.setText(this.language.getString("conventioneditor.scripture"));
		this.timeDay2Label.setText(this.language.getString("conventioneditor.time"));
		this.timeSeparator1Day2Label.setText(this.language.getString("conventioneditor.separator1"));
		this.timeSeparator2Day2Label.setText(this.language.getString("conventioneditor.separator2"));
		this.timeSeparator3Day2Label.setText(this.language.getString("conventioneditor.separator1"));

		this.scriptureDay3Label.setText(this.language.getString("conventioneditor.scripture"));
		this.timeDay3Label.setText(this.language.getString("conventioneditor.time"));
		this.timeSeparator1Day3Label.setText(this.language.getString("conventioneditor.separator1"));
		this.timeSeparator2Day3Label.setText(this.language.getString("conventioneditor.separator2"));
		this.timeSeparator3Day3Label.setText(this.language.getString("conventioneditor.separator1"));

		this.questionHeaderLabel.setText(this.language.getString("conventioneditor.questionheader"));
		this.question1Label.setText(this.language.getString("conventioneditor.question1"));
		this.question2Label.setText(this.language.getString("conventioneditor.question2"));
		this.question3Label.setText(this.language.getString("conventioneditor.question3"));
		this.question4Label.setText(this.language.getString("conventioneditor.question4"));
		this.question5Label.setText(this.language.getString("conventioneditor.question5"));
		this.question6Label.setText(this.language.getString("conventioneditor.question6"));
		this.question7Label.setText(this.language.getString("conventioneditor.question7"));
		this.question8Label.setText(this.language.getString("conventioneditor.question8"));
		this.question9Label.setText(this.language.getString("conventioneditor.question9"));
		this.question10Label.setText(this.language.getString("conventioneditor.question10"));

		this.typeComboBox.setMinWidth(550);

		int width = 100;
		this.startHourDay1ComboBox.setMinWidth(width);
		this.startHourDay2ComboBox.setMinWidth(width);
		this.startHourDay3ComboBox.setMinWidth(width);
		this.endHourDay1ComboBox.setMinWidth(width);
		this.endHourDay2ComboBox.setMinWidth(width);
		this.endHourDay3ComboBox.setMinWidth(width);
		this.startMinuteDay1ComboBox.setMinWidth(width);
		this.startMinuteDay2ComboBox.setMinWidth(width);
		this.startMinuteDay3ComboBox.setMinWidth(width);
		this.endMinuteDay1ComboBox.setMinWidth(width);
		this.endMinuteDay2ComboBox.setMinWidth(width);
		this.endMinuteDay3ComboBox.setMinWidth(width);
		this.startHourDay1ComboBox.setMaxWidth(width);
		this.startHourDay2ComboBox.setMaxWidth(width);
		this.startHourDay3ComboBox.setMaxWidth(width);
		this.endHourDay1ComboBox.setMaxWidth(width);
		this.endHourDay2ComboBox.setMaxWidth(width);
		this.endHourDay3ComboBox.setMaxWidth(width);
		this.startMinuteDay1ComboBox.setMaxWidth(width);
		this.startMinuteDay2ComboBox.setMaxWidth(width);
		this.startMinuteDay3ComboBox.setMaxWidth(width);
		this.endMinuteDay1ComboBox.setMaxWidth(width);
		this.endMinuteDay2ComboBox.setMaxWidth(width);
		this.endMinuteDay3ComboBox.setMaxWidth(width);

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

		this.placeLabel.setText(this.language.getString("conventioneditor.place"));
		this.placeSelectButton.setText(null);
		this.placeSelectButton.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.SEARCH));
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

		Callback<ListView<EnumConventionType>, ListCell<EnumConventionType>> callbackConventionType = callbackForConventionTypeComboBox();
		this.typeComboBox.setButtonCell(callbackConventionType.call(null));
		this.typeComboBox.setCellFactory(callbackConventionType);

		this.typeComboBox.getItems().addAll(EnumConventionType.values());
		this.typeComboBox.getSelectionModel().selectFirst();

		this.yearTextField.setText(String.valueOf(LocalDate.now().getYear()));

		Callback<ListView<EnumDays>, ListCell<EnumDays>> callbackDay = callbackForDayComboBox();
		this.dayComboBox.setButtonCell(callbackDay.call(null));
		this.dayComboBox.setCellFactory(callbackDay);

		this.dayComboBox.getItems().addAll(EnumDays.EMPTY, EnumDays.SAB, EnumDays.DOM);
		this.dayComboBox.getSelectionModel().selectFirst();

		this.initIntegers(this.startHourDay1ComboBox, 24);
		this.initIntegers(this.startHourDay2ComboBox, 24);
		this.initIntegers(this.startHourDay3ComboBox, 24);
		this.initIntegers(this.endHourDay1ComboBox, 24);
		this.initIntegers(this.endHourDay2ComboBox, 24);
		this.initIntegers(this.endHourDay3ComboBox, 24);

		this.initIntegers(this.startMinuteDay1ComboBox, 60);
		this.initIntegers(this.startMinuteDay2ComboBox, 60);
		this.initIntegers(this.startMinuteDay3ComboBox, 60);
		this.initIntegers(this.endMinuteDay1ComboBox, 60);
		this.initIntegers(this.endMinuteDay2ComboBox, 60);
		this.initIntegers(this.endMinuteDay3ComboBox, 60);

		this.startHourDay1ComboBox.getSelectionModel().selectFirst();
		this.startHourDay2ComboBox.getSelectionModel().selectFirst();
		this.startHourDay3ComboBox.getSelectionModel().selectFirst();
		this.endHourDay1ComboBox.getSelectionModel().selectFirst();
		this.endHourDay2ComboBox.getSelectionModel().selectFirst();
		this.endHourDay3ComboBox.getSelectionModel().selectFirst();

		this.startMinuteDay1ComboBox.getSelectionModel().selectFirst();
		this.startMinuteDay2ComboBox.getSelectionModel().selectFirst();
		this.startMinuteDay3ComboBox.getSelectionModel().selectFirst();
		this.endMinuteDay1ComboBox.getSelectionModel().selectFirst();
		this.endMinuteDay2ComboBox.getSelectionModel().selectFirst();
		this.endMinuteDay3ComboBox.getSelectionModel().selectFirst();

		initPlace();

		this.placeSelectButton.setOnAction(e -> selectPlace());
	}

	private void initPlace() {

		ObservableList<Place> placesList = this.ownerCtrl.getPlacesList();
		Place found = null;
		for (Place place : placesList)
			if (place.getType().get() == EnumPlaceType.CONVENTIONS)
				if (place.getDef().get()) {
					found = place;
					break;
				}

		if (found != null) {

			String addr = placeToText(found);

			this.placeTextField.setText(addr);
		}
	}

	private String placeToText(Place found) {

		String addr = "";

		HashMap<String, String> configs = this.ownerCtrl.getConfigs();
		String pattern = configs.get("inf1");
		if (pattern != null) {

			pattern = Crypt.decrypt(pattern, this.application.getSettings().getDatabaseSecretKey());
			addr = PlaceUtils.toText(found, pattern);

		} else

			addr = PlaceUtils.toText(found);

		return addr;
	}

	private void selectPlace() {

		Place place = PlaceDialog.show(this.application, this.ownerStage, this.ownerCtrl.getPlacesList(),
				EnumPlaceType.CONVENTIONS);
		if (place != null)
			this.placeTextField.setText(placeToText(place));
	}

	private void initIntegers(ComboBox<Integer> cb, int size) {

		for (int i = 0; i < size; i++)
			cb.getItems().add(i);
	}

	private Callback<ListView<EnumConventionType>, ListCell<EnumConventionType>> callbackForConventionTypeComboBox() {
		return param -> new EnumConventionTypeComboBoxListCell(this.getSettings().getLanguage());
	}

	private Callback<ListView<EnumDays>, ListCell<EnumDays>> callbackForDayComboBox() {
		return param -> new EnumDaysComboBoxListCell(this.getSettings().getLanguage());
	}

	private void loadSelectedWeek() {

		if (this.selectedWeek != null)
			if (this.selectedWeek.spConvenIDProperty() != null) {

				this.yearTextField.setText(String.valueOf(this.selectedWeek.getSpInf2()));

				int spInf1 = this.selectedWeek.getSpInf1();
				EnumConventionType type = EnumConventionType.getByID(spInf1);
				if (type != null)
					this.typeComboBox.getSelectionModel().select(type);

				this.themeTextField.setText(this.selectedWeek.getSpInf3());

				this.startHourDay1ComboBox.getSelectionModel().select(this.selectedWeek.getSpInf8());
				this.startMinuteDay1ComboBox.getSelectionModel().select(this.selectedWeek.getSpInf9());
				this.endHourDay1ComboBox.getSelectionModel().select(this.selectedWeek.getSpInf10());
				this.endMinuteDay1ComboBox.getSelectionModel().select(this.selectedWeek.getSpInf11());

				EnumDays day = EnumDays.getByID(this.selectedWeek.getSpInf7());
				this.dayComboBox.getSelectionModel().select(day);

				this.scriptureDay1TextField.setText(this.selectedWeek.getSpInf4());

				this.startHourDay2ComboBox.getSelectionModel().select(this.selectedWeek.getSpInf12());
				this.startMinuteDay2ComboBox.getSelectionModel().select(this.selectedWeek.getSpInf13());
				this.endHourDay2ComboBox.getSelectionModel().select(this.selectedWeek.getSpInf14());
				this.endMinuteDay2ComboBox.getSelectionModel().select(this.selectedWeek.getSpInf15());

				this.scriptureDay2TextField.setText(this.selectedWeek.getSpInf5());

				this.startHourDay3ComboBox.getSelectionModel().select(this.selectedWeek.getSpInf16());
				this.startMinuteDay3ComboBox.getSelectionModel().select(this.selectedWeek.getSpInf17());
				this.endHourDay3ComboBox.getSelectionModel().select(this.selectedWeek.getSpInf18());
				this.endMinuteDay3ComboBox.getSelectionModel().select(this.selectedWeek.getSpInf19());

				this.scriptureDay3TextField.setText(this.selectedWeek.getSpInf6());

				this.questionHeaderTextField.setText(this.selectedWeek.getSpInf20());
				this.question1TextField.setText(this.selectedWeek.getSpInf21());
				this.question2TextField.setText(this.selectedWeek.getSpInf22());
				this.question3TextField.setText(this.selectedWeek.getSpInf23());
				this.question4TextField.setText(this.selectedWeek.getSpInf24());
				this.question5TextField.setText(this.selectedWeek.getSpInf25());
				this.question6TextField.setText(this.selectedWeek.getSpInf26());
				this.question7TextField.setText(this.selectedWeek.getSpInf27());
				this.question8TextField.setText(this.selectedWeek.getSpInf28());
				this.question9TextField.setText(this.selectedWeek.getSpInf29());
				this.question10TextField.setText(this.selectedWeek.getSpInf30());

				this.placeTextField.setText(this.selectedWeek.getSpInf32());
			}
	}

	private void listeners() {

		this.saveButton.setOnAction(event -> save());
	}

	private void save() {

		if (checkFields()) {

			if (this.selectedWeek.spConvenIDProperty() != null) {

				// editWeek

				WeekConvention weekConvention = WeekConvention.newInstanceByView(this);
				weekConvention.setConvenID(this.selectedWeek.getConvenID());

				String waitMessage = this.language.getString("datetime.new.wait.save");
				TaskManager.run(this.application.getAlertBuilder2(), this.ownerStage, waitMessage,
						new WeekConventionSaveTask(this.application.getAlertBuilder2(), this.settings, this.ownerStage,
								weekConvention, this.ownerCtrl, this.thisTab));

			} else {

				// newWeek

				WeekConvention weekConvention = WeekConvention.newInstanceByView(this);

				String waitMessage = this.language.getString("datetime.new.wait.save");
				TaskManager.run(this.application.getAlertBuilder2(), this.ownerStage, waitMessage,
						new WeekConventionSaveTask(this.application.getAlertBuilder2(), this.settings, this.ownerStage,
								weekConvention, this.ownerCtrl, this.thisTab));
			}
		}
	}

	private boolean checkFields() {

		try {
			new Integer(this.yearTextField.getText());
		} catch (Exception e) {
			this.application.getAlertBuilder2().error(this.ownerStage,
					this.language.getString("conventioneditor.error.year"));
			return false;
		}

		EnumConventionType type = this.typeComboBox.getSelectionModel().getSelectedItem();
		switch (type) {
		case CIRCUITASSEMBLY_BRANCH:
		case CIRCUITASSEMBLY_OVERSEER:

			String scr1 = this.scriptureDay1TextField.getText();
			String scr2 = this.scriptureDay2TextField.getText();
			String scr3 = this.scriptureDay3TextField.getText();

			if (!scr1.isEmpty() || !scr2.isEmpty() || !scr3.isEmpty()) {

				this.application.getAlertBuilder2().error(this.ownerStage,
						this.language.getString("conventioneditor.error.circuitassembly.scriptures"));
				return false;
			}

			Integer startHourDay2 = this.startHourDay2ComboBox.getSelectionModel().getSelectedItem();
			Integer startHourDay3 = this.startHourDay3ComboBox.getSelectionModel().getSelectedItem();
			Integer startMinuteDay2 = this.startMinuteDay2ComboBox.getSelectionModel().getSelectedItem();
			Integer startMinuteDay3 = this.startMinuteDay3ComboBox.getSelectionModel().getSelectedItem();
			Integer endHourDay2 = this.startHourDay2ComboBox.getSelectionModel().getSelectedItem();
			Integer endHourDay3 = this.startHourDay3ComboBox.getSelectionModel().getSelectedItem();
			Integer endMinuteDay2 = this.startMinuteDay2ComboBox.getSelectionModel().getSelectedItem();
			Integer endMinuteDay3 = this.startMinuteDay3ComboBox.getSelectionModel().getSelectedItem();

			if (startHourDay2 > 0 || startHourDay3 > 0 || startMinuteDay2 > 0 || startMinuteDay3 > 0 || endHourDay2 > 0
					|| endHourDay3 > 0 || endMinuteDay2 > 0 || endMinuteDay3 > 0) {

				this.application.getAlertBuilder2().error(this.ownerStage,
						this.language.getString("conventioneditor.error.circuitassembly.times"));
				return false;
			}

			break;

		case REGIONALCONVENTION:

			EnumDays day = this.dayComboBox.getSelectionModel().getSelectedItem();
			switch (day) {
			case SAB:
			case DOM:

				this.application.getAlertBuilder2().error(this.ownerStage,
						this.language.getString("conventioneditor.error.regionalconvention.day"));
				return false;

			default:
				break;
			}

			String qheader = this.questionHeaderTextField.getText();
			String q1 = this.question1TextField.getText();
			String q2 = this.question2TextField.getText();
			String q3 = this.question3TextField.getText();
			String q4 = this.question4TextField.getText();
			String q5 = this.question5TextField.getText();
			String q6 = this.question6TextField.getText();
			String q7 = this.question7TextField.getText();
			String q8 = this.question8TextField.getText();
			String q9 = this.question9TextField.getText();
			String q10 = this.question10TextField.getText();

			if (!qheader.isEmpty() || !q1.isEmpty() || !q2.isEmpty() || !q3.isEmpty() || !q4.isEmpty() || !q5.isEmpty()
					|| !q6.isEmpty() || !q7.isEmpty() || !q8.isEmpty() || !q9.isEmpty() || !q10.isEmpty()) {

				this.application.getAlertBuilder2().error(this.ownerStage,
						this.language.getString("conventioneditor.error.regionalconvention.questions"));
				return false;
			}

			break;
		}

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
		return day1Tab;
	}

	public Tab getDay2Tab() {
		return day2Tab;
	}

	public Tab getDay3Tab() {
		return day3Tab;
	}

	public Tab getQuestionsTab() {
		return questionsTab;
	}

	public Label getTypeLabel() {
		return typeLabel;
	}

	public Label getYearLabel() {
		return yearLabel;
	}

	public Label getThemeLabel() {
		return themeLabel;
	}

	public ComboBox<EnumConventionType> getTypeComboBox() {
		return typeComboBox;
	}

	public TextField getYearTextField() {
		return yearTextField;
	}

	public TextField getThemeTextField() {
		return themeTextField;
	}

	public Label getDay1CircuitAssemblyLabel() {
		return day1CircuitAssemblyLabel;
	}

	public Label getDay1RegionalConventionLabel() {
		return day1RegionalConventionLabel;
	}

	public Label getDay2RegionalConventionLabel() {
		return day2RegionalConventionLabel;
	}

	public Label getDay3RegionalConventionLabel() {
		return day3RegionalConventionLabel;
	}

	public Label getQuestionsCircuitAssemblyLabel() {
		return questionsCircuitAssemblyLabel;
	}

	public Label getScriptureDay1Label() {
		return scriptureDay1Label;
	}

	public Label getDayLabel() {
		return dayLabel;
	}

	public Label getTimeDay1Label() {
		return timeDay1Label;
	}

	public Label getTimeSeparator1Day1Label() {
		return timeSeparator1Day1Label;
	}

	public Label getTimeSeparator2Day1Label() {
		return timeSeparator2Day1Label;
	}

	public Label getTimeSeparator3Day1Label() {
		return timeSeparator3Day1Label;
	}

	public TextField getScriptureDay1TextField() {
		return scriptureDay1TextField;
	}

	public ComboBox<EnumDays> getDayComboBox() {
		return dayComboBox;
	}

	public ComboBox<Integer> getStartHourDay1ComboBox() {
		return startHourDay1ComboBox;
	}

	public ComboBox<Integer> getStartMinuteDay1ComboBox() {
		return startMinuteDay1ComboBox;
	}

	public ComboBox<Integer> getEndHourDay1ComboBox() {
		return endHourDay1ComboBox;
	}

	public ComboBox<Integer> getEndMinuteDay1ComboBox() {
		return endMinuteDay1ComboBox;
	}

	public Label getScriptureDay2Label() {
		return scriptureDay2Label;
	}

	public Label getTimeDay2Label() {
		return timeDay2Label;
	}

	public Label getTimeSeparator1Day2Label() {
		return timeSeparator1Day2Label;
	}

	public Label getTimeSeparator2Day2Label() {
		return timeSeparator2Day2Label;
	}

	public Label getTimeSeparator3Day2Label() {
		return timeSeparator3Day2Label;
	}

	public TextField getScriptureDay2TextField() {
		return scriptureDay2TextField;
	}

	public ComboBox<Integer> getStartHourDay2ComboBox() {
		return startHourDay2ComboBox;
	}

	public ComboBox<Integer> getStartMinuteDay2ComboBox() {
		return startMinuteDay2ComboBox;
	}

	public ComboBox<Integer> getEndHourDay2ComboBox() {
		return endHourDay2ComboBox;
	}

	public ComboBox<Integer> getEndMinuteDay2ComboBox() {
		return endMinuteDay2ComboBox;
	}

	public Label getScriptureDay3Label() {
		return scriptureDay3Label;
	}

	public Label getTimeDay3Label() {
		return timeDay3Label;
	}

	public Label getTimeSeparator1Day3Label() {
		return timeSeparator1Day3Label;
	}

	public Label getTimeSeparator2Day3Label() {
		return timeSeparator2Day3Label;
	}

	public Label getTimeSeparator3Day3Label() {
		return timeSeparator3Day3Label;
	}

	public TextField getScriptureDay3TextField() {
		return scriptureDay3TextField;
	}

	public ComboBox<Integer> getStartHourDay3ComboBox() {
		return startHourDay3ComboBox;
	}

	public ComboBox<Integer> getStartMinuteDay3ComboBox() {
		return startMinuteDay3ComboBox;
	}

	public ComboBox<Integer> getEndHourDay3ComboBox() {
		return endHourDay3ComboBox;
	}

	public ComboBox<Integer> getEndMinuteDay3ComboBox() {
		return endMinuteDay3ComboBox;
	}

	public Label getQuestionHeaderLabel() {
		return questionHeaderLabel;
	}

	public Label getQuestion1Label() {
		return question1Label;
	}

	public Label getQuestion2Label() {
		return question2Label;
	}

	public Label getQuestion3Label() {
		return question3Label;
	}

	public Label getQuestion4Label() {
		return question4Label;
	}

	public Label getQuestion5Label() {
		return question5Label;
	}

	public Label getQuestion6Label() {
		return question6Label;
	}

	public Label getQuestion7Label() {
		return question7Label;
	}

	public Label getQuestion8Label() {
		return question8Label;
	}

	public Label getQuestion9Label() {
		return question9Label;
	}

	public Label getQuestion10Label() {
		return question10Label;
	}

	public TextField getQuestionHeaderTextField() {
		return questionHeaderTextField;
	}

	public TextField getQuestion1TextField() {
		return question1TextField;
	}

	public TextField getQuestion2TextField() {
		return question2TextField;
	}

	public TextField getQuestion3TextField() {
		return question3TextField;
	}

	public TextField getQuestion4TextField() {
		return question4TextField;
	}

	public TextField getQuestion5TextField() {
		return question5TextField;
	}

	public TextField getQuestion6TextField() {
		return question6TextField;
	}

	public TextField getQuestion7TextField() {
		return question7TextField;
	}

	public TextField getQuestion8TextField() {
		return question8TextField;
	}

	public TextField getQuestion9TextField() {
		return question9TextField;
	}

	public TextField getQuestion10TextField() {
		return question10TextField;
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
		this.day1Tab = day1Tab;
	}

	public void setDay2Tab(Tab day2Tab) {
		this.day2Tab = day2Tab;
	}

	public void setDay3Tab(Tab day3Tab) {
		this.day3Tab = day3Tab;
	}

	public void setQuestionsTab(Tab questionsTab) {
		this.questionsTab = questionsTab;
	}

	public void setTypeLabel(Label typeLabel) {
		this.typeLabel = typeLabel;
	}

	public void setYearLabel(Label yearLabel) {
		this.yearLabel = yearLabel;
	}

	public void setThemeLabel(Label themeLabel) {
		this.themeLabel = themeLabel;
	}

	public void setTypeComboBox(ComboBox<EnumConventionType> typeComboBox) {
		this.typeComboBox = typeComboBox;
	}

	public void setYearTextField(TextField yearTextField) {
		this.yearTextField = yearTextField;
	}

	public void setThemeTextField(TextField themeTextField) {
		this.themeTextField = themeTextField;
	}

	public void setDay1CircuitAssemblyLabel(Label day1CircuitAssemblyLabel) {
		this.day1CircuitAssemblyLabel = day1CircuitAssemblyLabel;
	}

	public void setDay1RegionalConventionLabel(Label day1RegionalConventionLabel) {
		this.day1RegionalConventionLabel = day1RegionalConventionLabel;
	}

	public void setDay2RegionalConventionLabel(Label day2RegionalConventionLabel) {
		this.day2RegionalConventionLabel = day2RegionalConventionLabel;
	}

	public void setDay3RegionalConventionLabel(Label day3RegionalConventionLabel) {
		this.day3RegionalConventionLabel = day3RegionalConventionLabel;
	}

	public void setQuestionsCircuitAssemblyLabel(Label questionsCircuitAssemblyLabel) {
		this.questionsCircuitAssemblyLabel = questionsCircuitAssemblyLabel;
	}

	public void setScriptureDay1Label(Label scriptureDay1Label) {
		this.scriptureDay1Label = scriptureDay1Label;
	}

	public void setDayLabel(Label dayLabel) {
		this.dayLabel = dayLabel;
	}

	public void setTimeDay1Label(Label timeDay1Label) {
		this.timeDay1Label = timeDay1Label;
	}

	public void setTimeSeparator1Day1Label(Label timeSeparator1Day1Label) {
		this.timeSeparator1Day1Label = timeSeparator1Day1Label;
	}

	public void setTimeSeparator2Day1Label(Label timeSeparator2Day1Label) {
		this.timeSeparator2Day1Label = timeSeparator2Day1Label;
	}

	public void setTimeSeparator3Day1Label(Label timeSeparator3Day1Label) {
		this.timeSeparator3Day1Label = timeSeparator3Day1Label;
	}

	public void setScriptureDay1TextField(TextField scriptureDay1TextField) {
		this.scriptureDay1TextField = scriptureDay1TextField;
	}

	public void setDayComboBox(ComboBox<EnumDays> dayComboBox) {
		this.dayComboBox = dayComboBox;
	}

	public void setStartHourDay1ComboBox(ComboBox<Integer> startHourDay1ComboBox) {
		this.startHourDay1ComboBox = startHourDay1ComboBox;
	}

	public void setStartMinuteDay1ComboBox(ComboBox<Integer> startMinuteDay1ComboBox) {
		this.startMinuteDay1ComboBox = startMinuteDay1ComboBox;
	}

	public void setEndHourDay1ComboBox(ComboBox<Integer> endHourDay1ComboBox) {
		this.endHourDay1ComboBox = endHourDay1ComboBox;
	}

	public void setEndMinuteDay1ComboBox(ComboBox<Integer> endMinuteDay1ComboBox) {
		this.endMinuteDay1ComboBox = endMinuteDay1ComboBox;
	}

	public void setScriptureDay2Label(Label scriptureDay2Label) {
		this.scriptureDay2Label = scriptureDay2Label;
	}

	public void setTimeDay2Label(Label timeDay2Label) {
		this.timeDay2Label = timeDay2Label;
	}

	public void setTimeSeparator1Day2Label(Label timeSeparator1Day2Label) {
		this.timeSeparator1Day2Label = timeSeparator1Day2Label;
	}

	public void setTimeSeparator2Day2Label(Label timeSeparator2Day2Label) {
		this.timeSeparator2Day2Label = timeSeparator2Day2Label;
	}

	public void setTimeSeparator3Day2Label(Label timeSeparator3Day2Label) {
		this.timeSeparator3Day2Label = timeSeparator3Day2Label;
	}

	public void setScriptureDay2TextField(TextField scriptureDay2TextField) {
		this.scriptureDay2TextField = scriptureDay2TextField;
	}

	public void setStartHourDay2ComboBox(ComboBox<Integer> startHourDay2ComboBox) {
		this.startHourDay2ComboBox = startHourDay2ComboBox;
	}

	public void setStartMinuteDay2ComboBox(ComboBox<Integer> startMinuteDay2ComboBox) {
		this.startMinuteDay2ComboBox = startMinuteDay2ComboBox;
	}

	public void setEndHourDay2ComboBox(ComboBox<Integer> endHourDay2ComboBox) {
		this.endHourDay2ComboBox = endHourDay2ComboBox;
	}

	public void setEndMinuteDay2ComboBox(ComboBox<Integer> endMinuteDay2ComboBox) {
		this.endMinuteDay2ComboBox = endMinuteDay2ComboBox;
	}

	public void setScriptureDay3Label(Label scriptureDay3Label) {
		this.scriptureDay3Label = scriptureDay3Label;
	}

	public void setTimeDay3Label(Label timeDay3Label) {
		this.timeDay3Label = timeDay3Label;
	}

	public void setTimeSeparator1Day3Label(Label timeSeparator1Day3Label) {
		this.timeSeparator1Day3Label = timeSeparator1Day3Label;
	}

	public void setTimeSeparator2Day3Label(Label timeSeparator2Day3Label) {
		this.timeSeparator2Day3Label = timeSeparator2Day3Label;
	}

	public void setTimeSeparator3Day3Label(Label timeSeparator3Day3Label) {
		this.timeSeparator3Day3Label = timeSeparator3Day3Label;
	}

	public void setScriptureDay3TextField(TextField scriptureDay3TextField) {
		this.scriptureDay3TextField = scriptureDay3TextField;
	}

	public void setStartHourDay3ComboBox(ComboBox<Integer> startHourDay3ComboBox) {
		this.startHourDay3ComboBox = startHourDay3ComboBox;
	}

	public void setStartMinuteDay3ComboBox(ComboBox<Integer> startMinuteDay3ComboBox) {
		this.startMinuteDay3ComboBox = startMinuteDay3ComboBox;
	}

	public void setEndHourDay3ComboBox(ComboBox<Integer> endHourDay3ComboBox) {
		this.endHourDay3ComboBox = endHourDay3ComboBox;
	}

	public void setEndMinuteDay3ComboBox(ComboBox<Integer> endMinuteDay3ComboBox) {
		this.endMinuteDay3ComboBox = endMinuteDay3ComboBox;
	}

	public void setQuestionHeaderLabel(Label questionHeaderLabel) {
		this.questionHeaderLabel = questionHeaderLabel;
	}

	public void setQuestion1Label(Label question1Label) {
		this.question1Label = question1Label;
	}

	public void setQuestion2Label(Label question2Label) {
		this.question2Label = question2Label;
	}

	public void setQuestion3Label(Label question3Label) {
		this.question3Label = question3Label;
	}

	public void setQuestion4Label(Label question4Label) {
		this.question4Label = question4Label;
	}

	public void setQuestion5Label(Label question5Label) {
		this.question5Label = question5Label;
	}

	public void setQuestion6Label(Label question6Label) {
		this.question6Label = question6Label;
	}

	public void setQuestion7Label(Label question7Label) {
		this.question7Label = question7Label;
	}

	public void setQuestion8Label(Label question8Label) {
		this.question8Label = question8Label;
	}

	public void setQuestion9Label(Label question9Label) {
		this.question9Label = question9Label;
	}

	public void setQuestion10Label(Label question10Label) {
		this.question10Label = question10Label;
	}

	public void setQuestionHeaderTextField(TextField questionHeaderTextField) {
		this.questionHeaderTextField = questionHeaderTextField;
	}

	public void setQuestion1TextField(TextField question1TextField) {
		this.question1TextField = question1TextField;
	}

	public void setQuestion2TextField(TextField question2TextField) {
		this.question2TextField = question2TextField;
	}

	public void setQuestion3TextField(TextField question3TextField) {
		this.question3TextField = question3TextField;
	}

	public void setQuestion4TextField(TextField question4TextField) {
		this.question4TextField = question4TextField;
	}

	public void setQuestion5TextField(TextField question5TextField) {
		this.question5TextField = question5TextField;
	}

	public void setQuestion6TextField(TextField question6TextField) {
		this.question6TextField = question6TextField;
	}

	public void setQuestion7TextField(TextField question7TextField) {
		this.question7TextField = question7TextField;
	}

	public void setQuestion8TextField(TextField question8TextField) {
		this.question8TextField = question8TextField;
	}

	public void setQuestion9TextField(TextField question9TextField) {
		this.question9TextField = question9TextField;
	}

	public void setQuestion10TextField(TextField question10TextField) {
		this.question10TextField = question10TextField;
	}

	public Label getPlaceLabel() {
		return placeLabel;
	}

	public TextField getPlaceTextField() {
		return placeTextField;
	}

	public Button getPlaceSelectButton() {
		return placeSelectButton;
	}

	public void setPlaceLabel(Label placeLabel) {
		this.placeLabel = placeLabel;
	}

	public void setPlaceTextField(TextField placeTextField) {
		this.placeTextField = placeTextField;
	}

	public void setPlaceSelectButton(Button placeSelectButton) {
		this.placeSelectButton = placeSelectButton;
	}
}
