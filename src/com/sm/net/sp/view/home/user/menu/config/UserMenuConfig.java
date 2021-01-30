package com.sm.net.sp.view.home.user.menu.config;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.tika.exception.TikaException;
import org.apache.tika.io.TikaInputStream;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.BodyContentHandler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.xml.sax.SAXException;

import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.model.EnumPDFReplaceType;
import com.sm.net.sp.model.PDFReplace;
import com.sm.net.sp.model.Song;
import com.sm.net.sp.view.SupportPlannerView;
import com.sm.net.sp.view.home.user.menu.config.task.ConfigLoadTask;
import com.sm.net.sp.view.home.user.menu.config.task.ConfigPDFReplaceLoadTask;
import com.sm.net.sp.view.home.user.menu.config.task.ConfigPDFReplaceRemoveTask;
import com.sm.net.sp.view.home.user.menu.config.task.ConfigPDFReplaceSaveTask;
import com.sm.net.sp.view.home.user.menu.config.task.ConfigSaveTask;
import com.sm.net.sp.view.home.user.menu.config.task.ConfigSongsLoadTask;
import com.sm.net.sp.view.home.user.menu.config.task.ConfigSongsSaveTask;
import com.sm.net.util.Crypt;
import com.smnet.core.task.TaskManager;

import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

public class UserMenuConfig {

	@FXML
	private ImageView headerImageView;
	@FXML
	private Label headerLabel;

	@FXML
	private Button saveButton;

	@FXML
	private TabPane tabPane;
	@FXML
	private TabPane postTabPane;

	@FXML
	private Tab placesTab;
	@FXML
	private Tab publicTalkTab;
	@FXML
	private Tab watchtowerTab;
	@FXML
	private Tab overseerTab;
	@FXML
	private Tab memorialTab;
	@FXML
	private Tab audioTab;
	@FXML
	private Tab usciereTab;
	@FXML
	private Tab songsTab;
	@FXML
	private Tab postTab;

	@FXML
	private Tab postTabPanePDFReaderTab;
	@FXML
	private Tab postTabPaneTargetTab;
	@FXML
	private Tab postTabPaneReplaceTab;

	@FXML
	private ScrollPane placesScrollPane;
	@FXML
	private ScrollPane publicTalkScrollPane;
	@FXML
	private ScrollPane watchtowerScrollPane;
	@FXML
	private ScrollPane overseerScrollPane;
	@FXML
	private ScrollPane memorialScrollPane;
	@FXML
	private ScrollPane audioScrollPane;
	@FXML
	private ScrollPane usciereScrollPane;
	@FXML
	private ScrollPane songsScrollPane;
	@FXML
	private ScrollPane postScrollPane;

	@FXML
	private Label placesHeaderLabel;
	@FXML
	private Label publicTalkHeaderLabel;
	@FXML
	private Label watchtowerHeaderLabel;
	@FXML
	private Label overseerHeaderLabel;
	@FXML
	private Label memorialHeaderLabel;
	@FXML
	private Label audioHeaderLabel;
	@FXML
	private Label usciereHeaderLabel;
	@FXML
	private Label songsHeaderLabel;
	@FXML
	private Label postHeaderLabel;

	@FXML
	private Label placesPatternLabel;
	@FXML
	private Label publicTalkMinLabel;
	@FXML
	private Label watchtowerMinLabel;
	@FXML
	private Label overseerTalk1MinLabel;
	@FXML
	private Label overseerTalk2MinLabel;
	@FXML
	private Label overseerTalk3MinLabel;
	@FXML
	private Label overseerVisitCounterLabel;
	@FXML
	private Label memorialTalkMinLabel;
	@FXML
	private Label audioPos1NameLabel;
	@FXML
	private Label audioPos2NameLabel;
	@FXML
	private Label audioPos3NameLabel;
	@FXML
	private Label usciereZone1NameLabel;
	@FXML
	private Label usciereZone2NameLabel;
	@FXML
	private Label usciereZone3NameLabel;
	@FXML
	private Label songsMinLabel;
	@FXML
	private Label songsSourceLabel;

	@FXML
	private TextField placesPatternTextField;
	@FXML
	private TextField publicTalkMinTextField;
	@FXML
	private TextField watchtowerMinTextField;
	@FXML
	private TextField overseerTalk1MinTextField;
	@FXML
	private TextField overseerTalk2MinTextField;
	@FXML
	private TextField overseerTalk3MinTextField;
	@FXML
	private TextField overseerVisitCounterTextField;
	@FXML
	private TextField memorialTalkMinTextField;

	@FXML
	private TextField audioPos1NameTextField;
	@FXML
	private TextField audioPos2NameTextField;
	@FXML
	private TextField audioPos3NameTextField;
	@FXML
	private TextField usciereZone1NameTextField;
	@FXML
	private TextField usciereZone2NameTextField;
	@FXML
	private TextField usciereZone3NameTextField;

	@FXML
	private TextField songsMinTextField;
	@FXML
	private TextField songsSourceTextField;

	@FXML
	private CheckBox usciereZone1WeekendCheckBox;
	@FXML
	private CheckBox usciereZone2WeekendCheckBox;
	@FXML
	private CheckBox usciereZone3WeekendCheckBox;

	@FXML
	private TableView<Song> songsTableView;
	@FXML
	private TableColumn<Song, Integer> songsNumTableColumn;
	@FXML
	private TableColumn<Song, String> songsTitleTableColumn;

	@FXML
	private Button placesPatternButton;
	@FXML
	private Button songsDownloadButton;

	@FXML
	private Label songsLoadLabel;
	@FXML
	private CheckBox songsLoadCheckBox;

	@FXML
	private Button pdfReaderTestButton;
	@FXML
	private Label pdfReaderTestLabel;
	@FXML
	private TextArea pdfReaderTestTextArea;

