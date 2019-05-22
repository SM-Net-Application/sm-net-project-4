package com.sm.net.sp.jasper.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import com.sm.net.project.Language;
import com.sm.net.sp.jasper.Jasper;
import com.sm.net.sp.model.ChristiansPart;
import com.sm.net.sp.model.Member;
import com.sm.net.sp.model.MinistryPart;
import com.sm.net.sp.model.Week;

import javafx.collections.ObservableList;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class JRWeek {

	private String weekHeader;
	private String treasuresHeader;
	private String treasuresMinSong1;
	private String treasuresSong1;
	private String treasuresPray1;
	private String treasuresPray1Name;
	private String treasuresOpeningCommentsMin;
	private String treasuresOpeningCommentsText;
	private String treasuresPresident;
	private String ministryPartHeader;
	private String christiansPartHeader;

	private JasperReport jasperReportMinistryPart;
	private JasperReport jasperReportChristiansPart;
	private JRBeanCollectionDataSource jrDataSourceMinistryPart;
	private JRBeanCollectionDataSource jrDataSourceChristiansPart;

	public JRWeek() {
		super();
		reset();
	}

	private void reset() {

		this.weekHeader = "";
		this.treasuresHeader = "";
		this.treasuresMinSong1 = "";
		this.treasuresSong1 = "";
		this.treasuresPray1 = "";
		this.treasuresPray1Name = "";
		this.treasuresOpeningCommentsMin = "";
		this.treasuresOpeningCommentsText = "";
		this.treasuresPresident = "";
		this.ministryPartHeader = "";
		this.christiansPartHeader = "";
		this.jasperReportMinistryPart = null;
		this.jasperReportChristiansPart = null;
		this.jrDataSourceMinistryPart = null;
		this.jrDataSourceChristiansPart = null;
	}

	public static JRWeek newObject(Week week, ObservableList<Member> membersList, Language language)
			throws JRException {

		JRWeek jrWeek = new JRWeek();

		String ministryPartReport = Jasper.Layouts.SP_MINISTRY_PART_ROW_LAYOUT.getAbsolutePath();
		String christiansPartReport = Jasper.Layouts.SP_CHRISTIANS_PART_ROW_LAYOUT.getAbsolutePath();

		ArrayList<JRMinistryPart> jrMinistryPart = new ArrayList<>();
		for (MinistryPart ministryPart : week.getMinistryPartList())
			jrMinistryPart.add(JRMinistryPart.newObject(ministryPart, language));

		jrWeek.setJrDataSourceMinistryPart(new JRBeanCollectionDataSource(jrMinistryPart));

		ArrayList<JRChristiansPart> jrChristiansPart = new ArrayList<>();
		for (ChristiansPart christiansPart : week.getChristiansPartList())
			jrChristiansPart.add(JRChristiansPart.newObject(christiansPart, language));

		jrWeek.setJrDataSourceChristiansPart(new JRBeanCollectionDataSource(jrChristiansPart));
		jrWeek.setJasperReportMinistryPart(JasperCompileManager.compileReport(ministryPartReport));
		jrWeek.setJasperReportChristiansPart(JasperCompileManager.compileReport(christiansPartReport));

		jrWeek.setWeekHeader(checkWeekHeader(week, language));
		jrWeek.setTreasuresHeader(language.getString("TEXT0080").toUpperCase());

		jrWeek.setTreasuresMinSong1(
				String.format(language.getString("jasper.layout.meeting.min"), Integer.valueOf(5).toString()));

		jrWeek.setTreasuresSong1(String.format(language.getString("jasper.layout.meeting.song1"), week.getSpInf5()));
		jrWeek.setTreasuresPray1(language.getString("jasper.layout.meeting.pray1"));

		Member member = getMemberFromList(membersList, week.getSpInf4());
		if (member != null)
			jrWeek.setTreasuresPray1Name(member.getNameStyle4());

		jrWeek.setTreasuresOpeningCommentsMin(
				String.format(language.getString("jasper.layout.meeting.min"), week.getSpInf7()));

		jrWeek.setTreasuresOpeningCommentsText(week.getSpInf8());

		member = getMemberFromList(membersList, week.getSpInf3());
		if (member != null)
			jrWeek.setTreasuresPresident(member.getNameStyle4());

		jrWeek.setMinistryPartHeader(language.getString("TEXT0081").toUpperCase());
		jrWeek.setChristiansPartHeader(language.getString("TEXT0082").toUpperCase());

		return jrWeek;
	}

	private static Member getMemberFromList(ObservableList<Member> membersList, int id) {
		for (Member member : membersList)
			if (member.getSpMemberID() == id)
				return member;
		return null;
	}

	private static String checkWeekHeader(Week week, Language language) {

		LocalDate from = week.getFrom();

		DateTimeFormatter dtfDay = DateTimeFormatter.ofPattern("d");
		DateTimeFormatter dtfMonth = DateTimeFormatter.ofPattern("M");

		String day = dtfDay.format(from);

		String keyMonth = "time.months." + dtfMonth.format(from);
		String month = language.getString(keyMonth);

		String keyWeek = language.getString("jasper.layout.meeting.weekheader");

		return String.format(keyWeek, day, month.toUpperCase(), week.getSpInf6());
	}

	public String getWeekHeader() {
		return weekHeader;
	}

	public void setWeekHeader(String weekHeader) {
		this.weekHeader = weekHeader;
	}

	public String getTreasuresHeader() {
		return treasuresHeader;
	}

	public void setTreasuresHeader(String treasuresHeader) {
		this.treasuresHeader = treasuresHeader;
	}

	public String getMinistryPartHeader() {
		return ministryPartHeader;
	}

	public void setMinistryPartHeader(String ministryPartHeader) {
		this.ministryPartHeader = ministryPartHeader;
	}

	public String getChristiansPartHeader() {
		return christiansPartHeader;
	}

	public void setChristiansPartHeader(String christiansPartHeader) {
		this.christiansPartHeader = christiansPartHeader;
	}

	public JasperReport getJasperReportMinistryPart() {
		return jasperReportMinistryPart;
	}

	public void setJasperReportMinistryPart(JasperReport jasperReportMinistryPart) {
		this.jasperReportMinistryPart = jasperReportMinistryPart;
	}

	public JasperReport getJasperReportChristiansPart() {
		return jasperReportChristiansPart;
	}

	public void setJasperReportChristiansPart(JasperReport jasperReportChristiansPart) {
		this.jasperReportChristiansPart = jasperReportChristiansPart;
	}

	public JRBeanCollectionDataSource getJrDataSourceMinistryPart() {
		return jrDataSourceMinistryPart;
	}

	public void setJrDataSourceMinistryPart(JRBeanCollectionDataSource jrDataSourceMinistryPart) {
		this.jrDataSourceMinistryPart = jrDataSourceMinistryPart;
	}

	public JRBeanCollectionDataSource getJrDataSourceChristiansPart() {
		return jrDataSourceChristiansPart;
	}

	public void setJrDataSourceChristiansPart(JRBeanCollectionDataSource jrDataSourceChristiansPart) {
		this.jrDataSourceChristiansPart = jrDataSourceChristiansPart;
	}

	public String getTreasuresMinSong1() {
		return treasuresMinSong1;
	}

	public void setTreasuresMinSong1(String treasuresMinSong1) {
		this.treasuresMinSong1 = treasuresMinSong1;
	}

	public String getTreasuresSong1() {
		return treasuresSong1;
	}

	public void setTreasuresSong1(String treasuresSong1) {
		this.treasuresSong1 = treasuresSong1;
	}

	public String getTreasuresPray1() {
		return treasuresPray1;
	}

	public void setTreasuresPray1(String treasuresPray1) {
		this.treasuresPray1 = treasuresPray1;
	}

	public String getTreasuresPray1Name() {
		return treasuresPray1Name;
	}

	public void setTreasuresPray1Name(String treasuresPray1Name) {
		this.treasuresPray1Name = treasuresPray1Name;
	}

	public String getTreasuresOpeningCommentsMin() {
		return treasuresOpeningCommentsMin;
	}

	public void setTreasuresOpeningCommentsMin(String treasuresOpeningCommentsMin) {
		this.treasuresOpeningCommentsMin = treasuresOpeningCommentsMin;
	}

	public String getTreasuresOpeningCommentsText() {
		return treasuresOpeningCommentsText;
	}

	public void setTreasuresOpeningCommentsText(String treasuresOpeningCommentsText) {
		this.treasuresOpeningCommentsText = treasuresOpeningCommentsText;
	}

	public String getTreasuresPresident() {
		return treasuresPresident;
	}

	public void setTreasuresPresident(String treasuresPresident) {
		this.treasuresPresident = treasuresPresident;
	}

}
