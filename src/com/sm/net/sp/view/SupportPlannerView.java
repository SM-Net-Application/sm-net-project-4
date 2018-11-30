package com.sm.net.sp.view;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SupportPlannerView {

	@FXML
	private BorderPane supportPlannerViewBorderPane;

	private Stage supportPlannerViewStage;

	public void loadLanguageView(AnchorPane LanguageViewAnchorPane) {
		this.supportPlannerViewBorderPane.setCenter(LanguageViewAnchorPane);
		this.supportPlannerViewStage.setMinWidth(500);
		this.supportPlannerViewStage.setMaxWidth(500);
		this.supportPlannerViewStage.setMinHeight(500);
		this.supportPlannerViewStage.setMaxHeight(500);
		this.supportPlannerViewStage.setMaximized(false);
		this.supportPlannerViewStage.setResizable(false);
	}

	public void loadAccessCreateView(AnchorPane accessCreateViewAnchorPane) {
		this.supportPlannerViewBorderPane.setCenter(accessCreateViewAnchorPane);
		this.supportPlannerViewStage.setMinWidth(500);
		this.supportPlannerViewStage.setMaxWidth(500);
		this.supportPlannerViewStage.setMinHeight(500);
		this.supportPlannerViewStage.setMaxHeight(500);
		this.supportPlannerViewStage.setMaximized(false);
		this.supportPlannerViewStage.setResizable(false);
	}

	public Stage getSupportPlannerViewStage() {
		return supportPlannerViewStage;
	}

	public void setSupportPlannerViewStage(Stage supportPlannerViewStage) {
		this.supportPlannerViewStage = supportPlannerViewStage;
	}

}
