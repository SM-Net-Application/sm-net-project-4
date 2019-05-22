package com.sm.net.sp.jasper.model;

import com.sm.net.project.Language;
import com.sm.net.sp.model.Member;
import com.sm.net.sp.model.MinistryPart;

public class JRMinistryPart {

	private Integer partType;
	private String min;
	private String theme;
	private String material;
	private String student1;
	private String student2;

	public JRMinistryPart(Integer partType, String min, String theme, String material, String student1,
			String student2) {
		super();
		this.partType = partType;
		this.min = min;
		this.theme = theme;
		this.material = material;
		this.student1 = student1;
		this.student2 = student2;
	}

	public static JRMinistryPart newObject(MinistryPart ministryPart, Language language) {

		Integer partType = Integer.valueOf(ministryPart.getMinistryTypeTranslated().getOrdinal());

		String min = String.format(language.getString("jasper.layout.meeting.min"),
				String.valueOf(ministryPart.getMin()));

		String theme = ministryPart.getTheme();

		String material = ministryPart.getMaterial();

		String student1Name = "";
		Member student1 = ministryPart.getStudent();
		if (student1 != null) {
			student1Name = student1.getNameStyle4();
			if (student1Name.trim().equals(language.getString("TEXT0096")))
				student1Name = "";
		}

		String assistant1Name = "";
		Member assistant1 = ministryPart.getAssistant();
		if (assistant1 != null) {
			assistant1Name = assistant1.getNameStyle4();
			if (assistant1Name.trim().equals(language.getString("TEXT0096")))
				assistant1Name = "";
		}

		if (!assistant1Name.isEmpty())
			student1Name += "\n   " + assistant1Name;

		String student2Name = "";
		Member student2 = ministryPart.getStudent2();
		if (student2 != null) {
			student2Name = student2.getNameStyle4();
			if (student2Name.trim().equals(language.getString("TEXT0096")))
				student2Name = "";
		}

		String assistant2Name = "";
		Member assistant2 = ministryPart.getAssistant2();
		if (assistant2 != null) {
			assistant2Name = assistant2.getNameStyle4();
			if (assistant2Name.trim().equals(language.getString("TEXT0096")))
				assistant2Name = "";
		}

		if (!assistant2Name.isEmpty())
			student2Name += "\n   " + assistant2Name;

		return new JRMinistryPart(partType, min, theme, material, student1Name, student2Name);
	}

	public Integer getPartType() {
		return partType;
	}

	public void setPartType(Integer partType) {
		this.partType = partType;
	}

	public String getMin() {
		return min;
	}

	public void setMin(String min) {
		this.min = min;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getStudent1() {
		return student1;
	}

	public void setStudent1(String student1) {
		this.student1 = student1;
	}

	public String getStudent2() {
		return student2;
	}

	public void setStudent2(String student2) {
		this.student2 = student2;
	}

}
