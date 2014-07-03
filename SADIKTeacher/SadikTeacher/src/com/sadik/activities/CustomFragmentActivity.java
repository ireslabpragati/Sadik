package com.sadik.activities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import com.sadik.interfaces.PickerInterface;
import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class CustomFragmentActivity extends FragmentActivity implements PickerInterface{

	TextView txtDate;
	protected final int TIME_DIALOG_ID = 34;
	protected final int DIALOG_FIXED_DATE_ID = 35;
	protected final int DIALOG_CURRENT_DATE_ID = 36;
	
	String start_date ;
	String end_date ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	
	@Override
	protected Dialog onCreateDialog(int id) {
		Dialog dialog=null;
		switch (id) {

		
		case DIALOG_FIXED_DATE_ID:
			// set date picker as current date
			return new DatePickerDialog(this, datePickerListener, 2013, 0,
					1);

		case DIALOG_CURRENT_DATE_ID:
			// set date picker as current date
			final Calendar c= Calendar.getInstance();
			
			DatePickerDialog dialog1 = new DatePickerDialog(this, datePickerListener, c.get(Calendar.YEAR), c.get(Calendar.MONTH),
					c.get(Calendar.DAY_OF_MONTH));
			
			if(start_date.equals("") || start_date == null)
			{
				start_date = "01/01/2000";
			}
			if(end_date.equals("") || end_date == null)
			{
				end_date = "31/12/2030";
			}

			SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
			Date start_d = null;
			Date end_d = null;
			int API_LEVEL = Integer.valueOf(android.os.Build.VERSION.SDK);
			if(API_LEVEL>=11)
			{
			try {
				end_d = f.parse(end_date);

				long end_milliseconds = end_d.getTime();
				
		        dialog1.getDatePicker().setMaxDate(end_milliseconds);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				start_d = f.parse(start_date);

				long start_milliseconds = start_d.getTime();
				
		        dialog1.getDatePicker().setMinDate(start_milliseconds);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			
	        dialog1.getDatePicker().setCalendarViewShown(false);
	        return dialog1;
			

		case TIME_DIALOG_ID:
			// set time picker as current time
			return new TimePickerDialog(this, 
                                        timePickerListener, 12, 0,false);
		}
		return dialog;
	}

	
	/**
	 * Opens DatePicker Dialog. After selecting a date, set on EditText. 
	 */
	private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

		// when dialog box is closed, below method will be called.
		public void onDateSet(DatePicker view, int selectedYear,
				int selectedMonth, int selectedDay) {
			int year = selectedYear;
			int month = selectedMonth;
			int day = selectedDay;
					//String strdate=String.format(String.format("%02d", day)+"/"+"%02d", (month+1))+"/"+String.format("%04d", year);
			String strdate1=String.format("%02d/%02d/%d", day, month+1, year);
			txtDate.setText(strdate1);
			
					//ToastCustom.makeText(MasterActivity.this, ":"+strdate, Toast.LENGTH_LONG);
		}
	};
	
	private TimePickerDialog.OnTimeSetListener timePickerListener = 
            new TimePickerDialog.OnTimeSetListener() {
		public void onTimeSet(TimePicker view, int selectedHour,
				int selectedMinute) {
			int hour = selectedHour;
			int minute = selectedMinute;
			String strTime;
			if(hour == 12)
			{
				strTime = String.format("%02d:%02d", hour, minute)+" PM";				
			}
			else if(hour == 0)
			{
				strTime = String.format("%02d:%02d", 12, minute)+" AM";				
			}
			else if(hour > 12)
			{
				strTime = String.format("%02d:%02d", hour%12, minute)+" PM";				
			}
			else
			{
				strTime = String.format("%02d:%02d", hour, minute)+" AM";	
			}
			txtDate.setText(strTime);
		}
	};
	
	@Override
	public void openTimePicker(TextView txtDate) {
		// TODO Auto-generated method stub
		this.txtDate = txtDate;
		showDialog(TIME_DIALOG_ID);
	}

	@Override
	public void openDatePicker(TextView txtDate, String startDate,
			String endDate) {
		// TODO Auto-generated method stub
		this.txtDate = txtDate;
		this.start_date = startDate;
		this.end_date = endDate;

		removeDialog(DIALOG_CURRENT_DATE_ID);

		showDialog(DIALOG_CURRENT_DATE_ID);
		
		
	}

	
}
