package com.sm.net.sp.jasper.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.sm.net.sp.model.EnumConventionType;
import com.sm.net.sp.model.WeekConvention;
import com.sm.net.sp.utils.DateUtils;
import com.sm.net.sp.view.SupportPlannerView;

public class InfoTableEvent {

	private LocalDate eventDate;
	private String eventDateText;
	private String eventType;

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
				infoTableEvent.setEventDateText(String.format("%s<br>- %s", firstDay, lastDay));

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
