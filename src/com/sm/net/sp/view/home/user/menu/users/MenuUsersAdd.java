package com.sm.net.sp.view.home.user.menu.users;

import com.sm.net.javafx.AlertDesigner;
import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.actions.Actions;
import com.sm.net.sp.settings.Settings;
import com.sm.net.util.Crypt;
import com.smnet.api.security.PasswordUtils;
import com.smnet.api.security.PasswordUtils.PasswordSecurityStatus;

import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MenuUsersAdd implements MenuUsersAddCallback {

	@FXML
	private AnchorPane anchorPane;
	@FXML
	private ImageView newUserImageView;
	@FXML
	private Label titleLabel;
	@FXML
	private Label usernameLabel;
	@FXML
	private Label passwordLabel;
	@FXML
	private TextField usernameTextField;
	@FXML
	private PasswordField passwordField;

	@FXML
	private Button createUserButton;

	private Settings settings;
	private Language language;
	private Stage ownerStage;
	private HomeUserMenuUsersList ownerCtrl;
	private Stage thisStage;

	@FXML
	private void initialize() {
		styleClasses();
	}

	private void styleClasses() {

		anchorPane.getStyleClass().add("main_color_001");

		titleLabel.getStyleClass().add("label_setting_name");

		usernameLabel.getStyleClass().add("label_set_001");
		passwordLabel.getStyleClass().add("label_set_001");

		usernameTextField.getStyleClass().add("text_field_001");
		passwordField.getStyleClass().add("text_field_001");

		createUserButton.getStyleClass().add("button_image_001");
	}

	public void objectInitialize() {
		listeners();
		viewUpdate();
	}

	private void viewUpdate() {

		this.language = settings.getLanguage();

		newUserImageView.setFitWidth(100);
		newUserImageView.setFitHeight(100);
		newUserImageView.setImage(Meta.Resources.getImageLogo(Meta.Resources.USER_MENU_USERS, 100, 100));

		titleLabel.setText(language.getString("TEXT0003"));

		usernameLabel.setText(language.getString("VIEW007LAB002"));
		passwordLabel.setText(language.getString("VIEW002LAB002"));

		createUserButton.setGraphic(Meta.Resources.imageViewForButton(Meta.Resources.SAVE));
		createUserButton.setText(language.getString("VIEW002BUT001"));
	}

	private void listeners() {
		listenerCreateUserButton();
	}

	private void listenerCreateUserButton() {

		createUserButton.setOnAction(event -> {

			String user = usernameTextField.getText();
			String password = passwordField.getText();

			if (checkFields(user, password)) {

				String userEncrypted = Crypt.encrypt(user, settings.getDatabaseSecretKey());
				String passwordEncrypted = Crypt.encrypt(password, settings.getDatabaseSecretKey());

				Actions.checkUsername(userEncrypted, passwordEncrypted, settings, thisStage, this);

			} else
				new AlertDesigner(language.getStringWithNewLine("TEXT0004"), language.getStringWithNewLine("MEX002"),
						thisStage, AlertType.ERROR, Meta.Application.getFullTitle(),
						Meta.Resources.getImageApplicationIcon(), Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").show();
		});
	}

	@Override
	public void usernameExists() {

		new AlertDesigner(settings.getLanguage().getString("TEXT0007"), ownerStage, AlertType.ERROR,
				Meta.Application.getFullTitle(), Meta.Resources.getImageApplicationIcon(),
				Meta.Themes.SUPPORTPLANNER_THEME, "alert_001").showAndWait();
	}

	@Override
	public void usernameNotExists(String userEncrypted, String passwordEncrypted) {

		Actions.insertNewUser(settings.getDatabaseUrl(), userEncrypted, passwordEncrypted, settings, thisStage,
				ownerCtrl);

	}

	private boolean checkFields(String user, String password) {

		boolean check = true;
		check = checkUsername(user);
		if (check)
			check = checkPassword(password);

		return check;
	}

	private boolean checkUsername(String user) {

		boolean check = true;
		if (PasswordUtils.isSecure(user) != PasswordSecurityStatus.OK)
			check = false;
		return check;
	}

	private boolean checkPassword(String pwd) {

		boolean check = true;
		if (PasswordUtils.isSecure(pwd) != PasswordSecurityStatus.OK)
			check = false;
		return check;
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

	public HomeUserMenuUsersList getOwnerCtrl() {
		return ownerCtrl;
	}

	public void setOwnerCtrl(HomeUserMenuUsersList ownerCtrl) {
		this.ownerCtrl = ownerCtrl;
	}

	public Stage getThisStage() {
		return thisStage;
	}

	public void setThisStage(Stage thisStage) {
		this.thisStage = thisStage;
	}
}
