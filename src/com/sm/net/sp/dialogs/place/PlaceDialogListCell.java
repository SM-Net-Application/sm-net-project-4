package com.sm.net.sp.dialogs.place;

import com.sm.net.sp.model.Place;
import com.sm.net.sp.utils.PlaceUtils;

import javafx.scene.control.ListCell;

public class PlaceDialogListCell extends ListCell<Place> {

	@Override
	protected void updateItem(Place item, boolean empty) {
		super.updateItem(item, empty);

		if (!empty)
			setText(PlaceUtils.toText(item));
		else
			setText(null);

		setGraphic(null);
	}
}
