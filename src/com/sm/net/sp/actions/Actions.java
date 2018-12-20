package com.sm.net.sp.actions;

import javax.crypto.SecretKey;

import org.json.JSONObject;

import com.sm.net.app.json.JSONRequest;
import com.sm.net.util.Crypt;
import com.sm.net.util.JSON;
import com.sm.net.util.enumeration.JSONStatus;

public class Actions {

	public static void checkUser(String url, String key, String user, String password) {

		SecretKey secretKey = Crypt.generateKey(key);
		if (secretKey != null) {

			String userEnc = Crypt.encrypt(user, secretKey);
			String passwordEnc = Crypt.encrypt(password, secretKey);

			if (userEnc != null && passwordEnc != null) {

				JSONObject jsonObject = JSON.executeHttpPostJSON(url, JSONRequest.RUN_INIT(userEnc, passwordEnc));
				JSONStatus status = JSONRequest.getStatus(jsonObject);
				if (!JSONRequest.isRequestOK(status))
					System.out.println(status.getText());
			} else
				System.out.println("Unable to encrypt user and password");
		} else
			System.out.println("The inserted key can not be used");
	}
}
