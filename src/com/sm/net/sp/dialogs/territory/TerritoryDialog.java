package com.sm.net.sp.dialogs.territory;

import java.io.IOException;

import com.sm.net.sp.Meta;
import com.sm.net.sp.view.SupportPlannerView;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class TerritoryDialog {

	@FXML
	private TextArea textArea;

	private SupportPlannerView application;
	private Stage ownerStage;
	private String url;

	public static void show(SupportPlannerView application, Stage ownerStage, String url) {

		try {

			Dialog<Void> dialog = new Dialog<>();
			dialog.setTitle(application.getSettings().getLanguage().getString("territory.dialog.viewerurl"));

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
			fxmlLoader.setLocation(TerritoryDialog.class.getResource("TerritoryDialog.fxml"));
			AnchorPane content = fxmlLoader.load();

			TerritoryDialog ctrl = fxmlLoader.getController();
			ctrl.setApplication(application);
			ctrl.setOwnerStage(ownerStage);
			ctrl.setUrl(url);

			ctrl.init();

			dialogPane.setContent(content);
//			dialog.setResultConverter(param -> resultConverter(param, ctrl));

//			Optional<Void> result = dialog.showAndWait();
			dialog.showAndWait();
//
//			if (result.isPresent())
//				return result.get();

		} catch (IOException e) {
			application.getAlertBuilder().error(ownerStage, e.getMessage()).show();
		}

//		return null;
	}

//	private static Place resultConverter(ButtonType param, TerritoryDialog ctrl) {
//
//		if (param != null)
//			if (param.getButtonData() == ButtonBar.ButtonData.OK_DONE)
//				return ctrl.getSelected();
//
//		return null;
//	}
//
//	private Place getSelected() {
//		return this.textArea.getSelectionModel().getSelectedItem();
//	}

	@FXML
	private void initialize() {

		this.textArea.getStyleClass().add("text_area_001");
		this.textArea.setMinWidth(1000);
		this.textArea.setMaxHeight(100);
		this.textArea.setWrapText(true);
		this.textArea.setEditable(false);
	}

	@FXML
	public void init() {

		initData();
	}

	private void initData() {

		this.textArea.setText(this.url);
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
