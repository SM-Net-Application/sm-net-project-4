package com.sm.net.sp;

import java.io.File;

import com.sm.net.sp.settings.SettingsConf;
import com.sm.net.util.FilesFolders;

import javafx.scene.image.Image;

public class Meta {

	public static final String APP_NAME = "SupportPlanner";

	public static final String APP_VERSION = "1.0";

	public static final String SETTINGS_FOLDER = "ini";

	public static final String SETTINGS_FILE = "settings";

	public static final Image ICON = new Image(Meta.class.getResourceAsStream("res/icon.png"));

	public static String getAppName() {
		return APP_NAME + " " + APP_VERSION;
	}

	public static File getFileSettings() {
		return new File(FilesFolders.concat(Meta.SETTINGS_FOLDER, Meta.SETTINGS_FILE + SettingsConf.FILE_EXT));
	}

}
