package com.sm.net.sp.view.home.user.menu;

import com.sm.net.sp.Meta;

public enum EnumHomeUserMenuList {

	USERS("USERMENU001", Meta.Resources.USER_MENU_USERS),

	CONGREGATION("USERMENU002", Meta.Resources.USER_MENU_CONGR),

	SERVICEGROUPS("USERMENU003", Meta.Resources.USER_MENU_SERVICEGROUPS),

	MEETINGS("USERMENU004", Meta.Resources.USER_MENU_MEETINGS),

	PUBLIC_TALK("sp.menu.publictalk", Meta.Resources.PUBLIC_TALK),

	CIRCUITOVERSEERS("USERMENU005", Meta.Resources.USER_MENU_CIRCUITOVERSEER),

	NATURAL_DISASTER("sp.menu.naturaldisaster", Meta.Resources.USER_MENU_NATURALDISASTER),

	MONITOR("sp.menu.monitor", Meta.Resources.USER_MENU_MONITOR);

	private String name;
	private String imageName;

	private EnumHomeUserMenuList(String name, String imageName) {
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

	public void setImage(String imageName) {
		this.imageName = imageName;
	}
}