package com.sm.net.sp.view.home.user.menu.places;

import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.model.EnumPlaceType;
import com.sm.net.sp.model.Place;
import com.sm.net.sp.model.UpdateDataAdapter;
import com.sm.net.sp.settings.Settings;
import com.sm.net.sp.view.SupportPlannerView;
import com.sm.net.sp.view.home.user.menu.places.task.PlaceSaveTask;
import com.smnet.core.task.TaskManager;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;

public class PlacesAdd extends UpdateDataAdapter {

	@FXML
	private AnchorPane anchorPane;
	@FXML
	private ImageView imageView;
	@FXML
	private Label titleLabel;

	@FXML
	private Label typeLabel;
	@FXML
	private Label descrLabel;
	@FXML
	private ComboBox<EnumPlaceType> typeComboBox;
	@FXML
	private TextField descrTextField;

	@FXML
	private Label streetLabel;
	@FXML
	private Label numLabel;
	@FXML
	private Label postCodeLabel;
	@FXML
	private Label cityLabel;
	@FXML
	private Label countyLabel;
	@FXML
	private Label countryLabel;
	@FXML
	private Label coordLabel;
	@FXML
	private Label defaultLabel;

	@FXML
	private TextField streetTextField;
	@FXML
	private TextField numTextField;
	@FXML
	private TextField postCodeTextField;
	@FXML
	private TextField cityTextField;
	@FXML
	private TextField countyTextField;
	@FXML
	private TextField countryTextField;
	@FXML
	private TextField coordTextField;

	@FXML
	private CheckBox defaultCheckBox;

	@FXML
	private Button saveButton;

	private Settings settings;
	private Language language;
	private Stage ownerStage;
	private HomeUserMenuPlaces ownerCtrl;
	private Stage thisStage;
	private SupportPlannerView application;

	@FXML
	private void initialize() {
		styleClasses();
	}

	private void styleClasses() {

		this.anchorPane.getStyleClass().add("main_color_001");

		this.titleLabel.getStyleClass().add("label_setting_name");

		this.typeComboBox.getStyleClass().add("combo_box_002");
		this.descrTextField.getStyleClass().add("text_field_001");

		this.typeLabel.getStyleClass().add("label_set_001");
		this.descrLabel.getStyleClass().add("label_set_001");

		this.streetLabel.getStyleClass().add("label_set_001");
		this.postCodeLabel.getStyleClass().add("label_set_001");
		this.countryLabel.getStyleClass().add("label_set_001");
		this.coordLabel.getStyleClass().add("label_set_001");
		this.defaultLabel.getStyleClass().add("label_set_001");

		this.numLabel.getStyleClass().add("label_001");
		this.cityLabel.getStyleClass().add("label_001");
		this.countyLabel.getStyleClass().add("label_001");

		this.streetTextField.getStyleClass().add("text_field_001");
		this.numTextField.getStyleClass().add("text_field_001");
		this.postCodeTextField.getStyleClass().add("text_field_001");
		this.cityTextField.getStyleClass().add("text_field_001");
		this.countyTextField.getStyleClass().add("text_field_001");
		this.countryTextField.getStyleClass().add("text_field_001");
		this.coordTextField.getStyleClass().add("text_field_001");

		this.defaultCheckBox.getStyleClass().add("check_box_001");

		this.saveButton.getStyleClass().add("button_image_001");
	}

	public void objectInitialize() {

		initData();
		listeners();
		viewUpdate();

		Callback<ListView<EnumPlaceType>, ListCell<EnumPlaceType>> callbackEnumPlaceType = callbackForEnumPlaceType();
		this.typeComboBox.setButtonCell(callbackEnumPlaceType.call(null));
		this.typeComboBox.setCellFactory(callbackEnumPlaceType);
	}

	private Callback<ListView<EnumPlaceType>, ListCell<EnumPlaceType>> callbackForEnumPlaceType() {
		return param -> new EnumPlaceTypeComboBoxListCell(this.language);
	}

	private void initData() {

		this.typeComboBox.getItems().addAll(EnumPlaceType.values());
		this.typeComboBox.getSelectionModel().selectFirst();
	}

	private void viewUpdate() {

		this.language = settings.getLanguage();

		this.imageView.setFitWidth(100);
		this.imageView.setFitHeight(100);
		this.imageView.setImage(Meta.Resources.getImageLogo(Meta.Resources.PLACES, 100, 100));

		this.titleLabel.setText(this.language.getString("places.new.title"));

		this.typeLabel.setText(this.language.getString("places.new.type"));
		this.descrLabel.setText(this.language.getString("places.new.descr"));

		this.streetLabel.setText(this.language.getString("places.new.street"));
		this.postCodeLabel.setText(this.language.getString("places.new.postcode"));
		this.countryLabel.setText(this.language.getString("places.new.country"));
		this.coordLabel.setText(this.language.getString("places.new.coord"));
		this.defaultLabel.setText(this.language.getString("places.new.default"));

		this.numLabel.setText(this.language.getString("places.new.num"));
		this.cityLabel.setText(this.language.getString("places.new.city"));
		this.countyLabel.setText(this.language.getString("places.new.county"));

		int width = 100;
		this.numTextField.setMaxWidth(width);
		this.countyTextField.setMaxWidth(width);
		this.postCodeTextField.setMaxWidth(width);

		this.saveButton.setGraphic(Meta.Resources.imageViewForButton(Meta.Resources.SAVE));
		this.saveButton.setText(null);
	}

