package com.sm.net.sp.view.home.user.menu.sergroups;

import com.sm.net.javafx.AlertDesigner;
import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.actions.Actions;
import com.sm.net.sp.model.Family;
import com.sm.net.sp.model.Member;
import com.sm.net.sp.model.SerGroup;
import com.sm.net.sp.model.UpdateDataAdapter;
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
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;

public class UserMenuSerGroupsEditor extends UpdateDataAdapter {

	@FXML
	private Label serGroupsNameLabel;
	@FXML
	private TextField serGroupsNameTextField;
	@FXML
	private Label serGroupsOverseerLabel;
	@FXML
	private ComboBox<Member> serGroupsOverseerComboBox;
	@FXML
	private Label serGroupsAssistantLabel;
	@FXML
	private ComboBox<Member> serGroupsAssistantComboBox;
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

	private ObservableList<Member> membersList;
	private ObservableList<Member> serGroupOverseerList;
	private ObservableList<Member> serGroupsAssistantList;
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

		serGroupsNameLabel.getStyleClass().add("label_001");
		serGroupsOverseerLabel.getStyleClass().add("label_001");
		serGroupsAssistantLabel.getStyleClass().add("label_001");

		familiesLabel.getStyleClass().add("label_001");
		serGroupsFamiliesLabel.getStyleClass().add("label_001");

		serGroupsNameTextField.getStyleClass().add("text_field_001");
		serGroupsOverseerComboBox.getStyleClass().add("combo_box_001");
		serGroupsAssistantComboBox.getStyleClass().add("combo_box_001");

		familiesTableView.getStyleClass().add("table_view_001");
		serGroupsFamiliesTableView.getStyleClass().add("table_view_001");

