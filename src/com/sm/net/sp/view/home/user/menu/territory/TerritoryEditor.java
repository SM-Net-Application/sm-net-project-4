package com.sm.net.sp.view.home.user.menu.territory;

import java.util.HashMap;

import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.model.Member;
import com.sm.net.sp.model.TerritoryObj;
import com.sm.net.sp.settings.Settings;
import com.sm.net.sp.view.SupportPlannerView;

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

	private TerritoryObj selectedTerritory;
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

		if (this.selectedTerritory != null) {

			this.territoryNumberTextField.setText(this.selectedTerritory.getSpInf7().toString());
			this.territoryNameTextField.setText(this.selectedTerritory.getSpInf8());
			this.territoryCoordinatesTextField.setText(this.selectedTerritory.getSpInf9());

			this.territoryLandTextField.setText(this.selectedTerritory.getSpInf1());
			this.territoryLandkreisTextField.setText(this.selectedTerritory.getSpInf2());
			this.territoryKreisstadtTextField.setText(this.selectedTerritory.getSpInf3());
			this.territoryOrtTextField.setText(this.selectedTerritory.getSpInf4());
			this.territoryZipCodeTextField.setText(String.valueOf(this.selectedTerritory.getSpInf5()));
			this.territoryOrtsteilTextField.setText(this.selectedTerritory.getSpInf6());
			this.territoryMyMapsIDTextField.setText(this.selectedTerritory.getSpInf10());

			this.image1TextField.setText(this.selectedTerritory.getSpInf11());
			this.image1TitleTextField.setText(this.selectedTerritory.getSpInf12());
			this.image2TextField.setText(this.selectedTerritory.getSpInf13());
			this.image2TitleTextField.setText(this.selectedTerritory.getSpInf14());
			this.image3TextField.setText(this.selectedTerritory.getSpInf15());
			this.image3TitleTextField.setText(this.selectedTerritory.getSpInf16());
			this.image4TextField.setText(this.selectedTerritory.getSpInf17());
			this.image4TitleTextField.setText(this.selectedTerritory.getSpInf18());
			this.image5TextField.setText(this.selectedTerritory.getSpInf19());
			this.image5TitleTextField.setText(this.selectedTerritory.getSpInf20());

			this.doc1TextField.setText(this.selectedTerritory.getSpInf21());
			this.doc1TitleTextField.setText(this.selectedTerritory.getSpInf22());
			this.doc2TextField.setText(this.selectedTerritory.getSpInf23());
			this.doc2TitleTextField.setText(this.selectedTerritory.getSpInf24());
			this.doc3TextField.setText(this.selectedTerritory.getSpInf25());
			this.doc3TitleTextField.setText(this.selectedTerritory.getSpInf26());
			this.doc4TextField.setText(this.selectedTerritory.getSpInf27());
			this.doc4TitleTextField.setText(this.selectedTerritory.getSpInf28());
			this.doc5TextField.setText(this.selectedTerritory.getSpInf29());
			this.doc5TitleTextField.setText(this.selectedTerritory.getSpInf30());

			// I campi da 31 a 40 non sono ancora utilizzati
		}
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

		this.saveButton.setOnAction(event -> save());
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

	private void save() {

		if (checkFields()) {

			if (this.selectedTerritory != null) {

//				// editWeek
//
//				WeekUsciere week = WeekUsciere.newInstanceByView(this);
//				week.setUscID(this.selectedWeek.getUscID());
//
//				String waitMessage = this.language.getString("memorialeditor.wait.save");
//				TaskManager.run(this.application.getAlertBuilder2(), this.ownerStage, waitMessage,
//						new WeekUsciereSaveTask(this.application.getAlertBuilder2(), this.settings, this.ownerStage,
//								week, this.ownerCtrl, this.thisTab));
//
			} else {
//
//				// newWeek
//
//				WeekUsciere week = WeekUsciere.newInstanceByView(this);
//
//				String waitMessage = this.language.getString("memorialeditor.wait.save");
//				TaskManager.run(this.application.getAlertBuilder2(), this.ownerStage, waitMessage,
//						new WeekUsciereSaveTask(this.application.getAlertBuilder2(), this.settings, this.ownerStage,
//								week, this.ownerCtrl, this.thisTab));
			}
		}
	}

	private boolean checkFields() {

		if (this.territoryNumberTextField.getText().isEmpty()) {

			this.application.getAlertBuilder2().error(this.ownerStage,
					this.language.getString("territoryeditor.error.emptynumber"));

			return false;
		}

		if (this.territoryNameTextField.getText().isEmpty()) {

			this.application.getAlertBuilder2().error(this.ownerStage,
					this.language.getString("territoryeditor.error.emptyname"));

			return false;
		}

		if (this.territoryZipCodeTextField.getText().isEmpty()) {

			this.application.getAlertBuilder2().error(this.ownerStage,
					this.language.getString("territoryeditor.error.emptyzipcode"));

			return false;
		}

		return true;
	}

	private void listenerNameTextField() {

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

		this.territoryMyMapsIDLabel.setText(this.language.getString("territoryeditor.label.mymapsid"));

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
