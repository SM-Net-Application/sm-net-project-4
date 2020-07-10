package com.sm.net.sp.view.home.user.menu.places;

import com.sm.net.project.Language;
import com.sm.net.sp.model.EnumPlaceType;

import javafx.scene.control.ListCell;

public class EnumPlaceTypeComboBoxListCell extends ListCell<EnumPlaceType> {

	private Language language;

	public EnumPlaceTypeComboBoxListCell(Language language) {
		super();
		this.language = language;
	}

	@Override
	protected void updateItem(EnumPlaceType item, boolean empty) {
		super.updateItem(item, empty);

		if (!empty)
			setText(this.language.getString(item.getTextKey()));
		else
			setText(null);

		setGraphic(null);
	}
}
