package com.sm.net.sp;

import java.io.File;
import java.io.IOException;

import com.sm.net.directory.Directory;
import com.sm.net.file.Extensions;
import com.sm.net.file.FileUtils;
import com.sm.net.project.Language;
import com.sm.net.sp.view.SupportPlannerView;
import com.sm.net.sp.view.init.language.InitLanguage;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SupportPlannerMain extends Application {

	// public static final SecretKey KEY_SOFTWARE = generateKeySoftware();

	private File settingsFile;
	private SupportPlannerView supportPlannerViewCtrl;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {

		checkSettings(primaryStage);

		// System.out.println(Authenticator.isValid("Password",
		// ValidationType.VERY_STRONG));

		// if (initSettings()) {
		// loadGUI(primaryStage);
		// } else {
		// exit();
		// }

		// if (Meta.getFileSettings().exists()) {
		//
		// // Access check
		//
		// System.out.println("File Settings exists");
		//
		// } else {
		//
		// // Access create
		//
		// System.out.println("File Settings NOT exists");
		//
		// try {
		//
		// Settings settings = new Settings(new
		// SettingsConf(Meta.SETTINGS_FOLDER, Meta.SETTINGS_FILE));
		// System.out.println("File Settings is created");
		//
		// } catch (IOException e) {
		// System.out.println("Error: " + e.getMessage());
		// exit();
		// }
		// }
		//
		// exit();
	}

	private void checkSettings(Stage stage) {

		this.settingsFile = Meta.Settings.getFile();

		if (this.settingsFile != null) {

			if (this.settingsFile.exists()) {
				// loadSettings();
			} else {
				initProject(stage);
			}
		} else {
			exitError("ERR001");
		}
	}

	private void initProject(Stage stage) {

		initSupportPlannerView(stage);
		checkLanguages();

	}

	private void initSupportPlannerView(Stage stage) {

		try {

			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(Meta.Views.SUPPORTPLANNER_VIEW);

			BorderPane borderPane = (BorderPane) fxmlLoader.load();

			Scene scene = new Scene(borderPane);
			scene.getStylesheets().add(Meta.Themes.SUPPORTPLANNER_THEME);

			stage.setScene(scene);
			stage.setTitle(Meta.Application.getFullTitle());
			stage.getIcons().add(Meta.Resources.ICON);

			this.supportPlannerViewCtrl = (SupportPlannerView) fxmlLoader.getController();
			this.supportPlannerViewCtrl.setSupportPlannerViewStage(stage);

			stage.show();

		} catch (IOException e) {
			exitError("ERR005");
		}
	}

	private void checkLanguages() {

		File[] allFiles = Directory.listAllFiles(Meta.Languages.getDirectory());

		if (allFiles != null) {

			if (allFiles.length > 0) {

				ObservableList<Language> languages = checkLanguagesProperties(allFiles);

				if (languages.size() > 0) {

					initLanguageView(languages);

				} else {
					exitError("ERR004");
				}
			} else {
				exitError("ERR003");
			}
		} else {
			exitError("ERR002");
		}
	}

	private ObservableList<Language> checkLanguagesProperties(File[] allFiles) {

		ObservableList<Language> languages = FXCollections.observableArrayList();

		for (File file : allFiles) {

			if (FileUtils.getExtensionWithDot(file.getName()).equals(Extensions.PROPERTIES)) {

				try {
					Language lang = new Language(file);
					if (lang.isLoaded() && !lang.getLanguage().equals("null")) {
						languages.add(lang);
					}
				} catch (IOException e) {
				}
			}
		}

		return languages;
	}

	private void initLanguageView(ObservableList<Language> languages) {

		try {

			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(Meta.Views.INIT_LANGUAGE);

			AnchorPane anchorPane = (AnchorPane) fxmlLoader.load();

			InitLanguage controller = (InitLanguage) fxmlLoader.getController();
			controller.setLanguages(languages);
			controller.setSupportPlannerViewCtrl(this.supportPlannerViewCtrl);
			controller.init();

			this.supportPlannerViewCtrl.loadLanguageView(anchorPane);

		} catch (IOException e) {
			exitError("ERR006");
		}
	}

	private void exitError(String errorCode) {
		System.out.println(errorCode);
		System.exit(0);
	}

	public File getSettingsFile() {
		return settingsFile;
	}

	public void setSettingsFile(File settingsFile) {
		this.settingsFile = settingsFile;
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
