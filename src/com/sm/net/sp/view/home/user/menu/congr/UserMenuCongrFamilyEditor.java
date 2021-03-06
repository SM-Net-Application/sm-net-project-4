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
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;

public class UserMenuCongrFamilyEditor {

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
	private Label familyPhoneLabel;
	@FXML
	private TextField familyPhoneTextField;
	@FXML
	private Label familyPrivilegeLabel;
	@FXML
	private CheckBox familyExcludeFromServiceGroupCheckBox;
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

	@FXML
	private Label familyCoordLabel;
	@FXML
	private TextField familyCoordTextField;

	@FXML
	private TabPane privilegiesTabPane;
	@FXML
	private Tab othersTab;
	@FXML
	private Tab memorialTab;

	@FXML
	private CheckBox memorialEmblemsCheckBox;

	private Settings settings;
	private Language language;
	private Stage ownerStage;
	private TabPane parentTabPane;
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

		familyNameLabel.getStyleClass().add("label_001");
		familyStreetLabel.getStyleClass().add("label_001");
		familyNummerLabel.getStyleClass().add("label_001");
		familyPostCodeLabel.getStyleClass().add("label_001");
		familyCityLabel.getStyleClass().add("label_001");
		membersLabel.getStyleClass().add("label_001");
		familyMembersLabel.getStyleClass().add("label_001");
		familyPhoneLabel.getStyleClass().add("label_001");
		this.familyCoordLabel.getStyleClass().add("label_001");

		familyNameTextField.getStyleClass().add("text_field_001");
		familyStreetTextField.getStyleClass().add("text_field_001");
		familyNummerTextField.getStyleClass().add("text_field_001");
		familyPostCodeTextField.getStyleClass().add("text_field_001");
		familyCityTextField.getStyleClass().add("text_field_001");
		familyPhoneTextField.getStyleClass().add("text_field_001");
		this.familyCoordTextField.getStyleClass().add("text_field_001");

		familyPrivilegeLabel.getStyleClass().add("label_002");
		familyExcludeFromServiceGroupCheckBox.getStyleClass().add("check_box_001");

		membersTableView.getStyleClass().add("table_view_001");
		familyMembersTableView.getStyleClass().add("table_view_001");

		familyAddMember.getStyleClass().add("button_image_001");
		familyRemoveMember.getStyleClass().add("button_image_001");
		saveButton.getStyleClass().add("button_image_001");

		this.privilegiesTabPane.getStyleClass().add("tab_pane_003");
		this.othersTab.getStyleClass().add("tab_001");
		this.memorialTab.getStyleClass().add("tab_001");

