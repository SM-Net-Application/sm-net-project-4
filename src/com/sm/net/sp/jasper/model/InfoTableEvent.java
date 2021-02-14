package com.sm.net.sp.jasper.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.sm.net.sp.model.EnumConventionType;
import com.sm.net.sp.model.Week;
import com.sm.net.sp.model.WeekConvention;
import com.sm.net.sp.model.WeekMemorial;
import com.sm.net.sp.model.WeekOverseer;
import com.sm.net.sp.utils.DateUtils;
import com.sm.net.sp.view.SupportPlannerView;
import com.sm.net.util.Crypt;

public class InfoTableEvent {

	private LocalDate eventDate;
	private String eventDateText;
	private String eventType;

	public static InfoTableEvent newInstance(SupportPlannerView application, LocalDate now, int nowWeekNr, Week week) {

		int spInf1 = week.getSpInf1();
		if (!(spInf1 < nowWeekNr)) {

			if (week.getSpInf65() == 1) {

				InfoTableEvent infoTableEvent = new InfoTableEvent();

				LocalDate firstDayOfWeekEvent = DateUtils.getLocalDateByWeekKey(spInf1);

				int day = week.getSpInf47();
				LocalDate eventDay = firstDayOfWeekEvent.plusDays(day - 1);
				infoTableEvent.setEventDate(eventDay);

				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
				infoTableEvent.setEventDateText(dtf.format(eventDay));

				String typeFormat = application.getSettings().getLanguage()
						.getString("infotable.print.specialtalk.typeformat");
				String typeText = application.getSettings().getLanguage().getString("infotable.print.specialtalk.type");

				String theme = Crypt.decrypt(week.getSpInf32(), application.getSettings().getDatabaseSecretKey());

				String eventType = String.format(typeFormat, typeText, theme);
				infoTableEvent.setEventType(eventType);

				return infoTableEvent;
			}
		}
		return null;
	}

	public static InfoTableEvent newInstance(SupportPlannerView application, LocalDate now, int nowWeekNr,
			WeekOverseer weekOverseer) {

		int spInf1 = weekOverseer.getSpInf1();
		if (!(spInf1 < nowWeekNr)) {

			LocalDate firstDayOfWeekEvent = DateUtils.getLocalDateByWeekKey(spInf1);
			LocalDate lastDayOfWeekEvent = firstDayOfWeekEvent.plusDays(6);

			InfoTableEvent infoTableEvent = new InfoTableEvent();

			String typeFormat = application.getSettings().getLanguage()
					.getString("infotable.print.overseer.typeformat");
			String typeText = application.getSettings().getLanguage().getString("infotable.print.overseer.type");

			String eventType = String.format(typeFormat, typeText);

			String overseer = weekOverseer.getOverseer().replace(",", "");

			if (!overseer.isEmpty()) {

				if (!eventType.isEmpty())
					eventType += "<br>";

				eventType += String.format(
						application.getSettings().getLanguage().getString("infotable.print.overseer.nameformat"),
						overseer);
			}

			String overseerWife = weekOverseer.getOverseerWife().replace(",", "");

			if (!overseerWife.isEmpty()) {

				if (!eventType.isEmpty())
					eventType += ", ";

				eventType += String.format(
						application.getSettings().getLanguage().getString("infotable.print.overseer.nameformat"),
						overseerWife);
			}

			infoTableEvent.setEventType(eventType);

			infoTableEvent.setEventDate(firstDayOfWeekEvent);

			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
			String firstDay = dtf.format(firstDayOfWeekEvent);
			String lastDay = dtf.format(lastDayOfWeekEvent);

			String dateFormat = application.getSettings().getLanguage()
					.getString("infotable.print.overseer.dateformat");
			infoTableEvent.setEventDateText(String.format(dateFormat, firstDay, lastDay));

			return infoTableEvent;

		}

		return null;
	}

