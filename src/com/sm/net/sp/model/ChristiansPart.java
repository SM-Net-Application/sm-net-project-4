package com.sm.net.sp.model;

import org.json.JSONObject;

import com.sm.net.project.Language;
import com.sm.net.sp.settings.Settings;
import com.sm.net.util.Crypt;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

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

	public ChristiansPart(JSONObject jb, Language language, Settings settings, ObservableList<Member> membersList) {

		// int spWeekCrID = jb.getInt("spWeekCrID");
		// int spInf1 = jb.getInt("spInf1");
		// int spInf2 = jb.getInt("spInf2");
		String spInf3 = jb.getString("spInf3");
		String spInf4 = jb.getString("spInf4");
		String spInf5 = jb.getString("spInf5");
		int spInf6 = jb.getInt("spInf6");

		this.fullText = new SimpleStringProperty("");

		if (!spInf3.isEmpty())
			this.min = new SimpleIntegerProperty(
					Integer.valueOf(Crypt.decrypt(spInf3, settings.getDatabaseSecretKey())));
		else
			this.min = new SimpleIntegerProperty(Integer.valueOf(0));

		if (!spInf4.isEmpty())
			this.theme = new SimpleStringProperty(Crypt.decrypt(spInf4, settings.getDatabaseSecretKey()));
		else
			this.theme = new SimpleStringProperty("");

		if (!spInf5.isEmpty())
			this.material = new SimpleStringProperty(Crypt.decrypt(spInf5, settings.getDatabaseSecretKey()));
		else
			this.material = new SimpleStringProperty("");

		this.teacher = new SimpleObjectProperty<Member>(Member.getFromID(membersList, spInf6, language));
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
