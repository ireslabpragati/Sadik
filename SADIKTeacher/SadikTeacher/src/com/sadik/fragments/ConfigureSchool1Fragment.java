package com.sadik.fragments;

import java.util.ArrayList;
import com.sadik.database.DatabaseHandler;
import com.sadik.interfaces.FragmentInterFace;
import com.sadik.model.BeanClassInfo;
import com.sadik.model.BeanSchool;
import com.sadik.teacher.Alerts;
import com.sadik.teacher.ArrayIndex;
import com.sadik.teacher.HideKeypad;
import com.sadik.teacher.R;
import com.sadik.teacher.ToastCustom;
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
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("ValidFragment")
public class ConfigureSchool1Fragment extends Fragment {
	
	EditText edit_ConfSchool_School_ID, edit_ConfSchool_Address, 
	edit_ConfSchool_Zipcode;
	TextView txt_ConfSchool_Effective_Days;
	
	Spinner spin_ConfSchool_Provience, spin_ConfSchool_City, spin_ConfSchool_Standard,
	spin_ConfSchool_Section, spin_ConfSchool_Period_Count, spin_ConfSchool_District,
	spin_ConfSchool_SubDist;
	LinearLayout layout_configure_class;
	Button btn_ConfSchool_More, btn_ConfSchool_Forword;
	
	String SpKey = null;
	Activity context;
	TextView textHeader, clickedText;
	String strHeader;
	FragmentInterFace fragmentInterFace;
	DatabaseHandler db;
	boolean addmore;
	
