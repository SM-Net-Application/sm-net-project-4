package com.sm.net.sp.view.home.user.menu.sergroups;

import java.io.IOException;

import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.actions.Actions;
import com.sm.net.sp.model.SerGroup;
import com.sm.net.sp.model.UpdateDataAdapter;
import com.sm.net.sp.settings.Settings;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class UserMenuSerGroupsList extends UpdateDataAdapter {

	@FXML
	private TabPane serGroupsTabPane;

	@FXML
	private Tab serGroupsTab;

	@FXML
	private TableView<SerGroup> serGroupsTableView;

	@FXML
	private TableColumn<SerGroup, Integer> serGroupsIDTableColumn;

	@FXML
	private TableColumn<SerGroup, String> serGroupsNameTableColumn;

	@FXML
	private TableColumn<SerGroup, String> serGroupsOverseerTableColumn;

	@FXML
	private TableColumn<SerGroup, String> serGroupsAssistantTableColumn;

	@FXML
	private TableColumn<SerGroup, Integer> serGroupsFamiliesTableColumn;

	@FXML
	private TableColumn<SerGroup, Integer> serGroupsMembersTableColumn;

	@FXML
	private Button serGroupsAddButton;

	@FXML
	private Button serGroupsDeleteButton;

	@FXML
	private Button serGroupsUpdateButton;

	private Settings settings;
	private Language language;
	private Stage ownerStage;

	private ObservableList<SerGroup> serGroupsList;

	@FXML
	private void initialize() {
		styleClasses();
		cellValueFactory();
	}

	private void cellValueFactory() {

		serGroupsIDTableColumn.setCellValueFactory(cellData -> cellData.getValue().spSerGrIDProperty().asObject());
		serGroupsNameTableColumn.setCellValueFactory(cellData -> cellData.getValue().spInf1DecryptedProperty());
		serGroupsOverseerTableColumn.setCellValueFactory(cellData -> cellData.getValue().overseerProperty());
		serGroupsAssistantTableColumn.setCellValueFactory(cellData -> cellData.getValue().assistantProperty());
		serGroupsFamiliesTableColumn
				.setCellValueFactory(cellData -> cellData.getValue().spSerGroupFamiliesProperty().asObject());
		serGroupsMembersTableColumn
				.setCellValueFactory(cellData -> cellData.getValue().spSerGroupMembersProperty().asObject());
	}

	private void styleClasses() {

		serGroupsTabPane.getStyleClass().add("tabPaneStyle1");

		serGroupsTab.getStyleClass().add("tabStyle1");
		serGroupsTableView.getStyleClass().add("tableViewStyle1");

		serGroupsAddButton.getStyleClass().add("buttonStyle2");
		serGroupsDeleteButton.getStyleClass().add("buttonStyle2");
		serGroupsUpdateButton.getStyleClass().add("buttonStyle2");
	}

	public void objectInitialize() {
		listeners();
		viewUpdate();
		updateSerGroups();
	}

	private void listeners() {
		listenerSerGroupsAddButton();
		listenerSerGroupsDeleteButton();
		listenerSerGroupsUpdateButton();
		listenerSerGroupsTableView();
	}

	private void listenerSerGroupsTableView() {
		serGroupsTableView.setRowFactory(param -> {
			TableRow<SerGroup> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (!row.isEmpty()))
					editSerGroup(row.getItem());
			});
			return row;
		});
	}

	private void listenerSerGroupsAddButton() {
		serGroupsAddButton.setOnAction(event -> newSerGroups());
	}

	private void listenerSerGroupsDeleteButton() {
		serGroupsDeleteButton.setOnAction(event -> deleteSerGroups());
	}

	private void listenerSerGroupsUpdateButton() {
		serGroupsUpdateButton.setOnAction(event -> updateSerGroups());
	}

	private void deleteSerGroups() {

		if (serGroupsTableView.getSelectionModel().getSelectedIndex() > -1) {

			// Family family =
			// familiesTableView.getSelectionModel().getSelectedItem();

			// Alert alert = new AlertDesigner(language.getString("TEXT0034"),
			// family.getSpInf1Decrypted(), ownerStage,
			// AlertType.CONFIRMATION, Meta.Application.getFullTitle(),
			// Meta.Resources.ICON);
			// if (alert.showAndWait().get() == ButtonType.OK)
			// Actions.deleteFamily(String.valueOf(family.getSpFamID()),
			// settings, ownerStage, this);
		}

	}

	private void newSerGroups() {

		if (!isAlreadyOpen(language.getString("TEXT0015"))) {

			try {

				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(Meta.Views.HOME_USER_MENU_SERGROUPS_EDITOR);
				AnchorPane layout = (AnchorPane) fxmlLoader.load();

				UserMenuSerGroupsEditor ctrl = (UserMenuSerGroupsEditor) fxmlLoader.getController();
				ctrl.setSettings(this.settings);
				ctrl.setOwnerStage(ownerStage);
				ctrl.setOwnerCtrl(this);

				Tab newSerGroupsTab = new Tab(language.getString("TEXT0015"), layout);
				newSerGroupsTab.setClosable(true);
				newSerGroupsTab.getStyleClass().add("tabStyle1");
				newSerGroupsTab.setGraphic(new ImageView(Meta.Resources.PLUS));

				ctrl.setSerGroupsTabPane(serGroupsTabPane);
				ctrl.setSerGroupTab(newSerGroupsTab);

				serGroupsTabPane.getTabs().add(newSerGroupsTab);
				serGroupsTabPane.getSelectionModel().select(newSerGroupsTab);

				ctrl.objectInitialize();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void editSerGroup(SerGroup serGroup) {

		// if (!isAlreadyOpen(family.getSpInf1Decrypted())) {
		//
		// try {
		//
		// FXMLLoader fxmlLoader = new FXMLLoader();
		// fxmlLoader.setLocation(Meta.Views.HOME_USER_MENU_CONGR_FAMILY_EDITOR);
		// AnchorPane layout = (AnchorPane) fxmlLoader.load();
		//
		// UserMenuSerGroupsEditor ctrl = (UserMenuSerGroupsEditor)
		// fxmlLoader.getController();
		// ctrl.setSettings(this.settings);
		// ctrl.setOwnerStage(ownerStage);
		// ctrl.setOwnerCtrl(this);
		// // ctrl.setSelectedFamily(family);
		//
		// Tab newFamilyTab = new Tab(family.getSpInf1Decrypted(), layout);
		// newFamilyTab.setClosable(true);
		// newFamilyTab.getStyleClass().add("tabStyle1");
		// newFamilyTab.setGraphic(new
		// ImageView(Meta.Resources.USER_MENU_SERVICEGROUPS));
		//
		// // ctrl.setCongrTabPane(serGroupsTabPane);
		// // ctrl.setMembersTab(membersTab);
		// // ctrl.setNewMemberTab(newFamilyTab);
		// // ctrl.setMembersList(this.membersList);
		//
		// serGroupsTabPane.getTabs().add(newFamilyTab);
		// serGroupsTabPane.getSelectionModel().select(newFamilyTab);
		//
		// ctrl.objectInitialize();
		//
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// }

	}

	private boolean isAlreadyOpen(String label) {

		for (Tab tab : serGroupsTabPane.getTabs())
			if (tab.getText().equals(label)) {
				serGroupsTabPane.getSelectionModel().select(tab);
				return true;
			}

		return false;
	}

	private void viewUpdate() {

		this.language = settings.getLanguage();

		serGroupsTabPane.setTabClosingPolicy(TabClosingPolicy.SELECTED_TAB);

		serGroupsTab.setText(language.getString("TEXT0035"));
		serGroupsTab.setGraphic(new ImageView(Meta.Resources.USER_MENU_SERVICEGROUPS));
		serGroupsTab.setClosable(false);

		serGroupsIDTableColumn.setText(language.getString("TEXT0005"));
		serGroupsIDTableColumn.setMinWidth(50);
		serGroupsIDTableColumn.setMaxWidth(50);
		serGroupsIDTableColumn.setResizable(false);
		serGroupsNameTableColumn.setText(language.getString("TEXT0036"));
		serGroupsOverseerTableColumn.setText(language.getString("TEXT0037"));
		serGroupsAssistantTableColumn.setText(language.getString("TEXT0038"));
		serGroupsFamiliesTableColumn.setText(language.getString("TEXT0012"));
		serGroupsMembersTableColumn.setText(language.getString("TEXT0011"));

		serGroupsAddButton.setText("");
		serGroupsAddButton.setGraphic(new ImageView(Meta.Resources.SERVICEGROUPS_ADD));
		serGroupsDeleteButton.setText("");
		serGroupsDeleteButton.setGraphic(new ImageView(Meta.Resources.SERVICEGROUPS_DEL));
		serGroupsUpdateButton.setText("");
		serGroupsUpdateButton.setGraphic(new ImageView(Meta.Resources.UPDATE));
	}

	@Override
	public void updateSerGroups() {
		Actions.getAllSerGroups(settings, ownerStage, this);
	}

	@Override
	public void updateSerGroups(ObservableList<SerGroup> list) {

		serGroupsList = list;
		serGroupsList.sort((a, b) -> a.getSpInf1Decrypted().compareTo(b.getSpInf1Decrypted()));

		serGroupsTableView.setItems(serGroupsList);
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
