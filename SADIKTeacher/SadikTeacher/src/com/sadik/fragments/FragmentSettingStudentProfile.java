package com.sadik.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.sadik.database.DatabaseHandler;
import com.sadik.interfaces.FragmentInterFace;
import com.sadik.interfaces.PickerInterface;
import com.sadik.model.BeanClassIdsNameArray;
import com.sadik.model.BeanStudent;
import com.sadik.teacher.ArrayIndex;
import com.sadik.teacher.R;
import com.sadik.teacher.ToastUD;
import com.sadik.validation.Validation;

@SuppressLint("ValidFragment")
public class FragmentSettingStudentProfile extends Fragment {

 	ImageView img_AddStudent_ProfileImage;
 	TextView txt_AddStudent_DOB;

	RadioButton radiobtn_AddStudent_Male, radiobtn_AddStudent_Female;

	EditText edit_AddStudent_StudentId, edit_AddStudent_FName, edit_AddStudent_LName,
	edit_AddStudent_Mobile, edit_AddStudent_Email_Address, edit_AddStudent_Address;

	Spinner spin_AddStudent_Class, spin_AddStudent_Religion, spin_AddStudent_provience,
	spin_AddStudent_City, spin_AddStudent_District, spin_AddStudent_Sub_District_Village;

	Button btn_AddStudent_Upload_Image, btn_AddStudent_Forword;

	DatabaseHandler db;
	String StudentPkey;
	PickerInterface pickerInterface;
	FragmentInterFace fragmentInterFace;
	Activity context;
	TextView textHeader;
	String strHeader;
	String SchoolPkey;
	
