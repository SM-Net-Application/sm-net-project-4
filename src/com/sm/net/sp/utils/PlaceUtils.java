package com.sm.net.sp.utils;

import com.sm.net.sp.model.Place;

public class PlaceUtils {

	public static String toText(Place found) {

		String descr = found.getDescr().get();
		String street = found.getStreet().get();
		String num = found.getNum().get();
		String postCode = found.getPostCode().get();
		String city = found.getCity().get();
		String country = found.getCountry().get();

		String format = "%s, %s %s, %s %s (%s)";
		String addr = String.format(format, descr, street, num, postCode, city, country);

		return addr;
	}

}
