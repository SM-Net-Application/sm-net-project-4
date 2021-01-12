package com.sm.net.sp.view.home.user.menu.meetings;

import java.io.IOException;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sm.net.javafx.AlertDesigner;
import com.sm.net.jw.wol.MinistryType;
import com.sm.net.jw.wol.MinistryTypeTranslated;
import com.sm.net.jw.wol.WatchtowerOnlineLibrary;
import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.actions.Actions;
import com.sm.net.sp.dialogs.place.PlaceDialog;
import com.sm.net.sp.model.ChristiansPart;
import com.sm.net.sp.model.DateAndTime;
import com.sm.net.sp.model.EnumPlaceType;
import com.sm.net.sp.model.Member;
import com.sm.net.sp.model.MinistryPart;
import com.sm.net.sp.model.Place;
import com.sm.net.sp.model.Privileges;
import com.sm.net.sp.model.UpdateDataAdapter;
import com.sm.net.sp.model.Week;
import com.sm.net.sp.model.WeekAudio;
import com.sm.net.sp.model.WeekOverseer;
import com.sm.net.sp.model.WeekType;
import com.sm.net.sp.model.WeekTypeTranslated;
import com.sm.net.sp.model.WeekUsciere;
import com.sm.net.sp.settings.Settings;
import com.sm.net.sp.utils.AlertBuilderOld;
import com.sm.net.sp.utils.DateAndTimeUtils;
import com.sm.net.sp.utils.PlaceUtils;
import com.sm.net.sp.utils.WOLUtils;
import com.sm.net.sp.view.SupportPlannerView;
import com.sm.net.sp.view.history.History;
import com.sm.net.sp.view.history.UpgradeableComboBoxSelection;
import com.sm.net.sp.view.wolbrowser.WOLBrowser;
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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.StringConverter;

public class UserMenuMeetingsEditor extends UpdateDataAdapter implements UpgradeableComboBoxSelection {

	@FXML
	private Button wolViewButton;
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
	private Tab watchtowerTab;

	@FXML
	private ScrollPane treasuresScrollPane;
	@FXML
	private ScrollPane ministryScrollPane;
	@FXML
	private ScrollPane christiansScrollPane;
	@FXML
	private ScrollPane watchtowerScrollPane;

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
	// @FXML
	// private TableColumn<MinistryPart, String> ministryFulltextTableColumn;
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
	// @FXML
	// private TableColumn<ChristiansPart, String>
	// christiansPartFulltextTableColumn;
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

	@FXML
	private Label publicTalkLabel;
	@FXML
	private Label presidentPublicMeetingLabel;
	@FXML
	private ComboBox<Member> presidentPublicMeetingComboBox;
	@FXML
	private Label publicTalkSongLabel;
	@FXML
	private TextField publicTalkSongTextField;
	@FXML
	private Label publicTalkThemeLabel;
	@FXML
	private TextField publicTalkThemeTextField;
	@FXML
	private Label publicTalkTalkerLabel;
	@FXML
	private TextField publicTalkTalkerTextField;
	@FXML
	private Label publicTalkTalkerCongrLabel;
	@FXML
	private TextField publicTalkTalkerCongrTextField;

	@FXML
	private Label watchtowerStudyLabel;
	@FXML
	private Label watchtowerStudySong2Label;
	@FXML
	private TextField watchtowerStudySong2TextField;
	@FXML
	private Label watchtowerStudyThemeLabel;
	@FXML
	private TextField watchtowerStudyThemeTextField;
	@FXML
	private Label watchtowerStudyConductorLabel;
	@FXML
	private ComboBox<Member> watchtowerStudyConductorComboBox;
	@FXML
	private Label watchtowerStudyReaderLabel;
	@FXML
	private ComboBox<Member> watchtowerStudyReaderComboBox;
	@FXML
	private Label watchtowerStudySong3Label;
	@FXML
	private TextField watchtowerStudySong3TextField;
	@FXML
	private Label watchtowerStudyPray2Label;
	@FXML
	private ComboBox<Member> watchtowerStudyPray2ComboBox;

	@FXML
	private Label congregationBibleStudyConductorLabel;
	@FXML
	private Label congregationBibleStudyReaderLabel;
	@FXML
	private ComboBox<Member> congregationBibleStudyReaderComboBox;

	@FXML
	private Label dateAndTimeTitleLabel;
	@FXML
	private Label dateAndTimeMidLabel;
	@FXML
	private Label dateAndTimeWeekendLabel;
	@FXML
	private Label time1Label;
	@FXML
	private Label timeSeparator1Label;
	@FXML
	private Label time2Label;
	@FXML
	private Label timeSeparator2Label;

	@FXML
	private Label placeLabel;
	@FXML
	private Label place1AddressLabel;
	@FXML
	private Label place2AddressLabel;

	@FXML
	private CheckBox day1CheckBox;
	@FXML
	private CheckBox day2CheckBox;
	@FXML
	private CheckBox day3CheckBox;
	@FXML
	private CheckBox day4CheckBox;
	@FXML
	private CheckBox day5CheckBox;
	@FXML
	private CheckBox day6CheckBox;
	@FXML
	private CheckBox day7CheckBox;

	@FXML
	private ComboBox<Integer> hours1ComboBox;
	@FXML
	private ComboBox<Integer> minute1ComboBox;
	@FXML
	private ComboBox<Integer> hours2ComboBox;
	@FXML
	private ComboBox<Integer> minute2ComboBox;

	@FXML
	private TextField place1TextField;
	@FXML
	private Button place1SelectButton;
	@FXML
	private Label place1PrintLabel;
	@FXML
	private CheckBox place1PrintCheckBox;

	@FXML
	private TextField place2TextField;
	@FXML
	private Button place2SelectButton;
	@FXML
	private Label place2PrintLabel;
	@FXML
	private CheckBox place2PrintCheckBox;

	@FXML
	private CheckBox presidentPublicMeetingOnlyPrayCheckBox;

	@FXML
	private Label publicTalkMinLabel;
	@FXML
	private TextField publicTalkMinTextField;

	@FXML
	private Label watchtowerStudyMinLabel;
	@FXML
	private TextField watchtowerStudyMinTextField;

	@FXML
	private Label publicTalkOnlyPray1Label;

	@FXML
	private Label conductorSecondHallLabel;
	@FXML
	private ComboBox<Member> conductorSecondHallComboBox;

	@FXML
	private Label midweekSettingsLabel;
	@FXML
	private CheckBox midweekNoPrintCheckBox;
	@FXML
	private Label weekendSettingsLabel;
	@FXML
	private CheckBox weekendOnlyWatchtowerStudyCheckBox;
	@FXML
	private Label weekendEventExtraLabel;
	@FXML
	private Label weekendEventExtraHeaderLabel;
	@FXML
	private Label weekendEventExtraContentLabel;
	@FXML
	private TextField weekendEventExtraHeaderTextField;
	@FXML
	private TextField weekendEventExtraContentTextField;

	@FXML
	private TextField midweekSong1TitelTextField;
	@FXML
	private TextField midweekSong2TitelTextField;
	@FXML
	private TextField midweekSong3TitelTextField;
	@FXML
	private TextField weekendSong1TitelTextField;
	@FXML
	private TextField weekendSong2TitelTextField;
	@FXML
	private TextField weekendSong3TitelTextField;
	@FXML
	private CheckBox specialTalkCheckBox;
	@FXML
	private Label internSpeakerLabel;
	@FXML
	private ComboBox<Member> internSpeakerComboBox;

	private SupportPlannerView application;

	private Settings settings;
	private Language language;
	private Stage ownerStage;
	private UserMenuMeetings ownerCtrl;
	private Week selectedWeek;
	private TabPane ownerTabPane;
	private Tab thisTab;

	private Tab circuitOverseerTab;

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

	private ObservableList<Member> presidentPublicMeetingList;
	private ObservableList<Member> watchtowerStudyList;
	private ObservableList<Member> watchtowerReaderList;
	private ObservableList<Member> prayEndPublicMeetingList;

	private ObservableList<Member> congregationBibleStudyReaderList;

	private ObservableList<Week> databaseWeeks;

	private AlertBuilderOld alertBuilder;

	private boolean listenerCheckFields;

	private ObservableList<Member> conductorSecondHallList;

	private HashMap<String, String> configs;
	private ObservableList<WeekAudio> databaseWeeksAudio;
	private ObservableList<WeekUsciere> databaseWeeksUsciere;

	private ObservableList<Member> internSpeakerList;

	@FXML
	private void initialize() {
		styleClasses();

		tableViewEditCommit();
		cellValueFactory();
		cellFactory();
	}

	public void objectInitialize() {

		this.listenerCheckFields = false;

		viewUpdate();
		contextMenu();
		initData();
		listeners();

		this.listenerCheckFields = true;
	}

