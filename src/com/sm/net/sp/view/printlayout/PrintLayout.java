package com.sm.net.sp.view.printlayout;

import java.io.IOException;
import java.util.Optional;

import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.model.EnumPrintLayouts;
import com.sm.net.sp.model.PrintLayoutTranslated;
import com.sm.net.sp.model.SerGroup;

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
	private ObservableList<SerGroup> servGroupList;

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
			if (enuPrintLayout == EnumPrintLayouts.NATURAL_DISASTER_SERVICEGROUPS) {

				if (this.servGroupList != null) {

					if (this.servGroupList.size() > 0)
						this.layouts.add(EnumPrintLayouts.getPrintLayoutTranslated(this.language,
								EnumPrintLayouts.NATURAL_DISASTER_SERVICEGROUPS_1, this.servGroupList.get(0)));

					if (this.servGroupList.size() > 1)
						this.layouts.add(EnumPrintLayouts.getPrintLayoutTranslated(this.language,
								EnumPrintLayouts.NATURAL_DISASTER_SERVICEGROUPS_2, this.servGroupList.get(1)));

					if (this.servGroupList.size() > 2)
						this.layouts.add(EnumPrintLayouts.getPrintLayoutTranslated(this.language,
								EnumPrintLayouts.NATURAL_DISASTER_SERVICEGROUPS_3, this.servGroupList.get(2)));

					if (this.servGroupList.size() > 3)
						this.layouts.add(EnumPrintLayouts.getPrintLayoutTranslated(this.language,
								EnumPrintLayouts.NATURAL_DISASTER_SERVICEGROUPS_4, this.servGroupList.get(3)));

					if (this.servGroupList.size() > 4)
						this.layouts.add(EnumPrintLayouts.getPrintLayoutTranslated(this.language,
								EnumPrintLayouts.NATURAL_DISASTER_SERVICEGROUPS_5, this.servGroupList.get(4)));

					if (this.servGroupList.size() > 5)
						this.layouts.add(EnumPrintLayouts.getPrintLayoutTranslated(this.language,
								EnumPrintLayouts.NATURAL_DISASTER_SERVICEGROUPS_6, this.servGroupList.get(5)));

					if (this.servGroupList.size() > 6)
						this.layouts.add(EnumPrintLayouts.getPrintLayoutTranslated(this.language,
								EnumPrintLayouts.NATURAL_DISASTER_SERVICEGROUPS_7, this.servGroupList.get(6)));

					if (this.servGroupList.size() > 7)
						this.layouts.add(EnumPrintLayouts.getPrintLayoutTranslated(this.language,
								EnumPrintLayouts.NATURAL_DISASTER_SERVICEGROUPS_8, this.servGroupList.get(7)));

					if (this.servGroupList.size() > 8)
						this.layouts.add(EnumPrintLayouts.getPrintLayoutTranslated(this.language,
								EnumPrintLayouts.NATURAL_DISASTER_SERVICEGROUPS_9, this.servGroupList.get(8)));

					if (this.servGroupList.size() > 9)
						this.layouts.add(EnumPrintLayouts.getPrintLayoutTranslated(this.language,
								EnumPrintLayouts.NATURAL_DISASTER_SERVICEGROUPS_10, this.servGroupList.get(9)));
				}
			} else
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

	public static EnumPrintLayouts dialogPrintLayout(Stage ownerStage, Language language,
			ObservableList<SerGroup> servGroupList, EnumPrintLayouts... layouts) {

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
			ctrl.setServGroupList(servGroupList);
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

	public ObservableList<SerGroup> getServGroupList() {
		return servGroupList;
	}

	public void setServGroupList(ObservableList<SerGroup> servGroupList) {
		this.servGroupList = servGroupList;
	}
}
