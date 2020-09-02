package com.sm.net.sp.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CommonUtils {

	public static BigDecimal calculateAge(LocalDate startDate) {

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");

		LocalDate now = LocalDate.now();
		BigDecimal nowBd = new BigDecimal(dtf.format(now));

		BigDecimal startDateBd = new BigDecimal(dtf.format(startDate));

		BigDecimal age = nowBd.subtract(startDateBd);
		age = age.divide(BigDecimal.valueOf(10000), 2, RoundingMode.HALF_DOWN);
		BigDecimal decimal = age.remainder(BigDecimal.ONE);
		decimal = decimal.multiply(BigDecimal.TEN);
		age = BigDecimal.valueOf(age.intValue()).add(decimal);

		return age;
	}

}
