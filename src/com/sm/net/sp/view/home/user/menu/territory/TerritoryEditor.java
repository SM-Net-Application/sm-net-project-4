package com.sm.net.sp.view.home.user.menu.territory;

import java.time.LocalDate;
import java.util.HashMap;

import javax.crypto.SecretKey;

import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.actions.Actions;
import com.sm.net.sp.model.Member;
import com.sm.net.sp.settings.Settings;
import com.sm.net.sp.view.SupportPlannerView;
import com.sm.net.util.Crypt;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;

public class TerritoryEditor {

	@FXML
	private TabPane territoryTabPane;
	@FXML
	private TabPane memberInfoTabPane;

	@FXML
	private Tab territoryInfoTab;

	@FXML
	private Label territoryNumberLabel;
	@FXML
	private TextField territoryNumberTextField;
	
	@FXML
	private Label territoryNameLabel;
	@FXML
	private TextField territoryNameTextField;
	
	@FXML
	private Label territoryCoordinatesLabel;
	@FXML
	private TextField territoryCoordinatesTextField;
	
	@FXML
	private Label territoryLandLabel;
	@FXML
	private TextField territoryLandTextField;
	
	@FXML
	private Label territoryLandkreisLabel;
	@FXML
	private TextField territoryLandkreisTextField;
	
	@FXML
	private Label territoryKreisstadtLabel;
	@FXML
	private TextField territoryKreisstadtTextField;

	@FXML
	private Label territoryOrtLabel;
	@FXML
	private TextField territoryOrtTextField;

	@FXML
	private Label territoryZipCodeLabel;
	@FXML
	private TextField territoryZipCodeTextField;
	
	@FXML
	private Label territoryOrtsteilLabel;
	@FXML
	private TextField territoryOrtsteilTextField;
	
	@FXML
	private Tab memberAppointmentAndPrivilegeTab;

	@FXML
	private CheckBox studentCheckBox;
	@FXML
	private CheckBox unbaptizedPublisherCheckBox;
	@FXML
	private CheckBox baptizedPublisherCheckBox;

	@FXML
	private Tab assignmentsTab;
	@FXML
	private ScrollPane assignmentsScrollPane;

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
	private ScrollPane privilegeScrollPane;

	@FXML
	private Label conductorLabel;
	@FXML
	private Label privilegeLabel;
	@FXML
	private Label prayStartLabel;
	@FXML
	private Label prayEndLabel;
//	@FXML
//	private Label meetingLabel;
//	@FXML
//	private Label meetingMidweekLabel;
//	@FXML
//	private Label meetingWeekendLabel;
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
	private Tab memberMonitorTab;

	@FXML
	private Label monitorLabel;
	@FXML
	private TextField monitorTextField;

	@FXML
	private Button saveButton;

	@FXML
	private CheckBox anointedCheckBox;
	@FXML
	private CheckBox otherSheepCheckBox;

	@FXML
	private CheckBox excludeFromNaturalDisastersListCheckBox;

	@FXML
	private Label dateOfBaptismLabel;
	@FXML
	private DatePicker dateOfBaptismDatePicker;

	@FXML
	private TabPane memberAssignmentTabPane;

	@FXML
	private Tab prayTab;
	@FXML
	private Label prayLabel;
	@FXML
	private Label prayMidweekLabel;
	@FXML
	private Label prayWeekendLabel;

	@FXML
	private Tab audioTab;
	@FXML
	private Label audioLabel;
	@FXML
	private Label audioMidweekLabel;
	@FXML
	private Label audioWeekendLabel;
	@FXML
	private Label audioMicLabel;
	@FXML
	private Label audioPos1Label;
	@FXML
	private Label audioPos2Label;
	@FXML
	private Label audioPos3Label;

	@FXML
	private CheckBox audioMicMidweekCheckBox;
	@FXML
	private CheckBox audioMicWeekendCheckBox;
	@FXML
	private CheckBox audioPos1MidweekCheckBox;
	@FXML
	private CheckBox audioPos1WeekendCheckBox;
	@FXML
	private CheckBox audioPos2MidweekCheckBox;
	@FXML
	private CheckBox audioPos2WeekendCheckBox;
	@FXML
	private CheckBox audioPos3MidweekCheckBox;
	@FXML
	private CheckBox audioPos3WeekendCheckBox;

	@FXML
	private Tab usciereTab;
	@FXML
	private Label usciereLabel;
	@FXML
	private Label usciereMidweekLabel;
	@FXML
	private Label usciereWeekendLabel;
	@FXML
	private Label usciereZone1Label;
	@FXML
	private Label usciereZone2Label;
	@FXML
	private Label usciereZone3Label;
	@FXML
	private CheckBox usciereZone1MidweekCheckBox;
	@FXML
	private CheckBox usciereZone1WeekendCheckBox;
	@FXML
	private CheckBox usciereZone2MidweekCheckBox;
	@FXML
	private CheckBox usciereZone2WeekendCheckBox;
	@FXML
	private CheckBox usciereZone3MidweekCheckBox;
	@FXML
	private CheckBox usciereZone3WeekendCheckBox;

	@FXML
	private Tab readerTab;
	@FXML
	private Tab speakerTab;

	@FXML
	private CheckBox presidentSecondHallCheckBox;
	@FXML
	private CheckBox serviceMeetingCongrCheckBox;
	@FXML
	private CheckBox serviceMeetingGroupCheckBox;

	private Settings settings;
	private Language language;
	private Stage ownerStage;
	private TabPane parentTabPane;
	private Tab newMemberTab;
	private Tab membersTab;
	private Territory ownerCtrl;
	private Member selectedMember;
	private SupportPlannerView application;

	private boolean listenerCheckFields;

	private HashMap<String, String> configs;

	@FXML
	private void initialize() {
		styleClasses();
	}

