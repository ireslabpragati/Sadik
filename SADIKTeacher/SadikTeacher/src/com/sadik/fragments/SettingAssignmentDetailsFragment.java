package com.sadik.fragments;

import com.sadik.database.DatabaseHandler;
import com.sadik.interfaces.FragmentInterFace;
import com.sadik.interfaces.PickerInterface;
import com.sadik.model.BeanAddress;
import com.sadik.model.BeanAssignmentDetails;
import com.sadik.model.BeanConfigPriodSetting;
import com.sadik.model.BeanTeacher;
import com.sadik.teacher.HideKeypad;
import com.sadik.teacher.R;
import com.sadik.teacher.ToastCustom;
import com.sadik.validation.Validation;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("ValidFragment")
public class SettingAssignmentDetailsFragment extends Fragment {
	PickerInterface pickerInterface;

	TextView txt_assignmentDetails_School_id, txt_assignmentDetails_examType,
			txt_assignmentDetails_exam_id, txt_assignmentDetails_Minkkh,
			txt_assignmentDetails_Date, txt_assignmentDetails_Period;

	Spinner spin_assignmentDetails_Class, spin_assignmentDetails_Subject,
			spin_assignmentDetails_grade_cat, spin_assignmentDetails_gradeType,
			spin_assignmentDetails_weight;

	Button btn_assignmentDetails_done;

	String Tsex = "";
	String SpKey = null;
	BeanTeacher beanteacherProfile;

	 
	FragmentInterFace fragmentInterFace;
	DatabaseHandler db;

	Activity context;
	TextView textHeader;
	String strHeader;

	 
	public SettingAssignmentDetailsFragment(TextView textHeader,
			String strHeader) {
		// TODO Auto-generated constructor stub
		this.textHeader = textHeader;
		this.strHeader = strHeader;
	}

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		this.context = activity;
		 	pickerInterface= (PickerInterface) activity;
		db = new DatabaseHandler(activity);
		textHeader.setText(strHeader);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		super.onCreateView(inflater, container, savedInstanceState);

		View v = inflater.inflate(R.layout.layout_assignment, container, false);

		btn_assignmentDetails_done = (Button) v
				.findViewById(R.id.btn_assignmentDetails_done);
		txt_assignmentDetails_School_id= (TextView)v.findViewById(R.id.txt_assignmentDetails_School_id);
		txt_assignmentDetails_Date= (TextView)v.findViewById(R.id.txt_assignmentDetails_Date);
		txt_assignmentDetails_exam_id= (TextView)v.findViewById(R.id.txt_assignmentDetails_exam_id);
		txt_assignmentDetails_examType= (TextView)v.findViewById(R.id.txt_assignmentDetails_examType);
		txt_assignmentDetails_Minkkh= (TextView)v.findViewById(R.id.txt_assignmentDetails_Minkkh);
		txt_assignmentDetails_Period= (TextView)v.findViewById(R.id.txt_assignmentDetails_Period);

		spin_assignmentDetails_Class = (Spinner) v.findViewById(R.id.spin_assignmentDetails_Class);
		 
		spin_assignmentDetails_Class = (Spinner) v.findViewById(R.id.spin_assignmentDetails_Class);
		spin_assignmentDetails_grade_cat = (Spinner) v.findViewById(R.id.spin_assignmentDetails_grade_cat); 
		spin_assignmentDetails_gradeType = (Spinner) v.findViewById(R.id.spin_assignmentDetails_gradeType);
		spin_assignmentDetails_Subject = (Spinner) v.findViewById(R.id.spin_assignmentDetails_Subject);
		spin_assignmentDetails_weight = (Spinner) v.findViewById(R.id.spin_assignmentDetails_weight);
		txt_assignmentDetails_Date.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				
				pickerInterface.openDatePicker(txt_assignmentDetails_Date , "01/01/2014",
						"01/01/2030");
				
				return false;
			}
		});
		
		btn_assignmentDetails_done.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				if (checkValidation()) {
					AddassignmentDetails();
				}
				
			}
		});
		return v;
	}

	// insert add teacher profile

	private void AddassignmentDetails() {

		BeanAssignmentDetails beanAssignmentDetails = new BeanAssignmentDetails();

		beanAssignmentDetails.set_ASSIGNMENT_CLASS(spin_assignmentDetails_Class
				.getSelectedItem().toString());
		beanAssignmentDetails.set_ASSIGNMENT_DATE(txt_assignmentDetails_Date
				.getText().toString());
		beanAssignmentDetails.set_ASSIGNMENT_EXAM_ID(txt_assignmentDetails_exam_id.getText().toString());
		beanAssignmentDetails.set_ASSIGNMENT_EXAM_TYPE(txt_assignmentDetails_examType.getText().toString());
		beanAssignmentDetails.set_ASSIGNMENT_GRADE_CATEGORY(spin_assignmentDetails_grade_cat.getSelectedItem().toString());
		beanAssignmentDetails.set_ASSIGNMENT_GRADE_TYPE(spin_assignmentDetails_gradeType.getSelectedItem().toString());
		beanAssignmentDetails.set_ASSIGNMENT_MIN_POINT_KKH(txt_assignmentDetails_Minkkh.getText().toString());
		beanAssignmentDetails.set_ASSIGNMENT_PRIOD(txt_assignmentDetails_Period.getText().toString());
		beanAssignmentDetails.set_ASSIGNMENT_SCHOOL_ID(txt_assignmentDetails_School_id.getText().toString());
		beanAssignmentDetails.set_ASSIGNMENT_SUBJECT(spin_assignmentDetails_Subject.getSelectedItem().toString());
		beanAssignmentDetails.set_ASSIGNMENT_WEIGHT(spin_assignmentDetails_weight.getSelectedItem().toString());

		db.Add_Assignment_details(beanAssignmentDetails);
          ToastCustom.makeText(context, "Data Saved", Toast.LENGTH_SHORT);// show toast after save data
		ResetTeacherField();
		 
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		HideKeypad.hideSoftKeyboard(context);
	}

	private void ResetTeacherField() {
		 
		txt_assignmentDetails_Date.setText("");
		txt_assignmentDetails_exam_id.setText("");
		txt_assignmentDetails_examType.setText("");
		txt_assignmentDetails_Minkkh.setText("");
		txt_assignmentDetails_Period.setText("");
		txt_assignmentDetails_School_id.setText("");
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
	}

	private boolean checkValidation() {
        boolean ret = true;

        if (!Validation.hasText(txt_assignmentDetails_Date)) ret = false;
        if (!Validation.hasText(txt_assignmentDetails_exam_id)) ret = false;
        if (!Validation.hasText(txt_assignmentDetails_examType)) ret = false;
        if (!Validation.hasText(txt_assignmentDetails_Minkkh)) ret = false;
     //   if (!Validation.isPhoneNumber(edit_AddTeacher_Mobile, true)) ret = false;
       // if (!Validation.isEmailAddress(edit_AddTeacher_Email_Address, true)) ret = false;
        if (!Validation.hasText(txt_assignmentDetails_Period)) ret = false;
        if (!Validation.hasText(txt_assignmentDetails_School_id)) ret = false;
        
        return ret;
    }
}