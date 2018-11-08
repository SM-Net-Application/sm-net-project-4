package com.sm.net.app.operations;

import javax.crypto.SecretKey;

import org.json.JSONArray;
import org.json.JSONObject;

import com.sm.net.app.exceptions.OperationCouldNotBeCompleted;
import com.sm.net.app.json.JSONRequest;
import com.sm.net.util.Crypt;
import com.sm.net.util.JSON;
import com.sm.net.util.enumeration.JSONStatus;

public class Operations {

	/**
	 * Check if there are users in the database
	 * 
	 * @param url
	 * @return
	 * @throws Exception
	 */
	public static boolean isNoUsers(String url) throws OperationCouldNotBeCompleted {

		JSONObject jsonObject = JSON.executeHttpPostJSON(url, JSONRequest.GET_COUNT_USERS());
		JSONStatus status = JSONRequest.getStatus(jsonObject);
		if (JSONRequest.isRequestOK(status)) {

			JSONArray resultArray = jsonObject.getJSONArray("result");
			if (resultArray != null && resultArray.length() == 1) {

				JSONObject result = resultArray.getJSONObject(0);
				if (result != null) {

					String users = result.getString("users");
					if (users != null && !users.isEmpty()) {

						Integer usersNr = Integer.valueOf(users);
						if (usersNr.equals(Integer.valueOf(0)))
							return true;

					} else
						throw new OperationCouldNotBeCompleted("Unable to determine the value of the key \"users\"");
				} else
					throw new OperationCouldNotBeCompleted("The key \"result\" is not present in the JSON object");
			} else
				throw new OperationCouldNotBeCompleted("The key \"result\" is not present in the JSON array");
		} else
			throw new OperationCouldNotBeCompleted(status.getText());

		System.out.println(jsonObject.toString());

		return false;
	}

	/**
	 * Initialize the MySQL Users-Table
	 * 
	 * @param user
	 * @param password
	 * @param key
	 * @throws OperationCouldNotBeCompleted
	 */
	public static void runInitialize(String url, String user, String password, String key)
			throws OperationCouldNotBeCompleted {

		// Con la key Software encrypto la key User

		SecretKey secretKey = Crypt.generateKey(key);
		if (secretKey != null) {

			// Con la key User cryptata encrypto user e password
			String userEncrypted = Crypt.encrypt(user, secretKey);
			String passwordEncrypted = Crypt.encrypt(password, secretKey);

			if (userEncrypted != null && passwordEncrypted != null) {

				// Inserisco i dati nel database

				// JSON.executeHttpPostJSON(url, JSONRequest.GET_COUNT_USERS());

			} else
				throw new OperationCouldNotBeCompleted("Unable to encrypt user and password");

		} else
			throw new OperationCouldNotBeCompleted("The inserted key can not be used");
	}
}