	private void styleClasses() {

		this.territoryInfoTab.getStyleClass().add("tab_001");
		
		this.territoryTabPane.getStyleClass().add("tab_pane_003");
		this.memberInfoTabPane.getStyleClass().add("tab_pane_003");

		this.territoryNumberLabel.getStyleClass().add("label_set_001");
		this.territoryNameLabel.getStyleClass().add("label_set_001");
		this.territoryCoordinatesLabel.getStyleClass().add("label_set_001");
		this.territoryLandLabel.getStyleClass().add("label_set_001");
		this.territoryLandkreisLabel.getStyleClass().add("label_set_001");
		this.territoryKreisstadtLabel.getStyleClass().add("label_set_001");
		this.territoryOrtLabel.getStyleClass().add("label_set_001");
		this.territoryZipCodeLabel.getStyleClass().add("label_set_001");
		this.territoryOrtsteilLabel.getStyleClass().add("label_set_001");

		assignmentsScrollPane.getStyleClass().add("scroll_pane_001");
		privilegeScrollPane.getStyleClass().add("scroll_pane_001");

		memberAppointmentAndPrivilegeTab.getStyleClass().add("tab_001");
		assignmentsTab.getStyleClass().add("tab_001");
		privilegeTab.getStyleClass().add("tab_001");
		appointmentTab.getStyleClass().add("tab_001");
		onthersTab.getStyleClass().add("tab_001");
		memberContactsTab.getStyleClass().add("tab_001");
		memberMonitorTab.getStyleClass().add("tab_001");


		treasuresLabel.getStyleClass().add("label_002");
		ministryLabel.getStyleClass().add("label_002");
		christiansLabel.getStyleClass().add("label_002");
		conductorLabel.getStyleClass().add("label_002");
		privilegeLabel.getStyleClass().add("label_002");
//		meetingLabel.getStyleClass().add("label_002");
//		meetingMidweekLabel.getStyleClass().add("label_002");
//		meetingWeekendLabel.getStyleClass().add("label_002");
		readerLabel.getStyleClass().add("label_002");
		publicSpeakerLabel.getStyleClass().add("label_002");
		smartphoneLabel.getStyleClass().add("label_set_001");
		emailLabel.getStyleClass().add("label_set_001");
		monitorLabel.getStyleClass().add("label_set_001");

		this.territoryNumberTextField.getStyleClass().add("text_field_002");
		this.territoryNameTextField.getStyleClass().add("text_field_001");
		this.territoryCoordinatesTextField.getStyleClass().add("text_field_001");
		this.territoryLandTextField.getStyleClass().add("text_field_001");
		this.territoryLandkreisTextField.getStyleClass().add("text_field_001");
		this.territoryKreisstadtTextField.getStyleClass().add("text_field_001");
		this.territoryOrtTextField.getStyleClass().add("text_field_001");
		this.territoryZipCodeTextField.getStyleClass().add("text_field_001");
		this.territoryOrtsteilTextField.getStyleClass().add("text_field_001");
		
		smartphoneTextField.getStyleClass().add("text_field_001");
		emailTextField.getStyleClass().add("text_field_001");
		monitorTextField.getStyleClass().add("text_field_001");

		this.studentCheckBox.getStyleClass().add("check_box_set_001");
		this.unbaptizedPublisherCheckBox.getStyleClass().add("check_box_set_001");
		this.baptizedPublisherCheckBox.getStyleClass().add("check_box_set_001");

		treasuresTalkCheckBox.getStyleClass().add("check_box_001");
		diggingCheckBox.getStyleClass().add("check_box_001");
		bibleReadingCheckBox.getStyleClass().add("check_box_001");
		initialCallCheckBox.getStyleClass().add("check_box_001");
		returnVisitCheckBox.getStyleClass().add("check_box_001");
		bibleStudyCheckBox.getStyleClass().add("check_box_001");
		talkCheckBox.getStyleClass().add("check_box_001");
		christiansCheckBox.getStyleClass().add("check_box_001");
		presidentMidweekCheckBox.getStyleClass().add("check_box_001");
		bibleStudyCongregationCheckBox.getStyleClass().add("check_box_001");
		watchtowerStudyCheckBox.getStyleClass().add("check_box_001");
		watchtowerSubstituteStudyCheckBox.getStyleClass().add("check_box_001");
		presidentWeekendCheckBox.getStyleClass().add("check_box_001");
		readerCongregationBibleStudyCheckBox.getStyleClass().add("check_box_001");
		readerWatchtowerCheckBox.getStyleClass().add("check_box_001");
		publicSpeakerInternCheckBox.getStyleClass().add("check_box_001");
		publicSpeakerExternCheckBox.getStyleClass().add("check_box_001");
		usciereZone1MidweekCheckBox.getStyleClass().add("check_box_001");
		usciereZone1WeekendCheckBox.getStyleClass().add("check_box_001");
		ministerialServantCheckBox.getStyleClass().add("check_box_001");
		elderCheckBox.getStyleClass().add("check_box_001");
		regularPioneerCheckBox.getStyleClass().add("check_box_001");
		specialPioneerCheckBox.getStyleClass().add("check_box_001");
		inactiveCheckBox.getStyleClass().add("check_box_001");
		markedCheckBox.getStyleClass().add("check_box_001");
		disfellowshippedCheckBox.getStyleClass().add("check_box_001");

		saveButton.getStyleClass().add("button_image_001");

		this.anointedCheckBox.getStyleClass().add("check_box_set_001");
		this.otherSheepCheckBox.getStyleClass().add("check_box_set_001");
		this.dateOfBaptismLabel.getStyleClass().add("label_set_001");
		this.dateOfBaptismDatePicker.getStyleClass().add("combo_box_001");
		this.excludeFromNaturalDisastersListCheckBox.getStyleClass().add("check_box_001");

		this.prayTab.getStyleClass().add("tab_001");
		this.audioTab.getStyleClass().add("tab_001");
		this.usciereTab.getStyleClass().add("tab_001");

		this.prayLabel.getStyleClass().add("label_002");
		this.prayStartLabel.getStyleClass().add("label_001");
		this.prayEndLabel.getStyleClass().add("label_001");
		this.prayMidweekLabel.getStyleClass().add("label_001");
		this.prayWeekendLabel.getStyleClass().add("label_001");
		this.prayStartMidweekCheckBox.getStyleClass().add("check_box_001");
		this.prayEndMidweekCheckBox.getStyleClass().add("check_box_001");
		this.prayEndWeekendCheckBox.getStyleClass().add("check_box_001");

		this.audioLabel.getStyleClass().add("label_002");
		this.audioMidweekLabel.getStyleClass().add("label_001");
		this.audioWeekendLabel.getStyleClass().add("label_001");
		this.audioPos1Label.getStyleClass().add("label_001");
		this.audioPos2Label.getStyleClass().add("label_001");
		this.audioPos3Label.getStyleClass().add("label_001");
		this.audioMicLabel.getStyleClass().add("label_001");
		this.audioMicMidweekCheckBox.getStyleClass().add("check_box_001");
		this.audioMicWeekendCheckBox.getStyleClass().add("check_box_001");
		this.audioPos1MidweekCheckBox.getStyleClass().add("check_box_001");
		this.audioPos1WeekendCheckBox.getStyleClass().add("check_box_001");
		this.audioPos2MidweekCheckBox.getStyleClass().add("check_box_001");
		this.audioPos2WeekendCheckBox.getStyleClass().add("check_box_001");
		this.audioPos3MidweekCheckBox.getStyleClass().add("check_box_001");
		this.audioPos3WeekendCheckBox.getStyleClass().add("check_box_001");

		this.usciereLabel.getStyleClass().add("label_002");
		this.usciereMidweekLabel.getStyleClass().add("label_001");
		this.usciereWeekendLabel.getStyleClass().add("label_001");
		this.usciereZone1Label.getStyleClass().add("label_001");
		this.usciereZone2Label.getStyleClass().add("label_001");
		this.usciereZone3Label.getStyleClass().add("label_001");
		this.usciereZone1MidweekCheckBox.getStyleClass().add("check_box_001");
		this.usciereZone1WeekendCheckBox.getStyleClass().add("check_box_001");
		this.usciereZone2MidweekCheckBox.getStyleClass().add("check_box_001");
		this.usciereZone2WeekendCheckBox.getStyleClass().add("check_box_001");
		this.usciereZone3MidweekCheckBox.getStyleClass().add("check_box_001");
		this.usciereZone3WeekendCheckBox.getStyleClass().add("check_box_001");

		this.readerTab.getStyleClass().add("tab_001");
		this.speakerTab.getStyleClass().add("tab_001");

		this.presidentSecondHallCheckBox.getStyleClass().add("check_box_001");
		this.serviceMeetingCongrCheckBox.getStyleClass().add("check_box_001");
		this.serviceMeetingGroupCheckBox.getStyleClass().add("check_box_001");
	}

