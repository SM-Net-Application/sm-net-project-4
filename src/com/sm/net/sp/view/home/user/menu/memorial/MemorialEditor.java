package com.sm.net.sp.view.home.user.menu.memorial;

import java.util.HashMap;

import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.dialogs.place.PlaceDialog;
import com.sm.net.sp.model.EnumDays;
import com.sm.net.sp.model.EnumPlaceType;
import com.sm.net.sp.model.Family;
import com.sm.net.sp.model.Member;
import com.sm.net.sp.model.Place;
import com.sm.net.sp.model.UpdateDataAdapter;
import com.sm.net.sp.model.WeekMemorial;
import com.sm.net.sp.settings.Settings;
import com.sm.net.sp.utils.PlaceUtils;
import com.sm.net.sp.view.SupportPlannerView;
import com.sm.net.sp.view.home.user.menu.conven.EnumDaysComboBoxListCell;
import com.sm.net.sp.view.home.user.menu.memorial.task.WeekMemorialSaveTask;
import com.sm.net.util.Crypt;
import com.smnet.core.task.TaskManager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
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
	private CheckBox prayStartPresidentCheckBox;
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

	@FXML
	private Label breadHeaderLabel;
	@FXML
	private Label breadFamily1Label;
	@FXML
	private Label breadFamily2Label;
	@FXML
	private Label breadFamily3Label;
	@FXML
	private Label breadFamily4Label;
	@FXML
	private Label breadFamily5Label;

	@FXML
	private ComboBox<Family> breadFamily1ComboBox;
	@FXML
	private ComboBox<Family> breadFamily2ComboBox;
	@FXML
	private ComboBox<Family> breadFamily3ComboBox;
	@FXML
	private ComboBox<Family> breadFamily4ComboBox;
	@FXML
	private ComboBox<Family> breadFamily5ComboBox;

	@FXML
	private Label wineHeaderLabel;
	@FXML
	private Label wineFamily1Label;
	@FXML
	private Label wineFamily2Label;
	@FXML
	private Label wineFamily3Label;
	@FXML
	private Label wineFamily4Label;
	@FXML
	private Label wineFamily5Label;

	@FXML
	private ComboBox<Family> wineFamily1ComboBox;
	@FXML
	private ComboBox<Family> wineFamily2ComboBox;
	@FXML
	private ComboBox<Family> wineFamily3ComboBox;
	@FXML
	private ComboBox<Family> wineFamily4ComboBox;
	@FXML
	private ComboBox<Family> wineFamily5ComboBox;

	@FXML
	private Label emblemsBrothersHeaderLabel;
	@FXML
	private Label emblemsBrother1Label;
	@FXML
	private Label emblemsBrother2Label;
	@FXML
	private Label emblemsBrother3Label;
	@FXML
	private Label emblemsBrother4Label;
	@FXML
	private Label emblemsBrother5Label;
	@FXML
	private Label emblemsBrother6Label;
	@FXML
	private Label emblemsBrother7Label;
	@FXML
	private Label emblemsBrother8Label;
	@FXML
	private Label emblemsBrother9Label;
	@FXML
	private Label emblemsBrother10Label;

	@FXML
	private ComboBox<Member> emblemsBrother1ComboBox;
	@FXML
	private ComboBox<Member> emblemsBrother2ComboBox;
	@FXML
	private ComboBox<Member> emblemsBrother3ComboBox;
	@FXML
	private ComboBox<Member> emblemsBrother4ComboBox;
	@FXML
	private ComboBox<Member> emblemsBrother5ComboBox;
	@FXML
	private ComboBox<Member> emblemsBrother6ComboBox;
	@FXML
	private ComboBox<Member> emblemsBrother7ComboBox;
	@FXML
	private ComboBox<Member> emblemsBrother8ComboBox;
	@FXML
	private ComboBox<Member> emblemsBrother9ComboBox;
	@FXML
	private ComboBox<Member> emblemsBrother10ComboBox;

	private Settings settings;
	private Language language;
	private Stage ownerStage;

	private ObservableList<Member> membersList;
	private ObservableList<Family> familiesList;

	private Memorial ownerCtrl;
	private WeekMemorial selectedWeek;

	private TabPane ownerTabPane;
	private Tab thisTab;

	private ObservableList<WeekMemorial> calendar;
	private HashMap<String, String> configs;

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

		this.songStartLabel.getStyleClass().add("label_set_001");
		this.prayStartLabel.getStyleClass().add("label_set_001");
		this.presidentLabel.getStyleClass().add("label_set_001");
		this.talkMinLabel.getStyleClass().add("label_set_001");
		this.talkThemeLabel.getStyleClass().add("label_set_001");
		this.talkBrotherLabel.getStyleClass().add("label_set_001");
		this.prayBreadLabel.getStyleClass().add("label_set_001");
		this.prayWineLabel.getStyleClass().add("label_set_001");
		this.songEndLabel.getStyleClass().add("label_set_001");
		this.prayEndLabel.getStyleClass().add("label_set_001");

		this.songStartTextField.getStyleClass().add("text_field_002");
		this.talkMinTextField.getStyleClass().add("text_field_002");
		this.talkThemeTextField.getStyleClass().add("text_field_001");
		this.songEndTextField.getStyleClass().add("text_field_002");

		this.prayStartPresidentCheckBox.getStyleClass().add("check_box_001");

		this.prayStartComboBox.getStyleClass().add("combo_box_001");
		this.presidentComboBox.getStyleClass().add("combo_box_001");
		this.talkBrotherComboBox.getStyleClass().add("combo_box_001");
		this.prayBreadComboBox.getStyleClass().add("combo_box_001");
		this.prayWineComboBox.getStyleClass().add("combo_box_001");
		this.prayEndComboBox.getStyleClass().add("combo_box_001");

		this.breadHeaderLabel.getStyleClass().add("label_002");
		this.wineHeaderLabel.getStyleClass().add("label_002");

		this.breadFamily1Label.getStyleClass().add("label_set_001");
		this.breadFamily2Label.getStyleClass().add("label_set_001");
		this.breadFamily3Label.getStyleClass().add("label_set_001");
		this.breadFamily4Label.getStyleClass().add("label_set_001");
		this.breadFamily5Label.getStyleClass().add("label_set_001");

		this.wineFamily1Label.getStyleClass().add("label_set_001");
		this.wineFamily2Label.getStyleClass().add("label_set_001");
		this.wineFamily3Label.getStyleClass().add("label_set_001");
		this.wineFamily4Label.getStyleClass().add("label_set_001");
		this.wineFamily5Label.getStyleClass().add("label_set_001");

		this.breadFamily1ComboBox.getStyleClass().add("combo_box_001");
		this.breadFamily2ComboBox.getStyleClass().add("combo_box_001");
		this.breadFamily3ComboBox.getStyleClass().add("combo_box_001");
		this.breadFamily4ComboBox.getStyleClass().add("combo_box_001");
		this.breadFamily5ComboBox.getStyleClass().add("combo_box_001");

		this.wineFamily1ComboBox.getStyleClass().add("combo_box_001");
		this.wineFamily2ComboBox.getStyleClass().add("combo_box_001");
		this.wineFamily3ComboBox.getStyleClass().add("combo_box_001");
		this.wineFamily4ComboBox.getStyleClass().add("combo_box_001");
		this.wineFamily5ComboBox.getStyleClass().add("combo_box_001");

		this.emblemsBrothersHeaderLabel.getStyleClass().add("label_002");
		this.emblemsBrother1Label.getStyleClass().add("label_set_001");
		this.emblemsBrother2Label.getStyleClass().add("label_set_001");
		this.emblemsBrother3Label.getStyleClass().add("label_set_001");
		this.emblemsBrother4Label.getStyleClass().add("label_set_001");
		this.emblemsBrother5Label.getStyleClass().add("label_set_001");
		this.emblemsBrother6Label.getStyleClass().add("label_set_001");
		this.emblemsBrother7Label.getStyleClass().add("label_set_001");
		this.emblemsBrother8Label.getStyleClass().add("label_set_001");
		this.emblemsBrother9Label.getStyleClass().add("label_set_001");
		this.emblemsBrother10Label.getStyleClass().add("label_set_001");

		this.emblemsBrother1ComboBox.getStyleClass().add("combo_box_001");
		this.emblemsBrother2ComboBox.getStyleClass().add("combo_box_001");
		this.emblemsBrother3ComboBox.getStyleClass().add("combo_box_001");
		this.emblemsBrother4ComboBox.getStyleClass().add("combo_box_001");
		this.emblemsBrother5ComboBox.getStyleClass().add("combo_box_001");
		this.emblemsBrother6ComboBox.getStyleClass().add("combo_box_001");
		this.emblemsBrother7ComboBox.getStyleClass().add("combo_box_001");
		this.emblemsBrother8ComboBox.getStyleClass().add("combo_box_001");
		this.emblemsBrother9ComboBox.getStyleClass().add("combo_box_001");
		this.emblemsBrother10ComboBox.getStyleClass().add("combo_box_001");

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

		this.prayStartComboBox.setMinWidth(200);

		this.songStartLabel.setText(this.language.getString("memorialeditor.songstart"));
		this.prayStartLabel.setText(this.language.getString("memorialeditor.praystart"));
		this.prayStartPresidentCheckBox.setText(this.language.getString("memorialeditor.praystartpresident"));
		this.presidentLabel.setText(this.language.getString("memorialeditor.president"));
		this.talkMinLabel.setText(this.language.getString("memorialeditor.talkmin"));
		this.talkBrotherLabel.setText(this.language.getString("memorialeditor.talkbrother"));
		this.talkThemeLabel.setText(this.language.getString("memorialeditor.talktheme"));
		this.songEndLabel.setText(this.language.getString("memorialeditor.songend"));
		this.prayEndLabel.setText(this.language.getString("memorialeditor.prayend"));
		this.prayBreadLabel.setText(this.language.getString("memorialeditor.praybread"));
		this.prayWineLabel.setText(this.language.getString("memorialeditor.praywine"));

		this.breadHeaderLabel.setText(this.language.getString("memorialeditor.breadheader"));
		this.breadFamily1Label.setText(this.language.getString("memorialeditor.breadfamily1"));
		this.breadFamily2Label.setText(this.language.getString("memorialeditor.breadfamily2"));
		this.breadFamily3Label.setText(this.language.getString("memorialeditor.breadfamily3"));
		this.breadFamily4Label.setText(this.language.getString("memorialeditor.breadfamily4"));
		this.breadFamily5Label.setText(this.language.getString("memorialeditor.breadfamily5"));

		this.breadFamily1ComboBox.setMinWidth(200);
		this.breadFamily2ComboBox.setMinWidth(200);
		this.breadFamily3ComboBox.setMinWidth(200);
		this.breadFamily4ComboBox.setMinWidth(200);
		this.breadFamily5ComboBox.setMinWidth(200);

		this.wineHeaderLabel.setText(this.language.getString("memorialeditor.wineheader"));
		this.wineFamily1Label.setText(this.language.getString("memorialeditor.winefamily1"));
		this.wineFamily2Label.setText(this.language.getString("memorialeditor.winefamily2"));
		this.wineFamily3Label.setText(this.language.getString("memorialeditor.winefamily3"));
		this.wineFamily4Label.setText(this.language.getString("memorialeditor.winefamily4"));
		this.wineFamily5Label.setText(this.language.getString("memorialeditor.winefamily5"));

		this.wineFamily1ComboBox.setMinWidth(200);
		this.wineFamily2ComboBox.setMinWidth(200);
		this.wineFamily3ComboBox.setMinWidth(200);
		this.wineFamily4ComboBox.setMinWidth(200);
		this.wineFamily5ComboBox.setMinWidth(200);

		this.emblemsBrothersHeaderLabel.setText(this.language.getString("memorialeditor.emblemsbrothersheader"));
		this.emblemsBrother1Label.setText(this.language.getString("memorialeditor.emblemsbrother1"));
		this.emblemsBrother2Label.setText(this.language.getString("memorialeditor.emblemsbrother2"));
		this.emblemsBrother3Label.setText(this.language.getString("memorialeditor.emblemsbrother3"));
		this.emblemsBrother4Label.setText(this.language.getString("memorialeditor.emblemsbrother4"));
		this.emblemsBrother5Label.setText(this.language.getString("memorialeditor.emblemsbrother5"));
		this.emblemsBrother6Label.setText(this.language.getString("memorialeditor.emblemsbrother6"));
		this.emblemsBrother7Label.setText(this.language.getString("memorialeditor.emblemsbrother7"));
		this.emblemsBrother8Label.setText(this.language.getString("memorialeditor.emblemsbrother8"));
		this.emblemsBrother9Label.setText(this.language.getString("memorialeditor.emblemsbrother9"));
		this.emblemsBrother10Label.setText(this.language.getString("memorialeditor.emblemsbrother10"));

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

		Callback<ListView<EnumDays>, ListCell<EnumDays>> callbackDay = callbackForDayComboBox();
		this.dayComboBox.setButtonCell(callbackDay.call(null));
		this.dayComboBox.setCellFactory(callbackDay);

		this.dayComboBox.getItems().addAll(EnumDays.LUN, EnumDays.MAR, EnumDays.MER, EnumDays.GIO, EnumDays.VEN,
				EnumDays.SAB, EnumDays.DOM);

		this.dayComboBox.getSelectionModel().selectFirst();

		this.initIntegers(this.hourComboBox, 24);
		this.initIntegers(this.minuteComboBox, 60);

		this.hourComboBox.getSelectionModel().selectFirst();
		this.minuteComboBox.getSelectionModel().selectFirst();

		initMembers();
		initFamilies();
		initPlace();

		this.placeSelectButton.setOnAction(e -> selectPlace());
	}

	private void initFamilies() {

		ObservableList<Family> list = FXCollections.observableArrayList();
		for (Family f : this.familiesList)
			if (f.getSpInf10() == 1)
				list.add(f);

		list.add(0, Family.emptyFamily(this.language));

		this.breadFamily1ComboBox.setItems(list);
		this.breadFamily2ComboBox.setItems(list);
		this.breadFamily3ComboBox.setItems(list);
		this.breadFamily4ComboBox.setItems(list);
		this.breadFamily5ComboBox.setItems(list);

		this.breadFamily1ComboBox.getSelectionModel().selectFirst();
		this.breadFamily2ComboBox.getSelectionModel().selectFirst();
		this.breadFamily3ComboBox.getSelectionModel().selectFirst();
		this.breadFamily4ComboBox.getSelectionModel().selectFirst();
		this.breadFamily5ComboBox.getSelectionModel().selectFirst();

		this.wineFamily1ComboBox.setItems(list);
		this.wineFamily2ComboBox.setItems(list);
		this.wineFamily3ComboBox.setItems(list);
		this.wineFamily4ComboBox.setItems(list);
		this.wineFamily5ComboBox.setItems(list);

		this.wineFamily1ComboBox.getSelectionModel().selectFirst();
		this.wineFamily2ComboBox.getSelectionModel().selectFirst();
		this.wineFamily3ComboBox.getSelectionModel().selectFirst();
		this.wineFamily4ComboBox.getSelectionModel().selectFirst();
		this.wineFamily5ComboBox.getSelectionModel().selectFirst();
	}

	private void initMembers() {

		ObservableList<Member> list = FXCollections.observableArrayList();
		for (Member m : this.membersList)
			if (m.getSpInf9() == 1 || m.getSpInf10() == 1)
				list.add(m);

		list.add(0, Member.emptyMember(this.language));

		this.prayStartComboBox.setItems(list);
		this.prayStartComboBox.getSelectionModel().selectFirst();

		this.presidentComboBox.setItems(list);
		this.presidentComboBox.getSelectionModel().selectFirst();

		this.talkBrotherComboBox.setItems(list);
		this.talkBrotherComboBox.getSelectionModel().selectFirst();

		this.prayEndComboBox.setItems(list);
		this.prayEndComboBox.getSelectionModel().selectFirst();

		this.prayBreadComboBox.setItems(list);
		this.prayBreadComboBox.getSelectionModel().selectFirst();

		this.prayWineComboBox.setItems(list);
		this.prayWineComboBox.getSelectionModel().selectFirst();

		this.emblemsBrother1ComboBox.setItems(list);
		this.emblemsBrother1ComboBox.getSelectionModel().selectFirst();

		this.emblemsBrother2ComboBox.setItems(list);
		this.emblemsBrother2ComboBox.getSelectionModel().selectFirst();

		this.emblemsBrother3ComboBox.setItems(list);
		this.emblemsBrother3ComboBox.getSelectionModel().selectFirst();

		this.emblemsBrother4ComboBox.setItems(list);
		this.emblemsBrother4ComboBox.getSelectionModel().selectFirst();

		this.emblemsBrother5ComboBox.setItems(list);
		this.emblemsBrother5ComboBox.getSelectionModel().selectFirst();

		this.emblemsBrother6ComboBox.setItems(list);
		this.emblemsBrother6ComboBox.getSelectionModel().selectFirst();

		this.emblemsBrother7ComboBox.setItems(list);
		this.emblemsBrother7ComboBox.getSelectionModel().selectFirst();

		this.emblemsBrother8ComboBox.setItems(list);
		this.emblemsBrother8ComboBox.getSelectionModel().selectFirst();

		this.emblemsBrother9ComboBox.setItems(list);
		this.emblemsBrother9ComboBox.getSelectionModel().selectFirst();

		this.emblemsBrother10ComboBox.setItems(list);
		this.emblemsBrother10ComboBox.getSelectionModel().selectFirst();
	}

	private void initPlace() {

		ObservableList<Place> placesList = this.ownerCtrl.getPlacesList();
		Place found = null;
		for (Place place : placesList)
			if (place.getType().get() == EnumPlaceType.KINGDOMHALL)
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
				EnumPlaceType.KINGDOMHALL);
		if (place != null)
			this.placeTextField.setText(placeToText(place));
	}

	private void initIntegers(ComboBox<Integer> cb, int size) {

		for (int i = 0; i < size; i++)
			cb.getItems().add(i);
	}

	private Callback<ListView<EnumDays>, ListCell<EnumDays>> callbackForDayComboBox() {
		return param -> new EnumDaysComboBoxListCell(this.getSettings().getLanguage());
	}

	private void loadSelectedWeek() {

		if (this.selectedWeek != null)
			if (this.selectedWeek.spMemorialIDProperty() != null) {

				EnumDays day = EnumDays.getByID(this.selectedWeek.getSpInf21());
				this.dayComboBox.getSelectionModel().select(day);

				int hours = this.selectedWeek.getSpInf22();
				int minutes = this.selectedWeek.getSpInf23();

				this.hourComboBox.getSelectionModel().select(hours);
				this.minuteComboBox.getSelectionModel().select(minutes);

				String place = this.selectedWeek.getSpInf24();
				this.placeTextField.setText(place);

				this.songStartTextField.setText(this.selectedWeek.getSpInf2());
				setComboBoxMember(this.prayStartComboBox, this.selectedWeek.getSpInf5());
				setComboBoxMember(this.presidentComboBox, this.selectedWeek.getSpInf4());
				this.prayStartPresidentCheckBox.setSelected(this.selectedWeek.getSpInf35() == 1);

				this.talkMinTextField.setText(this.selectedWeek.getSpInf8());
				setComboBoxMember(this.talkBrotherComboBox, this.selectedWeek.getSpInf6());
				this.talkThemeTextField.setText(this.selectedWeek.getSpInf7());

				this.songEndTextField.setText(this.selectedWeek.getSpInf3());
				setComboBoxMember(this.prayEndComboBox, this.selectedWeek.getSpInf36());

				setComboBoxMember(this.prayBreadComboBox, this.selectedWeek.getSpInf9());
				setComboBoxMember(this.prayWineComboBox, this.selectedWeek.getSpInf10());

				setComboBoxFamily(this.breadFamily1ComboBox, this.selectedWeek.getSpInf11());
				setComboBoxFamily(this.breadFamily2ComboBox, this.selectedWeek.getSpInf12());
				setComboBoxFamily(this.breadFamily3ComboBox, this.selectedWeek.getSpInf13());
				setComboBoxFamily(this.breadFamily4ComboBox, this.selectedWeek.getSpInf14());
				setComboBoxFamily(this.breadFamily5ComboBox, this.selectedWeek.getSpInf15());

				setComboBoxFamily(this.wineFamily1ComboBox, this.selectedWeek.getSpInf16());
				setComboBoxFamily(this.wineFamily2ComboBox, this.selectedWeek.getSpInf17());
				setComboBoxFamily(this.wineFamily3ComboBox, this.selectedWeek.getSpInf18());
				setComboBoxFamily(this.wineFamily4ComboBox, this.selectedWeek.getSpInf19());
				setComboBoxFamily(this.wineFamily5ComboBox, this.selectedWeek.getSpInf20());

				setComboBoxMember(this.emblemsBrother1ComboBox, this.selectedWeek.getSpInf25());
				setComboBoxMember(this.emblemsBrother2ComboBox, this.selectedWeek.getSpInf26());
				setComboBoxMember(this.emblemsBrother3ComboBox, this.selectedWeek.getSpInf27());
				setComboBoxMember(this.emblemsBrother4ComboBox, this.selectedWeek.getSpInf28());
				setComboBoxMember(this.emblemsBrother5ComboBox, this.selectedWeek.getSpInf29());
				setComboBoxMember(this.emblemsBrother6ComboBox, this.selectedWeek.getSpInf30());
				setComboBoxMember(this.emblemsBrother7ComboBox, this.selectedWeek.getSpInf31());
				setComboBoxMember(this.emblemsBrother8ComboBox, this.selectedWeek.getSpInf32());
				setComboBoxMember(this.emblemsBrother9ComboBox, this.selectedWeek.getSpInf33());
				setComboBoxMember(this.emblemsBrother10ComboBox, this.selectedWeek.getSpInf34());

			} else {

				String talkMin = this.configs.get("inf8");
				String talkMinDecrypted = Crypt.decrypt(talkMin, this.settings.getDatabaseSecretKey());
				this.talkMinTextField.setText(talkMinDecrypted);

			}
	}

	private void setComboBoxMember(ComboBox<Member> cb, int id) {

		int found = 0;
		for (int i = 0; i < cb.getItems().size(); i++) {
			Member m = cb.getItems().get(i);
			if (m.getSpMemberID() == id) {
				found = i;
				break;
			}
		}

		cb.getSelectionModel().select(found);
	}

	private void setComboBoxFamily(ComboBox<Family> cb, int id) {

		int found = 0;
		for (int i = 0; i < cb.getItems().size(); i++) {
			Family f = cb.getItems().get(i);
			if (f.getSpFamID() == id) {
				found = i;
				break;
			}
		}

		cb.getSelectionModel().select(found);
	}

	private void listeners() {

		this.saveButton.setOnAction(event -> save());
	}

	private void save() {

		if (checkFields()) {

			if (this.selectedWeek.spMemorialIDProperty() != null) {

				// editWeek

				WeekMemorial weekMemorial = WeekMemorial.newInstanceByView(this);
				weekMemorial.setMemorialID(this.selectedWeek.getMemorialID());

				String waitMessage = this.language.getString("memorialeditor.wait.save");
				TaskManager.run(this.application.getAlertBuilder2(), this.ownerStage, waitMessage,
						new WeekMemorialSaveTask(this.application.getAlertBuilder2(), this.settings, this.ownerStage,
								weekMemorial, this.ownerCtrl, this.thisTab));

			} else {

				// newWeek

				WeekMemorial weekMemorial = WeekMemorial.newInstanceByView(this);

				String waitMessage = this.language.getString("memorialeditor.wait.save");
				TaskManager.run(this.application.getAlertBuilder2(), this.ownerStage, waitMessage,
						new WeekMemorialSaveTask(this.application.getAlertBuilder2(), this.settings, this.ownerStage,
								weekMemorial, this.ownerCtrl, this.thisTab));
			}
		}
	}

	private boolean checkFields() {

		String songStart = this.songStartTextField.getText();
		if (!songStart.isEmpty()) {
			try {
				new Integer(songStart);
			} catch (Exception e) {
				this.application.getAlertBuilder2().error(this.ownerStage,
						this.language.getString("memorialeditor.error.songstart"));
				return false;
			}
		}

		String songEnd = this.songEndTextField.getText();
		if (!songEnd.isEmpty()) {
			try {
				new Integer(songEnd);
			} catch (Exception e) {
				this.application.getAlertBuilder2().error(this.ownerStage,
						this.language.getString("memorialeditor.error.songend"));
				return false;
			}
		}

		String talkMin = this.talkMinTextField.getText();
		if (!talkMin.isEmpty()) {
			try {
				new Integer(talkMin);
			} catch (Exception e) {
				this.application.getAlertBuilder2().error(this.ownerStage,
						this.language.getString("memorialeditor.error.talkmin"));
				return false;
			}
		}

		int prayStartIndex = this.prayStartComboBox.getSelectionModel().getSelectedIndex();
		boolean prayStartPresidentSelected = this.prayStartPresidentCheckBox.isSelected();

		if (prayStartPresidentSelected && prayStartIndex > 0) {
			this.application.getAlertBuilder2().error(this.ownerStage,
					this.language.getString("memorialeditor.error.praystartpresident"));
			return false;
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

	public Memorial getOwnerCtrl() {
		return ownerCtrl;
	}

	public void setOwnerCtrl(Memorial ownerCtrl) {
		this.ownerCtrl = ownerCtrl;
	}

	public WeekMemorial getSelectedWeek() {
		return selectedWeek;
	}

	public void setSelectedWeek(WeekMemorial selectedWeek) {
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

	public ObservableList<WeekMemorial> getCalendar() {
		return calendar;
	}

	public void setCalendar(ObservableList<WeekMemorial> calendar) {
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

	public ObservableList<Member> getMembersList() {
		return membersList;
	}

	public void setMembersList(ObservableList<Member> membersList) {
		this.membersList = membersList;
	}

	public ObservableList<Family> getFamiliesList() {
		return familiesList;
	}

	public void setFamiliesList(ObservableList<Family> familiesList) {
		this.familiesList = familiesList;
	}

	public Tab getPodiumTab() {
		return podiumTab;
	}

	public Tab getEmblemsTab() {
		return emblemsTab;
	}

	public Tab getEmblemsBrothersTab() {
		return emblemsBrothersTab;
	}

	public Label getTimeLabel() {
		return timeLabel;
	}

	public Label getTimeSeparatorLabel() {
		return timeSeparatorLabel;
	}

	public Label getPlaceLabel() {
		return placeLabel;
	}

	public ComboBox<Integer> getHourComboBox() {
		return hourComboBox;
	}

	public ComboBox<Integer> getMinuteComboBox() {
		return minuteComboBox;
	}

	public TextField getPlaceTextField() {
		return placeTextField;
	}

	public Button getPlaceSelectButton() {
		return placeSelectButton;
	}

	public Label getSongStartLabel() {
		return songStartLabel;
	}

	public Label getPrayStartLabel() {
		return prayStartLabel;
	}

	public Label getPresidentLabel() {
		return presidentLabel;
	}

	public Label getTalkMinLabel() {
		return talkMinLabel;
	}

	public Label getTalkThemeLabel() {
		return talkThemeLabel;
	}

	public Label getTalkBrotherLabel() {
		return talkBrotherLabel;
	}

	public Label getPrayBreadLabel() {
		return prayBreadLabel;
	}

	public Label getPrayWineLabel() {
		return prayWineLabel;
	}

	public Label getSongEndLabel() {
		return songEndLabel;
	}

	public Label getPrayEndLabel() {
		return prayEndLabel;
	}

	public TextField getSongStartTextField() {
		return songStartTextField;
	}

	public ComboBox<Member> getPrayStartComboBox() {
		return prayStartComboBox;
	}

	public CheckBox getPrayStartPresidentCheckBox() {
		return prayStartPresidentCheckBox;
	}

	public ComboBox<Member> getPresidentComboBox() {
		return presidentComboBox;
	}

	public TextField getTalkMinTextField() {
		return talkMinTextField;
	}

	public TextField getTalkThemeTextField() {
		return talkThemeTextField;
	}

	public ComboBox<Member> getTalkBrotherComboBox() {
		return talkBrotherComboBox;
	}

	public ComboBox<Member> getPrayBreadComboBox() {
		return prayBreadComboBox;
	}

	public ComboBox<Member> getPrayWineComboBox() {
		return prayWineComboBox;
	}

	public TextField getSongEndTextField() {
		return songEndTextField;
	}

	public ComboBox<Member> getPrayEndComboBox() {
		return prayEndComboBox;
	}

	public Label getBreadHeaderLabel() {
		return breadHeaderLabel;
	}

	public Label getBreadFamily1Label() {
		return breadFamily1Label;
	}

	public Label getBreadFamily2Label() {
		return breadFamily2Label;
	}

	public Label getBreadFamily3Label() {
		return breadFamily3Label;
	}

	public Label getBreadFamily4Label() {
		return breadFamily4Label;
	}

	public Label getBreadFamily5Label() {
		return breadFamily5Label;
	}

	public ComboBox<Family> getBreadFamily1ComboBox() {
		return breadFamily1ComboBox;
	}

	public ComboBox<Family> getBreadFamily2ComboBox() {
		return breadFamily2ComboBox;
	}

	public ComboBox<Family> getBreadFamily3ComboBox() {
		return breadFamily3ComboBox;
	}

	public ComboBox<Family> getBreadFamily4ComboBox() {
		return breadFamily4ComboBox;
	}

	public ComboBox<Family> getBreadFamily5ComboBox() {
		return breadFamily5ComboBox;
	}

	public Label getWineHeaderLabel() {
		return wineHeaderLabel;
	}

	public Label getWineFamily1Label() {
		return wineFamily1Label;
	}

	public Label getWineFamily2Label() {
		return wineFamily2Label;
	}

	public Label getWineFamily3Label() {
		return wineFamily3Label;
	}

	public Label getWineFamily4Label() {
		return wineFamily4Label;
	}

	public Label getWineFamily5Label() {
		return wineFamily5Label;
	}

	public ComboBox<Family> getWineFamily1ComboBox() {
		return wineFamily1ComboBox;
	}

	public ComboBox<Family> getWineFamily2ComboBox() {
		return wineFamily2ComboBox;
	}

	public ComboBox<Family> getWineFamily3ComboBox() {
		return wineFamily3ComboBox;
	}

	public ComboBox<Family> getWineFamily4ComboBox() {
		return wineFamily4ComboBox;
	}

	public ComboBox<Family> getWineFamily5ComboBox() {
		return wineFamily5ComboBox;
	}

	public Label getEmblemsBrothersHeaderLabel() {
		return emblemsBrothersHeaderLabel;
	}

	public Label getEmblemsBrother1Label() {
		return emblemsBrother1Label;
	}

	public Label getEmblemsBrother2Label() {
		return emblemsBrother2Label;
	}

	public Label getEmblemsBrother3Label() {
		return emblemsBrother3Label;
	}

	public Label getEmblemsBrother4Label() {
		return emblemsBrother4Label;
	}

	public Label getEmblemsBrother5Label() {
		return emblemsBrother5Label;
	}

	public Label getEmblemsBrother6Label() {
		return emblemsBrother6Label;
	}

	public Label getEmblemsBrother7Label() {
		return emblemsBrother7Label;
	}

	public Label getEmblemsBrother8Label() {
		return emblemsBrother8Label;
	}

	public Label getEmblemsBrother9Label() {
		return emblemsBrother9Label;
	}

	public Label getEmblemsBrother10Label() {
		return emblemsBrother10Label;
	}

	public ComboBox<Member> getEmblemsBrother1ComboBox() {
		return emblemsBrother1ComboBox;
	}

	public ComboBox<Member> getEmblemsBrother2ComboBox() {
		return emblemsBrother2ComboBox;
	}

	public ComboBox<Member> getEmblemsBrother3ComboBox() {
		return emblemsBrother3ComboBox;
	}

	public ComboBox<Member> getEmblemsBrother4ComboBox() {
		return emblemsBrother4ComboBox;
	}

	public ComboBox<Member> getEmblemsBrother5ComboBox() {
		return emblemsBrother5ComboBox;
	}

	public ComboBox<Member> getEmblemsBrother6ComboBox() {
		return emblemsBrother6ComboBox;
	}

	public ComboBox<Member> getEmblemsBrother7ComboBox() {
		return emblemsBrother7ComboBox;
	}

	public ComboBox<Member> getEmblemsBrother8ComboBox() {
		return emblemsBrother8ComboBox;
	}

	public ComboBox<Member> getEmblemsBrother9ComboBox() {
		return emblemsBrother9ComboBox;
	}

	public ComboBox<Member> getEmblemsBrother10ComboBox() {
		return emblemsBrother10ComboBox;
	}

	public void setPodiumTab(Tab podiumTab) {
		this.podiumTab = podiumTab;
	}

	public void setEmblemsTab(Tab emblemsTab) {
		this.emblemsTab = emblemsTab;
	}

	public void setEmblemsBrothersTab(Tab emblemsBrothersTab) {
		this.emblemsBrothersTab = emblemsBrothersTab;
	}

	public void setTimeLabel(Label timeLabel) {
		this.timeLabel = timeLabel;
	}

	public void setTimeSeparatorLabel(Label timeSeparatorLabel) {
		this.timeSeparatorLabel = timeSeparatorLabel;
	}

	public void setPlaceLabel(Label placeLabel) {
		this.placeLabel = placeLabel;
	}

	public void setHourComboBox(ComboBox<Integer> hourComboBox) {
		this.hourComboBox = hourComboBox;
	}

	public void setMinuteComboBox(ComboBox<Integer> minuteComboBox) {
		this.minuteComboBox = minuteComboBox;
	}

	public void setPlaceTextField(TextField placeTextField) {
		this.placeTextField = placeTextField;
	}

	public void setPlaceSelectButton(Button placeSelectButton) {
		this.placeSelectButton = placeSelectButton;
	}

	public void setSongStartLabel(Label songStartLabel) {
		this.songStartLabel = songStartLabel;
	}

	public void setPrayStartLabel(Label prayStartLabel) {
		this.prayStartLabel = prayStartLabel;
	}

	public void setPresidentLabel(Label presidentLabel) {
		this.presidentLabel = presidentLabel;
	}

	public void setTalkMinLabel(Label talkMinLabel) {
		this.talkMinLabel = talkMinLabel;
	}

	public void setTalkThemeLabel(Label talkThemeLabel) {
		this.talkThemeLabel = talkThemeLabel;
	}

	public void setTalkBrotherLabel(Label talkBrotherLabel) {
		this.talkBrotherLabel = talkBrotherLabel;
	}

	public void setPrayBreadLabel(Label prayBreadLabel) {
		this.prayBreadLabel = prayBreadLabel;
	}

	public void setPrayWineLabel(Label prayWineLabel) {
		this.prayWineLabel = prayWineLabel;
	}

	public void setSongEndLabel(Label songEndLabel) {
		this.songEndLabel = songEndLabel;
	}

	public void setPrayEndLabel(Label prayEndLabel) {
		this.prayEndLabel = prayEndLabel;
	}

	public void setSongStartTextField(TextField songStartTextField) {
		this.songStartTextField = songStartTextField;
	}

	public void setPrayStartComboBox(ComboBox<Member> prayStartComboBox) {
		this.prayStartComboBox = prayStartComboBox;
	}

	public void setPrayStartPresidentCheckBox(CheckBox prayStartPresidentCheckBox) {
		this.prayStartPresidentCheckBox = prayStartPresidentCheckBox;
	}

	public void setPresidentComboBox(ComboBox<Member> presidentComboBox) {
		this.presidentComboBox = presidentComboBox;
	}

	public void setTalkMinTextField(TextField talkMinTextField) {
		this.talkMinTextField = talkMinTextField;
	}

	public void setTalkThemeTextField(TextField talkThemeTextField) {
		this.talkThemeTextField = talkThemeTextField;
	}

	public void setTalkBrotherComboBox(ComboBox<Member> talkBrotherComboBox) {
		this.talkBrotherComboBox = talkBrotherComboBox;
	}

	public void setPrayBreadComboBox(ComboBox<Member> prayBreadComboBox) {
		this.prayBreadComboBox = prayBreadComboBox;
	}

	public void setPrayWineComboBox(ComboBox<Member> prayWineComboBox) {
		this.prayWineComboBox = prayWineComboBox;
	}

	public void setSongEndTextField(TextField songEndTextField) {
		this.songEndTextField = songEndTextField;
	}

	public void setPrayEndComboBox(ComboBox<Member> prayEndComboBox) {
		this.prayEndComboBox = prayEndComboBox;
	}

	public void setBreadHeaderLabel(Label breadHeaderLabel) {
		this.breadHeaderLabel = breadHeaderLabel;
	}

	public void setBreadFamily1Label(Label breadFamily1Label) {
		this.breadFamily1Label = breadFamily1Label;
	}

	public void setBreadFamily2Label(Label breadFamily2Label) {
		this.breadFamily2Label = breadFamily2Label;
	}

	public void setBreadFamily3Label(Label breadFamily3Label) {
		this.breadFamily3Label = breadFamily3Label;
	}

	public void setBreadFamily4Label(Label breadFamily4Label) {
		this.breadFamily4Label = breadFamily4Label;
	}

	public void setBreadFamily5Label(Label breadFamily5Label) {
		this.breadFamily5Label = breadFamily5Label;
	}

	public void setBreadFamily1ComboBox(ComboBox<Family> breadFamily1ComboBox) {
		this.breadFamily1ComboBox = breadFamily1ComboBox;
	}

	public void setBreadFamily2ComboBox(ComboBox<Family> breadFamily2ComboBox) {
		this.breadFamily2ComboBox = breadFamily2ComboBox;
	}

	public void setBreadFamily3ComboBox(ComboBox<Family> breadFamily3ComboBox) {
		this.breadFamily3ComboBox = breadFamily3ComboBox;
	}

	public void setBreadFamily4ComboBox(ComboBox<Family> breadFamily4ComboBox) {
		this.breadFamily4ComboBox = breadFamily4ComboBox;
	}

	public void setBreadFamily5ComboBox(ComboBox<Family> breadFamily5ComboBox) {
		this.breadFamily5ComboBox = breadFamily5ComboBox;
	}

	public void setWineHeaderLabel(Label wineHeaderLabel) {
		this.wineHeaderLabel = wineHeaderLabel;
	}

	public void setWineFamily1Label(Label wineFamily1Label) {
		this.wineFamily1Label = wineFamily1Label;
	}

	public void setWineFamily2Label(Label wineFamily2Label) {
		this.wineFamily2Label = wineFamily2Label;
	}

	public void setWineFamily3Label(Label wineFamily3Label) {
		this.wineFamily3Label = wineFamily3Label;
	}

	public void setWineFamily4Label(Label wineFamily4Label) {
		this.wineFamily4Label = wineFamily4Label;
	}

	public void setWineFamily5Label(Label wineFamily5Label) {
		this.wineFamily5Label = wineFamily5Label;
	}

	public void setWineFamily1ComboBox(ComboBox<Family> wineFamily1ComboBox) {
		this.wineFamily1ComboBox = wineFamily1ComboBox;
	}

	public void setWineFamily2ComboBox(ComboBox<Family> wineFamily2ComboBox) {
		this.wineFamily2ComboBox = wineFamily2ComboBox;
	}

	public void setWineFamily3ComboBox(ComboBox<Family> wineFamily3ComboBox) {
		this.wineFamily3ComboBox = wineFamily3ComboBox;
	}

	public void setWineFamily4ComboBox(ComboBox<Family> wineFamily4ComboBox) {
		this.wineFamily4ComboBox = wineFamily4ComboBox;
	}

	public void setWineFamily5ComboBox(ComboBox<Family> wineFamily5ComboBox) {
		this.wineFamily5ComboBox = wineFamily5ComboBox;
	}

	public void setEmblemsBrothersHeaderLabel(Label emblemsBrothersHeaderLabel) {
		this.emblemsBrothersHeaderLabel = emblemsBrothersHeaderLabel;
	}

	public void setEmblemsBrother1Label(Label emblemsBrother1Label) {
		this.emblemsBrother1Label = emblemsBrother1Label;
	}

	public void setEmblemsBrother2Label(Label emblemsBrother2Label) {
		this.emblemsBrother2Label = emblemsBrother2Label;
	}

	public void setEmblemsBrother3Label(Label emblemsBrother3Label) {
		this.emblemsBrother3Label = emblemsBrother3Label;
	}

	public void setEmblemsBrother4Label(Label emblemsBrother4Label) {
		this.emblemsBrother4Label = emblemsBrother4Label;
	}

	public void setEmblemsBrother5Label(Label emblemsBrother5Label) {
		this.emblemsBrother5Label = emblemsBrother5Label;
	}

	public void setEmblemsBrother6Label(Label emblemsBrother6Label) {
		this.emblemsBrother6Label = emblemsBrother6Label;
	}

	public void setEmblemsBrother7Label(Label emblemsBrother7Label) {
		this.emblemsBrother7Label = emblemsBrother7Label;
	}

	public void setEmblemsBrother8Label(Label emblemsBrother8Label) {
		this.emblemsBrother8Label = emblemsBrother8Label;
	}

	public void setEmblemsBrother9Label(Label emblemsBrother9Label) {
		this.emblemsBrother9Label = emblemsBrother9Label;
	}

	public void setEmblemsBrother10Label(Label emblemsBrother10Label) {
		this.emblemsBrother10Label = emblemsBrother10Label;
	}

	public void setEmblemsBrother1ComboBox(ComboBox<Member> emblemsBrother1ComboBox) {
		this.emblemsBrother1ComboBox = emblemsBrother1ComboBox;
	}

	public void setEmblemsBrother2ComboBox(ComboBox<Member> emblemsBrother2ComboBox) {
		this.emblemsBrother2ComboBox = emblemsBrother2ComboBox;
	}

	public void setEmblemsBrother3ComboBox(ComboBox<Member> emblemsBrother3ComboBox) {
		this.emblemsBrother3ComboBox = emblemsBrother3ComboBox;
	}

	public void setEmblemsBrother4ComboBox(ComboBox<Member> emblemsBrother4ComboBox) {
		this.emblemsBrother4ComboBox = emblemsBrother4ComboBox;
	}

	public void setEmblemsBrother5ComboBox(ComboBox<Member> emblemsBrother5ComboBox) {
		this.emblemsBrother5ComboBox = emblemsBrother5ComboBox;
	}

	public void setEmblemsBrother6ComboBox(ComboBox<Member> emblemsBrother6ComboBox) {
		this.emblemsBrother6ComboBox = emblemsBrother6ComboBox;
	}

	public void setEmblemsBrother7ComboBox(ComboBox<Member> emblemsBrother7ComboBox) {
		this.emblemsBrother7ComboBox = emblemsBrother7ComboBox;
	}

	public void setEmblemsBrother8ComboBox(ComboBox<Member> emblemsBrother8ComboBox) {
		this.emblemsBrother8ComboBox = emblemsBrother8ComboBox;
	}

	public void setEmblemsBrother9ComboBox(ComboBox<Member> emblemsBrother9ComboBox) {
		this.emblemsBrother9ComboBox = emblemsBrother9ComboBox;
	}

	public void setEmblemsBrother10ComboBox(ComboBox<Member> emblemsBrother10ComboBox) {
		this.emblemsBrother10ComboBox = emblemsBrother10ComboBox;
	}

	public HashMap<String, String> getConfigs() {
		return configs;
	}

	public void setConfigs(HashMap<String, String> configs) {
		this.configs = configs;
	}
}
