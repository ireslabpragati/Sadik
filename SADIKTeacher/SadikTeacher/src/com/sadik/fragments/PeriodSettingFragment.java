package com.sadik.fragments;

import java.util.ArrayList;
import com.sadik.database.DatabaseHandler;
import com.sadik.interfaces.PickerInterface;
import com.sadik.model.BeanPriodSetting;
import com.sadik.model.BeanSchool;
import com.sadik.teacher.HideKeypad;
import com.sadik.teacher.R;
import com.sadik.teacher.TimeInMinutes;
import com.sadik.teacher.ToastFormError;
import com.sadik.validation.Validation;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("ValidFragment")
public class PeriodSettingFragment extends Fragment {
	DatabaseHandler db;
	BeanPriodSetting beanpreaodsetting;
	ArrayList<BeanPriodSetting> beanpriods;
	LinearLayout layout_period_setting;
	Button btn_period_setting_done;
	TextView txt_subPeriod_startTime,txt_subPeriod_endTime;
	PickerInterface pickerInterface;
	Activity context;
	TextView textHeader, clickedText;
	String strHeader;
	 String SchoolPkey=null;
	public PeriodSettingFragment(TextView textHeader, String strHeader) {
		// TODO Auto-generated constructor stub
		this.textHeader = textHeader;
		this.strHeader = strHeader;
	}

	@Override
	public void onAttach(Activity activity) {
		db = new DatabaseHandler(activity);
		// TODO Auto-generated method stub
		super.onAttach(activity);
		this.context = activity;
		pickerInterface = (PickerInterface) activity;
		textHeader.setText(strHeader);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		super.onCreateView(inflater, container, savedInstanceState);
		View V = inflater.inflate(R.layout.layout_period_setting, container,
				false);

		layout_period_setting = (LinearLayout) V
				.findViewById(R.id.layout_period_setting);
		btn_period_setting_done = (Button) V
				.findViewById(R.id.btn_period_setting_done);

		
		clickedText = (TextView) textHeader.getTag();

		setData();
		clickedText.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setData();
			}
		});
		
		btn_period_setting_done.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				/*ArrayList<BeanPriodSetting> beans = */addClassPriod();
