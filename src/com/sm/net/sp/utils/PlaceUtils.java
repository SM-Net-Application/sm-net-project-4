package com.sm.net.sp.utils;

import com.sm.net.sp.model.Place;

public class PlaceUtils {

	public static String toText(Place found) {

		String descr = found.getDescr().get();
		String street = found.getStreet().get();
		String num = found.getNum().get();
		String postCode = found.getPostCode().get();
		String city = found.getCity().get();
		String county = found.getCounty().get();
		String country = found.getCountry().get();
		String coord = found.getCoord().get();

		String format = "%s, %s %s, %s %s (%s), %s, %s";
		String addr = String.format(format, descr, street, num, postCode, city, county, country, coord);

		return addr;
	}

	public static String toText(Place found, String pattern) {

		String descr = found.getDescr().get();
		String street = found.getStreet().get();
		String num = found.getNum().get();
		String postCode = found.getPostCode().get();
		String city = found.getCity().get();
		String county = found.getCounty().get();
		String country = found.getCountry().get();
		String coord = found.getCoord().get();

		pattern = pattern.replace("<d>", descr);
		pattern = pattern.replace("<s>", street);
		pattern = pattern.replace("<n>", num);
		pattern = pattern.replace("<p>", postCode);
		pattern = pattern.replace("<c>", city);
		pattern = pattern.replace("<y>", county);
		pattern = pattern.replace("<l>", country);
		pattern = pattern.replace("<k>", coord);

		return pattern;
	}

}