	private void listeners() {

		this.saveButton.setOnAction(event -> save());

	}

	private void save() {

		if (this.checkFields()) {

			Place place = Place.newInstanceByView(this, this.settings.getDatabaseSecretKey());

			String waitMessage = this.language.getString("places.new.wait.save");
			TaskManager.run(this.application.getAlertBuilder2(), this.thisStage, waitMessage, new PlaceSaveTask(
					this.application.getAlertBuilder2(), this.settings, this.thisStage, place, this.ownerCtrl));
		}
	}

	private boolean checkFields() {

		boolean check = true;

		if (this.descrTextField.getText().isEmpty()) {
			this.application.getAlertBuilder2().error(this.thisStage,
					this.language.getString("places.new.error.descr"));

			return false;
		}

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

	public HomeUserMenuPlaces getOwnerCtrl() {
		return ownerCtrl;
	}

	public void setOwnerCtrl(HomeUserMenuPlaces ownerCtrl) {
		this.ownerCtrl = ownerCtrl;
	}

	public Stage getThisStage() {
		return thisStage;
	}

	public void setThisStage(Stage thisStage) {
		this.thisStage = thisStage;
	}

	public SupportPlannerView getApplication() {
		return application;
	}

	public void setApplication(SupportPlannerView application) {
		this.application = application;
	}

	public AnchorPane getAnchorPane() {
		return anchorPane;
	}

	public void setAnchorPane(AnchorPane anchorPane) {
		this.anchorPane = anchorPane;
	}

	public ImageView getImageView() {
		return imageView;
	}

	public void setImageView(ImageView imageView) {
		this.imageView = imageView;
	}

	public Label getTitleLabel() {
		return titleLabel;
	}

	public void setTitleLabel(Label titleLabel) {
		this.titleLabel = titleLabel;
	}

	public Label getTypeLabel() {
		return typeLabel;
	}

	public void setTypeLabel(Label typeLabel) {
		this.typeLabel = typeLabel;
	}

	public Label getDescrLabel() {
		return descrLabel;
	}

	public void setDescrLabel(Label descrLabel) {
		this.descrLabel = descrLabel;
	}

	public ComboBox<EnumPlaceType> getTypeComboBox() {
		return typeComboBox;
	}

	public void setTypeComboBox(ComboBox<EnumPlaceType> typeComboBox) {
		this.typeComboBox = typeComboBox;
	}

	public TextField getDescrTextField() {
		return descrTextField;
	}

	public void setDescrTextField(TextField descrTextField) {
		this.descrTextField = descrTextField;
	}

	public Label getStreetLabel() {
		return streetLabel;
	}

	public void setStreetLabel(Label streetLabel) {
		this.streetLabel = streetLabel;
	}

	public Label getNumLabel() {
		return numLabel;
	}

	public void setNumLabel(Label numLabel) {
		this.numLabel = numLabel;
	}

	public Label getPostCodeLabel() {
		return postCodeLabel;
	}

	public void setPostCodeLabel(Label postCodeLabel) {
		this.postCodeLabel = postCodeLabel;
	}

	public Label getCityLabel() {
		return cityLabel;
	}

	public void setCityLabel(Label cityLabel) {
		this.cityLabel = cityLabel;
	}

	public Label getCountyLabel() {
		return countyLabel;
	}

	public void setCountyLabel(Label countyLabel) {
		this.countyLabel = countyLabel;
	}

	public Label getCountryLabel() {
		return countryLabel;
	}

	public void setCountryLabel(Label countryLabel) {
		this.countryLabel = countryLabel;
	}

	public Label getCoordLabel() {
		return coordLabel;
	}

	public void setCoordLabel(Label coordLabel) {
		this.coordLabel = coordLabel;
	}

	public Label getDefaultLabel() {
		return defaultLabel;
	}

	public void setDefaultLabel(Label defaultLabel) {
		this.defaultLabel = defaultLabel;
	}

	public TextField getStreetTextField() {
		return streetTextField;
	}

	public void setStreetTextField(TextField streetTextField) {
		this.streetTextField = streetTextField;
	}

	public TextField getNumTextField() {
		return numTextField;
	}

	public void setNumTextField(TextField numTextField) {
		this.numTextField = numTextField;
	}

	public TextField getPostCodeTextField() {
		return postCodeTextField;
	}

	public void setPostCodeTextField(TextField postCodeTextField) {
		this.postCodeTextField = postCodeTextField;
	}

	public TextField getCityTextField() {
		return cityTextField;
	}

	public void setCityTextField(TextField cityTextField) {
		this.cityTextField = cityTextField;
	}

	public TextField getCountyTextField() {
		return countyTextField;
	}

	public void setCountyTextField(TextField countyTextField) {
		this.countyTextField = countyTextField;
	}

	public TextField getCountryTextField() {
		return countryTextField;
	}

	public void setCountryTextField(TextField countryTextField) {
		this.countryTextField = countryTextField;
	}

	public TextField getCoordTextField() {
		return coordTextField;
	}

	public void setCoordTextField(TextField coordTextField) {
		this.coordTextField = coordTextField;
	}

	public CheckBox getDefaultCheckBox() {
		return defaultCheckBox;
	}

	public void setDefaultCheckBox(CheckBox defaultCheckBox) {
		this.defaultCheckBox = defaultCheckBox;
	}

	public Button getSaveButton() {
		return saveButton;
	}

	public void setSaveButton(Button saveButton) {
		this.saveButton = saveButton;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

}