/*				for (int i = 0; i < beans.size(); i++) {

					// db.Add_Priod_Setting(beans.get(i));
				}

				Toast.makeText(context, "Record Saved", 1).show();*/
			}
		});
		
		beanpreaodsetting = new BeanPriodSetting();
		beanpriods = new ArrayList<BeanPriodSetting>();
		//listpriods();

		return V;

	}

	private void addClassPriod(int count , BeanPriodSetting beanPriodSetting) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(context.LAYOUT_INFLATER_SERVICE);

		final View convertView = inflater.inflate(R.layout.layout_sub_period,
				null);

		TextView txt_subPeriod_Count = (TextView) convertView
				.findViewById(R.id.txt_subPeriod_Count);
		final TextView txt_subPeriod_startTime = (TextView) convertView
				.findViewById(R.id.txt_subPeriod_startTime);
		final TextView txt_subPeriod_endTime = (TextView) convertView
				.findViewById(R.id.txt_subPeriod_endTime);

		txt_subPeriod_Count.setText("Period "+(count+1));
		if(beanPriodSetting != null)
		{
			txt_subPeriod_startTime.setText(beanPriodSetting.getStart_time());
			txt_subPeriod_endTime.setText(beanPriodSetting.getEnd_time());
			
			btn_period_setting_done.setText(context.getResources().getString(R.string.Update));
			Log.i("beanPriodSetting", "Not Null");
			
			
		}
		else
		{
			Log.i("beanPriodSetting", " Null");

		}

		txt_subPeriod_startTime.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				pickerInterface.openTimePicker(txt_subPeriod_startTime);
			}
		});

		txt_subPeriod_endTime.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				pickerInterface.openTimePicker(txt_subPeriod_endTime);
			}
		});

		layout_period_setting.addView(convertView);
	}
  // set data according to schook pkey
    private void setData()
    {
    	layout_period_setting.removeAllViews();
		BeanSchool beanSchool = (BeanSchool) clickedText.getTag();
		
		if(beanSchool != null)
		{
			SchoolPkey = beanSchool.getSchoolPkey();	 
			ArrayList<BeanPriodSetting> beangpriods= db.GetPriods(beanSchool.getSchoolPkey());

			Log.i("BeanPriodSetting size", ": "+beangpriods.size());
			
			for (int i = 0; i < beanSchool.getPeriod_Count();i++) {
					if(i < beangpriods.size())
					{
						Log.i("BeanPriodSetting", ": If");
						addClassPriod(i,beangpriods.get(i));						
					}
					else
					{
						Log.i("BeanPriodSetting", ": Else");
						addClassPriod(i,null);
					}

				}
				
		}
		else
		{
	    	ToastFormError.schoolNotconfigured(context);
		}

    }

	
	// add priod one by one

	private void addClassPriod() {
		ArrayList<BeanPriodSetting> beanPriodSettings = new ArrayList<BeanPriodSetting>();
		
		int timeInMinutes = 0;
		boolean allvalidTime = true;
		
		for (int i = 0; i < layout_period_setting.getChildCount(); i++) {

			BeanPriodSetting bean = new BeanPriodSetting();
			View convertView = layout_period_setting.getChildAt(i);

			 txt_subPeriod_startTime = (TextView) convertView
					.findViewById(R.id.txt_subPeriod_startTime);
			 txt_subPeriod_endTime = (TextView) convertView
					.findViewById(R.id.txt_subPeriod_endTime);
			 
			 // Validation time and hasText
			 if(Validation.hasText(txt_subPeriod_startTime))
			 {
				 int time = TimeInMinutes.getTime(txt_subPeriod_startTime.getText().toString());
				 if(time > timeInMinutes)
				 {
					 timeInMinutes = time;					 
				 }
				 else
				 {
					 txt_subPeriod_startTime.setError("Time Not Valid");
					 allvalidTime = false;
				 }
			 }
			 else
			 {
				 allvalidTime = false;
			 }
			 if(Validation.hasText(txt_subPeriod_endTime))
			 {
				 int time = TimeInMinutes.getTime(txt_subPeriod_endTime.getText().toString());
				 if(time > timeInMinutes)
				 {
					 timeInMinutes = time;					 
				 }
				 else
				 {
					 txt_subPeriod_endTime.setError("Time Not Valid");
					 allvalidTime = false;
				 }
			 }
			 else
			 {
				 allvalidTime = false;
			 }
			 

			bean.setStart_time(txt_subPeriod_startTime.getText().toString());
			
			
			
			bean.setEnd_time(txt_subPeriod_endTime.getText().toString());
			
			if(SchoolPkey != null)
			{
				bean.set_SCHOOL_PKEY(SchoolPkey);
			}
			else
			{
				ToastFormError.schoolNotconfigured(context);
			}
			
			 
			beanPriodSettings.add(bean);
		}
		
		if(allvalidTime)
		{
		for (int i = 0; i < beanPriodSettings.size(); i++) {

			db.AddOrUpdatePriodSetting(beanPriodSettings.get(i));
		}

		//Toast.makeText(context, "Record Saved", 1).show();
		}
	}
	


	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		HideKeypad.hideSoftKeyboard(context);
	}
 /// priod list
	 private void listpriods() {
		db = new DatabaseHandler(context);
		beanpriods = new ArrayList<BeanPriodSetting>();

		
		if(SchoolPkey != null)
		{
			beanpriods = db.GetPriods(SchoolPkey);
			for (int i = 0; i < beanpriods.size(); i++) {
				showPriodDetails(beanpriods.get(i));
			}
		}
		else
		{
			ToastFormError.schoolNotconfigured(context);
		}
		
		

	}
	 
 private void showPriodDetails(BeanPriodSetting priods) {

	
	txt_subPeriod_startTime.setText(priods.getStart_time());
	txt_subPeriod_endTime.setText(priods.getEnd_time());
	
		   
	} 

	 
	
	
}

 