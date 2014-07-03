package com.sadik.model;

public class BeanEvent {
	
	int event_id;

	String event_name, event_date, event_type, schoolPkey;

	public String getSchoolPkey() {
		return schoolPkey;
	}

	public void setSchoolPkey(String schoolPkey) {
		this.schoolPkey = schoolPkey;
	}

	public int getEvent_id() {
		return event_id;
	}

	public void setEvent_id(int event_id) {
		this.event_id = event_id;
	}

	public String getEvent_name() {
		return event_name;
	}

	public void setEvent_name(String event_name) {
		this.event_name = event_name;
	}

	public String getEvent_date() {
		return event_date;
	}

	public void setEvent_date(String event_date) {
		this.event_date = event_date;
	}

	public String getEvent_type() {
		return event_type;
	}

	public void setEvent_type(String event_type) {
		this.event_type = event_type;
	}



}
