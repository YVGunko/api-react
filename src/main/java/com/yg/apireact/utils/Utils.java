package com.yg.apireact.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Date;

public class Utils {
	public static int getRandomIntWithExclusion(int min, int max, int [] exclude) {
	    Arrays.sort(exclude);
	    int random = min + (int) ((max - min + 1 - exclude.length) * Math.random());
	    for (int ex : exclude) {
	        if (random < ex) {
	            break;
	        }
	        random++;
	    }
	    return random;
	}
	
	/* datetime */
	public static String toStringOnlyDate(final LocalDateTime localDateTime) {
		return localDateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
	}
	public static long toMills(final LocalDateTime localDateTime) {
		return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();}

	public static Date toDate(final LocalDateTime localDateTime) {
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}
	public static LocalDateTime toLocalDate(final Date dateToConvert) {
	    return dateToConvert.toInstant()
	    	      .atZone(ZoneId.systemDefault())
	    	      .toLocalDateTime();
	    	}
}
