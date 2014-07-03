package com.sadik.fragments;

import java.util.ArrayList;

import com.sadik.database.DatabaseHandler;
import com.sadik.model.BeanClassInfo;
import com.sadik.model.BeanSchool;
import com.sadik.teacher.HideKeypad;
import com.sadik.teacher.R;
import com.sadik.teacher.ToastFormError;
import com.sadik.teacher.ToastUD;
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
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

@SuppressLint("ValidFragment")
public class ClassRoomMgtFragment extends Fragment {
	
	TextView txtSettingClassSchoolId;
	LinearLayout layout_classroom_mgt;
	Button btn_classroom_mgt_Proceed;
	
	DatabaseHandler db;
	Activity context;
	TextView textHeader, clickedText;
	String strHeader;
	
	public ClassRoomMgtFragment(TextView textHeader, String strHeader) {
		// TODO Auto-generated constructor stub
		this.textHeader = textHeader;
		this.strHeader = strHeader;
	}
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		db = new DatabaseHandler(activity);
		this.context = activity;
		textHeader.setText(strHeader);

	}
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, 
        Bundle savedInstanceState) {
        // Inflate the layout for this fragment
    	super.onCreateView(inflater, container, savedInstanceState);
    	View V = inflater.inflate(R.layout.layout_classroom_management, container, false);
    	
    	txtSettingClassSchoolId = (TextView) V.findViewById(R.id.txtSettingClassSchoolId);
    	layout_classroom_mgt = (LinearLayout) V.findViewById(R.id.layout_classroom_mgt);
    	btn_classroom_mgt_Proceed = (Button) V.findViewById(R.id.btn_classroom_mgt_Proceed);

		clickedText = (TextView) textHeader.getTag();

		setData();
		clickedText.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setData();
			}
		});
		
    	btn_classroom_mgt_Proceed.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onButtonClick();
			}
		});
    	
    	return V;
    	
    }

    private void setData()
    {
    	layout_classroom_mgt.removeAllViews();
		BeanSchool beanSchool = (BeanSchool) clickedText.getTag();
		if(beanSchool != null)
		{
			txtSettingClassSchoolId.setText(beanSchool.getSchoolId());
			ArrayList<BeanClassInfo> beanClassInfos= db.Get_classInfoDetails(beanSchool.getSchoolPkey());
			
	    	for (int i = 0; i < beanClassInfos.size(); i++) {
	        	addClassMgtSub(beanClassInfos.get(i));
			}
	    	Log.i("bean school", "true");
		}
		else
		{
	    	ToastFormError.schoolNotconfigured(context);
		}

    }
    
   private void addClassMgtSub(BeanClassInfo bean)
    {
		LayoutInflater inflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
		
		final View convertView = inflater.inflate(R.layout.layout_sub_classcapacity, null);
		
		TextView txt_ClassMgtSub_ClassName = (TextView) convertView.findViewById(R.id.txt_ClassMgtSub_ClassName);
		EditText edit_ClassMgtSub_Capacity = (EditText) convertView.findViewById(R.id.edit_ClassMgtSub_Capacity);
		EditText edit_ClassMgtSub_Adviser_Name = (EditText) convertView.findViewById(R.id.edit_ClassMgtSub_Adviser_Name);
		EditText edit_ClassMgtSub_row = (EditText) convertView.findViewById(R.id.edit_ClassMgtSub_row);
		EditText edit_ClassMgtSub_Column = (EditText) convertView.findViewById(R.id.edit_ClassMgtSub_Column);

		txt_ClassMgtSub_ClassName.setText(bean.getClassStandard()+" "+bean.getSection());
		txt_ClassMgtSub_ClassName.setTag(bean.getClassId());
		
		edit_ClassMgtSub_Capacity.setText(bean.getCapacity());
		edit_ClassMgtSub_Adviser_Name.setText(bean.getAdviserName());
		edit_ClassMgtSub_row.setText(bean.getRow());
		edit_ClassMgtSub_Column.setText(bean.getColumn());
		
		layout_classroom_mgt.addView(convertView);
    }

  private void onButtonClick()
  {
	  boolean validate = true;
	  
	  ArrayList<BeanClassInfo> beans = new ArrayList<BeanClassInfo>();
	  
	  int size = layout_classroom_mgt.getChildCount();
	  for (int i = 0; i < size; i++) {
		
		BeanClassInfo bean = new BeanClassInfo();
		  
		View convertView = layout_classroom_mgt.getChildAt(i);
		TextView txt_ClassMgtSub_ClassName = (TextView) convertView.findViewById(R.id.txt_ClassMgtSub_ClassName);
		EditText edit_ClassMgtSub_Capacity = (EditText) convertView.findViewById(R.id.edit_ClassMgtSub_Capacity);
		EditText edit_ClassMgtSub_Adviser_Name = (EditText) convertView.findViewById(R.id.edit_ClassMgtSub_Adviser_Name);
		EditText edit_ClassMgtSub_row = (EditText) convertView.findViewById(R.id.edit_ClassMgtSub_row);
		EditText edit_ClassMgtSub_Column = (EditText) convertView.findViewById(R.id.edit_ClassMgtSub_Column);

		bean.setClassId((String)txt_ClassMgtSub_ClassName.getTag());
		bean.setAdviserName(edit_ClassMgtSub_Adviser_Name.getText().toString());
		
		bean.setCapacity(edit_ClassMgtSub_Capacity.getText().toString());
		bean.setRow(edit_ClassMgtSub_row.getText().toString());
		bean.setColumn(edit_ClassMgtSub_Column.getText().toString());

		beans.add(bean);
		
		validate = (validate && checkValidation(edit_ClassMgtSub_Capacity, edit_ClassMgtSub_Adviser_Name, edit_ClassMgtSub_row, edit_ClassMgtSub_Column));
		
	}
	  if(validate)
	  {
		  for (int i = 0; i < beans.size(); i++) {
			db.update_ClassCapacityInfo(beans.get(i));
		}
		  
	  }
  }

   @Override
    public void onPause() {
    	// TODO Auto-generated method stub
    	super.onPause();
    	HideKeypad.hideSoftKeyboard(context);
    }

   private boolean checkValidation(EditText edit_ClassMgtSub_Capacity, EditText edit_ClassMgtSub_Adviser_Name,
		   EditText edit_ClassMgtSub_row, EditText edit_ClassMgtSub_Column)
   {
       boolean ret = true;

       if (!Validation.hasText(edit_ClassMgtSub_Capacity)) ret = false;
       if (!Validation.hasText(edit_ClassMgtSub_Adviser_Name)) ret = false;
       if (!Validation.hasText(edit_ClassMgtSub_row)) ret = false;
       if (!Validation.hasText(edit_ClassMgtSub_Column)) ret = false;
       
       return ret;
   }
   
}