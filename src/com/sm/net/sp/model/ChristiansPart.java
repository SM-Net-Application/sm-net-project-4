package com.sm.net.sp.model;

import com.sm.net.project.Language;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ChristiansPart {

	private StringProperty fullText;
	private IntegerProperty min;
	private StringProperty theme;
	private StringProperty material;
	private ObjectProperty<Member> teacher;

	public ChristiansPart(String fullText, Integer min, String theme, String material, Member teacher) {
		super();
		this.fullText = new SimpleStringProperty(fullText);

		if (min != null)
			this.min = new SimpleIntegerProperty(min);
		else
			this.min = new SimpleIntegerProperty(Integer.valueOf(0));

		this.theme = new SimpleStringProperty(theme);
		this.material = new SimpleStringProperty(material);
		this.teacher = new SimpleObjectProperty<Member>(teacher);
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

	public final ObjectProperty<Member> teacherProperty() {
		return this.teacher;
	}

	public final Member getTeacher() {
		return this.teacherProperty().get();
	}

	public final void setTeacher(final Member teacher) {
		this.teacherProperty().set(teacher);
	}

	public static ChristiansPart newChristiansPart(Language language) {
		return new ChristiansPart("", 0, "", "", Member.emptyMember(language));
	}

	public String printChristiansPart(Language language) {

		String text = "";

		text += language.getString("TEXT0093");
		text += " : " + this.getMin();
		text += "\n";
		text += language.getString("TEXT0094");
		text += " : " + this.getTheme();
		text += "\n";
		text += language.getString("TEXT0095");
		text += " : " + this.getMaterial();
		text += "\n";
		text += language.getString("TEXT0098");
		text += " : " + this.getTeacher().getNameStyle1();

		return text;
	}

}
