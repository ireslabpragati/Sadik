package com.sadik.fragments;

import com.sadik.database.DatabaseHandler;
import com.sadik.interfaces.FragmentInterFace;
import com.sadik.interfaces.PickerInterface;
import com.sadik.model.BeanTeacher;
import com.sadik.teacher.ArrayIndex;
import com.sadik.teacher.HideKeypad;
import com.sadik.teacher.R;
import com.sadik.teacher.ToastCustom;
import com.sadik.teacher.ToastUD;
import com.sadik.validation.Validation;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("ValidFragment")
public class FragmentSettingTeacherProfile extends Fragment {
	private static int LOAD_IMAGE_RESULTS = 1;

	public static final String TAG = "TeacherFragment";
	PickerInterface pickerInterface;
	EditText edit_AddTeacher_Teacher_Id, edit_AddTeacher_FName,
			edit_AddTeacher_LName, edit_AddTeacher_Mobile,
			edit_AddTeacher_Email_Address, edit_AddTeacher_Address;
	TextView txt_ConfSchool_Effective_Days, edit_AddTeacher_DOB,
			edit_AddTeacher_DOJ;
	ImageView img_AddTeacher_ProfileImage;
	Spinner spin_AddTeacher_status_deg, spin_AddTeacher_Religion,
			spin_AddTeacher_provience, spin_AddTeacher_City,
			spin_AddTeacher_EmpStatus, spin_AddTeacher_District,
			spin_AddTeacher_Sub_District_Village;

	RadioButton radiobtn_AddTeacher_Female, radiobtn_AddTeacher_Male;

	Button btn_AddTeacher_Upload_Image, btn_AddTeacher_Submit;
	String Tsex = "";
	String SpKey = null;

	FragmentInterFace fragmentInterFace;
	DatabaseHandler db;

	Activity context;
	TextView textHeader;
	String strHeader;

	@SuppressLint("ValidFragment")
	public FragmentSettingTeacherProfile(TextView textHeader, String strHeader) {
		// TODO Auto-generated constructor stub
		this.textHeader = textHeader;
		this.strHeader = strHeader;
	}

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		this.context = activity;
		pickerInterface = (PickerInterface) activity;
		db = new DatabaseHandler(activity);

		textHeader.setText(strHeader);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		super.onCreateView(inflater, container, savedInstanceState);
		View v = inflater.inflate(R.layout.layout_teacher_profile, container,
				false);

		edit_AddTeacher_Address = (EditText) v
				.findViewById(R.id._edit_AddTeacher_Address);
		edit_AddTeacher_DOB = (TextView) v
				.findViewById(R.id._edit_AddTeacher_DOB);
		edit_AddTeacher_DOJ = (TextView) v
				.findViewById(R.id._edit_AddTeacher_DOJ);
		edit_AddTeacher_Email_Address = (EditText) v
				.findViewById(R.id._edit_AddTeacher_Email_Address);
		edit_AddTeacher_FName = (EditText) v
				.findViewById(R.id._edit_AddTeacher_FName);
		edit_AddTeacher_LName = (EditText) v
				.findViewById(R.id._edit_AddTeacher_LName);
		edit_AddTeacher_Mobile = (EditText) v
				.findViewById(R.id._edit_AddTeacher_Mobile);
		edit_AddTeacher_Teacher_Id = (EditText) v
				.findViewById(R.id._edit_AddTeacher_Teacher_Id);

		img_AddTeacher_ProfileImage = (ImageView) v
				.findViewById(R.id._img_AddTeacher_ProfileImage);

		btn_AddTeacher_Submit = (Button) v
				.findViewById(R.id._btn_AddTeacher_Submit);
		btn_AddTeacher_Upload_Image = (Button) v
				.findViewById(R.id._btn_AddTeacher_Upload_Image);

		radiobtn_AddTeacher_Female = (RadioButton) v
				.findViewById(R.id._radiobtn_AddTeacher_Female);
		radiobtn_AddTeacher_Male = (RadioButton) v
				.findViewById(R.id._radiobtn_AddTeacher_Male);

		img_AddTeacher_ProfileImage = (ImageView) v
				.findViewById(R.id._img_AddTeacher_ProfileImage);

		spin_AddTeacher_status_deg = (Spinner) v
				.findViewById(R.id._spin_AddTeacher_status_deg);
		spin_AddTeacher_City = (Spinner) v
				.findViewById(R.id._spin_AddTeacher_City);
		spin_AddTeacher_District = (Spinner) v
				.findViewById(R.id._spin_AddTeacher_District);
		spin_AddTeacher_EmpStatus = (Spinner) v
				.findViewById(R.id._spin_AddTeacher_EmpStatus);
		spin_AddTeacher_provience = (Spinner) v
				.findViewById(R.id._spin_AddTeacher_provience);
		spin_AddTeacher_Religion = (Spinner) v
				.findViewById(R.id._spin_AddTeacher_Religion);
		spin_AddTeacher_Sub_District_Village = (Spinner) v
				.findViewById(R.id._spin_AddTeacher_Sub_District_Village);

