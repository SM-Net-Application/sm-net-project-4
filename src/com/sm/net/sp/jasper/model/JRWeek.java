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
import com.sm.net.sp.model.WeekOverseer;
import com.sm.net.sp.model.WeekType;

import javafx.collections.ObservableList;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class JRWeek {

	private Integer weekType;
	private String weekTypeText;
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
	private String christiansBibleStudyReaderText;
	private String christiansBibleStudyReaderName;
	private String christiansReviewMin;
	private String christiansReviewText;
	private String christiansMinSong3;
	private String christiansSong3;
	private String christiansPray2;
	private String christiansPray2Name;

	private String overseerHeader;
	private String overseerMinTalk1;
	private String overseerThemeTalk1;
	private String overseerName;
	private String overseerSongTalk1;

	private String weekHeader2;
	private String publicTalkHeader;
	private String publicTalkMinSong1;
	private String publicTalkSong1;
	private String publicTalkPresidentText;
	private String publicTalkPresident;
	private String publicTalkMin;
	private String publicTalkTheme;
	private String publicTalkName;
	private String publicTalkCongregation;
	private String watchtowerStudyHeader;
	private String watchtowerStudyMinSong2;
	private String watchtowerStudySong2;
	private String watchtowerStudyMin;
	private String watchtowerStudyTheme;
	private String watchtowerStudyName;
	private String watchtowerStudyReaderText;
	private String watchtowerStudyReaderName;
	private String watchtowerStudyMinSong3;
	private String watchtowerStudySong3;

	private String congregationName;
	private String programmName;

	private JasperReport jasperReportMinistryPart;
	private JasperReport jasperReportChristiansPart;
	private JRBeanCollectionDataSource jrDataSourceMinistryPart;
	private JRBeanCollectionDataSource jrDataSourceChristiansPart;

	public JRWeek() {
		super();
		reset();
	}

	private void reset() {

		this.weekType = null;
		this.weekTypeText = "";
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
		this.christiansBibleStudyReaderText = "";
		this.christiansBibleStudyReaderName = "";
		this.christiansReviewMin = "";
		this.christiansReviewText = "";
		this.christiansMinSong3 = "";
		this.christiansSong3 = "";
		this.christiansPray2 = "";
		this.christiansPray2Name = "";

		this.overseerHeader = "";
		this.overseerMinTalk1 = "";
		this.overseerThemeTalk1 = "";
		this.overseerName = "";
		this.overseerSongTalk1 = "";

		this.jasperReportMinistryPart = null;
		this.jasperReportChristiansPart = null;
		this.jrDataSourceMinistryPart = null;
		this.jrDataSourceChristiansPart = null;
	}

	public static JRWeek newObject(Week week, ObservableList<Member> membersList, Language language,
			boolean extendedName, boolean complete) throws JRException {

		JRWeek jrWeek = new JRWeek();

		String ministryPartReport = Jasper.Layouts.SM_NET_MEETINGS_MINISTRY_PART_ROW.getAbsolutePath();
		String christiansPartReport = Jasper.Layouts.SM_NET_MEETINGS_CHRISTIANS_PART_ROW.getAbsolutePath();

		ArrayList<JRMinistryPart> jrMinistryPart = new ArrayList<>();
		for (MinistryPart ministryPart : week.getMinistryPartList())
			jrMinistryPart.add(JRMinistryPart.newObject(ministryPart, language, extendedName));

		jrWeek.setJrDataSourceMinistryPart(new JRBeanCollectionDataSource(jrMinistryPart));

		ArrayList<JRChristiansPart> jrChristiansPart = new ArrayList<>();
		for (ChristiansPart christiansPart : week.getChristiansPartList())
			jrChristiansPart.add(JRChristiansPart.newObject(christiansPart, language, extendedName));

		jrWeek.setJrDataSourceChristiansPart(new JRBeanCollectionDataSource(jrChristiansPart));
		jrWeek.setJasperReportMinistryPart(JasperCompileManager.compileReport(ministryPartReport));
		jrWeek.setJasperReportChristiansPart(JasperCompileManager.compileReport(christiansPartReport));

		int spInf2 = week.getSpInf2();
		jrWeek.setWeekType(spInf2);

		WeekType weekType = WeekType.getFromOrdinal(spInf2);
		if (weekType != null)
			jrWeek.setWeekTypeText(language.getString(weekType.getName()));

		// Circuit overseer
		if (spInf2 == 4) {
			WeekOverseer weekOverseer = week.getWeekOverseer();
			if (weekOverseer != null) {
				String overseerName = weekOverseer.getSpInf3();
				String overseerShortName = weekOverseer.getSpInf4();
				String overseerSurname = weekOverseer.getSpInf5();
				String overseerSongTalk1 = weekOverseer.getSpInf8();
				String overseerMinTalk1 = weekOverseer.getSpInf9();
				String overseerThemeTalk1 = weekOverseer.getSpInf10();

				jrWeek.setOverseerHeader(language.getString("TEXT0131").toUpperCase());

				jrWeek.setOverseerMinTalk1(
						String.format(language.getString("jasper.layout.meeting.min"), overseerMinTalk1));
				jrWeek.setOverseerThemeTalk1(overseerThemeTalk1);

				if (extendedName)
					jrWeek.setOverseerName(overseerSurname + " " + overseerName);
				else
					jrWeek.setOverseerName(overseerSurname + " " + overseerShortName);

				jrWeek.setOverseerSongTalk1(
						String.format(language.getString("jasper.layout.meeting.song1"), overseerSongTalk1));
			}
		}

		jrWeek.setWeekHeader(checkMidweekHeader(week, spInf2, language, complete));
		jrWeek.setTreasuresHeader(language.getString("TEXT0080").toUpperCase());

		jrWeek.setTreasuresMinSong1(String.format(language.getString("jasper.layout.meeting.min"), "5"));

		jrWeek.setTreasuresSong1(String.format(language.getString("jasper.layout.meeting.song1"), week.getSpInf5()));
		jrWeek.setTreasuresPray1(language.getString("jasper.layout.meeting.pray1"));

		Member member = getMemberFromList(membersList, week.getSpInf4());
		if (member != null)
			if (extendedName)
				jrWeek.setTreasuresPray1Name(member.getNameStyle3());
			else
				jrWeek.setTreasuresPray1Name(member.getNameStyle4());

		jrWeek.setTreasuresOpeningCommentsMin(
				String.format(language.getString("jasper.layout.meeting.min"), week.getSpInf7()));

		jrWeek.setTreasuresOpeningCommentsText(week.getSpInf8());

		member = getMemberFromList(membersList, week.getSpInf3());
		if (member != null) {
			if (extendedName)
				jrWeek.setTreasuresPresident(member.getNameStyle3());
			else
				jrWeek.setTreasuresPresident(member.getNameStyle4());
		}

		jrWeek.setTreasuresTalkMin(String.format(language.getString("jasper.layout.meeting.min"), week.getSpInf9()));
		jrWeek.setTreasuresTalkTheme(week.getSpInf10());

		member = getMemberFromList(membersList, week.getSpInf11());
		if (member != null)
			if (extendedName)
				jrWeek.setTreasuresTalkName(member.getNameStyle3());
			else
				jrWeek.setTreasuresTalkName(member.getNameStyle4());

		jrWeek.setTreasuresDiggingMin(
				String.format(language.getString("jasper.layout.meeting.min"), week.getSpInf12()));
		jrWeek.setTreasuresDiggingTheme(week.getSpInf13());

		member = getMemberFromList(membersList, week.getSpInf14());
		if (member != null)
			if (extendedName)
				jrWeek.setTreasuresDiggingName(member.getNameStyle3());
			else
				jrWeek.setTreasuresDiggingName(member.getNameStyle4());

		jrWeek.setTreasuresBibleReadingMin(
				String.format(language.getString("jasper.layout.meeting.min"), week.getSpInf15()));

		jrWeek.setTreasuresBibleReadingTheme(String.format(language.getString("jasper.layout.meeting.dividedstyle"),
				week.getSpInf16(), week.getSpInf17()));

		member = getMemberFromList(membersList, week.getSpInf18());
		if (member != null)
			if (extendedName)
				jrWeek.setTreasuresBibleReadingName1(member.getNameStyle3());
			else
				jrWeek.setTreasuresBibleReadingName1(member.getNameStyle4());

		member = getMemberFromList(membersList, week.getSpInf28());
		if (member != null)
			if (extendedName)
				jrWeek.setTreasuresBibleReadingName2(member.getNameStyle3());
			else
				jrWeek.setTreasuresBibleReadingName2(member.getNameStyle4());

		jrWeek.setMinistryPartHeader(language.getString("TEXT0081").toUpperCase());

		jrWeek.setChristiansMinSong2(String.format(language.getString("jasper.layout.meeting.min"), "5"));
		jrWeek.setChristiansSong2(String.format(language.getString("jasper.layout.meeting.song2"), week.getSpInf19()));

		jrWeek.setChristiansBibleStudyMin(
				String.format(language.getString("jasper.layout.meeting.min"), week.getSpInf20()));
		jrWeek.setChristiansBibleStudyText(String.format(language.getString("jasper.layout.meeting.dividedstyle"),
				week.getSpInf21(), week.getSpInf22()));

		member = getMemberFromList(membersList, week.getSpInf23());
		if (member != null)
			if (extendedName)
				jrWeek.setChristiansBibleStudyName(member.getNameStyle3());
			else
				jrWeek.setChristiansBibleStudyName(member.getNameStyle4());

		jrWeek.setChristiansBibleStudyReaderText(
				String.format(language.getString("jasper.layout.meeting.reader"), week.getSpInf20()));

		member = getMemberFromList(membersList, week.getSpInf29());
		if (member != null)
			if (extendedName)
				jrWeek.setChristiansBibleStudyReaderName(member.getNameStyle3());
			else
				jrWeek.setChristiansBibleStudyReaderName(member.getNameStyle4());

		jrWeek.setChristiansReviewMin(
				String.format(language.getString("jasper.layout.meeting.min"), week.getSpInf24()));
		jrWeek.setChristiansReviewText(week.getSpInf25());

		jrWeek.setChristiansMinSong3(String.format(language.getString("jasper.layout.meeting.min"), "5"));
		jrWeek.setChristiansSong3(String.format(language.getString("jasper.layout.meeting.song3"), week.getSpInf26()));

		jrWeek.setChristiansPray2(language.getString("jasper.layout.meeting.pray2"));

		member = getMemberFromList(membersList, week.getSpInf27());
		if (member != null)
			if (extendedName)
				jrWeek.setChristiansPray2Name(member.getNameStyle3());
			else
				jrWeek.setChristiansPray2Name(member.getNameStyle4());

		jrWeek.setChristiansPartHeader(language.getString("TEXT0082").toUpperCase());

		jrWeek.setWeekHeader2(checkWeekendHeader(week, spInf2, language));
		jrWeek.setPublicTalkHeader(language.getString("jasper.layout.meeting.weekend.publictalk").toUpperCase());

		jrWeek.setPublicTalkMinSong1(String.format(language.getString("jasper.layout.meeting.min"), "5"));
		jrWeek.setPublicTalkSong1(String.format(language.getString("jasper.layout.meeting.song1"), week.getSpInf31()));
		jrWeek.setPublicTalkPresidentText(language.getString("jasper.layout.meeting.weekend.president"));

		member = getMemberFromList(membersList, week.getSpInf30());
		if (member != null)
			if (extendedName)
				jrWeek.setPublicTalkPresident(member.getNameStyle3());
			else
				jrWeek.setPublicTalkPresident(member.getNameStyle4());

		jrWeek.setPublicTalkMin(String.format(language.getString("jasper.layout.meeting.min"), "30"));
		jrWeek.setPublicTalkTheme(week.getSpInf32());
		jrWeek.setPublicTalkName(week.getSpInf33());
		jrWeek.setPublicTalkCongregation(week.getSpInf34());

		jrWeek.setWatchtowerStudyHeader(
				language.getString("jasper.layout.meeting.weekend.watchtowerstudy").toUpperCase());

		jrWeek.setWatchtowerStudyMinSong2(String.format(language.getString("jasper.layout.meeting.min"), "5"));
		jrWeek.setWatchtowerStudySong2(
				String.format(language.getString("jasper.layout.meeting.song1"), week.getSpInf35()));
		jrWeek.setWatchtowerStudyMin(String.format(language.getString("jasper.layout.meeting.min"), "60"));
		jrWeek.setWatchtowerStudyTheme(week.getSpInf36());

		member = getMemberFromList(membersList, week.getSpInf37());
		if (member != null)
			if (extendedName)
				jrWeek.setWatchtowerStudyName(member.getNameStyle3());
			else
				jrWeek.setWatchtowerStudyName(member.getNameStyle4());

		jrWeek.setWatchtowerStudyReaderText(language.getString("jasper.layout.meeting.weekend.reader"));

		member = getMemberFromList(membersList, week.getSpInf38());
		if (member != null)
			if (extendedName)
				jrWeek.setWatchtowerStudyReaderName(member.getNameStyle3());
			else
				jrWeek.setWatchtowerStudyReaderName(member.getNameStyle4());

		jrWeek.setWatchtowerStudyMinSong3(String.format(language.getString("jasper.layout.meeting.min"), "5"));
		jrWeek.setWatchtowerStudySong3(
				String.format(language.getString("jasper.layout.meeting.song1"), week.getSpInf39()));

		return jrWeek;
	}

	private static Member getMemberFromList(ObservableList<Member> membersList, int id) {
		for (Member member : membersList)
			if (member.getSpMemberID() == id)
				return member;
		return null;
	}

	private static String checkMidweekHeader(Week week, int spInf2, Language language, boolean complete) {

		if (spInf2 == 1) {

			if (complete)
				return String.format(language.getString("jasper.layout.meeting.midweek.withbible"), week.getSpInf6());
			else
				return String.format(language.getString("jasper.layout.meeting.weekheader.with.bible"),
						checkWeekFromText(week, spInf2, language), week.getSpInf6());

		} else {
			return checkWeekFromText(week, spInf2, language);
		}
	}

	public static String getProgrammNameHeader(Week week, Language language) {

		String header = checkWeekFromText(week, week.getSpInf2(), language);
		return String.format(language.getString("jasper.layout.meeting.programmcomplete.header"), header);
	}

	private static String checkWeekendHeader(Week week, int spInf2, Language language) {

		return (spInf2 == 1) ? String.format(language.getString("jasper.layout.meeting.weekend"), week.getSpInf6())
				: "";
	}

	private static String checkWeekFromText(Week week, int spInf2, Language language) {

		LocalDate from = week.getFrom();

		DateTimeFormatter dtfDay = DateTimeFormatter.ofPattern("d");
		DateTimeFormatter dtfMonth = DateTimeFormatter.ofPattern("M");

		String day = dtfDay.format(from);

		String keyMonth = "time.months." + dtfMonth.format(from);
		String month = language.getString(keyMonth);

		String keyWeek = language.getString("jasper.layout.meeting.weekheader");

		return String.format(keyWeek, day, month.toUpperCase());
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

	public Integer getWeekType() {
		return weekType;
	}

	public void setWeekType(Integer weekType) {
		this.weekType = weekType;
	}

	public String getWeekTypeText() {
		return weekTypeText;
	}

	public void setWeekTypeText(String weekTypeText) {
		this.weekTypeText = weekTypeText;
	}

	public String getOverseerMinTalk1() {
		return overseerMinTalk1;
	}

	public void setOverseerMinTalk1(String overseerMinTalk1) {
		this.overseerMinTalk1 = overseerMinTalk1;
	}

	public String getOverseerThemeTalk1() {
		return overseerThemeTalk1;
	}

	public void setOverseerThemeTalk1(String overseerThemeTalk1) {
		this.overseerThemeTalk1 = overseerThemeTalk1;
	}

	public String getOverseerName() {
		return overseerName;
	}

	public void setOverseerName(String overseerName) {
		this.overseerName = overseerName;
	}

	public String getOverseerSongTalk1() {
		return overseerSongTalk1;
	}

	public void setOverseerSongTalk1(String overseerSongTalk1) {
		this.overseerSongTalk1 = overseerSongTalk1;
	}

	public String getOverseerHeader() {
		return overseerHeader;
	}

	public void setOverseerHeader(String overseerHeader) {
		this.overseerHeader = overseerHeader;
	}

	public String getChristiansBibleStudyReaderText() {
		return christiansBibleStudyReaderText;
	}

	public void setChristiansBibleStudyReaderText(String christiansBibleStudyReaderText) {
		this.christiansBibleStudyReaderText = christiansBibleStudyReaderText;
	}

	public String getWeekHeader2() {
		return weekHeader2;
	}

	public void setWeekHeader2(String weekHeader2) {
		this.weekHeader2 = weekHeader2;
	}

	public String getPublicTalkHeader() {
		return publicTalkHeader;
	}

	public void setPublicTalkHeader(String publicTalkHeader) {
		this.publicTalkHeader = publicTalkHeader;
	}

	public String getWatchtowerStudyHeader() {
		return watchtowerStudyHeader;
	}

	public void setWatchtowerStudyHeader(String watchtowerStudyHeader) {
		this.watchtowerStudyHeader = watchtowerStudyHeader;
	}

	public String getPublicTalkMinSong1() {
		return publicTalkMinSong1;
	}

	public void setPublicTalkMinSong1(String publicTalkMinSong1) {
		this.publicTalkMinSong1 = publicTalkMinSong1;
	}

	public String getPublicTalkSong1() {
		return publicTalkSong1;
	}

	public void setPublicTalkSong1(String publicTalkSong1) {
		this.publicTalkSong1 = publicTalkSong1;
	}

	public String getPublicTalkPresidentText() {
		return publicTalkPresidentText;
	}

	public void setPublicTalkPresidentText(String publicTalkPresidentText) {
		this.publicTalkPresidentText = publicTalkPresidentText;
	}

	public String getPublicTalkPresident() {
		return publicTalkPresident;
	}

	public void setPublicTalkPresident(String publicTalkPresident) {
		this.publicTalkPresident = publicTalkPresident;
	}

	public String getPublicTalkMin() {
		return publicTalkMin;
	}

	public void setPublicTalkMin(String publicTalkMin) {
		this.publicTalkMin = publicTalkMin;
	}

	public String getPublicTalkTheme() {
		return publicTalkTheme;
	}

	public void setPublicTalkTheme(String publicTalkTheme) {
		this.publicTalkTheme = publicTalkTheme;
	}

	public String getPublicTalkName() {
		return publicTalkName;
	}

	public void setPublicTalkName(String publicTalkName) {
		this.publicTalkName = publicTalkName;
	}

	public String getPublicTalkCongregation() {
		return publicTalkCongregation;
	}

	public void setPublicTalkCongregation(String publicTalkCongregation) {
		this.publicTalkCongregation = publicTalkCongregation;
	}

	public String getWatchtowerStudyMinSong2() {
		return watchtowerStudyMinSong2;
	}

	public void setWatchtowerStudyMinSong2(String watchtowerStudyMinSong2) {
		this.watchtowerStudyMinSong2 = watchtowerStudyMinSong2;
	}

	public String getWatchtowerStudySong2() {
		return watchtowerStudySong2;
	}

	public void setWatchtowerStudySong2(String watchtowerStudySong2) {
		this.watchtowerStudySong2 = watchtowerStudySong2;
	}

	public String getWatchtowerStudyMin() {
		return watchtowerStudyMin;
	}

	public void setWatchtowerStudyMin(String watchtowerStudyMin) {
		this.watchtowerStudyMin = watchtowerStudyMin;
	}

	public String getWatchtowerStudyTheme() {
		return watchtowerStudyTheme;
	}

	public void setWatchtowerStudyTheme(String watchtowerStudyTheme) {
		this.watchtowerStudyTheme = watchtowerStudyTheme;
	}

	public String getWatchtowerStudyName() {
		return watchtowerStudyName;
	}

	public void setWatchtowerStudyName(String watchtowerStudyName) {
		this.watchtowerStudyName = watchtowerStudyName;
	}

	public String getWatchtowerStudyReaderText() {
		return watchtowerStudyReaderText;
	}

	public void setWatchtowerStudyReaderText(String watchtowerStudyReaderText) {
		this.watchtowerStudyReaderText = watchtowerStudyReaderText;
	}

	public String getWatchtowerStudyReaderName() {
		return watchtowerStudyReaderName;
	}

	public void setWatchtowerStudyReaderName(String watchtowerStudyReaderName) {
		this.watchtowerStudyReaderName = watchtowerStudyReaderName;
	}

	public String getWatchtowerStudyMinSong3() {
		return watchtowerStudyMinSong3;
	}

	public void setWatchtowerStudyMinSong3(String watchtowerStudyMinSong3) {
		this.watchtowerStudyMinSong3 = watchtowerStudyMinSong3;
	}

	public String getWatchtowerStudySong3() {
		return watchtowerStudySong3;
	}

	public void setWatchtowerStudySong3(String watchtowerStudySong3) {
		this.watchtowerStudySong3 = watchtowerStudySong3;
	}

	public String getCongregationName() {
		return congregationName;
	}

	public void setCongregationName(String congregationName) {
		this.congregationName = congregationName;
	}

	public String getProgrammName() {
		return programmName;
	}

	public void setProgrammName(String programmName) {
		this.programmName = programmName;
	}
}