	@FXML
	private Label pdfReplaceTypeLabel;
	@FXML
	private Label pdfReplaceValueLabel;
	@FXML
	private Label pdfReplaceTextLabel;
	@FXML
	private ComboBox<EnumPDFReplaceType> pdfReplaceTypeComboBox;
	@FXML
	private TextField pdfReplaceValueTextField;
	@FXML
	private TextField pdfReplaceTextTextField;
	@FXML
	private Button pdfReplaceAddButton;
	@FXML
	private Button pdfReplaceRemoveButton;
	@FXML
	private TableView<PDFReplace> pdfReplaceTableView;
	@FXML
	private TableColumn<PDFReplace, EnumPDFReplaceType> pdfReplaceTypeTableColumn;
	@FXML
	private TableColumn<PDFReplace, String> pdfReplaceValueTableColumn;
	@FXML
	private TableColumn<PDFReplace, String> pdfReplaceTextTableColumn;

	private SupportPlannerView application;
	private Stage ownerStage;

	private HashMap<String, String> configs;

	private ObservableList<Song> songList;
	private ObservableList<PDFReplace> pdfReplaceList;

	@FXML
	private void initialize() {

		styleClasses();
		cellFactory();
		cellValueFactory();
	}

	private void cellFactory() {

		this.pdfReplaceTypeTableColumn.setCellFactory(param -> tableCellForEnumPDFReplaceType());

	}

	private void cellValueFactory() {

		this.songsNumTableColumn.setCellValueFactory(cellData -> cellData.getValue().getNumberProperty().asObject());
		this.songsTitleTableColumn.setCellValueFactory(cellData -> cellData.getValue().getTitleProperty());

		this.pdfReplaceTypeTableColumn.setCellValueFactory(cellData -> {
			Integer spInf1 = cellData.getValue().getSpInf1();
			return new SimpleObjectProperty<EnumPDFReplaceType>(EnumPDFReplaceType.valueByID(spInf1));
		});
		this.pdfReplaceValueTableColumn.setCellValueFactory(cellData -> cellData.getValue().getSpInf2Property());
		this.pdfReplaceTextTableColumn.setCellValueFactory(cellData -> cellData.getValue().getSpInf3Property());
	}

	private TableCell<PDFReplace, EnumPDFReplaceType> tableCellForEnumPDFReplaceType() {

		return new TableCell<PDFReplace, EnumPDFReplaceType>() {
			@Override
			protected void updateItem(EnumPDFReplaceType item, boolean empty) {
				super.updateItem(item, empty);

				if (empty)
					setText(null);
				else {
					setText(application.getSettings().getLanguage().getString(item.getTextKey().get()));
				}
			}
		};
	}

	public void objectInitialize() {

		viewUpdate();
		initData();
		listeners();
	}

