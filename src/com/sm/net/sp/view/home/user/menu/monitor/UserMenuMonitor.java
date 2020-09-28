package com.sm.net.sp.view.home.user.menu.monitor;

import java.time.LocalDate;

import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.actions.Actions;
import com.sm.net.sp.model.Activities;
import com.sm.net.sp.model.UpdateDataAdapter;
import com.sm.net.sp.model.Week;
import com.sm.net.sp.settings.Settings;
import com.sm.net.util.Crypt;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class UserMenuMonitor extends UpdateDataAdapter {

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
	private TableView<Activities> activityTableView;
	@FXML
	private TableColumn<Activities, String> dateTableColumn;
	@FXML
	private TableColumn<Activities, ImageView> imageTableColumn;
	@FXML
	private TableColumn<Activities, String> activityTableColumn;

	private Settings settings;
	private Language language;
	private Stage ownerStage;

	@FXML
	private void initialize() {
		styleClasses();
		cellValueFactory();
		listeners();
	}

	private void cellValueFactory() {

		dateTableColumn
				.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLastDateText()));

		imageTableColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<ImageView>(
				Meta.Resources.imageForButtonSmall(cellData.getValue().getPrivilege().getImageName())));

		activityTableColumn.setCellValueFactory(
				cellData -> new SimpleStringProperty(language.getString(cellData.getValue().getPrivilege().getName())));
	}

	public void objectInitialize() {
		viewUpdate();
		loadPassword();
		updateActivities();
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

		dateTableColumn.getStyleClass().add("table_column_002");
		imageTableColumn.getStyleClass().add("table_column_002");
	}

	private void viewUpdate() {

		this.language = settings.getLanguage();

		titleImageView.setFitWidth(50);
		titleImageView.setFitHeight(50);
		titleImageView.setImage(Meta.Resources.getImageLogo(Meta.Resources.USER_MENU_MONITOR, 50, 50));

		titleLabel.setText(language.getString("sp.menu.monitor"));
		passwordLabel.setText(language.getString("sp.monitor.password"));

		Tooltip passwordTooltip = new Tooltip(this.language.getString("monitor.tooltip.update"));
		passwordTooltip.getStyleClass().add("tooltip_001");
		this.passwordButton.setTooltip(passwordTooltip);
		this.passwordButton.setGraphic(Meta.Resources.imageForButtonSmall(Meta.Resources.UPDATE));
		this.passwordButton.setText(null);

		dateTableColumn.setMinWidth(150);
		dateTableColumn.setMaxWidth(150);
		dateTableColumn.setText(language.getString("sp.monitor.table.data"));

		imageTableColumn.setMinWidth(100);
		imageTableColumn.setMaxWidth(100);
		imageTableColumn.setText("");

		activityTableColumn.setText(language.getString("sp.monitor.table.activity"));
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
		this.passwordButton.setOnAction(event -> updateActivities());
	}

	@Override
	public void updateActivities() {
		super.updateActivities();

		String password = this.passwordTextField.getText();
		if (!password.trim().isEmpty())
			Actions.getAllActivities(password, Week.buildKey(LocalDate.now()), this.settings, this.ownerStage, this);
	}

	@Override
	public void updateActivities(ObservableList<Activities> list) {
		super.updateActivities(list);

		this.activityTableView.setItems(list);
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
