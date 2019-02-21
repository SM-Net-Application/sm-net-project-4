package com.sm.net.sp.view.home.user.menu;

import com.sm.net.sp.Meta;

import javafx.scene.image.Image;

public enum EnumHomeUserMenuList {

	USERS("USERMENU001", Meta.Resources.USER_MENU_USERS), CONGREGATION("USERMENU002",
			Meta.Resources.USER_MENU_CONGR), SERVICEGROUPS("USERMENU003",
					Meta.Resources.USER_MENU_SERVICEGROUPS), MEETINGS("USERMENU004", Meta.Resources.USER_MENU_MEETINGS);

	private String name;
	private Image image;

	private EnumHomeUserMenuList(String name, Image image) {
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