	public void objectInitialize() {

		this.listenerCheckFields = false;

		initValue();
		viewUpdate();
		listeners();

		this.listenerCheckFields = true;
	}

	private void initValue() {

		if (selectedMember != null) {

			territoryNumberTextField.setText(selectedMember.getSpInf2Decrypted());
			territoryNameTextField.setText(selectedMember.getSpInf1Decrypted());
			territoryCoordinatesTextField.setText(selectedMember.getSpInf3Decrypted());
			smartphoneTextField.setText(selectedMember.getSpInf40Decrypted());
			emailTextField.setText(selectedMember.getSpInf41Decrypted());
			monitorTextField.setText(selectedMember.getSpInf47());

			// DATA DI NASCITA

			// DATA DI BATTESIMO

			String spInf53 = this.selectedMember.getSpInf53Decrypted();
			if (!spInf53.isEmpty())
				this.dateOfBaptismDatePicker.setValue(LocalDate.parse(spInf53));

			setCheckBoxes();

		} else {
			this.otherSheepCheckBox.setSelected(true);
		}
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

		this.audioMicMidweekCheckBox.setSelected((selectedMember.getSpInf20() == 1));
		this.audioMicWeekendCheckBox.setSelected((selectedMember.getSpInf21() == 1));
		this.audioPos1MidweekCheckBox.setSelected((selectedMember.getSpInf22() == 1));
		this.audioPos1WeekendCheckBox.setSelected((selectedMember.getSpInf23() == 1));
		this.audioPos2MidweekCheckBox.setSelected((selectedMember.getSpInf24() == 1));
		this.audioPos2WeekendCheckBox.setSelected((selectedMember.getSpInf25() == 1));

		this.readerCongregationBibleStudyCheckBox.setSelected((selectedMember.getSpInf26() == 1));
		this.readerWatchtowerCheckBox.setSelected((selectedMember.getSpInf27() == 1));

		this.usciereZone1MidweekCheckBox.setSelected((selectedMember.getSpInf28() == 1));
		this.usciereZone1WeekendCheckBox.setSelected((selectedMember.getSpInf29() == 1));

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

		this.otherSheepCheckBox.setSelected((this.selectedMember.getSpInf48() == 1));
		this.anointedCheckBox.setSelected((this.selectedMember.getSpInf49() == 1));
		this.excludeFromNaturalDisastersListCheckBox.setSelected((this.selectedMember.getSpInf51() == 1));

		this.audioPos3MidweekCheckBox.setSelected((this.selectedMember.getSpInf54() == 1));
		this.audioPos3WeekendCheckBox.setSelected((this.selectedMember.getSpInf55() == 1));
		this.usciereZone2MidweekCheckBox.setSelected((this.selectedMember.getSpInf56() == 1));
		this.usciereZone2WeekendCheckBox.setSelected((this.selectedMember.getSpInf57() == 1));
		this.usciereZone3MidweekCheckBox.setSelected((this.selectedMember.getSpInf58() == 1));
		this.usciereZone3WeekendCheckBox.setSelected((this.selectedMember.getSpInf59() == 1));
		
		this.presidentSecondHallCheckBox.setSelected((this.selectedMember.getSpInf60() == 1));
		this.serviceMeetingCongrCheckBox.setSelected((this.selectedMember.getSpInf61() == 1));
		this.serviceMeetingGroupCheckBox.setSelected((this.selectedMember.getSpInf62() == 1));
	}

