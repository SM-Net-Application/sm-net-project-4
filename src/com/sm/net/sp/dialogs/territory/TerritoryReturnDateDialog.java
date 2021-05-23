package com.sm.net.sp.dialogs.territory;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

import com.sm.net.sp.Meta;
import com.sm.net.sp.view.SupportPlannerView;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class TerritoryReturnDateDialog {

	@FXML
	private Label dateLabel;

	@FXML
	private DatePicker returnDatePicker;

	private SupportPlannerView application;
	private Stage ownerStage;

	public static LocalDate show(SupportPlannerView application, Stage ownerStage) {

		try {

			Dialog<LocalDate> dialog = new Dialog<>();
			dialog.setTitle(application.getSettings().getLanguage().getString("territory.dialog.returndate"));

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
			fxmlLoader.setLocation(TerritoryReturnDateDialog.class.getResource("TerritoryReturnDateDialog.fxml"));
			AnchorPane content = fxmlLoader.load();

			TerritoryReturnDateDialog ctrl = fxmlLoader.getController();
			ctrl.setApplication(application);
			ctrl.setOwnerStage(ownerStage);

			ctrl.init();

			dialogPane.setContent(content);
			dialog.setResultConverter(param -> resultConverter(param, ctrl));

			Optional<LocalDate> result = dialog.showAndWait();

			if (result.isPresent())
				return result.get();

		} catch (IOException e) {
			application.getAlertBuilder().error(ownerStage, e.getMessage()).show();
		}

		return null;
	}

	private static LocalDate resultConverter(ButtonType param, TerritoryReturnDateDialog ctrl) {

		if (param != null)
			if (param.getButtonData() == ButtonData.OK_DONE)
				return ctrl.getSelected();

		return null;
	}

	private LocalDate getSelected() {
		return this.returnDatePicker.getValue();
	}

	@FXML
	private void initialize() {

		this.dateLabel.getStyleClass().add("label_set_001");
		this.returnDatePicker.getStyleClass().add("combo_box_001");
	}

	public void init() {

		this.dateLabel.setText(
				this.application.getSettings().getLanguage().getString("territory.dialog.returndate.label.date"));

		this.returnDatePicker.setValue(LocalDate.now());
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
}
