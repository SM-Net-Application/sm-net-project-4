package com.sm.net.sp.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MinistryPart {

	private ObjectProperty<MinistryTypeTranslated> ministryTypeTranslated;
	private StringProperty fullText;
	private IntegerProperty min;
	private StringProperty theme;
	private StringProperty material;
	private ObjectProperty<Member> student;
	private ObjectProperty<Member> assistant;

	public MinistryPart(MinistryTypeTranslated ministryTypeTranslated, String fullText, Integer min, String theme,
			String material, Member student, Member assistant) {
		super();
		this.ministryTypeTranslated = new SimpleObjectProperty<MinistryTypeTranslated>(ministryTypeTranslated);
		this.fullText = new SimpleStringProperty(fullText);
		this.min = new SimpleIntegerProperty(min);
		this.theme = new SimpleStringProperty(theme);
		this.material = new SimpleStringProperty(material);
		this.student = new SimpleObjectProperty<Member>(student);
		this.assistant = new SimpleObjectProperty<Member>(assistant);
	}

	public final StringProperty fullTextProperty() {
		return this.fullText;
	}

	public final String getFullText() {
		return this.fullTextProperty().get();
	}

	public final void setFullText(final String fullText) {
		this.fullTextProperty().set(fullText);
	}

	public final IntegerProperty minProperty() {
		return this.min;
	}

	public final int getMin() {
		return this.minProperty().get();
	}

	public final void setMin(final int min) {
		this.minProperty().set(min);
	}

	public final StringProperty themeProperty() {
		return this.theme;
	}

	public final String getTheme() {
		return this.themeProperty().get();
	}

	public final void setTheme(final String theme) {
		this.themeProperty().set(theme);
	}

	public final StringProperty materialProperty() {
		return this.material;
	}

	public final String getMaterial() {
		return this.materialProperty().get();
	}

	public final void setMaterial(final String material) {
		this.materialProperty().set(material);
	}

	public final ObjectProperty<MinistryTypeTranslated> ministryTypeTranslatedProperty() {
		return this.ministryTypeTranslated;
	}

	public final MinistryTypeTranslated getMinistryTypeTranslated() {
		return this.ministryTypeTranslatedProperty().get();
	}

	public final void setMinistryTypeTranslated(final MinistryTypeTranslated ministryTypeTranslated) {
		this.ministryTypeTranslatedProperty().set(ministryTypeTranslated);
	}

	public final ObjectProperty<Member> studentProperty() {
		return this.student;
	}

	public final Member getStudent() {
		return this.studentProperty().get();
	}

	public final void setStudent(final Member student) {
		this.studentProperty().set(student);
	}

	public final ObjectProperty<Member> assistantProperty() {
		return this.assistant;
	}

	public final Member getAssistant() {
		return this.assistantProperty().get();
	}

	public final void setAssistant(final Member assistant) {
		this.assistantProperty().set(assistant);
	}

}
