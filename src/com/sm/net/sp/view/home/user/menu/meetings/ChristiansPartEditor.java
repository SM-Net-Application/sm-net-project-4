package com.sm.net.sp.view.home.user.menu.meetings;

import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.model.ChristiansPart;
import com.sm.net.sp.model.Member;
import com.sm.net.sp.model.UpdateDataAdapter;
import com.sm.net.sp.settings.Settings;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ChristiansPartEditor extends UpdateDataAdapter {

	@FXML
	private Label wolTextLabel;
	@FXML
	private Label minLabel;
	@FXML
	private Label themeLabel;
	@FXML
	private Label materialLabel;
	@FXML
	private Label teacherLabel;
	@FXML
	private TextField wolTextTextField;
	@FXML
	private TextField minTextField;
	@FXML
	private TextField themeTextField;
	@FXML
	private TextField materialTextField;
	@FXML
	private ListView<Member> teachersListView;
	@FXML
	private Button saveButton;

	private Settings settings;
	private Language language;
	private ChristiansPart christiansPart;
	private int christiansPartIndex;
	private Stage ownerStage;
	private Stage thisStage;
	private TableView<ChristiansPart> christiansPartTableView;

	private ObservableList<Member> membersList;
	private ObservableList<Member> validMembersList;
	private boolean initData;

	@FXML
	private void initialize() {
		styleClasses();
	}

	private void styleClasses() {

		wolTextLabel.getStyleClass().add("labelStyle3");
		minLabel.getStyleClass().add("labelStyle3");
		themeLabel.getStyleClass().add("labelStyle3");
		materialLabel.getStyleClass().add("labelStyle3");
		teacherLabel.getStyleClass().add("labelStyle1");

		wolTextTextField.getStyleClass().add("textFieldStyle1");
		minTextField.getStyleClass().add("textFieldStyle1");
		themeTextField.getStyleClass().add("textFieldStyle1");
		materialTextField.getStyleClass().add("textFieldStyle1");

		teachersListView.getStyleClass().add("listViewStyle1");

		saveButton.getStyleClass().add("buttonStyle2");
	}

	public void objectInitialize() {

		validMembersList = FXCollections.observableArrayList();

		listeners();
		viewUpdate();

		wolTextTextField.setEditable(false);

		initData();
		updateTeachersListView();
	}

	private void listeners() {
		listenerSaveButton();
	}

	private void listenerSaveButton() {
		saveButton.setOnAction(event -> saveButtonOnAction());
	}

	private void saveButtonOnAction() {

		String min = minTextField.getText();
		String theme = themeTextField.getText();
		String material = materialTextField.getText();

		Member teacher = (teachersListView.getSelectionModel().getSelectedIndex() > -1)
				? teachersListView.getSelectionModel().getSelectedItem() : Member.emptyMember(language);

		christiansPart.setMin(Integer.valueOf(min));
		christiansPart.setTheme(theme);
		christiansPart.setMaterial(material);
		christiansPart.setTeacher(teacher);

		christiansPartTableView.refresh();

		thisStage.close();
	}

	private void viewUpdate() {

		this.language = settings.getLanguage();

		wolTextLabel.setText(language.getString("TEXT0092"));
		minLabel.setText(language.getString("TEXT0093"));
		themeLabel.setText(language.getString("TEXT0094"));
		materialLabel.setText(language.getString("TEXT0095"));
		teacherLabel.setText(language.getString("TEXT0098"));

		saveButton.setGraphic(new ImageView(Meta.Resources.SAVE));
		saveButton.setText("");
	}

	private void initData() {

		initData = true;

		wolTextTextField.setText(christiansPart.getFullText());
		minTextField.setText(String.valueOf(christiansPart.getMin()));
		themeTextField.setText(christiansPart.getTheme());
		materialTextField.setText(christiansPart.getMaterial());

		initData = false;
	}

	private void updateTeachersListView() {

		for (Member member : membersList)
			if (member.getSpInf9() == 1 || member.getSpInf10() == 1)
				validMembersList.add(member);

		setListView(validMembersList);
	}

	private void setListView(ObservableList<Member> list) {

		validMembersList.sort((o1, o2) -> (o1.getSpInf2Decrypted().concat(o1.getSpInf1Decrypted()))
				.compareTo((o2.getSpInf2Decrypted().concat(o2.getSpInf1Decrypted()))));

		validMembersList.add(0, Member.emptyMember(language));

		teachersListView.setItems(list);
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

	public ObservableList<Member> getMembersList() {
		return membersList;
	}

	public void setMembersList(ObservableList<Member> membersList) {
		this.membersList = membersList;
	}

	public boolean isViewUpdate() {
		return initData;
	}

	public void setViewUpdate(boolean viewUpdate) {
		this.initData = viewUpdate;
	}

	public Stage getThisStage() {
		return thisStage;
	}

	public void setThisStage(Stage thisStage) {
		this.thisStage = thisStage;
	}

	public ChristiansPart getChristiansPart() {
		return christiansPart;
	}

	public void setChristiansPart(ChristiansPart christiansPart) {
		this.christiansPart = christiansPart;
	}

	public int getChristiansPartIndex() {
		return christiansPartIndex;
	}

	public void setChristiansPartIndex(int christiansPartIndex) {
		this.christiansPartIndex = christiansPartIndex;
	}

	public ObservableList<Member> getValidMembersList() {
		return validMembersList;
	}

	public void setValidMembersList(ObservableList<Member> validMembersList) {
		this.validMembersList = validMembersList;
	}

	public TableView<ChristiansPart> getChristiansPartTableView() {
		return christiansPartTableView;
	}

	public void setChristiansPartTableView(TableView<ChristiansPart> christiansPartTableView) {
		this.christiansPartTableView = christiansPartTableView;
	}

}
