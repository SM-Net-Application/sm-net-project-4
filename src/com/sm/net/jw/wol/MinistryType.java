package com.sm.net.jw.wol;

import com.sm.net.project.Language;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public enum MinistryType {

	DISCUSSION("wol.ministry.discussion", 1),

	INITIAL_CALL("wol.ministry.initialcall", 2),

	RETURN_VISIT("wol.ministry.returnvisit", 3),

	BIBLE_STUDY("wol.ministry.biblestudy", 4),

	TALK("wol.ministry.talk", 5),

	MEMORIAL("wol.ministry.memorial", 6);
	
	private StringProperty name;
	private IntegerProperty ordinal;

	private MinistryType(String name, int ordinal) {
		this.name = new SimpleStringProperty(name);
		this.ordinal = new SimpleIntegerProperty(ordinal);
	}

	public static MinistryTypeTranslated getMinistryTypeTranslated(MinistryType ministryType, Language language) {
		return new MinistryTypeTranslated(ministryType, language);
	}

	public static final ObservableList<MinistryTypeTranslated> getAllMinistryTypeTranslated(Language language) {
		ObservableList<MinistryTypeTranslated> ministryTypeTranslatedList = FXCollections.observableArrayList();
		MinistryType[] var5;
		int var4 = (var5 = values()).length;

		for (int var3 = 0; var3 < var4; ++var3) {
			MinistryType ministryType = var5[var3];
			ministryTypeTranslatedList.add(new MinistryTypeTranslated(ministryType, language));
		}

		return ministryTypeTranslatedList;
	}

	public static MinistryType getFromOrdinal(int ordinal) {
		MinistryType[] var4;
		int var3 = (var4 = values()).length;

		for (int var2 = 0; var2 < var3; ++var2) {
			MinistryType mt = var4[var2];
			if (mt.getOrdinal() == ordinal) {
				return mt;
			}
		}

		return DISCUSSION;
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
}
