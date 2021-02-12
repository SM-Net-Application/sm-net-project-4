package com.sm.net.sp.view.home.user.menu.infotable.task;

import java.io.IOException;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.sm.net.sp.json.JSONRequest;
import com.sm.net.sp.model.PostNews;
import com.sm.net.sp.model.Week;
import com.sm.net.sp.model.WeekConvention;
import com.sm.net.sp.model.WeekMemorial;
import com.sm.net.sp.model.WeekOverseer;
import com.sm.net.sp.settings.Settings;
import com.sm.net.sp.view.home.user.menu.infotable.InfoTable;
import com.sm.net.util.Crypt;
import com.smnet.core.dialog.AlertBuilder;
import com.smnet.core.task.TaskInterface;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

public class InfoTableInitDataLoadTask implements TaskInterface {

	private InfoTable view;
	private AlertBuilder alertBuilder;
	private Settings settings;
	private Stage stage;

	public InfoTableInitDataLoadTask(AlertBuilder alertBuilder, Settings settings, Stage stage, InfoTable view) {
		super();

		this.view = view;
		this.alertBuilder = alertBuilder;
		this.settings = settings;
		this.stage = stage;
	}

	@Override
	public void start(HashMap<String, Object> hashMap) {

		String url = this.settings.getDatabaseUrl();

		JSONObject jsonInfoTable = JSONRequest.INFOTABLE_LOAD();
		JSONObject jsonConvention = JSONRequest.CONVENTION_LOAD();
		JSONObject jsonMemorial = JSONRequest.MEMORIAL_LOAD();
		JSONObject jsonGeneralInfo = JSONRequest.GENERAL_INFO_LOAD();
		JSONObject jsonOverseer = JSONRequest.OVERSEER_LOAD();
		JSONObject jsonMeeting = JSONRequest.MEETING_LOAD();

		post(hashMap, url, jsonInfoTable, "responseInfoTable");
		post(hashMap, url, jsonConvention, "responseConvention");
		post(hashMap, url, jsonMemorial, "responseMemorial");
		post(hashMap, url, jsonGeneralInfo, "responseGeneralInfo");
		post(hashMap, url, jsonOverseer, "responseOverseer");
		post(hashMap, url, jsonMeeting, "responseMeeting");
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

		ObservableList<PostNews> postNewsList = postNewsListFeedback(hashMap);
		ObservableList<WeekConvention> convention = conventionFeedback(hashMap);
		ObservableList<WeekMemorial> memorial = memorialFeedback(hashMap);
		HashMap<String, String> generalInfo = generalInfoFeedback(hashMap);
		ObservableList<WeekOverseer> overseer = overseerFeedback(hashMap);
		ObservableList<Week> meeting = meetingFeedback(hashMap);

		this.view.updatePostNewsList(postNewsList);
		this.view.setConvention(convention);
		this.view.setMemorial(memorial);
		this.view.setGeneralInfo(generalInfo);
		this.view.setOverseer(overseer);
		this.view.setMeeting(meeting);
	}

	private ObservableList<Week> meetingFeedback(HashMap<String, Object> hashMap) {

		ObservableList<Week> list = FXCollections.observableArrayList();
		JSONObject response = (JSONObject) hashMap.get("responseMeeting");
		int status = response.getInt("status");
		if (status == 0) {
			JSONArray result = (JSONArray) response.get("result");
			for (Object object : result) {
				JSONObject json = (JSONObject) object;
				list.add(Week.newInstance(json));
			}
		}

		return list;
	}

	private ObservableList<WeekOverseer> overseerFeedback(HashMap<String, Object> hashMap) {

		ObservableList<WeekOverseer> list = FXCollections.observableArrayList();

		JSONObject response = (JSONObject) hashMap.get("responseOverseer");
		int status = response.getInt("status");
		if (status == 0) {
			JSONArray result = (JSONArray) response.get("result");
			for (Object object : result) {
				JSONObject json = (JSONObject) object;
				list.add(new WeekOverseer(json, settings.getLanguage(), settings, false));
			}
		}
		return list;
	}

	private HashMap<String, String> generalInfoFeedback(HashMap<String, Object> hashMap) {

		HashMap<String, String> generalInfos = new HashMap<String, String>();

		JSONObject response = (JSONObject) hashMap.get("responseGeneralInfo");
		int status = response.getInt("status");

		if (status == 0) {

			JSONArray result = (JSONArray) response.get("result");
			for (Object obj : result) {

				JSONObject jsonObj = (JSONObject) obj;

				String key = jsonObj.getString("keyInf");
				String value = jsonObj.getString("inf");

				generalInfos.put(key, value);
			}
		}

		return generalInfos;
	}