	private void listeners() {

		listenerNameTextField();

		this.studentCheckBox.selectedProperty().addListener((obs, oldV, newV) -> checkBoxGroups(newV, false,
				this.studentCheckBox, this.unbaptizedPublisherCheckBox, this.baptizedPublisherCheckBox));

		this.unbaptizedPublisherCheckBox.selectedProperty().addListener((obs, oldV, newV) -> checkBoxGroups(newV, false,
				this.unbaptizedPublisherCheckBox, this.studentCheckBox, this.baptizedPublisherCheckBox));

		this.baptizedPublisherCheckBox.selectedProperty().addListener((obs, oldV, newV) -> checkBoxGroups(newV, false,
				this.baptizedPublisherCheckBox, this.studentCheckBox, this.unbaptizedPublisherCheckBox));

		this.anointedCheckBox.selectedProperty().addListener(
				(obs, oldV, newV) -> checkBoxGroups(newV, true, this.anointedCheckBox, this.otherSheepCheckBox));

		this.otherSheepCheckBox.selectedProperty().addListener(
				(obs, oldV, newV) -> checkBoxGroups(newV, true, this.otherSheepCheckBox, this.anointedCheckBox));

		listenerSaveButton();
	}

	private void listenerSaveButton() {
		saveButton.setOnAction(event -> saveMember());
	}

	private void checkBoxGroups(Boolean newV, Boolean notNull, CheckBox edited, CheckBox... others) {

		if (this.listenerCheckFields) {

			this.listenerCheckFields = false;

			if (newV) {
				for (CheckBox cb : others)
					if (cb.isSelected())
						cb.setSelected(false);
			} else {
				if (notNull)
					edited.setSelected(true);
			}

			this.listenerCheckFields = true;
		}
	}

	private void saveMember() {

		if (checkFields()) {

			SecretKey secretKey = this.settings.getDatabaseSecretKey();

			String spInf1 = Crypt.encrypt(territoryNameTextField.getText(), settings.getDatabaseSecretKey());
			String spInf2 = Crypt.encrypt(territoryNumberTextField.getText(), settings.getDatabaseSecretKey());
			String spInf3 = Crypt.encrypt(territoryCoordinatesTextField.getText(), settings.getDatabaseSecretKey());

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

			String spInf20 = !this.audioMicMidweekCheckBox.isSelected() ? "0" : "1";
			String spInf21 = !this.audioMicWeekendCheckBox.isSelected() ? "0" : "1";
			String spInf22 = !this.audioPos1MidweekCheckBox.isSelected() ? "0" : "1";
			String spInf23 = !this.audioPos1WeekendCheckBox.isSelected() ? "0" : "1";
			String spInf24 = !this.audioPos2MidweekCheckBox.isSelected() ? "0" : "1";
			String spInf25 = !this.audioPos2WeekendCheckBox.isSelected() ? "0" : "1";

			String spInf26 = !this.readerCongregationBibleStudyCheckBox.isSelected() ? "0" : "1";
			String spInf27 = !this.readerWatchtowerCheckBox.isSelected() ? "0" : "1";

			String spInf28 = !this.usciereZone1MidweekCheckBox.isSelected() ? "0" : "1";
			String spInf29 = !this.usciereZone1WeekendCheckBox.isSelected() ? "0" : "1";

			String spInf30 = !this.treasuresTalkCheckBox.isSelected() ? "0" : "1";
			String spInf31 = !this.diggingCheckBox.isSelected() ? "0" : "1";
			String spInf32 = !this.christiansCheckBox.isSelected() ? "0" : "1";
			String spInf33 = !this.presidentMidweekCheckBox.isSelected() ? "0" : "1";
			String spInf34 = !this.prayStartMidweekCheckBox.isSelected() ? "0" : "1";
			String spInf35 = !this.prayEndMidweekCheckBox.isSelected() ? "0" : "1";
			String spInf36 = !this.presidentWeekendCheckBox.isSelected() ? "0" : "1";
			String spInf37 = !this.prayEndWeekendCheckBox.isSelected() ? "0" : "1";
			String spInf38 = !this.inactiveCheckBox.isSelected() ? "0" : "1";
			String spInf40 = Crypt.encrypt(smartphoneTextField.getText(), settings.getDatabaseSecretKey());
			String spInf41 = Crypt.encrypt(emailTextField.getText(), settings.getDatabaseSecretKey());
			String spInf42 = !this.bibleStudyCongregationCheckBox.isSelected() ? "0" : "1";
			String spInf43 = !this.watchtowerStudyCheckBox.isSelected() ? "0" : "1";
			String spInf44 = !this.watchtowerSubstituteStudyCheckBox.isSelected() ? "0" : "1";
			String spInf45 = !this.publicSpeakerInternCheckBox.isSelected() ? "0" : "1";
			String spInf46 = !this.publicSpeakerExternCheckBox.isSelected() ? "0" : "1";
			String spInf47 = monitorTextField.getText();

			String spInf48 = !this.otherSheepCheckBox.isSelected() ? "0" : "1";
			String spInf49 = !this.anointedCheckBox.isSelected() ? "0" : "1";
			String spInf51 = !this.excludeFromNaturalDisastersListCheckBox.isSelected() ? "0" : "1";

			LocalDate dateOfBaptism = this.dateOfBaptismDatePicker.getValue();
			String spInf53 = dateOfBaptism != null ? Crypt.encrypt(dateOfBaptism.toString(), secretKey)
					: Crypt.encrypt("", secretKey);

			String spInf54 = !this.audioPos3MidweekCheckBox.isSelected() ? "0" : "1";
			String spInf55 = !this.audioPos3WeekendCheckBox.isSelected() ? "0" : "1";
			String spInf56 = !this.usciereZone2MidweekCheckBox.isSelected() ? "0" : "1";
			String spInf57 = !this.usciereZone2WeekendCheckBox.isSelected() ? "0" : "1";
			String spInf58 = !this.usciereZone3MidweekCheckBox.isSelected() ? "0" : "1";
			String spInf59 = !this.usciereZone3WeekendCheckBox.isSelected() ? "0" : "1";

			String spInf60 = !this.presidentSecondHallCheckBox.isSelected() ? "0" : "1";
			String spInf61 = !this.serviceMeetingCongrCheckBox.isSelected() ? "0" : "1";
			String spInf62 = !this.serviceMeetingGroupCheckBox.isSelected() ? "0" : "1";

//			if (selectedMember != null)
//				editMember(spInf1, spInf2, spInf3, spInf4, spInf6, spInf7, spInf8, spInf9, spInf10, spInf11, spInf12,
//						spInf13, spInf14, spInf15, spInf16, spInf17, spInf18, spInf19, spInf20, spInf21, spInf22,
//						spInf23, spInf24, spInf25, spInf26, spInf27, spInf28, spInf29, spInf30, spInf31, spInf32,
//						spInf33, spInf34, spInf35, spInf36, spInf37, spInf38, spInf39, spInf40, spInf41, spInf42,
//						spInf43, spInf44, spInf45, spInf46, spInf47, spInf48, spInf49, spInf50, spInf51, spInf52,
//						spInf53, spInf54, spInf55, spInf56, spInf57, spInf58, spInf59, spInf60, spInf61, spInf62);
//			else
//				newMember(spInf1, spInf2, spInf3, spInf4, spInf6, spInf7, spInf8, spInf9, spInf10, spInf11, spInf12,
//						spInf13, spInf14, spInf15, spInf16, spInf17, spInf18, spInf19, spInf20, spInf21, spInf22,
//						spInf23, spInf24, spInf25, spInf26, spInf27, spInf28, spInf29, spInf30, spInf31, spInf32,
//						spInf33, spInf34, spInf35, spInf36, spInf37, spInf38, spInf39, spInf40, spInf41, spInf42,
//						spInf43, spInf44, spInf45, spInf46, spInf47, spInf48, spInf49, spInf50, spInf51, spInf52,
//						spInf53, spInf54, spInf55, spInf56, spInf57, spInf58, spInf59, spInf60, spInf61, spInf62);
		}
//		else
//			new AlertDesigner(language.getStringWithNewLine("TEXT0004"), ownerStage, AlertType.ERROR,
//					Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
//					Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").show();
	}

