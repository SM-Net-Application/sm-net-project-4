package com.sm.net.sp.view.home.user.menu.territory;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.stream.StreamSupport;

import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;

import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.actions.Actions;
import com.sm.net.sp.dialogs.territory.TerritoryAssignDateDialog;
import com.sm.net.sp.dialogs.territory.TerritoryAssignToMemberDialog;
import com.sm.net.sp.dialogs.territory.TerritoryDialog;
import com.sm.net.sp.dialogs.territory.TerritoryReturnDateDialog;
import com.sm.net.sp.model.EnumPrintLayouts;
import com.sm.net.sp.model.Member;
import com.sm.net.sp.model.TerritoryMap;
import com.sm.net.sp.model.TerritoryModul;
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
import com.sm.net.sp.view.home.user.menu.territory.task.TerritoryMapsDeleteTask;
import com.sm.net.sp.view.home.user.menu.territory.task.TerritoryMapsDownloadAllTask;
import com.sm.net.sp.view.home.user.menu.territory.task.TerritoryMapsDownloadTask;
import com.sm.net.sp.view.home.user.menu.territory.task.TerritoryMapsLoadTask;
import com.sm.net.sp.view.home.user.menu.territory.task.TerritoryRegistryEntitySaveReturnTask;
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
import javafx.stage.DirectoryChooser;
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
	private Tab mapsTab;

	@FXML
	private TabPane territoryTabPane;
	@FXML
	private Tab territoryListTab;

	@FXML
	private Button territoryPrintButton;

	@FXML
	private Button territoryAddButton;
	@FXML
	private Button territoryEditButton;
	@FXML
	private Button territoryViewButton;
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
	private Button territoryReturnButton;

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
	private TableColumn<TerritoryObj, LocalDate> territoryAssignedDateTableColumn;

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
	private TableColumn<TerritoryObj, LocalDate> memberAssignedTerritoryAssignedDateTableColumn;

	@FXML
	private VBox memberAssignedTerritoryTablesVBox;
	@FXML
	private StackPane memberAssignedTerritoryImageViewStackPane;
	@FXML
	private ImageView memberAssignedTerritoryImageView;

	@FXML
	private TabPane territoryMapsTabPane;
	@FXML
	private Tab territoryMapsListTab;

	@FXML
	private Button territoryMapsAddButton;
	@FXML
	private Button territoryMapsEditButton;
	@FXML
	private Button territoryMapsViewButton;
	@FXML
	private Button territoryMapsRemoveButton;
	@FXML
	private Button territoryMapsOpenViewerButton;
	@FXML
	private Button territoryMapsOpenViewerURLButton;
	@FXML
	private Button territoryMapsResourcesDownloadButton;
	@FXML
	private Button territoryMapsResourcesDownloadAllButton;
	@FXML
	private Button territoryMapsResourcesOpenDirectoryButton;
	@FXML
	private Button territoryMapsResourcesDeleteAllButton;

	@FXML
	private TextField territoryMapsFilterTextField;

	@FXML
	private TableView<TerritoryMap> territoryMapsTableView;
	@FXML
	private TableColumn<TerritoryMap, String> territoryMapsNameTableColumn;

	@FXML
	private Label territoryMapsDocsLabel;
	@FXML
	private TableView<File> territoryMapsDocsTableView;
	@FXML
	private TableColumn<File, String> territoryMapsDocsNameTableColumn;

	@FXML
	private Label territoryMapsImagesLabel;
	@FXML
	private TableView<File> territoryMapsImagesTableView;
	@FXML
	private TableColumn<File, String> territoryMapsImagesNameTableColumn;

	@FXML
	private VBox territoryMapsTablesVBox;
	@FXML
	private StackPane territoryMapsImageViewStackPane;
	@FXML
	private ImageView territoryMapsImageView;

	private Settings settings;
	private Language language;
	private Stage ownerStage;

	private SupportPlannerView application;

	private ObservableList<TerritoryObj> territoryList;
	private ObservableList<File> territoryDocs;
	private ObservableList<File> territoryImages;

	private ObservableList<TerritoryMap> territoryMapList;
	private ObservableList<File> territoryMapDocs;
	private ObservableList<File> territoryMapImages;

	private ObservableList<Member> membersList;

	private HashMap<String, String> configs;

	private TerritoryRegistry territoryRegistry;
	private boolean imageView1;
	private boolean imageView2;
	private boolean imageView3;

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

		this.territoryAssignedDateTableColumn
				.setCellValueFactory(cellData -> cellData.getValue().assignedDateProperty());

		this.territoryDocsNameTableColumn
				.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
		this.territoryImagesNameTableColumn
				.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));

		this.territoryMapsNameTableColumn.setCellValueFactory(cellData -> cellData.getValue().spInf1Property());

		this.territoryMapsDocsNameTableColumn
				.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
		this.territoryMapsImagesNameTableColumn
				.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));

		this.memberSurnameTableColumn.setCellValueFactory(cellData -> cellData.getValue().spInf2DecryptedProperty());
		this.memberNameTableColumn.setCellValueFactory(cellData -> cellData.getValue().spInf1DecryptedProperty());

		this.memberIconTableColumn.setCellValueFactory(cellData -> {

			if (cellData.getValue().getSpInf4() == 0)
				return new SimpleObjectProperty<ImageView>(Meta.Resources.imageForButtonSmall(Meta.Resources.MALE));
			else
				return new SimpleObjectProperty<ImageView>(Meta.Resources.imageForButtonSmall(Meta.Resources.FEMALE));

		});

		this.memberAssignedTerritoryNumberTableColumn.setCellValueFactory(
				cellData -> new SimpleObjectProperty<BigDecimal>(new BigDecimal(cellData.getValue().getSpInf7())));
		this.memberAssignedTerritoryNameTableColumn
				.setCellValueFactory(cellData -> cellData.getValue().spInf8Property());
		this.memberAssignedTerritoryAssignedDateTableColumn
				.setCellValueFactory(cellData -> cellData.getValue().assignedDateProperty());
	}

	private void styleClasses() {

		this.congrHeaderLabel.getStyleClass().add("label_header_001");

		this.congrTabPane.getStyleClass().add("tab_pane_003");

		this.territoryTab.getStyleClass().add("tab_001");
		this.publisherTab.getStyleClass().add("tab_001");
		this.mapsTab.getStyleClass().add("tab_001");

		this.territoryTabPane.getStyleClass().add("tab_pane_001");
		this.territoryListTab.getStyleClass().add("tab_001");

		this.territoryMapsTabPane.getStyleClass().add("tab_pane_001");
		this.territoryMapsListTab.getStyleClass().add("tab_001");

		this.memberIconTableColumn.getStyleClass().add("table_column_002");

		this.membersTableView.getStyleClass().add("table_view_001");

		this.territoryTableView.getStyleClass().add("table_view_001");
		this.territoryMapsTableView.getStyleClass().add("table_view_001");

		this.territoryReturnButton.getStyleClass().add("button_image_001");
		this.memberAssignedTerritoryReturnButton.getStyleClass().add("button_image_001");

		this.territoryNumberTableColumn.getStyleClass().add("table_column_002");
		this.territoryAssignedDateTableColumn.getStyleClass().add("table_column_002");

		this.territoryPrintButton.getStyleClass().add("button_image_001");

		this.territoryAddButton.getStyleClass().add("button_image_001");
		this.territoryOpenViewerButton.getStyleClass().add("button_image_001");
		this.territoryOpenViewerURLButton.getStyleClass().add("button_image_001");
		this.territoryRemoveButton.getStyleClass().add("button_image_001");
		this.territoryEditButton.getStyleClass().add("button_image_001");
		this.territoryViewButton.getStyleClass().add("button_image_001");
		this.territoryResourcesDownloadButton.getStyleClass().add("button_image_001");
		this.territoryResourcesDownloadAllButton.getStyleClass().add("button_image_001");
		this.territoryResourcesOpenDirectoryButton.getStyleClass().add("button_image_001");
		this.territoryResourcesDeleteAllButton.getStyleClass().add("button_image_001");

		this.territoryMapsAddButton.getStyleClass().add("button_image_001");
		this.territoryMapsOpenViewerButton.getStyleClass().add("button_image_001");
		this.territoryMapsOpenViewerURLButton.getStyleClass().add("button_image_001");
		this.territoryMapsRemoveButton.getStyleClass().add("button_image_001");
		this.territoryMapsEditButton.getStyleClass().add("button_image_001");
		this.territoryMapsViewButton.getStyleClass().add("button_image_001");
		this.territoryMapsResourcesDownloadButton.getStyleClass().add("button_image_001");
		this.territoryMapsResourcesDownloadAllButton.getStyleClass().add("button_image_001");
		this.territoryMapsResourcesOpenDirectoryButton.getStyleClass().add("button_image_001");
		this.territoryMapsResourcesDeleteAllButton.getStyleClass().add("button_image_001");

		this.territoryDocsLabel.getStyleClass().add("label_001");
		this.territoryImagesLabel.getStyleClass().add("label_001");
		this.territoryMapsDocsLabel.getStyleClass().add("label_001");
		this.territoryMapsImagesLabel.getStyleClass().add("label_001");

		this.territoryDocsTableView.getStyleClass().add("table_view_001");
		this.territoryImagesTableView.getStyleClass().add("table_view_001");
		this.territoryMapsDocsTableView.getStyleClass().add("table_view_001");
		this.territoryMapsImagesTableView.getStyleClass().add("table_view_001");

		this.filterMemberTextField.getStyleClass().add("text_field_001");

		this.territoryFilterTextField.getStyleClass().add("text_field_001");
		this.territoryMapsFilterTextField.getStyleClass().add("text_field_001");

		this.memberAssignedTerritoryLabel.getStyleClass().add("label_001");
		this.memberAssignedTerritoryTableView.getStyleClass().add("table_view_001");

		this.memberAssignedTerritoryNumberTableColumn.getStyleClass().add("table_column_002");
		this.memberAssignedTerritoryAssignedDateTableColumn.getStyleClass().add("table_column_002");

		this.territoryAssignButton.getStyleClass().add("button_image_001");
	}

	public void objectInitialize() {

		this.imageView1 = false;
		this.imageView2 = false;

		this.territoryRegistry = new TerritoryRegistry();

		this.territoryDocs = FXCollections.observableArrayList();
		this.territoryImages = FXCollections.observableArrayList();

		this.territoryMapDocs = FXCollections.observableArrayList();
		this.territoryMapImages = FXCollections.observableArrayList();

		this.territoryDocsTableView.setItems(this.territoryDocs);
		this.territoryImagesTableView.setItems(this.territoryImages);

		this.territoryMapsDocsTableView.setItems(this.territoryMapDocs);
		this.territoryMapsImagesTableView.setItems(this.territoryMapImages);

		this.territoryTablesVBox.setMinWidth(750);
		this.territoryTablesVBox.setMaxWidth(750);

		this.territoryMapsTablesVBox.setMinWidth(750);
		this.territoryMapsTablesVBox.setMaxWidth(750);

		this.memberAssignedTerritoryTablesVBox.setMinWidth(600);
		this.memberAssignedTerritoryTablesVBox.setMaxWidth(600);

		this.territoryList = FXCollections.observableArrayList();
		this.territoryTableView.setItems(this.territoryList);

		this.territoryMapList = FXCollections.observableArrayList();
		this.territoryMapsTableView.setItems(this.territoryMapList);

		listeners();
		viewUpdate();

		updateMembers();
		updateTerritoryMaps();
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

		updateTerritory();
	}

	public void updateTerritoryList(ObservableList<TerritoryObj> list) {
		this.territoryList.clear();
		this.territoryList.addAll(list);
	}

	public void updateTerritoryMapList(ObservableList<TerritoryMap> list) {
		this.territoryMapList.clear();
		this.territoryMapList.addAll(list);
		Platform.runLater(() -> this.territoryMapsTableView.refresh());
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

		Tooltip territoryMapsTabTooltip = new Tooltip(language.getString("territory.tab.maps"));
		territoryMapsTabTooltip.getStyleClass().add("tooltip_001");
		this.mapsTab.setTooltip(territoryMapsTabTooltip);
		this.mapsTab.setText("");
		this.mapsTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.MAPSGLOBAL));
		this.mapsTab.setClosable(false);

		this.territoryListTab.setText(this.language.getString("territory.tab.territorylist"));
		this.territoryMapsListTab.setText(this.language.getString("territory.tab.territorylist"));

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

		this.territoryReturnButton.setTooltip(memberAssignedTerritoryReturnTooltip);
		this.territoryReturnButton.setText("");
		this.territoryReturnButton.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.OK));

		this.territoryNumberTableColumn.setText(this.language.getString("territory.tablecolumns.territorynumber"));
		this.territoryNumberTableColumn.setMinWidth(100);
		this.territoryNumberTableColumn.setMaxWidth(100);
		this.territoryNumberTableColumn.setResizable(false);
		this.territoryNameTableColumn.setText(this.language.getString("territory.tablecolumns.territoryname"));
		this.territoryAssignedToTableColumn
				.setText(this.language.getString("territory.tablecolumns.territoryassignedto"));

		this.territoryMapsNameTableColumn.setText(this.language.getString("territory.tablecolumns.mapsname"));

		this.territoryAssignedDateTableColumn
				.setText(this.language.getString("territory.tablecolumns.territoryassigneddate"));
		this.territoryAssignedDateTableColumn.setMinWidth(100);
		this.territoryAssignedDateTableColumn.setMaxWidth(100);

		Tooltip territoryPrintTooltip = new Tooltip(this.language.getString("territory.tooltip.print"));
		territoryPrintTooltip.getStyleClass().add("tooltip_001");
		this.territoryPrintButton.setTooltip(territoryPrintTooltip);
		this.territoryPrintButton.setText("");
		this.territoryPrintButton.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.PRINT));

		Tooltip territoryAddTooltip = new Tooltip(this.language.getString("territory.tooltip.add"));
		territoryAddTooltip.getStyleClass().add("tooltip_001");
		this.territoryAddButton.setTooltip(territoryAddTooltip);
		this.territoryAddButton.setText("");
		this.territoryAddButton.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.TERRITORY_ADD));

		Tooltip territoryMapsAddTooltip = new Tooltip(this.language.getString("territory.tooltip.mapsadd"));
		territoryMapsAddTooltip.getStyleClass().add("tooltip_001");
		this.territoryMapsAddButton.setTooltip(territoryMapsAddTooltip);
		this.territoryMapsAddButton.setText("");
		this.territoryMapsAddButton.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.MAPS_ADD));

		Tooltip territoryViewerTooltip = new Tooltip(this.language.getString("territory.tooltip.viewer"));
		territoryViewerTooltip.getStyleClass().add("tooltip_001");
		this.territoryOpenViewerButton.setTooltip(territoryViewerTooltip);
		this.territoryOpenViewerButton.setText("");
		this.territoryOpenViewerButton.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.TERRITORYVIEWER));

		Tooltip territoryMapsViewerTooltip = new Tooltip(this.language.getString("territory.tooltip.mapsviewer"));
		territoryMapsViewerTooltip.getStyleClass().add("tooltip_001");
		this.territoryMapsOpenViewerButton.setTooltip(territoryMapsViewerTooltip);
		this.territoryMapsOpenViewerButton.setText("");
		this.territoryMapsOpenViewerButton
				.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.TERRITORYVIEWER));

		Tooltip territoryViewerURLTooltip = new Tooltip(this.language.getString("territory.tooltip.viewerurl"));
		territoryViewerURLTooltip.getStyleClass().add("tooltip_001");
		this.territoryOpenViewerURLButton.setTooltip(territoryViewerURLTooltip);
		this.territoryOpenViewerURLButton.setText("");
		this.territoryOpenViewerURLButton
				.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.TERRITORYVIEWERURL));

		Tooltip territoryMapsViewerURLTooltip = new Tooltip(this.language.getString("territory.tooltip.mapsviewerurl"));
		territoryMapsViewerURLTooltip.getStyleClass().add("tooltip_001");
		this.territoryMapsOpenViewerURLButton.setTooltip(territoryMapsViewerURLTooltip);
		this.territoryMapsOpenViewerURLButton.setText("");
		this.territoryMapsOpenViewerURLButton
				.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.TERRITORYVIEWERURL));

		Tooltip territoryRemoveTooltip = new Tooltip(this.language.getString("territory.tooltip.remove"));
		territoryRemoveTooltip.getStyleClass().add("tooltip_001");
		this.territoryRemoveButton.setTooltip(territoryRemoveTooltip);
		this.territoryRemoveButton.setText("");
		this.territoryRemoveButton.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.TERRITORY_REMOVE));

		Tooltip territoryMapsRemoveTooltip = new Tooltip(this.language.getString("territory.tooltip.mapsremove"));
		territoryMapsRemoveTooltip.getStyleClass().add("tooltip_001");
		this.territoryMapsRemoveButton.setTooltip(territoryMapsRemoveTooltip);
		this.territoryMapsRemoveButton.setText("");
		this.territoryMapsRemoveButton.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.MAPS_REMOVE));

		Tooltip territoryEditTooltip = new Tooltip(this.language.getString("territory.tooltip.edit"));
		territoryEditTooltip.getStyleClass().add("tooltip_001");
		this.territoryEditButton.setTooltip(territoryEditTooltip);
		this.territoryEditButton.setText("");
		this.territoryEditButton.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.TERRITORY_EDIT));

		Tooltip territoryMapsEditTooltip = new Tooltip(this.language.getString("territory.tooltip.mapsedit"));
		territoryMapsEditTooltip.getStyleClass().add("tooltip_001");
		this.territoryMapsEditButton.setTooltip(territoryMapsEditTooltip);
		this.territoryMapsEditButton.setText("");
		this.territoryMapsEditButton.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.MAPS_EDIT));

		Tooltip territoryViewTooltip = new Tooltip(this.language.getString("territory.tooltip.view"));
		territoryViewTooltip.getStyleClass().add("tooltip_001");
		this.territoryViewButton.setTooltip(territoryViewTooltip);
		this.territoryViewButton.setText("");
		this.territoryViewButton.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.TERRITORY_VIEW));

		Tooltip territoryMapsViewTooltip = new Tooltip(this.language.getString("territory.tooltip.view"));
		territoryMapsViewTooltip.getStyleClass().add("tooltip_001");
		this.territoryMapsViewButton.setTooltip(territoryMapsViewTooltip);
		this.territoryMapsViewButton.setText("");
		this.territoryMapsViewButton.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.TERRITORY_VIEW));

		Tooltip territoryDownloadTooltip = new Tooltip(this.language.getString("territory.tooltip.downloadresources"));
		territoryDownloadTooltip.getStyleClass().add("tooltip_001");
		this.territoryResourcesDownloadButton.setTooltip(territoryDownloadTooltip);
		this.territoryResourcesDownloadButton.setText("");
		this.territoryResourcesDownloadButton.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.DOWNLOAD));

		Tooltip territoryMapsDownloadTooltip = new Tooltip(
				this.language.getString("territory.tooltip.mapsdownloadresources"));
		territoryMapsDownloadTooltip.getStyleClass().add("tooltip_001");
		this.territoryMapsResourcesDownloadButton.setTooltip(territoryMapsDownloadTooltip);
		this.territoryMapsResourcesDownloadButton.setText("");
		this.territoryMapsResourcesDownloadButton
				.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.DOWNLOAD));

		Tooltip territoryDownloadAllTooltip = new Tooltip(
				this.language.getString("territory.tooltip.downloadallresources"));
		territoryDownloadAllTooltip.getStyleClass().add("tooltip_001");
		this.territoryResourcesDownloadAllButton.setTooltip(territoryDownloadAllTooltip);
		this.territoryResourcesDownloadAllButton.setText("");
		this.territoryResourcesDownloadAllButton
				.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.DOWNLOAD2));

		Tooltip territoryMapsDownloadAllTooltip = new Tooltip(
				this.language.getString("territory.tooltip.mapsdownloadallresources"));
		territoryMapsDownloadAllTooltip.getStyleClass().add("tooltip_001");
		this.territoryMapsResourcesDownloadAllButton.setTooltip(territoryMapsDownloadAllTooltip);
		this.territoryMapsResourcesDownloadAllButton.setText("");
		this.territoryMapsResourcesDownloadAllButton
				.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.DOWNLOAD2));

		Tooltip territoryResourcesOpenDirectoryTooltip = new Tooltip(
				this.language.getString("territory.tooltip.resourcesopendirectory"));
		territoryResourcesOpenDirectoryTooltip.getStyleClass().add("tooltip_001");
		this.territoryResourcesOpenDirectoryButton.setTooltip(territoryResourcesOpenDirectoryTooltip);
		this.territoryResourcesOpenDirectoryButton.setText("");
		this.territoryResourcesOpenDirectoryButton
				.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.FOLDER));

		Tooltip territoryMapsResourcesOpenDirectoryTooltip = new Tooltip(
				this.language.getString("territory.tooltip.mapsresourcesopendirectory"));
		territoryMapsResourcesOpenDirectoryTooltip.getStyleClass().add("tooltip_001");
		this.territoryMapsResourcesOpenDirectoryButton.setTooltip(territoryMapsResourcesOpenDirectoryTooltip);
		this.territoryMapsResourcesOpenDirectoryButton.setText("");
		this.territoryMapsResourcesOpenDirectoryButton
				.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.FOLDER));

		Tooltip territoryResourcesDeleteAllTooltip = new Tooltip(
				this.language.getString("territory.tooltip.resourcesdeleteall"));
		territoryResourcesDeleteAllTooltip.getStyleClass().add("tooltip_001");
		this.territoryResourcesDeleteAllButton.setTooltip(territoryResourcesDeleteAllTooltip);
		this.territoryResourcesDeleteAllButton.setText("");
		this.territoryResourcesDeleteAllButton.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.DELETE));

		Tooltip territoryMapsResourcesDeleteAllTooltip = new Tooltip(
				this.language.getString("territory.tooltip.mapsresourcesdeleteall"));
		territoryMapsResourcesDeleteAllTooltip.getStyleClass().add("tooltip_001");
		this.territoryMapsResourcesDeleteAllButton.setTooltip(territoryMapsResourcesDeleteAllTooltip);
		this.territoryMapsResourcesDeleteAllButton.setText("");
		this.territoryMapsResourcesDeleteAllButton
				.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.DELETE));

		this.territoryDocsLabel.setText(this.language.getString("territory.label.resourcedocs"));
		this.territoryImagesLabel.setText(this.language.getString("territory.label.resourceimages"));

		this.territoryDocsNameTableColumn.setText(this.language.getString("territory.tablecolumn.resourcedocfilename"));
		this.territoryImagesNameTableColumn
				.setText(this.language.getString("territory.tablecolumn.resourceimagefilename"));

		this.territoryMapsDocsLabel.setText(this.language.getString("territory.label.resourcedocs"));
		this.territoryMapsImagesLabel.setText(this.language.getString("territory.label.resourceimages"));

		this.territoryMapsDocsNameTableColumn
				.setText(this.language.getString("territory.tablecolumn.resourcedocfilename"));
		this.territoryMapsImagesNameTableColumn
				.setText(this.language.getString("territory.tablecolumn.resourceimagefilename"));

		this.territoryTableView.setMinHeight(400);
		this.territoryTableView.setMaxHeight(400);
