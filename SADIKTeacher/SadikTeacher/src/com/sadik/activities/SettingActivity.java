package com.sadik.activities;

import java.util.ArrayList;

import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sadik.database.DatabaseHandler;
import com.sadik.fragments.AddEventsFragment;
import com.sadik.fragments.AddHolidaysFragment;
import com.sadik.fragments.CalendarSettingsFragment;
import com.sadik.fragments.ClassRoomMgtFragment;
import com.sadik.fragments.ConfPeriodFragment;
import com.sadik.fragments.ConfigureSchool1Fragment;
import com.sadik.fragments.ConfigureSchool2Fragment;
import com.sadik.fragments.FragmentDemo;
import com.sadik.fragments.FragmentSettingAttendance;
import com.sadik.fragments.FragmentSettingGradeCategory;
import com.sadik.fragments.FragmentSettingGradeType;
import com.sadik.fragments.FragmentSettingGuardianProfile;
import com.sadik.fragments.FragmentSettingStudentList;
import com.sadik.fragments.FragmentSettingStudentProfile;
import com.sadik.fragments.FragmentSettingTeacherProfile;
import com.sadik.fragments.PeriodSettingFragment;
import com.sadik.fragments.SettingAssignmentDetailsFragment;
import com.sadik.fragments.SettingFragment;
import com.sadik.fragments.SettingSeatAllocationFragment;
import com.sadik.interfaces.FragmentInterFace;
import com.sadik.interfaces.NavigationInterface;
import com.sadik.model.BeanClassInfo;
import com.sadik.model.BeanDateValidation;
import com.sadik.model.BeanPriodSetting;
import com.sadik.model.BeanSchool;
import com.sadik.model.BeanStudent;
import com.sadik.teacher.R;
import com.sadik.teacher.ToastCustom;
import com.sadik.teacher.ToastUD;
import com.sadik.teacher.VerticalTextView;

