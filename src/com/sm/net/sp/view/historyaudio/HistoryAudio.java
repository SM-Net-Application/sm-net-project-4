package com.sm.net.sp.view.historyaudio;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Optional;
import java.util.stream.StreamSupport;

import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.model.ChristiansPart;
import com.sm.net.sp.model.Member;
import com.sm.net.sp.model.MemberAudioHistory;
import com.sm.net.sp.model.PrivilegeHistory;
import com.sm.net.sp.model.Privileges;
import com.sm.net.sp.model.Week;
import com.sm.net.sp.model.WeekAudio;
import com.sm.net.sp.view.SupportPlannerView;
import com.sm.net.sp.view.history.UpgradeableComboBoxSelection;

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

public class HistoryAudio {

	@FXML
	private AnchorPane layout;

	@FXML
	private Label titleLabel;
	@FXML
	private Label brotherLabel;

	@FXML
	private TableView<MemberAudioHistory> membersTableView;
	@FXML
	private TableColumn<MemberAudioHistory, String> memberNameTableColumn;
	@FXML
	private TableColumn<MemberAudioHistory, String> memberLastDateTableColumn;
	@FXML
	private TableColumn<MemberAudioHistory, ImageView> memberLastDateStatusTableColumn;

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
	private ObservableList<MemberAudioHistory> membersPrivilege;
	private ObservableList<PrivilegeHistory> memberPrivilegeHistory;
	private Privileges privilege;
	private ObservableList<Week> databaseWeeks;
	private ObservableList<WeekAudio> databaseWeeksAudio;
	private WeekAudio selectedWeek;
	private WeekAudio editorWeek;
	private UpgradeableComboBoxSelection editor;
	private Stage thisStage;

	private SupportPlannerView application;

	private HashMap<String, String> configs;

	@FXML
	private void initialize() {
		styleClasses();
		cellValueFactory();
	}

	private void styleClasses() {

		this.layout.getStyleClass().add("main_color_001");

		this.titleLabel.getStyleClass().add("label_002");
		this.brotherLabel.getStyleClass().add("label_002");

		this.membersTableView.getStyleClass().add("table_view_001");
		this.privilegesTableView.getStyleClass().add("table_view_001");

		this.selectButton.getStyleClass().add("button_image_001");
	}

