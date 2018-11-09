package com.sm.net.app;

import java.io.File;
import java.io.IOException;

import javax.crypto.SecretKey;

import org.ini4j.InvalidFileFormatException;
import org.ini4j.Wini;

import com.sm.net.util.Crypt;
import com.sm.net.util.MyApp;

import javafx.application.Application;
import javafx.stage.Stage;

public class SupportPlannerTools extends Application {

	/**
	 * Software SecretKey
	 */
	public static final SecretKey KEY_SOFTWARE = generateKeySoftware();

	/**
	 * Method Main
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * JavaFX Method start
	 */
	@Override
	public void start(Stage primaryStage) {

		loadSettings();

		// final String URL =
		// "https://sm-netzwerk.com/owner/supportplanner/exchange.php";

		// testCheckUsers(URL);

		// testCrypt();

		System.out.println("END");
		System.exit(0);
	}

	/**
	 * Load or create Settings
	 */
	private void loadSettings() {

		File iniDirSettings = MyApp.getMyAppFolder("ini", true);
		if (iniDirSettings != null) {

			File iniFileSettings = MyApp.getMyAppFile(iniDirSettings, "settings.ini");
			if (iniFileSettings != null) {

				if (!iniFileSettings.exists())
					try {
						iniFileSettings.createNewFile();
					} catch (IOException e) {
						e.printStackTrace();
					}

				Wini ini;
				try {

					ini = new Wini(iniFileSettings);
					ini.put("SupportPlannerOnlineConfig", "URL", "");
					ini.store();

				} catch (InvalidFileFormatException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

			} else
				System.out.println("Il file non esiste, devo crearlo");

		} else
			System.out.println(
					"La cartella non esiste, non è stata creata o qualche altro problema. Non posso caricare le impostazioni. Messaggio e chiudo l'app");
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

	// private void testCheckUsers(final String URL) {
	// try {
	// if (Operations.isNoUsers(URL)) {
	//
	// String user = "SMolaro";
	// String password = "PasswordSMolaro";
	// String key = "SMNetKey";
	//
	// Operations.runInitialize(URL, user, password, key);
	//
	// } else
	// System.out.println("Ci sono utenti");
	// } catch (OperationCouldNotBeCompleted e) {
	// System.out.println(e.getMessage());
	// }
	// }

}
