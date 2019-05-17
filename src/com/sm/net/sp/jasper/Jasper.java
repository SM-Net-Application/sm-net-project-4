package com.sm.net.sp.jasper;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Jasper {

	private static final String DEFAULT_FOLDER = "layout";
	private static final String DEFAULT_EXT = ".jrxml";
	private static final String DEFAULT_IMAGE_EXT = ".png";

	public static class Layouts {

		public static final File SP_IMAGE_LAYOUT = getImage("iconSP");
		public static final File SP_USER_LAYOUT = getLayout("spUsers");
		public static final File SP_MINISTRY_PART_ROW_LAYOUT = getLayout("spMeetingMinistryPartRow");
		public static final File SP_CHRISTIANS_PART_ROW_LAYOUT = getLayout("spMeetingChristiansPartRow");
		public static final File SP_WEEK_LAYOUT = getLayout("spMeetingWeek");

		public static File getLayout(String name) {

			String layoutPath = getLayoutPath(name);
			URL resource = Jasper.class.getResource(layoutPath);

			URI uri;
			try {
				uri = resource.toURI();
				Path path = Paths.get(uri);
				File file = path.toFile();
				return file;
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			return null;
		}

		public static File getImage(String name) {

			String layoutPath = getImagePath(name);
			URL resource = Jasper.class.getResource(layoutPath);

			URI uri;
			try {
				uri = resource.toURI();
				Path path = Paths.get(uri);
				File file = path.toFile();
				return file;
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			return null;
		}

		public static String getLayoutPath(String name) {
			return DEFAULT_FOLDER.concat(File.separator).concat(name).concat(DEFAULT_EXT);
		}

		public static String getImagePath(String name) {
			return DEFAULT_FOLDER.concat(File.separator).concat(name).concat(DEFAULT_IMAGE_EXT);
		}
	}
}
