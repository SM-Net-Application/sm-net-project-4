package com.sm.net.sp.view.home.user.menu.meetings;

import java.io.IOException;

import com.sm.net.javafx.AlertDesigner;
import com.sm.net.jw.wol.MinistryType;
import com.sm.net.jw.wol.MinistryTypeTranslated;
import com.sm.net.jw.wol.ScheduleForMeeting;
import com.sm.net.jw.wol.ScheduleForMeetingHTML;
import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.actions.Actions;
import com.sm.net.sp.model.ChristiansPart;
import com.sm.net.sp.model.Member;
import com.sm.net.sp.model.MinistryPart;
import com.sm.net.sp.model.UpdateDataAdapter;
import com.sm.net.sp.model.Week;
import com.sm.net.sp.settings.Settings;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class UserMenuMeetingsEditor extends UpdateDataAdapter {

	@FXML
	private Button loadWeekFromWOLButton;
	@FXML
	private Button saveWeekButton;

	@FXML
	private TabPane tabPane;
	@FXML
	private Tab treasuresTab;
	@FXML
	private Tab ministryTab;
	@FXML
	private Tab christiansTab;

	@FXML
	private Label generalLabel;
	@FXML
	private Label treasuresLabel;
	@FXML
	private Label openingCommentsLabel;
	@FXML
	private Label talkLabel;
	@FXML
	private Label diggingLabel;
	@FXML
	private Label bibleReadingLabel;

	@FXML
	private Label presidentLabel;
	@FXML
	private ComboBox<Member> presidentComboBox;
	@FXML
	private Label bibleChaptersLabel;
	@FXML
	private TextField bibleChaptersTextField;
	@FXML
	private Label song1Label;
	@FXML
	private TextField song1TextField;
	@FXML
	private Label pray1Label;
	@FXML
	private ComboBox<Member> pray1ComboBox;

	@FXML
	private TextField openingCommentsMinTextField;
	@FXML
	private Label openingCommentsMinLabel;
	@FXML
	private TextField openingCommentsTextTextField;

	@FXML
	private TextField talkMinTextField;
	@FXML
	private Label talkMinLabel;
	@FXML
	private TextField talkTextTextField;
	@FXML
	private ComboBox<Member> talkComboBox;

	@FXML
	private TextField diggingMinTextField;
	@FXML
	private Label diggingMinLabel;
	@FXML
	private TextField diggingTextTextField;
	@FXML
	private ComboBox<Member> diggingComboBox;

	@FXML
	private TextField bibleReadingMinTextField;
	@FXML
	private Label bibleReadingMinLabel;
	@FXML
	private TextField bibleReadingTextTextField;
	@FXML
	private TextField bibleReadingMaterialsTextField;
	@FXML
	private ComboBox<Member> bibleReadingComboBox;

	@FXML
	private TableView<MinistryPart> ministryTableView;
	@FXML
	private TableColumn<MinistryPart, String> ministryTypeTableColumn;
	@FXML
	private TableColumn<MinistryPart, String> ministryFulltextTableColumn;
	@FXML
	private TableColumn<MinistryPart, Integer> ministryMinTableColumn;
	@FXML
	private TableColumn<MinistryPart, String> ministryThemeTableColumn;
	@FXML
	private TableColumn<MinistryPart, String> ministryMaterialTableColumn;
	@FXML
	private TableColumn<MinistryPart, Member> ministryMember1TableColumn;
	@FXML
	private TableColumn<MinistryPart, Member> ministryMember2TableColumn;

	@FXML
	private Button ministryPartAddButton;
	@FXML
	private Button ministryPartDeleteButton;

	@FXML
	private Label song2Label;
	@FXML
	private TextField song2TextField;

	@FXML
	private TableView<ChristiansPart> christiansPartTableView;
	@FXML
	private TableColumn<ChristiansPart, String> christiansPartFulltextTableColumn;
	@FXML
	private TableColumn<ChristiansPart, Integer> christiansPartMinTableColumn;
	@FXML
	private TableColumn<ChristiansPart, String> christiansPartThemeTableColumn;
	@FXML
	private TableColumn<ChristiansPart, String> christiansPartMaterialTableColumn;
	@FXML
	private TableColumn<ChristiansPart, Member> christiansPartTeacherTableColumn;

	@FXML
	private Button christiansPartAddButton;
	@FXML
	private Button christiansPartDeleteButton;

	@FXML
	private Label congregationBibleStudyLabel;
	@FXML
	private TextField congregationBibleStudyMinTextField;
	@FXML
	private Label congregationBibleStudyMinLabel;
	@FXML
	private TextField congregationBibleStudyTextTextField;
	@FXML
	private TextField congregationBibleStudyMaterialTextField;
	@FXML
	private ComboBox<Member> congregationBibleStudyComboBox;

	@FXML
	private Label endLabel;

	@FXML
	private Label reviewLabel;
	@FXML
	private Label reviewMinLabel;
	@FXML
	private TextField reviewTextTextField;
	@FXML
	private TextField reviewMinTextField;

	@FXML
	private Label song3Label;
	@FXML
	private TextField song3TextField;
	@FXML
	private Label pray2Label;
	@FXML
	private ComboBox<Member> pray2ComboBox;

	private Settings settings;
	private Language language;
	private Stage ownerStage;
	private UserMenuMeetings ownerCtrl;
	private Week selectedWeek;
	private TabPane ownerTabPane;
	private Tab thisTab;

	private ObservableList<MinistryTypeTranslated> ministryTypeTranslatedList;
	private ObservableList<MinistryPart> ministryPartList;
	private ObservableList<Member> memberList;
	private ObservableList<ChristiansPart> christiansPartList;

	@FXML
	private void initialize() {
		styleClasses();

		tableViewEditCommit();
		cellValueFactory();
		cellFactory();
	}

	private void styleClasses() {
		tabPane.getStyleClass().add("tabPaneStyle2");
		treasuresTab.getStyleClass().add("tabStyle1");
		ministryTab.getStyleClass().add("tabStyle1");
		christiansTab.getStyleClass().add("tabStyle1");

		generalLabel.getStyleClass().add("labelStyle2");
		treasuresLabel.getStyleClass().add("labelStyle2");

		presidentLabel.getStyleClass().add("labelStyle3");
		presidentComboBox.getStyleClass().add("comboBoxStyle2");

		bibleChaptersLabel.getStyleClass().add("labelStyle3");
		bibleChaptersTextField.getStyleClass().add("textFieldStyle3");

		song1Label.getStyleClass().add("labelStyle3");
		song1TextField.getStyleClass().add("textFieldStyle2");

		pray1Label.getStyleClass().add("labelStyle3");
		pray1ComboBox.getStyleClass().add("comboBoxStyle2");

		openingCommentsLabel.getStyleClass().add("labelStyle3");
		openingCommentsMinTextField.getStyleClass().add("textFieldStyle2");
		openingCommentsMinLabel.getStyleClass().add("labelStyle3");
		openingCommentsTextTextField.getStyleClass().add("textFieldStyle3");

		talkLabel.getStyleClass().add("labelStyle1");
		talkMinTextField.getStyleClass().add("textFieldStyle2");
		talkMinLabel.getStyleClass().add("labelStyle3");
		talkTextTextField.getStyleClass().add("textFieldStyle3");
		talkComboBox.getStyleClass().add("comboBoxStyle2");

		diggingLabel.getStyleClass().add("labelStyle1");
		diggingMinTextField.getStyleClass().add("textFieldStyle2");
		diggingMinLabel.getStyleClass().add("labelStyle3");
		diggingTextTextField.getStyleClass().add("textFieldStyle3");
		diggingComboBox.getStyleClass().add("comboBoxStyle2");

		bibleReadingLabel.getStyleClass().add("labelStyle1");
		bibleReadingMinTextField.getStyleClass().add("textFieldStyle2");
		bibleReadingMinLabel.getStyleClass().add("labelStyle3");
		bibleReadingTextTextField.getStyleClass().add("textFieldStyle3");
		bibleReadingMaterialsTextField.getStyleClass().add("textFieldStyle3");
		bibleReadingComboBox.getStyleClass().add("comboBoxStyle2");

		ministryTableView.getStyleClass().add("tableViewStyle1");
		ministryMinTableColumn.getStyleClass().add("tableColumnStyle1");

		ministryPartAddButton.getStyleClass().add("buttonStyle2");
		ministryPartDeleteButton.getStyleClass().add("buttonStyle2");

		song2Label.getStyleClass().add("labelStyle3");
		song2TextField.getStyleClass().add("textFieldStyle2");

		christiansPartTableView.getStyleClass().add("tableViewStyle1");
		christiansPartMinTableColumn.getStyleClass().add("tableColumnStyle1");

		christiansPartAddButton.getStyleClass().add("buttonStyle2");
		christiansPartDeleteButton.getStyleClass().add("buttonStyle2");

		congregationBibleStudyLabel.getStyleClass().add("labelStyle2");
		congregationBibleStudyMinTextField.getStyleClass().add("textFieldStyle2");
		congregationBibleStudyMinLabel.getStyleClass().add("labelStyle3");
		congregationBibleStudyTextTextField.getStyleClass().add("textFieldStyle3");
		congregationBibleStudyMaterialTextField.getStyleClass().add("textFieldStyle3");
		congregationBibleStudyComboBox.getStyleClass().add("comboBoxStyle2");

		endLabel.getStyleClass().add("labelStyle2");

		reviewLabel.getStyleClass().add("labelStyle3");
		reviewMinTextField.getStyleClass().add("textFieldStyle2");
		reviewMinLabel.getStyleClass().add("labelStyle3");
		reviewTextTextField.getStyleClass().add("textFieldStyle3");

		song3Label.getStyleClass().add("labelStyle3");
		song3TextField.getStyleClass().add("textFieldStyle2");

		pray2Label.getStyleClass().add("labelStyle3");
		pray2ComboBox.getStyleClass().add("comboBoxStyle2");

		loadWeekFromWOLButton.getStyleClass().add("buttonStyle2");
		saveWeekButton.getStyleClass().add("buttonStyle2");
	}

	private void cellValueFactory() {

		ministryTypeTableColumn
				.setCellValueFactory(cellData -> cellData.getValue().getMinistryTypeTranslated().nameProperty());
		ministryFulltextTableColumn.setCellValueFactory(cellData -> cellData.getValue().fullTextProperty());
		ministryMinTableColumn.setCellValueFactory(cellData -> cellData.getValue().minProperty().asObject());
		ministryThemeTableColumn.setCellValueFactory(cellData -> cellData.getValue().themeProperty());
		ministryMaterialTableColumn.setCellValueFactory(cellData -> cellData.getValue().materialProperty());
		ministryMember1TableColumn.setCellValueFactory(cellData -> cellData.getValue().studentProperty());
		ministryMember2TableColumn.setCellValueFactory(cellData -> cellData.getValue().assistantProperty());

		christiansPartFulltextTableColumn.setCellValueFactory(cellData -> cellData.getValue().fullTextProperty());
		christiansPartMinTableColumn.setCellValueFactory(cellData -> cellData.getValue().minProperty().asObject());
		christiansPartThemeTableColumn.setCellValueFactory(cellData -> cellData.getValue().themeProperty());
		christiansPartMaterialTableColumn.setCellValueFactory(cellData -> cellData.getValue().materialProperty());
		christiansPartTeacherTableColumn.setCellValueFactory(cellData -> cellData.getValue().teacherProperty());
	}

	private void cellFactory() {

		ministryMinTableColumn.setCellFactory(TextFieldTableCell.forTableColumn(stringToInteger()));
		ministryThemeTableColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		ministryMaterialTableColumn.setCellFactory(TextFieldTableCell.forTableColumn());

		christiansPartMinTableColumn.setCellFactory(TextFieldTableCell.forTableColumn(stringToInteger()));
		christiansPartThemeTableColumn.setCellFactory(TextFieldTableCell.forTableColumn());
		christiansPartMaterialTableColumn.setCellFactory(TextFieldTableCell.forTableColumn());
	}

	private StringConverter<Integer> stringToInteger() {

		return new StringConverter<Integer>() {

			@Override
			public String toString(Integer object) {
				return object.toString();
			}

			@Override
			public Integer fromString(String string) {

				Integer value = Integer.valueOf(0);
				try {
					value = Integer.valueOf(string);
				} catch (NumberFormatException e) {
				}

				return value;
			}
		};
	}

	private void tableViewEditCommit() {

		ministryTableView.setEditable(true);
		ministryMinTableColumn.setOnEditCommit(event -> ministryMinOnEditCommit(event));
		ministryThemeTableColumn.setOnEditCommit(event -> ministryThemeOnEditCommit(event));
		ministryMaterialTableColumn.setOnEditCommit(event -> ministryMaterialOnEditCommit(event));

		christiansPartTableView.setEditable(true);
		christiansPartMinTableColumn.setOnEditCommit(event -> christiansPartMinOnEditCommit(event));
		christiansPartThemeTableColumn.setOnEditCommit(event -> christiansPartThemeOnEditCommit(event));
		christiansPartMaterialTableColumn.setOnEditCommit(event -> christiansPartMaterialOnEditCommit(event));
	}

	private void ministryMinOnEditCommit(CellEditEvent<MinistryPart, Integer> event) {

		MinistryPart ministryPart = event.getTableView().getItems().get(event.getTablePosition().getRow());
		ministryPart.setMin(event.getNewValue().intValue());
	}

	private void ministryThemeOnEditCommit(CellEditEvent<MinistryPart, String> event) {

		MinistryPart ministryPart = event.getTableView().getItems().get(event.getTablePosition().getRow());
		ministryPart.setTheme(event.getNewValue());
	}

	private void ministryMaterialOnEditCommit(CellEditEvent<MinistryPart, String> event) {

		MinistryPart ministryPart = event.getTableView().getItems().get(event.getTablePosition().getRow());
		ministryPart.setMaterial(event.getNewValue());
	}

	private void christiansPartMinOnEditCommit(CellEditEvent<ChristiansPart, Integer> event) {

		ChristiansPart christiansPartPart = event.getTableView().getItems().get(event.getTablePosition().getRow());
		christiansPartPart.setMin(event.getNewValue().intValue());
	}

	private void christiansPartThemeOnEditCommit(CellEditEvent<ChristiansPart, String> event) {

		ChristiansPart christiansPartPart = event.getTableView().getItems().get(event.getTablePosition().getRow());
		christiansPartPart.setTheme(event.getNewValue());
	}

	private void christiansPartMaterialOnEditCommit(CellEditEvent<ChristiansPart, String> event) {

		ChristiansPart christiansPartPart = event.getTableView().getItems().get(event.getTablePosition().getRow());
		christiansPartPart.setMaterial(event.getNewValue());
	}

	public void objectInitialize() {
		viewUpdate();
		listeners();
		initData();
	}

	private void initData() {

		memberList = FXCollections.observableArrayList();

		ministryTypeTranslatedList = MinistryType.getAllMinistryTypeTranslated(language);

		ministryPartList = FXCollections.observableArrayList();

		ministryTableView.setItems(ministryPartList);

		christiansPartList = FXCollections.observableArrayList();

		christiansPartTableView.setItems(christiansPartList);

		updateMembers();
	}

	@Override
	public void updateMembers() {
		super.updateMembers();
		Actions.getAllMembers(settings, ownerStage, this);
	}

	@Override
	public void updateMembers(ObservableList<Member> list) {
		super.updateMembers(list);
		memberList = list;
	}

	private void listeners() {
		listenerMinistryTableView();
		listenerMinistryPartAddButton();
		listenerMinistryPartDeleteButton();

		listenerChristiansPartTableView();
		listenerChristiansPartAddButton();
		listenerChristiansPartDeleteButton();

		listenerLoadWeekFromWOLButton();
	}

	private void listenerLoadWeekFromWOLButton() {
		loadWeekFromWOLButton.setOnAction(event -> loadWeekFromWOLOnAction());
	}

	private void loadWeekFromWOLOnAction() {

		// TODO: Usare Language

		Alert alert = new AlertDesigner("Programma per la settimana",
				"Vuoi scaricare il programma da Watchtower Online Library?", ownerStage, AlertType.CONFIRMATION,
				Meta.Application.getFullTitle(), Meta.Resources.ICON);

		if (alert.showAndWait().get() == ButtonType.OK) {
			ScheduleForMeetingHTML scheduleForMeetingHTML = new ScheduleForMeetingHTML(language,
					selectedWeek.getFrom());
			scheduleForMeetingHTML.download();

			if (scheduleForMeetingHTML != null) {
				ScheduleForMeeting scheduleForMeeting = new ScheduleForMeeting(scheduleForMeetingHTML.getRelevantRows(),
						language);
				if (scheduleForMeeting != null) {

					song1TextField.setText(scheduleForMeeting.getSong1().getSongNo().toString());

					bibleChaptersTextField.setText(scheduleForMeeting.getBibleChapters());

					openingCommentsMinTextField.setText(scheduleForMeeting.getOpeningComments().getMin().toString());
					openingCommentsTextTextField.setText(scheduleForMeeting.getOpeningComments().getTitle());

					talkMinTextField.setText(scheduleForMeeting.getTreasuresTalk().getMin().toString());
					talkTextTextField.setText(scheduleForMeeting.getTreasuresTalk().getTitle());

					diggingMinTextField.setText(scheduleForMeeting.getTreasuresDigging().getMin().toString());
					diggingTextTextField.setText(scheduleForMeeting.getTreasuresDigging().getTitle());

					bibleReadingMinTextField.setText(scheduleForMeeting.getTreasuresBibleReading().getMin().toString());
					bibleReadingTextTextField.setText(scheduleForMeeting.getTreasuresBibleReading().getTextPart());
					bibleReadingMaterialsTextField.setText(scheduleForMeeting.getTreasuresBibleReading().getBible());

					ministryPartList.clear();
					for (ScheduleForMeeting.MinistryPart part : scheduleForMeeting.getMinistryPartsList())
						ministryPartList.add(new MinistryPart(part.getMinistryTypeTranslated(), part.getText(),
								part.getMin(), part.getTextPart(), part.getMaterial(), Member.emptyMember(language),
								Member.emptyMember(language)));

					song2TextField.setText(scheduleForMeeting.getSong2().getSongNo().toString());

					christiansPartList.clear();
					for (ScheduleForMeeting.ChristiansPart part : scheduleForMeeting.getChristiansPartsList())
						christiansPartList.add(new ChristiansPart(part.getText(), part.getMin(), part.getTextPart(),
								part.getBody(), Member.emptyMember(language)));

					congregationBibleStudyMinTextField
							.setText(scheduleForMeeting.getCongregationBibleStudy().getMin().toString());
					congregationBibleStudyTextTextField
							.setText(scheduleForMeeting.getCongregationBibleStudy().getTextPart());
					congregationBibleStudyMaterialTextField
							.setText(scheduleForMeeting.getCongregationBibleStudy().getBody());

					reviewMinTextField.setText(scheduleForMeeting.getReview().getMin().toString());
					reviewTextTextField.setText(scheduleForMeeting.getReview().getTitle());

					song3TextField.setText(scheduleForMeeting.getSong3().getSongNo().toString());

				}
			}
		}

	}

	private void listenerMinistryPartAddButton() {
		ministryPartAddButton.setOnAction(event -> ministryPartAddButtonOnActionEvent());
	}

	private void listenerMinistryPartDeleteButton() {
		ministryPartDeleteButton.setOnAction(event -> ministryPartDeleteButtonOnActionEvent());
	}

	private void ministryPartAddButtonOnActionEvent() {
		ministryPartList.add(MinistryPart.newMinistryPart(language));
		ministryTableView.refresh();
	}

	private void ministryPartDeleteButtonOnActionEvent() {

		if (ministryTableView.getSelectionModel().getSelectedIndex() > -1) {

			MinistryPart ministryPart = ministryTableView.getSelectionModel().getSelectedItem();

			Alert alert = new AlertDesigner(language.getString("TEXT0097"), ministryPart.printMinistryPart(language),
					ownerStage, AlertType.CONFIRMATION, Meta.Application.getFullTitle(), Meta.Resources.ICON);

			if (alert.showAndWait().get() == ButtonType.OK) {
				int index = ministryTableView.getSelectionModel().getSelectedIndex();
				ministryTableView.getItems().remove(index);
				ministryTableView.refresh();
			}
		}
	}

	private void listenerMinistryTableView() {
		ministryTableView.setRowFactory(param -> {
			TableRow<MinistryPart> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (!row.isEmpty()))
					editMinistryPart(row.getItem());
			});
			return row;
		});
	}

	private void editMinistryPart(MinistryPart rowItem) {

		try {

			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(Meta.Views.HOME_USER_MENU_MEETINGS_MINISTRY_PART_EDITOR);
			Scene scene = new Scene(fxmlLoader.load());

			scene.getStylesheets().add(Meta.Themes.SUPPORTPLANNER_THEME);

			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setTitle(Meta.Application.getFullTitle());
			stage.getIcons().add(Meta.Resources.ICON);

			stage.setResizable(false);

			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(ownerStage);

			MinistryPartEditor ctrl = (MinistryPartEditor) fxmlLoader.getController();
			ctrl.setSettings(this.settings);
			ctrl.setOwnerStage(ownerStage);
			ctrl.setThisStage(stage);
			ctrl.setMinistryPart(rowItem);
			ctrl.setMinistryTableView(ministryTableView);
			ctrl.setMembersList(memberList);
			ctrl.setMinistryTypeTranslatedList(ministryTypeTranslatedList);
			ctrl.objectInitialize();

			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void listenerChristiansPartAddButton() {
		christiansPartAddButton.setOnAction(event -> christiansPartAddButtonOnActionEvent());
	}

	private void listenerChristiansPartDeleteButton() {
		christiansPartDeleteButton.setOnAction(event -> christiansPartDeleteButtonOnActionEvent());
	}

	private void christiansPartAddButtonOnActionEvent() {
		christiansPartList.add(ChristiansPart.newChristiansPart(language));
		christiansPartTableView.refresh();
	}

	private void christiansPartDeleteButtonOnActionEvent() {

		if (christiansPartTableView.getSelectionModel().getSelectedIndex() > -1) {

			ChristiansPart christiansPart = christiansPartTableView.getSelectionModel().getSelectedItem();

			Alert alert = new AlertDesigner(language.getString("TEXT0097"),
					christiansPart.printChristiansPart(language), ownerStage, AlertType.CONFIRMATION,
					Meta.Application.getFullTitle(), Meta.Resources.ICON);

			if (alert.showAndWait().get() == ButtonType.OK) {
				int index = christiansPartTableView.getSelectionModel().getSelectedIndex();
				christiansPartTableView.getItems().remove(index);
				christiansPartTableView.refresh();
			}
		}
	}

	private void listenerChristiansPartTableView() {
		christiansPartTableView.setRowFactory(param -> {
			TableRow<ChristiansPart> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (!row.isEmpty()))
					editChristiansPart(row.getItem());
			});
			return row;
		});
	}

	private void editChristiansPart(ChristiansPart rowItem) {

		try {

			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(Meta.Views.HOME_USER_MENU_MEETINGS_CHRISTIANS_PART_EDITOR);
			Scene scene = new Scene(fxmlLoader.load());

			scene.getStylesheets().add(Meta.Themes.SUPPORTPLANNER_THEME);

			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setTitle(Meta.Application.getFullTitle());
			stage.getIcons().add(Meta.Resources.ICON);

			stage.setResizable(false);

			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(ownerStage);

			ChristiansPartEditor ctrl = (ChristiansPartEditor) fxmlLoader.getController();
			ctrl.setSettings(this.settings);
			ctrl.setOwnerStage(ownerStage);
			ctrl.setThisStage(stage);
			ctrl.setChristiansPart(rowItem);
			ctrl.setChristiansPartTableView(christiansPartTableView);
			ctrl.setMembersList(memberList);
			ctrl.objectInitialize();

			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void viewUpdate() {

		this.language = settings.getLanguage();

		treasuresTab.setText(language.getString("TEXT0080"));
		treasuresTab.setGraphic(Meta.Resources.createTabIcon(Meta.Resources.USER_MENU_MEETINGS_TREASURES));
		ministryTab.setText(language.getString("TEXT0081"));
		ministryTab.setGraphic(Meta.Resources.createTabIcon(Meta.Resources.USER_MENU_MEETINGS_MINISTRY));
		christiansTab.setText(language.getString("TEXT0082"));
		christiansTab.setGraphic(Meta.Resources.createTabIcon(Meta.Resources.USER_MENU_MEETINGS_CHRISTIANS));

		generalLabel.setText(language.getString("TEXT0079"));
		treasuresLabel.setText(language.getString("TEXT0080"));

		presidentLabel.setText(language.getString("TEXT0083"));
		bibleChaptersLabel.setText(language.getString("TEXT0086"));
		song1Label.setText(language.getString("TEXT0085"));
		pray1Label.setText(language.getString("TEXT0084"));
		openingCommentsLabel.setText(language.getString("TEXT0087"));
		openingCommentsMinLabel.setText(language.getString("TEXT0089"));
		talkLabel.setText(language.getString("TEXT0051"));
		talkMinLabel.setText(language.getString("TEXT0089"));
		diggingLabel.setText(language.getString("TEXT0088"));
		diggingMinLabel.setText(language.getString("TEXT0089"));

		bibleReadingLabel.setText(language.getString("TEXT0047"));
		bibleReadingMinLabel.setText(language.getString("TEXT0089"));

		ministryTypeTableColumn.setText(language.getString("TEXT0091"));
		ministryFulltextTableColumn.setText(language.getString("TEXT0092"));
		ministryMinTableColumn.setText(language.getString("TEXT0093"));
		ministryThemeTableColumn.setText(language.getString("TEXT0094"));
		ministryMaterialTableColumn.setText(language.getString("TEXT0095"));
		ministryMember1TableColumn.setText(language.getString("TEXT0083") + " / " + language.getString("TEXT0044"));
		ministryMember2TableColumn.setText(language.getString("TEXT0038"));

		ministryPartAddButton.setText(null);
		ministryPartAddButton.setGraphic(Meta.Resources.createTabIcon(Meta.Resources.USER_MENU_MEETINGS_MINISTRY_ADD));
		ministryPartDeleteButton.setText(null);
		ministryPartDeleteButton
				.setGraphic(Meta.Resources.createTabIcon(Meta.Resources.USER_MENU_MEETINGS_MINISTRY_DELETE));

		song2Label.setText(language.getString("TEXT0099"));

		christiansPartFulltextTableColumn.setText(language.getString("TEXT0092"));
		christiansPartMinTableColumn.setText(language.getString("TEXT0093"));
		christiansPartThemeTableColumn.setText(language.getString("TEXT0094"));
		christiansPartMaterialTableColumn.setText(language.getString("TEXT0095"));
		christiansPartTeacherTableColumn.setText(language.getString("TEXT0098"));

		christiansPartAddButton.setText(null);
		christiansPartAddButton
				.setGraphic(Meta.Resources.createTabIcon(Meta.Resources.USER_MENU_MEETINGS_CHRISTIANS_ADD));
		christiansPartDeleteButton.setText(null);
		christiansPartDeleteButton
				.setGraphic(Meta.Resources.createTabIcon(Meta.Resources.USER_MENU_MEETINGS_CHRISTIANS_DELETE));

		congregationBibleStudyLabel.setText(language.getString("TEXT0061"));
		congregationBibleStudyMinLabel.setText(language.getString("TEXT0089"));

		endLabel.setText(language.getString("TEXT0103"));
		reviewLabel.setText(language.getString("TEXT0101"));
		reviewMinLabel.setText(language.getString("TEXT0089"));
		song3Label.setText(language.getString("TEXT0100"));
		pray2Label.setText(language.getString("TEXT0102"));

		loadWeekFromWOLButton.setText(null);
		loadWeekFromWOLButton.setGraphic(Meta.Resources.createTabIcon(Meta.Resources.USER_MENU_MEETINGS_WOL_LOAD));
		saveWeekButton.setText(null);
		saveWeekButton.setGraphic(Meta.Resources.createTabIcon(Meta.Resources.SAVE));
	}

	public Settings getSettings() {
		return settings;
	}

	public void setSettings(Settings settings) {
		this.settings = settings;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public Stage getOwnerStage() {
		return ownerStage;
	}

	public void setOwnerStage(Stage ownerStage) {
		this.ownerStage = ownerStage;
	}

	public UserMenuMeetings getOwnerCtrl() {
		return ownerCtrl;
	}

	public void setOwnerCtrl(UserMenuMeetings ownerCtrl) {
		this.ownerCtrl = ownerCtrl;
	}

	public Week getSelectedWeek() {
		return selectedWeek;
	}

	public void setSelectedWeek(Week selectedWeek) {
		this.selectedWeek = selectedWeek;
	}

	public TabPane getOwnerTabPane() {
		return ownerTabPane;
	}

	public void setOwnerTabPane(TabPane ownerTabPane) {
		this.ownerTabPane = ownerTabPane;
	}

	public Tab getThisTab() {
		return thisTab;
	}

	public void setThisTab(Tab thisTab) {
		this.thisTab = thisTab;
	}

	public ObservableList<MinistryPart> getMinistryPartList() {
		return ministryPartList;
	}

	public void setMinistryPartList(ObservableList<MinistryPart> ministryPartList) {
		this.ministryPartList = ministryPartList;
	}

	public ObservableList<MinistryTypeTranslated> getMinistryTypeTranslatedList() {
		return ministryTypeTranslatedList;
	}

	public void setMinistryTypeTranslatedList(ObservableList<MinistryTypeTranslated> ministryTypeTranslatedList) {
		this.ministryTypeTranslatedList = ministryTypeTranslatedList;
	}

	public ObservableList<ChristiansPart> getChristiansPartList() {
		return christiansPartList;
	}

	public void setChristiansPartList(ObservableList<ChristiansPart> christiansPartList) {
		this.christiansPartList = christiansPartList;
	}

}
