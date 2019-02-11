package com.sm.net.sp.view.home.user.menu.sergroups;

import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.actions.Actions;
import com.sm.net.sp.model.Family;
import com.sm.net.sp.model.SerGroup;
import com.sm.net.sp.model.UpdateDataAdapter;
import com.sm.net.sp.settings.Settings;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class UserMenuSerGroupsEditor extends UpdateDataAdapter {

	@FXML
	private Label titleLabel;

	@FXML
	private Label serGroupsNameLabel;

	@FXML
	private TextField serGroupsNameTextField;

	@FXML
	private Label serGroupsOverseerLabel;

	@FXML
	private ComboBox<String> serGroupsOverseerComboBox;

	@FXML
	private Label serGroupsAssistantLabel;

	@FXML
	private ComboBox<String> serGroupsAssistantComboBox;

	@FXML
	private Label familiesLabel;

	@FXML
	private TableView<Family> familiesTableView;

	@FXML
	private TableColumn<Family, Integer> familiesIDTableColumn;

	@FXML
	private TableColumn<Family, String> familiesNameTableColumn;

	@FXML
	private TableColumn<Family, Integer> familiesMembersTableColumn;

	@FXML
	private Button serGroupsAddFamily;

	@FXML
	private Button serGroupsRemoveFamily;

	@FXML
	private Label serGroupsFamiliesLabel;

	@FXML
	private TableView<Family> serGroupsFamiliesTableView;

	@FXML
	private TableColumn<Family, Integer> serGroupsFamiliesIDTableColumn;

	@FXML
	private TableColumn<Family, String> serGroupsFamiliesNameTableColumn;

	@FXML
	private TableColumn<Family, Integer> serGroupsFamiliesMembersTableColumn;

	@FXML
	private Button saveButton;

	private Settings settings;
	private Language language;
	private Stage ownerStage;
	private TabPane serGroupsTabPane;
	private Tab serGroupTab;
	private UserMenuSerGroupsList ownerCtrl;
	private SerGroup selectedSerGroups;

	private ObservableList<Family> familiesList;
	private ObservableList<Family> familiesAvailableList;
	private ObservableList<Family> serGroupsFamilyList;

	@FXML
	private void initialize() {
		styleClasses();
		cellValueFactory();
	}

	private void cellValueFactory() {

		familiesIDTableColumn.setCellValueFactory(cellData -> cellData.getValue().spFamIDProperty().asObject());
		familiesNameTableColumn.setCellValueFactory(cellData -> cellData.getValue().spInf1DecryptedProperty());
		familiesMembersTableColumn
				.setCellValueFactory(cellData -> cellData.getValue().spFamMembersProperty().asObject());

		serGroupsFamiliesIDTableColumn
				.setCellValueFactory(cellData -> cellData.getValue().spFamIDProperty().asObject());
		serGroupsFamiliesNameTableColumn.setCellValueFactory(cellData -> cellData.getValue().spInf1DecryptedProperty());
		serGroupsFamiliesMembersTableColumn
				.setCellValueFactory(cellData -> cellData.getValue().spFamMembersProperty().asObject());
	}

	private void styleClasses() {

		titleLabel.getStyleClass().add("labelStyle2");
		serGroupsNameLabel.getStyleClass().add("labelStyle1");
		serGroupsOverseerLabel.getStyleClass().add("labelStyle1");
		serGroupsAssistantLabel.getStyleClass().add("labelStyle1");

		familiesLabel.getStyleClass().add("labelStyle1");
		serGroupsFamiliesLabel.getStyleClass().add("labelStyle1");

		serGroupsNameTextField.getStyleClass().add("textFieldStyle1");
		serGroupsOverseerComboBox.getStyleClass().add("comboBoxStyle1");
		serGroupsAssistantComboBox.getStyleClass().add("comboBoxStyle1");

		familiesTableView.getStyleClass().add("tableViewStyle1");

		serGroupsFamiliesTableView.getStyleClass().add("tableViewStyle1");

		serGroupsAddFamily.getStyleClass().add("buttonStyle2");
		serGroupsRemoveFamily.getStyleClass().add("buttonStyle2");
		saveButton.getStyleClass().add("buttonStyle1");
	}

	public void objectInitialize() {
		initValue();
		viewUpdate();
		listeners();
	}

	private void initValue() {

		familiesListBuild();
		serGroupsMembersListBuild();

		// if (selectedFamily != null) {
		//
		// this.serGroupsNameTextField.setText(selectedFamily.getSpInf1Decrypted());
		// //
		// this.serGroupsOverseerComboBox.setText(selectedFamily.getSpInf2Decrypted());
		// }
	}

	private void familiesListBuild() {
		familiesList = FXCollections.observableArrayList();
		Actions.getAllFamilies(settings, ownerStage, this);
	}

	@Override
	public void updateFamilies(ObservableList<Family> list) {
		super.updateFamilies(list);

		this.familiesList = list;
		familiesAvaiableListBuild();
	}

	private void familiesAvaiableListBuild() {

		familiesAvailableList = FXCollections.observableArrayList();

		for (Family family : familiesList)
			if (family.getSpInf6() == -1)
				familiesAvailableList.add(family);

		familiesAvailableList.sort((a, b) -> (a.getSpInf1Decrypted().compareTo(b.getSpInf1Decrypted())));
		familiesTableView.setItems(familiesAvailableList);
	}

	private void serGroupsMembersListBuild() {

		serGroupsFamilyList = FXCollections.observableArrayList();
		//
		// if (selectedSerGroups != null) {
		// for (Family family : familiesList)
		// if (family.getSpInf6() == selectedSerGroups.getSpInf6())
		// serGroupsMembersList.add(family);
		// }
		//
		serGroupsFamiliesTableView.setItems(serGroupsFamilyList);
	}

	private void listeners() {
		listenerSerGroupAddFamily();
		listenerSerGroupRemoveFamily();
		listenerSaveButton();
	}

	private void listenerSerGroupAddFamily() {
		serGroupsAddFamily.setOnAction(event -> serGroupAddFamily());
	}

	private void listenerSerGroupRemoveFamily() {
		serGroupsRemoveFamily.setOnAction(event -> serGroupRemoveFamily());
	}

	private void serGroupAddFamily() {

		if (familiesTableView.getSelectionModel().getSelectedIndex() > -1) {

			Family family = familiesTableView.getSelectionModel().getSelectedItem();
			familiesTableView.getItems().remove(family);

			serGroupsFamilyList.add(family);
			serGroupsFamilyList.sort((a, b) -> (a.getSpInf1Decrypted().compareTo(b.getSpInf1Decrypted())));
		}
	}

	private void serGroupRemoveFamily() {

		if (serGroupsFamiliesTableView.getSelectionModel().getSelectedIndex() > -1) {

			Family family = serGroupsFamiliesTableView.getSelectionModel().getSelectedItem();
			serGroupsFamiliesTableView.getItems().remove(family);

			familiesAvailableList.add(family);
			familiesAvailableList.sort((a, b) -> (a.getSpInf1Decrypted().compareTo(b.getSpInf1Decrypted())));
		}
	}

	private void listenerSaveButton() {
		saveButton.setOnAction(event -> saveSerGroup());
	}

	private void saveSerGroup() {

		// if (checkFields()) {
		//
		// String spInf1 = Crypt.encrypt(serGroupsNameTextField.getText(),
		// settings.getDatabaseSecretKey());
		// // String spInf2 =
		// // Crypt.encrypt(serGroupsOverseerComboBox.getText(),
		// // settings.getDatabaseSecretKey());
		// // String spInf3 = Crypt.encrypt(familyNummerTextField.getText(),
		// // settings.getDatabaseSecretKey());
		// // String spInf4 = Crypt.encrypt(familyPostCodeTextField.getText(),
		// // settings.getDatabaseSecretKey());
		// // String spInf5 = Crypt.encrypt(familyCityTextField.getText(),
		// // settings.getDatabaseSecretKey());
		//
		// String idToRemove = "";
		//
		// for (Member member : familiesAvailableList)
		// if (member.getSpInf5() != -1)
		// idToRemove += idToRemove.isEmpty() ? member.getSpMemberID() : ", " +
		// member.getSpMemberID();
		//
		// String idToSet = "";
		//
		// for (Member member : serGroupsMembersList)
		// if (member.getSpInf5() == -1)
		// idToSet += idToSet.isEmpty() ? member.getSpMemberID() : ", " +
		// member.getSpMemberID();
		//
		// // if (selectedFamily != null)
		// // editFamily(spInf1, spInf2, spInf3, spInf4, spInf5, idToRemove,
		// // idToSet);
		// // else
		// // newFamily(spInf1, spInf2, spInf3, spInf4, spInf5, idToRemove,
		// // idToSet);
		// } else
		// new AlertDesigner(language.getStringWithNewLine("TEXT0004"),
		// ownerStage, AlertType.ERROR,
		// Meta.Application.getFullTitle(), Meta.Resources.ICON).show();
	}

	private void newSerGroup(String spInf1, String spInf2, String spInf3, String spInf4, String spInf5,
			String idToRemove, String idToSet) {

		// Actions.insertFamily(spInf1, spInf2, spInf3, spInf4, spInf5,
		// idToRemove, idToSet, settings, ownerStage,
		// congrTabPane, newFamilyTab, familiesTab, ownerCtrl);
	}

	private void editSerGroup(String spInf1, String spInf2, String spInf3, String spInf4, String spInf5,
			String idToRemove, String idToSet) {

		// Actions.updateFamily(String.valueOf(selectedFamily.getSpFamID()),
		// spInf1, spInf2, spInf3, spInf4, spInf5,
		// idToRemove, idToSet, settings, ownerStage, congrTabPane,
		// newFamilyTab, familiesTab, ownerCtrl);
	}

	private boolean checkFields() {

		boolean status = true;

		// if (serGroupsNameTextField.getText().isEmpty())
		// status = false;
		//
		// if (status)
		// if (serGroupsMembersList.size() == 0)
		// status = false;

		return status;
	}

	private void viewUpdate() {

		this.language = settings.getLanguage();

		if (this.selectedSerGroups != null)
			titleLabel.setText(language.getString("TEXT0023"));
		else
			titleLabel.setText(language.getString("TEXT0039"));

		serGroupsNameLabel.setText(language.getString("TEXT0036"));
		serGroupsOverseerLabel.setText(language.getString("TEXT0037"));
		serGroupsAssistantLabel.setText(language.getString("TEXT0038"));
		familiesLabel.setText(language.getString("TEXT0040"));
		familiesIDTableColumn.setText(language.getString("TEXT0005"));
		familiesIDTableColumn.setMinWidth(50);
		familiesIDTableColumn.setMaxWidth(50);
		familiesIDTableColumn.setResizable(false);
		familiesNameTableColumn.setText(language.getString("TEXT0014"));
		familiesMembersTableColumn.setText(language.getString("TEXT0011"));
		serGroupsFamiliesLabel.setText(language.getString("TEXT0041"));
		serGroupsFamiliesIDTableColumn.setText(language.getString("TEXT0005"));
		serGroupsFamiliesIDTableColumn.setMinWidth(50);
		serGroupsFamiliesIDTableColumn.setMaxWidth(50);
		serGroupsFamiliesIDTableColumn.setResizable(false);
		serGroupsFamiliesNameTableColumn.setText(language.getString("TEXT0014"));
		serGroupsFamiliesMembersTableColumn.setText(language.getString("TEXT0011"));

		serGroupsAddFamily.setText("");
		serGroupsAddFamily.setGraphic(new ImageView(Meta.Resources.ARROW_FRONT));
		serGroupsRemoveFamily.setText("");
		serGroupsRemoveFamily.setGraphic(new ImageView(Meta.Resources.ARROW_BACK));
		saveButton.setGraphic(new ImageView(Meta.Resources.SAVE));
		saveButton.setText(language.getString("TEXT0022"));
	}

	public Settings getSettings() {
		return settings;
	}

	public void setSettings(Settings settings) {
		this.settings = settings;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public Stage getOwnerStage() {
		return ownerStage;
	}

	public void setOwnerStage(Stage ownerStage) {
		this.ownerStage = ownerStage;
	}

	public UserMenuSerGroupsList getOwnerCtrl() {
		return ownerCtrl;
	}

	public void setOwnerCtrl(UserMenuSerGroupsList ownerCtrl) {
		this.ownerCtrl = ownerCtrl;
	}

	public SerGroup getSelectedSerGroups() {
		return selectedSerGroups;
	}

	public void setSelectedSerGroups(SerGroup selectedSerGroups) {
		this.selectedSerGroups = selectedSerGroups;
	}

	public TabPane getSerGroupsTabPane() {
		return serGroupsTabPane;
	}

	public void setSerGroupsTabPane(TabPane serGroupsTabPane) {
		this.serGroupsTabPane = serGroupsTabPane;
	}

	public Tab getSerGroupTab() {
		return serGroupTab;
	}

	public void setSerGroupTab(Tab serGroupTab) {
		this.serGroupTab = serGroupTab;
	}
}
