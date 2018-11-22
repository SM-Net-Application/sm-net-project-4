package com.sm.net.app.view;

import com.sm.net.app.AppInfos;
import com.sm.net.app.exceptions.OperationCouldNotBeCompleted;
import com.sm.net.app.operations.Operations;
import com.sm.net.auth.Authenticator;
import com.sm.net.auth.ValidationType;
import com.sm.net.fx.AlertDesigner;
import com.sm.net.util.Html;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
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

				String url = textFieldUrl.getText();
				String user = textFieldUsername.getText();
				String password = textFieldPassword.getText();
				String key = textFieldKey.getText();

				if (checkFields(url, user, password, key)) {
					try {
						if (Operations.noUsersInDatabase(url)) {
							Operations.runInitialize(url, user, password, key);
							new AlertDesigner("Database inizializzato con successo", initSupportPlannerStage,
									AlertType.INFORMATION, "Utente creato!", AppInfos.ICON).show();
						} else
							new AlertDesigner("Il database non necessita di essere inizializzato",
									"La tabella utenti non è vuota.", initSupportPlannerStage, AlertType.ERROR,
									"Attenzione!", AppInfos.ICON).show();
					} catch (OperationCouldNotBeCompleted e) {
						new AlertDesigner("L'operazione non può essere completata", e.getMessage(),
								initSupportPlannerStage, AlertType.ERROR, "Attenzione!", AppInfos.ICON).show();
					}
				}
			}
		});

	}

	protected boolean checkFields(String url, String user, String password, String key) {

		boolean check = true;
		check = checkURL(url);
		if (check)
			check = checkUsername(user);
		if (check)
			check = checkPassword(password);
		if (check)
			check = checkKey(key);

		return check;
	}

	private boolean checkKey(String key) {

		boolean check = true;

		if (!Authenticator.isValid(key, ValidationType.VERY_STRONG)) {
			check = false;
			new AlertDesigner("L'Encryption-Key fornita non è valida", ValidationType.VERY_STRONG.getInfo(),
					initSupportPlannerStage, AlertType.ERROR, "Attenzione!", AppInfos.ICON).show();
		}
		return check;
	}

	private boolean checkPassword(String pwd) {
		boolean check = true;

		if (!Authenticator.isValid(pwd, ValidationType.VERY_STRONG)) {
			check = false;
			new AlertDesigner("La password fornita non è valida", ValidationType.VERY_STRONG.getInfo(),
					initSupportPlannerStage, AlertType.ERROR, "Attenzione!", AppInfos.ICON).show();
		}

		return check;
	}

	private boolean checkUsername(String user) {

		boolean check = true;

		if (!Authenticator.isValid(user, ValidationType.VERY_STRONG)) {
			check = false;
			new AlertDesigner("L'username fornito non è valido", "Formato:\n" + ValidationType.VERY_STRONG.getInfo(),
					initSupportPlannerStage, AlertType.ERROR, "Attenzione!", AppInfos.ICON).show();
		}

		return check;
	}

	private boolean checkURL(String url) {

		boolean check = true;

		if (!Html.isValidUrl(url)) {
			check = false;
			new AlertDesigner("L'URL fornito non è valido", "Formato:\nhttp[s]://mysite.org[/home/]",
					initSupportPlannerStage, AlertType.ERROR, "Attenzione!", AppInfos.ICON).show();
		}

		return check;
	}

	public Stage getInitSupportPlannerStage() {
		return initSupportPlannerStage;
	}

	public void setInitSupportPlannerStage(Stage initSupportPlannerStage) {
		this.initSupportPlannerStage = initSupportPlannerStage;
	}
}
