package com.sm.net.sp.view.home.user.menu.territory;

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
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;

public class TerritoryEditor {

	@FXML
	private TabPane territoryTabPane;

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
	private Label territoryMyMapsIDLabel;
	@FXML
	private TextField territoryMyMapsIDTextField;

	@FXML
	private Tab imagesTab;

	@FXML
	private Label image1Label;
	@FXML
	private TextField image1TextField;
	@FXML
	private Label image2Label;
	@FXML
	private TextField image2TextField;
	@FXML
	private Label image3Label;
	@FXML
	private TextField image3TextField;
	@FXML
	private Label image4Label;
	@FXML
	private TextField image4TextField;
	@FXML
	private Label image5Label;
	@FXML
	private TextField image5TextField;

	@FXML
	private Label image1TitleLabel;
	@FXML
	private TextField image1TitleTextField;
	@FXML
	private Label image2TitleLabel;
	@FXML
	private TextField image2TitleTextField;
	@FXML
	private Label image3TitleLabel;
	@FXML
	private TextField image3TitleTextField;
	@FXML
	private Label image4TitleLabel;
	@FXML
	private TextField image4TitleTextField;
	@FXML
	private Label image5TitleLabel;
	@FXML
	private TextField image5TitleTextField;

	@FXML
	private Tab docsTab;

	@FXML
	private Label doc1Label;
	@FXML
	private TextField doc1TextField;
	@FXML
	private Label doc2Label;
	@FXML
	private TextField doc2TextField;
	@FXML
	private Label doc3Label;
	@FXML
	private TextField doc3TextField;
	@FXML
	private Label doc4Label;
	@FXML
	private TextField doc4TextField;
	@FXML
	private Label doc5Label;
	@FXML
	private TextField doc5TextField;

	@FXML
	private Label doc1TitleLabel;
	@FXML
	private TextField doc1TitleTextField;
	@FXML
	private Label doc2TitleLabel;
	@FXML
	private TextField doc2TitleTextField;
	@FXML
	private Label doc3TitleLabel;
	@FXML
	private TextField doc3TitleTextField;
	@FXML
	private Label doc4TitleLabel;
	@FXML
	private TextField doc4TitleTextField;
	@FXML
	private Label doc5TitleLabel;
	@FXML
	private TextField doc5TitleTextField;

	@FXML
	private Button saveButton;

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

		this.territoryNumberLabel.getStyleClass().add("label_set_001");
		this.territoryNameLabel.getStyleClass().add("label_set_001");
		this.territoryCoordinatesLabel.getStyleClass().add("label_set_001");
		this.territoryLandLabel.getStyleClass().add("label_set_001");
		this.territoryLandkreisLabel.getStyleClass().add("label_set_001");
		this.territoryKreisstadtLabel.getStyleClass().add("label_set_001");
		this.territoryOrtLabel.getStyleClass().add("label_set_001");
		this.territoryZipCodeLabel.getStyleClass().add("label_set_001");
		this.territoryOrtsteilLabel.getStyleClass().add("label_set_001");
		this.territoryMyMapsIDLabel.getStyleClass().add("label_set_001");

		this.image1Label.getStyleClass().add("label_set_001");
		this.image1TitleLabel.getStyleClass().add("label_set_001");
		this.image2Label.getStyleClass().add("label_set_001");
		this.image2TitleLabel.getStyleClass().add("label_set_001");
		this.image3Label.getStyleClass().add("label_set_001");
		this.image3TitleLabel.getStyleClass().add("label_set_001");
		this.image4Label.getStyleClass().add("label_set_001");
		this.image4TitleLabel.getStyleClass().add("label_set_001");
		this.image5Label.getStyleClass().add("label_set_001");
		this.image5TitleLabel.getStyleClass().add("label_set_001");

		this.doc1Label.getStyleClass().add("label_set_001");
		this.doc1TitleLabel.getStyleClass().add("label_set_001");
		this.doc2Label.getStyleClass().add("label_set_001");
		this.doc2TitleLabel.getStyleClass().add("label_set_001");
		this.doc3Label.getStyleClass().add("label_set_001");
		this.doc3TitleLabel.getStyleClass().add("label_set_001");
		this.doc4Label.getStyleClass().add("label_set_001");
		this.doc4TitleLabel.getStyleClass().add("label_set_001");
		this.doc5Label.getStyleClass().add("label_set_001");
		this.doc5TitleLabel.getStyleClass().add("label_set_001");

