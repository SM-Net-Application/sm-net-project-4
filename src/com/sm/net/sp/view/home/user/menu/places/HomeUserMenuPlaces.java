package com.sm.net.sp.view.home.user.menu.places;

import java.io.IOException;

import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.model.Place;
import com.sm.net.sp.settings.Settings;
import com.sm.net.sp.view.SupportPlannerView;
import com.sm.net.sp.view.home.user.menu.places.task.PlaceDeleteTask;
import com.sm.net.sp.view.home.user.menu.places.task.PlaceLoadTask;
import com.smnet.core.task.TaskManager;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class HomeUserMenuPlaces {

	@FXML
	private ImageView headerImageView;
	@FXML
	private Label headerLabel;
	@FXML
	private TableView<Place> placesTableView;
	@FXML
	private TableColumn<Place, String> typeTableColumn;
	@FXML
	private TableColumn<Place, String> descrTableColumn;
	@FXML
	private TableColumn<Place, String> streetTableColumn;
	@FXML
	private TableColumn<Place, String> numTableColumn;
	@FXML
	private TableColumn<Place, String> postCodeTableColumn;
	@FXML
	private TableColumn<Place, String> cityTableColumn;
	@FXML
	private TableColumn<Place, String> countyTableColumn;
	@FXML
	private TableColumn<Place, String> countryTableColumn;
	@FXML
	private TableColumn<Place, String> coordTableColumn;
	@FXML
	private TableColumn<Place, Boolean> defaultTableColumn;
	@FXML
	private Button addButton;
	@FXML
	private Button deleteButton;

	private Settings settings;
	private Language language;
	private Stage stageSupportPlannerView;
	private SupportPlannerView application;

	@FXML
	private void initialize() {
		styleClasses();
		tableColumnsCells();
	}

	private void tableColumnsCells() {

		this.typeTableColumn.setCellFactory(c -> tableCellForEnumText());
		this.typeTableColumn.setCellValueFactory(cellData -> cellData.getValue().getType().get().getTextKey());

		this.descrTableColumn.setCellValueFactory(cellData -> cellData.getValue().getDescr());
		this.streetTableColumn.setCellValueFactory(cellData -> cellData.getValue().getStreet());
		this.numTableColumn.setCellValueFactory(cellData -> cellData.getValue().getNum());
		this.postCodeTableColumn.setCellValueFactory(cellData -> cellData.getValue().getPostCode());
		this.cityTableColumn.setCellValueFactory(cellData -> cellData.getValue().getCity());
		this.countyTableColumn.setCellValueFactory(cellData -> cellData.getValue().getCounty());
		this.countryTableColumn.setCellValueFactory(cellData -> cellData.getValue().getCountry());
		this.coordTableColumn.setCellValueFactory(cellData -> cellData.getValue().getCoord());

		this.defaultTableColumn.setCellFactory(CheckBoxTableCell.forTableColumn(this.defaultTableColumn));
		this.defaultTableColumn.setCellValueFactory(cellData -> cellData.getValue().getDef());

	}

	private TableCell<Place, String> tableCellForEnumText() {

		return new TableCell<Place, String>() {
			@Override
			protected void updateItem(String item, boolean empty) {
				super.updateItem(item, empty);

				if (empty)
					setText(null);
				else
					setText(language.getString(item));
			}
		};
	}

	private void styleClasses() {

		this.headerImageView.setFitWidth(50);
		this.headerImageView.setFitHeight(50);
		this.headerImageView.setImage(Meta.Resources.getImageLogo(Meta.Resources.PLACES, 50, 50));

		this.headerLabel.getStyleClass().add("label_header_001");

		this.placesTableView.getStyleClass().add("table_view_001");

		this.addButton.getStyleClass().add("button_image_001");
		this.deleteButton.getStyleClass().add("button_image_001");
	}

	public void objectInitialize() {

		this.language = this.settings.getLanguage();

		listeners();
		viewUpdate();
		loadList();
	}

	private void viewUpdate() {

		this.headerLabel.setText(this.language.getString("sp.menu.places"));

		this.typeTableColumn.setText(this.language.getString("places.table.column.type"));
		this.descrTableColumn.setText(this.language.getString("places.table.column.descr"));
		this.streetTableColumn.setText(this.language.getString("places.table.column.street"));
		this.numTableColumn.setText(this.language.getString("places.table.column.num"));
		this.postCodeTableColumn.setText(this.language.getString("places.table.column.postCode"));
		this.cityTableColumn.setText(this.language.getString("places.table.column.city"));
		this.countyTableColumn.setText(this.language.getString("places.table.column.county"));
		this.countryTableColumn.setText(this.language.getString("places.table.column.country"));
		this.coordTableColumn.setText(this.language.getString("places.table.column.coord"));
		this.defaultTableColumn.setText(this.language.getString("places.table.column.default"));

		Tooltip addTooltip = new Tooltip(this.language.getString("places.tooltip.add"));
		addTooltip.getStyleClass().add("tooltip_001");
		this.addButton.setTooltip(addTooltip);
		this.addButton.setText(null);
		this.addButton.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.ADD));

		Tooltip deleteTooltip = new Tooltip(this.language.getString("places.tooltip.delete"));
		deleteTooltip.getStyleClass().add("tooltip_001");
		this.deleteButton.setTooltip(deleteTooltip);
		this.deleteButton.setText(null);
		this.deleteButton.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.REMOVE));
	}

	public void loadList() {

		String waitMessage = this.language.getString("places.wait.load");

		TaskManager.run(this.application.getAlertBuilder2(), this.stageSupportPlannerView, waitMessage,
				new PlaceLoadTask(this.application.getAlertBuilder2(), this.settings, this.stageSupportPlannerView,
						this, this.settings.getDatabaseSecretKey()));

	}

	private void listeners() {
		listenerUserAddButton();
		listenerUserDeleteButton();
	}

	private void listenerUserDeleteButton() {

		this.deleteButton.setOnAction(event -> {

			if (this.placesTableView.getSelectionModel().getSelectedIndex() > -1) {

				Place item = this.placesTableView.getSelectionModel().getSelectedItem();

				if (this.application.getAlertBuilder2().confirm(this.stageSupportPlannerView,
						this.language.getString("places.delete.confirm"), item.getDescr().get())) {

					String waitMessage = this.language.getString("places.wait.delete");

					TaskManager.run(this.application.getAlertBuilder2(), this.stageSupportPlannerView, waitMessage,
							new PlaceDeleteTask(this.application.getAlertBuilder2(), this.settings,
									this.stageSupportPlannerView, item, this));
				}
			}

		});
	}

	private void listenerUserAddButton() {

		addButton.setOnAction(event -> {

			try {

				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(Meta.Views.HOME_USER_MENU_PLACES_ADD);
				AnchorPane layout = (AnchorPane) fxmlLoader.load();

				PlacesAdd ctrl = (PlacesAdd) fxmlLoader.getController();
				ctrl.setSettings(this.settings);
				ctrl.setOwnerStage(this.stageSupportPlannerView);
				ctrl.setOwnerCtrl(this);
				ctrl.setApplication(this.application);
				ctrl.objectInitialize();

				Scene scene = new Scene(layout);
				scene.getStylesheets().add(Meta.Themes.SUPPORTPLANNER_THEME);

				Stage stage = new Stage();
				stage.setScene(scene);
				stage.setTitle(Meta.Application.getFullTitle());
				stage.getIcons().add(Meta.Resources.getImageApplicationIcon());

				stage.setResizable(false);

				stage.initModality(Modality.WINDOW_MODAL);
				stage.initOwner(this.stageSupportPlannerView);

				ctrl.setThisStage(stage);
				stage.show();

			} catch (IOException e) {
				e.printStackTrace();
			}

		});
	}

	public Settings getSettings() {
		return settings;
	}

	public void setSettings(Settings settings) {
		this.settings = settings;
	}

	public Stage getStageSupportPlannerView() {
		return stageSupportPlannerView;
	}

	public void setStageSupportPlannerView(Stage stageSupportPlannerView) {
		this.stageSupportPlannerView = stageSupportPlannerView;
	}

	public SupportPlannerView getApplication() {
		return application;
	}

	public void setApplication(SupportPlannerView application) {
		this.application = application;
	}

	public ImageView getHeaderImageView() {
		return headerImageView;
	}

	public void setHeaderImageView(ImageView headerImageView) {
		this.headerImageView = headerImageView;
	}

	public Label getHeaderLabel() {
		return headerLabel;
	}

	public void setHeaderLabel(Label headerLabel) {
		this.headerLabel = headerLabel;
	}

	public Button getAddButton() {
		return addButton;
	}

	public void setAddButton(Button addButton) {
		this.addButton = addButton;
	}

	public Button getDeleteButton() {
		return deleteButton;
	}

	public void setDeleteButton(Button deleteButton) {
		this.deleteButton = deleteButton;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public TableView<Place> getPlacesTableView() {
		return placesTableView;
	}

	public void setPlacesTableView(TableView<Place> placesTableView) {
		this.placesTableView = placesTableView;
	}

	public TableColumn<Place, String> getTypeTableColumn() {
		return typeTableColumn;
	}

	public void setTypeTableColumn(TableColumn<Place, String> typeTableColumn) {
		this.typeTableColumn = typeTableColumn;
	}

	public TableColumn<Place, String> getDescrTableColumn() {
		return descrTableColumn;
	}

	public void setDescrTableColumn(TableColumn<Place, String> descrTableColumn) {
		this.descrTableColumn = descrTableColumn;
	}

	public TableColumn<Place, String> getStreetTableColumn() {
		return streetTableColumn;
	}

	public void setStreetTableColumn(TableColumn<Place, String> streetTableColumn) {
		this.streetTableColumn = streetTableColumn;
	}

	public TableColumn<Place, String> getNumTableColumn() {
		return numTableColumn;
	}

	public void setNumTableColumn(TableColumn<Place, String> numTableColumn) {
		this.numTableColumn = numTableColumn;
	}

	public TableColumn<Place, String> getPostCodeTableColumn() {
		return postCodeTableColumn;
	}

	public void setPostCodeTableColumn(TableColumn<Place, String> postCodeTableColumn) {
		this.postCodeTableColumn = postCodeTableColumn;
	}

	public TableColumn<Place, String> getCityTableColumn() {
		return cityTableColumn;
	}

	public void setCityTableColumn(TableColumn<Place, String> cityTableColumn) {
		this.cityTableColumn = cityTableColumn;
	}

	public TableColumn<Place, String> getCountyTableColumn() {
		return countyTableColumn;
	}

	public void setCountyTableColumn(TableColumn<Place, String> countyTableColumn) {
		this.countyTableColumn = countyTableColumn;
	}

	public TableColumn<Place, String> getCountryTableColumn() {
		return countryTableColumn;
	}

	public void setCountryTableColumn(TableColumn<Place, String> countryTableColumn) {
		this.countryTableColumn = countryTableColumn;
	}

	public TableColumn<Place, String> getCoordTableColumn() {
		return coordTableColumn;
	}

	public void setCoordTableColumn(TableColumn<Place, String> coordTableColumn) {
		this.coordTableColumn = coordTableColumn;
	}

	public TableColumn<Place, Boolean> getDefaultTableColumn() {
		return defaultTableColumn;
	}

	public void setDefaultTableColumn(TableColumn<Place, Boolean> defaultTableColumn) {
		this.defaultTableColumn = defaultTableColumn;
	}
}