public class SettingActivity extends CustomFragmentActivity implements OnClickListener, FragmentInterFace,
	NavigationInterface
{
	
	FrameLayout frame1;
	VerticalTextView vtxt_Setting_Profile, vtxt_Setting_School, vtxt_Setting_Class, vtxt_Setting_Calendar,
	vtxt_Setting_Assignment, vtxt_Setting_Attendance, vtxt_Setting_Grade, vtxt_Setting_Student,
	vtxt_Setting_Seat_Allocation;
	
	View view_Setting_Profile, view_Setting_School, view_Setting_Class, view_Setting_Calendar,
	view_Setting_Assignment, view_Setting_Attendance, view_Setting_Grade, view_Setting_Student,
	view_Setting_Seat_Allocation;
	TextView txt_setting_heading, txt_setting_invisibleClickable;
	Button btn_Setting_Back, btn_Setting_Done;
	LinearLayout layout_school_ids;
	
	private int SELECT_TAB = 0;
	ArrayList<ArrayList<Fragment>> group = new ArrayList<ArrayList<Fragment>>();
	DatabaseHandler db = new DatabaseHandler(SettingActivity.this);

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		try
		{
		setContentView(R.layout.layout_master);
		}
		catch(Exception e)
		{
			
		}
		
		txt_setting_heading = (TextView) findViewById(R.id.txt_setting_heading);
		txt_setting_invisibleClickable = (TextView) findViewById(R.id.txt_setting_invisibleClickable);
		btn_Setting_Back = (Button) findViewById(R.id.btn_Setting_Back);
		btn_Setting_Done = (Button) findViewById(R.id.btn_Setting_Done);
		layout_school_ids = (LinearLayout) findViewById(R.id.layout_school_ids);
		
		frame1 = (FrameLayout) findViewById(R.id.frame1);
		vtxt_Setting_Profile = (VerticalTextView)findViewById(R.id.vtxt_Setting_Profile);
		vtxt_Setting_School = (VerticalTextView)findViewById(R.id.vtxt_Setting_School);
		vtxt_Setting_Class = (VerticalTextView)findViewById(R.id.vtxt_Setting_Class);
		vtxt_Setting_Calendar = (VerticalTextView)findViewById(R.id.vtxt_Setting_Calendar);
		vtxt_Setting_Assignment = (VerticalTextView)findViewById(R.id.vtxt_Setting_Assignment);
		vtxt_Setting_Attendance = (VerticalTextView)findViewById(R.id.vtxt_Setting_Attendance);
		vtxt_Setting_Grade = (VerticalTextView)findViewById(R.id.vtxt_Setting_Grade);
		vtxt_Setting_Student = (VerticalTextView)findViewById(R.id.vtxt_Setting_Student);
		vtxt_Setting_Seat_Allocation = (VerticalTextView)findViewById(R.id.vtxt_Setting_Seat_Allocation);
		
		view_Setting_Profile = findViewById(R.id.view_Setting_Profile);
		view_Setting_School = findViewById(R.id.view_Setting_School);
		view_Setting_Class = findViewById(R.id.view_Setting_Class);
		view_Setting_Calendar = findViewById(R.id.view_Setting_Calendar);
		view_Setting_Assignment = findViewById(R.id.view_Setting_Assignment);
		view_Setting_Attendance = findViewById(R.id.view_Setting_Attendance);
		view_Setting_Grade = findViewById(R.id.view_Setting_Grade);
		view_Setting_Student = findViewById(R.id.view_Setting_Student);
		view_Setting_Seat_Allocation = findViewById(R.id.view_Setting_Seat_Allocation);
		
		vtxt_Setting_Profile.setOnClickListener(this); 
		vtxt_Setting_School.setOnClickListener(this);
		vtxt_Setting_Class.setOnClickListener(this);
		vtxt_Setting_Calendar.setOnClickListener(this);
		vtxt_Setting_Assignment.setOnClickListener(this);
		vtxt_Setting_Attendance.setOnClickListener(this);
		vtxt_Setting_Grade.setOnClickListener(this);
		vtxt_Setting_Student.setOnClickListener(this);
		vtxt_Setting_Seat_Allocation.setOnClickListener(this);
		
		SELECT_TAB = getIntent().getIntExtra("selectedtab", 0);

		IntializeArraylist();
		
		SettingFragment newFragment = new SettingFragment(txt_setting_heading, getString(R.string.Settings));
		transactFragment(newFragment,true);
		
		setSchoolButtons();
		
		btn_Setting_Back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onBackPressed();
			}
		});
		
		getScreenOrientation(getResources().getConfiguration());
	}
	
	private void unSelectAll()
	{
		view_Setting_Profile.setVisibility(View.GONE);
		view_Setting_School.setVisibility(View.GONE);
		view_Setting_Class.setVisibility(View.GONE);
		view_Setting_Calendar.setVisibility(View.GONE);
		view_Setting_Assignment.setVisibility(View.GONE);
		view_Setting_Attendance .setVisibility(View.GONE);
		view_Setting_Grade .setVisibility(View.GONE);
		view_Setting_Student.setVisibility(View.GONE);
		view_Setting_Seat_Allocation.setVisibility(View.GONE);
	}

	private void selectTab()
	{
		ArrayList<Fragment> fragments = group.get(SELECT_TAB); 
		
		if(fragments.size()>0)
		{
		Fragment fragment = fragments.get(fragments.size()-1);
		transactFragment(fragment,true);
		
		}
		else
		{
			switch(SELECT_TAB)
			{
			case 0:
				startFragmentSettingTeacherProfile();
				break;
			case 1:
				startConfigureSchool1Fragment(false);
				break;
			case 2:
				ClassRoomMgtFragment classRoomMgtFragment = new ClassRoomMgtFragment(txt_setting_heading, getString(R.string.Classroom_Management));
				transactFragment(classRoomMgtFragment,false);
				break;
			case 3:
				CalendarSettingsFragment calendarSettingsFragment = new CalendarSettingsFragment(txt_setting_heading, getString(R.string.Calendar_Settings));
				transactFragment(calendarSettingsFragment,false);
				break;
			case 4:
				startFragmentSettingAssignmentDetails();
				break;
			case 5:
				startFragmentSettingAttendance();
				break;
			case 6:
				startFragmentSettingGradeCategory();
				break;
			case 7:
				startFragmentSettingStudentList();
				break;
			case 8:
				//startSeatAllocationFragment();
				break;
			}
		}

		}

	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		unSelectAll();
		
		switch(v.getId())
		{
		case R.id.vtxt_Setting_Profile:
			SELECT_TAB = 0;
			view_Setting_Profile.setVisibility(View.VISIBLE);
			break;

		case R.id.vtxt_Setting_School:
			SELECT_TAB = 1;
			view_Setting_School.setVisibility(View.VISIBLE);
			break;
		case R.id.vtxt_Setting_Class:
			SELECT_TAB = 2;
			view_Setting_Class.setVisibility(View.VISIBLE);
			break;
		case R.id.vtxt_Setting_Calendar:
			SELECT_TAB = 3;
			view_Setting_Calendar.setVisibility(View.VISIBLE);
			break;
		case R.id.vtxt_Setting_Assignment:
			SELECT_TAB = 4;
			view_Setting_Assignment.setVisibility(View.VISIBLE);
			ToastCustom.makeText(getApplicationContext(), "Assignment Setting", Toast.LENGTH_SHORT);
			break;
		case R.id.vtxt_Setting_Attendance:
			SELECT_TAB = 5;
			view_Setting_Attendance.setVisibility(View.VISIBLE);
			break;
		case R.id.vtxt_Setting_Grade:
			SELECT_TAB = 6;
			view_Setting_Grade.setVisibility(View.VISIBLE);
			break;
		case R.id.vtxt_Setting_Student:
			SELECT_TAB = 7;
			view_Setting_Student.setVisibility(View.VISIBLE);
			break;
		case R.id.vtxt_Setting_Seat_Allocation:
			//SELECT_TAB = 8;
			view_Setting_Seat_Allocation.setVisibility(View.VISIBLE);
			ToastUD.show(SettingActivity.this);
			break;
		}
		selectTab();
	}
	
	private void transactFragment(Fragment newFragment,boolean isback)
	{
		if(!isback)
		{
			group.get(SELECT_TAB).add(newFragment);
		}
		// newFragment.setArguments(savedInstanceState);
		newFragment.setRetainInstance(false);
		FragmentManager  fm = SettingActivity.this.getSupportFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();

		transaction.replace(R.id.frag1, newFragment);
		transaction.commit();
		
		ArrayList<Fragment> fragments = group.get(SELECT_TAB); 
		if(fragments.size()>1)
		{
			btn_Setting_Back.setVisibility(View.VISIBLE);
		}
		else
		{
			btn_Setting_Back.setVisibility(View.INVISIBLE);
		}

	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub

		ArrayList<Fragment> fragments = group.get(SELECT_TAB); 
		
		if(fragments.size()>1)
		{
			fragments.remove(fragments.size()-1);
		Fragment fragment = fragments.get(fragments.size()-1);
		transactFragment(fragment,true);
		}
		else
		{
			super.onBackPressed();
		}
		
	}
	
	
	public void getScreenOrientation(Configuration configuration) {


		// Query what the orientation currently really is.
		if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT)                {
		   // The following message is only displayed once.
	       	frame1.setBackgroundResource(R.drawable.whitebackground);

		}else if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
		   // The following message is only displayed once.
	       	frame1.setBackgroundResource(R.drawable.whitebackground_land);
		}
		}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
		getScreenOrientation(newConfig);
		/*switch(newConfig.orientation) {
        case Configuration.ORIENTATION_LANDSCAPE:
        	frame1.setBackgroundResource(R.drawable.whitebackground_land);
            break;
        case Configuration.ORIENTATION_PORTRAIT:
        	frame1.setBackgroundResource(R.drawable.whitebackground);
            break;
    }*/	}

	
