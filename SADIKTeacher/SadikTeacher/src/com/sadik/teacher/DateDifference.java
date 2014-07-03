package com.sadik.teacher;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.widget.TextView;
 
public class DateDifference {
	
	static long diff = 0;
	static boolean isFlag = false;
 
	public static boolean isGreater(TextView textView1, TextView textView2) {
 
		String dateStart = textView1.getText().toString();
		String dateStop = textView2.getText().toString();
 
		//HH converts hour in 24 hours format (0-23), day calculation
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
 
		Date d1 = null;
		Date d2 = null;
 
		try {
			d1 = format.parse(dateStart);
			d2 = format.parse(dateStop);
 
			//in milliseconds
			diff = d2.getTime() - d1.getTime();
 
			/*long diffSeconds = diff / 1000 % 60;
			long diffMinutes = diff / (60 * 1000) % 60;
			long diffHours = diff / (60 * 60 * 1000) % 24;
			long diffDays = diff / (24 * 60 * 60 * 1000);
 
			System.out.print(diff + " main, ");
			System.out.print(diffDays + " days, ");
			System.out.print(diffHours + " hours, ");
			System.out.print(diffMinutes + " minutes, ");
			System.out.print(diffSeconds + " seconds.");*/
 
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(diff>=0) {
			isFlag = true;
		}
		else {
			isFlag = false;
		}
		return isFlag;
 
	}
 
}