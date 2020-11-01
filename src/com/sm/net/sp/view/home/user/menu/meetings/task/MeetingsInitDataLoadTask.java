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
import com.sm.net.sp.model.WeekAudio;
import com.sm.net.sp.model.WeekConvention;
import com.sm.net.sp.model.WeekMemorial;
import com.sm.net.sp.model.WeekUsciere;
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
		JSONObject jsonConvention = JSONRequest.CONVENTION_LOAD();
		JSONObject jsonMemorial = JSONRequest.MEMORIAL_LOAD();
		JSONObject jsonAudio = JSONRequest.AUDIO_LOAD();
		JSONObject jsonUsciere = JSONRequest.USCIERE_LOAD();

		post(hashMap, url, jsonDateAndTime, "responseDateAndTime");
		post(hashMap, url, jsonPlaces, "responsePlaces");
		post(hashMap, url, jsonConfig, "responseConfig");
		post(hashMap, url, jsonConvention, "responseConvention");
		post(hashMap, url, jsonMemorial, "responseMemorial");
		post(hashMap, url, jsonAudio, "responseAudio");
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

		ObservableList<DateAndTime> dateAndTimeList = dateAndTimeFeedback(hashMap);
		ObservableList<Place> placesList = placesFeedback(hashMap);
		HashMap<String, String> configs = configFeedback(hashMap);
		ObservableList<WeekConvention> convention = conventionFeedback(hashMap);
		ObservableList<WeekMemorial> memorial = memorialFeedback(hashMap);
		ObservableList<WeekAudio> audio = audioFeedback(hashMap);
		ObservableList<WeekUsciere> usciere = usciereFeedback(hashMap);

		this.view.setDateAndTimeList(dateAndTimeList);
		this.view.setPlacesList(placesList);
		this.view.setConfigs(configs);
		this.view.setConvention(convention);
		this.view.setMemorial(memorial);
		this.view.setAudio(audio);
		this.view.setUsciere(usciere);
	}

	private ObservableList<WeekMemorial> memorialFeedback(HashMap<String, Object> hashMap) {

		ObservableList<WeekMemorial> list = FXCollections.observableArrayList();

		JSONObject response = (JSONObject) hashMap.get("responseMemorial");
		if (response != null) {

			int status = response.getInt("status");

			if (status == 0) {

				JSONArray result = (JSONArray) response.get("result");
				for (Object obj : result) {

					JSONObject json = (JSONObject) obj;
					WeekMemorial convention = WeekMemorial.newInstanceByJSONObject(json,
							this.settings.getDatabaseSecretKey());
					list.add(convention);
				}

			}
		}

		return list;
	}

	private ObservableList<WeekAudio> audioFeedback(HashMap<String, Object> hashMap) {

		ObservableList<WeekAudio> list = FXCollections.observableArrayList();

		JSONObject response = (JSONObject) hashMap.get("responseAudio");
		if (response != null) {

			int status = response.getInt("status");

			if (status == 0) {

				JSONArray result = (JSONArray) response.get("result");
				for (Object obj : result) {

					JSONObject json = (JSONObject) obj;
					WeekAudio w = WeekAudio.newInstanceByJSONObject(json, this.settings.getDatabaseSecretKey());
					list.add(w);
				}

			}
		}

		return list;
	}

	private ObservableList<WeekUsciere> usciereFeedback(HashMap<String, Object> hashMap) {

		ObservableList<WeekUsciere> list = FXCollections.observableArrayList();

		JSONObject response = (JSONObject) hashMap.get("responseUsciere");
		if (response != null) {

			int status = response.getInt("status");

			if (status == 0) {

				JSONArray result = (JSONArray) response.get("result");
				for (Object obj : result) {

					JSONObject json = (JSONObject) obj;
					WeekUsciere w = WeekUsciere.newInstanceByJSONObject(json, this.settings.getDatabaseSecretKey());
					list.add(w);
				}

			}
		}

		return list;
	}

	private ObservableList<WeekConvention> conventionFeedback(HashMap<String, Object> hashMap) {

		ObservableList<WeekConvention> list = FXCollections.observableArrayList();

		JSONObject response = (JSONObject) hashMap.get("responseConvention");
		if (response != null) {

			int status = response.getInt("status");

			if (status == 0) {

				JSONArray result = (JSONArray) response.get("result");
				for (Object obj : result) {

					JSONObject json = (JSONObject) obj;
					WeekConvention convention = WeekConvention.newInstanceByJSONObject(json,
							this.settings.getDatabaseSecretKey());
					list.add(convention);
				}
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

	private ObservableList<DateAndTime> dateAndTimeFeedback(HashMap<String, Object> hashMap) {

		ObservableList<DateAndTime> dateAndTimeList = FXCollections.observableArrayList();

		JSONObject response = (JSONObject) hashMap.get("responseDateAndTime");

		if (response != null) {

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
		}

		return dateAndTimeList;
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