		this.imagesTab.getStyleClass().add("tab_001");
		this.docsTab.getStyleClass().add("tab_001");

		this.territoryNumberTextField.getStyleClass().add("text_field_002");
		this.territoryNameTextField.getStyleClass().add("text_field_001");
		this.territoryCoordinatesTextField.getStyleClass().add("text_field_001");
		this.territoryLandTextField.getStyleClass().add("text_field_001");
		this.territoryLandkreisTextField.getStyleClass().add("text_field_001");
		this.territoryKreisstadtTextField.getStyleClass().add("text_field_001");
		this.territoryOrtTextField.getStyleClass().add("text_field_001");
		this.territoryZipCodeTextField.getStyleClass().add("text_field_001");
		this.territoryOrtsteilTextField.getStyleClass().add("text_field_001");
		this.territoryMyMapsIDTextField.getStyleClass().add("text_field_001");

		this.image1TextField.getStyleClass().add("text_field_001");
		this.image1TitleTextField.getStyleClass().add("text_field_001");
		this.image2TextField.getStyleClass().add("text_field_001");
		this.image2TitleTextField.getStyleClass().add("text_field_001");
		this.image3TextField.getStyleClass().add("text_field_001");
		this.image3TitleTextField.getStyleClass().add("text_field_001");
		this.image4TextField.getStyleClass().add("text_field_001");
		this.image4TitleTextField.getStyleClass().add("text_field_001");
		this.image5TextField.getStyleClass().add("text_field_001");
		this.image5TitleTextField.getStyleClass().add("text_field_001");

		this.doc1TextField.getStyleClass().add("text_field_001");
		this.doc1TitleTextField.getStyleClass().add("text_field_001");
		this.doc2TextField.getStyleClass().add("text_field_001");
		this.doc2TitleTextField.getStyleClass().add("text_field_001");
		this.doc3TextField.getStyleClass().add("text_field_001");
		this.doc3TitleTextField.getStyleClass().add("text_field_001");
		this.doc4TextField.getStyleClass().add("text_field_001");
		this.doc4TitleTextField.getStyleClass().add("text_field_001");
		this.doc5TextField.getStyleClass().add("text_field_001");
		this.doc5TitleTextField.getStyleClass().add("text_field_001");

		this.saveButton.getStyleClass().add("button_image_001");
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

