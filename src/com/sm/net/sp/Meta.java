package com.sm.net.sp;

import java.io.File;
import java.net.URL;

import com.sm.net.file.Extensions;
import com.sm.net.path.PathBuilder;
import com.sm.net.project.Project;
import com.sm.net.sp.view.SupportPlannerMenu;
import com.sm.net.sp.view.SupportPlannerView;
import com.sm.net.sp.view.check.access.CheckAccess;
import com.sm.net.sp.view.history.History;
import com.sm.net.sp.view.home.access.HomeAccess;
import com.sm.net.sp.view.home.user.menu.HomeUserMenuList;
import com.sm.net.sp.view.home.user.menu.circuitoverseer.UserMenuCircuitOverseer;
import com.sm.net.sp.view.home.user.menu.congr.UserMenuCongrList;
import com.sm.net.sp.view.home.user.menu.meetings.UserMenuMeetings;
import com.sm.net.sp.view.home.user.menu.monitor.UserMenuMonitor;
import com.sm.net.sp.view.home.user.menu.naturaldisaster.UserMenuNaturalDisasterList;
import com.sm.net.sp.view.home.user.menu.publicmeetings.UserMenuPublicMeetings;
import com.sm.net.sp.view.home.user.menu.sergroups.UserMenuSerGroupsList;
import com.sm.net.sp.view.home.user.menu.users.HomeUserMenuUsersList;
import com.sm.net.sp.view.home.user.menu.users.MenuUsersAdd;
import com.sm.net.sp.view.menu.settings.SettingsList;
import com.sm.net.sp.view.menu.settings.connection.SettingConnection;
import com.sm.net.sp.view.menu.settings.database.SettingDatabase;
import com.sm.net.sp.view.menu.settings.monitor.SettingMonitor;
import com.sm.net.sp.view.menu.settings.user.SettingUser;
import com.sm.net.sp.view.setting.create.access.SettingCreateAccess;
import com.sm.net.sp.view.setting.create.language.SettingCreateLanguage;
import com.sm.net.sp.view.wolbrowser.WOLBrowser;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class Meta {

	public static class Application {

		public static final String DEVELOPER = "SM-Net";

		public static final String NAME = "SupportPlanner";

		public static final String VERSION = "1.0 (beta 9)";

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
		public static final String KEY_USERMONITOR = "usermonitor";

		public static final String SECTION_MYSQL = "mysql";
		public static final String KEY_HOST = "host";
		public static final String KEY_DBNAME = "dbname";
		public static final String KEY_DBUSERNAME = "mysqluser";
		public static final String KEY_DBPASSWORD = "mysqlpass";
		
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
		public static final URL SUPPORTPLANNER_HOME_ACCESS = HomeAccess.class.getResource("HomeAccess.fxml");
		public static final URL SUPPORTPLANNER_MENU = SupportPlannerMenu.class.getResource("SupportPlannerMenu.fxml");
		public static final URL MENU_SETTINGS_LIST = SettingsList.class.getResource("SettingsList.fxml");
		public static final URL MENU_SETTING_CONNECTION = SettingConnection.class.getResource("SettingConnection.fxml");
		public static final URL MENU_SETTING_MONITOR = SettingMonitor.class.getResource("SettingMonitor.fxml");
		public static final URL MENU_SETTING_DB = SettingDatabase.class.getResource("SettingDatabase.fxml");
		public static final URL MENU_SETTING_DB_ROOT = SettingUser.class.getResource("SettingUserAddSuperuser.fxml");
		public static final URL MENU_SETTING_USER = SettingUser.class.getResource("SettingUser.fxml");
		public static final URL CHECK_ACCESS = CheckAccess.class.getResource("CheckAccess.fxml");
		public static final URL SETTING_CREATE_LANGUAGE = SettingCreateLanguage.class
				.getResource("SettingCreateLanguage.fxml");
		public static final URL SETTING_CREATE_ACCESS = SettingCreateAccess.class
				.getResource("SettingCreateAccess.fxml");
		public static final URL HOME_USER_MENU_LIST = HomeUserMenuList.class.getResource("HomeUserMenuList.fxml");
		public static final URL HOME_USER_MENU_USERLIST = HomeUserMenuUsersList.class
				.getResource("HomeUserMenuUsersList.fxml");
		public static final URL HOME_USER_MENU_USER_ADD = MenuUsersAdd.class.getResource("MenuUsersAdd.fxml");
		public static final URL HOME_USER_MENU_CONGRLIST = UserMenuCongrList.class
				.getResource("UserMenuCongrList.fxml");
		public static final URL HOME_USER_MENU_CONGR_MEMBER_EDITOR = UserMenuCongrList.class
				.getResource("UserMenuCongrMemberEditor.fxml");
		public static final URL HOME_USER_MENU_CONGR_FAMILY_EDITOR = UserMenuCongrList.class
				.getResource("UserMenuCongrFamilyEditor.fxml");
		public static final URL HOME_USER_MENU_SERGROUPS = UserMenuSerGroupsList.class
				.getResource("UserMenuSerGroupsList.fxml");
		public static final URL HOME_USER_MENU_SERGROUPS_EDITOR = UserMenuSerGroupsList.class
				.getResource("UserMenuSerGroupsEditor.fxml");
		public static final URL HOME_USER_MENU_MEETINGS = UserMenuMeetings.class.getResource("UserMenuMeetings.fxml");
		public static final URL HOME_USER_MENU_PUBLICMEETINGS = UserMenuPublicMeetings.class
				.getResource("UserMenuPublicMeetings.fxml");
		public static final URL HOME_USER_MENU_CIRCUITOVERSEER = UserMenuCircuitOverseer.class
				.getResource("UserMenuCircuitOverseer.fxml");
		public static final URL HOME_USER_MENU_MONITOR = UserMenuMonitor.class.getResource("UserMenuMonitor.fxml");
		public static final URL HOME_USER_MENU_NATURALDISASTER = UserMenuNaturalDisasterList.class
				.getResource("UserMenuNaturalDisasterList.fxml");
		public static final URL HOME_USER_MENU_NATURALDISASTER_MEMBER_EDITOR = UserMenuNaturalDisasterList.class
				.getResource("UserMenuNaturalDisasterMemberEditor.fxml");
		public static final URL HOME_USER_MENU_NATURALDISASTER_FAMILY_EDITOR = UserMenuNaturalDisasterList.class
				.getResource("UserMenuNaturalDisasterFamilyEditor.fxml");
		public static final URL HOME_USER_MENU_MEETINGS_EDITOR = UserMenuMeetings.class
				.getResource("UserMenuMeetingsEditor.fxml");
		public static final URL HOME_USER_MENU_PUBLICMEETINGS_EDITOR = UserMenuPublicMeetings.class
				.getResource("UserMenuPublicMeetingsEditor.fxml");
		public static final URL HOME_USER_MENU_MEETINGS_CIRCUITOVERSEER = UserMenuMeetings.class
				.getResource("UserMenuMeetingCircuitOverseer.fxml");
		public static final URL HOME_USER_MENU_CIRCUITOVERSEER_EDITOR = UserMenuCircuitOverseer.class
				.getResource("UserMenuCircuitOverseerEditor.fxml");
		public static final URL HOME_USER_MENU_MEETINGS_MINISTRY_PART_EDITOR = UserMenuMeetings.class
				.getResource("MinistryPartEditor.fxml");
		public static final URL HOME_USER_MENU_MEETINGS_CHRISTIANS_PART_EDITOR = UserMenuMeetings.class
				.getResource("ChristiansPartEditor.fxml");
		public static final URL HISTORY = History.class.getResource("History.fxml");
		public static final URL WOLBROWSER = WOLBrowser.class.getResource("WOLBrowser.fxml");
	}

	public static class Themes {
		public static final String SUPPORTPLANNER_THEME = SupportPlannerMain.class.getResource("theme.css")
				.toExternalForm();
	}

	public static class Resources {

		private static final String RESOURCES = "resources";

		public static final String ICON = "iconSP.png";
		public static final String PRINT = "print.png";
		public static final String MENU_APP = "menu_app.png";
		public static final String MENU_APP_SETTINGS = "menu_app_settings.png";
		public static final String MENU_SETTINGS_CONNECTION = "connection.png";
		public static final String MENU_SETTINGS_DB = "menu_settings_db.png";
		public static final String MENU_SETTINGS_USER = "menu_settings_user.png";
		public static final String MENU_BACK = "menu_back.png";
		public static final String HOME_ACCESS = "home_access.png";
		public static final String USER_MENU_LOGOUT = "logout.png";
		public static final String USER_MENU_USERS = "user_menu_users.png";
		public static final String USER_MENU_CONGR = "user_menu_congr.png";
		public static final String USER_MENU_USERS_ADD = "addUser_50x50.png";
		public static final String USER_MENU_USERS_DEL = "deleteUser_50x50.png";
		public static final String SUPERUSER = "superuser.png";
		public static final String SUPERUSER_PRINT = "superuserPrint.png";
		public static final String MEMBER = "person_35x35.png";
		public static final String MEMBER_ADD = "person_add_50x50.png";
		public static final String MEMBER_DEL = "person_delete_50x50.png";
		public static final String FAMILY = "family2_35x35.png";
		public static final String FAMILY_ADD = "family2_add_50x50.png";
		public static final String FAMILY_DEL = "family2_delete_50x50.png";
		public static final String PLUS = "plus_35x35.png";
		public static final String MEMBER_PERSONAL_INFO = "personal_info_45x35.png";
		public static final String MEMBER_PRIVILEGES = "privilege2_45x35.png";
		public static final String CONTACTS = "contacts_45x35.png";
		public static final String SAVE = "save_50x50.png";
		public static final String UPDATE = "update.png";
		public static final String ARROW_BACK = "arrow_back2_50x50.png";
		public static final String ARROW_FRONT = "arrow_front2_50x50.png";
		public static final String USER_MENU_SERVICEGROUPS = "bag.png";
		public static final String SERVICEGROUPS_GROUP = "group.png";
		public static final String SERVICEGROUPS_ADD = "sergroup_add_50x50.png";
		public static final String SERVICEGROUPS_DEL = "sergroup_del_50x50.png";
		public static final String USER_MENU_MEETINGS = "meetings.png";
		public static final String USER_MENU_CIRCUITOVERSEER = "overseer.png";
		public static final String USER_MENU_MONITOR = "monitor.png";
		public static final String USER_MENU_NATURALDISASTER = "naturaldis.png";
		public static final String INFO = "info.png";
		public static final String WOL = "wol.png";
		public static final String PUBLIC_TALK = "public_talk.png";
		public static final String CALENDAR = "calendar.png";
		public static final String TALKS = "talks.png";
		public static final String USER_MENU_MEETINGS_TREASURES = "treasures.png";
		public static final String USER_MENU_MEETINGS_MINISTRY = "ministry.png";
		public static final String USER_MENU_MEETINGS_CHRISTIANS = "christians.png";
		public static final String USER_MENU_MEETINGS_MINISTRY_ADD = "ministry_add.png";
		public static final String USER_MENU_MEETINGS_MINISTRY_DELETE = "ministry_delete.png";
		public static final String USER_MENU_MEETINGS_CHRISTIANS_ADD = "christians_add.png";
		public static final String USER_MENU_MEETINGS_CHRISTIANS_DELETE = "christians_delete.png";
		public static final String USER_MENU_MEETINGS_WOL_LOAD = "wol_load.png";
		public static final String USER_MENU_MEETINGS_WOL_VIEW = "wol_view.png";
		public static final String OK = "ok.png";
		public static final String HISTORY = "history.png";
		public static final String SEARCH = "search.png";
		public static final String FUTURE = "future.png";
		public static final String PAST = "past.png";
		public static final String PRESENT = "present.png";
		public static final String NOTHING = "nothing.png";
		public static final String CONNECTION_TEST = "connection_test.png";

		public static Image getImageFromResources(String imageName, double width, double height) {

			String absoulutePath = PathBuilder.concatFolder(Project.currentWorkingDirectory(), RESOURCES);
			absoulutePath = PathBuilder.concatFolder(absoulutePath, imageName);
			File file = new File(absoulutePath);

			if (file.exists())
				return new Image(file.toURI().toString(), width, height, true, true);

			return null;
		}

		public static Image getImageApplicationIcon() {
			return getImageFromResources(Meta.Resources.ICON, 48, 48);
		}

		public static Image getImageApplicationLogo() {
			return getImageLogo(Meta.Resources.ICON, 100, 100);
		}

		public static Image getImageLogo(String imageName, double width, double height) {
			return getImageFromResources(imageName, width, height);
		}

		public static ImageView imageViewForButton(String imageName) {

			Image image = getImageFromResources(imageName, 40, 40);
			ImageView imageView = new ImageView(image);

			return imageView;
		}

		public static ImageView imageForButtonSmall(String imageName) {

			Image image = getImageFromResources(imageName, 25, 25);
			ImageView imageView = new ImageView(image);

			return imageView;
		}

		public static Image imageForImage(String imageName) {
			return getImageFromResources(imageName, 50, 50);
		}

		public static Image imageForMenu(String imageName) {
			return getImageFromResources(imageName, 25, 25);
		}

		public static Image imageForWindowsIcon(String imageName) {
			return getImageFromResources(imageName, 48, 48);
		}

		public static StackPane imageInStackPaneForMenu(String imageName) {

			StackPane sp = new StackPane();
			sp.setPadding(new Insets(0.0, 10.0, 0.0, 0.0));

			Image image = getImageFromResources(imageName, 25, 25);
			ImageView imageView = new ImageView(image);

			sp.getChildren().add(imageView);

			return sp;
		}

		public static ImageView imageForTab(String imageName) {

			Image image = getImageFromResources(imageName, 40, 40);
			ImageView imageView = new ImageView(image);

			return imageView;
		}
	}
}
