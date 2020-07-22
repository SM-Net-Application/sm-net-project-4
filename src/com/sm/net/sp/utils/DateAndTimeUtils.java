package com.sm.net.sp.utils;

import java.time.LocalDate;

import com.sm.net.sp.model.DateAndTime;

import javafx.collections.ObservableList;

public class DateAndTimeUtils {

	public static DateAndTime check(ObservableList<DateAndTime> dateAndTimeList, LocalDate from) {

		DateAndTime found = null;

		for (DateAndTime dateAndTime : dateAndTimeList) {

			LocalDate date = dateAndTime.getDate().get();

			if (!(date.compareTo(from) > 0))
				if (found != null) {
					if (date.compareTo(found.getDate().get()) > 0)
						found = dateAndTime;
				} else
					found = dateAndTime;
		}

		return found;
	}

}
