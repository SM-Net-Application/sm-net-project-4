package com.smnet.core.dialog;

import java.io.File;
import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class AlertBuilder {

	private String appTitle;

	private String cssFileURI;
	private String cssClassName;

	private String iconFileURI;

	public AlertBuilder(String appTitle) {

		this.appTitle = appTitle;
	}

	public void information(Stage stage, String content) {
		this.build(Alert.AlertType.INFORMATION, stage, appTitle, null, content).show();
	}

	public void information(Stage stage, String header, String content) {
		this.build(Alert.AlertType.INFORMATION, stage, appTitle, header, content).show();
	}

	public void error(Stage stage, String header, String content) {
		this.build(Alert.AlertType.ERROR, stage, appTitle, header, content).show();
	}

	public void error(Stage stage, String content) {
		this.build(Alert.AlertType.ERROR, stage, appTitle, null, content).show();
	}

	public boolean confirm(Stage stage, String header, String content) {

		Alert alert = build(Alert.AlertType.CONFIRMATION, stage, this.appTitle, header, content);
		Optional<ButtonType> buttonType = alert.showAndWait();
		if (buttonType.isPresent())
			if (buttonType.get() == ButtonType.OK)
				return true;

		return false;
	}

	public boolean confirm(Stage stage, String content) {
		return confirm(stage, null, content);
	}

	public Alert wait(Stage stage, String content) {
		return this.wait(stage, null, content);
	}

	public Alert wait(Stage stage, String header, String content) {
		Alert alert = build(Alert.AlertType.NONE, stage, appTitle, header, content);
		alert.setResult(ButtonType.OK);
		return alert;
	}

	public void setCSS(File css, String cssClassName) {

		if (css != null)
			if (css.exists()) {
				this.cssFileURI = css.toURI().toString();
				this.cssClassName = cssClassName;
			}
	}

	public void setIcon(File icon) {

		if (icon != null)
			if (icon.exists())
				this.iconFileURI = icon.toURI().toString();
	}

	public Alert build(Alert.AlertType type, Stage stage, String title, String header, String content) {

		Alert alert = build(type);

		if (stage != null)
			alert.initOwner(stage);

		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);

		return alert;
	}

	private Alert build(Alert.AlertType type) {

		Alert alert = new Alert(type);

		if (!this.appTitle.isEmpty())
			alert.setTitle(this.appTitle);

		DialogPane dialogPane = alert.getDialogPane();
		dialogPane.setMinHeight(Region.USE_PREF_SIZE);

		if (!this.cssFileURI.isEmpty() && !this.cssClassName.isEmpty()) {
			dialogPane.getStylesheets().add(this.cssFileURI);
			dialogPane.getStyleClass().add(this.cssClassName);
		}

		if (!this.iconFileURI.isEmpty()) {
			Stage alertStage = (Stage) dialogPane.getScene().getWindow();
			alertStage.getIcons().add(new Image(this.iconFileURI));
		}

		return alert;
	}
}
