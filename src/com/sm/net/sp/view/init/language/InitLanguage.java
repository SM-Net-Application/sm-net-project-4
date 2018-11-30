package com.sm.net.sp.view.init.language;

import java.io.IOException;

import com.sm.net.project.Language;
import com.sm.net.sp.Meta;
import com.sm.net.sp.view.SupportPlannerView;
import com.sm.net.sp.view.access.create.AccessCreate;

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

public class InitLanguage {

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
		setStyleClass();
	}

	private void setStyleClass() {
		labelSelect.getStyleClass().add("labelStyle1");
		listViewLang.getStyleClass().add("listViewStyle1");
		buttonNext.getStyleClass().add("buttonStyle1");
	}

	public void init() {

		initListeners();
		initViewListViewLang();
	}

	private void initViewListViewLang() {

		if (languages.size() > 0) {
			listViewLang.setItems(languages);
			listViewLang.getSelectionModel().selectFirst();
		}
	}

	private void initListeners() {
		addListenerListViewLang();
		addListenerButtonNext();
	}

	private void addListenerButtonNext() {
		buttonNext.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				Language lang = listViewLang.getSelectionModel().getSelectedItem();
				if (lang != null) {
					initAccessCreateView();
				}
			}

		});
	}

	private void initAccessCreateView() {

		try {

			FXMLLoader fxmlLoader = new FXMLLoader();
			fxmlLoader.setLocation(Meta.Views.ACCESS_CREATE);

			AnchorPane anchorPane = (AnchorPane) fxmlLoader.load();

			AccessCreate controller = (AccessCreate) fxmlLoader.getController();
			// controller.setLanguages(languages);
			// controller.setSupportPlannerViewCtrl(this.supportPlannerViewCtrl);
			// controller.init();

			this.supportPlannerViewCtrl.loadAccessCreateView(anchorPane);

		} catch (IOException e) {
			// this.supportPlannerViewCtrl.exitError("ERR007");
		}
	}

	private void addListenerListViewLang() {

		listViewLang.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

				if (newValue.intValue() > -1)
					updateGUI();
			}
		});

	}

	private void updateGUI() {

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
