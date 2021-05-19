package com.sm.net.sp.view.home.user.menu.territory;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.StreamSupport;

import org.apache.commons.io.FileUtils;

import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.actions.Actions;
import com.sm.net.sp.dialogs.territory.TerritoryDialog;
import com.sm.net.sp.model.EnumPrintLayouts;
import com.sm.net.sp.model.Member;
import com.sm.net.sp.model.TerritoryObj;
import com.sm.net.sp.model.TerritoryResource;
import com.sm.net.sp.settings.Settings;
import com.sm.net.sp.utils.CommonUtils;
import com.sm.net.sp.view.SupportPlannerView;
import com.sm.net.sp.view.home.user.menu.territory.task.TerritoryDeleteTask;
import com.sm.net.sp.view.home.user.menu.territory.task.TerritoryDownloadAllTask;
import com.sm.net.sp.view.home.user.menu.territory.task.TerritoryDownloadTask;
import com.sm.net.sp.view.home.user.menu.territory.task.TerritoryLoadTask;
import com.sm.net.sp.view.printlayout.PrintLayout;
import com.smnet.core.task.TaskManager;

import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Territory {

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
	private TabPane territoryTabPane;

	@FXML
	private Button territoryAddButton;
	@FXML
	private Button territoryEditButton;
	@FXML
	private Button territoryRemoveButton;
	@FXML
	private Button territoryOpenViewerButton;
	@FXML
	private Button territoryOpenViewerURLButton;
	@FXML
	private Button territoryResourcesDownloadButton;
	@FXML
	private Button territoryResourcesDownloadAllButton;

	@FXML
	private TextField territoryFilterTextField;

	@FXML
	private TableView<TerritoryObj> territoryTableView;
	@FXML
	private TableColumn<TerritoryObj, BigDecimal> territoryNumberTableColumn;
	@FXML
	private TableColumn<TerritoryObj, String> territoryNameTableColumn;
	@FXML
	private TableColumn<TerritoryObj, String> territoryAssignedToTableColumn;

	@FXML
	private Button territoryResourcesOpenDirectoryButton;
	@FXML
	private Button territoryResourcesDeleteAllButton;

	@FXML
	private Label territoryDocsLabel;
	@FXML
	private TableView<File> territoryDocsTableView;
	@FXML
	private TableColumn<File, String> territoryDocsNameTableColumn;

	@FXML
	private Label territoryImagesLabel;
	@FXML
	private TableView<File> territoryImagesTableView;
	@FXML
	private TableColumn<File, String> territoryImagesNameTableColumn;

	@FXML
	private VBox territoryTablesVBox;
	@FXML
	private StackPane territoryImageViewStackPane;
	@FXML
	private ImageView territoryImageView;

	@FXML
	private TabPane memberTabPane;
	@FXML
	private Tab memberListTab;

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

	private Settings settings;
	private Language language;
	private Stage ownerStage;

	private SupportPlannerView application;

	private ObservableList<TerritoryObj> territoryList;
	private ObservableList<File> territoryDocs;
	private ObservableList<File> territoryImages;

	private ObservableList<Member> membersList;

	private HashMap<String, String> configs;

	@FXML
	private void initialize() {
		styleClasses();
		cellValueFactory();
	}

	private void cellValueFactory() {

		this.territoryNumberTableColumn.setCellValueFactory(
				cellData -> new SimpleObjectProperty<BigDecimal>(new BigDecimal(cellData.getValue().getSpInf7())));
		this.territoryNameTableColumn.setCellValueFactory(cellData -> cellData.getValue().spInf8Property());

		this.territoryDocsNameTableColumn
				.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
		this.territoryImagesNameTableColumn
				.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));

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

