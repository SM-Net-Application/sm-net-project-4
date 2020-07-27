package com.sm.net.sp.view.home.user.menu.config.task;

import java.io.IOException;
import java.util.HashMap;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.sm.net.sp.json.JSONRequest;
import com.sm.net.sp.settings.Settings;
import com.smnet.core.dialog.AlertBuilder;
import com.smnet.core.task.TaskInterface;

import javafx.stage.Stage;

public class ConfigSaveTask implements TaskInterface {

	private AlertBuilder alertBuilder;
	private Settings settings;
	private Stage viewStage;

	private String placesPatternEncrypted;
	private String publicTalkMinEncrypted;
	private String watchtowerMinEncrypted;

	public ConfigSaveTask(AlertBuilder alertBuilder, Settings settings, Stage viewStage, String placesPatternEncrypted,
			String publicTalkMinEncrypted, String watchtowerMinEncrypted) {
		super();

		this.alertBuilder = alertBuilder;
		this.settings = settings;
		this.viewStage = viewStage;

		this.placesPatternEncrypted = placesPatternEncrypted;
		this.publicTalkMinEncrypted = publicTalkMinEncrypted;
		this.watchtowerMinEncrypted = watchtowerMinEncrypted;
	}

	@Override
	public void start(HashMap<String, Object> hashMap) {

		JSONObject json = JSONRequest.CONFIG_UPDATE(this.placesPatternEncrypted, this.publicTalkMinEncrypted,
				this.watchtowerMinEncrypted);

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
			this.alertBuilder.information(this.viewStage,
					this.settings.getLanguage().getString("conf.save.completed"));
		} else if (status == 1)
			this.alertBuilder.error(this.viewStage, error);
	}
}