		//try {
			// call for read data from db of bean teacher
			BeanTeacher beanteacherProfile = db.Get_TeachetProfileDetails();
			// to set data in field
			if(beanteacherProfile.isResponse())
			{
			showTeacherDetails(beanteacherProfile);
			}

/*		} catch (Exception e) {
			// TODO: handle exception
		}*/

		edit_AddTeacher_DOJ.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub

				// Date StartDate = new Date();
				pickerInterface.openDatePicker(edit_AddTeacher_DOJ,
						"01/01/1950", "01/01/2030");
				return false;
			}
		});
		edit_AddTeacher_DOB.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub

				// Date StartDate = new Date();
				pickerInterface.openDatePicker(edit_AddTeacher_DOB,
						"01/01/1930", "01/01/2030");

				return false;
			}
		});
		try {

		} catch (Exception e) {
			// TODO: handle exception
			Log.i(TAG, e.toString());

		}

		btn_AddTeacher_Upload_Image.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// Create the Intent for Image Gallery.
				
				ToastUD.show(context);
				
				
				/*Intent i = new Intent(
						Intent.ACTION_PICK,
						android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

				// Start new activity with the LOAD_IMAGE_RESULTS to handle back
				// the results when image is picked from the Image Gallery.
				startActivityForResult(i, LOAD_IMAGE_RESULTS);*/

			}
		});

		btn_AddTeacher_Submit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				// to check validation by class validation
				if (checkValidation()) {

					AddTeacherProfile();
				}
			}
		});
		return v;
	}

	// insert add teacher profile

	private void AddTeacherProfile() {

		BeanTeacher beanteacherProfile = new BeanTeacher();

		beanteacherProfile.set_teacher_add_city(spin_AddTeacher_City
				.getSelectedItem().toString());
		beanteacherProfile.set_teacher_add_district(spin_AddTeacher_District
				.getSelectedItem().toString());
		beanteacherProfile.set_teacher_add_previence(spin_AddTeacher_provience
				.getSelectedItem().toString());
		beanteacherProfile
				.set_teacher_add_subDtt_vill(spin_AddTeacher_Sub_District_Village
						.getSelectedItem().toString());
		beanteacherProfile.set_teacher_address(edit_AddTeacher_Address
				.getText().toString());
		beanteacherProfile.set_teacher_Date_of_joining(edit_AddTeacher_DOJ
				.getText().toString());
		beanteacherProfile.set_teacher_dob(edit_AddTeacher_DOB.getText()
				.toString());
		beanteacherProfile.set_teacher_email_id(edit_AddTeacher_Email_Address
				.getText().toString());
		beanteacherProfile.set_teacher_emp_status(spin_AddTeacher_EmpStatus
				.getSelectedItem().toString());
		beanteacherProfile.set_teacher_f_name(edit_AddTeacher_FName.getText()
				.toString());
		beanteacherProfile.set_teacher_l_name(edit_AddTeacher_LName.getText()
				.toString());
		beanteacherProfile.set_teacher_id(edit_AddTeacher_Teacher_Id.getText()
				.toString());
		beanteacherProfile.set_teacher_mobile_number(edit_AddTeacher_Mobile
				.getText().toString());
		// beanteacherProfile.set_teacher_profile_img_path(_teacher_profile_img_path);
		beanteacherProfile.set_teacher_religion(spin_AddTeacher_Religion
				.getSelectedItem().toString());
		// beanteacherProfile.set_teacher_school_id(_teacher_school_id);

		if (radiobtn_AddTeacher_Female.isChecked()) {
			Tsex = "Female";

		} else {
			Tsex = "Male";

		}
		beanteacherProfile.set_teacher_sex(Tsex);
		beanteacherProfile.set_teacher_status_deg(spin_AddTeacher_status_deg
				.getSelectedItem().toString());
		// CALL FOR ADD DATA IN TABLE TEACHER
		if(btn_AddTeacher_Submit.getText().toString().equalsIgnoreCase(context.getResources().getString(R.string.Update)))
		{
			db.Update_Teacher_profile(beanteacherProfile);
			ToastCustom.makeText(context, "Profile Updated",Toast.LENGTH_SHORT);
			
			
		}
		else
		{
			db.Add_TeacherProfile(beanteacherProfile);
			ToastCustom.makeText(context, "Profile Saved",Toast.LENGTH_SHORT);
		}
		// AFTER SAVE DATA IN DB RESET FIELD
	//	ResetTeacherField();

		// addClassLayout(beanconfigsetting);
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		HideKeypad.hideSoftKeyboard(context);
	}

	// RESET ALL FIELDS

	private void ResetTeacherField1() {
		edit_AddTeacher_Teacher_Id.setText("");
		edit_AddTeacher_FName.setText("");
		edit_AddTeacher_LName.setText("");
		edit_AddTeacher_DOB.setText("");
		edit_AddTeacher_Mobile.setText("");
		edit_AddTeacher_Email_Address.setText("");
		edit_AddTeacher_Address.setText("");
		edit_AddTeacher_DOJ.setText("");
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);

		// Here we need to check if the activity that was triggers was the Image
		// Gallery.
		// If it is the requestCode will match the LOAD_IMAGE_RESULTS value.
		// If the resultCode is RESULT_OK and there is some data we know that an
		// image was picked.

		Log.i("requestCode", "" + requestCode);
		Log.i("resultCode", "" + resultCode);

		if (requestCode == LOAD_IMAGE_RESULTS && resultCode == -1
				&& data != null) {
			// Let's read picked image data - its URI
			Uri pickedImage = data.getData();
			// Let's read picked image path using content resolver
			String[] filePath = { MediaStore.Images.Media.DATA };

			Cursor cursor = context.getContentResolver().query(pickedImage,
					filePath, null, null, null);
			cursor.moveToFirst();
			String imagePath = cursor.getString(cursor
					.getColumnIndex(filePath[0]));

			// Now we need to set the GUI ImageView data with data read from the
			// picked file.
			img_AddTeacher_ProfileImage.setImageBitmap(BitmapFactory
					.decodeFile(filePath[0]));
			Log.i("image", imagePath);
			// img_AddTeacher_ProfileImage.setImageBitmap(BitmapFactory)
			// At the end remember to close the cursor or you will end with the
			// RuntimeException!
			// cursor.close();

		}

	}

	// for set data in assosiated field

	private void showTeacherDetails(BeanTeacher teacherinfo) {

		edit_AddTeacher_Teacher_Id.setText(teacherinfo.get_teacher_id());
		edit_AddTeacher_FName.setText(teacherinfo.get_teacher_f_name());
		edit_AddTeacher_LName.setText(teacherinfo.get_teacher_l_name());
		edit_AddTeacher_DOB.setText(teacherinfo.get_teacher_sex());
		edit_AddTeacher_Mobile.setText(teacherinfo.get_teacher_mobile_number());
		edit_AddTeacher_Email_Address.setText(teacherinfo
				.get_teacher_email_id());
		edit_AddTeacher_Address.setText(teacherinfo.get_teacher_address());
		edit_AddTeacher_DOJ.setText(teacherinfo.get_teacher_Date_of_joining());
		
		
		edit_AddTeacher_Mobile.setText(teacherinfo.get_teacher_mobile_number());

		ArrayIndex arrayIndex = new ArrayIndex();

		arrayIndex.setSpinnerItem(spin_AddTeacher_Sub_District_Village,
				teacherinfo.get_teacher_add_subDtt_vill());
		arrayIndex.setSpinnerItem(spin_AddTeacher_City,
				teacherinfo.get_teacher_add_city());
		arrayIndex.setSpinnerItem(spin_AddTeacher_District,
				teacherinfo.get_teacher_add_district());
		arrayIndex.setSpinnerItem(spin_AddTeacher_EmpStatus,
				teacherinfo.get_teacher_emp_status());
		arrayIndex.setSpinnerItem(spin_AddTeacher_Religion,
				teacherinfo.get_teacher_religion());
		arrayIndex.setSpinnerItem(spin_AddTeacher_Sub_District_Village,
				teacherinfo.get_teacher_add_subDtt_vill());
		arrayIndex.setSpinnerItem(spin_AddTeacher_provience,
				teacherinfo.get_teacher_add_previence());
		arrayIndex.setSpinnerItem(spin_AddTeacher_status_deg,
				teacherinfo.get_teacher_status_deg());

		if (teacherinfo.get_teacher_sex().equalsIgnoreCase("male")
				|| teacherinfo.get_teacher_sex().equalsIgnoreCase("m")) {
			radiobtn_AddTeacher_Male.setChecked(true);
			radiobtn_AddTeacher_Female.setChecked(false);
		} else if (teacherinfo.get_teacher_sex().equalsIgnoreCase("female")
				|| teacherinfo.get_teacher_sex().equalsIgnoreCase("f")) {
			radiobtn_AddTeacher_Male.setChecked(false);
			radiobtn_AddTeacher_Female.setChecked(true);
		}

		btn_AddTeacher_Submit.setText(context.getResources().getString(
				R.string.Update));
		
		
	}
 
	
	                
	// checking for validation
	private boolean checkValidation() {
		boolean ret = true;

		if (!Validation.hasText(edit_AddTeacher_Teacher_Id))
			ret = false;
		if (!Validation.hasText(edit_AddTeacher_FName))
			ret = false;
		if (!Validation.hasText(edit_AddTeacher_LName))
			ret = false;
		if (!Validation.hasText(edit_AddTeacher_DOB))
			ret = false;
		if (!Validation.isPhoneNumber(edit_AddTeacher_Mobile, true))
			ret = false;
		if (!Validation.isEmailAddress(edit_AddTeacher_Email_Address, true))
			ret = false;
		if (!Validation.hasText(edit_AddTeacher_Address))
			ret = false;
		if (!Validation.hasText(edit_AddTeacher_DOJ))
			ret = false;

		return ret;
	}

}