package com.sm.net.sp.view.home.user.menu.naturaldisaster;

import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.actions.Actions;
import com.sm.net.sp.model.Member;
import com.sm.net.sp.settings.Settings;
import com.sm.net.util.Crypt;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;

public class UserMenuNaturalDisasterMemberEditor {

	@FXML
	private TabPane memberTabPane;

	@FXML
	private Tab memberContactsTab;

	@FXML
	private Label smartphoneLabel;
	@FXML
	private TextField smartphoneTextField;
	@FXML
	private Label emailLabel;
	@FXML
	private TextField emailTextField;

	@FXML
	private Button saveButton;

	private Settings settings;
	private Language language;
	private Stage ownerStage;
	private TabPane congrTabPane;
	private Tab newMemberTab;
	private Tab membersTab;
	private UserMenuNaturalDisasterList ownerCtrl;
	private Member selectedMember;

	@FXML
	private void initialize() {
		styleClasses();
	}

	private void styleClasses() {

		this.memberTabPane.getStyleClass().add("tab_pane_003");

		memberContactsTab.getStyleClass().add("tab_001");

		smartphoneLabel.getStyleClass().add("label_set_001");
		emailLabel.getStyleClass().add("label_set_001");

		smartphoneTextField.getStyleClass().add("text_field_001");
		emailTextField.getStyleClass().add("text_field_001");

		saveButton.getStyleClass().add("button_image_001");
	}

	public void objectInitialize() {
		initValue();
		viewUpdate();
		listeners();
	}

	private void initValue() {

		if (selectedMember != null) {
			smartphoneTextField.setText(selectedMember.getSpInf40Decrypted());
			emailTextField.setText(selectedMember.getSpInf41Decrypted());
		}
	}

	private void listeners() {
		listenerSaveButton();
	}

	private void listenerSaveButton() {
		saveButton.setOnAction(event -> saveMember());
	}

	private void saveMember() {

		String spInf40 = Crypt.encrypt(smartphoneTextField.getText(), settings.getDatabaseSecretKey());
		String spInf41 = Crypt.encrypt(emailTextField.getText(), settings.getDatabaseSecretKey());

		if (selectedMember != null)
			editMember(spInf40, spInf41);
	}

	private void editMember(String spInf40, String spInf41) {

		Actions.updateMemberNaturalDisaster(String.valueOf(selectedMember.getSpMemberID()), spInf40, spInf41, settings,
				ownerStage, congrTabPane, newMemberTab, membersTab, ownerCtrl);
	}

	private void viewUpdate() {

		this.language = settings.getLanguage();

		Tooltip saveTabTooltip = new Tooltip(this.language.getString("naturaldisastermembereditor.tooltip.save"));
		saveTabTooltip.getStyleClass().add("tooltip_001");
		this.saveButton.setTooltip(saveTabTooltip);
		this.saveButton.setText("");
		this.saveButton.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.SAVE));

		this.memberTabPane.setTabMinHeight(75);
		this.memberTabPane.setTabMaxHeight(75);

		Tooltip memberContactsTabTooltip = new Tooltip(this.language.getString("TEXT0106"));
		memberContactsTabTooltip.getStyleClass().add("tooltip_001");
		this.memberContactsTab.setTooltip(memberContactsTabTooltip);
		this.memberContactsTab.setText("");
		this.memberContactsTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.CONTACTS));
		this.memberContactsTab.setText("");

		smartphoneLabel.setText(language.getString("TEXT0107"));
		emailLabel.setText(language.getString("TEXT0108"));
	}

	public Settings getSettings() {
		return settings;
	}

	public void setSettings(Settings settings) {
		this.settings = settings;
	}

	public Stage getOwnerStage() {
		return ownerStage;
	}

	public void setOwnerStage(Stage ownerStage) {
		this.ownerStage = ownerStage;
	}

	public TabPane getCongrTabPane() {
		return congrTabPane;
	}

	public void setCongrTabPane(TabPane congrTabPane) {
		this.congrTabPane = congrTabPane;
	}

	public Tab getNewMemberTab() {
		return newMemberTab;
	}

	public void setNewMemberTab(Tab newMemberTab) {
		this.newMemberTab = newMemberTab;
	}

	public UserMenuNaturalDisasterList getOwnerCtrl() {
		return ownerCtrl;
	}

	public void setOwnerCtrl(UserMenuNaturalDisasterList ownerCtrl) {
		this.ownerCtrl = ownerCtrl;
	}

	public Tab getMembersTab() {
		return membersTab;
	}

	public void setMembersTab(Tab membersTab) {
		this.membersTab = membersTab;
	}

	public Member getSelectedMember() {
		return selectedMember;
	}

	public void setSelectedMember(Member selectedMember) {
		this.selectedMember = selectedMember;
	}
}
