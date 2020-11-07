package com.sm.net.sp.view.home.user.menu.audio.task;

import java.io.IOException;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.sm.net.sp.json.JSONRequest;
import com.sm.net.sp.model.WeekAudio;
import com.sm.net.sp.model.WeekUsciere;
import com.sm.net.sp.settings.Settings;
import com.sm.net.sp.view.home.user.menu.audio.Audio;
import com.smnet.core.dialog.AlertBuilder;
import com.smnet.core.task.TaskInterface;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

public class AudioInitDataLoadTask implements TaskInterface {

	private Audio view;
	private AlertBuilder alertBuilder;
	private Settings settings;
	private Stage stage;

	public AudioInitDataLoadTask(AlertBuilder alertBuilder, Settings settings, Stage stage, Audio view) {
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
		JSONObject jsonUsciere = JSONRequest.USCIERE_LOAD();

		post(hashMap, url, jsonConfig, "responseConfig");
		post(hashMap, url, jsonUsciere, "responseUsciere");

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
		ObservableList<WeekUsciere> databaseWeeksUsciere = usciereFeedback(hashMap);

		this.view.setConfigs(configs);
		this.view.setDatabaseWeeksUsciere(databaseWeeksUsciere);
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

	private ObservableList<WeekUsciere> usciereFeedback(HashMap<String, Object> hashMap) {

		ObservableList<WeekUsciere> list = FXCollections.observableArrayList();

		JSONObject response = (JSONObject) hashMap.get("responseUsciere");
		int status = response.getInt("status");

		if (status == 0) {

			JSONArray result = (JSONArray) response.get("result");
			for (Object obj : result) {

				JSONObject json = (JSONObject) obj;
				WeekUsciere w = WeekUsciere.newInstanceByJSONObject(json, this.settings.getDatabaseSecretKey());
				list.add(w);
			}
		}

		return list;
	}
}
