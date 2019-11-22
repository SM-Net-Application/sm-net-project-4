package com.sm.net.sp.view.wolbrowser;

import com.sm.net.jw.wol.WatchtowerOnlineLibrary;
import com.sm.net.project.Language;
import com.sm.net.sp.model.Week;

import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class WOLBrowser {

	@FXML
	private WebView browser;

	private Language language;
	private Week week;

	public void init() {

		String wolLink = WatchtowerOnlineLibrary.createLink(this.language, this.week.getFrom());

		WebEngine engine = this.browser.getEngine();
		engine.load(wolLink);
	}

	public Week getWeek() {
		return week;
	}

	public void setWeek(Week week) {
		this.week = week;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

}
