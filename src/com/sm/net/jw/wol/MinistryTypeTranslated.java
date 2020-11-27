package com.sm.net.jw.wol;

import com.sm.net.project.Language;

import javafx.beans.property.*;

public class MinistryTypeTranslated {
	private ObjectProperty<MinistryType> type;
	private StringProperty name;
	private IntegerProperty ordinal;

	public MinistryTypeTranslated(MinistryType ministryType, Language language) {
		this.type = new SimpleObjectProperty<>(ministryType);
		this.name = new SimpleStringProperty(language.getString(ministryType.getName()));
		this.ordinal = ministryType.ordinalProperty();
	}

	public MinistryTypeTranslated(int spInf3, Language language) {
		MinistryType ministryType = MinistryType.getFromOrdinal(spInf3);
		this.type = new SimpleObjectProperty<>(ministryType);
		this.name = new SimpleStringProperty(language.getString(ministryType.getName()));
		this.ordinal = ministryType.ordinalProperty();
	}

	public final StringProperty nameProperty() {
		return this.name;
	}

	public final String getName() {
		return (String) this.nameProperty().get();
	}

	public final void setName(String name) {
		this.nameProperty().set(name);
	}

	public final IntegerProperty ordinalProperty() {
		return this.ordinal;
	}

	public final int getOrdinal() {
		return this.ordinalProperty().get();
	}

	public final void setOrdinal(int ordinal) {
		this.ordinalProperty().set(ordinal);
	}

	public String toString() {
		return this.getName();
	}

	public final ObjectProperty<MinistryType> typeProperty() {
		return this.type;
	}

	public final MinistryType getType() {
		return (MinistryType) this.typeProperty().get();
	}

	public final void setType(MinistryType type) {
		this.typeProperty().set(type);
	}
}
