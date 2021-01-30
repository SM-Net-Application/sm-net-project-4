package com.sm.net.sp.view.home.user.menu.config;

import com.sm.net.project.Language;
import com.sm.net.sp.model.EnumPDFReplaceType;

import javafx.scene.control.ListCell;

public class EnumPDFReplaceTypeComboBoxListCell extends ListCell<EnumPDFReplaceType> {

	private Language language;

	public EnumPDFReplaceTypeComboBoxListCell(Language language) {
		super();
		this.language = language;
	}

	@Override
	protected void updateItem(EnumPDFReplaceType item, boolean empty) {
		super.updateItem(item, empty);

		if (!empty)
			setText(this.language.getString(item.getTextKey().get()));
		else
			setText(null);

		setGraphic(null);
	}
}
