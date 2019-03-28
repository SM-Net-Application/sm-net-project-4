package com.sm.net.sp.actions;

import org.json.JSONArray;
import org.json.JSONObject;

import com.sm.net.javafx.AlertDesigner;
import com.sm.net.sp.Meta;
import com.sm.net.sp.json.JSONRequest;
import com.sm.net.sp.model.Family;
import com.sm.net.sp.model.Member;
import com.sm.net.sp.model.SerGroup;
import com.sm.net.sp.model.UpdateData;
import com.sm.net.sp.model.User;
import com.sm.net.sp.settings.Settings;
import com.sm.net.sp.view.SupportPlannerCallback;
import com.sm.net.sp.view.home.user.menu.congr.UserMenuCongrList;
import com.sm.net.sp.view.home.user.menu.users.MenuUsersAddCallback;
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
	public static void getAllUsers(String url, Settings settings, Stage viewSupportPlannerStage, UpdateData callback) {

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

							callback.updateUsers(listUser);
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
			UpdateData callback) {

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
							callback.updateUsers();
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
	public static void updateUserRules(String spUserID, String spRoleAdmin, String spInf1, String spInf2, String spInf3,
			String spInf4, Settings settings, Stage ownerStage, UpdateData callback) {

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
						JSONRequest.UPDATE_USER_RULES(spUserID, spRoleAdmin, spInf1, spInf2, spInf3, spInf4));
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
	public static void deleteUser(String spUserID, Settings settings, Stage ownerStage, UpdateData callback) {

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
							callback.updateUsers();
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
	 * @param spInf29
	 * @param spInf28
	 * @param spInf27
	 * @param spInf26
	 * @param spInf25
	 * @param spInf24
	 * @param spInf23
	 * @param spInf22
	 * @param spInf21
	 * @param spInf20
	 * @param spInf19
	 * @param spInf18
	 * @param spInf17
	 * @param spInf16
	 * @param spInf15
	 * @param spInf14
	 * @param spInf13
	 * @param spInf12
	 * @param spInf11
	 * @param spInf10
	 * @param spInf9
	 * @param spInf8
	 * @param spInf7
	 * @param spInf6
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
	public static void insertMember(String spInf1, String spInf2, String spInf3, String spInf4, String spInf5,
			String spInf6, String spInf7, String spInf8, String spInf9, String spInf10, String spInf11, String spInf12,
			String spInf13, String spInf14, String spInf15, String spInf16, String spInf17, String spInf18,
			String spInf19, String spInf20, String spInf21, String spInf22, String spInf23, String spInf24,
			String spInf25, String spInf26, String spInf27, String spInf28, String spInf29, Settings settings,
			Stage ownerStage, TabPane congrTabPane, Tab newMemberTab, Tab membersTab, UpdateData callback) {

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
						JSONRequest.INSERT_MEMBER(spInf1, spInf2, spInf3, spInf4, spInf5, spInf6, spInf7, spInf8,
								spInf9, spInf10, spInf11, spInf12, spInf13, spInf14, spInf15, spInf16, spInf17, spInf18,
								spInf19, spInf20, spInf21, spInf22, spInf23, spInf24, spInf25, spInf26, spInf27,
								spInf28, spInf29));
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
	public static void getAllMembers(Settings settings, Stage ownerStage, UpdateData callback) {

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

							callback.updateMembers(list);
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
	 * @param spInf29
	 * @param spInf28
	 * @param spInf27
	 * @param spInf26
	 * @param spInf25
	 * @param spInf24
	 * @param spInf23
	 * @param spInf22
	 * @param spInf21
	 * @param spInf20
	 * @param spInf19
	 * @param spInf18
	 * @param spInf17
	 * @param spInf16
	 * @param spInf15
	 * @param spInf14
	 * @param spInf13
	 * @param spInf12
	 * @param spInf11
	 * @param spInf10
	 * @param spInf9
	 * @param spInf8
	 * @param spInf7
	 * @param spInf6
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
			String spInf6, String spInf7, String spInf8, String spInf9, String spInf10, String spInf11, String spInf12,
			String spInf13, String spInf14, String spInf15, String spInf16, String spInf17, String spInf18,
			String spInf19, String spInf20, String spInf21, String spInf22, String spInf23, String spInf24,
			String spInf25, String spInf26, String spInf27, String spInf28, String spInf29, Settings settings,
			Stage ownerStage, TabPane congrTabPane, Tab newMemberTab, Tab membersTab, UpdateData callback) {

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
						JSONRequest.UPDATE_MEMBER(spMemberID, spInf1, spInf2, spInf3, spInf4, spInf6, spInf7, spInf8,
								spInf9, spInf10, spInf11, spInf12, spInf13, spInf14, spInf15, spInf16, spInf17, spInf18,
								spInf19, spInf20, spInf21, spInf22, spInf23, spInf24, spInf25, spInf26, spInf27,
								spInf28, spInf29));
			}
		};

		waitAlert.show();
		Thread taskThread = new Thread(task);
		taskThread.start();
	}

	/**
	 * Delete member
	 * 
	 * @param spMemberID
	 * @param settings
	 * @param ownerStage
	 * @param callback
	 */
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
							callback.updateMembers();
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

	/**
	 * Delete Family
	 * 
	 * @param spFamilyID
	 * @param settings
	 * @param ownerStage
	 * @param userMenuCongrList
	 */
	public static void deleteFamily(String spFamilyID, Settings settings, Stage ownerStage,
			UserMenuCongrList ownerCtrl) {

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
							ownerCtrl.updateMembers();
							ownerCtrl.updateFamilies();
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
				return JSON.executeHttpPostJSON(settings.getDatabaseUrl(), JSONRequest.DELETE_FAMILY(spFamilyID));
			}
		};

		waitAlert.show();
		Thread taskThread = new Thread(task);
		taskThread.start();
	}

	/**
	 * Insert family
	 * 
	 * @param spInf1
	 * @param spInf2
	 * @param spInf3
	 * @param spInf4
	 * @param spInf5
	 * @param spInf6
	 * @param idToSet
	 * @param idToRemove
	 * @param settings
	 * @param ownerStage
	 * @param congrTabPane
	 * @param newFamilyTab
	 * @param familiesTab
	 * @param ownerCtrl
	 */
	public static void insertFamily(String spInf1, String spInf2, String spInf3, String spInf4, String spInf5,
			String spInf6, String idToRemove, String idToSet, Settings settings, Stage ownerStage, TabPane congrTabPane,
			Tab newFamilyTab, Tab familiesTab, UserMenuCongrList ownerCtrl) {

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

							congrTabPane.getTabs().remove(newFamilyTab);
							congrTabPane.getSelectionModel().select(familiesTab);
							ownerCtrl.updateMembers();
							ownerCtrl.updateFamilies();

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
						JSONRequest.INSERT_FAMILY(spInf1, spInf2, spInf3, spInf4, spInf5, spInf6, idToRemove, idToSet));
			}
		};

		waitAlert.show();
		Thread taskThread = new Thread(task);
		taskThread.start();

	}

	/**
	 * Get All Families from Database
	 * 
	 * @param url
	 * @param username
	 * @param password
	 * @param settings
	 * @param ownerStage
	 * @param callback
	 */
	public static void getAllFamilies(Settings settings, Stage ownerStage, UpdateData callback) {

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

							ObservableList<Family> list = FXCollections.observableArrayList();
							JSONArray jsonArray = jsonObject.getJSONArray("result");
							for (Object object : jsonArray) {
								JSONObject json = (JSONObject) object;
								list.add(new Family(json, settings.getDatabaseSecretKey()));
							}

							callback.updateFamilies(list);
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
				return JSON.executeHttpPostJSON(settings.getDatabaseUrl(), JSONRequest.GET_ALL_FAMILIES());
			}
		};

		waitAlert.show();
		Thread taskThread = new Thread(task);
		taskThread.start();
	}

	/**
	 * 
	 * Update family
	 * 
	 * @param spFamID
	 * @param spInf1
	 * @param spInf2
	 * @param spInf3
	 * @param spInf4
	 * @param spInf5
	 * @param idToRemove
	 * @param idToSet
	 * @param settings
	 * @param ownerStage
	 * @param congrTabPane
	 * @param newFamilyTab
	 * @param familiesTab
	 * @param ownerCtrl
	 */
	public static void updateFamily(String spFamID, String spInf1, String spInf2, String spInf3, String spInf4,
			String spInf5, String idToRemove, String idToSet, Settings settings, Stage ownerStage, TabPane congrTabPane,
			Tab newFamilyTab, Tab familiesTab, UserMenuCongrList ownerCtrl) {

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
							congrTabPane.getTabs().remove(newFamilyTab);
							congrTabPane.getSelectionModel().select(familiesTab);
							ownerCtrl.updateMembers();
							ownerCtrl.updateFamilies();
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
				return JSON.executeHttpPostJSON(settings.getDatabaseUrl(), JSONRequest.UPDATE_FAMILY(spFamID, spInf1,
						spInf2, spInf3, spInf4, spInf5, idToRemove, idToSet));
			}
		};

		waitAlert.show();
		Thread taskThread = new Thread(task);
		taskThread.start();

	}

	/**
	 * Insert new Service Group
	 * 
	 * @param spInf1
	 * @param spInf2
	 * @param spInf3
	 * @param idToRemove
	 * @param idToSet
	 * @param settings
	 * @param ownerStage
	 * @param tabPane
	 * @param newTab
	 * @param callback
	 */
	public static void insertSerGroup(String spInf1, String spInf2, String spInf3, String idToRemove, String idToSet,
			Settings settings, Stage ownerStage, TabPane tabPane, Tab newTab, UpdateData callback) {

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

							tabPane.getTabs().remove(newTab);
							callback.updateSerGroups();

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
						JSONRequest.INSERT_SERGROUP(spInf1, spInf2, spInf3, idToRemove, idToSet));
			}
		};

		waitAlert.show();
		Thread taskThread = new Thread(task);
		taskThread.start();

	}

	/**
	 * Update Service Group
	 * 
	 * @param spSerGrID
	 * @param spInf1
	 * @param spInf2
	 * @param spInf3
	 * @param idToRemove
	 * @param idToSet
	 * @param settings
	 * @param ownerStage
	 * @param tabPane
	 * @param tab
	 * @param callback
	 */
	public static void updateSerGroup(String spSerGrID, String spInf1, String spInf2, String spInf3, String idToRemove,
			String idToSet, Settings settings, Stage ownerStage, TabPane tabPane, Tab tab, UpdateData callback) {

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
							tabPane.getTabs().remove(tab);
							callback.updateSerGroups();
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
						JSONRequest.UPDATE_SERGROUP(spSerGrID, spInf1, spInf2, spInf3, idToRemove, idToSet));
			}
		};

		waitAlert.show();
		Thread taskThread = new Thread(task);
		taskThread.start();

	}

	public static void getAllSerGroups(Settings settings, Stage ownerStage, UpdateData callback) {

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

							ObservableList<SerGroup> list = FXCollections.observableArrayList();
							JSONArray jsonArray = jsonObject.getJSONArray("result");
							for (Object object : jsonArray) {
								JSONObject json = (JSONObject) object;
								list.add(new SerGroup(json, settings.getDatabaseSecretKey()));
							}

							callback.updateSerGroups(list);
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
				return JSON.executeHttpPostJSON(settings.getDatabaseUrl(), JSONRequest.GET_ALL_SERGROUPS());
			}
		};

		waitAlert.show();
		Thread taskThread = new Thread(task);
		taskThread.start();
	}

	/**
	 * Delete Service Group
	 * 
	 */
	public static void deleteSerGroup(String spSerGrID, Settings settings, Stage ownerStage, UpdateData ownerCtrl) {

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
							ownerCtrl.updateSerGroups();
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
				return JSON.executeHttpPostJSON(settings.getDatabaseUrl(), JSONRequest.DELETE_SERGROUP(spSerGrID));
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
}
