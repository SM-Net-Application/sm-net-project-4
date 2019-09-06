package com.sm.net.sp.view.home.user.menu.naturaldisaster;

import java.io.IOException;

import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.actions.Actions;
import com.sm.net.sp.model.EnumPrintLayouts;
import com.sm.net.sp.model.Family;
import com.sm.net.sp.model.Member;
import com.sm.net.sp.model.UpdateDataAdapter;
import com.sm.net.sp.settings.Settings;
import com.sm.net.sp.view.printlayout.PrintLayout;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class UserMenuNaturalDisasterList extends UpdateDataAdapter {

	@FXML
	private ImageView headerImageView;
	@FXML
	private Label headerLabel;

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
	private TableColumn<Member, String> memberPhoneTableColumn;
	@FXML
	private TableColumn<Member, String> memberEmailTableColumn;

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
	private TableColumn<Family, String> familyPhoneTableColumn;

	@FXML
	private Button printButton;

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
		memberPhoneTableColumn.setCellValueFactory(cellData -> cellData.getValue().spInf40DecryptedProperty());
		memberEmailTableColumn.setCellValueFactory(cellData -> cellData.getValue().spInf41DecryptedProperty());

		familyIDTableColumn.setCellValueFactory(cellData -> cellData.getValue().spFamIDProperty().asObject());
		familyNameTableColumn.setCellValueFactory(cellData -> cellData.getValue().spInf1DecryptedProperty());
		familyCountTableColumn.setCellValueFactory(cellData -> cellData.getValue().spFamMembersProperty().asObject());
		familyStreetTableColumn.setCellValueFactory(cellData -> cellData.getValue().spInf2DecryptedProperty());
		familyNummerTableColumn.setCellValueFactory(cellData -> cellData.getValue().spInf3DecryptedProperty());
		familyPostCodeTableColumn.setCellValueFactory(cellData -> cellData.getValue().spInf4DecryptedProperty());
		familyCityTableColumn.setCellValueFactory(cellData -> cellData.getValue().spInf5DecryptedProperty());
		familyPhoneTableColumn.setCellValueFactory(cellData -> cellData.getValue().spInf7DecryptedProperty());
	}

	private void styleClasses() {

		headerLabel.getStyleClass().add("label_header_001");

		congrTabPane.getStyleClass().add("tab_pane_001");

		membersTab.getStyleClass().add("tab_001");
		familyTab.getStyleClass().add("tab_001");

		membersTableView.getStyleClass().add("table_view_001");
		familiesTableView.getStyleClass().add("table_view_001");

		printButton.getStyleClass().add("button_image_001");
	}

	public void objectInitialize() {
		listeners();
		viewUpdate();
		updateMembers();
		updateFamilies();
	}

	private void viewUpdate() {

		this.language = settings.getLanguage();

		headerLabel.setText(language.getString("sp.menu.naturaldisaster"));

		headerImageView.setFitWidth(50);
		headerImageView.setFitHeight(50);
		headerImageView.setImage(Meta.Resources.getImageLogo(Meta.Resources.USER_MENU_NATURALDISASTER, 50, 50));

		congrTabPane.setTabClosingPolicy(TabClosingPolicy.SELECTED_TAB);

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
		memberPhoneTableColumn.setText(language.getString("TEXT0107"));
		memberEmailTableColumn.setText(language.getString("TEXT0108"));

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
		familyPhoneTableColumn.setText(language.getString("TEXT0109"));

		printButton.setGraphic(Meta.Resources.imageViewForButton(Meta.Resources.PRINT));
		printButton.setText(null);
	}

	private void listeners() {

		listenerMembersTableView();
		listenerFamiliesTableView();

		listenerPrintButton();
	}

	private void listenerPrintButton() {
		this.printButton.setOnAction(event -> print());
	}

	private void print() {

		EnumPrintLayouts selectedLayout = PrintLayout.dialogPrintLayout(this.ownerStage, language,
				EnumPrintLayouts.NATURAL_DISASTER_LIST);

		if (selectedLayout != null) {

			switch (selectedLayout) {

			case NATURAL_DISASTER_LIST:
//				Actions.printWeek(printableWeeks, membersList, congregationName, settings, ownerStage, language, true);
				break;

			default:
				break;
			}

		}
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

	private void editMember(Member member) {

		if (!isAlreadyOpen(member.getNameStyle1())) {

			try {

				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(Meta.Views.HOME_USER_MENU_NATURALDISASTER_MEMBER_EDITOR);
				AnchorPane layout = (AnchorPane) fxmlLoader.load();

				UserMenuNaturalDisasterMemberEditor ctrl = (UserMenuNaturalDisasterMemberEditor) fxmlLoader
						.getController();
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
				fxmlLoader.setLocation(Meta.Views.HOME_USER_MENU_NATURALDISASTER_FAMILY_EDITOR);
				AnchorPane layout = (AnchorPane) fxmlLoader.load();

				UserMenuNaturalDisasterFamilyEditor ctrl = (UserMenuNaturalDisasterFamilyEditor) fxmlLoader
						.getController();
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
