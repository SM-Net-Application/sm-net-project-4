package com.sm.net.sp.jasper.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

import com.sm.net.project.Language;
import com.sm.net.sp.jasper.Jasper;
import com.sm.net.sp.model.ChristiansPart;
import com.sm.net.sp.model.EnumConventionType;
import com.sm.net.sp.model.EnumDays;
import com.sm.net.sp.model.EnumMonths;
import com.sm.net.sp.model.Family;
import com.sm.net.sp.model.Member;
import com.sm.net.sp.model.MinistryPart;
import com.sm.net.sp.model.Week;
import com.sm.net.sp.model.WeekConvention;
import com.sm.net.sp.model.WeekMemorial;
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
	private String overseerName;
	private String overseerSongTalk1;
	private String overseerMinTalk1;
	private String overseerThemeTalk1;

	private String overseerSongTalk2;
	private String overseerMinTalk2;
	private String overseerThemeTalk2;

	private String overseerMinTalk3;
	private String overseerThemeTalk3;

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

	private String place1;
	private String place2;

	private boolean convPrintOneDay;
	private boolean convPrintThreeDay;
	private String convType;
	private String convTheme;

	private String convDay1;
	private String convDay2;
	private String convDay3;

	private String convScripture1;
	private String convScripture2;
	private String convScripture3;

	private String convAddr;

	private String convQHeader;
	private String convQ1;
	private String convQ2;
	private String convQ3;
	private String convQ4;
	private String convQ5;
	private String convQ6;
	private String convQ7;
	private String convQ8;
	private String convQ9;
	private String convQ10;

	private boolean memPrintMidweek;
	private boolean memPrintWeekend;

	private String memDay;
	private String memPlace;
	private String memSong1Min;
	private String memSong1;
	private boolean memPrayPresident;
	private String memPresidentName;
	private String memPresidentNameText;
	private String memPray1Name;
	private String memPray1NameText;

	private String memTalkMin;
	private String memTalkTheme;
	private String memTalkName;

	private String memPrayBreadText;
	private String memPrayBreadName;
	private String memPrayWineText;
	private String memPrayWineName;

	private String memSong2Min;
	private String memSong2;
	private String memPray2Name;
	private String memPray2NameText;

	private String memFamilyEmblemsText;
	private String memFamilyEmblemsList;

	private String memMemberEmblemsText;
	private String memMemberEmblemsList;

	private String memPlaceText;

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

		this.weekHeader2 = "";
		this.publicTalkHeader = "";
		this.publicTalkMinSong1 = "";
		this.publicTalkSong1 = "";
		this.publicTalkPresidentText = "";
		this.publicTalkPresident = "";
		this.publicTalkMin = "";
		this.publicTalkTheme = "";
		this.publicTalkName = "";
		this.publicTalkCongregation = "";
		this.watchtowerStudyHeader = "";
		this.watchtowerStudyMinSong2 = "";
		this.watchtowerStudySong2 = "";
		this.watchtowerStudyMin = "";
		this.watchtowerStudyTheme = "";
		this.watchtowerStudyName = "";
		this.watchtowerStudyReaderText = "";
		this.watchtowerStudyReaderName = "";
		this.watchtowerStudyMinSong3 = "";
		this.watchtowerStudySong3 = "";

		this.place1 = "";
		this.place2 = "";

		this.congregationName = "";
		this.programmName = "";

		this.jasperReportMinistryPart = null;
		this.jasperReportChristiansPart = null;
		this.jrDataSourceMinistryPart = null;
		this.jrDataSourceChristiansPart = null;
	}

	public static JRWeek newObject(Week week, ObservableList<Member> membersList, ObservableList<Family> familiesList,
			ObservableList<WeekConvention> convention, ObservableList<WeekMemorial> memorial, Language language,
			boolean extendedName, boolean complete) throws JRException {

		LocalDate weekFrom = week.getFrom();

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

		int weekTypeID = week.getSpInf2();
		jrWeek.setWeekType(weekTypeID);

		WeekType weekType = WeekType.getFromOrdinal(weekTypeID);
		if (weekType != null)
			jrWeek.setWeekTypeText(language.getString(weekType.getName()));

		int weekKey = week.getSpInf1();

		if (weekType == WeekType.CONVENTION) {

			WeekConvention weekConvention = null;
			for (WeekConvention c : convention)
				if (c.getSpInf31() == weekKey) {
					weekConvention = c;
					break;
				}
			if (weekConvention != null) {

				// Week convention data

				int convTypeID = weekConvention.getSpInf1();
				int convYear = weekConvention.getSpInf2();
				String convTheme = weekConvention.getSpInf3();
				String convScripture1 = weekConvention.getSpInf4();
				String convScripture2 = weekConvention.getSpInf5();
				String convScripture3 = weekConvention.getSpInf6();

				int convOneDay = weekConvention.getSpInf7();
				int convDay1HourStart = weekConvention.getSpInf8();
				int convDay1MinuteStart = weekConvention.getSpInf9();
				int convDay1HourEnd = weekConvention.getSpInf10();
				int convDay1MinuteEnd = weekConvention.getSpInf11();

				int convDay2HourStart = weekConvention.getSpInf12();
				int convDay2MinuteStart = weekConvention.getSpInf13();
				int convDay2HourEnd = weekConvention.getSpInf14();
				int convDay2MinuteEnd = weekConvention.getSpInf15();

				int convDay3HourStart = weekConvention.getSpInf16();
				int convDay3MinuteStart = weekConvention.getSpInf17();
				int convDay3HourEnd = weekConvention.getSpInf18();
				int convDay3MinuteEnd = weekConvention.getSpInf19();

				String convQHeader = weekConvention.getSpInf20();
				String convQ1 = weekConvention.getSpInf21();
				String convQ2 = weekConvention.getSpInf22();
				String convQ3 = weekConvention.getSpInf23();
				String convQ4 = weekConvention.getSpInf24();
				String convQ5 = weekConvention.getSpInf25();
				String convQ6 = weekConvention.getSpInf26();
				String convQ7 = weekConvention.getSpInf27();
				String convQ8 = weekConvention.getSpInf28();
				String convQ9 = weekConvention.getSpInf29();
				String convQ10 = weekConvention.getSpInf30();

				String convAddr = weekConvention.getSpInf32();

				// Data workflow

				EnumConventionType convType = EnumConventionType.getByID(convTypeID);

				// Set data to JasperReports

				String convTypeCircuit = "";
				String convTypeFormat = "";
				String convTypeFormatText = "";
				String convTypeDayFormat = "";

				LocalDate convDay1 = null;
				LocalDate convDay2 = null;
				LocalDate convDay3 = null;

				EnumDays convDay1Enum = EnumDays.LUN;
				EnumDays convDay2Enum = EnumDays.LUN;
				EnumDays convDay3Enum = EnumDays.LUN;

				String convDay1EnumText = "";
				String convDay2EnumText = "";
				String convDay3EnumText = "";

				int convDay1DayOfMonth = 0;
				int convDay2DayOfMonth = 0;
				int convDay3DayOfMonth = 0;

				int convDay1Month = 0;
				int convDay2Month = 0;
				int convDay3Month = 0;

				int convDay1Year = 0;
				int convDay2Year = 0;
				int convDay3Year = 0;

				String convDay1Text = "";
				String convDay2Text = "";
				String convDay3Text = "";

				EnumMonths convDay1MonthEnum = EnumMonths.GEN;
				EnumMonths convDay2MonthEnum = EnumMonths.GEN;
				EnumMonths convDay3MonthEnum = EnumMonths.GEN;

				String convDay1MonthText = "";
				String convDay2MonthText = "";
				String convDay3MonthText = "";

				switch (convType) {
				case CIRCUITASSEMBLY_BRANCH:

					jrWeek.setConvPrintOneDay(true);
					jrWeek.setConvPrintThreeDay(false);

					convTypeCircuit = language.getString("jasper.convention.type.circuit");
					String convTypeBranch = language.getString("jasper.convention.type.branch");

					convTypeFormat = language.getString("jasper.convention.type.circuit.format");

					convTypeFormatText = String.format(convTypeFormat, convTypeCircuit, convYear, (convYear + 1),
							convTypeBranch);

					jrWeek.setConvType(convTypeFormatText);

					convDay1 = weekFrom.plus((convOneDay - 1), ChronoUnit.DAYS);
					convDay1Enum = EnumDays.getByID(convDay1.getDayOfWeek().getValue());

					convDay1EnumText = language.getString(convDay1Enum.getKey());
					convDay1DayOfMonth = convDay1.getDayOfMonth();
					convDay1Month = convDay1.getMonthValue();
					convDay1MonthEnum = EnumMonths.getByID(convDay1Month);
					convDay1MonthText = language.getString(convDay1MonthEnum.getKey());
					convDay1Year = convDay1.getYear();

					convTypeDayFormat = language.getString("jasper.convention.type.day.format");

					convDay1Text = String.format(convTypeDayFormat, convDay1EnumText, convDay1DayOfMonth,
							convDay1MonthText, convDay1Year, convDay1HourStart, convDay1MinuteStart, convDay1HourEnd,
							convDay1MinuteEnd);

					jrWeek.setConvDay1(convDay1Text);

					jrWeek.setConvQHeader(convQHeader);
					jrWeek.setConvQ1(convQ1);
					jrWeek.setConvQ2(convQ2);
					jrWeek.setConvQ3(convQ3);
					jrWeek.setConvQ4(convQ4);
					jrWeek.setConvQ5(convQ5);
					jrWeek.setConvQ6(convQ6);
					jrWeek.setConvQ7(convQ7);
					jrWeek.setConvQ8(convQ8);
					jrWeek.setConvQ9(convQ9);
					jrWeek.setConvQ10(convQ10);

					break;
				case CIRCUITASSEMBLY_OVERSEER:

					jrWeek.setConvPrintOneDay(true);
					jrWeek.setConvPrintThreeDay(false);

					convTypeCircuit = language.getString("jasper.convention.type.circuit");
					String convTypeOverseer = language.getString("jasper.convention.type.overseer");

					convTypeFormat = language.getString("jasper.convention.type.circuit.format");
					convTypeFormatText = String.format(convTypeFormat, convTypeCircuit, convYear, (convYear + 1),
							convTypeOverseer);

					jrWeek.setConvType(convTypeFormatText);

					convDay1 = weekFrom.plus((convOneDay - 1), ChronoUnit.DAYS);
					convDay1Enum = EnumDays.getByID(convDay1.getDayOfWeek().getValue());

					convDay1EnumText = language.getString(convDay1Enum.getKey());
					convDay1DayOfMonth = convDay1.getDayOfMonth();
					convDay1Month = convDay1.getMonthValue();
					convDay1MonthEnum = EnumMonths.getByID(convDay1Month);
					convDay1MonthText = language.getString(convDay1MonthEnum.getKey());
					convDay1Year = convDay1.getYear();

					convTypeDayFormat = language.getString("jasper.convention.type.day.format");

					convDay1Text = String.format(convTypeDayFormat, convDay1EnumText, convDay1DayOfMonth,
							convDay1MonthText, convDay1Year, convDay1HourStart, convDay1MinuteStart, convDay1HourEnd,
							convDay1MinuteEnd);

					jrWeek.setConvDay1(convDay1Text);

					jrWeek.setConvQHeader(convQHeader);
					jrWeek.setConvQ1(convQ1);
					jrWeek.setConvQ2(convQ2);
					jrWeek.setConvQ3(convQ3);
					jrWeek.setConvQ4(convQ4);
					jrWeek.setConvQ5(convQ5);
					jrWeek.setConvQ6(convQ6);
					jrWeek.setConvQ7(convQ7);
					jrWeek.setConvQ8(convQ8);
					jrWeek.setConvQ9(convQ9);
					jrWeek.setConvQ10(convQ10);

					break;
				case REGIONALCONVENTION:

					jrWeek.setConvPrintOneDay(false);
					jrWeek.setConvPrintThreeDay(true);

					String convTypeRegional = language.getString("jasper.convention.type.regional");
					convTypeFormat = language.getString("jasper.convention.type.regional.format");

					convTypeFormatText = String.format(convTypeFormat, convTypeRegional, convYear);

					jrWeek.setConvType(convTypeFormatText);

					convDay1 = weekFrom.plus(4, ChronoUnit.DAYS);
					convDay2 = weekFrom.plus(5, ChronoUnit.DAYS);
					convDay3 = weekFrom.plus(6, ChronoUnit.DAYS);

					convDay1Enum = EnumDays.getByID(convDay1.getDayOfWeek().getValue());
					convDay2Enum = EnumDays.getByID(convDay2.getDayOfWeek().getValue());
					convDay3Enum = EnumDays.getByID(convDay3.getDayOfWeek().getValue());

					convDay1EnumText = language.getString(convDay1Enum.getKey());
					convDay2EnumText = language.getString(convDay2Enum.getKey());
					convDay3EnumText = language.getString(convDay3Enum.getKey());

					convDay1DayOfMonth = convDay1.getDayOfMonth();
					convDay2DayOfMonth = convDay2.getDayOfMonth();
					convDay3DayOfMonth = convDay3.getDayOfMonth();

					convDay1Month = convDay1.getMonthValue();
					convDay2Month = convDay2.getMonthValue();
					convDay3Month = convDay3.getMonthValue();

					convDay1MonthEnum = EnumMonths.getByID(convDay1Month);
					convDay2MonthEnum = EnumMonths.getByID(convDay2Month);
					convDay3MonthEnum = EnumMonths.getByID(convDay3Month);

					convDay1MonthText = language.getString(convDay1MonthEnum.getKey());
					convDay2MonthText = language.getString(convDay2MonthEnum.getKey());
					convDay3MonthText = language.getString(convDay3MonthEnum.getKey());

					convDay1Year = convDay1.getYear();
					convDay2Year = convDay2.getYear();
					convDay3Year = convDay3.getYear();

					convTypeDayFormat = language.getString("jasper.convention.type.day.format");

					convDay1Text = String.format(convTypeDayFormat, convDay1EnumText, convDay1DayOfMonth,
							convDay1MonthText, convDay1Year, convDay1HourStart, convDay1MinuteStart, convDay1HourEnd,
							convDay1MinuteEnd);
					convDay2Text = String.format(convTypeDayFormat, convDay2EnumText, convDay2DayOfMonth,
							convDay2MonthText, convDay2Year, convDay2HourStart, convDay2MinuteStart, convDay2HourEnd,
							convDay2MinuteEnd);
					convDay3Text = String.format(convTypeDayFormat, convDay3EnumText, convDay3DayOfMonth,
							convDay3MonthText, convDay3Year, convDay3HourStart, convDay3MinuteStart, convDay3HourEnd,
							convDay3MinuteEnd);

					jrWeek.setConvDay1(convDay1Text);
					jrWeek.setConvDay2(convDay2Text);
					jrWeek.setConvDay3(convDay3Text);

					jrWeek.setConvScripture1(convScripture1);
					jrWeek.setConvScripture2(convScripture2);
					jrWeek.setConvScripture3(convScripture3);

					break;
				default:

					jrWeek.setConvPrintOneDay(false);
					jrWeek.setConvPrintThreeDay(false);

					break;
				}

				jrWeek.setConvTheme(convTheme);
				jrWeek.setConvAddr(convAddr);
			}
		}

		if (weekType == WeekType.MEMORIAL) {

			WeekMemorial weekMemorial = null;
			for (WeekMemorial m : memorial)
				if (m.getSpInf1() == weekKey) {
					weekMemorial = m;
					break;
				}

			boolean memorialPrint = true;

			if (weekMemorial != null) {

				int dayID = weekMemorial.getSpInf21();
				EnumDays memorialEnumDay = EnumDays.getByID(dayID);
				switch (memorialEnumDay) {
				case LUN:
				case MAR:
				case MER:
				case GIO:
				case VEN:

					jrWeek.setMemPrintMidweek(true);
					jrWeek.setMemPrintWeekend(false);

					break;

				case SAB:
				case DOM:

					jrWeek.setMemPrintMidweek(false);
					jrWeek.setMemPrintWeekend(true);

					break;

				default:

					jrWeek.setMemPrintMidweek(false);
					jrWeek.setMemPrintWeekend(false);
					memorialPrint = false;

					break;
				}

				if (memorialPrint) {

					String memDayText = language.getString(memorialEnumDay.getKey());
					LocalDate memDayLocalDate = weekFrom.plus((dayID - 1), ChronoUnit.DAYS);
					int memDayOfMonth = memDayLocalDate.getDayOfMonth();
					int memDayMonth = memDayLocalDate.getMonthValue();
					EnumMonths memDayMonthEnum = EnumMonths.getByID(memDayMonth);
					String memDayMonthText = language.getString(memDayMonthEnum.getKey());
					int memDayYear = memDayLocalDate.getYear();

					int memDayHours = weekMemorial.getSpInf22();
					int memDayMinute = weekMemorial.getSpInf23();

					String memorialDayFormat = language.getString("jasper.memorial.day.format");

					String memorialDayText = String.format(memorialDayFormat, memDayText, memDayOfMonth,
							memDayMonthText, memDayYear, memDayHours, memDayMinute);

					jrWeek.setMemDay(memorialDayText.toUpperCase());

					String memPlace = weekMemorial.getSpInf24();
					jrWeek.setMemPlace(memPlace);

					String memSong1Min = language.getString("jasper.memorial.song1min");
					jrWeek.setMemSong1Min(memSong1Min);

					String memSong1Format = language.getString("jasper.memorial.song1format");
					String memSong1 = weekMemorial.getSpInf2();
					String memSong1Text = String.format(memSong1Format, memSong1);
					jrWeek.setMemSong1(memSong1Text);

					boolean memPrayPresident = weekMemorial.getSpInf35() == 1;
					jrWeek.setMemPrayPresident(memPrayPresident);

					String memPresidentNameText = language.getString("jasper.memorial.presidenttext");
					jrWeek.setMemPresidentNameText(memPresidentNameText);

					int memPresidentID = weekMemorial.getSpInf4();
					String memPresidentName = "";
					Member member = getMemberFromList(membersList, memPresidentID);
					if (member != null)
						if (extendedName)
							memPresidentName = member.getNameStyle3();
						else
							memPresidentName = member.getNameStyle4();
					jrWeek.setMemPresidentName(memPresidentName);

					String memPray1NameText = language.getString("jasper.memorial.pray1text");
					jrWeek.setMemPray1NameText(memPray1NameText);

					int memPray1ID = weekMemorial.getSpInf5();
					String memPray1Name = "";
					member = getMemberFromList(membersList, memPray1ID);
					if (member != null)
						if (extendedName)
							memPray1Name = member.getNameStyle3();
						else
							memPray1Name = member.getNameStyle4();
					jrWeek.setMemPray1Name(memPray1Name);

					String memTalkMin = weekMemorial.getSpInf8();
					String memTalkMinFormat = language.getString("jasper.memorial.talkmin");
					String memTalkMinText = String.format(memTalkMinFormat, memTalkMin);
					jrWeek.setMemTalkMin(memTalkMinText);

					String memTalkTheme = weekMemorial.getSpInf7();
					jrWeek.setMemTalkTheme(memTalkTheme);

					int memTalkID = weekMemorial.getSpInf6();
					String memTalkName = "";
					member = getMemberFromList(membersList, memTalkID);
					if (member != null)
						if (extendedName)
							memTalkName = member.getNameStyle3();
						else
							memTalkName = member.getNameStyle4();
					jrWeek.setMemTalkName(memTalkName);

					String memPrayBreadText = language.getString("jasper.memorial.praybread");
					jrWeek.setMemPrayBreadText(memPrayBreadText);

					int memPrayBreadID = weekMemorial.getSpInf9();
					String memPrayBreadName = "";
					member = getMemberFromList(membersList, memPrayBreadID);
					if (member != null)
						if (extendedName)
							memPrayBreadName = member.getNameStyle3();
						else
							memPrayBreadName = member.getNameStyle4();
					jrWeek.setMemPrayBreadName(memPrayBreadName);

					String memPrayWineText = language.getString("jasper.memorial.praywine");
					jrWeek.setMemPrayWineText(memPrayWineText);

					int memPrayWineID = weekMemorial.getSpInf10();
					String memPrayWineName = "";
					member = getMemberFromList(membersList, memPrayWineID);
					if (member != null)
						if (extendedName)
							memPrayWineName = member.getNameStyle3();
						else
							memPrayWineName = member.getNameStyle4();
					jrWeek.setMemPrayWineName(memPrayWineName);

					String memSong2Min = language.getString("jasper.memorial.song2min");
					jrWeek.setMemSong2Min(memSong2Min);

					String memSong2Format = language.getString("jasper.memorial.song2format");
					String memSong2 = weekMemorial.getSpInf3();
					String memSong2Text = String.format(memSong2Format, memSong2);
					jrWeek.setMemSong2(memSong2Text);

					String memPray2NameText = language.getString("jasper.memorial.pray2text");
					jrWeek.setMemPray2NameText(memPray2NameText);

					int memPray2ID = weekMemorial.getSpInf36();
					String memPray2Name = "";
					member = getMemberFromList(membersList, memPray2ID);
					if (member != null)
						if (extendedName)
							memPray2Name = member.getNameStyle3();
						else
							memPray2Name = member.getNameStyle4();
					jrWeek.setMemPray2Name(memPray2Name);

					String memFamilyEmblemsText = language.getString("jasper.memorial.familyemblems");
					jrWeek.setMemFamilyEmblemsText(memFamilyEmblemsText);

					int memBreadFamily1ID = weekMemorial.getSpInf11();
					String memBreadFamily1Name = "";
					Family family = getFamilyFromList(familiesList, memBreadFamily1ID);
					if (family != null)
						memBreadFamily1Name = family.getSpInf1Decrypted();

					int memBreadFamily2ID = weekMemorial.getSpInf12();
					String memBreadFamily2Name = "";
					family = getFamilyFromList(familiesList, memBreadFamily2ID);
					if (family != null)
						memBreadFamily2Name = family.getSpInf1Decrypted();

					int memBreadFamily3ID = weekMemorial.getSpInf13();
					String memBreadFamily3Name = "";
					family = getFamilyFromList(familiesList, memBreadFamily3ID);
					if (family != null)
						memBreadFamily3Name = family.getSpInf1Decrypted();

					int memBreadFamily4ID = weekMemorial.getSpInf14();
					String memBreadFamily4Name = "";
					family = getFamilyFromList(familiesList, memBreadFamily4ID);
					if (family != null)
						memBreadFamily4Name = family.getSpInf1Decrypted();

					int memBreadFamily5ID = weekMemorial.getSpInf15();
					String memBreadFamily5Name = "";
					family = getFamilyFromList(familiesList, memBreadFamily5ID);
					if (family != null)
						memBreadFamily5Name = family.getSpInf1Decrypted();

					String memBreadFamilyList = "";
					if (!memBreadFamily1Name.isEmpty())
						memBreadFamilyList += memBreadFamilyList.isEmpty() ? memBreadFamily1Name
								: ", " + memBreadFamily1Name;
					if (!memBreadFamily2Name.isEmpty())
						memBreadFamilyList += memBreadFamilyList.isEmpty() ? memBreadFamily2Name
								: ", " + memBreadFamily2Name;
					if (!memBreadFamily3Name.isEmpty())
						memBreadFamilyList += memBreadFamilyList.isEmpty() ? memBreadFamily3Name
								: ", " + memBreadFamily3Name;
					if (!memBreadFamily4Name.isEmpty())
						memBreadFamilyList += memBreadFamilyList.isEmpty() ? memBreadFamily4Name
								: ", " + memBreadFamily4Name;
					if (!memBreadFamily5Name.isEmpty())
						memBreadFamilyList += memBreadFamilyList.isEmpty() ? memBreadFamily5Name
								: ", " + memBreadFamily5Name;

					int memWineFamily1ID = weekMemorial.getSpInf16();
					String memWineFamily1Name = "";
					family = getFamilyFromList(familiesList, memWineFamily1ID);
					if (family != null)
						memWineFamily1Name = family.getSpInf1Decrypted();

					int memWineFamily2ID = weekMemorial.getSpInf17();
					String memWineFamily2Name = "";
					family = getFamilyFromList(familiesList, memWineFamily2ID);
					if (family != null)
						memWineFamily2Name = family.getSpInf1Decrypted();

					int memWineFamily3ID = weekMemorial.getSpInf18();
					String memWineFamily3Name = "";
					family = getFamilyFromList(familiesList, memWineFamily3ID);
					if (family != null)
						memWineFamily3Name = family.getSpInf1Decrypted();

					int memWineFamily4ID = weekMemorial.getSpInf19();
					String memWineFamily4Name = "";
					family = getFamilyFromList(familiesList, memWineFamily4ID);
					if (family != null)
						memWineFamily4Name = family.getSpInf1Decrypted();

					int memWineFamily5ID = weekMemorial.getSpInf20();
					String memWineFamily5Name = "";
					family = getFamilyFromList(familiesList, memWineFamily5ID);
					if (family != null)
						memWineFamily5Name = family.getSpInf1Decrypted();

					String memWineFamilyList = "";
					if (!memWineFamily1Name.isEmpty())
						memWineFamilyList += memWineFamilyList.isEmpty() ? memWineFamily1Name
								: ", " + memWineFamily1Name;
					if (!memWineFamily2Name.isEmpty())
						memWineFamilyList += memWineFamilyList.isEmpty() ? memWineFamily2Name
								: ", " + memWineFamily2Name;
					if (!memWineFamily3Name.isEmpty())
						memWineFamilyList += memWineFamilyList.isEmpty() ? memWineFamily3Name
								: ", " + memWineFamily3Name;
					if (!memWineFamily4Name.isEmpty())
						memWineFamilyList += memWineFamilyList.isEmpty() ? memWineFamily4Name
								: ", " + memWineFamily4Name;
					if (!memWineFamily5Name.isEmpty())
						memWineFamilyList += memWineFamilyList.isEmpty() ? memWineFamily5Name
								: ", " + memWineFamily5Name;

					String familyEmblemsText = "";
					if (!(memBreadFamilyList.isEmpty() && memWineFamilyList.isEmpty())) {
						String familyEmblemsTextFormat = language.getString("jasper.memorial.familyemblemstext");
						familyEmblemsText = String.format(familyEmblemsTextFormat, memBreadFamilyList,
								memWineFamilyList);
						jrWeek.setMemFamilyEmblemsList(familyEmblemsText);
					}

					String memMemberEmblemsText = language.getString("jasper.memorial.memberemblems");
					jrWeek.setMemMemberEmblemsText(memMemberEmblemsText);

					int memEmblemsMember1ID = weekMemorial.getSpInf25();
					String memEmblemsMember1Name = "";
					member = getMemberFromList(membersList, memEmblemsMember1ID);
					if (member != null)
						if (extendedName)
							memEmblemsMember1Name = member.getNameStyle3();
						else
							memEmblemsMember1Name = member.getNameStyle4();

					int memEmblemsMember2ID = weekMemorial.getSpInf26();
					String memEmblemsMember2Name = "";
					member = getMemberFromList(membersList, memEmblemsMember2ID);
					if (member != null)
						if (extendedName)
							memEmblemsMember2Name = member.getNameStyle3();
						else
							memEmblemsMember2Name = member.getNameStyle4();

					int memEmblemsMember3ID = weekMemorial.getSpInf27();
					String memEmblemsMember3Name = "";
					member = getMemberFromList(membersList, memEmblemsMember3ID);
					if (member != null)
						if (extendedName)
							memEmblemsMember3Name = member.getNameStyle3();
						else
							memEmblemsMember3Name = member.getNameStyle4();

					int memEmblemsMember4ID = weekMemorial.getSpInf28();
					String memEmblemsMember4Name = "";
					member = getMemberFromList(membersList, memEmblemsMember4ID);
					if (member != null)
						if (extendedName)
							memEmblemsMember4Name = member.getNameStyle3();
						else
							memEmblemsMember4Name = member.getNameStyle4();

					int memEmblemsMember5ID = weekMemorial.getSpInf29();
					String memEmblemsMember5Name = "";
					member = getMemberFromList(membersList, memEmblemsMember5ID);
					if (member != null)
						if (extendedName)
							memEmblemsMember5Name = member.getNameStyle3();
						else
							memEmblemsMember5Name = member.getNameStyle4();

					int memEmblemsMember6ID = weekMemorial.getSpInf30();
					String memEmblemsMember6Name = "";
					member = getMemberFromList(membersList, memEmblemsMember6ID);
					if (member != null)
						if (extendedName)
							memEmblemsMember6Name = member.getNameStyle3();
						else
							memEmblemsMember6Name = member.getNameStyle4();

					int memEmblemsMember7ID = weekMemorial.getSpInf31();
					String memEmblemsMember7Name = "";
					member = getMemberFromList(membersList, memEmblemsMember7ID);
					if (member != null)
						if (extendedName)
							memEmblemsMember7Name = member.getNameStyle3();
						else
							memEmblemsMember7Name = member.getNameStyle4();

					int memEmblemsMember8ID = weekMemorial.getSpInf32();
					String memEmblemsMember8Name = "";
					member = getMemberFromList(membersList, memEmblemsMember8ID);
					if (member != null)
						if (extendedName)
							memEmblemsMember8Name = member.getNameStyle3();
						else
							memEmblemsMember8Name = member.getNameStyle4();

					int memEmblemsMember9ID = weekMemorial.getSpInf33();
					String memEmblemsMember9Name = "";
					member = getMemberFromList(membersList, memEmblemsMember9ID);
					if (member != null)
						if (extendedName)
							memEmblemsMember9Name = member.getNameStyle3();
						else
							memEmblemsMember9Name = member.getNameStyle4();

					int memEmblemsMember10ID = weekMemorial.getSpInf34();
					String memEmblemsMember10Name = "";
					member = getMemberFromList(membersList, memEmblemsMember10ID);
					if (member != null)
						if (extendedName)
							memEmblemsMember10Name = member.getNameStyle3();
						else
							memEmblemsMember10Name = member.getNameStyle4();

					String memEmblemsMemberList = "";
					if (!memEmblemsMember1Name.isEmpty())
						memEmblemsMemberList += memEmblemsMemberList.isEmpty() ? memEmblemsMember1Name
								: ", " + memEmblemsMember1Name;
					if (!memEmblemsMember2Name.isEmpty())
						memEmblemsMemberList += memEmblemsMemberList.isEmpty() ? memEmblemsMember2Name
								: ", " + memEmblemsMember2Name;
					if (!memEmblemsMember3Name.isEmpty())
						memEmblemsMemberList += memEmblemsMemberList.isEmpty() ? memEmblemsMember3Name
								: ", " + memEmblemsMember3Name;
					if (!memEmblemsMember4Name.isEmpty())
						memEmblemsMemberList += memEmblemsMemberList.isEmpty() ? memEmblemsMember4Name
								: ", " + memEmblemsMember4Name;
					if (!memEmblemsMember5Name.isEmpty())
						memEmblemsMemberList += memEmblemsMemberList.isEmpty() ? memEmblemsMember5Name
								: ", " + memEmblemsMember5Name;
					if (!memEmblemsMember6Name.isEmpty())
						memEmblemsMemberList += memEmblemsMemberList.isEmpty() ? memEmblemsMember6Name
								: ", " + memEmblemsMember6Name;
					if (!memEmblemsMember7Name.isEmpty())
						memEmblemsMemberList += memEmblemsMemberList.isEmpty() ? memEmblemsMember7Name
								: ", " + memEmblemsMember7Name;
					if (!memEmblemsMember8Name.isEmpty())
						memEmblemsMemberList += memEmblemsMemberList.isEmpty() ? memEmblemsMember8Name
								: ", " + memEmblemsMember8Name;
					if (!memEmblemsMember9Name.isEmpty())
						memEmblemsMemberList += memEmblemsMemberList.isEmpty() ? memEmblemsMember9Name
								: ", " + memEmblemsMember9Name;
					if (!memEmblemsMember10Name.isEmpty())
						memEmblemsMemberList += memEmblemsMemberList.isEmpty() ? memEmblemsMember10Name
								: ", " + memEmblemsMember10Name;

					jrWeek.setMemMemberEmblemsList(memEmblemsMemberList);

					String memPlaceText = language.getString("jasper.memorial.place");
					jrWeek.setMemPlaceText(memPlaceText);
				}
			}
		}

		// Circuit overseer
		if (weekTypeID == 4) {

			WeekOverseer weekOverseer = week.getWeekOverseer();
			if (weekOverseer != null) {
				String overseerName = weekOverseer.getSpInf3();
				String overseerShortName = weekOverseer.getSpInf4();
				String overseerSurname = weekOverseer.getSpInf5();
				String overseerSongTalk1 = weekOverseer.getSpInf8();
				String overseerMinTalk1 = weekOverseer.getSpInf9();
				String overseerThemeTalk1 = weekOverseer.getSpInf10();

				String overseerSongTalk2 = weekOverseer.getSpInf11();
				String overseerMinTalk2 = weekOverseer.getSpInf12();
				String overseerThemeTalk2 = weekOverseer.getSpInf13();

				String overseerMinTalk3 = weekOverseer.getSpInf14();
				String overseerThemeTalk3 = weekOverseer.getSpInf15();

				jrWeek.setOverseerHeader(language.getString("TEXT0131").toUpperCase());

				jrWeek.setOverseerMinTalk1(
						String.format(language.getString("jasper.layout.meeting.min"), overseerMinTalk1));
				jrWeek.setOverseerMinTalk2(
						String.format(language.getString("jasper.layout.meeting.min"), overseerMinTalk2));
				jrWeek.setOverseerMinTalk3(
						String.format(language.getString("jasper.layout.meeting.min"), overseerMinTalk3));

				jrWeek.setOverseerSongTalk1(
						String.format(language.getString("jasper.layout.meeting.song1"), overseerSongTalk1));
				jrWeek.setOverseerSongTalk2(
						String.format(language.getString("jasper.layout.meeting.song1"), overseerSongTalk2));

				jrWeek.setOverseerThemeTalk1(overseerThemeTalk1);
				jrWeek.setOverseerThemeTalk2(overseerThemeTalk2);
				jrWeek.setOverseerThemeTalk3(overseerThemeTalk3);

				if (extendedName)
					jrWeek.setOverseerName(overseerSurname + " " + overseerName);
				else
					jrWeek.setOverseerName(overseerSurname + " " + overseerShortName);
			}
		}

		jrWeek.setWeekHeader(checkMidweekHeader(week, weekTypeID, language, complete));

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

		// Add reader to Congregation Study Bible
		if (weekTypeID == 1) {

			if (!jrWeek.getChristiansBibleStudyReaderName().isEmpty()) {

				jrWeek.setChristiansBibleStudyText(jrWeek.getChristiansBibleStudyText().concat("<br><i>")
						.concat(jrWeek.getChristiansBibleStudyReaderText()).concat("</i>"));

				jrWeek.setChristiansBibleStudyName(jrWeek.getChristiansBibleStudyName().concat("<br><i>")
						.concat(jrWeek.getChristiansBibleStudyReaderName()).concat("</i>"));
			}
		}

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

		jrWeek.setWeekHeader2(checkWeekendHeader(week, weekTypeID, language));
		jrWeek.setPublicTalkHeader(language.getString("jasper.layout.meeting.weekend.publictalk").toUpperCase());

		jrWeek.setPublicTalkMinSong1(String.format(language.getString("jasper.layout.meeting.min"), "5"));
		jrWeek.setPublicTalkSong1(String.format(language.getString("jasper.layout.meeting.song1"), week.getSpInf31()));

		// Solo preghiera
		if (week.getSpInf41() == 1)
			jrWeek.setPublicTalkPresidentText(language.getString("jasper.layout.meeting.weekend.presidentonlypray"));
		else
			jrWeek.setPublicTalkPresidentText(language.getString("jasper.layout.meeting.weekend.president"));

		member = getMemberFromList(membersList, week.getSpInf30());
		if (member != null)
			if (extendedName)
				jrWeek.setPublicTalkPresident(member.getNameStyle3());
			else
				jrWeek.setPublicTalkPresident(member.getNameStyle4());

		jrWeek.setPublicTalkMin(String.format(language.getString("jasper.layout.meeting.min"), week.getSpInf42()));
		jrWeek.setPublicTalkTheme(week.getSpInf32());
		jrWeek.setPublicTalkName(week.getSpInf33());
		jrWeek.setPublicTalkCongregation(week.getSpInf34());

		jrWeek.setWatchtowerStudyHeader(
				language.getString("jasper.layout.meeting.weekend.watchtowerstudy").toUpperCase());

		jrWeek.setWatchtowerStudyMinSong2(String.format(language.getString("jasper.layout.meeting.min"), "5"));
		jrWeek.setWatchtowerStudySong2(
				String.format(language.getString("jasper.layout.meeting.song1"), week.getSpInf35()));

		// if (spInf2 == 4)
		jrWeek.setWatchtowerStudyMin(String.format(language.getString("jasper.layout.meeting.min"), week.getSpInf43()));
		// else
		// jrWeek.setWatchtowerStudyMin(String.format(language.getString("jasper.layout.meeting.min"),
		// "60"));

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

		// Add reader to Watchtower Study
		if (weekTypeID == 1) {

			if (!jrWeek.getWatchtowerStudyReaderName().isEmpty()) {

				jrWeek.setWatchtowerStudyTheme(jrWeek.getWatchtowerStudyTheme().concat("<br><i>")
						.concat(jrWeek.getWatchtowerStudyReaderText()).concat("</i>"));

				jrWeek.setWatchtowerStudyName(jrWeek.getWatchtowerStudyName().concat("<br><i>")
						.concat(jrWeek.getWatchtowerStudyReaderName()).concat("</i>"));
			}
		}

		jrWeek.setWatchtowerStudyMinSong3(String.format(language.getString("jasper.layout.meeting.min"), "5"));
		jrWeek.setWatchtowerStudySong3(
				String.format(language.getString("jasper.layout.meeting.song1"), week.getSpInf39()));

		// Places
		if (week.getSpInf51() == 1)
			jrWeek.setPlace1(week.getSpInf50());
		if (week.getSpInf53() == 1)
			jrWeek.setPlace2(week.getSpInf52());

		return jrWeek;
	}

	private static Family getFamilyFromList(ObservableList<Family> familiesList, int id) {
		for (Family f : familiesList)
			if (f.getSpFamID() == id)
				return f;
		return null;
	}

	private static Member getMemberFromList(ObservableList<Member> membersList, int id) {
		for (Member member : membersList)
			if (member.getSpMemberID() == id)
				return member;
		return null;
	}

	private static String checkMidweekHeader(Week week, int spInf2, Language language, boolean complete) {

		if (spInf2 == 1 || spInf2 == 4) {

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

		return (spInf2 == 1 || spInf2 == 4)
				? String.format(language.getString("jasper.layout.meeting.weekend"), week.getSpInf6())
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

	public String getOverseerSongTalk2() {
		return overseerSongTalk2;
	}

	public void setOverseerSongTalk2(String overseerSongTalk2) {
		this.overseerSongTalk2 = overseerSongTalk2;
	}

	public String getOverseerMinTalk2() {
		return overseerMinTalk2;
	}

	public void setOverseerMinTalk2(String overseerMinTalk2) {
		this.overseerMinTalk2 = overseerMinTalk2;
	}

	public String getOverseerThemeTalk2() {
		return overseerThemeTalk2;
	}

	public void setOverseerThemeTalk2(String overseerThemeTalk2) {
		this.overseerThemeTalk2 = overseerThemeTalk2;
	}

	public String getOverseerMinTalk3() {
		return overseerMinTalk3;
	}

	public void setOverseerMinTalk3(String overseerMinTalk3) {
		this.overseerMinTalk3 = overseerMinTalk3;
	}

	public String getOverseerThemeTalk3() {
		return overseerThemeTalk3;
	}

	public void setOverseerThemeTalk3(String overseerThemeTalk3) {
		this.overseerThemeTalk3 = overseerThemeTalk3;
	}

	public String getPlace1() {
		return place1;
	}

	public void setPlace1(String place1) {
		this.place1 = place1;
	}

	public String getPlace2() {
		return place2;
	}

	public void setPlace2(String place2) {
		this.place2 = place2;
	}

	public boolean isConvPrintOneDay() {
		return convPrintOneDay;
	}

	public boolean isConvPrintThreeDay() {
		return convPrintThreeDay;
	}

	public void setConvPrintOneDay(boolean convPrintOneDay) {
		this.convPrintOneDay = convPrintOneDay;
	}

	public void setConvPrintThreeDay(boolean convPrintThreeDay) {
		this.convPrintThreeDay = convPrintThreeDay;
	}

	public String getConvType() {
		return convType;
	}

	public void setConvType(String convType) {
		this.convType = convType;
	}

	public String getConvTheme() {
		return convTheme;
	}

	public void setConvTheme(String convTheme) {
		this.convTheme = convTheme;
	}

	public String getConvDay1() {
		return convDay1;
	}

	public String getConvDay2() {
		return convDay2;
	}

	public String getConvDay3() {
		return convDay3;
	}

	public void setConvDay1(String convDay1) {
		this.convDay1 = convDay1;
	}

	public void setConvDay2(String convDay2) {
		this.convDay2 = convDay2;
	}

	public void setConvDay3(String convDay3) {
		this.convDay3 = convDay3;
	}

	public String getConvScripture1() {
		return convScripture1;
	}

	public String getConvScripture2() {
		return convScripture2;
	}

	public String getConvScripture3() {
		return convScripture3;
	}

	public void setConvScripture1(String convScripture1) {
		this.convScripture1 = convScripture1;
	}

	public void setConvScripture2(String convScripture2) {
		this.convScripture2 = convScripture2;
	}

	public void setConvScripture3(String convScripture3) {
		this.convScripture3 = convScripture3;
	}

	public String getConvAddr() {
		return convAddr;
	}

	public void setConvAddr(String convAddr) {
		this.convAddr = convAddr;
	}

	public String getConvQHeader() {
		return convQHeader;
	}

	public String getConvQ1() {
		return convQ1;
	}

	public String getConvQ2() {
		return convQ2;
	}

	public String getConvQ3() {
		return convQ3;
	}

	public String getConvQ4() {
		return convQ4;
	}

	public String getConvQ5() {
		return convQ5;
	}

	public String getConvQ6() {
		return convQ6;
	}

	public String getConvQ7() {
		return convQ7;
	}

	public String getConvQ8() {
		return convQ8;
	}

	public String getConvQ9() {
		return convQ9;
	}

	public String getConvQ10() {
		return convQ10;
	}

	public void setConvQHeader(String convQHeader) {
		this.convQHeader = convQHeader;
	}

	public void setConvQ1(String convQ1) {
		this.convQ1 = convQ1;
	}

	public void setConvQ2(String convQ2) {
		this.convQ2 = convQ2;
	}

	public void setConvQ3(String convQ3) {
		this.convQ3 = convQ3;
	}

	public void setConvQ4(String convQ4) {
		this.convQ4 = convQ4;
	}

	public void setConvQ5(String convQ5) {
		this.convQ5 = convQ5;
	}

	public void setConvQ6(String convQ6) {
		this.convQ6 = convQ6;
	}

	public void setConvQ7(String convQ7) {
		this.convQ7 = convQ7;
	}

	public void setConvQ8(String convQ8) {
		this.convQ8 = convQ8;
	}

	public void setConvQ9(String convQ9) {
		this.convQ9 = convQ9;
	}

	public void setConvQ10(String convQ10) {
		this.convQ10 = convQ10;
	}

	public boolean isMemPrintMidweek() {
		return memPrintMidweek;
	}

	public boolean isMemPrintWeekend() {
		return memPrintWeekend;
	}

	public void setMemPrintMidweek(boolean memPrintMidweek) {
		this.memPrintMidweek = memPrintMidweek;
	}

	public void setMemPrintWeekend(boolean memPrintWeekend) {
		this.memPrintWeekend = memPrintWeekend;
	}

	public String getMemDay() {
		return memDay;
	}

	public void setMemDay(String memDay) {
		this.memDay = memDay;
	}

	public String getMemPlace() {
		return memPlace;
	}

	public void setMemPlace(String memPlace) {
		this.memPlace = memPlace;
	}

	public String getMemSong1Min() {
		return memSong1Min;
	}

	public void setMemSong1Min(String memSong1Min) {
		this.memSong1Min = memSong1Min;
	}

	public String getMemSong1() {
		return memSong1;
	}

	public void setMemSong1(String memSong1) {
		this.memSong1 = memSong1;
	}

	public boolean isMemPrayPresident() {
		return memPrayPresident;
	}

	public String getMemPresidentName() {
		return memPresidentName;
	}

	public String getMemPray1Name() {
		return memPray1Name;
	}

	public void setMemPrayPresident(boolean memPrayPresident) {
		this.memPrayPresident = memPrayPresident;
	}

	public void setMemPresidentName(String memPresidentName) {
		this.memPresidentName = memPresidentName;
	}

	public void setMemPray1Name(String memPray1Name) {
		this.memPray1Name = memPray1Name;
	}

	public String getMemPresidentNameText() {
		return memPresidentNameText;
	}

	public String getMemPray1NameText() {
		return memPray1NameText;
	}

	public void setMemPresidentNameText(String memPresidentNameText) {
		this.memPresidentNameText = memPresidentNameText;
	}

	public void setMemPray1NameText(String memPray1NameText) {
		this.memPray1NameText = memPray1NameText;
	}

	public String getMemTalkMin() {
		return memTalkMin;
	}

	public String getMemTalkTheme() {
		return memTalkTheme;
	}

	public String getMemTalkName() {
		return memTalkName;
	}

	public void setMemTalkMin(String memTalkMin) {
		this.memTalkMin = memTalkMin;
	}

	public void setMemTalkTheme(String memTalkTheme) {
		this.memTalkTheme = memTalkTheme;
	}

	public void setMemTalkName(String memTalkName) {
		this.memTalkName = memTalkName;
	}

	public String getMemPrayBreadText() {
		return memPrayBreadText;
	}

	public String getMemPrayWineText() {
		return memPrayWineText;
	}

	public void setMemPrayBreadText(String memPrayBreadText) {
		this.memPrayBreadText = memPrayBreadText;
	}

	public void setMemPrayWineText(String memPrayWineText) {
		this.memPrayWineText = memPrayWineText;
	}

	public String getMemPrayBreadName() {
		return memPrayBreadName;
	}

	public String getMemPrayWineName() {
		return memPrayWineName;
	}

	public void setMemPrayBreadName(String memPrayBreadName) {
		this.memPrayBreadName = memPrayBreadName;
	}

	public void setMemPrayWineName(String memPrayWineName) {
		this.memPrayWineName = memPrayWineName;
	}

	public String getMemSong2Min() {
		return memSong2Min;
	}

	public String getMemSong2() {
		return memSong2;
	}

	public String getMemPray2Name() {
		return memPray2Name;
	}

	public String getMemPray2NameText() {
		return memPray2NameText;
	}

	public void setMemSong2Min(String memSong2Min) {
		this.memSong2Min = memSong2Min;
	}

	public void setMemSong2(String memSong2) {
		this.memSong2 = memSong2;
	}

	public void setMemPray2Name(String memPray2Name) {
		this.memPray2Name = memPray2Name;
	}

	public void setMemPray2NameText(String memPray2NameText) {
		this.memPray2NameText = memPray2NameText;
	}

	public String getMemFamilyEmblemsText() {
		return memFamilyEmblemsText;
	}

	public void setMemFamilyEmblemsText(String memFamilyEmblemsText) {
		this.memFamilyEmblemsText = memFamilyEmblemsText;
	}

	public String getMemFamilyEmblemsList() {
		return memFamilyEmblemsList;
	}

	public void setMemFamilyEmblemsList(String memFamilyEmblemsList) {
		this.memFamilyEmblemsList = memFamilyEmblemsList;
	}

	public String getMemMemberEmblemsText() {
		return memMemberEmblemsText;
	}

	public String getMemMemberEmblemsList() {
		return memMemberEmblemsList;
	}

	public void setMemMemberEmblemsText(String memMemberEmblemsText) {
		this.memMemberEmblemsText = memMemberEmblemsText;
	}

	public void setMemMemberEmblemsList(String memMemberEmblemsList) {
		this.memMemberEmblemsList = memMemberEmblemsList;
	}

	public String getMemPlaceText() {
		return memPlaceText;
	}

	public void setMemPlaceText(String memPlaceText) {
		this.memPlaceText = memPlaceText;
	}

}
