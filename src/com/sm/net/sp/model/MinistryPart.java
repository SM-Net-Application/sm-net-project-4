package com.sm.net.sp.model;

import org.json.JSONObject;

import com.sm.net.jw.wol.MinistryType;
import com.sm.net.jw.wol.MinistryTypeTranslated;
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

public class MinistryPart {

	private ObjectProperty<MinistryTypeTranslated> ministryTypeTranslated;
	private StringProperty fullText;
	private IntegerProperty min;
	private StringProperty theme;
	private StringProperty material;
	private ObjectProperty<Member> student;
	private ObjectProperty<Member> assistant;
	private ObjectProperty<Member> student2;
	private ObjectProperty<Member> assistant2;

	public MinistryPart(MinistryTypeTranslated ministryTypeTranslated, String fullText, Integer min, String theme,
			String material, Member student, Member assistant, Member student2, Member assistant2) {
		super();
		this.ministryTypeTranslated = new SimpleObjectProperty<MinistryTypeTranslated>(ministryTypeTranslated);
		this.fullText = new SimpleStringProperty(fullText);
		this.min = new SimpleIntegerProperty(min);
		this.theme = new SimpleStringProperty(theme);
		this.material = new SimpleStringProperty(material);
		this.student = new SimpleObjectProperty<Member>(student);
		this.assistant = new SimpleObjectProperty<Member>(assistant);
		this.student2 = new SimpleObjectProperty<Member>(student2);
		this.assistant2 = new SimpleObjectProperty<Member>(assistant2);
	}

	public MinistryPart(JSONObject jb, Language language, Settings settings, ObservableList<Member> membersList) {

		// int spWeekMinID = jb.getInt("spWeekMinID");
		// int spInf1 = jb.getInt("spInf1");
		// int spInf2 = jb.getInt("spInf2");
		int spInf3 = jb.getInt("spInf3");
		String spInf4 = jb.getString("spInf4");
		String spInf5 = jb.getString("spInf5");
		String spInf6 = jb.getString("spInf6");
		int spInf7 = jb.getInt("spInf7");
		int spInf8 = jb.getInt("spInf8");
		int spInf9 = jb.getInt("spInf9");
		int spInf10 = jb.getInt("spInf10");

		this.ministryTypeTranslated = new SimpleObjectProperty<MinistryTypeTranslated>(
				new MinistryTypeTranslated(spInf3, language));
		this.fullText = new SimpleStringProperty("");

		if (!spInf4.isEmpty())
			this.min = new SimpleIntegerProperty(
					Integer.valueOf(Crypt.decrypt(spInf4, settings.getDatabaseSecretKey())));
		else
			this.min = new SimpleIntegerProperty(Integer.valueOf(0));

		if (!spInf5.isEmpty())
			this.theme = new SimpleStringProperty(Crypt.decrypt(spInf5, settings.getDatabaseSecretKey()));
		else
			this.theme = new SimpleStringProperty("");

		if (!spInf6.isEmpty())
			this.material = new SimpleStringProperty(Crypt.decrypt(spInf6, settings.getDatabaseSecretKey()));
		else
			this.material = new SimpleStringProperty("");

		this.student = new SimpleObjectProperty<Member>(Member.getFromID(membersList, spInf7, language));
		this.assistant = new SimpleObjectProperty<Member>(Member.getFromID(membersList, spInf8, language));

		this.student2 = new SimpleObjectProperty<Member>(Member.getFromID(membersList, spInf9, language));
		this.assistant2 = new SimpleObjectProperty<Member>(Member.getFromID(membersList, spInf10, language));
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

	public static MinistryPart newMinistryPart(Language language) {

		return new MinistryPart(new MinistryTypeTranslated(MinistryType.INITIAL_CALL, language), "", 0, "", "",
				Member.emptyMember(language), Member.emptyMember(language), Member.emptyMember(language),
				Member.emptyMember(language));
	}

	public String printMinistryPart(Language language) {

		String text = "";

		text += language.getString("TEXT0091");
		text += " : " + this.ministryTypeTranslated.get().getName();
		text += "\n";
		text += language.getString("TEXT0093");
		text += " : " + this.getMin();
		text += "\n";
		text += language.getString("TEXT0094");
		text += " : " + this.getTheme();
		text += "\n";
		text += language.getString("TEXT0095");
		text += " : " + this.getMaterial();
		text += "\n";
		text += language.getString("TEXT0044");
		text += " : " + this.getStudent().getNameStyle1();

		return text;
	}

	public final ObjectProperty<Member> student2Property() {
		return this.student2;
	}

	public final Member getStudent2() {
		return this.student2Property().get();
	}

	public final void setStudent2(final Member student2) {
		this.student2Property().set(student2);
	}

	public final ObjectProperty<Member> assistant2Property() {
		return this.assistant2;
	}

	public final Member getAssistant2() {
		return this.assistant2Property().get();
	}

	public final void setAssistant2(final Member assistant2) {
		this.assistant2Property().set(assistant2);
	}

}
