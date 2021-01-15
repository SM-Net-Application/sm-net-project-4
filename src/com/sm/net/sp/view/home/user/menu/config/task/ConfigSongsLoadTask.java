package com.sm.net.sp.view.home.user.menu.config.task;

import java.io.IOException;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.sm.net.sp.json.JSONRequest;
import com.sm.net.sp.model.Song;
import com.sm.net.sp.settings.Settings;
import com.sm.net.sp.view.home.user.menu.config.UserMenuConfig;
import com.sm.net.util.Crypt;
import com.smnet.core.dialog.AlertBuilder;
import com.smnet.core.task.TaskInterface;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

public class ConfigSongsLoadTask implements TaskInterface {

	private UserMenuConfig view;
	private AlertBuilder alertBuilder;
	private Settings settings;
	private Stage viewStage;

	public ConfigSongsLoadTask(AlertBuilder alertBuilder, Settings settings, Stage viewStage, UserMenuConfig view) {
		super();

		this.view = view;
		this.alertBuilder = alertBuilder;
		this.settings = settings;
		this.viewStage = viewStage;
	}

	@Override
	public void start(HashMap<String, Object> hashMap) {

		JSONObject json = JSONRequest.SONGS_LOAD();
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

		ObservableList<Song> songs = FXCollections.observableArrayList();

		JSONObject response = (JSONObject) hashMap.get("response");
		if (response != null) {

			int status = response.getInt("status");

			if (status == 0) {

				JSONArray result = (JSONArray) response.get("result");
				for (Object obj : result) {

					JSONObject jsonObj = (JSONObject) obj;

					String numberEncrypted = jsonObj.getString("spInf1");
					String titleEncrypted = jsonObj.getString("spInf2");

					int number = Integer.valueOf(Crypt.decrypt(numberEncrypted, this.settings.getDatabaseSecretKey()));
					String title = Crypt.decrypt(titleEncrypted, this.settings.getDatabaseSecretKey());

					songs.add(new Song(number, title));
				}

				songs.sort((s1, s2) -> s1.getNumber().compareTo(s2.getNumber()));
				this.view.updateSongList(songs);

			} else if (status == 1) {

				String error = response.getString("error");
				this.alertBuilder.error(this.viewStage, error);

			}
		}
	}
}
