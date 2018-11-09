package com.sm.net.app.json;

import org.json.JSONException;
import org.json.JSONObject;

import com.sm.net.util.enumeration.JSONStatus;

public class JSONRequest {

	public static JSONObject GET_COUNT_USERS() {
		return create(Integer.valueOf(1));
	}

	public static JSONObject RUN_INIT(String userEncrypted, String passwordEncrypted) {
		JSONObject jsonObj = create(Integer.valueOf(2));
		jsonObj.put("user", userEncrypted);
		jsonObj.put("password", passwordEncrypted);
		return jsonObj;
	}

	public static JSONStatus getStatus(JSONObject jsonObject) {
		int status = -1;
		if (jsonObject != null) {
			try {
				status = jsonObject.getInt("status");
			} catch (JSONException e) {
			}
		}
		return JSONStatus.getFromId(Integer.valueOf(status));
	}

	public static boolean isRequestOK(JSONObject jsonObject) {
		return isRequestOK(JSONRequest.getStatus(jsonObject));
	}

	public static boolean isRequestOK(JSONStatus jsonStatus) {
		boolean status = false;
		switch (jsonStatus) {
		case OK:
			status = true;
			break;
		default:
			System.out.println(jsonStatus.getText());
			break;
		}
		return status;
	}

	private static JSONObject create(Integer type) {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("type", type.intValue());
		return jsonObj;
	}
}
