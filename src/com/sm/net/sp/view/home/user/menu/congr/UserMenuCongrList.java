package com.sm.net.sp.view.home.user.menu.congr;

import java.io.IOException;

import com.sm.net.javafx.AlertDesigner;
import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.actions.Actions;
import com.sm.net.sp.model.Family;
import com.sm.net.sp.model.Info;
import com.sm.net.sp.model.Info.EnumActions;
import com.sm.net.sp.model.Member;
import com.sm.net.sp.model.UpdateDataAdapter;
import com.sm.net.sp.settings.Settings;
import com.sm.net.util.Crypt;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class UserMenuCongrList extends UpdateDataAdapter {

	@FXML
	private ImageView congrImageView;
	@FXML
	private Label congrHeaderLabel;

	@FXML
	private TabPane congrTabPane;
	@FXML
	private Tab generalTab;
	@FXML
	private Tab membersTab;
	@FXML
	private Tab familyTab;

	@FXML
	private Label congrLabel;
	@FXML
	private TextField congrTextField;
	// @FXML
	// private Label overseer1Label;
	// @FXML
	// private Label overseer1NameLabel;
	// @FXML
	// private TextField overseer1NameTextField;
	// @FXML
	// private Label overseer1WifeLabel;
	// @FXML
	// private TextField overseer1WifeTextField;
	// @FXML
	// private Label overseer2Label;
	// @FXML
	// private Label overseer2NameLabel;
	// @FXML
	// private TextField overseer2NameTextField;
	// @FXML
	// private Label overseer2WifeLabel;
	// @FXML
	// private TextField overseer2WifeTextField;

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

	private String bufferCongr;
	// private String bufferOverseer1;
	// private String bufferOverseer1Wife;
	// private String bufferOverseer2;
	// private String bufferOverseer2Wife;

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

		congrHeaderLabel.getStyleClass().add("label_header_001");

		congrTabPane.getStyleClass().add("tab_pane_001");

		generalTab.getStyleClass().add("tab_001");
		membersTab.getStyleClass().add("tab_001");
		familyTab.getStyleClass().add("tab_001");

		membersTableView.getStyleClass().add("tableViewStyle1");
		familiesTableView.getStyleClass().add("tableViewStyle1");

		memberAddButton.getStyleClass().add("button_image_001");
		memberDeleteButton.getStyleClass().add("button_image_001");
		membersUpdateButton.getStyleClass().add("button_image_001");

		familyAddButton.getStyleClass().add("button_image_001");
		familyDeleteButton.getStyleClass().add("button_image_001");
		familiesUpdateButton.getStyleClass().add("button_image_001");

		congrLabel.getStyleClass().add("label_set_001");
		congrTextField.getStyleClass().add("text_field_001");

		// overseer1Label.getStyleClass().add("labelStyle2");
		// overseer1NameLabel.getStyleClass().add("labelStyle3");
		// overseer1NameTextField.getStyleClass().add("textFieldStyle1");
		// overseer1WifeLabel.getStyleClass().add("labelStyle3");
		// overseer1WifeTextField.getStyleClass().add("textFieldStyle1");
		// overseer2Label.getStyleClass().add("labelStyle2");
		// overseer2NameLabel.getStyleClass().add("labelStyle3");
		// overseer2NameTextField.getStyleClass().add("textFieldStyle1");
		// overseer2WifeLabel.getStyleClass().add("labelStyle3");
		// overseer2WifeTextField.getStyleClass().add("textFieldStyle1");
	}

	public void objectInitialize() {
		listeners();
		viewUpdate();
		initInfo();
		updateMembers();
		updateFamilies();
	}

	private void viewUpdate() {

		this.language = settings.getLanguage();

		congrHeaderLabel.setText(language.getString("USERMENU002"));

		congrImageView.setFitWidth(50);
		congrImageView.setFitHeight(50);
		congrImageView.setImage(Meta.Resources.USER_MENU_CONGR);

		congrTabPane.setTabClosingPolicy(TabClosingPolicy.SELECTED_TAB);

		generalTab.setText(language.getString("TEXT0043"));
		generalTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.INFO));
		generalTab.setClosable(false);

		membersTab.setText(language.getString("TEXT0011"));
		membersTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.MEMBER));
		membersTab.setClosable(false);

		familyTab.setText(language.getString("TEXT0012"));
		familyTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.FAMILY));
		familyTab.setClosable(false);

		memberIDTableColumn.setText(language.getString("TEXT0005"));
		memberIDTableColumn.setMinWidth(50);
		memberIDTableColumn.setMaxWidth(50);
		memberIDTableColumn.setResizable(false);
		memberSurnameTableColumn.setText(language.getString("TEXT0013"));
		memberNameTableColumn.setText(language.getString("TEXT0014"));

		memberAddButton.setText("");
		memberAddButton.setGraphic(Meta.Resources.imageForButton(Meta.Resources.MEMBER_ADD));

		memberDeleteButton.setText("");
		memberDeleteButton.setGraphic(Meta.Resources.imageForButton(Meta.Resources.MEMBER_DEL));

		membersUpdateButton.setText("");
		membersUpdateButton.setGraphic(Meta.Resources.imageForButton(Meta.Resources.UPDATE));

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
		familyAddButton.setGraphic(Meta.Resources.imageForButton(Meta.Resources.FAMILY_ADD));
		familyDeleteButton.setText("");
		familyDeleteButton.setGraphic(Meta.Resources.imageForButton(Meta.Resources.FAMILY_DEL));
		familiesUpdateButton.setText("");
		familiesUpdateButton.setGraphic(Meta.Resources.imageForButton(Meta.Resources.UPDATE));

		congrLabel.setText(language.getString("sp.congr.name"));

		// overseer1Label.setText(language.getString("TEXT0131"));
		// overseer1NameLabel.setText(language.getString("TEXT0037"));
		// overseer1WifeLabel.setText(language.getString("TEXT0133"));
		// overseer2Label.setText(language.getString("TEXT0132"));
		// overseer2NameLabel.setText(language.getString("TEXT0037"));
		// overseer2WifeLabel.setText(language.getString("TEXT0133"));
	}

	private void initInfo() {
		Actions.getUserMenuCongrListInfo(settings, ownerStage, this);
	}

	@Override
	public void updateInfo(Info info) {
		super.updateInfo(info);

		String congr = info.getCongr();
		setTextField(congrTextField, congr);

		// setTextField(overseer1NameTextField, info.getOverseer1());
		// setTextField(overseer1WifeTextField, info.getOverseer1wife());
		// setTextField(overseer2NameTextField, info.getOverseer2());
		// setTextField(overseer2WifeTextField, info.getOverseer2wife());

		if (!congr.isEmpty())
			congrHeaderLabel.setText(language.getString("USERMENU002") + ": " + congr);
	}

	private void setTextField(TextField tf, String text) {
		if (text != null)
			tf.setText(text);
		else
			tf.setText("");
	}

	private void listeners() {

		listenerCongrTextField();

		// listenerOverseer1NameTextField();
		// listenerOverseer1WifeTextField();
		// listenerOverseer2NameTextField();
		// listenerOverseer2WifeTextField();

		listenerMemberAddButton();
		listenerMemberDeleteButton();
		listenerMembersUpdateButton();
		listenerMembersTableView();

		listenerFamilyAddButton();
		listenerFamilyDeleteButton();
		listenerFamilyUpdateButton();
		listenerFamiliesTableView();
	}

	private void listenerCongrTextField() {
		congrTextField.focusedProperty()
				.addListener((observable, oldValue, newValue) -> congrTextFieldFocused(newValue.booleanValue()));
	}

	// private void listenerOverseer1NameTextField() {
	// overseer1NameTextField.focusedProperty().addListener(
	// (observable, oldValue, newValue) ->
	// overseer1NameTextFieldFocused(newValue.booleanValue()));
	// }
	//
	// private void listenerOverseer1WifeTextField() {
	// overseer1WifeTextField.focusedProperty().addListener(
	// (observable, oldValue, newValue) ->
	// overseer1WifeTextFieldFocused(newValue.booleanValue()));
	// }
	//
	// private void listenerOverseer2NameTextField() {
	// overseer2NameTextField.focusedProperty().addListener(
	// (observable, oldValue, newValue) ->
	// overseer2NameTextFieldFocused(newValue.booleanValue()));
	// }
	//
	// private void listenerOverseer2WifeTextField() {
	// overseer2WifeTextField.focusedProperty().addListener(
	// (observable, oldValue, newValue) ->
	// overseer2WifeTextFieldFocused(newValue.booleanValue()));
	// }

	private void congrTextFieldFocused(boolean focused) {
		if (focused)
			bufferCongr = congrTextField.getText();
		else {
			String newText = congrTextField.getText();
			if (!newText.equals(bufferCongr))
				Info.runAction(EnumActions.SAVE, Info.KEYS.CONGR,
						Crypt.encrypt(newText, settings.getDatabaseSecretKey()), settings, ownerStage);
		}
	}

	// private void overseer1NameTextFieldFocused(boolean focused) {
	// if (focused)
	// bufferOverseer1 = overseer1NameTextField.getText();
	// else {
	// String newText = overseer1NameTextField.getText();
	// if (!newText.equals(bufferOverseer1))
	// Info.runAction(EnumActions.SAVE, Info.KEYS.OVERSEER1,
	// Crypt.encrypt(newText, settings.getDatabaseSecretKey()), settings,
	// ownerStage);
	// }
	// }
	//
	// private void overseer1WifeTextFieldFocused(boolean focused) {
	// if (focused)
	// bufferOverseer1Wife = overseer1WifeTextField.getText();
	// else {
	// String newText = overseer1WifeTextField.getText();
	// if (!newText.equals(bufferOverseer1Wife))
	// Info.runAction(EnumActions.SAVE, Info.KEYS.OVERSEER1WIFE,
	// Crypt.encrypt(newText, settings.getDatabaseSecretKey()), settings,
	// ownerStage);
	// }
	// }
	//
	// private void overseer2NameTextFieldFocused(boolean focused) {
	// if (focused)
	// bufferOverseer2 = overseer2NameTextField.getText();
	// else {
	// String newText = overseer2NameTextField.getText();
	// if (!newText.equals(bufferOverseer2))
	// Info.runAction(EnumActions.SAVE, Info.KEYS.OVERSEER2,
	// Crypt.encrypt(newText, settings.getDatabaseSecretKey()), settings,
	// ownerStage);
	// }
	// }
	//
	// private void overseer2WifeTextFieldFocused(boolean focused) {
	// if (focused)
	// bufferOverseer2Wife = overseer2WifeTextField.getText();
	// else {
	// String newText = overseer2WifeTextField.getText();
	// if (!newText.equals(bufferOverseer2Wife))
	// Info.runAction(EnumActions.SAVE, Info.KEYS.OVERSEER2WIFE,
	// Crypt.encrypt(newText, settings.getDatabaseSecretKey()), settings,
	// ownerStage);
	// }
	// }

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
		membersUpdateButton.setOnAction(event -> updateMembers());
	}

	private void listenerFamilyUpdateButton() {
		familiesUpdateButton.setOnAction(event -> updateFamilies());
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
				newMemberTab.getStyleClass().add("tab_001");
				newMemberTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.PLUS));

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
				newFamilyTab.getStyleClass().add("tab_001");
				newFamilyTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.PLUS));

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
				newMemberTab.getStyleClass().add("tab_001");
				newMemberTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.MEMBER));

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
				newFamilyTab.getStyleClass().add("tab_001");
				newFamilyTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.FAMILY));

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

	@Override
	public void updateMembers() {
		Actions.getAllMembers(settings, ownerStage, this);
	}

	@Override
	public void updateFamilies() {
		Actions.getAllFamilies(settings, ownerStage, this);
	}

	@Override
	public void updateMembers(ObservableList<Member> list) {

		this.membersList = list;
		membersList.sort((a, b) -> (a.getSpInf2Decrypted().concat(a.getSpInf1Decrypted())
				.compareTo(b.getSpInf2Decrypted().concat(b.getSpInf1Decrypted()))));

		membersTableView.setItems(membersList);
	}

	@Override
	public void updateFamilies(ObservableList<Family> list) {

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
