package com.sm.net.sp.view.home.user.menu.territory;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.StreamSupport;

import org.apache.commons.io.FileUtils;

import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.actions.Actions;
import com.sm.net.sp.dialogs.territory.TerritoryAssignDateDialog;
import com.sm.net.sp.dialogs.territory.TerritoryAssignToMemberDialog;
import com.sm.net.sp.dialogs.territory.TerritoryDialog;
import com.sm.net.sp.model.EnumPrintLayouts;
import com.sm.net.sp.model.Member;
import com.sm.net.sp.model.TerritoryObj;
import com.sm.net.sp.model.TerritoryRegistry;
import com.sm.net.sp.model.TerritoryRegistryEntity;
import com.sm.net.sp.model.TerritoryResource;
import com.sm.net.sp.model.UpdateDataAdapter;
import com.sm.net.sp.settings.Settings;
import com.sm.net.sp.utils.CommonUtils;
import com.sm.net.sp.view.SupportPlannerView;
import com.sm.net.sp.view.home.user.menu.territory.task.TerritoryDeleteTask;
import com.sm.net.sp.view.home.user.menu.territory.task.TerritoryDownloadAllTask;
import com.sm.net.sp.view.home.user.menu.territory.task.TerritoryDownloadTask;
import com.sm.net.sp.view.home.user.menu.territory.task.TerritoryLoadTask;
import com.sm.net.sp.view.home.user.menu.territory.task.TerritoryRegistryEntitySaveTask;
import com.sm.net.sp.view.home.user.menu.territory.task.TerritoryRegistryLoadTask;
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
	private TabPane territoryTabPane;
	@FXML
	private Tab territoryListTab;

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
	private Button territoryAssignButton;

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
	private TableView<Member> membersTableView;
	@FXML
	private TableColumn<Member, String> memberSurnameTableColumn;
	@FXML
	private TableColumn<Member, String> memberNameTableColumn;
	@FXML
	private TableColumn<Member, ImageView> memberIconTableColumn;

	@FXML
	private Button memberAssignedTerritoryReturnButton;

	@FXML
	private TextField filterMemberTextField;

	@FXML
	private Label memberAssignedTerritoryLabel;

	@FXML
	private TableView<TerritoryObj> memberAssignedTerritoryTableView;
	@FXML
	private TableColumn<TerritoryObj, BigDecimal> memberAssignedTerritoryNumberTableColumn;
	@FXML
	private TableColumn<TerritoryObj, String> memberAssignedTerritoryNameTableColumn;

	@FXML
	private VBox memberAssignedTerritoryTablesVBox;
	@FXML
	private StackPane memberAssignedTerritoryImageViewStackPane;
	@FXML
	private ImageView memberAssignedTerritoryImageView;

	private Settings settings;
	private Language language;
	private Stage ownerStage;

	private SupportPlannerView application;

	private ObservableList<TerritoryObj> territoryList;
	private ObservableList<File> territoryDocs;
	private ObservableList<File> territoryImages;

	private ObservableList<Member> membersList;

	private HashMap<String, String> configs;

	private TerritoryRegistry territoryRegistry;

	@FXML
	private void initialize() {
		styleClasses();
		cellValueFactory();
	}

	private void cellValueFactory() {

		this.territoryNumberTableColumn.setCellValueFactory(
				cellData -> new SimpleObjectProperty<BigDecimal>(new BigDecimal(cellData.getValue().getSpInf7())));
		this.territoryNameTableColumn.setCellValueFactory(cellData -> cellData.getValue().spInf8Property());
		this.territoryAssignedToTableColumn.setCellValueFactory(cellData -> {
			Member assignedMember = cellData.getValue().getAssignedMember();
			if (assignedMember != null) {
				return new SimpleStringProperty(assignedMember.getNameStyle1());
			}
			return null;
		});

		this.territoryDocsNameTableColumn
				.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
		this.territoryImagesNameTableColumn
				.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));

		this.memberSurnameTableColumn.setCellValueFactory(cellData -> cellData.getValue().spInf2DecryptedProperty());
		this.memberNameTableColumn.setCellValueFactory(cellData -> cellData.getValue().spInf1DecryptedProperty());

		this.memberIconTableColumn.setCellValueFactory(cellData -> {

			if (cellData.getValue().getSpInf4() == 0)
				return new SimpleObjectProperty<ImageView>(Meta.Resources.imageForButtonSmall(Meta.Resources.MALE));
			else
				return new SimpleObjectProperty<ImageView>(Meta.Resources.imageForButtonSmall(Meta.Resources.FEMALE));

		});
	}

	private void styleClasses() {

		this.congrHeaderLabel.getStyleClass().add("label_header_001");

		this.congrTabPane.getStyleClass().add("tab_pane_003");

		this.territoryTab.getStyleClass().add("tab_001");
		this.publisherTab.getStyleClass().add("tab_001");

		this.territoryTabPane.getStyleClass().add("tab_pane_001");
		this.territoryListTab.getStyleClass().add("tab_001");

		this.memberIconTableColumn.getStyleClass().add("table_column_002");

		this.membersTableView.getStyleClass().add("table_view_001");
		this.territoryTableView.getStyleClass().add("table_view_001");

		this.memberAssignedTerritoryReturnButton.getStyleClass().add("button_image_001");

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

		this.filterMemberTextField.getStyleClass().add("text_field_001");

		this.territoryFilterTextField.getStyleClass().add("text_field_001");

		this.memberAssignedTerritoryLabel.getStyleClass().add("label_001");
		this.memberAssignedTerritoryTableView.getStyleClass().add("table_view_001");

		this.territoryAssignButton.getStyleClass().add("button_image_001");
	}

	public void objectInitialize() {

		this.territoryRegistry = new TerritoryRegistry();

		this.territoryDocs = FXCollections.observableArrayList();
		this.territoryImages = FXCollections.observableArrayList();

		this.territoryDocsTableView.setItems(this.territoryDocs);
		this.territoryImagesTableView.setItems(this.territoryImages);

		this.territoryTablesVBox.setMinWidth(600);
		this.territoryTablesVBox.setMaxWidth(600);

		this.memberAssignedTerritoryTablesVBox.setMinWidth(600);
		this.memberAssignedTerritoryTablesVBox.setMaxWidth(600);

		listeners();
		viewUpdate();
		initInfo();

		updateMembers();
	}

	@Override
	public void updateMembers() {
		Actions.getAllMembers(settings, ownerStage, this);
	}

	@Override
	public void updateMembers(ObservableList<Member> list) {

		this.membersList = list;
		this.membersList.sort((a, b) -> (a.getSpInf2Decrypted().concat(a.getSpInf1Decrypted())
				.compareTo(b.getSpInf2Decrypted().concat(b.getSpInf1Decrypted()))));

		// Rimuovi i non proclamatori
		this.membersList.removeIf(member -> {
			boolean isPublisher = member.getSpInf7() == 1 || member.getSpInf8() == 1;
			boolean isInactive = member.getSpInf38() == 1;
			return (!isPublisher || isInactive);
		});

		this.membersTableView.setItems(membersList);

		this.filterMemberTextField.setText("");
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

		this.territoryListTab.setText(this.language.getString("territory.tab.territorylist"));

		this.memberIconTableColumn.setText("");
		this.memberIconTableColumn.setMinWidth(50);
		this.memberIconTableColumn.setMaxWidth(50);
		this.memberIconTableColumn.setResizable(false);

		this.memberSurnameTableColumn.setText(this.language.getString("TEXT0013"));
		this.memberNameTableColumn.setText(this.language.getString("TEXT0014"));

		Tooltip memberAssignedTerritoryReturnTooltip = new Tooltip(
				this.language.getString("territory.tooltip.assignedterritoryreturn"));
		memberAssignedTerritoryReturnTooltip.getStyleClass().add("tooltip_001");
		this.memberAssignedTerritoryReturnButton.setTooltip(memberAssignedTerritoryReturnTooltip);
		this.memberAssignedTerritoryReturnButton.setText("");
		this.memberAssignedTerritoryReturnButton.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.OK));

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

		this.memberAssignedTerritoryLabel.setText(this.language.getString("territory.label.assignedterritory"));

		this.memberAssignedTerritoryNumberTableColumn
				.setText(this.language.getString("territory.tablecolumns.territorynumber"));
		this.memberAssignedTerritoryNumberTableColumn.setMinWidth(100);
		this.memberAssignedTerritoryNumberTableColumn.setMaxWidth(100);
		this.memberAssignedTerritoryNumberTableColumn.setResizable(false);

		this.memberAssignedTerritoryNameTableColumn
				.setText(this.language.getString("territory.tablecolumns.territoryname"));

		this.memberAssignedTerritoryTableView.setMaxHeight(250);

		Tooltip territoryAssignTooltip = new Tooltip(this.language.getString("territory.tooltip.assign"));
		territoryAssignTooltip.getStyleClass().add("tooltip_001");
		this.territoryAssignButton.setTooltip(territoryAssignTooltip);
		this.territoryAssignButton.setText("");
		this.territoryAssignButton.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.MEMBER));
	}

	private void initInfo() {

		this.territoryList = FXCollections.observableArrayList();
		this.territoryTableView.setItems(this.territoryList);

		updateTerritory();
	}

	public void loadTerritoryRegistry() {

		String waitMessage = this.language.getString("territory.wait.loadregistry");

		TaskManager.run(this.application.getAlertBuilder2(), this.ownerStage, waitMessage,
				new TerritoryRegistryLoadTask(this.application.getAlertBuilder2(), this.settings, this.ownerStage,
						this));

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
		this.territoryAssignButton.setOnAction(event -> assignTerritory());

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

		this.territoryDocsTableView.setRowFactory(param -> {
			TableRow<File> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (!row.isEmpty())) {
					try {
						CommonUtils.open(row.getItem());
					} catch (IOException e) {
					}
				}
			});
			return row;
		});

		this.territoryImagesTableView.setRowFactory(param -> {
			TableRow<File> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (!row.isEmpty())) {
					try {
						CommonUtils.open(row.getItem());
					} catch (IOException e) {
					}
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
		this.filterMemberTextField.textProperty()
				.addListener((observable, oldValue, newValue) -> updateFilterMember(newValue));
//

//		this.familiesMapsButton.setOnAction(event -> viewMaps());
	}

	private void assignTerritory() {

		// TODO: Assegna territorio

		if (this.territoryTableView.getSelectionModel().getSelectedIndex() > -1) {

			TerritoryObj territoryObj = this.territoryTableView.getSelectionModel().getSelectedItem();
			if (territoryObj.isAvailable()) {

				Member member = TerritoryAssignToMemberDialog.show(this.application, this.ownerStage, this.membersList);
				if (member != null) {

					// TODO: Aggiungere il controllo che la data scelta sia superiore all'ultima

					LocalDate assignDate = TerritoryAssignDateDialog.show(this.application, this.ownerStage,
							territoryObj, member);

					if (assignDate != null) {

						String header = this.application.getSettings().getLanguage()
								.getString("territory.error.registryentityheader");

						String contentFormat = this.application.getSettings().getLanguage()
								.getStringWithNewLine("territory.error.registryentitycontentformat");

						String territoryName = String.format("%s - %s", territoryObj.getSpInf7(),
								territoryObj.getSpInf8());
						String memberName = member.getNameStyle1();

						String datePattern = this.application.getSettings().getLanguage().getString("datepattern");
						String date = DateTimeFormatter.ofPattern(datePattern).format(assignDate);

						String content = String.format(contentFormat, territoryName, memberName, date);

						if (this.application.getAlertBuilder2().confirm(this.ownerStage, header, content)) {

							// TODO: Salvare il movimento nel registro

							String waitMessage = this.language.getString("territory.wait.assignsave");
							TaskManager.run(this.application.getAlertBuilder2(), this.ownerStage, waitMessage,
									new TerritoryRegistryEntitySaveTask(this.application.getAlertBuilder2(),
											this.settings, this.ownerStage, this, territoryObj, member, assignDate));

						}

					} else {
						this.application.getAlertBuilder2().error(this.ownerStage,
								this.language.getString("territory.error.noassigndate"));
					}

				} else {
					this.application.getAlertBuilder2().error(this.ownerStage,
							this.language.getString("territory.error.nomembertoassign"));
				}

			} else {
				this.application.getAlertBuilder2().error(this.ownerStage,
						this.language.getString("territory.error.noterritoryavailable"));
			}

		} else {
			this.application.getAlertBuilder2().error(this.ownerStage,
					this.language.getString("territory.error.noterritoryselected"));
		}
	}

	public void updateTerritoryRegistryList(ObservableList<TerritoryRegistryEntity> list) {
		this.territoryRegistry.update(list);
	}

	public void updateTerritoryAssignedMember() {
		this.territoryRegistry.updateTerritoryAssignedMember(this.territoryList, this.membersList);
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

			this.territoryImagesTableView.getSelectionModel().selectFirst();
		}
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
