package com.sampana.login.utils;

import java.util.Date;


public class UserLocale {

	/**
	 * This function will return date based on the user location.
	 * TODO: Still not implemented.
	 * @return
	 */
	public static Date getUserLocaleDate() {
		//TODO: In future we need to implement Date according user locale. 
		return new Date();
		
	}

	/**
	 * This function is returns one hour incremental date/time from the current date/time. 
	 * @param date
	 * @return
	 */
	public static Date getTokenExpiryDate(Date date) {
		return new Date(date.getTime() + IConstants.ONE_HOUR);
	}
}