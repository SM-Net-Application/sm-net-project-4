package com.sm.net.sp.view.home.user.menu.audio;

import java.io.IOException;
import java.util.HashMap;

import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.model.ChristiansPart;
import com.sm.net.sp.model.Member;
import com.sm.net.sp.model.Privileges;
import com.sm.net.sp.model.UpdateDataAdapter;
import com.sm.net.sp.model.Week;
import com.sm.net.sp.model.WeekAudio;
import com.sm.net.sp.settings.Settings;
import com.sm.net.sp.view.SupportPlannerView;
import com.sm.net.sp.view.history.History;
import com.sm.net.sp.view.history.UpgradeableComboBoxSelection;
import com.sm.net.sp.view.historyaudio.HistoryAudio;
import com.sm.net.sp.view.home.user.menu.audio.task.WeekAudioSaveTask;
import com.smnet.core.task.TaskManager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AudioEditor extends UpdateDataAdapter implements UpgradeableComboBoxSelection {

	@FXML
	private Button saveButton;

	@FXML
	private Label midweekLabel;
	@FXML
	private Label weekendLabel;

	@FXML
	private Label pos1MidweekLabel;
	@FXML
	private Label pos2MidweekLabel;
	@FXML
	private Label pos3MidweekLabel;

	@FXML
	private ComboBox<Member> pos1MidweekComboBox;
	@FXML
	private ComboBox<Member> pos2MidweekComboBox;
	@FXML
	private ComboBox<Member> pos3MidweekComboBox;

	@FXML
	private Label mic1MidweekLabel;
	@FXML
	private Label mic2MidweekLabel;
	@FXML
	private Label mic3MidweekLabel;

	@FXML
	private ComboBox<Member> mic1MidweekComboBox;
	@FXML
	private ComboBox<Member> mic2MidweekComboBox;
	@FXML
	private ComboBox<Member> mic3MidweekComboBox;

	@FXML
	private Label pos1WeekendLabel;
	@FXML
	private Label pos2WeekendLabel;
	@FXML
	private Label pos3WeekendLabel;

	@FXML
	private ComboBox<Member> pos1WeekendComboBox;
	@FXML
	private ComboBox<Member> pos2WeekendComboBox;
	@FXML
	private ComboBox<Member> pos3WeekendComboBox;

	@FXML
	private Label mic1WeekendLabel;
	@FXML
	private Label mic2WeekendLabel;
	@FXML
	private Label mic3WeekendLabel;

	@FXML
	private ComboBox<Member> mic1WeekendComboBox;
	@FXML
	private ComboBox<Member> mic2WeekendComboBox;
	@FXML
	private ComboBox<Member> mic3WeekendComboBox;

	private Settings settings;
	private Language language;
	private Stage ownerStage;

	private ObservableList<Member> membersList;

	private ObservableList<Member> pos1MidweekList;
	private ObservableList<Member> pos2MidweekList;
	private ObservableList<Member> pos3MidweekList;
	private ObservableList<Member> micMidweekList;

	private ObservableList<Member> pos1WeekendList;
	private ObservableList<Member> pos2WeekendList;
	private ObservableList<Member> pos3WeekendList;
	private ObservableList<Member> micWeekendList;

	private Audio ownerCtrl;
	private WeekAudio selectedWeek;

	private TabPane ownerTabPane;
	private Tab thisTab;

	private ObservableList<WeekAudio> calendar;
	private HashMap<String, String> configs;

	private SupportPlannerView application;
	private ObservableList<Week> databaseWeeks;

	@FXML
	private void initialize() {
		styleClasses();
	}

	private void styleClasses() {

		this.midweekLabel.getStyleClass().add("label_002");
		this.weekendLabel.getStyleClass().add("label_002");

		this.pos1MidweekLabel.getStyleClass().add("label_set_001");
		this.pos2MidweekLabel.getStyleClass().add("label_set_001");
		this.pos3MidweekLabel.getStyleClass().add("label_set_001");
		this.mic1MidweekLabel.getStyleClass().add("label_set_001");
		this.mic2MidweekLabel.getStyleClass().add("label_set_001");
		this.mic3MidweekLabel.getStyleClass().add("label_set_001");

		this.pos1MidweekComboBox.getStyleClass().add("combo_box_001");
		this.pos2MidweekComboBox.getStyleClass().add("combo_box_001");
		this.pos3MidweekComboBox.getStyleClass().add("combo_box_001");
		this.mic1MidweekComboBox.getStyleClass().add("combo_box_001");
		this.mic2MidweekComboBox.getStyleClass().add("combo_box_001");
		this.mic3MidweekComboBox.getStyleClass().add("combo_box_001");

		this.pos1WeekendLabel.getStyleClass().add("label_set_001");
		this.pos2WeekendLabel.getStyleClass().add("label_set_001");
		this.pos3WeekendLabel.getStyleClass().add("label_set_001");
		this.mic1WeekendLabel.getStyleClass().add("label_set_001");
		this.mic2WeekendLabel.getStyleClass().add("label_set_001");
		this.mic3WeekendLabel.getStyleClass().add("label_set_001");

		this.pos1WeekendComboBox.getStyleClass().add("combo_box_001");
		this.pos2WeekendComboBox.getStyleClass().add("combo_box_001");
		this.pos3WeekendComboBox.getStyleClass().add("combo_box_001");
		this.mic1WeekendComboBox.getStyleClass().add("combo_box_001");
		this.mic2WeekendComboBox.getStyleClass().add("combo_box_001");
		this.mic3WeekendComboBox.getStyleClass().add("combo_box_001");

		this.saveButton.getStyleClass().add("button_image_001");
	}

	private void viewUpdate() {

		this.language = settings.getLanguage();

		this.midweekLabel.setText(this.language.getString("audioeditor.midweek"));
		this.weekendLabel.setText(this.language.getString("audioeditor.weekend"));

		String audioFormat = this.language.getString("audioeditor.pos");
		String noconfig = this.language.getString("audioeditor.noconfig");
		String pos1 = this.configs.get("inf9");
		String pos2 = this.configs.get("inf10");
		String pos3 = this.configs.get("inf11");

		if (pos1 == null || pos1.isEmpty()) {
			this.pos1MidweekLabel.setText(String.format(audioFormat, noconfig));
			this.pos1WeekendLabel.setText(String.format(audioFormat, noconfig));
		} else {
			this.pos1MidweekLabel.setText(String.format(audioFormat, pos1));
			this.pos1WeekendLabel.setText(String.format(audioFormat, pos1));
		}

		if (pos2 == null || pos2.isEmpty()) {
			this.pos2MidweekLabel.setText(String.format(audioFormat, noconfig));
			this.pos2WeekendLabel.setText(String.format(audioFormat, noconfig));
		} else {
			this.pos2MidweekLabel.setText(String.format(audioFormat, pos2));
			this.pos2WeekendLabel.setText(String.format(audioFormat, pos2));
		}

		if (pos3 == null || pos3.isEmpty()) {
			this.pos3MidweekLabel.setText(String.format(audioFormat, noconfig));
			this.pos3WeekendLabel.setText(String.format(audioFormat, noconfig));
		} else {
			this.pos3MidweekLabel.setText(String.format(audioFormat, pos3));
			this.pos3WeekendLabel.setText(String.format(audioFormat, pos3));
		}

		this.mic1MidweekLabel.setText(this.language.getString("audioeditor.mic1"));
		this.mic2MidweekLabel.setText(this.language.getString("audioeditor.mic2"));
		this.mic3MidweekLabel.setText(this.language.getString("audioeditor.mic3"));

		this.mic1WeekendLabel.setText(this.language.getString("audioeditor.mic1"));
		this.mic2WeekendLabel.setText(this.language.getString("audioeditor.mic2"));
		this.mic3WeekendLabel.setText(this.language.getString("audioeditor.mic3"));

		Tooltip saveButtonTooltip = new Tooltip(this.language.getString("audioeditor.tooltip.button.save"));
		saveButtonTooltip.getStyleClass().add("tooltip_001");
		this.saveButton.setTooltip(saveButtonTooltip);
		this.saveButton.setText("");
		this.saveButton.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.SAVE));
	}

	public void objectInitialize() {
		viewUpdate();
		contextMenu();
		initData();
		listeners();
	}

	private void contextMenu() {

		this.pos1MidweekComboBox.setContextMenu(createPrivilegeRegisterContextMenu(Privileges.MIDWEEK_AUDIO_POS1));
		this.pos1MidweekComboBox.addEventFilter(MouseEvent.MOUSE_RELEASED, event -> disableMouseSecondary(event));
	}

	private void disableMouseSecondary(MouseEvent event) {
		if (event.getButton() == MouseButton.SECONDARY)
			event.consume();
	}

	private ContextMenu createPrivilegeRegisterContextMenu(Privileges privilege) {

		String privilegeTranslatedName = privilege.getTranslatedName(this.language);

		switch (privilege) {
		case MIDWEEK_AUDIO_POS1:
		case WEEKEND_AUDIO_POS1:
			privilegeTranslatedName = String.format(privilegeTranslatedName, this.configs.get("inf9"));
			break;
		case MIDWEEK_AUDIO_POS2:
		case WEEKEND_AUDIO_POS2:
			privilegeTranslatedName = String.format(privilegeTranslatedName, this.configs.get("inf10"));
			break;
		case MIDWEEK_AUDIO_POS3:
		case WEEKEND_AUDIO_POS3:
			privilegeTranslatedName = String.format(privilegeTranslatedName, this.configs.get("inf11"));
			break;
		default:
			break;
		}

		String menuItemText = String.format(language.getString("sp.meetings.history"), privilegeTranslatedName);

		String historyTitle = String.format(language.getString("sp.history.title"), menuItemText,
				this.selectedWeek.getFrom().toString());

		StackPane graphic = Meta.Resources.imageInStackPaneForMenu(Meta.Resources.SEARCH);

		MenuItem menuItem = new MenuItem(menuItemText, graphic);
		menuItem.getStyleClass().add("menu_item_001");
		menuItem.setOnAction(event -> openHistory(privilege, historyTitle));

		ContextMenu contextMenu = new ContextMenu(menuItem);

		return contextMenu;
	}

	private void openHistory(Privileges privilege, String title) {

		try {

			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(Meta.Views.HISTORYAUDIO);
			AnchorPane layout = (AnchorPane) fxmlLoader.load();

			Scene scene = new Scene(layout);
			scene.getStylesheets().add(Meta.Themes.SUPPORTPLANNER_THEME);

			Stage stage = new Stage();
			stage.setScene(scene);
			stage.initModality(Modality.WINDOW_MODAL);
			stage.initOwner(this.ownerStage);
			stage.setTitle(title);
			stage.getIcons().add(Meta.Resources.imageForWindowsIcon(Meta.Resources.SEARCH));

			stage.setMinWidth(1050);
			stage.setMinHeight(500);

			HistoryAudio ctrl = (HistoryAudio) fxmlLoader.getController();
			ctrl.setLayout(layout);
			ctrl.setPrivilege(privilege);
			ctrl.setMembers(this.membersList);
			ctrl.setLanguage(this.language);
			ctrl.setDatabaseWeeks(this.databaseWeeks);
			ctrl.setSelectedWeek(this.selectedWeek);
			ctrl.setEditorWeek(WeekAudio.buildEditorWeek(this));
			ctrl.setEditor(this);
			ctrl.setThisStage(stage);
			ctrl.setApplication(this.application);

			ctrl.objectInitialize();

			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateSelectedComboBox(Privileges privilege, int memberID) {
	}

	@Override
	public void updateSelectedChristianPart(ChristiansPart christiansPart, int memberID) {
	}

	private void initData() {

		initFields();
		loadSelectedWeek();
	}

	private void initFields() {

		initMembers();
	}

	private void initMembers() {

		this.pos1MidweekList = FXCollections.observableArrayList();
		this.pos2MidweekList = FXCollections.observableArrayList();
		this.pos3MidweekList = FXCollections.observableArrayList();
		this.micMidweekList = FXCollections.observableArrayList();

		this.pos1WeekendList = FXCollections.observableArrayList();
		this.pos2WeekendList = FXCollections.observableArrayList();
		this.pos3WeekendList = FXCollections.observableArrayList();
		this.micWeekendList = FXCollections.observableArrayList();

		for (Member m : this.membersList) {
			if (m.getSpInf22() == 1)
				this.pos1MidweekList.add(m);
			if (m.getSpInf23() == 1)
				this.pos1WeekendList.add(m);
			if (m.getSpInf24() == 1)
				this.pos2MidweekList.add(m);
			if (m.getSpInf25() == 1)
				this.pos2WeekendList.add(m);
			if (m.getSpInf54() == 1)
				this.pos3MidweekList.add(m);
			if (m.getSpInf55() == 1)
				this.pos3WeekendList.add(m);
			if (m.getSpInf20() == 1)
				this.micMidweekList.add(m);
			if (m.getSpInf21() == 1)
				this.micWeekendList.add(m);
		}

		this.pos1MidweekList.add(0, Member.emptyMember(this.language));
		this.pos2MidweekList.add(0, Member.emptyMember(this.language));
		this.pos3MidweekList.add(0, Member.emptyMember(this.language));
		this.micMidweekList.add(0, Member.emptyMember(this.language));
		this.pos1WeekendList.add(0, Member.emptyMember(this.language));
		this.pos2WeekendList.add(0, Member.emptyMember(this.language));
		this.pos3WeekendList.add(0, Member.emptyMember(this.language));
		this.micWeekendList.add(0, Member.emptyMember(this.language));

		this.pos1MidweekComboBox.setItems(this.pos1MidweekList);
		this.pos2MidweekComboBox.setItems(this.pos2MidweekList);
		this.pos3MidweekComboBox.setItems(this.pos3MidweekList);
		this.mic1MidweekComboBox.setItems(this.micMidweekList);
		this.mic2MidweekComboBox.setItems(this.micMidweekList);
		this.mic3MidweekComboBox.setItems(this.micMidweekList);

		this.pos1WeekendComboBox.setItems(this.pos1WeekendList);
		this.pos2WeekendComboBox.setItems(this.pos2WeekendList);
		this.pos3WeekendComboBox.setItems(this.pos3WeekendList);
		this.mic1WeekendComboBox.setItems(this.micWeekendList);
		this.mic2WeekendComboBox.setItems(this.micWeekendList);
		this.mic3WeekendComboBox.setItems(this.micWeekendList);

		this.pos1MidweekComboBox.getSelectionModel().selectFirst();
		this.pos2MidweekComboBox.getSelectionModel().selectFirst();
		this.pos3MidweekComboBox.getSelectionModel().selectFirst();
		this.mic1MidweekComboBox.getSelectionModel().selectFirst();
		this.mic2MidweekComboBox.getSelectionModel().selectFirst();
		this.mic3MidweekComboBox.getSelectionModel().selectFirst();

		this.pos1WeekendComboBox.getSelectionModel().selectFirst();
		this.pos2WeekendComboBox.getSelectionModel().selectFirst();
		this.pos3WeekendComboBox.getSelectionModel().selectFirst();
		this.mic1WeekendComboBox.getSelectionModel().selectFirst();
		this.mic2WeekendComboBox.getSelectionModel().selectFirst();
		this.mic3WeekendComboBox.getSelectionModel().selectFirst();
	}

	private void loadSelectedWeek() {

		if (this.selectedWeek != null)
			if (this.selectedWeek.spAudioIDProperty() != null) {

				setComboBoxMember(this.pos1MidweekComboBox, this.selectedWeek.getSpInf2());
				setComboBoxMember(this.pos2MidweekComboBox, this.selectedWeek.getSpInf3());
				setComboBoxMember(this.pos3MidweekComboBox, this.selectedWeek.getSpInf4());

				setComboBoxMember(this.mic1MidweekComboBox, this.selectedWeek.getSpInf5());
				setComboBoxMember(this.mic2MidweekComboBox, this.selectedWeek.getSpInf6());
				setComboBoxMember(this.mic3MidweekComboBox, this.selectedWeek.getSpInf7());

				setComboBoxMember(this.pos1WeekendComboBox, this.selectedWeek.getSpInf8());
				setComboBoxMember(this.pos2WeekendComboBox, this.selectedWeek.getSpInf9());
				setComboBoxMember(this.pos3WeekendComboBox, this.selectedWeek.getSpInf10());

				setComboBoxMember(this.mic1WeekendComboBox, this.selectedWeek.getSpInf11());
				setComboBoxMember(this.mic2WeekendComboBox, this.selectedWeek.getSpInf12());
				setComboBoxMember(this.mic3WeekendComboBox, this.selectedWeek.getSpInf13());

			}
	}

	private void setComboBoxMember(ComboBox<Member> cb, int id) {

		int found = 0;
		for (int i = 0; i < cb.getItems().size(); i++) {
			Member m = cb.getItems().get(i);
			if (m.getSpMemberID() == id) {
				found = i;
				break;
			}
		}

		cb.getSelectionModel().select(found);
	}

	private void listeners() {

		this.saveButton.setOnAction(event -> save());
	}

	private void save() {

		if (checkFields()) {

			if (this.selectedWeek.spAudioIDProperty() != null) {

				// editWeek

				WeekAudio week = WeekAudio.newInstanceByView(this);
				week.setAudioID(this.selectedWeek.getAudioID());

				String waitMessage = this.language.getString("memorialeditor.wait.save");
				TaskManager.run(this.application.getAlertBuilder2(), this.ownerStage, waitMessage,
						new WeekAudioSaveTask(this.application.getAlertBuilder2(), this.settings, this.ownerStage, week,
								this.ownerCtrl, this.thisTab));

			} else {

				// newWeek

				WeekAudio week = WeekAudio.newInstanceByView(this);

				String waitMessage = this.language.getString("memorialeditor.wait.save");
				TaskManager.run(this.application.getAlertBuilder2(), this.ownerStage, waitMessage,
						new WeekAudioSaveTask(this.application.getAlertBuilder2(), this.settings, this.ownerStage, week,
								this.ownerCtrl, this.thisTab));
			}
		}
	}

	private boolean checkFields() {

		return true;
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

	public Audio getOwnerCtrl() {
		return ownerCtrl;
	}

	public void setOwnerCtrl(Audio ownerCtrl) {
		this.ownerCtrl = ownerCtrl;
	}

	public WeekAudio getSelectedWeek() {
		return selectedWeek;
	}

	public void setSelectedWeek(WeekAudio selectedWeek) {
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

	public ObservableList<WeekAudio> getCalendar() {
		return calendar;
	}

	public void setCalendar(ObservableList<WeekAudio> calendar) {
		this.calendar = calendar;
	}

	public SupportPlannerView getApplication() {
		return application;
	}

	public void setApplication(SupportPlannerView application) {
		this.application = application;
	}

	public Button getSaveButton() {
		return saveButton;
	}

	public void setSaveButton(Button saveButton) {
		this.saveButton = saveButton;
	}

	public ObservableList<Member> getMembersList() {
		return membersList;
	}

	public void setMembersList(ObservableList<Member> membersList) {
		this.membersList = membersList;
	}

	public HashMap<String, String> getConfigs() {
		return configs;
	}

	public void setConfigs(HashMap<String, String> configs) {
		this.configs = configs;
	}

	public Label getMidweekLabel() {
		return midweekLabel;
	}

	public Label getWeekendLabel() {
		return weekendLabel;
	}

	public Label getPos1MidweekLabel() {
		return pos1MidweekLabel;
	}

	public Label getPos2MidweekLabel() {
		return pos2MidweekLabel;
	}

	public Label getPos3MidweekLabel() {
		return pos3MidweekLabel;
	}

	public ComboBox<Member> getPos1MidweekComboBox() {
		return pos1MidweekComboBox;
	}

	public ComboBox<Member> getPos2MidweekComboBox() {
		return pos2MidweekComboBox;
	}

	public ComboBox<Member> getPos3MidweekComboBox() {
		return pos3MidweekComboBox;
	}

	public Label getMic1MidweekLabel() {
		return mic1MidweekLabel;
	}

	public Label getMic2MidweekLabel() {
		return mic2MidweekLabel;
	}

	public Label getMic3MidweekLabel() {
		return mic3MidweekLabel;
	}

	public ComboBox<Member> getMic1MidweekComboBox() {
		return mic1MidweekComboBox;
	}

	public ComboBox<Member> getMic2MidweekComboBox() {
		return mic2MidweekComboBox;
	}

	public ComboBox<Member> getMic3MidweekComboBox() {
		return mic3MidweekComboBox;
	}

	public Label getPos1WeekendLabel() {
		return pos1WeekendLabel;
	}

	public Label getPos2WeekendLabel() {
		return pos2WeekendLabel;
	}

	public Label getPos3WeekendLabel() {
		return pos3WeekendLabel;
	}

	public ComboBox<Member> getPos1WeekendComboBox() {
		return pos1WeekendComboBox;
	}

	public ComboBox<Member> getPos2WeekendComboBox() {
		return pos2WeekendComboBox;
	}

	public ComboBox<Member> getPos3WeekendComboBox() {
		return pos3WeekendComboBox;
	}

	public Label getMic1WeekendLabel() {
		return mic1WeekendLabel;
	}

	public Label getMic2WeekendLabel() {
		return mic2WeekendLabel;
	}

	public Label getMic3WeekendLabel() {
		return mic3WeekendLabel;
	}

	public ComboBox<Member> getMic1WeekendComboBox() {
		return mic1WeekendComboBox;
	}

	public ComboBox<Member> getMic2WeekendComboBox() {
		return mic2WeekendComboBox;
	}

	public ComboBox<Member> getMic3WeekendComboBox() {
		return mic3WeekendComboBox;
	}

	public ObservableList<Member> getPos1MidweekList() {
		return pos1MidweekList;
	}

	public ObservableList<Member> getPos2MidweekList() {
		return pos2MidweekList;
	}

	public ObservableList<Member> getPos3MidweekList() {
		return pos3MidweekList;
	}

	public ObservableList<Member> getMicMidweekList() {
		return micMidweekList;
	}

	public ObservableList<Member> getPos1WeekendList() {
		return pos1WeekendList;
	}

	public ObservableList<Member> getPos2WeekendList() {
		return pos2WeekendList;
	}

	public ObservableList<Member> getPos3WeekendList() {
		return pos3WeekendList;
	}

	public ObservableList<Member> getMicWeekendList() {
		return micWeekendList;
	}

	public void setMidweekLabel(Label midweekLabel) {
		this.midweekLabel = midweekLabel;
	}

	public void setWeekendLabel(Label weekendLabel) {
		this.weekendLabel = weekendLabel;
	}

	public void setPos1MidweekLabel(Label pos1MidweekLabel) {
		this.pos1MidweekLabel = pos1MidweekLabel;
	}

	public void setPos2MidweekLabel(Label pos2MidweekLabel) {
		this.pos2MidweekLabel = pos2MidweekLabel;
	}

	public void setPos3MidweekLabel(Label pos3MidweekLabel) {
		this.pos3MidweekLabel = pos3MidweekLabel;
	}

	public void setPos1MidweekComboBox(ComboBox<Member> pos1MidweekComboBox) {
		this.pos1MidweekComboBox = pos1MidweekComboBox;
	}

	public void setPos2MidweekComboBox(ComboBox<Member> pos2MidweekComboBox) {
		this.pos2MidweekComboBox = pos2MidweekComboBox;
	}

	public void setPos3MidweekComboBox(ComboBox<Member> pos3MidweekComboBox) {
		this.pos3MidweekComboBox = pos3MidweekComboBox;
	}

	public void setMic1MidweekLabel(Label mic1MidweekLabel) {
		this.mic1MidweekLabel = mic1MidweekLabel;
	}

	public void setMic2MidweekLabel(Label mic2MidweekLabel) {
		this.mic2MidweekLabel = mic2MidweekLabel;
	}

	public void setMic3MidweekLabel(Label mic3MidweekLabel) {
		this.mic3MidweekLabel = mic3MidweekLabel;
	}

	public void setMic1MidweekComboBox(ComboBox<Member> mic1MidweekComboBox) {
		this.mic1MidweekComboBox = mic1MidweekComboBox;
	}

	public void setMic2MidweekComboBox(ComboBox<Member> mic2MidweekComboBox) {
		this.mic2MidweekComboBox = mic2MidweekComboBox;
	}

	public void setMic3MidweekComboBox(ComboBox<Member> mic3MidweekComboBox) {
		this.mic3MidweekComboBox = mic3MidweekComboBox;
	}

	public void setPos1WeekendLabel(Label pos1WeekendLabel) {
		this.pos1WeekendLabel = pos1WeekendLabel;
	}

	public void setPos2WeekendLabel(Label pos2WeekendLabel) {
		this.pos2WeekendLabel = pos2WeekendLabel;
	}

	public void setPos3WeekendLabel(Label pos3WeekendLabel) {
		this.pos3WeekendLabel = pos3WeekendLabel;
	}

	public void setPos1WeekendComboBox(ComboBox<Member> pos1WeekendComboBox) {
		this.pos1WeekendComboBox = pos1WeekendComboBox;
	}

	public void setPos2WeekendComboBox(ComboBox<Member> pos2WeekendComboBox) {
		this.pos2WeekendComboBox = pos2WeekendComboBox;
	}

	public void setPos3WeekendComboBox(ComboBox<Member> pos3WeekendComboBox) {
		this.pos3WeekendComboBox = pos3WeekendComboBox;
	}

	public void setMic1WeekendLabel(Label mic1WeekendLabel) {
		this.mic1WeekendLabel = mic1WeekendLabel;
	}

	public void setMic2WeekendLabel(Label mic2WeekendLabel) {
		this.mic2WeekendLabel = mic2WeekendLabel;
	}

	public void setMic3WeekendLabel(Label mic3WeekendLabel) {
		this.mic3WeekendLabel = mic3WeekendLabel;
	}

	public void setMic1WeekendComboBox(ComboBox<Member> mic1WeekendComboBox) {
		this.mic1WeekendComboBox = mic1WeekendComboBox;
	}

	public void setMic2WeekendComboBox(ComboBox<Member> mic2WeekendComboBox) {
		this.mic2WeekendComboBox = mic2WeekendComboBox;
	}

	public void setMic3WeekendComboBox(ComboBox<Member> mic3WeekendComboBox) {
		this.mic3WeekendComboBox = mic3WeekendComboBox;
	}

	public void setPos1MidweekList(ObservableList<Member> pos1MidweekList) {
		this.pos1MidweekList = pos1MidweekList;
	}

	public void setPos2MidweekList(ObservableList<Member> pos2MidweekList) {
		this.pos2MidweekList = pos2MidweekList;
	}

	public void setPos3MidweekList(ObservableList<Member> pos3MidweekList) {
		this.pos3MidweekList = pos3MidweekList;
	}

	public void setMicMidweekList(ObservableList<Member> micMidweekList) {
		this.micMidweekList = micMidweekList;
	}

	public void setPos1WeekendList(ObservableList<Member> pos1WeekendList) {
		this.pos1WeekendList = pos1WeekendList;
	}

	public void setPos2WeekendList(ObservableList<Member> pos2WeekendList) {
		this.pos2WeekendList = pos2WeekendList;
	}

	public void setPos3WeekendList(ObservableList<Member> pos3WeekendList) {
		this.pos3WeekendList = pos3WeekendList;
	}

	public void setMicWeekendList(ObservableList<Member> micWeekendList) {
		this.micWeekendList = micWeekendList;
	}

	public ObservableList<Week> getDatabaseWeeks() {
		return databaseWeeks;
	}

	public void setDatabaseWeeks(ObservableList<Week> databaseWeeks) {
		this.databaseWeeks = databaseWeeks;
	}
}
