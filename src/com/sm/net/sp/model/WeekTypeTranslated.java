package com.sm.net.sp.model;

import com.sm.net.project.Language;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class WeekTypeTranslated {

	private ObjectProperty<WeekType> type;
	private StringProperty name;
	private IntegerProperty ordinal;

	public WeekTypeTranslated(WeekType weekType, Language language) {

		this.type = new SimpleObjectProperty<WeekType>(weekType);
		this.name = new SimpleStringProperty(language.getString(weekType.getName()));
		this.ordinal = weekType.ordinalProperty();
	}

	@Override
	public String toString() {
		return getName();
	}

	public final ObjectProperty<WeekType> typeProperty() {
		return this.type;
	}

	public final WeekType getType() {
		return this.typeProperty().get();
	}

	public final void setType(final WeekType type) {
		this.typeProperty().set(type);
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
