package com.sm.net.sp.view.setting.create.language;

import java.io.IOException;

import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.view.SupportPlannerView;
import com.sm.net.sp.view.setting.create.access.SettingCreateAccess;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

public class SettingCreateLanguage {

	@FXML
	private Label labelSelect;

	@FXML
	private ListView<Language> listViewLang;

	@FXML
	private Button buttonNext;

	private SupportPlannerView supportPlannerViewCtrl;
	
	private ObservableList<Language> languages;

	@FXML
	private void initialize() {
		styleClasses();
	}

	public void objectInitialize() {
		listeners();
		dataListViewLang();
	}

	private void styleClasses() {
		labelSelect.getStyleClass().add("labelStyle1");
		listViewLang.getStyleClass().add("listViewStyle1");
		buttonNext.getStyleClass().add("buttonStyle1");
	}

	private void dataListViewLang() {
		if (languages.size() > 0) {
			listViewLang.setItems(languages);
			listViewLang.getSelectionModel().selectFirst();
		}
	}

	private void listeners() {
		listenerListViewLang();
		listenerButtonNext();
	}

	private void listenerListViewLang() {
		listViewLang.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				if (newValue.intValue() > -1)
					viewUpdate();
			}
		});
	}

	private void listenerButtonNext() {
		buttonNext.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Language lang = listViewLang.getSelectionModel().getSelectedItem();
				if (lang != null)
					viewSettingCreateAccess(lang);
			}

		});
	}

	private void viewSettingCreateAccess(Language language) {

		try {

			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(Meta.Views.SETTING_CREATE_ACCESS);

			AnchorPane anchorPane = (AnchorPane) fxmlLoader.load();

			SettingCreateAccess controller = (SettingCreateAccess) fxmlLoader.getController();
			controller.setLanguage(language);
			controller.setSupportPlannerViewCtrl(this.supportPlannerViewCtrl);
			controller.objectInitialize();

			this.supportPlannerViewCtrl.viewSettingCreateAccess(anchorPane);

		} catch (IOException e) {
		}
	}

	private void viewUpdate() {

		Language language = listViewLang.getSelectionModel().getSelectedItem();
		labelSelect.setText(language.getString("VIEW001LAB001"));
		buttonNext.setText(language.getString("VIEW001BUT001"));
	}

	public ObservableList<Language> getLanguages() {
		return languages;
	}

	public void setLanguages(ObservableList<Language> languages) {
		this.languages = languages;
	}

	public SupportPlannerView getSupportPlannerViewCtrl() {
		return supportPlannerViewCtrl;
	}

	public void setSupportPlannerViewCtrl(SupportPlannerView supportPlannerViewCtrl) {
		this.supportPlannerViewCtrl = supportPlannerViewCtrl;
	}

}
