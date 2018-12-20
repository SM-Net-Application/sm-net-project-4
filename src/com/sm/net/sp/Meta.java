package com.sm.net.sp;

import java.io.File;
import java.net.URL;

import com.sm.net.file.Extensions;
import com.sm.net.path.PathBuilder;
import com.sm.net.project.Project;
import com.sm.net.sp.view.SupportPlannerHome;
import com.sm.net.sp.view.SupportPlannerMenu;
import com.sm.net.sp.view.SupportPlannerView;
import com.sm.net.sp.view.check.access.CheckAccess;
import com.sm.net.sp.view.home.access.HomeAccess;
import com.sm.net.sp.view.home.user.HomeUser;
import com.sm.net.sp.view.menu.settings.SettingsList;
import com.sm.net.sp.view.menu.settings.database.SettingDatabase;
import com.sm.net.sp.view.menu.settings.user.SettingUser;
import com.sm.net.sp.view.setting.create.access.SettingCreateAccess;
import com.sm.net.sp.view.setting.create.language.SettingCreateLanguage;

import javafx.scene.image.Image;

public class Meta {

	public static class Application {

		public static final String DEVELOPER = "SM-Net";

		public static final String NAME = "SupportPlanner";

		public static final String VERSION = "1.0";

		public static String getFullTitle() {
			return DEVELOPER + ": " + NAME + " " + VERSION;
		}

		public static String getTitle() {
			return NAME + " " + VERSION;
		}

	}

	public static class Settings {

		public static final String FOLDER = "ini";

		public static final String FILE = "settings";

		public static final String SECTION_APPLICATION = "application";
		public static final String KEY_LANGUAGE = "language";
		public static final String KEY_PASSWORD = "password";

		public static final String SECTION_DATABASE = "database";
		public static final String KEY_URL = "url";
		public static final String KEY_DB_KEY = "key";

		public static final String SECTION_USER = "user";
		public static final String KEY_USERNAME = "username";
		public static final String KEY_USERPASS = "userpassword";

		public static File getFile() {

			String absoulutePath = PathBuilder.concatFolder(Project.currentWorkingDirectory(), FOLDER);
			absoulutePath = PathBuilder.concatFile(absoulutePath, FILE, Extensions.INI);

			return new File(absoulutePath);
		}
	}

	public static class Languages {

		public static final String FOLDER = "languages";

		public static File getDirectory() {
			return new File(PathBuilder.concatFolder(Project.currentWorkingDirectory(), FOLDER));
		}
	}

	public static class Views {

		public static final URL SUPPORTPLANNER_VIEW = SupportPlannerView.class.getResource("SupportPlannerView.fxml");
		public static final URL SUPPORTPLANNER_HOME = SupportPlannerHome.class.getResource("SupportPlannerHome.fxml");
		public static final URL SUPPORTPLANNER_HOME_ACCESS = HomeAccess.class.getResource("HomeAccess.fxml");
		public static final URL SUPPORTPLANNER_HOME_USER = HomeUser.class.getResource("HomeUser.fxml");
		public static final URL SUPPORTPLANNER_MENU = SupportPlannerMenu.class.getResource("SupportPlannerMenu.fxml");
		public static final URL MENU_SETTINGS_LIST = SettingsList.class.getResource("SettingsList.fxml");
		public static final URL MENU_SETTING_DB = SettingDatabase.class.getResource("SettingDatabase.fxml");
		public static final URL MENU_SETTING_USER = SettingUser.class.getResource("SettingUser.fxml");
		public static final URL CHECK_ACCESS = CheckAccess.class.getResource("CheckAccess.fxml");
		public static final URL SETTING_CREATE_LANGUAGE = SettingCreateLanguage.class
				.getResource("SettingCreateLanguage.fxml");
		public static final URL SETTING_CREATE_ACCESS = SettingCreateAccess.class
				.getResource("SettingCreateAccess.fxml");
	}

	public static class Themes {
		public static final String SUPPORTPLANNER_THEME = SupportPlannerMain.class.getResource("theme.css")
				.toExternalForm();
	}

	public static class Resources {

		public static final Image ICON = new Image(SupportPlannerMain.class.getResourceAsStream("resources/icon.png"));
		public static final Image MENU_APP = new Image(
				SupportPlannerMain.class.getResourceAsStream("resources/menu_app.png"));
		public static final Image MENU_APP_SETTINGS = new Image(
				SupportPlannerMain.class.getResourceAsStream("resources/menu_app_settings.png"));
		public static final Image MENU_SETTINGS_DB = new Image(
				SupportPlannerMain.class.getResourceAsStream("resources/menu_settings_db.png"));
		public static final Image MENU_SETTINGS_USER = new Image(
				SupportPlannerMain.class.getResourceAsStream("resources/menu_settings_user.png"));
		public static final Image MENU_BACK = new Image(
				SupportPlannerMain.class.getResourceAsStream("resources/menu_back.png"));
		public static final Image HOME_ACCESS = new Image(
				SupportPlannerMain.class.getResourceAsStream("resources/home_access.png"));
	}
}
