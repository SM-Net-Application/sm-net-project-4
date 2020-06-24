package com.sm.net.sp.view.home.user.menu.dateandtime;

import com.sm.net.auth.Authenticator;
import com.sm.net.auth.ValidationType;
import com.sm.net.javafx.AlertDesigner;
import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.model.UpdateDataAdapter;
import com.sm.net.sp.settings.Settings;
import com.sm.net.util.Crypt;

import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MenuDateAndTimeAdd extends UpdateDataAdapter {

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
	private HomeUserMenuDateAndTime ownerCtrl;
	private Stage thisStage;

	@FXML
	private void initialize() {
		styleClasses();
	}

	private void styleClasses() {

		anchorPane.getStyleClass().add("main_color_001");

		titleLabel.getStyleClass().add("label_setting_name");

		dateLabel.getStyleClass().add("label_set_001");
		meeting1Label.getStyleClass().add("label_set_001");

		saveButton.getStyleClass().add("button_image_001");
	}

	public void objectInitialize() {
		listeners();
		viewUpdate();
	}

	private void viewUpdate() {

		this.language = settings.getLanguage();

		imageView.setFitWidth(100);
		imageView.setFitHeight(100);
		imageView.setImage(Meta.Resources.getImageLogo(Meta.Resources.USER_MENU_USERS, 100, 100));

		titleLabel.setText(language.getString("TEXT0003"));

		dateLabel.setText(language.getString("VIEW007LAB002"));
		meeting1Label.setText(language.getString("VIEW002LAB002"));

		saveButton.setGraphic(Meta.Resources.imageViewForButton(Meta.Resources.SAVE));
		saveButton.setText(language.getString("VIEW002BUT001"));
	}

	private void listeners() {
		listenerCreateUserButton();
	}

	private void listenerCreateUserButton() {

		saveButton.setOnAction(event -> {

			// String user = usernameTextField.getText();
			// String password = passwordField.getText();

			// if (checkFields(user, password)) {

			// String userEncrypted = Crypt.encrypt(user, settings.getDatabaseSecretKey());
			// String passwordEncrypted = Crypt.encrypt(password,
			// settings.getDatabaseSecretKey());

			// Actions.checkUsername(userEncrypted, passwordEncrypted, settings, thisStage,
			// this);

			// } else
			// new AlertDesigner(language.getStringWithNewLine("TEXT0004"),
			// language.getStringWithNewLine("MEX002"),
			// thisStage, AlertType.ERROR, Meta.Application.getFullTitle(),
			// Meta.Resources.getImageApplicationIcon(), Meta.Themes.SUPPORTPLANNER_THEME,
			// "alert_001").show();
		});
	}

	private boolean checkFields(String user, String password) {

		boolean check = true;
		check = checkUsername(user);
		if (check)
			check = checkPassword(password);

		return check;
	}

	private boolean checkUsername(String user) {

		boolean check = true;
		if (!Authenticator.isValid(user, ValidationType.VERY_STRONG))
			check = false;
		return check;
	}

	private boolean checkPassword(String pwd) {

		boolean check = true;
		if (!Authenticator.isValid(pwd, ValidationType.VERY_STRONG))
			check = false;
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

	public HomeUserMenuDateAndTime getOwnerCtrl() {
		return ownerCtrl;
	}

	public void setOwnerCtrl(HomeUserMenuDateAndTime ownerCtrl) {
		this.ownerCtrl = ownerCtrl;
	}

	public Stage getThisStage() {
		return thisStage;
	}

	public void setThisStage(Stage thisStage) {
		this.thisStage = thisStage;
	}
}
