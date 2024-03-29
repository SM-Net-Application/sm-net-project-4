package com.sm.net.sp.view.home.user.menu;

import com.sm.net.sp.Meta;

public enum EnumHomeUserMenuList {

	USERS("USERMENU001", Meta.Resources.USER_MENU_USERS),

	INFO("sp.menu.info", Meta.Resources.GENERALINFO),

	DATE_AND_TIME("sp.menu.dateandtime", Meta.Resources.TIME),

	PLACES("sp.menu.places", Meta.Resources.PLACES),

	CONGREGATION("USERMENU002", Meta.Resources.USER_MENU_CONGR),

	SERVICEGROUPS("USERMENU003", Meta.Resources.USER_MENU_SERVICEGROUPS),

	MEETINGS("USERMENU004", Meta.Resources.USER_MENU_MEETINGS),

	AUDIO("sp.menu.audio", Meta.Resources.AUDIO),

	USCIERE("sp.menu.usciere", Meta.Resources.USCIERE),

	PUBLIC_TALK("sp.menu.publictalk", Meta.Resources.PUBLIC_TALK),

	SERVICE_MEETINGS_CONGR("sp.menu.servicemeetingcongr", Meta.Resources.DOOR_CONGR),

	SERVICE_MEETINGS_GROUPS("sp.menu.servicemeetinggroups", Meta.Resources.DOOR_GROUPS),

	CLEAN("sp.menu.clean", Meta.Resources.CLEAN),

	CIRCUITOVERSEERS("USERMENU005", Meta.Resources.USER_MENU_CIRCUITOVERSEER),

	CONVENTIONS("sp.menu.conventions", Meta.Resources.CONVENTIONS),

	MEMORIAL("sp.menu.memorial", Meta.Resources.MEMORIAL),

	NATURAL_DISASTER("sp.menu.naturaldisaster", Meta.Resources.USER_MENU_NATURALDISASTER),
	
	TERRITORY("sp.menu.territory", Meta.Resources.TERRITORY),

	POST("sp.menu.post", Meta.Resources.POST),

	INFOTABLE("sp.menu.infotable", Meta.Resources.INFOTABLE),

	MONITOR("sp.menu.monitor", Meta.Resources.USER_MENU_MONITOR),

	CONFIG("sp.menu.config", Meta.Resources.CONFIG),

	DATABASE("sp.menu.database", Meta.Resources.DATABASE);

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