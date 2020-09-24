package com.sm.net.sp.actions;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.sm.net.javafx.AlertDesigner;
import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.jasper.Jasper;
import com.sm.net.sp.jasper.model.JRNaturalDisasterFamily;
import com.sm.net.sp.jasper.model.JRWeek;
import com.sm.net.sp.json.JSONRequest;
import com.sm.net.sp.model.Activities;
import com.sm.net.sp.model.Family;
import com.sm.net.sp.model.Info;
import com.sm.net.sp.model.Member;
import com.sm.net.sp.model.SerGroup;
import com.sm.net.sp.model.UpdateData;
import com.sm.net.sp.model.User;
import com.sm.net.sp.model.Week;
import com.sm.net.sp.model.WeekOverseer;
import com.sm.net.sp.settings.Settings;
import com.sm.net.sp.utils.JSONUtils;
import com.sm.net.sp.view.SupportPlannerCallback;
import com.sm.net.sp.view.SupportPlannerView;
import com.sm.net.sp.view.home.user.menu.congr.UserMenuCongrList;
import com.sm.net.sp.view.home.user.menu.naturaldisaster.UserMenuNaturalDisasterList;
import com.sm.net.sp.view.home.user.menu.users.MenuUsersAddCallback;
import com.sm.net.sp.view.menu.settings.user.SettingsUserCallback;
import com.sm.net.util.Crypt;
import com.sm.net.util.JSON;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRFontNotFoundException;
import net.sf.jasperreports.view.JasperViewer;

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
	 * @param application
	 */
	public static void checkUser(String url, String username, String password, Settings settings,
			Stage viewSupportPlannerStage, SupportPlannerCallback callback, SupportPlannerView application) {

		Alert waitAlert = createWaitAlert(settings, Meta.Application.getFullTitle(),
				settings.getLanguage().getString("MEX005"), viewSupportPlannerStage);

		Task<JSONObject> task = new Task<JSONObject>() {

			{
				setOnSucceeded(value -> {

					waitAlert.close();

					JSONObject jsonObject = getValue();

					int status = -1;
					try {
						status = jsonObject.getInt("status");
					} catch (JSONException e) {
					}

					switch (status) {
					case 4:
						application.getAlertBuilder()
								.error(viewSupportPlannerStage, settings.getLanguage().getString("sp.login.error1"))
								.show();
						break;
					case 5:
						application.getAlertBuilder()
								.error(viewSupportPlannerStage, settings.getLanguage().getString("sp.login.error2"))
								.show();
						break;

					default:

						Boolean result = Boolean.valueOf(JSONRequest.isRequestOK(jsonObject));

						if (result != null)
							if (result.booleanValue()) {

								JSONArray jsonArray = jsonObject.getJSONArray("result");
								if (jsonArray.length() == 1) {
									callback.setUserLogin((JSONObject) jsonArray.get(0));
									callback.viewSupportPlannerHomeUser();
								} else
									new AlertDesigner(settings.getLanguage().getString("MEX006"),
											viewSupportPlannerStage, AlertType.ERROR, Meta.Application.getFullTitle(),
											Meta.Resources.getImageApplicationIcon(), Meta.Themes.SUPPORTPLANNER_THEME,
											"alert_001").showAndWait();

							} else
								new AlertDesigner(settings.getLanguage().getString("MEX006"), viewSupportPlannerStage,
										AlertType.ERROR, Meta.Application.getFullTitle(),
										Meta.Resources.getImageApplicationIcon(), Meta.Themes.SUPPORTPLANNER_THEME,
										"alert_001").showAndWait();
						break;
					}

				});
				setOnCancelled(value -> {
					waitAlert.close();
					new AlertDesigner(settings.getLanguage().getString("MEX007"), viewSupportPlannerStage,
							AlertType.ERROR, Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
				});
				setOnFailed(value -> {
					new AlertDesigner(settings.getLanguage().getString("MEX008"), viewSupportPlannerStage,
							AlertType.ERROR, Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
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
							AlertType.ERROR, Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
				});
				setOnFailed(value -> {
					new AlertDesigner(settings.getLanguage().getString("MEX008"), viewSupportPlannerStage,
							AlertType.ERROR, Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
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
									Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
									Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
				});
				setOnCancelled(value -> {
					waitAlert.close();
					new AlertDesigner(settings.getLanguage().getString("MEX007"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
				});
				setOnFailed(value -> {
					new AlertDesigner(settings.getLanguage().getString("MEX008"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
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
	 * @param usernameEnc
	 * @param passwordEnc
	 * @param key
	 * @param password
	 * @param user
	 * @param settings
	 * @param ownerStage
	 * @param callback
	 */
	public static void insertRootUser(String usernameEnc, String passwordEnc, String user, String password, String key,
			Settings settings, Stage ownerStage, SettingsUserCallback callback) {

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
							try {
								callback.updateSettings(user, password, key);
							} catch (IOException e) {
								System.out.println(e.getMessage());
							}
							ownerStage.close();
						} else
							new AlertDesigner(settings.getLanguage().getString("MEX006"), ownerStage, AlertType.ERROR,
									Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
									Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
				});
				setOnCancelled(value -> {
					waitAlert.close();
					new AlertDesigner(settings.getLanguage().getString("MEX007"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
				});
				setOnFailed(value -> {
					new AlertDesigner(settings.getLanguage().getString("MEX008"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
					waitAlert.close();
				});
			}

			@Override
			protected JSONObject call() throws Exception {
				return JSON.executeHttpPostJSON(settings.getDatabaseUrl(),
						JSONRequest.INSERT_ROOT_USER(usernameEnc, passwordEnc));
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
	 * @param string
	 * @param spRoleAdmin
	 * @param settings
	 * @param ownerStage
	 * @param callback
	 */
	public static void updateUserRules(String spUserID, String spInf1, String spInf2, String spInf3, String spInf4,
			String spInf5, String spInf6, String spInf7, String spInf8, Settings settings, Stage ownerStage,
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
						if (!result.booleanValue())
							new AlertDesigner(settings.getLanguage().getString("MEX006"), ownerStage, AlertType.ERROR,
									Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
									Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
				});
				setOnCancelled(value -> {
					waitAlert.close();
					new AlertDesigner(settings.getLanguage().getString("MEX007"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
				});
				setOnFailed(value -> {
					new AlertDesigner(settings.getLanguage().getString("MEX008"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
					waitAlert.close();
				});
			}

			@Override
			protected JSONObject call() throws Exception {
				return JSON.executeHttpPostJSON(settings.getDatabaseUrl(), JSONRequest.UPDATE_USER_RULES(spUserID,
						spInf1, spInf2, spInf3, spInf4, spInf5, spInf6, spInf7, spInf8));
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
									Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
									Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
				});
				setOnCancelled(value -> {
					waitAlert.close();
					new AlertDesigner(settings.getLanguage().getString("MEX007"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
				});
				setOnFailed(value -> {
					new AlertDesigner(settings.getLanguage().getString("MEX008"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
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
									Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
									Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
				});
				setOnCancelled(value -> {
					waitAlert.close();
					new AlertDesigner(settings.getLanguage().getString("MEX007"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
				});
				setOnFailed(value -> {
					new AlertDesigner(settings.getLanguage().getString("MEX008"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
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
	 * @param application
	 */
	public static void checkNoUsers(Settings settings, Stage ownerStage, SettingsUserCallback callback,
			SupportPlannerView application) {

		Alert waitAlert = createWaitAlert(settings, Meta.Application.getFullTitle(),
				settings.getLanguage().getString("MEX005"), ownerStage);

		Task<JSONObject> task = new Task<JSONObject>() {

			{
				setOnSucceeded(value -> {

					waitAlert.close();

					JSONObject jsonObject = getValue();

					int status = -1;
					try {
						status = jsonObject.getInt("status");
					} catch (JSONException e) {
					}

					switch (status) {
					case 4:
						application.getAlertBuilder()
								.error(ownerStage, settings.getLanguage().getString("sp.login.error1")).show();
						break;
					case 5:
						application.getAlertBuilder()
								.error(ownerStage, settings.getLanguage().getString("sp.login.error2")).show();
						break;

					default:

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
								new AlertDesigner(settings.getLanguage().getString("MEX006"), ownerStage,
										AlertType.ERROR, Meta.Application.getFullTitle(),
										Meta.Resources.getImageApplicationIcon(), Meta.Themes.SUPPORTPLANNER_THEME,
										"alert_001").showAndWait();
						break;
					}
				});
				setOnCancelled(value -> {
					waitAlert.close();
					new AlertDesigner(settings.getLanguage().getString("MEX007"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
				});
				setOnFailed(value -> {
					new AlertDesigner(settings.getLanguage().getString("MEX008"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
					waitAlert.close();
				});
			}

			@Override
			protected JSONObject call() throws Exception {
				return JSONUtils.executeHttpPostJSON(settings.getDatabaseUrl(), JSONRequest.GET_COUNT_USERS());
			}
		};

		waitAlert.show();
		Thread taskThread = new Thread(task);
		taskThread.start();
	}

	/**
	 * Insert Member
	 * 
	 */
	public static void insertMember(String spInf1, String spInf2, String spInf3, String spInf4, String spInf5,
			String spInf6, String spInf7, String spInf8, String spInf9, String spInf10, String spInf11, String spInf12,
			String spInf13, String spInf14, String spInf15, String spInf16, String spInf17, String spInf18,
			String spInf19, String spInf20, String spInf21, String spInf22, String spInf23, String spInf24,
			String spInf25, String spInf26, String spInf27, String spInf28, String spInf29, String spInf30,
			String spInf31, String spInf32, String spInf33, String spInf34, String spInf35, String spInf36,
			String spInf37, String spInf38, String spInf39, String spInf40, String spInf41, String spInf42,
			String spInf43, String spInf44, String spInf45, String spInf46, String spInf47, String spInf48,
			String spInf49, String spInf50, String spInf51, String spInf52, String spInf53, Settings settings,
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
									Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
									Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
				});
				setOnCancelled(value -> {
					waitAlert.close();
					new AlertDesigner(settings.getLanguage().getString("MEX007"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
				});
				setOnFailed(value -> {
					new AlertDesigner(settings.getLanguage().getString("MEX008"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
					waitAlert.close();
				});
			}

			@Override
			protected JSONObject call() throws Exception {
				return JSON.executeHttpPostJSON(settings.getDatabaseUrl(),
						JSONRequest.INSERT_MEMBER(spInf1, spInf2, spInf3, spInf4, spInf5, spInf6, spInf7, spInf8,
								spInf9, spInf10, spInf11, spInf12, spInf13, spInf14, spInf15, spInf16, spInf17, spInf18,
								spInf19, spInf20, spInf21, spInf22, spInf23, spInf24, spInf25, spInf26, spInf27,
								spInf28, spInf29, spInf30, spInf31, spInf32, spInf33, spInf34, spInf35, spInf36,
								spInf37, spInf38, spInf39, spInf40, spInf41, spInf42, spInf43, spInf44, spInf45,
								spInf46, spInf47, spInf48, spInf49, spInf50, spInf51, spInf52, spInf53));
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
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
				});
				setOnFailed(value -> {
					new AlertDesigner(settings.getLanguage().getString("MEX008"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
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
	 */
	public static void updateMember(String spMemberID, String spInf1, String spInf2, String spInf3, String spInf4,
			String spInf6, String spInf7, String spInf8, String spInf9, String spInf10, String spInf11, String spInf12,
			String spInf13, String spInf14, String spInf15, String spInf16, String spInf17, String spInf18,
			String spInf19, String spInf20, String spInf21, String spInf22, String spInf23, String spInf24,
			String spInf25, String spInf26, String spInf27, String spInf28, String spInf29, String spInf30,
			String spInf31, String spInf32, String spInf33, String spInf34, String spInf35, String spInf36,
			String spInf37, String spInf38, String spInf39, String spInf40, String spInf41, String spInf42,
			String spInf43, String spInf44, String spInf45, String spInf46, String spInf47, String spInf48,
			String spInf49, String spInf50, String spInf51, String spInf52, String spInf53, Settings settings,
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
									Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
									Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
				});
				setOnCancelled(value -> {
					waitAlert.close();
					new AlertDesigner(settings.getLanguage().getString("MEX007"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
				});
				setOnFailed(value -> {
					new AlertDesigner(settings.getLanguage().getString("MEX008"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
					waitAlert.close();
				});
			}

			@Override
			protected JSONObject call() throws Exception {
				return JSON.executeHttpPostJSON(settings.getDatabaseUrl(),
						JSONRequest.UPDATE_MEMBER(spMemberID, spInf1, spInf2, spInf3, spInf4, spInf6, spInf7, spInf8,
								spInf9, spInf10, spInf11, spInf12, spInf13, spInf14, spInf15, spInf16, spInf17, spInf18,
								spInf19, spInf20, spInf21, spInf22, spInf23, spInf24, spInf25, spInf26, spInf27,
								spInf28, spInf29, spInf30, spInf31, spInf32, spInf33, spInf34, spInf35, spInf36,
								spInf37, spInf38, spInf39, spInf40, spInf41, spInf42, spInf43, spInf44, spInf45,
								spInf46, spInf47, spInf48, spInf49, spInf50, spInf51, spInf52, spInf53));
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
									Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
									Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
				});
				setOnCancelled(value -> {
					waitAlert.close();
					new AlertDesigner(settings.getLanguage().getString("MEX007"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
				});
				setOnFailed(value -> {
					new AlertDesigner(settings.getLanguage().getString("MEX008"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
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
									Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
									Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
				});
				setOnCancelled(value -> {
					waitAlert.close();
					new AlertDesigner(settings.getLanguage().getString("MEX007"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
				});
				setOnFailed(value -> {
					new AlertDesigner(settings.getLanguage().getString("MEX008"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
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
			String spInf6, String spInf7, String spInf8, String spInf9, String spInf10, String idToRemove,
			String idToSet, Settings settings, Stage ownerStage, TabPane congrTabPane, Tab newFamilyTab,
			Tab familiesTab, UserMenuCongrList ownerCtrl) {

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
									Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
									Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
				});
				setOnCancelled(value -> {
					waitAlert.close();
					new AlertDesigner(settings.getLanguage().getString("MEX007"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
				});
				setOnFailed(value -> {
					new AlertDesigner(settings.getLanguage().getString("MEX008"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
					waitAlert.close();
				});
			}

			@Override
			protected JSONObject call() throws Exception {
				return JSON.executeHttpPostJSON(settings.getDatabaseUrl(), JSONRequest.INSERT_FAMILY(spInf1, spInf2,
						spInf3, spInf4, spInf5, spInf6, spInf7, spInf8, spInf9, spInf10, idToRemove, idToSet));
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
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
				});
				setOnFailed(value -> {
					new AlertDesigner(settings.getLanguage().getString("MEX008"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
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
			String spInf5, String spInf7, String spInf8, String spInf9, String spInf10, String idToRemove,
			String idToSet, Settings settings, Stage ownerStage, TabPane congrTabPane, Tab newFamilyTab,
			Tab familiesTab, UserMenuCongrList ownerCtrl) {

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
									Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
									Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
				});
				setOnCancelled(value -> {
					waitAlert.close();
					new AlertDesigner(settings.getLanguage().getString("MEX007"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
				});
				setOnFailed(value -> {
					new AlertDesigner(settings.getLanguage().getString("MEX008"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
					waitAlert.close();
				});
			}

			@Override
			protected JSONObject call() throws Exception {
				return JSON.executeHttpPostJSON(settings.getDatabaseUrl(), JSONRequest.UPDATE_FAMILY(spFamID, spInf1,
						spInf2, spInf3, spInf4, spInf5, spInf7, spInf8, spInf9, spInf10, idToRemove, idToSet));
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
									Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
									Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
				});
				setOnCancelled(value -> {
					waitAlert.close();
					new AlertDesigner(settings.getLanguage().getString("MEX007"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
				});
				setOnFailed(value -> {
					new AlertDesigner(settings.getLanguage().getString("MEX008"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
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
									Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
									Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
				});
				setOnCancelled(value -> {
					waitAlert.close();
					new AlertDesigner(settings.getLanguage().getString("MEX007"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
				});
				setOnFailed(value -> {
					new AlertDesigner(settings.getLanguage().getString("MEX008"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
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
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
				});
				setOnFailed(value -> {
					new AlertDesigner(settings.getLanguage().getString("MEX008"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
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
									Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
									Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
				});
				setOnCancelled(value -> {
					waitAlert.close();
					new AlertDesigner(settings.getLanguage().getString("MEX007"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
				});
				setOnFailed(value -> {
					new AlertDesigner(settings.getLanguage().getString("MEX008"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
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
	 * Get All Week Type
	 * 
	 * @param weekStart
	 * @param weekEnd
	 */
	public static void getAllWeeks(Week weekStart, Week weekEnd, Settings settings, Stage ownerStage,
			ObservableList<Member> membersList, UpdateData callback) {

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

							ObservableList<Week> list = FXCollections.observableArrayList();
							JSONArray jsonArray = jsonObject.getJSONArray("result");
							for (Object object : jsonArray) {
								JSONObject json = (JSONObject) object;
								list.add(new Week(json, settings.getLanguage(), settings, membersList));
							}

							callback.updateWeeks(list);

						} else {
							callback.updateWeeks(null);
							// new
							// AlertDesigner(settings.getLanguage().getString("MEX006"),
							// ownerStage, AlertType.ERROR,
							// Meta.Application.getFullTitle(),
							// Meta.Resources.ICON).showAndWait();
						}
				});
				setOnCancelled(value -> {
					waitAlert.close();
					new AlertDesigner(settings.getLanguage().getString("MEX007"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
				});
				setOnFailed(value -> {
					new AlertDesigner(settings.getLanguage().getString("MEX008"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
					waitAlert.close();
				});
			}

			@Override
			protected JSONObject call() throws Exception {
				return JSON.executeHttpPostJSON(settings.getDatabaseUrl(),
						JSONRequest.GET_ALL_WEEKS(weekStart, weekEnd));
			}
		};

		waitAlert.show();
		Thread taskThread = new Thread(task);
		taskThread.start();

	}

	public static void insertWeek(String spInf1, String spInf2, String spInf3, String spInf4, String spInf5,
			String spInf6, String spInf7, String spInf8, String spInf9, String spInf10, String spInf11, String spInf12,
			String spInf13, String spInf14, String spInf15, String spInf16, String spInf17, String spInf18,
			String spInf19, String spInf20, String spInf21, String spInf22, String spInf23, String spInf24,
			String spInf25, String spInf26, String spInf27, String spInf28, String spInf29, String spInf30,
			String spInf31, String spInf32, String spInf33, String spInf34, String spInf35, String spInf36,
			String spInf37, String spInf38, String spInf39, String spInf40, int spInf41, String spInf42, String spInf43,
			int spInf44, int spInf45, int spInf46, int spInf47, int spInf48, int spInf49, String spInf50, int spInf51,
			String spInf52, int spInf53, String spInfMinistryParts, String spInfChristiansParts, Settings settings,
			Stage ownerStage, TabPane tabPane, Tab newTab, UpdateData callback) {

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
							callback.updateWeeks();

						} else
							new AlertDesigner(settings.getLanguage().getString("MEX006"), ownerStage, AlertType.ERROR,
									Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
									Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
				});
				setOnCancelled(value -> {
					waitAlert.close();
					new AlertDesigner(settings.getLanguage().getString("MEX007"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
				});
				setOnFailed(value -> {
					new AlertDesigner(settings.getLanguage().getString("MEX008"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
					waitAlert.close();
				});
			}

			@Override
			protected JSONObject call() throws Exception {
				return JSON.executeHttpPostJSON(settings.getDatabaseUrl(),
						JSONRequest.INSERT_WEEK(spInf1, spInf2, spInf3, spInf4, spInf5, spInf6, spInf7, spInf8, spInf9,
								spInf10, spInf11, spInf12, spInf13, spInf14, spInf15, spInf16, spInf17, spInf18,
								spInf19, spInf20, spInf21, spInf22, spInf23, spInf24, spInf25, spInf26, spInf27,
								spInf28, spInf29, spInf30, spInf31, spInf32, spInf33, spInf34, spInf35, spInf36,
								spInf37, spInf38, spInf39, spInf40, spInf41, spInf42, spInf43, spInf44, spInf45,
								spInf46, spInf47, spInf48, spInf49, spInf50, spInf51, spInf52, spInf53,
								spInfMinistryParts, spInfChristiansParts));
			}
		};

		waitAlert.show();
		Thread taskThread = new Thread(task);
		taskThread.start();

	}

	public static void updateWeek(String spWeekID, String spInf1, String spInf2, String spInf3, String spInf4,
			String spInf5, String spInf6, String spInf7, String spInf8, String spInf9, String spInf10, String spInf11,
			String spInf12, String spInf13, String spInf14, String spInf15, String spInf16, String spInf17,
			String spInf18, String spInf19, String spInf20, String spInf21, String spInf22, String spInf23,
			String spInf24, String spInf25, String spInf26, String spInf27, String spInf28, String spInf29,
			String spInf30, String spInf31, String spInf32, String spInf33, String spInf34, String spInf35,
			String spInf36, String spInf37, String spInf38, String spInf39, String spInf40, int spInf41, String spInf42,
			String spInf43, int spInf44, int spInf45, int spInf46, int spInf47, int spInf48, int spInf49,
			String spInf50, int spInf51, String spInf52, int spInf53, String spInfMinistryParts,
			String spInfChristiansParts, Settings settings, Stage ownerStage, TabPane tabPane, Tab newTab,
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

							tabPane.getTabs().remove(newTab);
							callback.updateWeeks();

						} else
							new AlertDesigner(settings.getLanguage().getString("MEX006"), ownerStage, AlertType.ERROR,
									Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
									Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
				});
				setOnCancelled(value -> {
					waitAlert.close();
					new AlertDesigner(settings.getLanguage().getString("MEX007"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
				});
				setOnFailed(value -> {
					new AlertDesigner(settings.getLanguage().getString("MEX008"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
					waitAlert.close();
				});
			}

			@Override
			protected JSONObject call() throws Exception {
				return JSON.executeHttpPostJSON(settings.getDatabaseUrl(),
						JSONRequest.UPDATE_WEEK(spWeekID, spInf1, spInf2, spInf3, spInf4, spInf5, spInf6, spInf7,
								spInf8, spInf9, spInf10, spInf11, spInf12, spInf13, spInf14, spInf15, spInf16, spInf17,
								spInf18, spInf19, spInf20, spInf21, spInf22, spInf23, spInf24, spInf25, spInf26,
								spInf27, spInf28, spInf29, spInf30, spInf31, spInf32, spInf33, spInf34, spInf35,
								spInf36, spInf37, spInf38, spInf39, spInf40, spInf41, spInf42, spInf43, spInf44,
								spInf45, spInf46, spInf47, spInf48, spInf49, spInf50, spInf51, spInf52, spInf53,
								spInfMinistryParts, spInfChristiansParts));
			}
		};

		waitAlert.show();
		Thread taskThread = new Thread(task);
		taskThread.start();

	}

	/**
	 * Get All Circuit Overseer Week
	 * 
	 * @param weekStart
	 * @param weekEnd
	 */
	public static void getAllCircuitOverseerWeeks(WeekOverseer weekStart, WeekOverseer weekEnd, Settings settings,
			Stage ownerStage, UpdateData callback) {

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

							ObservableList<WeekOverseer> list = FXCollections.observableArrayList();
							JSONArray jsonArray = jsonObject.getJSONArray("result");
							for (Object object : jsonArray) {
								JSONObject json = (JSONObject) object;
								list.add(new WeekOverseer(json, settings.getLanguage(), settings, false));
							}

							callback.updateWeeksOverseer(list);

						} else {
							callback.updateWeeks(null);
						}
				});
				setOnCancelled(value -> {
					waitAlert.close();
					new AlertDesigner(settings.getLanguage().getString("MEX007"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
				});
				setOnFailed(value -> {
					new AlertDesigner(settings.getLanguage().getString("MEX008"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
					waitAlert.close();
				});
			}

			@Override
			protected JSONObject call() throws Exception {
				return JSON.executeHttpPostJSON(settings.getDatabaseUrl(),
						JSONRequest.GET_ALL_CIRCUITOVERSEER_WEEKS(weekStart, weekEnd));
			}
		};

		waitAlert.show();
		Thread taskThread = new Thread(task);
		taskThread.start();

	}

	public static void getLastCircuitOverseerWeeks(Settings settings, Stage ownerStage, UpdateData callback) {

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

							ObservableList<WeekOverseer> list = FXCollections.observableArrayList();
							JSONArray jsonArray = jsonObject.getJSONArray("result");
							for (Object object : jsonArray) {
								JSONObject json = (JSONObject) object;
								list.add(new WeekOverseer(json, settings.getLanguage(), settings, false));
							}

							callback.updateWeeksOverseer(list);

						} else {
							callback.updateWeeks(null);
						}
				});
				setOnCancelled(value -> {
					waitAlert.close();
					new AlertDesigner(settings.getLanguage().getString("MEX007"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
				});
				setOnFailed(value -> {
					new AlertDesigner(settings.getLanguage().getString("MEX008"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
					waitAlert.close();
				});
			}

			@Override
			protected JSONObject call() throws Exception {
				return JSON.executeHttpPostJSON(settings.getDatabaseUrl(),
						JSONRequest.GET_LAST_CIRCUITOVERSEER_WEEKS());
			}
		};

		waitAlert.show();
		Thread taskThread = new Thread(task);
		taskThread.start();
	}

	/**
	 * Insert Circuit Overseer Week
	 * 
	 * @param spInf20
	 */
	public static void insertOverseerWeek(String spInf1, String spInf2, String spInf3, String spInf4, String spInf5,
			String spInf6, String spInf7, String spInf8, String spInf9, String spInf10, String spInf11, String spInf12,
			String spInf13, String spInf14, String spInf15, String spInf16, String spInf17, String spInf18,
			String spInf19, String spInf20, Settings settings, Stage ownerStage, TabPane tabPane, Tab newTab,
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

							tabPane.getTabs().remove(newTab);
							callback.updateWeeksOverseer();

						} else
							new AlertDesigner(settings.getLanguage().getString("MEX006"), ownerStage, AlertType.ERROR,
									Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
									Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
				});
				setOnCancelled(value -> {
					waitAlert.close();
					new AlertDesigner(settings.getLanguage().getString("MEX007"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
				});
				setOnFailed(value -> {
					new AlertDesigner(settings.getLanguage().getString("MEX008"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
					waitAlert.close();
				});
			}

			@Override
			protected JSONObject call() throws Exception {
				return JSON.executeHttpPostJSON(settings.getDatabaseUrl(),
						JSONRequest.INSERT_CIRCUITOVERSEER_WEEK(spInf1, spInf2, spInf3, spInf4, spInf5, spInf6, spInf7,
								spInf8, spInf9, spInf10, spInf11, spInf12, spInf13, spInf14, spInf15, spInf16, spInf17,
								spInf18, spInf19, spInf20));
			}
		};

		waitAlert.show();
		Thread taskThread = new Thread(task);
		taskThread.start();

	}

	/**
	 * Update Circuit Overseer Week
	 */
	public static void updateOverseerWeek(String spWeekOvID, String spInf1, String spInf2, String spInf3, String spInf4,
			String spInf5, String spInf6, String spInf7, String spInf8, String spInf9, String spInf10, String spInf11,
			String spInf12, String spInf13, String spInf14, String spInf15, String spInf16, String spInf17,
			String spInf18, String spInf19, String spInf20, Settings settings, Stage ownerStage, TabPane tabPane,
			Tab newTab, UpdateData callback) {

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
							callback.updateWeeksOverseer();

						} else
							new AlertDesigner(settings.getLanguage().getString("MEX006"), ownerStage, AlertType.ERROR,
									Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
									Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
				});
				setOnCancelled(value -> {
					waitAlert.close();
					new AlertDesigner(settings.getLanguage().getString("MEX007"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
				});
				setOnFailed(value -> {
					new AlertDesigner(settings.getLanguage().getString("MEX008"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
					waitAlert.close();
				});
			}

			@Override
			protected JSONObject call() throws Exception {
				return JSON.executeHttpPostJSON(settings.getDatabaseUrl(),
						JSONRequest.UPDATE_OVERSEER_WEEK(spWeekOvID, spInf1, spInf2, spInf3, spInf4, spInf5, spInf6,
								spInf7, spInf8, spInf9, spInf10, spInf11, spInf12, spInf13, spInf14, spInf15, spInf16,
								spInf17, spInf18, spInf19, spInf20));
			}
		};

		waitAlert.show();
		Thread taskThread = new Thread(task);
		taskThread.start();

	}

	/**
	 * Check if a info is present
	 */
	public static void checkInfo(String key, Settings settings, Stage ownerStage, UpdateData callback) {

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
							callback.checkInfo(true);
						else
							callback.checkInfo(false);

				});
				setOnCancelled(value -> {
					waitAlert.close();
					new AlertDesigner(settings.getLanguage().getString("MEX007"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
				});
				setOnFailed(value -> {
					new AlertDesigner(settings.getLanguage().getString("MEX008"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
					waitAlert.close();
				});
			}

			@Override
			protected JSONObject call() throws Exception {
				return JSON.executeHttpPostJSON(settings.getDatabaseUrl(), JSONRequest.CHECK_INFO(key));
			}
		};

		waitAlert.show();
		Thread taskThread = new Thread(task);
		taskThread.start();

	}

	/**
	 * Update Info
	 */
	public static void updateInfo(String key, String value, Settings settings, Stage ownerStage) {

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
									Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
									Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();

				});
				setOnCancelled(value -> {
					waitAlert.close();
					new AlertDesigner(settings.getLanguage().getString("MEX007"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
				});
				setOnFailed(value -> {
					new AlertDesigner(settings.getLanguage().getString("MEX008"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
					waitAlert.close();
				});
			}

			@Override
			protected JSONObject call() throws Exception {
				return JSON.executeHttpPostJSON(settings.getDatabaseUrl(), JSONRequest.UPDATE_INFO(key, value));
			}
		};

		waitAlert.show();
		Thread taskThread = new Thread(task);
		taskThread.start();
	}

	/**
	 * Insert Info
	 */
	public static void insertInfo(String key, String value, Settings settings, Stage ownerStage) {

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
									Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
									Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();

				});
				setOnCancelled(value -> {
					waitAlert.close();
					new AlertDesigner(settings.getLanguage().getString("MEX007"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
				});
				setOnFailed(value -> {
					new AlertDesigner(settings.getLanguage().getString("MEX008"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
					waitAlert.close();
				});
			}

			@Override
			protected JSONObject call() throws Exception {
				return JSON.executeHttpPostJSON(settings.getDatabaseUrl(), JSONRequest.INSERT_INFO(key, value));
			}
		};

		waitAlert.show();
		Thread taskThread = new Thread(task);
		taskThread.start();
	}

	public static void getUserMenuCongrListInfo(Settings settings, Stage ownerStage, UpdateData callback) {

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

							Info info = new Info();

							JSONArray jsonArray = jsonObject.getJSONArray("result");
							for (Object object : jsonArray) {
								JSONObject json = (JSONObject) object;
								info.setInfo(settings, json);
							}

							callback.updateInfo(info);
						}

				});
				setOnCancelled(value -> {
					waitAlert.close();
					new AlertDesigner(settings.getLanguage().getString("MEX007"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
				});
				setOnFailed(value -> {
					new AlertDesigner(settings.getLanguage().getString("MEX008"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
					waitAlert.close();
				});
			}

			@Override
			protected JSONObject call() throws Exception {
				return JSON.executeHttpPostJSON(settings.getDatabaseUrl(),
						JSONRequest.GET_INFO(Info.KEYS.getUserMenuCongrListSelect()));
			}
		};

		waitAlert.show();
		Thread taskThread = new Thread(task);
		taskThread.start();
	}

	public static void getUserMenuMeetingsInfo(Settings settings, Stage ownerStage, UpdateData callback) {

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

							Info info = new Info();

							JSONArray jsonArray = jsonObject.getJSONArray("result");
							for (Object object : jsonArray) {
								JSONObject json = (JSONObject) object;
								info.setInfo(settings, json);
							}

							callback.updateInfo(info);
						}

				});
				setOnCancelled(value -> {
					waitAlert.close();
					new AlertDesigner(settings.getLanguage().getString("MEX007"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
				});
				setOnFailed(value -> {
					new AlertDesigner(settings.getLanguage().getString("MEX008"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
					waitAlert.close();
				});
			}

			@Override
			protected JSONObject call() throws Exception {
				return JSON.executeHttpPostJSON(settings.getDatabaseUrl(),
						JSONRequest.GET_INFO(Info.KEYS.getUserMenuMeetingsSelect()));
			}
		};

		waitAlert.show();
		Thread taskThread = new Thread(task);
		taskThread.start();

	}

	public static void getUserMenuNaturalDisasterInfo(Settings settings, Stage ownerStage, UpdateData callback) {

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

							Info info = new Info();

							JSONArray jsonArray = jsonObject.getJSONArray("result");
							for (Object object : jsonArray) {
								JSONObject json = (JSONObject) object;
								info.setInfo(settings, json);
							}

							callback.updateInfo(info);
						}

				});
				setOnCancelled(value -> {
					waitAlert.close();
					new AlertDesigner(settings.getLanguage().getString("MEX007"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
				});
				setOnFailed(value -> {
					new AlertDesigner(settings.getLanguage().getString("MEX008"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
					waitAlert.close();
				});
			}

			@Override
			protected JSONObject call() throws Exception {
				return JSON.executeHttpPostJSON(settings.getDatabaseUrl(),
						JSONRequest.GET_INFO(Info.KEYS.getUserMenuNaturalDisasterSelect()));
			}
		};

		waitAlert.show();
		Thread taskThread = new Thread(task);
		taskThread.start();

	}

	/**
	 * Get All Week Type
	 * 
	 * @param string
	 * 
	 * @param weekStart
	 * @param weekEnd
	 */
	public static void getAllActivities(String password, String weekcode, Settings settings, Stage ownerStage,
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
							ObservableList<Activities> list = Activities.parseJSONResult(jsonObject);
							callback.updateActivities(list);
						} else
							callback.updateActivities(null);
				});
				setOnCancelled(value -> {
					waitAlert.close();
					new AlertDesigner(settings.getLanguage().getString("MEX007"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
				});
				setOnFailed(value -> {
					new AlertDesigner(settings.getLanguage().getString("MEX008"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
					waitAlert.close();
				});
			}

			@Override
			protected JSONObject call() throws Exception {
				return JSON.executeHttpPostJSON(settings.getDatabaseUrl(),
						JSONRequest.GET_ALL_ACTIVITIES(password, weekcode));
			}
		};

		waitAlert.show();
		Thread taskThread = new Thread(task);
		taskThread.start();

	}

	public static void printUser(User user, Settings settings, Stage ownerStage, Language language) {

		Alert waitAlert = createWaitAlert(settings, Meta.Application.getFullTitle(),
				settings.getLanguage().getString("MEX005"), ownerStage);

		Task<Void> task = new Task<Void>() {

			{
				setOnSucceeded(value -> waitAlert.close());

				setOnCancelled(value -> {
					waitAlert.close();
					new AlertDesigner(settings.getLanguage().getString("MEX007"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
				});

				setOnFailed(value -> {
					new AlertDesigner(settings.getLanguage().getString("MEX008"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
					waitAlert.close();
				});
			}

			@Override
			protected Void call() throws Exception {

				String reportPath = Jasper.Layouts.SM_NET_USERS_USERS.getAbsolutePath();

				Map<String, Object> parameters = new HashMap<String, Object>();
				parameters.put("txtaccess", language.getString("jasper.layout.user.txtaccess"));
				parameters.put("txtconfig", language.getString("jasper.layout.user.txtconfig"));
				parameters.put("txtusername", language.getString("jasper.layout.user.txtusername"));
				parameters.put("txtpassword", language.getString("jasper.layout.user.txtpassword"));
				parameters.put("txturl", language.getString("jasper.layout.user.txturl"));
				parameters.put("txtkey", language.getString("jasper.layout.user.txtkey"));

				parameters.put("image", Jasper.Layouts.SM_NET_USERS_LOGO.getAbsolutePath());
				parameters.put("username", user.getUsername());
				parameters.put("password", Crypt.decrypt(user.getPasswordEncrypted(), settings.getDatabaseSecretKey()));
				parameters.put("url", settings.getDatabaseUrl());
				parameters.put("key", Crypt.decrypt(settings.getDatabaseKeyEncrypted(), settings.getApplicationKey()));

				try {
					JasperReport jasperReport = JasperCompileManager.compileReport(reportPath);
					JRDataSource jrDataSource = new JREmptyDataSource();
					JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrDataSource);

					JasperViewer jv = new JasperViewer(jasperPrint, false);
					jv.setTitle(Meta.Application.getFullTitle());
					jv.setIconImage(SwingFXUtils.fromFXImage(Meta.Resources.getImageApplicationIcon(), null));
					jv.setVisible(true);

				} catch (JRException e) {
					System.out.println(e.getMessage());
				} catch (JRFontNotFoundException e) {
					System.out.println(e.getMessage());
				}

				return null;
			}
		};

		waitAlert.show();
		Thread taskThread = new Thread(task);
		taskThread.start();
	}

	public static void printWeek(ArrayList<Week> weeks, ObservableList<Member> membersList, String congregationName,
			Settings settings, Stage ownerStage, Language language, boolean extendedName) {

		Alert waitAlert = createWaitAlert(settings, Meta.Application.getFullTitle(),
				settings.getLanguage().getString("MEX005"), ownerStage);

		Task<Void> task = new Task<Void>() {

			{
				setOnSucceeded(value -> waitAlert.close());

				setOnCancelled(value -> {
					waitAlert.close();
					new AlertDesigner(settings.getLanguage().getString("MEX007"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
				});

				setOnFailed(value -> {
					new AlertDesigner(settings.getLanguage().getString("MEX008"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
					waitAlert.close();
				});
			}

			@Override
			protected Void call() throws Exception {

				try {

					String programmReportFile = Jasper.Layouts.SM_NET_MEETINGS_PROGRAMM.getAbsolutePath();
					String weekReportFile = Jasper.Layouts.SM_NET_MEETINGS_WEEK.getAbsolutePath();

					JasperReport programmJasperReport = JasperCompileManager.compileReport(programmReportFile);
					JasperReport weekJasperReport = JasperCompileManager.compileReport(weekReportFile);

					ArrayList<JRWeek> jrWeeks = new ArrayList<>();
					for (Week week : weeks)
						jrWeeks.add(JRWeek.newObject(week, membersList, language, extendedName, false));

					JRBeanCollectionDataSource jrWeeksDataSource = new JRBeanCollectionDataSource(jrWeeks);

					Map<String, Object> parameters = new HashMap<String, Object>();
					parameters.put("congregationName",
							String.format(language.getString("jasper.layout.meeting.congregation"), congregationName));
					parameters.put("programmName", language.getString("jasper.layout.meeting.programm").toUpperCase());
					parameters.put("jrWeekReport", weekJasperReport);
					parameters.put("jrWeeksDataSource", jrWeeksDataSource);

					JasperPrint jasperPrint = JasperFillManager.fillReport(programmJasperReport, parameters,
							new JREmptyDataSource());

					JasperViewer jv = new JasperViewer(jasperPrint, false);
					jv.setTitle(Meta.Application.getFullTitle());
					jv.setIconImage(SwingFXUtils.fromFXImage(Meta.Resources.getImageApplicationIcon(), null));
					jv.setVisible(true);

				} catch (JRException e) {
					System.out.println(e.getMessage());
				}

				return null;
			}
		};

		waitAlert.show();
		Thread taskThread = new Thread(task);
		taskThread.start();
	}

	public static void printWeekComplete(ArrayList<Week> weeks, ObservableList<Member> membersList,
			String congregationName, Settings settings, Stage ownerStage, Language language, boolean extendedName) {

		Alert waitAlert = createWaitAlert(settings, Meta.Application.getFullTitle(),
				settings.getLanguage().getString("MEX005"), ownerStage);

		Task<Void> task = new Task<Void>() {

			{
				setOnSucceeded(value -> waitAlert.close());

				setOnCancelled(value -> {
					waitAlert.close();
					new AlertDesigner(settings.getLanguage().getString("MEX007"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
				});

				setOnFailed(value -> {
					new AlertDesigner(settings.getLanguage().getString("MEX008"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
					waitAlert.close();
				});
			}

			@Override
			protected Void call() throws Exception {

				try {

					String programmReportFile = Jasper.Layouts.SM_NET_MEETINGS_PROGRAMM_COMPLETE.getAbsolutePath();
					String weekReportFile = Jasper.Layouts.SM_NET_MEETINGS_WEEK_COMPLETE.getAbsolutePath();

					JasperReport programmJasperReport = JasperCompileManager.compileReport(programmReportFile);
					JasperReport weekJasperReport = JasperCompileManager.compileReport(weekReportFile);

					ArrayList<JRWeek> jrWeeks = new ArrayList<>();
					for (Week week : weeks) {

						JRWeek newJRWeek = JRWeek.newObject(week, membersList, language, extendedName, true);

						String congregationNameHeader = String
								.format(language.getString("jasper.layout.meeting.congregation"), congregationName);

						newJRWeek.setCongregationName(congregationNameHeader);

						String programmName = JRWeek.getProgrammNameHeader(week, language);

						newJRWeek.setProgrammName(programmName);

						jrWeeks.add(newJRWeek);
					}

					JRBeanCollectionDataSource jrWeeksDataSource = new JRBeanCollectionDataSource(jrWeeks);

					Map<String, Object> parameters = new HashMap<String, Object>();
					parameters.put("jrWeekReport", weekJasperReport);
					parameters.put("jrWeeksDataSource", jrWeeksDataSource);

					JasperPrint jasperPrint = JasperFillManager.fillReport(programmJasperReport, parameters,
							new JREmptyDataSource());

					JasperViewer jv = new JasperViewer(jasperPrint, false);
					jv.setTitle(Meta.Application.getFullTitle());
					jv.setIconImage(SwingFXUtils.fromFXImage(Meta.Resources.getImageApplicationIcon(), null));
					jv.setVisible(true);

				} catch (JRException e) {
					System.out.println(e.getMessage());
				}

				return null;
			}
		};

		waitAlert.show();
		Thread taskThread = new Thread(task);
		taskThread.start();
	}

	public static void updateMemberNaturalDisaster(String spMemberID, String spInf40, String spInf41, Settings settings,
			Stage ownerStage, TabPane congrTabPane, Tab newMemberTab, Tab membersTab,
			UserMenuNaturalDisasterList callback) {

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
									Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
									Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
				});
				setOnCancelled(value -> {
					waitAlert.close();
					new AlertDesigner(settings.getLanguage().getString("MEX007"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
				});
				setOnFailed(value -> {
					new AlertDesigner(settings.getLanguage().getString("MEX008"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
					waitAlert.close();
				});
			}

			@Override
			protected JSONObject call() throws Exception {
				return JSON.executeHttpPostJSON(settings.getDatabaseUrl(),
						JSONRequest.UPDATE_MEMBER_NATURAL_DISASTER(spMemberID, spInf40, spInf41));
			}
		};

		waitAlert.show();
		Thread taskThread = new Thread(task);
		taskThread.start();

	}

	public static void updateFamilyNaturalDisaster(String spFamID, String spInf1, String spInf2, String spInf3,
			String spInf4, String spInf5, String spInf7, Settings settings, Stage ownerStage, TabPane congrTabPane,
			Tab newFamilyTab, Tab familiesTab, UserMenuNaturalDisasterList ownerCtrl) {

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
							ownerCtrl.updateFamilies();
						} else
							new AlertDesigner(settings.getLanguage().getString("MEX006"), ownerStage, AlertType.ERROR,
									Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
									Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
				});
				setOnCancelled(value -> {
					waitAlert.close();
					new AlertDesigner(settings.getLanguage().getString("MEX007"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
				});
				setOnFailed(value -> {
					new AlertDesigner(settings.getLanguage().getString("MEX008"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
					waitAlert.close();
				});
			}

			@Override
			protected JSONObject call() throws Exception {
				return JSON.executeHttpPostJSON(settings.getDatabaseUrl(), JSONRequest
						.UPDATE_FAMILY_NATURAL_DISASTER(spFamID, spInf1, spInf2, spInf3, spInf4, spInf5, spInf7));
			}
		};

		waitAlert.show();
		Thread taskThread = new Thread(task);
		taskThread.start();
	}

	public static void printNaturalDisaster(String overseerName, String overseerPhone, String overseerMail,
			ObservableList<Member> membersList, ObservableList<Family> familiesList, String congregationName,
			Settings settings, Stage ownerStage, Language language) {

		Alert waitAlert = createWaitAlert(settings, Meta.Application.getFullTitle(),
				settings.getLanguage().getString("MEX005"), ownerStage);

		Task<Void> task = new Task<Void>() {

			{
				setOnSucceeded(value -> waitAlert.close());

				setOnCancelled(value -> {
					waitAlert.close();
					new AlertDesigner(settings.getLanguage().getString("MEX007"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
				});

				setOnFailed(value -> {
					new AlertDesigner(settings.getLanguage().getString("MEX008"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
					waitAlert.close();
				});
			}

			@Override
			protected Void call() throws Exception {

				try {

					String programmReportFile = Jasper.Layouts.SM_NET_NATURAL_DISASTER.getAbsolutePath();
					String familyReportFile = Jasper.Layouts.SM_NET_NATURAL_DISASTER_FAMILY.getAbsolutePath();
					String memberReportFile = Jasper.Layouts.SM_NET_NATURAL_DISASTER_MEMBER.getAbsolutePath();

					JasperReport programmJasperReport = JasperCompileManager.compileReport(programmReportFile);
					JasperReport familyJasperReport = JasperCompileManager.compileReport(familyReportFile);
					JasperReport memberJasperReport = JasperCompileManager.compileReport(memberReportFile);

					ArrayList<JRNaturalDisasterFamily> jrFamilies = new ArrayList<>();

					for (Family family : familiesList) {
						JRNaturalDisasterFamily jrFamily = new JRNaturalDisasterFamily(family, memberJasperReport,
								membersList);
						jrFamilies.add(jrFamily);
					}

					JRBeanCollectionDataSource jrFamiliesDataSource = new JRBeanCollectionDataSource(jrFamilies);

					Map<String, Object> parameters = new HashMap<String, Object>();
					parameters.put("txtSurname", language.getString("jasper.layout.naturaldisaster.surname"));
					parameters.put("txtFirstName", language.getString("jasper.layout.naturaldisaster.firstname"));
					parameters.put("txtSurnameWife", language.getString("jasper.layout.naturaldisaster.surnamewife"));
					parameters.put("txtMobile", language.getString("jasper.layout.naturaldisaster.mobile"));
					parameters.put("txtMail", language.getString("jasper.layout.naturaldisaster.mail"));
					parameters.put("txtAddress", language.getString("jasper.layout.naturaldisaster.address"));

					parameters.put("congregationName",
							String.format(language.getString("jasper.layout.meeting.congregation"), congregationName));

					parameters.put("txtOverseerName", language.getString("jasper.layout.naturaldisaster.overseer"));
					parameters.put("txtOverseerPhone", language.getString("jasper.layout.naturaldisaster.mobile"));
					parameters.put("txtOverseerMail", language.getString("jasper.layout.naturaldisaster.mail"));
					parameters.put("txtDate", language.getString("jasper.layout.naturaldisaster.date"));
					parameters.put("txtPage", language.getString("jasper.layout.naturaldisaster.page"));
					parameters.put("txtOf", language.getString("jasper.layout.naturaldisaster.of"));

					SimpleDateFormat sdf = new SimpleDateFormat(
							language.getString("jasper.layout.naturaldisaster.datepattern"));
					String date = sdf.format(new Date());

					parameters.put("date", date);
					parameters.put("overseerName", overseerName);
					parameters.put("overseerPhone", overseerPhone);
					parameters.put("overseerMail", overseerMail);

					parameters.put("programmName",
							language.getString("jasper.layout.naturaldisaster.programm").toUpperCase());

					parameters.put("familyJasperReport", familyJasperReport);
					parameters.put("jrFamiliesDataSource", jrFamiliesDataSource);

					JasperPrint jasperPrint = JasperFillManager.fillReport(programmJasperReport, parameters,
							new JREmptyDataSource());

					JasperViewer jv = new JasperViewer(jasperPrint, false);
					jv.setTitle(Meta.Application.getFullTitle());
					jv.setIconImage(SwingFXUtils.fromFXImage(Meta.Resources.getImageApplicationIcon(), null));
					jv.setVisible(true);

				} catch (JRException e) {
					System.out.println(e.getMessage());
				}

				return null;
			}
		};

		waitAlert.show();
		Thread taskThread = new Thread(task);
		taskThread.start();
	}

	public static void printMonitorPassword(String memberName, String monitorPassword, String link, Settings settings,
			Stage ownerStage, Language language) {

		Alert waitAlert = createWaitAlert(settings, Meta.Application.getFullTitle(),
				settings.getLanguage().getString("MEX005"), ownerStage);

		Task<Void> task = new Task<Void>() {

			{
				setOnSucceeded(value -> waitAlert.close());

				setOnCancelled(value -> {
					waitAlert.close();
					new AlertDesigner(settings.getLanguage().getString("MEX007"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
				});

				setOnFailed(value -> {
					new AlertDesigner(settings.getLanguage().getString("MEX008"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
					waitAlert.close();
				});
			}

			@Override
			protected Void call() throws Exception {

				try {

					String programmReportFile = Jasper.Layouts.SM_NET_MONITOR.getAbsolutePath();

					JasperReport programmJasperReport = JasperCompileManager.compileReport(programmReportFile);

					Map<String, Object> parameters = new HashMap<String, Object>();
					parameters.put("image", Jasper.Layouts.SM_NET_USERS_LOGO.getAbsolutePath());
					parameters.put("memberName", memberName);
					parameters.put("password", monitorPassword);
					parameters.put("txtlink", language.getString("jasper.layout.monitor.link"));
					parameters.put("link", link);
					parameters.put("txtpassword", language.getString("jasper.layout.monitor.password"));

					JasperPrint jasperPrint = JasperFillManager.fillReport(programmJasperReport, parameters,
							new JREmptyDataSource());

					JasperViewer jv = new JasperViewer(jasperPrint, false);
					jv.setTitle(Meta.Application.getFullTitle());
					jv.setIconImage(SwingFXUtils.fromFXImage(Meta.Resources.getImageApplicationIcon(), null));
					jv.setVisible(true);

				} catch (JRException e) {
					System.out.println(e.getMessage());
				}

				return null;
			}
		};

		waitAlert.show();
		Thread taskThread = new Thread(task);
		taskThread.start();
	}

	public static void updatePublicMeeting(String spWeekID, String spInf30, String spInf31, String spInf32,
			String spInf33, String spInf34, Settings settings, Stage ownerStage, TabPane tabPane, Tab newTab,
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

							tabPane.getTabs().remove(newTab);
							callback.updateWeeks();

						} else
							new AlertDesigner(settings.getLanguage().getString("MEX006"), ownerStage, AlertType.ERROR,
									Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
									Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
				});
				setOnCancelled(value -> {
					waitAlert.close();
					new AlertDesigner(settings.getLanguage().getString("MEX007"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
				});
				setOnFailed(value -> {
					new AlertDesigner(settings.getLanguage().getString("MEX008"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
					waitAlert.close();
				});
			}

			@Override
			protected JSONObject call() throws Exception {
				return JSON.executeHttpPostJSON(settings.getDatabaseUrl(),
						JSONRequest.UPDATE_PUBLIC_MEETING(spWeekID, spInf30, spInf31, spInf32, spInf33, spInf34));
			}
		};

		waitAlert.show();
		Thread taskThread = new Thread(task);
		taskThread.start();
	}

	public static void checkConnection(String url, Settings settings, Stage ownerStage,
			SupportPlannerView application) {

		Alert waitAlert = createWaitAlert(settings, Meta.Application.getFullTitle(),
				settings.getLanguage().getString("MEX005"), ownerStage);

		Task<JSONObject> task = new Task<JSONObject>() {

			{
				setOnSucceeded(value -> {

					waitAlert.close();

					JSONObject jsonObject = getValue();

					int status = -1;
					try {
						if (jsonObject != null)
							status = jsonObject.getInt("status");
					} catch (JSONException e) {
					}

					switch (status) {
					case 5:
						application.getAlertBuilder().information(ownerStage,
								settings.getLanguage().getString("sp.settings.connection.test5")).show();
						break;
					case 6:
						application.getAlertBuilder().information(ownerStage,
								settings.getLanguage().getString("sp.settings.connection.test3")).show();
						break;

					default:

						application.getAlertBuilder()
								.error(ownerStage, settings.getLanguage().getString("sp.settings.connection.test4"))
								.show();

						break;
					}

				});
				setOnCancelled(value -> {
					waitAlert.close();
					new AlertDesigner(settings.getLanguage().getString("MEX007"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
				});
				setOnFailed(value -> {
					new AlertDesigner(settings.getLanguage().getString("MEX008"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
					waitAlert.close();
				});
			}

			@Override
			protected JSONObject call() throws Exception {
				return JSON.executeHttpPostJSON(url, JSONRequest.CHECK_CONNECTION());
			}
		};

		waitAlert.show();
		Thread taskThread = new Thread(task);
		taskThread.start();
	}

	public static void createConfigPHPFile(String host, String dbname, String dbusername, String dbpassword,
			File directory, Stage ownerStage, Settings settings, SupportPlannerView application) {

		Alert waitAlert = createWaitAlert(settings, Meta.Application.getFullTitle(),
				settings.getLanguage().getString("MEX005"), ownerStage);

		Task<Void> task = new Task<Void>() {

			{
				setOnSucceeded(value -> {
					application.getAlertBuilder()
							.information(ownerStage,
									settings.getLanguage().getString("sp.settings.database.createconfig.created"))
							.show();
					waitAlert.close();
				});

				setOnCancelled(value -> {
					waitAlert.close();
					new AlertDesigner(settings.getLanguage().getString("MEX007"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
				});

				setOnFailed(value -> {
					new AlertDesigner(settings.getLanguage().getString("MEX008"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
					waitAlert.close();
				});
			}

			@Override
			protected Void call() throws Exception {

				File config = new File(directory, "config.php");
				config.createNewFile();

				BufferedWriter bw = new BufferedWriter(new FileWriter(config));

				bw.write("<?php\n");
				bw.write(String.format("define ( 'DB_USER', \"%s\" );\n", dbusername));
				bw.write(String.format("define ( 'DB_PASSWORD', \"%s\" );\n", dbpassword));
				bw.write(String.format("define ( 'DB_DATABASE', \"%s\" );\n", dbname));
				bw.write(String.format("define ( 'DB_SERVER', \"%s\" );\n", host));
				bw.write("?>");

				bw.close();

				return null;
			}
		};

		waitAlert.show();
		Thread taskThread = new Thread(task);
		taskThread.start();

	}

	public static void createBackupDatabase(String host, String dbname, String dbusername, String dbpassword,
			File directory, Stage ownerStage, Settings settings, SupportPlannerView application) {

		Alert waitAlert = createWaitAlert(settings, Meta.Application.getFullTitle(),
				settings.getLanguage().getString("MEX005"), ownerStage);

		Task<String> task = new Task<String>() {

			{
				setOnSucceeded(value -> {

					String console = getValue();
					if (!console.trim().isEmpty())
						application.getAlertBuilder().error(ownerStage, console).show();
					else
						application.getAlertBuilder().information(ownerStage,
								settings.getLanguage().getString("sp.database.mysqldump.created")).show();

					waitAlert.close();
				});

				setOnCancelled(value -> {
					waitAlert.close();
					new AlertDesigner(settings.getLanguage().getString("MEX007"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
				});

				setOnFailed(value -> {
					new AlertDesigner(settings.getLanguage().getString("MEX008"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
					waitAlert.close();
				});
			}

			@Override
			protected String call() throws Exception {

				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmm");
				String fileName = String.format("Backup%s.splan", sdf.format(new Date()));
				File file = new File(directory, fileName);

				String command = "";

				command = "%s";
				command += " --skip-lock-tables";
				command += " --column-statistics=0";
				command += String.format(" -h %s", host);
				command += String.format(" -u %s %s", dbusername, dbname);
				command += String.format(" -p%s", dbpassword);
				command += " %s";

				switch (application.getSystem()) {
				case WINDOWS:

					command = String.format(command,
							String.format("\"%s\"", application.getMysqlDump().getAbsolutePath()),
							String.format("-r \"%s\"", file.getAbsolutePath()));

					break;
				case LINUX:

					command = String.format(command, String.format("%s", application.getMysqlDump().getAbsolutePath()),
							String.format("-r %s", file.getAbsolutePath()));

					break;
				case MAC:
					break;
				default:
					break;

				}

				// System.out.println("command : " + command);

				String console = "";

				Runtime rt = Runtime.getRuntime();
				Process p = rt.exec(command);

				BufferedReader input = new BufferedReader(new InputStreamReader(p.getErrorStream()));

				String line = null;
				while ((line = input.readLine()) != null) {
					// System.out.println(line);
					if (!line.contains("[Warning]"))
						console += line + "\n";
				}

				if (!console.trim().isEmpty())
					file.delete();

				return console;
			}
		};

		waitAlert.show();
		Thread taskThread = new Thread(task);
		taskThread.start();

	}

	public static void startRestoreDatabase(String host, String dbname, String dbusername, String dbpassword, File file,
			Stage ownerStage, Settings settings, SupportPlannerView application) {

		Alert waitAlert = createWaitAlert(settings, Meta.Application.getFullTitle(),
				settings.getLanguage().getString("MEX005"), ownerStage);

		Task<String> task = new Task<String>() {

			{
				setOnSucceeded(value -> {

					String console = getValue();
					if (!console.trim().isEmpty())
						application.getAlertBuilder().error(ownerStage, console).show();
					else
						application.getAlertBuilder().information(ownerStage,
								settings.getLanguage().getString("sp.database.mysqlrestore.restored")).show();

					waitAlert.close();
				});

				setOnCancelled(value -> {
					waitAlert.close();
					new AlertDesigner(settings.getLanguage().getString("MEX007"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
				});

				setOnFailed(value -> {
					new AlertDesigner(settings.getLanguage().getString("MEX008"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
					waitAlert.close();
				});
			}

			@Override
			protected String call() throws Exception {

				ProcessBuilder builder = null;
				String command = "";

				command = "%s";
				command += String.format(" -h %s", host);
				command += String.format(" --password=%s", dbpassword);
				command += String.format(" -u %s %s", dbusername, dbname);
				command += "%s";

				switch (application.getSystem()) {
				case WINDOWS:

					command = String.format(command,
							String.format("\"%s\"", application.getMysqlRestore().getAbsolutePath()),
							String.format(" -e \"source %s\"", file.getAbsolutePath()));

					break;
				case LINUX:

					builder = new ProcessBuilder();

					String mysqlValue = String.format("%s", application.getMysqlRestore().getAbsolutePath());
					String hostKey = "-h";
					String hostValue = host;
					String passwordValue = String.format("--password=%s", dbpassword);
					String dbKey = "-u";
					String dbUserValue = dbusername;
					String dbNameValue = dbname;
					String sourceKey = "-e";
					String sourceValue = String.format("source %s", file.getAbsolutePath());

					builder.command(mysqlValue, hostKey, hostValue, passwordValue, dbKey, dbUserValue, dbNameValue,
							sourceKey, sourceValue);

					break;
				case MAC:
					break;
				default:
					break;

				}

				String console = "";
				Process p = null;

				switch (application.getSystem()) {
				case LINUX:
					p = builder.start();
					break;
				case MAC:
					break;
				case WINDOWS:
					Runtime rt = Runtime.getRuntime();
					p = rt.exec(command);
					break;
				}

				BufferedReader input = new BufferedReader(new InputStreamReader(p.getErrorStream()));

				String line = null;
				while ((line = input.readLine()) != null) {
					// System.out.println(line);
					if (!line.contains("[Warning]"))
						console += line + "\n";
				}

				return console;
			}
		};

		waitAlert.show();

		Thread taskThread = new Thread(task);
		taskThread.start();
	}

	public static void cleanDatabase(Settings settings, Stage ownerStage, SupportPlannerView application) {

		Alert waitAlert = createWaitAlert(settings, Meta.Application.getFullTitle(),
				settings.getLanguage().getString("MEX005"), ownerStage);

		Task<JSONObject> task = new Task<JSONObject>() {

			{
				setOnSucceeded(value -> {

					waitAlert.close();

					application.getAlertBuilder()
							.information(ownerStage, settings.getLanguage().getString("sp.database.clean.done")).show();

				});
				setOnCancelled(value -> {
					waitAlert.close();
					new AlertDesigner(settings.getLanguage().getString("MEX007"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
				});
				setOnFailed(value -> {
					new AlertDesigner(settings.getLanguage().getString("MEX008"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
							Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
					waitAlert.close();
				});
			}

			@Override
			protected JSONObject call() throws Exception {
				return JSON.executeHttpPostJSON(settings.getDatabaseUrl(), JSONRequest.CLEAN_DATABASE());
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

		DialogPane dialogPane = waitAlert.getDialogPane();
		dialogPane.getStylesheets().add(Meta.Themes.SUPPORTPLANNER_THEME);
		dialogPane.getStyleClass().add("alert_001");

		waitAlert.setResult(ButtonType.OK);

		waitAlert.setTitle(title);
		waitAlert.setContentText(contentText);
		waitAlert.initOwner(stageOwner);

		return waitAlert;
	}
}