		serGroupsAddFamily.getStyleClass().add("button_image_001");
		serGroupsRemoveFamily.getStyleClass().add("button_image_001");
		saveButton.getStyleClass().add("button_image_001");
	}

	public void objectInitialize() {
		initValue();
		viewUpdate();
		listeners();
	}

	private void viewUpdate() {

		this.language = settings.getLanguage();

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

		Tooltip serGroupsAddTooltip = new Tooltip(this.language.getString("servicegroupeditor.tooltip.add"));
		serGroupsAddTooltip.getStyleClass().add("tooltip_001");
		this.serGroupsAddFamily.setTooltip(serGroupsAddTooltip);
		this.serGroupsAddFamily.setText("");
		this.serGroupsAddFamily.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.ARROW_FRONT));
		
		Tooltip serGroupsRemoveTooltip = new Tooltip(this.language.getString("servicegroupeditor.tooltip.delete"));
		serGroupsRemoveTooltip.getStyleClass().add("tooltip_001");
		this.serGroupsRemoveFamily.setTooltip(serGroupsRemoveTooltip);
		this.serGroupsRemoveFamily.setText("");
		this.serGroupsRemoveFamily.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.ARROW_BACK));
		
		Tooltip saveTooltip = new Tooltip(this.language.getString("servicegroupeditor.tooltip.save"));
		saveTooltip.getStyleClass().add("tooltip_001");
		this.saveButton.setTooltip(saveTooltip);
		this.saveButton.setText("");
		this.saveButton.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.SAVE));
	}

	private void initValue() {

		familiesListBuild();
		membersListBuild();

		if (selectedSerGroups != null)
			this.serGroupsNameTextField.setText(selectedSerGroups.getSpInf1Decrypted());
	}

	private Member selectMemberComboBox(ComboBox<Member> comboBox, int idMember) {

		ObservableList<Member> items = comboBox.getItems();
		for (Member member : items)
			if (idMember == member.getSpMemberID())
				return member;

		return null;
	}

	private void membersListBuild() {
		membersList = FXCollections.observableArrayList();
		Actions.getAllMembers(settings, ownerStage, this);
	}

	@Override
	public void updateMembers(ObservableList<Member> list) {
		super.updateMembers(list);

		this.membersList = list;
		this.membersList.sort((a, b) -> a.getNameStyle1().compareTo(b.getNameStyle1()));

		overseenListBuild();
		assistantListBuild();
	}

	private void overseenListBuild() {
		serGroupOverseerList = FXCollections.observableArrayList();

		for (Member member : membersList)
			if (member.getSpInf9() == 1 || member.getSpInf10() == 1)
				serGroupOverseerList.add(member);

		serGroupsOverseerComboBox.setItems(serGroupOverseerList);

		if (selectedSerGroups != null)
			this.serGroupsOverseerComboBox.getSelectionModel()
					.select(selectMemberComboBox(this.serGroupsOverseerComboBox, selectedSerGroups.getSpInf2()));

	}

	private void assistantListBuild() {
		serGroupsAssistantList = FXCollections.observableArrayList();

		for (Member member : membersList)
			if (member.getSpInf9() == 1 || member.getSpInf10() == 1)
				serGroupsAssistantList.add(member);

		serGroupsAssistantComboBox.setItems(serGroupsAssistantList);

		if (selectedSerGroups != null)
			this.serGroupsAssistantComboBox.getSelectionModel()
					.select(selectMemberComboBox(this.serGroupsAssistantComboBox, selectedSerGroups.getSpInf3()));
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
		serGroupsFamilyListBuild();
	}

	private void familiesAvaiableListBuild() {

		familiesAvailableList = FXCollections.observableArrayList();

		for (Family family : familiesList)
			if (family.getSpInf6() == -1)
				familiesAvailableList.add(family);

		familiesAvailableList.sort((a, b) -> (a.getSpInf1Decrypted().compareTo(b.getSpInf1Decrypted())));
		familiesTableView.setItems(familiesAvailableList);
	}

	private void serGroupsFamilyListBuild() {

		serGroupsFamilyList = FXCollections.observableArrayList();

		if (selectedSerGroups != null)
			for (Family family : familiesList)
				if (family.getSpInf6() == selectedSerGroups.getSpSerGrID())
					serGroupsFamilyList.add(family);

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

		if (checkFields()) {

			String spInf1 = Crypt.encrypt(serGroupsNameTextField.getText(), settings.getDatabaseSecretKey());

			Member overseer = serGroupsOverseerComboBox.getSelectionModel().getSelectedItem();
			String spInf2 = String.valueOf(overseer.getSpMemberID());

			String spInf3 = "-1";
			if (serGroupsAssistantComboBox.getSelectionModel().getSelectedIndex() > -1) {
				Member assistant = serGroupsAssistantComboBox.getSelectionModel().getSelectedItem();
				spInf3 = String.valueOf(assistant.getSpMemberID());
			}

			String idToRemove = "";

			for (Family family : familiesAvailableList)
				if (family.getSpInf6() != -1)
					idToRemove += idToRemove.isEmpty() ? family.getSpFamID() : ", " + family.getSpFamID();

			String idToSet = "";

			for (Family family : serGroupsFamilyList)
				if (family.getSpInf6() == -1)
					idToSet += idToSet.isEmpty() ? family.getSpFamID() : ", " + family.getSpFamID();

			if (selectedSerGroups != null)
				editSerGroup(spInf1, spInf2, spInf3, idToRemove, idToSet);
			else
				newSerGroup(spInf1, spInf2, spInf3, idToRemove, idToSet);
		} else
			new AlertDesigner(language.getStringWithNewLine("TEXT0004"), ownerStage, AlertType.ERROR,
					Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(), Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").show();
	}

	private void newSerGroup(String spInf1, String spInf2, String spInf3, String idToRemove, String idToSet) {
		Actions.insertSerGroup(spInf1, spInf2, spInf3, idToRemove, idToSet, settings, ownerStage, serGroupsTabPane,
				serGroupTab, ownerCtrl);
	}

	private void editSerGroup(String spInf1, String spInf2, String spInf3, String idToRemove, String idToSet) {
		Actions.updateSerGroup(String.valueOf(selectedSerGroups.getSpSerGrID()), spInf1, spInf2, spInf3, idToRemove,
				idToSet, settings, ownerStage, this.serGroupsTabPane, serGroupTab, ownerCtrl);
	}

	private boolean checkFields() {

		boolean status = true;

		if (serGroupsNameTextField.getText().isEmpty())
			status = false;

		if (status)
			if (serGroupsFamilyList.size() == 0)
				status = false;

		if (status)
			if (!(serGroupsOverseerComboBox.getSelectionModel().getSelectedIndex() > -1))
				status = false;

		if (status)
			if (serGroupsAssistantComboBox.getSelectionModel().getSelectedIndex() > -1) {
				Member overseer = serGroupsOverseerComboBox.getSelectionModel().getSelectedItem();
				Member assistant = serGroupsAssistantComboBox.getSelectionModel().getSelectedItem();

				if (overseer.getSpMemberID() == assistant.getSpMemberID())
					status = false;
			}

		return status;
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
