package com.sm.net.sp.view.browser;

import javafx.fxml.FXML;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class Browser {

	@FXML
	private WebView browser;

	private String link;

	public void init() {

		WebEngine engine = this.browser.getEngine();
		engine.load(this.link);
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

}
