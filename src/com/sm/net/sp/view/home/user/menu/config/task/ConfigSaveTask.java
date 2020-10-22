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

	private String overseerTalk1MinEncrypted;
	private String overseerTalk2MinEncrypted;
	private String overseerTalk3MinEncrypted;
	private String overseerVisitCounterEncrypted;
	private String memorialTalkMinEncrypted;

	private String audio1;
	private String audio2;
	private String audio3;
	private String usciere1;
	private String usciere2;
	private String usciere3;
	private String usciere1equals;
	private String usciere2equals;
	private String usciere3equals;

	public ConfigSaveTask(AlertBuilder alertBuilder, Settings settings, Stage viewStage, String placesPatternEncrypted,
			String publicTalkMinEncrypted, String watchtowerMinEncrypted, String overseerTalk1MinEncrypted,
			String overseerTalk2MinEncrypted, String overseerTalk3MinEncrypted, String overseerVisitCounterEncrypted,
			String memorialTalkMinEncrypted, String audio1, String audio2, String audio3, String usciere1,
			String usciere2, String usciere3, String usciere1equals, String usciere2equals, String usciere3equals) {

		super();

		this.alertBuilder = alertBuilder;
		this.settings = settings;
		this.viewStage = viewStage;

		this.placesPatternEncrypted = placesPatternEncrypted;
		this.publicTalkMinEncrypted = publicTalkMinEncrypted;
		this.watchtowerMinEncrypted = watchtowerMinEncrypted;
		this.overseerTalk1MinEncrypted = overseerTalk1MinEncrypted;
		this.overseerTalk2MinEncrypted = overseerTalk2MinEncrypted;
		this.overseerTalk3MinEncrypted = overseerTalk3MinEncrypted;
		this.overseerVisitCounterEncrypted = overseerVisitCounterEncrypted;
		this.memorialTalkMinEncrypted = memorialTalkMinEncrypted;

		this.audio1 = audio1;
		this.audio2 = audio2;
		this.audio3 = audio3;
		this.usciere1 = usciere1;
		this.usciere2 = usciere2;
		this.usciere3 = usciere3;
		this.usciere1equals = usciere1equals;
		this.usciere2equals = usciere2equals;
		this.usciere3equals = usciere3equals;
	}

	@Override
	public void start(HashMap<String, Object> hashMap) {

		JSONObject json = JSONRequest.CONFIG_UPDATE(this.placesPatternEncrypted, this.publicTalkMinEncrypted,
				this.watchtowerMinEncrypted, this.overseerTalk1MinEncrypted, this.overseerTalk2MinEncrypted,
				this.overseerTalk3MinEncrypted, this.overseerVisitCounterEncrypted, this.memorialTalkMinEncrypted,
				this.audio1, this.audio2, this.audio3, this.usciere1, this.usciere2, this.usciere3, this.usciere1equals,
				this.usciere2equals, this.usciere3equals);

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
			this.alertBuilder.information(this.viewStage, this.settings.getLanguage().getString("conf.save.completed"));
		} else if (status == 1)
			this.alertBuilder.error(this.viewStage, error);
	}
}
