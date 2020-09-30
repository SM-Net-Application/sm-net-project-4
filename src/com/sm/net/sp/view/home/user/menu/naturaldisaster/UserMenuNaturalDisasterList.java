package com.sm.net.sp.view.home.user.menu.naturaldisaster;

import java.io.IOException;
import java.util.stream.StreamSupport;

import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.actions.Actions;
import com.sm.net.sp.model.EnumPrintLayouts;
import com.sm.net.sp.model.Family;
import com.sm.net.sp.model.Info;
import com.sm.net.sp.model.Member;
import com.sm.net.sp.model.SerGroup;
import com.sm.net.sp.model.UpdateDataAdapter;
import com.sm.net.sp.model.WeekOverseer;
import com.sm.net.sp.settings.Settings;
import com.sm.net.sp.view.printlayout.PrintLayout;

import javafx.collections.FXCollections;
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
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
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
	private Tab overseerTab;
	@FXML
	private Tab membersTab;
	@FXML
	private Tab familyTab;

	@FXML
	private TabPane memberListTabPane;
	@FXML
	private Tab memberListTab;

	@FXML
	private TabPane familyTabPane;
	@FXML
	private Tab familyListTab;

	@FXML
	private Label overseerLabel;
	@FXML
	private Label overseerPhoneLabel;
	@FXML
	private Label overseerMailLabel;
	@FXML
	private TextField overseerTextField;
	@FXML
	private TextField overseerPhoneTextField;
	@FXML
	private TextField overseerMailTextField;

	@FXML
	private TableView<Member> membersTableView;
	@FXML
	private TableColumn<Member, Integer> memberIDTableColumn;
	@FXML
	private TableColumn<Member, String> memberSurnameTableColumn;
	@FXML
	private TableColumn<Member, String> memberNameTableColumn;
	@FXML
	private TableColumn<Member, String> memberShortNameTableColumn;
	@FXML
	private TableColumn<Member, String> memberSurname2TableColumn;
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
	private TextField filterMembers;
	@FXML
	private TextField filterFamilies;

	@FXML
	private Button printButton1;

	private String congregationName;

	private Settings settings;
	private Language language;
	private Stage ownerStage;

	private ObservableList<Member> membersList;
	private ObservableList<Family> familiesList;
	private ObservableList<SerGroup> servGroupList;

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

		this.memberSurname2TableColumn.setCellValueFactory(cellData -> cellData.getValue().spInf39DecryptedProperty());
		this.memberShortNameTableColumn.setCellValueFactory(cellData -> cellData.getValue().spInf3DecryptedProperty());

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

		this.congrTabPane.getStyleClass().add("tab_pane_003");

		this.memberListTabPane.getStyleClass().add("tab_pane_001");
		this.familyTabPane.getStyleClass().add("tab_pane_001");

		this.membersTab.getStyleClass().add("tab_001");
		this.familyTab.getStyleClass().add("tab_001");
		this.overseerTab.getStyleClass().add("tab_001");

		this.memberListTab.getStyleClass().add("tab_001");
		this.filterMembers.getStyleClass().add("text_field_001");

		this.memberIDTableColumn.getStyleClass().add("table_column_002");
		this.familyIDTableColumn.getStyleClass().add("table_column_002");

		this.familyListTab.getStyleClass().add("tab_001");
		this.filterFamilies.getStyleClass().add("text_field_001");

		this.memberIDTableColumn.getStyleClass().add("table_column_002");

		overseerLabel.getStyleClass().add("label_set_001");
		overseerPhoneLabel.getStyleClass().add("label_set_001");
		overseerMailLabel.getStyleClass().add("label_set_001");

		overseerTextField.getStyleClass().add("text_field_001");
		overseerPhoneTextField.getStyleClass().add("text_field_001");
		overseerMailTextField.getStyleClass().add("text_field_001");

		membersTableView.getStyleClass().add("table_view_001");
		familiesTableView.getStyleClass().add("table_view_001");

		printButton1.getStyleClass().add("button_image_001");
	}

	public void objectInitialize() {
		listeners();
		viewUpdate();
		loadGeneralInfo();
		updateWeeksOverseer();
		updateMembers();
		updateFamilies();

		updateSerGroups();
	}

	@Override
	public void updateSerGroups() {
		Actions.getAllSerGroups(this.settings, this.ownerStage, this);
	}

	@Override
	public void updateSerGroups(ObservableList<SerGroup> list) {

		this.servGroupList = list;
		this.servGroupList.sort((a, b) -> a.getSpInf1Decrypted().compareTo(b.getSpInf1Decrypted()));
	}

	@Override
	public void updateWeeksOverseer(ObservableList<WeekOverseer> list) {
		super.updateWeeksOverseer(list);

		if (list.size() == 1) {
			WeekOverseer weekOverseer = list.get(0);
			this.overseerTextField.setText(weekOverseer.getOverseer());
			this.overseerPhoneTextField.setText(weekOverseer.getSpInf16());
			this.overseerMailTextField.setText(weekOverseer.getSpInf17());
		}
	}

	@Override
	public void updateWeeksOverseer() {
		super.updateWeeksOverseer();

		Actions.getLastCircuitOverseerWeeks(this.settings, this.ownerStage, this);
	}

	private void viewUpdate() {

		this.language = settings.getLanguage();

		headerLabel.setText(language.getString("sp.menu.naturaldisaster"));

		headerImageView.setFitWidth(50);
		headerImageView.setFitHeight(50);
		headerImageView.setImage(Meta.Resources.getImageLogo(Meta.Resources.USER_MENU_NATURALDISASTER, 50, 50));

		this.congrTabPane.setTabClosingPolicy(TabClosingPolicy.SELECTED_TAB);
		this.congrTabPane.setTabMinHeight(75);
		this.congrTabPane.setTabMaxHeight(75);

		Tooltip overseerTabTooltip = new Tooltip(this.language.getString("sp.naturaldisaster.overseer"));
		overseerTabTooltip.getStyleClass().add("tooltip_001");
		this.overseerTab.setTooltip(overseerTabTooltip);
		this.overseerTab.setText("");
		this.overseerTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.USER_MENU_CIRCUITOVERSEER));
		this.overseerTab.setClosable(false);

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

		overseerLabel.setText(language.getString("sp.naturaldisaster.overseername"));
		overseerPhoneLabel.setText(language.getString("sp.naturaldisaster.phone"));
		overseerMailLabel.setText(language.getString("sp.naturaldisaster.mail"));

		memberIDTableColumn.setText(language.getString("TEXT0005"));
		memberIDTableColumn.setMinWidth(50);
		memberIDTableColumn.setMaxWidth(50);
		memberIDTableColumn.setResizable(false);
		memberSurnameTableColumn.setText(language.getString("TEXT0013"));
		memberNameTableColumn.setText(language.getString("TEXT0014"));
		memberPhoneTableColumn.setText(language.getString("TEXT0107"));
		memberEmailTableColumn.setText(language.getString("TEXT0108"));

		this.memberSurname2TableColumn.setText(this.language.getString("naturaldisaster.members.column.surname2"));

		this.memberShortNameTableColumn.setText("");
		this.memberShortNameTableColumn.setMinWidth(75);
		this.memberShortNameTableColumn.setMaxWidth(75);
		this.memberShortNameTableColumn.setResizable(false);

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

		Tooltip printTabTooltip = new Tooltip(this.language.getString("naturaldisaster.tooltip.print"));
		printTabTooltip.getStyleClass().add("tooltip_001");
		this.printButton1.setTooltip(printTabTooltip);
		this.printButton1.setText("");
		this.printButton1.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.PRINT));

		this.memberListTab.setText(this.language.getString("naturaldisaster.tab.memberlist"));
		this.familyListTab.setText(this.language.getString("naturaldisaster.tab.familylist"));
	}

	private void listeners() {

		listenerMembersTableView();
		listenerFamiliesTableView();

		listenerPrintButton();

		this.filterMembers.textProperty().addListener((observable, oldValue, newValue) -> updateFilterMember(newValue));
		this.filterFamilies.textProperty()
				.addListener((observable, oldValue, newValue) -> updateFilterFamily(newValue));
	}

	private void updateFilterFamily(String newValue) {

		if (newValue.isEmpty()) {
			this.familiesTableView.setItems(this.familiesList);
		} else {
			ObservableList<Family> filteredMemberList = buildListFamily(newValue);
			this.familiesTableView.setItems(filteredMemberList);
		}

		this.familiesTableView.refresh();
	}

	private ObservableList<Family> buildListFamily(String filter) {

		ObservableList<Family> list = FXCollections.observableArrayList();

		StreamSupport.stream(this.familiesList.spliterator(), false).filter(obj -> matchFilterFamily(obj, filter))
				.forEach(obj -> list.add(obj));

		return list;
	}

	private boolean matchFilterFamily(Family obj, String match) {

		String filter = match.toLowerCase();

		return obj.getSpInf1Decrypted().toLowerCase().contains(filter)
				|| obj.getSpInf2Decrypted().toLowerCase().contains(filter)
				|| obj.getSpInf3Decrypted().toLowerCase().contains(filter)
				|| obj.getSpInf4Decrypted().toLowerCase().contains(filter)
				|| obj.getSpInf5Decrypted().toLowerCase().contains(filter)
				|| obj.getSpInf7Decrypted().toLowerCase().contains(filter);
	}

	private void updateFilterMember(String newValue) {

		if (newValue.isEmpty())
			this.membersTableView.setItems(this.membersList);
		else {
			ObservableList<Member> filteredMemberList = buildListMember(newValue);
			this.membersTableView.setItems(filteredMemberList);
		}

		this.membersTableView.refresh();
	}

	private ObservableList<Member> buildListMember(String filter) {

		ObservableList<Member> list = FXCollections.observableArrayList();

		StreamSupport.stream(this.membersList.spliterator(), false).filter(obj -> matchFilterMember(obj, filter))
				.forEach(obj -> list.add(obj));

		return list;
	}

	private boolean matchFilterMember(Member obj, String match) {

		String filter = match.toLowerCase();

		return obj.getSpInf1Decrypted().toLowerCase().contains(filter)
				|| obj.getSpInf2Decrypted().toLowerCase().contains(filter)
				|| obj.getSpInf39Decrypted().toLowerCase().contains(filter);
	}

	private void loadGeneralInfo() {
		Actions.getUserMenuNaturalDisasterInfo(settings, ownerStage, this);
	}

	@Override
	public void updateInfo(Info info) {
		super.updateInfo(info);

		this.congregationName = info.getCongr();
	}

	private void listenerPrintButton() {
		this.printButton1.setOnAction(event -> print());
	}

	private void print() {

		EnumPrintLayouts selectedLayout = PrintLayout.dialogPrintLayout(this.ownerStage, language, this.servGroupList,
				EnumPrintLayouts.NATURAL_DISASTER_LIST, EnumPrintLayouts.NATURAL_DISASTER_SERVICEGROUPS);

		if (selectedLayout != null) {

			String overseerName = this.overseerTextField.getText();
			String overseerPhone = this.overseerPhoneTextField.getText();
			String overseerMail = this.overseerMailTextField.getText();

			switch (selectedLayout) {

			case NATURAL_DISASTER_LIST:

				Actions.printNaturalDisaster(overseerName, overseerPhone, overseerMail, null, this.membersList,
						this.familiesList, this.congregationName, settings, ownerStage, language);
				break;

			case NATURAL_DISASTER_SERVICEGROUPS_1:

				Actions.printNaturalDisaster(overseerName, overseerPhone, overseerMail, this.servGroupList.get(0),
						this.membersList, this.familiesList, this.congregationName, settings, ownerStage, language);

				break;

			case NATURAL_DISASTER_SERVICEGROUPS_2:

				Actions.printNaturalDisaster(overseerName, overseerPhone, overseerMail, this.servGroupList.get(1),
						this.membersList, this.familiesList, this.congregationName, settings, ownerStage, language);
				break;

			case NATURAL_DISASTER_SERVICEGROUPS_3:

				Actions.printNaturalDisaster(overseerName, overseerPhone, overseerMail, this.servGroupList.get(2),
						this.membersList, this.familiesList, this.congregationName, settings, ownerStage, language);
				break;

			case NATURAL_DISASTER_SERVICEGROUPS_4:

				Actions.printNaturalDisaster(overseerName, overseerPhone, overseerMail, this.servGroupList.get(3),
						this.membersList, this.familiesList, this.congregationName, settings, ownerStage, language);
				break;

			case NATURAL_DISASTER_SERVICEGROUPS_5:

				Actions.printNaturalDisaster(overseerName, overseerPhone, overseerMail, this.servGroupList.get(4),
						this.membersList, this.familiesList, this.congregationName, settings, ownerStage, language);
				break;

			case NATURAL_DISASTER_SERVICEGROUPS_6:

				Actions.printNaturalDisaster(overseerName, overseerPhone, overseerMail, this.servGroupList.get(5),
						this.membersList, this.familiesList, this.congregationName, settings, ownerStage, language);
				break;

			case NATURAL_DISASTER_SERVICEGROUPS_7:

				Actions.printNaturalDisaster(overseerName, overseerPhone, overseerMail, this.servGroupList.get(6),
						this.membersList, this.familiesList, this.congregationName, settings, ownerStage, language);
				break;

			case NATURAL_DISASTER_SERVICEGROUPS_8:

				Actions.printNaturalDisaster(overseerName, overseerPhone, overseerMail, this.servGroupList.get(7),
						this.membersList, this.familiesList, this.congregationName, settings, ownerStage, language);
				break;

			case NATURAL_DISASTER_SERVICEGROUPS_9:

				Actions.printNaturalDisaster(overseerName, overseerPhone, overseerMail, this.servGroupList.get(8),
						this.membersList, this.familiesList, this.congregationName, settings, ownerStage, language);
				break;

			case NATURAL_DISASTER_SERVICEGROUPS_10:

				Actions.printNaturalDisaster(overseerName, overseerPhone, overseerMail, this.servGroupList.get(9),
						this.membersList, this.familiesList, this.congregationName, settings, ownerStage, language);
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

				if (member.getSpInf4() == 0)
					newMemberTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.MALE));
				else if (member.getSpInf4() == 1)
					newMemberTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.FEMALE));

				ctrl.setCongrTabPane(this.memberListTabPane);
				ctrl.setMembersTab(this.memberListTab);
				ctrl.setNewMemberTab(newMemberTab);

				this.memberListTabPane.getTabs().add(newMemberTab);
				this.memberListTabPane.getSelectionModel().select(newMemberTab);

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

				ctrl.setCongrTabPane(this.familyTabPane);
				ctrl.setMembersTab(this.familyListTab);
				ctrl.setNewMemberTab(newFamilyTab);

				this.familyTabPane.getTabs().add(newFamilyTab);
				this.familyTabPane.getSelectionModel().select(newFamilyTab);

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

	public String getCongregationName() {
		return congregationName;
	}

	public void setCongregationName(String congregationName) {
		this.congregationName = congregationName;
	}
}
