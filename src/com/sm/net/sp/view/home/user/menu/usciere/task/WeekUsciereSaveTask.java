package com.sm.net.sp.view.home.user.menu.usciere.task;

import java.io.IOException;
import java.util.HashMap;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.sm.net.sp.json.JSONRequest;
import com.sm.net.sp.model.WeekUsciere;
import com.sm.net.sp.settings.Settings;
import com.sm.net.sp.view.home.user.menu.usciere.Usciere;
import com.smnet.core.dialog.AlertBuilder;
import com.smnet.core.task.TaskInterface;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class WeekUsciereSaveTask implements TaskInterface {

	private Usciere view;
	private AlertBuilder alertBuilder;
	private Settings settings;
	private Stage viewStage;
	private WeekUsciere week;
	private Tab thisTab;

	public WeekUsciereSaveTask(AlertBuilder alertBuilder, Settings settings, Stage viewStage, WeekUsciere week,
			Usciere view, Tab thisTab) {
		super();

		this.view = view;
		this.alertBuilder = alertBuilder;
		this.settings = settings;
		this.viewStage = viewStage;
		this.week = week;
		this.thisTab = thisTab;
	}

	@Override
	public void start(HashMap<String, Object> hashMap) {

		JSONObject json = null;
		if (this.week.spUscIDProperty() != null)
			json = JSONRequest.USCIERE_UPDATE(this.week);
		else
			json = JSONRequest.USCIERE_INSERT(this.week);

		String url = this.settings.getDatabaseUrl();

		try {

			Document post = Jsoup.connect(url).requestBody(json.toString()).post();

			JSONObject response = new JSONObject(post.body().text());

			int status = response.getInt("status");
			String error = response.getString("error");

			hashMap.put("status", status);
			hashMap.put("error", error);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void feedback(HashMap<String, Object> hashMap) {

		int status = (int) hashMap.get("status");
		String error = (String) hashMap.get("error");

		if (status == 0) {

			this.view.updateWeeksData();

			TabPane tabPane = this.view.getTabPane();
			tabPane.getTabs().remove(this.thisTab);

		} else if (status == 1)
			this.alertBuilder.error(this.viewStage, error);
		else if (status == 2)
			this.alertBuilder.error(this.viewStage, this.settings.getLanguage().getString(error));
	}
}