	private void styleClasses() {

		tabPane.getStyleClass().add("tab_pane_003");

		generalTab.getStyleClass().add("tab_001");
		treasuresTab.getStyleClass().add("tab_001");
		ministryTab.getStyleClass().add("tab_001");
		christiansTab.getStyleClass().add("tab_001");
		watchtowerTab.getStyleClass().add("tab_001");

		treasuresScrollPane.getStyleClass().add("scroll_pane_001");
		ministryScrollPane.getStyleClass().add("scroll_pane_001");
		christiansScrollPane.getStyleClass().add("scroll_pane_001");
		watchtowerScrollPane.getStyleClass().add("scroll_pane_001");

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
		ministryMinTableColumn.getStyleClass().add("table_column_002");

		ministryPartAddButton.getStyleClass().add("button_image_001");
		ministryPartDeleteButton.getStyleClass().add("button_image_001");

		song2Label.getStyleClass().add("label_001");
		song2TextField.getStyleClass().add("text_field_002");

		christiansPartTableView.getStyleClass().add("table_view_001");
		christiansPartMinTableColumn.getStyleClass().add("table_column_002");

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

		publicTalkLabel.getStyleClass().add("label_002");

		presidentPublicMeetingLabel.getStyleClass().add("label_set_001");
		publicTalkSongLabel.getStyleClass().add("label_set_001");
		publicTalkThemeLabel.getStyleClass().add("label_set_001");
		publicTalkTalkerLabel.getStyleClass().add("label_set_001");
		presidentPublicMeetingComboBox.getStyleClass().add("combo_box_001");
		publicTalkSongTextField.getStyleClass().add("text_field_002");
		publicTalkThemeTextField.getStyleClass().add("text_field_001");
		publicTalkTalkerTextField.getStyleClass().add("text_field_001");
		publicTalkTalkerCongrLabel.getStyleClass().add("label_set_001");
		publicTalkTalkerCongrTextField.getStyleClass().add("text_field_001");

		this.publicTalkOnlyPray1Label.getStyleClass().add("label_set_001");

		watchtowerStudyLabel.getStyleClass().add("label_002");
		watchtowerStudySong2Label.getStyleClass().add("label_set_001");
		watchtowerStudyThemeLabel.getStyleClass().add("label_set_001");
		watchtowerStudyConductorLabel.getStyleClass().add("label_set_001");
		watchtowerStudyReaderLabel.getStyleClass().add("label_set_001");
		watchtowerStudySong3Label.getStyleClass().add("label_set_001");
		watchtowerStudyPray2Label.getStyleClass().add("label_set_001");
		watchtowerStudySong2TextField.getStyleClass().add("text_field_002");
		watchtowerStudyThemeTextField.getStyleClass().add("text_field_001");
		watchtowerStudySong3TextField.getStyleClass().add("text_field_002");
		watchtowerStudyConductorComboBox.getStyleClass().add("combo_box_001");
		watchtowerStudyReaderComboBox.getStyleClass().add("combo_box_001");
		watchtowerStudyPray2ComboBox.getStyleClass().add("combo_box_001");

		congregationBibleStudyConductorLabel.getStyleClass().add("label_set_001");
		congregationBibleStudyReaderLabel.getStyleClass().add("label_001");
		congregationBibleStudyReaderComboBox.getStyleClass().add("combo_box_001");

		wolViewButton.getStyleClass().add("button_image_001");
		loadWeekFromWOLButton.getStyleClass().add("button_image_001");
		saveWeekButton.getStyleClass().add("button_image_001");

		this.dateAndTimeTitleLabel.getStyleClass().add("label_002");

		this.dateAndTimeMidLabel.getStyleClass().add("label_001");
		this.dateAndTimeWeekendLabel.getStyleClass().add("label_001");

		this.time1Label.getStyleClass().add("label_001");
		this.timeSeparator1Label.getStyleClass().add("label_001");
		this.time2Label.getStyleClass().add("label_001");
		this.timeSeparator2Label.getStyleClass().add("label_001");

		this.hours1ComboBox.getStyleClass().add("combo_box_002");
		this.minute1ComboBox.getStyleClass().add("combo_box_002");
		this.hours2ComboBox.getStyleClass().add("combo_box_002");
		this.minute2ComboBox.getStyleClass().add("combo_box_002");

		this.day1CheckBox.getStyleClass().add("check_box_001");
		this.day2CheckBox.getStyleClass().add("check_box_001");
		this.day3CheckBox.getStyleClass().add("check_box_001");
		this.day4CheckBox.getStyleClass().add("check_box_001");
		this.day5CheckBox.getStyleClass().add("check_box_001");
		this.day6CheckBox.getStyleClass().add("check_box_001");
		this.day7CheckBox.getStyleClass().add("check_box_001");

		this.placeLabel.getStyleClass().add("label_002");

		this.place1AddressLabel.getStyleClass().add("label_set_001");
		this.place2AddressLabel.getStyleClass().add("label_set_001");
		this.place1PrintLabel.getStyleClass().add("label_set_001");
		this.place2PrintLabel.getStyleClass().add("label_set_001");

		this.place1TextField.getStyleClass().add("text_field_001");
		this.place2TextField.getStyleClass().add("text_field_001");

		this.place1SelectButton.getStyleClass().add("button_image_001");
		this.place2SelectButton.getStyleClass().add("button_image_001");

		this.place1PrintCheckBox.getStyleClass().add("check_box_001");
		this.place2PrintCheckBox.getStyleClass().add("check_box_001");
		this.presidentPublicMeetingOnlyPrayCheckBox.getStyleClass().add("check_box_set_001");

		this.publicTalkMinLabel.getStyleClass().add("label_set_001");
		this.publicTalkMinTextField.getStyleClass().add("text_field_002");

		this.watchtowerStudyMinLabel.getStyleClass().add("label_set_001");
		this.watchtowerStudyMinTextField.getStyleClass().add("text_field_002");

		this.midweekSettingsLabel.getStyleClass().add("label_002");
		this.midweekNoPrintCheckBox.getStyleClass().add("check_box_001");
		this.weekendSettingsLabel.getStyleClass().add("label_002");
		this.weekendOnlyWatchtowerStudyCheckBox.getStyleClass().add("check_box_001");
		this.weekendEventExtraLabel.getStyleClass().add("label_002");
		this.weekendEventExtraHeaderLabel.getStyleClass().add("label_set_001");
		this.weekendEventExtraContentLabel.getStyleClass().add("label_set_001");
		this.weekendEventExtraHeaderTextField.getStyleClass().add("text_field_001");
		this.weekendEventExtraContentTextField.getStyleClass().add("text_field_001");

		this.conductorSecondHallLabel.getStyleClass().add("label_001");
		this.conductorSecondHallComboBox.getStyleClass().add("combo_box_001");

		this.midweekSong1TitelTextField.getStyleClass().add("text_field_001");
		this.midweekSong2TitelTextField.getStyleClass().add("text_field_001");
		this.midweekSong3TitelTextField.getStyleClass().add("text_field_001");
		this.weekendSong1TitelTextField.getStyleClass().add("text_field_001");
		this.weekendSong2TitelTextField.getStyleClass().add("text_field_001");
		this.weekendSong3TitelTextField.getStyleClass().add("text_field_001");

		this.specialTalkCheckBox.getStyleClass().add("check_box_001");
		this.internSpeakerLabel.getStyleClass().add("label_set_001");
		this.internSpeakerComboBox.getStyleClass().add("combo_box_001");
	}

