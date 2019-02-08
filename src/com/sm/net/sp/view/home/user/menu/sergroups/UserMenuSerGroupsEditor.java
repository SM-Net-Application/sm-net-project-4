package com.sm.net.sp.view.home.user.menu.sergroups;

import com.sm.net.javafx.AlertDesigner;
import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.actions.Actions;
import com.sm.net.sp.model.Family;
import com.sm.net.sp.model.Member;
import com.sm.net.sp.settings.Settings;
import com.sm.net.util.Crypt;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
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

public class UserMenuSerGroupsEditor {

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
	private TableView<Member> familiesTableView;

	@FXML
	private TableColumn<Member, Integer> familiesIDTableColumn;

	@FXML
	private TableColumn<Member, String> familiesNameTableColumn;

	@FXML
	private TableColumn<Member, String> familiesMembersTableColumn;

	@FXML
	private Button serGroupsAddFamily;

	@FXML
	private Button serGroupsRemoveFamily;

	@FXML
	private Label serGroupsFamiliesLabel;

	@FXML
	private TableView<Member> serGroupsFamiliesTableView;

	@FXML
	private TableColumn<Member, Integer> serGroupsFamiliesIDTableColumn;

	@FXML
	private TableColumn<Member, String> serGroupsFamiliesNameTableColumn;

	@FXML
	private TableColumn<Member, String> serGroupsFamiliesMembersTableColumn;

	@FXML
	private Button saveButton;

	private Settings settings;
	private Language language;
	private Stage ownerStage;
	private TabPane congrTabPane;
	private Tab newFamilyTab;
	private Tab familiesTab;
	private UserMenuSerGroupsList ownerCtrl;
	private Family selectedFamily;

	private ObservableList<Member> membersList;
	private ObservableList<Member> membersAvailableList;
	private ObservableList<Member> familyMembersList;

	@FXML
	private void initialize() {
		styleClasses();
		cellValueFactory();
	}

	private void cellValueFactory() {

		familiesIDTableColumn.setCellValueFactory(cellData -> cellData.getValue().spMemberIDProperty().asObject());
		familiesNameTableColumn.setCellValueFactory(cellData -> cellData.getValue().spInf2DecryptedProperty());
		familiesMembersTableColumn.setCellValueFactory(cellData -> cellData.getValue().spInf1DecryptedProperty());

		serGroupsFamiliesIDTableColumn
				.setCellValueFactory(cellData -> cellData.getValue().spMemberIDProperty().asObject());
		serGroupsFamiliesNameTableColumn.setCellValueFactory(cellData -> cellData.getValue().spInf2DecryptedProperty());
		serGroupsFamiliesMembersTableColumn
				.setCellValueFactory(cellData -> cellData.getValue().spInf1DecryptedProperty());
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

		// membersAvaiableListBuild();
		// familyMembersListBuild();

		// if (selectedFamily != null) {
		//
		// this.serGroupsNameTextField.setText(selectedFamily.getSpInf1Decrypted());
		// //
		// this.serGroupsOverseerComboBox.setText(selectedFamily.getSpInf2Decrypted());
		// }
	}

	private void membersAvaiableListBuild() {

		membersAvailableList = FXCollections.observableArrayList();

		for (Member member : membersList)
			if (member.getSpInf5() == -1)
				membersAvailableList.add(member);

		familiesTableView.setItems(membersAvailableList);
	}

	private void familyMembersListBuild() {

		familyMembersList = FXCollections.observableArrayList();

		if (selectedFamily != null) {
			for (Member member : membersList)
				if (member.getSpInf5() == selectedFamily.getSpFamID())
					familyMembersList.add(member);
		}

		serGroupsFamiliesTableView.setItems(familyMembersList);
	}

	private void listeners() {
		listenerFamilyAddMember();
		listenerFamilyRemoveMember();
		listenerSaveButton();
	}

	private void listenerFamilyRemoveMember() {
		serGroupsRemoveFamily.setOnAction(event -> familyRemoveMember());
	}

	private void listenerFamilyAddMember() {
		serGroupsAddFamily.setOnAction(event -> familyAddMember());
	}

	private void familyAddMember() {

		if (familiesTableView.getSelectionModel().getSelectedIndex() > -1) {

			Member member = familiesTableView.getSelectionModel().getSelectedItem();
			familiesTableView.getItems().remove(member);

			familyMembersList.add(member);
			familyMembersList.sort((a, b) -> (a.getSpInf2Decrypted().concat(a.getSpInf1Decrypted())
					.compareTo(b.getSpInf2Decrypted().concat(b.getSpInf1Decrypted()))));

			checkFamilyName(member);
		}
	}

	private void checkFamilyName(Member member) {
		if (familyMembersList.size() == 1)
			if (serGroupsNameTextField.getText().isEmpty())
				serGroupsNameTextField.setText(member.getNameStyle2());
	}

