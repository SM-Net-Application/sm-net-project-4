package com.sm.net.sp.view.home.user.menu.config.task;

import java.io.IOException;
import java.util.HashMap;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.sm.net.sp.json.JSONRequest;
import com.sm.net.sp.model.Song;
import com.sm.net.sp.settings.Settings;
import com.sm.net.sp.view.home.user.menu.config.UserMenuConfig;
import com.smnet.core.dialog.AlertBuilder;
import com.smnet.core.task.TaskInterface;

import javafx.collections.ObservableList;
import javafx.stage.Stage;

public class ConfigSongsSaveTask implements TaskInterface {

	private UserMenuConfig view;
	private AlertBuilder alertBuilder;
	private Settings settings;
	private Stage viewStage;

	private ObservableList<Song> songList;

	public ConfigSongsSaveTask(UserMenuConfig view, AlertBuilder alertBuilder, Settings settings, Stage viewStage,
			ObservableList<Song> songList) {

		super();

		this.view = view;
		this.alertBuilder = alertBuilder;
		this.settings = settings;
		this.viewStage = viewStage;

		this.songList = songList;
	}

	@Override
	public void start(HashMap<String, Object> hashMap) {

		JSONObject json = JSONRequest.SONGS_UPDATE(this.settings.getDatabaseSecretKey(), this.songList);

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
			
			this.view.updateSongList();
			
		} else if (status == 1)
			this.alertBuilder.error(this.viewStage, error);
	}
}
