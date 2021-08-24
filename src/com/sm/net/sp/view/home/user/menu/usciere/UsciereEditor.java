package com.sm.net.sp.view.home.user.menu.usciere;

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
import com.sm.net.sp.model.WeekUsciere;
import com.sm.net.sp.settings.Settings;
import com.sm.net.sp.view.SupportPlannerView;
import com.sm.net.sp.view.history.UpgradeableComboBoxSelection;
import com.sm.net.sp.view.historyusciere.HistoryUsciere;
import com.sm.net.sp.view.home.user.menu.usciere.task.WeekUsciereSaveTask;
import com.sm.net.util.Crypt;
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

public class UsciereEditor extends UpdateDataAdapter implements UpgradeableComboBoxSelection {

	@FXML
	private Button saveButton;

	@FXML
	private Label midweekLabel;
	@FXML
	private Label weekendLabel;

	@FXML
	private Label z1MidweekLabel;
	@FXML
	private Label z2MidweekLabel;
	@FXML
	private Label z3MidweekLabel;
	@FXML
	private Label z1WeekendLabel;
	@FXML
	private Label z2WeekendLabel;
	@FXML
	private Label z3WeekendLabel;

	@FXML
	private ComboBox<Member> z1u1MidweekComboBox;
	@FXML
	private ComboBox<Member> z1u2MidweekComboBox;
	@FXML
	private ComboBox<Member> z1u3MidweekComboBox;
	@FXML
	private ComboBox<Member> z2u1MidweekComboBox;
	@FXML
	private ComboBox<Member> z2u2MidweekComboBox;
	@FXML
	private ComboBox<Member> z2u3MidweekComboBox;
	@FXML
	private ComboBox<Member> z3u1MidweekComboBox;
	@FXML
	private ComboBox<Member> z3u2MidweekComboBox;
	@FXML
	private ComboBox<Member> z3u3MidweekComboBox;
	@FXML
	private ComboBox<Member> z1u1WeekendComboBox;
	@FXML
	private ComboBox<Member> z1u2WeekendComboBox;
	@FXML
	private ComboBox<Member> z1u3WeekendComboBox;
	@FXML
	private ComboBox<Member> z2u1WeekendComboBox;
	@FXML
	private ComboBox<Member> z2u2WeekendComboBox;
	@FXML
	private ComboBox<Member> z2u3WeekendComboBox;
	@FXML
	private ComboBox<Member> z3u1WeekendComboBox;
	@FXML
	private ComboBox<Member> z3u2WeekendComboBox;
	@FXML
	private ComboBox<Member> z3u3WeekendComboBox;

	private Settings settings;
	private Language language;
	private Stage ownerStage;

	private ObservableList<Member> membersList;

	private ObservableList<Member> z1MidweekList;
	private ObservableList<Member> z2MidweekList;
	private ObservableList<Member> z3MidweekList;
	private ObservableList<Member> z1WeekendList;
	private ObservableList<Member> z2WeekendList;
	private ObservableList<Member> z3WeekendList;

	private Usciere ownerCtrl;
	private WeekUsciere selectedWeek;

	private TabPane ownerTabPane;
	private Tab thisTab;

	private ObservableList<WeekUsciere> calendar;
	private HashMap<String, String> configs;

	private SupportPlannerView application;

	private ObservableList<Week> databaseWeeks;
	private ObservableList<WeekUsciere> databaseWeeksUsciere;
	private ObservableList<WeekAudio> databaseWeeksAudio;

	private boolean activeListenerComboBox;
	private String inf15;
	private String inf16;
	private String inf17;

	@FXML
	private void initialize() {
		styleClasses();
	}

	private void styleClasses() {

		this.midweekLabel.getStyleClass().add("label_002");
		this.weekendLabel.getStyleClass().add("label_002");

		this.z1MidweekLabel.getStyleClass().add("label_002");
		this.z2MidweekLabel.getStyleClass().add("label_002");
		this.z3MidweekLabel.getStyleClass().add("label_002");
		this.z1WeekendLabel.getStyleClass().add("label_002");
		this.z2WeekendLabel.getStyleClass().add("label_002");
		this.z3WeekendLabel.getStyleClass().add("label_002");

		this.z1u1MidweekComboBox.getStyleClass().add("combo_box_001");
		this.z1u2MidweekComboBox.getStyleClass().add("combo_box_001");
		this.z1u3MidweekComboBox.getStyleClass().add("combo_box_001");
		this.z2u1MidweekComboBox.getStyleClass().add("combo_box_001");
		this.z2u2MidweekComboBox.getStyleClass().add("combo_box_001");
		this.z2u3MidweekComboBox.getStyleClass().add("combo_box_001");
		this.z3u1MidweekComboBox.getStyleClass().add("combo_box_001");
		this.z3u2MidweekComboBox.getStyleClass().add("combo_box_001");
		this.z3u3MidweekComboBox.getStyleClass().add("combo_box_001");

		this.z1u1WeekendComboBox.getStyleClass().add("combo_box_001");
		this.z1u2WeekendComboBox.getStyleClass().add("combo_box_001");
		this.z1u3WeekendComboBox.getStyleClass().add("combo_box_001");
		this.z2u1WeekendComboBox.getStyleClass().add("combo_box_001");
		this.z2u2WeekendComboBox.getStyleClass().add("combo_box_001");
		this.z2u3WeekendComboBox.getStyleClass().add("combo_box_001");
		this.z3u1WeekendComboBox.getStyleClass().add("combo_box_001");
		this.z3u2WeekendComboBox.getStyleClass().add("combo_box_001");
		this.z3u3WeekendComboBox.getStyleClass().add("combo_box_001");

		this.saveButton.getStyleClass().add("button_image_001");
	}

