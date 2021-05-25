package com.sm.net.sp.view.menu.settings.modules;

import java.io.File;
import java.io.IOException;

import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.model.User;
import com.sm.net.sp.settings.Settings;
import com.sm.net.sp.view.SupportPlannerView;
import com.sm.net.util.Crypt;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class SettingModules {

	@FXML
	private ImageView imageView;
	@FXML
	private Label titleLabel;
	@FXML
	private Label s13Label;
	@FXML
	private TextField s13TextField;
	@FXML
	private Button s13Button;

	private Settings settings;
	private Language language;
	private User loggedUser;

	private SupportPlannerView application;
	private Stage ownerStage;

	@FXML
	private void initialize() {
		styleClasses();
	}

	public void objectInitialize() {
		listeners();
		viewUpdate();
		loadSettings();
	}

	private void styleClasses() {

		titleLabel.getStyleClass().add("label_header_001");
		s13Label.getStyleClass().add("label_set_001");
		s13TextField.getStyleClass().add("text_field_001");
		s13Button.getStyleClass().add("button_image_001");
	}

	private void viewUpdate() {

		this.language = settings.getLanguage();

		imageView.setFitWidth(50);
		imageView.setFitHeight(50);
		imageView.setImage(Meta.Resources.getImageLogo(Meta.Resources.PDF, 50, 50));

		titleLabel.setText(language.getString("sp.settings.modules"));

		s13Label.setText(language.getString("sp.settings.modules.s13"));
		this.s13TextField.setEditable(false);

		Tooltip s13Tooltip = new Tooltip(this.language.getString("sp.settings.modules.s13button"));
		s13Tooltip.getStyleClass().add("tooltip_001");
		this.s13Button.setTooltip(s13Tooltip);
		this.s13Button.setText("");
		this.s13Button.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.FOLDER));
	}

	private void listeners() {
		this.s13Button.setOnAction(event -> selectFile());
	}

	private void selectFile() {

		if (this.loggedUser == null) {

			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle(language.getString("sp.settings.selectmodule"));

			fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
			fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF", "*.pdf"));

			File file = fileChooser.showOpenDialog(this.ownerStage);
			if (file != null) {
				saveS13(file);
			}
		}
	}

	private void saveS13(File file) {

		this.s13TextField.setText(file.getAbsolutePath());
		try {
			this.settings.setModuleS13(Crypt.encrypt(s13TextField.getText(), this.settings.getApplicationKey()));
			this.settings.save();
		} catch (IOException e) {
		}

	}

	private void loadSettings() {

		String s13 = Crypt.decrypt(this.settings.getModuleS13(), this.settings.getApplicationKey());
		if (s13 != null)
			this.s13TextField.setText(s13);
	}

	public Settings getSettings() {
		return settings;
	}

	public void setSettings(Settings settings) {
		this.settings = settings;
	}

	public User getLoggedUser() {
		return loggedUser;
	}

	public void setLoggedUser(User loggedUser) {
		this.loggedUser = loggedUser;
	}

	public SupportPlannerView getApplication() {
		return application;
	}

	public void setApplication(SupportPlannerView application) {
		this.application = application;
	}

	public Stage getOwnerStage() {
		return ownerStage;
	}

	public void setOwnerStage(Stage ownerStage) {
		this.ownerStage = ownerStage;
	}
}