	private void viewUpdate() {

		this.language = settings.getLanguage();

		this.tabPane.setTabMinHeight(75);
		this.tabPane.setTabMaxHeight(75);

		Tooltip generalTabTooltip = new Tooltip(language.getString("TEXT0043"));
		generalTabTooltip.getStyleClass().add("tooltip_001");
		this.generalTab.setTooltip(generalTabTooltip);
		this.generalTab.setText("");
		this.generalTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.INFO));

		Tooltip treasuresTabTooltip = new Tooltip(language.getString("TEXT0080"));
		treasuresTabTooltip.getStyleClass().add("tooltip_001");
		this.treasuresTab.setTooltip(treasuresTabTooltip);
		this.treasuresTab.setText("");
		this.treasuresTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.USER_MENU_MEETINGS_TREASURES));

		Tooltip ministryTabTooltip = new Tooltip(language.getString("TEXT0081"));
		ministryTabTooltip.getStyleClass().add("tooltip_001");
		this.ministryTab.setTooltip(ministryTabTooltip);
		this.ministryTab.setText("");
		this.ministryTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.USER_MENU_MEETINGS_MINISTRY));

		Tooltip christiansTabTooltip = new Tooltip(language.getString("TEXT0082"));
		christiansTabTooltip.getStyleClass().add("tooltip_001");
		this.christiansTab.setTooltip(christiansTabTooltip);
		this.christiansTab.setText("");
		this.christiansTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.USER_MENU_MEETINGS_CHRISTIANS));

		Tooltip watchtowerTabTooltip = new Tooltip(language.getString("sp.meetings.publicmeeting"));
		watchtowerTabTooltip.getStyleClass().add("tooltip_001");
		this.watchtowerTab.setTooltip(watchtowerTabTooltip);
		this.watchtowerTab.setText("");
		this.watchtowerTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.WOL));

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
		// ministryFulltextTableColumn.setText(language.getString("TEXT0092"));

		this.ministryMinTableColumn.setText(language.getString("TEXT0093"));
		this.ministryMinTableColumn.setMinWidth(50);
		this.ministryMinTableColumn.setMaxWidth(50);

		ministryThemeTableColumn.setText(language.getString("TEXT0094"));
		ministryMaterialTableColumn.setText(language.getString("TEXT0095"));
		ministryMember1TableColumn.setText(language.getString("TEXT0135"));
		ministryMember2TableColumn.setText(language.getString("TEXT0038"));
		ministryMember3TableColumn.setText(language.getString("TEXT0136"));
		ministryMember4TableColumn.setText(language.getString("TEXT0038"));

		ministryPartAddButton.setText(null);
		ministryPartAddButton
				.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.USER_MENU_MEETINGS_MINISTRY_ADD));
		ministryPartDeleteButton.setText(null);
		ministryPartDeleteButton
				.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.USER_MENU_MEETINGS_MINISTRY_DELETE));

		song2Label.setText(language.getString("TEXT0099"));

		// christiansPartFulltextTableColumn.setText(language.getString("TEXT0092"));
		this.christiansPartMinTableColumn.setText(language.getString("TEXT0093"));
		this.christiansPartMinTableColumn.setMinWidth(50);
		this.christiansPartMinTableColumn.setMaxWidth(50);

		christiansPartThemeTableColumn.setText(language.getString("TEXT0094"));
		christiansPartMaterialTableColumn.setText(language.getString("TEXT0095"));
		christiansPartTeacherTableColumn.setText(language.getString("TEXT0098"));

		christiansPartAddButton.setText(null);
		christiansPartAddButton
				.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.USER_MENU_MEETINGS_CHRISTIANS_ADD));
		christiansPartDeleteButton.setText(null);
		christiansPartDeleteButton
				.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.USER_MENU_MEETINGS_CHRISTIANS_DELETE));

		congregationBibleStudyLabel.setText(language.getString("TEXT0061"));
		congregationBibleStudyMinLabel.setText(language.getString("TEXT0089"));

		endLabel.setText(language.getString("TEXT0103"));
		reviewLabel.setText(language.getString("TEXT0101"));
		reviewMinLabel.setText(language.getString("TEXT0089"));
		song3Label.setText(language.getString("TEXT0100"));
		pray2Label.setText(language.getString("TEXT0102"));

		publicTalkLabel.setText(language.getString("sp.meetings.publictalk"));
		presidentPublicMeetingLabel.setText(language.getString("sp.meetings.presidentpublicmeeting"));
		publicTalkSongLabel.setText(language.getString("sp.meetings.song1publicmeeting"));
		publicTalkThemeLabel.setText(language.getString("sp.meetings.publictalktheme"));
		publicTalkTalkerLabel.setText(language.getString("sp.meetings.publictalktalker"));
		publicTalkTalkerCongrLabel.setText(language.getString("sp.meetings.publictalktalkercongr"));
		watchtowerStudyLabel.setText(language.getString("sp.meetings.watchtowerstudy"));
		watchtowerStudySong2Label.setText(language.getString("sp.meetings.song2publicmeeting"));
		watchtowerStudyThemeLabel.setText(language.getString("sp.meetings.watchtowerstudytheme"));
		watchtowerStudyConductorLabel.setText(language.getString("sp.meetings.watchtowerstudyconductor"));
		watchtowerStudyReaderLabel.setText(language.getString("sp.meetings.watchtowerstudyreader"));
		watchtowerStudySong3Label.setText(language.getString("sp.meetings.song3publicmeeting"));
		watchtowerStudyPray2Label.setText(language.getString("sp.meetings.watchtowerstudypray2"));
		congregationBibleStudyConductorLabel.setText(language.getString("sp.meetings.congregationbiblestudyconductor"));
		congregationBibleStudyReaderLabel.setText(language.getString("sp.meetings.congregationbiblestudyreader"));

		this.publicTalkOnlyPray1Label.setText("");

		Tooltip wolViewTooltip = new Tooltip(language.getString("meetings.tooltip.wolview"));
		wolViewTooltip.getStyleClass().add("tooltip_001");
		this.wolViewButton.setTooltip(wolViewTooltip);
		this.wolViewButton.setText(null);
		this.wolViewButton.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.USER_MENU_MEETINGS_WOL_VIEW));

		Tooltip loadWOLTooltip = new Tooltip(language.getString("meetings.tooltip.woldownload"));
		loadWOLTooltip.getStyleClass().add("tooltip_001");
		this.loadWeekFromWOLButton.setTooltip(loadWOLTooltip);
		this.loadWeekFromWOLButton.setText(null);
		this.loadWeekFromWOLButton
				.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.USER_MENU_MEETINGS_WOL_LOAD));

		Tooltip saveTooltip = new Tooltip(language.getString("meetings.tooltip.save"));
		saveTooltip.getStyleClass().add("tooltip_001");
		this.saveWeekButton.setTooltip(saveTooltip);
		this.saveWeekButton.setText(null);
		this.saveWeekButton.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.SAVE));

		this.day1CheckBox.setText(this.language.getString("TEXT0123"));
		this.day2CheckBox.setText(this.language.getString("TEXT0124"));
		this.day3CheckBox.setText(this.language.getString("TEXT0125"));
		this.day4CheckBox.setText(this.language.getString("TEXT0126"));
		this.day5CheckBox.setText(this.language.getString("TEXT0127"));
		this.day6CheckBox.setText(this.language.getString("TEXT0128"));
		this.day7CheckBox.setText(this.language.getString("TEXT0129"));

		int width = 100;
		this.hours1ComboBox.setMinWidth(width);
		this.hours1ComboBox.setMaxWidth(width);
		this.hours2ComboBox.setMinWidth(width);
		this.hours2ComboBox.setMaxWidth(width);
		this.minute1ComboBox.setMinWidth(width);
		this.minute1ComboBox.setMaxWidth(width);
		this.minute2ComboBox.setMinWidth(width);
		this.minute2ComboBox.setMaxWidth(width);

		this.dateAndTimeTitleLabel.setText(this.language.getString("meetings.dateandtime.title"));
		this.dateAndTimeMidLabel.setText(this.language.getString("meetings.dateandtime.mid"));
		this.dateAndTimeWeekendLabel.setText(this.language.getString("meetings.dateandtime.weekend"));
		this.time1Label.setText(this.language.getString("meetings.dateandtime.time1"));
		this.timeSeparator1Label.setText(this.language.getString("meetings.dateandtime.timeseparator1"));
		this.time2Label.setText(this.language.getString("meetings.dateandtime.time2"));
		this.timeSeparator2Label.setText(this.language.getString("meetings.dateandtime.timeseparator2"));

		this.placeLabel.setText(this.language.getString("meetings.place"));
		this.place1AddressLabel.setText(this.language.getString("meetings.place.addr1"));
		this.place2AddressLabel.setText(this.language.getString("meetings.place.addr2"));
		this.place1PrintLabel.setText(this.language.getString("meetings.place.print"));
		this.place2PrintLabel.setText(this.language.getString("meetings.place.print"));

		this.presidentPublicMeetingOnlyPrayCheckBox
				.setText(this.language.getString("meetings.presidentpublicmeeting.onlypray"));

		this.publicTalkMinLabel.setText(this.language.getString("meetings.publictalk.min"));
		this.watchtowerStudyMinLabel.setText(this.language.getString("meetings.watchtowerstudy.min"));

		this.place1SelectButton.setText(null);
		this.place1SelectButton.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.SEARCH));

		this.place2SelectButton.setText(null);
		this.place2SelectButton.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.SEARCH));

		this.midweekSettingsLabel.setText(this.language.getString("meetings.editor.midweeksettings"));
		this.midweekNoPrintCheckBox.setText(this.language.getString("meetings.editor.midweeksettings.noprint"));
		this.weekendSettingsLabel.setText(this.language.getString("meetings.editor.weekendsettings"));
		this.weekendOnlyWatchtowerStudyCheckBox
				.setText(this.language.getString("meetings.editor.weekendsettings.onlywatchtowerstudy"));
		this.weekendEventExtraLabel.setText(this.language.getString("meetings.editor.weekendsettings.eventextra"));
		this.weekendEventExtraHeaderLabel
				.setText(this.language.getString("meetings.editor.weekendsettings.eventextraheader"));
		this.weekendEventExtraContentLabel
				.setText(this.language.getString("meetings.editor.weekendsettings.eventextracontent"));

		this.conductorSecondHallLabel.setText(this.language.getString("meetings.editor.conductorsecondhall"));

		this.specialTalkCheckBox.setText(this.language.getString("meetings.editor.specialtalk"));
		this.internSpeakerLabel.setText(this.language.getString("meetings.editor.internspeaker"));
	}

	private void cellValueFactory() {

		ministryTypeTableColumn
				.setCellValueFactory(cellData -> cellData.getValue().getMinistryTypeTranslated().nameProperty());
		// ministryFulltextTableColumn.setCellValueFactory(cellData ->
		// cellData.getValue().fullTextProperty());
		ministryMinTableColumn.setCellValueFactory(cellData -> cellData.getValue().minProperty().asObject());
		ministryThemeTableColumn.setCellValueFactory(cellData -> cellData.getValue().themeProperty());
		ministryMaterialTableColumn.setCellValueFactory(cellData -> cellData.getValue().materialProperty());
		ministryMember1TableColumn.setCellValueFactory(cellData -> cellData.getValue().studentProperty());
		ministryMember2TableColumn.setCellValueFactory(cellData -> cellData.getValue().assistantProperty());
		ministryMember3TableColumn.setCellValueFactory(cellData -> cellData.getValue().student2Property());
		ministryMember4TableColumn.setCellValueFactory(cellData -> cellData.getValue().assistant2Property());

		// christiansPartFulltextTableColumn.setCellValueFactory(cellData ->
		// cellData.getValue().fullTextProperty());
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

	private void contextMenu() {

		presidentComboBox.setContextMenu(createPrivilegeRegisterContextMenu(Privileges.MIDWEEK_PRESIDENT));
		pray1ComboBox.setContextMenu(createPrivilegeRegisterContextMenu(Privileges.MIDWEEK_PRAY_START));
		talkComboBox.setContextMenu(createPrivilegeRegisterContextMenu(Privileges.MIDWEEK_TALK));
		diggingComboBox.setContextMenu(createPrivilegeRegisterContextMenu(Privileges.MIDWEEK_DIGGING));
		congregationBibleStudyComboBox
				.setContextMenu(createPrivilegeRegisterContextMenu(Privileges.MIDWEEK_CONGRBIBLESTUDY));
		pray2ComboBox.setContextMenu(createPrivilegeRegisterContextMenu(Privileges.MIDWEEK_PRAY_END));

		this.christiansPartTableView.setContextMenu(
				createPrivilegeRegisterContextMenu(Privileges.MIDWEEK_CHRISTIAN_LIFE, this.christiansPartTableView));

		congregationBibleStudyReaderComboBox
				.setContextMenu(createPrivilegeRegisterContextMenu(Privileges.MIDWEEK_CONGRBIBLESTUDY_READER));
		presidentPublicMeetingComboBox.setContextMenu(createPrivilegeRegisterContextMenu(Privileges.WEEKEND_PRESIDENT));
		watchtowerStudyConductorComboBox
				.setContextMenu(createPrivilegeRegisterContextMenu(Privileges.WEEKEND_WATCHTOWER));
		watchtowerStudyReaderComboBox
				.setContextMenu(createPrivilegeRegisterContextMenu(Privileges.WEEKEND_WATCHTOWER_READER));
		watchtowerStudyPray2ComboBox.setContextMenu(createPrivilegeRegisterContextMenu(Privileges.WEEKEND_PRAY_END));
	}

	private ContextMenu createPrivilegeRegisterContextMenu(Privileges privilege) {
		return createPrivilegeRegisterContextMenu(privilege, null);
	}

	private ContextMenu createPrivilegeRegisterContextMenu(Privileges privilege,
			TableView<ChristiansPart> tableViewCP) {

		String menuItemText = String.format(language.getString("sp.meetings.history"),
				privilege.getTranslatedName(language));

		String historyTitle = String.format(language.getString("sp.history.title"), menuItemText,
				this.selectedWeek.getFrom().toString());

		StackPane graphic = Meta.Resources.imageInStackPaneForMenu(Meta.Resources.SEARCH);

		MenuItem menuItem = new MenuItem(menuItemText, graphic);
		menuItem.getStyleClass().add("menu_item_001");
		menuItem.setOnAction(event -> openHistory(privilege, historyTitle, tableViewCP));

		ContextMenu contextMenu = new ContextMenu(menuItem);

		return contextMenu;
	}

	private void openHistory(Privileges privilege, String title, TableView<ChristiansPart> tableViewCP) {

		if (this.selectedWeek.spInf1Property() != null) {

			ChristiansPart christiansPart = null;

			if (tableViewCP != null)
				if (tableViewCP.getSelectionModel().getSelectedIndex() > -1) {

					christiansPart = tableViewCP.getSelectionModel().getSelectedItem();

					if (!(tableViewCP.getItems().size() == 1)) {

						if (!this.application.getAlertBuilder2().confirm(this.ownerStage,
								this.language.getString("meetings.christianlife.confirm.assignpart"),
								christiansPart.getTheme())) {

							return;

						}
					}
				} else {
					this.application.getAlertBuilder2().error(this.ownerStage,
							this.language.getString("meetings.christianlife.error.noselection"));
					return;
				}

			try {

				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(Meta.Views.HISTORY);
				AnchorPane layout = (AnchorPane) fxmlLoader.load();

				Scene scene = new Scene(layout);
				scene.getStylesheets().add(Meta.Themes.SUPPORTPLANNER_THEME);

				Stage stage = new Stage();
				stage.setScene(scene);
				stage.initModality(Modality.WINDOW_MODAL);
				stage.initOwner(ownerStage);
				stage.setTitle(title);
				stage.getIcons().add(Meta.Resources.imageForWindowsIcon(Meta.Resources.SEARCH));

				stage.setMinWidth(1050);
				stage.setMinHeight(500);

				History ctrl = (History) fxmlLoader.getController();
				ctrl.setLayout(layout);
				ctrl.setPrivilege(privilege);
				ctrl.setMembers(memberList);
				ctrl.setLanguage(language);
				ctrl.setDatabaseWeeks(this.databaseWeeks);
				ctrl.setSelectedWeek(this.selectedWeek);
				ctrl.setEditorWeek(Week.buildMeetingEditorWeek(this));
				ctrl.setEditor(this);
				ctrl.setThisStage(stage);
				ctrl.setAlertBuilder(this.alertBuilder);
				ctrl.setChristiansPart(christiansPart);
				ctrl.setDatabaseWeeksAudio(this.databaseWeeksAudio);
				ctrl.setApplication(this.application);
				ctrl.setConfigs(this.configs);
				ctrl.setDatabaseWeeksUsciere(this.databaseWeeksUsciere);

				ctrl.objectInitialize();

				stage.show();

			} catch (IOException e) {
				e.printStackTrace();
			}

		} else {

			this.application.getAlertBuilder2().error(this.ownerStage,
					this.language.getString("sp.history.weeknotsaved"));
		}
	}

	@Override
	public void updateSelectedChristianPart(ChristiansPart christiansPart, int memberID) {

		for (Member m : this.memberList)
			if (m.getSpMemberID() == memberID) {
				christiansPart.setTeacher(m);
				this.christiansPartTableView.refresh();
				break;
			}

	}

	private void initData() {

		for (int i = 0; i < 24; i++) {
			this.hours1ComboBox.getItems().add(i);
			this.hours2ComboBox.getItems().add(i);
		}

		this.hours1ComboBox.getSelectionModel().selectFirst();
		this.hours2ComboBox.getSelectionModel().selectFirst();

		for (int i = 0; i < 60; i++) {
			this.minute1ComboBox.getItems().add(i);
			this.minute2ComboBox.getItems().add(i);
		}

		this.minute1ComboBox.getSelectionModel().selectFirst();
		this.minute2ComboBox.getSelectionModel().selectFirst();

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

		presidentPublicMeetingList = FXCollections.observableArrayList();
		watchtowerStudyList = FXCollections.observableArrayList();
		watchtowerReaderList = FXCollections.observableArrayList();
		prayEndPublicMeetingList = FXCollections.observableArrayList();

		congregationBibleStudyReaderList = FXCollections.observableArrayList();

		this.conductorSecondHallList = FXCollections.observableArrayList();

		this.internSpeakerList = FXCollections.observableArrayList();

		addEmptyMember();

		presidentComboBox.setItems(presidentList);
		pray1ComboBox.setItems(prayStartList);
		talkComboBox.setItems(talkList);
		diggingComboBox.setItems(diggingList);
		bibleReadingComboBox.setItems(bibleReadingList);
		bibleReading2ComboBox.setItems(bibleReadingList);
		congregationBibleStudyComboBox.setItems(congregationBibleStudyList);
		pray2ComboBox.setItems(prayEndList);

		presidentPublicMeetingComboBox.setItems(presidentPublicMeetingList);
		watchtowerStudyConductorComboBox.setItems(watchtowerStudyList);
		watchtowerStudyReaderComboBox.setItems(watchtowerReaderList);
		watchtowerStudyPray2ComboBox.setItems(prayEndPublicMeetingList);

		congregationBibleStudyReaderComboBox.setItems(congregationBibleStudyReaderList);

		this.conductorSecondHallComboBox.setItems(this.conductorSecondHallList);

		this.internSpeakerComboBox.setItems(this.internSpeakerList);

		selectFirst();

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

		presidentPublicMeetingComboBox.getSelectionModel().selectFirst();

		if (!(watchtowerStudyConductorComboBox.getSelectionModel().getSelectedIndex() > -1)) {
			int watchtowerStudyConductorIndex = -1;
			for (int i = 0; i < watchtowerStudyList.size(); i++) {
				Member member = watchtowerStudyList.get(i);
				if (member.getSpInf43() == 1) {
					watchtowerStudyConductorIndex = i;
					break;
				}
			}

			if (watchtowerStudyConductorIndex == -1)
				watchtowerStudyConductorComboBox.getSelectionModel().selectFirst();
			else
				watchtowerStudyConductorComboBox.getSelectionModel().select(watchtowerStudyConductorIndex);
		}
		watchtowerStudyReaderComboBox.getSelectionModel().selectFirst();
		watchtowerStudyPray2ComboBox.getSelectionModel().selectFirst();

		congregationBibleStudyReaderComboBox.getSelectionModel().selectFirst();

		this.conductorSecondHallComboBox.getSelectionModel().selectFirst();

		this.internSpeakerComboBox.getSelectionModel().selectFirst();
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

			if (member.getSpInf36() == 1)
				this.presidentPublicMeetingList.add(member);
			if (member.getSpInf43() == 1 || member.getSpInf44() == 1)
				this.watchtowerStudyList.add(member);
			if (member.getSpInf27() == 1)
				this.watchtowerReaderList.add(member);
			if (member.getSpInf37() == 1)
				this.prayEndPublicMeetingList.add(member);
			if (member.getSpInf26() == 1)
				this.congregationBibleStudyReaderList.add(member);

			// if (member.getSpInf9() == 1 || member.getSpInf10() == 1)
			if (member.getSpInf60() == 1)
				this.conductorSecondHallList.add(member);

			if (member.getSpInf45() == 1)
				this.internSpeakerList.add(member);
		}

		orderLists();
	}

	private void orderLists() {

		this.presidentList.sort((o1, o2) -> o1.getNameStyle1().compareTo(o2.getNameStyle1()));
		this.prayStartList.sort((o1, o2) -> o1.getNameStyle1().compareTo(o2.getNameStyle1()));
		this.talkList.sort((o1, o2) -> o1.getNameStyle1().compareTo(o2.getNameStyle1()));
		this.diggingList.sort((o1, o2) -> o1.getNameStyle1().compareTo(o2.getNameStyle1()));
		this.bibleReadingList.sort((o1, o2) -> o1.getNameStyle1().compareTo(o2.getNameStyle1()));
		this.congregationBibleStudyList.sort((o1, o2) -> o1.getNameStyle1().compareTo(o2.getNameStyle1()));
		this.prayEndList.sort((o1, o2) -> o1.getNameStyle1().compareTo(o2.getNameStyle1()));

		this.presidentPublicMeetingList.sort((o1, o2) -> o1.getNameStyle1().compareTo(o2.getNameStyle1()));
		this.watchtowerReaderList.sort((o1, o2) -> o1.getNameStyle1().compareTo(o2.getNameStyle1()));
		this.prayEndPublicMeetingList.sort((o1, o2) -> o1.getNameStyle1().compareTo(o2.getNameStyle1()));

		this.congregationBibleStudyReaderList.sort((o1, o2) -> o1.getNameStyle1().compareTo(o2.getNameStyle1()));

		this.watchtowerStudyList.sort((o1, o2) -> o1.getNameStyle1().compareTo(o2.getNameStyle1()));

		this.conductorSecondHallList.sort((o1, o2) -> o1.getNameStyle1().compareTo(o2.getNameStyle1()));

		this.internSpeakerList.sort((o1, o2) -> o1.getNameStyle1().compareTo(o2.getNameStyle1()));
	}

	private void addEmptyMember() {

		this.presidentList.add(Member.emptyMember(language));
		this.prayStartList.add(Member.emptyMember(language));
		this.talkList.add(Member.emptyMember(language));
		this.diggingList.add(Member.emptyMember(language));
		this.bibleReadingList.add(Member.emptyMember(language));
		this.congregationBibleStudyList.add(Member.emptyMember(language));
		this.prayEndList.add(Member.emptyMember(language));

		this.presidentPublicMeetingList.add(Member.emptyMember(language));
		this.watchtowerStudyList.add(Member.emptyMember(language));
		this.watchtowerReaderList.add(Member.emptyMember(language));
		this.prayEndPublicMeetingList.add(Member.emptyMember(language));

		this.congregationBibleStudyReaderList.add(Member.emptyMember(language));

		this.conductorSecondHallList.add(Member.emptyMember(this.language));

		this.internSpeakerList.add(Member.emptyMember(this.language));
	}

	private void resetLists() {

		this.presidentList.clear();
		this.prayStartList.clear();
		this.talkList.clear();
		this.diggingList.clear();
		this.bibleReadingList.clear();
		this.congregationBibleStudyList.clear();
		this.prayEndList.clear();

		this.presidentPublicMeetingList.clear();
		this.watchtowerStudyList.clear();
		this.watchtowerReaderList.clear();
		this.prayEndPublicMeetingList.clear();

		this.congregationBibleStudyReaderList.clear();
		this.conductorSecondHallList.clear();

		this.internSpeakerList.clear();
	}

	private void loadSelectedWeek() {

		if (this.selectedWeek != null)
			if (this.selectedWeek.spWeekIDProperty() != null) {

				// Week type
				for (int i = 0; i < typeWeekListView.getItems().size(); i++) {

					WeekTypeTranslated weekTypeTranslated = typeWeekListView.getItems().get(i);
					if (weekTypeTranslated.getOrdinal() == this.selectedWeek.getSpInf2()) {
						typeWeekListView.getSelectionModel().select(i);

						switch (weekTypeTranslated.getType()) {
						case CIRCUIT_OVERSEERS_VISIT:

							WeekOverseer weekOverseer = this.selectedWeek.getWeekOverseer();
							if (weekOverseer != null)
								addCircuitOverseerTab(weekOverseer, false);

							break;

						default:
							break;
						}

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

				setMemberComboBoxIndex(this.congregationBibleStudyReaderComboBox, this.selectedWeek.getSpInf29());
				setMemberComboBoxIndex(this.presidentPublicMeetingComboBox, this.selectedWeek.getSpInf30());
				this.publicTalkSongTextField.setText(this.selectedWeek.getSpInf31());
				this.publicTalkThemeTextField.setText(this.selectedWeek.getSpInf32());
				this.publicTalkTalkerTextField.setText(this.selectedWeek.getSpInf33());
				this.publicTalkTalkerCongrTextField.setText(this.selectedWeek.getSpInf34());
				this.watchtowerStudySong2TextField.setText(this.selectedWeek.getSpInf35());
				this.watchtowerStudyThemeTextField.setText(this.selectedWeek.getSpInf36());
				setMemberComboBoxIndex(this.watchtowerStudyConductorComboBox, this.selectedWeek.getSpInf37());
				setMemberComboBoxIndex(this.watchtowerStudyReaderComboBox, this.selectedWeek.getSpInf38());
				this.watchtowerStudySong3TextField.setText(this.selectedWeek.getSpInf39());
				setMemberComboBoxIndex(this.watchtowerStudyPray2ComboBox, this.selectedWeek.getSpInf40());

				int spInf41 = this.selectedWeek.getSpInf41();
				this.presidentPublicMeetingOnlyPrayCheckBox.setSelected(spInf41 == 1);

				this.publicTalkMinTextField.setText(this.selectedWeek.getSpInf42());

				this.watchtowerStudyMinTextField.setText(this.selectedWeek.getSpInf43());

				setDayMeeting1(this.selectedWeek.getSpInf44());

				this.hours1ComboBox.getSelectionModel().select(this.selectedWeek.getSpInf45());

				this.minute1ComboBox.getSelectionModel().select(this.selectedWeek.getSpInf46());

				setDayMeeting2(this.selectedWeek.getSpInf47());

				this.hours2ComboBox.getSelectionModel().select(this.selectedWeek.getSpInf48());

				this.minute2ComboBox.getSelectionModel().select(this.selectedWeek.getSpInf49());

				this.place1TextField.setText(this.selectedWeek.getSpInf50());

				int spInf51 = this.selectedWeek.getSpInf51();
				this.place1PrintCheckBox.setSelected(spInf51 == 1);

				this.place2TextField.setText(this.selectedWeek.getSpInf52());

				int spInf53 = this.selectedWeek.getSpInf53();
				this.place2PrintCheckBox.setSelected(spInf53 == 1);

				// 0.11.0

				this.weekendEventExtraHeaderTextField.setText(this.selectedWeek.getSpInf54());
				this.weekendEventExtraContentTextField.setText(this.selectedWeek.getSpInf55());
				this.weekendOnlyWatchtowerStudyCheckBox.setSelected(this.selectedWeek.getSpInf56() == 1);
				this.midweekNoPrintCheckBox.setSelected(this.selectedWeek.getSpInf57() == 1);
				setMemberComboBoxIndex(this.conductorSecondHallComboBox, this.selectedWeek.getSpInf58());

				// 1.1

				this.midweekSong1TitelTextField.setText(this.selectedWeek.getSpInf59());
				this.midweekSong2TitelTextField.setText(this.selectedWeek.getSpInf60());
				this.midweekSong3TitelTextField.setText(this.selectedWeek.getSpInf61());
				this.weekendSong1TitelTextField.setText(this.selectedWeek.getSpInf62());
				this.weekendSong2TitelTextField.setText(this.selectedWeek.getSpInf63());
				this.weekendSong3TitelTextField.setText(this.selectedWeek.getSpInf64());

				int spInf65 = this.selectedWeek.getSpInf65();
				this.specialTalkCheckBox.setSelected(spInf65 == 1);

				setMemberComboBoxIndex(this.internSpeakerComboBox, this.selectedWeek.getSpInf66());

				// --------------------------------------------------------

				this.ministryPartList.addAll(this.selectedWeek.getMinistryPartList());
				this.christiansPartList.addAll(this.selectedWeek.getChristiansPartList());

				this.ministryTableView.refresh();
				this.christiansPartTableView.refresh();

			} else {

				// Minuti discorso pubblico
				String publicTalkMin = this.ownerCtrl.getConfigs().get("inf2");
				if (publicTalkMin != null) {
					publicTalkMin = Crypt.decrypt(publicTalkMin, this.application.getSettings().getDatabaseSecretKey());
					this.publicTalkMinTextField.setText(publicTalkMin);
				}

				// Minuti Studio Torre di Guardia
				String watchtowerMin = this.ownerCtrl.getConfigs().get("inf3");
				if (watchtowerMin != null) {
					watchtowerMin = Crypt.decrypt(watchtowerMin, this.application.getSettings().getDatabaseSecretKey());
					this.watchtowerStudyMinTextField.setText(watchtowerMin);
				}

				initDateAndTime();
				initPlace();

			}
	}

	private void initPlace() {

		ObservableList<Place> placesList = this.ownerCtrl.getPlacesList();
		Place found = null;
		for (Place place : placesList)
			if (place.getType().get() == EnumPlaceType.KINGDOMHALL)
				if (place.getDef().get()) {
					found = place;
					break;
				}

		if (found != null) {

			String addr = placeToText(found);

			this.place1TextField.setText(addr);
			this.place2TextField.setText(addr);
		}
	}

	private String placeToText(Place found) {

		String addr = "";

		HashMap<String, String> configs = this.ownerCtrl.getConfigs();
		String pattern = configs.get("inf1");
		if (pattern != null) {

			pattern = Crypt.decrypt(pattern, this.application.getSettings().getDatabaseSecretKey());
			addr = PlaceUtils.toText(found, pattern);

		} else

			addr = PlaceUtils.toText(found);

		return addr;
	}

	private void initDateAndTime() {
		ObservableList<DateAndTime> dateAndTimeList = this.ownerCtrl.getDateAndTimeList();
		DateAndTime dateAndTime = DateAndTimeUtils.check(dateAndTimeList, this.selectedWeek.getFrom());

		if (dateAndTime != null) {

			int day1 = dateAndTime.getDay1().get();
			switch (day1) {
			case 1:
				this.day1CheckBox.setSelected(true);
				break;
			case 2:
				this.day2CheckBox.setSelected(true);
				break;
			case 3:
				this.day3CheckBox.setSelected(true);
				break;
			case 4:
				this.day4CheckBox.setSelected(true);
				break;
			case 5:
				this.day5CheckBox.setSelected(true);
				break;
			default:
				break;
			}

			int h1 = dateAndTime.getHour1().get();
			int m1 = dateAndTime.getMinute1().get();

			this.hours1ComboBox.getSelectionModel().select(h1);
			this.minute1ComboBox.getSelectionModel().select(m1);

			int day2 = dateAndTime.getDay2().get();
			switch (day2) {
			case 6:
				this.day6CheckBox.setSelected(true);
				break;
			case 7:
				this.day7CheckBox.setSelected(true);
				break;
			default:
				break;
			}

			int h2 = dateAndTime.getHour2().get();
			int m2 = dateAndTime.getMinute2().get();

			this.hours2ComboBox.getSelectionModel().select(h2);
			this.minute2ComboBox.getSelectionModel().select(m2);

		} else {
			this.day1CheckBox.setSelected(true);
			this.day6CheckBox.setSelected(true);
		}
	}

	private void setDayMeeting1(int day) {

		switch (day) {
		case 1:
			this.day1CheckBox.setSelected(true);
			break;
		case 2:
			this.day2CheckBox.setSelected(true);
			break;
		case 3:
			this.day3CheckBox.setSelected(true);
			break;
		case 4:
			this.day4CheckBox.setSelected(true);
			break;
		case 5:
			this.day5CheckBox.setSelected(true);
			break;
		default:
			break;
		}

	}

	private void setDayMeeting2(int day) {

		switch (day) {
		case 6:
			this.day6CheckBox.setSelected(true);
			break;
		case 7:
			this.day7CheckBox.setSelected(true);
			break;
		default:
			break;
		}

	}

	private void listeners() {

		listenerDisableMouseSecondary();

		listenerTypeWeekListView();

		listenerMinistryTableView();
		listenerMinistryPartAddButton();
		listenerMinistryPartDeleteButton();

		listenerChristiansPartTableView();
		listenerChristiansPartAddButton();
		listenerChristiansPartDeleteButton();

		listenerWOLView();
		listenerLoadWeekFromWOLButton();

		listenerSaveWeekButton();

		this.day1CheckBox.selectedProperty().addListener((obs, oldV, newV) -> checkBoxGroups(newV, day1CheckBox,
				day2CheckBox, day3CheckBox, day4CheckBox, day5CheckBox));
		this.day2CheckBox.selectedProperty().addListener((obs, oldV, newV) -> checkBoxGroups(newV, day2CheckBox,
				day1CheckBox, day3CheckBox, day4CheckBox, day5CheckBox));
		this.day3CheckBox.selectedProperty().addListener((obs, oldV, newV) -> checkBoxGroups(newV, day3CheckBox,
				day2CheckBox, day1CheckBox, day4CheckBox, day5CheckBox));
		this.day4CheckBox.selectedProperty().addListener((obs, oldV, newV) -> checkBoxGroups(newV, day4CheckBox,
				day2CheckBox, day3CheckBox, day1CheckBox, day5CheckBox));
		this.day5CheckBox.selectedProperty().addListener((obs, oldV, newV) -> checkBoxGroups(newV, day5CheckBox,
				day2CheckBox, day3CheckBox, day4CheckBox, day1CheckBox));

		this.day6CheckBox.selectedProperty()
				.addListener((obs, oldV, newV) -> checkBoxGroups(newV, day6CheckBox, day7CheckBox));
		this.day7CheckBox.selectedProperty()
				.addListener((obs, oldV, newV) -> checkBoxGroups(newV, day7CheckBox, day6CheckBox));

		this.place1SelectButton.setOnAction(param -> selectPlace1());
		this.place2SelectButton.setOnAction(param -> selectPlace2());
	}

	private void selectPlace1() {

		Place place = PlaceDialog.show(this.application, this.ownerStage, this.ownerCtrl.getPlacesList(),
				EnumPlaceType.KINGDOMHALL);
		if (place != null)
			this.place1TextField.setText(placeToText(place));
	}

	private void selectPlace2() {
		Place place = PlaceDialog.show(this.application, this.ownerStage, this.ownerCtrl.getPlacesList(),
				EnumPlaceType.KINGDOMHALL);
		if (place != null)
			this.place2TextField.setText(placeToText(place));
	}

	private void checkBoxGroups(Boolean newV, CheckBox edited, CheckBox... others) {

		if (this.listenerCheckFields) {

			this.listenerCheckFields = false;

			if (newV) {
				for (CheckBox cb : others)
					if (cb.isSelected())
						cb.setSelected(false);
			} else
				edited.setSelected(true);

			this.listenerCheckFields = true;
		}
	}

	private void listenerDisableMouseSecondary() {

		presidentComboBox.addEventFilter(MouseEvent.MOUSE_RELEASED, event -> disableMouseSecondary(event));
		pray1ComboBox.addEventFilter(MouseEvent.MOUSE_RELEASED, event -> disableMouseSecondary(event));
		talkComboBox.addEventFilter(MouseEvent.MOUSE_RELEASED, event -> disableMouseSecondary(event));
		diggingComboBox.addEventFilter(MouseEvent.MOUSE_RELEASED, event -> disableMouseSecondary(event));
		congregationBibleStudyComboBox.addEventFilter(MouseEvent.MOUSE_RELEASED, event -> disableMouseSecondary(event));
		pray2ComboBox.addEventFilter(MouseEvent.MOUSE_RELEASED, event -> disableMouseSecondary(event));
		congregationBibleStudyReaderComboBox.addEventFilter(MouseEvent.MOUSE_RELEASED,
				event -> disableMouseSecondary(event));
		presidentPublicMeetingComboBox.addEventFilter(MouseEvent.MOUSE_RELEASED, event -> disableMouseSecondary(event));
		watchtowerStudyConductorComboBox.addEventFilter(MouseEvent.MOUSE_RELEASED,
				event -> disableMouseSecondary(event));
		watchtowerStudyReaderComboBox.addEventFilter(MouseEvent.MOUSE_RELEASED, event -> disableMouseSecondary(event));
		watchtowerStudyPray2ComboBox.addEventFilter(MouseEvent.MOUSE_RELEASED, event -> disableMouseSecondary(event));
	}

	private void disableMouseSecondary(MouseEvent event) {
		if (event.getButton() == MouseButton.SECONDARY)
			event.consume();
	}

	private void listenerTypeWeekListView() {
		this.typeWeekListView.getSelectionModel().selectedItemProperty()
				.addListener((observable, oldValue, newValue) -> checkAdditionalInfo(oldValue, newValue));
	}

	private void checkAdditionalInfo(WeekTypeTranslated oldValue, WeekTypeTranslated newValue) {

		switch (newValue.getType()) {
		case CIRCUIT_OVERSEERS_VISIT:

			if (this.selectedWeek != null) {

				WeekOverseer weekOverseer = this.selectedWeek.getWeekOverseer();
				if (weekOverseer != null)
					addCircuitOverseerTab(weekOverseer, true);
//				else
//					Platform.runLater(() -> this.typeWeekListView.getSelectionModel().select(oldValue));
			}
			break;

		default:

			switch (oldValue.getType()) {
			case CIRCUIT_OVERSEERS_VISIT:

				removeCircuitOverseerTab();
				break;
			default:
				break;
			}
			break;
		}

	}

	private void addCircuitOverseerTab(WeekOverseer weekOverseer, boolean selectTab) {

		try

		{

			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(Meta.Views.HOME_USER_MENU_MEETINGS_CIRCUITOVERSEER);
			AnchorPane layout = (AnchorPane) fxmlLoader.load();

			this.circuitOverseerTab = new Tab("", layout);
			this.circuitOverseerTab.getStyleClass().add("tab_001");
			this.circuitOverseerTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.USER_MENU_CIRCUITOVERSEER));
			this.tabPane.getTabs().add(this.circuitOverseerTab);

			if (selectTab)
				this.tabPane.getSelectionModel().select(this.circuitOverseerTab);

			UserMenuMeetingCircuitOverseer ctrl = (UserMenuMeetingCircuitOverseer) fxmlLoader.getController();
			ctrl.setSettings(this.settings);
			ctrl.setOwnerStage(ownerStage);
			ctrl.setOwnerCtrl(this);
			ctrl.setSelectedWeek(weekOverseer);
			ctrl.setOwnerTabPane(tabPane);
			ctrl.setThisTab(this.circuitOverseerTab);
			ctrl.objectInitialize();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void removeCircuitOverseerTab() {

		if (this.circuitOverseerTab != null) {
			this.tabPane.getTabs().remove(this.circuitOverseerTab);
			this.circuitOverseerTab = null;
		}
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

			String spInf29 = String.valueOf(
					this.congregationBibleStudyReaderComboBox.getSelectionModel().getSelectedItem().getSpMemberID());
			String spInf30 = String
					.valueOf(this.presidentPublicMeetingComboBox.getSelectionModel().getSelectedItem().getSpMemberID());
			String spInf31 = Crypt.encrypt(this.publicTalkSongTextField.getText(), settings.getDatabaseSecretKey());
			String spInf32 = Crypt.encrypt(this.publicTalkThemeTextField.getText(), settings.getDatabaseSecretKey());
			String spInf33 = Crypt.encrypt(this.publicTalkTalkerTextField.getText(), settings.getDatabaseSecretKey());
			String spInf34 = Crypt.encrypt(this.publicTalkTalkerCongrTextField.getText(),
					settings.getDatabaseSecretKey());
			String spInf35 = Crypt.encrypt(this.watchtowerStudySong2TextField.getText(),
					settings.getDatabaseSecretKey());
			String spInf36 = Crypt.encrypt(this.watchtowerStudyThemeTextField.getText(),
					settings.getDatabaseSecretKey());
			String spInf37 = String.valueOf(
					this.watchtowerStudyConductorComboBox.getSelectionModel().getSelectedItem().getSpMemberID());
			String spInf38 = String
					.valueOf(this.watchtowerStudyReaderComboBox.getSelectionModel().getSelectedItem().getSpMemberID());
			String spInf39 = Crypt.encrypt(this.watchtowerStudySong3TextField.getText(),
					settings.getDatabaseSecretKey());
			String spInf40 = String
					.valueOf(this.watchtowerStudyPray2ComboBox.getSelectionModel().getSelectedItem().getSpMemberID());

			// 0.10.0

			int spInf41 = this.presidentPublicMeetingOnlyPrayCheckBox.isSelected() ? 1 : 0;

			String spInf42 = Crypt.encrypt(this.publicTalkMinTextField.getText(), this.settings.getDatabaseSecretKey());

			String spInf43 = Crypt.encrypt(this.watchtowerStudyMinTextField.getText(),
					this.settings.getDatabaseSecretKey());

			int spInf44 = defineDayMeetings1();

			int spInf45 = 0;
			Integer hour1 = this.hours1ComboBox.getSelectionModel().getSelectedItem();
			if (hour1 != null)
				spInf45 = hour1;

			int spInf46 = 0;
			Integer minute1 = this.minute1ComboBox.getSelectionModel().getSelectedItem();
			if (minute1 != null)
				spInf46 = minute1;

			int spInf47 = defineDayMeetings2();

			int spInf48 = 0;
			Integer hour2 = this.hours2ComboBox.getSelectionModel().getSelectedItem();
			if (hour2 != null)
				spInf48 = hour2;

			int spInf49 = 0;
			Integer minute2 = this.minute2ComboBox.getSelectionModel().getSelectedItem();
			if (minute2 != null)
				spInf49 = minute2;

			String spInf50 = Crypt.encrypt(this.place1TextField.getText(), this.settings.getDatabaseSecretKey());

			int spInf51 = this.place1PrintCheckBox.isSelected() ? 1 : 0;

			String spInf52 = Crypt.encrypt(this.place2TextField.getText(), this.settings.getDatabaseSecretKey());

			int spInf53 = this.place2PrintCheckBox.isSelected() ? 1 : 0;

			// 0.11.0

			String spInf54 = Crypt.encrypt(this.weekendEventExtraHeaderTextField.getText(),
					this.settings.getDatabaseSecretKey());
			String spInf55 = Crypt.encrypt(this.weekendEventExtraContentTextField.getText(),
					this.settings.getDatabaseSecretKey());
			int spInf56 = this.weekendOnlyWatchtowerStudyCheckBox.isSelected() ? 1 : 0;
			int spInf57 = this.midweekNoPrintCheckBox.isSelected() ? 1 : 0;
			int spInf58 = this.conductorSecondHallComboBox.getSelectionModel().getSelectedItem().getSpMemberID();

			// 1.1

			String spInf59 = Crypt.encrypt(this.midweekSong1TitelTextField.getText(),
					this.settings.getDatabaseSecretKey());
			String spInf60 = Crypt.encrypt(this.midweekSong2TitelTextField.getText(),
					this.settings.getDatabaseSecretKey());
			String spInf61 = Crypt.encrypt(this.midweekSong3TitelTextField.getText(),
					this.settings.getDatabaseSecretKey());
			String spInf62 = Crypt.encrypt(this.weekendSong1TitelTextField.getText(),
					this.settings.getDatabaseSecretKey());
			String spInf63 = Crypt.encrypt(this.weekendSong2TitelTextField.getText(),
					this.settings.getDatabaseSecretKey());
			String spInf64 = Crypt.encrypt(this.weekendSong3TitelTextField.getText(),
					this.settings.getDatabaseSecretKey());
			int spInf65 = this.specialTalkCheckBox.isSelected() ? 1 : 0;
			int spInf66 = this.internSpeakerComboBox.getSelectionModel().getSelectedItem().getSpMemberID();

			// -------

			String spInfMinistryParts = getMinistryParts();
			String spInfChristiansParts = getChristiansParts();

			if (this.selectedWeek.spWeekIDProperty() != null) {
				// editWeek

				String spWeekID = String.valueOf(selectedWeek.getSpWeekID());
				String spInf1 = String.valueOf(selectedWeek.getSpInf1());

				Actions.updateWeek(spWeekID, spInf1, spInf2, spInf3, spInf4, spInf5, spInf6, spInf7, spInf8, spInf9,
						spInf10, spInf11, spInf12, spInf13, spInf14, spInf15, spInf16, spInf17, spInf18, spInf19,
						spInf20, spInf21, spInf22, spInf23, spInf24, spInf25, spInf26, spInf27, spInf28, spInf29,
						spInf30, spInf31, spInf32, spInf33, spInf34, spInf35, spInf36, spInf37, spInf38, spInf39,
						spInf40, spInf41, spInf42, spInf43, spInf44, spInf45, spInf46, spInf47, spInf48, spInf49,
						spInf50, spInf51, spInf52, spInf53, spInf54, spInf55, spInf56, spInf57, spInf58, spInf59,
						spInf60, spInf61, spInf62, spInf63, spInf64, spInf65, spInf66, spInfMinistryParts,
						spInfChristiansParts, settings, ownerStage, ownerTabPane, thisTab, ownerCtrl);

			} else {
				// newWeek

				String spInf1 = Week.buildKey(this.selectedWeek.getTo());

				Actions.insertWeek(spInf1, spInf2, spInf3, spInf4, spInf5, spInf6, spInf7, spInf8, spInf9, spInf10,
						spInf11, spInf12, spInf13, spInf14, spInf15, spInf16, spInf17, spInf18, spInf19, spInf20,
						spInf21, spInf22, spInf23, spInf24, spInf25, spInf26, spInf27, spInf28, spInf29, spInf30,
						spInf31, spInf32, spInf33, spInf34, spInf35, spInf36, spInf37, spInf38, spInf39, spInf40,
						spInf41, spInf42, spInf43, spInf44, spInf45, spInf46, spInf47, spInf48, spInf49, spInf50,
						spInf51, spInf52, spInf53, spInf54, spInf55, spInf56, spInf57, spInf58, spInf59, spInf60,
						spInf61, spInf62, spInf63, spInf64, spInf65, spInf66, spInfMinistryParts, spInfChristiansParts,
						settings, ownerStage, ownerTabPane, thisTab, ownerCtrl);
			}
		}
	}

	private int defineDayMeetings1() {

		if (day1CheckBox.isSelected())
			return 1;
		else if (day2CheckBox.isSelected())
			return 2;
		else if (day3CheckBox.isSelected())
			return 3;
		else if (day4CheckBox.isSelected())
			return 4;
		else if (day5CheckBox.isSelected())
			return 5;

		return 0;
	}

	private int defineDayMeetings2() {

		if (day6CheckBox.isSelected())
			return 6;
		else if (day7CheckBox.isSelected())
			return 7;

		return 0;
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

		boolean status = true;

		int internSpeakerID = -1;
		Member internSpeaker = this.internSpeakerComboBox.getSelectionModel().getSelectedItem();
		if (internSpeaker != null)
			internSpeakerID = internSpeaker.getSpMemberID();

		String externSpeaker = this.publicTalkTalkerTextField.getText();
		if (!externSpeaker.isEmpty() && internSpeakerID > 0) {
			status = false;
			this.application.getAlertBuilder2().error(this.ownerStage,
					this.language.getString("meetings.editor.error.speaker"));
		}

		return status;
	}

	private void listenerWOLView() {
		wolViewButton.setOnAction(event -> wolViewOnAction());
	}

	private void wolViewOnAction() {

		try {

			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(Meta.Views.WOLBROWSER);

			AnchorPane anchorPane = (AnchorPane) fxmlLoader.load();

			Scene scene = new Scene(anchorPane);
			scene.getStylesheets().add(Meta.Themes.SUPPORTPLANNER_THEME);

			Stage stage = new Stage();

			stage.setScene(scene);
			stage.setTitle(Meta.Application.getFullTitle());
			stage.getIcons().add(Meta.Resources.getImageApplicationIcon());
			stage.initOwner(this.ownerStage);

			stage.setWidth(750);
			stage.setHeight(750);

			WOLBrowser ctrl = fxmlLoader.getController();
			ctrl.setLanguage(this.language);
			ctrl.setWeek(this.selectedWeek);
			ctrl.init();

			stage.show();

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	private void listenerLoadWeekFromWOLButton() {
		loadWeekFromWOLButton.setOnAction(event -> loadWeekFromWOLOnAction());
	}

	private void loadWeekFromWOLOnAction() {

		Alert alert = new AlertDesigner(language.getString("TEXT0137"), language.getString("TEXT0138"), ownerStage,
				AlertType.CONFIRMATION, Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
				Meta.Themes.SUPPORTPLANNER_THEME, "alert_001");

		if (alert.showAndWait().get() == ButtonType.OK) {

//			ScheduleForMeetingHTML scheduleForMeetingHTML = new ScheduleForMeetingHTML(language,
//					selectedWeek.getFrom());

//			scheduleForMeetingHTML.download();

//			if (scheduleForMeetingHTML != null) {
//				ScheduleForMeeting scheduleForMeeting = new ScheduleForMeeting(scheduleForMeetingHTML.getRelevantRows(),
//						language);
//				if (scheduleForMeeting != null) {
//					if (scheduleForMeeting.isPresent()) {

//						song1TextField.setText(scheduleForMeeting.getSong1().getSongNo().toString());
//
//						bibleChaptersTextField.setText(scheduleForMeeting.getBibleChapters());
//
//						openingCommentsMinTextField
//								.setText(scheduleForMeeting.getOpeningComments().getMin().toString());
//						openingCommentsTextTextField.setText(scheduleForMeeting.getOpeningComments().getTitle());
//
//						talkMinTextField.setText(scheduleForMeeting.getTreasuresTalk().getMin().toString());
//						talkTextTextField.setText(scheduleForMeeting.getTreasuresTalk().getTitle());
//
//						diggingMinTextField.setText(scheduleForMeeting.getTreasuresDigging().getMin().toString());
//						diggingTextTextField.setText(scheduleForMeeting.getTreasuresDigging().getTitle());
//
//						bibleReadingMinTextField
//								.setText(scheduleForMeeting.getTreasuresBibleReading().getMin().toString());
//						bibleReadingTextTextField.setText(scheduleForMeeting.getTreasuresBibleReading().getTextPart());
//						bibleReadingMaterialsTextField
//								.setText(scheduleForMeeting.getTreasuresBibleReading().getBible());
//
//						ministryPartList.clear();
//						for (ScheduleForMeeting.MinistryPart part : scheduleForMeeting.getMinistryPartsList())
//							ministryPartList.add(new MinistryPart(part.getMinistryTypeTranslated(), part.getText(),
//									part.getMin(), part.getTextPart(), part.getMaterial(), Member.emptyMember(language),
//									Member.emptyMember(language), Member.emptyMember(language),
//									Member.emptyMember(language)));
//
//						song2TextField.setText(scheduleForMeeting.getSong2().getSongNo().toString());
//
//						christiansPartList.clear();
//						for (ScheduleForMeeting.ChristiansPart part : scheduleForMeeting.getChristiansPartsList())
//							christiansPartList.add(new ChristiansPart(part.getText(), part.getMin(), part.getTextPart(),
//									part.getBody(), Member.emptyMember(language)));
//
//						congregationBibleStudyMinTextField
//								.setText(scheduleForMeeting.getCongregationBibleStudy().getMin().toString());
//						congregationBibleStudyTextTextField
//								.setText(scheduleForMeeting.getCongregationBibleStudy().getTextPart());
//						congregationBibleStudyMaterialTextField
//								.setText(scheduleForMeeting.getCongregationBibleStudy().getBody());
//
//						reviewMinTextField.setText(scheduleForMeeting.getReview().getMin().toString());
//						reviewTextTextField.setText(scheduleForMeeting.getReview().getTitle());
//
//						song3TextField.setText(scheduleForMeeting.getSong3().getSongNo().toString());

			getWOLNewInfo();

		} else {
			new AlertDesigner(language.getString("sp.meetings.wol.error"), ownerStage, AlertType.ERROR,
					Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
					Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
		}
	}
//			}
//		}
//	}

	private void getWOLNewInfo() {

		try {

			Document document = Jsoup
					.connect(WatchtowerOnlineLibrary.createLink(this.language, this.selectedWeek.getFrom())).get();

			newMethode(document);

		} catch (IOException e) {
			this.application.getAlertBuilder2().error(this.ownerStage, e.getMessage());
		}
	}

	private void watchtowerStudy(Element document) throws IOException {

		Elements groupTOCs = document.getElementsByClass("groupTOC");
		if (groupTOCs.size() > 0) {

			Element first = groupTOCs.get(0);

			Elements tags = first.getElementsByTag("a");
			if (tags.size() > 0) {

				Element firstTag = tags.get(0);

				// TITOLO DELLA TORRE DI GUARDIA
				this.watchtowerStudyThemeTextField.setText(firstTag.text());

				Elements attributes = firstTag.getElementsByAttribute("href");

				if (attributes.size() > 0) {

					String attr = attributes.attr("href");

					String watchtowerLink = WOLUtils.ADDRESS + attr;
					getWOLWatchtowerSongs(watchtowerLink);
				}

			}

		}
	}

	private void newMethode(Document document) throws IOException {

		// System.out.println(document);
		// System.out.println("\n==================================\n");

		Elements todayItemsList = document.getElementsByClass("todayItems");
		if (todayItemsList.size() == 1) {

			Element todayItems = todayItemsList.get(0);

			Elements itemDataList = todayItems.getElementsByClass("itemData");

			for (int i = 0; i < itemDataList.size(); i++) {

				Element element = itemDataList.get(i);
				switch (WOLUtils.checkItemData(element)) {

				case SCRIPTURE_OF_THE_DAY:
					// Informazioni non necessarie
					break;
				case MEETING:
					meeting(element);
					break;
				case WATCHTOWER_STUDY:
					watchtowerStudy(element);
					break;
				case UNKNOW:
					System.out.println("UNKNOW");
					System.out.println("\n===================================\n");
					System.out.println(element);
					break;

				}
			}
		}
	}

	private void meeting(Element element) {

		// SETTIMANA DEL (non utilizzato)

		// String week = "";
		// Elements pageNum = element.getElementsByClass("pageNum");
		// if (pageNum.size() == 1)
		// week = pageNum.get(0).parent().text();

		// LETTURA BIBLICA
		meetingBibleWeek(element);

		// SEZIONI
		Elements sections = element.getElementsByClass("section");
		// Sezione 1
		// - Cantico iniziale
		// - Commenti introduttivi
		meetingSection1(sections);
		// Sezione 2
		// - Discorso
		// - Gemme
		// - Lettura
		meetingSection2(sections);
		// Sezione 3
		meetingSection3(sections);
		// Sezione 4
		meetingSection4(sections);
	}

	private void meetingBibleWeek(Element element) {

		String bibleWeek = "";
		// class "b" -> sono tutti i riferimenti scritturali dell'articolo
		// il primo riferimento è la lettura biblica settimanale

		Elements bs = element.getElementsByClass("b");
		if (bs.size() > 0)
			bibleWeek = bs.get(0).text();

		if (!bibleWeek.isEmpty())
			this.bibleChaptersTextField.setText(bibleWeek);
	}

	private void meetingSection1(Elements sections) {

		// SEZIONE 1 - Introduzione

		String song1 = "";
		String comments1 = "";
		String comments1min = "";

		if (sections.size() > 0) {

			Element section1 = sections.get(0);

			Elements s1soList = section1.getElementsByClass("so");

			// 1. Cantico iniziale
			if (s1soList.size() > 0) {
				Element s1so1 = s1soList.get(0);
				String song1text = s1so1.text();
				Matcher song1match = Pattern.compile(this.language.getString("match.wol.song1midweek"))
						.matcher(song1text);

				if (song1match.find())
					song1 = song1match.group();

				if (!song1.isEmpty())
					this.song1TextField.setText(song1);
			}

			// 2. Commenti introduttivi
			if (s1soList.size() > 1) {

				Element s1so2 = s1soList.get(1);
				String comments1text = s1so2.text();

				// Commenti introduttivi - Testo
				Matcher comments1match = Pattern.compile(this.language.getString("match.wol.comments1midweek"))
						.matcher(comments1text);

				if (comments1match.find())
					comments1 = comments1match.group();

				if (!comments1.isEmpty())
					this.openingCommentsTextTextField.setText(comments1);

				// Commenti introduttivi - Minuti
				comments1match = Pattern.compile(this.language.getString("match.wol.comments1minmidweek"))
						.matcher(comments1text);

				if (comments1match.find()) {
					comments1match = Pattern.compile("\\d+").matcher(comments1match.group());
					comments1match.find();
					comments1min = comments1match.group();

					if (!comments1min.isEmpty())
						this.openingCommentsMinTextField.setText(comments1min);
				}
			}
		}
	}

	private void meetingSection2(Elements sections) {

		// SEZIONE 2 - Tesori della Parola di Dio

		String talk = "";
		String talkmin = "";
		String gems = "";
		String gemsmin = "";
		String bibleread = "";
		String biblereadmin = "";
		String biblereadvers = "";
		@SuppressWarnings("unused")
		String biblereadpoint = "";

		if (sections.size() > 1) {

			Element section = sections.get(1);

			Elements soList = section.getElementsByClass("so");

			// 1. Discorso
			if (soList.size() > 0) {

				Element so1 = soList.get(0);
				String so1text = so1.text();

				// I doublequote HTML non vengono riconosciuti
				char dlquoteStart = (char) 8220;
				char dlquoteEnd = (char) 8221;
				so1text = so1text.replace(dlquoteStart, '"');
				so1text = so1text.replace(dlquoteEnd, '"');

				Matcher talkMinMatch = Pattern.compile(this.language.getString("match.wol.treasminmidweek"))
						.matcher(so1text);
				if (talkMinMatch.find()) {

					String minGroup = talkMinMatch.group();
					Matcher minMatch = Pattern.compile(this.language.getString("match.wol.min")).matcher(minGroup);

					if (minMatch.find()) {

						talkmin = minMatch.group();

						int minGroupIndex = so1text.indexOf(minGroup);
						if (minGroupIndex > -1)
							talk = so1text.substring(0, minGroupIndex).trim();
					}
				}

				if (!talkmin.isEmpty())
					this.talkMinTextField.setText(talkmin);

				if (!talk.isEmpty())
					this.talkTextTextField.setText(talk);
			}

			// 2. Gemme
			if (soList.size() > 1) {
				Element so = soList.get(1);
				String sotext = so.text();

				Matcher gemsMinMatch = Pattern.compile(this.language.getString("match.wol.gemsminmidweek"))
						.matcher(sotext);
				if (gemsMinMatch.find()) {

					String minGroup = gemsMinMatch.group();
					Matcher minMatch = Pattern.compile(this.language.getString("match.wol.min")).matcher(minGroup);

					if (minMatch.find()) {

						gemsmin = minMatch.group();

						int minGroupIndex = sotext.indexOf(minGroup);
						if (minGroupIndex > -1)
							gems = sotext.substring(0, minGroupIndex).trim();
					}
				}

				if (!gemsmin.isEmpty())
					this.diggingMinTextField.setText(gemsmin);

				if (!gems.isEmpty())
					this.diggingTextTextField.setText(gems);
			}

			// 3. Lettura biblica
			if (soList.size() > 2) {
				Element so = soList.get(2);
				String sotext = so.text();

				Matcher bibleMinMatch = Pattern.compile(this.language.getString("match.wol.bibleminmidweek"))
						.matcher(sotext);
				if (bibleMinMatch.find()) {

					String minGroup = bibleMinMatch.group();

					Matcher minMatch = Pattern.compile(this.language.getString("match.wol.min")).matcher(minGroup);
					if (minMatch.find()) {

						biblereadmin = minMatch.group();

						int minGroupIndex = sotext.indexOf(minGroup);
						if (minGroupIndex > -1)
							bibleread = sotext.substring(0, minGroupIndex).trim();

						// Materiale da leggere
						biblereadvers = sotext.substring(minGroupIndex + minGroup.length(), sotext.length()).trim();

						// Punto
						Matcher pointMatch = Pattern.compile(this.language.getString("match.wol.biblepointmidweek"))
								.matcher(biblereadvers);
						if (pointMatch.find()) {

							String pointGroup = pointMatch.group();

							int pointGroupIndex = biblereadvers.indexOf(pointGroup);
							if (pointGroupIndex > -1)
								biblereadvers = biblereadvers.substring(0, pointGroupIndex).trim();

							String bracket = this.language.getString("match.wol.biblepointbracketsmidweek");
							if (bracket.equals("1"))
								biblereadpoint = pointGroup.substring(1, pointGroup.length() - 1);
						}
					}
				}

				if (!biblereadmin.isEmpty())
					this.bibleReadingMinTextField.setText(biblereadmin);
				if (!bibleread.isEmpty())
					this.bibleReadingTextTextField.setText(bibleread);
				if (!biblereadvers.isEmpty())
					this.bibleReadingMaterialsTextField.setText(biblereadvers);

				// Al momento non utilizzato
				// if (!biblereadpoint.isEmpty()) {
				// }
			}
		}
	}

	private void meetingSection3(Elements sections) {

		// SEZIONE 3 - Discorsi di esercitazione

		if (sections.size() > 2) {

			Element section = sections.get(2);

			Elements soList = section.getElementsByClass("so");

			for (Element e : soList) {

				String ministryMin = "";
				String ministryTitle = "";
				String ministryInfo = "";

				String ministryText = e.text();

				Matcher minnistryMinMatch = Pattern.compile(this.language.getString("match.wol.ministryminmidweek"))
						.matcher(ministryText);

				if (minnistryMinMatch.find()) {

					String minGroup = minnistryMinMatch.group();
					Matcher minMatch = Pattern.compile(this.language.getString("match.wol.min")).matcher(minGroup);

					if (minMatch.find()) {

						ministryMin = minMatch.group();

						int minGroupIndex = ministryText.indexOf(minGroup);
						if (minGroupIndex > -1) {

							ministryTitle = ministryText.substring(0, minGroupIndex).trim();
							ministryInfo = ministryText.substring(minGroupIndex + minGroup.length()).trim();
						}

						int ministryMinInt = 0;
						if (!ministryMin.isEmpty())
							ministryMinInt = Integer.valueOf(ministryMin);

						this.ministryPartList.add(new MinistryPart(checkMinistryType(ministryTitle), ministryText,
								ministryMinInt, ministryTitle, ministryInfo, Member.emptyMember(this.language),
								Member.emptyMember(this.language), Member.emptyMember(this.language),
								Member.emptyMember(this.language)));
					}
				}
			}

			this.ministryTableView.refresh();
		}
	}

	private void meetingSection4(Elements sections) {

		// SEZIONE 4 - Vita cristiana

		String song2 = "";

		if (sections.size() > 3) {

			Element section = sections.get(3);

			Elements soList = section.getElementsByClass("so");

			// 1. Cantico centrale

			if (soList.size() > 0) {

				Element so1 = soList.get(0);
				String song2text = so1.text();
				Matcher song2match = Pattern.compile(this.language.getString("match.wol.song2midweek"))
						.matcher(song2text);

				if (song2match.find())
					song2 = song2match.group();
			}

			// 2. Parti vita cristiana
			// Le ultime 3 righe riguardano Studio biblico / Commenti conclusivi / Cantico

			int lastIndex = 1;
			for (int i = 1; i < soList.size() - 3; i++) {

				lastIndex = i + 1;

				String christianMin = "";
				String christianTitle = "";
				String christianInfo = "";

				String sotext = soList.get(i).text();

				// I doublequote HTML non vengono riconosciuti
				char dlquoteStart = (char) 8220;
				char dlquoteEnd = (char) 8221;
				sotext = sotext.replace(dlquoteStart, '"');
				sotext = sotext.replace(dlquoteEnd, '"');

				Matcher christianMinMatch = Pattern.compile(this.language.getString("match.wol.christianmidweek"))
						.matcher(sotext);
				if (christianMinMatch.find()) {

					String minGroup = christianMinMatch.group();
					Matcher minMatch = Pattern.compile(this.language.getString("match.wol.min")).matcher(minGroup);

					if (minMatch.find()) {

						christianMin = minMatch.group();

						int minGroupIndex = sotext.indexOf(minGroup);
						if (minGroupIndex > -1) {
							christianTitle = sotext.substring(0, minGroupIndex).trim();
							christianInfo = sotext.substring(minGroupIndex + minGroup.length()).trim();
						}

						int christianMinInt = 0;
						if (!christianMin.isEmpty())
							christianMinInt = Integer.valueOf(christianMin);

						this.christiansPartList.add(new ChristiansPart(sotext, christianMinInt, christianTitle,
								christianInfo, Member.emptyMember(this.language)));
					}
				}
			}

			// 3. Studio biblico di congregazione

			String congrMin = "";
			String congrTitle = "";
			String congrInfo = "";

			if (soList.size() > lastIndex) {

				Element so = soList.get(lastIndex);
				String sotext = so.text();

				Matcher congrMinMatch = Pattern.compile(this.language.getString("match.wol.congrbiblestudyminmidweek"))
						.matcher(sotext);
				if (congrMinMatch.find()) {

					String minGroup = congrMinMatch.group();
					Matcher minMatch = Pattern.compile(this.language.getString("match.wol.min")).matcher(minGroup);

					if (minMatch.find()) {

						congrMin = minMatch.group();

						int minGroupIndex = sotext.indexOf(minGroup);
						if (minGroupIndex > -1) {
							congrTitle = sotext.substring(0, minGroupIndex).trim();
							congrInfo = sotext.substring(minGroupIndex + minGroup.length()).trim();
						}
					}
				}
			}

			// 4. Commenti conclusivi

			String conclMin = "";
			String conclTitle = "";

			if (soList.size() > lastIndex + 1) {

				Element so = soList.get(lastIndex + 1);
				String sotext = so.text();

				Matcher conclMinMatch = Pattern.compile(this.language.getString("match.wol.conclcommentsminmidweek"))
						.matcher(sotext);
				if (conclMinMatch.find()) {

					String minGroup = conclMinMatch.group();
					Matcher minMatch = Pattern.compile(this.language.getString("match.wol.min")).matcher(minGroup);

					if (minMatch.find()) {

						conclMin = minMatch.group();

						int minGroupIndex = sotext.indexOf(minGroup);
						if (minGroupIndex > -1)
							conclTitle = sotext.substring(0, minGroupIndex).trim();
					}
				}
			}

			// 5. Cantico finale

			String song3 = "";

			if (soList.size() > lastIndex + 2) {

				Element so = soList.get(lastIndex + 2);
				String sotext = so.text();

				Matcher song3match = Pattern.compile(this.language.getString("match.wol.song3midweek")).matcher(sotext);

				if (song3match.find())
					song3 = song3match.group();
			}

			if (!song2.isEmpty())
				this.song2TextField.setText(song2);

			this.christiansPartTableView.refresh();

			if (!congrMin.isEmpty())
				this.congregationBibleStudyMinTextField.setText(congrMin);

			if (!congrTitle.isEmpty())
				this.congregationBibleStudyTextTextField.setText(congrTitle);

			if (!congrInfo.isEmpty())
				this.congregationBibleStudyMaterialTextField.setText(congrInfo);

			if (!conclMin.isEmpty())
				this.reviewMinTextField.setText(conclMin);

			if (!conclTitle.isEmpty())
				this.reviewTextTextField.setText(conclTitle);

			if (!song3.isEmpty())
				this.song3TextField.setText(song3);
		}
	}

	private MinistryTypeTranslated checkMinistryType(String ministryTitle) {

		if (ministryTitle.matches(this.language.getString("match.wol.ministry.initialcall")))
			return new MinistryTypeTranslated(MinistryType.INITIAL_CALL, this.language);

		if (ministryTitle.matches(this.language.getString("match.wol.ministry.returnvisit")))
			return new MinistryTypeTranslated(MinistryType.RETURN_VISIT, this.language);

		if (ministryTitle.matches(this.language.getString("match.wol.ministry.biblestudy")))
			return new MinistryTypeTranslated(MinistryType.BIBLE_STUDY, this.language);

		if (ministryTitle.matches(this.language.getString("match.wol.ministry.talk")))
			return new MinistryTypeTranslated(MinistryType.TALK, this.language);

		return new MinistryTypeTranslated(MinistryType.DISCUSSION, this.language);
	}

	private void getWOLWatchtowerSongs(String watchtowerLink) throws IOException {

		Document document = Jsoup.connect(watchtowerLink).get();

		Elements pubRefs = document.getElementsByClass("pubRefs");
		if (pubRefs.size() > 0) {

			String song1 = "";
			String song2 = "";

			for (Element a : pubRefs) {

				String text = a.text();

				Matcher matcher = Pattern.compile("\\d+").matcher(text);
				if (matcher.find()) {

					String group = matcher.group();

					if (!song1.isEmpty())
						song2 = group;
					else
						song1 = group;

				}
			}

			this.watchtowerStudySong2TextField.setText(song1);
			this.watchtowerStudySong3TextField.setText(song2);
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
					Meta.Resources.getImageApplicationIcon(), Meta.Themes.SUPPORTPLANNER_THEME, "alert_001");

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
			ctrl.setConductor1(this.presidentComboBox.getSelectionModel().getSelectedItem());
			ctrl.setConductor2(this.conductorSecondHallComboBox.getSelectionModel().getSelectedItem());
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
					Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
					Meta.Themes.SUPPORTPLANNER_THEME, "alert_001");

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

	@Override
	public void updateSelectedComboBox(Privileges privilege, int memberID) {

		switch (privilege) {
		case MIDWEEK_PRESIDENT:
			updateComboBox(this.presidentComboBox, memberID);
			break;
		case MIDWEEK_PRAY_START:
			updateComboBox(this.pray1ComboBox, memberID);
			break;
		case MIDWEEK_TALK:
			updateComboBox(this.talkComboBox, memberID);
			break;
		case MIDWEEK_DIGGING:
			updateComboBox(this.diggingComboBox, memberID);
			break;
		case MIDWEEK_BIBLE_READING_A:
			updateComboBox(this.bibleReadingComboBox, memberID);
			break;
		case MIDWEEK_BIBLE_READING_B:
			updateComboBox(this.bibleReading2ComboBox, memberID);
			break;
		case MIDWEEK_CONGRBIBLESTUDY:
			updateComboBox(this.congregationBibleStudyComboBox, memberID);
			break;
		case MIDWEEK_PRAY_END:
			updateComboBox(this.pray2ComboBox, memberID);
			break;
		case MIDWEEK_CONGRBIBLESTUDY_READER:
			updateComboBox(this.congregationBibleStudyReaderComboBox, memberID);
			break;
		case WEEKEND_PRESIDENT:
			updateComboBox(this.presidentPublicMeetingComboBox, memberID);
			break;
		case WEEKEND_WATCHTOWER:
			updateComboBox(this.watchtowerStudyConductorComboBox, memberID);
			break;
		case WEEKEND_WATCHTOWER_READER:
			updateComboBox(this.watchtowerStudyReaderComboBox, memberID);
			break;
		case WEEKEND_PRAY_END:
			updateComboBox(this.watchtowerStudyPray2ComboBox, memberID);
			break;
		default:
			break;
		}
	}

	public void updateComboBox(ComboBox<Member> cb, int memberID) {

		int index = -1;

		for (int i = 0; i < cb.getItems().size(); i++) {
			if (cb.getItems().get(i).getSpMemberID() == memberID) {
				index = i;
				break;
			}
		}

		cb.getSelectionModel().select(index);
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

	public ObservableList<Week> getDatabaseWeeks() {
		return databaseWeeks;
	}

	public void setDatabaseWeeks(ObservableList<Week> databaseWeeks) {
		this.databaseWeeks = databaseWeeks;
	}

	public ComboBox<Member> getPresidentComboBox() {
		return presidentComboBox;
	}

	public void setPresidentComboBox(ComboBox<Member> presidentComboBox) {
		this.presidentComboBox = presidentComboBox;
	}

	public ComboBox<Member> getPray1ComboBox() {
		return pray1ComboBox;
	}

	public void setPray1ComboBox(ComboBox<Member> pray1ComboBox) {
		this.pray1ComboBox = pray1ComboBox;
	}

	public ComboBox<Member> getTalkComboBox() {
		return talkComboBox;
	}

	public void setTalkComboBox(ComboBox<Member> talkComboBox) {
		this.talkComboBox = talkComboBox;
	}

	public ComboBox<Member> getDiggingComboBox() {
		return diggingComboBox;
	}

	public void setDiggingComboBox(ComboBox<Member> diggingComboBox) {
		this.diggingComboBox = diggingComboBox;
	}

	public ComboBox<Member> getBibleReadingComboBox() {
		return bibleReadingComboBox;
	}

	public void setBibleReadingComboBox(ComboBox<Member> bibleReadingComboBox) {
		this.bibleReadingComboBox = bibleReadingComboBox;
	}

	public ComboBox<Member> getBibleReading2ComboBox() {
		return bibleReading2ComboBox;
	}

	public void setBibleReading2ComboBox(ComboBox<Member> bibleReading2ComboBox) {
		this.bibleReading2ComboBox = bibleReading2ComboBox;
	}

	public ComboBox<Member> getCongregationBibleStudyComboBox() {
		return congregationBibleStudyComboBox;
	}

	public void setCongregationBibleStudyComboBox(ComboBox<Member> congregationBibleStudyComboBox) {
		this.congregationBibleStudyComboBox = congregationBibleStudyComboBox;
	}

	public ComboBox<Member> getPray2ComboBox() {
		return pray2ComboBox;
	}

	public void setPray2ComboBox(ComboBox<Member> pray2ComboBox) {
		this.pray2ComboBox = pray2ComboBox;
	}

	public ComboBox<Member> getPresidentPublicMeetingComboBox() {
		return presidentPublicMeetingComboBox;
	}

	public ComboBox<Member> getWatchtowerStudyConductorComboBox() {
		return watchtowerStudyConductorComboBox;
	}

	public ComboBox<Member> getWatchtowerStudyReaderComboBox() {
		return watchtowerStudyReaderComboBox;
	}

	public ComboBox<Member> getWatchtowerStudyPray2ComboBox() {
		return watchtowerStudyPray2ComboBox;
	}

	public ComboBox<Member> getCongregationBibleStudyReaderComboBox() {
		return congregationBibleStudyReaderComboBox;
	}

	public AlertBuilderOld getAlertBuilder() {
		return alertBuilder;
	}

	public void setAlertBuilder(AlertBuilderOld alertBuilder) {
		this.alertBuilder = alertBuilder;
	}

	public SupportPlannerView getApplication() {
		return application;
	}

	public void setApplication(SupportPlannerView application) {
		this.application = application;
	}

	public ObservableList<Member> getConductorSecondHallList() {
		return conductorSecondHallList;
	}

	public void setConductorSecondHallList(ObservableList<Member> conductorSecondHallList) {
		this.conductorSecondHallList = conductorSecondHallList;
	}

	public ObservableList<WeekAudio> getDatabaseWeeksAudio() {
		return databaseWeeksAudio;
	}

	public void setDatabaseWeeksAudio(ObservableList<WeekAudio> databaseWeeksAudio) {
		this.databaseWeeksAudio = databaseWeeksAudio;
	}

	public HashMap<String, String> getConfigs() {
		return configs;
	}

	public void setConfigs(HashMap<String, String> configs) {
		this.configs = configs;
	}

	public ObservableList<WeekUsciere> getDatabaseWeeksUsciere() {
		return databaseWeeksUsciere;
	}

	public void setDatabaseWeeksUsciere(ObservableList<WeekUsciere> databaseWeeksUsciere) {
		this.databaseWeeksUsciere = databaseWeeksUsciere;
	}
}
