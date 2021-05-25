package com.sm.net.sp.view.menu.settings;

import com.sm.net.sp.Meta;

public enum EnumSettingsList {

	CONNECTION("sp.settings.connection", Meta.Resources.MENU_SETTINGS_CONNECTION),
	DATABASE("VIEW006MEN001", Meta.Resources.MENU_SETTINGS_DB),
	USER("VIEW006MEN002", Meta.Resources.MENU_SETTINGS_USER),
	MONITOR("sp.settings.monitor", Meta.Resources.USER_MENU_MONITOR),
	MODULES("sp.settings.modules", Meta.Resources.PDF);

	private String name;
	private String imageName;

	private EnumSettingsList(String name, String imageName) {
		this.name = name;
		this.imageName = imageName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
}