	private void styleClasses() {

		this.headerLabel.getStyleClass().add("label_header_001");

		this.tabPane.getStyleClass().add("tab_pane_003");
		this.postTabPane.getStyleClass().add("tab_pane_003");

		this.placesTab.getStyleClass().add("tab_001");
		this.publicTalkTab.getStyleClass().add("tab_001");
		this.watchtowerTab.getStyleClass().add("tab_001");
		this.overseerTab.getStyleClass().add("tab_001");
		this.memorialTab.getStyleClass().add("tab_001");
		this.audioTab.getStyleClass().add("tab_001");
		this.usciereTab.getStyleClass().add("tab_001");
		this.songsTab.getStyleClass().add("tab_001");
		this.postTab.getStyleClass().add("tab_001");
		this.postTabPanePDFReaderTab.getStyleClass().add("tab_001");
		this.postTabPaneReplaceTab.getStyleClass().add("tab_001");
		this.postTabPaneTargetTab.getStyleClass().add("tab_001");

		this.placesScrollPane.getStyleClass().add("scroll_pane_001");
		this.publicTalkScrollPane.getStyleClass().add("scroll_pane_001");
		this.watchtowerScrollPane.getStyleClass().add("scroll_pane_001");
		this.overseerScrollPane.getStyleClass().add("scroll_pane_001");
		this.memorialScrollPane.getStyleClass().add("scroll_pane_001");
		this.audioScrollPane.getStyleClass().add("scroll_pane_001");
		this.usciereScrollPane.getStyleClass().add("scroll_pane_001");
		this.songsScrollPane.getStyleClass().add("scroll_pane_001");
		this.postScrollPane.getStyleClass().add("scroll_pane_001");

		this.placesPatternLabel.getStyleClass().add("label_set_001");
		this.publicTalkMinLabel.getStyleClass().add("label_set_001");
		this.watchtowerMinLabel.getStyleClass().add("label_set_001");
		this.overseerTalk1MinLabel.getStyleClass().add("label_set_001");
		this.overseerTalk2MinLabel.getStyleClass().add("label_set_001");
		this.overseerTalk3MinLabel.getStyleClass().add("label_set_001");
		this.overseerVisitCounterLabel.getStyleClass().add("label_set_001");
		this.memorialTalkMinLabel.getStyleClass().add("label_set_001");
		this.audioPos1NameLabel.getStyleClass().add("label_set_001");
		this.audioPos2NameLabel.getStyleClass().add("label_set_001");
		this.audioPos3NameLabel.getStyleClass().add("label_set_001");
		this.usciereZone1NameLabel.getStyleClass().add("label_set_001");
		this.usciereZone2NameLabel.getStyleClass().add("label_set_001");
		this.usciereZone3NameLabel.getStyleClass().add("label_set_001");
		this.songsMinLabel.getStyleClass().add("label_set_001");
		this.songsSourceLabel.getStyleClass().add("label_set_001");

		this.placesPatternTextField.getStyleClass().add("text_field_001");
		this.publicTalkMinTextField.getStyleClass().add("text_field_002");
		this.watchtowerMinTextField.getStyleClass().add("text_field_002");
		this.overseerTalk1MinTextField.getStyleClass().add("text_field_002");
		this.overseerTalk2MinTextField.getStyleClass().add("text_field_002");
		this.overseerTalk3MinTextField.getStyleClass().add("text_field_002");
		this.overseerVisitCounterTextField.getStyleClass().add("text_field_002");
		this.memorialTalkMinTextField.getStyleClass().add("text_field_002");
		this.audioPos1NameTextField.getStyleClass().add("text_field_001");
		this.audioPos2NameTextField.getStyleClass().add("text_field_001");
		this.audioPos3NameTextField.getStyleClass().add("text_field_001");
		this.usciereZone1NameTextField.getStyleClass().add("text_field_001");
		this.usciereZone2NameTextField.getStyleClass().add("text_field_001");
		this.usciereZone3NameTextField.getStyleClass().add("text_field_001");
		this.songsMinTextField.getStyleClass().add("text_field_002");
		this.songsSourceTextField.getStyleClass().add("text_field_001");

		this.placesPatternButton.getStyleClass().add("button_image_001");
		this.saveButton.getStyleClass().add("button_image_001");
		this.songsDownloadButton.getStyleClass().add("button_image_001");
		this.pdfReaderTestButton.getStyleClass().add("button_image_001");

		this.placesHeaderLabel.getStyleClass().add("label_002");
		this.publicTalkHeaderLabel.getStyleClass().add("label_002");
		this.watchtowerHeaderLabel.getStyleClass().add("label_002");
		this.overseerHeaderLabel.getStyleClass().add("label_002");
		this.memorialHeaderLabel.getStyleClass().add("label_002");
		this.audioHeaderLabel.getStyleClass().add("label_002");
		this.usciereHeaderLabel.getStyleClass().add("label_002");
		this.songsHeaderLabel.getStyleClass().add("label_002");
		this.postHeaderLabel.getStyleClass().add("label_002");

		this.usciereZone1WeekendCheckBox.getStyleClass().add("check_box_001");
		this.usciereZone2WeekendCheckBox.getStyleClass().add("check_box_001");
		this.usciereZone3WeekendCheckBox.getStyleClass().add("check_box_001");

		this.songsTableView.getStyleClass().add("table_view_001");
		this.songsNumTableColumn.getStyleClass().add("table_column_002");

		this.songsLoadLabel.getStyleClass().add("label_set_001");
		this.songsLoadCheckBox.getStyleClass().add("check_box_set_001");

		this.pdfReaderTestLabel.getStyleClass().add("label_001");
		this.pdfReaderTestTextArea.getStyleClass().add("text_area_001");

		this.pdfReplaceTypeLabel.getStyleClass().add("label_set_001");
		this.pdfReplaceValueLabel.getStyleClass().add("label_set_001");
		this.pdfReplaceTextLabel.getStyleClass().add("label_set_001");
		this.pdfReplaceTypeComboBox.getStyleClass().add("combo_box_001");
		this.pdfReplaceValueTextField.getStyleClass().add("text_field_001");
		this.pdfReplaceTextTextField.getStyleClass().add("text_field_001");
		this.pdfReplaceAddButton.getStyleClass().add("button_image_001");
		this.pdfReplaceRemoveButton.getStyleClass().add("button_image_001");
		this.pdfReplaceTableView.getStyleClass().add("table_view_001");
	}

