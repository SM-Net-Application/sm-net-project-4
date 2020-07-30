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
	private ScrollPane placesScrollPane;
	@FXML
	private ScrollPane publicTalkScrollPane;
	@FXML
	private ScrollPane watchtowerScrollPane;

	@FXML
	private Label placesPatternLabel;
	@FXML
	private Label publicTalkMinLabel;
	@FXML
	private Label watchtowerMinLabel;

	@FXML
	private TextField placesPatternTextField;
	@FXML
	private TextField publicTalkMinTextField;
	@FXML
	private TextField watchtowerMinTextField;

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

		this.placesScrollPane.getStyleClass().add("scroll_pane_001");
		this.publicTalkScrollPane.getStyleClass().add("scroll_pane_001");
		this.watchtowerScrollPane.getStyleClass().add("scroll_pane_001");

		this.placesPatternLabel.getStyleClass().add("label_set_001");
		this.publicTalkMinLabel.getStyleClass().add("label_set_001");
		this.watchtowerMinLabel.getStyleClass().add("label_set_001");

		this.placesPatternTextField.getStyleClass().add("text_field_001");
		this.publicTalkMinTextField.getStyleClass().add("text_field_001");
		this.watchtowerMinTextField.getStyleClass().add("text_field_001");

		this.placesPatternButton.getStyleClass().add("button_image_001");
		this.saveButton.getStyleClass().add("button_image_001");
	}

	private void viewUpdate() {

		Language language = this.application.getSettings().getLanguage();

		this.headerImageView.setFitWidth(50);
		this.headerImageView.setFitHeight(50);
		this.headerImageView.setImage(Meta.Resources.getImageLogo(Meta.Resources.CONFIG, 50, 50));

		this.headerLabel.setText(language.getString("sp.menu.config"));

		this.tabPane.setTabMinHeight(75);
		this.tabPane.setTabMaxHeight(75);

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

		this.placesPatternLabel.setText(language.getString("conf.label.places.pattern"));
		this.publicTalkMinLabel.setText(language.getString("conf.label.publictalk.min"));
		this.watchtowerMinLabel.setText(language.getString("conf.label.watchtower.min"));

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

	}

	private void initData() {

		generalInfoLoad();
	}

	private void generalInfoLoad() {

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

			String waitMessage = this.application.getSettings().getLanguage().getString("conf.save.wait");

			TaskManager.run(this.getApplication().getAlertBuilder2(), this.ownerStage, waitMessage,
					new ConfigSaveTask(this.application.getAlertBuilder2(), this.application.getSettings(),
							this.ownerStage, placesPatternEncrypted, publicTalkMinEncrypted, watchtowerMinEncrypted));
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