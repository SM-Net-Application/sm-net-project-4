package com.sm.net.sp.view.home.user.menu.congr;

import com.sm.net.javafx.AlertDesigner;
import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.actions.Actions;
import com.sm.net.sp.model.Member;
import com.sm.net.sp.settings.Settings;
import com.sm.net.util.Crypt;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class UserMenuCongrFamilyEditor {

	@FXML
	private Label titleLabel;

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
	private Label membersLabel;

	@FXML
	private TableView<Member> membersTableView;

	@FXML
	private TableColumn<Member, Integer> memberIDTableColumn;

	@FXML
	private TableColumn<Member, String> memberSurnameTableColumn;

	@FXML
	private TableColumn<Member, String> memberNameTableColumn;

	@FXML
	private Button familyAddMember;

	@FXML
	private Button familyRemoveMember;

	@FXML
	private Label familyMembersLabel;

	@FXML
	private TableView<Member> familyMembersTableView;

	@FXML
	private TableColumn<Member, Integer> familyMemberIDTableColumn;

	@FXML
	private TableColumn<Member, String> familyMemberSurnameTableColumn;

	@FXML
	private TableColumn<Member, String> familyMemberNameTableColumn;

	@FXML
	private Button saveButton;

	private Settings settings;
	private Language language;
	private Stage ownerStage;
	private TabPane congrTabPane;
	private Tab newMemberTab;
	private Tab membersTab;
	private UserMenuCongrList ownerCtrl;
	private Member selectedMember;

	@FXML
	private void initialize() {
		styleClasses();
	}

	private void styleClasses() {

		// memberTabPane.getStyleClass().add("tabPaneStyle2");
		// memberPersonalTab.getStyleClass().add("tabStyle1");
		// titleLabel.getStyleClass().add("labelStyle2");
		// surnameLabel.getStyleClass().add("labelStyle1");
		// surnameTextField.getStyleClass().add("textFieldStyle1");
		// nameLabel.getStyleClass().add("labelStyle1");
		// nameTextField.getStyleClass().add("textFieldStyle1");
		// nameShortLabel.getStyleClass().add("labelStyle1");
		// nameShortTextField.getStyleClass().add("textFieldStyle1");
		// genderLabel.getStyleClass().add("labelStyle1");
		// genderMaleCheckBox.getStyleClass().add("checkBoxStyle1");
		// genderFemaleCheckBox.getStyleClass().add("checkBoxStyle1");
		saveButton.getStyleClass().add("buttonStyle1");
	}

	public void objectInitialize() {
		initValue();
		viewUpdate();
		listeners();
	}

	private void initValue() {

		// if (selectedMember != null) {
		//
		// surnameTextField.setText(selectedMember.getSpInf2Decrypted());
		// nameTextField.setText(selectedMember.getSpInf1Decrypted());
		// nameShortTextField.setText(selectedMember.getSpInf3Decrypted());
		//
		// if (selectedMember.getSpInf4() == 0)
		// genderMaleCheckBox.setSelected(true);
		// else
		// genderFemaleCheckBox.setSelected(true);
		//
		// } else
		// this.genderMaleCheckBox.setSelected(true);
	}

	private void listeners() {
		listenerNameTextField();
		listenerSaveButton();
	}

	private void listenerSaveButton() {
		saveButton.setOnAction(event -> saveMember());
	}

	private void saveMember() {

		// if (checkFields()) {
		//
		// String spInf1 = Crypt.encrypt(nameTextField.getText(),
		// settings.getDatabaseSecretKey());
		// String spInf2 = Crypt.encrypt(surnameTextField.getText(),
		// settings.getDatabaseSecretKey());
		// String spInf3 = Crypt.encrypt(nameShortTextField.getText(),
		// settings.getDatabaseSecretKey());
		// String spInf4 = getSpInf4();
		//
		// if (selectedMember != null)
		// editMember(spInf1, spInf2, spInf3, spInf4);
		// else
		// newMember(spInf1, spInf2, spInf3, spInf4);
		// } else
		// new AlertDesigner(language.getStringWithNewLine("TEXT0004"),
		// ownerStage, AlertType.ERROR,
		// Meta.Application.getFullTitle(), Meta.Resources.ICON).show();
	}

	private void newMember(String spInf1, String spInf2, String spInf3, String spInf4) {
		// Actions.insertMember(spInf1, spInf2, spInf3, spInf4, settings,
		// ownerStage, congrTabPane, newMemberTab,
		// membersTab, ownerCtrl);
	}

	private void editMember(String spInf1, String spInf2, String spInf3, String spInf4) {
		// Actions.updateMember(String.valueOf(selectedMember.getSpMemberID()),
		// spInf1, spInf2, spInf3, spInf4, settings,
		// ownerStage, congrTabPane, newMemberTab, membersTab, ownerCtrl);
	}

	// private String getSpInf4() {
	// return genderMaleCheckBox.isSelected() ? "0" : "1";
	// }

	private boolean checkFields() {

		boolean status = true;

		// if (surnameTextField.getText().isEmpty())
		// status = false;
		//
		// if (status)
		// if (nameTextField.getText().isEmpty())
		// status = false;
		//
		// if (status)
		// if (nameShortTextField.getText().isEmpty())
		// status = false;
		//
		// boolean male = genderMaleCheckBox.isSelected();
		// boolean female = genderFemaleCheckBox.isSelected();
		//
		// if ((male && female) || (!male && !female))
		// status = false;

		return status;
	}

	private void listenerNameTextField() {

		// nameTextField.focusedProperty().addListener((observable, oldValue,
		// newValue) -> {
		// if (!newValue)
		// if (nameShortTextField.getText().isEmpty()) {
		// if (!nameTextField.getText().isEmpty())
		// nameShortTextField.setText(nameTextField.getText(0, 1) + ".");
		// } else if (nameTextField.getText().isEmpty())
		// nameShortTextField.setText("");
		// });

	}

	private void viewUpdate() {

		this.language = settings.getLanguage();

		// memberPersonalTab.setText(language.getString("TEXT0016"));
		// memberPersonalTab.setGraphic(new
		// ImageView(Meta.Resources.MEMBER_PERSONAL_INFO));
		//
		// if (selectedMember != null)
		// titleLabel.setText(language.getString("TEXT0023"));
		// else
		// titleLabel.setText(language.getString("TEXT0017"));
		//
		// surnameLabel.setText(language.getString("TEXT0013"));
		// nameLabel.setText(language.getString("TEXT0014"));
		// nameShortLabel.setText(language.getString("TEXT0018"));
		// genderLabel.setText(language.getString("TEXT0019"));
		// genderMaleCheckBox.setText(language.getString("TEXT0020"));
		// genderFemaleCheckBox.setText(language.getString("TEXT0021"));

		saveButton.setGraphic(new ImageView(Meta.Resources.SAVE));
		saveButton.setText(language.getString("TEXT0022"));
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
		return newMemberTab;
	}

	public void setNewMemberTab(Tab newMemberTab) {
		this.newMemberTab = newMemberTab;
	}

	public UserMenuCongrList getOwnerCtrl() {
		return ownerCtrl;
	}

	public void setOwnerCtrl(UserMenuCongrList ownerCtrl) {
		this.ownerCtrl = ownerCtrl;
	}

	public Tab getMembersTab() {
		return membersTab;
	}

	public void setMembersTab(Tab membersTab) {
		this.membersTab = membersTab;
	}

	public Member getSelectedMember() {
		return selectedMember;
	}

	public void setSelectedMember(Member selectedMember) {
		this.selectedMember = selectedMember;
	}
}
