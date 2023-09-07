package com.yg.apireact;

public final class Constants {
	public static final String[] ORIGINS = new String[] {"http://localhost:8082", "http://localhost:3000"};
	
	public static final String[] DIVISIONS = {"00-000025", "00-000047", "00-000002"};
	public static final String TEP_DIVISION = "00-000025";
	public static final String PU_DIVISION = "00-000002";
	public static final String EVA_DIVISION = "00-000047";
	public static final String EXTERNAL_USER = "ROLE_EXTERNAL";
	public static final String ORDER_MAKER = "ROLE_ORDER_MAKER";
	
	private Constants() {
	    //this prevents even the native class from 
	    //calling this constructor as well :
	    throw new AssertionError();
	}
}
