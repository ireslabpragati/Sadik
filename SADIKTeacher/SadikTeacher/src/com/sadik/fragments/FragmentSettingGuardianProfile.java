package com.sadik.fragments;

import com.sadik.database.DatabaseHandler;
import com.sadik.interfaces.NavigationInterface;
import com.sadik.interfaces.PickerInterface;
import com.sadik.model.BeanStudent;
import com.sadik.teacher.ArrayIndex;
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
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

@SuppressLint("ValidFragment")
public class FragmentSettingGuardianProfile extends Fragment {

	EditText edit_AddGuardian_FathersName, edit_AddGuardian_FatherDOB, edit_AddGuardian_MothersName,
	edit_AddGuardian_MotherDOB, edit_AddGuardian_Email_Address, edit_AddGuardian_Mobile;
	
	Button btn_AddSchool_Back, btn_AddSchool_Done;

	NavigationInterface navigationInterface;
	PickerInterface pickerInterface;
	DatabaseHandler db;
	Activity context;
	TextView textHeader;
	String strHeader;
	BeanStudent beanStudent;
	
	@SuppressLint("ValidFragment")
	public FragmentSettingGuardianProfile(TextView textHeader, String strHeader, BeanStudent beanStudent) {
		// TODO Auto-generated constructor stub
		this.textHeader = textHeader;
		this.strHeader = strHeader;
		this.beanStudent = beanStudent;
	}
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		this.context = activity;
		pickerInterface = (PickerInterface) activity;
		navigationInterface = (NavigationInterface) activity;
		db = new DatabaseHandler(activity);
		textHeader.setText(strHeader);
	}
	

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, 
        Bundle savedInstanceState) {
    	
    	super.onCreateView(inflater, container, savedInstanceState);
    	
    	View v = inflater.inflate(R.layout.layout_add_guardian, container, false);
    	
    	edit_AddGuardian_FathersName = (EditText) v.findViewById(R.id.edit_AddGuardian_FathersName);
    	edit_AddGuardian_FatherDOB = (EditText) v.findViewById(R.id.edit_AddGuardian_FatherDOB);
    	edit_AddGuardian_MothersName = (EditText) v.findViewById(R.id.edit_AddGuardian_MothersName);
    	edit_AddGuardian_MotherDOB = (EditText) v.findViewById(R.id.edit_AddGuardian_MotherDOB);
    	edit_AddGuardian_Email_Address = (EditText) v.findViewById(R.id.edit_AddGuardian_Email_Address);
    	edit_AddGuardian_Mobile = (EditText) v.findViewById(R.id.edit_AddGuardian_Mobile);
    	btn_AddSchool_Back = (Button) v.findViewById(R.id.btn_AddSchool_Back);
    	btn_AddSchool_Done = (Button) v.findViewById(R.id.btn_AddSchool_Done);
    	
    	setData();
    	
    	edit_AddGuardian_FatherDOB.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				pickerInterface.openDatePicker(edit_AddGuardian_FatherDOB, "", "");
			}
		});
    	
    	edit_AddGuardian_MotherDOB.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				pickerInterface.openDatePicker(edit_AddGuardian_MotherDOB, "", "");
			}
		});
    	
    	btn_AddSchool_Done.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(checkValidation())
				{
				BeanStudent bean = onDoneButtonClick(beanStudent);
				boolean result = db.insertOrUpdate_Student(bean);
				if(result)
				{
					navigationInterface.backPress();
					navigationInterface.backPress();
				}
				}
			}
		});
    	
    	btn_AddSchool_Back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				navigationInterface.backPress();
			}
		});

        return v;
    }
	
	private void setData()
	{
		if(beanStudent!=null )
		{
			if(beanStudent.getStudent_Pkey()!=null)
			{
				edit_AddGuardian_FathersName.setText(beanStudent.getFatherName());
				edit_AddGuardian_FatherDOB.setText(beanStudent.getFatherDob());
				edit_AddGuardian_MothersName.setText(beanStudent.getMotherName());
				edit_AddGuardian_MotherDOB.setText(beanStudent.getMotherDob());
				edit_AddGuardian_Email_Address.setText(beanStudent.getGuardianEmail());
				edit_AddGuardian_Mobile.setText(beanStudent.getGuardianPhone());
				
				btn_AddSchool_Done.setText(context.getResources().getString(R.string.Update));
			}
		}
	}
	
	private BeanStudent onDoneButtonClick(BeanStudent bean)
	{
		bean.setFatherName(edit_AddGuardian_FathersName.getText().toString());
		bean.setFatherDob(edit_AddGuardian_FatherDOB.getText().toString());
		bean.setMotherName(edit_AddGuardian_MothersName.getText().toString());
		bean.setMotherDob(edit_AddGuardian_MotherDOB.getText().toString());
		bean.setGuardianEmail(edit_AddGuardian_Email_Address.getText().toString());
		bean.setGuardianPhone(edit_AddGuardian_Mobile.getText().toString());
		
		return bean;
	}
	
	private boolean checkValidation() {
        boolean ret = true;

        if (!Validation.hasText(edit_AddGuardian_FathersName)) ret = false;
        if (!Validation.hasText(edit_AddGuardian_FatherDOB)) ret = false;
        if (!Validation.hasText(edit_AddGuardian_MothersName)) ret = false;
        if (!Validation.hasText(edit_AddGuardian_MotherDOB)) ret = false;
        if (!Validation.isPhoneNumber(edit_AddGuardian_Mobile, true)) ret = false;
        if (!Validation.isEmailAddress(edit_AddGuardian_Email_Address, true)) ret = false;
        
        return ret;
    }
}