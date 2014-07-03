package com.sadik.fragments;

import java.util.ArrayList;

import com.sadik.database.DatabaseHandler;
import com.sadik.interfaces.FragmentInterFace;
import com.sadik.interfaces.RemoveViewInterface;
import com.sadik.model.BeanGradeCategory;
import com.sadik.model.BeanSchool;
import com.sadik.teacher.Alerts;
import com.sadik.teacher.HideKeypad;
import com.sadik.teacher.R;
import com.sadik.teacher.ToastCustom;
import com.sadik.teacher.ToastFormError;
import com.sadik.teacher.ToastUD;
import com.sadik.validation.Validation;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("ValidFragment")
public class FragmentSettingGradeCategory extends Fragment {

	EditText edit_setting_grade_category_Name;
	EditText edit_setting_grade_category_Weightage;
	TextView txt_setting_grade_category_next;
	Button btn_setting_grade_category_Add;
	LinearLayout layout_setting_grade_category_List;
	BeanGradeCategory beanGradeCategory;
	ArrayList<BeanGradeCategory> beanGradeCategoryList;
	DatabaseHandler databaseHandler;
	FragmentInterFace fragmentInterFace;
	TextView clickedText;
	String schoolPkey=null;

	Activity context;
	TextView textHeader;
	String strHeader;

	public FragmentSettingGradeCategory(TextView textHeader, String strHeader) {
		// TODO Auto-generated constructor stub
		this.textHeader = textHeader;
		this.strHeader = strHeader;

	}

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		this.context = activity;
		fragmentInterFace = (FragmentInterFace) activity;
		textHeader.setText(strHeader);
		databaseHandler = new DatabaseHandler(activity);
		beanGradeCategory = new BeanGradeCategory();
		beanGradeCategoryList = new ArrayList<BeanGradeCategory>();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, 
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		super.onCreateView(inflater, container, savedInstanceState);
		View V = inflater.inflate(R.layout.layout_setting_grade_category, container, false);

		edit_setting_grade_category_Name = (EditText) V.findViewById(R.id.edit_setting_grade_category_Name);
		edit_setting_grade_category_Weightage = (EditText) V.findViewById(R.id.edit_setting_grade_category_Weightage);
		txt_setting_grade_category_next = (TextView) V.findViewById(R.id.txt_setting_grade_category_next);
		btn_setting_grade_category_Add = (Button) V.findViewById(R.id.btn_setting_grade_category_Add);
		layout_setting_grade_category_List = (LinearLayout) V.findViewById(R.id.layout_setting_grade_category_List);

		clickedText = (TextView) textHeader.getTag();

		setData();
		clickedText.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setData();
			}
		});

		edit_setting_grade_category_Name.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				Validation.hasText(edit_setting_grade_category_Name);
			}
		});

		edit_setting_grade_category_Weightage.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				Validation.hasText(edit_setting_grade_category_Weightage);
			}
		});

		txt_setting_grade_category_next.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				fragmentInterFace.startFragmentSettingGradeType();
			}
		});


		btn_setting_grade_category_Add.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(checkValidation()) {
					if(schoolPkey != null)  {
					beanGradeCategory = new BeanGradeCategory();
					beanGradeCategory.setGrade_category_name(edit_setting_grade_category_Name.getText().toString());
					beanGradeCategory.setGrade_category_percentage(Float.parseFloat(edit_setting_grade_category_Weightage.getText().toString()));
					beanGradeCategory.setSchoolPkey(schoolPkey);
					int grade_category_id = databaseHandler.Add_Grade_Category(beanGradeCategory);
					if(grade_category_id>0) {
						beanGradeCategory.setGrade_category_id(grade_category_id);
						addClassLayout(beanGradeCategory);
						edit_setting_grade_category_Name.setText("");
						edit_setting_grade_category_Weightage.setText("");
						ToastCustom.makeText(context, context.getResources().getString(R.string.Data_Successfully_Saved), Toast.LENGTH_SHORT);
						//ToastUD.show(context);
					}
					}
					else {
						ToastFormError.schoolNotconfigured(context);
					}

				}
				else {
					ToastFormError.show(context);
				}
			}
		});

		return V;

	}

	private void addClassLayout(final BeanGradeCategory beanGradeCategory)
	{
		LayoutInflater inflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

		final View convertView = inflater.inflate(R.layout.layout_sub_setting_grade_category, null);

		final TextView txt_sub_setting_grade_category_Name = (TextView) convertView.findViewById(R.id.txt_sub_setting_grade_category_Name);
		TextView txt_sub_setting_grade_category_Weightage = (TextView) convertView.findViewById(R.id.txt_sub_setting_grade_category_Weightage);
		LinearLayout layout_sub_setting_grade_category_cross = (LinearLayout) convertView.findViewById(R.id.layout_sub_setting_grade_category_cross);
		txt_sub_setting_grade_category_Name.setText(beanGradeCategory.getGrade_category_name());
		txt_sub_setting_grade_category_Weightage.setText(beanGradeCategory.getGrade_category_percentage()+" %");
		layout_sub_setting_grade_category_cross.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				RemoveViewInterface removeViewInterface = new RemoveViewInterface() {

					@Override
					public boolean remove() {
						return databaseHandler.Delete_Grade_Category(beanGradeCategory.getGrade_category_id(),beanGradeCategory.getSchoolPkey());
						// TODO Auto-generated method stub

					}
				};

				Alerts.removeView(context, convertView, layout_setting_grade_category_List, removeViewInterface);

			}
		});

		layout_setting_grade_category_List.addView(convertView);
	}


	private void setData()
	{
		layout_setting_grade_category_List.removeAllViews();
		BeanSchool beanSchool = (BeanSchool) clickedText.getTag();
		if(beanSchool != null)
		{
		schoolPkey = beanSchool.getSchoolPkey();
		beanGradeCategory = new BeanGradeCategory();
		beanGradeCategoryList = new ArrayList<BeanGradeCategory>();
		beanGradeCategoryList = databaseHandler.Get_Grade_Category(schoolPkey);
		for (int i = 0; i < beanGradeCategoryList.size(); i++) {
			addClassLayout(beanGradeCategoryList.get(i));
		}
		}
		else
		{
			ToastFormError.schoolNotconfigured(context);
		}

	}



	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		HideKeypad.hideSoftKeyboard(context);
	}

	private boolean checkValidation() {
		boolean ret = true;

		if (!Validation.hasText(edit_setting_grade_category_Name)) ret = false;
		if (!Validation.hasText(edit_setting_grade_category_Weightage)) ret = false;

		return ret;
	}

}