	private void newMember(String spInf1, String spInf2, String spInf3, String spInf4, String spInf6, String spInf7,
			String spInf8, String spInf9, String spInf10, String spInf11, String spInf12, String spInf13,
			String spInf14, String spInf15, String spInf16, String spInf17, String spInf18, String spInf19,
			String spInf20, String spInf21, String spInf22, String spInf23, String spInf24, String spInf25,
			String spInf26, String spInf27, String spInf28, String spInf29, String spInf30, String spInf31,
			String spInf32, String spInf33, String spInf34, String spInf35, String spInf36, String spInf37,
			String spInf38, String spInf39, String spInf40, String spInf41, String spInf42, String spInf43,
			String spInf44, String spInf45, String spInf46, String spInf47, String spInf48, String spInf49,
			String spInf50, String spInf51, String spInf52, String spInf53, String spInf54, String spInf55,
			String spInf56, String spInf57, String spInf58, String spInf59, String spInf60, String spInf61,
			String spInf62) {

		Actions.insertMember(spInf1, spInf2, spInf3, spInf4, "-1", spInf6, spInf7, spInf8, spInf9, spInf10, spInf11,
				spInf12, spInf13, spInf14, spInf15, spInf16, spInf17, spInf18, spInf19, spInf20, spInf21, spInf22,
				spInf23, spInf24, spInf25, spInf26, spInf27, spInf28, spInf29, spInf30, spInf31, spInf32, spInf33,
				spInf34, spInf35, spInf36, spInf37, spInf38, spInf39, spInf40, spInf41, spInf42, spInf43, spInf44,
				spInf45, spInf46, spInf47, spInf48, spInf49, spInf50, spInf51, spInf52, spInf53, spInf54, spInf55,
				spInf56, spInf57, spInf58, spInf59, spInf60, spInf61, spInf62, settings, ownerStage, parentTabPane,
				newMemberTab, membersTab, ownerCtrl);
	}

	private void editMember(String spInf1, String spInf2, String spInf3, String spInf4, String spInf6, String spInf7,
			String spInf8, String spInf9, String spInf10, String spInf11, String spInf12, String spInf13,
			String spInf14, String spInf15, String spInf16, String spInf17, String spInf18, String spInf19,
			String spInf20, String spInf21, String spInf22, String spInf23, String spInf24, String spInf25,
			String spInf26, String spInf27, String spInf28, String spInf29, String spInf30, String spInf31,
			String spInf32, String spInf33, String spInf34, String spInf35, String spInf36, String spInf37,
			String spInf38, String spInf39, String spInf40, String spInf41, String spInf42, String spInf43,
			String spInf44, String spInf45, String spInf46, String spInf47, String spInf48, String spInf49,
			String spInf50, String spInf51, String spInf52, String spInf53, String spInf54, String spInf55,
			String spInf56, String spInf57, String spInf58, String spInf59, String spInf60, String spInf61,
			String spInf62) {

		Actions.updateMember(String.valueOf(selectedMember.getSpMemberID()), spInf1, spInf2, spInf3, spInf4, spInf6,
				spInf7, spInf8, spInf9, spInf10, spInf11, spInf12, spInf13, spInf14, spInf15, spInf16, spInf17, spInf18,
				spInf19, spInf20, spInf21, spInf22, spInf23, spInf24, spInf25, spInf26, spInf27, spInf28, spInf29,
				spInf30, spInf31, spInf32, spInf33, spInf34, spInf35, spInf36, spInf37, spInf38, spInf39, spInf40,
				spInf41, spInf42, spInf43, spInf44, spInf45, spInf46, spInf47, spInf48, spInf49, spInf50, spInf51,
				spInf52, spInf53, spInf54, spInf55, spInf56, spInf57, spInf58, spInf59, spInf60, spInf61, spInf62,
				settings, ownerStage, parentTabPane, newMemberTab, membersTab, ownerCtrl);
	}

