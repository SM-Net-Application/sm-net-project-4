package com.sm.net.sp.view;

import com.sm.net.project.Language;
import com.sm.net.sp.settings.Settings;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class SupportPlannerWaitWindow {

	@FXML
	private AnchorPane anchorPane;

	@FXML
	private ImageView waitImageView;

	@FXML
	private Label waitLabel;

	private Settings settings;
	private Language language;
	private Stage stage;

	@FXML
	private void initialize() {
		styleClasses();
	}

	public void objectInitialize() {
		viewUpdate();
	}

	private void styleClasses() {

		anchorPane.getStyleClass().add("anchorPaneStyle1");
		waitLabel.getStyleClass().add("labelStyle2");
	}

	private void viewUpdate() {

		this.language = settings.getLanguage();
		waitLabel.setText(language.getString("VIEW009LAB001"));
	}

	public Settings getSettings() {
		return settings;
	}

	public void setSettings(Settings settings) {
		this.settings = settings;
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

}
