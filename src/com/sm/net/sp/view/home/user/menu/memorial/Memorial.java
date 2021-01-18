package com.sm.net.sp.view.home.user.menu.memorial;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;

import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.actions.Actions;
import com.sm.net.sp.model.EnumDays;
import com.sm.net.sp.model.Family;
import com.sm.net.sp.model.Member;
import com.sm.net.sp.model.Place;
import com.sm.net.sp.model.Song;
import com.sm.net.sp.model.UpdateDataAdapter;
import com.sm.net.sp.model.WeekMemorial;
import com.sm.net.sp.settings.Settings;
import com.sm.net.sp.view.SupportPlannerView;
import com.sm.net.sp.view.home.user.menu.memorial.task.MemorialInitDataLoadTask;
import com.sm.net.sp.view.home.user.menu.memorial.task.WeekMemorialDeleteTask;
import com.sm.net.sp.view.home.user.menu.memorial.task.WeekMemorialLoadTask;
import com.sm.net.util.Crypt;
import com.sm.net.util.DateUtil;
import com.smnet.core.task.TaskManager;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Memorial extends UpdateDataAdapter {

	@FXML
	private ImageView headerImageView;
	@FXML
	private Label headerLabel;
	@FXML
	private TabPane tabPane;
	@FXML
	private Tab calendarTab;
	@FXML
	private TableView<WeekMemorial> weekTableView;
	@FXML
	private TableColumn<WeekMemorial, Integer> weekTableColumn;
	@FXML
	private TableColumn<WeekMemorial, LocalDate> fromTableColumn;
	@FXML
	private TableColumn<WeekMemorial, LocalDate> toTableColumn;
	@FXML
	private TableColumn<WeekMemorial, String> dayColumn;
	@FXML
	private TableColumn<WeekMemorial, String> placeColumn;
	@FXML
	private TableColumn<WeekMemorial, String> talkBrotherColumn;
	@FXML
	private TableColumn<WeekMemorial, String> themeColumn;
	@FXML
	private Button deleteWeekButton;

	private SupportPlannerView application;

	private Settings settings;
	private Language language;
	private Stage ownerStage;

	private ObservableList<WeekMemorial> calendar;

	private ObservableList<Member> membersList;
	private ObservableList<Family> familiesList;
	private ObservableList<Place> placesList;
	private HashMap<String, String> configs;

	private ObservableList<Song> songList;
	private boolean configSongTitleLoad;

	@FXML
	private void initialize() {
		styleClasses();
		cellValueFactory();
	}

	private void cellValueFactory() {

		this.weekTableColumn.setCellValueFactory(cellData -> cellData.getValue().weekProperty().asObject());
		this.fromTableColumn.setCellValueFactory(cellData -> cellData.getValue().fromProperty());
		this.toTableColumn.setCellValueFactory(cellData -> cellData.getValue().toProperty());

		this.dayColumn.setCellValueFactory(cellData -> {

			WeekMemorial memorial = cellData.getValue();

			if (memorial.spMemorialIDProperty() != null) {

				int dayID = memorial.getSpInf21();
				int hour = memorial.getSpInf22();
				int minute = memorial.getSpInf23();

				EnumDays day = EnumDays.getByID(dayID);
				String format = this.language.getString("memorial.tablecolumn.patternday");

				return new SimpleStringProperty(
						String.format(format, this.language.getString(day.getKey()), hour, minute));
			}

			return null;
		});

		this.placeColumn.setCellValueFactory(cellData -> cellData.getValue().spInf24Property());
		this.talkBrotherColumn.setCellValueFactory(cellData -> {

			WeekMemorial memorial = cellData.getValue();

			if (memorial.spMemorialIDProperty() != null) {

				int talkBrotherID = memorial.getSpInf6();
				if (talkBrotherID > 0)
					for (Member m : this.membersList)
						if (m.getSpMemberID() == talkBrotherID)
							return new SimpleStringProperty(m.getNameStyle1());

			}

			return null;
		});

		this.themeColumn.setCellValueFactory(cellData -> cellData.getValue().spInf7Property());
	}

	private void styleClasses() {

		this.headerLabel.getStyleClass().add("label_header_001");

		this.tabPane.getStyleClass().add("tab_pane_001");

		this.calendarTab.getStyleClass().add("tab_001");
		this.weekTableView.getStyleClass().add("table_view_001");

		this.weekTableColumn.getStyleClass().add("table_column_002");
		this.fromTableColumn.getStyleClass().add("table_column_002");
		this.toTableColumn.getStyleClass().add("table_column_002");

		this.dayColumn.getStyleClass().add("table_column_002");

		this.deleteWeekButton.getStyleClass().add("button_image_001");
	}

	public void objectInitialize() {

		this.settings = this.application.getSettings();

		viewUpdate();
		initData();
		listeners();
	}

	private void viewUpdate() {

		this.language = settings.getLanguage();

		this.headerImageView.setFitWidth(50);
		this.headerImageView.setFitHeight(50);
		this.headerImageView.setImage(Meta.Resources.getImageLogo(Meta.Resources.MEMORIAL, 50, 50));

		this.headerLabel.setText(this.language.getString("memorial.header"));

		this.calendarTab.setText(this.language.getString("TEXT0075"));
		this.calendarTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.CALENDAR));

		this.weekTableColumn.setText(this.language.getString("TEXT0076"));
		this.weekTableColumn.setMinWidth(100);
		this.weekTableColumn.setMaxWidth(100);

		this.fromTableColumn.setText(this.language.getString("TEXT0077"));
		this.fromTableColumn.setMinWidth(100);
		this.fromTableColumn.setMaxWidth(100);

		this.toTableColumn.setText(this.language.getString("TEXT0078"));
		this.toTableColumn.setMinWidth(100);
		this.toTableColumn.setMaxWidth(100);

		this.dayColumn.setText(this.language.getString("memorial.tablecolumn.day"));
		this.dayColumn.setMinWidth(150);
		this.dayColumn.setMaxWidth(150);

		this.placeColumn.setText(this.language.getString("memorial.tablecolumn.place"));

		this.talkBrotherColumn.setText(this.language.getString("memorial.tablecolumn.talkbrother"));

		this.themeColumn.setText(this.language.getString("memorial.tablecolumn.theme"));

		Tooltip deleteTooltip = new Tooltip(language.getString("memorial.tooltip.delete"));
		deleteTooltip.getStyleClass().add("tooltip_001");
		this.deleteWeekButton.setTooltip(deleteTooltip);
		this.deleteWeekButton.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.DELETE));
		this.deleteWeekButton.setText(null);
	}

	private void initData() {

		this.loadCalendar();
		this.updateWeeksData();

		this.membersList = FXCollections.observableArrayList();
		this.familiesList = FXCollections.observableArrayList();

		updateMembers();
	}

	public void checkConfigs() {
		String inf19 = Crypt.decrypt(this.configs.get("inf19"), this.application.getSettings().getDatabaseSecretKey());
		this.configSongTitleLoad = inf19.equals("1");
	}
	
	@Override
	public void updateMembers() {
		Actions.getAllMembers(settings, ownerStage, this);
	}

	@Override
	public void updateFamilies() {
		Actions.getAllFamilies(settings, ownerStage, this);
	}

	@Override
	public void updateMembers(ObservableList<Member> list) {

		this.membersList = list;
		this.membersList.sort((a, b) -> (a.getSpInf2Decrypted().concat(a.getSpInf1Decrypted())
				.compareTo(b.getSpInf2Decrypted().concat(b.getSpInf1Decrypted()))));

		updateFamilies();
	}

	@Override
	public void updateFamilies(ObservableList<Family> list) {

		this.familiesList = list;
		this.familiesList.sort((a, b) -> a.getSpInf1Decrypted().compareTo(b.getSpInf1Decrypted()));

		// Carica programmazione e luoghi

		String waitMessage = this.language.getString("memorial.wait.load");

		TaskManager.run(this.application.getAlertBuilder2(), this.ownerStage, waitMessage, new MemorialInitDataLoadTask(
				this.application.getAlertBuilder2(), this.settings, this.ownerStage, this));
	}

	private void loadCalendar() {

		this.calendar = FXCollections.observableArrayList();

		// Data di oggi
		LocalDate now = LocalDate.now();

		int month = now.getMonthValue();
		int year = (month == 1) ? (now.getYear() - 1) : now.getYear();
		month = (month == 1) ? 12 : (month - 1);

		// Primo giorno del mese (determinato da now)
		LocalDate day = LocalDate.of(year, month, 1);

		// Primo giorno della settimana contenente il primo giorno del mese
		LocalDate firstDayOfWeek = DateUtil.getFirstDayOfWeek(day);

		// Se il lunedi ricade nel mese precedente, inizio dalla settimana successiva
		if (firstDayOfWeek.getMonthValue() != day.getMonthValue())
			day = firstDayOfWeek.plusDays(7);

		// Generazione del calendario
		int countMonth = 0;
		boolean first = true;

		int monthsToGenerate = 36;

		do {

			int lastMonth = day.getMonthValue();

			day = first ? day : day.plusDays(7);
			if (first)
				first = false;

			int currentMonth = day.getMonthValue();

			if (lastMonth == currentMonth) {
				if (currentMonth == 2 || currentMonth == 3 || currentMonth == 4)
					this.calendar.add(new WeekMemorial(day.plusDays(6), this.language));
			} else {
				countMonth = countMonth + 1;
				if (countMonth < monthsToGenerate)
					if (currentMonth == 2 || currentMonth == 3 || currentMonth == 4)
						this.calendar.add(new WeekMemorial(day.plusDays(6), this.language));
			}

		} while (countMonth < monthsToGenerate);

		this.weekTableView.setItems(this.calendar);
	}

	public void updateWeeksData() {

		String waitMessage = this.language.getString("memorial.task.load");

		TaskManager.run(this.application.getAlertBuilder2(), this.ownerStage, waitMessage,
				new WeekMemorialLoadTask(this.application.getAlertBuilder2(), this.settings, this.ownerStage, this));

	}

	private void listeners() {

		listenerWeekTableView();
		this.deleteWeekButton.setOnAction(event -> delete());
	}

	private void delete() {

		if (this.weekTableView.getSelectionModel().getSelectedIndex() > -1) {

			WeekMemorial item = this.weekTableView.getSelectionModel().getSelectedItem();
			if (item.spMemorialIDProperty() != null) {

				if (this.application.getAlertBuilder2().confirm(this.ownerStage,
						this.language.getString("memorial.delete.confirm"))) {

					int id = item.getMemorialID();

					String waitMessage = this.language.getString("memorial.task.delete");

					TaskManager.run(this.application.getAlertBuilder2(), this.ownerStage, waitMessage,
							new WeekMemorialDeleteTask(this.application.getAlertBuilder2(), this.settings,
									this.ownerStage, this, id));
				}
			} else {

				this.application.getAlertBuilder2().error(this.ownerStage,
						this.language.getString("convention.delete.noconvention"));

			}

		} else {
			this.application.getAlertBuilder2().error(this.ownerStage,
					this.language.getString("convention.delete.noselection"));
		}
	}

	private void listenerWeekTableView() {

		this.weekTableView.setRowFactory(param -> {

			TableRow<WeekMemorial> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 2 && (!row.isEmpty()))
					editWeek(row.getItem());
			});

			return row;
		});
	}

	private void editWeek(WeekMemorial week) {

		if (!isAlreadyOpen(week.getFrom().toString())) {

			try {

				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(Meta.Views.MEMORIAL_EDITOR_FXML_URL);
				AnchorPane layout = (AnchorPane) fxmlLoader.load();

				MemorialEditor ctrl = (MemorialEditor) fxmlLoader.getController();
				ctrl.setSettings(this.settings);
				ctrl.setOwnerStage(ownerStage);
				ctrl.setOwnerCtrl(this);
				ctrl.setSelectedWeek(week);
				ctrl.setCalendar(this.calendar);
				ctrl.setApplication(this.application);
				ctrl.setMembersList(this.membersList);
				ctrl.setFamiliesList(this.familiesList);
				ctrl.setConfigs(this.configs);
				ctrl.setConfigSongTitleLoad(this.configSongTitleLoad);
				ctrl.setSongList(this.songList);

				Tab newTab = new Tab(week.getFrom().toString(), layout);
				newTab.setClosable(true);
				newTab.getStyleClass().add("tab_001");
				newTab.setGraphic(Meta.Resources.imageForTab(Meta.Resources.CALENDAR));

				ctrl.setOwnerTabPane(tabPane);
				ctrl.setThisTab(newTab);

				this.tabPane.getTabs().add(newTab);
				this.tabPane.getSelectionModel().select(newTab);

				ctrl.objectInitialize();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	private boolean isAlreadyOpen(String label) {

		for (Tab tab : tabPane.getTabs())
			if (tab.getText().equals(label)) {
				tabPane.getSelectionModel().select(tab);
				return true;
			}

		return false;
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

	public ObservableList<WeekMemorial> getCalendar() {
		return calendar;
	}

	public void setCalendar(ObservableList<WeekMemorial> calendar) {
		this.calendar = calendar;
	}

	public SupportPlannerView getApplication() {
		return application;
	}

	public void setApplication(SupportPlannerView application) {
		this.application = application;
	}

	public ImageView getHeaderImageView() {
		return headerImageView;
	}

	public Label getHeaderLabel() {
		return headerLabel;
	}

	public TabPane getTabPane() {
		return tabPane;
	}

	public Tab getCalendarTab() {
		return calendarTab;
	}

	public TableView<WeekMemorial> getWeekTableView() {
		return weekTableView;
	}

	public TableColumn<WeekMemorial, Integer> getWeekTableColumn() {
		return weekTableColumn;
	}

	public TableColumn<WeekMemorial, LocalDate> getFromTableColumn() {
		return fromTableColumn;
	}

	public TableColumn<WeekMemorial, LocalDate> getToTableColumn() {
		return toTableColumn;
	}

	public TableColumn<WeekMemorial, String> getVisitNumberColumn() {
		return themeColumn;
	}

	public void setHeaderImageView(ImageView headerImageView) {
		this.headerImageView = headerImageView;
	}

	public void setHeaderLabel(Label headerLabel) {
		this.headerLabel = headerLabel;
	}

	public void setTabPane(TabPane tabPane) {
		this.tabPane = tabPane;
	}

	public void setCalendarTab(Tab calendarTab) {
		this.calendarTab = calendarTab;
	}

	public void setWeekTableView(TableView<WeekMemorial> weekTableView) {
		this.weekTableView = weekTableView;
	}

	public void setWeekTableColumn(TableColumn<WeekMemorial, Integer> weekTableColumn) {
		this.weekTableColumn = weekTableColumn;
	}

	public void setFromTableColumn(TableColumn<WeekMemorial, LocalDate> fromTableColumn) {
		this.fromTableColumn = fromTableColumn;
	}

	public void setToTableColumn(TableColumn<WeekMemorial, LocalDate> toTableColumn) {
		this.toTableColumn = toTableColumn;
	}

	public void setVisitNumberColumn(TableColumn<WeekMemorial, String> visitNumberColumn) {
		this.themeColumn = visitNumberColumn;
	}

	public ObservableList<Place> getPlacesList() {
		return placesList;
	}

	public void setPlacesList(ObservableList<Place> placesList) {
		this.placesList = placesList;
	}

	public HashMap<String, String> getConfigs() {
		return configs;
	}

	public void setConfigs(HashMap<String, String> configs) {
		this.configs = configs;
	}

	public ObservableList<Song> getSongList() {
		return songList;
	}

	public void setSongList(ObservableList<Song> songList) {
		this.songList = songList;
	}

	public boolean isConfigSongTitleLoad() {
		return configSongTitleLoad;
	}

	public void setConfigSongTitleLoad(boolean configSongTitleLoad) {
		this.configSongTitleLoad = configSongTitleLoad;
	}

}