	private void viewUpdate() {

		this.language = settings.getLanguage();

		this.midweekLabel.setText(this.language.getString("usciereeditor.midweek"));
		this.weekendLabel.setText(this.language.getString("usciereeditor.weekend"));

		String noconfig = this.language.getString("usciereeditor.noconfig");
		String z1 = this.configs.get("inf12");
		String z2 = this.configs.get("inf13");
		String z3 = this.configs.get("inf14");

		if (z1 == null || z1.isEmpty()) {
			this.z1MidweekLabel.setText(noconfig);
			this.z1WeekendLabel.setText(noconfig);
		} else {
			this.z1MidweekLabel.setText(z1);
			this.z1WeekendLabel.setText(z1);
		}

		if (z2 == null || z2.isEmpty()) {
			this.z2MidweekLabel.setText(noconfig);
			this.z2WeekendLabel.setText(noconfig);
		} else {
			this.z2MidweekLabel.setText(z2);
			this.z2WeekendLabel.setText(z2);
		}

		if (z3 == null || z3.isEmpty()) {
			this.z3MidweekLabel.setText(noconfig);
			this.z3WeekendLabel.setText(noconfig);
		} else {
			this.z3MidweekLabel.setText(z3);
			this.z3WeekendLabel.setText(z3);
		}

		Tooltip saveButtonTooltip = new Tooltip(this.language.getString("audioeditor.tooltip.button.save"));
		saveButtonTooltip.getStyleClass().add("tooltip_001");
		this.saveButton.setTooltip(saveButtonTooltip);
		this.saveButton.setText("");
		this.saveButton.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.SAVE));
	}

	public void objectInitialize() {

		this.activeListenerComboBox = false;

		this.inf15 = Crypt.decrypt(this.configs.get("inf15"), this.application.getSettings().getDatabaseSecretKey());
		this.inf16 = Crypt.decrypt(this.configs.get("inf16"), this.application.getSettings().getDatabaseSecretKey());
		this.inf17 = Crypt.decrypt(this.configs.get("inf17"), this.application.getSettings().getDatabaseSecretKey());

		viewUpdate();
		contextMenu();
		initData();
		listeners();

		this.activeListenerComboBox = true;
	}

	private void contextMenu() {

		this.z1u1MidweekComboBox.setContextMenu(createPrivilegeRegisterContextMenu(Privileges.MIDWEEK_USCIERE1_Z1));
		this.z1u1MidweekComboBox.addEventFilter(MouseEvent.MOUSE_RELEASED, event -> disableMouseSecondary(event));

		this.z1u2MidweekComboBox.setContextMenu(createPrivilegeRegisterContextMenu(Privileges.MIDWEEK_USCIERE2_Z1));
		this.z1u2MidweekComboBox.addEventFilter(MouseEvent.MOUSE_RELEASED, event -> disableMouseSecondary(event));

		this.z1u3MidweekComboBox.setContextMenu(createPrivilegeRegisterContextMenu(Privileges.MIDWEEK_USCIERE3_Z1));
		this.z1u3MidweekComboBox.addEventFilter(MouseEvent.MOUSE_RELEASED, event -> disableMouseSecondary(event));

		this.z2u1MidweekComboBox.setContextMenu(createPrivilegeRegisterContextMenu(Privileges.MIDWEEK_USCIERE1_Z2));
		this.z2u1MidweekComboBox.addEventFilter(MouseEvent.MOUSE_RELEASED, event -> disableMouseSecondary(event));

		this.z2u2MidweekComboBox.setContextMenu(createPrivilegeRegisterContextMenu(Privileges.MIDWEEK_USCIERE2_Z2));
		this.z2u2MidweekComboBox.addEventFilter(MouseEvent.MOUSE_RELEASED, event -> disableMouseSecondary(event));

		this.z2u3MidweekComboBox.setContextMenu(createPrivilegeRegisterContextMenu(Privileges.MIDWEEK_USCIERE3_Z2));
		this.z2u3MidweekComboBox.addEventFilter(MouseEvent.MOUSE_RELEASED, event -> disableMouseSecondary(event));

		this.z3u1MidweekComboBox.setContextMenu(createPrivilegeRegisterContextMenu(Privileges.MIDWEEK_USCIERE1_Z3));
		this.z3u1MidweekComboBox.addEventFilter(MouseEvent.MOUSE_RELEASED, event -> disableMouseSecondary(event));

		this.z3u2MidweekComboBox.setContextMenu(createPrivilegeRegisterContextMenu(Privileges.MIDWEEK_USCIERE2_Z3));
		this.z3u2MidweekComboBox.addEventFilter(MouseEvent.MOUSE_RELEASED, event -> disableMouseSecondary(event));

		this.z3u3MidweekComboBox.setContextMenu(createPrivilegeRegisterContextMenu(Privileges.MIDWEEK_USCIERE3_Z3));
		this.z3u3MidweekComboBox.addEventFilter(MouseEvent.MOUSE_RELEASED, event -> disableMouseSecondary(event));

		this.z1u1WeekendComboBox.setContextMenu(createPrivilegeRegisterContextMenu(Privileges.WEEKEND_USCIERE1_Z1));
		this.z1u1WeekendComboBox.addEventFilter(MouseEvent.MOUSE_RELEASED, event -> disableMouseSecondary(event));

		this.z1u2WeekendComboBox.setContextMenu(createPrivilegeRegisterContextMenu(Privileges.WEEKEND_USCIERE2_Z1));
		this.z1u2WeekendComboBox.addEventFilter(MouseEvent.MOUSE_RELEASED, event -> disableMouseSecondary(event));

		this.z1u3WeekendComboBox.setContextMenu(createPrivilegeRegisterContextMenu(Privileges.WEEKEND_USCIERE3_Z1));
		this.z1u3WeekendComboBox.addEventFilter(MouseEvent.MOUSE_RELEASED, event -> disableMouseSecondary(event));

		this.z2u1WeekendComboBox.setContextMenu(createPrivilegeRegisterContextMenu(Privileges.WEEKEND_USCIERE1_Z2));
		this.z2u1WeekendComboBox.addEventFilter(MouseEvent.MOUSE_RELEASED, event -> disableMouseSecondary(event));

		this.z2u2WeekendComboBox.setContextMenu(createPrivilegeRegisterContextMenu(Privileges.WEEKEND_USCIERE2_Z2));
		this.z2u2WeekendComboBox.addEventFilter(MouseEvent.MOUSE_RELEASED, event -> disableMouseSecondary(event));

		this.z2u3WeekendComboBox.setContextMenu(createPrivilegeRegisterContextMenu(Privileges.WEEKEND_USCIERE3_Z2));
		this.z2u3WeekendComboBox.addEventFilter(MouseEvent.MOUSE_RELEASED, event -> disableMouseSecondary(event));

		this.z3u1WeekendComboBox.setContextMenu(createPrivilegeRegisterContextMenu(Privileges.WEEKEND_USCIERE1_Z3));
		this.z3u1WeekendComboBox.addEventFilter(MouseEvent.MOUSE_RELEASED, event -> disableMouseSecondary(event));

		this.z3u2WeekendComboBox.setContextMenu(createPrivilegeRegisterContextMenu(Privileges.WEEKEND_USCIERE2_Z3));
		this.z3u2WeekendComboBox.addEventFilter(MouseEvent.MOUSE_RELEASED, event -> disableMouseSecondary(event));

		this.z3u3WeekendComboBox.setContextMenu(createPrivilegeRegisterContextMenu(Privileges.WEEKEND_USCIERE3_Z3));
		this.z3u3WeekendComboBox.addEventFilter(MouseEvent.MOUSE_RELEASED, event -> disableMouseSecondary(event));
	}

	private void disableMouseSecondary(MouseEvent event) {
		if (event.getButton() == MouseButton.SECONDARY)
			event.consume();
	}

	private ContextMenu createPrivilegeRegisterContextMenu(Privileges privilege) {

		String privilegeTranslatedName = privilege.getTranslatedName(this.language);

		switch (privilege) {
		case MIDWEEK_USCIERE1_Z1:
		case MIDWEEK_USCIERE2_Z1:
		case MIDWEEK_USCIERE3_Z1:
		case WEEKEND_USCIERE1_Z1:
		case WEEKEND_USCIERE2_Z1:
		case WEEKEND_USCIERE3_Z1:
			privilegeTranslatedName = String.format(privilegeTranslatedName, this.configs.get("inf12"));
			break;
		case MIDWEEK_USCIERE1_Z2:
		case MIDWEEK_USCIERE2_Z2:
		case MIDWEEK_USCIERE3_Z2:
		case WEEKEND_USCIERE1_Z2:
		case WEEKEND_USCIERE2_Z2:
		case WEEKEND_USCIERE3_Z2:
			privilegeTranslatedName = String.format(privilegeTranslatedName, this.configs.get("inf13"));
			break;
		case MIDWEEK_USCIERE1_Z3:
		case MIDWEEK_USCIERE2_Z3:
		case MIDWEEK_USCIERE3_Z3:
		case WEEKEND_USCIERE1_Z3:
		case WEEKEND_USCIERE2_Z3:
		case WEEKEND_USCIERE3_Z3:
			privilegeTranslatedName = String.format(privilegeTranslatedName, this.configs.get("inf14"));
			break;
		default:
			break;
		}

		String menuItemText = String.format(language.getString("sp.meetings.history"), privilegeTranslatedName);

		String historyTitle = String.format(language.getString("sp.history.title"), menuItemText,
				this.selectedWeek.getFrom().toString(), this.selectedWeek.getWeek());

		StackPane graphic = Meta.Resources.imageInStackPaneForMenu(Meta.Resources.SEARCH);

		MenuItem menuItem = new MenuItem(menuItemText, graphic);
		menuItem.getStyleClass().add("menu_item_001");
		menuItem.setOnAction(event -> openHistory(privilege, historyTitle));

		ContextMenu contextMenu = new ContextMenu(menuItem);

		return contextMenu;
	}

	private void openHistory(Privileges privilege, String title) {

		if (this.selectedWeek.spInf1Property() != null) {

			try {

				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(Meta.Views.HISTORYUSCIERE);
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

				HistoryUsciere ctrl = (HistoryUsciere) fxmlLoader.getController();
				ctrl.setLayout(layout);
				ctrl.setPrivilege(privilege);
				ctrl.setMembers(this.membersList);
				ctrl.setLanguage(this.language);
				ctrl.setDatabaseWeeks(this.databaseWeeks);
				ctrl.setDatabaseWeeksAudio(this.databaseWeeksAudio);
				ctrl.setDatabaseWeeksUsciere(this.databaseWeeksUsciere);
				ctrl.setSelectedWeek(this.selectedWeek);
				ctrl.setEditorWeek(WeekUsciere.buildEditorWeek(this));
				ctrl.setEditor(this);
				ctrl.setThisStage(stage);
				ctrl.setApplication(this.application);
				ctrl.setConfigs(this.configs);

				ctrl.objectInitialize();

				stage.show();

			} catch (IOException e) {
				e.printStackTrace();
			}

		} else {

			this.application.getAlertBuilder2().error(this.ownerStage,
					this.language.getString("sp.history.weeknotsaved"));
		}
	}

	private void initData() {

		initFields();
		loadSelectedWeek();
	}

	private void initFields() {

		initMembers();
	}

	private void initMembers() {

		this.z1MidweekList = FXCollections.observableArrayList();
		this.z2MidweekList = FXCollections.observableArrayList();
		this.z3MidweekList = FXCollections.observableArrayList();

		this.z1WeekendList = FXCollections.observableArrayList();
		this.z2WeekendList = FXCollections.observableArrayList();
		this.z3WeekendList = FXCollections.observableArrayList();

		for (Member m : this.membersList) {
			if (m.getSpInf28() == 1)
				this.z1MidweekList.add(m);
			if (m.getSpInf29() == 1)
				this.z1WeekendList.add(m);
			if (m.getSpInf56() == 1)
				this.z2MidweekList.add(m);
			if (m.getSpInf57() == 1)
				this.z2WeekendList.add(m);
			if (m.getSpInf58() == 1)
				this.z3MidweekList.add(m);
			if (m.getSpInf59() == 1)
				this.z3WeekendList.add(m);
		}

		this.z1MidweekList.add(0, Member.emptyMember(this.language));
		this.z2MidweekList.add(0, Member.emptyMember(this.language));
		this.z3MidweekList.add(0, Member.emptyMember(this.language));
		this.z1WeekendList.add(0, Member.emptyMember(this.language));
		this.z2WeekendList.add(0, Member.emptyMember(this.language));
		this.z3WeekendList.add(0, Member.emptyMember(this.language));

		this.z1u1MidweekComboBox.setItems(this.z1MidweekList);
		this.z1u2MidweekComboBox.setItems(this.z1MidweekList);
		this.z1u3MidweekComboBox.setItems(this.z1MidweekList);
		this.z2u1MidweekComboBox.setItems(this.z2MidweekList);
		this.z2u2MidweekComboBox.setItems(this.z2MidweekList);
		this.z2u3MidweekComboBox.setItems(this.z2MidweekList);
		this.z3u1MidweekComboBox.setItems(this.z3MidweekList);
		this.z3u2MidweekComboBox.setItems(this.z3MidweekList);
		this.z3u3MidweekComboBox.setItems(this.z3MidweekList);
		this.z1u1WeekendComboBox.setItems(this.z1WeekendList);
		this.z1u2WeekendComboBox.setItems(this.z1WeekendList);
		this.z1u3WeekendComboBox.setItems(this.z1WeekendList);
		this.z2u1WeekendComboBox.setItems(this.z2WeekendList);
		this.z2u2WeekendComboBox.setItems(this.z2WeekendList);
		this.z2u3WeekendComboBox.setItems(this.z2WeekendList);
		this.z3u1WeekendComboBox.setItems(this.z3WeekendList);
		this.z3u2WeekendComboBox.setItems(this.z3WeekendList);
		this.z3u3WeekendComboBox.setItems(this.z3WeekendList);

		this.z1u1MidweekComboBox.getSelectionModel().selectFirst();
		this.z1u2MidweekComboBox.getSelectionModel().selectFirst();
		this.z1u3MidweekComboBox.getSelectionModel().selectFirst();
		this.z2u1MidweekComboBox.getSelectionModel().selectFirst();
		this.z2u2MidweekComboBox.getSelectionModel().selectFirst();
		this.z2u3MidweekComboBox.getSelectionModel().selectFirst();
		this.z3u1MidweekComboBox.getSelectionModel().selectFirst();
		this.z3u2MidweekComboBox.getSelectionModel().selectFirst();
		this.z3u3MidweekComboBox.getSelectionModel().selectFirst();
		this.z1u1WeekendComboBox.getSelectionModel().selectFirst();
		this.z1u2WeekendComboBox.getSelectionModel().selectFirst();
		this.z1u3WeekendComboBox.getSelectionModel().selectFirst();
		this.z2u1WeekendComboBox.getSelectionModel().selectFirst();
		this.z2u2WeekendComboBox.getSelectionModel().selectFirst();
		this.z2u3WeekendComboBox.getSelectionModel().selectFirst();
		this.z3u1WeekendComboBox.getSelectionModel().selectFirst();
		this.z3u2WeekendComboBox.getSelectionModel().selectFirst();
		this.z3u3WeekendComboBox.getSelectionModel().selectFirst();
	}

	private void loadSelectedWeek() {

		if (this.selectedWeek != null)
			if (this.selectedWeek.spUscIDProperty() != null) {

				setComboBoxMember(this.z1u1MidweekComboBox, this.selectedWeek.getSpInf2());
				setComboBoxMember(this.z1u2MidweekComboBox, this.selectedWeek.getSpInf3());
				setComboBoxMember(this.z1u3MidweekComboBox, this.selectedWeek.getSpInf4());
				setComboBoxMember(this.z2u1MidweekComboBox, this.selectedWeek.getSpInf5());
				setComboBoxMember(this.z2u2MidweekComboBox, this.selectedWeek.getSpInf6());
				setComboBoxMember(this.z2u3MidweekComboBox, this.selectedWeek.getSpInf7());
				setComboBoxMember(this.z3u1MidweekComboBox, this.selectedWeek.getSpInf8());
				setComboBoxMember(this.z3u2MidweekComboBox, this.selectedWeek.getSpInf9());
				setComboBoxMember(this.z3u3MidweekComboBox, this.selectedWeek.getSpInf10());

				setComboBoxMember(this.z1u1WeekendComboBox, this.selectedWeek.getSpInf11());
				setComboBoxMember(this.z1u2WeekendComboBox, this.selectedWeek.getSpInf12());
				setComboBoxMember(this.z1u3WeekendComboBox, this.selectedWeek.getSpInf13());
				setComboBoxMember(this.z2u1WeekendComboBox, this.selectedWeek.getSpInf14());
				setComboBoxMember(this.z2u2WeekendComboBox, this.selectedWeek.getSpInf15());
				setComboBoxMember(this.z2u3WeekendComboBox, this.selectedWeek.getSpInf16());
				setComboBoxMember(this.z3u1WeekendComboBox, this.selectedWeek.getSpInf17());
				setComboBoxMember(this.z3u2WeekendComboBox, this.selectedWeek.getSpInf18());
				setComboBoxMember(this.z3u3WeekendComboBox, this.selectedWeek.getSpInf19());
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

		// USCIERI ZONA 1 INFRASETTIMANALE

		this.z1u1MidweekComboBox.getSelectionModel().selectedIndexProperty()
				.addListener((obs, oldV, newV) -> checkComboBox(this.z1u1MidweekComboBox, this.z1u1WeekendComboBox,
						(this.inf15.equals("1")), newV));

		this.z1u2MidweekComboBox.getSelectionModel().selectedIndexProperty()
				.addListener((obs, oldV, newV) -> checkComboBox(this.z1u2MidweekComboBox, this.z1u2WeekendComboBox,
						(this.inf15.equals("1")), newV));

		this.z1u3MidweekComboBox.getSelectionModel().selectedIndexProperty()
				.addListener((obs, oldV, newV) -> checkComboBox(this.z1u3MidweekComboBox, this.z1u3WeekendComboBox,
						(this.inf15.equals("1")), newV));

		// USCIERI ZONA 2 INFRASETTIMANALE

		this.z2u1MidweekComboBox.getSelectionModel().selectedIndexProperty()
				.addListener((obs, oldV, newV) -> checkComboBox(this.z2u1MidweekComboBox, this.z2u1WeekendComboBox,
						(this.inf16.equals("1")), newV));

		this.z2u2MidweekComboBox.getSelectionModel().selectedIndexProperty()
				.addListener((obs, oldV, newV) -> checkComboBox(this.z2u2MidweekComboBox, this.z2u2WeekendComboBox,
						(this.inf16.equals("1")), newV));

		this.z2u3MidweekComboBox.getSelectionModel().selectedIndexProperty()
				.addListener((obs, oldV, newV) -> checkComboBox(this.z2u3MidweekComboBox, this.z2u3WeekendComboBox,
						(this.inf16.equals("1")), newV));

		// USCIERI ZONA 3 INFRASETTIMANALE

		this.z3u1MidweekComboBox.getSelectionModel().selectedIndexProperty()
				.addListener((obs, oldV, newV) -> checkComboBox(this.z3u1MidweekComboBox, this.z3u1WeekendComboBox,
						(this.inf17.equals("1")), newV));

		this.z3u2MidweekComboBox.getSelectionModel().selectedIndexProperty()
				.addListener((obs, oldV, newV) -> checkComboBox(this.z3u2MidweekComboBox, this.z3u2WeekendComboBox,
						(this.inf17.equals("1")), newV));

		this.z3u3MidweekComboBox.getSelectionModel().selectedIndexProperty()
				.addListener((obs, oldV, newV) -> checkComboBox(this.z3u3MidweekComboBox, this.z3u3WeekendComboBox,
						(this.inf17.equals("1")), newV));

		// USCIERI ZONA 1 FINE SETTIMANA

		this.z1u1WeekendComboBox.getSelectionModel().selectedIndexProperty()
				.addListener((obs, oldV, newV) -> checkComboBox(this.z1u1WeekendComboBox, this.z1u1MidweekComboBox,
						(this.inf15.equals("1")), newV));

		this.z1u2WeekendComboBox.getSelectionModel().selectedIndexProperty()
				.addListener((obs, oldV, newV) -> checkComboBox(this.z1u2WeekendComboBox, this.z1u2MidweekComboBox,
						(this.inf15.equals("1")), newV));

		this.z1u3WeekendComboBox.getSelectionModel().selectedIndexProperty()
				.addListener((obs, oldV, newV) -> checkComboBox(this.z1u3WeekendComboBox, this.z1u3MidweekComboBox,
						(this.inf15.equals("1")), newV));

		// USCIERI ZONA 2 FINE SETTIMANA

		this.z2u1WeekendComboBox.getSelectionModel().selectedIndexProperty()
				.addListener((obs, oldV, newV) -> checkComboBox(this.z2u1WeekendComboBox, this.z2u1MidweekComboBox,
						(this.inf16.equals("1")), newV));

		this.z2u2WeekendComboBox.getSelectionModel().selectedIndexProperty()
				.addListener((obs, oldV, newV) -> checkComboBox(this.z2u2WeekendComboBox, this.z2u2MidweekComboBox,
						(this.inf16.equals("1")), newV));

		this.z2u3WeekendComboBox.getSelectionModel().selectedIndexProperty()
				.addListener((obs, oldV, newV) -> checkComboBox(this.z2u3WeekendComboBox, this.z2u3MidweekComboBox,
						(this.inf16.equals("1")), newV));

		// USCIERI ZONA 3 FINE SETTIMANA

		this.z3u1WeekendComboBox.getSelectionModel().selectedIndexProperty()
				.addListener((obs, oldV, newV) -> checkComboBox(this.z3u1WeekendComboBox, this.z3u1MidweekComboBox,
						(this.inf17.equals("1")), newV));

		this.z3u2WeekendComboBox.getSelectionModel().selectedIndexProperty()
				.addListener((obs, oldV, newV) -> checkComboBox(this.z3u2WeekendComboBox, this.z3u2MidweekComboBox,
						(this.inf17.equals("1")), newV));

		this.z3u3WeekendComboBox.getSelectionModel().selectedIndexProperty()
				.addListener((obs, oldV, newV) -> checkComboBox(this.z3u3WeekendComboBox, this.z3u3MidweekComboBox,
						(this.inf17.equals("1")), newV));

		this.saveButton.setOnAction(event -> save());
	}

	private void checkComboBox(ComboBox<Member> cb1, ComboBox<Member> cb2, boolean equals, Number newV) {

		if (this.activeListenerComboBox) {

			if (equals) {

				int index = newV.intValue();
				if (index > -1) {

					// MEMBER COMBOBOX 1
					int memberID = cb1.getItems().get(index).getSpMemberID();

					// FIND INTO COMBOBOX 2
					ObservableList<Member> items = cb2.getItems();
					for (int i = 0; i < items.size(); i++) {
						if (items.get(i).getSpMemberID() == memberID) {
							cb2.getSelectionModel().select(i);
							break;
						}
					}
				}
			}
		}
	}

	private void save() {

		if (checkFields()) {

			if (this.selectedWeek.spUscIDProperty() != null) {

				// editWeek

				WeekUsciere week = WeekUsciere.newInstanceByView(this);
				week.setUscID(this.selectedWeek.getUscID());

				String waitMessage = this.language.getString("memorialeditor.wait.save");
				TaskManager.run(this.application.getAlertBuilder2(), this.ownerStage, waitMessage,
						new WeekUsciereSaveTask(this.application.getAlertBuilder2(), this.settings, this.ownerStage,
								week, this.ownerCtrl, this.thisTab));

			} else {

				// newWeek

				WeekUsciere week = WeekUsciere.newInstanceByView(this);

				String waitMessage = this.language.getString("memorialeditor.wait.save");
				TaskManager.run(this.application.getAlertBuilder2(), this.ownerStage, waitMessage,
						new WeekUsciereSaveTask(this.application.getAlertBuilder2(), this.settings, this.ownerStage,
								week, this.ownerCtrl, this.thisTab));
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

	public Usciere getOwnerCtrl() {
		return ownerCtrl;
	}

	public void setOwnerCtrl(Usciere ownerCtrl) {
		this.ownerCtrl = ownerCtrl;
	}

	public WeekUsciere getSelectedWeek() {
		return selectedWeek;
	}

	public void setSelectedWeek(WeekUsciere selectedWeek) {
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

	public ObservableList<WeekUsciere> getCalendar() {
		return calendar;
	}

	public void setCalendar(ObservableList<WeekUsciere> calendar) {
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

	public void setMidweekLabel(Label midweekLabel) {
		this.midweekLabel = midweekLabel;
	}

	public void setWeekendLabel(Label weekendLabel) {
		this.weekendLabel = weekendLabel;
	}

	public Label getZ1MidweekLabel() {
		return z1MidweekLabel;
	}

	public Label getZ2MidweekLabel() {
		return z2MidweekLabel;
	}

	public Label getZ3MidweekLabel() {
		return z3MidweekLabel;
	}

	public Label getZ1WeekendLabel() {
		return z1WeekendLabel;
	}

	public Label getZ2WeekendLabel() {
		return z2WeekendLabel;
	}

	public Label getZ3WeekendLabel() {
		return z3WeekendLabel;
	}

	public ComboBox<Member> getZ1u1MidweekComboBox() {
		return z1u1MidweekComboBox;
	}

	public ComboBox<Member> getZ1u2MidweekComboBox() {
		return z1u2MidweekComboBox;
	}

	public ComboBox<Member> getZ1u3MidweekComboBox() {
		return z1u3MidweekComboBox;
	}

	public ComboBox<Member> getZ2u1MidweekComboBox() {
		return z2u1MidweekComboBox;
	}

	public ComboBox<Member> getZ2u2MidweekComboBox() {
		return z2u2MidweekComboBox;
	}

	public ComboBox<Member> getZ2u3MidweekComboBox() {
		return z2u3MidweekComboBox;
	}

	public ComboBox<Member> getZ3u1MidweekComboBox() {
		return z3u1MidweekComboBox;
	}

	public ComboBox<Member> getZ3u2MidweekComboBox() {
		return z3u2MidweekComboBox;
	}

	public ComboBox<Member> getZ3u3MidweekComboBox() {
		return z3u3MidweekComboBox;
	}

	public ComboBox<Member> getZ1u1WeekendComboBox() {
		return z1u1WeekendComboBox;
	}

	public ComboBox<Member> getZ1u2WeekendComboBox() {
		return z1u2WeekendComboBox;
	}

	public ComboBox<Member> getZ1u3WeekendComboBox() {
		return z1u3WeekendComboBox;
	}

	public ComboBox<Member> getZ2u1WeekendComboBox() {
		return z2u1WeekendComboBox;
	}

	public ComboBox<Member> getZ2u2WeekendComboBox() {
		return z2u2WeekendComboBox;
	}

	public ComboBox<Member> getZ2u3WeekendComboBox() {
		return z2u3WeekendComboBox;
	}

	public ComboBox<Member> getZ3u1WeekendComboBox() {
		return z3u1WeekendComboBox;
	}

	public ComboBox<Member> getZ3u2WeekendComboBox() {
		return z3u2WeekendComboBox;
	}

	public ComboBox<Member> getZ3u3WeekendComboBox() {
		return z3u3WeekendComboBox;
	}

	public ObservableList<Member> getZ1MidweekList() {
		return z1MidweekList;
	}

	public ObservableList<Member> getZ2MidweekList() {
		return z2MidweekList;
	}

	public ObservableList<Member> getZ3MidweekList() {
		return z3MidweekList;
	}

	public ObservableList<Member> getZ1WeekendList() {
		return z1WeekendList;
	}

	public ObservableList<Member> getZ2WeekendList() {
		return z2WeekendList;
	}

	public ObservableList<Member> getZ3WeekendList() {
		return z3WeekendList;
	}

	public void setZ1MidweekLabel(Label z1MidweekLabel) {
		this.z1MidweekLabel = z1MidweekLabel;
	}

	public void setZ2MidweekLabel(Label z2MidweekLabel) {
		this.z2MidweekLabel = z2MidweekLabel;
	}

	public void setZ3MidweekLabel(Label z3MidweekLabel) {
		this.z3MidweekLabel = z3MidweekLabel;
	}

	public void setZ1WeekendLabel(Label z1WeekendLabel) {
		this.z1WeekendLabel = z1WeekendLabel;
	}

	public void setZ2WeekendLabel(Label z2WeekendLabel) {
		this.z2WeekendLabel = z2WeekendLabel;
	}

	public void setZ3WeekendLabel(Label z3WeekendLabel) {
		this.z3WeekendLabel = z3WeekendLabel;
	}

	public void setZ1u1MidweekComboBox(ComboBox<Member> z1u1MidweekComboBox) {
		this.z1u1MidweekComboBox = z1u1MidweekComboBox;
	}

	public void setZ1u2MidweekComboBox(ComboBox<Member> z1u2MidweekComboBox) {
		this.z1u2MidweekComboBox = z1u2MidweekComboBox;
	}

	public void setZ1u3MidweekComboBox(ComboBox<Member> z1u3MidweekComboBox) {
		this.z1u3MidweekComboBox = z1u3MidweekComboBox;
	}

	public void setZ2u1MidweekComboBox(ComboBox<Member> z2u1MidweekComboBox) {
		this.z2u1MidweekComboBox = z2u1MidweekComboBox;
	}

	public void setZ2u2MidweekComboBox(ComboBox<Member> z2u2MidweekComboBox) {
		this.z2u2MidweekComboBox = z2u2MidweekComboBox;
	}

	public void setZ2u3MidweekComboBox(ComboBox<Member> z2u3MidweekComboBox) {
		this.z2u3MidweekComboBox = z2u3MidweekComboBox;
	}

	public void setZ3u1MidweekComboBox(ComboBox<Member> z3u1MidweekComboBox) {
		this.z3u1MidweekComboBox = z3u1MidweekComboBox;
	}

	public void setZ3u2MidweekComboBox(ComboBox<Member> z3u2MidweekComboBox) {
		this.z3u2MidweekComboBox = z3u2MidweekComboBox;
	}

	public void setZ3u3MidweekComboBox(ComboBox<Member> z3u3MidweekComboBox) {
		this.z3u3MidweekComboBox = z3u3MidweekComboBox;
	}

	public void setZ1u1WeekendComboBox(ComboBox<Member> z1u1WeekendComboBox) {
		this.z1u1WeekendComboBox = z1u1WeekendComboBox;
	}

	public void setZ1u2WeekendComboBox(ComboBox<Member> z1u2WeekendComboBox) {
		this.z1u2WeekendComboBox = z1u2WeekendComboBox;
	}

	public void setZ1u3WeekendComboBox(ComboBox<Member> z1u3WeekendComboBox) {
		this.z1u3WeekendComboBox = z1u3WeekendComboBox;
	}

	public void setZ2u1WeekendComboBox(ComboBox<Member> z2u1WeekendComboBox) {
		this.z2u1WeekendComboBox = z2u1WeekendComboBox;
	}

	public void setZ2u2WeekendComboBox(ComboBox<Member> z2u2WeekendComboBox) {
		this.z2u2WeekendComboBox = z2u2WeekendComboBox;
	}

	public void setZ2u3WeekendComboBox(ComboBox<Member> z2u3WeekendComboBox) {
		this.z2u3WeekendComboBox = z2u3WeekendComboBox;
	}

	public void setZ3u1WeekendComboBox(ComboBox<Member> z3u1WeekendComboBox) {
		this.z3u1WeekendComboBox = z3u1WeekendComboBox;
	}

	public void setZ3u2WeekendComboBox(ComboBox<Member> z3u2WeekendComboBox) {
		this.z3u2WeekendComboBox = z3u2WeekendComboBox;
	}

	public void setZ3u3WeekendComboBox(ComboBox<Member> z3u3WeekendComboBox) {
		this.z3u3WeekendComboBox = z3u3WeekendComboBox;
	}

	public void setZ1MidweekList(ObservableList<Member> z1MidweekList) {
		this.z1MidweekList = z1MidweekList;
	}

	public void setZ2MidweekList(ObservableList<Member> z2MidweekList) {
		this.z2MidweekList = z2MidweekList;
	}

	public void setZ3MidweekList(ObservableList<Member> z3MidweekList) {
		this.z3MidweekList = z3MidweekList;
	}

	public void setZ1WeekendList(ObservableList<Member> z1WeekendList) {
		this.z1WeekendList = z1WeekendList;
	}

	public void setZ2WeekendList(ObservableList<Member> z2WeekendList) {
		this.z2WeekendList = z2WeekendList;
	}

	public void setZ3WeekendList(ObservableList<Member> z3WeekendList) {
		this.z3WeekendList = z3WeekendList;
	}

	public ObservableList<Week> getDatabaseWeeks() {
		return databaseWeeks;
	}

	public void setDatabaseWeeks(ObservableList<Week> databaseWeeks) {
		this.databaseWeeks = databaseWeeks;
	}

	public ObservableList<WeekUsciere> getDatabaseWeeksUsciere() {
		return databaseWeeksUsciere;
	}

	public void setDatabaseWeeksUsciere(ObservableList<WeekUsciere> databaseWeeksUsciere) {
		this.databaseWeeksUsciere = databaseWeeksUsciere;
	}

	public ObservableList<WeekAudio> getDatabaseWeeksAudio() {
		return databaseWeeksAudio;
	}

	public void setDatabaseWeeksAudio(ObservableList<WeekAudio> databaseWeeksAudio) {
		this.databaseWeeksAudio = databaseWeeksAudio;
	}

	@Override
	public void updateSelectedComboBox(Privileges privilege, int memberID) {
		switch (privilege) {
		case MIDWEEK_USCIERE1_Z1:
			updateComboBox(this.z1u1MidweekComboBox, memberID);
			break;
		case MIDWEEK_USCIERE2_Z1:
			updateComboBox(this.z1u2MidweekComboBox, memberID);
			break;
		case MIDWEEK_USCIERE3_Z1:
			updateComboBox(this.z1u3MidweekComboBox, memberID);
			break;
		case MIDWEEK_USCIERE1_Z2:
			updateComboBox(this.z2u1MidweekComboBox, memberID);
			break;
		case MIDWEEK_USCIERE2_Z2:
			updateComboBox(this.z2u2MidweekComboBox, memberID);
			break;
		case MIDWEEK_USCIERE3_Z2:
			updateComboBox(this.z2u3MidweekComboBox, memberID);
			break;
		case MIDWEEK_USCIERE1_Z3:
			updateComboBox(this.z3u1MidweekComboBox, memberID);
			break;
		case MIDWEEK_USCIERE2_Z3:
			updateComboBox(this.z3u2MidweekComboBox, memberID);
			break;
		case MIDWEEK_USCIERE3_Z3:
			updateComboBox(this.z3u3MidweekComboBox, memberID);
			break;
		case WEEKEND_USCIERE1_Z1:
			updateComboBox(this.z1u1WeekendComboBox, memberID);
			break;
		case WEEKEND_USCIERE2_Z1:
			updateComboBox(this.z1u2WeekendComboBox, memberID);
			break;
		case WEEKEND_USCIERE3_Z1:
			updateComboBox(this.z1u3WeekendComboBox, memberID);
			break;
		case WEEKEND_USCIERE1_Z2:
			updateComboBox(this.z2u1WeekendComboBox, memberID);
			break;
		case WEEKEND_USCIERE2_Z2:
			updateComboBox(this.z2u2WeekendComboBox, memberID);
			break;
		case WEEKEND_USCIERE3_Z2:
			updateComboBox(this.z2u3WeekendComboBox, memberID);
			break;
		case WEEKEND_USCIERE1_Z3:
			updateComboBox(this.z3u1WeekendComboBox, memberID);
			break;
		case WEEKEND_USCIERE2_Z3:
			updateComboBox(this.z3u2WeekendComboBox, memberID);
			break;
		case WEEKEND_USCIERE3_Z3:
			updateComboBox(this.z3u3WeekendComboBox, memberID);
			break;
		default:
			break;
		}
	}

	public void updateComboBox(ComboBox<Member> cb, int memberID) {

		int index = -1;

		for (int i = 0; i < cb.getItems().size(); i++) {
			if (cb.getItems().get(i).getSpMemberID() == memberID) {
				index = i;
				break;
			}
		}

		cb.getSelectionModel().select(index);
	}

	@Override
	public void updateSelectedChristianPart(ChristiansPart christiansPart, int memberID) {
	}
}
