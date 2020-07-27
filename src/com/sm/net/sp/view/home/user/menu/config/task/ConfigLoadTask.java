package com.sm.net.sp.view.home.user.menu.config.task;

import java.io.IOException;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.sm.net.sp.json.JSONRequest;
import com.sm.net.sp.settings.Settings;
import com.sm.net.sp.view.home.user.menu.config.UserMenuConfig;
import com.smnet.core.dialog.AlertBuilder;
import com.smnet.core.task.TaskInterface;

import javafx.stage.Stage;

public class ConfigLoadTask implements TaskInterface {

	private UserMenuConfig view;
	private AlertBuilder alertBuilder;
	private Settings settings;
	private Stage viewStage;

	public ConfigLoadTask(AlertBuilder alertBuilder, Settings settings, Stage viewStage, UserMenuConfig view) {
		super();

		this.view = view;
		this.alertBuilder = alertBuilder;
		this.settings = settings;
		this.viewStage = viewStage;
	}

	@Override
	public void start(HashMap<String, Object> hashMap) {

		JSONObject json = JSONRequest.CONFIG_LOAD();
		String url = this.settings.getDatabaseUrl();

		try {

			Document post = Jsoup.connect(url).requestBody(json.toString()).post();
			JSONObject response = new JSONObject(post.body().text());

			hashMap.put("response", response);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void feedback(HashMap<String, Object> hashMap) {

		HashMap<String, String> configs = new HashMap<String, String>();

		JSONObject response = (JSONObject) hashMap.get("response");
		int status = response.getInt("status");

		if (status == 0) {

			JSONArray result = (JSONArray) response.get("result");
			for (Object obj : result) {

				JSONObject jsonObj = (JSONObject) obj;

				String key = jsonObj.getString("keyInf");
				String value = jsonObj.getString("inf");

				configs.put(key, value);
			}

		} else if (status == 1) {

			String error = response.getString("error");
			this.alertBuilder.error(this.viewStage, error);

		}

		this.view.setConfigs(configs);
		this.view.fill();
	}
}
