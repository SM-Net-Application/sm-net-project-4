package com.sm.net.sp.view.home.user.menu.congr;

import java.io.IOException;

import com.sm.net.javafx.AlertDesigner;
import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.actions.Actions;
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

	private Settings settings;
	private Language language;
	private Stage ownerStage;

	@FXML
	private void initialize() {
		styleClasses();
		cellValueFactory();
	}

	private void cellValueFactory() {

		memberIDTableColumn.setCellValueFactory(cellData -> cellData.getValue().spMemberIDProperty().asObject());
		memberSurnameTableColumn.setCellValueFactory(cellData -> cellData.getValue().spInf2DecryptedProperty());
		memberNameTableColumn.setCellValueFactory(cellData -> cellData.getValue().spInf1DecryptedProperty());
	}

	private void styleClasses() {

		congrTabPane.getStyleClass().add("tabPaneStyle1");

		membersTab.getStyleClass().add("tabStyle1");
		familyTab.getStyleClass().add("tabStyle1");
		membersTableView.getStyleClass().add("tableViewStyle1");

		memberAddButton.getStyleClass().add("buttonStyle2");
		memberDeleteButton.getStyleClass().add("buttonStyle2");
		membersUpdateButton.getStyleClass().add("buttonStyle2");
	}

	public void objectInitialize() {
		listeners();
		viewUpdate();
		getMembers();
	}

	@Override
	public void getMembers() {
		Actions.getAllMembers(settings, ownerStage, this);
	}

	private void listeners() {
		listenerMemberAddButton();
		listenerMemberDeleteButton();
		listenerMembersUpdateButton();
		listenerMembersTableView();
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

	private void listenerMemberAddButton() {
		memberAddButton.setOnAction(event -> newMember());
	}

	private void listenerMemberDeleteButton() {
		memberDeleteButton.setOnAction(event -> deleteMember());
	}

	private void listenerMembersUpdateButton() {
		membersUpdateButton.setOnAction(event -> getMembers());
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
				ctrl.objectInitialize();

				Tab newMemberTab = new Tab(language.getString("TEXT0015"), layout);
				newMemberTab.setClosable(true);
				newMemberTab.getStyleClass().add("tabStyle1");
				newMemberTab.setGraphic(new ImageView(Meta.Resources.PLUS));

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
	}

	@Override
	public void updateTable(ObservableList<Member> list) {

		list.sort((a, b) -> (a.getSpInf2Decrypted().concat(a.getSpInf1Decrypted())
				.compareTo(b.getSpInf2Decrypted().concat(b.getSpInf1Decrypted()))));
		membersTableView.setItems(list);
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
}
