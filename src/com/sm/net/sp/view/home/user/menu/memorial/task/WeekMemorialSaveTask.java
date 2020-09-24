package com.sm.net.sp.view.home.user.menu.memorial.task;

import java.io.IOException;
import java.util.HashMap;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.sm.net.sp.json.JSONRequest;
import com.sm.net.sp.model.WeekConvention;
import com.sm.net.sp.model.WeekMemorial;
import com.sm.net.sp.settings.Settings;
import com.sm.net.sp.view.home.user.menu.conven.Convention;
import com.sm.net.sp.view.home.user.menu.memorial.Memorial;
import com.smnet.core.dialog.AlertBuilder;
import com.smnet.core.task.TaskInterface;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class WeekMemorialSaveTask implements TaskInterface {

	private Memorial view;
	private AlertBuilder alertBuilder;
	private Settings settings;
	private Stage viewStage;
	private WeekMemorial weekMemorial;
	private Tab thisTab;

	public WeekMemorialSaveTask(AlertBuilder alertBuilder, Settings settings, Stage viewStage,
			WeekMemorial weekMemorial, Memorial view, Tab thisTab) {
		super();

		this.view = view;
		this.alertBuilder = alertBuilder;
		this.settings = settings;
		this.viewStage = viewStage;
		this.weekMemorial = weekMemorial;
		this.thisTab = thisTab;
	}

	@Override
	public void start(HashMap<String, Object> hashMap) {

		JSONObject json = null;
		if (this.weekMemorial.spMemorialIDProperty() != null)
			json = JSONRequest.MEMORIAL_UPDATE(this.weekMemorial);
		else
			json = JSONRequest.MEMORIAL_INSERT(this.weekMemorial);

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
