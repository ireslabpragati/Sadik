package com.sadik.activities;

import com.sadik.teacher.R;
import com.sadik.teacher.ToastUD;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HomeActivity extends Activity {
	
	TextView txtHomeTeacherName;
	LinearLayout lay_Home_Attendance, lay_Home_Grade, lay_Home_Reports, lay_Home_Settings;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_home);
		
		txtHomeTeacherName = (TextView) findViewById(R.id.txtHomeTeacherName);
		lay_Home_Attendance = (LinearLayout) findViewById(R.id.lay_Home_Attendance);
		lay_Home_Grade = (LinearLayout) findViewById(R.id.lay_Home_Grade);
		lay_Home_Reports = (LinearLayout) findViewById(R.id.lay_Home_Reports);
		lay_Home_Settings = (LinearLayout) findViewById(R.id.lay_Home_Settings);
		
		lay_Home_Settings.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(HomeActivity.this, SettingActivity.class);
				startActivity(intent);
			}
		});
		
		lay_Home_Attendance.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ToastUD.show(HomeActivity.this);
			}
		});
		
		lay_Home_Grade.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ToastUD.show(HomeActivity.this);
			}
		});
		
		lay_Home_Reports.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ToastUD.show(HomeActivity.this);
			}
		});
		
	}

}
