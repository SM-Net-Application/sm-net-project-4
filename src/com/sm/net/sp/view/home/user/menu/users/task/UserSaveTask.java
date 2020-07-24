package com.sm.net.sp.view.home.user.menu.users.task;

import java.io.IOException;
import java.util.HashMap;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.sm.net.sp.json.JSONRequest;
import com.sm.net.sp.model.DateAndTime;
import com.sm.net.sp.settings.Settings;
import com.sm.net.sp.view.home.user.menu.dateandtime.HomeUserMenuDateAndTime;
import com.sm.net.sp.view.home.user.menu.users.HomeUserMenuUsersList;
import com.smnet.core.dialog.AlertBuilder;
import com.smnet.core.task.TaskInterface;

import javafx.stage.Stage;

public class UserSaveTask implements TaskInterface {

	private HomeUserMenuUsersList view;
	private AlertBuilder alertBuilder;
	private Settings settings;
	private Stage viewStage;

	private int userID;
	private int spInf1;
	private int spInf2;
	private int spInf3;
	private int spInf4;
	private int spInf5;
	private int spInf6;
	private int spInf7;
	private int spInf8;
	private int spInf9;
	private int spInf10;
	private int spInf11;
	private int spInf12;
	private int spInf13;
	private int spInf14;
	private int spInf15;
	private int spInf16;
	private int spInf17;
	private int spInf18;
	private int spInf19;
	private int spInf20;

	public UserSaveTask(AlertBuilder alertBuilder, Settings settings, Stage viewStage, int userID, int spInf1,
			int spInf2, int spInf3, int spInf4, int spInf5, int spInf6, int spInf7, int spInf8, int spInf9, int spInf10,
			int spInf11, int spInf12, int spInf13, int spInf14, int spInf15, int spInf16, int spInf17, int spInf18,
			int spInf19, int spInf20, HomeUserMenuUsersList view) {
		super();

		this.view = view;
		this.alertBuilder = alertBuilder;
		this.settings = settings;
		this.viewStage = viewStage;

		this.userID = userID;
		this.spInf1 = spInf1;
		this.spInf2 = spInf2;
		this.spInf3 = spInf3;
		this.spInf4 = spInf4;
		this.spInf5 = spInf5;
		this.spInf6 = spInf6;
		this.spInf7 = spInf7;
		this.spInf8 = spInf8;
		this.spInf9 = spInf9;
		this.spInf10 = spInf10;
		this.spInf11 = spInf11;
		this.spInf12 = spInf12;
		this.spInf13 = spInf13;
		this.spInf14 = spInf14;
		this.spInf15 = spInf15;
		this.spInf16 = spInf16;
		this.spInf17 = spInf17;
		this.spInf18 = spInf18;
		this.spInf19 = spInf19;
		this.spInf20 = spInf20;
	}

	@Override
	public void start(HashMap<String, Object> hashMap) {

		JSONObject json = JSONRequest.USER_SAVE(this.userID, this.spInf1, this.spInf2, this.spInf3, this.spInf4,
				this.spInf5, this.spInf6, this.spInf7, this.spInf8, spInf9, this.spInf10, this.spInf11, this.spInf12,
				this.spInf13, this.spInf14, this.spInf15, this.spInf16, this.spInf17, this.spInf18, this.spInf19,
				spInf20);

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

			this.view.updateUsers();

		} else if (status == 1)
			this.alertBuilder.error(this.viewStage, error);
	}
}
