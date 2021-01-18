package com.sm.net.sp.view.home.user.menu.circuitoverseer;

import java.util.HashMap;

import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.actions.Actions;
import com.sm.net.sp.model.Song;
import com.sm.net.sp.model.UpdateDataAdapter;
import com.sm.net.sp.model.Week;
import com.sm.net.sp.model.WeekOverseer;
import com.sm.net.sp.settings.Settings;
import com.sm.net.util.Crypt;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;

public class UserMenuCircuitOverviewEditor extends UpdateDataAdapter {

	@FXML
	private Button saveWeekButton;

	@FXML
	private TabPane tabPane;
	@FXML
	private Tab generalTab;
	@FXML
	private Tab contactsTab;
	@FXML
	private Tab talksTab;

	@FXML
	private ScrollPane generalScrollPane;
	@FXML
	private ScrollPane contactsScrollPane;
	@FXML
	private ScrollPane talksScrollPane;

	@FXML
	private Label visitNumberLabel;
	@FXML
	private Label overseerLabel;
	@FXML
	private Label overseerNameLabel;
	@FXML
	private Label overseerShortNameLabel;
	@FXML
	private Label overseerSurnameLabel;
	@FXML
	private Label wifeLabel;
	@FXML
	private Label wifeNameLabel;
	@FXML
	private Label wifeShortNameLabel;
	@FXML
	private Label talk1Label;
	@FXML
	private Label talk1SongLabel;
	@FXML
	private Label talk1MinLabel;
	@FXML
	private Label talk1ThemeLabel;
	@FXML
	private Label talk2Label;
	@FXML
	private Label talk2SongLabel;
	@FXML
	private Label talk2MinLabel;
	@FXML
	private Label talk2ThemeLabel;
	@FXML
	private Label talk3Label;
	@FXML
	private Label talk3MinLabel;
	@FXML
	private Label talk3ThemeLabel;
	@FXML
	private Label phoneOverseerLabel;
	@FXML
	private Label mailOverseerLabel;
	@FXML
	private Label phoneWifeLabel;
	@FXML
	private Label mailWifeLabel;
	@FXML
	private Label overseerContactsLabel;
	@FXML
	private Label wifeContactsLabel;

	@FXML
	private TextField visitNumberTextField;
	@FXML
	private TextField overseerNameTextField;
	@FXML
	private TextField overseerShortNameTextField;
	@FXML
	private TextField overseerSurnameTextField;
	@FXML
	private TextField wifeNameTextField;
	@FXML
	private TextField wifeShortNameTextField;
	@FXML
	private TextField talk1SongTextField;
	@FXML
	private TextField talk1MinTextField;
	@FXML
	private TextField talk1ThemeTextField;
	@FXML
	private TextField talk2SongTextField;
	@FXML
	private TextField talk2MinTextField;
	@FXML
	private TextField talk2ThemeTextField;
	@FXML
	private TextField talk3MinTextField;
	@FXML
	private TextField talk3ThemeTextField;
	@FXML
	private TextField phoneOverseerTextField;
	@FXML
	private TextField mailOverseerTextField;
	@FXML
	private TextField phoneWifeTextField;
	@FXML
	private TextField mailWifeTextField;
	@FXML
	private CheckBox substituteCheckBox;

	@FXML
	private TextField song1TextField;
	@FXML
	private TextField song2TextField;

	private ObservableList<Song> songList;
	private boolean configSongTitleLoad;

	private Settings settings;
	private Language language;
	private Stage ownerStage;
	private UserMenuCircuitOverseer ownerCtrl;
	private WeekOverseer selectedWeek;
	private TabPane ownerTabPane;
	private Tab thisTab;

	private ObservableList<WeekOverseer> calendar;
	private HashMap<String, String> configs;

	@FXML
	private void initialize() {
		styleClasses();
	}

