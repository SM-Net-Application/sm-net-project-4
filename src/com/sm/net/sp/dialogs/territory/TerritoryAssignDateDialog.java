package com.sm.net.sp.dialogs.territory;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

import com.sm.net.sp.Meta;
import com.sm.net.sp.model.Member;
import com.sm.net.sp.model.TerritoryObj;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class TerritoryAssignDateDialog {

	@FXML
	private Label territoryLabel;
	@FXML
	private Label memberLabel;
	@FXML
	private Label dateLabel;

	@FXML
	private TextField territoryTextField;
	@FXML
	private TextField memberTextField;
	@FXML
	private DatePicker assignDatePicker;

	private SupportPlannerView application;
	private Stage ownerStage;
	private TerritoryObj territoryObj;
	private Member member;

	public static LocalDate show(SupportPlannerView application, Stage ownerStage, TerritoryObj territoryObj,
			Member member) {

		try {

			Dialog<LocalDate> dialog = new Dialog<>();
			dialog.setTitle(application.getSettings().getLanguage().getString("territory.dialog.assigndate"));

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
			fxmlLoader.setLocation(TerritoryAssignDateDialog.class.getResource("TerritoryAssignDateDialog.fxml"));
			AnchorPane content = fxmlLoader.load();

			TerritoryAssignDateDialog ctrl = fxmlLoader.getController();
			ctrl.setApplication(application);
			ctrl.setOwnerStage(ownerStage);
			ctrl.setTerritoryObj(territoryObj);
			ctrl.setMember(member);

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

	private static LocalDate resultConverter(ButtonType param, TerritoryAssignDateDialog ctrl) {

		if (param != null)
			if (param.getButtonData() == ButtonData.OK_DONE)
				return ctrl.getSelected();

		return null;
	}

	private LocalDate getSelected() {
		return this.assignDatePicker.getValue();
	}

	@FXML
	private void initialize() {

		this.territoryLabel.getStyleClass().add("label_set_001");
		this.memberLabel.getStyleClass().add("label_set_001");
		this.dateLabel.getStyleClass().add("label_set_001");

		this.territoryTextField.getStyleClass().add("text_field_001");
		this.memberTextField.getStyleClass().add("text_field_001");
		this.assignDatePicker.getStyleClass().add("combo_box_001");
	}

	public void init() {

		this.territoryLabel.setText(
				this.application.getSettings().getLanguage().getString("territory.dialog.assigndate.label.territory"));
		this.memberLabel.setText(
				this.application.getSettings().getLanguage().getString("territory.dialog.assigndate.label.member"));
		this.dateLabel.setText(
				this.application.getSettings().getLanguage().getString("territory.dialog.assigndate.label.date"));

		this.territoryTextField.setMinWidth(400);
		this.territoryTextField.setEditable(false);
		this.territoryTextField
				.setText(String.format("%s - %s", this.territoryObj.getSpInf7(), this.territoryObj.getSpInf8()));

		this.memberTextField.setEditable(false);
		this.memberTextField.setText(this.member.getNameStyle1());

		this.assignDatePicker.setValue(LocalDate.now());
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

	public TerritoryObj getTerritoryObj() {
		return territoryObj;
	}

	public void setTerritoryObj(TerritoryObj territoryObj) {
		this.territoryObj = territoryObj;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}
}
