package com.sadik.teacher;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.util.Log;


public class DateConverter {
	
	
	public static String convertToDatabaseFormat(String inputDate) {
		Date date = null;
		try {		SimpleDateFormat parser = new SimpleDateFormat("dd/mm/yyyy");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");

			date = parser.parse(inputDate);
			Log.i("convertToDatabaseFormat", "try : "+formatter.format(date));
		return formatter.format(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		Log.i("convertToDatabaseFormat", "catch : "+inputDate);
		return inputDate;
	}
	public static String convertToScreenFormat(String inputDate) {
		Date date = null;
		try {		SimpleDateFormat parser = new SimpleDateFormat("yyyy-mm-dd");
		SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yyyy");

			date = parser.parse(inputDate);

			Log.i("convertToScreenFormat", "try : "+formatter.format(date));
		return formatter.format(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		Log.i("convertToScreenFormat", "catch : "+inputDate);
		return inputDate;
	}

}