	private boolean checkFields() {

		if (territoryNumberTextField.getText().isEmpty()) {

			this.application.getAlertBuilder2().error(this.ownerStage,
					this.language.getString("congregation.memberseditor.error.surname"));

			return false;
		}

		if (territoryNameTextField.getText().isEmpty()) {

			this.application.getAlertBuilder2().error(this.ownerStage,
					this.language.getString("congregation.memberseditor.error.name"));

			return false;
		}

		if (territoryCoordinatesTextField.getText().isEmpty()) {

			this.application.getAlertBuilder2().error(this.ownerStage,
					this.language.getString("congregation.memberseditor.error.nameshort"));

			return false;
		}

//		boolean male = genderMaleCheckBox.isSelected();
//		boolean female = genderFemaleCheckBox.isSelected();

//		if ((male && female) || (!male && !female)) {
//
//			this.application.getAlertBuilder2().error(this.ownerStage,
//					this.language.getString("congregation.memberseditor.error.gender"));
//
//			return false;
//		}

//		if (!this.singlenessTextField.getText().isEmpty())
//			if (this.genderMaleCheckBox.isSelected()) {
//
//				this.application.getAlertBuilder2().error(this.ownerStage,
//						this.language.getString("congregation.memberseditor.error.surname2"));
//
//				return false;
//			}

//		if (this.maidenNamePrintCheckBox.isSelected())
//			if (!this.genderFemaleCheckBox.isSelected()) {
//
//				this.application.getAlertBuilder2().error(this.ownerStage,
//						this.language.getString("congregation.memberseditor.error.maidennameprint"));
//
//				return false;
//			}

		if (this.dateOfBaptismDatePicker.getValue() != null)
			if (!this.baptizedPublisherCheckBox.isSelected()) {

				this.application.getAlertBuilder2().error(this.ownerStage,
						this.language.getString("congregation.memberseditor.error.dateofbaptism"));

				return false;
			}

		return true;
	}

	private void listenerNameTextField() {

		territoryNameTextField.focusedProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue)
				if (territoryCoordinatesTextField.getText().isEmpty()) {
					if (!territoryNameTextField.getText().isEmpty())
						territoryCoordinatesTextField.setText(territoryNameTextField.getText(0, 1) + ".");
				} else if (territoryNameTextField.getText().isEmpty())
					territoryCoordinatesTextField.setText("");
		});

	}

	private void viewUpdate() {

		this.language = this.settings.getLanguage();

		this.territoryTabPane.setTabMinHeight(75);
		this.territoryTabPane.setTabMaxHeight(75);

		Tooltip territoryInfoTooltip = new Tooltip(
				this.language.getString("territoryeditor.tab.tooltip.territoryinfo"));
		territoryInfoTooltip.getStyleClass().add("tooltip_001");
		this.territoryInfoTab.setTooltip(territoryInfoTooltip);
		this.territoryInfoTab.setText("");
		this.territoryInfoTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.INFO));

		this.territoryNumberLabel.setText(this.language.getString("territoryeditor.label.number"));
		this.territoryNameLabel.setText(this.language.getString("territoryeditor.label.name"));
		this.territoryCoordinatesLabel.setText(this.language.getString("territoryeditor.label.coordinates"));
		
		this.territoryLandLabel.setText(this.language.getString("territoryeditor.label.land"));
		this.territoryLandkreisLabel.setText(this.language.getString("territoryeditor.label.landkreis"));
		this.territoryKreisstadtLabel.setText(this.language.getString("territoryeditor.label.kreisstadt"));
		this.territoryOrtLabel.setText(this.language.getString("territoryeditor.label.ort"));
		this.territoryZipCodeLabel.setText(this.language.getString("territoryeditor.label.zipcode"));
		this.territoryOrtsteilLabel.setText(this.language.getString("territoryeditor.label.ortsteil"));
		
//		singlenessLabel.setText(language.getString("TEXT0065"));
//		genderLabel.setText(language.getString("TEXT0019"));
//
//		this.genderMaleCheckBox.setText(language.getString("TEXT0020"));
//		this.genderMaleCheckBox.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.MALE));
//		this.genderFemaleCheckBox.setText(language.getString("TEXT0021"));
//		this.genderFemaleCheckBox.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.FEMALE));

		Tooltip saveTooltip = new Tooltip(this.language.getString("congregation.memberseditor.tooltip.save"));
		saveTooltip.getStyleClass().add("tooltip_001");
		this.saveButton.setTooltip(saveTooltip);
		this.saveButton.setText("");
		this.saveButton.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.SAVE));

		Tooltip memberAppointmentAndPrivilegeTooltip = new Tooltip(
				this.language.getString("congregation.memberseditor.tooltip.appointmentundprivileges"));
		memberAppointmentAndPrivilegeTooltip.getStyleClass().add("tooltip_001");
		this.memberAppointmentAndPrivilegeTab.setTooltip(memberAppointmentAndPrivilegeTooltip);
		this.memberAppointmentAndPrivilegeTab.setText("");
		this.memberAppointmentAndPrivilegeTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.MEMBER_PRIVILEGES));

		studentCheckBox.setText(language.getString("TEXT0044"));
		unbaptizedPublisherCheckBox.setText(language.getString("TEXT0045"));
		baptizedPublisherCheckBox.setText(language.getString("TEXT0046"));

		// assignmentsTab.setText(language.getString("TEXT0104"));

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

		// privilegeTab.setText(language.getString("TEXT0052"));

		conductorLabel.setText(language.getString("TEXT0105"));
		privilegeLabel.setText(this.language.getString("congregation.memberseditor.assignments"));
		prayStartLabel.setText(language.getString("TEXT0084"));
		prayEndLabel.setText(language.getString("TEXT0102"));
