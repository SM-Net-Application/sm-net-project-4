package com.sm.net.sp.view.home.user.menu.territory.task;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.sm.net.sp.json.JSONRequest;
import com.sm.net.sp.model.TerritoryObj;
import com.sm.net.sp.settings.Settings;
import com.sm.net.sp.view.home.user.menu.territory.Territory;
import com.smnet.core.dialog.AlertBuilder;
import com.smnet.core.task.TaskInterface;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

public class TerritoryLoadTask implements TaskInterface {

	private Territory view;
	private AlertBuilder alertBuilder;
	private Settings settings;
	private Stage viewStage;

	public TerritoryLoadTask(AlertBuilder alertBuilder, Settings settings, Stage viewStage, Territory view) {
		super();

		this.view = view;
		this.alertBuilder = alertBuilder;
		this.settings = settings;
		this.viewStage = viewStage;
	}

	@Override
	public void start(HashMap<String, Object> hashMap) {

		JSONObject json = JSONRequest.TERRITORY_LOAD();
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

		ObservableList<TerritoryObj> list = FXCollections.observableArrayList();

		JSONObject response = (JSONObject) hashMap.get("response");
		int status = response.getInt("status");

		if (status == 0) {

			JSONArray result = (JSONArray) response.get("result");
			for (Object obj : result) {

				JSONObject json = (JSONObject) obj;
				TerritoryObj territory = TerritoryObj.newInstanceByJSONObject(json,
						this.settings.getDatabaseSecretKey());
				
				list.add(territory);
			}

		} else if (status == 1) {

			String error = response.getString("error");
			this.alertBuilder.error(this.viewStage, error);

		}

		Collections.sort(list, (t1, t2) -> (new BigDecimal(t1.getSpInf7()).compareTo(new BigDecimal(t2.getSpInf7()))));

		this.view.updateTerritoryList(list);
		this.view.loadTerritoryRegistry();
	}
}