//		familyIDTableColumn.setCellValueFactory(cellData -> cellData.getValue().spFamIDProperty().asObject());
//
//		this.familyMapsTableColumn.setCellValueFactory(cellData -> {
//
//			if (!cellData.getValue().getSpInf9Decrypted().isEmpty())
//				return new SimpleObjectProperty<ImageView>(Meta.Resources.imageForButtonSmall(Meta.Resources.MAPS));
//
//			return null;
//		});
//
//		familyNameTableColumn.setCellValueFactory(cellData -> cellData.getValue().spInf1DecryptedProperty());
//		familyCountTableColumn.setCellValueFactory(cellData -> cellData.getValue().spFamMembersProperty().asObject());
//		familyStreetTableColumn.setCellValueFactory(cellData -> cellData.getValue().spInf2DecryptedProperty());
//		familyNummerTableColumn.setCellValueFactory(cellData -> cellData.getValue().spInf3DecryptedProperty());
//		familyPostCodeTableColumn.setCellValueFactory(cellData -> cellData.getValue().spInf4DecryptedProperty());
//		familyCityTableColumn.setCellValueFactory(cellData -> cellData.getValue().spInf5DecryptedProperty());
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
		territoryTableView.getStyleClass().add("table_view_001");

		memberAddButton.getStyleClass().add("button_image_001");
		memberDeleteButton.getStyleClass().add("button_image_001");
		membersUpdateButton.getStyleClass().add("button_image_001");

		this.territoryNumberTableColumn.getStyleClass().add("table_column_002");

		this.territoryAddButton.getStyleClass().add("button_image_001");
		this.territoryOpenViewerButton.getStyleClass().add("button_image_001");
		this.territoryOpenViewerURLButton.getStyleClass().add("button_image_001");
		this.territoryRemoveButton.getStyleClass().add("button_image_001");
		this.territoryEditButton.getStyleClass().add("button_image_001");

		this.territoryResourcesDownloadButton.getStyleClass().add("button_image_001");
		this.territoryResourcesDownloadAllButton.getStyleClass().add("button_image_001");

		this.territoryResourcesOpenDirectoryButton.getStyleClass().add("button_image_001");
		this.territoryResourcesDeleteAllButton.getStyleClass().add("button_image_001");

		this.territoryDocsLabel.getStyleClass().add("label_001");
		this.territoryImagesLabel.getStyleClass().add("label_001");

		this.territoryDocsTableView.getStyleClass().add("table_view_001");
		this.territoryImagesTableView.getStyleClass().add("table_view_001");

		memberMonitorPrintButton.getStyleClass().add("button_image_001");

		this.filterMemberTextField.getStyleClass().add("text_field_001");
		this.totalTextField.getStyleClass().add("text_field_002");
		this.totalMaleTextField.getStyleClass().add("text_field_002");
		this.totalFemaleTextField.getStyleClass().add("text_field_002");

		this.territoryFilterTextField.getStyleClass().add("text_field_001");
	}

	public void objectInitialize() {

		this.territoryDocs = FXCollections.observableArrayList();
		this.territoryImages = FXCollections.observableArrayList();

		this.territoryDocsTableView.setItems(this.territoryDocs);
		this.territoryImagesTableView.setItems(this.territoryImages);

		this.territoryTablesVBox.setMinWidth(600);
		this.territoryTablesVBox.setMaxWidth(600);
		
		listeners();
		viewUpdate();
		initInfo();

		// Carica programmazione e luoghi

//		String waitMessage = this.language.getString("congregation.wait.load");
//
//		TaskManager.run(this.application.getAlertBuilder2(), this.ownerStage, waitMessage,
//				new CongrInitDataLoadTask(this.application.getAlertBuilder2(), this.settings, this.ownerStage, this));
	}

	public void updateTerritoryList(ObservableList<TerritoryObj> list) {
		this.territoryList.clear();
		this.territoryList.addAll(list);
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

		this.territoryNumberTableColumn.setText(this.language.getString("territory.tablecolumns.territorynumber"));
		this.territoryNumberTableColumn.setMinWidth(100);
		this.territoryNumberTableColumn.setMaxWidth(100);
		this.territoryNumberTableColumn.setResizable(false);
		this.territoryNameTableColumn.setText(this.language.getString("territory.tablecolumns.territoryname"));
		this.territoryAssignedToTableColumn
				.setText(this.language.getString("territory.tablecolumns.territoryassignedto"));

		Tooltip territoryAddTooltip = new Tooltip(this.language.getString("territory.tooltip.add"));
		territoryAddTooltip.getStyleClass().add("tooltip_001");
		this.territoryAddButton.setTooltip(territoryAddTooltip);
		this.territoryAddButton.setText("");
		this.territoryAddButton.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.TERRITORY_ADD));

		Tooltip territoryViewerTooltip = new Tooltip(this.language.getString("territory.tooltip.viewer"));
		territoryViewerTooltip.getStyleClass().add("tooltip_001");
		this.territoryOpenViewerButton.setTooltip(territoryViewerTooltip);
		this.territoryOpenViewerButton.setText("");
		this.territoryOpenViewerButton.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.TERRITORYVIEWER));

		Tooltip territoryViewerURLTooltip = new Tooltip(this.language.getString("territory.tooltip.viewerurl"));
		territoryViewerURLTooltip.getStyleClass().add("tooltip_001");
		this.territoryOpenViewerURLButton.setTooltip(territoryViewerURLTooltip);
		this.territoryOpenViewerURLButton.setText("");
		this.territoryOpenViewerURLButton
				.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.TERRITORYVIEWERURL));

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

		Tooltip territoryDownloadTooltip = new Tooltip(this.language.getString("territory.tooltip.downloadresources"));
		territoryDownloadTooltip.getStyleClass().add("tooltip_001");
		this.territoryResourcesDownloadButton.setTooltip(territoryDownloadTooltip);
		this.territoryResourcesDownloadButton.setText("");
		this.territoryResourcesDownloadButton.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.DOWNLOAD));

		Tooltip territoryDownloadAllTooltip = new Tooltip(
				this.language.getString("territory.tooltip.downloadallresources"));
		territoryDownloadAllTooltip.getStyleClass().add("tooltip_001");
		this.territoryResourcesDownloadAllButton.setTooltip(territoryDownloadAllTooltip);
		this.territoryResourcesDownloadAllButton.setText("");
		this.territoryResourcesDownloadAllButton
				.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.DOWNLOAD2));

		Tooltip territoryResourcesOpenDirectoryTooltip = new Tooltip(
				this.language.getString("territory.tooltip.resourcesopendirectory"));
		territoryResourcesOpenDirectoryTooltip.getStyleClass().add("tooltip_001");
		this.territoryResourcesOpenDirectoryButton.setTooltip(territoryResourcesOpenDirectoryTooltip);
		this.territoryResourcesOpenDirectoryButton.setText("");
		this.territoryResourcesOpenDirectoryButton
				.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.FOLDER));

		Tooltip territoryResourcesDeleteAllTooltip = new Tooltip(
				this.language.getString("territory.tooltip.resourcesdeleteall"));
		territoryResourcesDeleteAllTooltip.getStyleClass().add("tooltip_001");
		this.territoryResourcesDeleteAllButton.setTooltip(territoryResourcesDeleteAllTooltip);
		this.territoryResourcesDeleteAllButton.setText("");
		this.territoryResourcesDeleteAllButton.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.DELETE));

		this.territoryDocsLabel.setText(this.language.getString("territory.label.resourcedocs"));
		this.territoryImagesLabel.setText(this.language.getString("territory.label.resourceimages"));

		this.territoryDocsNameTableColumn.setText(this.language.getString("territory.tablecolumn.resourcedocfilename"));
		this.territoryImagesNameTableColumn
				.setText(this.language.getString("territory.tablecolumn.resourceimagefilename"));

		this.territoryDocsTableView.setMaxHeight(250);
		this.territoryImagesTableView.setMaxHeight(250);

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
	}

	private void initInfo() {

		this.territoryList = FXCollections.observableArrayList();
		this.territoryTableView.setItems(this.territoryList);

		updateTerritory();
	}

	public void updateTerritory() {

		String waitMessage = this.language.getString("territory.wait.load");

		TaskManager.run(this.application.getAlertBuilder2(), this.ownerStage, waitMessage,
				new TerritoryLoadTask(this.application.getAlertBuilder2(), this.settings, this.ownerStage, this));
	}

	private void listeners() {

		// TODO: Fix all listeners
		this.territoryAddButton.setOnAction(event -> newTerritory());
		this.territoryEditButton.setOnAction(event -> editTerritory());
		this.territoryRemoveButton.setOnAction(event -> removeTerritory());
		this.territoryOpenViewerButton.setOnAction(event -> openTerritoryViewer());
		this.territoryOpenViewerURLButton.setOnAction(event -> openTerritoryViewerURL());
		this.territoryResourcesDownloadButton.setOnAction(event -> downloadTerritoryResources());
		this.territoryResourcesDownloadAllButton.setOnAction(event -> downloadAllTerritoryResources());
		this.territoryResourcesOpenDirectoryButton.setOnAction(event -> openResourceDirectory());
		this.territoryResourcesDeleteAllButton.setOnAction(event -> deleteResourceDirectory());

		this.territoryTableView.getSelectionModel().selectedIndexProperty()
				.addListener((obs, oldV, newV) -> selectTerritory());

		this.territoryImagesTableView.getSelectionModel().selectedIndexProperty()
				.addListener((obs, oldV, newV) -> selectTerritoryImage());

		this.territoryTableView.setRowFactory(param -> {
			TableRow<TerritoryObj> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (!row.isEmpty())) {
//					editTerritory(row.getItem());
					// TODO: Registro
				}
			});
			return row;
		});

		this.territoryFilterTextField.textProperty()
				.addListener((observable, oldValue, newValue) -> updateFilterTerritory(newValue));

