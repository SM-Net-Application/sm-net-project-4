package com.sm.net.sp.view.access.create;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AccessCreate {

	@FXML
	private Label labelTitle;

	@FXML
	private Label labelPassword;

	@FXML
	private Label labelPasswordConfirm;

	@FXML
	private TextField textFieldPassword;

	@FXML
	private TextField textFiedlPasswordConfirm;

	@FXML
	private Button buttonCreate;

	@FXML
	private void initialize() {
		setStyleClass();
	}

	private void setStyleClass() {
		labelTitle.getStyleClass().add("labelStyle2");
		labelPassword.getStyleClass().add("labelStyle1");
		labelPasswordConfirm.getStyleClass().add("labelStyle1");
		textFieldPassword.getStyleClass().add("textFieldStyle1");
		textFiedlPasswordConfirm.getStyleClass().add("textFieldStyle1");
		buttonCreate.getStyleClass().add("buttonStyle1");
	}
}