	private void familyRemoveMember() {

		if (serGroupsFamiliesTableView.getSelectionModel().getSelectedIndex() > -1) {

			Member member = serGroupsFamiliesTableView.getSelectionModel().getSelectedItem();
			serGroupsFamiliesTableView.getItems().remove(member);

			membersAvailableList.add(member);
			membersAvailableList.sort((a, b) -> (a.getSpInf2Decrypted().concat(a.getSpInf1Decrypted())
					.compareTo(b.getSpInf2Decrypted().concat(b.getSpInf1Decrypted()))));
		}
	}

	private void listenerSaveButton() {
		saveButton.setOnAction(event -> saveFamily());
	}

	private void saveFamily() {

		if (checkFields()) {

			String spInf1 = Crypt.encrypt(serGroupsNameTextField.getText(), settings.getDatabaseSecretKey());
			// String spInf2 =
			// Crypt.encrypt(serGroupsOverseerComboBox.getText(),
			// settings.getDatabaseSecretKey());
			// String spInf3 = Crypt.encrypt(familyNummerTextField.getText(),
			// settings.getDatabaseSecretKey());
			// String spInf4 = Crypt.encrypt(familyPostCodeTextField.getText(),
			// settings.getDatabaseSecretKey());
			// String spInf5 = Crypt.encrypt(familyCityTextField.getText(),
			// settings.getDatabaseSecretKey());

			String idToRemove = "";

			for (Member member : membersAvailableList)
				if (member.getSpInf5() != -1)
					idToRemove += idToRemove.isEmpty() ? member.getSpMemberID() : ", " + member.getSpMemberID();

			String idToSet = "";

			for (Member member : familyMembersList)
				if (member.getSpInf5() == -1)
					idToSet += idToSet.isEmpty() ? member.getSpMemberID() : ", " + member.getSpMemberID();

			// if (selectedFamily != null)
			// editFamily(spInf1, spInf2, spInf3, spInf4, spInf5, idToRemove,
			// idToSet);
			// else
			// newFamily(spInf1, spInf2, spInf3, spInf4, spInf5, idToRemove,
			// idToSet);
		} else
			new AlertDesigner(language.getStringWithNewLine("TEXT0004"), ownerStage, AlertType.ERROR,
					Meta.Application.getFullTitle(), Meta.Resources.ICON).show();
	}

	private void newFamily(String spInf1, String spInf2, String spInf3, String spInf4, String spInf5, String idToRemove,
			String idToSet) {

		// Actions.insertFamily(spInf1, spInf2, spInf3, spInf4, spInf5,
		// idToRemove, idToSet, settings, ownerStage,
		// congrTabPane, newFamilyTab, familiesTab, ownerCtrl);
	}

	private void editFamily(String spInf1, String spInf2, String spInf3, String spInf4, String spInf5,
			String idToRemove, String idToSet) {

		// Actions.updateFamily(String.valueOf(selectedFamily.getSpFamID()),
		// spInf1, spInf2, spInf3, spInf4, spInf5,
		// idToRemove, idToSet, settings, ownerStage, congrTabPane,
		// newFamilyTab, familiesTab, ownerCtrl);
	}

	private boolean checkFields() {

		boolean status = true;

		if (serGroupsNameTextField.getText().isEmpty())
			status = false;

		if (status)
			if (familyMembersList.size() == 0)
				status = false;

		return status;
	}

	private void viewUpdate() {

		this.language = settings.getLanguage();

		if (this.selectedFamily != null)
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

	public UserMenuSerGroupsList getOwnerCtrl() {
		return ownerCtrl;
	}

	public void setOwnerCtrl(UserMenuSerGroupsList ownerCtrl) {
		this.ownerCtrl = ownerCtrl;
	}

	public Tab getMembersTab() {
		return familiesTab;
	}

	public void setMembersTab(Tab membersTab) {
		this.familiesTab = membersTab;
	}

	public ObservableList<Member> getMembersList() {
		return membersList;
	}

	public void setMembersList(ObservableList<Member> membersList) {
		this.membersList = membersList;
	}

	public ObservableList<Member> getMembersAvailableList() {
		return membersAvailableList;
	}

	public void setMembersAvailableList(ObservableList<Member> membersAvailableList) {
		this.membersAvailableList = membersAvailableList;
	}

	public ObservableList<Member> getFamilyMembersList() {
		return familyMembersList;
	}

	public void setFamilyMembersList(ObservableList<Member> familyMembersList) {
		this.familyMembersList = familyMembersList;
	}

	public Family getSelectedFamily() {
		return selectedFamily;
	}

	public void setSelectedFamily(Family selectedFamily) {
		this.selectedFamily = selectedFamily;
	}
}