	private ObservableList<PostNews> postNewsListFeedback(HashMap<String, Object> hashMap) {

		ObservableList<PostNews> postNewsList = FXCollections.observableArrayList();

		JSONObject response = (JSONObject) hashMap.get("responseInfoTable");
		if (response != null) {

			int status = response.getInt("status");

			if (status == 0) {

				JSONArray result = (JSONArray) response.get("result");
				for (Object obj : result) {

					JSONObject jsonObj = (JSONObject) obj;

					int spInfID = jsonObj.getInt("spInfID");
					int spInf1 = jsonObj.getInt("spInf1");
					String spInf2 = Crypt.decrypt(jsonObj.getString("spInf2"), this.settings.getDatabaseSecretKey());
					String spInf3 = Crypt.decrypt(jsonObj.getString("spInf3"), this.settings.getDatabaseSecretKey());
					String spInf4 = Crypt.decrypt(jsonObj.getString("spInf4"), this.settings.getDatabaseSecretKey());
					String spInf5 = Crypt.decrypt(jsonObj.getString("spInf5"), this.settings.getDatabaseSecretKey());
					String spInf6 = Crypt.decrypt(jsonObj.getString("spInf6"), this.settings.getDatabaseSecretKey());
					int spInf7 = jsonObj.getInt("spInf7");
					int spInf8 = jsonObj.getInt("spInf8");

					postNewsList
							.add(new PostNews(spInfID, spInf1, spInf2, spInf3, spInf4, spInf5, spInf6, spInf7, spInf8));
				}

				postNewsList.sort((s1, s2) -> s2.getSpInf1().compareTo(s1.getSpInf1()));

			} else if (status == 1) {
				String error = response.getString("error");
				this.alertBuilder.error(this.stage, error);
			}
		}

		return postNewsList;
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

//	private ObservableList<WeekAudio> audioFeedback(HashMap<String, Object> hashMap) {
//
//		ObservableList<WeekAudio> list = FXCollections.observableArrayList();
//
//		JSONObject response = (JSONObject) hashMap.get("responseAudio");
//		if (response != null) {
//
//			int status = response.getInt("status");
//
//			if (status == 0) {
//
//				JSONArray result = (JSONArray) response.get("result");
//				for (Object obj : result) {
//
//					JSONObject json = (JSONObject) obj;
//					WeekAudio w = WeekAudio.newInstanceByJSONObject(json, this.settings.getDatabaseSecretKey());
//					list.add(w);
//				}
//
//			}
//		}
//
//		return list;
//	}

//	private ObservableList<WeekUsciere> usciereFeedback(HashMap<String, Object> hashMap) {
//
//		ObservableList<WeekUsciere> list = FXCollections.observableArrayList();
//
//		JSONObject response = (JSONObject) hashMap.get("responseUsciere");
//		if (response != null) {
//
//			int status = response.getInt("status");
//
//			if (status == 0) {
//
//				JSONArray result = (JSONArray) response.get("result");
//				for (Object obj : result) {
//
//					JSONObject json = (JSONObject) obj;
//					WeekUsciere w = WeekUsciere.newInstanceByJSONObject(json, this.settings.getDatabaseSecretKey());
//					list.add(w);
//				}
//
//			}
//		}
//
//		return list;
//	}

//	private ObservableList<Song> songsFeedback(HashMap<String, Object> hashMap) {
//
//		ObservableList<Song> list = FXCollections.observableArrayList();
//
//		JSONObject response = (JSONObject) hashMap.get("responseSongs");
//		if (response != null) {
//
//			int status = response.getInt("status");
//
//			if (status == 0) {
//
//				JSONArray result = (JSONArray) response.get("result");
//				for (Object obj : result) {
//
//					JSONObject jsonObj = (JSONObject) obj;
//
//					String numberEncrypted = jsonObj.getString("spInf1");
//					String titleEncrypted = jsonObj.getString("spInf2");
//
//					int number = Integer.valueOf(Crypt.decrypt(numberEncrypted, this.settings.getDatabaseSecretKey()));
//					String title = Crypt.decrypt(titleEncrypted, this.settings.getDatabaseSecretKey());
//
//					list.add(new Song(number, title));
//				}
//
//				list.sort((s1, s2) -> s1.getNumber().compareTo(s2.getNumber()));
//			}
//		}
//
//		return list;
//	}

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

//	private ObservableList<Place> placesFeedback(HashMap<String, Object> hashMap) {
//
//		ObservableList<Place> places = FXCollections.observableArrayList();
//
//		JSONObject response = (JSONObject) hashMap.get("responsePlaces");
//
//		if (response != null) {
//
//			int status = response.getInt("status");
//
//			if (status == 0) {
//
//				JSONArray result = (JSONArray) response.get("result");
//				for (Object obj : result) {
//
//					JSONObject json = (JSONObject) obj;
//					Place place = Place.newInstanceByJSONObject(json, this.settings.getDatabaseSecretKey());
//					places.add(place);
//				}
//
//			} else if (status == 1) {
//
//				String error = response.getString("error");
//				this.alertBuilder.error(this.stage, error);
//
//			}
//		}
//
//		return places;
//	}

//	private ObservableList<DateAndTime> dateAndTimeFeedback(HashMap<String, Object> hashMap) {
//
//		ObservableList<DateAndTime> dateAndTimeList = FXCollections.observableArrayList();
//
//		JSONObject response = (JSONObject) hashMap.get("responseDateAndTime");
//
//		if (response != null) {
//
//			int status = response.getInt("status");
//
//			if (status == 0) {
//
//				JSONArray result = (JSONArray) response.get("result");
//				for (Object obj : result) {
//
//					JSONObject json = (JSONObject) obj;
//					DateAndTime dateAndTime = DateAndTime.newInstanceByJSONObject(json);
//					dateAndTimeList.add(dateAndTime);
//				}
//
//			} else if (status == 1) {
//
//				String error = response.getString("error");
//				this.alertBuilder.error(this.stage, error);
//
//			}
//		}
//
//		return dateAndTimeList;
//	}

//	private HashMap<String, String> configFeedback(HashMap<String, Object> hashMap) {
//
//		HashMap<String, String> configs = new HashMap<>();
//
//		JSONObject response = (JSONObject) hashMap.get("responseConfig");
//
//		if (response != null) {
//
//			int status = response.getInt("status");
//
//			if (status == 0) {
//
//				JSONArray result = (JSONArray) response.get("result");
//				for (Object obj : result) {
//
//					JSONObject jsonObj = (JSONObject) obj;
//
//					String key = jsonObj.getString("keyInf");
//					String value = jsonObj.getString("inf");
//
//					configs.put(key, value);
//				}
//			}
//		}
//
//		return configs;
//	}
}
