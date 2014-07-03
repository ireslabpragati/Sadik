package com.sadik.fragments;

import java.util.ArrayList;

import com.sadik.database.DatabaseHandler;
import com.sadik.interfaces.FragmentInterFace;
import com.sadik.interfaces.NavigationInterface;
import com.sadik.model.BeanClassInfo;
import com.sadik.model.BeanSchool;
import com.sadik.teacher.Alerts;
import com.sadik.teacher.ArrayIndex;
import com.sadik.teacher.HideKeypad;
import com.sadik.teacher.R;
import com.sadik.teacher.ToastCustom;

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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("ValidFragment")
public class ConfigureSchool2Fragment extends Fragment {
	
	LinearLayout layout_confschool2;
	Button btn_ConfSchool2_Back, btn_ConfSchool2_Done, btn_ConfSchool2_AddMoreSchool;
	
	NavigationInterface navigationInterface;
	FragmentInterFace fragmentInterFace;
	Activity context;
	TextView textHeader;
	String strHeader;
	BeanSchool beanSchool;
	ArrayList<BeanClassInfo> beanClassInfos;
	ArrayList<String> classIds;
    DatabaseHandler db;
    int numberOfSchool;

	
	public ConfigureSchool2Fragment(TextView textHeader, String strHeader,BeanSchool beanSchool, ArrayList<BeanClassInfo> beanClassInfos,int numberOfSchool) {
		// TODO Auto-generated constructor stub
		this.textHeader = textHeader;
		this.strHeader = strHeader;
		this.beanSchool = beanSchool;
		this.beanClassInfos = beanClassInfos;
		this.numberOfSchool = numberOfSchool;
	}
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		navigationInterface = (NavigationInterface) activity;
		fragmentInterFace = (FragmentInterFace) activity;
		this.context = activity;
		db = new DatabaseHandler(activity);
		textHeader.setText(strHeader);
		classIds = new ArrayList<String>();
	}
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, 
        Bundle savedInstanceState) {
        // Inflate the layout for this fragment
    	super.onCreateView(inflater, container, savedInstanceState);
    	View V = inflater.inflate(R.layout.layout_configure_school2, container, false);
    	
    	layout_confschool2 = (LinearLayout) V.findViewById(R.id.layout_confschool2);
    	btn_ConfSchool2_Back = (Button) V.findViewById(R.id.btn_ConfSchool2_Back);
    	btn_ConfSchool2_Done = (Button) V.findViewById(R.id.btn_ConfSchool2_Done);
    	btn_ConfSchool2_AddMoreSchool = (Button) V.findViewById(R.id.btn_ConfSchool2_AddMoreSchool);

    	for (int i = 0; i < beanClassInfos.size(); i++) {
    		
    			addClassLayout(beanClassInfos.get(i));
		}
    	
    	if(beanSchool.getSchoolPkey()!=null)
		{
    		btn_ConfSchool2_Done.setText(context.getResources().getString(R.string.Update));
    		btn_ConfSchool2_AddMoreSchool.setVisibility(View.VISIBLE);
		}
    	else
    	{
    		btn_ConfSchool2_Done.setText(context.getResources().getString(R.string.Done));
    		btn_ConfSchool2_AddMoreSchool.setVisibility(View.GONE);
    	}
		if(numberOfSchool >= 2)
		{
    		btn_ConfSchool2_AddMoreSchool.setVisibility(View.GONE);			
		}
    	
    	btn_ConfSchool2_Back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//navigationInterface.backPress();
				fragmentInterFace.startConfigureSchool1Fragment(false);
			}
		});
    	
    	btn_ConfSchool2_AddMoreSchool.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				fragmentInterFace.startConfigureSchool1Fragment(true);
			}
		});
    	
    	btn_ConfSchool2_Done.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				boolean result = AddSchool(beanSchool);
				if(result)
				{
					ToastCustom.makeText(context, context.getResources().getString(R.string.Data_Successfully_Saved), Toast.LENGTH_SHORT);
					// navigationInterface.backPress();
					navigationInterface.setSchoolButtons();
					fragmentInterFace.startConfigureSchool1Fragment(false);
				}
				else
				{
					ToastCustom.makeText(context, "Duplicate subject exists. Please remove duplicacy.", Toast.LENGTH_SHORT);
				}
				
			}
		});
    	
    	return V;
    	
    }
    
   private void addClassLayout(BeanClassInfo classInfo)
    {
		LayoutInflater inflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
		
		final View convertView = inflater.inflate(R.layout.layout_sub_class_school, null);
		
		TextView txt_ClassSubject_ClassName = (TextView) convertView.findViewById(R.id.txt_ClassSubject_ClassName);
		Spinner spin_ClassSubject_Subject = (Spinner) convertView.findViewById(R.id.spin_ClassSubject_Subject);
		Button btn_ClassSubject_More = (Button) convertView.findViewById(R.id.btn_ClassSubject_More);
		final LinearLayout layout_ClassSubject = (LinearLayout) convertView.findViewById(R.id.layout_ClassSubject);

		txt_ClassSubject_ClassName.setText(classInfo.getClassStandard()+" "+classInfo.getSection());
		layout_ClassSubject.setTag(classInfo.getSection());
		txt_ClassSubject_ClassName.setTag(classInfo.getClassStandard());
		btn_ClassSubject_More.setTag(classInfo.getClassId());
		
		Log.i("Class id", "tag "+classInfo.getClassId());
		
		if(classInfo.getSubjects() != null)
		{
		String subject[] = classInfo.getSubjects().split(context.getResources().getString(R.string.seperater));
		for (int i = 0; i < subject.length; i++) {
			if(i == 0)
			{
				ArrayIndex arrayIndex = new ArrayIndex();
			arrayIndex.setSpinnerItem(spin_ClassSubject_Subject, subject[i]);
			}
			else
			{
			addSubjectLayout(layout_ClassSubject, subject[i]);
			}
		}
		}
		
		btn_ClassSubject_More.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				addSubjectLayout(layout_ClassSubject, null);
			}
		});
		
		layout_confschool2.addView(convertView);
    }

   private void addSubjectLayout(final LinearLayout linearLayout, String subject)
   {
		LayoutInflater inflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
		
		final View convertView = inflater.inflate(R.layout.layout_more_subject, null);
		
		Spinner spin_ClassSubject_Subject = (Spinner) convertView.findViewById(R.id.spin_ClassSubject_Subject);
		Button btn_ClassSubject_More = (Button) convertView.findViewById(R.id.btn_ClassSubject_More);

		btn_ClassSubject_More.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Alerts.removeView(context, convertView, linearLayout);
			}
		});
		
		if(subject!=null)
		{
			ArrayIndex arrayIndex = new ArrayIndex();
			arrayIndex.setSpinnerItem(spin_ClassSubject_Subject, subject);
		}
		
		linearLayout.addView(convertView);
   }

   
   @Override
    public void onPause() {
    	// TODO Auto-generated method stub
    	super.onPause();
    	HideKeypad.hideSoftKeyboard(context);
    }
   
   private boolean AddSchool(BeanSchool beanSchool)
   {
	   boolean response = true;
		String result = db.insertOrUpdate_School(beanSchool);
		if(!result.equals(""))
		{
			if(beanSchool.getSchoolPkey()!=null)
			{
				//db.Delete_Class(beanSchool.getSchoolPkey());
			}
			
			response=  addClass(result);
		}
		return response;
   }

   private boolean addClass(String Spkey)
   {
	   boolean overallduplicasy = false;
	   ArrayList<BeanClassInfo> beanClassInfos = new ArrayList<BeanClassInfo>();
	   for (int i = 0; i < layout_confschool2.getChildCount(); i++) {
		   View view1 = layout_confschool2.getChildAt(i);
		   LinearLayout linearLayout = (LinearLayout) view1.findViewById(R.id.layout_ClassSubject);
		   TextView txt_ClassSubject_ClassName = (TextView) view1.findViewById(R.id.txt_ClassSubject_ClassName);
		   Button btn_ClassSubject_More1 = (Button) view1.findViewById(R.id.btn_ClassSubject_More);
		   
		   String allSubject = "";
		   
		   boolean duplicasy = false;
		   
		   for (int j = 0; j < linearLayout.getChildCount(); j++) {
			   View view2 = linearLayout.getChildAt(j);
			   Spinner spin_ClassSubject_Subject = (Spinner) view2.findViewById(R.id.spin_ClassSubject_Subject);
			   String str = spin_ClassSubject_Subject.getSelectedItem().toString();
           	if(j==0)
           	{
           		allSubject = str;
           	}
           	else
           	{
           		duplicasy = allSubject.contains(str);
           		allSubject += context.getResources().getString(R.string.seperater) +str;
           	}

		}
		   
		   BeanClassInfo beanClassInfo = new BeanClassInfo();
		   beanClassInfo.setClassStandard(txt_ClassSubject_ClassName.getTag().toString());
		   beanClassInfo.setSection(linearLayout.getTag().toString());
		   beanClassInfo.setSchoolPkey(Spkey);
		   beanClassInfo.setSubjects(allSubject);
		   beanClassInfo.setClassId((String)btn_ClassSubject_More1.getTag());

		   if((String)btn_ClassSubject_More1.getTag()!=null)
		   {
			   classIds.add((String)btn_ClassSubject_More1.getTag());
			   
			   Log.i("Hello", ":"+(String)btn_ClassSubject_More1.getTag());
		   }
		   beanClassInfos.add(beanClassInfo);
		   overallduplicasy = duplicasy || overallduplicasy;
		   
	}
	   if(!overallduplicasy)
	   {
		   if(classIds.size()>0)
		   {
			   String[] classArr = new String[classIds.size()];
			   classArr = classIds.toArray(classArr);
			   db.delete_Class(classArr, beanSchool.getSchoolPkey());
		   }
		   
		   for (int i = 0; i < beanClassInfos.size(); i++) {
			   db.insertOrUpdate_ClassBasicInfo(beanClassInfos.get(i));
		}
	   }

	   return !overallduplicasy;
   }
   
   
}