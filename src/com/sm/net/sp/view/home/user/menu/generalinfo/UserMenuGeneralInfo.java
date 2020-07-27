package com.sm.net.sp.view.home.user.menu.generalinfo;

import java.util.HashMap;

import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.view.SupportPlannerView;
import com.sm.net.sp.view.home.user.menu.generalinfo.task.GeneralInfoLoadTask;
import com.sm.net.sp.view.home.user.menu.generalinfo.task.GeneralInfoSaveTask;
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

public class UserMenuGeneralInfo {

	@FXML
	private ImageView headerImageView;
	@FXML
	private Label headerLabel;

	@FXML
	private Button saveButton;

	@FXML
	private TabPane tabPane;
	@FXML
	private Tab congregationTab;

	@FXML
	private ScrollPane congregationScrollPane;

	@FXML
	private Label congregationNameLabel;
	@FXML
	private Label congregationNumberLabel;

	@FXML
	private TextField congregationNameTextField;
	@FXML
	private TextField congregationNumberTextField;

	private SupportPlannerView application;
	private Stage ownerStage;

	private HashMap<String, String> generalInfos;

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

		this.congregationTab.getStyleClass().add("tab_001");

		this.congregationScrollPane.getStyleClass().add("scroll_pane_001");

		this.congregationNameLabel.getStyleClass().add("label_set_001");
		this.congregationNumberLabel.getStyleClass().add("label_set_001");

		this.congregationNameTextField.getStyleClass().add("text_field_001");
		this.congregationNumberTextField.getStyleClass().add("text_field_001");

		this.saveButton.getStyleClass().add("button_image_001");
	}

	private void viewUpdate() {

		Language language = this.application.getSettings().getLanguage();

		this.headerImageView.setFitWidth(50);
		this.headerImageView.setFitHeight(50);
		this.headerImageView.setImage(Meta.Resources.getImageLogo(Meta.Resources.GENERALINFO, 50, 50));

		this.headerLabel.setText(language.getString("sp.menu.info"));

		this.tabPane.setTabMinHeight(75);
		this.tabPane.setTabMaxHeight(75);

		Tooltip congregationTabTooltip = new Tooltip(language.getString("generalinfo.tooltip.tab.congregation"));
		congregationTabTooltip.getStyleClass().add("tooltip_001");
		this.congregationTab.setTooltip(congregationTabTooltip);
		this.congregationTab.setText("");
		this.congregationTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.USER_MENU_CONGR));

		this.congregationNameLabel.setText(language.getString("generalinfo.label.congregation.name"));
		this.congregationNumberLabel.setText(language.getString("generalinfo.label.congregation.number"));

		Tooltip saveTooltip = new Tooltip(language.getString("generalinfo.tooltip.save"));
		saveTooltip.getStyleClass().add("tooltip_001");
		this.saveButton.setTooltip(saveTooltip);
		this.saveButton.setText(null);
		this.saveButton.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.SAVE));

	}

	private void initData() {

		generalInfoLoad();
	}

	private void generalInfoLoad() {

		String waitMessage = this.application.getSettings().getLanguage().getString("generalinfo.load.wait");

		TaskManager.run(this.application.getAlertBuilder2(), this.ownerStage, waitMessage, new GeneralInfoLoadTask(
				this.application.getAlertBuilder2(), this.application.getSettings(), this.ownerStage, this));

	}

	public void fill() {

		String congregationName = this.generalInfos.get("inf1");
		if (congregationName != null)
			this.congregationNameTextField
					.setText(Crypt.decrypt(congregationName, this.application.getSettings().getDatabaseSecretKey()));

		String congregationNumber = this.generalInfos.get("inf2");
		if (congregationNumber != null)
			this.congregationNumberTextField
					.setText(Crypt.decrypt(congregationNumber, this.application.getSettings().getDatabaseSecretKey()));

	}

	private void listeners() {

		this.saveButton.setOnAction(event -> save());
	}

	private void save() {

		if (checkFields()) {

			String congregationName = this.congregationNameTextField.getText();
			String congregationNameEncrypted = Crypt.encrypt(congregationName,
					this.application.getSettings().getDatabaseSecretKey());

			String congregationNumber = this.congregationNumberTextField.getText();
			String congregationNumberEncrypted = Crypt.encrypt(congregationNumber,
					this.application.getSettings().getDatabaseSecretKey());

			String waitMessage = this.application.getSettings().getLanguage().getString("generalinfo.save.wait");
			TaskManager.run(this.getApplication().getAlertBuilder2(), this.ownerStage, waitMessage,
					new GeneralInfoSaveTask(this.application.getAlertBuilder2(), this.application.getSettings(),
							this.ownerStage, congregationNameEncrypted, congregationNumberEncrypted));
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

	public Tab getCongregationTab() {
		return congregationTab;
	}

	public void setCongregationTab(Tab congregationTab) {
		this.congregationTab = congregationTab;
	}

	public ScrollPane getCongregationScrollPane() {
		return congregationScrollPane;
	}

	public void setCongregationScrollPane(ScrollPane congregationScrollPane) {
		this.congregationScrollPane = congregationScrollPane;
	}

	public Label getCongregationNameLabel() {
		return congregationNameLabel;
	}

	public void setCongregationNameLabel(Label congregationNameLabel) {
		this.congregationNameLabel = congregationNameLabel;
	}

	public Label getCongregationNumberLabel() {
		return congregationNumberLabel;
	}

	public void setCongregationNumberLabel(Label congregationNumberLabel) {
		this.congregationNumberLabel = congregationNumberLabel;
	}

	public TextField getCongregationNameTextField() {
		return congregationNameTextField;
	}

	public void setCongregationNameTextField(TextField congregationNameTextField) {
		this.congregationNameTextField = congregationNameTextField;
	}

	public TextField getCongregationNumberTextField() {
		return congregationNumberTextField;
	}

	public void setCongregationNumberTextField(TextField congregationNumberTextField) {
		this.congregationNumberTextField = congregationNumberTextField;
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

	public HashMap<String, String> getGeneralInfos() {
		return generalInfos;
	}

	public void setGeneralInfos(HashMap<String, String> generalInfos) {
		this.generalInfos = generalInfos;
	}
}
