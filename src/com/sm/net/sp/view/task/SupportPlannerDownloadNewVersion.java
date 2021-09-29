package com.sm.net.sp.view.task;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;

import com.sm.net.sp.view.SupportPlannerView;
import com.smnet.core.task.TaskInterface;

import javafx.stage.Stage;

public class SupportPlannerDownloadNewVersion implements TaskInterface {

	private SupportPlannerView application;
	private Stage viewStage;
	private File targetDirectory;
	private File targetFile;

	public SupportPlannerDownloadNewVersion(SupportPlannerView application, Stage viewStage, File targetDirectory) {
		super();

		this.application = application;
		this.viewStage = viewStage;
		this.targetDirectory = targetDirectory;
	}

	@Override
	public void start(HashMap<String, Object> hashMap) {

		String installFilename = this.application.getInstallFilename();
		if (!installFilename.isEmpty()) {

			File targetFile = new File(this.targetDirectory, installFilename);
			String feedback = download(targetFile);

			if (feedback.isEmpty())
				hashMap.put("status", 0);
			else {
				hashMap.put("error", feedback);
				hashMap.put("status", 1);
			}
		}
	}

	private String download(File targetFile) {

		this.targetFile = targetFile;
		String downloadURL = this.application.getDownloadURL();
		if (downloadURL.isEmpty())
			return this.application.getSettings().getLanguage().getString("sp.login.downloadnewversionfailempty");

		try {

			URL url = new URL(downloadURL);

			HttpURLConnection httpcon = (HttpURLConnection) url.openConnection();
			httpcon.addRequestProperty("User-Agent", "Mozilla/4.0");

			InputStream in = httpcon.getInputStream();
			Files.copy(in, Paths.get(targetFile.getAbsolutePath()), StandardCopyOption.REPLACE_EXISTING);

			return "";

		} catch (IOException e) {
			return e.getMessage();
		}
	}

	@Override
	public void feedback(HashMap<String, Object> hashMap) {

		int status = (int) hashMap.get("status");
		String error = (String) hashMap.get("error");

		switch (status) {
		case 1:

			String header = this.application.getSettings().getLanguage().getString("sp.login.downloadnewversionfail");
			this.application.getAlertBuilder2().error(this.viewStage, header, error);

			break;
		default:

			if (Desktop.isDesktopSupported()) {
				Desktop desktop = Desktop.getDesktop();
				if (this.targetFile.exists())
					try {
						desktop.open(this.targetFile);
						System.exit(0);
					} catch (IOException e) {
					}
			}

			break;
		}
	}
}
