package com.sadik.activities;

import java.util.ArrayList;

import yuku.ambilwarna.AmbilWarnaDialog;
import yuku.ambilwarna.AmbilWarnaDialog.OnAmbilWarnaListener;

import com.sadik.colorfragments.ActivityHostFragment;
import com.sadik.database.DatabaseHandler;
import com.sadik.interfaces.RemoveViewInterface;
import com.sadik.model.BeanSchool;
import com.sadik.model.BeanSettingAttendanceType;
import com.sadik.teacher.Alerts;
import com.sadik.teacher.HideKeypad;
import com.sadik.teacher.R;
import com.sadik.teacher.ToastCustom;
import com.sadik.teacher.ToastFormError;
import com.sadik.validation.Validation;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SettingAttendanceActivity extends Activity {
	
	
	EditText edit_SettingAttendance_Type;
	TextView txt_SettingAttendance_Color;
	Button btn_SettingAttendance_Add;
	LinearLayout layout_SettingAttendance_AttendanceList;
	DatabaseHandler databaseHandler;
	BeanSettingAttendanceType beanSettingAttendanceType;
	ArrayList<BeanSettingAttendanceType> beanSettingAttendanceTypesList;
	String schoolPkey=null;
	ResponseReceiver receiver;
	

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_setting_attendance);
        IntentFilter filter = new IntentFilter(ResponseReceiver.ACTION_RESP);
        filter.addCategory(Intent.CATEGORY_DEFAULT);
        receiver = new ResponseReceiver();
        registerReceiver(receiver, filter);
		btn_SettingAttendance_Add = (Button) findViewById(R.id.btn_SettingAttendance_Add);

		txt_SettingAttendance_Color = (TextView) findViewById(R.id.txt_SettingAttendance_Color);
		edit_SettingAttendance_Type = (EditText) findViewById(R.id.edit_SettingAttendance_Type);
		layout_SettingAttendance_AttendanceList = (LinearLayout) findViewById(R.id.layout_SettingAttendance_AttendanceList);
		databaseHandler = new DatabaseHandler(SettingAttendanceActivity.this);
		beanSettingAttendanceType = new BeanSettingAttendanceType();
		/*beanSettingAttendanceTypesList = new ArrayList<BeanSettingAttendanceType>();
		beanSettingAttendanceTypesList = databaseHandler.Get_AttendanceType();
		clickedText = (TextView) textHeader.getTag();*/

		setData(getIntent());
    	
    	
    	edit_SettingAttendance_Type.setOnFocusChangeListener(new OnFocusChangeListener() {
			
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
                Validation.hasText(edit_SettingAttendance_Type);
			}
		});
    	
    	txt_SettingAttendance_Color.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				colorpicker();
			}
		});
		
    	btn_SettingAttendance_Add.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(checkValidation()) {
					if(schoolPkey != null) {
					beanSettingAttendanceType = new BeanSettingAttendanceType();
					beanSettingAttendanceType.setAttendance_name(edit_SettingAttendance_Type.getText().toString());
					beanSettingAttendanceType.setAttendance_color(txt_SettingAttendance_Color.getTag().toString());
					beanSettingAttendanceType.setSchoolPkey(schoolPkey);
					int attendance_id = databaseHandler.Add_Atendance_Type(beanSettingAttendanceType);
					if(attendance_id>0) {
						beanSettingAttendanceType.setAttendance_id(attendance_id);
							addClassLayout(beanSettingAttendanceType);
							//ToastUD.show(context);
							edit_SettingAttendance_Type.setText("");
							txt_SettingAttendance_Color.setBackgroundResource(R.drawable.blackoutline_whitebg);
							ToastCustom.makeText(SettingAttendanceActivity.this, SettingAttendanceActivity.this.getResources().getString(R.string.Data_Successfully_Saved), Toast.LENGTH_SHORT);
					}
					else {
						ToastFormError.schoolNotconfigured(SettingAttendanceActivity.this);
					}
				//ToastUD.show(SettingAttendanceActivity.this);
			}
				else {
					ToastFormError.show(SettingAttendanceActivity.this);
				}
			}
			}
		});
		
	}
	
    public void colorpicker()
    {
    	// 	initialColor is the initially-selected color to be shown in the rectangle on the left of the arrow.
    	// 	for example, 0xff000000 is black, 0xff0000ff is blue. Please be aware of the initial 0xff which is the alpha.
        
        AmbilWarnaDialog dialog = new AmbilWarnaDialog(this, 0xff0000ff, new OnAmbilWarnaListener() 
    	{

        	// Executes, when user click Cancel button
    		@Override
    		public void onCancel(AmbilWarnaDialog dialog){
    	
    		}
    		
    		
    		// Executes, when user click OK button
    		@Override
    		public void onOk(AmbilWarnaDialog dialog, int color) {
    			txt_SettingAttendance_Color.setBackgroundColor(color);
     			txt_SettingAttendance_Color.setTag(color);
     			
     		    			//Toast.makeText(SettingAttendanceActivity.this, "Selected Color : " + color, Toast.LENGTH_LONG).show();    			
    		}
    			
    	});

        dialog.show();
    }
    
    
    private void addClassLayout(final BeanSettingAttendanceType beanSettingAttendanceType)
    {
		LayoutInflater inflater=(LayoutInflater) SettingAttendanceActivity.this.getSystemService(SettingAttendanceActivity.this.LAYOUT_INFLATER_SERVICE);
		
		final View convertView = inflater.inflate(R.layout.layout_sub_setting_attendance, null);
		final TextView txt_sub_setting_attendance_type = (TextView) convertView.findViewById(R.id.txt_sub_setting_attendance_type);
		TextView txt_sub_setting_attendance_color = (TextView) convertView.findViewById(R.id.txt_sub_setting_attendance_color);
		final LinearLayout layout_sub_setting_attendance_cross = (LinearLayout) convertView.findViewById(R.id.layout_sub_setting_attendance_cross);
		txt_sub_setting_attendance_type.setText(beanSettingAttendanceType.getAttendance_name());
		txt_sub_setting_attendance_color.setBackgroundColor(Integer.parseInt(beanSettingAttendanceType.getAttendance_color()));
		//layout_sub_setting_attendance_cross.setTag(beanSettingAttendanceType.getAttendance_id());
		layout_sub_setting_attendance_cross.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				RemoveViewInterface removeViewInterface = new RemoveViewInterface() {
					
					@Override
					public boolean remove() {
						return databaseHandler.Delete_Attendance_Type(beanSettingAttendanceType.getAttendance_id(),"");
						// TODO Auto-generated method stub
						
					}
				};
				
				Alerts.removeView(SettingAttendanceActivity.this, convertView, layout_SettingAttendance_AttendanceList, removeViewInterface);
				
			}
		});

		layout_SettingAttendance_AttendanceList.addView(convertView);
    }
    
	private void setData(Intent intent)
	{
		layout_SettingAttendance_AttendanceList.removeAllViews();
        schoolPkey = intent.getStringExtra(ActivityHostFragment.PARAM_MSG);
		if(schoolPkey != null)
		{
			beanSettingAttendanceTypesList = new ArrayList<BeanSettingAttendanceType>();
			beanSettingAttendanceTypesList = databaseHandler.Get_AttendanceType(schoolPkey);
	    	for (int i = 0; i < beanSettingAttendanceTypesList.size(); i++) {
	        	addClassLayout(beanSettingAttendanceTypesList.get(i));
			}
		}
		else
		{
			ToastFormError.schoolNotconfigured(SettingAttendanceActivity.this);
		}

	}

   

   @Override
    public void onPause() {
    	// TODO Auto-generated method stub
    	super.onPause();
    	HideKeypad.hideSoftKeyboard(SettingAttendanceActivity.this);
    }
   
   @Override
   public void onDestroy() {
       this.unregisterReceiver(receiver);
       super.onDestroy();
   }
   
   private boolean checkValidation() {
       boolean ret = true;

       if (!Validation.hasText(edit_SettingAttendance_Type)) ret = false;
       //if (!Validation.hasText(txt_SettingAttendance_Color)) ret = false;

       return ret;
   }
   
   
   public class ResponseReceiver extends BroadcastReceiver {
       public static final String ACTION_RESP = "com.sadik.intent.action.MESSAGE_PROCESSED";
       @Override
       public void onReceive(Context context, Intent intent) {
          
           // Update UI, new "message" processed by SimpleIntentService
          setData(intent);
       }
       
   }

   
}
