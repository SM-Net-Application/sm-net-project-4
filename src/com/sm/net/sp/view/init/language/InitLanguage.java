package com.sm.net.sp.view.init.language;

import com.sm.net.project.Language;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class InitLanguage {

	@FXML
	private Label labelSelect;

	@FXML
	private ListView<Language> listViewLang;

	@FXML
	private Button buttonNext;

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

}
