package com.sadik.interfaces;

import android.widget.TextView;

public interface PickerInterface {
	
	public void openDatePicker(TextView txtDate, String startDate, String endDate);
	public void openTimePicker(TextView txtDate);

}
