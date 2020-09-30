package com.sm.net.sp.view.home.user.menu.naturaldisaster;

import com.sm.net.javafx.AlertDesigner;
import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.actions.Actions;
import com.sm.net.sp.model.Family;
import com.sm.net.sp.settings.Settings;
import com.sm.net.util.Crypt;

import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;

public class UserMenuNaturalDisasterFamilyEditor {

	@FXML
	private Label familyNameLabel;
	@FXML
	private TextField familyNameTextField;
	@FXML
	private Label familyStreetLabel;
	@FXML
	private TextField familyStreetTextField;
	@FXML
	private Label familyNummerLabel;
	@FXML
	private TextField familyNummerTextField;
	@FXML
	private Label familyPostCodeLabel;
	@FXML
	private TextField familyPostCodeTextField;
	@FXML
	private Label familyCityLabel;
	@FXML
	private TextField familyCityTextField;
	@FXML
	private Label familyPhoneLabel;
	@FXML
	private TextField familyPhoneTextField;
	@FXML
	private Button saveButton;

	private Settings settings;
	private Language language;
	private Stage ownerStage;
	private TabPane congrTabPane;
	private Tab newFamilyTab;
	private Tab familiesTab;
	private UserMenuNaturalDisasterList ownerCtrl;
	private Family selectedFamily;

	@FXML
	private void initialize() {
		styleClasses();
	}

	private void styleClasses() {

		familyNameLabel.getStyleClass().add("label_001");
		familyStreetLabel.getStyleClass().add("label_001");
		familyNummerLabel.getStyleClass().add("label_001");
		familyPostCodeLabel.getStyleClass().add("label_001");
		familyCityLabel.getStyleClass().add("label_001");
		familyPhoneLabel.getStyleClass().add("label_001");

		familyNameTextField.getStyleClass().add("text_field_001");
		familyStreetTextField.getStyleClass().add("text_field_001");
		familyNummerTextField.getStyleClass().add("text_field_001");
		familyPostCodeTextField.getStyleClass().add("text_field_001");
		familyCityTextField.getStyleClass().add("text_field_001");
		familyPhoneTextField.getStyleClass().add("text_field_001");

		saveButton.getStyleClass().add("button_image_001");
	}

	public void objectInitialize() {
		initValue();
		viewUpdate();
		listeners();
	}

	private void viewUpdate() {

		this.language = settings.getLanguage();

		familyNameLabel.setText(language.getString("TEXT0025"));
		familyNameTextField.setEditable(false);
		familyStreetLabel.setText(language.getString("TEXT0027"));
		familyNummerLabel.setText(language.getString("TEXT0028"));
		familyPostCodeLabel.setText(language.getString("TEXT0029"));
		familyCityLabel.setText(language.getString("TEXT0030"));
		familyPhoneLabel.setText(language.getString("TEXT0109"));

		Tooltip saveTooltip = new Tooltip(this.language.getString("naturaldisasterfamilyeditor.tooltip.save"));
		saveTooltip.getStyleClass().add("tooltip_001");
		this.saveButton.setTooltip(saveTooltip);
		this.saveButton.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.SAVE));
		this.saveButton.setText("");
	}

	private void initValue() {

		if (selectedFamily != null) {

			this.familyNameTextField.setText(selectedFamily.getSpInf1Decrypted());
			this.familyStreetTextField.setText(selectedFamily.getSpInf2Decrypted());
			this.familyNummerTextField.setText(selectedFamily.getSpInf3Decrypted());
			this.familyPostCodeTextField.setText(selectedFamily.getSpInf4Decrypted());
			this.familyCityTextField.setText(selectedFamily.getSpInf5Decrypted());
			this.familyPhoneTextField.setText(selectedFamily.getSpInf7Decrypted());
		}
	}

	private void listeners() {
		listenerSaveButton();
	}

	private void listenerSaveButton() {
		saveButton.setOnAction(event -> saveFamily());
	}

	private void saveFamily() {

		if (checkFields()) {

			String spInf1 = Crypt.encrypt(familyNameTextField.getText(), settings.getDatabaseSecretKey());
			String spInf2 = Crypt.encrypt(familyStreetTextField.getText(), settings.getDatabaseSecretKey());
			String spInf3 = Crypt.encrypt(familyNummerTextField.getText(), settings.getDatabaseSecretKey());
			String spInf4 = Crypt.encrypt(familyPostCodeTextField.getText(), settings.getDatabaseSecretKey());
			String spInf5 = Crypt.encrypt(familyCityTextField.getText(), settings.getDatabaseSecretKey());
			String spInf7 = Crypt.encrypt(familyPhoneTextField.getText(), settings.getDatabaseSecretKey());

			if (selectedFamily != null)
				editFamily(spInf1, spInf2, spInf3, spInf4, spInf5, spInf7);
		} else
			new AlertDesigner(language.getStringWithNewLine("TEXT0004"), ownerStage, AlertType.ERROR,
					Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
					Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").show();
	}

	private void editFamily(String spInf1, String spInf2, String spInf3, String spInf4, String spInf5, String spInf7) {

		Actions.updateFamilyNaturalDisaster(String.valueOf(selectedFamily.getSpFamID()), spInf1, spInf2, spInf3, spInf4,
				spInf5, spInf7, settings, ownerStage, congrTabPane, newFamilyTab, familiesTab, ownerCtrl);
	}

	private boolean checkFields() {

		boolean status = true;

		if (familyNameTextField.getText().isEmpty())
			status = false;

		return status;
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

	public TabPane getCongrTabPane() {
		return congrTabPane;
	}

	public void setCongrTabPane(TabPane congrTabPane) {
		this.congrTabPane = congrTabPane;
	}

	public Tab getNewMemberTab() {
		return newFamilyTab;
	}

	public void setNewMemberTab(Tab newMemberTab) {
		this.newFamilyTab = newMemberTab;
	}

	public UserMenuNaturalDisasterList getOwnerCtrl() {
		return ownerCtrl;
	}

	public void setOwnerCtrl(UserMenuNaturalDisasterList ownerCtrl) {
		this.ownerCtrl = ownerCtrl;
	}

	public Tab getMembersTab() {
		return familiesTab;
	}

	public void setMembersTab(Tab membersTab) {
		this.familiesTab = membersTab;
	}

	public Family getSelectedFamily() {
		return selectedFamily;
	}

	public void setSelectedFamily(Family selectedFamily) {
		this.selectedFamily = selectedFamily;
	}
}
