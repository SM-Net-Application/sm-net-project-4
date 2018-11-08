package com.sm.net.app;

import javax.crypto.SecretKey;

import com.sm.net.app.exceptions.OperationCouldNotBeCompleted;
import com.sm.net.app.operations.Operations;
import com.sm.net.util.Crypt;

import javafx.application.Application;
import javafx.stage.Stage;

public class SupportPlannerTools extends Application {

	public static final SecretKey KEY_SOFTWARE = generateKeySoftware();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {

		final String URL = "https://sm-netzwerk.com/owner/supportplanner/exchange.php";

		testCheckUsers(URL);

		// testCrypt();

		System.out.println("END");
		System.exit(0);
	}

	/**
	 * Create Software SecretKey
	 * 
	 * @return SecretKey
	 */
	private static SecretKey generateKeySoftware() {
		return Crypt.generateKey(SupportPlannerTools.class.getSimpleName());
	}

	// private void testCrypt() {
	// SecretKey key = Crypt.generateKey("Salvatore");
	//
	// String encrypt = Crypt.encrypt("Testo da cryptare", key);
	// System.out.println(encrypt);
	//
	// String decrypt = Crypt.decrypt(encrypt, key);
	// System.out.println(decrypt);
	// }

	private void testCheckUsers(final String URL) {
		try {
			if (!Operations.isNoUsers(URL)) {
				System.out.println("Non ci sono utenti");

				String user = "SMolaro";
				String password = "PasswordSMolaro";
				String key = "SMNetKey";

				Operations.runInitialize(URL, user, password, key);

			} else
				System.out.println("Ci sono utenti");
		} catch (OperationCouldNotBeCompleted e) {
			System.out.println(e.getMessage());
		}
	}

}
