package com.sm.net.sp.model;

import com.sm.net.project.Language;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public enum WeekType {

	EMPTY("TEXT0115", 0), STANDARD("TEXT0116", 1), CONVENTION("TEXT0117", 2), CIRCUIT_ASSEMBLY("TEXT0118",
			3), CIRCUIT_OVERSEERS_VISIT("TEXT0119", 4), SPECIAL_PUBLIC_TALK("TEXT0120", 5), MEMORIAL("TEXT0121", 6);

	private StringProperty name;
	private IntegerProperty ordinal;

	WeekType(String name, int ordinal) {

		this.name = new SimpleStringProperty(name);
		this.ordinal = new SimpleIntegerProperty(ordinal);
	}

	public static WeekTypeTranslated getWeekTypeTranslated(WeekType weekType, Language language) {
		return new WeekTypeTranslated(weekType, language);
	}

	public static final ObservableList<WeekTypeTranslated> getAllWeekTypeTranslated(Language language) {

		ObservableList<WeekTypeTranslated> weekTypeTranslatedList = FXCollections.observableArrayList();

		for (WeekType weekType : values())
			weekTypeTranslatedList.add(new WeekTypeTranslated(weekType, language));

		return weekTypeTranslatedList;
	}

	public static ObservableList<WeekTypeTranslated> getNotEmptyWeekTypeTranslated(Language language) {

		ObservableList<WeekTypeTranslated> weekTypeTranslatedList = FXCollections.observableArrayList();

		for (WeekType weekType : values())
			if (!(weekType == WeekType.EMPTY))
				weekTypeTranslatedList.add(new WeekTypeTranslated(weekType, language));

		return weekTypeTranslatedList;
	}

	public static WeekType getFromOrdinal(int ordinal) {

		for (WeekType weekType : values())
			if (weekType.getOrdinal() == ordinal)
				return weekType;

		return WeekType.EMPTY;
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
