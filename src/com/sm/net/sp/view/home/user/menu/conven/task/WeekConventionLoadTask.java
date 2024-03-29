package com.sm.net.sp.view.home.user.menu.conven.task;

import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;
import java.util.stream.StreamSupport;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.sm.net.sp.json.JSONRequest;
import com.sm.net.sp.model.WeekConvention;
import com.sm.net.sp.settings.Settings;
import com.sm.net.sp.view.home.user.menu.conven.Convention;
import com.smnet.core.dialog.AlertBuilder;
import com.smnet.core.task.TaskInterface;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

public class WeekConventionLoadTask implements TaskInterface {

	private Convention view;
	private AlertBuilder alertBuilder;
	private Settings settings;
	private Stage viewStage;

	public WeekConventionLoadTask(AlertBuilder alertBuilder, Settings settings, Stage viewStage, Convention view) {
		super();

		this.view = view;
		this.alertBuilder = alertBuilder;
		this.settings = settings;
		this.viewStage = viewStage;
	}

	@Override
	public void start(HashMap<String, Object> hashMap) {

		JSONObject json = JSONRequest.CONVENTION_LOAD();
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

		ObservableList<WeekConvention> list = FXCollections.observableArrayList();

		JSONObject response = (JSONObject) hashMap.get("response");
		int status = response.getInt("status");

		if (status == 0) {

			JSONArray result = (JSONArray) response.get("result");
			for (Object obj : result) {

				JSONObject json = (JSONObject) obj;
				WeekConvention convention = WeekConvention.newInstanceByJSONObject(json,
						this.settings.getDatabaseSecretKey());
				list.add(convention);
			}

		} else if (status == 1) {

			String error = response.getString("error");
			this.alertBuilder.error(this.viewStage, error);

		}

		ObservableList<WeekConvention> calendar = this.view.getCalendar();

		calendar.forEach(c -> {

			int key = Integer.valueOf(c.getKey());

			Optional<WeekConvention> find = StreamSupport.stream(list.spliterator(), false)
					.filter(wc -> wc.getSpInf31() == key).findFirst();

			if (find.isPresent()) {

				WeekConvention weekConvention = find.get();
				c.updateInfo(weekConvention);
			} else {
				c.deleteInfo();
			}

		});

		this.view.getWeekTableView().refresh();
	}
}
