package com.sm.net.sp.view.home.user.menu.congr;

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
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
	private Tab newFamilyTab;
	private Tab familiesTab;
	private UserMenuCongrList ownerCtrl;
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

		memberIDTableColumn.setCellValueFactory(cellData -> cellData.getValue().spMemberIDProperty().asObject());
		memberSurnameTableColumn.setCellValueFactory(cellData -> cellData.getValue().spInf2DecryptedProperty());
		memberNameTableColumn.setCellValueFactory(cellData -> cellData.getValue().spInf1DecryptedProperty());

		familyMemberIDTableColumn.setCellValueFactory(cellData -> cellData.getValue().spMemberIDProperty().asObject());
		familyMemberSurnameTableColumn.setCellValueFactory(cellData -> cellData.getValue().spInf2DecryptedProperty());
		familyMemberNameTableColumn.setCellValueFactory(cellData -> cellData.getValue().spInf1DecryptedProperty());
	}

	private void styleClasses() {

		titleLabel.getStyleClass().add("labelStyle2");
		familyNameLabel.getStyleClass().add("labelStyle1");
		familyStreetLabel.getStyleClass().add("labelStyle1");
		familyNummerLabel.getStyleClass().add("labelStyle1");
		familyPostCodeLabel.getStyleClass().add("labelStyle1");
		familyCityLabel.getStyleClass().add("labelStyle1");
		membersLabel.getStyleClass().add("labelStyle1");
		familyMembersLabel.getStyleClass().add("labelStyle1");

		familyNameTextField.getStyleClass().add("textFieldStyle1");
		familyStreetTextField.getStyleClass().add("textFieldStyle1");
		familyNummerTextField.getStyleClass().add("textFieldStyle1");
		familyPostCodeTextField.getStyleClass().add("textFieldStyle1");
		familyCityTextField.getStyleClass().add("textFieldStyle1");

		membersTableView.getStyleClass().add("tableViewStyle1");

		familyMembersTableView.getStyleClass().add("tableViewStyle1");

		familyAddMember.getStyleClass().add("buttonStyle2");
		familyRemoveMember.getStyleClass().add("buttonStyle2");
		saveButton.getStyleClass().add("buttonStyle1");
	}

	public void objectInitialize() {
		initValue();
		viewUpdate();
		listeners();
	}

	private void initValue() {

		membersAvaiableListBuild();
		familyMembersListBuild();

		if (selectedFamily != null) {

			this.familyNameTextField.setText(selectedFamily.getSpInf1Decrypted());
			this.familyStreetTextField.setText(selectedFamily.getSpInf2Decrypted());
			this.familyNummerTextField.setText(selectedFamily.getSpInf3Decrypted());
			this.familyPostCodeTextField.setText(selectedFamily.getSpInf4Decrypted());
			this.familyCityTextField.setText(selectedFamily.getSpInf5Decrypted());
		}
	}

	private void membersAvaiableListBuild() {

		membersAvailableList = FXCollections.observableArrayList();

		for (Member member : membersList)
			if (member.getSpInf5() == -1)
				membersAvailableList.add(member);

		membersTableView.setItems(membersAvailableList);
	}

	private void familyMembersListBuild() {

		familyMembersList = FXCollections.observableArrayList();

		if (selectedFamily != null) {
			for (Member member : membersList)
				if (member.getSpInf5() == selectedFamily.getSpFamID())
					familyMembersList.add(member);
		}

		familyMembersTableView.setItems(familyMembersList);
	}

	private void listeners() {
		listenerFamilyAddMember();
		listenerFamilyRemoveMember();
		listenerSaveButton();
	}

	private void listenerFamilyRemoveMember() {
		familyRemoveMember.setOnAction(event -> familyRemoveMember());
	}

	private void listenerFamilyAddMember() {
		familyAddMember.setOnAction(event -> familyAddMember());
	}

	private void familyAddMember() {

		if (membersTableView.getSelectionModel().getSelectedIndex() > -1) {

			Member member = membersTableView.getSelectionModel().getSelectedItem();
			membersTableView.getItems().remove(member);

			familyMembersList.add(member);
			familyMembersList.sort((a, b) -> (a.getSpInf2Decrypted().concat(a.getSpInf1Decrypted())
					.compareTo(b.getSpInf2Decrypted().concat(b.getSpInf1Decrypted()))));

			checkFamilyName(member);
		}
	}

	private void checkFamilyName(Member member) {
		if (familyMembersList.size() == 1)
			if (familyNameTextField.getText().isEmpty())
				familyNameTextField.setText(member.getNameStyle2());
	}

	private void familyRemoveMember() {

		if (familyMembersTableView.getSelectionModel().getSelectedIndex() > -1) {

			Member member = familyMembersTableView.getSelectionModel().getSelectedItem();
			familyMembersTableView.getItems().remove(member);

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

			String spInf1 = Crypt.encrypt(familyNameTextField.getText(), settings.getDatabaseSecretKey());
			String spInf2 = Crypt.encrypt(familyStreetTextField.getText(), settings.getDatabaseSecretKey());
			String spInf3 = Crypt.encrypt(familyNummerTextField.getText(), settings.getDatabaseSecretKey());
			String spInf4 = Crypt.encrypt(familyPostCodeTextField.getText(), settings.getDatabaseSecretKey());
			String spInf5 = Crypt.encrypt(familyCityTextField.getText(), settings.getDatabaseSecretKey());

			String idToRemove = "";

			for (Member member : membersAvailableList)
				if (member.getSpInf5() != -1)
					idToRemove += idToRemove.isEmpty() ? member.getSpMemberID() : ", " + member.getSpMemberID();

			String idToSet = "";

			for (Member member : familyMembersList)
				if (member.getSpInf5() == -1)
					idToSet += idToSet.isEmpty() ? member.getSpMemberID() : ", " + member.getSpMemberID();

			if (selectedFamily != null)
				editFamily(spInf1, spInf2, spInf3, spInf4, spInf5, idToRemove, idToSet);
			else
				newFamily(spInf1, spInf2, spInf3, spInf4, spInf5, "-1", idToRemove, idToSet);
		} else
			new AlertDesigner(language.getStringWithNewLine("TEXT0004"), ownerStage, AlertType.ERROR,
					Meta.Application.getFullTitle(), Meta.Resources.ICON).show();
	}

	private void newFamily(String spInf1, String spInf2, String spInf3, String spInf4, String spInf5, String spInf6,
			String idToRemove, String idToSet) {

		Actions.insertFamily(spInf1, spInf2, spInf3, spInf4, spInf5, spInf6, idToRemove, idToSet, settings, ownerStage,
				congrTabPane, newFamilyTab, familiesTab, ownerCtrl);
	}

	private void editFamily(String spInf1, String spInf2, String spInf3, String spInf4, String spInf5,
			String idToRemove, String idToSet) {

		Actions.updateFamily(String.valueOf(selectedFamily.getSpFamID()), spInf1, spInf2, spInf3, spInf4, spInf5,
				idToRemove, idToSet, settings, ownerStage, congrTabPane, newFamilyTab, familiesTab, ownerCtrl);
	}

	private boolean checkFields() {

		boolean status = true;

		if (familyNameTextField.getText().isEmpty())
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
			titleLabel.setText(language.getString("TEXT0031"));

		familyNameLabel.setText(language.getString("TEXT0025"));
		familyStreetLabel.setText(language.getString("TEXT0027"));
		familyNummerLabel.setText(language.getString("TEXT0028"));
		familyPostCodeLabel.setText(language.getString("TEXT0029"));
		familyCityLabel.setText(language.getString("TEXT0030"));
		membersLabel.setText(language.getString("TEXT0032"));
		memberIDTableColumn.setText(language.getString("TEXT0005"));
		memberIDTableColumn.setMinWidth(50);
		memberIDTableColumn.setMaxWidth(50);
		memberIDTableColumn.setResizable(false);
		memberSurnameTableColumn.setText(language.getString("TEXT0013"));
		memberNameTableColumn.setText(language.getString("TEXT0014"));
		familyMembersLabel.setText(language.getString("TEXT0033"));
		familyMemberIDTableColumn.setText(language.getString("TEXT0005"));
		familyMemberIDTableColumn.setMinWidth(50);
		familyMemberIDTableColumn.setMaxWidth(50);
		familyMemberIDTableColumn.setResizable(false);
		familyMemberSurnameTableColumn.setText(language.getString("TEXT0013"));
		familyMemberNameTableColumn.setText(language.getString("TEXT0014"));

		familyAddMember.setText("");
		familyAddMember.setGraphic(new ImageView(Meta.Resources.ARROW_FRONT));
		familyRemoveMember.setText("");
		familyRemoveMember.setGraphic(new ImageView(Meta.Resources.ARROW_BACK));
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

	public UserMenuCongrList getOwnerCtrl() {
		return ownerCtrl;
	}

	public void setOwnerCtrl(UserMenuCongrList ownerCtrl) {
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
