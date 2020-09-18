package com.sm.net.sp.view.home.user.menu.conven;

import com.sm.net.project.Language;
import com.sm.net.sp.model.EnumConventionType;

import javafx.scene.control.ListCell;

public class EnumConventionTypeComboBoxListCell extends ListCell<EnumConventionType> {

	private Language language;

	public EnumConventionTypeComboBoxListCell(Language language) {
		super();
		this.language = language;
	}

	@Override
	protected void updateItem(EnumConventionType item, boolean empty) {
		super.updateItem(item, empty);

		if (!empty) {

			String text = this.language.getString(item.getKey());
			setText(text);

		} else
			setText(null);

		setGraphic(null);
	}
}
