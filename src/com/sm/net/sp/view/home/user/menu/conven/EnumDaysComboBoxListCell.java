package com.sm.net.sp.view.home.user.menu.conven;

import com.sm.net.project.Language;
import com.sm.net.sp.model.EnumDays;

import javafx.scene.control.ListCell;

public class EnumDaysComboBoxListCell extends ListCell<EnumDays> {

	private Language language;

	public EnumDaysComboBoxListCell(Language language) {
		super();
		this.language = language;
	}

	@Override
	protected void updateItem(EnumDays item, boolean empty) {
		super.updateItem(item, empty);

		if (!empty) {

			String key = item.getKey();
			if (key.isEmpty())
				setText("");
			else
				setText(this.language.getString(key));

		} else
			setText(null);

		setGraphic(null);
	}
}