//		memberAddButton.setOnAction(event2 -> newMember());
//		memberDeleteButton.setOnAction(event3 -> deleteMember());
//		membersTableView.setRowFactory(param1 -> {
//			TableRow<Member> row = new TableRow<>();
//			row.setOnMouseClicked(event4 -> {
//				if (event4.getClickCount() == 2 && (!row.isEmpty()))
//					editMember(row.getItem());
//			});
//			return row;
//		});
//
//		this.memberMonitorPrintButton.setOnAction(event4 -> print());
//
//		this.filterMemberTextField.textProperty()
//				.addListener((observable, oldValue, newValue) -> updateFilterMember(newValue));
//

//		this.familiesMapsButton.setOnAction(event -> viewMaps());
	}

	private void selectTerritoryImage() {

		if (this.territoryImagesTableView.getSelectionModel().getSelectedIndex() > -1) {

			File item = this.territoryImagesTableView.getSelectionModel().getSelectedItem();
			if (item.exists()) {
				clearImage();
				fillImage(item);
			}
		}
	}

	private void fillImage(File fileImage) {

		// TODO: check image

		if (fileImage.exists()) {

			double stackPaneWidth = this.territoryImageViewStackPane.getWidth() - 5;
			double stackPaneHeight = this.territoryImageViewStackPane.getHeight() - 5;

			Image image = new Image(fileImage.toURI().toString());
			this.territoryImageView.setImage(image);
			this.territoryImageView.setPreserveRatio(true);

			double imageWidth = image.getWidth();
			double imageHeight = image.getHeight();

			double newWidth = setNewWidthSize(imageWidth, imageHeight, stackPaneWidth, stackPaneHeight);
			double newHeight = setNewHeightSize(imageWidth, imageHeight, stackPaneWidth, stackPaneHeight);

			if (newWidth > stackPaneWidth || newHeight > stackPaneHeight) {
				newWidth = reverseWidthSize(newWidth, newHeight, stackPaneWidth, stackPaneHeight);
				newHeight = reverseHeightSize(newWidth, newHeight, stackPaneWidth, stackPaneHeight);
			}

			this.territoryImageView.setFitWidth(newWidth);
			this.territoryImageView.setFitHeight(newHeight);

		}
	}

	private double setNewWidthSize(double width, double height, double screenWidth, double screenHeight) {

		if (width > height) {
			return screenWidth;
		} else {
			return imageRatioSize(screenHeight, height, width);
		}
	}

	private double setNewHeightSize(double width, double height, double screenWidth, double screenHeight) {

		if (width > height) {
			return imageRatioSize(screenWidth, width, height);
		} else {
			return screenHeight;
		}
	}

	private double imageRatioSize(double screenSize, double originalSize, double otherSide) {

		double ratio = screenSize * 100 / originalSize;
		return (otherSide * ratio / 100);
	}

	private double reverseWidthSize(double newWidth, double newHeight, double stackPaneWidth, double stackPaneHeight) {

		if (newWidth > newHeight) {
			return imageRatioSize(stackPaneHeight, newHeight, newWidth);
		} else {
			return stackPaneWidth;
		}
	}

	private double reverseHeightSize(double newWidth, double newHeight, double stackPaneWidth, double stackPaneHeight) {

		if (newWidth > newHeight) {
			return stackPaneHeight;
		} else {
			return imageRatioSize(stackPaneWidth, newWidth, newHeight);
		}
	}

	private void clearImage() {
		this.territoryImageView.setImage(null);
	}

	private void deleteResourceDirectory() {

		if (this.territoryTableView.getSelectionModel().getSelectedIndex() > -1) {

			String content = this.application.getSettings().getLanguage()
					.getString("territory.confirm.deleteallresources");
			if (this.application.getAlertBuilder2().confirm(this.ownerStage, content)) {

				TerritoryObj territoryObj = this.territoryTableView.getSelectionModel().getSelectedItem();
				File targetDirectory = territoryObj.buildTargetDirectory();

				try {
					FileUtils.deleteDirectory(targetDirectory);
				} catch (IOException e) {
					this.application.getAlertBuilder2().error(this.ownerStage, e.getMessage());
				}

				selectTerritory();
			}

		} else {
			this.application.getAlertBuilder2().error(this.ownerStage,
					this.language.getString("territory.error.noterritoryselected"));
		}
	}

	public void selectTerritory() {

		if (this.territoryTableView.getSelectionModel().getSelectedIndex() > -1) {

			this.territoryDocs.clear();
			this.territoryImages.clear();

			TerritoryObj territoryObj = this.territoryTableView.getSelectionModel().getSelectedItem();
			File targetDirectory = territoryObj.buildTargetDirectory();

			ArrayList<TerritoryResource> resources = territoryObj.getResources();
			for (TerritoryResource territoryResource : resources) {

				File resource = new File(targetDirectory, territoryResource.getResourceName());
				if (resource.exists()) {

					switch (territoryResource.getType()) {
					case 0: // Image

						this.territoryImages.add(resource);

						break;

					case 1: // Doc

						this.territoryDocs.add(resource);

						break;

					default:
						break;
					}
				}
			}

			Platform.runLater(() -> {
				this.territoryDocsTableView.refresh();
				this.territoryImagesTableView.refresh();
			});

			// TODO: check selection
			this.territoryImagesTableView.getSelectionModel().selectFirst();
			// selectFirstTerritoryImage();
		}
	}

	private void selectFirstTerritoryImage() {

		this.territoryImagesTableView.getSelectionModel().selectFirst();

//		if(this.territoryImagesTableView.getItems().size()>0) {
//		}
	}

	private void openResourceDirectory() {

		if (this.territoryTableView.getSelectionModel().getSelectedIndex() > -1) {

			TerritoryObj territoryObj = this.territoryTableView.getSelectionModel().getSelectedItem();
			File targetDirectory = territoryObj.buildTargetDirectory();
			if (targetDirectory.exists()) {

				try {
					Desktop.getDesktop().open(targetDirectory);
				} catch (IOException e) {
					this.application.getAlertBuilder2().error(this.ownerStage, e.getMessage());
				}
			}

		} else {
			this.application.getAlertBuilder2().error(this.ownerStage,
					this.language.getString("territory.error.noterritoryselected"));
		}
	}

	private void downloadTerritoryResources() {

		if (this.territoryTableView.getSelectionModel().getSelectedIndex() > -1) {

			TerritoryObj territoryObj = this.territoryTableView.getSelectionModel().getSelectedItem();

			String territoryNr = territoryObj.getSpInf7();
			String territoryName = territoryObj.getSpInf8();

			String header = this.application.getSettings().getLanguage()
					.getString("territory.confirm.downloadresources");
			String content = String.format("%s - %s", territoryNr, territoryName);

			if (this.application.getAlertBuilder2().confirm(this.ownerStage, header, content)) {

				String waitMessage = this.language.getString("territory.wait.download");

				TaskManager.run(this.application.getAlertBuilder2(), this.ownerStage, waitMessage,
						new TerritoryDownloadTask(this.application, this.ownerStage, this, territoryObj));

			}
		}
	}

	private void downloadAllTerritoryResources() {

		String content = this.application.getSettings().getLanguage()
				.getString("territory.confirm.downloadallresources");

		if (this.application.getAlertBuilder2().confirm(this.ownerStage, content)) {

			String waitMessage = this.language.getString("territory.wait.download");

			TaskManager.run(this.application.getAlertBuilder2(), this.ownerStage, waitMessage,
					new TerritoryDownloadAllTask(this.application, this.ownerStage, this.territoryList));

		}
	}

	private void openTerritoryViewer() {

		if (this.territoryTableView.getSelectionModel().getSelectedIndex() > -1) {

			TerritoryObj territoryObj = this.territoryTableView.getSelectionModel().getSelectedItem();

			String spInf10 = territoryObj.getSpInf10();
			String spInf31 = territoryObj.getSpInf31();

			if (!(spInf10.isEmpty() || spInf31.isEmpty())) {

				String dbUrl = this.application.getSettings().getDatabaseUrl();
				int indexOf = dbUrl.indexOf("exchange.php");
				if (indexOf > -1) {

					String subDbUrl = dbUrl.substring(0, indexOf);
					String link = subDbUrl + "monitor/territoryviewer.php?tid=%s&amp;lang=%s";
					link = String.format(link, spInf31, this.language.getString("sp.monitor.language"));

					Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
					if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
						try {
							desktop.browse(new URI(link));
						} catch (Exception e) {
							this.application.getAlertBuilder().error(this.ownerStage, e.getMessage()).show();
						}
					}
				}

			} else {
				this.application.getAlertBuilder2().error(this.ownerStage,
						this.language.getString("territory.error.nomymapsidorviewerid"));
			}

		} else {
			this.application.getAlertBuilder2().error(this.ownerStage,
					this.language.getString("territory.error.noterritoryselected"));
		}
	}

	private void openTerritoryViewerURL() {

		if (this.territoryTableView.getSelectionModel().getSelectedIndex() > -1) {

			TerritoryObj territoryObj = this.territoryTableView.getSelectionModel().getSelectedItem();

			String spInf10 = territoryObj.getSpInf10();
			String spInf31 = territoryObj.getSpInf31();

			if (!(spInf10.isEmpty() || spInf31.isEmpty())) {

				String dbUrl = this.application.getSettings().getDatabaseUrl();
				int indexOf = dbUrl.indexOf("exchange.php");
				if (indexOf > -1) {

					String subDbUrl = dbUrl.substring(0, indexOf);
					String link = subDbUrl + "monitor/territoryviewer.php?tid=%s&amp;lang=%s";
					link = String.format(link, spInf31, this.language.getString("sp.monitor.language"));

					TerritoryDialog.show(this.application, this.ownerStage, link);

				}

			} else {
				this.application.getAlertBuilder2().error(this.ownerStage,
						this.language.getString("territory.error.nomymapsidorviewerid"));
			}

		} else {
			this.application.getAlertBuilder2().error(this.ownerStage,
					this.language.getString("territory.error.noterritoryselected"));
		}
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

	private void updateFilterTerritory(String newValue) {

		if (newValue.isEmpty()) {
			this.territoryTableView.setItems(this.territoryList);
		} else {
			ObservableList<TerritoryObj> filteredMemberList = buildListTerritory(newValue);
			this.territoryTableView.setItems(filteredMemberList);
		}

		Platform.runLater(() -> territoryTableView.refresh());
	}

	private ObservableList<Member> buildListMember(String filter) {

		ObservableList<Member> list = FXCollections.observableArrayList();

		StreamSupport.stream(this.membersList.spliterator(), false).filter(obj -> matchFilterMember(obj, filter))
				.forEach(obj -> list.add(obj));

		return list;
	}

	private ObservableList<TerritoryObj> buildListTerritory(String filter) {

		ObservableList<TerritoryObj> list = FXCollections.observableArrayList();

		StreamSupport.stream(this.territoryList.spliterator(), false).filter(obj -> matchFilterTerritory(obj, filter))
				.forEach(obj -> list.add(obj));

		return list;
	}

	private boolean matchFilterMember(Member obj, String match) {

		String filter = match.toLowerCase();

		return obj.getSpInf1Decrypted().toLowerCase().contains(filter)
				|| obj.getSpInf2Decrypted().toLowerCase().contains(filter)
				|| obj.getSpInf39Decrypted().toLowerCase().contains(filter);
	}

	private boolean matchFilterTerritory(TerritoryObj obj, String match) {

		String filter = match.toLowerCase();

		return obj.getSpInf1().toLowerCase().contains(filter) || obj.getSpInf2().toLowerCase().contains(filter)
				|| obj.getSpInf3().toLowerCase().contains(filter) || obj.getSpInf4().toLowerCase().contains(filter)
				|| obj.getSpInf5().toLowerCase().contains(filter) || obj.getSpInf6().toLowerCase().contains(filter)
				|| obj.getSpInf7().toLowerCase().contains(filter) || obj.getSpInf8().toLowerCase().contains(filter)
				|| obj.getSpInf9().toLowerCase().contains(filter) || obj.getSpInf10().toLowerCase().contains(filter)
				|| obj.getSpInf31().toLowerCase().contains(filter);
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

	private void listenerTerritoryTableView() {

		this.territoryTableView.setRowFactory(param -> {
			TableRow<TerritoryObj> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (!row.isEmpty()))
					editTerritory(row.getItem());
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

		if (territoryTableView.getSelectionModel().getSelectedIndex() > -1) {

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
				Tab newTab = new Tab("", layout);
				newTab.setClosable(true);
				newTab.getStyleClass().add("tab_001");
				newTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.PLUS));

				ctrl.setParentTabPane(this.memberTabPane);
				ctrl.setMembersTab(territoryTab);
				ctrl.setNewTab(newTab);
				ctrl.setConfigs(this.configs);

//				congrTabPane.getTabs().add(newMemberTab);
//				congrTabPane.getSelectionModel().select(newMemberTab);
				this.memberTabPane.getTabs().add(newTab);
				this.memberTabPane.getSelectionModel().select(newTab);

				ctrl.objectInitialize();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void newTerritory() {

		if (!this.application.getUser().isSpUserSU() && !this.application.getUser().isSpInf25()) {

			final String content = this.application.getSettings().getLanguage()
					.getString("territory.error.nopermission");

			this.application.getAlertBuilder2().error(this.ownerStage, content);

			return;
		}

		if (!isAlreadyOpen(this.territoryTabPane, "")) {

			try {

				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(Meta.Views.TERRITORY_EDITOR_FXML_URL);
				AnchorPane layout = (AnchorPane) fxmlLoader.load();

				TerritoryEditor ctrl = (TerritoryEditor) fxmlLoader.getController();
				ctrl.setApplication(this.application);
				ctrl.setSettings(this.settings);
				ctrl.setOwnerStage(this.ownerStage);
				ctrl.setOwnerCtrl(this);

				Tab newTab = new Tab("", layout);
				newTab.setClosable(true);
				newTab.getStyleClass().add("tab_001");
				newTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.PLUS));

				ctrl.setParentTabPane(this.territoryTabPane);
//				ctrl.setMembersTab(membersTab);
				ctrl.setNewTab(newTab);
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
				// ctrl.setSelectedMember(member);
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
				ctrl.setNewTab(newMemberTab);

//				congrTabPane.getTabs().add(newMemberTab);
//				congrTabPane.getSelectionModel().select(newMemberTab);
				this.memberTabPane.getTabs().add(newMemberTab);
				this.memberTabPane.getSelectionModel().select(newMemberTab);

			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	private void editTerritory() {

		if (this.territoryTableView.getSelectionModel().getSelectedIndex() > -1)
			editTerritory(this.territoryTableView.getSelectionModel().getSelectedItem());
	}

	private void editTerritory(TerritoryObj territoryObj) {

		if (!this.application.getUser().isSpUserSU() && !this.application.getUser().isSpInf25()) {

			final String content = this.application.getSettings().getLanguage()
					.getString("territory.error.nopermission");

			this.application.getAlertBuilder2().error(this.ownerStage, content);

			return;
		}

		if (!isAlreadyOpen(this.territoryTabPane, territoryObj.getSpInf7())) {

			try {

				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(Meta.Views.TERRITORY_EDITOR_FXML_URL);
				AnchorPane layout = (AnchorPane) fxmlLoader.load();

				TerritoryEditor ctrl = (TerritoryEditor) fxmlLoader.getController();
				ctrl.setApplication(this.application);
				ctrl.setSettings(this.settings);
				ctrl.setOwnerStage(this.ownerStage);
				ctrl.setOwnerCtrl(this);
				ctrl.setSelectedTerritory(territoryObj);

				Tab newTab = new Tab(territoryObj.getSpInf7(), layout);
				newTab.setClosable(true);
				newTab.getStyleClass().add("tab_001");
				newTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.TERRITORY_EDIT));

				ctrl.setParentTabPane(this.territoryTabPane);
				ctrl.setNewTab(newTab);

				this.territoryTabPane.getTabs().add(newTab);
				this.territoryTabPane.getSelectionModel().select(newTab);

				ctrl.objectInitialize();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	private void removeTerritory() {

		if (!this.application.getUser().isSpUserSU() && !this.application.getUser().isSpInf25()) {

			final String content = this.application.getSettings().getLanguage()
					.getString("territory.error.nopermission");

			this.application.getAlertBuilder2().error(this.ownerStage, content);

			return;
		}

		if (this.territoryTableView.getSelectionModel().getSelectedIndex() > -1) {

			TerritoryObj territoryObj = this.territoryTableView.getSelectionModel().getSelectedItem();

			String territoryNr = territoryObj.getSpInf7();
			String territoryName = territoryObj.getSpInf8();

			String header = this.application.getSettings().getLanguage().getString("territory.confirm.territoryremove");
			String content = String.format("%s - %s", territoryNr, territoryName);
			if (this.application.getAlertBuilder2().confirm(this.ownerStage, header, content)) {

				int id = territoryObj.getSpTerritoryID();

				String waitMessage = this.language.getString("territory.wait.delete");

				TaskManager.run(this.application.getAlertBuilder2(), this.ownerStage, waitMessage,
						new TerritoryDeleteTask(this.application.getAlertBuilder2(), this.settings, this.ownerStage,
								this, id));

			}
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

	public void territoryTableViewRefresh() {
		Platform.runLater(() -> this.territoryTableView.refresh());
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

	public TabPane getTerritoryTabPane() {
		return territoryTabPane;
	}

	public void setTerritoryTabPane(TabPane territoryTabPane) {
		this.territoryTabPane = territoryTabPane;
	}

	public ObservableList<TerritoryObj> getTerritoryList() {
		return territoryList;
	}

	public void setTerritoryList(ObservableList<TerritoryObj> territoryList) {
		this.territoryList = territoryList;
	}
}
