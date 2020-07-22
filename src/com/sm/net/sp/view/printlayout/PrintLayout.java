package com.sm.net.sp.view.printlayout;

import java.io.IOException;
import java.util.Optional;

import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.model.EnumPrintLayouts;
import com.sm.net.sp.model.PrintLayoutTranslated;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.image.ImageView;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

public class PrintLayout {

	@FXML
	private AnchorPane layout;

	@FXML
	private ImageView logoImageView;

	@FXML
	private Label titleLabel;

	@FXML
	private ListView<PrintLayoutTranslated> printLayoutListView;

	private Language language;
	private ObservableList<PrintLayoutTranslated> layouts;

	@FXML
	private void initialize() {
		styleClasses();
		this.printLayoutListView.setMinWidth(750);
	}

	private void styleClasses() {

		layout.getStyleClass().add("main_color_001");

		printLayoutListView.getStyleClass().add("list_view_001");

		titleLabel.getStyleClass().add("label_header_001");
	}

	private void objectInitialize(EnumPrintLayouts[] layouts) {

		initView();

		this.layouts = FXCollections.observableArrayList();

		for (EnumPrintLayouts enuPrintLayout : layouts)
			this.layouts.add(EnumPrintLayouts.getPrintLayoutTranslated(this.language, enuPrintLayout));

		this.printLayoutListView.setItems(this.layouts);

		if (this.layouts.size() > 0)
			this.printLayoutListView.getSelectionModel().selectFirst();
	}

	private void initView() {

		this.logoImageView.setImage(Meta.Resources.imageForImage(Meta.Resources.PRINT));
		this.titleLabel.setText(this.language.getString("sp.printlayout.select"));
		// this.printLayoutListView.setMinWidth(500);
	}

	private boolean checkSelectedIndex() {

		return (this.printLayoutListView.getSelectionModel().getSelectedIndex() > -1);
	}

	private EnumPrintLayouts getSelectedItem() {

		PrintLayoutTranslated item = this.printLayoutListView.getSelectionModel().getSelectedItem();
		return (item != null) ? item.getPrintLayout() : null;
	}

	public static EnumPrintLayouts dialogPrintLayout(Stage ownerStage, Language language, EnumPrintLayouts... layouts) {

		try {

			Dialog<EnumPrintLayouts> dialog = new Dialog<>();
			dialog.setTitle(Meta.Application.getFullTitle());

			DialogPane dialogPane = dialog.getDialogPane();
			dialogPane.getStylesheets().add(Meta.Themes.SUPPORTPLANNER_THEME);
			dialogPane.getStyleClass().add("dialog_001");
			dialogPane.setMinHeight(Region.USE_PREF_SIZE);

			Scene scene = dialogPane.getScene();

			Stage stage = (Stage) scene.getWindow();
			stage.getIcons().add(Meta.Resources.getImageApplicationIcon());
			stage.initOwner(ownerStage);

			dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(PrintLayout.class.getResource("PrintLayout.fxml"));
			AnchorPane content = (AnchorPane) fxmlLoader.load();
			PrintLayout ctrl = (PrintLayout) fxmlLoader.getController();
			ctrl.setLanguage(language);
			ctrl.objectInitialize(layouts);

			dialogPane.setContent(content);
			dialog.setResultConverter(param -> resultConverter(param, ctrl));

			Optional<EnumPrintLayouts> result = dialog.showAndWait();

			if (result.isPresent())
				return result.get();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	private static EnumPrintLayouts resultConverter(ButtonType param, PrintLayout ctrl) {

		if (param.getButtonData() == ButtonData.OK_DONE)
			if (ctrl.checkSelectedIndex())
				return ctrl.getSelectedItem();

		return null;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

}
