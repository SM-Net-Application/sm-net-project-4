package com.sm.net.sp.actions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.sm.net.javafx.AlertDesigner;
import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.jasper.Jasper;
import com.sm.net.sp.jasper.model.JRMinistryPart;
import com.sm.net.sp.json.JSONRequest;
import com.sm.net.sp.model.Family;
import com.sm.net.sp.model.Info;
import com.sm.net.sp.model.Member;
import com.sm.net.sp.model.MinistryPart;
import com.sm.net.sp.model.SerGroup;
import com.sm.net.sp.model.UpdateData;
import com.sm.net.sp.model.User;
import com.sm.net.sp.model.Week;
import com.sm.net.sp.settings.Settings;
import com.sm.net.sp.view.SupportPlannerCallback;
import com.sm.net.sp.view.home.user.menu.congr.UserMenuCongrList;
import com.sm.net.sp.view.home.user.menu.users.MenuUsersAddCallback;
import com.sm.net.sp.view.menu.settings.database.SettingsDatabaseCallback;
import com.sm.net.util.Crypt;
import com.sm.net.util.JSON;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
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
	public static void updateUserRules(String spUserID, String spInf1, String spInf2, String spInf3, String spInf4,
			Settings settings, Stage ownerStage, UpdateData callback) {

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
						JSONRequest.UPDATE_USER_RULES(spUserID, spInf1, spInf2, spInf3, spInf4));
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
	 */
	public static void insertMember(String spInf1, String spInf2, String spInf3, String spInf4, String spInf5,
			String spInf6, String spInf7, String spInf8, String spInf9, String spInf10, String spInf11, String spInf12,
			String spInf13, String spInf14, String spInf15, String spInf16, String spInf17, String spInf18,
			String spInf19, String spInf20, String spInf21, String spInf22, String spInf23, String spInf24,
			String spInf25, String spInf26, String spInf27, String spInf28, String spInf29, String spInf30,
			String spInf31, String spInf32, String spInf33, String spInf34, String spInf35, String spInf36,
			String spInf37, String spInf38, String spInf39, String spInf40, String spInf41, String spInf42,
			String spInf43, String spInf44, String spInf45, String spInf46, Settings settings, Stage ownerStage,
			TabPane congrTabPane, Tab newMemberTab, Tab membersTab, UpdateData callback) {

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
								spInf28, spInf29, spInf30, spInf31, spInf32, spInf33, spInf34, spInf35, spInf36,
								spInf37, spInf38, spInf39, spInf40, spInf41, spInf42, spInf43, spInf44, spInf45,
								spInf46));
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
	 */
	public static void updateMember(String spMemberID, String spInf1, String spInf2, String spInf3, String spInf4,
			String spInf6, String spInf7, String spInf8, String spInf9, String spInf10, String spInf11, String spInf12,
			String spInf13, String spInf14, String spInf15, String spInf16, String spInf17, String spInf18,
			String spInf19, String spInf20, String spInf21, String spInf22, String spInf23, String spInf24,
			String spInf25, String spInf26, String spInf27, String spInf28, String spInf29, String spInf30,
			String spInf31, String spInf32, String spInf33, String spInf34, String spInf35, String spInf36,
			String spInf37, String spInf38, String spInf39, String spInf40, String spInf41, String spInf42,
			String spInf43, String spInf44, String spInf45, String spInf46, Settings settings, Stage ownerStage,
			TabPane congrTabPane, Tab newMemberTab, Tab membersTab, UpdateData callback) {

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
								spInf28, spInf29, spInf30, spInf31, spInf32, spInf33, spInf34, spInf35, spInf36,
								spInf37, spInf38, spInf39, spInf40, spInf41, spInf42, spInf43, spInf44, spInf45,
								spInf46));
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
			String spInf6, String spInf7, String idToRemove, String idToSet, Settings settings, Stage ownerStage,
			TabPane congrTabPane, Tab newFamilyTab, Tab familiesTab, UserMenuCongrList ownerCtrl) {

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
				return JSON.executeHttpPostJSON(settings.getDatabaseUrl(), JSONRequest.INSERT_FAMILY(spInf1, spInf2,
						spInf3, spInf4, spInf5, spInf6, spInf7, idToRemove, idToSet));
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
			String spInf5, String spInf7, String idToRemove, String idToSet, Settings settings, Stage ownerStage,
			TabPane congrTabPane, Tab newFamilyTab, Tab familiesTab, UserMenuCongrList ownerCtrl) {

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
						spInf2, spInf3, spInf4, spInf5, spInf7, idToRemove, idToSet));
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
			String spInf25, String spInf26, String spInf27, String spInfMinistryParts, String spInfChristiansParts,
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
							callback.updateWeeks();

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
						JSONRequest.INSERT_WEEK(spInf1, spInf2, spInf3, spInf4, spInf5, spInf6, spInf7, spInf8, spInf9,
								spInf10, spInf11, spInf12, spInf13, spInf14, spInf15, spInf16, spInf17, spInf18,
								spInf19, spInf20, spInf21, spInf22, spInf23, spInf24, spInf25, spInf26, spInf27,
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
			String spInf24, String spInf25, String spInf26, String spInf27, String spInfMinistryParts,
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
						JSONRequest.UPDATE_WEEK(spWeekID, spInf1, spInf2, spInf3, spInf4, spInf5, spInf6, spInf7,
								spInf8, spInf9, spInf10, spInf11, spInf12, spInf13, spInf14, spInf15, spInf16, spInf17,
								spInf18, spInf19, spInf20, spInf21, spInf22, spInf23, spInf24, spInf25, spInf26,
								spInf27, spInfMinistryParts, spInfChristiansParts));
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
						JSONRequest.GET_INFO(Info.KEYS.getUserMenuMeetingsSelect()));
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
							Meta.Application.getFullTitle(), Meta.Resources.ICON).showAndWait();
				});

				setOnFailed(value -> {
					new AlertDesigner(settings.getLanguage().getString("MEX008"), ownerStage, AlertType.ERROR,
							Meta.Application.getFullTitle(), Meta.Resources.ICON).showAndWait();
					waitAlert.close();
				});
			}

			@Override
			protected Void call() throws Exception {

				String reportPath = Jasper.Layouts.SP_USER_LAYOUT.getAbsolutePath();

				Map<String, Object> parameters = new HashMap<String, Object>();
				parameters.put("txtaccess", language.getString("jasper.layout.user.txtaccess"));
				parameters.put("txtconfig", language.getString("jasper.layout.user.txtconfig"));
				parameters.put("txtusername", language.getString("jasper.layout.user.txtusername"));
				parameters.put("txtpassword", language.getString("jasper.layout.user.txtpassword"));
				parameters.put("txturl", language.getString("jasper.layout.user.txturl"));
				parameters.put("txtkey", language.getString("jasper.layout.user.txtkey"));

				parameters.put("image", Jasper.Layouts.SP_IMAGE_LAYOUT.getAbsolutePath());
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
					jv.setIconImage(SwingFXUtils.fromFXImage(Meta.Resources.ICON, null));
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

	public static void printWeek(Week selectedWeek, Settings settings, Stage ownerStage, Language language) {

		Alert waitAlert = createWaitAlert(settings, Meta.Application.getFullTitle(),
				settings.getLanguage().getString("MEX005"), ownerStage);

		Task<Void> task = new Task<Void>() {

			{
				setOnSucceeded(value -> waitAlert.close());

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
			protected Void call() throws Exception {

				String reportPath = Jasper.Layouts.SP_MINISTRY_PART_ROW_LAYOUT.getAbsolutePath();

				ArrayList<JRMinistryPart> jrMinistryPart = new ArrayList<>();
				for (MinistryPart ministryPart : selectedWeek.getMinistryPartList())
					jrMinistryPart.add(JRMinistryPart.newObject(ministryPart, language));

				JRBeanCollectionDataSource jrDataSource = new JRBeanCollectionDataSource(jrMinistryPart);

				Map<String, Object> parameters = new HashMap<String, Object>();
				parameters.put("header", "EFFICACI NEL MINISTERO");

				try {
					JasperReport jasperReport = JasperCompileManager.compileReport(reportPath);
					// JRDataSource jrDataSource = new JREmptyDataSource();
					JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, jrDataSource);

					JasperViewer jv = new JasperViewer(jasperPrint, false);
					jv.setTitle(Meta.Application.getFullTitle());
					jv.setIconImage(SwingFXUtils.fromFXImage(Meta.Resources.ICON, null));
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
