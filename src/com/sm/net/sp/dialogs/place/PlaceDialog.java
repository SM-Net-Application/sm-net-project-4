package com.sm.net.sp.dialogs.place;

import java.io.IOException;
import java.util.Optional;

import com.sm.net.sp.Meta;
import com.sm.net.sp.model.EnumPlaceType;
import com.sm.net.sp.model.Place;
import com.sm.net.sp.view.SupportPlannerView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class PlaceDialog {

	@FXML
	private ListView<Place> listView;

	private SupportPlannerView application;
	private Stage ownerStage;
	private ObservableList<Place> placeList;

	private EnumPlaceType selectedType;

	public static Place show(SupportPlannerView application, Stage ownerStage, ObservableList<Place> placeList,
			EnumPlaceType selectedType) {

		try {

			Dialog<Place> dialog = new Dialog<>();
			dialog.setTitle(application.getSettings().getLanguage().getString("meetings.dialog.place"));

			DialogPane dialogPane = dialog.getDialogPane();

			Scene scene = dialogPane.getScene();
			scene.getStylesheets().add(Meta.Themes.SUPPORTPLANNER_THEME);

			dialogPane.getStyleClass().add("dialog_001");
			dialogPane.setMinHeight(Region.USE_PREF_SIZE);
			dialogPane.getButtonTypes().add(ButtonType.OK);

			Stage stage = (Stage) scene.getWindow();

			stage.setTitle(Meta.Application.getFullTitle());
			stage.getIcons().add(Meta.Resources.getImageApplicationIcon());

			stage.initOwner(ownerStage);

			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(PlaceDialog.class.getResource("PlaceDialog.fxml"));
			AnchorPane content = fxmlLoader.load();

			PlaceDialog ctrl = fxmlLoader.getController();
			ctrl.setApplication(application);
			ctrl.setOwnerStage(ownerStage);
			ctrl.setPlaceList(placeList);
			ctrl.setSelectedType(selectedType);

			ctrl.init();

			dialogPane.setContent(content);
			dialog.setResultConverter(param -> resultConverter(param, ctrl));

			Optional<Place> result = dialog.showAndWait();

			if (result.isPresent())
				return result.get();

		} catch (IOException e) {
			application.getAlertBuilder().error(ownerStage, e.getMessage()).show();
		}

		return null;
	}

	private static Place resultConverter(ButtonType param, PlaceDialog ctrl) {

		if (param != null)
			if (param.getButtonData() == ButtonBar.ButtonData.OK_DONE)
				return ctrl.getSelected();

		return null;
	}

	private Place getSelected() {
		return this.listView.getSelectionModel().getSelectedItem();
	}

	@FXML
	private void initialize() {

		this.listView.getStyleClass().add("list_view_001");
		this.listView.setMinWidth(750);
	}

	@FXML
	public void init() {

		this.listView.setCellFactory(param -> new PlaceDialogListCell());
		initData();
	}

	private void initData() {

		ObservableList<Place> list = FXCollections.observableArrayList();

		for (Place place : this.placeList)
			if (place.getType().get() == this.selectedType || place.getType().get() == EnumPlaceType.OTHER)
				list.add(place);

		list.sort((o1, o2) -> {

			if (o1 == null)
				return 1;

			if (o2 == null)
				return -1;

			if (o1.getType().get().getId() < o2.getType().get().getId())
				return -1;

			if (o1.getType().get().getId() > o2.getType().get().getId())
				return 1;

			return 0;
		});

		this.listView.setItems(list);
	}

	public ListView<Place> getListView() {
		return listView;
	}

	public void setListView(ListView<Place> listView) {
		this.listView = listView;
	}

	public SupportPlannerView getApplication() {
		return application;
	}

	public void setApplication(SupportPlannerView application) {
		this.application = application;
	}

	public Stage getOwnerStage() {
		return ownerStage;
	}

	public void setOwnerStage(Stage ownerStage) {
		this.ownerStage = ownerStage;
	}

	public ObservableList<Place> getPlaceList() {
		return placeList;
	}

	public void setPlaceList(ObservableList<Place> placeList) {
		this.placeList = placeList;
	}

	public EnumPlaceType getSelectedType() {
		return selectedType;
	}

	public void setSelectedType(EnumPlaceType selectedType) {
		this.selectedType = selectedType;
	}
}