		this.memorialEmblemsCheckBox.getStyleClass().add("check_box_001");
	}

	public void objectInitialize() {
		initValue();
		viewUpdate();
		listeners();
	}

	private void viewUpdate() {

		this.language = settings.getLanguage();

		familyNameLabel.setText(language.getString("TEXT0025"));
		familyStreetLabel.setText(language.getString("TEXT0027"));
		familyNummerLabel.setText(language.getString("TEXT0028"));
		familyPostCodeLabel.setText(language.getString("TEXT0029"));
		familyCityLabel.setText(language.getString("TEXT0030"));
		familyPhoneLabel.setText(language.getString("TEXT0109"));

		this.familyCoordLabel.setText(language.getString("congregation.family.editor.label.coord"));

		familyPrivilegeLabel.setText(language.getString("TEXT0052"));

		this.familyExcludeFromServiceGroupCheckBox
				.setText(language.getString("congregation.family.editor.checkbox.excludeservicegroup"));

		this.memorialEmblemsCheckBox.setText(language.getString("congregation.family.editor.checkbox.memorialemblems"));

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

		Tooltip familyAddTooltip = new Tooltip(this.language.getString("congregation.family.editor.tooltip.add"));
		familyAddTooltip.getStyleClass().add("tooltip_001");
		this.familyAddMember.setTooltip(familyAddTooltip);
		this.familyAddMember.setText("");
		this.familyAddMember.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.ARROW_FRONT));

		Tooltip familyRemoveTooltip = new Tooltip(this.language.getString("congregation.family.editor.tooltip.remove"));
		familyRemoveTooltip.getStyleClass().add("tooltip_001");
		this.familyRemoveMember.setTooltip(familyRemoveTooltip);
		this.familyRemoveMember.setText("");
		this.familyRemoveMember.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.ARROW_BACK));

		Tooltip saveTooltip = new Tooltip(this.language.getString("congregation.family.editor.tooltip.save"));
		saveTooltip.getStyleClass().add("tooltip_001");
		this.saveButton.setTooltip(saveTooltip);
		this.saveButton.setText("");
		this.saveButton.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.SAVE));

		this.privilegiesTabPane.setTabMinHeight(75);
		this.privilegiesTabPane.setTabMaxHeight(75);

		Tooltip othersTooltip = new Tooltip(this.language.getString("congregation.family.editor.tooltip.others"));
		othersTooltip.getStyleClass().add("tooltip_001");
		this.othersTab.setTooltip(othersTooltip);
		this.othersTab.setText("");
		this.othersTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.OTHERS));

		Tooltip memorialTooltip = new Tooltip(this.language.getString("congregation.family.editor.tooltip.memorial"));
		memorialTooltip.getStyleClass().add("tooltip_001");
		this.memorialTab.setTooltip(memorialTooltip);
		this.memorialTab.setText("");
		this.memorialTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.MEMORIAL));
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
			this.familyPhoneTextField.setText(selectedFamily.getSpInf7Decrypted());
			this.familyExcludeFromServiceGroupCheckBox.setSelected((selectedFamily.getSpInf8() == 1));
			this.familyCoordTextField.setText(selectedFamily.getSpInf9Decrypted());
			this.memorialEmblemsCheckBox.setSelected((selectedFamily.getSpInf10() == 1));
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
			String spInf7 = Crypt.encrypt(familyPhoneTextField.getText(), settings.getDatabaseSecretKey());
			String spInf8 = !familyExcludeFromServiceGroupCheckBox.isSelected() ? "0" : "1";

			String spInf9 = Crypt.encrypt(this.familyCoordTextField.getText(), this.settings.getDatabaseSecretKey());

			String spInf10 = !this.memorialEmblemsCheckBox.isSelected() ? "0" : "1";

			String idToRemove = "";

			for (Member member : membersAvailableList)
				if (member.getSpInf5() != -1)
					idToRemove += idToRemove.isEmpty() ? member.getSpMemberID() : ", " + member.getSpMemberID();

			String idToSet = "";

			for (Member member : familyMembersList)
				if (member.getSpInf5() == -1)
					idToSet += idToSet.isEmpty() ? member.getSpMemberID() : ", " + member.getSpMemberID();

			if (selectedFamily != null)
				editFamily(spInf1, spInf2, spInf3, spInf4, spInf5, spInf7, spInf8, spInf9, spInf10, idToRemove,
						idToSet);
			else
				newFamily(spInf1, spInf2, spInf3, spInf4, spInf5, "-1", spInf7, spInf8, spInf9, spInf10, idToRemove,
						idToSet);
		} else
			new AlertDesigner(language.getStringWithNewLine("TEXT0004"), ownerStage, AlertType.ERROR,
					Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
					Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").show();
	}

	private void newFamily(String spInf1, String spInf2, String spInf3, String spInf4, String spInf5, String spInf6,
			String spInf7, String spInf8, String spInf9, String spInf10, String idToRemove, String idToSet) {

		Actions.insertFamily(spInf1, spInf2, spInf3, spInf4, spInf5, spInf6, spInf7, spInf8, spInf9, spInf10,
				idToRemove, idToSet, settings, ownerStage, parentTabPane, newFamilyTab, familiesTab, ownerCtrl);
	}

	private void editFamily(String spInf1, String spInf2, String spInf3, String spInf4, String spInf5, String spInf7,
			String spInf8, String spInf9, String spInf10, String idToRemove, String idToSet) {

		Actions.updateFamily(String.valueOf(selectedFamily.getSpFamID()), spInf1, spInf2, spInf3, spInf4, spInf5,
				spInf7, spInf8, spInf9, spInf10, idToRemove, idToSet, settings, ownerStage, parentTabPane, newFamilyTab,
				familiesTab, ownerCtrl);
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
