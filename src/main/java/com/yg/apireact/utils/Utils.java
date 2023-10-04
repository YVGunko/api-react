package com.yg.apireact.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
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
	//day
	public static LocalDateTime startOfDay() {
		return LocalDateTime.now(ZoneId.systemDefault()).with(LocalTime.MIN);}

	public static LocalDateTime endOfDay() {
		return LocalDateTime.now(ZoneId.systemDefault()).with(LocalTime.MAX);}

	public static boolean belongsToCurrentDay(final LocalDateTime localDateTime) {
	return localDateTime.isAfter(startOfDay()) && localDateTime.isBefore(endOfDay());}

	// note that week starts with Monday
	public static LocalDateTime startOfWeek() {
		return LocalDateTime.now(ZoneId.systemDefault())
		.with(LocalTime.MIN)
		.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));}

	// note that week ends with Sunday
	public static LocalDateTime endOfWeek() {
		return LocalDateTime.now(ZoneId.systemDefault())
		.with(LocalTime.MAX)
		.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));}
	//month
	public static LocalDateTime startOfMonth() {
		return LocalDateTime.now(ZoneId.systemDefault())
		.with(LocalTime.MIN)
		.with(TemporalAdjusters.firstDayOfMonth());}
	
	public static LocalDateTime endOfMonth() {
		return LocalDateTime.now(ZoneId.systemDefault())
		.with(LocalTime.MAX)
		.with(TemporalAdjusters.lastDayOfMonth());
	}
}
