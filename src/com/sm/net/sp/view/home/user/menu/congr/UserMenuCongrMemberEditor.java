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
	private Label singlenessLabel;
	@FXML
	private TextField singlenessTextField;

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
	private Label privilegeLabel;
	@FXML
	private Label prayStartLabel;
	@FXML
	private Label prayEndLabel;
	@FXML
	private Label soundSystemMicrophoneHandlerLabel;
	@FXML
	private Label soundSystemMixerLabel;
	@FXML
	private Label soundSystemPCLabel;
	@FXML
	private Label meetingLabel;
	@FXML
	private Label meetingMidweekLabel;
	@FXML
	private Label meetingWeekendLabel;
	@FXML
	private CheckBox presidentMidweekCheckBox;
	@FXML
	private CheckBox presidentWeekendCheckBox;
	@FXML
	private CheckBox bibleStudyCongregationCheckBox;
	@FXML
	private CheckBox watchtowerStudyCheckBox;
	@FXML
	private CheckBox watchtowerSubstituteStudyCheckBox;
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
	private CheckBox prayStartMidweekCheckBox;
	@FXML
	private CheckBox prayEndMidweekCheckBox;
	@FXML
	private CheckBox prayEndWeekendCheckBox;
	@FXML
	private Label readerLabel;
	@FXML
	private CheckBox readerCongregationBibleStudyCheckBox;
	@FXML
	private CheckBox readerWatchtowerCheckBox;
	@FXML
	private Label publicSpeakerLabel;
	@FXML
	private CheckBox publicSpeakerInternCheckBox;
	@FXML
	private CheckBox publicSpeakerExternCheckBox;
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
	private CheckBox inactiveCheckBox;
	@FXML
	private CheckBox markedCheckBox;
	@FXML
	private CheckBox disfellowshippedCheckBox;

	@FXML
	private Tab memberContactsTab;

	@FXML
	private Label smartphoneLabel;
	@FXML
	private TextField smartphoneTextField;
	@FXML
	private Label emailLabel;
	@FXML
	private TextField emailTextField;

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
		singlenessLabel.getStyleClass().add("labelStyle1");
		singlenessTextField.getStyleClass().add("textFieldStyle1");
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
		soundSystemLabel.getStyleClass().add("labelStyle1");
		privilegeLabel.getStyleClass().add("labelStyle2");
		prayStartLabel.getStyleClass().add("labelStyle1");
		prayEndLabel.getStyleClass().add("labelStyle1");
		soundSystemMicrophoneHandlerLabel.getStyleClass().add("labelStyle1");
		soundSystemMixerLabel.getStyleClass().add("labelStyle1");
		soundSystemPCLabel.getStyleClass().add("labelStyle1");
		meetingLabel.getStyleClass().add("labelStyle2");
		meetingMidweekLabel.getStyleClass().add("labelStyle2");
		meetingWeekendLabel.getStyleClass().add("labelStyle2");
		presidentMidweekCheckBox.getStyleClass().add("checkBoxStyle1");
		bibleStudyCongregationCheckBox.getStyleClass().add("checkBoxStyle1");
		watchtowerStudyCheckBox.getStyleClass().add("checkBoxStyle1");
		watchtowerSubstituteStudyCheckBox.getStyleClass().add("checkBoxStyle1");
		microphoneHandlerMidweekCheckBox.getStyleClass().add("checkBoxStyle2");
		microphoneHandlerWeekendCheckBox.getStyleClass().add("checkBoxStyle2");
		soundSystemMixerMidweekCheckBox.getStyleClass().add("checkBoxStyle2");
		soundSystemMixerWeekendCheckBox.getStyleClass().add("checkBoxStyle2");
		soundSystemPCMidweekCheckBox.getStyleClass().add("checkBoxStyle2");
		soundSystemPCWeekendCheckBox.getStyleClass().add("checkBoxStyle2");
		prayStartMidweekCheckBox.getStyleClass().add("checkBoxStyle2");
		presidentWeekendCheckBox.getStyleClass().add("checkBoxStyle2");
		prayEndMidweekCheckBox.getStyleClass().add("checkBoxStyle2");
		prayEndWeekendCheckBox.getStyleClass().add("checkBoxStyle2");
		readerLabel.getStyleClass().add("labelStyle2");
		readerCongregationBibleStudyCheckBox.getStyleClass().add("checkBoxStyle1");
		readerWatchtowerCheckBox.getStyleClass().add("checkBoxStyle1");
		publicSpeakerLabel.getStyleClass().add("labelStyle2");
		publicSpeakerInternCheckBox.getStyleClass().add("checkBoxStyle1");
		publicSpeakerExternCheckBox.getStyleClass().add("checkBoxStyle1");
		attendantLabel.getStyleClass().add("labelStyle1");
		attendantMidweekCheckBox.getStyleClass().add("checkBoxStyle2");
		attendantWeekendCheckBox.getStyleClass().add("checkBoxStyle2");

		appointmentTab.getStyleClass().add("tabStyle1");

		ministerialServantCheckBox.getStyleClass().add("checkBoxStyle1");
		elderCheckBox.getStyleClass().add("checkBoxStyle1");
		regularPioneerCheckBox.getStyleClass().add("checkBoxStyle1");
		specialPioneerCheckBox.getStyleClass().add("checkBoxStyle1");

		onthersTab.getStyleClass().add("tabStyle1");

		inactiveCheckBox.getStyleClass().add("checkBoxStyle1");
		markedCheckBox.getStyleClass().add("checkBoxStyle1");
		disfellowshippedCheckBox.getStyleClass().add("checkBoxStyle1");

		memberContactsTab.getStyleClass().add("tabStyle1");

		smartphoneLabel.getStyleClass().add("labelStyle3");
		smartphoneTextField.getStyleClass().add("textFieldStyle1");
		emailLabel.getStyleClass().add("labelStyle3");
		emailTextField.getStyleClass().add("textFieldStyle1");
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
			singlenessTextField.setText(selectedMember.getSpInf39Decrypted());
			smartphoneTextField.setText(selectedMember.getSpInf40Decrypted());
			emailTextField.setText(selectedMember.getSpInf41Decrypted());

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

		this.treasuresTalkCheckBox.setSelected((selectedMember.getSpInf30() == 1));
		this.diggingCheckBox.setSelected((selectedMember.getSpInf31() == 1));
		this.christiansCheckBox.setSelected((selectedMember.getSpInf32() == 1));
		this.presidentMidweekCheckBox.setSelected((selectedMember.getSpInf33() == 1));
		this.prayStartMidweekCheckBox.setSelected((selectedMember.getSpInf34() == 1));
		this.prayEndMidweekCheckBox.setSelected((selectedMember.getSpInf35() == 1));
		this.presidentWeekendCheckBox.setSelected((selectedMember.getSpInf36() == 1));
		this.prayEndWeekendCheckBox.setSelected((selectedMember.getSpInf37() == 1));
		this.inactiveCheckBox.setSelected((selectedMember.getSpInf38() == 1));

		this.bibleStudyCongregationCheckBox.setSelected((selectedMember.getSpInf42() == 1));
		this.watchtowerStudyCheckBox.setSelected((selectedMember.getSpInf43() == 1));

		this.watchtowerSubstituteStudyCheckBox.setSelected((selectedMember.getSpInf44() == 1));
		this.publicSpeakerInternCheckBox.setSelected((selectedMember.getSpInf45() == 1));
		this.publicSpeakerExternCheckBox.setSelected((selectedMember.getSpInf46() == 1));
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

			String spInf30 = !this.treasuresTalkCheckBox.isSelected() ? "0" : "1";
			String spInf31 = !this.diggingCheckBox.isSelected() ? "0" : "1";
			String spInf32 = !this.christiansCheckBox.isSelected() ? "0" : "1";
			String spInf33 = !this.presidentMidweekCheckBox.isSelected() ? "0" : "1";
			String spInf34 = !this.prayStartMidweekCheckBox.isSelected() ? "0" : "1";
			String spInf35 = !this.prayEndMidweekCheckBox.isSelected() ? "0" : "1";
			String spInf36 = !this.presidentWeekendCheckBox.isSelected() ? "0" : "1";
			String spInf37 = !this.prayEndWeekendCheckBox.isSelected() ? "0" : "1";
			String spInf38 = !this.inactiveCheckBox.isSelected() ? "0" : "1";
			String spInf39 = Crypt.encrypt(singlenessTextField.getText(), settings.getDatabaseSecretKey());
			String spInf40 = Crypt.encrypt(smartphoneTextField.getText(), settings.getDatabaseSecretKey());
			String spInf41 = Crypt.encrypt(emailTextField.getText(), settings.getDatabaseSecretKey());
			String spInf42 = !this.bibleStudyCongregationCheckBox.isSelected() ? "0" : "1";
			String spInf43 = !this.watchtowerStudyCheckBox.isSelected() ? "0" : "1";
			String spInf44 = !this.watchtowerSubstituteStudyCheckBox.isSelected() ? "0" : "1";
			String spInf45 = !this.publicSpeakerInternCheckBox.isSelected() ? "0" : "1";
			String spInf46 = !this.publicSpeakerExternCheckBox.isSelected() ? "0" : "1";

			if (selectedMember != null)
				editMember(spInf1, spInf2, spInf3, spInf4, spInf6, spInf7, spInf8, spInf9, spInf10, spInf11, spInf12,
						spInf13, spInf14, spInf15, spInf16, spInf17, spInf18, spInf19, spInf20, spInf21, spInf22,
						spInf23, spInf24, spInf25, spInf26, spInf27, spInf28, spInf29, spInf30, spInf31, spInf32,
						spInf33, spInf34, spInf35, spInf36, spInf37, spInf38, spInf39, spInf40, spInf41, spInf42,
						spInf43, spInf44, spInf45, spInf46);
			else
				newMember(spInf1, spInf2, spInf3, spInf4, spInf6, spInf7, spInf8, spInf9, spInf10, spInf11, spInf12,
						spInf13, spInf14, spInf15, spInf16, spInf17, spInf18, spInf19, spInf20, spInf21, spInf22,
						spInf23, spInf24, spInf25, spInf26, spInf27, spInf28, spInf29, spInf30, spInf31, spInf32,
						spInf33, spInf34, spInf35, spInf36, spInf37, spInf38, spInf39, spInf40, spInf41, spInf42,
						spInf43, spInf44, spInf45, spInf46);
		} else
			new AlertDesigner(language.getStringWithNewLine("TEXT0004"), ownerStage, AlertType.ERROR,
					Meta.Application.getFullTitle(), Meta.Resources.ICON).show();
	}

	private void newMember(String spInf1, String spInf2, String spInf3, String spInf4, String spInf6, String spInf7,
			String spInf8, String spInf9, String spInf10, String spInf11, String spInf12, String spInf13,
			String spInf14, String spInf15, String spInf16, String spInf17, String spInf18, String spInf19,
			String spInf20, String spInf21, String spInf22, String spInf23, String spInf24, String spInf25,
			String spInf26, String spInf27, String spInf28, String spInf29, String spInf30, String spInf31,
			String spInf32, String spInf33, String spInf34, String spInf35, String spInf36, String spInf37,
			String spInf38, String spInf39, String spInf40, String spInf41, String spInf42, String spInf43,
			String spInf44, String spInf45, String spInf46) {

		Actions.insertMember(spInf1, spInf2, spInf3, spInf4, "-1", spInf6, spInf7, spInf8, spInf9, spInf10, spInf11,
				spInf12, spInf13, spInf14, spInf15, spInf16, spInf17, spInf18, spInf19, spInf20, spInf21, spInf22,
				spInf23, spInf24, spInf25, spInf26, spInf27, spInf28, spInf29, spInf30, spInf31, spInf32, spInf33,
				spInf34, spInf35, spInf36, spInf37, spInf38, spInf39, spInf40, spInf41, spInf42, spInf43, spInf44,
				spInf45, spInf46, settings, ownerStage, congrTabPane, newMemberTab, membersTab, ownerCtrl);
	}

	private void editMember(String spInf1, String spInf2, String spInf3, String spInf4, String spInf6, String spInf7,
			String spInf8, String spInf9, String spInf10, String spInf11, String spInf12, String spInf13,
			String spInf14, String spInf15, String spInf16, String spInf17, String spInf18, String spInf19,
			String spInf20, String spInf21, String spInf22, String spInf23, String spInf24, String spInf25,
			String spInf26, String spInf27, String spInf28, String spInf29, String spInf30, String spInf31,
			String spInf32, String spInf33, String spInf34, String spInf35, String spInf36, String spInf37,
			String spInf38, String spInf39, String spInf40, String spInf41, String spInf42, String spInf43,
			String spInf44, String spInf45, String spInf46) {

		Actions.updateMember(String.valueOf(selectedMember.getSpMemberID()), spInf1, spInf2, spInf3, spInf4, spInf6,
				spInf7, spInf8, spInf9, spInf10, spInf11, spInf12, spInf13, spInf14, spInf15, spInf16, spInf17, spInf18,
				spInf19, spInf20, spInf21, spInf22, spInf23, spInf24, spInf25, spInf26, spInf27, spInf28, spInf29,
				spInf30, spInf31, spInf32, spInf33, spInf34, spInf35, spInf36, spInf37, spInf38, spInf39, spInf40,
				spInf41, spInf42, spInf43, spInf44, spInf45, spInf46, settings, ownerStage, congrTabPane, newMemberTab,
				membersTab, ownerCtrl);
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
		singlenessLabel.setText(language.getString("TEXT0065"));
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
		privilegeLabel.setText(language.getString("TEXT0052"));
		prayStartLabel.setText(language.getString("TEXT0084"));
		prayEndLabel.setText(language.getString("TEXT0102"));
		soundSystemMicrophoneHandlerLabel.setText(language.getString("TEXT0054"));
		soundSystemMixerLabel.setText(language.getString("TEXT0056"));
		soundSystemPCLabel.setText(language.getString("TEXT0058"));

		meetingLabel.setText(language.getString("TEXT0055"));
		meetingMidweekLabel.setText(language.getString("TEXT0057"));
		meetingWeekendLabel.setText(language.getString("TEXT0059"));

		presidentMidweekCheckBox.setText(language.getString("TEXT0083"));
		presidentWeekendCheckBox.setText(language.getString("TEXT0110"));
		bibleStudyCongregationCheckBox.setText(language.getString("TEXT0061"));
		watchtowerStudyCheckBox.setText(language.getString("TEXT0062"));
		watchtowerSubstituteStudyCheckBox.setText(language.getString("TEXT0111"));
		microphoneHandlerMidweekCheckBox.setText("");
		microphoneHandlerWeekendCheckBox.setText("");
		soundSystemMixerMidweekCheckBox.setText("");
		soundSystemMixerWeekendCheckBox.setText("");
		soundSystemPCMidweekCheckBox.setText("");
		soundSystemPCWeekendCheckBox.setText("");
		prayStartMidweekCheckBox.setText("");
		prayEndMidweekCheckBox.setText("");
		prayEndWeekendCheckBox.setText("");
		readerLabel.setText(language.getString("TEXT0060"));
		readerCongregationBibleStudyCheckBox.setText(language.getString("TEXT0061"));
		readerWatchtowerCheckBox.setText(language.getString("TEXT0062"));
		publicSpeakerLabel.setText(language.getString("TEXT0112"));
		publicSpeakerInternCheckBox.setText(language.getString("TEXT0113"));
		publicSpeakerExternCheckBox.setText(language.getString("TEXT0114"));
		attendantLabel.setText(language.getString("TEXT0063"));
		attendantMidweekCheckBox.setText("");
		attendantWeekendCheckBox.setText("");

		appointmentTab.setText(language.getString("TEXT0066"));

		ministerialServantCheckBox.setText(language.getString("TEXT0067"));
		elderCheckBox.setText(language.getString("TEXT0068"));
		regularPioneerCheckBox.setText(language.getString("TEXT0069"));
		specialPioneerCheckBox.setText(language.getString("TEXT0070"));

		onthersTab.setText(language.getString("TEXT0071"));

		inactiveCheckBox.setText(language.getString("TEXT0064"));
		markedCheckBox.setText(language.getString("TEXT0072"));
		disfellowshippedCheckBox.setText(language.getString("TEXT0073"));

		memberContactsTab.setText(language.getString("TEXT0106"));
		memberContactsTab.setGraphic(new ImageView(Meta.Resources.MEMBER_CONTACTS));

		smartphoneLabel.setText(language.getString("TEXT0107"));
		emailLabel.setText(language.getString("TEXT0108"));
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
