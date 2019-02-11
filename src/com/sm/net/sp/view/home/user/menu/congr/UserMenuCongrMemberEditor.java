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
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class UserMenuCongrMemberEditor {

	@FXML
	private TabPane memberTabPane;

	@FXML
	private Tab memberPersonalTab;

	@FXML
	private Label titleLabel;

	@FXML
	private Label surnameLabel;

	@FXML
	private TextField surnameTextField;

	@FXML
	private Label nameLabel;

	@FXML
	private TextField nameTextField;

	@FXML
	private Label nameShortLabel;

	@FXML
	private TextField nameShortTextField;

	@FXML
	private Label genderLabel;

	@FXML
	private CheckBox genderMaleCheckBox;

	@FXML
	private CheckBox genderFemaleCheckBox;

	@FXML
	private Tab memberAppointmentAndPrivilegeTab;

	@FXML
	private Label generalLabel;
	@FXML
	private CheckBox studentCheckBox;
	@FXML
	private CheckBox unbaptizedPublisherCheckBox;
	@FXML
	private CheckBox baptizedPublisherCheckBox;

	@FXML
	private Tab studentTab;

	@FXML
	private CheckBox bibleReadingCheckBox;
	@FXML
	private CheckBox initialCallCheckBox;
	@FXML
	private CheckBox returnVisitCheckBox;
	@FXML
	private CheckBox bibleStudyCheckBox;
	@FXML
	private CheckBox talkCheckBox;

	@FXML
	private Tab privilegeTab;

	@FXML
	private Label soundSystemLabel;
	@FXML
	private CheckBox microphoneHandlerMidweekCheckBox;
	@FXML
	private CheckBox MicrophoneHandlerWeekendCheckBox;
	@FXML
	private CheckBox soundSystemMixerMidweekCheckBox;
	@FXML
	private CheckBox soundSystemMixerWeekendCheckBox;
	@FXML
	private CheckBox soundSystemPCMidweekCheckBox;
	@FXML
	private CheckBox soundSystemPCWeekendCheckBox;

	@FXML
	private Label readerLabel;
	@FXML
	private CheckBox readerCongregationBibleStudyCheckBox;
	@FXML
	private CheckBox readerWatchtowerCheckBox;

	@FXML
	private Label attendantLabel;
	@FXML
	private CheckBox attendantMidweekCheckBox;
	@FXML
	private CheckBox attendantWeekendCheckBox;

	@FXML
	private Tab appointmentTab;

	@FXML
	private CheckBox ministerialServantCheckBox;
	@FXML
	private CheckBox elderCheckBox;
	@FXML
	private CheckBox regularPioneerCheckBox;
	@FXML
	private CheckBox specialPioneerCheckBox;

	@FXML
	private Tab onthersTab;

	@FXML
	private CheckBox markedCheckBox;
	@FXML
	private CheckBox disfellowshippedCheckBox;

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

		memberTabPane.getStyleClass().add("tabPaneStyle2");
		memberPersonalTab.getStyleClass().add("tabStyle1");
		titleLabel.getStyleClass().add("labelStyle2");
		surnameLabel.getStyleClass().add("labelStyle1");
		surnameTextField.getStyleClass().add("textFieldStyle1");
		nameLabel.getStyleClass().add("labelStyle1");
		nameTextField.getStyleClass().add("textFieldStyle1");
		nameShortLabel.getStyleClass().add("labelStyle1");
		nameShortTextField.getStyleClass().add("textFieldStyle1");
		genderLabel.getStyleClass().add("labelStyle1");
		genderMaleCheckBox.getStyleClass().add("checkBoxStyle1");
		genderFemaleCheckBox.getStyleClass().add("checkBoxStyle1");
		saveButton.getStyleClass().add("buttonStyle1");

		memberAppointmentAndPrivilegeTab.getStyleClass().add("tabStyle1");
		generalLabel.getStyleClass().add("labelStyle2");
		studentCheckBox.getStyleClass().add("checkBoxStyle1");
		unbaptizedPublisherCheckBox.getStyleClass().add("checkBoxStyle1");
		baptizedPublisherCheckBox.getStyleClass().add("checkBoxStyle1");

		studentTab.getStyleClass().add("tabStyle1");
		bibleReadingCheckBox.getStyleClass().add("checkBoxStyle1");
		initialCallCheckBox.getStyleClass().add("checkBoxStyle1");
		returnVisitCheckBox.getStyleClass().add("checkBoxStyle1");
		bibleStudyCheckBox.getStyleClass().add("checkBoxStyle1");
		talkCheckBox.getStyleClass().add("checkBoxStyle1");

		privilegeTab.getStyleClass().add("tabStyle1");
		soundSystemLabel.getStyleClass().add("labelStyle2");
		microphoneHandlerMidweekCheckBox.getStyleClass().add("checkBoxStyle1");
		MicrophoneHandlerWeekendCheckBox.getStyleClass().add("checkBoxStyle1");
		soundSystemMixerMidweekCheckBox.getStyleClass().add("checkBoxStyle1");
		soundSystemMixerWeekendCheckBox.getStyleClass().add("checkBoxStyle1");
		soundSystemPCMidweekCheckBox.getStyleClass().add("checkBoxStyle1");
		soundSystemPCWeekendCheckBox.getStyleClass().add("checkBoxStyle1");

		readerLabel.getStyleClass().add("labelStyle2");
		readerCongregationBibleStudyCheckBox.getStyleClass().add("checkBoxStyle1");
		readerWatchtowerCheckBox.getStyleClass().add("checkBoxStyle1");

		attendantLabel.getStyleClass().add("labelStyle2");
		attendantMidweekCheckBox.getStyleClass().add("checkBoxStyle1");
		attendantWeekendCheckBox.getStyleClass().add("checkBoxStyle1");

		appointmentTab.getStyleClass().add("tabStyle1");

		ministerialServantCheckBox.getStyleClass().add("checkBoxStyle1");
		elderCheckBox.getStyleClass().add("checkBoxStyle1");
		regularPioneerCheckBox.getStyleClass().add("checkBoxStyle1");
		specialPioneerCheckBox.getStyleClass().add("checkBoxStyle1");

		onthersTab.getStyleClass().add("tabStyle1");

		markedCheckBox.getStyleClass().add("checkBoxStyle1");
		disfellowshippedCheckBox.getStyleClass().add("checkBoxStyle1");
	}

	public void objectInitialize() {
		initValue();
		viewUpdate();
		listeners();
	}

	private void initValue() {
		if (selectedMember != null) {

			surnameTextField.setText(selectedMember.getSpInf2Decrypted());
			nameTextField.setText(selectedMember.getSpInf1Decrypted());
			nameShortTextField.setText(selectedMember.getSpInf3Decrypted());

			if (selectedMember.getSpInf4() == 0)
				genderMaleCheckBox.setSelected(true);
			else
				genderFemaleCheckBox.setSelected(true);

		} else
			this.genderMaleCheckBox.setSelected(true);
	}

	private void listeners() {
		listenerNameTextField();
		listenerGenderMaleCheckBox();
		listenerGenderFemaleCheckBox();
		listenerSaveButton();
	}

	private void listenerSaveButton() {
		saveButton.setOnAction(event -> saveMember());
	}

	private void saveMember() {

		if (checkFields()) {

			String spInf1 = Crypt.encrypt(nameTextField.getText(), settings.getDatabaseSecretKey());
			String spInf2 = Crypt.encrypt(surnameTextField.getText(), settings.getDatabaseSecretKey());
			String spInf3 = Crypt.encrypt(nameShortTextField.getText(), settings.getDatabaseSecretKey());
			String spInf4 = getSpInf4();

			if (selectedMember != null)
				editMember(spInf1, spInf2, spInf3, spInf4);
			else
				newMember(spInf1, spInf2, spInf3, spInf4);
		} else
			new AlertDesigner(language.getStringWithNewLine("TEXT0004"), ownerStage, AlertType.ERROR,
					Meta.Application.getFullTitle(), Meta.Resources.ICON).show();
	}

	private void newMember(String spInf1, String spInf2, String spInf3, String spInf4) {
		Actions.insertMember(spInf1, spInf2, spInf3, spInf4, "-1", settings, ownerStage, congrTabPane, newMemberTab,
				membersTab, ownerCtrl);
	}

	private void editMember(String spInf1, String spInf2, String spInf3, String spInf4) {
		Actions.updateMember(String.valueOf(selectedMember.getSpMemberID()), spInf1, spInf2, spInf3, spInf4, settings,
				ownerStage, congrTabPane, newMemberTab, membersTab, ownerCtrl);
	}

	private String getSpInf4() {
		return genderMaleCheckBox.isSelected() ? "0" : "1";
	}

	private boolean checkFields() {

		boolean status = true;

		if (surnameTextField.getText().isEmpty())
			status = false;

		if (status)
			if (nameTextField.getText().isEmpty())
				status = false;

		if (status)
			if (nameShortTextField.getText().isEmpty())
				status = false;

		boolean male = genderMaleCheckBox.isSelected();
		boolean female = genderFemaleCheckBox.isSelected();

		if ((male && female) || (!male && !female))
			status = false;

		return status;
	}

	private void listenerGenderMaleCheckBox() {
		this.genderMaleCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue)
				this.genderFemaleCheckBox.setSelected(false);
			else
				this.genderFemaleCheckBox.setSelected(true);
		});
	}

	private void listenerGenderFemaleCheckBox() {
		this.genderFemaleCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue)
				this.genderMaleCheckBox.setSelected(false);
			else
				this.genderMaleCheckBox.setSelected(true);
		});
	}

	private void listenerNameTextField() {

		nameTextField.focusedProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue)
				if (nameShortTextField.getText().isEmpty()) {
					if (!nameTextField.getText().isEmpty())
						nameShortTextField.setText(nameTextField.getText(0, 1) + ".");
				} else if (nameTextField.getText().isEmpty())
					nameShortTextField.setText("");
		});

	}

	private void viewUpdate() {

		this.language = settings.getLanguage();

		memberPersonalTab.setText(language.getString("TEXT0016"));
		memberPersonalTab.setGraphic(new ImageView(Meta.Resources.MEMBER_PERSONAL_INFO));

		if (selectedMember != null)
			titleLabel.setText(language.getString("TEXT0023"));
		else
			titleLabel.setText(language.getString("TEXT0017"));

		surnameLabel.setText(language.getString("TEXT0013"));
		nameLabel.setText(language.getString("TEXT0014"));
		nameShortLabel.setText(language.getString("TEXT0018"));
		genderLabel.setText(language.getString("TEXT0019"));
		genderMaleCheckBox.setText(language.getString("TEXT0020"));
		genderFemaleCheckBox.setText(language.getString("TEXT0021"));

		saveButton.setGraphic(new ImageView(Meta.Resources.SAVE));
		saveButton.setText(language.getString("TEXT0022"));

		memberAppointmentAndPrivilegeTab.setText(language.getString("TEXT0042"));
		generalLabel.setText(language.getString("TEXT0043"));
		studentCheckBox.setText(language.getString("TEXT0044"));
		unbaptizedPublisherCheckBox.setText(language.getString("TEXT0045"));
		baptizedPublisherCheckBox.setText(language.getString("TEXT0046"));

		studentTab.setText(language.getString("TEXT0044"));
		bibleReadingCheckBox.setText(language.getString("TEXT0047"));
		initialCallCheckBox.setText(language.getString("TEXT0048"));
		returnVisitCheckBox.setText(language.getString("TEXT0049"));
		bibleStudyCheckBox.setText(language.getString("TEXT0050"));
		talkCheckBox.setText(language.getString("TEXT0051"));

		privilegeTab.setText(language.getString("TEXT0052"));
		soundSystemLabel.setText(language.getString("TEXT0053"));
		microphoneHandlerMidweekCheckBox.setText(language.getString("TEXT0054"));
		MicrophoneHandlerWeekendCheckBox.setText(language.getString("TEXT0055"));
		soundSystemMixerMidweekCheckBox.setText(language.getString("TEXT0056"));
		soundSystemMixerWeekendCheckBox.setText(language.getString("TEXT0057"));
		soundSystemPCMidweekCheckBox.setText(language.getString("TEXT0058"));
		soundSystemPCWeekendCheckBox.setText(language.getString("TEXT0059"));

		readerLabel.setText(language.getString("TEXT0060"));
		readerCongregationBibleStudyCheckBox.setText(language.getString("TEXT0061"));
		readerWatchtowerCheckBox.setText(language.getString("TEXT0062"));

		attendantLabel.setText(language.getString("TEXT0063"));
		attendantMidweekCheckBox.setText(language.getString("TEXT0064"));
		attendantWeekendCheckBox.setText(language.getString("TEXT0065"));

		appointmentTab.setText(language.getString("TEXT0066"));

		ministerialServantCheckBox.setText(language.getString("TEXT0067"));
		elderCheckBox.setText(language.getString("TEXT0068"));
		regularPioneerCheckBox.setText(language.getString("TEXT0069"));
		specialPioneerCheckBox.setText(language.getString("TEXT0070"));

		onthersTab.setText(language.getString("TEXT0071"));

		markedCheckBox.setText(language.getString("TEXT0072"));
		disfellowshippedCheckBox.setText(language.getString("TEXT0073"));
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