	private void viewUpdate() {

		Language language = this.application.getSettings().getLanguage();

		this.headerImageView.setFitWidth(50);
		this.headerImageView.setFitHeight(50);
		this.headerImageView.setImage(Meta.Resources.getImageLogo(Meta.Resources.CONFIG, 50, 50));

		this.headerLabel.setText(language.getString("sp.menu.config"));

		this.tabPane.setTabMinHeight(75);
		this.tabPane.setTabMaxHeight(75);
		this.postTabPane.setTabMinHeight(75);
		this.postTabPane.setTabMaxHeight(75);

		Tooltip audioTabTooltip = new Tooltip(language.getString("conf.tooltip.tab.audio"));
		audioTabTooltip.getStyleClass().add("tooltip_001");
		this.audioTab.setTooltip(audioTabTooltip);
		this.audioTab.setText("");
		this.audioTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.AUDIO));

		Tooltip usciereTabTooltip = new Tooltip(language.getString("conf.tooltip.tab.usciere"));
		usciereTabTooltip.getStyleClass().add("tooltip_001");
		this.usciereTab.setTooltip(usciereTabTooltip);
		this.usciereTab.setText("");
		this.usciereTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.USCIERE));

		Tooltip placesTabTooltip = new Tooltip(language.getString("conf.tooltip.tab.places"));
		placesTabTooltip.getStyleClass().add("tooltip_001");
		this.placesTab.setTooltip(placesTabTooltip);
		this.placesTab.setText("");
		this.placesTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.PLACES));

		Tooltip publicTalkTabTooltip = new Tooltip(language.getString("conf.tooltip.tab.publictalk"));
		publicTalkTabTooltip.getStyleClass().add("tooltip_001");
		this.publicTalkTab.setTooltip(publicTalkTabTooltip);
		this.publicTalkTab.setText("");
		this.publicTalkTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.PUBLIC_TALK));

		Tooltip watchtowerTabTooltip = new Tooltip(language.getString("conf.tooltip.tab.watchtower"));
		watchtowerTabTooltip.getStyleClass().add("tooltip_001");
		this.watchtowerTab.setTooltip(watchtowerTabTooltip);
		this.watchtowerTab.setText("");
		this.watchtowerTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.WOL));

		Tooltip overseerTabTooltip = new Tooltip(language.getString("conf.tooltip.tab.overseer"));
		overseerTabTooltip.getStyleClass().add("tooltip_001");
		this.overseerTab.setTooltip(overseerTabTooltip);
		this.overseerTab.setText("");
		this.overseerTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.USER_MENU_CIRCUITOVERSEER));

		Tooltip memorialTabTooltip = new Tooltip(language.getString("conf.tooltip.tab.memorial"));
		memorialTabTooltip.getStyleClass().add("tooltip_001");
		this.memorialTab.setTooltip(memorialTabTooltip);
		this.memorialTab.setText("");
		this.memorialTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.MEMORIAL));

		Tooltip songsTooltip = new Tooltip(language.getString("conf.tooltip.tab.songs"));
		songsTooltip.getStyleClass().add("tooltip_001");
		this.songsTab.setTooltip(songsTooltip);
		this.songsTab.setText(null);
		this.songsTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.SONGS));

		Tooltip placesPatternHelpTooltip = new Tooltip(language.getString("conf.tooltip.places.pattern.help"));
		placesPatternHelpTooltip.getStyleClass().add("tooltip_001");
		this.placesPatternButton.setTooltip(placesPatternHelpTooltip);
		this.placesPatternButton.setText(null);
		this.placesPatternButton.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.HELP));

		Tooltip saveTooltip = new Tooltip(language.getString("conf.tooltip.save"));
		saveTooltip.getStyleClass().add("tooltip_001");
		this.saveButton.setTooltip(saveTooltip);
		this.saveButton.setText(null);
		this.saveButton.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.SAVE));

		Tooltip songsDownloadTooltip = new Tooltip(language.getString("conf.tooltip.songsdownload"));
		songsDownloadTooltip.getStyleClass().add("tooltip_001");
		this.songsDownloadButton.setTooltip(songsDownloadTooltip);
		this.songsDownloadButton.setText(null);
		this.songsDownloadButton
				.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.USER_MENU_MEETINGS_WOL_LOAD));

		Tooltip postTabTooltip = new Tooltip(language.getString("conf.tooltip.tab.post"));
		postTabTooltip.getStyleClass().add("tooltip_001");
		this.postTab.setTooltip(postTabTooltip);
		this.postTab.setText("");
		this.postTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.POST));

		Tooltip postPDFTabTooltip = new Tooltip(language.getString("conf.tooltip.tab.postpdf"));
		postPDFTabTooltip.getStyleClass().add("tooltip_001");
		this.postTabPanePDFReaderTab.setTooltip(postPDFTabTooltip);
		this.postTabPanePDFReaderTab.setText("");
		this.postTabPanePDFReaderTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.PDF));

		Tooltip postReplaceTabTooltip = new Tooltip(language.getString("conf.tooltip.tab.postreplace"));
		postReplaceTabTooltip.getStyleClass().add("tooltip_001");
		this.postTabPaneReplaceTab.setTooltip(postReplaceTabTooltip);
		this.postTabPaneReplaceTab.setText("");
		this.postTabPaneReplaceTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.UPDATE));

		Tooltip postTargetTabTooltip = new Tooltip(language.getString("conf.tooltip.tab.posttarget"));
		postTargetTabTooltip.getStyleClass().add("tooltip_001");
		this.postTabPaneTargetTab.setTooltip(postTargetTabTooltip);
		this.postTabPaneTargetTab.setText("");
		this.postTabPaneTargetTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.POSTDEST));

		Tooltip postPDFButtonTooltip = new Tooltip(language.getString("conf.tooltip.postpdftest"));
		postPDFButtonTooltip.getStyleClass().add("tooltip_001");
		this.pdfReaderTestButton.setTooltip(postPDFButtonTooltip);
		this.pdfReaderTestButton.setText(null);
		this.pdfReaderTestButton.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.PDF));

		this.placesHeaderLabel.setText(language.getString("conf.label.places.header"));
		this.publicTalkHeaderLabel.setText(language.getString("conf.label.publictalk.header"));
		this.watchtowerHeaderLabel.setText(language.getString("conf.label.watchtower.header"));
		this.overseerHeaderLabel.setText(language.getString("conf.label.overseer.header"));
		this.memorialHeaderLabel.setText(language.getString("conf.label.memorial.header"));

		this.audioHeaderLabel.setText(language.getString("conf.label.audio.header"));
		this.usciereHeaderLabel.setText(language.getString("conf.label.usciere.header"));
		this.songsHeaderLabel.setText(language.getString("conf.label.songs.header"));
		this.postHeaderLabel.setText(language.getString("conf.label.post.header"));

		this.placesPatternLabel.setText(language.getString("conf.label.places.pattern"));
		this.publicTalkMinLabel.setText(language.getString("conf.label.publictalk.min"));
		this.watchtowerMinLabel.setText(language.getString("conf.label.watchtower.min"));
		this.overseerTalk1MinLabel.setText(language.getString("conf.label.overseer.talk1min"));
		this.overseerTalk2MinLabel.setText(language.getString("conf.label.overseer.talk2min"));
		this.overseerTalk3MinLabel.setText(language.getString("conf.label.overseer.talk3min"));
		this.overseerVisitCounterLabel.setText(language.getString("conf.label.overseer.visitcounter"));
		this.memorialTalkMinLabel.setText(language.getString("conf.label.memorial.talkmin"));

		this.audioPos1NameLabel.setText(language.getString("conf.label.audio.pos1name"));
		this.audioPos2NameLabel.setText(language.getString("conf.label.audio.pos2name"));
		this.audioPos3NameLabel.setText(language.getString("conf.label.audio.pos3name"));

		this.usciereZone1NameLabel.setText(language.getString("conf.label.usciere.zone1name"));
		this.usciereZone2NameLabel.setText(language.getString("conf.label.usciere.zone2name"));
		this.usciereZone3NameLabel.setText(language.getString("conf.label.usciere.zone3name"));

		this.usciereZone1WeekendCheckBox.setText(language.getString("conf.label.usciere.zone1weekend"));
		this.usciereZone2WeekendCheckBox.setText(language.getString("conf.label.usciere.zone2weekend"));
		this.usciereZone3WeekendCheckBox.setText(language.getString("conf.label.usciere.zone3weekend"));

		this.songsMinLabel.setText(language.getString("conf.label.songs.min"));
		this.songsSourceLabel.setText(language.getString("conf.label.songs.source"));
		this.songsNumTableColumn.setText(language.getString("conf.tablecolumn.songs.num"));
		this.songsNumTableColumn.setMaxWidth(500);
		this.songsTitleTableColumn.setText(language.getString("conf.tablecolumn.songs.title"));

		this.songsSourceTextField.setText(this.application.getSettings().getLanguage().getString("conf.songs.source"));
		this.songsLoadLabel.setText(this.application.getSettings().getLanguage().getString("conf.label.songs.load"));
		this.songsLoadCheckBox.setText("");

		this.pdfReaderTestLabel.setText(language.getString("conf.label.post.pdftest"));

		this.pdfReplaceTypeLabel.setText(language.getString("conf.label.post.pdfreplacetype"));
		this.pdfReplaceValueLabel.setText(language.getString("conf.label.post.pdfreplacevalue"));
		this.pdfReplaceTextLabel.setText(language.getString("conf.label.post.pdfreplacetext"));
		this.pdfReplaceTypeTableColumn.setText(language.getString("conf.label.post.pdfreplacecolumntype"));
		this.pdfReplaceValueTableColumn.setText(language.getString("conf.label.post.pdfreplacecolumnvalue"));
		this.pdfReplaceTextTableColumn.setText(language.getString("conf.label.post.pdfreplacecolumntext"));
		Tooltip pdfReplaceAddTooltip = new Tooltip(language.getString("conf.tooltip.pdfreplaceadd"));
		pdfReplaceAddTooltip.getStyleClass().add("tooltip_001");
		this.pdfReplaceAddButton.setTooltip(pdfReplaceAddTooltip);
		this.pdfReplaceAddButton.setText(null);
		this.pdfReplaceAddButton.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.ADD));
		Tooltip pdfReplaceRemoveTooltip = new Tooltip(language.getString("conf.tooltip.pdfreplaceremove"));
		pdfReplaceRemoveTooltip.getStyleClass().add("tooltip_001");
		this.pdfReplaceRemoveButton.setTooltip(pdfReplaceRemoveTooltip);
		this.pdfReplaceRemoveButton.setText(null);
		this.pdfReplaceRemoveButton.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.REMOVE));
	}

	private void initData() {

		this.songList = FXCollections.observableArrayList();
		this.songsTableView.setItems(this.songList);

		this.pdfReplaceList = FXCollections.observableArrayList();
		this.pdfReplaceTableView.setItems(this.pdfReplaceList);

		Callback<ListView<EnumPDFReplaceType>, ListCell<EnumPDFReplaceType>> callbackEnumPDFReplaceType = callbackForEnumPDFReplaceType();
		this.pdfReplaceTypeComboBox.setButtonCell(callbackEnumPDFReplaceType.call(null));
		this.pdfReplaceTypeComboBox.setCellFactory(callbackEnumPDFReplaceType);

		this.pdfReplaceTypeComboBox.getItems().addAll(EnumPDFReplaceType.values());
		this.pdfReplaceTypeComboBox.getSelectionModel().selectFirst();

		configLoad();
	}

	private Callback<ListView<EnumPDFReplaceType>, ListCell<EnumPDFReplaceType>> callbackForEnumPDFReplaceType() {
		return param -> new EnumPDFReplaceTypeComboBoxListCell(this.application.getSettings().getLanguage());
	}

	private void configLoad() {

		String waitMessage = this.application.getSettings().getLanguage().getString("conf.load.wait");

		TaskManager.run(this.application.getAlertBuilder2(), this.ownerStage, waitMessage, new ConfigLoadTask(
				this.application.getAlertBuilder2(), this.application.getSettings(), this.ownerStage, this));

	}

	public void fill() {

		String placesPattern = this.configs.get("inf1");
		if (placesPattern != null)
			this.placesPatternTextField
					.setText(Crypt.decrypt(placesPattern, this.application.getSettings().getDatabaseSecretKey()));

		String publicTalkMin = this.configs.get("inf2");
		if (publicTalkMin != null)
			this.publicTalkMinTextField
					.setText(Crypt.decrypt(publicTalkMin, this.application.getSettings().getDatabaseSecretKey()));

		String watchtowerMin = this.configs.get("inf3");
		if (watchtowerMin != null)
			this.watchtowerMinTextField
					.setText(Crypt.decrypt(watchtowerMin, this.application.getSettings().getDatabaseSecretKey()));

		// ----------------

		String overseerTalk1Min = this.configs.get("inf4");
		if (overseerTalk1Min != null)
			this.overseerTalk1MinTextField
					.setText(Crypt.decrypt(overseerTalk1Min, this.application.getSettings().getDatabaseSecretKey()));

		String overseerTalk2Min = this.configs.get("inf5");
		if (overseerTalk2Min != null)
			this.overseerTalk2MinTextField
					.setText(Crypt.decrypt(overseerTalk2Min, this.application.getSettings().getDatabaseSecretKey()));

		String overseerTalk3Min = this.configs.get("inf6");
		if (overseerTalk3Min != null)
			this.overseerTalk3MinTextField
					.setText(Crypt.decrypt(overseerTalk3Min, this.application.getSettings().getDatabaseSecretKey()));

		String overseerVisitCounter = this.configs.get("inf7");
		if (overseerVisitCounter != null)
			this.overseerVisitCounterTextField.setText(
					Crypt.decrypt(overseerVisitCounter, this.application.getSettings().getDatabaseSecretKey()));

		String memorialTalkMin = this.configs.get("inf8");
		if (memorialTalkMin != null)
			this.memorialTalkMinTextField
					.setText(Crypt.decrypt(memorialTalkMin, this.application.getSettings().getDatabaseSecretKey()));

		String audio1 = this.configs.get("inf9");
		if (audio1 != null)
			this.audioPos1NameTextField.setText(audio1);

		String audio2 = this.configs.get("inf10");
		if (audio2 != null)
			this.audioPos2NameTextField.setText(audio2);

		String audio3 = this.configs.get("inf11");
		if (audio3 != null)
			this.audioPos3NameTextField.setText(audio3);

		String usciere1 = this.configs.get("inf12");
		if (usciere1 != null)
			this.usciereZone1NameTextField.setText(usciere1);

		String usciere2 = this.configs.get("inf13");
		if (usciere2 != null)
			this.usciereZone2NameTextField.setText(usciere2);

		String usciere3 = this.configs.get("inf14");
		if (usciere3 != null)
			this.usciereZone3NameTextField.setText(usciere3);

		String usciere1equals = this.configs.get("inf15");
		if (usciere1equals != null) {
			usciere1equals = Crypt.decrypt(usciere1equals, this.application.getSettings().getDatabaseSecretKey());
			this.usciereZone1WeekendCheckBox.setSelected(usciere1equals.equals("1"));
		}

		String usciere2equals = this.configs.get("inf16");
		if (usciere2equals != null) {
			usciere2equals = Crypt.decrypt(usciere2equals, this.application.getSettings().getDatabaseSecretKey());
			this.usciereZone2WeekendCheckBox.setSelected(usciere2equals.equals("1"));
		}

		String usciere3equals = this.configs.get("inf17");
		if (usciere3equals != null) {
			usciere3equals = Crypt.decrypt(usciere3equals, this.application.getSettings().getDatabaseSecretKey());
			this.usciereZone3WeekendCheckBox.setSelected(usciere3equals.equals("1"));
		}

		String songsMin = this.configs.get("inf18");
		if (songsMin != null)
			this.songsMinTextField
					.setText(Crypt.decrypt(songsMin, this.application.getSettings().getDatabaseSecretKey()));

		String songsLoad = this.configs.get("inf19");
		if (songsLoad != null) {
			songsLoad = Crypt.decrypt(songsLoad, this.application.getSettings().getDatabaseSecretKey());
			this.songsLoadCheckBox.setSelected(songsLoad.equals("1"));
		}

		// CARICO I CANTICI
		this.updateSongList();

		// CARICO I PDF REPLACE
		this.updatePDFReplaceList();
	}

	private void listeners() {

		this.saveButton.setOnAction(event -> save());
		this.placesPatternButton.setOnAction(event -> showHelp());
		this.songsDownloadButton.setOnAction(event -> songsDownload());
		this.pdfReaderTestButton.setOnAction(event -> pdfTest());

		this.pdfReplaceAddButton.setOnAction(event -> pdfReplaceAdd());
		this.pdfReplaceRemoveButton.setOnAction(event -> pdfReplaceRemove());
	}

	private void pdfReplaceAdd() {

		String value = this.pdfReplaceValueTextField.getText();

		if (value.isEmpty()) {

			String content = this.application.getSettings().getLanguage().getString("conf.pdfreplace.errornovalue");
			this.application.getAlertBuilder2().error(this.ownerStage, content);

		} else {

			String content = this.application.getSettings().getLanguage().getString("conf.pdfreplace.confirmadd");
			if (this.application.getAlertBuilder2().confirm(this.ownerStage, content)) {

				int spInf1 = this.pdfReplaceTypeComboBox.getSelectionModel().getSelectedItem().getId();
				String spInf2 = Crypt.encrypt(this.pdfReplaceValueTextField.getText(),
						this.application.getSettings().getDatabaseSecretKey());
				String spInf3 = Crypt.encrypt(this.pdfReplaceTextTextField.getText(),
						this.application.getSettings().getDatabaseSecretKey());

				PDFReplace pdfReplace = new PDFReplace(spInf1, spInf2, spInf3);

				String waitMessage = this.application.getSettings().getLanguage().getString("conf.pdfreplace.savewait");

				TaskManager.run(this.getApplication().getAlertBuilder2(), this.ownerStage, waitMessage,
						new ConfigPDFReplaceSaveTask(this, this.application.getAlertBuilder2(),
								this.application.getSettings(), this.ownerStage, pdfReplace));

			}
		}
	}

	private void pdfReplaceRemove() {

		if (this.pdfReplaceTableView.getSelectionModel().getSelectedIndex() > -1) {

			PDFReplace pdfReplace = this.pdfReplaceTableView.getSelectionModel().getSelectedItem();

			String content = this.application.getSettings().getLanguage().getString("conf.pdfreplace.confirmremove");
			if (this.application.getAlertBuilder2().confirm(this.ownerStage, content)) {

				String waitMessage = this.application.getSettings().getLanguage()
						.getString("conf.pdfreplace.removewait");

				TaskManager.run(this.getApplication().getAlertBuilder2(), this.ownerStage, waitMessage,
						new ConfigPDFReplaceRemoveTask(this, this.application.getAlertBuilder2(),
								this.application.getSettings(), this.ownerStage, pdfReplace));

			}

		} else {
			String content = this.application.getSettings().getLanguage().getString("conf.pdfreplace.errornoselected");
			this.application.getAlertBuilder2().error(this.ownerStage, content);
		}
	}

	private void pdfTest() {

		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Resource File");
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF", "*.pdf"));
		File file = fileChooser.showOpenDialog(this.ownerStage);
		if (file != null) {

			try {
				this.pdfReaderTestTextArea.clear();

				AutoDetectParser parser = new AutoDetectParser();
				BodyContentHandler handler = new BodyContentHandler(-1);

				Metadata tikaMetadata = new Metadata();
				InputStream input;
				input = TikaInputStream.get(file, tikaMetadata);
				parser.parse(input, handler, tikaMetadata, new ParseContext());

				String text = handler.toString();

				for (PDFReplace pdfReplace : pdfReplaceList) {

					switch (EnumPDFReplaceType.valueByID(pdfReplace.getSpInf1())) {
					case REGEX:
						text = text.replaceAll(pdfReplace.getSpInf2(), pdfReplace.getSpInf3());
						break;
					case TEXT:
						text = text.replace(pdfReplace.getSpInf2(), pdfReplace.getSpInf3());
						break;
					}
				}
				
				text = text.trim();

//				System.out.println("=====");
//				checkText(text);
//				System.out.println("=====");

				this.pdfReaderTestTextArea.setText(text);

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (TikaException e) {
				e.printStackTrace();
			}
		}
	}

	private void checkText(String text) {

		String regExDest = "^PER GLI ANZIANI$|^PER I COORDINATORI DEI CORPI DEGLI ANZIANI$|^PER I SORVEGLIANTI DEL SERVIZIO$|^PER LE CONGREGAZIONI$";
		String regExTitolo = "^\\d+\\.\\s*.+$";
		boolean destinatario = false;
		boolean titolo = false;
		boolean titoloComeTesto = false;

		String[] strings = text.split("\n");
		for (int i = 0; i < strings.length; i++) {

			String currText = strings[i];
			if (currText.isEmpty())
				continue;

			if (!destinatario) {
				if (currText.matches(regExDest)) {
					destinatario = true;
					System.out.println("DESTINATARIO: " + currText);
				} else
					System.out.println("TESTO INIZIALE: " + currText);
			} else {

				if (!titolo) {

					if (currText.matches(regExTitolo)) {

						titolo = true;
						System.out.println("TITOLO: " + currText);
						i--;
						titoloComeTesto = true;

					}

				} else {

					if (titoloComeTesto) {

						System.out.println("TESTO: " + currText);
						titoloComeTesto = false;

					} else {

						if (currText.matches(regExDest)) {
							destinatario = false;
							titolo = false;
							i--;
							continue;
						}

						if (currText.matches(regExTitolo)) {
							titolo = false;
							i--;
							continue;
						}

						System.out.println("TESTO: " + currText);
					}
				}
			}
		}
	}

	private void songsDownload() {

		String source = songsSourceTextField.getText();
		if (!source.isEmpty()) {

			try {
				Document doc = Jsoup.connect(source).get();
				if (doc != null) {

					ObservableList<Song> newSongList = checkSongListByDoc(doc);
					if (!newSongList.isEmpty()) {

						if (!songListEquals(newSongList)) {

							String header = this.application.getSettings().getLanguage()
									.getString("conf.songs.confirmheader");
							String content = this.application.getSettings().getLanguage()
									.getString("conf.songs.confirm");

							if (this.application.getAlertBuilder2().confirm(this.ownerStage, header, content)) {

								String waitMessage = this.application.getSettings().getLanguage()
										.getString("conf.songs.savewait");

								TaskManager.run(this.getApplication().getAlertBuilder2(), this.ownerStage, waitMessage,
										new ConfigSongsSaveTask(this, this.application.getAlertBuilder2(),
												this.application.getSettings(), this.ownerStage, newSongList));

							}

						} else {

							String content = this.application.getSettings().getLanguage()
									.getString("conf.songs.equals");
							this.application.getAlertBuilder2().information(this.ownerStage, content);
						}
					}

				} else {
					String content = this.application.getSettings().getLanguage()
							.getString("conf.songs.nosourceresult");
					this.application.getAlertBuilder2().error(this.ownerStage, content);
				}
			} catch (Exception e) {
				String content = e.getMessage();
				this.application.getAlertBuilder2().error(this.ownerStage, content);
			}

		} else {
			String content = this.application.getSettings().getLanguage().getString("conf.songs.nosource");
			this.application.getAlertBuilder2().error(this.ownerStage, content);
		}

	}

	public void updateSongList() {

		String waitMessage = this.application.getSettings().getLanguage().getString("conf.songs.loadwait");

		TaskManager.run(this.application.getAlertBuilder2(), this.ownerStage, waitMessage, new ConfigSongsLoadTask(
				this.application.getAlertBuilder2(), this.application.getSettings(), this.ownerStage, this));

	}

	public void updatePDFReplaceList() {

		String waitMessage = this.application.getSettings().getLanguage().getString("conf.pdfreplace.loadwait");

		TaskManager.run(this.application.getAlertBuilder2(), this.ownerStage, waitMessage, new ConfigPDFReplaceLoadTask(
				this.application.getAlertBuilder2(), this.application.getSettings(), this.ownerStage, this));

	}

	public void updateSongList(ObservableList<Song> newSongList) {

		this.songList.clear();
		this.songList.addAll(newSongList);
		Platform.runLater(() -> this.songsTableView.refresh());
	}

	public void updatePDFReplaceList(ObservableList<PDFReplace> newList) {

		this.pdfReplaceList.clear();
		this.pdfReplaceList.addAll(newList);
		Platform.runLater(() -> this.pdfReplaceTableView.refresh());
	}

	private boolean songListEquals(ObservableList<Song> newSongList) {

		if (this.songList.size() != newSongList.size())
			return false;

		return this.songList.equals(newSongList);
	}

	private ObservableList<Song> checkSongListByDoc(Document doc) {

		// I doublequote HTML non vengono riconosciuti
		char dlquoteStart = (char) 8220;
		char dlquoteEnd = (char) 8221;

		Pattern numberPattern = Pattern.compile("\\d+");

		ObservableList<Song> newList = FXCollections.observableArrayList();

		Elements elemList = doc.getElementsByAttribute("data-classification");
		for (Element e : elemList) {

			Elements cardLine1List = e.getElementsByClass("cardLine1");

			Elements cardLine2List = e.getElementsByClass("cardLine2");
			if (cardLine1List.size() == 1 && cardLine2List.size() == 1) {

				int number = 0;
				String numberText = cardLine1List.get(0).text();
				Matcher numberMatcher = numberPattern.matcher(numberText);
				if (numberMatcher.find())
					number = Integer.valueOf(numberMatcher.group());

				String title = cardLine2List.get(0).text();
				title = title.replace(dlquoteStart, '"');
				title = title.replace(dlquoteEnd, '"');

				if (number > 0 && !title.isEmpty())
					newList.add(new Song(number, title));
			}

			newList.sort((s1, s2) -> s1.getNumber().compareTo(s2.getNumber()));
		}

		return newList;
	}

	private void showHelp() {

		String header = this.application.getSettings().getLanguage().getString("conf.dialog.places.pattern.help");
		String content = this.application.getSettings().getLanguage()
				.getStringWithNewLine("conf.dialog.places.pattern.info");

		this.application.getAlertBuilder2().information(this.ownerStage, header, content);
	}

	private void save() {

		if (checkFields()) {

			String placesPattern = this.placesPatternTextField.getText();
			String placesPatternEncrypted = Crypt.encrypt(placesPattern,
					this.application.getSettings().getDatabaseSecretKey());

			String publicTalkMin = this.publicTalkMinTextField.getText();
			String publicTalkMinEncrypted = Crypt.encrypt(publicTalkMin,
					this.application.getSettings().getDatabaseSecretKey());

			String watchtowerMin = this.watchtowerMinTextField.getText();
			String watchtowerMinEncrypted = Crypt.encrypt(watchtowerMin,
					this.application.getSettings().getDatabaseSecretKey());

			String overseerTalk1Min = this.overseerTalk1MinTextField.getText();
			String overseerTalk1MinEncrypted = Crypt.encrypt(overseerTalk1Min,
					this.application.getSettings().getDatabaseSecretKey());

			String overseerTalk2Min = this.overseerTalk2MinTextField.getText();
			String overseerTalk2MinEncrypted = Crypt.encrypt(overseerTalk2Min,
					this.application.getSettings().getDatabaseSecretKey());

			String overseerTalk3Min = this.overseerTalk3MinTextField.getText();
			String overseerTalk3MinEncrypted = Crypt.encrypt(overseerTalk3Min,
					this.application.getSettings().getDatabaseSecretKey());

			String overseerVisitCounter = this.overseerVisitCounterTextField.getText();
			String overseerVisitCounterEncrypted = Crypt.encrypt(overseerVisitCounter,
					this.application.getSettings().getDatabaseSecretKey());

			String memorialTalkMin = this.memorialTalkMinTextField.getText();
			String memorialTalkMinEncrypted = Crypt.encrypt(memorialTalkMin,
					this.application.getSettings().getDatabaseSecretKey());

			String audio1 = this.audioPos1NameTextField.getText();
			String audio2 = this.audioPos2NameTextField.getText();
			String audio3 = this.audioPos3NameTextField.getText();

			String usciere1 = this.usciereZone1NameTextField.getText();
			String usciere2 = this.usciereZone2NameTextField.getText();
			String usciere3 = this.usciereZone3NameTextField.getText();

			String usciere1equals = this.usciereZone1WeekendCheckBox.isSelected() ? "1" : "0";
			usciere1equals = Crypt.encrypt(usciere1equals, this.application.getSettings().getDatabaseSecretKey());
			String usciere2equals = this.usciereZone2WeekendCheckBox.isSelected() ? "1" : "0";
			usciere2equals = Crypt.encrypt(usciere2equals, this.application.getSettings().getDatabaseSecretKey());
			String usciere3equals = this.usciereZone3WeekendCheckBox.isSelected() ? "1" : "0";
			usciere3equals = Crypt.encrypt(usciere3equals, this.application.getSettings().getDatabaseSecretKey());

			String songsMin = this.songsMinTextField.getText();
			String songsMinEncrypted = Crypt.encrypt(songsMin, this.application.getSettings().getDatabaseSecretKey());

			String songsLoad = this.songsLoadCheckBox.isSelected() ? "1" : "0";
			songsLoad = Crypt.encrypt(songsLoad, this.application.getSettings().getDatabaseSecretKey());

			// ------------------------------------------

			String waitMessage = this.application.getSettings().getLanguage().getString("conf.save.wait");

			TaskManager.run(this.getApplication().getAlertBuilder2(), this.ownerStage, waitMessage,
					new ConfigSaveTask(this.application.getAlertBuilder2(), this.application.getSettings(),
							this.ownerStage, placesPatternEncrypted, publicTalkMinEncrypted, watchtowerMinEncrypted,
							overseerTalk1MinEncrypted, overseerTalk2MinEncrypted, overseerTalk3MinEncrypted,
							overseerVisitCounterEncrypted, memorialTalkMinEncrypted, audio1, audio2, audio3, usciere1,
							usciere2, usciere3, usciere1equals, usciere2equals, usciere3equals, songsMinEncrypted,
							songsLoad));
		}
	}

	private boolean checkFields() {

		// Al momento non c'e' nessuna condizione da soddisfare

		boolean status = true;

		return status;
	}

	public Button getSaveButton() {
		return saveButton;
	}

	public void setSaveButton(Button saveButton) {
		this.saveButton = saveButton;
	}

	public TabPane getTabPane() {
		return tabPane;
	}

	public void setTabPane(TabPane tabPane) {
		this.tabPane = tabPane;
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

	public HashMap<String, String> getConfigs() {
		return configs;
	}

	public void setConfigs(HashMap<String, String> configs) {
		this.configs = configs;
	}

}
