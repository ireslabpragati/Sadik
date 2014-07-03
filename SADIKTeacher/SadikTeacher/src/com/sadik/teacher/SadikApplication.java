package com.sadik.teacher;

import com.sadik.database.DatabaseHandler;
import com.sadik.model.BeanSettingAttendanceType;
import com.sadik.teacher.R;

import android.app.Application;

public class SadikApplication extends Application {
	
	//DatabaseHandler databaseHandler;
	
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		//databaseHandler = new DatabaseHandler(SadikApplication.this);
		Add_ATTENDANCE_TYPE_INITIAL();
	}
	
	
	public void Add_ATTENDANCE_TYPE_INITIAL() {

		String[] attendance_type_name = this.getResources().getStringArray(R.array.attendance_type_name);
		String[] attendance_type_color = this.getResources().getStringArray(R.array.attendance_type_color);
		for(int i=0; i<attendance_type_name.length;i++) {
			BeanSettingAttendanceType beanSettingAttendanceType = new BeanSettingAttendanceType();
			beanSettingAttendanceType.setAttendance_name(attendance_type_name[i]+"");
			beanSettingAttendanceType.setAttendance_color(attendance_type_color[i]+"");
			beanSettingAttendanceType.setSchoolPkey("");
			//databaseHandler.Add_Atendance_Type(beanSettingAttendanceType);
		}

}
	
}
