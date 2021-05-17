package com.sm.net.sp.view.home.user.menu.territory.task;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

import com.sm.net.sp.model.TerritoryObj;
import com.sm.net.sp.model.TerritoryResource;
import com.sm.net.sp.settings.Settings;
import com.sm.net.sp.view.home.user.menu.territory.Territory;
import com.smnet.core.dialog.AlertBuilder;
import com.smnet.core.task.TaskInterface;

import javafx.stage.Stage;

public class TerritoryDownloadTask implements TaskInterface {

	private AlertBuilder alertBuilder;
	private Settings settings;
	private Stage viewStage;
	private Territory view;
	private TerritoryObj territory;

	public TerritoryDownloadTask(AlertBuilder alertBuilder, Settings settings, Stage viewStage, Territory view,
			TerritoryObj territory) {
		super();

		this.alertBuilder = alertBuilder;
		this.settings = settings;
		this.viewStage = viewStage;
		this.view = view;
		this.territory = territory;
	}

	@Override
	public void start(HashMap<String, Object> hashMap) {

		ArrayList<TerritoryResource> resourceList = new ArrayList<>();
		ArrayList<TerritoryResource> resourceErrorList = new ArrayList<>();

		// ImageList
		checkResource(resourceList, 0, this.territory.getSpInf11(), this.territory.getSpInf12());
		checkResource(resourceList, 0, this.territory.getSpInf13(), this.territory.getSpInf14());
		checkResource(resourceList, 0, this.territory.getSpInf15(), this.territory.getSpInf16());
		checkResource(resourceList, 0, this.territory.getSpInf17(), this.territory.getSpInf18());
		checkResource(resourceList, 0, this.territory.getSpInf19(), this.territory.getSpInf20());

		if (!resourceList.isEmpty()) {

			// Download Target
			String userHome = System.getProperty("user.home");
			File target = Paths.get(userHome, "SupportPlanner", "TerritoryResources",
					String.valueOf(this.territory.getSpTerritoryID())).toFile();

			if (!target.exists()) {
				target.mkdirs();
			}

			resourceList.forEach(res -> {
				if (!download(target, res)) {
					resourceErrorList.add(res);
				}
			});

			if (resourceErrorList.isEmpty())
				hashMap.put("status", 0);
			else {
				hashMap.put("error", buildErrorString(resourceErrorList));
				hashMap.put("status", 1);
			}
		}
	}

	private String buildErrorString(ArrayList<TerritoryResource> resourceErrorList) {

		String errorList = "";

		for (TerritoryResource territoryResource : resourceErrorList) {

			if (!errorList.isEmpty())
				errorList += "\n\n";

			errorList += String.format("Download failed: (%s) -> %s", territoryResource.getResourceName(),
					territoryResource.getResourceURL());
		}

		return errorList;
	}

	private boolean download(File target, TerritoryResource res) {

		switch (res.getType()) {
		case 0:// IMAGE

			try {

				URL url = new URL(res.getResourceURL());

				InputStream in = new BufferedInputStream(url.openStream());
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				byte[] buf = new byte[1024];
				int n = 0;
				while (-1 != (n = in.read(buf))) {
					out.write(buf, 0, n);
				}
				out.close();
				in.close();
				byte[] response = out.toByteArray();

				// Save
				String targetPath = Paths.get(target.getAbsolutePath(), res.getResourceName()).toFile()
						.getAbsolutePath();
				FileOutputStream fos = new FileOutputStream(targetPath);
				fos.write(response);
				fos.close();

				return true;

			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}

		case 1:// DOC

			// TODO: Download documents

			break;
		default:
			break;
		}

		return false;
	}

	private void checkResource(ArrayList<TerritoryResource> resourceList, int type, String url, String title) {

		if (!url.isEmpty() && !title.isEmpty())
			resourceList.add(new TerritoryResource(type, title, url));
	}

	@Override
	public void feedback(HashMap<String, Object> hashMap) {

		int status = (int) hashMap.get("status");
		String error = (String) hashMap.get("error");

		if (status == 0) {

			this.view.updateTerritory();

		} else if (status == 1)
			this.alertBuilder.error(this.viewStage, error);
		else if (status == 2)
			this.alertBuilder.error(this.viewStage, this.settings.getLanguage().getString(error));
	}
}
