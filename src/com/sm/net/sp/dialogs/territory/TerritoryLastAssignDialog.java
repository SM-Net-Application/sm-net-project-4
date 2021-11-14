package com.sm.net.sp.dialogs.territory;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;

import com.sm.net.sp.Meta;
import com.sm.net.sp.model.Member;
import com.sm.net.sp.model.TerritoryObj;
import com.sm.net.sp.model.TerritoryRegistry;
import com.sm.net.sp.view.SupportPlannerView;

import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class TerritoryLastAssignDialog {

	@FXML
	private Label preacherLabel;
	@FXML
	private TextField preacherTextField;
	@FXML
	private Button preacherButton;

	@FXML
	private TableView<TerritoryObj> tableView;
	@FXML
	private TableColumn<TerritoryObj, BigDecimal> territoryNrTableColumn;
	@FXML
	private TableColumn<TerritoryObj, String> territoryNameTableColumn;
	@FXML
	private TableColumn<TerritoryObj, LocalDate> lastDoneTableColumn;
	@FXML
	private TableColumn<TerritoryObj, ImageView> preacherHadTableColumn;
	@FXML
	private TableColumn<TerritoryObj, LocalDate> preacherHadDateTableColumn;

	private SupportPlannerView application;
	private Stage ownerStage;

	private ObservableList<Member> membersList;
	private ObservableList<TerritoryObj> territoryList;
	private TerritoryRegistry territoryRegistry;

	private ObservableList<Member> membersWithoutDisabledList;
	private ObservableList<TerritoryObj> territoryAvailableList;

	private Member selectedMember;

	public static void show(SupportPlannerView application, Stage ownerStage, ObservableList<Member> membersList,
			ObservableList<TerritoryObj> territoryList, TerritoryRegistry territoryRegistry, Member selectedMember) {

		try {

			Dialog<Void> dialog = new Dialog<>();

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
			fxmlLoader.setLocation(TerritoryLastAssignDialog.class.getResource("TerritoryLastAssignDialog.fxml"));
			AnchorPane content = fxmlLoader.load();

			TerritoryLastAssignDialog ctrl = fxmlLoader.getController();
			ctrl.setApplication(application);
			ctrl.setOwnerStage(ownerStage);
			ctrl.setMembersList(membersList);
			ctrl.setTerritoryList(territoryList);
			ctrl.setTerritoryRegistry(territoryRegistry);
			ctrl.setSelectedMember(selectedMember);

			ctrl.init();

			dialogPane.setContent(content);
			dialog.showAndWait();

		} catch (IOException e) {
			application.getAlertBuilder().error(ownerStage, e.getMessage()).show();
		}
	}

	@FXML
	private void initialize() {

		this.preacherLabel.getStyleClass().add("label_001");
		this.preacherTextField.getStyleClass().add("text_field_001");
		this.preacherButton.getStyleClass().add("button_image_001");

		this.tableView.getStyleClass().add("table_view_001");
		this.territoryNrTableColumn.getStyleClass().add("table_column_002");
		this.lastDoneTableColumn.getStyleClass().add("table_column_002");
		this.preacherHadTableColumn.getStyleClass().add("table_column_002");
		this.preacherHadDateTableColumn.getStyleClass().add("table_column_002");

		this.territoryNrTableColumn.setCellValueFactory(
				cellData -> new SimpleObjectProperty<BigDecimal>(new BigDecimal(cellData.getValue().getSpInf7())));
		this.territoryNameTableColumn.setCellValueFactory(cellData -> cellData.getValue().spInf8Property());

		this.lastDoneTableColumn.setCellValueFactory(cellData -> cellData.getValue().lastDoneDateProperty());

		this.preacherHadTableColumn.setCellValueFactory(cellData -> {

			LocalDate preacherLastDoneDate = cellData.getValue().getPreacherLastDoneDate();
			if (preacherLastDoneDate != null)
				return new SimpleObjectProperty<ImageView>(Meta.Resources.imageForTableColumnRow(Meta.Resources.OK));

			return null;
		});

		this.preacherHadDateTableColumn
				.setCellValueFactory(cellData -> cellData.getValue().preacherLastDoneDateProperty());
	}

	public void init() {

		if (this.selectedMember != null)
			this.preacherTextField.setText(this.selectedMember.getNameStyle1());

		this.membersWithoutDisabledList = FXCollections.observableArrayList();
		this.territoryAvailableList = FXCollections.observableArrayList();

		this.preacherLabel.setText(
				this.application.getSettings().getLanguage().getString("territory.dialog.lastassign.label.preacher"));
		this.preacherTextField.setEditable(false);

		Tooltip preacherButtonTooltip = new Tooltip(
				this.application.getSettings().getLanguage().getString("territory.dialog.lastassign.tooltip.preacher"));
		preacherButtonTooltip.getStyleClass().add("tooltip_001");
		this.preacherButton.setTooltip(preacherButtonTooltip);
		this.preacherButton.setText("");
		this.preacherButton.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.MEMBER));

		this.territoryNrTableColumn.setText(this.application.getSettings().getLanguage()
				.getString("territory.dialog.lastassign.tablecolumn.territorynr"));
		this.territoryNrTableColumn.setMinWidth(100);
		this.territoryNrTableColumn.setMaxWidth(100);
		this.territoryNrTableColumn.setResizable(false);

		this.territoryNameTableColumn.setText(this.application.getSettings().getLanguage()
				.getString("territory.dialog.lastassign.tablecolumn.territoryname"));
		this.territoryNameTableColumn.setMinWidth(350);

		this.lastDoneTableColumn.setText(this.application.getSettings().getLanguage()
				.getString("territory.dialog.lastassign.tablecolumn.lastdone"));
		this.lastDoneTableColumn.setMinWidth(200);

		this.preacherHadTableColumn.setText("");
		this.preacherHadTableColumn.setMinWidth(50);
		this.preacherHadTableColumn.setMaxWidth(50);

		this.preacherHadDateTableColumn.setText(this.application.getSettings().getLanguage()
				.getString("territory.dialog.lastassign.tablecolumn.lastdonepreacher"));
		this.preacherHadDateTableColumn.setMinWidth(400);

		// BUILD MEMBER LIST AND DELETE DISABLED
		this.membersWithoutDisabledList.addAll(this.membersList);
		this.membersWithoutDisabledList.removeIf(member -> member.isDisabled());

		// BUILD TERRITORY LIST AND DELETE NOT AVIALABLE
		this.territoryAvailableList.addAll(this.territoryList);
		this.territoryAvailableList.removeIf(territory -> !territory.isAvailable());
		this.territoryAvailableList.removeIf(territory -> territory.isBlocked());
		this.territoryAvailableList.removeIf(territory -> territory.isArchived());
		this.territoryAvailableList.forEach(territory -> territory.checkLastDoneDate(this.territoryRegistry));
		this.territoryAvailableListCheckSelectedPreacher();

		Collections.sort(this.territoryAvailableList, Comparator.comparing(TerritoryObj::ownGetLastDoneDate));

		// TABLEVIEW INIT
		this.tableView.setMinWidth(750);
		this.tableView.setItems(this.territoryAvailableList);
		Platform.runLater(() -> this.tableView.refresh());

		// LISTENERS
		this.preacherButton.setOnAction(event -> selectMember());
	}

	private void selectMember() {

		Member member = TerritoryAssignToMemberDialog.show(this.application, this.ownerStage,
				this.membersWithoutDisabledList);

		if (member != null) {

			this.selectedMember = member;
			this.preacherTextField.setText(member.getNameStyle1());

			this.territoryAvailableListCheckSelectedPreacher();
			Platform.runLater(() -> this.tableView.refresh());

		}
	}

	private void territoryAvailableListCheckSelectedPreacher() {

		if (this.selectedMember != null)
			this.territoryAvailableList.forEach(
					territory -> territory.checkPreacherLastDoneDate(this.selectedMember, this.territoryRegistry));
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

	public ObservableList<Member> getMembersList() {
		return membersList;
	}

	public void setMembersList(ObservableList<Member> membersList) {
		this.membersList = membersList;
	}

	public ObservableList<TerritoryObj> getTerritoryList() {
		return territoryList;
	}

	public void setTerritoryList(ObservableList<TerritoryObj> territoryList) {
		this.territoryList = territoryList;
	}

	public TerritoryRegistry getTerritoryRegistry() {
		return territoryRegistry;
	}

	public void setTerritoryRegistry(TerritoryRegistry territoryRegistry) {
		this.territoryRegistry = territoryRegistry;
	}

	public Member getSelectedMember() {
		return selectedMember;
	}

	public void setSelectedMember(Member selectedMember) {
		this.selectedMember = selectedMember;
	}
}
