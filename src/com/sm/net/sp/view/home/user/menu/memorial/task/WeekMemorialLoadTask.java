package com.sm.net.sp.view.home.user.menu.memorial.task;

import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;
import java.util.stream.StreamSupport;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.sm.net.sp.json.JSONRequest;
import com.sm.net.sp.model.WeekMemorial;
import com.sm.net.sp.settings.Settings;
import com.sm.net.sp.view.home.user.menu.memorial.Memorial;
import com.smnet.core.dialog.AlertBuilder;
import com.smnet.core.task.TaskInterface;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

public class WeekMemorialLoadTask implements TaskInterface {

	private Memorial view;
	private AlertBuilder alertBuilder;
	private Settings settings;
	private Stage viewStage;

	public WeekMemorialLoadTask(AlertBuilder alertBuilder, Settings settings, Stage viewStage, Memorial view) {
		super();

		this.view = view;
		this.alertBuilder = alertBuilder;
		this.settings = settings;
		this.viewStage = viewStage;
	}

	@Override
	public void start(HashMap<String, Object> hashMap) {

		JSONObject json = JSONRequest.MEMORIAL_LOAD();
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

		ObservableList<WeekMemorial> list = FXCollections.observableArrayList();

		JSONObject response = (JSONObject) hashMap.get("response");
		int status = response.getInt("status");

		if (status == 0) {

			JSONArray result = (JSONArray) response.get("result");
			for (Object obj : result) {

				JSONObject json = (JSONObject) obj;
				WeekMemorial convention = WeekMemorial.newInstanceByJSONObject(json,
						this.settings.getDatabaseSecretKey());
				list.add(convention);
			}

		} else if (status == 1) {

			String error = response.getString("error");
			this.alertBuilder.error(this.viewStage, error);

		}

		ObservableList<WeekMemorial> calendar = this.view.getCalendar();

		calendar.forEach(c -> {

			int key = Integer.valueOf(c.getKey());

			Optional<WeekMemorial> find = StreamSupport.stream(list.spliterator(), false)
					.filter(wc -> wc.getSpInf1() == key).findFirst();

			if (find.isPresent()) {

				WeekMemorial weekMemorial = find.get();
				c.updateInfo(weekMemorial);
			} else {
				c.deleteInfo();
			}

		});

		this.view.getWeekTableView().refresh();
	}
}
