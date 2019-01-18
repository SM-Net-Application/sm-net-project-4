package com.sm.net.sp.view.home.user.menu.congr;

import java.io.IOException;

import com.sm.net.javafx.AlertDesigner;
import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.actions.Actions;
import com.sm.net.sp.model.Family;
import com.sm.net.sp.model.Member;
import com.sm.net.sp.settings.Settings;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class UserMenuCongrList implements UserMenuCongrListCallback {

	@FXML
	private TabPane congrTabPane;

	@FXML
	private Tab membersTab;

	@FXML
	private Tab familyTab;

	@FXML
	private TableView<Member> membersTableView;

	@FXML
	private TableColumn<Member, Integer> memberIDTableColumn;

	@FXML
	private TableColumn<Member, String> memberSurnameTableColumn;

	@FXML
	private TableColumn<Member, String> memberNameTableColumn;

	@FXML
	private Button memberAddButton;

	@FXML
	private Button memberDeleteButton;

	@FXML
	private Button membersUpdateButton;

	@FXML
	private TableView<Family> familiesTableView;

	@FXML
	private TableColumn<Family, Integer> familyIDTableColumn;

	@FXML
	private TableColumn<Family, String> familyNameTableColumn;

	@FXML
	private TableColumn<Family, Integer> familyCountTableColumn;

	@FXML
	private TableColumn<Family, String> familyStreetTableColumn;

	@FXML
	private TableColumn<Family, String> familyNummerTableColumn;

	@FXML
	private TableColumn<Family, String> familyPostCodeTableColumn;

	@FXML
	private TableColumn<Family, String> familyCityTableColumn;

	@FXML
	private Button familyAddButton;

	@FXML
	private Button familyDeleteButton;

	@FXML
	private Button familiesUpdateButton;

	private Settings settings;
	private Language language;
	private Stage ownerStage;

	private ObservableList<Member> membersList;
	private ObservableList<Family> familiesList;

	@FXML
	private void initialize() {
		styleClasses();
		cellValueFactory();
	}

	private void cellValueFactory() {

		memberIDTableColumn.setCellValueFactory(cellData -> cellData.getValue().spMemberIDProperty().asObject());
		memberSurnameTableColumn.setCellValueFactory(cellData -> cellData.getValue().spInf2DecryptedProperty());
		memberNameTableColumn.setCellValueFactory(cellData -> cellData.getValue().spInf1DecryptedProperty());

		familyIDTableColumn.setCellValueFactory(cellData -> cellData.getValue().spFamIDProperty().asObject());
		familyNameTableColumn.setCellValueFactory(cellData -> cellData.getValue().spInf1DecryptedProperty());
		familyCountTableColumn.setCellValueFactory(cellData -> cellData.getValue().spFamMembersProperty().asObject());
		familyStreetTableColumn.setCellValueFactory(cellData -> cellData.getValue().spInf2DecryptedProperty());
		familyNummerTableColumn.setCellValueFactory(cellData -> cellData.getValue().spInf3DecryptedProperty());
		familyPostCodeTableColumn.setCellValueFactory(cellData -> cellData.getValue().spInf4DecryptedProperty());
		familyCityTableColumn.setCellValueFactory(cellData -> cellData.getValue().spInf5DecryptedProperty());
	}

	private void styleClasses() {

		congrTabPane.getStyleClass().add("tabPaneStyle1");

		membersTab.getStyleClass().add("tabStyle1");
		familyTab.getStyleClass().add("tabStyle1");
		membersTableView.getStyleClass().add("tableViewStyle1");
		familiesTableView.getStyleClass().add("tableViewStyle1");

		memberAddButton.getStyleClass().add("buttonStyle2");
		memberDeleteButton.getStyleClass().add("buttonStyle2");
		membersUpdateButton.getStyleClass().add("buttonStyle2");

		familyAddButton.getStyleClass().add("buttonStyle2");
		familyDeleteButton.getStyleClass().add("buttonStyle2");
		familiesUpdateButton.getStyleClass().add("buttonStyle2");
	}

	public void objectInitialize() {
		listeners();
		viewUpdate();
		updateMembersTable();
		updateFamiliesTable();
	}

	private void listeners() {
		listenerMemberAddButton();
		listenerMemberDeleteButton();
		listenerMembersUpdateButton();
		listenerMembersTableView();

		listenerFamilyAddButton();
		listenerFamilyDeleteButton();
		listenerFamilyUpdateButton();
		listenerFamiliesTableView();
	}

	private void listenerMembersTableView() {
		membersTableView.setRowFactory(param -> {
			TableRow<Member> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (!row.isEmpty()))
					editMember(row.getItem());
			});
			return row;
		});
	}

	private void listenerFamiliesTableView() {
		familiesTableView.setRowFactory(param -> {
			TableRow<Family> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (!row.isEmpty()))
					editFamily(row.getItem());
			});
			return row;
		});
	}

	private void listenerMemberAddButton() {
		memberAddButton.setOnAction(event -> newMember());
	}

	private void listenerFamilyAddButton() {
		familyAddButton.setOnAction(event -> newFamily());
	}

	private void listenerMemberDeleteButton() {
		memberDeleteButton.setOnAction(event -> deleteMember());
	}

	private void listenerFamilyDeleteButton() {
		familyDeleteButton.setOnAction(event -> deleteFamily());
	}

	private void listenerMembersUpdateButton() {
		membersUpdateButton.setOnAction(event -> updateMembersTable());
	}

	private void listenerFamilyUpdateButton() {
		familiesUpdateButton.setOnAction(event -> updateFamiliesTable());
	}

	private void deleteMember() {

		if (membersTableView.getSelectionModel().getSelectedIndex() > -1) {

			Member member = membersTableView.getSelectionModel().getSelectedItem();

			Alert alert = new AlertDesigner(language.getString("TEXT0024"), member.getNameStyle1(), ownerStage,
					AlertType.CONFIRMATION, Meta.Application.getFullTitle(), Meta.Resources.ICON);
			if (alert.showAndWait().get() == ButtonType.OK)
				Actions.deleteMember(String.valueOf(member.getSpMemberID()), settings, ownerStage, this);
		}

	}

	private void deleteFamily() {

		if (familiesTableView.getSelectionModel().getSelectedIndex() > -1) {

			Family family = familiesTableView.getSelectionModel().getSelectedItem();

			Alert alert = new AlertDesigner(language.getString("TEXT0034"), family.getSpInf1Decrypted(), ownerStage,
					AlertType.CONFIRMATION, Meta.Application.getFullTitle(), Meta.Resources.ICON);
			if (alert.showAndWait().get() == ButtonType.OK)
				Actions.deleteFamily(String.valueOf(family.getSpFamID()), settings, ownerStage, this);
		}

	}

	private void newMember() {

		if (!isAlreadyOpen(language.getString("TEXT0015"))) {

			try {

				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(Meta.Views.HOME_USER_MENU_CONGR_MEMBER_EDITOR);
				AnchorPane layout = (AnchorPane) fxmlLoader.load();

				UserMenuCongrMemberEditor ctrl = (UserMenuCongrMemberEditor) fxmlLoader.getController();
				ctrl.setSettings(this.settings);
				ctrl.setOwnerStage(ownerStage);
				ctrl.setOwnerCtrl(this);

				Tab newMemberTab = new Tab(language.getString("TEXT0015"), layout);
				newMemberTab.setClosable(true);
				newMemberTab.getStyleClass().add("tabStyle1");
				newMemberTab.setGraphic(new ImageView(Meta.Resources.PLUS));

				ctrl.setCongrTabPane(congrTabPane);
				ctrl.setMembersTab(membersTab);
				ctrl.setNewMemberTab(newMemberTab);

				congrTabPane.getTabs().add(newMemberTab);
				congrTabPane.getSelectionModel().select(newMemberTab);

				ctrl.objectInitialize();

			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	private void newFamily() {

		if (!isAlreadyOpen(language.getString("TEXT0015"))) {

			try {

				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(Meta.Views.HOME_USER_MENU_CONGR_FAMILY_EDITOR);
				AnchorPane layout = (AnchorPane) fxmlLoader.load();

				UserMenuCongrFamilyEditor ctrl = (UserMenuCongrFamilyEditor) fxmlLoader.getController();
				ctrl.setSettings(this.settings);
				ctrl.setOwnerStage(ownerStage);
				ctrl.setOwnerCtrl(this);

				Tab newFamilyTab = new Tab(language.getString("TEXT0015"), layout);
				newFamilyTab.setClosable(true);
				newFamilyTab.getStyleClass().add("tabStyle1");
				newFamilyTab.setGraphic(new ImageView(Meta.Resources.PLUS));

				ctrl.setCongrTabPane(congrTabPane);
				ctrl.setMembersTab(membersTab);
				ctrl.setNewMemberTab(newFamilyTab);
				ctrl.setMembersList(this.membersList);

				congrTabPane.getTabs().add(newFamilyTab);
				congrTabPane.getSelectionModel().select(newFamilyTab);

				ctrl.objectInitialize();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void editMember(Member member) {

		if (!isAlreadyOpen(member.getNameStyle1())) {

			try {

				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(Meta.Views.HOME_USER_MENU_CONGR_MEMBER_EDITOR);
				AnchorPane layout = (AnchorPane) fxmlLoader.load();

				UserMenuCongrMemberEditor ctrl = (UserMenuCongrMemberEditor) fxmlLoader.getController();
				ctrl.setSettings(this.settings);
				ctrl.setOwnerStage(ownerStage);
				ctrl.setOwnerCtrl(this);
				ctrl.setSelectedMember(member);
				ctrl.objectInitialize();

				Tab newMemberTab = new Tab(member.getNameStyle1(), layout);
				newMemberTab.setClosable(true);
				newMemberTab.getStyleClass().add("tabStyle1");
				newMemberTab.setGraphic(new ImageView(Meta.Resources.MEMBER));

				ctrl.setCongrTabPane(congrTabPane);
				ctrl.setMembersTab(membersTab);
				ctrl.setNewMemberTab(newMemberTab);

				congrTabPane.getTabs().add(newMemberTab);
				congrTabPane.getSelectionModel().select(newMemberTab);

			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	private void editFamily(Family family) {

		if (!isAlreadyOpen(family.getSpInf1Decrypted())) {

			try {

				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(Meta.Views.HOME_USER_MENU_CONGR_FAMILY_EDITOR);
				AnchorPane layout = (AnchorPane) fxmlLoader.load();

				UserMenuCongrFamilyEditor ctrl = (UserMenuCongrFamilyEditor) fxmlLoader.getController();
				ctrl.setSettings(this.settings);
				ctrl.setOwnerStage(ownerStage);
				ctrl.setOwnerCtrl(this);
				ctrl.setSelectedFamily(family);

				Tab newFamilyTab = new Tab(family.getSpInf1Decrypted(), layout);
				newFamilyTab.setClosable(true);
				newFamilyTab.getStyleClass().add("tabStyle1");
				newFamilyTab.setGraphic(new ImageView(Meta.Resources.FAMILY));

				ctrl.setCongrTabPane(congrTabPane);
				ctrl.setMembersTab(membersTab);
				ctrl.setNewMemberTab(newFamilyTab);
				ctrl.setMembersList(this.membersList);

				congrTabPane.getTabs().add(newFamilyTab);
				congrTabPane.getSelectionModel().select(newFamilyTab);

				ctrl.objectInitialize();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	private boolean isAlreadyOpen(String label) {

		for (Tab tab : congrTabPane.getTabs())
			if (tab.getText().equals(label)) {
				congrTabPane.getSelectionModel().select(tab);
				return true;
			}

		return false;
	}

	private void viewUpdate() {

		this.language = settings.getLanguage();

		congrTabPane.setTabClosingPolicy(TabClosingPolicy.SELECTED_TAB);

		membersTab.setText(language.getString("TEXT0011"));
		membersTab.setGraphic(new ImageView(Meta.Resources.MEMBER));
		membersTab.setClosable(false);
		familyTab.setText(language.getString("TEXT0012"));
		familyTab.setGraphic(new ImageView(Meta.Resources.FAMILY));
		familyTab.setClosable(false);

		memberIDTableColumn.setText(language.getString("TEXT0005"));
		memberIDTableColumn.setMinWidth(50);
		memberIDTableColumn.setMaxWidth(50);
		memberIDTableColumn.setResizable(false);
		memberSurnameTableColumn.setText(language.getString("TEXT0013"));
		memberNameTableColumn.setText(language.getString("TEXT0014"));

		memberAddButton.setText("");
		memberAddButton.setGraphic(new ImageView(Meta.Resources.MEMBER_ADD));
		memberDeleteButton.setText("");
		memberDeleteButton.setGraphic(new ImageView(Meta.Resources.MEMBER_DEL));
		membersUpdateButton.setText("");
		membersUpdateButton.setGraphic(new ImageView(Meta.Resources.UPDATE));

		familyIDTableColumn.setText(language.getString("TEXT0005"));
		familyIDTableColumn.setMinWidth(50);
		familyIDTableColumn.setMaxWidth(50);
		familyIDTableColumn.setResizable(false);
		familyNameTableColumn.setText(language.getString("TEXT0025"));
		familyCountTableColumn.setText(language.getString("TEXT0026"));
		familyStreetTableColumn.setText(language.getString("TEXT0027"));
		familyNummerTableColumn.setText(language.getString("TEXT0028"));
		familyPostCodeTableColumn.setText(language.getString("TEXT0029"));
		familyCityTableColumn.setText(language.getString("TEXT0030"));

		familyAddButton.setText("");
		familyAddButton.setGraphic(new ImageView(Meta.Resources.FAMILY_ADD));
		familyDeleteButton.setText("");
		familyDeleteButton.setGraphic(new ImageView(Meta.Resources.FAMILY_DEL));
		familiesUpdateButton.setText("");
		familiesUpdateButton.setGraphic(new ImageView(Meta.Resources.UPDATE));
	}

	@Override
	public void updateMembersTable() {
		Actions.getAllMembers(settings, ownerStage, this);
	}

	@Override
	public void updateFamiliesTable() {
		Actions.getAllFamilies(settings, ownerStage, this);
	}

	@Override
	public void updateMembersTable(ObservableList<Member> list) {

		this.membersList = list;
		membersList.sort((a, b) -> (a.getSpInf2Decrypted().concat(a.getSpInf1Decrypted())
				.compareTo(b.getSpInf2Decrypted().concat(b.getSpInf1Decrypted()))));

		membersTableView.setItems(membersList);
	}

	@Override
	public void updateFamiliesTable(ObservableList<Family> list) {

		this.familiesList = list;
		familiesList.sort((a, b) -> a.getSpInf1Decrypted().compareTo(b.getSpInf1Decrypted()));

		familiesTableView.setItems(familiesList);
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

	public ObservableList<Member> getMembersList() {
		return membersList;
	}

	public void setMembersList(ObservableList<Member> membersList) {
		this.membersList = membersList;
	}
}
