package com.sm.net.sp.jasper.model;

import com.sm.net.project.Language;
import com.sm.net.sp.model.ChristiansPart;
import com.sm.net.sp.model.Member;

public class JRChristiansPart {

	private String min;
	private String theme;
	private String material;
	private String teacher;

	public JRChristiansPart(String min, String theme, String material, String teacher) {
		super();
		this.min = min;
		this.theme = theme;
		this.material = material;
		this.teacher = teacher;
	}

	public static JRChristiansPart newObject(ChristiansPart christiansPart, Language language) {

		String min = String.format(language.getString("jasper.layout.meeting.min"),
				String.valueOf(christiansPart.getMin()));

		String theme = christiansPart.getTheme();

		String material = christiansPart.getMaterial();

		String teacherName = "";
		Member teacher = christiansPart.getTeacher();
		if (teacher != null) {
			teacherName = teacher.getNameStyle4();
			if (teacherName.trim().equals(language.getString("TEXT0096")))
				teacherName = "";
		}

		return new JRChristiansPart(min, theme, material, teacherName);
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

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

}
