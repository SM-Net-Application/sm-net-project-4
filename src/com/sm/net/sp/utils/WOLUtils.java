package com.sm.net.sp.utils;

import org.jsoup.nodes.Element;

public class WOLUtils {

	public static final String ADDRESS = "https://wol.jw.org";

	public static WOLItemDataType checkItemData(Element element) {

		if (element.getElementsByClass("themeScrp").size() == 1)
			return WOLItemDataType.SCRIPTURE_OF_THE_DAY;

		if (element.getElementsByClass("section").size() > 0)
			return WOLItemDataType.MEETING;

		if (element.getElementsByClass("groupTOC").size() == 1) {
			return WOLItemDataType.WATCHTOWER_STUDY;
		}

		return WOLItemDataType.UNKNOW;
	}

	public enum WOLItemDataType {
		UNKNOW, SCRIPTURE_OF_THE_DAY, MEETING, WATCHTOWER_STUDY;
	}
}
