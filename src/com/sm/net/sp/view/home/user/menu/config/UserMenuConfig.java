package com.sm.net.sp.view.home.user.menu.config;

import java.util.HashMap;

import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.view.SupportPlannerView;
import com.sm.net.sp.view.home.user.menu.config.task.ConfigLoadTask;
import com.sm.net.sp.view.home.user.menu.config.task.ConfigSaveTask;
import com.sm.net.util.Crypt;
import com.smnet.core.task.TaskManager;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

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
	private CheckBox usciereZone1WeekendCheckBox;
	@FXML
	private CheckBox usciereZone2WeekendCheckBox;
	@FXML
	private CheckBox usciereZone3WeekendCheckBox;

	@FXML
	private Button placesPatternButton;

	private SupportPlannerView application;
	private Stage ownerStage;

	private HashMap<String, String> configs;

	@FXML
	private void initialize() {

		styleClasses();
	}

	public void objectInitialize() {

		viewUpdate();
		initData();
		listeners();
	}

	private void styleClasses() {

		this.headerLabel.getStyleClass().add("label_header_001");

		this.tabPane.getStyleClass().add("tab_pane_003");

		this.placesTab.getStyleClass().add("tab_001");
		this.publicTalkTab.getStyleClass().add("tab_001");
		this.watchtowerTab.getStyleClass().add("tab_001");
		this.overseerTab.getStyleClass().add("tab_001");
		this.memorialTab.getStyleClass().add("tab_001");
		this.audioTab.getStyleClass().add("tab_001");
		this.usciereTab.getStyleClass().add("tab_001");

		this.placesScrollPane.getStyleClass().add("scroll_pane_001");
		this.publicTalkScrollPane.getStyleClass().add("scroll_pane_001");
		this.watchtowerScrollPane.getStyleClass().add("scroll_pane_001");
		this.overseerScrollPane.getStyleClass().add("scroll_pane_001");
		this.memorialScrollPane.getStyleClass().add("scroll_pane_001");
		this.audioScrollPane.getStyleClass().add("scroll_pane_001");
		this.usciereScrollPane.getStyleClass().add("scroll_pane_001");

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

		this.placesPatternButton.getStyleClass().add("button_image_001");
		this.saveButton.getStyleClass().add("button_image_001");

		this.placesHeaderLabel.getStyleClass().add("label_002");
		this.publicTalkHeaderLabel.getStyleClass().add("label_002");
		this.watchtowerHeaderLabel.getStyleClass().add("label_002");
		this.overseerHeaderLabel.getStyleClass().add("label_002");
		this.memorialHeaderLabel.getStyleClass().add("label_002");
		this.audioHeaderLabel.getStyleClass().add("label_002");
		this.usciereHeaderLabel.getStyleClass().add("label_002");

		this.usciereZone1WeekendCheckBox.getStyleClass().add("check_box_001");
		this.usciereZone2WeekendCheckBox.getStyleClass().add("check_box_001");
		this.usciereZone3WeekendCheckBox.getStyleClass().add("check_box_001");
	}

	private void viewUpdate() {

		Language language = this.application.getSettings().getLanguage();

		this.headerImageView.setFitWidth(50);
		this.headerImageView.setFitHeight(50);
		this.headerImageView.setImage(Meta.Resources.getImageLogo(Meta.Resources.CONFIG, 50, 50));

		this.headerLabel.setText(language.getString("sp.menu.config"));

		this.tabPane.setTabMinHeight(75);
		this.tabPane.setTabMaxHeight(75);

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

		this.placesHeaderLabel.setText(language.getString("conf.label.places.header"));
		this.publicTalkHeaderLabel.setText(language.getString("conf.label.publictalk.header"));
		this.watchtowerHeaderLabel.setText(language.getString("conf.label.watchtower.header"));
		this.overseerHeaderLabel.setText(language.getString("conf.label.overseer.header"));
		this.memorialHeaderLabel.setText(language.getString("conf.label.memorial.header"));

		this.audioHeaderLabel.setText(language.getString("conf.label.audio.header"));
		this.usciereHeaderLabel.setText(language.getString("conf.label.usciere.header"));

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
	}

	private void initData() {

		configLoad();
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

	}

	private void listeners() {

		this.saveButton.setOnAction(event -> save());
		this.placesPatternButton.setOnAction(event -> showHelp());
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

			// ------------------------------------------

			String waitMessage = this.application.getSettings().getLanguage().getString("conf.save.wait");

			TaskManager.run(this.getApplication().getAlertBuilder2(), this.ownerStage, waitMessage,
					new ConfigSaveTask(this.application.getAlertBuilder2(), this.application.getSettings(),
							this.ownerStage, placesPatternEncrypted, publicTalkMinEncrypted, watchtowerMinEncrypted,
							overseerTalk1MinEncrypted, overseerTalk2MinEncrypted, overseerTalk3MinEncrypted,
							overseerVisitCounterEncrypted, memorialTalkMinEncrypted, audio1, audio2, audio3, usciere1,
							usciere2, usciere3, usciere1equals, usciere2equals, usciere3equals));
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