	private void styleClasses() {

		this.tabPane.getStyleClass().add("tab_pane_003");

		generalTab.getStyleClass().add("tab_001");
		contactsTab.getStyleClass().add("tab_001");
		talksTab.getStyleClass().add("tab_001");

		generalScrollPane.getStyleClass().add("scroll_pane_001");
		contactsScrollPane.getStyleClass().add("scroll_pane_001");
		talksScrollPane.getStyleClass().add("scroll_pane_001");

		overseerLabel.getStyleClass().add("label_002");
		wifeLabel.getStyleClass().add("label_002");
		talk1Label.getStyleClass().add("label_002");
		talk2Label.getStyleClass().add("label_002");
		talk3Label.getStyleClass().add("label_002");
		overseerContactsLabel.getStyleClass().add("label_002");
		wifeContactsLabel.getStyleClass().add("label_002");

		visitNumberLabel.getStyleClass().add("label_set_001");
		overseerNameLabel.getStyleClass().add("label_set_001");
		overseerShortNameLabel.getStyleClass().add("label_set_001");
		overseerSurnameLabel.getStyleClass().add("label_set_001");
		wifeNameLabel.getStyleClass().add("label_set_001");
		wifeShortNameLabel.getStyleClass().add("label_set_001");
		talk1SongLabel.getStyleClass().add("label_set_001");
		talk1MinLabel.getStyleClass().add("label_set_001");
		talk1ThemeLabel.getStyleClass().add("label_set_001");
		talk2SongLabel.getStyleClass().add("label_set_001");
		talk2MinLabel.getStyleClass().add("label_set_001");
		talk2ThemeLabel.getStyleClass().add("label_set_001");
		talk3MinLabel.getStyleClass().add("label_set_001");
		talk3ThemeLabel.getStyleClass().add("label_set_001");
		talk3ThemeLabel.getStyleClass().add("label_set_001");
		phoneOverseerLabel.getStyleClass().add("label_set_001");
		mailOverseerLabel.getStyleClass().add("label_set_001");
		phoneWifeLabel.getStyleClass().add("label_set_001");
		mailWifeLabel.getStyleClass().add("label_set_001");

		visitNumberTextField.getStyleClass().add("text_field_002");
		talk1SongTextField.getStyleClass().add("text_field_002");
		talk1MinTextField.getStyleClass().add("text_field_002");
		talk2SongTextField.getStyleClass().add("text_field_002");
		talk2MinTextField.getStyleClass().add("text_field_002");
		talk3MinTextField.getStyleClass().add("text_field_002");

		overseerNameTextField.getStyleClass().add("text_field_001");
		overseerShortNameTextField.getStyleClass().add("text_field_001");
		overseerSurnameTextField.getStyleClass().add("text_field_001");
		wifeNameTextField.getStyleClass().add("text_field_001");
		wifeShortNameTextField.getStyleClass().add("text_field_001");
		talk1ThemeTextField.getStyleClass().add("text_field_001");
		talk2ThemeTextField.getStyleClass().add("text_field_001");
		talk3ThemeTextField.getStyleClass().add("text_field_001");
		phoneOverseerTextField.getStyleClass().add("text_field_001");
		mailOverseerTextField.getStyleClass().add("text_field_001");
		phoneWifeTextField.getStyleClass().add("text_field_001");
		mailWifeTextField.getStyleClass().add("text_field_001");

		substituteCheckBox.getStyleClass().add("check_box_001");

		saveWeekButton.getStyleClass().add("button_image_001");

		this.song1TextField.getStyleClass().add("text_field_001");
		this.song2TextField.getStyleClass().add("text_field_001");
	}