	public ConfigureSchool1Fragment(TextView textHeader, String strHeader, boolean addmore) {
		// TODO Auto-generated constructor stub
		this.textHeader = textHeader;
		this.strHeader = strHeader;
		this.addmore = addmore;
	}
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		this.context = activity;
		textHeader.setText(strHeader);
		fragmentInterFace = (FragmentInterFace) activity;
		db = new DatabaseHandler(activity);
	}
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, 
        Bundle savedInstanceState) {
        // Inflate the layout for this fragment
    	super.onCreateView(inflater, container, savedInstanceState);
    	View V = inflater.inflate(R.layout.layout_configure_school1, container, false);
    	
    	edit_ConfSchool_School_ID = (EditText) V.findViewById(R.id.edit_ConfSchool_School_ID);
    	edit_ConfSchool_Address = (EditText) V.findViewById(R.id.edit_ConfSchool_Address);
    	edit_ConfSchool_Zipcode = (EditText) V.findViewById(R.id.edit_ConfSchool_Zipcode);
    	txt_ConfSchool_Effective_Days = (TextView) V.findViewById(R.id.txt_ConfSchool_Effective_Days);
    	spin_ConfSchool_Provience = (Spinner) V.findViewById(R.id.spin_ConfSchool_Provience);
    	spin_ConfSchool_City = (Spinner) V.findViewById(R.id.spin_ConfSchool_City);
    	spin_ConfSchool_District = (Spinner) V.findViewById(R.id.spin_ConfSchool_District);
    	spin_ConfSchool_SubDist = (Spinner) V.findViewById(R.id.spin_ConfSchool_SubDist);
    	spin_ConfSchool_Standard = (Spinner) V.findViewById(R.id.spin_ConfSchool_Standard);
    	spin_ConfSchool_Section = (Spinner) V.findViewById(R.id.spin_ConfSchool_Section);
    	spin_ConfSchool_Period_Count = (Spinner) V.findViewById(R.id.spin_ConfSchool_Period_Count);
    	layout_configure_class = (LinearLayout) V.findViewById(R.id.layout_configure_class);
    	btn_ConfSchool_More = (Button) V.findViewById(R.id.btn_ConfSchool_More);
    	btn_ConfSchool_Forword = (Button) V.findViewById(R.id.btn_ConfSchool_Forword);
    	
		clickedText = (TextView) textHeader.getTag();
		clickedText.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				BeanSchool beanSchool = (BeanSchool) clickedText.getTag();
				if(beanSchool != null)
				{
					layout_configure_class.removeViews(1, layout_configure_class.getChildCount()-1);
					setData(beanSchool);
				}
			}
		});
		
		BeanSchool beanSchool = (BeanSchool) clickedText.getTag();

		if(beanSchool != null && !addmore) // if(!(beanSchool == null || addmore))
		{
			setData(beanSchool);
		}
		
    	txt_ConfSchool_Effective_Days.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Alerts.alertRecurrance(context, txt_ConfSchool_Effective_Days, context.getResources().getString(R.string.Effective_Days));
			}
		});
    	
	btn_ConfSchool_More.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				addBottomLayout(null);
			}
		});
	
	btn_ConfSchool_Forword.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			if(checkValidation())
			{
			//String result = db.getPrimaryKeybyId(edit_ConfSchool_School_ID.getText().toString());
			//if(SpKey!=null || result.equals(""))
			{
				BeanSchool beanSchool = new BeanSchool();
				beanSchool.setSchoolId(edit_ConfSchool_School_ID.getText().toString());
				beanSchool.setAddress(edit_ConfSchool_Address.getText().toString());
				beanSchool.setState(spin_ConfSchool_Provience.getSelectedItem().toString());
				beanSchool.setCity(spin_ConfSchool_City.getSelectedItem().toString());
				beanSchool.setDistrict(spin_ConfSchool_District.getSelectedItem().toString());
				beanSchool.setSubdist(spin_ConfSchool_SubDist.getSelectedItem().toString());
				beanSchool.setZipCode(edit_ConfSchool_Zipcode.getText().toString());
				beanSchool.setPeriod_Count(spin_ConfSchool_Period_Count.getSelectedItemPosition()+1);
				beanSchool.setEffective_Days(txt_ConfSchool_Effective_Days.getText().toString().replace(",", context.getResources().getString(R.string.seperater)));
				
				beanSchool.setSchoolPkey(SpKey);
				
				ArrayList<BeanClassInfo> beanClasses = new ArrayList<BeanClassInfo>();
				ArrayList<String> strings = new ArrayList<String>();
				
				boolean duplicasy = false;
				
				for (int i = 0; i < layout_configure_class.getChildCount(); i++) {
					
					BeanClassInfo beanClass = new BeanClassInfo();
					LinearLayout linearLayout = (LinearLayout) layout_configure_class.getChildAt(i);
					
					Spinner spinStandard = (Spinner)linearLayout.findViewById(R.id.spin_ConfSchool_Standard);
					String standard = spinStandard.getSelectedItem().toString();
					String section = ((Spinner)linearLayout.findViewById(R.id.spin_ConfSchool_Section)).getSelectedItem().toString();
					if(spinStandard.getTag()!=null)
					{
					beanClass.setClassId(spinStandard.getTag().toString());
					}
					beanClass.setClassStandard(standard);
					beanClass.setSection(section);
					beanClass.setSubjects((String)((Spinner)linearLayout.findViewById(R.id.spin_ConfSchool_Section)).getTag());
					
					linearLayout.findViewById(R.id.spin_ConfSchool_Section);
					
					beanClasses.add(beanClass);
					
					if(strings.indexOf(standard+""+section)==-1)
					{
						strings.add(standard+""+section);
					}
					else
					{
						duplicasy = true;
					}
				}
				if(duplicasy)
				{
					ToastCustom.makeText(context, "Duplicate class exists. Please remove duplicacy.", Toast.LENGTH_SHORT);
				}
				else
				{
					fragmentInterFace.startConfigureSchool2Fragment(beanSchool, beanClasses);
				}
				
			}
		}
		}
	});

    	return V;

    }

   private void addBottomLayout(BeanClassInfo bean)
    {
		LayoutInflater inflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
		
		final View convertView = inflater.inflate(R.layout.layout_more_class, null);
		
		Spinner spin_ConfSchool_Standard1 = (Spinner) convertView.findViewById(R.id.spin_ConfSchool_Standard);
		Spinner spin_ConfSchool_Section1 = (Spinner) convertView.findViewById(R.id.spin_ConfSchool_Section);
		Button btn_ConfSchool_More_Remove = (Button) convertView.findViewById(R.id.btn_ConfSchool_More_Remove);
		
		setClassData(spin_ConfSchool_Standard1, spin_ConfSchool_Section1, bean);
		
		btn_ConfSchool_More_Remove.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				Alerts.removeView(context, convertView, layout_configure_class);
				//layout_configure_class.removeView(convertView);
			}
		});
		
		layout_configure_class.addView(convertView);
    }
   
   private void setClassData(Spinner spinnerStandard, Spinner spinnerSectionIndex, BeanClassInfo bean)
   {
		if(bean != null)
		{
			ArrayIndex arrayIndex = new ArrayIndex();
			arrayIndex.setSpinnerItem(spinnerStandard, bean.getClassStandard());
			arrayIndex.setSpinnerItem(spinnerSectionIndex, bean.getSection());
		spinnerStandard.setTag(bean.getClassId());
		spinnerSectionIndex.setTag(bean.getSubjects());
		
		Log.i("class "+bean.getClassStandard(), bean.getClassId()+" section "+bean.getSection());
		}

   }

    @Override
    public void onPause() {
    	// TODO Auto-generated method stub
    	super.onPause();
    	HideKeypad.hideSoftKeyboard(context);
    }
    
    private void setData(BeanSchool beanSchool)
    {
    	SpKey = beanSchool.getSchoolPkey();
    	beanSchool.getAddress();
    	
    	edit_ConfSchool_School_ID.setText(beanSchool.getSchoolId());
    	edit_ConfSchool_School_ID.setTag(SpKey);
    	edit_ConfSchool_Address.setText(beanSchool.getAddress());
    	edit_ConfSchool_Zipcode.setText(beanSchool.getZipCode());
    	txt_ConfSchool_Effective_Days.setText(beanSchool.getEffective_Days()
    			.replace(context.getResources().getString(R.string.seperater), ","));

		ArrayIndex arrayIndex = new ArrayIndex();

		arrayIndex.setSpinnerItem(spin_ConfSchool_Provience, beanSchool.getState());
		arrayIndex.setSpinnerItem(spin_ConfSchool_City, beanSchool.getCity());
		arrayIndex.setSpinnerItem(spin_ConfSchool_District, beanSchool.getDistrict());
		arrayIndex.setSpinnerItem(spin_ConfSchool_SubDist, beanSchool.getSubdist());
//    	ArrayIndex.setSpinnerItem(spin_ConfSchool_Period_Count, "4");
		arrayIndex.setSpinnerItem(spin_ConfSchool_Period_Count, ""+beanSchool.getPeriod_Count());
  
    	
    	// spin_ConfSchool_Section, spin_ConfSchool_Period_Count;
    	
    	ArrayList<BeanClassInfo> beanClassInfos= db.Get_classInfoDetails(beanSchool.getSchoolPkey());
    	Log.i("child count", ": "+beanClassInfos.size());
    	for (int i = 0; i < beanClassInfos.size(); i++) {
    		
    		if(i == 0)
    		{
    			setClassData(spin_ConfSchool_Standard, spin_ConfSchool_Section, beanClassInfos.get(i));
    		}
    		else
    		{
    			addBottomLayout(beanClassInfos.get(i));
    		}
			
		}
    }
    
    private boolean checkValidation() {
        boolean ret = true;

        if (!Validation.hasText(edit_ConfSchool_School_ID)) ret = false;
        if (!Validation.hasText(edit_ConfSchool_Address)) ret = false;
        if (!Validation.hasText(edit_ConfSchool_Zipcode)) ret = false;
        if (!Validation.hasText(txt_ConfSchool_Effective_Days)) ret = false;
        
        return ret;
    }

    
}