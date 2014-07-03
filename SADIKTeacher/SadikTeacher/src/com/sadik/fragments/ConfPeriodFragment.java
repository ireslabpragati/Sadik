package com.sadik.fragments;

import java.util.ArrayList;
import java.util.Collections;

import com.sadik.database.DatabaseHandler;
import com.sadik.interfaces.PickerInterface;
import com.sadik.interfaces.RemoveViewInterface;
import com.sadik.model.BeanClassInfo;
import com.sadik.model.BeanConfigPriodSetting;
import com.sadik.model.BeanDateValidation;
import com.sadik.model.BeanSchool;
import com.sadik.teacher.Alerts;
import com.sadik.teacher.HideKeypad;
import com.sadik.teacher.R;
import com.sadik.teacher.SubStringConverter;
import com.sadik.teacher.ToastCustom;
import com.sadik.teacher.ToastFormError;
import com.sadik.validation.Validation;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("ValidFragment")
public class ConfPeriodFragment extends Fragment {
	BeanConfigPriodSetting beanconfigpriod;
	ArrayList<BeanConfigPriodSetting> beanconfigpriodList;

	DatabaseHandler db;

	TextView txt_ConfigurePeriod_Period, txt_ConfigurePeriod_Date,
			txt_ConfigurePeriod_Recurrence, txt_ConfigurePeriod_School;
	Spinner spin_ConfigurePeriod_Class, spin_ConfigurePeriod_Subject;
	RadioButton radiobtn_ConfigurePeriod_Yes, radiobtn_ConfigurePeriod_No;
	LinearLayout layout_ConfigurePeriod_Recurrence,
			layout_ConfigurePeriod_PeriodList;
	Button btn_ConfigurePeriod;
  // for data from school 
	int period_Count = 0;
	 String SchoolPkey=null;
	PickerInterface pickerInterface;
	Activity context;
	TextView textHeader,clickedText;
	String strHeader;
	BeanDateValidation bean;

