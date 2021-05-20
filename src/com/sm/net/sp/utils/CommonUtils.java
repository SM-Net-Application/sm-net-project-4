package com.sm.net.sp.utils;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class CommonUtils {

	public static BigDecimal calculateAge(LocalDate startDate) {

		LocalDate now = LocalDate.now();

		long daysBetween = ChronoUnit.DAYS.between(startDate, now);
		BigDecimal days = new BigDecimal(daysBetween);
		
		BigDecimal daysYear = new BigDecimal(365.25);
		BigDecimal age = days.divide(daysYear, 2, RoundingMode.HALF_UP);
		
//		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");

//		BigDecimal nowBd = new BigDecimal(dtf.format(now));
//
//		BigDecimal startDateBd = new BigDecimal(dtf.format(startDate));
//
//		BigDecimal age = nowBd.subtract(startDateBd);
//		age = age.divide(BigDecimal.valueOf(10000), 2, RoundingMode.HALF_DOWN);
//		BigDecimal decimal = age.remainder(BigDecimal.ONE);
//		decimal = decimal.multiply(BigDecimal.TEN);
//		age = BigDecimal.valueOf(age.intValue()).add(decimal);

		return age;
	}
	
	public static void open(File file) throws IOException {

        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            if (file.exists()) desktop.open(file);
        }
    }

}