/*	private void selectTab()
	{
		ArrayList<Fragment> fragments = group.get(SELECT_TAB); 
		
		if(fragments.size()>0)
		{
		Fragment fragment = fragments.get(fragments.size()-1);
		transactFragment(fragment,true);
		
		}
		else
		{
			switch(SELECT_TAB)
			{
			case 0:
				break;
			case 1:
					
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			}
		}

		}
		*/
	private void addButtons(ArrayList<BeanSchool> beans)
	{
		int count = beans.size();
		
		layout_school_ids.removeAllViews();
		Log.i("School Data size", ": "+count);
		
		if(count>1)
		{
		for (int i = 0; i < count; i++) {

			BeanSchool beanSchool = beans.get(i);
			
			Button b = new Button(SettingActivity.this);
			b.setSingleLine();
			b.setText(beanSchool.getSchoolId());
			b.setTag(beanSchool);
			b.setPadding(0, 0, 0, 0);
			b.setTextColor(Color.parseColor("#474644"));
			b.setTextSize(txt_setting_invisibleClickable.getTextSize());
			
			LinearLayout.LayoutParams leftMarginParams;
			if(i == 0)
			{
				b.setBackgroundResource(R.drawable.round_green_dark);				
				leftMarginParams = new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.FILL_PARENT,(1.0f/count));
				txt_setting_invisibleClickable.setTag(beanSchool);
				}
			else
			{
				b.setBackgroundResource(R.drawable.round_green_light);				
				leftMarginParams = new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.FILL_PARENT,(1.0f/count));
		        leftMarginParams.leftMargin = 1;
			}
	        
	        b.setLayoutParams(leftMarginParams);
	        b.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					unselectAll();
					Button b1 = (Button) v;
					b1.setBackgroundResource(R.drawable.round_green_dark);
					BeanSchool beanSchool = (BeanSchool) b1.getTag();
					txt_setting_invisibleClickable.setTag(beanSchool);
						txt_setting_invisibleClickable.performClick();
				}
			});
	        
	        layout_school_ids.addView(b);
		}
		}
		else if(count == 1)
		{
			BeanSchool beanSchool = beans.get(0);
			txt_setting_invisibleClickable.setTag(beanSchool);
		}
		
		txt_setting_heading.setTag(txt_setting_invisibleClickable);
	}
	
	private void unselectAll()
	{
		for (int i = 0; i < layout_school_ids.getChildCount(); i++) {
			Button b = (Button) layout_school_ids.getChildAt(i);
			b.setBackgroundResource(R.drawable.round_green_light);
		}
	}

	
	void IntializeArraylist()
	{
		for(int i=0;i<=8;i++)
		{
		ArrayList<Fragment> frag = new ArrayList<Fragment>();
		group.add(frag);
		}
	}

