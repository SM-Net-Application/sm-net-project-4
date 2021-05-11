package com.sm.net.sp.view.home.user.menu.territory;

import java.awt.Desktop;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.stream.StreamSupport;

import com.sm.net.javafx.AlertDesigner;
import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.actions.Actions;
import com.sm.net.sp.model.EnumPrintLayouts;
import com.sm.net.sp.model.Family;
import com.sm.net.sp.model.Member;
import com.sm.net.sp.model.UpdateDataAdapter;
import com.sm.net.sp.settings.Settings;
import com.sm.net.sp.utils.CommonUtils;
import com.sm.net.sp.view.SupportPlannerView;
import com.sm.net.sp.view.home.user.menu.congr.task.CongrInitDataLoadTask;
import com.sm.net.sp.view.printlayout.PrintLayout;
import com.smnet.core.task.TaskManager;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
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
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Territory extends UpdateDataAdapter {

	@FXML
	private ImageView congrImageView;
	@FXML
	private Label congrHeaderLabel;

	@FXML
	private TabPane congrTabPane;
	@FXML
	private Tab territoryTab;
	@FXML
	private Tab publisherTab;

	@FXML
	private TabPane memberTabPane;
	@FXML
	private Tab memberListTab;

	@FXML
	private TabPane territoryTabPane;
	@FXML
	private Tab familyListTab;

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
	private TableColumn<Member, BigDecimal> memberAgeTableColumn;
	@FXML
	private TableColumn<Member, String> memberNumberTableColumn;
	@FXML
	private TableColumn<Member, String> memberMailTableColumn;
	@FXML
	private TableColumn<Member, BigDecimal> memberAgeBaptismTableColumn;
	@FXML
	private TableColumn<Member, ImageView> memberMonitorTableColumn;

	@FXML
	private Button memberAddButton;
	@FXML
	private Button memberDeleteButton;
	@FXML
	private Button membersUpdateButton;
	@FXML
	private Button memberMonitorPrintButton;

	@FXML
	private TextField filterMemberTextField;
	@FXML
	private ImageView totalImageView;
	@FXML
	private ImageView totalMaleImageView;
	@FXML
	private ImageView totalFemaleImageView;
	@FXML
	private TextField totalTextField;
	@FXML
	private TextField totalMaleTextField;
	@FXML
	private TextField totalFemaleTextField;

	@FXML
	private TableView<Family> familiesTableView;
	@FXML
	private TableColumn<Family, Integer> familyIDTableColumn;
	@FXML
	private TableColumn<Family, ImageView> familyMapsTableColumn;
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
	private Button territoryAddButton;
	@FXML
	private Button territoryRemoveButton;
	@FXML
	private Button territoryEditButton;
	@FXML
	private Button familiesMapsButton;

	@FXML
	private TextField filterFamilyTextField;
	@FXML
	private ImageView totalFamilyImageView;
	@FXML
	private TextField totalFamilyTextField;

	@FXML
	private ImageView totalFilteredFamilyImageView;
	@FXML
	private TextField totalFilteredFamilyTextField;

	private Settings settings;
	private Language language;
	private Stage ownerStage;

	private SupportPlannerView application;

	private ObservableList<Member> membersList;
	private ObservableList<Family> familiesList;

	private HashMap<String, String> configs;

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
		this.memberAgeTableColumn.setCellValueFactory(cellData -> {

			String spInf52 = cellData.getValue().getSpInf52Decrypted();
			if (!spInf52.isEmpty())
				return new SimpleObjectProperty<BigDecimal>(CommonUtils.calculateAge(LocalDate.parse(spInf52)));

			return null;
		});
		this.memberNumberTableColumn.setCellValueFactory(cellData -> cellData.getValue().spInf40DecryptedProperty());
		this.memberMailTableColumn.setCellValueFactory(cellData -> cellData.getValue().spInf41DecryptedProperty());
		this.memberAgeBaptismTableColumn.setCellValueFactory(cellData -> {

			String spInf53 = cellData.getValue().getSpInf53Decrypted();
			if (!spInf53.isEmpty())
				return new SimpleObjectProperty<BigDecimal>(CommonUtils.calculateAge(LocalDate.parse(spInf53)));

			return null;
		});
		this.memberMonitorTableColumn.setCellValueFactory(cellData -> {

			if (!cellData.getValue().getSpInf47().isEmpty())
				return new SimpleObjectProperty<ImageView>(
						Meta.Resources.imageForButtonSmall(Meta.Resources.USER_MENU_MONITOR));

			return null;
		});

		familyIDTableColumn.setCellValueFactory(cellData -> cellData.getValue().spFamIDProperty().asObject());

		this.familyMapsTableColumn.setCellValueFactory(cellData -> {

			if (!cellData.getValue().getSpInf9Decrypted().isEmpty())
				return new SimpleObjectProperty<ImageView>(Meta.Resources.imageForButtonSmall(Meta.Resources.MAPS));

			return null;
		});

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

		territoryTab.getStyleClass().add("tab_001");
		publisherTab.getStyleClass().add("tab_001");

		this.memberTabPane.getStyleClass().add("tab_pane_001");
		this.memberListTab.getStyleClass().add("tab_001");

		this.territoryTabPane.getStyleClass().add("tab_pane_001");
		this.familyListTab.getStyleClass().add("tab_001");

		this.memberIDTableColumn.getStyleClass().add("table_column_002");
		this.memberIconTableColumn.getStyleClass().add("table_column_002");
		this.memberMonitorTableColumn.getStyleClass().add("table_column_002");

		membersTableView.getStyleClass().add("table_view_001");
		familiesTableView.getStyleClass().add("table_view_001");

		memberAddButton.getStyleClass().add("button_image_001");
		memberDeleteButton.getStyleClass().add("button_image_001");
		membersUpdateButton.getStyleClass().add("button_image_001");

		this.familyMapsTableColumn.getStyleClass().add("table_column_002");

		this.territoryAddButton.getStyleClass().add("button_image_001");
		this.territoryRemoveButton.getStyleClass().add("button_image_001");
		this.territoryEditButton.getStyleClass().add("button_image_001");

		this.familiesMapsButton.getStyleClass().add("button_image_001");

		memberMonitorPrintButton.getStyleClass().add("button_image_001");

		this.filterMemberTextField.getStyleClass().add("text_field_001");
		this.totalTextField.getStyleClass().add("text_field_002");
		this.totalMaleTextField.getStyleClass().add("text_field_002");
		this.totalFemaleTextField.getStyleClass().add("text_field_002");

		this.filterFamilyTextField.getStyleClass().add("text_field_001");
		this.totalFamilyTextField.getStyleClass().add("text_field_002");
		this.totalFilteredFamilyTextField.getStyleClass().add("text_field_002");
	}

	public void objectInitialize() {
		listeners();
		viewUpdate();
		initInfo();
		updateMembers();
		updateFamilies();

		// Carica programmazione e luoghi

//		String waitMessage = this.language.getString("congregation.wait.load");
//
//		TaskManager.run(this.application.getAlertBuilder2(), this.ownerStage, waitMessage,
//				new CongrInitDataLoadTask(this.application.getAlertBuilder2(), this.settings, this.ownerStage, this));
	}

	private void viewUpdate() {

		this.language = settings.getLanguage();

		congrHeaderLabel.setText(language.getString("sp.menu.territory"));

		congrImageView.setFitWidth(50);
		congrImageView.setFitHeight(50);
		congrImageView.setImage(Meta.Resources.getImageLogo(Meta.Resources.TERRITORY, 50, 50));

		this.congrTabPane.setTabClosingPolicy(TabClosingPolicy.SELECTED_TAB);
		this.congrTabPane.setTabMinHeight(75);
		this.congrTabPane.setTabMaxHeight(75);

		Tooltip territoryTabTooltip = new Tooltip(language.getString("territory.tab.territory"));
		territoryTabTooltip.getStyleClass().add("tooltip_001");
		this.territoryTab.setTooltip(territoryTabTooltip);
		this.territoryTab.setText("");
		this.territoryTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.TERRITORY));
		this.territoryTab.setClosable(false);

		Tooltip publisherTabTooltip = new Tooltip(language.getString("territory.tab.publisher"));
		publisherTabTooltip.getStyleClass().add("tooltip_001");
		this.publisherTab.setTooltip(publisherTabTooltip);
		this.publisherTab.setText("");
		this.publisherTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.MEMBER));
		this.publisherTab.setClosable(false);

		this.memberListTab.setText(this.language.getString("congregation.members.list"));

		this.familyListTab.setText(this.language.getString("congregation.family.list"));

		memberIDTableColumn.setText(language.getString("TEXT0005"));
		memberIDTableColumn.setMinWidth(50);
		memberIDTableColumn.setMaxWidth(50);
		memberIDTableColumn.setResizable(false);

		this.memberIconTableColumn.setText("");
		this.memberIconTableColumn.setMinWidth(50);
		this.memberIconTableColumn.setMaxWidth(50);
		this.memberIconTableColumn.setResizable(false);

		this.memberMonitorTableColumn.setText("");
		this.memberMonitorTableColumn.setMinWidth(50);
		this.memberMonitorTableColumn.setMaxWidth(50);
		this.memberMonitorTableColumn.setResizable(false);

		this.memberSurnameTableColumn.setText(this.language.getString("TEXT0013"));
		this.memberSurname2TableColumn.setText(this.language.getString("congregation.members.column.surname2"));
		this.memberNameTableColumn.setText(this.language.getString("TEXT0014"));

		this.memberNumberTableColumn.setText(this.language.getString("congregation.members.column.number"));
		this.memberMailTableColumn.setText(this.language.getString("congregation.members.column.mail"));

		this.memberName2TableColumn.setText("");
		this.memberName2TableColumn.setMinWidth(75);
		this.memberName2TableColumn.setMaxWidth(75);
		this.memberName2TableColumn.setResizable(false);

		this.memberAgeTableColumn.setText(this.language.getString("congregation.members.column.age"));
		this.memberAgeTableColumn.setMinWidth(50);
		this.memberAgeTableColumn.setMaxWidth(50);
		this.memberAgeTableColumn.setResizable(false);

		this.memberAgeBaptismTableColumn.setText(this.language.getString("congregation.members.column.agebaptism"));
		this.memberAgeBaptismTableColumn.setMinWidth(100);
		this.memberAgeBaptismTableColumn.setMaxWidth(100);
