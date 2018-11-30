package com.sm.net.sp;

import java.io.File;
import java.net.URL;

import com.sm.net.file.Extensions;
import com.sm.net.path.PathBuilder;
import com.sm.net.project.Project;
import com.sm.net.sp.view.SupportPlannerView;
import com.sm.net.sp.view.access.create.AccessCreate;
import com.sm.net.sp.view.init.language.InitLanguage;

import javafx.scene.image.Image;

public class Meta {

	public static class Application {

		public static final String DEVELOPER = "SM-Net";

		public static final String NAME = "SupportPlanner";

		public static final String VERSION = "1.0";

		public static String getFullTitle() {
			return DEVELOPER + ": " + NAME + " " + VERSION;
		}

		public static String getTitle() {
			return NAME + " " + VERSION;
		}

	}

	public static class Settings {

		public static final String FOLDER = "ini";

		public static final String FILE = "settings";

		public static File getFile() {

			String absoulutePath = PathBuilder.concatFolder(Project.currentWorkingDirectory(), FOLDER);
			absoulutePath = PathBuilder.concatFile(absoulutePath, FILE, Extensions.INI);

			return new File(absoulutePath);
		}
	}

	public static class Languages {

		public static final String FOLDER = "languages";

		public static File getDirectory() {
			return new File(PathBuilder.concatFolder(Project.currentWorkingDirectory(), FOLDER));
		}
	}

	public static class Views {
		public static final URL SUPPORTPLANNER_VIEW = SupportPlannerView.class.getResource("SupportPlannerView.fxml");
		public static final URL INIT_LANGUAGE = InitLanguage.class.getResource("InitLanguage.fxml");
		public static final URL ACCESS_CREATE = AccessCreate.class.getResource("AccessCreate.fxml");
	}

	public static class Themes {
		public static final String SUPPORTPLANNER_THEME = SupportPlannerMain.class.getResource("theme.css")
				.toExternalForm();
	}

	public static class Resources {
		public static final Image ICON = new Image(SupportPlannerMain.class.getResourceAsStream("resources/icon.png"));
	}
}
