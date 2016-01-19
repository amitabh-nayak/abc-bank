package com.abc;

import java.util.Calendar;
import java.util.Date;

public class DateProvider {
	/**
	 *  Threadsafe implementation
	 *  Initialization-on-demand holder idiom pattern
	 *
	 */
	private static class Holder {
        static final DateProvider INSTANCE = new DateProvider();
    }

    public static DateProvider getInstance() {
        return Holder.INSTANCE;
    }
   

    public static Date now() {
        return Calendar.getInstance().getTime();
    }
    
    public static Date beforeTenDays() {
    	Calendar cal = Calendar.getInstance();
    	// Substract 10 days from the calendar
        cal.add(Calendar.DATE, -10);
       return  cal.getTime();
    }
}
