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

import com.sm.net.sp.model.TerritoryMap;
import com.sm.net.sp.model.TerritoryResource;
import com.sm.net.sp.view.SupportPlannerView;
import com.sm.net.sp.view.home.user.menu.territory.Territory;
import com.smnet.core.task.TaskInterface;

import javafx.stage.Stage;

public class TerritoryMapsDownloadTask implements TaskInterface {

	private SupportPlannerView application;
	private Stage viewStage;
	private Territory view;
	private TerritoryMap territoryMap;

	public TerritoryMapsDownloadTask(SupportPlannerView application, Stage viewStage, Territory view,
			TerritoryMap territoryMap) {
		super();

		this.application = application;
		this.viewStage = viewStage;
		this.view = view;
		this.territoryMap = territoryMap;
	}

	@Override
	public void start(HashMap<String, Object> hashMap) {

		ArrayList<TerritoryResource> territoryResourceList = this.territoryMap.getResources();

		if (!territoryResourceList.isEmpty()) {

			File targetDirectory = this.territoryMap.buildTargetDirectory();

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

			if (error.isEmpty())
				hashMap.put("status", 0);
			else {
				hashMap.put("error", error);
				hashMap.put("status", 1);
			}
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
			this.view.selectMaps();
			break;
		}
	}
}
