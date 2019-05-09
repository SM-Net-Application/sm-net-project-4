package com.sm.net.sp.view.home.user.menu.meetings;

import com.sm.net.jw.wol.MinistryTypeTranslated;
import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.model.Member;
import com.sm.net.sp.model.MinistryPart;
import com.sm.net.sp.model.UpdateDataAdapter;
import com.sm.net.sp.settings.Settings;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class MinistryPartEditor extends UpdateDataAdapter {

	@FXML
	private Label typeLabel;
	@FXML
	private Label wolTextLabel;
	@FXML
	private Label minLabel;
	@FXML
	private Label themeLabel;
	@FXML
	private Label materialLabel;
	@FXML
	private ComboBox<MinistryTypeTranslated> typeComboBox;
	@FXML
	private TextField wolTextTextField;
	@FXML
	private TextField minTextField;
	@FXML
	private TextField themeTextField;
	@FXML
	private TextField materialTextField;
	@FXML
	private TabPane hallsTabPane;
	@FXML
	private Tab hall1Tab;
	@FXML
	private Tab hall2Tab;
	@FXML
	private Label studentLabel;
	@FXML
	private ListView<Member> studentsListView;
	@FXML
	private Label assistantLabel;
	@FXML
	private ListView<Member> assistantsListView;
	@FXML
	private Label student2Label;
	@FXML
	private ListView<Member> students2ListView;
	@FXML
	private Label assistant2Label;
	@FXML
	private ListView<Member> assistants2ListView;
	@FXML
	private Button saveButton;

	private Settings settings;
	private Language language;
	private MinistryPart ministryPart;
	private int ministryPartIndex;
	private Stage ownerStage;
	private Stage thisStage;
	private TableView<MinistryPart> ministryTableView;

	private ObservableList<MinistryTypeTranslated> ministryTypeTranslatedList;
	private ObservableList<Member> membersList;
	private ObservableList<Member> validMembersList;
	private boolean initData;

	@FXML
	private void initialize() {
		styleClasses();
	}

	private void styleClasses() {

		typeLabel.getStyleClass().add("labelStyle3");
		wolTextLabel.getStyleClass().add("labelStyle3");
		minLabel.getStyleClass().add("labelStyle3");
		themeLabel.getStyleClass().add("labelStyle3");
		materialLabel.getStyleClass().add("labelStyle3");

		typeComboBox.getStyleClass().add("comboBoxStyle1");
		wolTextTextField.getStyleClass().add("textFieldStyle1");
		minTextField.getStyleClass().add("textFieldStyle1");
		themeTextField.getStyleClass().add("textFieldStyle1");
		materialTextField.getStyleClass().add("textFieldStyle1");

		hallsTabPane.getStyleClass().add("tabPaneStyle1");
		hall1Tab.getStyleClass().add("tabStyle1");
		hall2Tab.getStyleClass().add("tabStyle1");

		studentLabel.getStyleClass().add("labelStyle1");
		studentsListView.getStyleClass().add("listViewStyle1");
		assistantLabel.getStyleClass().add("labelStyle1");
		assistantsListView.getStyleClass().add("listViewStyle1");

		student2Label.getStyleClass().add("labelStyle1");
		students2ListView.getStyleClass().add("listViewStyle1");
		assistant2Label.getStyleClass().add("labelStyle1");
		assistants2ListView.getStyleClass().add("listViewStyle1");

		saveButton.getStyleClass().add("buttonStyle2");
	}

	public void objectInitialize() {

		validMembersList = FXCollections.observableArrayList();

		listeners();
		viewUpdate();

		wolTextTextField.setEditable(false);

		initData();
		updateValidMembersListViews(typeComboBox.getSelectionModel().getSelectedIndex());
		selectMembers(this.studentsListView, this.ministryPart.getStudent());
		selectMembers(this.assistantsListView, this.ministryPart.getAssistant());
		selectMembers(this.students2ListView, this.ministryPart.getStudent2());
		selectMembers(this.assistants2ListView, this.ministryPart.getAssistant2());
	}

	private void listeners() {
		listenerTypeComboBox();
		listenerSaveButton();
	}

	private void selectMembers(ListView<Member> listView, Member member) {

		if (member != null) {

			ObservableList<Member> items = listView.getItems();
			if (items != null) {

				boolean find = false;
				int index = -1;

				for (Member listViewMember : items) {
					index += 1;
					if (listViewMember.getSpMemberID() == member.getSpMemberID()) {
						find = true;
						break;
					}
				}

				if (find)
					listView.getSelectionModel().select(index);
			}
		}
	}

	private void listenerTypeComboBox() {

		typeComboBox.getSelectionModel().selectedIndexProperty()
				.addListener((observable, oldValue, newValue) -> updateValidMembersListViews(newValue));
	}

	private void listenerSaveButton() {

		saveButton.setOnAction(event -> saveButtonOnAction());
	}

	private void saveButtonOnAction() {

		MinistryTypeTranslated ministryTypeTranslated = typeComboBox.getSelectionModel().getSelectedItem();
		String min = minTextField.getText();
		String theme = themeTextField.getText();
		String material = materialTextField.getText();

		Member student = (studentsListView.getSelectionModel().getSelectedIndex() > -1)
				? studentsListView.getSelectionModel().getSelectedItem() : Member.emptyMember(language);

		Member assistant = (assistantsListView.getSelectionModel().getSelectedIndex() > -1)
				? assistantsListView.getSelectionModel().getSelectedItem() : Member.emptyMember(language);

		Member student2 = (students2ListView.getSelectionModel().getSelectedIndex() > -1)
				? students2ListView.getSelectionModel().getSelectedItem() : Member.emptyMember(language);

		Member assistant2 = (assistants2ListView.getSelectionModel().getSelectedIndex() > -1)
				? assistants2ListView.getSelectionModel().getSelectedItem() : Member.emptyMember(language);

		ministryPart.setMinistryTypeTranslated(ministryTypeTranslated);
		ministryPart.setMin(Integer.valueOf(min));
		ministryPart.setTheme(theme);
		ministryPart.setMaterial(material);
		ministryPart.setStudent(student);
		ministryPart.setAssistant(assistant);
		ministryPart.setStudent2(student2);
		ministryPart.setAssistant2(assistant2);

		ministryTableView.refresh();

		thisStage.close();
	}

	private void viewUpdate() {

		this.language = settings.getLanguage();

		typeLabel.setText(language.getString("TEXT0091"));
		wolTextLabel.setText(language.getString("TEXT0092"));
		minLabel.setText(language.getString("TEXT0093"));
		themeLabel.setText(language.getString("TEXT0094"));
		materialLabel.setText(language.getString("TEXT0095"));

		hall1Tab.setText(language.getString("TEXT0135"));
		hall2Tab.setText(language.getString("TEXT0136"));

		// studentLabel.setText(language.getString("TEXT0134") + " / " +
		// language.getString("TEXT0044"));
		// student2Label.setText(language.getString("TEXT0134") + " / " +
		// language.getString("TEXT0044"));

		assistantLabel.setText(language.getString("TEXT0038"));
		assistant2Label.setText(language.getString("TEXT0038"));

		saveButton.setGraphic(new ImageView(Meta.Resources.SAVE));
		saveButton.setText("");
	}

	private void initData() {

		initData = true;

		initTypeComboBox();
		wolTextTextField.setText(ministryPart.getFullText());
		minTextField.setText(String.valueOf(ministryPart.getMin()));
		themeTextField.setText(ministryPart.getTheme());
		materialTextField.setText(ministryPart.getMaterial());

		initData = false;
	}

	private void initTypeComboBox() {

		typeComboBox.setItems(ministryTypeTranslatedList);

		int index = -1;
		for (MinistryTypeTranslated ministryTypeTranslated : typeComboBox.getItems()) {
			index += 1;
			if (ministryPart.getMinistryTypeTranslated().getOrdinal() == ministryTypeTranslated.getOrdinal())
				break;
		}

		typeComboBox.getSelectionModel().select(index);

		switch (typeComboBox.getItems().get(index).getType()) {
		case DISCUSSION:
			studentLabel.setText(language.getString("TEXT0134"));
			student2Label.setText(language.getString("TEXT0134"));
			break;
		default:
			studentLabel.setText(language.getString("TEXT0044"));
			student2Label.setText(language.getString("TEXT0044"));
			break;
		}
	}

	public void updateValidMembersListViews(Number newValue) {

		if (newValue.intValue() > -1) {

			if (!initData) {

				validMembersList.clear();

				switch (typeComboBox.getSelectionModel().getSelectedItem().getType()) {
				case DISCUSSION:
					studentLabel.setText(language.getString("TEXT0134"));
					student2Label.setText(language.getString("TEXT0134"));
					GUIUpdateForDiscussion();
					break;
				case INITIAL_CALL:
					studentLabel.setText(language.getString("TEXT0044"));
					student2Label.setText(language.getString("TEXT0044"));
					GUIUpdateForInitialCall();
					break;
				case RETURN_VISIT:
					studentLabel.setText(language.getString("TEXT0044"));
					student2Label.setText(language.getString("TEXT0044"));
					GUIUpdateForReturnVisit();
					break;
				case BIBLE_STUDY:
					studentLabel.setText(language.getString("TEXT0044"));
					student2Label.setText(language.getString("TEXT0044"));
					GUIUpdateForBibleStudy();
					break;
				case TALK:
					studentLabel.setText(language.getString("TEXT0044"));
					student2Label.setText(language.getString("TEXT0044"));
					GUIUpdateForTalk();
					break;
				}
			}
		}
	}

	private void GUIUpdateForDiscussion() {

		for (Member member : membersList)
			if (member.getSpInf10() == 1)
				validMembersList.add(member);

		setListView(validMembersList, null);
	}

	private void GUIUpdateForInitialCall() {

		for (Member member : membersList)
			if (member.getSpInf14() == 1)
				validMembersList.add(member);

		setListView(validMembersList, validMembersList);
	}

	private void GUIUpdateForReturnVisit() {

		for (Member member : membersList)
			if (member.getSpInf15() == 1)
				validMembersList.add(member);

		setListView(validMembersList, validMembersList);
	}

	private void GUIUpdateForBibleStudy() {

		for (Member member : membersList)
			if (member.getSpInf16() == 1)
				validMembersList.add(member);

		setListView(validMembersList, validMembersList);
	}

	private void GUIUpdateForTalk() {

		for (Member member : membersList)
			if (member.getSpInf17() == 1)
				validMembersList.add(member);

		setListView(validMembersList, null);
	}

	private void setListView(ObservableList<Member> list1, ObservableList<Member> list2) {

		validMembersList.sort((o1, o2) -> (o1.getSpInf2Decrypted().concat(o1.getSpInf1Decrypted()))
				.compareTo((o2.getSpInf2Decrypted().concat(o2.getSpInf1Decrypted()))));

		validMembersList.add(0, Member.emptyMember(language));

		studentsListView.setItems(list1);
		assistantsListView.setItems(list2);
		students2ListView.setItems(list1);
		assistants2ListView.setItems(list2);
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

	public MinistryPart getMinistryPart() {
		return ministryPart;
	}

	public void setMinistryPart(MinistryPart ministryPart) {
		this.ministryPart = ministryPart;
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

	public ObservableList<MinistryTypeTranslated> getMinistryTypeTranslatedList() {
		return ministryTypeTranslatedList;
	}

	public void setMinistryTypeTranslatedList(ObservableList<MinistryTypeTranslated> ministryTypeTranslatedList) {
		this.ministryTypeTranslatedList = ministryTypeTranslatedList;
	}

	public Stage getThisStage() {
		return thisStage;
	}

	public void setThisStage(Stage thisStage) {
		this.thisStage = thisStage;
	}

	public int getMinistryPartIndex() {
		return ministryPartIndex;
	}

	public void setMinistryPartIndex(int ministryPartIndex) {
		this.ministryPartIndex = ministryPartIndex;
	}

	public TableView<MinistryPart> getMinistryTableView() {
		return ministryTableView;
	}

	public void setMinistryTableView(TableView<MinistryPart> ministryTableView) {
		this.ministryTableView = ministryTableView;
	}

}
