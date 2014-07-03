package com.sadik.model;

public class BeanSettingAttendanceType {
	
	int attendance_id;

	String attendance_name, attendance_color, schoolPkey;

	public String getSchoolPkey() {
		return schoolPkey;
	}

	public void setSchoolPkey(String schoolPkey) {
		this.schoolPkey = schoolPkey;
	}

	public String getAttendance_color() {
		return attendance_color;
	}

	public void setAttendance_color(String attendance_color) {
		this.attendance_color = attendance_color;
	}

	public String getAttendance_name() {
		return attendance_name;
	}

	public void setAttendance_name(String attendance_name) {
		this.attendance_name = attendance_name;
	}
	
	public int getAttendance_id() {
		return attendance_id;
	}

	public void setAttendance_id(int attendance_id) {
		this.attendance_id = attendance_id;
	}


}
