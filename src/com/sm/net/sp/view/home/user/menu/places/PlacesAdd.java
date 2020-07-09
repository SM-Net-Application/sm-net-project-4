package com.sm.net.sp.view.home.user.menu.places;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;

import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.model.UpdateDataAdapter;
import com.sm.net.sp.settings.Settings;
import com.sm.net.sp.view.SupportPlannerView;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class PlacesAdd extends UpdateDataAdapter {

	@FXML
	private AnchorPane anchorPane;
	@FXML
	private ImageView imageView;
	@FXML
	private Label titleLabel;
	@FXML
	private Label dateLabel;
	@FXML
	private Label meeting1Label;
	@FXML
	private Label meeting2Label;
	@FXML
	private Label time1Label;
	@FXML
	private Label timeSeparator1Label;
	@FXML
	private Label time2Label;
	@FXML
	private Label timeSeparator2Label;
	@FXML
	private DatePicker datePicker;
	@FXML
	private CheckBox day1CheckBox;
	@FXML
	private CheckBox day2CheckBox;
	@FXML
	private CheckBox day3CheckBox;
	@FXML
	private CheckBox day4CheckBox;
	@FXML
	private CheckBox day5CheckBox;
	@FXML
	private CheckBox day6CheckBox;
	@FXML
	private CheckBox day7CheckBox;
	@FXML
	private ComboBox<Integer> hours1ComboBox;
	@FXML
	private ComboBox<Integer> minute1ComboBox;
	@FXML
	private ComboBox<Integer> hours2ComboBox;
	@FXML
	private ComboBox<Integer> minute2ComboBox;

	@FXML
	private Button saveButton;

	private Settings settings;
	private Language language;
	private Stage ownerStage;
	private HomeUserMenuPlaces ownerCtrl;
	private Stage thisStage;
	private SupportPlannerView application;

	private ArrayList<Integer> hours;
	private ArrayList<Integer> minutes;

	private boolean listenerCheckFields;

	@FXML
	private void initialize() {
		styleClasses();
	}

	private void styleClasses() {

		this.anchorPane.getStyleClass().add("main_color_001");

		this.titleLabel.getStyleClass().add("label_setting_name");

		this.dateLabel.getStyleClass().add("label_001");
		this.meeting1Label.getStyleClass().add("label_001");
		this.meeting2Label.getStyleClass().add("label_001");
		this.time1Label.getStyleClass().add("label_001");
		this.time2Label.getStyleClass().add("label_001");
		this.timeSeparator1Label.getStyleClass().add("label_001");
		this.timeSeparator2Label.getStyleClass().add("label_001");

		this.day1CheckBox.getStyleClass().add("check_box_001");
		this.day2CheckBox.getStyleClass().add("check_box_001");
		this.day3CheckBox.getStyleClass().add("check_box_001");
		this.day4CheckBox.getStyleClass().add("check_box_001");
		this.day5CheckBox.getStyleClass().add("check_box_001");
		this.day6CheckBox.getStyleClass().add("check_box_001");
		this.day7CheckBox.getStyleClass().add("check_box_001");

		this.datePicker.getStyleClass().add("combo_box_002");

		this.hours1ComboBox.getStyleClass().add("combo_box_002");
		this.hours2ComboBox.getStyleClass().add("combo_box_002");
		this.minute1ComboBox.getStyleClass().add("combo_box_002");
		this.minute2ComboBox.getStyleClass().add("combo_box_002");

		this.saveButton.getStyleClass().add("button_image_001");
	}

	public void objectInitialize() {

		this.listenerCheckFields = false;

		initDataNew();
		listeners();
		viewUpdate();

		this.listenerCheckFields = true;
	}

	private void initDataNew() {

		thisWeek();

		hours();
		minutes();

		this.day1CheckBox.setSelected(true);
		this.day6CheckBox.setSelected(true);

		this.hours1ComboBox.getSelectionModel().selectFirst();
		this.hours2ComboBox.getSelectionModel().selectFirst();

		this.minute1ComboBox.getSelectionModel().selectFirst();
		this.minute2ComboBox.getSelectionModel().selectFirst();
	}

	private void thisWeek() {

		firstDayOfWeek(LocalDate.now());
	}

	private void minutes() {
		this.minutes = new ArrayList<Integer>();
		this.minutes.add(0);
		this.minutes.add(1);
		this.minutes.add(2);
		this.minutes.add(3);
		this.minutes.add(4);
		this.minutes.add(5);
		this.minutes.add(6);
		this.minutes.add(7);
		this.minutes.add(8);
		this.minutes.add(9);
		this.minutes.add(10);
		this.minutes.add(11);
		this.minutes.add(12);
		this.minutes.add(13);
		this.minutes.add(14);
		this.minutes.add(15);
		this.minutes.add(16);
		this.minutes.add(17);
		this.minutes.add(18);
		this.minutes.add(19);
		this.minutes.add(20);
		this.minutes.add(21);
		this.minutes.add(22);
		this.minutes.add(23);
		this.minutes.add(24);
		this.minutes.add(25);
		this.minutes.add(26);
		this.minutes.add(27);
		this.minutes.add(28);
		this.minutes.add(29);
		this.minutes.add(30);
		this.minutes.add(31);
		this.minutes.add(32);
		this.minutes.add(33);
		this.minutes.add(34);
		this.minutes.add(35);
		this.minutes.add(36);
		this.minutes.add(37);
		this.minutes.add(38);
		this.minutes.add(39);
		this.minutes.add(40);
		this.minutes.add(41);
		this.minutes.add(42);
		this.minutes.add(43);
		this.minutes.add(44);
		this.minutes.add(45);
		this.minutes.add(46);
		this.minutes.add(47);
		this.minutes.add(48);
		this.minutes.add(49);
		this.minutes.add(50);
		this.minutes.add(51);
		this.minutes.add(52);
		this.minutes.add(53);
		this.minutes.add(54);
		this.minutes.add(55);
		this.minutes.add(56);
		this.minutes.add(57);
		this.minutes.add(58);
		this.minutes.add(59);

		this.minute1ComboBox.getItems().addAll(this.minutes);
		this.minute2ComboBox.getItems().addAll(this.minutes);
	}

	private void hours() {
		this.hours = new ArrayList<Integer>();
		this.hours.add(0);
		this.hours.add(1);
		this.hours.add(2);
		this.hours.add(3);
		this.hours.add(4);
		this.hours.add(5);
		this.hours.add(6);
		this.hours.add(7);
		this.hours.add(8);
		this.hours.add(9);
		this.hours.add(10);
		this.hours.add(11);
		this.hours.add(12);
		this.hours.add(13);
		this.hours.add(14);
		this.hours.add(15);
		this.hours.add(16);
		this.hours.add(17);
		this.hours.add(18);
		this.hours.add(19);
		this.hours.add(20);
		this.hours.add(21);
		this.hours.add(22);
		this.hours.add(23);
		this.hours.add(24);

		this.hours1ComboBox.getItems().addAll(this.hours);
		this.hours2ComboBox.getItems().addAll(this.hours);
	}

	private void viewUpdate() {

		this.language = settings.getLanguage();

		this.imageView.setFitWidth(100);
		this.imageView.setFitHeight(100);
		this.imageView.setImage(Meta.Resources.getImageLogo(Meta.Resources.PLACES, 100, 100));

		this.titleLabel.setText(this.language.getString("places.new.title"));
		this.dateLabel.setText(this.language.getString("datetime.new.week"));
		this.time1Label.setText(this.language.getString("datetime.new.time1"));
		this.timeSeparator1Label.setText(this.language.getString("datetime.new.separator"));
		this.time2Label.setText(this.language.getString("datetime.new.time2"));
		this.timeSeparator2Label.setText(this.language.getString("datetime.new.separator"));

		this.meeting1Label.setText(this.language.getString("datetime.new.meeting1"));
		this.meeting2Label.setText(this.language.getString("datetime.new.meeting2"));

		this.day1CheckBox.setText(this.language.getString("TEXT0123"));
		this.day2CheckBox.setText(this.language.getString("TEXT0124"));
		this.day3CheckBox.setText(this.language.getString("TEXT0125"));
		this.day4CheckBox.setText(this.language.getString("TEXT0126"));
		this.day5CheckBox.setText(this.language.getString("TEXT0127"));
		this.day6CheckBox.setText(this.language.getString("TEXT0128"));
		this.day7CheckBox.setText(this.language.getString("TEXT0129"));

		int width = 100;
		this.hours1ComboBox.setMinWidth(width);
		this.hours1ComboBox.setMaxWidth(width);
		this.hours2ComboBox.setMinWidth(width);
		this.hours2ComboBox.setMaxWidth(width);
		this.minute1ComboBox.setMinWidth(width);
		this.minute1ComboBox.setMaxWidth(width);
		this.minute2ComboBox.setMinWidth(width);
		this.minute2ComboBox.setMaxWidth(width);

		this.saveButton.setGraphic(Meta.Resources.imageViewForButton(Meta.Resources.SAVE));
		this.saveButton.setText(null);
	}

	private void listeners() {

		this.saveButton.setOnAction(event -> save());

		this.datePicker.valueProperty().addListener((obs, oldV, newV) -> firstDayOfWeek(newV));

		this.day1CheckBox.selectedProperty().addListener((obs, oldV, newV) -> checkBoxGroups(newV, day1CheckBox,
				day2CheckBox, day3CheckBox, day4CheckBox, day5CheckBox));
		this.day2CheckBox.selectedProperty().addListener((obs, oldV, newV) -> checkBoxGroups(newV, day2CheckBox,
				day1CheckBox, day3CheckBox, day4CheckBox, day5CheckBox));
		this.day3CheckBox.selectedProperty().addListener((obs, oldV, newV) -> checkBoxGroups(newV, day3CheckBox,
				day2CheckBox, day1CheckBox, day4CheckBox, day5CheckBox));
		this.day4CheckBox.selectedProperty().addListener((obs, oldV, newV) -> checkBoxGroups(newV, day4CheckBox,
				day2CheckBox, day3CheckBox, day1CheckBox, day5CheckBox));
		this.day5CheckBox.selectedProperty().addListener((obs, oldV, newV) -> checkBoxGroups(newV, day5CheckBox,
				day2CheckBox, day3CheckBox, day4CheckBox, day1CheckBox));

		this.day6CheckBox.selectedProperty()
				.addListener((obs, oldV, newV) -> checkBoxGroups(newV, day6CheckBox, day7CheckBox));
		this.day7CheckBox.selectedProperty()
				.addListener((obs, oldV, newV) -> checkBoxGroups(newV, day7CheckBox, day6CheckBox));
	}

	private void save() {

		if (this.checkFields()) {

//			DateAndTime dateAndTime = DateAndTime.newInstanceByView(this);

			String waitMessage = this.language.getString("datetime.new.wait.save");
//			TaskManager.run(this.application.getAlertBuilder2(), this.thisStage, waitMessage, new DateAndTimeSaveTask(
//					this.application.getAlertBuilder2(), this.settings, this.thisStage, dateAndTime, this.ownerCtrl));
		}
	}

	private void checkBoxGroups(Boolean newV, CheckBox edited, CheckBox... others) {

		if (this.listenerCheckFields) {

			this.listenerCheckFields = false;

			if (newV) {
				for (CheckBox cb : others)
					if (cb.isSelected())
						cb.setSelected(false);
			} else
				edited.setSelected(true);

			this.listenerCheckFields = true;
		}
	}

	private void firstDayOfWeek(LocalDate newV) {

		if (newV != null) {

			DayOfWeek dayOfWeek = newV.getDayOfWeek();
			if (dayOfWeek != null) {

				int diff = dayOfWeek.getValue() - 1;
				LocalDate localDate = newV.minusDays(diff);
				this.datePicker.setValue(localDate);
			}
		}
	}

	private boolean checkFields() {

		boolean check = true;

		if (this.datePicker.getValue() == null) {
			String content = this.language.getString("datetime.new.error.date");
			this.application.getAlertBuilder2().error(thisStage, content);
			return false;
		}

		return check;
	}

	public Settings getSettings() {
		return settings;
	}

	public void setSettings(Settings settings) {
		this.settings = settings;
	}

	public Stage getOwnerStage() {
		return ownerStage;
	}

	public void setOwnerStage(Stage ownerStage) {
		this.ownerStage = ownerStage;
	}

	public HomeUserMenuPlaces getOwnerCtrl() {
		return ownerCtrl;
	}

	public void setOwnerCtrl(HomeUserMenuPlaces ownerCtrl) {
		this.ownerCtrl = ownerCtrl;
	}

	public Stage getThisStage() {
		return thisStage;
	}

	public void setThisStage(Stage thisStage) {
		this.thisStage = thisStage;
	}

	public SupportPlannerView getApplication() {
		return application;
	}

	public void setApplication(SupportPlannerView application) {
		this.application = application;
	}

	public DatePicker getDatePicker() {
		return datePicker;
	}

	public void setDatePicker(DatePicker datePicker) {
		this.datePicker = datePicker;
	}

	public CheckBox getDay1CheckBox() {
		return day1CheckBox;
	}

	public void setDay1CheckBox(CheckBox day1CheckBox) {
		this.day1CheckBox = day1CheckBox;
	}

	public CheckBox getDay2CheckBox() {
		return day2CheckBox;
	}

	public void setDay2CheckBox(CheckBox day2CheckBox) {
		this.day2CheckBox = day2CheckBox;
	}

	public CheckBox getDay3CheckBox() {
		return day3CheckBox;
	}

	public void setDay3CheckBox(CheckBox day3CheckBox) {
		this.day3CheckBox = day3CheckBox;
	}

	public CheckBox getDay4CheckBox() {
		return day4CheckBox;
	}

	public void setDay4CheckBox(CheckBox day4CheckBox) {
		this.day4CheckBox = day4CheckBox;
	}

	public CheckBox getDay5CheckBox() {
		return day5CheckBox;
	}

	public void setDay5CheckBox(CheckBox day5CheckBox) {
		this.day5CheckBox = day5CheckBox;
	}

	public CheckBox getDay6CheckBox() {
		return day6CheckBox;
	}

	public void setDay6CheckBox(CheckBox day6CheckBox) {
		this.day6CheckBox = day6CheckBox;
	}

	public CheckBox getDay7CheckBox() {
		return day7CheckBox;
	}

	public void setDay7CheckBox(CheckBox day7CheckBox) {
		this.day7CheckBox = day7CheckBox;
	}

	public ComboBox<Integer> getHours1ComboBox() {
		return hours1ComboBox;
	}

	public void setHours1ComboBox(ComboBox<Integer> hours1ComboBox) {
		this.hours1ComboBox = hours1ComboBox;
	}

	public ComboBox<Integer> getMinute1ComboBox() {
		return minute1ComboBox;
	}

	public void setMinute1ComboBox(ComboBox<Integer> minute1ComboBox) {
		this.minute1ComboBox = minute1ComboBox;
	}

	public ComboBox<Integer> getHours2ComboBox() {
		return hours2ComboBox;
	}

	public void setHours2ComboBox(ComboBox<Integer> hours2ComboBox) {
		this.hours2ComboBox = hours2ComboBox;
	}

	public ComboBox<Integer> getMinute2ComboBox() {
		return minute2ComboBox;
	}

	public void setMinute2ComboBox(ComboBox<Integer> minute2ComboBox) {
		this.minute2ComboBox = minute2ComboBox;
	}
}
