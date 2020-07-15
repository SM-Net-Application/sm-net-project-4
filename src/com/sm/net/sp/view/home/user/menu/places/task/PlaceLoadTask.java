package com.sm.net.sp.view.home.user.menu.places.task;

import java.io.IOException;
import java.util.HashMap;

import javax.crypto.SecretKey;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.sm.net.sp.json.JSONRequest;
import com.sm.net.sp.model.Place;
import com.sm.net.sp.settings.Settings;
import com.sm.net.sp.view.home.user.menu.places.HomeUserMenuPlaces;
import com.smnet.core.dialog.AlertBuilder;
import com.smnet.core.task.TaskInterface;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

public class PlaceLoadTask implements TaskInterface {

	private HomeUserMenuPlaces view;
	private AlertBuilder alertBuilder;
	private Settings settings;
	private Stage viewStage;
	private SecretKey secretKey;

	public PlaceLoadTask(AlertBuilder alertBuilder, Settings settings, Stage viewStage, HomeUserMenuPlaces view,
			SecretKey secretKey) {
		super();

		this.view = view;
		this.alertBuilder = alertBuilder;
		this.settings = settings;
		this.viewStage = viewStage;
		this.secretKey = secretKey;
	}

	@Override
	public void start(HashMap<String, Object> hashMap) {

		JSONObject json = JSONRequest.PLACES_LOAD();
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

		ObservableList<Place> list = FXCollections.observableArrayList();

		JSONObject response = (JSONObject) hashMap.get("response");
		int status = response.getInt("status");

		if (status == 0) {

			JSONArray result = (JSONArray) response.get("result");
			for (Object obj : result) {

				JSONObject json = (JSONObject) obj;
				Place place = Place.newInstanceByJSONObject(json, this.secretKey);
				list.add(place);
			}

		} else if (status == 1) {

			String error = response.getString("error");
			this.alertBuilder.error(this.viewStage, error);

		}

		this.view.getPlacesTableView().setItems(list);
	}
}
