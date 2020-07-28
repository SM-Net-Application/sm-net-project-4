package com.sm.net.sp.view.home.user.menu.meetings.task;

import java.io.IOException;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.sm.net.sp.json.JSONRequest;
import com.sm.net.sp.model.DateAndTime;
import com.sm.net.sp.model.Place;
import com.sm.net.sp.settings.Settings;
import com.sm.net.sp.view.home.user.menu.meetings.UserMenuMeetings;
import com.smnet.core.dialog.AlertBuilder;
import com.smnet.core.task.TaskInterface;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

public class MeetingsInitDataLoadTask implements TaskInterface {

	private UserMenuMeetings view;
	private AlertBuilder alertBuilder;
	private Settings settings;
	private Stage stage;

	public MeetingsInitDataLoadTask(AlertBuilder alertBuilder, Settings settings, Stage stage, UserMenuMeetings view) {
		super();

		this.view = view;
		this.alertBuilder = alertBuilder;
		this.settings = settings;
		this.stage = stage;
	}

	@Override
	public void start(HashMap<String, Object> hashMap) {

		String url = this.settings.getDatabaseUrl();

		JSONObject jsonDateAndTime = JSONRequest.DATE_AND_TIME_LOAD();
		JSONObject jsonPlaces = JSONRequest.PLACES_LOAD();
		JSONObject jsonConfig = JSONRequest.CONFIG_LOAD();

		post(hashMap, url, jsonDateAndTime, "responseDateAndTime");
		post(hashMap, url, jsonPlaces, "responsePlaces");
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

		ObservableList<DateAndTime> dateAndTimeList = dateAndTimeFeedback(hashMap);
		ObservableList<Place> placesList = placesFeedback(hashMap);
		HashMap<String, String> configs = configFeedback(hashMap);

		this.view.setDateAndTimeList(dateAndTimeList);
		this.view.setPlacesList(placesList);
		this.view.setConfigs(configs);
	}

	private ObservableList<Place> placesFeedback(HashMap<String, Object> hashMap) {

		ObservableList<Place> places = FXCollections.observableArrayList();

		JSONObject response = (JSONObject) hashMap.get("responsePlaces");
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

		return places;
	}

	private ObservableList<DateAndTime> dateAndTimeFeedback(HashMap<String, Object> hashMap) {

		ObservableList<DateAndTime> dateAndTimeList = FXCollections.observableArrayList();

		JSONObject response = (JSONObject) hashMap.get("responseDateAndTime");
		int status = response.getInt("status");

		if (status == 0) {

			JSONArray result = (JSONArray) response.get("result");
			for (Object obj : result) {

				JSONObject json = (JSONObject) obj;
				DateAndTime dateAndTime = DateAndTime.newInstanceByJSONObject(json);
				dateAndTimeList.add(dateAndTime);
			}

		} else if (status == 1) {

			String error = response.getString("error");
			this.alertBuilder.error(this.stage, error);

		}

		return dateAndTimeList;
	}

	private HashMap<String, String> configFeedback(HashMap<String, Object> hashMap) {

		HashMap<String, String> configs = new HashMap<>();

		JSONObject response = (JSONObject) hashMap.get("responseConfig");
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

		return configs;
	}
}
