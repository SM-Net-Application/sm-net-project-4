package com.sm.net.sp.view.home.user.menu.places.task;

import java.io.IOException;
import java.util.HashMap;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.sm.net.sp.json.JSONRequest;
import com.sm.net.sp.model.Place;
import com.sm.net.sp.settings.Settings;
import com.sm.net.sp.view.home.user.menu.places.HomeUserMenuPlaces;
import com.smnet.core.dialog.AlertBuilder;
import com.smnet.core.task.TaskInterface;

import javafx.stage.Stage;

public class PlaceSaveTask implements TaskInterface {

	private HomeUserMenuPlaces view;
	private AlertBuilder alertBuilder;
	private Settings settings;
	private Stage viewStage;
	private Place place;

	public PlaceSaveTask(AlertBuilder alertBuilder, Settings settings, Stage viewStage, Place place,
			HomeUserMenuPlaces ownerCtrl) {
		super();

		this.view = ownerCtrl;
		this.alertBuilder = alertBuilder;
		this.settings = settings;
		this.viewStage = viewStage;
		this.place = place;
	}

	@Override
	public void start(HashMap<String, Object> hashMap) {

		JSONObject json = JSONRequest.PLACE_INSERT(this.place);
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

		JSONObject response = (JSONObject) hashMap.get("response");

		int status = response.getInt("status");
		String error = response.getString("error");

		if (status == 0) {

			this.view.loadList();
			this.viewStage.close();

		} else if (status == 1)
			this.alertBuilder.error(this.viewStage, error);
	}
}
