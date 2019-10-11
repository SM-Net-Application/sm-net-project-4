package com.sm.net.sp.view.history;

import java.time.LocalDate;
import java.util.Comparator;

import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.model.ChristiansPart;
import com.sm.net.sp.model.Member;
import com.sm.net.sp.model.MemberHistory;
import com.sm.net.sp.model.PrivilegeHistory;
import com.sm.net.sp.model.Privileges;
import com.sm.net.sp.model.Week;
import com.sm.net.sp.view.home.user.menu.meetings.UserMenuMeetingsEditor;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class History {

	@FXML
	private AnchorPane layout;

	@FXML
	private Label titleLabel;
	@FXML
	private Label brotherLabel;

	@FXML
	private TableView<MemberHistory> membersTableView;
	@FXML
	private TableColumn<MemberHistory, String> memberNameTableColumn;
	@FXML
	private TableColumn<MemberHistory, String> memberLastDateTableColumn;
	@FXML
	private TableColumn<MemberHistory, ImageView> memberLastDateStatusTableColumn;

	@FXML
	private TableView<PrivilegeHistory> privilegesTableView;
	@FXML
	private TableColumn<PrivilegeHistory, String> privilegesDateTableColumn;
	@FXML
	private TableColumn<PrivilegeHistory, ImageView> privilegesStatusTableColumn;
	@FXML
	private TableColumn<PrivilegeHistory, String> privilegesNameTableColumn;

	@FXML
	private Button selectButton;

	private Language language;
	private ObservableList<Member> members;
	private ObservableList<MemberHistory> membersPrivilege;
	private ObservableList<PrivilegeHistory> memberPrivilegeHistory;
	private Privileges privilege;
	private ObservableList<Week> databaseWeeks;
	private Week selectedWeek;
	private Week editorWeek;
	private UserMenuMeetingsEditor editor;
	private Stage thisStage;

	@FXML
	private void initialize() {
		styleClasses();
		cellValueFactory();
	}

	private void styleClasses() {
		layout.getStyleClass().add("main_color_001");

		titleLabel.getStyleClass().add("label_002");
		brotherLabel.getStyleClass().add("label_002");

		membersTableView.getStyleClass().add("table_view_001");
		privilegesTableView.getStyleClass().add("table_view_001");

		selectButton.getStyleClass().add("button_image_001");
	}

	private void cellValueFactory() {

		memberNameTableColumn.setCellValueFactory(
				cellData -> new SimpleStringProperty(cellData.getValue().getMember().getNameStyle1()));

		memberLastDateTableColumn
				.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLastDateText()));

		memberLastDateStatusTableColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<ImageView>(
				Meta.Resources.imageForTab(cellData.getValue().getStatus())));

		privilegesDateTableColumn
				.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLastDateText()));

		privilegesStatusTableColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<ImageView>(
				Meta.Resources.imageForTab(cellData.getValue().getStatus())));

		privilegesNameTableColumn.setCellValueFactory(
				cellData -> new SimpleStringProperty(cellData.getValue().getPrivilege().getTranslatedName(language)));
	}

	public void objectInitialize() {

		viewUpdate();
		listeners();
		createMembersPrivilegeList();
		setLastDates();
	}

	private void listeners() {
		listenerMembersTableView();
		listenerSelectButton();
	}

	private void listenerSelectButton() {
		selectButton.setOnAction(event -> selectMember());
	}

	private void selectMember() {

		if (this.membersTableView.getSelectionModel().getSelectedIndex() > -1) {

			MemberHistory member = this.membersTableView.getSelectionModel().getSelectedItem();
			int memberID = member.getMember().getSpMemberID();
			this.editor.updateSelectedComboBox(this.privilege, memberID);
			this.thisStage.close();
		}
	}

	private void listenerMembersTableView() {
		membersTableView.getSelectionModel().selectedIndexProperty().addListener((ob, o, n) -> changeMember(n));
		membersTableView.setRowFactory(param -> {
			TableRow<MemberHistory> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (!row.isEmpty()))
					selectMember();
			});
			return row;
		});
	}

	private void changeMember(Number n) {
		if (n.intValue() > -1) {
			MemberHistory member = this.membersTableView.getSelectionModel().getSelectedItem();
			this.brotherLabel.setText(String.format(language.getString("sp.history.brotherselected"),
					member.getMember().getNameStyle1()));

			checkPrivilegeMember(member);

		} else
			brotherLabel.setText(language.getString("sp.history.select"));
	}

	private void checkPrivilegeMember(MemberHistory member) {

		this.memberPrivilegeHistory = FXCollections.observableArrayList();

		if (selectedWeek.spInf1Property() != null) {

			int memberID = member.getMember().getSpMemberID();
			checkPrivilegeMemberDatabaseWeek(memberID);
			checkPrivilegeMemberWeek(memberID, this.editorWeek);

		} else
			this.brotherLabel.setText(language.getString("sp.history.weeknotsaved"));

		memberPrivilegeHistory.sort((o1, o2) -> o1.getLastDate().compareTo(o2.getLastDate()));
		this.privilegesTableView.setItems(memberPrivilegeHistory);
	}

	private void checkPrivilegeMemberDatabaseWeek(int memberID) {

		for (Week week : databaseWeeks)
			if (week.getSpInf1() != this.selectedWeek.getSpInf1())
				checkPrivilegeMemberWeek(memberID, week);
	}

	private void checkPrivilegeMemberWeek(int memberID, Week week) {

		if (memberID == week.getSpInf3())
			checkPrivilege(Privileges.PRESIDENT_MIDWEEK, week);

		if (memberID == week.getSpInf4() || memberID == week.getSpInf27() || memberID == week.getSpInf40()) {

			if (memberID == week.getSpInf4())
				checkPrivilege(Privileges.PRAY1_MIDWEEK, week);
			else if (memberID == week.getSpInf27())
				checkPrivilege(Privileges.PRAY2_MIDWEEK, week);
			else if (memberID == week.getSpInf40())
				checkPrivilege(Privileges.PRAY_END_WEEKEND, week);
		}

		if (memberID == week.getSpInf29() || memberID == week.getSpInf38()) {

			if (memberID == week.getSpInf29())
				checkPrivilege(Privileges.CONGRBIBLESTUDY_READER_MIDWEEK, week);
			else if (memberID == week.getSpInf38())
				checkPrivilege(Privileges.WATCHTOWER_READER, week);
		}

		if (memberID == week.getSpInf30())
			checkPrivilege(Privileges.PRESIDENT_WEEKEND, week);

		if (memberID == week.getSpInf37())
			checkPrivilege(Privileges.CONDUCTOR_WATCHTOWER, week);

		if (memberID == week.getSpInf11())
			checkPrivilege(Privileges.TALK_MIDWEEK, week);

		if (memberID == week.getSpInf14())
			checkPrivilege(Privileges.DIGGING_MIDWEEK, week);

		if (memberID == week.getSpInf23())
			checkPrivilege(Privileges.CONGRBIBLESTUDY_MIDWEEK, week);

		for (ChristiansPart cp : week.getChristiansPartList())
			if (memberID == cp.getTeacher().getSpMemberID()) {
				checkPrivilege(Privileges.CHRISTIAN_LIFE, week);
				break;
			}
	}

	private void checkPrivilege(Privileges privilege, Week week) {

		PrivilegeHistory checkPrivilegeHistory = privilegeHistoryContains(privilege);
		if (checkPrivilegeHistory != null)
			checkPrivilegeHistory.checkLastDate(week.getSpInf1(), selectedWeek.getSpInf1());
		else
			this.memberPrivilegeHistory
					.add(new PrivilegeHistory(privilege, week.getSpInf1(), selectedWeek.getSpInf1()));
	}

	private PrivilegeHistory privilegeHistoryContains(Privileges privilege) {

		for (PrivilegeHistory ph : this.memberPrivilegeHistory)
			if (ph.getPrivilege() == privilege)
				return ph;

		return null;
	}

	private void setLastDates() {

		membersPrivilege.forEach(mh -> mh.checkLastDate(this.databaseWeeks));
		membersPrivilege.forEach(mh -> mh.checkEditorLastDate(this.editorWeek));

		membersPrivilege.sort(new Comparator<MemberHistory>() {

			@Override
			public int compare(MemberHistory o1, MemberHistory o2) {

				LocalDate lastDate = o1.getLastDate();
				if (lastDate == null)
					return -1;

				LocalDate lastDate2 = o2.getLastDate();
				if (lastDate2 == null)
					return 1;

				return lastDate.compareTo(lastDate2);
			}
		});

		membersTableView.refresh();
	}

	private void viewUpdate() {

		titleLabel.setText(privilege.getTranslatedName(language));
		brotherLabel.setText(language.getString("sp.history.select"));

		membersTableView.setMinWidth(500);
		memberNameTableColumn.setText(language.getString("sp.history.brother"));
		memberLastDateTableColumn.setText(language.getString("sp.history.lastdate"));
		memberLastDateTableColumn.setMinWidth(250);
		memberLastDateTableColumn.setMaxWidth(250);
		memberLastDateStatusTableColumn.setText("");
		memberLastDateStatusTableColumn.setMinWidth(50);
		memberLastDateStatusTableColumn.setMaxWidth(50);

		privilegesDateTableColumn.setText(language.getString("sp.history.date"));
		privilegesDateTableColumn.setMinWidth(100);
		privilegesDateTableColumn.setMaxWidth(100);
		privilegesStatusTableColumn.setText("");
		privilegesStatusTableColumn.setMinWidth(50);
		privilegesStatusTableColumn.setMaxWidth(50);
		privilegesNameTableColumn.setText(language.getString("sp.history.privilege"));

		selectButton.setText(null);
		selectButton.setGraphic(Meta.Resources.imageViewForButton(Meta.Resources.OK));
	}

	private void createMembersPrivilegeList() {

		membersPrivilege = FXCollections.observableArrayList();
		members.forEach(member -> checkMemberPrivilege(member));
		membersTableView.setItems(membersPrivilege);
	}

	private void checkMemberPrivilege(Member member) {

		switch (this.privilege) {
		case CONGRBIBLESTUDY_READER_MIDWEEK:
			if (member.getSpInf26() == 1)
				addMember(member);
			break;
		case WATCHTOWER_READER:
			if (member.getSpInf27() == 1)
				addMember(member);
			break;
		case TALK_MIDWEEK:
			if (member.getSpInf30() == 1)
				addMember(member);
			break;
		case DIGGING_MIDWEEK:
			if (member.getSpInf31() == 1)
				addMember(member);
			break;
		case CHRISTIAN_LIFE:
			if (member.getSpInf32() == 1)
				addMember(member);
			break;
		case PRESIDENT_MIDWEEK:
			if (member.getSpInf33() == 1)
				addMember(member);
			break;
		case PRAY1_MIDWEEK:
			if (member.getSpInf34() == 1)
				addMember(member);
			break;
		case PRAY2_MIDWEEK:
			if (member.getSpInf35() == 1)
				addMember(member);
			break;
		case PRESIDENT_WEEKEND:
			if (member.getSpInf36() == 1)
				addMember(member);
			break;
		case PRAY_END_WEEKEND:
			if (member.getSpInf37() == 1)
				addMember(member);
			break;
		case CONGRBIBLESTUDY_MIDWEEK:
			if (member.getSpInf42() == 1)
				addMember(member);
			break;
		case CONDUCTOR_WATCHTOWER:
			if (member.getSpInf43() == 1 || member.getSpInf44() == 1)
				addMember(member);
			break;
		default:
			break;
		}
	}

	private void addMember(Member member) {
		this.membersPrivilege.add(new MemberHistory(member, privilege, selectedWeek));
	}

	public AnchorPane getLayout() {
		return layout;
	}

	public void setLayout(AnchorPane layout) {
		this.layout = layout;
	}

	public ObservableList<Member> getMembers() {
		return members;
	}

	public void setMembers(ObservableList<Member> members) {
		this.members = members;
	}

	public Privileges getPrivilege() {
		return privilege;
	}

	public void setPrivilege(Privileges privilege) {
		this.privilege = privilege;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public ObservableList<Week> getDatabaseWeeks() {
		return databaseWeeks;
	}

	public void setDatabaseWeeks(ObservableList<Week> databaseWeeks) {
		this.databaseWeeks = databaseWeeks;
	}

	public Week getSelectedWeek() {
		return selectedWeek;
	}

	public void setSelectedWeek(Week selectedWeek) {
		this.selectedWeek = selectedWeek;
	}

	public Week getEditorWeek() {
		return editorWeek;
	}

	public void setEditorWeek(Week editorWeek) {
		this.editorWeek = editorWeek;
	}

	public UserMenuMeetingsEditor getEditor() {
		return editor;
	}

	public void setEditor(UserMenuMeetingsEditor editor) {
		this.editor = editor;
	}

	public Stage getThisStage() {
		return thisStage;
	}

	public void setThisStage(Stage thisStage) {
		this.thisStage = thisStage;
	}
}