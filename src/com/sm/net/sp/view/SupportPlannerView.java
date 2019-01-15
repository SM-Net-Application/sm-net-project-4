package com.sm.net.sp.view;

import java.io.File;
import java.io.IOException;

import javax.crypto.SecretKey;

import org.ini4j.InvalidFileFormatException;
import org.json.JSONObject;

import com.sm.net.directory.Directory;
import com.sm.net.file.Extensions;
import com.sm.net.file.FileUtils;
import com.sm.net.javafx.AlertDesigner;
import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.model.User;
import com.sm.net.sp.settings.Settings;
import com.sm.net.sp.settings.SettingsConf;
import com.sm.net.sp.view.check.access.CheckAccess;
import com.sm.net.sp.view.home.access.HomeAccess;
import com.sm.net.sp.view.home.user.menu.HomeUserMenuList;
import com.sm.net.sp.view.home.user.menu.congr.UserMenuCongrList;
import com.sm.net.sp.view.home.user.menu.users.HomeUserMenuUsersList;
import com.sm.net.sp.view.menu.settings.SettingsList;
import com.sm.net.sp.view.menu.settings.database.SettingDatabase;
import com.sm.net.sp.view.menu.settings.user.SettingUser;
import com.sm.net.sp.view.setting.create.language.SettingCreateLanguage;
import com.sm.net.util.Crypt;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SupportPlannerView implements SupportPlannerCallback {

	@FXML
	private BorderPane viewSupportPlannerBorderPane;

	private Stage viewSupportPlannerStage;
	private SupportPlannerView ctrlViewSupportPlanner;
	private User user;

	private Settings settings;
	private int left;
	private int center;

	public void objectInitialize() {

		this.left = 0;
		this.center = 0;
		this.user = null;

		this.settings = null;

		settingsInitialize();
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
					AlertType.ERROR, Meta.Application.getFullTitle(), Meta.Resources.ICON).show();

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
					viewSupportPlannerStage, AlertType.ERROR, Meta.Application.getFullTitle(), Meta.Resources.ICON)
							.show();

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
			this.viewSupportPlannerStage.setMinWidth(500);
			this.viewSupportPlannerStage.setMaxWidth(500);
			this.viewSupportPlannerStage.setMinHeight(500);
			this.viewSupportPlannerStage.setMaxHeight(500);
			this.viewSupportPlannerStage.setMaximized(false);
			this.viewSupportPlannerStage.setResizable(false);

		} catch (IOException e) {
		}
	}

	public void viewSettingCreateAccess(AnchorPane accessCreateViewAnchorPane) {

		this.viewSupportPlannerBorderPane.setCenter(accessCreateViewAnchorPane);
		this.viewSupportPlannerStage.setMinWidth(500);
		this.viewSupportPlannerStage.setMaxWidth(500);
		this.viewSupportPlannerStage.setMinHeight(300);
		this.viewSupportPlannerStage.setMaxHeight(300);
		this.viewSupportPlannerStage.setMaximized(false);
		this.viewSupportPlannerStage.setResizable(false);
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
			//this.viewSupportPlannerStage.setMinWidth(500);
			//this.viewSupportPlannerStage.setMaxWidth(500);
			this.viewSupportPlannerStage.setMinHeight(175);
			this.viewSupportPlannerStage.setMaxHeight(175);
			this.viewSupportPlannerStage.setMaximized(false);
			this.viewSupportPlannerStage.setResizable(false);

		} catch (IOException e) {
		}
	}

	public void viewSupportPlannerHome() {

		this.viewSupportPlannerStage.setMinWidth(1000);
		this.viewSupportPlannerStage.setMaxWidth(Double.MAX_VALUE);
		this.viewSupportPlannerStage.setMinHeight(500);
		this.viewSupportPlannerStage.setMaxHeight(Double.MAX_VALUE);
		this.viewSupportPlannerStage.setResizable(true);
		// this.viewSupportPlannerStage.setMaximized(true);

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
				ctrl.objectInitialize();

				this.viewSupportPlannerBorderPane.setCenter(layout);

			} catch (IOException e) {
				e.printStackTrace();
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
					AlertType.ERROR, Meta.Application.getFullTitle(), Meta.Resources.ICON).show();
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
				// ctrl.setCtrlViewSupportPlanner(this.ctrlViewSupportPlanner);
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
}
