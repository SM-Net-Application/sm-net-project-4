package com.sm.net.sp.view.home.user.menu.territory.task;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.sm.net.sp.json.JSONRequest;
import com.sm.net.sp.model.TerritoryRegistryEntity;
import com.sm.net.sp.settings.Settings;
import com.sm.net.sp.view.home.user.menu.territory.Territory;
import com.smnet.core.dialog.AlertBuilder;
import com.smnet.core.task.TaskInterface;

import javafx.stage.Stage;

public class TerritoryRegistryEntitySaveReturnTask implements TaskInterface {

	private Territory view;
	private AlertBuilder alertBuilder;
	private Settings settings;
	private Stage viewStage;

	private TerritoryRegistryEntity territoriesEntity;
	private LocalDate returnDate;

	public TerritoryRegistryEntitySaveReturnTask(AlertBuilder alertBuilder, Settings settings, Stage viewStage,
			Territory view, TerritoryRegistryEntity territoriesEntity, LocalDate returnDate) {
		super();

		this.view = view;
		this.alertBuilder = alertBuilder;
		this.settings = settings;
		this.viewStage = viewStage;
		this.territoriesEntity = territoriesEntity;
		this.returnDate = returnDate;
	}

	@Override
	public void start(HashMap<String, Object> hashMap) {

		JSONObject json = JSONRequest.TERRITORY_REGISTRY_INSERT_RETURN(this.territoriesEntity, this.returnDate,
				this.settings.getDatabaseSecretKey());

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

			this.view.updateTerritory();
			this.view.resetSelectionAllTable();
			
		} else if (status == 1)
			this.alertBuilder.error(this.viewStage, error);
		else if (status == 2)
			this.alertBuilder.error(this.viewStage, this.settings.getLanguage().getString(error));
	}
}
