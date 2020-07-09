package com.sm.net.sp.view.home.user.menu.dateandtime.task;

import java.io.IOException;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.sm.net.sp.json.JSONRequest;
import com.sm.net.sp.model.DateAndTime;
import com.sm.net.sp.settings.Settings;
import com.sm.net.sp.view.home.user.menu.dateandtime.HomeUserMenuDateAndTime;
import com.smnet.core.dialog.AlertBuilder;
import com.smnet.core.task.TaskInterface;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

public class DateAndTimeLoadTask implements TaskInterface {

	private HomeUserMenuDateAndTime view;
	private AlertBuilder alertBuilder;
	private Settings settings;
	private Stage viewStage;

	public DateAndTimeLoadTask(AlertBuilder alertBuilder, Settings settings, Stage viewStage,
			HomeUserMenuDateAndTime view) {
		super();

		this.view = view;
		this.alertBuilder = alertBuilder;
		this.settings = settings;
		this.viewStage = viewStage;
	}

	@Override
	public void start(HashMap<String, Object> hashMap) {

		JSONObject json = JSONRequest.DATE_AND_TIME_LOAD();
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

		ObservableList<DateAndTime> list = FXCollections.observableArrayList();

		JSONObject response = (JSONObject) hashMap.get("response");
		int status = response.getInt("status");

		if (status == 0) {

			JSONArray result = (JSONArray) response.get("result");
			for (Object obj : result) {

				JSONObject json = (JSONObject) obj;
				DateAndTime dateAndTime = DateAndTime.newInstanceByJSONObject(json);
				list.add(dateAndTime);
			}

		} else if (status == 1) {

			String error = response.getString("error");
			this.alertBuilder.error(this.viewStage, error);

		}

		this.view.getDateTimeTableView().setItems(list);
	}
}
