package com.sm.net.sp.view.home.user.menu.territory.task;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;

import com.sm.net.sp.model.TerritoryObj;
import com.sm.net.sp.model.TerritoryResource;
import com.sm.net.sp.view.SupportPlannerView;
import com.sm.net.sp.view.home.user.menu.territory.Territory;
import com.smnet.core.task.TaskInterface;

import javafx.stage.Stage;

public class TerritoryDownloadTask implements TaskInterface {

	private SupportPlannerView application;
	private Stage viewStage;
	private Territory view;
	private TerritoryObj territoryObj;

	public TerritoryDownloadTask(SupportPlannerView application, Stage viewStage, Territory view,
			TerritoryObj territoryObj) {
		super();

		this.application = application;
		this.viewStage = viewStage;
		this.view = view;
		this.territoryObj = territoryObj;
	}

	@Override
	public void start(HashMap<String, Object> hashMap) {

		ArrayList<String> errorList = new ArrayList<>();

		download(errorList, territoryObj);

		if (errorList.isEmpty())
			hashMap.put("status", 0);
		else {
			hashMap.put("error", errorList.get(0));
			hashMap.put("status", 1);
		}
	}

	private boolean download(ArrayList<String> errorList, TerritoryObj territoryObj) {

		ArrayList<TerritoryResource> territoryResourceList = territoryObj.getResources();

		if (!territoryResourceList.isEmpty()) {

			File targetDirectory = territoryObj.buildTargetDirectory();

			for (TerritoryResource territoryResource : territoryResourceList) {

				File targetFile = new File(targetDirectory, territoryResource.getResourceName());
				if (!targetFile.exists()) {

					String feedback = download(targetFile, territoryResource);

					if (!feedback.isEmpty()) {

						errorList.add(targetFile.getName());

						return false;
					}
				}
			}
		}

		return true;
	}

	private String download(File targetFile, TerritoryResource res) {

		switch (res.getType()) {
		case 0:// IMAGE
		case 1:// DOC

			try {

				URL url = new URL(res.getResourceURL());

				HttpURLConnection httpcon = (HttpURLConnection) url.openConnection();
				httpcon.addRequestProperty("User-Agent", "Mozilla/4.0");

				InputStream in = httpcon.getInputStream();
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
			String header = this.application.getSettings().getLanguage()
					.getStringWithNewLine("territory.download.error");
			this.application.getAlertBuilder2().error(this.viewStage, header, error);
			break;
		case 2:
			this.application.getAlertBuilder2().error(this.viewStage,
					this.application.getSettings().getLanguage().getString(error));
			break;
		default:
			this.view.selectTerritory();
			break;
		}
	}
}
