package com.sm.net.sp.view.home.user.menu.congr;

import java.io.IOException;

import com.sm.net.javafx.AlertDesigner;
import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.actions.Actions;
import com.sm.net.sp.model.EnumPrintLayouts;
import com.sm.net.sp.model.Family;
import com.sm.net.sp.model.Member;
import com.sm.net.sp.model.UpdateDataAdapter;
import com.sm.net.sp.settings.Settings;
import com.sm.net.sp.view.SupportPlannerView;
import com.sm.net.sp.view.printlayout.PrintLayout;

import javafx.beans.property.SimpleObjectProperty;
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
import javafx.scene.control.Tooltip;
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
	private Tab membersTab;
	@FXML
	private Tab familyTab;

	@FXML
	private TabPane memberTabPane;
	@FXML
	private Tab memberListTab;

	@FXML
	private TableView<Member> membersTableView;
	@FXML
	private TableColumn<Member, Integer> memberIDTableColumn;
	@FXML
	private TableColumn<Member, String> memberSurnameTableColumn;
	@FXML
	private TableColumn<Member, String> memberNameTableColumn;
	@FXML
	private TableColumn<Member, ImageView> memberIconTableColumn;
	@FXML
	private TableColumn<Member, String> memberSurname2TableColumn;
	@FXML
	private TableColumn<Member, String> memberName2TableColumn;
	@FXML
	private TableColumn<Member, String> memberAgeTableColumn;
	@FXML
	private TableColumn<Member, String> memberNumberTableColumn;
	@FXML
	private TableColumn<Member, String> memberMailTableColumn;

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

	@FXML
	private Button memberMonitorPrintButton;

	private Settings settings;
	private Language language;
	private Stage ownerStage;

	private SupportPlannerView application;

	private ObservableList<Member> membersList;
	private ObservableList<Family> familiesList;

	@FXML
	private void initialize() {
		styleClasses();
		cellValueFactory();
	}

	private void cellValueFactory() {

		this.memberIDTableColumn.setCellValueFactory(cellData -> cellData.getValue().spMemberIDProperty().asObject());
		this.memberSurnameTableColumn.setCellValueFactory(cellData -> cellData.getValue().spInf2DecryptedProperty());
		this.memberNameTableColumn.setCellValueFactory(cellData -> cellData.getValue().spInf1DecryptedProperty());

		this.memberIconTableColumn.setCellValueFactory(cellData -> {

			if (cellData.getValue().getSpInf4() == 0)
				return new SimpleObjectProperty<ImageView>(Meta.Resources.imageForButtonSmall(Meta.Resources.MALE));
			else
				return new SimpleObjectProperty<ImageView>(Meta.Resources.imageForButtonSmall(Meta.Resources.FEMALE));

		});
		this.memberSurname2TableColumn.setCellValueFactory(cellData -> cellData.getValue().spInf39DecryptedProperty());
		this.memberName2TableColumn.setCellValueFactory(cellData -> cellData.getValue().spInf3DecryptedProperty());
		// this.memberAgeTableColumn.setCellValueFactory(cellData ->
		// cellData.getValue().spMemberIDProperty().asObject());
		this.memberNumberTableColumn.setCellValueFactory(cellData -> cellData.getValue().spInf40DecryptedProperty());
		this.memberMailTableColumn.setCellValueFactory(cellData -> cellData.getValue().spInf41DecryptedProperty());

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

		congrTabPane.getStyleClass().add("tab_pane_003");

		membersTab.getStyleClass().add("tab_001");
		familyTab.getStyleClass().add("tab_001");

		this.memberTabPane.getStyleClass().add("tab_pane_001");
		this.memberListTab.getStyleClass().add("tab_001");

		membersTableView.getStyleClass().add("table_view_001");
		familiesTableView.getStyleClass().add("table_view_001");

		memberAddButton.getStyleClass().add("button_image_001");
		memberDeleteButton.getStyleClass().add("button_image_001");
		membersUpdateButton.getStyleClass().add("button_image_001");

		familyAddButton.getStyleClass().add("button_image_001");
		familyDeleteButton.getStyleClass().add("button_image_001");
		familiesUpdateButton.getStyleClass().add("button_image_001");

		memberMonitorPrintButton.getStyleClass().add("button_image_001");
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
		congrImageView.setImage(Meta.Resources.getImageLogo(Meta.Resources.USER_MENU_CONGR, 50, 50));

		congrTabPane.setTabClosingPolicy(TabClosingPolicy.SELECTED_TAB);
		this.congrTabPane.setTabMinHeight(75);
		this.congrTabPane.setTabMaxHeight(75);

		Tooltip memberTabTooltip = new Tooltip(language.getString("TEXT0011"));
		memberTabTooltip.getStyleClass().add("tooltip_001");
		this.membersTab.setTooltip(memberTabTooltip);
		this.membersTab.setText("");
		this.membersTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.MEMBER));
		this.membersTab.setClosable(false);

		Tooltip familyTabTooltip = new Tooltip(language.getString("TEXT0012"));
		familyTabTooltip.getStyleClass().add("tooltip_001");
		this.familyTab.setTooltip(familyTabTooltip);
		this.familyTab.setText("");
		this.familyTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.FAMILY));
		this.familyTab.setClosable(false);

		this.memberListTab.setText(this.language.getString("congregation.members.list"));

		memberIDTableColumn.setText(language.getString("TEXT0005"));
		memberIDTableColumn.setMinWidth(50);
		memberIDTableColumn.setMaxWidth(50);
		memberIDTableColumn.setResizable(false);

		this.memberIconTableColumn.setText("");
		this.memberIconTableColumn.setMinWidth(50);
		this.memberIconTableColumn.setMaxWidth(50);
		this.memberIconTableColumn.setResizable(false);

		this.memberSurnameTableColumn.setText(this.language.getString("TEXT0013"));
		this.memberSurname2TableColumn.setText(this.language.getString("congregation.members.column.surname2"));
		this.memberNameTableColumn.setText(this.language.getString("TEXT0014"));

		this.memberName2TableColumn.setText("");
		this.memberName2TableColumn.setMinWidth(50);
		this.memberName2TableColumn.setMaxWidth(50);
		this.memberName2TableColumn.setResizable(false);

		this.memberAgeTableColumn.setText(this.language.getString("congregation.members.column.age"));
		this.memberAgeTableColumn.setMinWidth(50);
		this.memberAgeTableColumn.setMaxWidth(50);
		this.memberAgeTableColumn.setResizable(false);

		Tooltip memberAddTooltip = new Tooltip(this.language.getString("congregation.members.tooltip.add"));
		memberAddTooltip.getStyleClass().add("tooltip_001");
		this.memberAddButton.setTooltip(memberAddTooltip);
		this.memberAddButton.setText("");
		this.memberAddButton.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.MEMBER_ADD));

		Tooltip memberDeleteTooltip = new Tooltip(this.language.getString("congregation.members.tooltip.delete"));
		memberDeleteTooltip.getStyleClass().add("tooltip_001");
		this.memberDeleteButton.setTooltip(memberDeleteTooltip);
		this.memberDeleteButton.setText("");
		this.memberDeleteButton.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.MEMBER_DEL));

		Tooltip membersUpdateTooltip = new Tooltip(this.language.getString("congregation.members.tooltip.update"));
		membersUpdateTooltip.getStyleClass().add("tooltip_001");
		this.membersUpdateButton.setTooltip(membersUpdateTooltip);
		this.membersUpdateButton.setText("");
		this.membersUpdateButton.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.UPDATE));

		Tooltip memberMonitorPrintTooltip = new Tooltip(this.language.getString("congregation.members.tooltip.print"));
		memberMonitorPrintTooltip.getStyleClass().add("tooltip_001");
		this.memberMonitorPrintButton.setTooltip(memberMonitorPrintTooltip);
		this.memberMonitorPrintButton.setText("");
		this.memberMonitorPrintButton.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.PRINT));

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
		familyAddButton.setGraphic(Meta.Resources.imageViewForButton(Meta.Resources.FAMILY_ADD));
		familyDeleteButton.setText("");
		familyDeleteButton.setGraphic(Meta.Resources.imageViewForButton(Meta.Resources.FAMILY_DEL));
		familiesUpdateButton.setText("");
		familiesUpdateButton.setGraphic(Meta.Resources.imageViewForButton(Meta.Resources.UPDATE));

	}

	private void initInfo() {

		// TODO : load Info con il nuovo sistema

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

		listenerMemberMonitorPrintButton();
	}

	private void listenerMemberMonitorPrintButton() {
		this.memberMonitorPrintButton.setOnAction(event -> print());
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
		membersUpdateButton.setOnAction(event -> updateMembers());
	}

	private void listenerFamilyUpdateButton() {
		familiesUpdateButton.setOnAction(event -> updateFamilies());
	}

	private void deleteMember() {

		if (membersTableView.getSelectionModel().getSelectedIndex() > -1) {

			Member member = membersTableView.getSelectionModel().getSelectedItem();

			Alert alert = new AlertDesigner(language.getString("TEXT0024"), member.getNameStyle1(), ownerStage,
					AlertType.CONFIRMATION, Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
					Meta.Themes.SUPPORTPLANNER_THEME, "alert_001");
			if (alert.showAndWait().get() == ButtonType.OK)
				Actions.deleteMember(String.valueOf(member.getSpMemberID()), settings, ownerStage, this);
		}

	}

	private void deleteFamily() {

		if (familiesTableView.getSelectionModel().getSelectedIndex() > -1) {

			Family family = familiesTableView.getSelectionModel().getSelectedItem();

			Alert alert = new AlertDesigner(language.getString("TEXT0034"), family.getSpInf1Decrypted(), ownerStage,
					AlertType.CONFIRMATION, Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
					Meta.Themes.SUPPORTPLANNER_THEME, "alert_001");
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

				// ctrl.setCongrTabPane(congrTabPane);
				// ctrl.setMembersTab(membersTab);
				ctrl.setCongrTabPane(this.memberTabPane);
				ctrl.setMembersTab(this.memberListTab);
				ctrl.setNewMemberTab(newMemberTab);

//				congrTabPane.getTabs().add(newMemberTab);
//				congrTabPane.getSelectionModel().select(newMemberTab);
				this.memberTabPane.getTabs().add(newMemberTab);
				this.memberTabPane.getSelectionModel().select(newMemberTab);

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

	private void print() {

		if (this.membersTableView.getSelectionModel().getSelectedIndex() > -1) {

			Member member = membersTableView.getSelectionModel().getSelectedItem();

			EnumPrintLayouts selectedLayout = PrintLayout.dialogPrintLayout(this.ownerStage, language,
					EnumPrintLayouts.MEMBER_PASSWORD_MONITOR);

			if (selectedLayout != null) {

				switch (selectedLayout) {

				case MEMBER_PASSWORD_MONITOR:

					String spInf47 = member.getSpInf47();
					if (!spInf47.isEmpty()) {

						String dbUrl = this.settings.getDatabaseUrl();
						int indexOf = dbUrl.indexOf("exchange.php");
						if (indexOf > -1) {

							String memberName = member.getNameStyle1();

							String subDbUrl = dbUrl.substring(0, indexOf);
							String link = subDbUrl + "monitor/index.php?pwmon=%s&amp;lang=%s";
							link = String.format(link, spInf47, this.language.getString("sp.monitor.language"));

							Actions.printMonitorPassword(memberName, spInf47, link, settings, ownerStage, language);
						}
					} else {

						String header = member.getNameStyle1();

						this.application.getAlertBuilder2().error(this.ownerStage, header,
								this.language.getString("congregation.members.print.nopasswordmonitor"));
					}

					break;

				default:
					break;
				}
			}
		} else
			this.application.getAlertBuilder2().error(this.ownerStage,
					this.language.getString("congregation.members.print.noselect"));

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

	public SupportPlannerView getApplication() {
		return application;
	}

	public void setApplication(SupportPlannerView application) {
		this.application = application;
	}
}
