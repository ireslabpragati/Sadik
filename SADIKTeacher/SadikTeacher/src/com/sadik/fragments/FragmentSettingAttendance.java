package com.sadik.fragments;

import com.sadik.activities.SettingAttendanceActivity;
import com.sadik.colorfragments.ActivityHostFragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.widget.TextView;

@SuppressLint("ValidFragment")
public class FragmentSettingAttendance extends ActivityHostFragment {
	
	TextView txt_setting_heading;
	String string;
    
	public FragmentSettingAttendance(TextView txt_setting_heading, String string) {
		// TODO Auto-generated constructor stub
		this.txt_setting_heading = txt_setting_heading;
		this.string = string;
	}

    @Override
    protected Class<? extends Activity> getActivityClass() {
    	txt_setting_heading.setText(string);
        return SettingAttendanceActivity.class;
    }

	@Override
	protected String getschoolPkey() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected TextView getschoolTab() {
		// TODO Auto-generated method stub
		return txt_setting_heading;
	}

	
}