	private void viewUpdate() {

		this.language = settings.getLanguage();

		this.tabPane.setTabMinHeight(75);
		this.tabPane.setTabMaxHeight(75);

		Tooltip generalTabTooltip = new Tooltip(this.language.getString("TEXT0016"));
		generalTabTooltip.getStyleClass().add("tooltip_001");
		this.generalTab.setTooltip(generalTabTooltip);
		this.generalTab.setText("");
		this.generalTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.MEMBER_PERSONAL_INFO));

		Tooltip contactsTabTooltip = new Tooltip(this.language.getString("TEXT0106"));
		contactsTabTooltip.getStyleClass().add("tooltip_001");
		this.contactsTab.setTooltip(contactsTabTooltip);
		this.contactsTab.setText("");
		this.contactsTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.CONTACTS));

		Tooltip talksTabTooltip = new Tooltip(this.language.getString("TEXT0140"));
		talksTabTooltip.getStyleClass().add("tooltip_001");
		this.talksTab.setTooltip(talksTabTooltip);
		this.talksTab.setText("");
		this.talksTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.TALKS));

		visitNumberLabel.setText(language.getString("TEXT0139"));
		overseerLabel.setText(language.getString("TEXT0037"));
		wifeLabel.setText(language.getString("TEXT0133"));
		talk1Label.setText(language.getString("TEXT0141"));
		talk2Label.setText(language.getString("TEXT0142"));
		talk3Label.setText(language.getString("TEXT0143"));
		overseerNameLabel.setText(language.getString("TEXT0014"));
		overseerShortNameLabel.setText(language.getString("TEXT0018"));
		overseerSurnameLabel.setText(language.getString("TEXT0013"));
		wifeNameLabel.setText(language.getString("TEXT0014"));
		wifeShortNameLabel.setText(language.getString("TEXT0018"));
		talk1SongLabel.setText(language.getString("TEXT0144"));
		talk1MinLabel.setText(language.getString("TEXT0093"));
		talk1ThemeLabel.setText(language.getString("TEXT0094"));
		talk2SongLabel.setText(language.getString("TEXT0144"));
		talk2MinLabel.setText(language.getString("TEXT0093"));
		talk2ThemeLabel.setText(language.getString("TEXT0094"));
		talk3MinLabel.setText(language.getString("TEXT0093"));
		talk3ThemeLabel.setText(language.getString("TEXT0094"));

		phoneOverseerLabel.setText(language.getString("sp.overseer.phone"));
		mailOverseerLabel.setText(language.getString("sp.overseer.mail"));
		phoneWifeLabel.setText(language.getString("sp.overseer.phone"));
		mailWifeLabel.setText(language.getString("sp.overseer.mail"));
		overseerContactsLabel.setText(language.getString("sp.overseer.overseer"));
		wifeContactsLabel.setText(language.getString("sp.overseer.wife"));

		substituteCheckBox.setText(language.getString("sp.overseer.substitute"));

		Tooltip saveTooltip = new Tooltip(language.getString("overseer.tooltip.save"));
		saveTooltip.getStyleClass().add("tooltip_001");
		this.saveWeekButton.setTooltip(saveTooltip);
		this.saveWeekButton.setText(null);
		this.saveWeekButton.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.SAVE));
	}

	public void objectInitialize() {
		viewUpdate();
		initData();
		listeners();
	}

	private void initData() {
		loadSelectedWeek();
	}

	private void loadSelectedWeek() {

		if (this.selectedWeek != null)
			if (this.selectedWeek.spWeekOvIDProperty() != null) {

				this.visitNumberTextField.setText(this.selectedWeek.getVisitNumber());
				this.overseerNameTextField.setText(this.selectedWeek.getSpInf3());
				this.overseerShortNameTextField.setText(this.selectedWeek.getSpInf4());
				this.overseerSurnameTextField.setText(this.selectedWeek.getSpInf5());
				this.wifeNameTextField.setText(this.selectedWeek.getSpInf6());
				this.wifeShortNameTextField.setText(this.selectedWeek.getSpInf7());
				this.talk1SongTextField.setText(this.selectedWeek.getSpInf8());
				this.talk1MinTextField.setText(this.selectedWeek.getSpInf9());
				this.talk1ThemeTextField.setText(this.selectedWeek.getSpInf10());
				this.talk2SongTextField.setText(this.selectedWeek.getSpInf11());
				this.talk2MinTextField.setText(this.selectedWeek.getSpInf12());
				this.talk2ThemeTextField.setText(this.selectedWeek.getSpInf13());
				this.talk3MinTextField.setText(this.selectedWeek.getSpInf14());
				this.talk3ThemeTextField.setText(this.selectedWeek.getSpInf15());

				this.phoneOverseerTextField.setText(this.selectedWeek.getSpInf16());
				this.mailOverseerTextField.setText(this.selectedWeek.getSpInf17());
				this.phoneWifeTextField.setText(this.selectedWeek.getSpInf18());
				this.mailWifeTextField.setText(this.selectedWeek.getSpInf19());

				this.substituteCheckBox.setSelected((this.selectedWeek.getSpInf20() == 1));

				this.song1TextField.setText(this.selectedWeek.getSpInf21());
				this.song2TextField.setText(this.selectedWeek.getSpInf22());

			} else {

				// TODO : Aggiungere le altre informazioni

				int visitNumber = 1;
				String overseerName = "";
				String overseerShortName = "";
				String overseerSurname = "";
				String wifeName = "";
				String wifeShortName = "";
				String phoneOverseer = "";
				String mailOverseer = "";
				String phoneWife = "";
				String mailWife = "";

				for (WeekOverseer wo : this.calendar) {

					if (wo.spWeekOvIDProperty() != null) {

						visitNumber = wo.getSpInf2();
						if (wo.getSpInf20() == 0) {

							overseerName = wo.getSpInf3();
							overseerShortName = wo.getSpInf4();
							overseerSurname = wo.getSpInf5();
							wifeName = wo.getSpInf6();
							wifeShortName = wo.getSpInf7();

							phoneOverseer = wo.getSpInf16();
							mailOverseer = wo.getSpInf17();
							phoneWife = wo.getSpInf18();
							mailWife = wo.getSpInf19();
						}
					}
				}

				String visitCounter = this.configs.get("inf7");
				String visitCounterDecrypted = Crypt.decrypt(visitCounter, this.settings.getDatabaseSecretKey());
				int visitMax = 6;
				if (!visitCounterDecrypted.isEmpty())
					visitMax = Integer.valueOf(visitCounterDecrypted);

				visitNumber = (visitNumber == visitMax) ? 1 : (visitNumber + 1);

				this.visitNumberTextField.setText(String.valueOf(visitNumber));
				this.overseerNameTextField.setText(overseerName);
				this.overseerShortNameTextField.setText(overseerShortName);
				this.overseerSurnameTextField.setText(overseerSurname);
				this.wifeNameTextField.setText(wifeName);
				this.wifeShortNameTextField.setText(wifeShortName);

				this.phoneOverseerTextField.setText(phoneOverseer);
				this.mailOverseerTextField.setText(mailOverseer);
				this.phoneWifeTextField.setText(phoneWife);
				this.mailWifeTextField.setText(mailWife);

				String talk1Min = this.configs.get("inf4");
				String talk1MinDecrypted = Crypt.decrypt(talk1Min, this.settings.getDatabaseSecretKey());
				this.talk1MinTextField.setText(talk1MinDecrypted);

				String talk2Min = this.configs.get("inf5");
				String talk2MinDecrypted = Crypt.decrypt(talk2Min, this.settings.getDatabaseSecretKey());
				this.talk2MinTextField.setText(talk2MinDecrypted);

				String talk3Min = this.configs.get("inf6");
				String talk3MinDecrypted = Crypt.decrypt(talk3Min, this.settings.getDatabaseSecretKey());
				this.talk3MinTextField.setText(talk3MinDecrypted);
			}
	}

	private void listeners() {
		listenerSaveWeekButton();
		listenerOverseerNameTextField();
		listenerWifeNameTextField();

		this.talk1SongTextField.focusedProperty().addListener((obs, oldV, newV) -> {
			if (!newV)
				checkSongTitle(this.talk1SongTextField, this.song1TextField);
		});

		this.talk2SongTextField.focusedProperty().addListener((obs, oldV, newV) -> {
			if (!newV)
				checkSongTitle(this.talk2SongTextField, this.song2TextField);
		});
	}

	private void checkSongTitle(TextField tfSong, TextField tfTitle) {

		String songText = tfSong.getText();
		if (songText != null) {

			try {

				Integer song = Integer.valueOf(songText);
				tfTitle.setText(checkSongTitleByNumber(song));

			} catch (Exception e) {
				tfTitle.setText("");
			}
		} else
			tfTitle.setText("");
	}

	private String checkSongTitleByNumber(Integer song) {

		if (song != null)
			for (Song s : this.songList)
				if (s.getNumber().intValue() == song.intValue())
					return s.getTitle();

		return "";
	}

	private void listenerOverseerNameTextField() {

		this.overseerNameTextField.focusedProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue)
				if (this.overseerShortNameTextField.getText().isEmpty()) {
					if (!this.overseerNameTextField.getText().isEmpty())
						this.overseerShortNameTextField.setText(this.overseerNameTextField.getText(0, 1) + ".");
				} else if (this.overseerNameTextField.getText().isEmpty())
					this.overseerShortNameTextField.setText("");
		});
	}

	private void listenerWifeNameTextField() {

		this.wifeNameTextField.focusedProperty().addListener((observable, oldValue, newValue) -> {
			if (!newValue)
				if (this.wifeShortNameTextField.getText().isEmpty()) {
					if (!this.wifeNameTextField.getText().isEmpty())
						this.wifeShortNameTextField.setText(this.wifeNameTextField.getText(0, 1) + ".");
				} else if (this.wifeNameTextField.getText().isEmpty())
					this.wifeShortNameTextField.setText("");
		});
	}

	private void listenerSaveWeekButton() {
		saveWeekButton.setOnAction(event -> saveWeek());
	}

	private void saveWeek() {

		if (checkFields()) {

			String spInf2 = visitNumberTextField.getText();
			String spInf3 = Crypt.encrypt(overseerNameTextField.getText(), settings.getDatabaseSecretKey());
			String spInf4 = Crypt.encrypt(overseerShortNameTextField.getText(), settings.getDatabaseSecretKey());
			String spInf5 = Crypt.encrypt(overseerSurnameTextField.getText(), settings.getDatabaseSecretKey());
			String spInf6 = Crypt.encrypt(wifeNameTextField.getText(), settings.getDatabaseSecretKey());
			String spInf7 = Crypt.encrypt(wifeShortNameTextField.getText(), settings.getDatabaseSecretKey());
			String spInf8 = Crypt.encrypt(talk1SongTextField.getText(), settings.getDatabaseSecretKey());
			String spInf9 = Crypt.encrypt(talk1MinTextField.getText(), settings.getDatabaseSecretKey());
			String spInf10 = Crypt.encrypt(talk1ThemeTextField.getText(), settings.getDatabaseSecretKey());
			String spInf11 = Crypt.encrypt(talk2SongTextField.getText(), settings.getDatabaseSecretKey());
			String spInf12 = Crypt.encrypt(talk2MinTextField.getText(), settings.getDatabaseSecretKey());
			String spInf13 = Crypt.encrypt(talk2ThemeTextField.getText(), settings.getDatabaseSecretKey());
			String spInf14 = Crypt.encrypt(talk3MinTextField.getText(), settings.getDatabaseSecretKey());
			String spInf15 = Crypt.encrypt(talk3ThemeTextField.getText(), settings.getDatabaseSecretKey());

			String spInf16 = Crypt.encrypt(phoneOverseerTextField.getText(), settings.getDatabaseSecretKey());
			String spInf17 = Crypt.encrypt(mailOverseerTextField.getText(), settings.getDatabaseSecretKey());
			String spInf18 = Crypt.encrypt(phoneWifeTextField.getText(), settings.getDatabaseSecretKey());
			String spInf19 = Crypt.encrypt(mailWifeTextField.getText(), settings.getDatabaseSecretKey());

			String spInf20 = !this.substituteCheckBox.isSelected() ? "0" : "1";

			String spInf21 = Crypt.encrypt(this.song1TextField.getText(), settings.getDatabaseSecretKey());
			String spInf22 = Crypt.encrypt(this.song2TextField.getText(), settings.getDatabaseSecretKey());

			if (this.selectedWeek.spWeekOvIDProperty() != null) {
				// editWeek

				String spWeekOvID = String.valueOf(selectedWeek.getSpWeekOvID());
				String spInf1 = String.valueOf(selectedWeek.getSpInf1());

				Actions.updateOverseerWeek(spWeekOvID, spInf1, spInf2, spInf3, spInf4, spInf5, spInf6, spInf7, spInf8,
						spInf9, spInf10, spInf11, spInf12, spInf13, spInf14, spInf15, spInf16, spInf17, spInf18,
						spInf19, spInf20, spInf21, spInf22, settings, ownerStage, ownerTabPane, thisTab, ownerCtrl);

			} else {
				// newWeek

				String spInf1 = Week.buildKey(this.selectedWeek.getTo());

				Actions.insertOverseerWeek(spInf1, spInf2, spInf3, spInf4, spInf5, spInf6, spInf7, spInf8, spInf9,
						spInf10, spInf11, spInf12, spInf13, spInf14, spInf15, spInf16, spInf17, spInf18, spInf19,
						spInf20, spInf21, spInf22, settings, ownerStage, ownerTabPane, thisTab, ownerCtrl);
			}
		}
	}

	private boolean checkFields() {

		// TODO: Check all fields
		boolean status = true;

		return status;
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

	public UserMenuCircuitOverseer getOwnerCtrl() {
		return ownerCtrl;
	}

	public void setOwnerCtrl(UserMenuCircuitOverseer ownerCtrl) {
		this.ownerCtrl = ownerCtrl;
	}

	public WeekOverseer getSelectedWeek() {
		return selectedWeek;
	}

	public void setSelectedWeek(WeekOverseer selectedWeek) {
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

	public ObservableList<WeekOverseer> getCalendar() {
		return calendar;
	}

	public void setCalendar(ObservableList<WeekOverseer> calendar) {
		this.calendar = calendar;
	}

	public HashMap<String, String> getConfigs() {
		return configs;
	}

	public void setConfigs(HashMap<String, String> configs) {
		this.configs = configs;
	}

	public boolean isConfigSongTitleLoad() {
		return configSongTitleLoad;
	}

	public void setConfigSongTitleLoad(boolean configSongTitleLoad) {
		this.configSongTitleLoad = configSongTitleLoad;
	}

	public ObservableList<Song> getSongList() {
		return songList;
	}

	public void setSongList(ObservableList<Song> songList) {
		this.songList = songList;
	}
}