	public static InfoTableEvent newInstance(SupportPlannerView application, LocalDate now, int nowWeekNr,
			WeekMemorial weekMemorial) {

		int spInf1 = weekMemorial.getSpInf1();
		if (!(spInf1 < nowWeekNr)) {

			LocalDate firstDayOfWeekEvent = DateUtils.getLocalDateByWeekKey(spInf1);

			InfoTableEvent infoTableEvent = new InfoTableEvent();

			String typeFormat = application.getSettings().getLanguage()
					.getString("infotable.print.memorial.typeformat");
			String typeText = application.getSettings().getLanguage().getString("infotable.print.memorial.type");

			String eventType = String.format(typeFormat, typeText);

			String place = weekMemorial.getSpInf24();

			if (!place.isEmpty()) {

				if (!eventType.isEmpty())
					eventType += "<br>";

				eventType += String.format(
						application.getSettings().getLanguage().getString("infotable.print.memorial.placeformat"),
						place);
			}

			infoTableEvent.setEventType(eventType);

			int day = weekMemorial.getSpInf21();
			LocalDate eventDay = firstDayOfWeekEvent.plusDays(day - 1);
			infoTableEvent.setEventDate(eventDay);

			int hour = weekMemorial.getSpInf22();
			int minute = weekMemorial.getSpInf23();

			LocalDateTime eventDayTime = LocalDateTime.of(eventDay, LocalTime.of(hour, minute));

			DateTimeFormatter dtfDate = DateTimeFormatter.ofPattern("dd.MM.yyyy");
			DateTimeFormatter dtfTime = DateTimeFormatter.ofPattern("HH:mm");

			String dateFormat = application.getSettings().getLanguage()
					.getString("infotable.print.memorial.dateformat");

			infoTableEvent.setEventDateText(
					String.format(dateFormat, dtfDate.format(eventDayTime), dtfTime.format(eventDayTime)));

			return infoTableEvent;

		}

		return null;
	}

	public static InfoTableEvent newInstance(SupportPlannerView application, LocalDate now, int nowWeekNr,
			WeekConvention weekConvention) {

		int spInf31 = weekConvention.getSpInf31();
		if (!(spInf31 < nowWeekNr)) {

			LocalDate eventDay = DateUtils.getLocalDateByWeekKey(spInf31);

			InfoTableEvent infoTableEvent = new InfoTableEvent();

			int typeID = weekConvention.getSpInf1();
			EnumConventionType conventionType = EnumConventionType.getByID(typeID);

			String theme = weekConvention.getSpInf3();
			String place = weekConvention.getSpInf32();

			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");

			String type = "";
			String eventType = "";

			switch (conventionType) {
			case REGIONALCONVENTION:

				LocalDate conventionFirstDay = eventDay.plusDays(4);
				LocalDate conventionLastDay = eventDay.plusDays(6);

				String firstDay = dtf.format(conventionFirstDay);
				String lastDay = dtf.format(conventionLastDay);

				infoTableEvent.setEventDate(conventionFirstDay);
				String dateFormat = application.getSettings().getLanguage()
						.getString("infotable.print.convention.dateformat");
				infoTableEvent.setEventDateText(String.format(dateFormat, firstDay, lastDay));

				type = application.getSettings().getLanguage().getString(conventionType.getKey());

				if (!type.isEmpty()) {

					eventType += String.format(
							application.getSettings().getLanguage().getString("infotable.print.convention.typeformat"),
							type);
				}

				if (!theme.isEmpty()) {

					if (!eventType.isEmpty())
						eventType += "<br>";

					eventType += String.format(
							application.getSettings().getLanguage().getString("infotable.print.convention.themeformat"),
							theme);
				}

				if (!place.isEmpty()) {

					if (!eventType.isEmpty())
						eventType += "<br>";

					eventType += String.format(
							application.getSettings().getLanguage().getString("infotable.print.convention.placeformat"),
							place);
				}

				infoTableEvent.setEventType(eventType);

				return infoTableEvent;

			case CIRCUITASSEMBLY_BRANCH:
			case CIRCUITASSEMBLY_OVERSEER:

				type = application.getSettings().getLanguage().getString(conventionType.getKey());

				int spInf7 = weekConvention.getSpInf7();
				LocalDate conventionDay = eventDay.plusDays(spInf7 - 1);

				infoTableEvent.setEventDate(conventionDay);
				infoTableEvent.setEventDateText(dtf.format(conventionDay));

				if (!type.isEmpty()) {

					eventType += String.format(
							application.getSettings().getLanguage().getString("infotable.print.convention.typeformat"),
							type);
				}

				if (!theme.isEmpty()) {

					if (!eventType.isEmpty())
						eventType += "<br>";

					eventType += String.format(
							application.getSettings().getLanguage().getString("infotable.print.convention.themeformat"),
							theme);
				}

				if (!place.isEmpty()) {

					if (!eventType.isEmpty())
						eventType += "<br>";

					eventType += String.format(
							application.getSettings().getLanguage().getString("infotable.print.convention.placeformat"),
							place);
				}

				infoTableEvent.setEventType(eventType);

				return infoTableEvent;

			default:
				break;
			}

		}

		return null;
	}

	public String getEventDateText() {
		return eventDateText;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventDateText(String eventDateText) {
		this.eventDateText = eventDateText;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public LocalDate getEventDate() {
		return eventDate;
	}

	public void setEventDate(LocalDate eventDate) {
		this.eventDate = eventDate;
	}
}
