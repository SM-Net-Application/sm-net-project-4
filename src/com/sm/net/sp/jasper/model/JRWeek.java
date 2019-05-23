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
	private String treasuresTalkMin;
	private String treasuresTalkTheme;
	private String treasuresTalkName;
	private String treasuresDiggingMin;
	private String treasuresDiggingTheme;
	private String treasuresDiggingName;
	private String treasuresBibleReadingMin;
	private String treasuresBibleReadingTheme;
	private String treasuresBibleReadingName1;
	private String treasuresBibleReadingName2;
	private String ministryPartHeader;
	private String christiansMinSong2;
	private String christiansSong2;
	private String christiansPartHeader;
	private String christiansBibleStudyMin;
	private String christiansBibleStudyText;
	private String christiansBibleStudyName;
	private String christiansBibleStudyReaderName;
	private String christiansReviewMin;
	private String christiansReviewText;
	private String christiansMinSong3;
	private String christiansSong3;
	private String christiansPray2;
	private String christiansPray2Name;

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
		this.treasuresTalkMin = "";
		this.treasuresTalkTheme = "";
		this.treasuresTalkName = "";
		this.treasuresDiggingMin = "";
		this.treasuresDiggingTheme = "";
		this.treasuresDiggingName = "";
		this.treasuresBibleReadingMin = "";
		this.treasuresBibleReadingTheme = "";
		this.treasuresBibleReadingName1 = "";
		this.treasuresBibleReadingName2 = "";
		this.christiansMinSong2 = "";
		this.christiansSong2 = "";
		this.christiansBibleStudyMin = "";
		this.christiansBibleStudyText = "";
		this.christiansBibleStudyName = "";
		this.christiansBibleStudyReaderName = "";
		this.christiansReviewMin = "";
		this.christiansReviewText = "";
		this.christiansMinSong3 = "";
		this.christiansSong3 = "";
		this.christiansPray2 = "";
		this.christiansPray2Name = "";
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

		jrWeek.setTreasuresMinSong1(String.format(language.getString("jasper.layout.meeting.min"), "5"));

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

		jrWeek.setTreasuresTalkMin(String.format(language.getString("jasper.layout.meeting.min"), week.getSpInf9()));
		jrWeek.setTreasuresTalkTheme(week.getSpInf10());

		member = getMemberFromList(membersList, week.getSpInf11());
		if (member != null)
			jrWeek.setTreasuresTalkName(member.getNameStyle4());

		jrWeek.setTreasuresDiggingMin(
				String.format(language.getString("jasper.layout.meeting.min"), week.getSpInf12()));
		jrWeek.setTreasuresDiggingTheme(week.getSpInf13());

		member = getMemberFromList(membersList, week.getSpInf14());
		if (member != null)
			jrWeek.setTreasuresDiggingName(member.getNameStyle4());

		jrWeek.setTreasuresBibleReadingMin(
				String.format(language.getString("jasper.layout.meeting.min"), week.getSpInf15()));

		jrWeek.setTreasuresBibleReadingTheme(String.format(language.getString("jasper.layout.meeting.dividedstyle"),
				week.getSpInf16(), week.getSpInf17()));

		member = getMemberFromList(membersList, week.getSpInf18());
		if (member != null)
			jrWeek.setTreasuresBibleReadingName1(member.getNameStyle4());

		jrWeek.setMinistryPartHeader(language.getString("TEXT0081").toUpperCase());

		jrWeek.setChristiansMinSong2(String.format(language.getString("jasper.layout.meeting.min"), "5"));
		jrWeek.setChristiansSong2(String.format(language.getString("jasper.layout.meeting.song2"), week.getSpInf19()));

		jrWeek.setChristiansBibleStudyMin(
				String.format(language.getString("jasper.layout.meeting.min"), week.getSpInf20()));
		jrWeek.setChristiansBibleStudyText(String.format(language.getString("jasper.layout.meeting.dividedstyle"),
				week.getSpInf21(), week.getSpInf22()));

		member = getMemberFromList(membersList, week.getSpInf23());
		if (member != null)
			jrWeek.setChristiansBibleStudyName(member.getNameStyle4());

		// TODO: Integrare il nome quando verranno gestiti i lettori
		jrWeek.setChristiansBibleStudyReaderName("");

		jrWeek.setChristiansReviewMin(
				String.format(language.getString("jasper.layout.meeting.min"), week.getSpInf24()));
		jrWeek.setChristiansReviewText(week.getSpInf25());

		jrWeek.setChristiansMinSong3(String.format(language.getString("jasper.layout.meeting.min"), "5"));
		jrWeek.setChristiansSong3(String.format(language.getString("jasper.layout.meeting.song3"), week.getSpInf26()));

		jrWeek.setChristiansPray2(language.getString("jasper.layout.meeting.pray2"));

		member = getMemberFromList(membersList, week.getSpInf27());
		if (member != null)
			jrWeek.setChristiansPray2Name(member.getNameStyle4());

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

	public String getTreasuresTalkMin() {
		return treasuresTalkMin;
	}

	public void setTreasuresTalkMin(String treasuresTalkMin) {
		this.treasuresTalkMin = treasuresTalkMin;
	}

	public String getTreasuresTalkTheme() {
		return treasuresTalkTheme;
	}

	public void setTreasuresTalkTheme(String treasuresTalkTheme) {
		this.treasuresTalkTheme = treasuresTalkTheme;
	}

	public String getTreasuresTalkName() {
		return treasuresTalkName;
	}

	public void setTreasuresTalkName(String treasuresTalkName) {
		this.treasuresTalkName = treasuresTalkName;
	}

	public String getTreasuresDiggingMin() {
		return treasuresDiggingMin;
	}

	public void setTreasuresDiggingMin(String treasuresDiggingMin) {
		this.treasuresDiggingMin = treasuresDiggingMin;
	}

	public String getTreasuresDiggingTheme() {
		return treasuresDiggingTheme;
	}

	public void setTreasuresDiggingTheme(String treasuresDiggingTheme) {
		this.treasuresDiggingTheme = treasuresDiggingTheme;
	}

	public String getTreasuresDiggingName() {
		return treasuresDiggingName;
	}

	public void setTreasuresDiggingName(String treasuresDiggingName) {
		this.treasuresDiggingName = treasuresDiggingName;
	}

	public String getTreasuresBibleReadingMin() {
		return treasuresBibleReadingMin;
	}

	public void setTreasuresBibleReadingMin(String treasuresBibleReadingMin) {
		this.treasuresBibleReadingMin = treasuresBibleReadingMin;
	}

	public String getTreasuresBibleReadingTheme() {
		return treasuresBibleReadingTheme;
	}

	public void setTreasuresBibleReadingTheme(String treasuresBibleReadingTheme) {
		this.treasuresBibleReadingTheme = treasuresBibleReadingTheme;
	}

	public String getTreasuresBibleReadingName1() {
		return treasuresBibleReadingName1;
	}

	public void setTreasuresBibleReadingName1(String treasuresBibleReadingName1) {
		this.treasuresBibleReadingName1 = treasuresBibleReadingName1;
	}

	public String getTreasuresBibleReadingName2() {
		return treasuresBibleReadingName2;
	}

	public void setTreasuresBibleReadingName2(String treasuresBibleReadingName2) {
		this.treasuresBibleReadingName2 = treasuresBibleReadingName2;
	}

	public String getChristiansMinSong2() {
		return christiansMinSong2;
	}

	public void setChristiansMinSong2(String christiansMinSong2) {
		this.christiansMinSong2 = christiansMinSong2;
	}

	public String getChristiansSong2() {
		return christiansSong2;
	}

	public void setChristiansSong2(String christiansSong2) {
		this.christiansSong2 = christiansSong2;
	}

	public String getChristiansBibleStudyMin() {
		return christiansBibleStudyMin;
	}

	public void setChristiansBibleStudyMin(String christiansBibleStudyMin) {
		this.christiansBibleStudyMin = christiansBibleStudyMin;
	}

	public String getChristiansBibleStudyText() {
		return christiansBibleStudyText;
	}

	public void setChristiansBibleStudyText(String christiansBibleStudyText) {
		this.christiansBibleStudyText = christiansBibleStudyText;
	}

	public String getChristiansBibleStudyName() {
		return christiansBibleStudyName;
	}

	public void setChristiansBibleStudyName(String christiansBibleStudyName) {
		this.christiansBibleStudyName = christiansBibleStudyName;
	}

	public String getChristiansBibleStudyReaderName() {
		return christiansBibleStudyReaderName;
	}

	public void setChristiansBibleStudyReaderName(String christiansBibleStudyReaderName) {
		this.christiansBibleStudyReaderName = christiansBibleStudyReaderName;
	}

	public String getChristiansReviewMin() {
		return christiansReviewMin;
	}

	public void setChristiansReviewMin(String christiansReviewMin) {
		this.christiansReviewMin = christiansReviewMin;
	}

	public String getChristiansReviewText() {
		return christiansReviewText;
	}

	public void setChristiansReviewText(String christiansReviewText) {
		this.christiansReviewText = christiansReviewText;
	}

	public String getChristiansMinSong3() {
		return christiansMinSong3;
	}

	public void setChristiansMinSong3(String christiansMinSong3) {
		this.christiansMinSong3 = christiansMinSong3;
	}

	public String getChristiansSong3() {
		return christiansSong3;
	}

	public void setChristiansSong3(String christiansSong3) {
		this.christiansSong3 = christiansSong3;
	}

	public String getChristiansPray2() {
		return christiansPray2;
	}

	public void setChristiansPray2(String christiansPray2) {
		this.christiansPray2 = christiansPray2;
	}

	public String getChristiansPray2Name() {
		return christiansPray2Name;
	}

	public void setChristiansPray2Name(String christiansPray2Name) {
		this.christiansPray2Name = christiansPray2Name;
	}

}
