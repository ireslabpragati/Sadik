package com.sadik.model;

public class BeanSchool extends BeanAddress{

	String schoolId,schoolPkey, effective_Days;
	int period_Count;
	boolean response;
	
	/* public BeanSchool(String id, String name, String address, String state, String city, String zipCode) {
		    }*/
	
	public String getSchoolId() {
		return schoolId;
	}

	public boolean isResponse() {
		return response;
	}

	public void setResponse(boolean response) {
		this.response = response;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	public String getEffective_Days() {
		return effective_Days;
	}

	public void setEffective_Days(String effective_Days) {
		this.effective_Days = effective_Days;
	}

	public int getPeriod_Count() {
		return period_Count;
	}

	public void setPeriod_Count(int period_Count) {
		this.period_Count = period_Count;
	}


	public String getSchoolPkey() {
		return schoolPkey;
	}

	public void setSchoolPkey(String schoolPkey) {
		this.schoolPkey = schoolPkey;
	}

}