			setCheckBoxes();

		} else {
//			this.otherSheepCheckBox.setSelected(true);
		}
	}

	private void setCheckBoxes() {

//		this.studentCheckBox.setSelected((selectedMember.getSpInf6() == 1));
//		this.unbaptizedPublisherCheckBox.setSelected((selectedMember.getSpInf7() == 1));
//		this.baptizedPublisherCheckBox.setSelected((selectedMember.getSpInf8() == 1));
//
//		this.ministerialServantCheckBox.setSelected((selectedMember.getSpInf9() == 1));
//		this.elderCheckBox.setSelected((selectedMember.getSpInf10() == 1));
//		this.regularPioneerCheckBox.setSelected((selectedMember.getSpInf11() == 1));
//		this.specialPioneerCheckBox.setSelected((selectedMember.getSpInf12() == 1));
//
//		this.bibleReadingCheckBox.setSelected((selectedMember.getSpInf13() == 1));
//		this.initialCallCheckBox.setSelected((selectedMember.getSpInf14() == 1));
//		this.returnVisitCheckBox.setSelected((selectedMember.getSpInf15() == 1));
//		this.bibleStudyCheckBox.setSelected((selectedMember.getSpInf16() == 1));
//		this.talkCheckBox.setSelected((selectedMember.getSpInf17() == 1));
//
//		this.markedCheckBox.setSelected((selectedMember.getSpInf18() == 1));
//		this.disfellowshippedCheckBox.setSelected((selectedMember.getSpInf19() == 1));
//
//		this.audioMicMidweekCheckBox.setSelected((selectedMember.getSpInf20() == 1));
//		this.audioMicWeekendCheckBox.setSelected((selectedMember.getSpInf21() == 1));
//		this.audioPos1MidweekCheckBox.setSelected((selectedMember.getSpInf22() == 1));
//		this.audioPos1WeekendCheckBox.setSelected((selectedMember.getSpInf23() == 1));
//		this.audioPos2MidweekCheckBox.setSelected((selectedMember.getSpInf24() == 1));
//		this.audioPos2WeekendCheckBox.setSelected((selectedMember.getSpInf25() == 1));
//
//		this.readerCongregationBibleStudyCheckBox.setSelected((selectedMember.getSpInf26() == 1));
//		this.readerWatchtowerCheckBox.setSelected((selectedMember.getSpInf27() == 1));
//
//		this.usciereZone1MidweekCheckBox.setSelected((selectedMember.getSpInf28() == 1));
//		this.usciereZone1WeekendCheckBox.setSelected((selectedMember.getSpInf29() == 1));
//
//		this.treasuresTalkCheckBox.setSelected((selectedMember.getSpInf30() == 1));
//		this.diggingCheckBox.setSelected((selectedMember.getSpInf31() == 1));
//		this.christiansCheckBox.setSelected((selectedMember.getSpInf32() == 1));
//		this.presidentMidweekCheckBox.setSelected((selectedMember.getSpInf33() == 1));
//		this.prayStartMidweekCheckBox.setSelected((selectedMember.getSpInf34() == 1));
//		this.prayEndMidweekCheckBox.setSelected((selectedMember.getSpInf35() == 1));
//		this.presidentWeekendCheckBox.setSelected((selectedMember.getSpInf36() == 1));
//		this.prayEndWeekendCheckBox.setSelected((selectedMember.getSpInf37() == 1));
//		this.inactiveCheckBox.setSelected((selectedMember.getSpInf38() == 1));
//
//		this.bibleStudyCongregationCheckBox.setSelected((selectedMember.getSpInf42() == 1));
//		this.watchtowerStudyCheckBox.setSelected((selectedMember.getSpInf43() == 1));
//
//		this.watchtowerSubstituteStudyCheckBox.setSelected((selectedMember.getSpInf44() == 1));
//		this.publicSpeakerInternCheckBox.setSelected((selectedMember.getSpInf45() == 1));
//		this.publicSpeakerExternCheckBox.setSelected((selectedMember.getSpInf46() == 1));
//
//		this.otherSheepCheckBox.setSelected((this.selectedMember.getSpInf48() == 1));
//		this.anointedCheckBox.setSelected((this.selectedMember.getSpInf49() == 1));
//		this.excludeFromNaturalDisastersListCheckBox.setSelected((this.selectedMember.getSpInf51() == 1));
//
//		this.audioPos3MidweekCheckBox.setSelected((this.selectedMember.getSpInf54() == 1));
//		this.audioPos3WeekendCheckBox.setSelected((this.selectedMember.getSpInf55() == 1));
//		this.usciereZone2MidweekCheckBox.setSelected((this.selectedMember.getSpInf56() == 1));
//		this.usciereZone2WeekendCheckBox.setSelected((this.selectedMember.getSpInf57() == 1));
//		this.usciereZone3MidweekCheckBox.setSelected((this.selectedMember.getSpInf58() == 1));
//		this.usciereZone3WeekendCheckBox.setSelected((this.selectedMember.getSpInf59() == 1));
//		
//		this.presidentSecondHallCheckBox.setSelected((this.selectedMember.getSpInf60() == 1));
//		this.serviceMeetingCongrCheckBox.setSelected((this.selectedMember.getSpInf61() == 1));
//		this.serviceMeetingGroupCheckBox.setSelected((this.selectedMember.getSpInf62() == 1));
	}

	private void listeners() {

		listenerNameTextField();

//		this.studentCheckBox.selectedProperty().addListener((obs, oldV, newV) -> checkBoxGroups(newV, false,
//				this.studentCheckBox, this.unbaptizedPublisherCheckBox, this.baptizedPublisherCheckBox));
//
//		this.unbaptizedPublisherCheckBox.selectedProperty().addListener((obs, oldV, newV) -> checkBoxGroups(newV, false,
//				this.unbaptizedPublisherCheckBox, this.studentCheckBox, this.baptizedPublisherCheckBox));
//
//		this.baptizedPublisherCheckBox.selectedProperty().addListener((obs, oldV, newV) -> checkBoxGroups(newV, false,
//				this.baptizedPublisherCheckBox, this.studentCheckBox, this.unbaptizedPublisherCheckBox));
//
//		this.anointedCheckBox.selectedProperty().addListener(
//				(obs, oldV, newV) -> checkBoxGroups(newV, true, this.anointedCheckBox, this.otherSheepCheckBox));
//
//		this.otherSheepCheckBox.selectedProperty().addListener(
//				(obs, oldV, newV) -> checkBoxGroups(newV, true, this.otherSheepCheckBox, this.anointedCheckBox));

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

//			String spInf6 = !this.studentCheckBox.isSelected() ? "0" : "1";
//			String spInf7 = !this.unbaptizedPublisherCheckBox.isSelected() ? "0" : "1";
//			String spInf8 = !this.baptizedPublisherCheckBox.isSelected() ? "0" : "1";
//
//			String spInf9 = !this.ministerialServantCheckBox.isSelected() ? "0" : "1";
//			String spInf10 = !this.elderCheckBox.isSelected() ? "0" : "1";
//			String spInf11 = !this.regularPioneerCheckBox.isSelected() ? "0" : "1";
//			String spInf12 = !this.specialPioneerCheckBox.isSelected() ? "0" : "1";
//
//			String spInf13 = !this.bibleReadingCheckBox.isSelected() ? "0" : "1";
//			String spInf14 = !this.initialCallCheckBox.isSelected() ? "0" : "1";
//			String spInf15 = !this.returnVisitCheckBox.isSelected() ? "0" : "1";
//			String spInf16 = !this.bibleStudyCheckBox.isSelected() ? "0" : "1";
//			String spInf17 = !this.talkCheckBox.isSelected() ? "0" : "1";
//
//			String spInf18 = !this.markedCheckBox.isSelected() ? "0" : "1";
//			String spInf19 = !this.disfellowshippedCheckBox.isSelected() ? "0" : "1";
//
//			String spInf20 = !this.audioMicMidweekCheckBox.isSelected() ? "0" : "1";
//			String spInf21 = !this.audioMicWeekendCheckBox.isSelected() ? "0" : "1";
//			String spInf22 = !this.audioPos1MidweekCheckBox.isSelected() ? "0" : "1";
//			String spInf23 = !this.audioPos1WeekendCheckBox.isSelected() ? "0" : "1";
//			String spInf24 = !this.audioPos2MidweekCheckBox.isSelected() ? "0" : "1";
//			String spInf25 = !this.audioPos2WeekendCheckBox.isSelected() ? "0" : "1";
//
//			String spInf26 = !this.readerCongregationBibleStudyCheckBox.isSelected() ? "0" : "1";
//			String spInf27 = !this.readerWatchtowerCheckBox.isSelected() ? "0" : "1";
//
//			String spInf28 = !this.usciereZone1MidweekCheckBox.isSelected() ? "0" : "1";
//			String spInf29 = !this.usciereZone1WeekendCheckBox.isSelected() ? "0" : "1";
//
//			String spInf30 = !this.treasuresTalkCheckBox.isSelected() ? "0" : "1";
//			String spInf31 = !this.diggingCheckBox.isSelected() ? "0" : "1";
//			String spInf32 = !this.christiansCheckBox.isSelected() ? "0" : "1";
//			String spInf33 = !this.presidentMidweekCheckBox.isSelected() ? "0" : "1";
//			String spInf34 = !this.prayStartMidweekCheckBox.isSelected() ? "0" : "1";
//			String spInf35 = !this.prayEndMidweekCheckBox.isSelected() ? "0" : "1";
//			String spInf36 = !this.presidentWeekendCheckBox.isSelected() ? "0" : "1";
//			String spInf37 = !this.prayEndWeekendCheckBox.isSelected() ? "0" : "1";
//			String spInf38 = !this.inactiveCheckBox.isSelected() ? "0" : "1";
//			String spInf40 = Crypt.encrypt(smartphoneTextField.getText(), settings.getDatabaseSecretKey());
//			String spInf41 = Crypt.encrypt(emailTextField.getText(), settings.getDatabaseSecretKey());
//			String spInf42 = !this.bibleStudyCongregationCheckBox.isSelected() ? "0" : "1";
//			String spInf43 = !this.watchtowerStudyCheckBox.isSelected() ? "0" : "1";
//			String spInf44 = !this.watchtowerSubstituteStudyCheckBox.isSelected() ? "0" : "1";
//			String spInf45 = !this.publicSpeakerInternCheckBox.isSelected() ? "0" : "1";
//			String spInf46 = !this.publicSpeakerExternCheckBox.isSelected() ? "0" : "1";
//			String spInf47 = monitorTextField.getText();
//
//			String spInf48 = !this.otherSheepCheckBox.isSelected() ? "0" : "1";
//			String spInf49 = !this.anointedCheckBox.isSelected() ? "0" : "1";
//			String spInf51 = !this.excludeFromNaturalDisastersListCheckBox.isSelected() ? "0" : "1";
//
//			LocalDate dateOfBaptism = this.dateOfBaptismDatePicker.getValue();
//			String spInf53 = dateOfBaptism != null ? Crypt.encrypt(dateOfBaptism.toString(), secretKey)
//					: Crypt.encrypt("", secretKey);
//
//			String spInf54 = !this.audioPos3MidweekCheckBox.isSelected() ? "0" : "1";
//			String spInf55 = !this.audioPos3WeekendCheckBox.isSelected() ? "0" : "1";
//			String spInf56 = !this.usciereZone2MidweekCheckBox.isSelected() ? "0" : "1";
//			String spInf57 = !this.usciereZone2WeekendCheckBox.isSelected() ? "0" : "1";
//			String spInf58 = !this.usciereZone3MidweekCheckBox.isSelected() ? "0" : "1";
//			String spInf59 = !this.usciereZone3WeekendCheckBox.isSelected() ? "0" : "1";
//
//			String spInf60 = !this.presidentSecondHallCheckBox.isSelected() ? "0" : "1";
//			String spInf61 = !this.serviceMeetingCongrCheckBox.isSelected() ? "0" : "1";
//			String spInf62 = !this.serviceMeetingGroupCheckBox.isSelected() ? "0" : "1";

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

//		if (this.dateOfBaptismDatePicker.getValue() != null)
//			if (!this.baptizedPublisherCheckBox.isSelected()) {
//
//				this.application.getAlertBuilder2().error(this.ownerStage,
//						this.language.getString("congregation.memberseditor.error.dateofbaptism"));
//
//				return false;
//			}

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

		Tooltip saveTooltip = new Tooltip(this.language.getString("territoryeditor.button.tooltip.save"));
		saveTooltip.getStyleClass().add("tooltip_001");
		this.saveButton.setTooltip(saveTooltip);
		this.saveButton.setText("");
		this.saveButton.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.SAVE));

		Tooltip imagesTabTooltip = new Tooltip(this.language.getString("territoryeditor.tab.tooltip.images"));
		imagesTabTooltip.getStyleClass().add("tooltip_001");
		this.imagesTab.setTooltip(imagesTabTooltip);
		this.imagesTab.setText("");
		this.imagesTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.GALLERY));

		String imageFormat = this.language.getString("territoryeditor.label.imageformat");
		String imageTitleFormat = this.language.getString("territoryeditor.label.imagenameformat");

		this.image1Label.setText(String.format(imageFormat, 1));
		this.image1TitleLabel.setText(String.format(imageTitleFormat, 1));
		this.image2Label.setText(String.format(imageFormat, 2));
		this.image2TitleLabel.setText(String.format(imageTitleFormat, 2));
		this.image3Label.setText(String.format(imageFormat, 3));
		this.image3TitleLabel.setText(String.format(imageTitleFormat, 3));
		this.image4Label.setText(String.format(imageFormat, 4));
		this.image4TitleLabel.setText(String.format(imageTitleFormat, 4));
		this.image5Label.setText(String.format(imageFormat, 5));
		this.image5TitleLabel.setText(String.format(imageTitleFormat, 5));

		String docFormat = this.language.getString("territoryeditor.label.docformat");
		String docTitleFormat = this.language.getString("territoryeditor.label.docnameformat");

		this.doc1Label.setText(String.format(docFormat, 1));
		this.doc1TitleLabel.setText(String.format(docTitleFormat, 1));
		this.doc2Label.setText(String.format(docFormat, 2));
		this.doc2TitleLabel.setText(String.format(docTitleFormat, 2));
		this.doc3Label.setText(String.format(docFormat, 3));
		this.doc3TitleLabel.setText(String.format(docTitleFormat, 3));
		this.doc4Label.setText(String.format(docFormat, 4));
		this.doc4TitleLabel.setText(String.format(docTitleFormat, 4));
		this.doc5Label.setText(String.format(docFormat, 5));
		this.doc5TitleLabel.setText(String.format(docTitleFormat, 5));

		Tooltip docsTooltip = new Tooltip(this.language.getString("congregation.memberseditor.tooltip.contacts"));
		docsTooltip.getStyleClass().add("tooltip_001");
		this.docsTab.setTooltip(docsTooltip);
		this.docsTab.setText("");
		this.docsTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.DOCS));
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