//		this.memberAgeBaptismTableColumn.setResizable(false);

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

		this.familyMapsTableColumn.setText("");
		this.familyMapsTableColumn.setMinWidth(50);
		this.familyMapsTableColumn.setMaxWidth(50);
		this.familyMapsTableColumn.setResizable(false);

		familyNameTableColumn.setText(language.getString("TEXT0025"));
		familyCountTableColumn.setText(language.getString("TEXT0026"));
		familyStreetTableColumn.setText(language.getString("TEXT0027"));
		familyNummerTableColumn.setText(language.getString("TEXT0028"));
		familyPostCodeTableColumn.setText(language.getString("TEXT0029"));
		familyCityTableColumn.setText(language.getString("TEXT0030"));

		Tooltip territoryAddTooltip = new Tooltip(this.language.getString("territory.tooltip.add"));
		territoryAddTooltip.getStyleClass().add("tooltip_001");
		this.territoryAddButton.setTooltip(territoryAddTooltip);
		this.territoryAddButton.setText("");
		this.territoryAddButton.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.TERRITORY_ADD));

		Tooltip territoryRemoveTooltip = new Tooltip(this.language.getString("territory.tooltip.remove"));
		territoryRemoveTooltip.getStyleClass().add("tooltip_001");
		this.territoryRemoveButton.setTooltip(territoryRemoveTooltip);
		this.territoryRemoveButton.setText("");
		this.territoryRemoveButton.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.TERRITORY_REMOVE));

		Tooltip territoryEditTooltip = new Tooltip(this.language.getString("territory.tooltip.edit"));
		territoryEditTooltip.getStyleClass().add("tooltip_001");
		this.territoryEditButton.setTooltip(territoryEditTooltip);
		this.territoryEditButton.setText("");
		this.territoryEditButton.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.TERRITORY_EDIT));

		Tooltip familyMapsTooltip = new Tooltip(this.language.getString("congregation.family.tooltip.maps"));
		familyMapsTooltip.getStyleClass().add("tooltip_001");
		this.familiesMapsButton.setTooltip(familyMapsTooltip);
		this.familiesMapsButton.setText("");
		this.familiesMapsButton.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.MAPS));

		this.totalImageView.setFitWidth(25);
		this.totalImageView.setFitHeight(25);
		this.totalImageView.setImage(Meta.Resources.getImageFromResources(Meta.Resources.USER_MENU_CONGR, 25, 25));
		this.totalTextField.setMinWidth(50);
		this.totalTextField.setMaxWidth(50);
		this.totalTextField.setEditable(false);

		this.totalMaleImageView.setFitWidth(25);
		this.totalMaleImageView.setFitHeight(25);
		this.totalMaleImageView.setImage(Meta.Resources.getImageFromResources(Meta.Resources.MALE, 25, 25));
		this.totalMaleTextField.setMinWidth(50);
		this.totalMaleTextField.setMaxWidth(50);
		this.totalMaleTextField.setEditable(false);

		this.totalFemaleImageView.setFitWidth(25);
		this.totalFemaleImageView.setFitHeight(25);
		this.totalFemaleImageView.setImage(Meta.Resources.getImageFromResources(Meta.Resources.FEMALE, 25, 25));
		this.totalFemaleTextField.setMinWidth(50);
		this.totalFemaleTextField.setMaxWidth(50);
		this.totalFemaleTextField.setEditable(false);

		this.totalFamilyImageView.setFitWidth(25);
		this.totalFamilyImageView.setFitHeight(25);
		this.totalFamilyImageView.setImage(Meta.Resources.getImageFromResources(Meta.Resources.FAMILY, 25, 25));
		this.totalFamilyTextField.setMinWidth(50);
		this.totalFamilyTextField.setMaxWidth(50);
		this.totalFamilyTextField.setEditable(false);

		this.totalFilteredFamilyImageView.setFitWidth(25);
		this.totalFilteredFamilyImageView.setFitHeight(25);
		this.totalFilteredFamilyImageView.setImage(Meta.Resources.getImageFromResources(Meta.Resources.FILTER, 25, 25));
		this.totalFilteredFamilyTextField.setMinWidth(50);
		this.totalFilteredFamilyTextField.setMaxWidth(50);
		this.totalFilteredFamilyTextField.setEditable(false);
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

		this.filterMemberTextField.textProperty()
				.addListener((observable, oldValue, newValue) -> updateFilterMember(newValue));

		this.filterFamilyTextField.textProperty()
				.addListener((observable, oldValue, newValue) -> updateFilterFamily(newValue));

		this.familiesMapsButton.setOnAction(event -> viewMaps());
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

	private void updateFilterFamily(String newValue) {

		if (newValue.isEmpty()) {
			this.familiesTableView.setItems(this.familiesList);
			// FILTRO
			this.totalFilteredFamilyTextField.setText(String.valueOf(this.familiesList.size()));
		} else {
			ObservableList<Family> filteredMemberList = buildListFamily(newValue);
			this.familiesTableView.setItems(filteredMemberList);
			// FILTRO
			this.totalFilteredFamilyTextField.setText(String.valueOf(filteredMemberList.size()));
		}

		this.familiesTableView.refresh();
	}

	private ObservableList<Member> buildListMember(String filter) {

		ObservableList<Member> list = FXCollections.observableArrayList();

		StreamSupport.stream(this.membersList.spliterator(), false).filter(obj -> matchFilterMember(obj, filter))
				.forEach(obj -> list.add(obj));

		return list;
	}

	private ObservableList<Family> buildListFamily(String filter) {

		ObservableList<Family> list = FXCollections.observableArrayList();

		StreamSupport.stream(this.familiesList.spliterator(), false).filter(obj -> matchFilterFamily(obj, filter))
				.forEach(obj -> list.add(obj));

		return list;
	}

	private boolean matchFilterMember(Member obj, String match) {

		String filter = match.toLowerCase();

		return obj.getSpInf1Decrypted().toLowerCase().contains(filter)
				|| obj.getSpInf2Decrypted().toLowerCase().contains(filter)
				|| obj.getSpInf39Decrypted().toLowerCase().contains(filter);
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
		territoryAddButton.setOnAction(event -> newTerritory());
	}

	private void listenerMemberDeleteButton() {
		memberDeleteButton.setOnAction(event -> deleteMember());
	}

	private void listenerFamilyDeleteButton() {
		territoryRemoveButton.setOnAction(event -> deleteFamily());
	}

	private void listenerMembersUpdateButton() {
		membersUpdateButton.setOnAction(event -> updateMembers());
	}

	private void listenerFamilyUpdateButton() {
		territoryEditButton.setOnAction(event -> updateFamilies());
	}

	private void deleteMember() {

		if (membersTableView.getSelectionModel().getSelectedIndex() > -1) {

			Member member = membersTableView.getSelectionModel().getSelectedItem();

//			Alert alert = new AlertDesigner(language.getString("TEXT0024"), member.getNameStyle1(), ownerStage,
//					AlertType.CONFIRMATION, Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
//					Meta.Themes.SUPPORTPLANNER_THEME, "alert_001");
//			if (alert.showAndWait().get() == ButtonType.OK)
//				Actions.deleteMember(String.valueOf(member.getSpMemberID()), settings, ownerStage, this);
		} else {

			this.application.getAlertBuilder2().error(this.ownerStage,
					this.language.getString("congregation.members.error.delete"));

		}

	}

	private void deleteFamily() {

		if (familiesTableView.getSelectionModel().getSelectedIndex() > -1) {

//			Family family = familiesTableView.getSelectionModel().getSelectedItem();
//
//			Alert alert = new AlertDesigner(language.getString("TEXT0034"), family.getSpInf1Decrypted(), ownerStage,
//					AlertType.CONFIRMATION, Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
//					Meta.Themes.SUPPORTPLANNER_THEME, "alert_001");
//			if (alert.showAndWait().get() == ButtonType.OK)
//				Actions.deleteFamily(String.valueOf(family.getSpFamID()), settings, ownerStage, this);

		} else {

			this.application.getAlertBuilder2().error(this.ownerStage,
					this.language.getString("congregation.family.error.delete"));

		}

	}

	private void newMember() {

		// if (!isAlreadyOpen(language.getString("TEXT0015"))) {
		if (!isAlreadyOpen(this.memberTabPane, "")) {

			try {

				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(Meta.Views.HOME_USER_MENU_CONGR_MEMBER_EDITOR);
				AnchorPane layout = (AnchorPane) fxmlLoader.load();

				TerritoryEditor ctrl = (TerritoryEditor) fxmlLoader.getController();
				ctrl.setSettings(this.settings);
				ctrl.setApplication(this.application);
				ctrl.setOwnerStage(ownerStage);
				ctrl.setOwnerCtrl(this);

				// Tab newMemberTab = new Tab(language.getString("TEXT0015"), layout);
				Tab newMemberTab = new Tab("", layout);
				newMemberTab.setClosable(true);
				newMemberTab.getStyleClass().add("tab_001");
				newMemberTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.PLUS));

				ctrl.setParentTabPane(this.memberTabPane);
				ctrl.setMembersTab(territoryTab);
				ctrl.setNewMemberTab(newMemberTab);
				ctrl.setConfigs(this.configs);

//				congrTabPane.getTabs().add(newMemberTab);
//				congrTabPane.getSelectionModel().select(newMemberTab);
				this.memberTabPane.getTabs().add(newMemberTab);
				this.memberTabPane.getSelectionModel().select(newMemberTab);

				ctrl.objectInitialize();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void newTerritory() {

		if (!isAlreadyOpen(this.territoryTabPane, "")) {

			try {

				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(Meta.Views.TERRITORY_EDITOR_FXML_URL);
				AnchorPane layout = (AnchorPane) fxmlLoader.load();

				TerritoryEditor ctrl = (TerritoryEditor) fxmlLoader.getController();
				ctrl.setSettings(this.settings);
				ctrl.setOwnerStage(ownerStage);
				ctrl.setOwnerCtrl(this);

				Tab newTab = new Tab("", layout);
				newTab.setClosable(true);
				newTab.getStyleClass().add("tab_001");
				newTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.PLUS));

				ctrl.setParentTabPane(this.territoryTabPane);
//				ctrl.setMembersTab(membersTab);
				ctrl.setNewMemberTab(newTab);
//				ctrl.setMembersList(this.membersList);

				this.territoryTabPane.getTabs().add(newTab);
				this.territoryTabPane.getSelectionModel().select(newTab);

				ctrl.objectInitialize();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void editMember(Member member) {

		if (!isAlreadyOpen(this.memberTabPane, member.getNameStyle1())) {

			try {

				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(Meta.Views.HOME_USER_MENU_CONGR_MEMBER_EDITOR);
				AnchorPane layout = (AnchorPane) fxmlLoader.load();

				TerritoryEditor ctrl = (TerritoryEditor) fxmlLoader.getController();
				ctrl.setSettings(this.settings);
				ctrl.setApplication(this.application);
				ctrl.setOwnerStage(ownerStage);
				ctrl.setOwnerCtrl(this);
				//ctrl.setSelectedMember(member);
				ctrl.setConfigs(this.configs);
				ctrl.objectInitialize();

				Tab newMemberTab = new Tab(member.getNameStyle1(), layout);
				newMemberTab.setClosable(true);
				newMemberTab.getStyleClass().add("tab_001");

				if (member.getSpInf4() == 0)
					newMemberTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.MALE));
				else if (member.getSpInf4() == 1)
					newMemberTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.FEMALE));

				// ctrl.setCongrTabPane(congrTabPane);
				// ctrl.setMembersTab(membersTab);
				ctrl.setParentTabPane(this.memberTabPane);
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

		if (!isAlreadyOpen(this.territoryTabPane, family.getSpInf1Decrypted())) {

//			try {
//
//				FXMLLoader fxmlLoader = new FXMLLoader();
//				fxmlLoader.setLocation(Meta.Views.HOME_USER_MENU_CONGR_FAMILY_EDITOR);
//				AnchorPane layout = (AnchorPane) fxmlLoader.load();
//
//				UserMenuCongrFamilyEditor ctrl = (UserMenuCongrFamilyEditor) fxmlLoader.getController();
//				ctrl.setSettings(this.settings);
//				ctrl.setOwnerStage(ownerStage);
//				ctrl.setOwnerCtrl(this);
//				ctrl.setSelectedFamily(family);
//
//				Tab newFamilyTab = new Tab(family.getSpInf1Decrypted(), layout);
//				newFamilyTab.setClosable(true);
//				newFamilyTab.getStyleClass().add("tab_001");
//				newFamilyTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.FAMILY));
//
//				ctrl.setParentTabPane(this.familyTabPane);
//				ctrl.setMembersTab(membersTab);
//				ctrl.setNewMemberTab(newFamilyTab);
//				ctrl.setMembersList(this.membersList);
//
//				this.familyTabPane.getTabs().add(newFamilyTab);
//				this.familyTabPane.getSelectionModel().select(newFamilyTab);
//
//				ctrl.objectInitialize();
//
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
		}

	}

	private boolean isAlreadyOpen(TabPane tabPane, String label) {

		for (Tab tab : tabPane.getTabs())
			if (tab.getText().equals(label)) {
				tabPane.getSelectionModel().select(tab);
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
		this.membersList.sort((a, b) -> (a.getSpInf2Decrypted().concat(a.getSpInf1Decrypted())
				.compareTo(b.getSpInf2Decrypted().concat(b.getSpInf1Decrypted()))));

		this.membersTableView.setItems(membersList);

		// RESET FILTRO
		this.filterMemberTextField.setText("");

		// TOTALE
		this.totalTextField.setText(String.valueOf(this.membersList.size()));

		// MASCHI E FEMMINE
		int male = 0;
		int female = 0;

		for (Member m : this.membersList) {
			if (m.getSpInf4() == 0)
				male++;
			else if (m.getSpInf4() == 1)
				female++;
		}

		this.totalMaleTextField.setText(String.valueOf(male));
		this.totalFemaleTextField.setText(String.valueOf(female));
	}

	@Override
	public void updateFamilies(ObservableList<Family> list) {

		this.familiesList = list;
		this.familiesList.sort((a, b) -> a.getSpInf1Decrypted().compareTo(b.getSpInf1Decrypted()));

		this.familiesTableView.setItems(familiesList);

		// RESET FILTRO
		this.filterFamilyTextField.setText("");

		// TOTALE
		this.totalFamilyTextField.setText(String.valueOf(this.familiesList.size()));

		// FILTRO
		this.totalFilteredFamilyTextField.setText(String.valueOf(this.familiesList.size()));
	}

	private void print() {

		if (this.membersTableView.getSelectionModel().getSelectedIndex() > -1) {

			Member member = membersTableView.getSelectionModel().getSelectedItem();

			EnumPrintLayouts selectedLayout = PrintLayout.dialogPrintLayout(this.ownerStage, language, null,
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

	private void viewMaps() {

		if (this.familiesTableView.getSelectionModel().getSelectedIndex() > -1) {

			Family family = this.familiesTableView.getSelectionModel().getSelectedItem();

			String link = "";

			String coord = family.getSpInf9Decrypted();
			if (!coord.isEmpty()) {

				link = this.language.getString("supportplanner.googlemaps.pattern");
				link = String.format(link, coord.replace(" ", "+"));

			} else {

				String spInf2 = family.getSpInf2Decrypted();
				String spInf3 = family.getSpInf3Decrypted();
				String spInf4 = family.getSpInf4Decrypted();
				String spInf5 = family.getSpInf5Decrypted();

				if (!spInf2.isEmpty() && !spInf3.isEmpty() && !spInf4.isEmpty() && !spInf5.isEmpty()) {

					String addr = String.format("%s %s, %s %s", spInf2, spInf3, spInf4, spInf5);
					addr = addr.replace(" ", "+");

					link = this.language.getString("supportplanner.googlemaps.pattern");
					link = String.format(link, addr);
				}

			}

			if (!link.isEmpty()) {

				Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
				if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
					try {
						desktop.browse(new URI(link));
					} catch (Exception e) {
						this.application.getAlertBuilder().error(this.ownerStage, e.getMessage()).show();
					}
				}
			} else {

				this.application.getAlertBuilder2().error(this.ownerStage,
						this.language.getString("congregation.family.error.coord"));
			}
		} else {
			this.application.getAlertBuilder2().error(this.ownerStage,
					this.language.getString("congregation.family.error.select"));
		}
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

	public HashMap<String, String> getConfigs() {
		return configs;
	}

	public void setConfigs(HashMap<String, String> configs) {
		this.configs = configs;
	}
}