	private void cellValueFactory() {

		this.memberNameTableColumn.setCellValueFactory(
				cellData -> new SimpleStringProperty(cellData.getValue().getMember().getNameStyle1()));
		this.memberLastDateTableColumn
				.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLastDateText()));
		this.memberLastDateStatusTableColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<ImageView>(
				Meta.Resources.imageForTab(cellData.getValue().getStatus())));

		this.privilegesDateTableColumn
				.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLastDateText()));
		this.privilegesStatusTableColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<ImageView>(
				Meta.Resources.imageForTab(cellData.getValue().getStatus())));
		this.privilegesNameTableColumn.setCellValueFactory(cellData -> {

			Privileges pr = cellData.getValue().getPrivilege();

			switch (pr) {
			case MIDWEEK_AUDIO_POS1:
			case WEEKEND_AUDIO_POS1:
				return new SimpleStringProperty(
						String.format(pr.getTranslatedName(language), this.configs.get("inf9")));
			case MIDWEEK_AUDIO_POS2:
			case WEEKEND_AUDIO_POS2:
				return new SimpleStringProperty(
						String.format(pr.getTranslatedName(language), this.configs.get("inf10")));
			case MIDWEEK_AUDIO_POS3:
			case WEEKEND_AUDIO_POS3:
				return new SimpleStringProperty(
						String.format(pr.getTranslatedName(language), this.configs.get("inf11")));
			default:
				return new SimpleStringProperty(pr.getTranslatedName(language));
			}
		});
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

			MemberAudioHistory member = this.membersTableView.getSelectionModel().getSelectedItem();

			Optional<PrivilegeHistory> find = StreamSupport.stream(this.memberPrivilegeHistory.spliterator(), false)
					.filter(ph -> ph.getStatus().equals(Meta.Resources.PRESENT)).findFirst();

			if (find.isPresent()) {

				PrivilegeHistory privilegeHistory = find.get();

				String header = String.format(this.language.getStringWithNewLine("sp.history.confirmselection"),
						member.getMember().getNameStyle1());

				Privileges pr = privilegeHistory.getPrivilege();
				String privilegeTranslatedName = pr.getTranslatedName(this.language);
				switch (pr) {
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

				String content = privilegeTranslatedName;
				if (this.application.getAlertBuilder().confirm(thisStage, header, content))
					selectionConfirmed(member);

			} else
				selectionConfirmed(member);
		}
	}

	private void selectionConfirmed(MemberAudioHistory member) {

		int memberID = member.getMember().getSpMemberID();

		this.editor.updateSelectedComboBox(this.privilege, memberID);

		this.thisStage.close();
	}

	private void listenerMembersTableView() {

		this.membersTableView.getSelectionModel().selectedIndexProperty().addListener((ob, o, n) -> changeMember(n));
		this.membersTableView.setRowFactory(param -> {
			TableRow<MemberAudioHistory> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (!row.isEmpty()))
					selectMember();
			});
			return row;
		});
	}

	private void changeMember(Number n) {
		if (n.intValue() > -1) {
			MemberAudioHistory member = this.membersTableView.getSelectionModel().getSelectedItem();
			this.brotherLabel.setText(String.format(language.getString("sp.history.brotherselected"),
					member.getMember().getNameStyle1()));

			checkPrivilegeMember(member);

		} else
			this.brotherLabel.setText(language.getString("sp.history.select"));
	}

	private void checkPrivilegeMember(MemberAudioHistory member) {

		this.memberPrivilegeHistory = FXCollections.observableArrayList();

		if (this.selectedWeek.spInf1Property() != null) {

			int memberID = member.getMember().getSpMemberID();
			checkPrivilegeMemberDatabaseWeek(memberID);
			checkPrivilegeMemberWeekAudio(memberID, this.editorWeek);

		} else
			this.brotherLabel.setText(language.getString("sp.history.weeknotsaved"));

		this.memberPrivilegeHistory.sort((o1, o2) -> o1.getLastDate().compareTo(o2.getLastDate()));
		this.privilegesTableView.setItems(memberPrivilegeHistory);
	}

	private void checkPrivilegeMemberDatabaseWeek(int memberID) {

		for (Week week : databaseWeeks)
			if (week.getSpInf1() != this.selectedWeek.getSpInf1())
				checkPrivilegeMemberWeek(memberID, week);

		for (WeekAudio week : databaseWeeksAudio)
			if (week.getSpInf1() != this.selectedWeek.getSpInf1())
				checkPrivilegeMemberWeekAudio(memberID, week);
	}

	private void checkPrivilegeMemberWeekAudio(int memberID, WeekAudio week) {

		if (memberID == week.getSpInf2())
			checkPrivilegeAudio(Privileges.MIDWEEK_AUDIO_POS1, week);

		if (memberID == week.getSpInf3())
			checkPrivilegeAudio(Privileges.MIDWEEK_AUDIO_POS2, week);

		if (memberID == week.getSpInf4())
			checkPrivilegeAudio(Privileges.MIDWEEK_AUDIO_POS3, week);

		if (memberID == week.getSpInf5() || memberID == week.getSpInf6() || memberID == week.getSpInf7()) {
			if (memberID == week.getSpInf5()) {
				checkPrivilegeAudio(Privileges.MIDWEEK_AUDIO_MIC1, week);
			} else if (memberID == week.getSpInf6()) {
				checkPrivilegeAudio(Privileges.MIDWEEK_AUDIO_MIC2, week);
			} else if (memberID == week.getSpInf7()) {
				checkPrivilegeAudio(Privileges.MIDWEEK_AUDIO_MIC3, week);
			}
		}

		if (memberID == week.getSpInf8())
			checkPrivilegeAudio(Privileges.WEEKEND_AUDIO_POS1, week);

		if (memberID == week.getSpInf9())
			checkPrivilegeAudio(Privileges.WEEKEND_AUDIO_POS2, week);

		if (memberID == week.getSpInf10())
			checkPrivilegeAudio(Privileges.WEEKEND_AUDIO_POS3, week);

		if (memberID == week.getSpInf11() || memberID == week.getSpInf12() || memberID == week.getSpInf13()) {
			if (memberID == week.getSpInf11()) {
				checkPrivilegeAudio(Privileges.WEEKEND_AUDIO_MIC1, week);
			} else if (memberID == week.getSpInf12()) {
				checkPrivilegeAudio(Privileges.WEEKEND_AUDIO_MIC1, week);
			} else if (memberID == week.getSpInf13()) {
				checkPrivilegeAudio(Privileges.WEEKEND_AUDIO_MIC1, week);
			}
		}
	}

	private void checkPrivilegeMemberWeek(int memberID, Week week) {

		if (memberID == week.getSpInf3())
			checkPrivilege(Privileges.MIDWEEK_PRESIDENT, week);

		if (memberID == week.getSpInf4() || memberID == week.getSpInf27() || memberID == week.getSpInf40()) {

			if (memberID == week.getSpInf4())
				checkPrivilege(Privileges.MIDWEEK_PRAY_START, week);
			else if (memberID == week.getSpInf27())
				checkPrivilege(Privileges.MIDWEEK_PRAY_END, week);
			else if (memberID == week.getSpInf40())
				checkPrivilege(Privileges.WEEKEND_PRAY_END, week);
		}

		if (memberID == week.getSpInf29() || memberID == week.getSpInf38()) {

			if (memberID == week.getSpInf29())
				checkPrivilege(Privileges.MIDWEEK_CONGRBIBLESTUDY_READER, week);
			else if (memberID == week.getSpInf38())
				checkPrivilege(Privileges.WEEKEND_WATCHTOWER_READER, week);
		}

		if (memberID == week.getSpInf30())
			checkPrivilege(Privileges.WEEKEND_PRESIDENT, week);

		if (memberID == week.getSpInf37())
			checkPrivilege(Privileges.WEEKEND_WATCHTOWER, week);

		if (memberID == week.getSpInf11())
			checkPrivilege(Privileges.MIDWEEK_TALK, week);

		if (memberID == week.getSpInf14())
			checkPrivilege(Privileges.MIDWEEK_DIGGING, week);

		if (memberID == week.getSpInf23())
			checkPrivilege(Privileges.MIDWEEK_CONGRBIBLESTUDY, week);

		for (ChristiansPart cp : week.getChristiansPartList())
			if (memberID == cp.getTeacher().getSpMemberID()) {
				checkPrivilege(Privileges.MIDWEEK_CHRISTIAN_LIFE, week);
				break;
			}
	}

	private void checkPrivilegeAudio(Privileges privilege, WeekAudio week) {

		PrivilegeHistory checkPrivilegeHistory = privilegeHistoryContains(privilege);
		if (checkPrivilegeHistory != null)
			checkPrivilegeHistory.checkLastDate(week.getSpInf1(), selectedWeek.getSpInf1());
		else
			this.memberPrivilegeHistory
					.add(new PrivilegeHistory(privilege, week.getSpInf1(), selectedWeek.getSpInf1()));
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

		this.membersPrivilege.forEach(mh -> mh.checkLastDate(this.databaseWeeksAudio));
		this.membersPrivilege.forEach(mh -> mh.checkEditorLastDate(this.editorWeek));

		this.membersPrivilege.sort(new Comparator<MemberAudioHistory>() {

			@Override
			public int compare(MemberAudioHistory o1, MemberAudioHistory o2) {

				LocalDate lastDate = o1.getLastDate();
				if (lastDate == null)
					return -1;

				LocalDate lastDate2 = o2.getLastDate();
				if (lastDate2 == null)
					return 1;

				return lastDate.compareTo(lastDate2);
			}
		});

		this.membersTableView.refresh();
	}

	private void viewUpdate() {

		String privilegeTranslatedName = this.privilege.getTranslatedName(language);
		switch (this.privilege) {
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

		titleLabel.setText(privilegeTranslatedName);
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

		this.membersPrivilege = FXCollections.observableArrayList();
		this.members.forEach(member -> checkMemberPrivilege(member));
		this.membersTableView.setItems(this.membersPrivilege);
	}

	private void checkMemberPrivilege(Member member) {

		switch (this.privilege) {
		case MIDWEEK_AUDIO_MIC1:
		case MIDWEEK_AUDIO_MIC2:
		case MIDWEEK_AUDIO_MIC3:
			if (member.getSpInf20() == 1)
				addMember(member);
			break;
		case WEEKEND_AUDIO_MIC1:
		case WEEKEND_AUDIO_MIC2:
		case WEEKEND_AUDIO_MIC3:
			if (member.getSpInf21() == 1)
				addMember(member);
			break;
		case MIDWEEK_AUDIO_POS1:
			if (member.getSpInf22() == 1)
				addMember(member);
			break;
		case WEEKEND_AUDIO_POS1:
			if (member.getSpInf23() == 1)
				addMember(member);
			break;
		case MIDWEEK_AUDIO_POS2:
			if (member.getSpInf24() == 1)
				addMember(member);
			break;
		case WEEKEND_AUDIO_POS2:
			if (member.getSpInf25() == 1)
				addMember(member);
			break;
		case MIDWEEK_AUDIO_POS3:
			if (member.getSpInf54() == 1)
				addMember(member);
			break;
		case WEEKEND_AUDIO_POS3:
			if (member.getSpInf55() == 1)
				addMember(member);
			break;
		default:
			break;
		}
	}

	private void addMember(Member member) {
		this.membersPrivilege.add(new MemberAudioHistory(member, privilege, selectedWeek));
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

	public WeekAudio getSelectedWeek() {
		return selectedWeek;
	}

	public void setSelectedWeek(WeekAudio selectedWeek) {
		this.selectedWeek = selectedWeek;
	}

	public WeekAudio getEditorWeek() {
		return editorWeek;
	}

	public void setEditorWeek(WeekAudio editorWeek) {
		this.editorWeek = editorWeek;
	}

	public UpgradeableComboBoxSelection getEditor() {
		return editor;
	}

	public void setEditor(UpgradeableComboBoxSelection editor) {
		this.editor = editor;
	}

	public Stage getThisStage() {
		return thisStage;
	}

	public void setThisStage(Stage thisStage) {
		this.thisStage = thisStage;
	}

	public SupportPlannerView getApplication() {
		return application;
	}

	public void setApplication(SupportPlannerView application) {
		this.application = application;
	}

	public HashMap<String, String> getConfigs() {
		return configs;
	}

	public void setConfigs(HashMap<String, String> configs) {
		this.configs = configs;
	}

	public ObservableList<WeekAudio> getDatabaseWeeksAudio() {
		return databaseWeeksAudio;
	}

	public void setDatabaseWeeksAudio(ObservableList<WeekAudio> databaseWeeksAudio) {
		this.databaseWeeksAudio = databaseWeeksAudio;
	}
}