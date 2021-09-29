package com.sm.net.sp.dialogs.territory;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.StreamSupport;

import com.sm.net.sp.Meta;
import com.sm.net.sp.model.Member;
import com.sm.net.sp.view.SupportPlannerView;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class TerritoryAssignToMemberDialog {

	@FXML
	private TextField filterTextField;

	@FXML
	private TableView<Member> membersTableView;
	@FXML
	private TableColumn<Member, String> memberSurnameTableColumn;
	@FXML
	private TableColumn<Member, String> memberNameTableColumn;
	@FXML
	private TableColumn<Member, ImageView> memberIconTableColumn;

	private SupportPlannerView application;
	private Stage ownerStage;
	private ObservableList<Member> membersList;
	private ObservableList<Member> memberListWithoutDisabled;

	public static Member show(SupportPlannerView application, Stage ownerStage, ObservableList<Member> membersList) {

		try {

			Dialog<Member> dialog = new Dialog<>();
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
			fxmlLoader
					.setLocation(TerritoryAssignToMemberDialog.class.getResource("TerritoryAssignToMemberDialog.fxml"));
			AnchorPane content = fxmlLoader.load();

			TerritoryAssignToMemberDialog ctrl = fxmlLoader.getController();
			ctrl.setApplication(application);
			ctrl.setOwnerStage(ownerStage);
			ctrl.setMembersList(membersList);

			ctrl.init();

			dialogPane.setContent(content);
			dialog.setResultConverter(param -> resultConverter(param, ctrl));

			Optional<Member> result = dialog.showAndWait();

			if (result.isPresent())
				return result.get();

		} catch (IOException e) {
			application.getAlertBuilder().error(ownerStage, e.getMessage()).show();
		}

		return null;
	}

	private static Member resultConverter(ButtonType param, TerritoryAssignToMemberDialog ctrl) {

		if (param != null)
			if (param.getButtonData() == ButtonData.OK_DONE)
				return ctrl.getSelected();

		return null;
	}

	private Member getSelected() {
		return (this.membersTableView.getSelectionModel().getSelectedIndex() > -1)
				? this.membersTableView.getSelectionModel().getSelectedItem()
				: null;
	}

	@FXML
	private void initialize() {

		this.filterTextField.getStyleClass().add("text_field_001");
		this.membersTableView.getStyleClass().add("table_view_001");
		this.memberIconTableColumn.getStyleClass().add("table_column_002");

		this.memberSurnameTableColumn.setCellValueFactory(cellData -> cellData.getValue().spInf2DecryptedProperty());
		this.memberNameTableColumn.setCellValueFactory(cellData -> cellData.getValue().spInf1DecryptedProperty());

		this.memberIconTableColumn.setCellValueFactory(cellData -> {

			if (cellData.getValue().getSpInf4() == 0)
				return new SimpleObjectProperty<ImageView>(Meta.Resources.imageForButtonSmall(Meta.Resources.MALE));
			else
				return new SimpleObjectProperty<ImageView>(Meta.Resources.imageForButtonSmall(Meta.Resources.FEMALE));

		});
	}

	public void init() {

		this.memberIconTableColumn.setText("");
		this.memberIconTableColumn.setMinWidth(50);
		this.memberIconTableColumn.setMaxWidth(50);
		this.memberIconTableColumn.setResizable(false);

		this.memberSurnameTableColumn.setText(this.application.getSettings().getLanguage().getString("TEXT0013"));
		this.memberNameTableColumn.setText(this.application.getSettings().getLanguage().getString("TEXT0014"));

		this.memberListWithoutDisabled = FXCollections.observableArrayList();
		this.memberListWithoutDisabled.addAll(this.membersList);
		this.memberListWithoutDisabled.removeIf(member -> member.isDisabled());

		// this.membersTableView.setItems(this.membersList);
		this.membersTableView.setItems(this.memberListWithoutDisabled);

		this.membersTableView.setMinWidth(500);

		this.filterTextField.textProperty()
				.addListener((observable, oldValue, newValue) -> updateFilterMember(newValue));
	}

	private void updateFilterMember(String newValue) {

		if (newValue.isEmpty())
			this.membersTableView.setItems(this.memberListWithoutDisabled);
		else {
			ObservableList<Member> filteredMemberList = buildListMember(newValue);
			this.membersTableView.setItems(filteredMemberList);
		}

		this.membersTableView.refresh();
	}

	private ObservableList<Member> buildListMember(String filter) {

		ObservableList<Member> list = FXCollections.observableArrayList();

		StreamSupport.stream(this.memberListWithoutDisabled.spliterator(), false).filter(obj -> matchFilterMember(obj, filter))
				.forEach(obj -> list.add(obj));

		return list;
	}

	private boolean matchFilterMember(Member obj, String match) {

		String filter = match.toLowerCase();

		return obj.getSpInf1Decrypted().toLowerCase().contains(filter)
				|| obj.getSpInf2Decrypted().toLowerCase().contains(filter)
				|| obj.getSpInf39Decrypted().toLowerCase().contains(filter);
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
}
