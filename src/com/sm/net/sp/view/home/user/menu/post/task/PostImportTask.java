package com.sm.net.sp.view.home.user.menu.post.task;

import java.io.IOException;
import java.util.HashMap;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.sm.net.sp.json.JSONRequest;
import com.sm.net.sp.model.PostImportDoc;
import com.sm.net.sp.settings.Settings;
import com.sm.net.sp.view.home.user.menu.post.PostImport;
import com.smnet.core.dialog.AlertBuilder;
import com.smnet.core.task.TaskInterface;

import javafx.stage.Stage;

public class PostImportTask implements TaskInterface {

	private PostImport view;
	private AlertBuilder alertBuilder;
	private Settings settings;
	private Stage viewStage;

	private PostImportDoc doc;

	public PostImportTask(PostImport view, AlertBuilder alertBuilder, Settings settings, Stage viewStage, PostImportDoc doc) {

		super();

		this.view = view;
		this.alertBuilder = alertBuilder;
		this.settings = settings;
		this.viewStage = viewStage;

		this.doc = doc;
	}

	@Override
	public void start(HashMap<String, Object> hashMap) {

		JSONObject json = JSONRequest.PDF_IMPORT(this.settings.getDatabaseSecretKey(), this.doc);

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

			this.view.closeTab();

		} else if (status == 1)
			this.alertBuilder.error(this.viewStage, error);
	}
}
