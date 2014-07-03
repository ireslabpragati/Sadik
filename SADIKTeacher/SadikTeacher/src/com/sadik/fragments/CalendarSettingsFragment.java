package com.sadik.fragments;

import com.sadik.interfaces.FragmentInterFace;
import com.sadik.interfaces.PickerInterface;
import com.sadik.model.BeanDateValidation;
import com.sadik.model.BeanSchool;
import com.sadik.teacher.HideKeypad;
import com.sadik.teacher.R;
import com.sadik.validation.Validation;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("ValidFragment")
public class CalendarSettingsFragment extends Fragment {

	TextView txt_caledarSetting_schoolId, txt_caledarSetting_StartDate, txt_caledarSetting_EndDate;
	Spinner spin_caledarSetting_Year, spin_caledarSetting_Term;
	Button btn_caledarSetting_Holiday_Settings, btn_caledarSetting_Period_Settings,
	btn_caledarSetting_Configure_Period, btn_caledarSetting_Event_Settings;

	PickerInterface pickerInterface;
	FragmentInterFace fragmentInterFace;
	Activity context;
	TextView textHeader, clickedText;
	String strHeader;
	
	public CalendarSettingsFragment(TextView textHeader, String strHeader) {
		// TODO Auto-generated constructor stub
		this.textHeader = textHeader;
		this.strHeader = strHeader;
	}
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		this.context = activity;
		pickerInterface = (PickerInterface) activity;
		fragmentInterFace = (FragmentInterFace) activity;
		textHeader.setText(strHeader);
	}
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, 
        Bundle savedInstanceState) {
        // Inflate the layout for this fragment
    	super.onCreateView(inflater, container, savedInstanceState);
    	View V = inflater.inflate(R.layout.layout_calendar_setting, container, false);
    	
    	txt_caledarSetting_schoolId = (TextView) V.findViewById(R.id.txt_caledarSetting_schoolId);
    	txt_caledarSetting_StartDate = (TextView) V.findViewById(R.id.txt_caledarSetting_StartDate);
    	txt_caledarSetting_EndDate = (TextView) V.findViewById(R.id.txt_caledarSetting_EndDate);
    	spin_caledarSetting_Year = (Spinner) V.findViewById(R.id.spin_caledarSetting_Year);
    	spin_caledarSetting_Term = (Spinner) V.findViewById(R.id.spin_caledarSetting_Term);
    	btn_caledarSetting_Holiday_Settings = (Button) V.findViewById(R.id.btn_caledarSetting_Holiday_Settings);
    	btn_caledarSetting_Period_Settings = (Button) V.findViewById(R.id.btn_caledarSetting_Period_Settings);
    	btn_caledarSetting_Configure_Period = (Button) V.findViewById(R.id.btn_caledarSetting_Configure_Period);
    	btn_caledarSetting_Event_Settings = (Button) V.findViewById(R.id.btn_caledarSetting_Event_Settings);
    	
    	clickedText = (TextView) textHeader.getTag();
		clickedText.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				BeanSchool beanSchool = (BeanSchool) clickedText.getTag();
				if(beanSchool != null)
				{
					setData(beanSchool);
				}
			}
		});
		
		BeanSchool beanSchool = (BeanSchool) clickedText.getTag();

		if(beanSchool != null) // if(!(beanSchool == null || addmore))
		{
			setData(beanSchool);
		}
		
    	
    	
     	txt_caledarSetting_StartDate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String endDate = txt_caledarSetting_EndDate.getText().toString();
				pickerInterface.openDatePicker(txt_caledarSetting_StartDate , "",
						endDate);			}
		});
    	
    	txt_caledarSetting_EndDate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String startDate = txt_caledarSetting_StartDate.getText().toString();
				
				pickerInterface.openDatePicker(txt_caledarSetting_EndDate , startDate,
						"");
				}
		});
    	
    	btn_caledarSetting_Holiday_Settings.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(checkValidation()) {
					BeanDateValidation bean = getBean();
				fragmentInterFace.startAddHolidaysFragment(bean);
			}
				else {
				}
			}
		});
    	
    	btn_caledarSetting_Event_Settings.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(checkValidation()) {
					BeanDateValidation bean = getBean();
				fragmentInterFace.startAddEventsFragment(bean);
				}
				else {
				}
					}
		});
    	
    	btn_caledarSetting_Period_Settings.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(checkValidation()) {
				fragmentInterFace.startPeriodFragment();
				}
				else {
				}

				}
		});
    	
    	btn_caledarSetting_Configure_Period.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(checkValidation()) {
					BeanDateValidation bean = getBean();
				fragmentInterFace.startConfPeriodFragment(bean);
				}
				else {
				}

				}
		});
    	
    	return V;
    }

   @Override
    public void onPause() {
    	// TODO Auto-generated method stub
    	super.onPause();
    	HideKeypad.hideSoftKeyboard(context);
    }
   
   private void setData(BeanSchool beanSchool)
   {
	   txt_caledarSetting_schoolId.setText(beanSchool.getSchoolId());
	   
	   
   }
   
   private boolean checkValidation() {
       boolean ret = true;

       if (!Validation.hasText(txt_caledarSetting_StartDate)) ret = false;
       if (!Validation.isDateGreater(txt_caledarSetting_StartDate, txt_caledarSetting_EndDate, true)) ret = false;

       return ret;
   }
   
   private BeanDateValidation getBean()
   {
	   String startDate = txt_caledarSetting_StartDate.getText().toString();
	   String endDate = txt_caledarSetting_EndDate.getText().toString();
	   BeanDateValidation bean = new BeanDateValidation();
	   bean.setStartDate(startDate);
	   bean.setEndDate(endDate);
	   
	   return bean;
   }
   
}