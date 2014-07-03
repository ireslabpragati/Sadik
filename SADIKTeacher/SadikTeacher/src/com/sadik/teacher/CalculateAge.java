package com.sadik.teacher;

import java.text.SimpleDateFormat;
import java.util.Date;

public class  CalculateAge
{
	public int Calculate(String dob) // Date is in DD/MM/YYYY format.
	{
		int yearDOB = Integer.parseInt(dob.substring(6, 10));
		int monthDOB = Integer.parseInt(dob.substring(3, 5));
		int dayDOB = Integer.parseInt(dob.substring(0, 2));

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
		Date date = new Date();
		int thisYear = Integer.parseInt(dateFormat.format(date));

		dateFormat = new SimpleDateFormat("MM");
		date = new java.util.Date();
		int thisMonth = Integer.parseInt(dateFormat.format(date));

		dateFormat = new SimpleDateFormat("dd");
		date = new java.util.Date();
		int thisDay = Integer.parseInt(dateFormat.format(date));

		int age = thisYear-yearDOB;
		if(thisMonth < monthDOB){
			age = age-1;
		}
		if(thisMonth == monthDOB && thisDay < dayDOB){
			age = age-1;
		}
		return age;
	}
} 