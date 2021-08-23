package com.sm.net.sp.view.home.user.menu.territory;

import java.time.LocalDate;
import java.util.HashMap;

import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.model.Member;
import com.sm.net.sp.model.TerritoryObj;
import com.sm.net.sp.model.TerritoryRegistry;
import com.sm.net.sp.model.TerritoryRegistryEntity;
import com.sm.net.sp.settings.Settings;
import com.sm.net.sp.view.SupportPlannerView;
import com.sm.net.sp.view.home.user.menu.territory.task.TerritoryRegistryEntityDeleteTask;
import com.smnet.core.task.TaskManager;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;

public class TerritoryRegistryViewer {

	@FXML
	private TableView<TerritoryRegistryEntity> tableView;
	@FXML
	private TableColumn<TerritoryRegistryEntity, LocalDate> startDateTableColumn;
	@FXML
	private TableColumn<TerritoryRegistryEntity, String> publisherTableColumn;
	@FXML
	private TableColumn<TerritoryRegistryEntity, LocalDate> endDateTableColumn;

	@FXML
	private Button deleteButton;

	private Settings settings;
	private Language language;
	private Stage ownerStage;
	private TabPane parentTabPane;
	private Tab newTab;
	private Tab membersTab;
	private Territory ownerCtrl;

	private TerritoryObj selectedTerritory;
	private SupportPlannerView application;

	private TerritoryRegistry territoryRegistry;
	private ObservableList<Member> membersList;

	private HashMap<String, String> configs;

	private ObservableList<TerritoryRegistryEntity> entityList;

	@FXML
	private void initialize() {

		styleClasses();

		this.startDateTableColumn.setCellValueFactory(
				cellData -> new SimpleObjectProperty<LocalDate>(cellData.getValue().getStartDate()));
		this.endDateTableColumn
				.setCellValueFactory(cellData -> new SimpleObjectProperty<LocalDate>(cellData.getValue().getEndDate()));
		this.publisherTableColumn.setCellValueFactory(
				cellData -> new SimpleStringProperty(cellData.getValue().getPublisher().getNameStyle1()));
	}

	private void styleClasses() {

		this.tableView.getStyleClass().add("table_view_001");

		this.startDateTableColumn.getStyleClass().add("table_column_002");
		this.endDateTableColumn.getStyleClass().add("table_column_002");

		this.deleteButton.getStyleClass().add("button_image_001");
	}

	public void objectInitialize() {

		viewUpdate();

		initValue();
		listeners();
	}

	private void initValue() {

		if (this.selectedTerritory != null) {

			this.entityList = this.territoryRegistry.findTerritoryEntityList(this.selectedTerritory.getSpTerritoryID());

			this.entityList.forEach(entity -> entity.updatePublisher(this.membersList));

			this.tableView.setItems(this.entityList);

		}
	}

	private void listeners() {

		this.deleteButton.setOnAction(event -> delete());

	}

	private void delete() {

		if (!this.application.getUser().isSpUserSU() && !this.application.getUser().isSpInf26()) {

			final String content = this.application.getSettings().getLanguage()
					.getString("territory.error.nopermission");

			this.application.getAlertBuilder2().error(this.ownerStage, content);

			return;
		}

		if (this.tableView.getSelectionModel().getSelectedIndex() > -1) {

			TerritoryRegistryEntity selectedItem = this.tableView.getSelectionModel().getSelectedItem();
			if (this.isLastEntity(selectedItem)) {

				String content = this.application.getSettings().getLanguage()
						.getString("territoryregistry.confirm.entitydelete");
				if (this.application.getAlertBuilder2().confirm(this.ownerStage, content)) {

					int id = selectedItem.getID();

					String waitMessage = this.language.getString("territoryregistry.wait.delete");

					TaskManager.run(this.application.getAlertBuilder2(), this.ownerStage, waitMessage,
							new TerritoryRegistryEntityDeleteTask(this.application.getAlertBuilder2(), this.settings,
									this.ownerStage, this, id));

				}

			} else {

				final String content = this.application.getSettings().getLanguage()
						.getString("territoryregistry.error.nolast");

				this.application.getAlertBuilder2().error(this.ownerStage, content);
			}

		} else {

			final String content = this.application.getSettings().getLanguage()
					.getString("territoryregistry.error.noselection");

			this.application.getAlertBuilder2().error(this.ownerStage, content);
		}
	}

	private boolean isLastEntity(TerritoryRegistryEntity selectedEntity) {

		return selectedEntity.getID() == this.getLastEntity().getID();
	}

	private TerritoryRegistryEntity getLastEntity() {

		TerritoryRegistryEntity lastEntity = null;
		for (TerritoryRegistryEntity entity : this.entityList) {

			if (lastEntity == null) {
				lastEntity = entity;
				continue;
			}

			if (entity.toTimestamp() > lastEntity.toTimestamp()) {
				lastEntity = entity;
			}
		}
		return lastEntity;
	}

	private void viewUpdate() {

		this.language = this.settings.getLanguage();

		this.startDateTableColumn.setText(this.language.getString("territoryregistry.tablecolumn.startdate"));
		this.startDateTableColumn.setMinWidth(250);
		this.startDateTableColumn.setMaxWidth(250);
		this.endDateTableColumn.setText(this.language.getString("territoryregistry.tablecolumn.enddate"));
		this.endDateTableColumn.setMinWidth(250);
		this.endDateTableColumn.setMaxWidth(250);
		this.publisherTableColumn.setText(this.language.getString("territoryregistry.tablecolumn.publisher"));

		Tooltip deleteTooltip = new Tooltip(this.language.getString("territoryregistry.button.tooltip.delete"));
		deleteTooltip.getStyleClass().add("tooltip_001");
		this.deleteButton.setTooltip(deleteTooltip);
		this.deleteButton.setText("");
		this.deleteButton.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.DELETE));

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

	public TabPane getParentTabPane() {
		return parentTabPane;
	}

	public void setParentTabPane(TabPane parentTabPane) {
		this.parentTabPane = parentTabPane;
	}

	public Tab getNewTab() {
		return newTab;
	}

	public void setNewTab(Tab newTab) {
		this.newTab = newTab;
	}

	public Territory getOwnerCtrl() {
		return ownerCtrl;
	}

	public void setOwnerCtrl(Territory ownerCtrl) {
		this.ownerCtrl = ownerCtrl;
	}

	public Tab getMembersTab() {
		return membersTab;
	}

	public void setMembersTab(Tab membersTab) {
		this.membersTab = membersTab;
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

	public TerritoryObj getSelectedTerritory() {
		return selectedTerritory;
	}

	public void setSelectedTerritory(TerritoryObj selectedTerritory) {
		this.selectedTerritory = selectedTerritory;
	}

	public TerritoryRegistry getTerritoryRegistry() {
		return territoryRegistry;
	}

	public void setTerritoryRegistry(TerritoryRegistry territoryRegistry) {
		this.territoryRegistry = territoryRegistry;
	}

	public ObservableList<Member> getMembersList() {
		return membersList;
	}

	public void setMembersList(ObservableList<Member> membersList) {
		this.membersList = membersList;
	}
}
