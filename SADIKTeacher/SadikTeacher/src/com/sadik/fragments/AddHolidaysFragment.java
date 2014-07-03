package com.sadik.fragments;

import java.util.ArrayList;

import com.sadik.database.DatabaseHandler;
import com.sadik.interfaces.PickerInterface;
import com.sadik.interfaces.RemoveViewInterface;
import com.sadik.model.BeanDateValidation;
import com.sadik.model.BeanEvent;
import com.sadik.model.BeanHoliday;
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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("ValidFragment")
public class AddHolidaysFragment extends Fragment {
	
	EditText edit_AddHolidays_Holiday;
	TextView txt_AddHolidays_Date;
	Button btn_AddHolidays_Holiday;
	LinearLayout layout_AddHolidays_HolidayList;
	BeanHoliday beanHoliday;
	ArrayList<BeanHoliday> beanHolidayList;
	DatabaseHandler databaseHandler;
	TextView clickedText;
	String schoolPkey=null;
	
	PickerInterface pickerInterface;
	Activity context;
	TextView textHeader;
	String strHeader;
	BeanDateValidation bean;
	
	public AddHolidaysFragment(TextView textHeader, String strHeader, BeanDateValidation bean) {
		// TODO Auto-generated constructor stub
		this.textHeader = textHeader;
		this.strHeader = strHeader;
		this.bean = bean;
		
	}
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		this.context = activity;
		pickerInterface = (PickerInterface) activity;
		textHeader.setText(strHeader);
		databaseHandler = new DatabaseHandler(activity);
		beanHoliday = new BeanHoliday();
		beanHolidayList = new ArrayList<BeanHoliday>();

	}
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, 
        Bundle savedInstanceState) {
        // Inflate the layout for this fragment
    	super.onCreateView(inflater, container, savedInstanceState);
    	View V = inflater.inflate(R.layout.layout_add_holidays, container, false);
    	
    	txt_AddHolidays_Date = (TextView) V.findViewById(R.id.txt_AddHolidays_Date);
    	edit_AddHolidays_Holiday = (EditText) V.findViewById(R.id.edit_AddHolidays_Holiday);
    	btn_AddHolidays_Holiday = (Button) V.findViewById(R.id.btn_AddHolidays_Holiday);
    	layout_AddHolidays_HolidayList = (LinearLayout) V.findViewById(R.id.layout_AddHolidays_HolidayList);

		clickedText = (TextView) textHeader.getTag();

		setData();
		clickedText.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setData();
			}
		});
    	
    	edit_AddHolidays_Holiday.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
                Validation.hasText(edit_AddHolidays_Holiday);
			}
		});
    	
    	txt_AddHolidays_Date.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//txt_AddHolidays_Date.setText("13/03/2014");
				pickerInterface.openDatePicker(txt_AddHolidays_Date, bean.getStartDate(), bean.getEndDate());
			}
		});
		
    	btn_AddHolidays_Holiday.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(checkValidation()) {
					if(schoolPkey != null)  {
					beanHoliday = new BeanHoliday();
					beanHoliday.setHoliday_date(txt_AddHolidays_Date.getText().toString());
					beanHoliday.setHoliday_name(edit_AddHolidays_Holiday.getText().toString());
					beanHoliday.setSchoolPkey(schoolPkey);
					int holiday_id = databaseHandler.Add_Holiday(beanHoliday);
					if(holiday_id>0) {
						beanHoliday.setHoliday_id(holiday_id);;
							addClassLayout(beanHoliday);
							txt_AddHolidays_Date.setText("");
							edit_AddHolidays_Holiday.setText("");
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
    
    private void addClassLayout(final BeanHoliday beanHoliday)
    {
		LayoutInflater inflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
		
		final View convertView = inflater.inflate(R.layout.layout_subholiday, null);
		
		final TextView txt_subholiday_Date = (TextView) convertView.findViewById(R.id.txt_subholiday_Date);
		TextView txt_subholiday_Holidays = (TextView) convertView.findViewById(R.id.txt_subholiday_Holidays);
		LinearLayout layout_subholiday_cross = (LinearLayout) convertView.findViewById(R.id.layout_subholiday_cross);
		txt_subholiday_Date.setText(beanHoliday.getHoliday_date());
		txt_subholiday_Holidays.setText(beanHoliday.getHoliday_name());
		layout_subholiday_cross.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				RemoveViewInterface removeViewInterface = new RemoveViewInterface() {
					
					@Override
					public boolean remove() {
						return databaseHandler.Delete_Holiday(beanHoliday.getHoliday_id(),beanHoliday.getSchoolPkey());
						// TODO Auto-generated method stub
						
					}
				};
				
				Alerts.removeView(context, convertView, layout_AddHolidays_HolidayList, removeViewInterface);
				
			}
		});

		layout_AddHolidays_HolidayList.addView(convertView);
    }
    
	private void setData()
	{
		layout_AddHolidays_HolidayList.removeAllViews();
		BeanSchool beanSchool = (BeanSchool) clickedText.getTag();
		if(beanSchool != null)
		{
			schoolPkey = beanSchool.getSchoolPkey();
			beanHoliday = new BeanHoliday();
			beanHolidayList = new ArrayList<BeanHoliday>();
			beanHolidayList = databaseHandler.Get_Holiday(schoolPkey, bean.getStartDate(), bean.getEndDate());
	    	for (int i = 0; i < beanHolidayList.size(); i++) {
	        	addClassLayout(beanHolidayList.get(i));
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

       if (!Validation.hasText(edit_AddHolidays_Holiday)) ret = false;
       if (!Validation.hasText(txt_AddHolidays_Date)) ret = false;

       return ret;
   }

}