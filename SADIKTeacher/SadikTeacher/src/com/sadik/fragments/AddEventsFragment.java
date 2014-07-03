package com.sadik.fragments;

import java.util.ArrayList;

import com.sadik.database.DatabaseHandler;
import com.sadik.interfaces.PickerInterface;
import com.sadik.interfaces.RemoveViewInterface;
import com.sadik.model.BeanDateValidation;
import com.sadik.model.BeanEvent;
import com.sadik.model.BeanSchool;
import com.sadik.teacher.Alerts;
import com.sadik.teacher.HideKeypad;
import com.sadik.teacher.R;
import com.sadik.teacher.ToastCustom;
import com.sadik.teacher.ToastFormError;
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
import android.webkit.WebView.FindListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("ValidFragment")
public class AddEventsFragment extends Fragment {
	
	EditText edit_AddEvent_Holiday;
	TextView txt_AddEvent_Date, txt_AddEvent_School;
	Button btn_AddEvent_Holiday;
	RadioButton radiobtn_AddEvent_Holiday, radiobtn_AddEvent_Effective_Days;
	LinearLayout layout_AddEvent_HolidayList;
	DatabaseHandler databaseHandler;
	BeanEvent beanEvent;
	ArrayList<BeanEvent> beanEventList;
	TextView clickedText;
	String schoolPkey=null;
	
	PickerInterface pickerInterface;
	Activity context;
	TextView textHeader;
	String strHeader;
	BeanDateValidation bean;
	int demoday =0, demoyear = 0;
	
	public AddEventsFragment(TextView textHeader, String strHeader, BeanDateValidation bean) {
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
		beanEvent = new BeanEvent();
		beanEventList = new ArrayList<BeanEvent>();

	}
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, 
        Bundle savedInstanceState) {
        // Inflate the layout for this fragment
    	super.onCreateView(inflater, container, savedInstanceState);
    	View V = inflater.inflate(R.layout.layout_add_events, container, false);
    	
    	txt_AddEvent_Date = (TextView) V.findViewById(R.id.txt_AddEvent_Date);
    	txt_AddEvent_School = (TextView) V.findViewById(R.id.txt_AddEvent_School);
    	edit_AddEvent_Holiday = (EditText) V.findViewById(R.id.edit_AddEvent_Holiday);
    	btn_AddEvent_Holiday = (Button) V.findViewById(R.id.btn_AddEvent_Holiday);
    	radiobtn_AddEvent_Holiday = (RadioButton) V.findViewById(R.id.radiobtn_AddEvent_Holiday);
    	radiobtn_AddEvent_Effective_Days = (RadioButton) V.findViewById(R.id.radiobtn_AddEvent_Effective_Days);
    	layout_AddEvent_HolidayList = (LinearLayout) V.findViewById(R.id.layout_AddEvent_HolidayList);

		clickedText = (TextView) textHeader.getTag();

		setData();
		clickedText.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setData();
			}
		});
    	
    	edit_AddEvent_Holiday.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
                Validation.hasText(edit_AddEvent_Holiday);
			}
		});
    	
    	btn_AddEvent_Holiday.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				demoyear++;
				if(checkValidation()) {
					if(schoolPkey != null) {
					beanEvent = new BeanEvent();
					beanEvent.setEvent_date(txt_AddEvent_Date.getText().toString());
					beanEvent.setEvent_name(edit_AddEvent_Holiday.getText().toString());
					beanEvent.setEvent_type(radiobtn_AddEvent_Holiday.isChecked()+"");
					beanEvent.setSchoolPkey(schoolPkey);
					int event_id = databaseHandler.Add_Event(beanEvent);
					if(event_id>0) {
						beanEvent.setEvent_id(event_id);
							addClassLayout(beanEvent);
							txt_AddEvent_Date.setText("");
							edit_AddEvent_Holiday.setText("");
							radiobtn_AddEvent_Effective_Days.setChecked(true);
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
    	
    	txt_AddEvent_Date.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				demoday++;
				//txt_AddEvent_Date.setText("2013-"+demoyear+"-"+demoday);
				pickerInterface.openDatePicker(txt_AddEvent_Date, bean.getStartDate(), bean.getEndDate());
			}
		});
    	
    	return V;
    	
    }
    
    private void addClassLayout(final BeanEvent beanEvent)
    {
		LayoutInflater inflater=(LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
		
		final View convertView = inflater.inflate(R.layout.layout_subevent, null);
		
		final TextView txt_subevent_Date = (TextView) convertView.findViewById(R.id.txt_subevent_Date);
		TextView txt_subevent_Holidays = (TextView) convertView.findViewById(R.id.txt_subevent_Holidays);
		LinearLayout layout_subevent_cross = (LinearLayout) convertView.findViewById(R.id.layout_subevent_cross);
		txt_subevent_Date.setText(beanEvent.getEvent_date());
		txt_subevent_Holidays.setText(beanEvent.getEvent_name());
		if(beanEvent.getEvent_type().equalsIgnoreCase("true")) {
		txt_subevent_Date.setTextColor(context.getResources().getColor(R.color.holiday_color));
		txt_subevent_Holidays.setTextColor(context.getResources().getColor(R.color.holiday_color));
		}
		else {
			txt_subevent_Date.setTextColor(context.getResources().getColor(R.color.holiday_not_color));
			txt_subevent_Holidays.setTextColor(context.getResources().getColor(R.color.holiday_not_color));
		}
		layout_subevent_cross.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				RemoveViewInterface removeViewInterface = new RemoveViewInterface() {
					
					@Override
					public boolean remove() {
						return databaseHandler.Delete_Event(beanEvent.getEvent_id(),beanEvent.getSchoolPkey());
						// TODO Auto-generated method stub
						
					}
				};
				
				Alerts.removeView(context, convertView, layout_AddEvent_HolidayList, removeViewInterface);
				
			}
		});

		layout_AddEvent_HolidayList.addView(convertView);
    }
    
	private void setData()
	{
		layout_AddEvent_HolidayList.removeAllViews();
		BeanSchool beanSchool = (BeanSchool) clickedText.getTag();
		if(beanSchool != null)
		{
			schoolPkey = beanSchool.getSchoolPkey();
			beanEvent = new BeanEvent();
			beanEventList = new ArrayList<BeanEvent>();
			beanEventList = databaseHandler.Get_Event(schoolPkey, bean.getStartDate(), bean.getEndDate());
	    	for (int i = 0; i < beanEventList.size(); i++) {
	        	addClassLayout(beanEventList.get(i));
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

       if (!Validation.hasText(edit_AddEvent_Holiday)) ret = false;
       if (!Validation.hasText(txt_AddEvent_Date)) ret = false;

       return ret;
   }

}