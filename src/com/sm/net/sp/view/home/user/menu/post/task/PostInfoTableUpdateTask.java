package com.sm.net.sp.view.home.user.menu.post.task;

import java.io.IOException;
import java.util.HashMap;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.sm.net.sp.json.JSONRequest;
import com.sm.net.sp.model.PostNews;
import com.sm.net.sp.settings.Settings;
import com.sm.net.sp.view.home.user.menu.post.Post;
import com.smnet.core.dialog.AlertBuilder;
import com.smnet.core.task.TaskInterface;

import javafx.stage.Stage;

public class PostInfoTableUpdateTask implements TaskInterface {

	private AlertBuilder alertBuilder;
	private Settings settings;
	private Stage viewStage;

	private PostNews postNews;
	private String spInf7;
	private Post view;

//	public PostInfoTableUpdateTask(AlertBuilder alertBuilder, Settings settings, Stage stage, Post view) {
//		super();
//
//		this.view = view;
//		this.alertBuilder = alertBuilder;
//		this.settings = settings;
//		this.stage = stage;
//	}

	public PostInfoTableUpdateTask(AlertBuilder alertBuilder, Settings settings, Stage viewStage, PostNews postNews,
			String spInf7, Post view) {

		this.alertBuilder = alertBuilder;
		this.settings = settings;
		this.viewStage = viewStage;
		this.postNews = postNews;
		this.spInf7 = spInf7;
		this.view = view;
	}

	@Override
	public void start(HashMap<String, Object> hashMap) {

		JSONObject json = JSONRequest.POST_INFOTABLE_UPDATE(this.postNews, this.spInf7);

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

		if (status == 0) {

			this.view.updatePostInfoTableStatus(this.postNews, this.spInf7);

		} else if (status == 1)
			this.alertBuilder.error(this.viewStage, error);
		else if (status == 2)
			this.alertBuilder.error(this.viewStage, this.settings.getLanguage().getString(error));
	}
}