@Override
public void startConfigureSchool2Fragment(BeanSchool beanSchool,ArrayList<BeanClassInfo> beanClassInfos) {
	// TODO Auto-generated method stub
	ConfigureSchool2Fragment newFragment = new ConfigureSchool2Fragment(txt_setting_heading, getString(R.string.Configure_School),beanSchool, beanClassInfos, layout_school_ids.getChildCount());
	transactFragment(newFragment,true);
}

@Override
public void startAddHolidaysFragment(BeanDateValidation bean) {
	// TODO Auto-generated method stub
	AddHolidaysFragment newFragment = new AddHolidaysFragment(txt_setting_heading, getString(R.string.Holidays),bean);
	transactFragment(newFragment,false);
}

@Override
public void startAddEventsFragment(BeanDateValidation bean) {
	// TODO Auto-generated method stub
	AddEventsFragment newFragment = new AddEventsFragment(txt_setting_heading, getString(R.string.Event_Settings),bean);
	transactFragment(newFragment,false);	
}

@Override
public void startPeriodFragment() {
	// TODO Auto-generated method stub
	PeriodSettingFragment newFragment = new PeriodSettingFragment(txt_setting_heading, getString(R.string.Period_Settings));
	transactFragment(newFragment,false);		
}

@Override
public void startConfPeriodFragment(BeanDateValidation bean) {
	// TODO Auto-generated method stub
	ConfPeriodFragment newFragment = new ConfPeriodFragment(txt_setting_heading, getString(R.string.Configure_Period),bean);
	transactFragment(newFragment,false);		
	
}

