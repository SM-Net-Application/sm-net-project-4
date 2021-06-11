package com.sm.net.sp.view.home.user.menu.territory.task;

import java.io.IOException;
import java.util.HashMap;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.sm.net.sp.json.JSONRequest;
import com.sm.net.sp.model.TerritoryMap;
import com.sm.net.sp.settings.Settings;
import com.sm.net.sp.view.home.user.menu.territory.Territory;
import com.smnet.core.dialog.AlertBuilder;
import com.smnet.core.task.TaskInterface;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class TerritoryMapsSaveTask implements TaskInterface {

	private Territory view;
	private AlertBuilder alertBuilder;
	private Settings settings;
	private Stage viewStage;
	private TerritoryMap territoryMap;
	private Tab thisTab;

	public TerritoryMapsSaveTask(AlertBuilder alertBuilder, Settings settings, Stage viewStage,
			TerritoryMap territoryMap, Territory view, Tab thisTab) {
		super();

		this.view = view;
		this.alertBuilder = alertBuilder;
		this.settings = settings;
		this.viewStage = viewStage;
		this.territoryMap = territoryMap;
		this.thisTab = thisTab;
	}

	@Override
	public void start(HashMap<String, Object> hashMap) {

		JSONObject json = null;

		if (this.territoryMap.getSpTerritoryID() > -1)
			json = JSONRequest.TERRITORY_MAPS_UPDATE(this.territoryMap);
		else
			json = JSONRequest.TERRITORY_MAPS_INSERT(this.territoryMap);

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

			this.view.updateTerritoryMaps();

			TabPane tabPane = this.view.getTerritoryMapsTabPane();
			tabPane.getTabs().remove(this.thisTab);

		} else if (status == 1)
			this.alertBuilder.error(this.viewStage, error);
		else if (status == 2)
			this.alertBuilder.error(this.viewStage, this.settings.getLanguage().getString(error));
	}
}
