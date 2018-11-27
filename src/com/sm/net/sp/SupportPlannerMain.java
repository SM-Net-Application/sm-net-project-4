package com.sm.net.sp;

import java.io.File;
import java.io.IOException;

import javax.crypto.SecretKey;

import com.sm.net.sp.settings.Settings;
import com.sm.net.sp.settings.SettingsConf;
import com.sm.net.util.Crypt;
import com.sm.net.util.FilesFolders;

import javafx.application.Application;
import javafx.stage.Stage;

public class SupportPlannerMain extends Application {

	// public static final SecretKey KEY_SOFTWARE = generateKeySoftware();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {

		// System.out.println(Authenticator.isValid("Password",
		// ValidationType.VERY_STRONG));

		// if (initSettings()) {
		// loadGUI(primaryStage);
		// } else {
		// exit();
		// }

		if (Meta.getFileSettings().exists()) {

			// Access check

			System.out.println("File Settings exists");

		} else {

			// Access create

			System.out.println("File Settings NOT exists");

			try {
				
				Settings settings = new Settings(new SettingsConf(Meta.SETTINGS_FOLDER, Meta.SETTINGS_FILE));
				System.out.println("File Settings is created");
				
			} catch (IOException e) {
				System.out.println("Error: " + e.getMessage());
				exit();
			}
		}

		exit();
	}

	private void exit() {
		System.exit(0);
	}

	// private void loadGUI(Stage primaryStage) {
	//
	// try {
	//
	// FXMLLoader fxmlLoader = new FXMLLoader();
	// fxmlLoader.setLocation(SupportPlannerMain.class.getResource("view/SupportPlannerTools.fxml"));
	//
	// AnchorPane anchorPane;
	// anchorPane = (AnchorPane) fxmlLoader.load();
	//
	// Scene scene = new Scene(anchorPane);
	// //
	// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	//
	// primaryStage.setScene(scene);
	//
	// primaryStage.setTitle("SM-Net: " + Meta.getAppName());
	// primaryStage.getIcons()
	// .add(new
	// Image(SupportPlannerMain.class.getResourceAsStream("view/resources/icon.png")));
	//
	// primaryStage.setResizable(false);
	//
	// // primaryStage.initStyle(StageStyle.UNIFIED);
	//
	// primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
	//
	// @Override
	// public void handle(WindowEvent event) {
	// exit();
	// }
	// });
	//
	// SupportPlannerTools controller = (SupportPlannerTools)
	// fxmlLoader.getController();
	// controller.setSupportPlannerToolsStage(primaryStage);
	// // controller.init();
	//
	// primaryStage.show();
	//
	// } catch (IOException e) {
	// exit();
	// }
	// }
	//
	// /**
	// * Load or create Settings
	// *
	// * @return
	// */
	// private boolean initSettings() {
	//
	// File settingsFile = MyApp.getMyAppFile("ini", "settings.ini", true);
	// if (settingsFile != null) {
	// try {
	// settings = new Wini(settingsFile);
	// if (settings != null)
	// if (settings.containsKey("ONLINE_CONFIG"))
	// return loadSettings();
	// else
	// return newSettings();
	// } catch (IOException e) {
	// return false;
	// }
	// }
	//
	// return false;
	// }
	//
	// private boolean newSettings() throws IOException {
	//
	// settings.put("ONLINE_CONFIG", "url", "");
	// settings.put("ONLINE_CONFIG", "user", "");
	// settings.put("ONLINE_CONFIG", "password", "");
	// settings.store();
	//
	// return true;
	// }
	//
	// private boolean loadSettings() {
	//
	// url = settings.get("ONLINE_CONFIG", "url", String.class);
	// user = settings.get("ONLINE_CONFIG", "user", String.class);
	// password = settings.get("ONLINE_CONFIG", "password", String.class);
	//
	// return true;
	// }

	/**
	 * Create Software SecretKey
	 * 
	 * @return SecretKey
	 */
	// private static SecretKey generateKeySoftware() {
	// return Crypt.generateKey("Ll^B2jeE");
	// }
}
