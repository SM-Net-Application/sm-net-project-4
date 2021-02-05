package com.sm.net.sp.view.home.user.menu.config.task;

import java.io.IOException;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.sm.net.sp.json.JSONRequest;
import com.sm.net.sp.model.PDFDest;
import com.sm.net.sp.settings.Settings;
import com.sm.net.sp.view.home.user.menu.config.UserMenuConfig;
import com.sm.net.util.Crypt;
import com.smnet.core.dialog.AlertBuilder;
import com.smnet.core.task.TaskInterface;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

public class ConfigPDFDestLoadTask implements TaskInterface {

	private UserMenuConfig view;
	private AlertBuilder alertBuilder;
	private Settings settings;
	private Stage viewStage;

	public ConfigPDFDestLoadTask(AlertBuilder alertBuilder, Settings settings, Stage viewStage, UserMenuConfig view) {
		super();

		this.view = view;
		this.alertBuilder = alertBuilder;
		this.settings = settings;
		this.viewStage = viewStage;
	}

	@Override
	public void start(HashMap<String, Object> hashMap) {

		JSONObject json = JSONRequest.PDFDEST_LOAD();
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

		ObservableList<PDFDest> pdfDestList = FXCollections.observableArrayList();

		JSONObject response = (JSONObject) hashMap.get("response");
		if (response != null) {

			int status = response.getInt("status");

			if (status == 0) {

				JSONArray result = (JSONArray) response.get("result");
				for (Object obj : result) {

					JSONObject jsonObj = (JSONObject) obj;

					int spInfID = jsonObj.getInt("spInfID");
					String spInf1 = Crypt.decrypt(jsonObj.getString("spInf1"), this.settings.getDatabaseSecretKey());
					String spInf2 = Crypt.decrypt(jsonObj.getString("spInf2"), this.settings.getDatabaseSecretKey());

					pdfDestList.add(new PDFDest(spInfID, spInf1, spInf2));
				}

				this.view.updatePDFDestList(pdfDestList);

			} else if (status == 1) {

				String error = response.getString("error");
				this.alertBuilder.error(this.viewStage, error);

			}
		}
	}
}
