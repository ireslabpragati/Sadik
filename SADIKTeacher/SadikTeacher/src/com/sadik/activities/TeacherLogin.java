package com.sadik.activities;

import com.sadik.teacher.R;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

public class TeacherLogin extends Activity{
	TextView txtwelcome;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_teacher_registration);
		
	txtwelcome=(TextView) findViewById(R.id.txtwelcome);
	
	Typeface font = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/sigwelcome.ttf");
	txtwelcome. setTypeface(font);
	
		
		
	}

}
