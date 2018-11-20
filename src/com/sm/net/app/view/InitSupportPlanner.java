package com.sm.net.app.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class InitSupportPlanner {

	@FXML
	public AnchorPane root;
	@FXML
	public Label labelTitle;
	@FXML
	public Label labelUrl;
	@FXML
	public TextField textFieldUrl;
	@FXML
	public Label labelUsername;
	@FXML
	public TextField textFieldUsername;
	@FXML
	public Label labelPassword;
	@FXML
	public TextField textFieldPassword;
	@FXML
	public Label labelKey;
	@FXML
	public TextField textFieldKey;
	@FXML
	public Button buttonInit;

	private Stage initSupportPlannerStage;

	@FXML
	public void initialize() {
		// this.root.getStyleClass().add("background");

		// this.labelTitle.getStyleClass().add("title");
		this.labelTitle.setText("Inizializza SupportPlanner");

		this.labelUrl.setText("SupportPlanner-URL");
		this.labelUsername.setText("Username");
		this.labelPassword.setText("Password");
		this.labelKey.setText("Encryption-Key");
		this.buttonInit.setText("Save");

		initListeners();
	}

	private void initListeners() {

		this.buttonInit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				checkFields();
			}
		});

	}

	protected void checkFields() {
		String url = textFieldUrl.getText();
		String user = textFieldUsername.getText();
		String pwd = textFieldPassword.getText();
		String key = textFieldKey.getText();

		if (!url.isEmpty() && !user.isEmpty() && !pwd.isEmpty() && !key.isEmpty()) {

		}else{
			System.out.println("Compilare tutti i campi");
		}
	}

	public Stage getInitSupportPlannerStage() {
		return initSupportPlannerStage;
	}

	public void setInitSupportPlannerStage(Stage initSupportPlannerStage) {
		this.initSupportPlannerStage = initSupportPlannerStage;
	}
}
