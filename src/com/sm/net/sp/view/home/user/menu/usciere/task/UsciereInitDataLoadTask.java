package com.sm.net.sp.view.home.user.menu.usciere.task;

import java.io.IOException;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.sm.net.sp.json.JSONRequest;
import com.sm.net.sp.settings.Settings;
import com.sm.net.sp.view.home.user.menu.audio.Audio;
import com.sm.net.sp.view.home.user.menu.usciere.Usciere;
import com.smnet.core.dialog.AlertBuilder;
import com.smnet.core.task.TaskInterface;

import javafx.stage.Stage;

public class UsciereInitDataLoadTask implements TaskInterface {

	private Usciere view;
	private AlertBuilder alertBuilder;
	private Settings settings;
	private Stage stage;

	public UsciereInitDataLoadTask(AlertBuilder alertBuilder, Settings settings, Stage stage, Usciere view) {
		super();

		this.view = view;
		this.alertBuilder = alertBuilder;
		this.settings = settings;
		this.stage = stage;
	}

	@Override
	public void start(HashMap<String, Object> hashMap) {

		String url = this.settings.getDatabaseUrl();

		JSONObject jsonConfig = JSONRequest.CONFIG_LOAD();

		post(hashMap, url, jsonConfig, "responseConfig");

	}

	private void post(HashMap<String, Object> hashMap, String url, JSONObject json, String keyResponse) {

		try {

			Document post = Jsoup.connect(url).requestBody(json.toString()).post();
			JSONObject response = new JSONObject(post.body().text());

			hashMap.put(keyResponse, response);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void feedback(HashMap<String, Object> hashMap) {

		HashMap<String, String> configs = configFeedback(hashMap);

		this.view.setConfigs(configs);
		this.view.updateWeeksData();
	}

	private HashMap<String, String> configFeedback(HashMap<String, Object> hashMap) {

		HashMap<String, String> configs = new HashMap<>();

		JSONObject response = (JSONObject) hashMap.get("responseConfig");
		if (response != null) {
			int status = response.getInt("status");

			if (status == 0) {

				JSONArray result = (JSONArray) response.get("result");
				for (Object obj : result) {

					JSONObject jsonObj = (JSONObject) obj;

					String key = jsonObj.getString("keyInf");
					String value = jsonObj.getString("inf");

					configs.put(key, value);
				}
			}
		}

		return configs;
	}
}
