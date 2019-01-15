package com.sm.net.sp.actions;

import org.json.JSONArray;
import org.json.JSONObject;

import com.sm.net.javafx.AlertDesigner;
import com.sm.net.sp.Meta;
import com.sm.net.sp.json.JSONRequest;
import com.sm.net.sp.model.Member;
import com.sm.net.sp.model.User;
import com.sm.net.sp.settings.Settings;
import com.sm.net.sp.view.SupportPlannerCallback;
import com.sm.net.sp.view.home.user.menu.congr.UserMenuCongrList;
import com.sm.net.sp.view.home.user.menu.congr.UserMenuCongrListCallback;
import com.sm.net.sp.view.home.user.menu.users.MenuUsersAddCallback;
import com.sm.net.sp.view.home.user.menu.users.MenuUsersListCallback;
import com.sm.net.sp.view.menu.settings.database.SettingsDatabaseCallback;
import com.sm.net.util.JSON;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
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
						}
					// } else
					// new
					// AlertDesigner(settings.getLanguage().getString("MEX006"),
					// viewSupportPlannerStage,
					// AlertType.ERROR, Meta.Application.getFullTitle(),
					// Meta.Resources.ICON)
					// .showAndWait();
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

	/**
	 * Insert Root user
	 * 
	 * @param url
	 * @param username
	 * @param password
	 * @param settings
	 * @param ownerStage
	 * @param callback
	 */
	public static void insertRootUser(String username, String password, Settings settings, Stage ownerStage) {

		Alert waitAlert = createWaitAlert(settings, Meta.Application.getFullTitle(),
				settings.getLanguage().getString("MEX005"), ownerStage);

		Task<JSONObject> task = new Task<JSONObject>() {

			{
				setOnSucceeded(value -> {

					waitAlert.close();

					JSONObject jsonObject = getValue();
					Boolean result = Boolean.valueOf(JSONRequest.isRequestOK(jsonObject));

					if (result != null)
						if (result.booleanValue())
							ownerStage.close();
						else
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
						JSONRequest.INSERT_ROOT_USER(username, password));
			}
		};

		waitAlert.show();
		Thread taskThread = new Thread(task);
		taskThread.start();
	}

	/**
	 * Update User Rules
	 * 
	 * @param spUserID
	 * @param spRoleAdmin
	 * @param settings
	 * @param ownerStage
	 * @param callback
	 */
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

	/**
	 * Delete User
	 * 
	 * @param spUserID
	 * @param settings
	 * @param ownerStage
	 * @param callback
	 */
	public static void deleteUser(String spUserID, Settings settings, Stage ownerStage,
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
						if (result.booleanValue())
							callback.updateListUsers();
						else
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
				return JSON.executeHttpPostJSON(settings.getDatabaseUrl(), JSONRequest.DELETE_USER(spUserID));
			}
		};

		waitAlert.show();
		Thread taskThread = new Thread(task);
		taskThread.start();
	}

	/**
	 * Check Username
	 * 
	 * @param url
	 * @param username
	 * @param passwordEncrypted
	 * @param password
	 * @param settings
	 * @param ownerStage
	 * @param callback
	 */
	public static void checkUsername(String username, String password, Settings settings, Stage ownerStage,
			MenuUsersAddCallback callbackAdd) {

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

							JSONArray jsonArray = jsonObject.getJSONArray("result");
							if (jsonArray.length() > 0) {

								JSONObject resultQuery = (JSONObject) jsonArray.get(0);
								if (resultQuery != null)
									if (resultQuery.getInt("users") == 0)
										callbackAdd.usernameNotExists(username, password);
									else
										callbackAdd.usernameExists();
							}

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
				return JSON.executeHttpPostJSON(settings.getDatabaseUrl(), JSONRequest.CHECK_USERNAME(username));
			}
		};

		waitAlert.show();
		Thread taskThread = new Thread(task);
		taskThread.start();
	}

	/**
	 * Check No Users
	 * 
	 * @param settings
	 * @param ownerStage
	 * @param callback
	 */
	public static void checkNoUsers(Settings settings, Stage ownerStage, SettingsDatabaseCallback callback) {

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

							JSONArray jsonArray = jsonObject.getJSONArray("result");
							if (jsonArray.length() > 0) {

								JSONObject resultQuery = (JSONObject) jsonArray.get(0);
								if (resultQuery != null)
									if (resultQuery.getInt("users") == 0)
										callback.usernameNotExists();
									else
										callback.usernameExists();
							}

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
				return JSON.executeHttpPostJSON(settings.getDatabaseUrl(), JSONRequest.GET_COUNT_USERS());
			}
		};

		waitAlert.show();
		Thread taskThread = new Thread(task);
		taskThread.start();
	}

	/**
	 * Insert Member
	 * 
	 * @param url
	 * @param username
	 * @param password
	 * @param settings
	 * @param ownerStage
	 * @param newMemberTab
	 * @param congrTabPane
	 * @param membersTab
	 * @param callback
	 */
	public static void insertMember(String spInf1, String spInf2, String spInf3, String spInf4, Settings settings,
			Stage ownerStage, TabPane congrTabPane, Tab newMemberTab, Tab membersTab,
			UserMenuCongrListCallback callback) {

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
							congrTabPane.getTabs().remove(newMemberTab);
							congrTabPane.getSelectionModel().select(membersTab);
							Actions.getAllMembers(settings, ownerStage, callback);
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
				return JSON.executeHttpPostJSON(settings.getDatabaseUrl(),
						JSONRequest.INSERT_MEMBER(spInf1, spInf2, spInf3, spInf4));
			}
		};

		waitAlert.show();
		Thread taskThread = new Thread(task);
		taskThread.start();
	}

	/**
	 * Get All Member from Database
	 * 
	 * @param url
	 * @param username
	 * @param password
	 * @param settings
	 * @param ownerStage
	 * @param callback
	 */
	public static void getAllMembers(Settings settings, Stage ownerStage, UserMenuCongrListCallback callback) {

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

							ObservableList<Member> list = FXCollections.observableArrayList();
							JSONArray jsonArray = jsonObject.getJSONArray("result");
							for (Object object : jsonArray) {
								JSONObject json = (JSONObject) object;
								list.add(new Member(json, settings.getDatabaseSecretKey()));
							}

							callback.updateTable(list);
						}
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
				return JSON.executeHttpPostJSON(settings.getDatabaseUrl(), JSONRequest.GET_ALL_MEMBERS());
			}
		};

		waitAlert.show();
		Thread taskThread = new Thread(task);
		taskThread.start();
	}

	/**
	 * Update Member
	 * 
	 * @param url
	 * @param username
	 * @param password
	 * @param settings
	 * @param ownerStage
	 * @param newMemberTab
	 * @param congrTabPane
	 * @param membersTab
	 * @param callback
	 */
	public static void updateMember(String spMemberID, String spInf1, String spInf2, String spInf3, String spInf4,
			Settings settings, Stage ownerStage, TabPane congrTabPane, Tab newMemberTab, Tab membersTab,
			UserMenuCongrListCallback callback) {

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
							congrTabPane.getTabs().remove(newMemberTab);
							congrTabPane.getSelectionModel().select(membersTab);
							Actions.getAllMembers(settings, ownerStage, callback);
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
				return JSON.executeHttpPostJSON(settings.getDatabaseUrl(),
						JSONRequest.UPDATE_MEMBER(spMemberID, spInf1, spInf2, spInf3, spInf4));
			}
		};

		waitAlert.show();
		Thread taskThread = new Thread(task);
		taskThread.start();
	}

	/**
	 * Create Alert Window
	 * 
	 * @param settings
	 * @param title
	 * @param contentText
	 * @param stageOwner
	 * @return
	 */
	private static Alert createWaitAlert(Settings settings, String title, String contentText, Stage stageOwner) {

		Alert waitAlert = new Alert(AlertType.NONE);
		waitAlert.setResult(ButtonType.OK);

		waitAlert.setTitle(title);
		waitAlert.setContentText(contentText);
		waitAlert.initOwner(stageOwner);

		return waitAlert;
	}

	public static void deleteMember(String spMemberID, Settings settings, Stage ownerStage,
			UserMenuCongrList callback) {

		Alert waitAlert = createWaitAlert(settings, Meta.Application.getFullTitle(),
				settings.getLanguage().getString("MEX005"), ownerStage);

		Task<JSONObject> task = new Task<JSONObject>() {

			{
				setOnSucceeded(value -> {

					waitAlert.close();

					JSONObject jsonObject = getValue();
					Boolean result = Boolean.valueOf(JSONRequest.isRequestOK(jsonObject));

					if (result != null)
						if (result.booleanValue())
							callback.getMembers();
						else
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
				return JSON.executeHttpPostJSON(settings.getDatabaseUrl(), JSONRequest.DELETE_MEMBER(spMemberID));
			}
		};

		waitAlert.show();
		Thread taskThread = new Thread(task);
		taskThread.start();

	}
}