//		audioPos1Label.setText(language.getString("TEXT0056"));
//		audioPos2Label.setText(language.getString("TEXT0058"));

//		meetingLabel.setText(language.getString("TEXT0055"));
//		meetingMidweekLabel.setText(language.getString("TEXT0057"));
//		meetingWeekendLabel.setText(language.getString("TEXT0059"));

		presidentMidweekCheckBox.setText(language.getString("TEXT0083"));
		presidentWeekendCheckBox.setText(language.getString("TEXT0110"));
		bibleStudyCongregationCheckBox.setText(language.getString("TEXT0061"));
		watchtowerStudyCheckBox.setText(language.getString("TEXT0062"));
		watchtowerSubstituteStudyCheckBox.setText(language.getString("TEXT0111"));
		prayStartMidweekCheckBox.setText("");
		prayEndMidweekCheckBox.setText("");
		prayEndWeekendCheckBox.setText("");
		readerLabel.setText(language.getString("TEXT0060"));
		readerCongregationBibleStudyCheckBox.setText(language.getString("TEXT0061"));
		readerWatchtowerCheckBox.setText(language.getString("TEXT0062"));
		publicSpeakerLabel.setText(language.getString("TEXT0112"));
		publicSpeakerInternCheckBox.setText(language.getString("TEXT0113"));
		publicSpeakerExternCheckBox.setText(language.getString("TEXT0114"));

		// appointmentTab.setText(language.getString("TEXT0066"));

		ministerialServantCheckBox.setText(language.getString("TEXT0067"));
		elderCheckBox.setText(language.getString("TEXT0068"));
		regularPioneerCheckBox.setText(language.getString("TEXT0069"));
		specialPioneerCheckBox.setText(language.getString("TEXT0070"));

		// onthersTab.setText(language.getString("TEXT0071"));

		inactiveCheckBox.setText(language.getString("TEXT0064"));
		markedCheckBox.setText(language.getString("TEXT0072"));
		disfellowshippedCheckBox.setText(language.getString("TEXT0073"));

//		this.maidenNamePrintCheckBox.setText(this.language.getString("congregation.memberseditor.maidennameprint"));
//		this.dateOfBirthLabel.setText(this.language.getString("congregation.memberseditor.dateofbirth"));
		this.anointedCheckBox.setText(this.language.getString("congregation.memberseditor.anointed"));
		this.otherSheepCheckBox.setText(this.language.getString("congregation.memberseditor.othersheep"));
		this.dateOfBaptismLabel.setText(this.language.getString("congregation.memberseditor.dateofbaptism"));
		this.excludeFromNaturalDisastersListCheckBox
				.setText(this.language.getString("congregation.memberseditor.excludefromnaturaldisasters"));

		Tooltip memberContactsTooltip = new Tooltip(
				this.language.getString("congregation.memberseditor.tooltip.contacts"));
		memberContactsTooltip.getStyleClass().add("tooltip_001");
		this.memberContactsTab.setTooltip(memberContactsTooltip);
		this.memberContactsTab.setText("");
		this.memberContactsTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.CONTACTS));

		smartphoneLabel.setText(language.getString("TEXT0107"));
		emailLabel.setText(language.getString("TEXT0108"));

		Tooltip memberMonitorTooltip = new Tooltip(
				this.language.getString("congregation.memberseditor.tooltip.monitor"));
		memberMonitorTooltip.getStyleClass().add("tooltip_001");
		this.memberMonitorTab.setTooltip(memberMonitorTooltip);
		this.memberMonitorTab.setText("");
		this.memberMonitorTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.USER_MENU_MONITOR));

		monitorLabel.setText(language.getString("sp.congr.member.monitorpass"));

		this.memberInfoTabPane.setTabMinHeight(75);
		this.memberInfoTabPane.setTabMaxHeight(75);

		Tooltip assignmentsTooltip = new Tooltip(
				this.language.getString("congregation.memberseditor.tooltip.assignments"));
		assignmentsTooltip.getStyleClass().add("tooltip_001");
		this.assignmentsTab.setTooltip(assignmentsTooltip);
		this.assignmentsTab.setText("");
		this.assignmentsTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.ASSIGNEMENTS));

		Tooltip privilegeTooltip = new Tooltip(this.language.getString("congregation.memberseditor.tooltip.privilege"));
		privilegeTooltip.getStyleClass().add("tooltip_001");
		this.privilegeTab.setTooltip(privilegeTooltip);
		this.privilegeTab.setText("");
		this.privilegeTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.PRIVILEGIES));

		Tooltip appointmentTooltip = new Tooltip(
				this.language.getString("congregation.memberseditor.tooltip.appointment"));
		appointmentTooltip.getStyleClass().add("tooltip_001");
		this.appointmentTab.setTooltip(appointmentTooltip);
		this.appointmentTab.setText("");
		this.appointmentTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.ROLES));

		Tooltip onthersTooltip = new Tooltip(this.language.getString("congregation.memberseditor.tooltip.onthers"));
		onthersTooltip.getStyleClass().add("tooltip_001");
		this.onthersTab.setTooltip(onthersTooltip);
		this.onthersTab.setText("");
		this.onthersTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.OTHERS));

		this.memberAssignmentTabPane.setTabMinHeight(75);
		this.memberAssignmentTabPane.setTabMaxHeight(75);

		Tooltip prayTabTooltip = new Tooltip(language.getString("congregation.memberseditor.tooltip.tab.pray"));
		prayTabTooltip.getStyleClass().add("tooltip_001");
		this.prayTab.setTooltip(prayTabTooltip);
		this.prayTab.setText("");
		this.prayTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.PRAY));

		Tooltip audioTabTooltip = new Tooltip(language.getString("congregation.memberseditor.tooltip.tab.audio"));
		audioTabTooltip.getStyleClass().add("tooltip_001");
		this.audioTab.setTooltip(audioTabTooltip);
		this.audioTab.setText("");
		this.audioTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.AUDIO));

		Tooltip usciereTabTooltip = new Tooltip(language.getString("congregation.memberseditor.tooltip.tab.usciere"));
		usciereTabTooltip.getStyleClass().add("tooltip_001");
		this.usciereTab.setTooltip(usciereTabTooltip);
		this.usciereTab.setText("");
		this.usciereTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.USCIERE));

		this.prayLabel.setText(this.language.getString("congregation.memberseditor.assignments.pray"));
		this.prayMidweekLabel.setText(this.language.getString("congregation.memberseditor.assignments.midweek"));
		this.prayWeekendLabel.setText(this.language.getString("congregation.memberseditor.assignments.weekend"));
		this.prayStartMidweekCheckBox.setText("");
		this.prayEndMidweekCheckBox.setText("");
		this.prayEndWeekendCheckBox.setText("");

		this.audioLabel.setText(this.language.getString("congregation.memberseditor.audio"));
		this.audioMidweekLabel.setText(this.language.getString("congregation.memberseditor.assignments.midweek"));
		this.audioWeekendLabel.setText(this.language.getString("congregation.memberseditor.assignments.weekend"));

		String noconfig = this.language.getString("congregation.memberseditor.assignments.noconfig");
		String audioFormat = this.language.getString("congregation.memberseditor.assignments.audiopos");
		String usciereFormat = this.language.getString("congregation.memberseditor.assignments.uscierezone");