	public ConfPeriodFragment(TextView textHeader, String strHeader,
			BeanDateValidation bean) {
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
		db = new DatabaseHandler(activity);
		pickerInterface = (PickerInterface) activity;
		textHeader.setText(strHeader);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment

		super.onCreateView(inflater, container, savedInstanceState);
		View V = inflater.inflate(R.layout.layout_configure_period, container,
				false);

		txt_ConfigurePeriod_Period = (TextView) V
				.findViewById(R.id.txt_ConfigurePeriod_Period);
		txt_ConfigurePeriod_Date = (TextView) V
				.findViewById(R.id.txt_ConfigurePeriod_Date);
		txt_ConfigurePeriod_Recurrence = (TextView) V
				.findViewById(R.id.txt_ConfigurePeriod_Recurrence);
		txt_ConfigurePeriod_School = (TextView) V
				.findViewById(R.id.txt_ConfigurePeriod_School);

		spin_ConfigurePeriod_Class = (Spinner) V
				.findViewById(R.id.spin_ConfigurePeriod_Class);
		spin_ConfigurePeriod_Subject = (Spinner) V
				.findViewById(R.id.spin_ConfigurePeriod_Subject);

		radiobtn_ConfigurePeriod_Yes = (RadioButton) V
				.findViewById(R.id.radiobtn_ConfigurePeriod_Yes);
		radiobtn_ConfigurePeriod_No = (RadioButton) V
				.findViewById(R.id.radiobtn_ConfigurePeriod_No);

		layout_ConfigurePeriod_Recurrence = (LinearLayout) V
				.findViewById(R.id.layout_ConfigurePeriod_Recurrence);
		layout_ConfigurePeriod_PeriodList = (LinearLayout) V
				.findViewById(R.id.layout_ConfigurePeriod_PeriodList);

		btn_ConfigurePeriod = (Button) V.findViewById(R.id.btn_ConfigurePeriod);
		// get tag school
		clickedText = (TextView) textHeader.getTag();
		setData();
		clickedText.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setData();
			}
		});
		beanconfigpriod = new BeanConfigPriodSetting();
		beanconfigpriodList = new ArrayList<BeanConfigPriodSetting>();
		listConfigpriod();

		radiobtn_ConfigurePeriod_Yes
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					@Override
					public void onCheckedChanged(CompoundButton buttonView,
							boolean isChecked) {
						// TODO Auto-generated method stub
						if (radiobtn_ConfigurePeriod_Yes.isChecked()) {
							txt_ConfigurePeriod_Recurrence
									.setVisibility(View.VISIBLE);
						} else {
							txt_ConfigurePeriod_Recurrence
									.setVisibility(View.GONE);
						}
					}
				});

		txt_ConfigurePeriod_Date.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				pickerInterface.openDatePicker(txt_ConfigurePeriod_Date,
						bean.getStartDate(), bean.getEndDate());

			}
		});

		btn_ConfigurePeriod.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (checkValidation()) {

					ToastCustom.makeText(context, context.getResources()
							.getString(R.string.Data_Successfully_Saved),
							Toast.LENGTH_SHORT);
					AddConfig_priod();

					// after insertion clere the all fileds

					txt_ConfigurePeriod_Period.setText("");
					txt_ConfigurePeriod_Date.setText("");
					;
					txt_ConfigurePeriod_Recurrence.setText("");
					// txt_ConfigurePeriod_School.setText("");;

					// ToastUD.show(context);
				} else {
					ToastFormError.show(context);
				}
			}
		});

		txt_ConfigurePeriod_Period.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(period_Count>0)
				{
				alertPeriod(txt_ConfigurePeriod_Period, period_Count);
				}
			}
		});

		txt_ConfigurePeriod_Recurrence
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub

						Alerts.alertRecurrance(
								context,
								txt_ConfigurePeriod_Recurrence,
								context.getResources().getString(
										R.string.Select_Recurrence));
					}
				});

		return V;

	}

	@SuppressWarnings("static-access")
	private void addClassLayout(final BeanConfigPriodSetting beanconfig) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(context.LAYOUT_INFLATER_SERVICE);

		final View convertView = inflater.inflate(
				R.layout.layout_sub_configureperiod, null);

		TextView txt_subConfPeriod_Date = (TextView) convertView
				.findViewById(R.id.txt_subConfPeriod_Date);
		TextView txt_subConfPeriod_Period = (TextView) convertView
				.findViewById(R.id.txt_subConfPeriod_Period);
		TextView txt_subConfPeriod_Subject = (TextView) convertView
				.findViewById(R.id.txt_subConfPeriod_Subject);
		LinearLayout layout_subConfPeriod_cross = (LinearLayout) convertView
				.findViewById(R.id.layout_subConfPeriod_cross);
		
		// set values to filed
		txt_subConfPeriod_Date.setText(beanconfig.getReccuranceDays());
		txt_subConfPeriod_Period.setText(beanconfig.getPeriods());
		txt_subConfPeriod_Subject.setText(beanconfig.getSubject());
		layout_subConfPeriod_cross.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
          //       remove with alert
				
				RemoveViewInterface removeViewInterface = new RemoveViewInterface() {
					
					@Override
					public boolean remove() {
						
						return db.Delete_ConfigPriod(beanconfig.getSchool_id());
						
						// TODO Auto-generated method stub
						
					}
				};
				// remove singhle  view 
				
 Alerts.removeView(context, convertView, layout_ConfigurePeriod_PeriodList, removeViewInterface);
				
				
				/*
				db.Delete_ConfigPriod(beanconfig.getSchool_id());
				// to remove view  dynamic
				layout_ConfigurePeriod_PeriodList.removeView(convertView);
				//ToastUD.show(context); under dev toast
				*/
				
	Log.i("error",beanconfig.getSchool_id());			

			}
		});
		layout_ConfigurePeriod_PeriodList.addView(convertView);
	}

	// insert add config priod data
	private void AddConfig_priod() {

		BeanConfigPriodSetting beanconfigsetting = new BeanConfigPriodSetting();

		beanconfigsetting.setPeriods(txt_ConfigurePeriod_Period.getText()
				.toString());
		beanconfigsetting.setPri_class(spin_ConfigurePeriod_Class
				.getSelectedItem().toString());
		beanconfigsetting.setSchool_id(txt_ConfigurePeriod_School.getText()
				.toString());
		beanconfigsetting.setReccurance(String
				.valueOf(radiobtn_ConfigurePeriod_Yes.isChecked()));
		beanconfigsetting.setReccuranceDays(SubStringConverter.convert(txt_ConfigurePeriod_Recurrence
				.getText().toString()));
		beanconfigsetting.setSubject(spin_ConfigurePeriod_Subject
				.getSelectedItem().toString());

		if(SchoolPkey != null)
		{
		beanconfigsetting.setSCHOOL_PKEY(SchoolPkey);
		}
		else
		{
			ToastFormError.schoolNotconfigured(context);
		}
		
		/*
		 * values.put(PRIOD_CONF_SID, beanpriodconfigSetting.getSchool_id());
		 * values.put(PRIODS, beanpriodconfigSetting.getPeriods());
		 * values.put(PRI_CLASS, beanpriodconfigSetting.getPri_class());
		 * values.put(PRI_SUBJECT, beanpriodconfigSetting.getSubject());
		 * values.put(PRI_RECURENCE, beanpriodconfigSetting.getReccurance());
		 * values.put(RECURENCE_DAY,
		 * beanpriodconfigSetting.getReccuranceDays());
		 */

		db.Add_Priod_Config_Setting(beanconfigsetting);
		addClassLayout(beanconfigsetting);
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		HideKeypad.hideSoftKeyboard(context);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void alertPeriod(final TextView textView, int count) {

		AlertDialog dialog;

		final String[] items = new String[count];

		final boolean[] checked = new boolean[count];
		String textString = textView.getText().toString();

		final ArrayList seletedItems = new ArrayList();

		for (int i = 0; i < count; i++) {

			items[i] = "Period " + (i + 1);
			checked[i] = textString.contains("P" + (i + 1));
			if (textString.contains("P" + (i + 1))) {
				seletedItems.add(i);
			}
		}

		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle("Select Recurrence");
		builder.setMultiChoiceItems(items, checked,
				new DialogInterface.OnMultiChoiceClickListener() {
					@Override
					public void onClick(DialogInterface dialog,
							int indexSelected, boolean isChecked) {
						if (isChecked) {
							// If the user checked the item, add it to the
							// selected items
							seletedItems.add(indexSelected);

						} else if (seletedItems.contains(indexSelected)) {
							// Else, if the item is already in the array, remove
							// it
							seletedItems.remove(Integer.valueOf(indexSelected));
						}
					}
				})
				// Set the action buttons
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int id) {
						String dayList = "";
						Collections.sort(seletedItems);
						for (int i = 0; i < seletedItems.size(); i++) {
							if (i == 0) {
								dayList = "P"
										+ ((Integer) seletedItems.get(i) + 1);
							} else {
								dayList += ", " + "P"
										+ ((Integer) seletedItems.get(i) + 1);
							}
						}
						textView.setText(dayList);
					}
				})
				.setNegativeButton("Cancel",
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int id) {
								// Your code when user clicked on Cancel

							}
						});

		dialog = builder.create();// AlertDialog dialog; create like this
									// outside onClick
		dialog.show();
	}

	private boolean checkValidation() {
		boolean ret = true;

		if (!Validation.hasText(txt_ConfigurePeriod_Date))
			ret = false;
		if (!Validation.hasText(txt_ConfigurePeriod_Period))
			ret = false;
		if (radiobtn_ConfigurePeriod_Yes.isChecked()) {
			if (!Validation.hasText(txt_ConfigurePeriod_Recurrence))
				ret = false;
		} else {
			return true;
		}
		return ret;
	}

	private void listConfigpriod() {
		db = new DatabaseHandler(context);
		beanconfigpriodList = new ArrayList<BeanConfigPriodSetting>();

		
		if(SchoolPkey != null)
		{
			beanconfigpriodList = db.Get_ConfigPriod(SchoolPkey, bean.getStartDate(), bean.getEndDate());
			for (int i = 0; i < beanconfigpriodList.size(); i++) {
				addClassLayout(beanconfigpriodList.get(i));
			}
		}
		else
		{
			ToastFormError.schoolNotconfigured(context);
		}
		
		

	}
// set all data according to school p id
	
	
	private void setData()
    {
		layout_ConfigurePeriod_PeriodList.removeAllViews();
		BeanSchool beanSchool = (BeanSchool) clickedText.getTag();
		if(beanSchool != null)
		{    // get dta from school table
			SchoolPkey = beanSchool.getSchoolPkey();
			period_Count = beanSchool.getPeriod_Count();
			
			txt_ConfigurePeriod_School.setText(beanSchool.getSchoolId());
			//txtSettingClassSchoolId.setText(beanSchool.getSchoolId());
			ArrayList<BeanConfigPriodSetting> beanconfigpriod= db.Get_ConfigPriod(beanSchool.getSchoolPkey(), bean.getStartDate(), bean.getEndDate());
			
	    	for (int i = 0; i < beanconfigpriod.size(); i++) {
	    		addClassLayout(beanconfigpriod.get(i));
			}
	    	Log.i("bean school", "true");
		}
		else
		{
	    	ToastFormError.schoolNotconfigured(context);
		}

    }

}