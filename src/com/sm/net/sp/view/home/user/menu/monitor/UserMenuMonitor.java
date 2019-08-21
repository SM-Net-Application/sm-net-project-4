package com.sm.net.sp.view.home.user.menu.monitor;

import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.settings.Settings;
import com.sm.net.util.Crypt;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class UserMenuMonitor {

	@FXML
	private Label titleLabel;
	@FXML
	private ImageView titleImageView;
	@FXML
	private Label passwordLabel;
	@FXML
	private PasswordField passwordTextField;
	@FXML
	private Button passwordButton;
	@FXML
	private TableView<String> activityTableView;
	@FXML
	private TableColumn<String, String> dateTableColumn;
	@FXML
	private TableColumn<String, String> imageTableColumn;
	@FXML
	private TableColumn<String, String> activityTableColumn;

	private Settings settings;
	private Language language;
	private Stage ownerStage;

	@FXML
	private void initialize() {
		styleClasses();
		listeners();
	}

	public void objectInitialize() {
		viewUpdate();
		loadPassword();
		loadActivities();
	}

	private void listeners() {
		listenerPasswordButton();
	}

	private void styleClasses() {

		titleLabel.getStyleClass().add("label_header_001");

		passwordLabel.getStyleClass().add("label_001");
		passwordTextField.getStyleClass().add("text_field_001");
		passwordButton.getStyleClass().add("button_image_001");

		activityTableView.getStyleClass().add("table_view_001");
	}

	private void viewUpdate() {

		this.language = settings.getLanguage();

		titleImageView.setFitWidth(50);
		titleImageView.setFitHeight(50);
		titleImageView.setImage(Meta.Resources.getImageLogo(Meta.Resources.USER_MENU_MONITOR, 50, 50));

		titleLabel.setText(language.getString("sp.menu.monitor"));
		passwordLabel.setText(language.getString("sp.monitor.password"));

		passwordButton.setText("");
		passwordButton.setGraphic(Meta.Resources.imageViewForButton(Meta.Resources.UPDATE));
	}

	private void loadPassword() {

		String passwordMonitorEncrypted = this.settings.getUserPasswordMonitorEncrypted();
		if (!passwordMonitorEncrypted.isEmpty()) {
			String passwordMonitorDecrypted = Crypt.decrypt(passwordMonitorEncrypted, settings.getApplicationKey());
			if (!passwordMonitorDecrypted.isEmpty())
				this.passwordTextField.setText(passwordMonitorDecrypted);
		}
	}

	private void listenerPasswordButton() {
		this.passwordButton.setOnAction(event -> loadActivities());
	}

	private void loadActivities() {

		String password = this.passwordTextField.getText();
		if (!password.trim().isEmpty()) {

			// TODO: Prendere le info sul componente e le attività
		}
	}

	public Settings getSettings() {
		return settings;
	}

	public void setSettings(Settings settings) {
		this.settings = settings;
	}

	public Stage getOwnerStage() {
		return ownerStage;
	}

	public void setOwnerStage(Stage ownerStage) {
		this.ownerStage = ownerStage;
	}

}
