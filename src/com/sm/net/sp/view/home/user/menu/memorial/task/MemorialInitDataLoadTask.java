package com.sm.net.sp.view.home.user.menu.memorial.task;

import java.io.IOException;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.sm.net.sp.json.JSONRequest;
import com.sm.net.sp.model.Place;
import com.sm.net.sp.model.Song;
import com.sm.net.sp.settings.Settings;
import com.sm.net.sp.view.home.user.menu.memorial.Memorial;
import com.sm.net.util.Crypt;
import com.smnet.core.dialog.AlertBuilder;
import com.smnet.core.task.TaskInterface;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

public class MemorialInitDataLoadTask implements TaskInterface {

	private Memorial view;
	private AlertBuilder alertBuilder;
	private Settings settings;
	private Stage stage;

	public MemorialInitDataLoadTask(AlertBuilder alertBuilder, Settings settings, Stage stage, Memorial view) {
		super();

		this.view = view;
		this.alertBuilder = alertBuilder;
		this.settings = settings;
		this.stage = stage;
	}

	@Override
	public void start(HashMap<String, Object> hashMap) {

		String url = this.settings.getDatabaseUrl();

		JSONObject jsonPlaces = JSONRequest.PLACES_LOAD();
		JSONObject jsonConfig = JSONRequest.CONFIG_LOAD();
		JSONObject jsonSongs = JSONRequest.SONGS_LOAD();

		post(hashMap, url, jsonPlaces, "responsePlaces");
		post(hashMap, url, jsonConfig, "responseConfig");
		post(hashMap, url, jsonSongs, "responseSongs");

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

		ObservableList<Place> placesList = placesFeedback(hashMap);
		HashMap<String, String> configs = configFeedback(hashMap);
		ObservableList<Song> songs = songsFeedback(hashMap);

		this.view.setPlacesList(placesList);
		this.view.setConfigs(configs);
		this.view.setSongList(songs);
		this.view.checkConfigs();
	}

	private ObservableList<Song> songsFeedback(HashMap<String, Object> hashMap) {

		ObservableList<Song> list = FXCollections.observableArrayList();

		JSONObject response = (JSONObject) hashMap.get("responseSongs");
		if (response != null) {

			int status = response.getInt("status");

			if (status == 0) {

				JSONArray result = (JSONArray) response.get("result");
				for (Object obj : result) {

					JSONObject jsonObj = (JSONObject) obj;

					String numberEncrypted = jsonObj.getString("spInf1");
					String titleEncrypted = jsonObj.getString("spInf2");

					int number = Integer.valueOf(Crypt.decrypt(numberEncrypted, this.settings.getDatabaseSecretKey()));
					String title = Crypt.decrypt(titleEncrypted, this.settings.getDatabaseSecretKey());

					list.add(new Song(number, title));
				}

				list.sort((s1, s2) -> s1.getNumber().compareTo(s2.getNumber()));
			}
		}

		return list;
	}

	private ObservableList<Place> placesFeedback(HashMap<String, Object> hashMap) {

		ObservableList<Place> places = FXCollections.observableArrayList();

		JSONObject response = (JSONObject) hashMap.get("responsePlaces");
		if (response != null) {

			int status = response.getInt("status");

			if (status == 0) {

				JSONArray result = (JSONArray) response.get("result");
				for (Object obj : result) {

					JSONObject json = (JSONObject) obj;
					Place place = Place.newInstanceByJSONObject(json, this.settings.getDatabaseSecretKey());
					places.add(place);
				}

			} else if (status == 1) {

				String error = response.getString("error");
				this.alertBuilder.error(this.stage, error);

			}
		}

		return places;
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
