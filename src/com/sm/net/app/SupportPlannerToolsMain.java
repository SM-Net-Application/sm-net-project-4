package com.sm.net.app;

import java.io.File;
import java.io.IOException;

import javax.crypto.SecretKey;

import org.ini4j.Wini;

import com.sm.net.app.view.SupportPlannerTools;
import com.sm.net.util.Crypt;
import com.sm.net.util.MyApp;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class SupportPlannerToolsMain extends Application {

	/**
	 * Software SecretKey
	 */
	public static final SecretKey KEY_SOFTWARE = generateKeySoftware();
	public static Wini settings;

	public static String url = "";
	public static String user = "";
	public static String password = "";

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

		// System.out.println(Authenticator.isValid("Password",
		// ValidationType.VERY_STRONG));

		if (initSettings()) {
			loadGUI(primaryStage);
		} else {
			exit();
		}
	}

	private void exit() {
		System.exit(0);
	}

	private void loadGUI(Stage primaryStage) {

		try {

			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(SupportPlannerToolsMain.class.getResource("view/SupportPlannerTools.fxml"));

			AnchorPane anchorPane;
			anchorPane = (AnchorPane) fxmlLoader.load();

			Scene scene = new Scene(anchorPane);
			// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			primaryStage.setScene(scene);

			primaryStage.setTitle("SM-Net: " + AppInfos.getAppName());
			primaryStage.getIcons()
					.add(new Image(SupportPlannerToolsMain.class.getResourceAsStream("view/resources/icon.png")));

			primaryStage.setResizable(false);

			// primaryStage.initStyle(StageStyle.UNIFIED);

			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

				@Override
				public void handle(WindowEvent event) {
					exit();
				}
			});

			SupportPlannerTools controller = (SupportPlannerTools) fxmlLoader.getController();
			controller.setSupportPlannerToolsStage(primaryStage);
			// controller.init();

			primaryStage.show();

		} catch (IOException e) {
			exit();
		}
	}

	/**
	 * Load or create Settings
	 * 
	 * @return
	 */
	private boolean initSettings() {

		File settingsFile = MyApp.getMyAppFile("ini", "settings.ini", true);
		if (settingsFile != null) {
			try {
				settings = new Wini(settingsFile);
				if (settings != null)
					if (settings.containsKey("ONLINE_CONFIG"))
						return loadSettings();
					else
						return newSettings();
			} catch (IOException e) {
				return false;
			}
		}

		return false;
	}

	private boolean newSettings() throws IOException {

		settings.put("ONLINE_CONFIG", "url", "");
		settings.put("ONLINE_CONFIG", "user", "");
		settings.put("ONLINE_CONFIG", "password", "");
		settings.store();

		return true;
	}

	private boolean loadSettings() {

		url = settings.get("ONLINE_CONFIG", "url", String.class);
		user = settings.get("ONLINE_CONFIG", "user", String.class);
		password = settings.get("ONLINE_CONFIG", "password", String.class);

		return true;
	}

	/**
	 * Create Software SecretKey
	 * 
	 * @return SecretKey
	 */
	private static SecretKey generateKeySoftware() {
		return Crypt.generateKey("SupportPlanner");
	}
}
