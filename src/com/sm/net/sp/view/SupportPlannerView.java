package com.sm.net.sp.view;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.crypto.SecretKey;

import org.ini4j.InvalidFileFormatException;
import org.json.JSONObject;

import com.sm.net.directory.Directory;
import com.sm.net.file.Extensions;
import com.sm.net.file.FileUtils;
import com.sm.net.javafx.AlertDesigner;
import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.SupportPlannerMain;
import com.sm.net.sp.model.User;
import com.sm.net.sp.settings.Settings;
import com.sm.net.sp.settings.SettingsConf;
import com.sm.net.sp.utils.AlertBuilderOld;
import com.sm.net.sp.utils.EnumOperatingSystem;
import com.sm.net.sp.utils.EnumOperatingSystemArchitecture;
import com.sm.net.sp.utils.OperatingSystemUtils;
import com.sm.net.sp.view.check.access.CheckAccess;
import com.sm.net.sp.view.home.access.HomeAccess;
import com.sm.net.sp.view.home.user.menu.HomeUserMenuList;
import com.sm.net.sp.view.home.user.menu.circuitoverseer.UserMenuCircuitOverseer;
import com.sm.net.sp.view.home.user.menu.config.UserMenuConfig;
import com.sm.net.sp.view.home.user.menu.congr.UserMenuCongrList;
import com.sm.net.sp.view.home.user.menu.conven.Convention;
import com.sm.net.sp.view.home.user.menu.database.UserMenuDatabase;
import com.sm.net.sp.view.home.user.menu.dateandtime.HomeUserMenuDateAndTime;
import com.sm.net.sp.view.home.user.menu.generalinfo.UserMenuGeneralInfo;
import com.sm.net.sp.view.home.user.menu.meetings.UserMenuMeetings;
import com.sm.net.sp.view.home.user.menu.memorial.Memorial;
import com.sm.net.sp.view.home.user.menu.monitor.UserMenuMonitor;
import com.sm.net.sp.view.home.user.menu.naturaldisaster.UserMenuNaturalDisasterList;
import com.sm.net.sp.view.home.user.menu.places.HomeUserMenuPlaces;
import com.sm.net.sp.view.home.user.menu.publicmeetings.UserMenuPublicMeetings;
import com.sm.net.sp.view.home.user.menu.sergroups.UserMenuSerGroupsList;
import com.sm.net.sp.view.home.user.menu.users.HomeUserMenuUsersList;
import com.sm.net.sp.view.menu.settings.SettingsList;
import com.sm.net.sp.view.menu.settings.connection.SettingConnection;
import com.sm.net.sp.view.menu.settings.database.SettingDatabase;
import com.sm.net.sp.view.menu.settings.monitor.SettingMonitor;
import com.sm.net.sp.view.menu.settings.user.SettingUser;
import com.sm.net.sp.view.setting.create.language.SettingCreateLanguage;
import com.sm.net.util.Crypt;
import com.smnet.core.dialog.AlertBuilder;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class SupportPlannerView implements SupportPlannerCallback {

	@FXML
	private BorderPane viewSupportPlannerBorderPane;

	private AlertBuilderOld alertBuilder;
	private Stage viewSupportPlannerStage;
	private SupportPlannerView ctrlViewSupportPlanner;
	private User user;
	private File mysqlDump;
	private File mysqlRestore;

	private Settings settings;
	private int left;
	private int center;

	private EnumOperatingSystem system;
	private EnumOperatingSystemArchitecture architecture;

	private AlertBuilder alertBuilder2;

	public void objectInitialize() {

		this.alertBuilder2 = new AlertBuilder(Meta.Application.getFullTitle());
		try {

			File css = new File(SupportPlannerMain.class.getResource("theme.css").toURI());
			File icon = new File(Meta.Resources.RESOURCES, Meta.Resources.ICON);

			this.alertBuilder2.setCSS(css, "alert_001");
			this.alertBuilder2.setIcon(icon);

		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		this.system = OperatingSystemUtils.getOperatingSystem();
		this.architecture = OperatingSystemUtils.getOperatingSystemArchitecture();

		this.alertBuilder = new AlertBuilderOld(Meta.Application.getFullTitle(), Meta.Themes.SUPPORTPLANNER_THEME,
				"alert_001", new File(Meta.Resources.ICON).toURI().toString());

		viewSupportPlannerBorderPane.getStyleClass().add("main_color_001");

		this.left = 0;
		this.center = 0;
		this.user = null;

		this.settings = null;

		settingsInitialize();

		this.mysqlDump = setMySQLDumpFile();
		this.mysqlRestore = setMySQLRestoreFile();

	}

	private File setMySQLRestoreFile() {

		// TODO: differenziare per sistema operativo

		File file = new File("tools", "mysql");

		File mysql = null;

		switch (this.architecture) {
		case BIT32:

			switch (this.system) {
			case WINDOWS:

				break;
			case LINUX:

				break;
			case MAC:

				break;
			}

			break;

		case BIT64:

			switch (this.system) {
			case WINDOWS:

				mysql = new File(file, "winx64");
				return new File(mysql, "mysql.exe");

			case LINUX:

				mysql = new File(file, "linuxx64");
				return new File(mysql, "mysql");

			case MAC:

				break;
			}

			break;
		}

		return mysql;
	}

	private File setMySQLDumpFile() {

		File file = new File("tools", "mysql");

		File dump = null;

		switch (this.architecture) {
		case BIT32:

			switch (this.system) {
			case WINDOWS:

				break;
			case LINUX:

				break;
			case MAC:

				break;
			}

			break;

		case BIT64:

			switch (this.system) {
			case WINDOWS:

				dump = new File(file, "winx64");
				return new File(dump, "mysqldump.exe");

			case LINUX:

				dump = new File(file, "linuxx64");
				return new File(dump, "mysqldump");

			case MAC:

				break;
			}

			break;
		}

		return dump;
	}

	private void settingsInitialize() {

		if (settingsCheckFile())
			settingsLoad();
		else
			settingsCreate();
	}

	private void settingsLoad() {

		try {
			this.settings = new Settings(new SettingsConf(Meta.Settings.getFile()));
			this.settings.load();
			viewCheckAccess();
		} catch (IOException e) {
			new AlertDesigner(Meta.Application.getFullTitle() + " Error:", "No Valid Settings", viewSupportPlannerStage,
					AlertType.ERROR, Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
					Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").show();

			System.exit(0);
		}
	}

	private boolean settingsCheckFile() {

		File settingsFile = Meta.Settings.getFile();

		if (settingsFile != null)
			return settingsFile.exists();

		return false;
	}

	private void settingsCreate() {

		ObservableList<Language> languages = languagesCheckPropertiesFiles();

		if (languagesHaveValid(languages))
			viewSettingCreateLanguage(languages);
		else {
			new AlertDesigner(Meta.Application.getFullTitle() + " Error:", "No Valid File Language",
					viewSupportPlannerStage, AlertType.ERROR, Meta.Application.getFullTitle(),
					Meta.Resources.getImageApplicationIcon(), Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").show();

			System.exit(0);
		}
	}

	private boolean languagesHaveValid(ObservableList<Language> languages) {

		if (languages != null)
			return (languages.size() > 0);

		return false;
	}

	private ObservableList<Language> languagesCheckPropertiesFiles() {

		File[] allFiles = Directory.listAllFiles(Meta.Languages.getDirectory());

		if (allFiles != null)
			if (allFiles.length > 0)
				return languagesCheckValidFiles(allFiles);

		return null;
	}

	private ObservableList<Language> languagesCheckValidFiles(File[] files) {

		ObservableList<Language> languages = FXCollections.observableArrayList();

		for (File file : files) {
			if (FileUtils.getExtensionWithDot(file.getName()).equals(Extensions.PROPERTIES)) {
				try {
					Language lang = new Language(file);
					if (lang.isLoaded() && !lang.getLanguage().equals("null"))
						languages.add(lang);
				} catch (IOException e) {
				}
			}
		}

		return languages;
	}

	private void viewSettingCreateLanguage(ObservableList<Language> languages) {

		try {

			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(Meta.Views.SETTING_CREATE_LANGUAGE);

			AnchorPane layout = (AnchorPane) fxmlLoader.load();

			SettingCreateLanguage controller = (SettingCreateLanguage) fxmlLoader.getController();
			controller.setLanguages(languages);
			controller.setSupportPlannerViewCtrl(this.ctrlViewSupportPlanner);
			controller.objectInitialize();

			this.viewSupportPlannerBorderPane.setCenter(layout);
			this.viewSupportPlannerStage.setMaximized(false);
			this.viewSupportPlannerStage.setResizable(false);

		} catch (IOException e) {
		}
	}

	public void viewSettingCreateAccess(AnchorPane accessCreateViewAnchorPane) {

		this.viewSupportPlannerBorderPane.setCenter(accessCreateViewAnchorPane);
		this.viewSupportPlannerStage.setMaximized(false);
		this.viewSupportPlannerStage.setResizable(false);
		this.viewSupportPlannerStage.hide();
		this.viewSupportPlannerStage.show();
	}

	private void viewCheckAccess() {

		try {

			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(Meta.Views.CHECK_ACCESS);

			AnchorPane layout = (AnchorPane) fxmlLoader.load();

			CheckAccess controller = (CheckAccess) fxmlLoader.getController();
			controller.setSettings(settings);
			controller.setCtrlViewSupportPlanner(this.ctrlViewSupportPlanner);
			controller.objectInitialize();

			this.viewSupportPlannerBorderPane.setCenter(layout);
			this.viewSupportPlannerStage.setMaximized(false);
			this.viewSupportPlannerStage.setResizable(false);

		} catch (IOException e) {
		}
	}

	public void viewSupportPlannerHome() {

		this.viewSupportPlannerStage.setMinWidth(1000);
		this.viewSupportPlannerStage.setMaxWidth(Double.MAX_VALUE);
		this.viewSupportPlannerStage.setWidth(Screen.getPrimary().getBounds().getWidth());
		this.viewSupportPlannerStage.setMinHeight(500);
		this.viewSupportPlannerStage.setMaxHeight(Double.MAX_VALUE);
		this.viewSupportPlannerStage.setHeight(Double.MAX_VALUE);
		this.viewSupportPlannerStage.setMaximized(true);
		this.viewSupportPlannerStage.setResizable(true);

		viewSupportPlannerHomeMenu();
		loadHome();
	}

	private void viewSupportPlannerHomeMenu() {

		try {

			FXMLLoader fxmlLoaderMenu = new FXMLLoader();
			fxmlLoaderMenu.setLocation(Meta.Views.SUPPORTPLANNER_MENU);
			AnchorPane layoutMenu = (AnchorPane) fxmlLoaderMenu.load();
			SupportPlannerMenu ctrlMenu = (SupportPlannerMenu) fxmlLoaderMenu.getController();
			ctrlMenu.setSettings(this.settings);
			ctrlMenu.setCtrlSupportPlannerView(this);
			ctrlMenu.objectInitialize();

			this.viewSupportPlannerBorderPane.setTop(layoutMenu);

		} catch (IOException e) {
		}
	}

	private void viewSupportPlannerHomeAccess() {

		if (this.center != 3) {

			this.left = 0;
			this.center = 3;
			this.viewSupportPlannerBorderPane.setLeft(null);

			try {

				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(Meta.Views.SUPPORTPLANNER_HOME_ACCESS);
				AnchorPane layout = (AnchorPane) fxmlLoader.load();
				HomeAccess ctrl = (HomeAccess) fxmlLoader.getController();
				ctrl.setSettings(this.settings);
				ctrl.setSupportPlannerViewCtrl(this);
				ctrl.setViewSupportPlannerStage(viewSupportPlannerStage);
				ctrl.objectInitialize();

				this.viewSupportPlannerBorderPane.setCenter(layout);

			} catch (IOException e) {
			}
		}
	}

	@Override
	public void viewSupportPlannerHomeUser() {

		if (this.left != 2) {

			this.left = 2;
			this.center = 0;
			this.viewSupportPlannerBorderPane.setCenter(null);

			try {

				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(Meta.Views.HOME_USER_MENU_LIST);
				AnchorPane layout = (AnchorPane) fxmlLoader.load();
				HomeUserMenuList ctrl = (HomeUserMenuList) fxmlLoader.getController();
				ctrl.setUser(this.user);
				ctrl.setSettings(this.settings);
				ctrl.setCtrlSupportPlannerView(this);
				ctrl.setStageSupportPlannerView(viewSupportPlannerStage);
				ctrl.objectInitialize();

				this.viewSupportPlannerBorderPane.setLeft(layout);

			} catch (IOException e) {
			}
		}
	}

	public void viewHomeUserMenuUsers() {

		if (this.center != 4) {

			this.center = 4;
			this.viewSupportPlannerBorderPane.setCenter(null);

			try {

				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(Meta.Views.HOME_USER_MENU_USERLIST);
				AnchorPane layout = (AnchorPane) fxmlLoader.load();
				HomeUserMenuUsersList ctrl = (HomeUserMenuUsersList) fxmlLoader.getController();
				ctrl.setSettings(this.settings);
				ctrl.setStageSupportPlannerView(viewSupportPlannerStage);
				ctrl.setApplication(this);
				ctrl.objectInitialize();

				this.viewSupportPlannerBorderPane.setCenter(layout);

			} catch (IOException e) {
			}
		}

	}

	public void viewHomeUserMenuCongregation() {

		if (this.center != 5) {

			this.center = 5;
			this.viewSupportPlannerBorderPane.setCenter(null);

			try {

				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(Meta.Views.HOME_USER_MENU_CONGRLIST);
				AnchorPane layout = (AnchorPane) fxmlLoader.load();

				UserMenuCongrList ctrl = (UserMenuCongrList) fxmlLoader.getController();
				ctrl.setSettings(this.settings);
				ctrl.setOwnerStage(this.viewSupportPlannerStage);
				ctrl.setApplication(this);
				ctrl.objectInitialize();

				this.viewSupportPlannerBorderPane.setCenter(layout);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void viewHomeUserMenuSerGroups() {

		if (this.center != 6) {

			this.center = 6;
			this.viewSupportPlannerBorderPane.setCenter(null);

			try {

				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(Meta.Views.HOME_USER_MENU_SERGROUPS);
				AnchorPane layout = (AnchorPane) fxmlLoader.load();

				UserMenuSerGroupsList ctrl = (UserMenuSerGroupsList) fxmlLoader.getController();
				ctrl.setApplication(this);
				ctrl.setSettings(this.settings);
				ctrl.setOwnerStage(this.viewSupportPlannerStage);
				ctrl.objectInitialize();

				this.viewSupportPlannerBorderPane.setCenter(layout);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void viewHomeUserMenuMeetings() {

		if (this.center != 7) {

			this.center = 7;
			this.viewSupportPlannerBorderPane.setCenter(null);

			try {

				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(Meta.Views.HOME_USER_MENU_MEETINGS);
				AnchorPane layout = (AnchorPane) fxmlLoader.load();

				UserMenuMeetings ctrl = (UserMenuMeetings) fxmlLoader.getController();
				ctrl.setSettings(this.settings);
				ctrl.setOwnerStage(this.viewSupportPlannerStage);
				ctrl.setAlertBuilder(this.alertBuilder);
				ctrl.setApplication(this);
				ctrl.objectInitialize();

				this.viewSupportPlannerBorderPane.setCenter(layout);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void viewHomeUserMenuCircuitOverseer() {

		if (this.center != 8) {

			this.center = 8;
			this.viewSupportPlannerBorderPane.setCenter(null);

			try {

				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(Meta.Views.HOME_USER_MENU_CIRCUITOVERSEER);
				AnchorPane layout = (AnchorPane) fxmlLoader.load();

				UserMenuCircuitOverseer ctrl = (UserMenuCircuitOverseer) fxmlLoader.getController();
				ctrl.setSettings(this.settings);
				ctrl.setOwnerStage(this.viewSupportPlannerStage);
				ctrl.objectInitialize();

				this.viewSupportPlannerBorderPane.setCenter(layout);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void viewHomeUserMenuMonitor() {

		if (this.center != 9) {

			this.center = 9;
			this.viewSupportPlannerBorderPane.setCenter(null);

			try {

				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(Meta.Views.HOME_USER_MENU_MONITOR);
				AnchorPane layout = (AnchorPane) fxmlLoader.load();

				UserMenuMonitor ctrl = (UserMenuMonitor) fxmlLoader.getController();
				ctrl.setSettings(this.settings);
				ctrl.setOwnerStage(this.viewSupportPlannerStage);
				ctrl.objectInitialize();

				this.viewSupportPlannerBorderPane.setCenter(layout);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void viewHomeUserMenuNaturalDisaster() {

		if (this.center != 10) {

			this.center = 10;
			this.viewSupportPlannerBorderPane.setCenter(null);

			try {

				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(Meta.Views.HOME_USER_MENU_NATURALDISASTER);
				AnchorPane layout = (AnchorPane) fxmlLoader.load();

				UserMenuNaturalDisasterList ctrl = (UserMenuNaturalDisasterList) fxmlLoader.getController();
				ctrl.setSettings(this.settings);
				ctrl.setOwnerStage(this.viewSupportPlannerStage);
				ctrl.objectInitialize();

				this.viewSupportPlannerBorderPane.setCenter(layout);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public void viewHomeUserMenuPublicMeetings() {

		if (this.center != 11) {

			this.center = 11;
			this.viewSupportPlannerBorderPane.setCenter(null);

			try {

				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(Meta.Views.HOME_USER_MENU_PUBLICMEETINGS);
				AnchorPane layout = fxmlLoader.load();

				UserMenuPublicMeetings ctrl = (UserMenuPublicMeetings) fxmlLoader.getController();
				ctrl.setApplication(this);
				ctrl.setSettings(this.settings);
				ctrl.setOwnerStage(this.viewSupportPlannerStage);
				ctrl.setAlertBuilder(this.alertBuilder);
				ctrl.objectInitialize();

				this.viewSupportPlannerBorderPane.setCenter(layout);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public void loadMenuSettingConnection() {

		if (this.center != 12) {
			this.center = 12;

			try {

				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(Meta.Views.MENU_SETTING_CONNECTION);
				AnchorPane layout = (AnchorPane) fxmlLoader.load();
				SettingConnection ctrl = (SettingConnection) fxmlLoader.getController();
				ctrl.setSettings(this.settings);
				ctrl.setOwnerStage(this.viewSupportPlannerStage);
				ctrl.setLoggedUser(this.user);
				ctrl.setApplication(this);
				ctrl.objectInitialize();

				this.viewSupportPlannerBorderPane.setCenter(layout);

			} catch (IOException e) {
			}
		}
	}

	public void loadMenuSettingMonitor() {

		if (this.center != 13) {
			this.center = 13;

			try {

				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(Meta.Views.MENU_SETTING_MONITOR);
				AnchorPane layout = (AnchorPane) fxmlLoader.load();
				SettingMonitor ctrl = (SettingMonitor) fxmlLoader.getController();
				ctrl.setSettings(this.settings);
				ctrl.setOwnerStage(this.viewSupportPlannerStage);
				ctrl.setLoggedUser(this.user);
				ctrl.setApplication(this);
				ctrl.objectInitialize();

				this.viewSupportPlannerBorderPane.setCenter(layout);

			} catch (IOException e) {
			}
		}
	}

	public void viewHomeUserMenuDatabase() {

		if (this.center != 14) {

			this.center = 14;
			this.viewSupportPlannerBorderPane.setCenter(null);

			try {

				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(Meta.Views.HOME_USER_MENU_DATABASE);
				AnchorPane layout = (AnchorPane) fxmlLoader.load();

				UserMenuDatabase ctrl = (UserMenuDatabase) fxmlLoader.getController();
				ctrl.setSettings(this.settings);
				ctrl.setOwnerStage(this.viewSupportPlannerStage);
				ctrl.setApplication(this);
				ctrl.objectInitialize();

				this.viewSupportPlannerBorderPane.setCenter(layout);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void viewHomeUserMenuDateAndTime() {

		if (this.center != 15) {

			this.center = 15;
			this.viewSupportPlannerBorderPane.setCenter(null);

			try {

				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(Meta.Views.HOME_USER_MENU_DATEANDTIME);
				AnchorPane layout = (AnchorPane) fxmlLoader.load();
				HomeUserMenuDateAndTime ctrl = (HomeUserMenuDateAndTime) fxmlLoader.getController();
				ctrl.setSettings(this.settings);
				ctrl.setStageSupportPlannerView(viewSupportPlannerStage);
				ctrl.setApplication(this);
				ctrl.objectInitialize();

				this.viewSupportPlannerBorderPane.setCenter(layout);

			} catch (IOException e) {
			}
		}
	}

	public void viewHomeUserMenuPlaces() {

		if (this.center != 16) {

			this.center = 16;
			this.viewSupportPlannerBorderPane.setCenter(null);

			try {

				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(Meta.Views.HOME_USER_MENU_PLACES);
				AnchorPane layout = (AnchorPane) fxmlLoader.load();
				HomeUserMenuPlaces ctrl = (HomeUserMenuPlaces) fxmlLoader.getController();
				ctrl.setSettings(this.settings);
				ctrl.setStageSupportPlannerView(viewSupportPlannerStage);
				ctrl.setApplication(this);
				ctrl.objectInitialize();

				this.viewSupportPlannerBorderPane.setCenter(layout);

			} catch (IOException e) {
			}
		}
	}

	public void viewHomeUserMenuInfo() {

		if (this.center != 17) {

			this.center = 17;
			this.viewSupportPlannerBorderPane.setCenter(null);

			try {

				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(Meta.Views.HOME_USER_MENU_GENERALINFO);
				AnchorPane layout = (AnchorPane) fxmlLoader.load();
				UserMenuGeneralInfo ctrl = (UserMenuGeneralInfo) fxmlLoader.getController();
				ctrl.setApplication(this);
				ctrl.setOwnerStage(this.viewSupportPlannerStage);
				ctrl.objectInitialize();

				this.viewSupportPlannerBorderPane.setCenter(layout);

			} catch (IOException e) {
			}
		}
	}

	public void viewHomeUserMenuConfig() {

		if (this.center != 18) {

			this.center = 18;
			this.viewSupportPlannerBorderPane.setCenter(null);

			try {

				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(Meta.Views.HOME_USER_MENU_CONFIG);
				AnchorPane layout = (AnchorPane) fxmlLoader.load();
				UserMenuConfig ctrl = (UserMenuConfig) fxmlLoader.getController();
				ctrl.setApplication(this);
				ctrl.setOwnerStage(this.viewSupportPlannerStage);
				ctrl.objectInitialize();

				this.viewSupportPlannerBorderPane.setCenter(layout);

			} catch (IOException e) {
			}
		}
	}

	public void viewConventions() {

		if (this.center != 19) {

			this.center = 19;
			this.viewSupportPlannerBorderPane.setCenter(null);

			try {

				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(Meta.Views.CONVENTIONS_FXML_URL);
				AnchorPane layout = (AnchorPane) fxmlLoader.load();
				Convention ctrl = (Convention) fxmlLoader.getController();
				ctrl.setApplication(this);
				ctrl.setOwnerStage(this.viewSupportPlannerStage);
				ctrl.objectInitialize();

				this.viewSupportPlannerBorderPane.setCenter(layout);

			} catch (IOException e) {
			}
		}

	}

	public void viewMemorial() {

		if (this.center != 20) {

			this.center = 20;
			this.viewSupportPlannerBorderPane.setCenter(null);

			try {

				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(Meta.Views.MEMORIAL_FXML_URL);
				AnchorPane layout = (AnchorPane) fxmlLoader.load();
				Memorial ctrl = (Memorial) fxmlLoader.getController();
				ctrl.setApplication(this);
				ctrl.setOwnerStage(this.viewSupportPlannerStage);
				ctrl.objectInitialize();

				this.viewSupportPlannerBorderPane.setCenter(layout);

			} catch (IOException e) {
			}
		}
	}

	@Override
	public void setUserLogin(JSONObject jsonObject) {
		this.user = new User(jsonObject, settings.getDatabaseSecretKey());
	}

	public void setUserlogout() {
		this.user = null;
	}

	public void settingsCreateNewFile(Language language, String password) {

		try {
			settingsCreateNewSetData(language, password);
			viewSupportPlannerHome();

		} catch (IOException e) {
			new AlertDesigner(Meta.Application.getFullTitle() + " Error:", e.getMessage(), viewSupportPlannerStage,
					AlertType.ERROR, Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
					Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").show();
		}
	}

	private void settingsCreateNewSetData(Language language, String password)
			throws InvalidFileFormatException, IOException {

		FileUtils.createFile(Meta.Settings.getFile(), true);
		this.settings = new Settings(new SettingsConf(Meta.Settings.getFile()));
		this.settings.setLanguage(language);

		SecretKey softwareKey = Crypt.generateKey(password);
		String passwordEncrypted = Crypt.encrypt(password, softwareKey);
		this.settings.setPasswordEncrypted(passwordEncrypted);
		this.settings.setApplicationKey(softwareKey);
		this.settings.setDefaultSettings();
		this.settings.save();
	}

	public void loadMenuSettingsList() {

		if (this.left != 1) {

			if (this.user != null)
				this.alertBuilder.information(viewSupportPlannerStage,
						this.settings.getLanguage().getString("sp.settings.logged")).show();

			this.left = 1;
			this.center = 0;
			this.viewSupportPlannerBorderPane.setCenter(null);

			try {

				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(Meta.Views.MENU_SETTINGS_LIST);
				AnchorPane layout = (AnchorPane) fxmlLoader.load();
				SettingsList ctrl = (SettingsList) fxmlLoader.getController();
				ctrl.setSettings(this.settings);
				ctrl.setCtrlViewSupportPlanner(this.ctrlViewSupportPlanner);
				ctrl.objectInitialize();

				this.viewSupportPlannerBorderPane.setLeft(layout);

			} catch (IOException e) {
			}
		}
	}

	public void loadMenuSettingDatabase() {

		if (this.center != 1) {
			this.center = 1;

			try {

				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(Meta.Views.MENU_SETTING_DB);
				AnchorPane layout = (AnchorPane) fxmlLoader.load();
				SettingDatabase ctrl = (SettingDatabase) fxmlLoader.getController();
				ctrl.setSettings(this.settings);
				ctrl.setOwnerStage(this.viewSupportPlannerStage);
				ctrl.setLoggedUser(this.user);
				ctrl.setApplication(this);
				ctrl.objectInitialize();

				this.viewSupportPlannerBorderPane.setCenter(layout);

			} catch (IOException e) {
			}
		}
	}

	public void loadMenuSettingUser() {

		if (this.center != 2) {
			this.center = 2;

			try {

				FXMLLoader fxmlLoader = new FXMLLoader();
				fxmlLoader.setLocation(Meta.Views.MENU_SETTING_USER);
				AnchorPane layout = (AnchorPane) fxmlLoader.load();
				SettingUser ctrl = (SettingUser) fxmlLoader.getController();
				ctrl.setSettings(this.settings);
				ctrl.setApplication(this);
				ctrl.setOwnerStage(this.viewSupportPlannerStage);
				ctrl.setLoggedUser(this.user);
				ctrl.objectInitialize();

				this.viewSupportPlannerBorderPane.setCenter(layout);

			} catch (IOException e) {
			}
		}
	}

	public void loadHome() {

		this.left = 0;
		this.viewSupportPlannerBorderPane.setLeft(null);
		this.center = 0;
		this.viewSupportPlannerBorderPane.setCenter(null);

		if (this.user != null)
			viewSupportPlannerHomeUser();
		else
			viewSupportPlannerHomeAccess();
	}

	public Stage getViewSupportPlannerStage() {
		return viewSupportPlannerStage;
	}

	public void setViewSupportPlannerStage(Stage viewSupportPlannerStage) {
		this.viewSupportPlannerStage = viewSupportPlannerStage;
	}

	public SupportPlannerView getCtrlViewSupportPlanner() {
		return ctrlViewSupportPlanner;
	}

	public void setCtrlViewSupportPlanner(SupportPlannerView ctrlViewSupportPlanner) {
		this.ctrlViewSupportPlanner = ctrlViewSupportPlanner;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public AlertBuilderOld getAlertBuilder() {
		return alertBuilder;
	}

	public void setAlertBuilder(AlertBuilderOld alertBuilder) {
		this.alertBuilder = alertBuilder;
	}

	public File getMysqlDump() {
		return mysqlDump;
	}

	public void setMysqlDump(File mysqlDump) {
		this.mysqlDump = mysqlDump;
	}

	public File getMysqlRestore() {
		return mysqlRestore;
	}

	public void setMysqlRestore(File mysqlRestore) {
		this.mysqlRestore = mysqlRestore;
	}

	public EnumOperatingSystem getSystem() {
		return system;
	}

	public void setSystem(EnumOperatingSystem system) {
		this.system = system;
	}

	public EnumOperatingSystemArchitecture getArchitecture() {
		return architecture;
	}

	public void setArchitecture(EnumOperatingSystemArchitecture architecture) {
		this.architecture = architecture;
	}

	public AlertBuilder getAlertBuilder2() {
		return alertBuilder2;
	}

	public void setAlertBuilder2(AlertBuilder alertBuilder2) {
		this.alertBuilder2 = alertBuilder2;
	}

	public Settings getSettings() {
		return settings;
	}

	public void setSettings(Settings settings) {
		this.settings = settings;
	}
}
