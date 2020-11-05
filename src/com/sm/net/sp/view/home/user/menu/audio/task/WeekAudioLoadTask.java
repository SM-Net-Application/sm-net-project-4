package com.sm.net.sp.view.home.user.menu.audio.task;

import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;
import java.util.stream.StreamSupport;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.sm.net.sp.json.JSONRequest;
import com.sm.net.sp.model.Member;
import com.sm.net.sp.model.WeekAudio;
import com.sm.net.sp.settings.Settings;
import com.sm.net.sp.view.home.user.menu.audio.Audio;
import com.smnet.core.dialog.AlertBuilder;
import com.smnet.core.task.TaskInterface;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

public class WeekAudioLoadTask implements TaskInterface {

	private Audio view;
	private AlertBuilder alertBuilder;
	private Settings settings;
	private Stage viewStage;
	private ObservableList<Member> membersList;

	public WeekAudioLoadTask(AlertBuilder alertBuilder, Settings settings, Stage viewStage, Audio view,
			ObservableList<Member> membersList) {
		super();

		this.view = view;
		this.alertBuilder = alertBuilder;
		this.settings = settings;
		this.viewStage = viewStage;
		this.membersList = membersList;
	}

	@Override
	public void start(HashMap<String, Object> hashMap) {

		JSONObject json = JSONRequest.AUDIO_LOAD();
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

		ObservableList<WeekAudio> list = FXCollections.observableArrayList();

		JSONObject response = (JSONObject) hashMap.get("response");
		int status = response.getInt("status");

		if (status == 0) {

			JSONArray result = (JSONArray) response.get("result");
			for (Object obj : result) {

				JSONObject json = (JSONObject) obj;
				WeekAudio w = WeekAudio.newInstanceByJSONObject(json, this.settings.getDatabaseSecretKey());
				w.updateText(this.membersList);
				list.add(w);
			}

		} else if (status == 1) {

			String error = response.getString("error");
			this.alertBuilder.error(this.viewStage, error);

		}

		this.view.setDatabaseWeeksAudio(list);
		ObservableList<WeekAudio> calendar = this.view.getCalendar();

		calendar.forEach(c -> {

			int key = Integer.valueOf(c.getKey());

			Optional<WeekAudio> find = StreamSupport.stream(list.spliterator(), false)
					.filter(wc -> wc.getSpInf1() == key).findFirst();

			if (find.isPresent()) {

				WeekAudio w = find.get();
				c.updateInfo(w);
			} else {
				c.deleteInfo();
			}

		});

		this.view.getWeekTableView().refresh();
	}
}
