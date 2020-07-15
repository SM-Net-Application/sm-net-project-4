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

public class PlaceDeleteTask implements TaskInterface {

	private HomeUserMenuPlaces view;
	private AlertBuilder alertBuilder;
	private Settings settings;
	private Stage viewStage;
	private Place place;

	public PlaceDeleteTask(AlertBuilder alertBuilder, Settings settings, Stage viewStage, Place place,
			HomeUserMenuPlaces view) {
		super();

		this.view = view;
		this.alertBuilder = alertBuilder;
		this.settings = settings;
		this.viewStage = viewStage;
		this.place = place;
	}

	@Override
	public void start(HashMap<String, Object> hashMap) {

		JSONObject json = JSONRequest.PLACE_DELETE(this.place);
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

		if (status == 0)
			this.view.loadList();
		else if (status == 1)
			this.alertBuilder.error(this.viewStage, error);
	}
}
