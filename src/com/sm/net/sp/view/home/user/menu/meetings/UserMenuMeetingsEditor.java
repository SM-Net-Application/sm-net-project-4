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
import com.sm.net.sp.model.WeekType;
import com.sm.net.sp.model.WeekTypeTranslated;
import com.sm.net.sp.settings.Settings;
import com.sm.net.util.Crypt;

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
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
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
	private Tab generalTab;
	@FXML
	private Tab treasuresTab;
	@FXML
	private Tab ministryTab;
	@FXML
	private Tab christiansTab;

	@FXML
	private ScrollPane treasuresScrollPane;
	@FXML
	private ScrollPane ministryScrollPane;
	@FXML
	private ScrollPane christiansScrollPane;

	@FXML
	private Label typeWeekLabel;
	@FXML
	private ListView<WeekTypeTranslated> typeWeekListView;

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
	private Label bibleReadingStudent1Label;
	@FXML
	private ComboBox<Member> bibleReadingComboBox;
	@FXML
	private Label bibleReadingStudent2Label;
	@FXML
	private ComboBox<Member> bibleReading2ComboBox;

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
	private TableColumn<MinistryPart, Member> ministryMember3TableColumn;
	@FXML
	private TableColumn<MinistryPart, Member> ministryMember4TableColumn;

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
	private ObservableList<ChristiansPart> christiansPartList;
	private ObservableList<Member> memberList;
	private ObservableList<WeekTypeTranslated> weekTypesList;

	private ObservableList<Member> presidentList;
	private ObservableList<Member> prayStartList;
	private ObservableList<Member> talkList;
	private ObservableList<Member> diggingList;
	private ObservableList<Member> bibleReadingList;
	private ObservableList<Member> congregationBibleStudyList;
	private ObservableList<Member> prayEndList;

	@FXML
	private void initialize() {
		styleClasses();

		tableViewEditCommit();
		cellValueFactory();
		cellFactory();
	}

	private void styleClasses() {

		tabPane.getStyleClass().add("tab_pane_002");

		generalTab.getStyleClass().add("tab_001");
		treasuresTab.getStyleClass().add("tab_001");
		ministryTab.getStyleClass().add("tab_001");
		christiansTab.getStyleClass().add("tab_001");

		treasuresScrollPane.getStyleClass().add("scroll_pane_001");
		ministryScrollPane.getStyleClass().add("scroll_pane_001");
		christiansScrollPane.getStyleClass().add("scroll_pane_001");

		typeWeekLabel.getStyleClass().add("label_002");
		typeWeekListView.getStyleClass().add("list_view_001");

		generalLabel.getStyleClass().add("label_002");
		treasuresLabel.getStyleClass().add("label_002");

		presidentLabel.getStyleClass().add("label_set_001");
		pray1Label.getStyleClass().add("label_set_001");
		song1Label.getStyleClass().add("label_set_001");
		bibleChaptersLabel.getStyleClass().add("label_set_001");
		openingCommentsLabel.getStyleClass().add("label_set_001");

		presidentComboBox.getStyleClass().add("combo_box_001");
		pray1ComboBox.getStyleClass().add("combo_box_001");
		song1TextField.getStyleClass().add("text_field_002");
		bibleChaptersTextField.getStyleClass().add("text_field_001");

		openingCommentsMinTextField.getStyleClass().add("text_field_002");
		openingCommentsMinLabel.getStyleClass().add("label_set_001");
		openingCommentsTextTextField.getStyleClass().add("text_field_001");

		talkLabel.getStyleClass().add("label_001");
		talkMinTextField.getStyleClass().add("text_field_002");
		talkMinLabel.getStyleClass().add("label_set_001");
		talkTextTextField.getStyleClass().add("text_field_001");
		talkComboBox.getStyleClass().add("combo_box_001");

		diggingLabel.getStyleClass().add("label_001");
		diggingMinTextField.getStyleClass().add("text_field_002");
		diggingMinLabel.getStyleClass().add("label_set_001");
		diggingTextTextField.getStyleClass().add("text_field_001");
		diggingComboBox.getStyleClass().add("combo_box_001");

		bibleReadingLabel.getStyleClass().add("label_001");
		bibleReadingMinTextField.getStyleClass().add("text_field_002");
		bibleReadingMinLabel.getStyleClass().add("label_set_001");
		bibleReadingTextTextField.getStyleClass().add("text_field_001");
		bibleReadingMaterialsTextField.getStyleClass().add("text_field_001");

		bibleReadingStudent1Label.getStyleClass().add("label_set_001");
		bibleReadingComboBox.getStyleClass().add("combo_box_001");
		bibleReadingStudent2Label.getStyleClass().add("label_set_001");
		bibleReading2ComboBox.getStyleClass().add("combo_box_001");

		ministryTableView.getStyleClass().add("table_view_001");
		ministryMinTableColumn.getStyleClass().add("tableColumnStyle1");

		ministryPartAddButton.getStyleClass().add("button_image_001");
		ministryPartDeleteButton.getStyleClass().add("button_image_001");

		song2Label.getStyleClass().add("label_001");
		song2TextField.getStyleClass().add("text_field_002");

		christiansPartTableView.getStyleClass().add("table_view_001");
		christiansPartMinTableColumn.getStyleClass().add("tableColumnStyle1");

		christiansPartAddButton.getStyleClass().add("button_image_001");
		christiansPartDeleteButton.getStyleClass().add("button_image_001");

		congregationBibleStudyLabel.getStyleClass().add("label_002");
		congregationBibleStudyMinTextField.getStyleClass().add("text_field_002");
		congregationBibleStudyMinLabel.getStyleClass().add("label_001");
		congregationBibleStudyTextTextField.getStyleClass().add("text_field_001");
		congregationBibleStudyMaterialTextField.getStyleClass().add("text_field_001");

		congregationBibleStudyComboBox.getStyleClass().add("combo_box_001");

		endLabel.getStyleClass().add("label_002");

		reviewLabel.getStyleClass().add("label_set_001");
		reviewMinTextField.getStyleClass().add("text_field_002");
		reviewMinLabel.getStyleClass().add("label_001");
		reviewTextTextField.getStyleClass().add("text_field_001");

		song3Label.getStyleClass().add("label_set_001");
		song3TextField.getStyleClass().add("text_field_002");

		pray2Label.getStyleClass().add("label_set_001");
		pray2ComboBox.getStyleClass().add("combo_box_001");

		loadWeekFromWOLButton.getStyleClass().add("button_image_001");
		saveWeekButton.getStyleClass().add("button_image_001");
	}

	private void viewUpdate() {

		this.language = settings.getLanguage();

		generalTab.setText(language.getString("TEXT0043"));
		generalTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.INFO));
		treasuresTab.setText(language.getString("TEXT0080"));
		treasuresTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.USER_MENU_MEETINGS_TREASURES));
		ministryTab.setText(language.getString("TEXT0081"));
		ministryTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.USER_MENU_MEETINGS_MINISTRY));
		christiansTab.setText(language.getString("TEXT0082"));
		christiansTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.USER_MENU_MEETINGS_CHRISTIANS));

		typeWeekLabel.setText(language.getString("TEXT0122"));
		generalLabel.setText(language.getString("TEXT0079"));
		treasuresLabel.setText(language.getString("TEXT0080"));

		presidentLabel.setText(language.getString("TEXT0134"));
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
		bibleReadingStudent1Label.setText(language.getString("TEXT0135"));
		bibleReadingStudent2Label.setText(language.getString("TEXT0136"));

		ministryTypeTableColumn.setText(language.getString("TEXT0091"));
		ministryFulltextTableColumn.setText(language.getString("TEXT0092"));
		ministryMinTableColumn.setText(language.getString("TEXT0093"));
		ministryThemeTableColumn.setText(language.getString("TEXT0094"));
		ministryMaterialTableColumn.setText(language.getString("TEXT0095"));
		ministryMember1TableColumn.setText(language.getString("TEXT0135"));
		ministryMember2TableColumn.setText(language.getString("TEXT0038"));
		ministryMember3TableColumn.setText(language.getString("TEXT0136"));
		ministryMember4TableColumn.setText(language.getString("TEXT0038"));

		ministryPartAddButton.setText(null);
		ministryPartAddButton
				.setGraphic(Meta.Resources.imageViewForButton(Meta.Resources.USER_MENU_MEETINGS_MINISTRY_ADD));
		ministryPartDeleteButton.setText(null);
		ministryPartDeleteButton
				.setGraphic(Meta.Resources.imageViewForButton(Meta.Resources.USER_MENU_MEETINGS_MINISTRY_DELETE));

		song2Label.setText(language.getString("TEXT0099"));

		christiansPartFulltextTableColumn.setText(language.getString("TEXT0092"));
		christiansPartMinTableColumn.setText(language.getString("TEXT0093"));
		christiansPartThemeTableColumn.setText(language.getString("TEXT0094"));
		christiansPartMaterialTableColumn.setText(language.getString("TEXT0095"));
		christiansPartTeacherTableColumn.setText(language.getString("TEXT0098"));

		christiansPartAddButton.setText(null);
		christiansPartAddButton
				.setGraphic(Meta.Resources.imageViewForButton(Meta.Resources.USER_MENU_MEETINGS_CHRISTIANS_ADD));
		christiansPartDeleteButton.setText(null);
		christiansPartDeleteButton
				.setGraphic(Meta.Resources.imageViewForButton(Meta.Resources.USER_MENU_MEETINGS_CHRISTIANS_DELETE));

		congregationBibleStudyLabel.setText(language.getString("TEXT0061"));
		congregationBibleStudyMinLabel.setText(language.getString("TEXT0089"));

		endLabel.setText(language.getString("TEXT0103"));
		reviewLabel.setText(language.getString("TEXT0101"));
		reviewMinLabel.setText(language.getString("TEXT0089"));
		song3Label.setText(language.getString("TEXT0100"));
		pray2Label.setText(language.getString("TEXT0102"));

		loadWeekFromWOLButton.setText(null);
		loadWeekFromWOLButton.setGraphic(Meta.Resources.imageViewForButton(Meta.Resources.USER_MENU_MEETINGS_WOL_LOAD));
		saveWeekButton.setText(null);
		saveWeekButton.setGraphic(Meta.Resources.imageViewForButton(Meta.Resources.SAVE));
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
		ministryMember3TableColumn.setCellValueFactory(cellData -> cellData.getValue().student2Property());
		ministryMember4TableColumn.setCellValueFactory(cellData -> cellData.getValue().assistant2Property());

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
		initData();
		listeners();
	}

	private void initData() {

		if (memberList == null)
			memberList = FXCollections.observableArrayList();

		ministryTypeTranslatedList = MinistryType.getAllMinistryTypeTranslated(language);

		ministryPartList = FXCollections.observableArrayList();

		ministryTableView.setItems(ministryPartList);

		christiansPartList = FXCollections.observableArrayList();

		christiansPartTableView.setItems(christiansPartList);

		weekTypesList = FXCollections.observableArrayList();
		weekTypesList.addAll(WeekType.getNotEmptyWeekTypeTranslated(language));
		this.typeWeekListView.setItems(weekTypesList);
		if (weekTypesList.size() > 0)
			this.typeWeekListView.getSelectionModel().select(0);

		presidentList = FXCollections.observableArrayList();
		prayStartList = FXCollections.observableArrayList();
		talkList = FXCollections.observableArrayList();
		diggingList = FXCollections.observableArrayList();
		bibleReadingList = FXCollections.observableArrayList();
		congregationBibleStudyList = FXCollections.observableArrayList();
		prayEndList = FXCollections.observableArrayList();

		addEmptyMember();

		presidentComboBox.setItems(presidentList);
		pray1ComboBox.setItems(prayStartList);
		talkComboBox.setItems(talkList);
		diggingComboBox.setItems(diggingList);
		bibleReadingComboBox.setItems(bibleReadingList);
		bibleReading2ComboBox.setItems(bibleReadingList);
		congregationBibleStudyComboBox.setItems(congregationBibleStudyList);
		pray2ComboBox.setItems(prayEndList);

		selectFirst();

		// updateMembers();
		updateLists();
		loadSelectedWeek();
	}

	private void setMemberComboBoxIndex(ComboBox<Member> cb, int id) {

		cb.getSelectionModel().selectFirst();

		for (int i = 0; i < cb.getItems().size(); i++) {

			Member member = cb.getItems().get(i);
			if (member.getSpMemberID() == id) {
				cb.getSelectionModel().select(i);
				break;
			}
		}
	}

	// @Override
	// public void updateMembers() {
	// super.updateMembers();
	// Actions.getAllMembers(settings, ownerStage, this);
	// }
	//
	// @Override
	// public void updateMembers(ObservableList<Member> list) {
	// super.updateMembers(list);
	// memberList = list;
	// updateLists();
	// loadSelectedWeek();
	// }

	private void updateLists() {
		resetLists();
		addEmptyMember();
		setLists();
		selectFirst();
	}

	private void selectFirst() {

		presidentComboBox.getSelectionModel().selectFirst();
		pray1ComboBox.getSelectionModel().selectFirst();
		talkComboBox.getSelectionModel().selectFirst();
		diggingComboBox.getSelectionModel().selectFirst();
		bibleReadingComboBox.getSelectionModel().selectFirst();
		bibleReading2ComboBox.getSelectionModel().selectFirst();
		congregationBibleStudyComboBox.getSelectionModel().selectFirst();
		pray2ComboBox.getSelectionModel().selectFirst();
	}

	private void setLists() {
		for (Member member : this.memberList) {

			if (member.getSpInf33() == 1)
				this.presidentList.add(member);
			if (member.getSpInf34() == 1)
				this.prayStartList.add(member);
			if (member.getSpInf35() == 1)
				this.talkList.add(member);
			if (member.getSpInf31() == 1)
				this.diggingList.add(member);
			if (member.getSpInf13() == 1)
				this.bibleReadingList.add(member);
			if (member.getSpInf42() == 1)
				this.congregationBibleStudyList.add(member);
			if (member.getSpInf35() == 1)
				this.prayEndList.add(member);
		}
	}

	private void addEmptyMember() {
		this.presidentList.add(Member.emptyMember(language));
		this.prayStartList.add(Member.emptyMember(language));
		this.talkList.add(Member.emptyMember(language));
		this.diggingList.add(Member.emptyMember(language));
		this.bibleReadingList.add(Member.emptyMember(language));
		this.congregationBibleStudyList.add(Member.emptyMember(language));
		this.prayEndList.add(Member.emptyMember(language));
	}

	private void resetLists() {
		this.presidentList.clear();
		this.prayStartList.clear();
		this.talkList.clear();
		this.diggingList.clear();
		this.bibleReadingList.clear();
		this.congregationBibleStudyList.clear();
		this.prayEndList.clear();
	}

	private void loadSelectedWeek() {

		if (this.selectedWeek != null)
			if (this.selectedWeek.spWeekIDProperty() != null) {

				// Week type
				for (int i = 0; i < typeWeekListView.getItems().size(); i++) {

					WeekTypeTranslated weekTypeTranslated = typeWeekListView.getItems().get(i);
					if (weekTypeTranslated.getOrdinal() == this.selectedWeek.getSpInf2()) {
						typeWeekListView.getSelectionModel().select(i);
						break;
					}
				}

				setMemberComboBoxIndex(this.presidentComboBox, this.selectedWeek.getSpInf3());
				setMemberComboBoxIndex(this.pray1ComboBox, this.selectedWeek.getSpInf4());
				this.song1TextField.setText(this.selectedWeek.getSpInf5());
				this.bibleChaptersTextField.setText(this.selectedWeek.getSpInf6());
				this.openingCommentsMinTextField.setText(this.selectedWeek.getSpInf7());
				this.openingCommentsTextTextField.setText(this.selectedWeek.getSpInf8());
				this.talkMinTextField.setText(this.selectedWeek.getSpInf9());
				this.talkTextTextField.setText(this.selectedWeek.getSpInf10());
				setMemberComboBoxIndex(this.talkComboBox, this.selectedWeek.getSpInf11());
				this.diggingMinTextField.setText(this.selectedWeek.getSpInf12());
				this.diggingTextTextField.setText(this.selectedWeek.getSpInf13());
				setMemberComboBoxIndex(this.diggingComboBox, this.selectedWeek.getSpInf14());
				this.bibleReadingMinTextField.setText(this.selectedWeek.getSpInf15());
				this.bibleReadingTextTextField.setText(this.selectedWeek.getSpInf16());
				this.bibleReadingMaterialsTextField.setText(this.selectedWeek.getSpInf17());
				setMemberComboBoxIndex(this.bibleReadingComboBox, this.selectedWeek.getSpInf18());
				this.song2TextField.setText(this.selectedWeek.getSpInf19());
				this.congregationBibleStudyMinTextField.setText(this.selectedWeek.getSpInf20());
				this.congregationBibleStudyTextTextField.setText(this.selectedWeek.getSpInf21());
				this.congregationBibleStudyMaterialTextField.setText(this.selectedWeek.getSpInf22());
				setMemberComboBoxIndex(this.congregationBibleStudyComboBox, this.selectedWeek.getSpInf23());
				this.reviewMinTextField.setText(this.selectedWeek.getSpInf24());
				this.reviewTextTextField.setText(this.selectedWeek.getSpInf25());
				this.song3TextField.setText(this.selectedWeek.getSpInf26());
				setMemberComboBoxIndex(this.pray2ComboBox, this.selectedWeek.getSpInf27());
				setMemberComboBoxIndex(this.bibleReading2ComboBox, this.selectedWeek.getSpInf28());

				this.ministryPartList.addAll(this.selectedWeek.getMinistryPartList());
				this.christiansPartList.addAll(this.selectedWeek.getChristiansPartList());

				this.ministryTableView.refresh();
				this.christiansPartTableView.refresh();
			}
	}

	private void listeners() {
		listenerMinistryTableView();
		listenerMinistryPartAddButton();
		listenerMinistryPartDeleteButton();

		listenerChristiansPartTableView();
		listenerChristiansPartAddButton();
		listenerChristiansPartDeleteButton();

		listenerLoadWeekFromWOLButton();

		listenerSaveWeekButton();
	}

	private void listenerSaveWeekButton() {
		saveWeekButton.setOnAction(event -> saveWeek());
	}

	private void saveWeek() {

		if (checkFields()) {

			String spInf2 = String.valueOf(WeekType.STANDARD.getOrdinal());
			if (typeWeekListView.getSelectionModel().getSelectedIndex() > -1) {
				WeekTypeTranslated weekTypeTranslated = typeWeekListView.getSelectionModel().getSelectedItem();
				spInf2 = String.valueOf(weekTypeTranslated.getOrdinal());
			}

			String spInf3 = String.valueOf(presidentComboBox.getSelectionModel().getSelectedItem().getSpMemberID());
			String spInf4 = String.valueOf(pray1ComboBox.getSelectionModel().getSelectedItem().getSpMemberID());
			String spInf5 = Crypt.encrypt(song1TextField.getText(), settings.getDatabaseSecretKey());
			String spInf6 = Crypt.encrypt(bibleChaptersTextField.getText(), settings.getDatabaseSecretKey());
			String spInf7 = Crypt.encrypt(openingCommentsMinTextField.getText(), settings.getDatabaseSecretKey());
			String spInf8 = Crypt.encrypt(openingCommentsTextTextField.getText(), settings.getDatabaseSecretKey());
			String spInf9 = Crypt.encrypt(talkMinTextField.getText(), settings.getDatabaseSecretKey());
			String spInf10 = Crypt.encrypt(talkTextTextField.getText(), settings.getDatabaseSecretKey());
			String spInf11 = String.valueOf(talkComboBox.getSelectionModel().getSelectedItem().getSpMemberID());
			String spInf12 = Crypt.encrypt(diggingMinTextField.getText(), settings.getDatabaseSecretKey());
			String spInf13 = Crypt.encrypt(diggingTextTextField.getText(), settings.getDatabaseSecretKey());
			String spInf14 = String.valueOf(diggingComboBox.getSelectionModel().getSelectedItem().getSpMemberID());
			String spInf15 = Crypt.encrypt(bibleReadingMinTextField.getText(), settings.getDatabaseSecretKey());
			String spInf16 = Crypt.encrypt(bibleReadingTextTextField.getText(), settings.getDatabaseSecretKey());
			String spInf17 = Crypt.encrypt(bibleReadingMaterialsTextField.getText(), settings.getDatabaseSecretKey());
			String spInf18 = String.valueOf(bibleReadingComboBox.getSelectionModel().getSelectedItem().getSpMemberID());
			String spInf19 = Crypt.encrypt(song2TextField.getText(), settings.getDatabaseSecretKey());
			String spInf20 = Crypt.encrypt(congregationBibleStudyMinTextField.getText(),
					settings.getDatabaseSecretKey());
			String spInf21 = Crypt.encrypt(congregationBibleStudyTextTextField.getText(),
					settings.getDatabaseSecretKey());
			String spInf22 = Crypt.encrypt(congregationBibleStudyMaterialTextField.getText(),
					settings.getDatabaseSecretKey());
			String spInf23 = String
					.valueOf(congregationBibleStudyComboBox.getSelectionModel().getSelectedItem().getSpMemberID());
			String spInf24 = Crypt.encrypt(reviewMinTextField.getText(), settings.getDatabaseSecretKey());
			String spInf25 = Crypt.encrypt(reviewTextTextField.getText(), settings.getDatabaseSecretKey());
			String spInf26 = Crypt.encrypt(song3TextField.getText(), settings.getDatabaseSecretKey());
			String spInf27 = String.valueOf(pray2ComboBox.getSelectionModel().getSelectedItem().getSpMemberID());
			String spInf28 = String
					.valueOf(bibleReading2ComboBox.getSelectionModel().getSelectedItem().getSpMemberID());

			String spInfMinistryParts = getMinistryParts();
			String spInfChristiansParts = getChristiansParts();

			if (this.selectedWeek.spWeekIDProperty() != null) {
				// editWeek

				String spWeekID = String.valueOf(selectedWeek.getSpWeekID());
				String spInf1 = String.valueOf(selectedWeek.getSpInf1());

				Actions.updateWeek(spWeekID, spInf1, spInf2, spInf3, spInf4, spInf5, spInf6, spInf7, spInf8, spInf9,
						spInf10, spInf11, spInf12, spInf13, spInf14, spInf15, spInf16, spInf17, spInf18, spInf19,
						spInf20, spInf21, spInf22, spInf23, spInf24, spInf25, spInf26, spInf27, spInf28,
						spInfMinistryParts, spInfChristiansParts, settings, ownerStage, ownerTabPane, thisTab,
						ownerCtrl);

			} else {
				// newWeek

				String spInf1 = Week.buildKey(this.selectedWeek.getTo());

				Actions.insertWeek(spInf1, spInf2, spInf3, spInf4, spInf5, spInf6, spInf7, spInf8, spInf9, spInf10,
						spInf11, spInf12, spInf13, spInf14, spInf15, spInf16, spInf17, spInf18, spInf19, spInf20,
						spInf21, spInf22, spInf23, spInf24, spInf25, spInf26, spInf27, spInf28, spInfMinistryParts,
						spInfChristiansParts, settings, ownerStage, ownerTabPane, thisTab, ownerCtrl);
			}
		}
	}

	private String getMinistryParts() {

		String ministryParts = "";

		int count = 0;
		for (MinistryPart mp : ministryPartList) {

			count += 1;

			String spInf1 = Week.buildKey(this.getSelectedWeek().getTo());
			String spInf2 = String.valueOf(count);
			String spInf3 = String.valueOf(mp.getMinistryTypeTranslated().getOrdinal());
			String spInf4 = Crypt.encrypt(String.valueOf(mp.getMin()), settings.getDatabaseSecretKey());
			String spInf5 = Crypt.encrypt(mp.getTheme(), settings.getDatabaseSecretKey());
			String spInf6 = Crypt.encrypt(mp.getMaterial(), settings.getDatabaseSecretKey());
			String spInf7 = String.valueOf(mp.getStudent().getSpMemberID());
			String spInf8 = String.valueOf(mp.getAssistant().getSpMemberID());
			String spInf9 = String.valueOf(mp.getStudent2().getSpMemberID());
			String spInf10 = String.valueOf(mp.getAssistant2().getSpMemberID());

			String insert = "(";
			insert += spInf1;
			insert += ", " + spInf2;
			insert += ", " + spInf3;
			insert += ", '" + spInf4 + "'";
			insert += ", '" + spInf5 + "'";
			insert += ", '" + spInf6 + "'";
			insert += ", " + spInf7;
			insert += ", " + spInf8;
			insert += ", " + spInf9;
			insert += ", " + spInf10;
			insert += ")";

			ministryParts += ministryParts.isEmpty() ? insert : ", " + insert;
		}

		return ministryParts;
	}

	private String getChristiansParts() {

		String christiansParts = "";

		int count = 0;
		for (ChristiansPart cp : christiansPartList) {

			count += 1;

			String spInf1 = Week.buildKey(this.getSelectedWeek().getTo());
			String spInf2 = String.valueOf(count);
			String spInf3 = Crypt.encrypt(String.valueOf(cp.getMin()), settings.getDatabaseSecretKey());
			String spInf4 = Crypt.encrypt(cp.getTheme(), settings.getDatabaseSecretKey());
			String spInf5 = Crypt.encrypt(cp.getMaterial(), settings.getDatabaseSecretKey());
			String spInf6 = String.valueOf(cp.getTeacher().getSpMemberID());

			String insert = "(";
			insert += spInf1;
			insert += ", " + spInf2;
			insert += ", '" + spInf3 + "'";
			insert += ", '" + spInf4 + "'";
			insert += ", '" + spInf5 + "'";
			insert += ", " + spInf6;
			insert += ")";

			christiansParts += christiansParts.isEmpty() ? insert : ", " + insert;
		}

		return christiansParts;
	}

	private boolean checkFields() {

		// TODO: Check all fields
		boolean status = true;

		return status;
	}

	private void listenerLoadWeekFromWOLButton() {
		loadWeekFromWOLButton.setOnAction(event -> loadWeekFromWOLOnAction());
	}

	private void loadWeekFromWOLOnAction() {

		Alert alert = new AlertDesigner(language.getString("TEXT0137"), language.getString("TEXT0138"), ownerStage,
				AlertType.CONFIRMATION, Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon());

		if (alert.showAndWait().get() == ButtonType.OK) {
			ScheduleForMeetingHTML scheduleForMeetingHTML = new ScheduleForMeetingHTML(language,
					selectedWeek.getFrom());
			scheduleForMeetingHTML.download();

			if (scheduleForMeetingHTML != null) {
				ScheduleForMeeting scheduleForMeeting = new ScheduleForMeeting(scheduleForMeetingHTML.getRelevantRows(),
						language);
				if (scheduleForMeeting != null) {
					if (scheduleForMeeting.isPresent()) {

						song1TextField.setText(scheduleForMeeting.getSong1().getSongNo().toString());

						bibleChaptersTextField.setText(scheduleForMeeting.getBibleChapters());

						openingCommentsMinTextField
								.setText(scheduleForMeeting.getOpeningComments().getMin().toString());
						openingCommentsTextTextField.setText(scheduleForMeeting.getOpeningComments().getTitle());

						talkMinTextField.setText(scheduleForMeeting.getTreasuresTalk().getMin().toString());
						talkTextTextField.setText(scheduleForMeeting.getTreasuresTalk().getTitle());

						diggingMinTextField.setText(scheduleForMeeting.getTreasuresDigging().getMin().toString());
						diggingTextTextField.setText(scheduleForMeeting.getTreasuresDigging().getTitle());

						bibleReadingMinTextField
								.setText(scheduleForMeeting.getTreasuresBibleReading().getMin().toString());
						bibleReadingTextTextField.setText(scheduleForMeeting.getTreasuresBibleReading().getTextPart());
						bibleReadingMaterialsTextField
								.setText(scheduleForMeeting.getTreasuresBibleReading().getBible());

						ministryPartList.clear();
						for (ScheduleForMeeting.MinistryPart part : scheduleForMeeting.getMinistryPartsList())
							ministryPartList.add(new MinistryPart(part.getMinistryTypeTranslated(), part.getText(),
									part.getMin(), part.getTextPart(), part.getMaterial(), Member.emptyMember(language),
									Member.emptyMember(language), Member.emptyMember(language),
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

					} else {
						new AlertDesigner(language.getString("sp.meetings.wol.error"), ownerStage, AlertType.ERROR,
								Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon())
										.showAndWait();
					}
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
					ownerStage, AlertType.CONFIRMATION, Meta.Application.getFullTitle(),
					Meta.Resources.getImageApplicationIcon());

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
			stage.getIcons().add(Meta.Resources.getImageApplicationIcon());

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
					Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon());

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
			stage.getIcons().add(Meta.Resources.getImageApplicationIcon());

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

	public ObservableList<Member> getMemberList() {
		return memberList;
	}

	public void setMemberList(ObservableList<Member> memberList) {
		this.memberList = memberList;
	}

}
