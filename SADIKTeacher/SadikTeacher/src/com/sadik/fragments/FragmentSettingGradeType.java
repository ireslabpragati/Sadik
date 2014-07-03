package com.sadik.fragments;

import com.sadik.teacher.Alerts;
import com.sadik.teacher.HideKeypad;
import com.sadik.teacher.R;
import com.sadik.teacher.ToastUD;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
public class FragmentSettingGradeType extends Fragment {
	
	EditText edit_setting_grade_type_point_min, edit_setting_grade_type_point_max,
	edit_setting_grade_type_score_min, edit_setting_grade_type_score_max,
	edit_setting_grade_type_letter_min, edit_setting_grade_type_letter_max,
	edit_setting_grade_type_letter_name;
	
	Button btn_setting_grade_type_done, btn_setting_grade_type_More;
	
	LinearLayout layout_setting_grade_type_List;
	
	
	Activity context;
	TextView textHeader;
	String strHeader;
	
	public FragmentSettingGradeType(TextView textHeader, String strHeader) {
		// TODO Auto-generated constructor stub
		this.textHeader = textHeader;
		this.strHeader = strHeader;
		
	}
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		this.context = activity;
		textHeader.setText(strHeader);

	}
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, 
        Bundle savedInstanceState) {
        // Inflate the layout for this fragment
    	super.onCreateView(inflater, container, savedInstanceState);
    	View V = inflater.inflate(R.layout.layout_setting_grade_type, container, false);
    	
    	edit_setting_grade_type_point_min = (EditText) V.findViewById(R.id.edit_setting_grade_type_point_min);
    	edit_setting_grade_type_point_max = (EditText) V.findViewById(R.id.edit_setting_grade_type_point_max);

    	edit_setting_grade_type_score_min = (EditText) V.findViewById(R.id.edit_setting_grade_type_score_min);
    	edit_setting_grade_type_score_max = (EditText) V.findViewById(R.id.edit_setting_grade_type_score_max);

    	edit_setting_grade_type_letter_min = (EditText) V.findViewById(R.id.edit_setting_grade_type_letter_min);
    	edit_setting_grade_type_letter_max = (EditText) V.findViewById(R.id.edit_setting_grade_type_letter_max);
    	edit_setting_grade_type_letter_name = (EditText) V.findViewById(R.id.edit_setting_grade_type_letter_name);

    	layout_setting_grade_type_List = (LinearLayout) V.findViewById(R.id.layout_setting_grade_type_List);
    	
    	btn_setting_grade_type_done = (Button) V.findViewById(R.id.btn_setting_grade_type_done);
    	btn_setting_grade_type_More = (Button) V.findViewById(R.id.btn_setting_grade_type_More);
    	
    	btn_setting_grade_type_More.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				addClassLayout();
			}
		});
    	
    	btn_setting_grade_type_done.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ToastUD.show(context);
			}
		});
    	
    	
    	
    	
		

    	return V;
    	
    }
    
    private void addClassLayout()
    {
		LayoutInflater inflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
		
		final View convertView = inflater.inflate(R.layout.layout_sub_setting_grade_type_letter, null);
		
		EditText edit_sub_setting_grade_type_letter_min = (EditText) convertView.findViewById(R.id.edit_sub_setting_grade_type_letter_min);
		EditText edit_sub_setting_grade_type_letter_max = (EditText) convertView.findViewById(R.id.edit_sub_setting_grade_type_letter_max);
		EditText edit_sub_setting_grade_type_letter_name = (EditText) convertView.findViewById(R.id.edit_sub_setting_grade_type_letter_name);
		Button btn_sub_setting_grade_type_letter_Remove = (Button) convertView.findViewById(R.id.btn_sub_setting_grade_type_letter_Remove);
		btn_sub_setting_grade_type_letter_Remove.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Alerts.removeView(context, convertView, layout_setting_grade_type_List);
			}
		});
		layout_setting_grade_type_List.addView(convertView);
    }
    
    

   @Override
    public void onPause() {
    	// TODO Auto-generated method stub
    	super.onPause();
    	HideKeypad.hideSoftKeyboard(context);
    }
   

}