//		this.territoryDocsTableView.setMinHeight(250);
//		this.territoryDocsTableView.setMaxHeight(250);
//		this.territoryImagesTableView.setMinHeight(250);
//		this.territoryImagesTableView.setMaxHeight(250);

		this.memberAssignedTerritoryLabel.setText(this.language.getString("territory.label.assignedterritory"));

		this.memberAssignedTerritoryNumberTableColumn
				.setText(this.language.getString("territory.tablecolumns.territorynumber"));
		this.memberAssignedTerritoryNumberTableColumn.setMinWidth(100);
		this.memberAssignedTerritoryNumberTableColumn.setMaxWidth(100);
		this.memberAssignedTerritoryNumberTableColumn.setResizable(false);

		this.memberAssignedTerritoryNameTableColumn
				.setText(this.language.getString("territory.tablecolumns.territoryname"));

		this.memberAssignedTerritoryAssignedDateTableColumn
				.setText(this.language.getString("territory.tablecolumns.territoryassigneddate"));
		this.memberAssignedTerritoryAssignedDateTableColumn.setMinWidth(100);
		this.memberAssignedTerritoryAssignedDateTableColumn.setMaxWidth(100);

		this.membersTableView.setMinHeight(500);
		this.membersTableView.setMaxHeight(500);