	@SuppressLint("ValidFragment")
	public FragmentSettingStudentProfile(TextView textHeader, String strHeader, String StudentPkey,String SchoolPkey) {
		// TODO Auto-generated constructor stub
		this.textHeader = textHeader;
		this.strHeader = strHeader;
		this.StudentPkey = StudentPkey;
		this.SchoolPkey = SchoolPkey;
	}
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		this.context = activity;
		pickerInterface = (PickerInterface) activity;
		fragmentInterFace = (FragmentInterFace) activity;
		textHeader.setText(strHeader);
		db = new DatabaseHandler(activity);
	}
	

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, 
        Bundle savedInstanceState) {
    	
    	super.onCreateView(inflater, container, savedInstanceState);
    	
    	View v = inflater.inflate(R.layout.layout_add_stutent, container, false);
    	
    	img_AddStudent_ProfileImage = (ImageView) v.findViewById(R.id.img_AddStudent_ProfileImage);

    	radiobtn_AddStudent_Male = (RadioButton) v.findViewById(R.id.radiobtn_AddStudent_Male);
    	radiobtn_AddStudent_Female = (RadioButton) v.findViewById(R.id.radiobtn_AddStudent_Female);

    	edit_AddStudent_StudentId = (EditText) v.findViewById(R.id.edit_AddStudent_StudentId);
    	edit_AddStudent_FName = (EditText) v.findViewById(R.id.edit_AddStudent_FName);
    	edit_AddStudent_LName = (EditText) v.findViewById(R.id.edit_AddStudent_LName);
    	txt_AddStudent_DOB = (TextView) v.findViewById(R.id.txt_AddStudent_DOB);
    	edit_AddStudent_Mobile = (EditText) v.findViewById(R.id.edit_AddStudent_Mobile);
    	edit_AddStudent_Email_Address = (EditText) v.findViewById(R.id.edit_AddStudent_Email_Address);
    	edit_AddStudent_Address = (EditText) v.findViewById(R.id.edit_AddStudent_Address);

    	spin_AddStudent_Class = (Spinner) v.findViewById(R.id.spin_AddStudent_Class);
    	spin_AddStudent_Religion = (Spinner) v.findViewById(R.id.spin_AddStudent_Religion);
    	spin_AddStudent_provience = (Spinner) v.findViewById(R.id.spin_AddStudent_provience);
    	spin_AddStudent_City = (Spinner) v.findViewById(R.id.spin_AddStudent_City);
    	spin_AddStudent_District = (Spinner) v.findViewById(R.id.spin_AddStudent_District);
    	spin_AddStudent_Sub_District_Village = (Spinner) v.findViewById(R.id.spin_AddStudent_Sub_District_Village);

    	btn_AddStudent_Upload_Image = (Button) v.findViewById(R.id.btn_AddStudent_Upload_Image);
    	btn_AddStudent_Forword = (Button) v.findViewById(R.id.btn_AddStudent_Forword);
    	
    	setData();
    	
    	txt_AddStudent_DOB.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				pickerInterface.openDatePicker(txt_AddStudent_DOB, "", "");
			}
		});
    	
    	btn_AddStudent_Upload_Image.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ToastUD.show(context);
			}
		});
    	
    	btn_AddStudent_Forword.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(checkValidation())
				{
				BeanStudent beanStudent = btnForwordOnClick();
				fragmentInterFace.startFragmentSettingGaurdianProfile(beanStudent);
				}
			}
		});
    	
        return v;
    }
	
	private void setData()
	{
		BeanClassIdsNameArray beanClassIdsNameArray = db.getClassIdsAndNameArrays(SchoolPkey);
		
		ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, beanClassIdsNameArray.getClassNames());
		spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spin_AddStudent_Class.setAdapter(spinnerArrayAdapter);
		spin_AddStudent_Class.setTag(beanClassIdsNameArray.getClassIds());
		
		if(!StudentPkey.equalsIgnoreCase("") || StudentPkey!=null)
		{
		BeanStudent beanStudent = db.getStudentCompleteInfo(StudentPkey);
		if(beanStudent.isResult())
		{
		
	 	txt_AddStudent_DOB.setText(beanStudent.getDob());
	 	if(beanStudent.getGender().equalsIgnoreCase("male") || beanStudent.getGender().equalsIgnoreCase("m"))
	 	{
			radiobtn_AddStudent_Male.setChecked(true);
			radiobtn_AddStudent_Female.setChecked(false);	 		
	 	}
	 	else if(beanStudent.getGender().equalsIgnoreCase("female") || beanStudent.getGender().equalsIgnoreCase("f"))
	 	{
			radiobtn_AddStudent_Male.setChecked(false);
			radiobtn_AddStudent_Female.setChecked(true);	 		
	 	}

		edit_AddStudent_StudentId.setText(beanStudent.getStudent_ID());
		edit_AddStudent_FName.setText(beanStudent.getF_Name());
		edit_AddStudent_LName.setText(beanStudent.getL_Name());
		edit_AddStudent_Mobile.setText(beanStudent.getPhone());
		edit_AddStudent_Email_Address.setText(beanStudent.getEmail());
		edit_AddStudent_Address.setText(beanStudent.getAddress());

	 	edit_AddStudent_StudentId.setTag(beanStudent.getStudent_Pkey());
	 	
	 	String separeater = context.getResources().getString(R.string.seperater);
	 	String guardianDetail = beanStudent.getFatherName()+separeater+beanStudent.getFatherDob()+separeater+
	 			beanStudent.getMotherName()+separeater+beanStudent.getMotherDob()+separeater+
	 			beanStudent.getGuardianEmail()+separeater+beanStudent.getGuardianPhone();
	 	
		edit_AddStudent_FName.setTag(guardianDetail);
		
		ArrayIndex arrayIndex = new ArrayIndex();
		
		String strArr[] = (String[]) spin_AddStudent_Class.getTag();
		int index = ArrayIndex.getItemIndex(strArr, beanStudent.getClass_Id());
		
		spin_AddStudent_Class.setSelection(index);
		arrayIndex.setSpinnerItem(spin_AddStudent_Class, beanStudent.getClass_Id());
		arrayIndex.setSpinnerItem(spin_AddStudent_Religion, beanStudent.getReligion());
		arrayIndex.setSpinnerItem(spin_AddStudent_provience, beanStudent.getState());
		arrayIndex.setSpinnerItem(spin_AddStudent_City, beanStudent.getCity());
		arrayIndex.setSpinnerItem(spin_AddStudent_District, beanStudent.getDistrict());
		arrayIndex.setSpinnerItem(spin_AddStudent_Sub_District_Village, beanStudent.getSubdist());
		}
		}
	}
	
	private BeanStudent btnForwordOnClick()
	{
		BeanStudent beanStudent = new BeanStudent();
		if(radiobtn_AddStudent_Male.isChecked())
		{
			beanStudent.setGender("M");
		}
		else
		{
			beanStudent.setGender("F");
		}
		beanStudent.setImgPath("");
		
		beanStudent.setStudent_ID(edit_AddStudent_StudentId.getText().toString());
		beanStudent.setF_Name(edit_AddStudent_FName .getText().toString());
		beanStudent.setL_Name(edit_AddStudent_LName.getText().toString());
		beanStudent.setDob(txt_AddStudent_DOB.getText().toString());
		beanStudent.setPhone(edit_AddStudent_Mobile.getText().toString());
		beanStudent.setEmail(edit_AddStudent_Email_Address.getText().toString());
		beanStudent.setAddress(edit_AddStudent_Address.getText().toString());
		
		int index = spin_AddStudent_Class.getSelectedItemPosition();
		String str[] = (String[]) spin_AddStudent_Class.getTag();
		beanStudent.setClass_Id(str[index]);
		
		beanStudent.setClass_Id(spin_AddStudent_Class.getSelectedItem().toString());

		beanStudent.setReligion(spin_AddStudent_Religion.getSelectedItem().toString());
		beanStudent.setState(spin_AddStudent_provience.getSelectedItem().toString());
		beanStudent.setCity(spin_AddStudent_City.getSelectedItem().toString());
		beanStudent.setDistrict(spin_AddStudent_District.getSelectedItem().toString());
		beanStudent.setSubdist(spin_AddStudent_Sub_District_Village.getSelectedItem().toString());
		
		beanStudent.setStudent_Pkey((String)edit_AddStudent_StudentId.getTag());
		String guardianStr = (String) edit_AddStudent_FName.getTag();
		if(guardianStr != null)
		{
			String guardian[] = guardianStr.split(context.getResources().getString(R.string.seperater));
			beanStudent.setFatherName(guardian[0]);
			beanStudent.setFatherDob(guardian[1]);
			beanStudent.setMotherName(guardian[2]);
			beanStudent.setMotherDob(guardian[3]);
			beanStudent.setGuardianEmail(guardian[4]);
			beanStudent.setGuardianPhone(guardian[5]);
		}

		return beanStudent;
	}
	
	private boolean checkValidation() {
        boolean ret = true;

        if (!Validation.hasText(edit_AddStudent_StudentId)) ret = false;
        if (!Validation.hasText(edit_AddStudent_FName)) ret = false;
        if (!Validation.hasText(edit_AddStudent_LName)) ret = false;
        if (!Validation.hasText(txt_AddStudent_DOB)) ret = false;
        if (!Validation.isPhoneNumber(edit_AddStudent_Mobile, true)) ret = false;
        if (!Validation.isEmailAddress(edit_AddStudent_Email_Address, true)) ret = false;
        if (!Validation.hasText(edit_AddStudent_Address)) ret = false;
        
        return ret;
    }

}