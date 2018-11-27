package com.sm.net.app.view;

import java.io.IOException;

import com.sm.net.sp.Meta;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SupportPlannerTools {

	@FXML
	public AnchorPane root;
	@FXML
	public Label labelLogo;
	@FXML
	public Button buttonCreateAdmin;

	private Stage supportPlannerToolsStage;

	@FXML
	public void initialize() {

		// this.root.getStyleClass().add("background");

		// this.imageButtonClose.setImage(new
		// Image(getClass().getResourceAsStream("resources/buttonClose.png")));

		// this.imageLogo.setImage(new
		// Image(getClass().getResourceAsStream("resources/icon.png")));

		// this.labelLogo.getStyleClass().add("title");
		this.labelLogo.setText(Meta.getAppName());

		// this.buttonCreateAdmin.getStyleClass().add("button");
		this.buttonCreateAdmin.setText("Inizializza SupportPlanner");

		initializeListeners();
	}

	private void initializeListeners() {

		this.buttonCreateAdmin.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				loadGUIF1();
			}
		});
	}

	protected void loadGUIF1() {
		try {

			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(SupportPlannerTools.class.getResource("InitSupportPlanner.fxml"));

			AnchorPane anchorPane;
			anchorPane = (AnchorPane) fxmlLoader.load();

			Scene scene = new Scene(anchorPane);
			// scene.getStylesheets().add(com.sm.net.app.SupportPlannerTools.class.getResource("application.css").toExternalForm());

			Stage stage = new Stage();
			stage.setScene(scene);

			stage.setTitle("SM-Net: " + Meta.getAppName());
			stage.getIcons().add(new Image(SupportPlannerTools.class.getResourceAsStream("resources/icon.png")));

			stage.setResizable(false);

			stage.initOwner(supportPlannerToolsStage);
			stage.initModality(Modality.WINDOW_MODAL);
			// stage.initStyle(StageStyle.UNDECORATED);

			InitSupportPlanner controller = (InitSupportPlanner) fxmlLoader.getController();
			controller.setInitSupportPlannerStage(stage);
			// controller.init();

			stage.show();

		} catch (IOException e) {
		}
	}

	protected void closeProgramm() {
		System.exit(0);
	}

	public Stage getSupportPlannerToolsStage() {
		return supportPlannerToolsStage;
	}

	public void setSupportPlannerToolsStage(Stage supportPlannerToolsStage) {
		this.supportPlannerToolsStage = supportPlannerToolsStage;
	}

}