//		this.memberAssignedTerritoryTableView.setMinHeight(250);
//		this.memberAssignedTerritoryTableView.setMaxHeight(250);

		Tooltip territoryAssignTooltip = new Tooltip(this.language.getString("territory.tooltip.assign"));
		territoryAssignTooltip.getStyleClass().add("tooltip_001");
		this.territoryAssignButton.setTooltip(territoryAssignTooltip);
		this.territoryAssignButton.setText("");
		this.territoryAssignButton.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.MEMBER));
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

	public void updateTerritoryMaps() {

		String waitMessage = this.language.getString("territorymaps.wait.load");

		TaskManager.run(this.application.getAlertBuilder2(), this.ownerStage, waitMessage,
				new TerritoryMapsLoadTask(this.application.getAlertBuilder2(), this.settings, this.ownerStage, this));
	}

	private void listeners() {

		this.territoryPrintButton.setOnAction(event -> print());
		this.territoryAddButton.setOnAction(event -> newTerritory());
		this.territoryEditButton.setOnAction(event -> editTerritory());
		this.territoryViewButton.setOnAction(event -> viewTerritory());
		this.territoryRemoveButton.setOnAction(event -> removeTerritory());
		this.territoryOpenViewerButton.setOnAction(event -> openTerritoryViewer());
		this.territoryOpenViewerURLButton.setOnAction(event -> openTerritoryViewerURL());
		this.territoryResourcesDownloadButton.setOnAction(event -> downloadTerritoryResources());
		this.territoryResourcesDownloadAllButton.setOnAction(event -> downloadAllTerritoryResources());
		this.territoryResourcesOpenDirectoryButton.setOnAction(event -> openResourceDirectory());
		this.territoryResourcesDeleteAllButton.setOnAction(event -> deleteResourceDirectory());
		this.territoryAssignButton.setOnAction(event -> assignTerritory());

		this.territoryMapsAddButton.setOnAction(event -> newTerritoryMaps());
		this.territoryMapsEditButton.setOnAction(event -> editTerritoryMaps());
		this.territoryMapsViewButton.setOnAction(event -> viewTerritoryMaps());
		this.territoryMapsRemoveButton.setOnAction(event -> removeTerritoryMaps());
		this.territoryMapsOpenViewerButton.setOnAction(event -> openTerritoryMapsViewer());
		this.territoryMapsOpenViewerURLButton.setOnAction(event -> openTerritoryMapsViewerURL());
		this.territoryMapsResourcesDownloadButton.setOnAction(event -> downloadTerritoryMapsResources());
		this.territoryMapsResourcesDownloadAllButton.setOnAction(event -> downloadAllTerritoryMapsResources());
		this.territoryMapsResourcesOpenDirectoryButton.setOnAction(event -> openResourceDirectoryMaps());
		this.territoryMapsResourcesDeleteAllButton.setOnAction(event -> deleteResourceDirectoryMaps());

		this.territoryTableView.getSelectionModel().selectedIndexProperty()
				.addListener((obs, oldV, newV) -> selectTerritory());

		this.territoryMapsTableView.getSelectionModel().selectedIndexProperty()
				.addListener((obs, oldV, newV) -> selectMaps());

		this.territoryImagesTableView.getSelectionModel().selectedIndexProperty()
				.addListener((obs, oldV, newV) -> selectTerritoryImage());

		this.territoryMapsImagesTableView.getSelectionModel().selectedIndexProperty()
				.addListener((obs, oldV, newV) -> selectTerritoryMapsImage());

		this.territoryTableView.setRowFactory(param -> {
			TableRow<TerritoryObj> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (!row.isEmpty())) {
					showRegistry(row.getItem());
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

		this.territoryMapsDocsTableView.setRowFactory(param -> {
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

		this.territoryMapsImagesTableView.setRowFactory(param -> {
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

		this.territoryMapsFilterTextField.textProperty()
				.addListener((observable, oldValue, newValue) -> updateFilterTerritoryMaps(newValue));

		this.filterMemberTextField.textProperty()
				.addListener((observable, oldValue, newValue) -> updateFilterMember(newValue));

		this.membersTableView.getSelectionModel().selectedIndexProperty()
				.addListener((obs, oldV, newV) -> selectMemberAssignedTerritory());

		this.memberAssignedTerritoryTableView.getSelectionModel().selectedIndexProperty()
				.addListener((obs, oldV, newV) -> selectAssignedTerritory());

		this.memberAssignedTerritoryReturnButton.setOnAction(event -> memberAssignedTerritoryReturn());

		this.territoryReturnButton.setOnAction(event -> assignedTerritoryReturn());
	}

	private void showRegistry(TerritoryObj territoryObj) {

		String title = this.language.getString("territory.tab.registrytitle");
		title = String.format(title, territoryObj.getSpInf7());

		if (!isAlreadyOpen(this.territoryTabPane, title)) {

			try {

				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(Meta.Views.TERRITORY_REGISTRY_FXML_URL);
				AnchorPane layout = (AnchorPane) fxmlLoader.load();

				TerritoryRegistryViewer ctrl = (TerritoryRegistryViewer) fxmlLoader.getController();
				ctrl.setApplication(this.application);
				ctrl.setSettings(this.settings);
				ctrl.setOwnerStage(this.ownerStage);
				ctrl.setOwnerCtrl(this);
				ctrl.setSelectedTerritory(territoryObj);
				ctrl.setTerritoryRegistry(this.territoryRegistry);
				ctrl.setMembersList(this.membersList);

				Tab newTab = new Tab(title, layout);
				newTab.setClosable(true);
				newTab.getStyleClass().add("tab_001");
				newTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.REGISTRY));

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

	private void assignedTerritoryReturn() {

		if (!this.application.getUser().isSpUserSU() && !this.application.getUser().isSpInf26()) {

			final String content = this.application.getSettings().getLanguage()
					.getString("territory.error.nopermission");

			this.application.getAlertBuilder2().error(this.ownerStage, content);

			return;
		}

		if (this.territoryTableView.getSelectionModel().getSelectedIndex() > -1) {

			TerritoryObj territoryObj = this.territoryTableView.getSelectionModel().getSelectedItem();

			if (!territoryObj.isAvailable()) {

				LocalDate returnDate = TerritoryReturnDateDialog.show(this.application, this.ownerStage);
				if (returnDate != null) {

					Member assignedMember = territoryObj.getAssignedMember();
					LocalDate assignedDate = territoryObj.getAssignedDate();

					DateTimeFormatter dtf = DateTimeFormatter
							.ofPattern(this.language.getStringWithNewLine("datepattern"));

					if (assignedDate.compareTo(returnDate) < 0) {

						String header = this.language.getString("territory.confirm.returnterritory");
						String content = this.language.getStringWithNewLine("territory.confirm.returnterritorycontent");

						String territoryName = String.format("%s - %s", territoryObj.getSpInf7(),
								territoryObj.getSpInf8());

						content = String.format(content, assignedMember.getNameStyle1(), territoryName,
								dtf.format(assignedDate), dtf.format(returnDate));
						if (this.application.getAlertBuilder2().confirm(this.ownerStage, header, content)) {

							TerritoryRegistryEntity territoriesEntity = this.territoryRegistry
									.findActualTerritoriesEntity(this.territoryList, assignedMember, territoryObj);

							String waitMessage = this.language.getString("territory.wait.returnsave");

							TaskManager.run(this.application.getAlertBuilder2(), this.ownerStage, waitMessage,
									new TerritoryRegistryEntitySaveReturnTask(this.application.getAlertBuilder2(),
											this.settings, this.ownerStage, this, territoriesEntity, returnDate));

						}

					} else {

						String errorReturnDateFormat = this.language.getStringWithNewLine("territory.error.returndate");

						String content = String.format(errorReturnDateFormat, dtf.format(assignedDate),
								dtf.format(returnDate));

						this.application.getAlertBuilder2().error(this.ownerStage, content);
					}
				}
			} else {
				this.application.getAlertBuilder2().error(this.ownerStage,
						this.language.getString("territory.error.territorynotassigned"));
			}
		} else {
			this.application.getAlertBuilder2().error(this.ownerStage,
					this.language.getString("territory.error.noterritoryselected"));
		}
	}

	private void memberAssignedTerritoryReturn() {

		if (!this.application.getUser().isSpUserSU() && !this.application.getUser().isSpInf26()) {

			final String content = this.application.getSettings().getLanguage()
					.getString("territory.error.nopermission");

			this.application.getAlertBuilder2().error(this.ownerStage, content);

			return;
		}

		if (this.memberAssignedTerritoryTableView.getSelectionModel().getSelectedIndex() > -1) {

			TerritoryObj territoryObj = this.memberAssignedTerritoryTableView.getSelectionModel().getSelectedItem();

			LocalDate returnDate = TerritoryReturnDateDialog.show(this.application, this.ownerStage);
			if (returnDate != null) {

				Member assignedMember = territoryObj.getAssignedMember();
				LocalDate assignedDate = territoryObj.getAssignedDate();

				DateTimeFormatter dtf = DateTimeFormatter.ofPattern(this.language.getStringWithNewLine("datepattern"));

				if (assignedDate.compareTo(returnDate) < 0) {

					String header = this.language.getString("territory.confirm.returnterritory");
					String content = this.language.getStringWithNewLine("territory.confirm.returnterritorycontent");

					String territoryName = String.format("%s - %s", territoryObj.getSpInf7(), territoryObj.getSpInf8());

					content = String.format(content, assignedMember.getNameStyle1(), territoryName,
							dtf.format(assignedDate), dtf.format(returnDate));
					if (this.application.getAlertBuilder2().confirm(this.ownerStage, header, content)) {

						TerritoryRegistryEntity territoriesEntity = this.territoryRegistry
								.findActualTerritoriesEntity(this.territoryList, assignedMember, territoryObj);

						String waitMessage = this.language.getString("territory.wait.returnsave");

						TaskManager.run(this.application.getAlertBuilder2(), this.ownerStage, waitMessage,
								new TerritoryRegistryEntitySaveReturnTask(this.application.getAlertBuilder2(),
										this.settings, this.ownerStage, this, territoriesEntity, returnDate));

					}

				} else {

					String errorReturnDateFormat = this.language.getStringWithNewLine("territory.error.returndate");

					String content = String.format(errorReturnDateFormat, dtf.format(assignedDate),
							dtf.format(returnDate));

					this.application.getAlertBuilder2().error(this.ownerStage, content);
				}
			}

		} else {
			this.application.getAlertBuilder2().error(this.ownerStage,
					this.language.getString("territory.error.noterritoryselected"));
		}
	}

	public void selectMemberAssignedTerritory() {

		if (this.membersTableView.getSelectionModel().getSelectedIndex() > -1) {

			Member publisher = this.membersTableView.getSelectionModel().getSelectedItem();
			ObservableList<TerritoryObj> territoryList = this.territoryRegistry
					.findActualTerritoriesByPublisher(this.territoryList, publisher);

			this.memberAssignedTerritoryTableView.setItems(territoryList);
			Platform.runLater(() -> this.memberAssignedTerritoryTableView.refresh());
		}
	}

	private void assignTerritory() {

		if (!this.application.getUser().isSpUserSU() && !this.application.getUser().isSpInf26()) {

			final String content = this.application.getSettings().getLanguage()
					.getString("territory.error.nopermission");

			this.application.getAlertBuilder2().error(this.ownerStage, content);

			return;
		}

		if (this.territoryTableView.getSelectionModel().getSelectedIndex() > -1) {

			TerritoryObj territoryObj = this.territoryTableView.getSelectionModel().getSelectedItem();
			if (territoryObj.isAvailable()) {

				LocalDate lastEndDate = null;
				ObservableList<TerritoryRegistryEntity> entityList = this.territoryRegistry
						.findTerritoryEntityList(territoryObj.getSpTerritoryID());

				if (!entityList.isEmpty()) {
					lastEndDate = entityList.get(0).getEndDate();
				}

				Member member = TerritoryAssignToMemberDialog.show(this.application, this.ownerStage, this.membersList);
				if (member != null) {

					LocalDate assignDate = TerritoryAssignDateDialog.show(this.application, this.ownerStage,
							territoryObj, member);

					if (assignDate != null) {

						if (lastEndDate != null) {

							if (lastEndDate.compareTo(assignDate) > 0) {

								DateTimeFormatter dtf = DateTimeFormatter
										.ofPattern(this.language.getString("datepattern"));

								String content = this.language
										.getStringWithNewLine("territory.error.assigndatenotvalid");

								content = String.format(content, dtf.format(lastEndDate), dtf.format(assignDate));

								this.application.getAlertBuilder2().error(this.ownerStage, content);

							} else
								assignTerritoryRun(territoryObj, member, assignDate);
						} else
							assignTerritoryRun(territoryObj, member, assignDate);

					} else {
						this.application.getAlertBuilder2().error(this.ownerStage,
								this.language.getString("territory.error.noassigndate"));
					}

				}
//				else {
//					this.application.getAlertBuilder2().error(this.ownerStage,
//							this.language.getString("territory.error.nomembertoassign"));
//				}

			} else {
				this.application.getAlertBuilder2().error(this.ownerStage,
						this.language.getString("territory.error.noterritoryavailable"));
			}

		} else {
			this.application.getAlertBuilder2().error(this.ownerStage,
					this.language.getString("territory.error.noterritoryselected"));
		}
	}

	private void assignTerritoryRun(TerritoryObj territoryObj, Member member, LocalDate assignDate) {

		String header = this.application.getSettings().getLanguage().getString("territory.error.registryentityheader");

		String contentFormat = this.application.getSettings().getLanguage()
				.getStringWithNewLine("territory.error.registryentitycontentformat");

		String territoryName = String.format("%s - %s", territoryObj.getSpInf7(), territoryObj.getSpInf8());
		String memberName = member.getNameStyle1();

		String datePattern = this.application.getSettings().getLanguage().getString("datepattern");
		String date = DateTimeFormatter.ofPattern(datePattern).format(assignDate);

		String content = String.format(contentFormat, territoryName, memberName, date);

		if (this.application.getAlertBuilder2().confirm(this.ownerStage, header, content)) {

			String waitMessage = this.language.getString("territory.wait.assignsave");

			TaskManager.run(this.application.getAlertBuilder2(), this.ownerStage, waitMessage,
					new TerritoryRegistryEntitySaveTask(this.application.getAlertBuilder2(), this.settings,
							this.ownerStage, this, territoryObj, member, assignDate));

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
				clearImage(this.territoryImageView);
				fillImage(item, this.territoryImageView);
			}
		}
	}

	private void selectTerritoryMapsImage() {

		if (this.territoryMapsImagesTableView.getSelectionModel().getSelectedIndex() > -1) {

			File item = this.territoryMapsImagesTableView.getSelectionModel().getSelectedItem();
			if (item.exists()) {
				clearImage(this.territoryMapsImageView);
				fillImage(item, this.territoryMapsImageView);
			}
		}
	}

	private void initImageViewSize(StackPane stackPane, ImageView imageView) {

		double stackPaneWidth = stackPane.getWidth() - 5;
		double stackPaneHeight = stackPane.getHeight() - 5;

		imageView.setPreserveRatio(true);

		imageView.setFitWidth(stackPaneWidth);
		imageView.setFitHeight(stackPaneHeight);
	}

	private void fillImage(File fileImage, ImageView imageView) {

		if (!imageView1) {
			initImageViewSize(this.territoryImageViewStackPane, this.territoryImageView);
			this.imageView1 = true;
		}
		if (!imageView2) {
			initImageViewSize(this.memberAssignedTerritoryImageViewStackPane, this.memberAssignedTerritoryImageView);
			this.imageView2 = true;
		}
		if (!imageView3) {
			initImageViewSize(this.territoryMapsImageViewStackPane, this.territoryMapsImageView);
			this.imageView3 = true;
		}

		if (fileImage.exists()) {
			Image image = new Image(fileImage.toURI().toString());
			imageView.setImage(image);
		}
	}

//	private double setNewWidthSize(double width, double height, double screenWidth, double screenHeight) {
//
//		if (width > height) {
//			return screenWidth;
//		} else {
//			return imageRatioSize(screenHeight, height, width);
//		}
//	}
//
//	private double setNewHeightSize(double width, double height, double screenWidth, double screenHeight) {
//
//		if (width > height) {
//			return imageRatioSize(screenWidth, width, height);
//		} else {
//			return screenHeight;
//		}
//	}
//
//	private double imageRatioSize(double screenSize, double originalSize, double otherSide) {
//
//		double ratio = screenSize * 100 / originalSize;
//		return (otherSide * ratio / 100);
//	}
//
//	private double reverseWidthSize(double newWidth, double newHeight, double stackPaneWidth, double stackPaneHeight) {
//
//		if (newWidth > newHeight) {
//			return imageRatioSize(stackPaneHeight, newHeight, newWidth);
//		} else {
//			return stackPaneWidth;
//		}
//	}
//
//	private double reverseHeightSize(double newWidth, double newHeight, double stackPaneWidth, double stackPaneHeight) {
//
//		if (newWidth > newHeight) {
//			return stackPaneHeight;
//		} else {
//			return imageRatioSize(stackPaneWidth, newWidth, newHeight);
//		}
//	}

	private void clearImage(ImageView imageView) {
		imageView.setImage(null);
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

	private void deleteResourceDirectoryMaps() {

		if (this.territoryMapsTableView.getSelectionModel().getSelectedIndex() > -1) {

			String content = this.application.getSettings().getLanguage()
					.getString("territorymaps.confirm.deleteallresources");
			if (this.application.getAlertBuilder2().confirm(this.ownerStage, content)) {

				TerritoryMap territoryObj = this.territoryMapsTableView.getSelectionModel().getSelectedItem();
				File targetDirectory = territoryObj.buildTargetDirectory();

				try {
					FileUtils.deleteDirectory(targetDirectory);
				} catch (IOException e) {
					this.application.getAlertBuilder2().error(this.ownerStage, e.getMessage());
				}

				selectMaps();
			}

		} else {
			this.application.getAlertBuilder2().error(this.ownerStage,
					this.language.getString("territory.error.noterritoryselected"));
		}
	}

	public void selectAssignedTerritory() {

		if (this.memberAssignedTerritoryTableView.getSelectionModel().getSelectedIndex() > -1) {

			clearImage(this.memberAssignedTerritoryImageView);

			TerritoryObj territoryObj = this.memberAssignedTerritoryTableView.getSelectionModel().getSelectedItem();
			File targetDirectory = territoryObj.buildTargetDirectory();

			String firstResourceTitle = territoryObj.getSpInf12();
			if (!firstResourceTitle.isEmpty()) {
				File firstResource = new File(targetDirectory, firstResourceTitle);
				if (firstResource.exists()) {
					fillImage(firstResource, this.memberAssignedTerritoryImageView);
				}
			}
		}
	}

	public void selectTerritory() {

		if (this.territoryTableView.getSelectionModel().getSelectedIndex() > -1) {

			this.territoryDocs.clear();
			this.territoryImages.clear();
			this.territoryImageView.setImage(null);

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

	public void selectMaps() {

		if (this.territoryMapsTableView.getSelectionModel().getSelectedIndex() > -1) {

			this.territoryMapDocs.clear();
			this.territoryMapImages.clear();
			this.territoryMapsImageView.setImage(null);

			TerritoryMap territoryObj = this.territoryMapsTableView.getSelectionModel().getSelectedItem();
			File targetDirectory = territoryObj.buildTargetDirectory();

			ArrayList<TerritoryResource> resources = territoryObj.getResources();
			for (TerritoryResource territoryResource : resources) {

				File resource = new File(targetDirectory, territoryResource.getResourceName());
				if (resource.exists()) {

					switch (territoryResource.getType()) {
					case 0: // Image

						this.territoryMapImages.add(resource);

						break;

					case 1: // Doc

						this.territoryMapDocs.add(resource);

						break;

					default:
						break;
					}
				}
			}

			Platform.runLater(() -> {
				this.territoryMapsDocsTableView.refresh();
				this.territoryMapsImagesTableView.refresh();
			});

			this.territoryMapsImagesTableView.getSelectionModel().selectFirst();
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

	private void openResourceDirectoryMaps() {

		if (this.territoryMapsTableView.getSelectionModel().getSelectedIndex() > -1) {

			TerritoryMap territoryObj = this.territoryMapsTableView.getSelectionModel().getSelectedItem();
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

	private void downloadTerritoryMapsResources() {

		if (this.territoryMapsTableView.getSelectionModel().getSelectedIndex() > -1) {

			TerritoryMap territoryMap = this.territoryMapsTableView.getSelectionModel().getSelectedItem();

			String header = this.application.getSettings().getLanguage()
					.getString("territorymaps.confirm.downloadresources");

			if (this.application.getAlertBuilder2().confirm(this.ownerStage, header, territoryMap.getSpInf1())) {

				String waitMessage = this.language.getString("territory.wait.download");

				TaskManager.run(this.application.getAlertBuilder2(), this.ownerStage, waitMessage,
						new TerritoryMapsDownloadTask(this.application, this.ownerStage, this, territoryMap));

			}
		}
	}

	private void downloadAllTerritoryResources() {

		String content = this.application.getSettings().getLanguage()
				.getString("territory.confirm.downloadallresources");

		if (this.application.getAlertBuilder2().confirm(this.ownerStage, content)) {

			String waitMessage = this.language.getString("territory.wait.download");

			TaskManager.run(this.application.getAlertBuilder2(), this.ownerStage, waitMessage,
					new TerritoryDownloadAllTask(this.application, this.ownerStage, this, this.territoryList));

		}
	}

	private void downloadAllTerritoryMapsResources() {

		String content = this.application.getSettings().getLanguage()
				.getString("territorymaps.confirm.downloadallresources");

		if (this.application.getAlertBuilder2().confirm(this.ownerStage, content)) {

			String waitMessage = this.language.getString("territory.wait.download");

			TaskManager.run(this.application.getAlertBuilder2(), this.ownerStage, waitMessage,
					new TerritoryMapsDownloadAllTask(this.application, this.ownerStage, this, this.territoryMapList));

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

	private void openTerritoryMapsViewer() {

		if (this.territoryMapsTableView.getSelectionModel().getSelectedIndex() > -1) {

			TerritoryMap territoryMap = this.territoryMapsTableView.getSelectionModel().getSelectedItem();

			String spInf2 = territoryMap.getSpInf2();
			String spInf3 = territoryMap.getSpInf3();

			if (!(spInf2.isEmpty() || spInf3.isEmpty())) {

				String dbUrl = this.application.getSettings().getDatabaseUrl();
				int indexOf = dbUrl.indexOf("exchange.php");
				if (indexOf > -1) {

					String subDbUrl = dbUrl.substring(0, indexOf);
					String link = subDbUrl + "monitor/mapsviewer.php?mid=%s&amp;lang=%s";
					link = String.format(link, spInf3, this.language.getString("sp.monitor.language"));

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
						this.language.getString("territorymaps.error.nomymapsidorviewerid"));
			}

		} else {
			this.application.getAlertBuilder2().error(this.ownerStage,
					this.language.getString("territorymaps.error.noterritoryselected"));
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

	private void openTerritoryMapsViewerURL() {

		if (this.territoryMapsTableView.getSelectionModel().getSelectedIndex() > -1) {

			TerritoryMap territoryMap = this.territoryMapsTableView.getSelectionModel().getSelectedItem();

			String spInf2 = territoryMap.getSpInf2();
			String spInf3 = territoryMap.getSpInf3();

			if (!(spInf2.isEmpty() || spInf3.isEmpty())) {

				String dbUrl = this.application.getSettings().getDatabaseUrl();
				int indexOf = dbUrl.indexOf("exchange.php");
				if (indexOf > -1) {

					String subDbUrl = dbUrl.substring(0, indexOf);
					String link = subDbUrl + "monitor/mapsviewer.php?mid=%s&amp;lang=%s";
					link = String.format(link, spInf3, this.language.getString("sp.monitor.language"));

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

	private void updateFilterTerritoryMaps(String newValue) {

		if (newValue.isEmpty()) {
			this.territoryMapsTableView.setItems(this.territoryMapList);
		} else {
			ObservableList<TerritoryMap> filteredMemberList = buildListTerritoryMaps(newValue);
			this.territoryMapsTableView.setItems(filteredMemberList);
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

	private ObservableList<TerritoryMap> buildListTerritoryMaps(String filter) {

		ObservableList<TerritoryMap> list = FXCollections.observableArrayList();

		StreamSupport.stream(this.territoryMapList.spliterator(), false)
				.filter(obj -> matchFilterTerritoryMaps(obj, filter)).forEach(obj -> list.add(obj));

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

	private boolean matchFilterTerritoryMaps(TerritoryMap obj, String match) {

		String filter = match.toLowerCase();

		return obj.getSpInf1().toLowerCase().contains(filter);
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

	private void newTerritoryMaps() {

		if (!this.application.getUser().isSpUserSU() && !this.application.getUser().isSpInf25()) {

			final String content = this.application.getSettings().getLanguage()
					.getString("territory.error.nopermission");

			this.application.getAlertBuilder2().error(this.ownerStage, content);

			return;
		}

		if (!isAlreadyOpen(this.territoryMapsTabPane, "")) {

			try {

				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(Meta.Views.TERRITORY_MAPS_EDITOR_FXML_URL);
				AnchorPane layout = (AnchorPane) fxmlLoader.load();

				TerritoryMapsEditor ctrl = (TerritoryMapsEditor) fxmlLoader.getController();
				ctrl.setApplication(this.application);
				ctrl.setSettings(this.settings);
				ctrl.setOwnerStage(this.ownerStage);
				ctrl.setOwnerCtrl(this);

				Tab newTab = new Tab("", layout);
				newTab.setClosable(true);
				newTab.getStyleClass().add("tab_001");
				newTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.PLUS));

				ctrl.setParentTabPane(this.territoryMapsTabPane);
//				ctrl.setMembersTab(membersTab);
				ctrl.setNewTab(newTab);
//				ctrl.setMembersList(this.membersList);

				this.territoryMapsTabPane.getTabs().add(newTab);
				this.territoryMapsTabPane.getSelectionModel().select(newTab);

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

	private void viewTerritory() {

		if (this.territoryTableView.getSelectionModel().getSelectedIndex() > -1)
			viewTerritory(this.territoryTableView.getSelectionModel().getSelectedItem());
	}

	private void viewTerritory(TerritoryObj territoryObj) {

		if (!isAlreadyOpen(this.territoryTabPane, territoryObj.getSpInf7())) {

			try {

				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(Meta.Views.TERRITORY_VIEWER_FXML_URL);
				AnchorPane layout = (AnchorPane) fxmlLoader.load();

				TerritoryViewer ctrl = (TerritoryViewer) fxmlLoader.getController();
				ctrl.setApplication(this.application);
				ctrl.setSettings(this.settings);
				ctrl.setOwnerStage(this.ownerStage);
				ctrl.setOwnerCtrl(this);
				ctrl.setSelectedTerritory(territoryObj);

				Tab newTab = new Tab(territoryObj.getSpInf7(), layout);
				newTab.setClosable(true);
				newTab.getStyleClass().add("tab_001");
				newTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.TERRITORY_VIEW));

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

	private void editTerritoryMaps() {

		if (this.territoryMapsTableView.getSelectionModel().getSelectedIndex() > -1)
			editTerritoryMaps(this.territoryMapsTableView.getSelectionModel().getSelectedItem());
	}

	private void editTerritoryMaps(TerritoryMap territoryMap) {

		if (!this.application.getUser().isSpUserSU() && !this.application.getUser().isSpInf25()) {

			final String content = this.application.getSettings().getLanguage()
					.getString("territory.error.nopermission");

			this.application.getAlertBuilder2().error(this.ownerStage, content);

			return;
		}

		if (!isAlreadyOpen(this.territoryMapsTabPane, territoryMap.getSpInf1())) {

			try {

				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(Meta.Views.TERRITORY_MAPS_EDITOR_FXML_URL);
				AnchorPane layout = (AnchorPane) fxmlLoader.load();

				TerritoryMapsEditor ctrl = (TerritoryMapsEditor) fxmlLoader.getController();
				ctrl.setApplication(this.application);
				ctrl.setSettings(this.settings);
				ctrl.setOwnerStage(this.ownerStage);
				ctrl.setOwnerCtrl(this);
				ctrl.setSelectedTerritory(territoryMap);

				Tab newTab = new Tab(territoryMap.getSpInf1(), layout);
				newTab.setClosable(true);
				newTab.getStyleClass().add("tab_001");
				newTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.MAPS_EDIT));

				ctrl.setParentTabPane(this.territoryMapsTabPane);
				ctrl.setNewTab(newTab);

				this.territoryMapsTabPane.getTabs().add(newTab);
				this.territoryMapsTabPane.getSelectionModel().select(newTab);

				ctrl.objectInitialize();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	private void viewTerritoryMaps() {

		if (this.territoryMapsTableView.getSelectionModel().getSelectedIndex() > -1)
			viewTerritoryMaps(this.territoryMapsTableView.getSelectionModel().getSelectedItem());
	}

	private void viewTerritoryMaps(TerritoryMap territoryMap) {

		if (!isAlreadyOpen(this.territoryMapsTabPane, territoryMap.getSpInf1())) {

			try {

				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(Meta.Views.TERRITORY_MAPS_VIEWER_FXML_URL);
				AnchorPane layout = (AnchorPane) fxmlLoader.load();

				TerritoryMapsViewer ctrl = (TerritoryMapsViewer) fxmlLoader.getController();
				ctrl.setApplication(this.application);
				ctrl.setSettings(this.settings);
				ctrl.setOwnerStage(this.ownerStage);
				ctrl.setOwnerCtrl(this);
				ctrl.setSelectedTerritory(territoryMap);

				Tab newTab = new Tab(territoryMap.getSpInf1(), layout);
				newTab.setClosable(true);
				newTab.getStyleClass().add("tab_001");
				newTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.SEARCH));

				ctrl.setParentTabPane(this.territoryMapsTabPane);
				ctrl.setNewTab(newTab);

				this.territoryMapsTabPane.getTabs().add(newTab);
				this.territoryMapsTabPane.getSelectionModel().select(newTab);

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

	private void removeTerritoryMaps() {

		if (!this.application.getUser().isSpUserSU() && !this.application.getUser().isSpInf25()) {

			final String content = this.application.getSettings().getLanguage()
					.getString("territory.error.nopermission");

			this.application.getAlertBuilder2().error(this.ownerStage, content);

			return;
		}

		if (this.territoryMapsTableView.getSelectionModel().getSelectedIndex() > -1) {

			TerritoryMap territoryMap = this.territoryMapsTableView.getSelectionModel().getSelectedItem();

			String territoryName = territoryMap.getSpInf1();

			String header = this.application.getSettings().getLanguage()
					.getString("territorymaps.confirm.territoryremove");
			if (this.application.getAlertBuilder2().confirm(this.ownerStage, header, territoryName)) {

				int id = territoryMap.getSpTerritoryID();

				String waitMessage = this.language.getString("territorymaps.wait.delete");

				TaskManager.run(this.application.getAlertBuilder2(), this.ownerStage, waitMessage,
						new TerritoryMapsDeleteTask(this.application.getAlertBuilder2(), this.settings, this.ownerStage,
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

		EnumPrintLayouts selectedLayout = PrintLayout.dialogPrintLayout(this.ownerStage, this.language, null,
				EnumPrintLayouts.MODULE_S13);

		if (selectedLayout != null) {

			switch (selectedLayout) {

			case MODULE_S13:

				printModuleS13();

				break;

			default:
				break;
			}
		}
	}

	private void printModuleS13() {

		String s13Path = this.settings.getModuleS13Decrypted();
		if (s13Path.isEmpty()) {

			this.application.getAlertBuilder2().error(this.ownerStage,
					this.application.getSettings().getLanguage().getString("territory.error.nomodules13"));
			return;
		}

		File fileS13 = new File(s13Path);
		if (!fileS13.exists()) {

			this.application.getAlertBuilder2().error(this.ownerStage,
					this.application.getSettings().getLanguage().getString("territory.error.nomodules13"));
			return;

		}

		DirectoryChooser directoryChooser = new DirectoryChooser();
		directoryChooser.setTitle(this.application.getSettings().getLanguage().getString("territory.chooser.saves13"));
		File directory = directoryChooser.showDialog(this.ownerStage);
		if (directory == null) {
			return;
		}

		if (!directory.exists()) {
			this.application.getAlertBuilder2().error(this.ownerStage,
					this.application.getSettings().getLanguage().getString("territory.error.nosavedirectory"));
			return;
		}

		File saveDirectory = new File(directory,
				String.format("S13_export%s", DateTimeFormatter.ofPattern("yyyyMMddHHmm").format(LocalDateTime.now())));
		if (!saveDirectory.exists()) {
			saveDirectory.mkdirs();
		}

		ArrayList<ArrayList<TerritoryModul>> modules = this.territoryRegistry.build(this.territoryList,
				this.membersList, DateTimeFormatter.ofPattern(this.language.getString("datepattern")));

		if (this.application.getAlertBuilder2().confirm(this.ownerStage,
				this.application.getSettings().getLanguage().getString("territory.confirm.saves13"))) {

			try {

				for (ArrayList<TerritoryModul> module : modules) {

					PDDocument doc = PDDocument.load(fileS13);
					PDDocumentCatalog docCatalog = doc.getDocumentCatalog();
					PDAcroForm acroForm = docCatalog.getAcroForm();

					if (acroForm != null) {

						String territories = "";

						for (TerritoryModul territoryModul : module) {

							HashMap<String, String> infos = territoryModul.getInfos();
							Set<String> keySet = infos.keySet();
							for (String key : keySet) {

								String value = infos.get(key);
								if (key.startsWith("Terr_")) {
									if (!territories.isEmpty())
										territories += "-";
									territories += value;
								}

								PDField field = (PDField) acroForm.getField(key);
								field.setValue(value);
							}
						}

						File export = new File(saveDirectory, String.format("S-13 (%s).pdf", territories));
						doc.save(export);
						doc.close();
					}
				}

				CommonUtils.open(saveDirectory);

				this.application.getAlertBuilder2().information(this.ownerStage,
						this.application.getSettings().getLanguage().getString("territory.information.saves13done"));

			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	public void territoryTableViewRefresh() {
		Platform.runLater(() -> this.territoryTableView.refresh());
	}

	public void resetSelectionAllTable() {

		this.territoryImages.clear();
		this.territoryImagesTableView.refresh();

		this.territoryDocs.clear();
		this.territoryDocsTableView.refresh();

		this.territoryImageView.setImage(null);
		this.memberAssignedTerritoryImageView.setImage(null);

		this.membersTableView.getSelectionModel().clearSelection();

		this.memberAssignedTerritoryTableView.setItems(null);
		this.memberAssignedTerritoryTableView.refresh();
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

	public TabPane getTerritoryMapsTabPane() {
		return territoryMapsTabPane;
	}

	public void setTerritoryMapsTabPane(TabPane territoryMapsTabPane) {
		this.territoryMapsTabPane = territoryMapsTabPane;
	}

}
