package com.sm.net.sp.view.home.user.menu.territory;

import java.math.BigDecimal;
import java.util.HashMap;

import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.model.TerritoryObj;
import com.sm.net.sp.settings.Settings;
import com.sm.net.sp.view.SupportPlannerView;
import com.sm.net.sp.view.home.user.menu.territory.task.TerritorySaveTask;
import com.smnet.core.task.TaskManager;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
	private Label territoryViewerIDLabel;
	@FXML
	private TextField territoryViewerIDTextField;

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
	private Tab newTab;
	private Tab membersTab;
	private Territory ownerCtrl;

	private TerritoryObj selectedTerritory;
	private SupportPlannerView application;

//	private boolean listenerCheckFields;

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
		this.territoryViewerIDLabel.getStyleClass().add("label_set_001");

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
		this.territoryViewerIDTextField.getStyleClass().add("text_field_001");

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

//		this.listenerCheckFields = false;

		initValue();
		viewUpdate();
		listeners();

//		this.listenerCheckFields = true;
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
			this.territoryViewerIDTextField.setText(this.selectedTerritory.getSpInf31());

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

			// I campi da 32 a 40 non sono ancora utilizzati
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

//	private void checkBoxGroups(Boolean newV, Boolean notNull, CheckBox edited, CheckBox... others) {
//
//		if (this.listenerCheckFields) {
//
//			this.listenerCheckFields = false;
//
//			if (newV) {
//				for (CheckBox cb : others)
//					if (cb.isSelected())
//						cb.setSelected(false);
//			} else {
//				if (notNull)
//					edited.setSelected(true);
//			}
//
//			this.listenerCheckFields = true;
//		}
//	}

	private void save() {

		if (checkFields()) {

			TerritoryObj territoryObj = TerritoryObj.newInstanceByView(this,
					this.application.getSettings().getDatabaseSecretKey());

			String waitMessage = this.language.getString("territoryeditor.wait.save");
			TaskManager.run(this.application.getAlertBuilder2(), this.ownerStage, waitMessage,
					new TerritorySaveTask(this.application.getAlertBuilder2(), this.settings, this.ownerStage,
							territoryObj, this.ownerCtrl, this.newTab));
		}
	}

	private boolean checkFields() {

		if (this.territoryNumberTextField.getText().isEmpty()) {

			this.application.getAlertBuilder2().error(this.ownerStage,
					this.language.getString("territoryeditor.error.emptynumber"));

			return false;
		}

		try {

			String number = this.territoryNumberTextField.getText();
			number = number.replace(',', '.');
			new BigDecimal(number);

		} catch (Exception e) {

			this.application.getAlertBuilder2().error(this.ownerStage,
					this.language.getString("territoryeditor.error.validnumber"));

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

		try {

			Integer.valueOf(territoryZipCodeTextField.getText());

		} catch (Exception e) {

			this.application.getAlertBuilder2().error(this.ownerStage,
					this.language.getString("territoryeditor.error.validzipcode"));

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
		this.territoryViewerIDLabel.setText(this.language.getString("territoryeditor.label.viewerid"));

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

	public Tab getNewTab() {
		return newTab;
	}

	public void setNewTab(Tab newTab) {
		this.newTab = newTab;
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

	public TextField getTerritoryNumberTextField() {
		return territoryNumberTextField;
	}

	public void setTerritoryNumberTextField(TextField territoryNumberTextField) {
		this.territoryNumberTextField = territoryNumberTextField;
	}

	public TextField getTerritoryNameTextField() {
		return territoryNameTextField;
	}

	public void setTerritoryNameTextField(TextField territoryNameTextField) {
		this.territoryNameTextField = territoryNameTextField;
	}

	public TextField getTerritoryCoordinatesTextField() {
		return territoryCoordinatesTextField;
	}

	public void setTerritoryCoordinatesTextField(TextField territoryCoordinatesTextField) {
		this.territoryCoordinatesTextField = territoryCoordinatesTextField;
	}

	public TextField getTerritoryLandTextField() {
		return territoryLandTextField;
	}

	public void setTerritoryLandTextField(TextField territoryLandTextField) {
		this.territoryLandTextField = territoryLandTextField;
	}

	public TextField getTerritoryLandkreisTextField() {
		return territoryLandkreisTextField;
	}

	public void setTerritoryLandkreisTextField(TextField territoryLandkreisTextField) {
		this.territoryLandkreisTextField = territoryLandkreisTextField;
	}

	public TextField getTerritoryKreisstadtTextField() {
		return territoryKreisstadtTextField;
	}

	public void setTerritoryKreisstadtTextField(TextField territoryKreisstadtTextField) {
		this.territoryKreisstadtTextField = territoryKreisstadtTextField;
	}

	public TextField getTerritoryOrtTextField() {
		return territoryOrtTextField;
	}

	public void setTerritoryOrtTextField(TextField territoryOrtTextField) {
		this.territoryOrtTextField = territoryOrtTextField;
	}

	public TextField getTerritoryZipCodeTextField() {
		return territoryZipCodeTextField;
	}

	public void setTerritoryZipCodeTextField(TextField territoryZipCodeTextField) {
		this.territoryZipCodeTextField = territoryZipCodeTextField;
	}

	public TextField getTerritoryOrtsteilTextField() {
		return territoryOrtsteilTextField;
	}

	public void setTerritoryOrtsteilTextField(TextField territoryOrtsteilTextField) {
		this.territoryOrtsteilTextField = territoryOrtsteilTextField;
	}

	public TextField getTerritoryMyMapsIDTextField() {
		return territoryMyMapsIDTextField;
	}

	public void setTerritoryMyMapsIDTextField(TextField territoryMyMapsIDTextField) {
		this.territoryMyMapsIDTextField = territoryMyMapsIDTextField;
	}

	public TextField getTerritoryViewerIDTextField() {
		return territoryViewerIDTextField;
	}

	public void setTerritoryViewerIDTextField(TextField territoryViewerIDTextField) {
		this.territoryViewerIDTextField = territoryViewerIDTextField;
	}

	public TextField getImage1TextField() {
		return image1TextField;
	}

	public void setImage1TextField(TextField image1TextField) {
		this.image1TextField = image1TextField;
	}

	public TextField getImage2TextField() {
		return image2TextField;
	}

	public void setImage2TextField(TextField image2TextField) {
		this.image2TextField = image2TextField;
	}

	public TextField getImage3TextField() {
		return image3TextField;
	}

	public void setImage3TextField(TextField image3TextField) {
		this.image3TextField = image3TextField;
	}

	public TextField getImage4TextField() {
		return image4TextField;
	}

	public void setImage4TextField(TextField image4TextField) {
		this.image4TextField = image4TextField;
	}

	public TextField getImage5TextField() {
		return image5TextField;
	}

	public void setImage5TextField(TextField image5TextField) {
		this.image5TextField = image5TextField;
	}

	public TextField getImage1TitleTextField() {
		return image1TitleTextField;
	}

	public void setImage1TitleTextField(TextField image1TitleTextField) {
		this.image1TitleTextField = image1TitleTextField;
	}

	public TextField getImage2TitleTextField() {
		return image2TitleTextField;
	}

	public void setImage2TitleTextField(TextField image2TitleTextField) {
		this.image2TitleTextField = image2TitleTextField;
	}

	public TextField getImage3TitleTextField() {
		return image3TitleTextField;
	}

	public void setImage3TitleTextField(TextField image3TitleTextField) {
		this.image3TitleTextField = image3TitleTextField;
	}

	public TextField getImage4TitleTextField() {
		return image4TitleTextField;
	}

	public void setImage4TitleTextField(TextField image4TitleTextField) {
		this.image4TitleTextField = image4TitleTextField;
	}

	public TextField getImage5TitleTextField() {
		return image5TitleTextField;
	}

	public void setImage5TitleTextField(TextField image5TitleTextField) {
		this.image5TitleTextField = image5TitleTextField;
	}

	public TextField getDoc1TextField() {
		return doc1TextField;
	}

	public void setDoc1TextField(TextField doc1TextField) {
		this.doc1TextField = doc1TextField;
	}

	public TextField getDoc2TextField() {
		return doc2TextField;
	}

	public void setDoc2TextField(TextField doc2TextField) {
		this.doc2TextField = doc2TextField;
	}

	public TextField getDoc3TextField() {
		return doc3TextField;
	}

	public void setDoc3TextField(TextField doc3TextField) {
		this.doc3TextField = doc3TextField;
	}

	public TextField getDoc4TextField() {
		return doc4TextField;
	}

	public void setDoc4TextField(TextField doc4TextField) {
		this.doc4TextField = doc4TextField;
	}

	public TextField getDoc5TextField() {
		return doc5TextField;
	}

	public void setDoc5TextField(TextField doc5TextField) {
		this.doc5TextField = doc5TextField;
	}

	public TextField getDoc1TitleTextField() {
		return doc1TitleTextField;
	}

	public void setDoc1TitleTextField(TextField doc1TitleTextField) {
		this.doc1TitleTextField = doc1TitleTextField;
	}

	public TextField getDoc2TitleTextField() {
		return doc2TitleTextField;
	}

	public void setDoc2TitleTextField(TextField doc2TitleTextField) {
		this.doc2TitleTextField = doc2TitleTextField;
	}

	public TextField getDoc3TitleTextField() {
		return doc3TitleTextField;
	}

	public void setDoc3TitleTextField(TextField doc3TitleTextField) {
		this.doc3TitleTextField = doc3TitleTextField;
	}

	public TextField getDoc4TitleTextField() {
		return doc4TitleTextField;
	}

	public void setDoc4TitleTextField(TextField doc4TitleTextField) {
		this.doc4TitleTextField = doc4TitleTextField;
	}

	public TextField getDoc5TitleTextField() {
		return doc5TitleTextField;
	}

	public void setDoc5TitleTextField(TextField doc5TitleTextField) {
		this.doc5TitleTextField = doc5TitleTextField;
	}

	public TerritoryObj getSelectedTerritory() {
		return selectedTerritory;
	}

	public void setSelectedTerritory(TerritoryObj selectedTerritory) {
		this.selectedTerritory = selectedTerritory;
	}
}
