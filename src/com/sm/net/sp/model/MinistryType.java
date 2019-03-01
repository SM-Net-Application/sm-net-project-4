package com.sm.net.sp.model;

import com.sm.net.project.Language;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public enum MinistryType {

	DISCUSSION("TEXT0090", 1), INITIAL_CALL("TEXT0048", 2), RETURN_VISIT("TEXT0049", 3), BIBLE_STUDY("TEXT0050",
			4), TALK("TEXT0051", 5);

	private StringProperty name;
	private IntegerProperty ordinal;

	MinistryType(String name, int ordinal) {

		this.name = new SimpleStringProperty(name);
		this.ordinal = new SimpleIntegerProperty(ordinal);
	}

	public static final ObservableList<MinistryTypeTranslated> getMinistryTypeTranslated(Language language) {

		ObservableList<MinistryTypeTranslated> ministryTypeTranslatedList = FXCollections.observableArrayList();

		for (MinistryType ministryType : values())
			ministryTypeTranslatedList.add(new MinistryTypeTranslated(ministryType, language));

		return ministryTypeTranslatedList;
	}

	public final StringProperty nameProperty() {
		return this.name;
	}

	public final String getName() {
		return this.nameProperty().get();
	}

	public final void setName(final String name) {
		this.nameProperty().set(name);
	}

	public final IntegerProperty ordinalProperty() {
		return this.ordinal;
	}

	public final int getOrdinal() {
		return this.ordinalProperty().get();
	}

	public final void setOrdinal(final int ordinal) {
		this.ordinalProperty().set(ordinal);
	}
}
