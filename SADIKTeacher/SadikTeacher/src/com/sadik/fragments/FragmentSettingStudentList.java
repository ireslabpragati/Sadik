package com.sadik.fragments;

import java.util.ArrayList;

import com.sadik.adapter.AdapterStudent;
import com.sadik.database.DatabaseHandler;
import com.sadik.interfaces.FragmentInterFace;
import com.sadik.model.BeanSchool;
import com.sadik.model.BeanStudent;
import com.sadik.teacher.R;
import com.sadik.teacher.ToastFormError;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

@SuppressLint("ValidFragment")
public class FragmentSettingStudentList extends Fragment {

	ListView list_StudentList;
	Button btn_StudentList_Forword;
	
	DatabaseHandler db;
	FragmentInterFace fragmentInterFace;
	Activity context;
	TextView textHeader, clickedText;
	String strHeader, schoolPkey;
	
	@SuppressLint("ValidFragment")
	public FragmentSettingStudentList(TextView textHeader, String strHeader) {
		// TODO Auto-generated constructor stub
		this.textHeader = textHeader;
		this.strHeader = strHeader;
	}
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		this.context = activity;
		db = new DatabaseHandler(activity);
		fragmentInterFace = (FragmentInterFace) activity;
		textHeader.setText(strHeader);
	}
	

	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, 
        Bundle savedInstanceState) {
    	
    	super.onCreateView(inflater, container, savedInstanceState);
    	
    	View v = inflater.inflate(R.layout.layout_stutent_list, container, false);
    	
    	list_StudentList = (ListView) v.findViewById(R.id.list_StudentList);
    	btn_StudentList_Forword = (Button) v.findViewById(R.id.btn_StudentList_Forword);
    	
		clickedText = (TextView) textHeader.getTag();

		setData();
		clickedText.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setData();
			}
		});

    	
    	btn_StudentList_Forword.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(schoolPkey!=null)
				{
				fragmentInterFace.startFragmentSettingStudentProfile("",schoolPkey);
				}
				else
				{
					ToastFormError.schoolNotconfigured(context);
				}
			}
		});
    	
    	list_StudentList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				TextView txt_studentList_StudentId = (TextView) arg1.findViewById(R.id.txt_studentList_StudentId);
				String StudentPKey = txt_studentList_StudentId.getTag().toString();
				fragmentInterFace.startFragmentSettingStudentProfile(StudentPKey, schoolPkey);
			}
		});
    	
        return v;
    }
	
	  private void setData()
	    {
			BeanSchool beanSchool = (BeanSchool) clickedText.getTag();
			if(beanSchool != null)
			{
				schoolPkey = beanSchool.getSchoolPkey();
				ArrayList<BeanStudent> beanStudents = db.getAllStudentListData(schoolPkey);
		    	list_StudentList.setAdapter(new AdapterStudent(context, beanStudents));
			}
			else
			{
		    	ToastFormError.schoolNotconfigured(context);
			}

	    }
	    
}