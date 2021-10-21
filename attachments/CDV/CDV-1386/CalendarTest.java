package com.terracottatest;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarTest {

	private GregorianCalendar sut = new GregorianCalendar();
	

	public static void main(String[] args) throws Exception {
		
		CalendarTest calendarTest = new CalendarTest();
		 
		
		int weekDay = calendarTest.sut.get(Calendar.DAY_OF_WEEK);
		
		calendarTest.sut.add(Calendar.DATE, 1);
		System.out.println(calendarTest.sut + " : " + weekDay);

	}

}