//		String inf9 = this.configs.get("inf9");
//		if (inf9 == null || inf9.isEmpty())
//			this.audioPos1Label.setText(String.format(audioFormat, noconfig));
//		else
//			this.audioPos1Label.setText(String.format(audioFormat, inf9));

//		String inf10 = this.configs.get("inf10");
//		if (inf10 == null || inf10.isEmpty())
//			this.audioPos2Label.setText(String.format(audioFormat, noconfig));
//		else
//			this.audioPos2Label.setText(String.format(audioFormat, inf10));
//
//		String inf11 = this.configs.get("inf11");
//		if (inf11 == null || inf11.isEmpty())
//			this.audioPos3Label.setText(String.format(audioFormat, noconfig));
//		else
//			this.audioPos3Label.setText(String.format(audioFormat, inf11));

		this.audioMicLabel.setText(this.language.getString("congregation.memberseditor.audio.mic"));
		this.audioMicMidweekCheckBox.setText("");
		this.audioMicWeekendCheckBox.setText("");
		this.audioPos1MidweekCheckBox.setText("");
		this.audioPos1WeekendCheckBox.setText("");
		this.audioPos2MidweekCheckBox.setText("");
		this.audioPos2WeekendCheckBox.setText("");
		this.audioPos3MidweekCheckBox.setText("");
		this.audioPos3WeekendCheckBox.setText("");

		this.usciereLabel.setText(this.language.getString("congregation.memberseditor.usciere"));
		this.usciereMidweekLabel.setText(this.language.getString("congregation.memberseditor.assignments.midweek"));
		this.usciereWeekendLabel.setText(this.language.getString("congregation.memberseditor.assignments.weekend"));

//		String inf12 = this.configs.get("inf12");
//		if (inf12 == null || inf12.isEmpty())
//			this.usciereZone1Label.setText(String.format(usciereFormat, noconfig));
//		else
//			this.usciereZone1Label.setText(String.format(usciereFormat, inf12));
//
//		String inf13 = this.configs.get("inf13");
//		if (inf13 == null || inf13.isEmpty())
//			this.usciereZone2Label.setText(String.format(usciereFormat, noconfig));
//		else
//			this.usciereZone2Label.setText(String.format(usciereFormat, inf13));
//
//		String inf14 = this.configs.get("inf14");
//		if (inf14 == null || inf14.isEmpty())
//			this.usciereZone3Label.setText(String.format(usciereFormat, noconfig));
//		else
//			this.usciereZone3Label.setText(String.format(usciereFormat, inf14));

		this.usciereZone1MidweekCheckBox.setText("");
		this.usciereZone1WeekendCheckBox.setText("");
		this.usciereZone2MidweekCheckBox.setText("");
		this.usciereZone2WeekendCheckBox.setText("");
		this.usciereZone3MidweekCheckBox.setText("");
		this.usciereZone3WeekendCheckBox.setText("");

		Tooltip readerTabTooltip = new Tooltip(language.getString("congregation.memberseditor.tooltip.tab.reader"));
		readerTabTooltip.getStyleClass().add("tooltip_001");
		this.readerTab.setTooltip(readerTabTooltip);
		this.readerTab.setText("");
		this.readerTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.PODIUM));

		Tooltip speakerTabTooltip = new Tooltip(language.getString("congregation.memberseditor.tooltip.tab.speaker"));
		speakerTabTooltip.getStyleClass().add("tooltip_001");
		this.speakerTab.setTooltip(speakerTabTooltip);
		this.speakerTab.setText("");
		this.speakerTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.PUBLIC_TALK));

		this.presidentSecondHallCheckBox
				.setText(this.language.getString("congregation.memberseditor.presidentsecondhall"));
		this.serviceMeetingCongrCheckBox.setText(this.language.getString("congregation.memberseditor.servmeetcongr"));
		this.serviceMeetingGroupCheckBox.setText(this.language.getString("congregation.memberseditor.servmeetgroup"));
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

	public TabPane getParentTabPane() {
		return parentTabPane;
	}

	public void setParentTabPane(TabPane parentTabPane) {
		this.parentTabPane = parentTabPane;
	}

	public Tab getNewMemberTab() {
		return newMemberTab;
	}

	public void setNewMemberTab(Tab newMemberTab) {
		this.newMemberTab = newMemberTab;
	}

	public Territory getOwnerCtrl() {
		return ownerCtrl;
	}

	public void setOwnerCtrl(Territory ownerCtrl) {
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

	public SupportPlannerView getApplication() {
		return application;
	}

	public void setApplication(SupportPlannerView application) {
		this.application = application;
	}

	public HashMap<String, String> getConfigs() {
		return configs;
	}

	public void setConfigs(HashMap<String, String> configs) {
		this.configs = configs;
	}

}
