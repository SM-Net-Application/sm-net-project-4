package com.sm.net.app;

import javafx.scene.image.Image;

public class AppInfos {

	public static final String APP_NAME = "SupportPlanner Tools";

	public static final String APP_VERSION = "1.0";

	public static final Image ICON = new Image(AppInfos.class.getResourceAsStream("icon.png"));

	public static String getAppName() {
		return APP_NAME + " " + APP_VERSION;
	}
}
