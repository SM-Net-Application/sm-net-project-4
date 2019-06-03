package com.sm.net.sp;

import java.io.IOException;

import com.sm.net.sp.view.SupportPlannerView;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SupportPlannerMain extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		viewSupportPlanner(primaryStage);
	}

	private void viewSupportPlanner(Stage stage) {

		try {

			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(Meta.Views.SUPPORTPLANNER_VIEW);

			BorderPane borderPane = (BorderPane) fxmlLoader.load();

			Scene scene = new Scene(borderPane);
			scene.getStylesheets().add(Meta.Themes.SUPPORTPLANNER_THEME);

			stage.setScene(scene);
			stage.setTitle(Meta.Application.getFullTitle());
			stage.getIcons().add(Meta.Resources.getImageApplicationIcon());

			SupportPlannerView ctrlViewSupportPlanner = (SupportPlannerView) fxmlLoader.getController();
			ctrlViewSupportPlanner.setViewSupportPlannerStage(stage);
			ctrlViewSupportPlanner.setCtrlViewSupportPlanner(ctrlViewSupportPlanner);
			ctrlViewSupportPlanner.objectInitialize();

			stage.show();

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
