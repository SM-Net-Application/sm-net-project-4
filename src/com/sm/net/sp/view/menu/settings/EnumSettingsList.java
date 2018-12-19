package com.sm.net.sp.view.menu.settings;

import com.sm.net.sp.Meta;

import javafx.scene.image.Image;

public enum EnumSettingsList {

	DATABASE("VIEW006MEN001", Meta.Resources.MENU_SETTINGS_DB), USER("VIEW006MEN002",
			Meta.Resources.MENU_SETTINGS_USER);

	private String name;
	private Image image;

	private EnumSettingsList(String name, Image image) {
		this.name = name;
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
}
