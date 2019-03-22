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
	private Tab assignmentsTab;

	@FXML
	private Label treasuresLabel;
	@FXML
	private Label ministryLabel;
	@FXML
	private Label christiansLabel;
	@FXML
	private CheckBox treasuresTalkCheckBox;
	@FXML
	private CheckBox diggingCheckBox;
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
	private CheckBox christiansCheckBox;

	@FXML
	private Tab privilegeTab;

	@FXML
	private Label conductorLabel;
	@FXML
	private Label soundSystemLabel;
	@FXML
	private CheckBox presidentCheckBox;
	@FXML
	private CheckBox bibleStudyCongregationCheckBox;
	@FXML
	private CheckBox watchtowerStudyCheckBox;
	@FXML
	private CheckBox microphoneHandlerMidweekCheckBox;
	@FXML
	private CheckBox microphoneHandlerWeekendCheckBox;
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

		assignmentsTab.getStyleClass().add("tabStyle1");
		treasuresLabel.getStyleClass().add("labelStyle2");
		ministryLabel.getStyleClass().add("labelStyle2");
		christiansLabel.getStyleClass().add("labelStyle2");
		treasuresTalkCheckBox.getStyleClass().add("checkBoxStyle1");
		diggingCheckBox.getStyleClass().add("checkBoxStyle1");
		bibleReadingCheckBox.getStyleClass().add("checkBoxStyle1");
		initialCallCheckBox.getStyleClass().add("checkBoxStyle1");
		returnVisitCheckBox.getStyleClass().add("checkBoxStyle1");
		bibleStudyCheckBox.getStyleClass().add("checkBoxStyle1");
		talkCheckBox.getStyleClass().add("checkBoxStyle1");
		christiansCheckBox.getStyleClass().add("checkBoxStyle1");

		privilegeTab.getStyleClass().add("tabStyle1");
		conductorLabel.getStyleClass().add("labelStyle2");
		soundSystemLabel.getStyleClass().add("labelStyle2");
		presidentCheckBox.getStyleClass().add("checkBoxStyle1");
		bibleStudyCongregationCheckBox.getStyleClass().add("checkBoxStyle1");
		watchtowerStudyCheckBox.getStyleClass().add("checkBoxStyle1");
		microphoneHandlerMidweekCheckBox.getStyleClass().add("checkBoxStyle1");
		microphoneHandlerWeekendCheckBox.getStyleClass().add("checkBoxStyle1");
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

			setCheckBoxes();

		} else
			this.genderMaleCheckBox.setSelected(true);
	}

	private void setCheckBoxes() {

		this.studentCheckBox.setSelected((selectedMember.getSpInf6() == 1));
		this.unbaptizedPublisherCheckBox.setSelected((selectedMember.getSpInf7() == 1));
		this.baptizedPublisherCheckBox.setSelected((selectedMember.getSpInf8() == 1));

		this.ministerialServantCheckBox.setSelected((selectedMember.getSpInf9() == 1));
		this.elderCheckBox.setSelected((selectedMember.getSpInf10() == 1));
		this.regularPioneerCheckBox.setSelected((selectedMember.getSpInf11() == 1));
		this.specialPioneerCheckBox.setSelected((selectedMember.getSpInf12() == 1));

		this.bibleReadingCheckBox.setSelected((selectedMember.getSpInf13() == 1));
		this.initialCallCheckBox.setSelected((selectedMember.getSpInf14() == 1));
		this.returnVisitCheckBox.setSelected((selectedMember.getSpInf15() == 1));
		this.bibleStudyCheckBox.setSelected((selectedMember.getSpInf16() == 1));
		this.talkCheckBox.setSelected((selectedMember.getSpInf17() == 1));

		this.markedCheckBox.setSelected((selectedMember.getSpInf18() == 1));
		this.disfellowshippedCheckBox.setSelected((selectedMember.getSpInf19() == 1));

		this.microphoneHandlerMidweekCheckBox.setSelected((selectedMember.getSpInf20() == 1));
		this.microphoneHandlerWeekendCheckBox.setSelected((selectedMember.getSpInf21() == 1));
		this.soundSystemMixerMidweekCheckBox.setSelected((selectedMember.getSpInf22() == 1));
		this.soundSystemMixerWeekendCheckBox.setSelected((selectedMember.getSpInf23() == 1));
		this.soundSystemPCMidweekCheckBox.setSelected((selectedMember.getSpInf24() == 1));
		this.soundSystemPCWeekendCheckBox.setSelected((selectedMember.getSpInf25() == 1));

		this.readerCongregationBibleStudyCheckBox.setSelected((selectedMember.getSpInf26() == 1));
		this.readerWatchtowerCheckBox.setSelected((selectedMember.getSpInf27() == 1));

		this.attendantMidweekCheckBox.setSelected((selectedMember.getSpInf28() == 1));
		this.attendantWeekendCheckBox.setSelected((selectedMember.getSpInf29() == 1));
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
			String spInf4 = genderMaleCheckBox.isSelected() ? "0" : "1";

			String spInf6 = !this.studentCheckBox.isSelected() ? "0" : "1";
			String spInf7 = !this.unbaptizedPublisherCheckBox.isSelected() ? "0" : "1";
			String spInf8 = !this.baptizedPublisherCheckBox.isSelected() ? "0" : "1";

			String spInf9 = !this.ministerialServantCheckBox.isSelected() ? "0" : "1";
			String spInf10 = !this.elderCheckBox.isSelected() ? "0" : "1";
			String spInf11 = !this.regularPioneerCheckBox.isSelected() ? "0" : "1";
			String spInf12 = !this.specialPioneerCheckBox.isSelected() ? "0" : "1";

			String spInf13 = !this.bibleReadingCheckBox.isSelected() ? "0" : "1";
			String spInf14 = !this.initialCallCheckBox.isSelected() ? "0" : "1";
			String spInf15 = !this.returnVisitCheckBox.isSelected() ? "0" : "1";
			String spInf16 = !this.bibleStudyCheckBox.isSelected() ? "0" : "1";
			String spInf17 = !this.talkCheckBox.isSelected() ? "0" : "1";

			String spInf18 = !this.markedCheckBox.isSelected() ? "0" : "1";
			String spInf19 = !this.disfellowshippedCheckBox.isSelected() ? "0" : "1";

			String spInf20 = !this.microphoneHandlerMidweekCheckBox.isSelected() ? "0" : "1";
			String spInf21 = !this.microphoneHandlerWeekendCheckBox.isSelected() ? "0" : "1";
			String spInf22 = !this.soundSystemMixerMidweekCheckBox.isSelected() ? "0" : "1";
			String spInf23 = !this.soundSystemMixerWeekendCheckBox.isSelected() ? "0" : "1";
			String spInf24 = !this.soundSystemPCMidweekCheckBox.isSelected() ? "0" : "1";
			String spInf25 = !this.soundSystemPCWeekendCheckBox.isSelected() ? "0" : "1";

			String spInf26 = !this.readerCongregationBibleStudyCheckBox.isSelected() ? "0" : "1";
			String spInf27 = !this.readerWatchtowerCheckBox.isSelected() ? "0" : "1";

			String spInf28 = !this.attendantMidweekCheckBox.isSelected() ? "0" : "1";
			String spInf29 = !this.attendantWeekendCheckBox.isSelected() ? "0" : "1";

			if (selectedMember != null)
				editMember(spInf1, spInf2, spInf3, spInf4, spInf6, spInf7, spInf8, spInf9, spInf10, spInf11, spInf12,
						spInf13, spInf14, spInf15, spInf16, spInf17, spInf18, spInf19, spInf20, spInf21, spInf22,
						spInf23, spInf24, spInf25, spInf26, spInf27, spInf28, spInf29);
			else
				newMember(spInf1, spInf2, spInf3, spInf4, spInf6, spInf7, spInf8, spInf9, spInf10, spInf11, spInf12,
						spInf13, spInf14, spInf15, spInf16, spInf17, spInf18, spInf19, spInf20, spInf21, spInf22,
						spInf23, spInf24, spInf25, spInf26, spInf27, spInf28, spInf29);
		} else
			new AlertDesigner(language.getStringWithNewLine("TEXT0004"), ownerStage, AlertType.ERROR,
					Meta.Application.getFullTitle(), Meta.Resources.ICON).show();
	}

	private void newMember(String spInf1, String spInf2, String spInf3, String spInf4, String spInf6, String spInf7,
			String spInf8, String spInf9, String spInf10, String spInf11, String spInf12, String spInf13,
			String spInf14, String spInf15, String spInf16, String spInf17, String spInf18, String spInf19,
			String spInf20, String spInf21, String spInf22, String spInf23, String spInf24, String spInf25,
			String spInf26, String spInf27, String spInf28, String spInf29) {

		Actions.insertMember(spInf1, spInf2, spInf3, spInf4, "-1", spInf6, spInf7, spInf8, spInf9, spInf10, spInf11,
				spInf12, spInf13, spInf14, spInf15, spInf16, spInf17, spInf18, spInf19, spInf20, spInf21, spInf22,
				spInf23, spInf24, spInf25, spInf26, spInf27, spInf28, spInf29, settings, ownerStage, congrTabPane,
				newMemberTab, membersTab, ownerCtrl);
	}

	private void editMember(String spInf1, String spInf2, String spInf3, String spInf4, String spInf6, String spInf7,
			String spInf8, String spInf9, String spInf10, String spInf11, String spInf12, String spInf13,
			String spInf14, String spInf15, String spInf16, String spInf17, String spInf18, String spInf19,
			String spInf20, String spInf21, String spInf22, String spInf23, String spInf24, String spInf25,
			String spInf26, String spInf27, String spInf28, String spInf29) {

		Actions.updateMember(String.valueOf(selectedMember.getSpMemberID()), spInf1, spInf2, spInf3, spInf4, spInf6,
				spInf7, spInf8, spInf9, spInf10, spInf11, spInf12, spInf13, spInf14, spInf15, spInf16, spInf17, spInf18,
				spInf19, spInf20, spInf21, spInf22, spInf23, spInf24, spInf25, spInf26, spInf27, spInf28, spInf29,
				settings, ownerStage, congrTabPane, newMemberTab, membersTab, ownerCtrl);
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
		memberAppointmentAndPrivilegeTab.setGraphic(new ImageView(Meta.Resources.MEMBER_PRIVILEGES));

		generalLabel.setText(language.getString("TEXT0043"));
		studentCheckBox.setText(language.getString("TEXT0044"));
		unbaptizedPublisherCheckBox.setText(language.getString("TEXT0045"));
		baptizedPublisherCheckBox.setText(language.getString("TEXT0046"));

		assignmentsTab.setText(language.getString("TEXT0104"));
		treasuresLabel.setText(language.getString("TEXT0080"));
		ministryLabel.setText(language.getString("TEXT0081"));
		christiansLabel.setText(language.getString("TEXT0082"));
		treasuresTalkCheckBox.setText(language.getString("TEXT0051"));
		diggingCheckBox.setText(language.getString("TEXT0088"));
		bibleReadingCheckBox.setText(language.getString("TEXT0047"));
		initialCallCheckBox.setText(language.getString("TEXT0048"));
		returnVisitCheckBox.setText(language.getString("TEXT0049"));
		bibleStudyCheckBox.setText(language.getString("TEXT0050"));
		talkCheckBox.setText(language.getString("TEXT0051"));
		christiansCheckBox.setText(language.getString("TEXT0082"));

		privilegeTab.setText(language.getString("TEXT0052"));
		conductorLabel.setText(language.getString("TEXT0105"));
		soundSystemLabel.setText(language.getString("TEXT0053"));
		presidentCheckBox.setText(language.getString("TEXT0083"));
		bibleStudyCongregationCheckBox.setText(language.getString("TEXT0061"));
		watchtowerStudyCheckBox.setText(language.getString("TEXT0062"));
		microphoneHandlerMidweekCheckBox.setText(language.getString("TEXT0054"));
		microphoneHandlerWeekendCheckBox.setText(language.getString("TEXT0055"));
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
