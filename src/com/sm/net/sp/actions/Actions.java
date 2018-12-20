package com.sm.net.sp.actions;

import java.io.IOException;

import org.json.JSONObject;

import com.sm.net.app.json.JSONRequest;
import com.sm.net.sp.Meta;
import com.sm.net.sp.settings.Settings;
import com.sm.net.sp.view.SupportPlannerWaitWindow;
import com.sm.net.util.JSON;
import com.sm.net.util.enumeration.JSONStatus;

import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Actions {

	public static void checkUser(String url, String username, String password, Settings settings,
			Stage viewSupportPlannerStage) {

		Task<Void> task = new Task<Void>() {

			private Stage stageWaitWindow;

			@Override
			protected Void call() throws Exception {

				try {

					FXMLLoader fxmlLoader = new FXMLLoader();
					fxmlLoader.setLocation(Meta.Views.SUPPORTPLANNER_WAIT);
					AnchorPane layout = (AnchorPane) fxmlLoader.load();

					Scene scene = new Scene(layout);
					Stage stage = new Stage();
					stage.setScene(scene);
					stage.initOwner(viewSupportPlannerStage);
					stage.getIcons().add(Meta.Resources.ICON);
					stage.setTitle(Meta.Application.getFullTitle());

					stage.setMinWidth(500);
					stage.setMinHeight(200);
					stage.setResizable(false);

					stageWaitWindow = stage;

					SupportPlannerWaitWindow ctrl = (SupportPlannerWaitWindow) fxmlLoader.getController();
					ctrl.setSettings(settings);
					ctrl.setStage(stage);
					ctrl.objectInitialize();
					stage.show();

				} catch (IOException e) {
				}

				return null;
			}

			@Override
			protected void running() {
				super.running();

				JSONObject jsonObject = JSON.executeHttpPostJSON(url, JSONRequest.CHECK_USER(username, password));
				JSONStatus status = JSONRequest.getStatus(jsonObject);

				if (!JSONRequest.isRequestOK(status))
					System.out.println(status.getText());

				System.out.println(jsonObject.toString());

			}

			@Override
			protected void succeeded() {

				if (stageWaitWindow != null)
					stageWaitWindow.close();

				super.succeeded();
			}

			@Override
			protected void cancelled() {

				if (stageWaitWindow != null)
					stageWaitWindow.close();

				super.cancelled();

			}

			@Override
			protected void failed() {

				if (stageWaitWindow != null)
					stageWaitWindow.close();

				super.failed();
			}
		};

		task.run();
	}
}
