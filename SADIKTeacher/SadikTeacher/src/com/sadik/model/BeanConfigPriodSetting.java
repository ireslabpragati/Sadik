package com.sadik.model;

public class BeanConfigPriodSetting {
	
	String School_id,periods,pri_class,subject,reccuranceDays,reccurance,SCHOOL_PKEY;
	
	 
//	INTEGER
	
	public String getReccuranceDays() {
		return reccuranceDays;
	}

	public String getSCHOOL_PKEY() {
		return SCHOOL_PKEY;
	}

	public void setSCHOOL_PKEY(String sCHOOL_PKEY) {
		SCHOOL_PKEY = sCHOOL_PKEY;
	}

	public void setReccuranceDays(String reccuranceDays) {
		this.reccuranceDays = reccuranceDays;
	}

	 

	public String getReccurance() {
		return reccurance;
	}

	public void setReccurance(String reccurance) {
		this.reccurance = reccurance;
	}

	public String getSchool_id() {
		return School_id;
	}

	public void setSchool_id(String school_id) {
		School_id = school_id;
	}

	public String getPeriods() {
		return periods;
	}

	public void setPeriods(String period) {
		this.periods = period;
	}

	public String getPri_class() {
		return pri_class;
	}

	public void setPri_class(String pri_class) {
		this.pri_class = pri_class;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

}
