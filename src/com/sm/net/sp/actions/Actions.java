package com.sm.net.sp.actions;

import org.json.JSONArray;
import org.json.JSONObject;

import com.sm.net.javafx.AlertDesigner;
import com.sm.net.sp.Meta;
import com.sm.net.sp.User;
import com.sm.net.sp.json.JSONRequest;
import com.sm.net.sp.settings.Settings;
import com.sm.net.sp.view.SupportPlannerCallback;
import com.sm.net.sp.view.home.user.menu.users.MenuUsersListCallback;
import com.sm.net.util.JSON;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class Actions {

	/**
	 * Check Login
	 * 
	 * @param url
	 * @param username
	 * @param password
	 * @param settings
	 * @param viewSupportPlannerStage
	 * @param callback
	 */
	public static void checkUser(String url, String username, String password, Settings settings,
			Stage viewSupportPlannerStage, SupportPlannerCallback callback) {

		Alert waitAlert = createWaitAlert(settings, Meta.Application.getFullTitle(),
				settings.getLanguage().getString("MEX005"), viewSupportPlannerStage);

		Task<JSONObject> task = new Task<JSONObject>() {

			{
				setOnSucceeded(value -> {

					waitAlert.close();

					JSONObject jsonObject = getValue();
					Boolean result = Boolean.valueOf(JSONRequest.isRequestOK(jsonObject));

					if (result != null)
						if (result.booleanValue()) {

							JSONArray jsonArray = jsonObject.getJSONArray("result");
							if (jsonArray.length() == 1) {
								callback.setUserLogin((JSONObject) jsonArray.get(0));
								callback.viewSupportPlannerHomeUser();
							} else
								new AlertDesigner(settings.getLanguage().getString("MEX006"), viewSupportPlannerStage,
										AlertType.ERROR, Meta.Application.getFullTitle(), Meta.Resources.ICON)
												.showAndWait();

						} else
							new AlertDesigner(settings.getLanguage().getString("MEX006"), viewSupportPlannerStage,
									AlertType.ERROR, Meta.Application.getFullTitle(), Meta.Resources.ICON)
											.showAndWait();
				});
				setOnCancelled(value -> {
					waitAlert.close();
					new AlertDesigner(settings.getLanguage().getString("MEX007"), viewSupportPlannerStage,
							AlertType.ERROR, Meta.Application.getFullTitle(), Meta.Resources.ICON).showAndWait();
				});
				setOnFailed(value -> {
					new AlertDesigner(settings.getLanguage().getString("MEX008"), viewSupportPlannerStage,
							AlertType.ERROR, Meta.Application.getFullTitle(), Meta.Resources.ICON).showAndWait();
					waitAlert.close();
				});
			}

			@Override
			protected JSONObject call() throws Exception {
				return JSON.executeHttpPostJSON(url, JSONRequest.CHECK_USER(username, password));
			}
		};

		waitAlert.show();
		Thread taskThread = new Thread(task);
		taskThread.start();
	}

	/**
	 * Get All Users from Database
	 * 
	 * @param url
	 * @param username
	 * @param password
	 * @param settings
	 * @param viewSupportPlannerStage
	 * @param callback
	 */
	public static void getAllUsers(String url, Settings settings, Stage viewSupportPlannerStage,
			MenuUsersListCallback callback) {

		Alert waitAlert = createWaitAlert(settings, Meta.Application.getFullTitle(),
				settings.getLanguage().getString("MEX005"), viewSupportPlannerStage);

		Task<JSONObject> task = new Task<JSONObject>() {

			{
				setOnSucceeded(value -> {

					waitAlert.close();

					JSONObject jsonObject = getValue();
					Boolean result = Boolean.valueOf(JSONRequest.isRequestOK(jsonObject));

					if (result != null)
						if (result.booleanValue()) {

							ObservableList<User> listUser = FXCollections.observableArrayList();
							JSONArray jsonArray = jsonObject.getJSONArray("result");
							for (Object object : jsonArray) {
								JSONObject jsonUser = (JSONObject) object;
								listUser.add(new User(jsonUser, settings.getDatabaseSecretKey(), settings,
										viewSupportPlannerStage, callback));
							}

							callback.updateTable(listUser);

						} else
							new AlertDesigner(settings.getLanguage().getString("MEX006"), viewSupportPlannerStage,
									AlertType.ERROR, Meta.Application.getFullTitle(), Meta.Resources.ICON)
											.showAndWait();
				});
				setOnCancelled(value -> {
					waitAlert.close();
					new AlertDesigner(settings.getLanguage().getString("MEX007"), viewSupportPlannerStage,
							AlertType.ERROR, Meta.Application.getFullTitle(), Meta.Resources.ICON).showAndWait();
				});
				setOnFailed(value -> {
					new AlertDesigner(settings.getLanguage().getString("MEX008"), viewSupportPlannerStage,
							AlertType.ERROR, Meta.Application.getFullTitle(), Meta.Resources.ICON).showAndWait();
					waitAlert.close();
				});
			}

			@Override
			protected JSONObject call() throws Exception {
				return JSON.executeHttpPostJSON(url, JSONRequest.GET_ALL_USERS());
			}
		};

		waitAlert.show();
		Thread taskThread = new Thread(task);
		taskThread.start();
	}

	/**
	 * Insert new user
	 * 
	 * @param url
	 * @param username
	 * @param password
	 * @param settings
	 * @param ownerStage
	 * @param callback
	 */
	public static void insertNewUser(String url, String username, String password, Settings settings, Stage ownerStage,
			MenuUsersListCallback callback) {

		Alert waitAlert = createWaitAlert(settings, Meta.Application.getFullTitle(),
				settings.getLanguage().getString("MEX005"), ownerStage);

		Task<JSONObject> task = new Task<JSONObject>() {

			{
				setOnSucceeded(value -> {

					waitAlert.close();

					JSONObject jsonObject = getValue();
					Boolean result = Boolean.valueOf(JSONRequest.isRequestOK(jsonObject));

					if (result != null)
						if (result.booleanValue()) {
							callback.updateListUsers();
							ownerStage.close();
						} else
							new AlertDesigner(settings.getLanguage().getString("MEX006"), ownerStage, AlertType.ERROR,
									Meta.Application.getFullTitle(), Meta.Resources.ICON).showAndWait();
				});
				setOnCancelled(value -> {
					waitAlert.close();
					new AlertDesigner(settings.getLanguage().getString("MEX007"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.ICON).showAndWait();
				});
				setOnFailed(value -> {
					new AlertDesigner(settings.getLanguage().getString("MEX008"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.ICON).showAndWait();
					waitAlert.close();
				});
			}

			@Override
			protected JSONObject call() throws Exception {
				return JSON.executeHttpPostJSON(url, JSONRequest.INSERT_NEW_USER(username, password));
			}
		};

		waitAlert.show();
		Thread taskThread = new Thread(task);
		taskThread.start();
	}

	public static void updateUserRules(String spUserID, String spRoleAdmin, Settings settings, Stage ownerStage,
			MenuUsersListCallback callback) {

		Alert waitAlert = createWaitAlert(settings, Meta.Application.getFullTitle(),
				settings.getLanguage().getString("MEX005"), ownerStage);

		Task<JSONObject> task = new Task<JSONObject>() {

			{
				setOnSucceeded(value -> {

					waitAlert.close();

					JSONObject jsonObject = getValue();
					Boolean result = Boolean.valueOf(JSONRequest.isRequestOK(jsonObject));

					if (result != null)
						if (!result.booleanValue())
							new AlertDesigner(settings.getLanguage().getString("MEX006"), ownerStage, AlertType.ERROR,
									Meta.Application.getFullTitle(), Meta.Resources.ICON).showAndWait();
				});
				setOnCancelled(value -> {
					waitAlert.close();
					new AlertDesigner(settings.getLanguage().getString("MEX007"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.ICON).showAndWait();
				});
				setOnFailed(value -> {
					new AlertDesigner(settings.getLanguage().getString("MEX008"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.ICON).showAndWait();
					waitAlert.close();
				});
			}

			@Override
			protected JSONObject call() throws Exception {
				return JSON.executeHttpPostJSON(settings.getDatabaseUrl(),
						JSONRequest.UPDATE_USER_RULES(spUserID, spRoleAdmin));
			}
		};

		waitAlert.show();
		Thread taskThread = new Thread(task);
		taskThread.start();
	}

	private static Alert createWaitAlert(Settings settings, String title, String contentText, Stage stageOwner) {

		Alert waitAlert = new Alert(AlertType.NONE);
		waitAlert.setResult(ButtonType.OK);

		waitAlert.setTitle(title);
		waitAlert.setContentText(contentText);
		waitAlert.initOwner(stageOwner);

		return waitAlert;
	}
}
