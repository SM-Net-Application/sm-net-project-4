package com.sm.net.sp.view.home.user.menu.territory.task;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;

import com.sm.net.sp.model.TerritoryObj;
import com.sm.net.sp.model.TerritoryResource;
import com.sm.net.sp.view.SupportPlannerView;
import com.smnet.core.task.TaskInterface;

import javafx.collections.ObservableList;
import javafx.stage.Stage;

public class TerritoryDownloadAllTask implements TaskInterface {

	private SupportPlannerView application;
	private Stage viewStage;
	private ObservableList<TerritoryObj> territoryList;

	public TerritoryDownloadAllTask(SupportPlannerView application, Stage viewStage,
			ObservableList<TerritoryObj> territoryList) {
		super();

		this.application = application;
		this.viewStage = viewStage;
		this.territoryList = territoryList;
	}

	@Override
	public void start(HashMap<String, Object> hashMap) {

		ArrayList<String> errorList = new ArrayList<>();

		this.territoryList.forEach(territoryObj -> download(errorList, territoryObj));

		if (errorList.isEmpty())
			hashMap.put("status", 0);
		else {
			hashMap.put("error", buildErrorMessage(errorList));
			hashMap.put("status", 1);
		}
	}

	private String buildErrorMessage(ArrayList<String> errorList) {

		String message = "";
		for (String error : errorList) {
			if (!message.isEmpty())
				message += "\n\n";
			message += error;
		}

		return message;
	}

	private void download(ArrayList<String> errorList, TerritoryObj territoryObj) {

		ArrayList<TerritoryResource> territoryResourceList = territoryObj.getResources();

		if (!territoryResourceList.isEmpty()) {

			File targetDirectory = territoryObj.buildTargetDirectory();

			String error = "";
			for (TerritoryResource territoryResource : territoryResourceList) {

				File targetFile = new File(targetDirectory, territoryResource.getResourceName());
				if (!targetFile.exists()) {

					String feedback = download(targetFile, territoryResource);

					if (!feedback.isEmpty()) {
						if (!error.isEmpty())
							error += "\n\n";

						error += String.format("Download failed: (%s) -> %s", territoryResource.getResourceName(),
								territoryResource.getResourceURL());
					}
				}
			}

			if (!error.isEmpty())
				errorList.add(error);
		}

	}

	private String download(File targetFile, TerritoryResource res) {

		switch (res.getType()) {
		case 0:// IMAGE
		case 1:// DOC

			try {
				InputStream in = new URL(res.getResourceURL()).openStream();
				Files.copy(in, Paths.get(targetFile.getAbsolutePath()), StandardCopyOption.REPLACE_EXISTING);
				return "";
			} catch (IOException e) {
				return e.getMessage();
			}

		default:
			break;
		}

		return "Document-Type unknow!";
	}

	@Override
	public void feedback(HashMap<String, Object> hashMap) {

		int status = (int) hashMap.get("status");
		String error = (String) hashMap.get("error");

		switch (status) {
		case 1:
			this.application.getAlertBuilder2().error(this.viewStage, error);
			break;
		case 2:
			this.application.getAlertBuilder2().error(this.viewStage,
					this.application.getSettings().getLanguage().getString(error));
			break;
		default:
			break;
		}
	}
}