@Override
public void backPress() {
	// TODO Auto-generated method stub
	onBackPressed();

}

@Override
public void startConfigureSchool1Fragment(boolean addmore) {
	// TODO Auto-generated method stub
	ConfigureSchool1Fragment configureSchool1Fragment = new ConfigureSchool1Fragment(txt_setting_heading, getString(R.string.Configure_School), addmore);
	transactFragment(configureSchool1Fragment,true);

}

@Override
public void startFragmentSettingGradeType() {
	// TODO Auto-generated method stub
	FragmentSettingGradeType configureSchool1Fragment = new FragmentSettingGradeType(txt_setting_heading, getString(R.string.Grade_Setting));
	transactFragment(configureSchool1Fragment,false);	
}

@Override
public void startFragmentSettingGradeCategory() {
	// TODO Auto-generated method stub
	FragmentSettingGradeCategory configureSchool1Fragment = new FragmentSettingGradeCategory(txt_setting_heading, getString(R.string.Grade_Setting));
	transactFragment(configureSchool1Fragment,false);	
}

@Override
public void startFragmentSettingAttendance() {
	// TODO Auto-generated method stub
	FragmentSettingAttendance configureSchool1Fragment = new FragmentSettingAttendance(txt_setting_heading, getString(R.string.Attendance));
	transactFragment(configureSchool1Fragment,false);		
}

@Override
public void startFragmentSettingTeacherProfile() {
	// TODO Auto-generated method stub
	FragmentSettingTeacherProfile configureSchool1Fragment = new FragmentSettingTeacherProfile(txt_setting_heading, getString(R.string.Teacher_Profile));
	transactFragment(configureSchool1Fragment,false);			
}

@Override
public void startFragmentSettingStudentProfile(String StudentPKey, String schoolPkey) {
	// TODO Auto-generated method stub
	FragmentSettingStudentProfile configureSchool1Fragment = new FragmentSettingStudentProfile(txt_setting_heading, getString(R.string.Add_Student),StudentPKey,schoolPkey);
	transactFragment(configureSchool1Fragment,false);				
}

@Override
public void startFragmentSettingStudentList() {
	// TODO Auto-generated method stub
	FragmentSettingStudentList configureSchool1Fragment = new FragmentSettingStudentList(txt_setting_heading, getString(R.string.Student));
	transactFragment(configureSchool1Fragment,false);					
}

@Override
public void startFragmentSettingGaurdianProfile(BeanStudent beanStudent) {
	// TODO Auto-generated method stub
	FragmentSettingGuardianProfile configureSchool1Fragment = new FragmentSettingGuardianProfile(txt_setting_heading, getString(R.string.Add_Student),beanStudent);
	transactFragment(configureSchool1Fragment,false);						
}

@Override
public void setSchoolButtons() {
	// TODO Auto-generated method stub
	ArrayList<BeanSchool> beans = db.getSchoolDatas();
	addButtons(beans);

}

@Override
public void startPeriodSettingFragment(BeanPriodSetting bean) {
	// TODO Auto-generated method stub
	PeriodSettingFragment priodsettingFragment = new PeriodSettingFragment(txt_setting_heading, getString(R.string.Period_Settings));
	transactFragment(priodsettingFragment,false);		
}

@Override
public void startFragmentSettingAssignmentDetails(   ) {
	// TODO Auto-generated method stub
	SettingAssignmentDetailsFragment assignmentDetailsFragment = new SettingAssignmentDetailsFragment(txt_setting_heading, getString(R.string.Assignment));
	transactFragment(assignmentDetailsFragment,false);
}

@Override
public void startSeatAllocationFragment() {
	// TODO Auto-generated method stub
	SettingSeatAllocationFragment seatAllocFragment = new SettingSeatAllocationFragment(txt_setting_heading, getString(R.string.Seat_Allocation));
	transactFragment(seatAllocFragment,false);
}

}
