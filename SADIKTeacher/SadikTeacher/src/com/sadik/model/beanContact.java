package com.sadik.model;

public class beanContact {

    // private variables
    public int _id;
    public String _name;
    public String _phone_number;
    public String _email;
// ................................................................................................
    // teacher table
    public String _t_id;
    public String _t_f_name;
    public String _t_l_name;
    public String _t_m_name;
    public String _t_email_id;
    public String _t_mobile_number;
    public String _t_address;
    
    // student table
    
    public String _student_id;
    public String _s_f_name;
    public String _s_m_name;
    public String _s_l_name;
    public String _s_dob;
    public String _s_address;
    public String _s_email;
    public String _s_phone_no;
    public String _s_sex;
    public String _s_img_sdcardPath;
    
    
    // gardia/parents table
    
    public String _gardianP_id;
    public String _g_f_name;
    public String _g_m_name;
    public String _g_l_name;
    
    public String _g_address;
    public String _g_email;
    public String _g_sex;
    public String _g_relationToChild;
    public String _g_isParents;
    
   // attandence table 
    public String _att_date;
    public String _att_student_id;
    public String _att_priod_id;
    
    
    
    //stydentGardde table
    
    public String _attan_stu_id;
    public String _attan_marksObtain;
    public String _attan_assignment_id;
    
    // assignment table
    
    public String _assignment_id;
    public String _asign_examType;
    public String _asign_examID;
             
    /**
	 * @return the _id
	 */
	public int get_id() {
		return _id;
	}

	/**
	 * @param _id the _id to set
	 */
	public void set_id(int _id) {
		this._id = _id;
	}

	/**
	 * @return the _name
	 */
	public String get_name() {
		return _name;
	}

	/**
	 * @param _name the _name to set
	 */
	public void set_name(String _name) {
		this._name = _name;
	}

	/**
	 * @return the _phone_number
	 */
	public String get_phone_number() {
		return _phone_number;
	}

	/**
	 * @param _phone_number the _phone_number to set
	 */
	public void set_phone_number(String _phone_number) {
		this._phone_number = _phone_number;
	}

	/**
	 * @return the _email
	 */
	public String get_email() {
		return _email;
	}

	/**
	 * @param _email the _email to set
	 */
	public void set_email(String _email) {
		this._email = _email;
	}

	/**
	 * @return the _t_id
	 */
	public String get_t_id() {
		return _t_id;
	}

	/**
	 * @param _t_id the _t_id to set
	 */
	public void set_t_id(String _t_id) {
		this._t_id = _t_id;
	}

	/**
	 * @return the _t_f_name
	 */
	public String get_t_f_name() {
		return _t_f_name;
	}

	/**
	 * @param _t_f_name the _t_f_name to set
	 */
	public void set_t_f_name(String _t_f_name) {
		this._t_f_name = _t_f_name;
	}

	/**
	 * @return the _t_l_name
	 */
	public String get_t_l_name() {
		return _t_l_name;
	}

	/**
	 * @param _t_l_name the _t_l_name to set
	 */
	public void set_t_l_name(String _t_l_name) {
		this._t_l_name = _t_l_name;
	}

	/**
	 * @return the _t_m_name
	 */
	public String get_t_m_name() {
		return _t_m_name;
	}

	/**
	 * @param _t_m_name the _t_m_name to set
	 */
	public void set_t_m_name(String _t_m_name) {
		this._t_m_name = _t_m_name;
	}

	/**
	 * @return the _t_email_id
	 */
	public String get_t_email_id() {
		return _t_email_id;
	}

	/**
	 * @param _t_email_id the _t_email_id to set
	 */
	public void set_t_email_id(String _t_email_id) {
		this._t_email_id = _t_email_id;
	}

	/**
	 * @return the _t_mobile_number
	 */
	public String get_t_mobile_number() {
		return _t_mobile_number;
	}

	/**
	 * @param _t_mobile_number the _t_mobile_number to set
	 */
	public void set_t_mobile_number(String _t_mobile_number) {
		this._t_mobile_number = _t_mobile_number;
	}

	/**
	 * @return the _t_address
	 */
	public String get_t_address() {
		return _t_address;
	}

	/**
	 * @param _t_address the _t_address to set
	 */
	public void set_t_address(String _t_address) {
		this._t_address = _t_address;
	}

	/**
	 * @return the _student_id
	 */
	public String get_student_id() {
		return _student_id;
	}

	/**
	 * @param _student_id the _student_id to set
	 */
	public void set_student_id(String _student_id) {
		this._student_id = _student_id;
	}

	/**
	 * @return the _s_f_name
	 */
	public String get_s_f_name() {
		return _s_f_name;
	}

	/**
	 * @param _s_f_name the _s_f_name to set
	 */
	public void set_s_f_name(String _s_f_name) {
		this._s_f_name = _s_f_name;
	}

	/**
	 * @return the _s_m_name
	 */
	public String get_s_m_name() {
		return _s_m_name;
	}

	/**
	 * @param _s_m_name the _s_m_name to set
	 */
	public void set_s_m_name(String _s_m_name) {
		this._s_m_name = _s_m_name;
	}

	/**
	 * @return the _s_l_name
	 */
	public String get_s_l_name() {
		return _s_l_name;
	}

	/**
	 * @param _s_l_name the _s_l_name to set
	 */
	public void set_s_l_name(String _s_l_name) {
		this._s_l_name = _s_l_name;
	}

	/**
	 * @return the _s_dob
	 */
	public String get_s_dob() {
		return _s_dob;
	}

	/**
	 * @param _s_dob the _s_dob to set
	 */
	public void set_s_dob(String _s_dob) {
		this._s_dob = _s_dob;
	}

	/**
	 * @return the _s_address
	 */
	public String get_s_address() {
		return _s_address;
	}

	/**
	 * @param _s_address the _s_address to set
	 */
	public void set_s_address(String _s_address) {
		this._s_address = _s_address;
	}

	/**
	 * @return the _s_email
	 */
	public String get_s_email() {
		return _s_email;
	}

	/**
	 * @param _s_email the _s_email to set
	 */
	public void set_s_email(String _s_email) {
		this._s_email = _s_email;
	}

	/**
	 * @return the _s_phone_no
	 */
	public String get_s_phone_no() {
		return _s_phone_no;
	}

	/**
	 * @param _s_phone_no the _s_phone_no to set
	 */
	public void set_s_phone_no(String _s_phone_no) {
		this._s_phone_no = _s_phone_no;
	}

	/**
	 * @return the _s_sex
	 */
	public String get_s_sex() {
		return _s_sex;
	}

	/**
	 * @param _s_sex the _s_sex to set
	 */
	public void set_s_sex(String _s_sex) {
		this._s_sex = _s_sex;
	}

	/**
	 * @return the _s_img_sdcardPath
	 */
	public String get_s_img_sdcardPath() {
		return _s_img_sdcardPath;
	}

	/**
	 * @param _s_img_sdcardPath the _s_img_sdcardPath to set
	 */
	public void set_s_img_sdcardPath(String _s_img_sdcardPath) {
		this._s_img_sdcardPath = _s_img_sdcardPath;
	}

	/**
	 * @return the _gardianP_id
	 */
	public String get_gardianP_id() {
		return _gardianP_id;
	}

	/**
	 * @param _gardianP_id the _gardianP_id to set
	 */
	public void set_gardianP_id(String _gardianP_id) {
		this._gardianP_id = _gardianP_id;
	}

	/**
	 * @return the _g_f_name
	 */
	public String get_g_f_name() {
		return _g_f_name;
	}

	/**
	 * @param _g_f_name the _g_f_name to set
	 */
	public void set_g_f_name(String _g_f_name) {
		this._g_f_name = _g_f_name;
	}

	/**
	 * @return the _g_m_name
	 */
	public String get_g_m_name() {
		return _g_m_name;
	}

	/**
	 * @param _g_m_name the _g_m_name to set
	 */
	public void set_g_m_name(String _g_m_name) {
		this._g_m_name = _g_m_name;
	}

	/**
	 * @return the _g_l_name
	 */
	public String get_g_l_name() {
		return _g_l_name;
	}

	/**
	 * @param _g_l_name the _g_l_name to set
	 */
	public void set_g_l_name(String _g_l_name) {
		this._g_l_name = _g_l_name;
	}

	/**
	 * @return the _g_address
	 */
	public String get_g_address() {
		return _g_address;
	}

	/**
	 * @param _g_address the _g_address to set
	 */
	public void set_g_address(String _g_address) {
		this._g_address = _g_address;
	}

	/**
	 * @return the _g_email
	 */
	public String get_g_email() {
		return _g_email;
	}

	/**
	 * @param _g_email the _g_email to set
	 */
	public void set_g_email(String _g_email) {
		this._g_email = _g_email;
	}

	/**
	 * @return the _g_sex
	 */
	public String get_g_sex() {
		return _g_sex;
	}

	/**
	 * @param _g_sex the _g_sex to set
	 */
	public void set_g_sex(String _g_sex) {
		this._g_sex = _g_sex;
	}

	/**
	 * @return the _g_relationToChild
	 */
	public String get_g_relationToChild() {
		return _g_relationToChild;
	}

	/**
	 * @param _g_relationToChild the _g_relationToChild to set
	 */
	public void set_g_relationToChild(String _g_relationToChild) {
		this._g_relationToChild = _g_relationToChild;
	}

	/**
	 * @return the _g_isParents
	 */
	public String get_g_isParents() {
		return _g_isParents;
	}

	/**
	 * @param _g_isParents the _g_isParents to set
	 */
	public void set_g_isParents(String _g_isParents) {
		this._g_isParents = _g_isParents;
	}

	/**
	 * @return the _att_date
	 */
	public String get_att_date() {
		return _att_date;
	}

	/**
	 * @param _att_date the _att_date to set
	 */
	public void set_att_date(String _att_date) {
		this._att_date = _att_date;
	}

	/**
	 * @return the _att_student_id
	 */
	public String get_att_student_id() {
		return _att_student_id;
	}

	/**
	 * @param _att_student_id the _att_student_id to set
	 */
	public void set_att_student_id(String _att_student_id) {
		this._att_student_id = _att_student_id;
	}

	/**
	 * @return the _att_priod_id
	 */
	public String get_att_priod_id() {
		return _att_priod_id;
	}

	/**
	 * @param _att_priod_id the _att_priod_id to set
	 */
	public void set_att_priod_id(String _att_priod_id) {
		this._att_priod_id = _att_priod_id;
	}

	/**
	 * @return the _attan_stu_id
	 */
	public String get_attan_stu_id() {
		return _attan_stu_id;
	}

	/**
	 * @param _attan_stu_id the _attan_stu_id to set
	 */
	public void set_attan_stu_id(String _attan_stu_id) {
		this._attan_stu_id = _attan_stu_id;
	}

	/**
	 * @return the _attan_marksObtain
	 */
	public String get_attan_marksObtain() {
		return _attan_marksObtain;
	}

	/**
	 * @param _attan_marksObtain the _attan_marksObtain to set
	 */
	public void set_attan_marksObtain(String _attan_marksObtain) {
		this._attan_marksObtain = _attan_marksObtain;
	}

	/**
	 * @return the _attan_assignment_id
	 */
	public String get_attan_assignment_id() {
		return _attan_assignment_id;
	}

	/**
	 * @param _attan_assignment_id the _attan_assignment_id to set
	 */
	public void set_attan_assignment_id(String _attan_assignment_id) {
		this._attan_assignment_id = _attan_assignment_id;
	}

	/**
	 * @return the _assignment_id
	 */
	public String get_assignment_id() {
		return _assignment_id;
	}

	/**
	 * @param _assignment_id the _assignment_id to set
	 */
	public void set_assignment_id(String _assignment_id) {
		this._assignment_id = _assignment_id;
	}

	/**
	 * @return the _asign_examType
	 */
	public String get_asign_examType() {
		return _asign_examType;
	}

	/**
	 * @param _asign_examType the _asign_examType to set
	 */
	public void set_asign_examType(String _asign_examType) {
		this._asign_examType = _asign_examType;
	}

	/**
	 * @return the _asign_examID
	 */
	public String get_asign_examID() {
		return _asign_examID;
	}

	/**
	 * @param _asign_examID the _asign_examID to set
	 */
	public void set_asign_examID(String _asign_examID) {
		this._asign_examID = _asign_examID;
	}

	/**
	 * @return the _school_id
	 */
	public String get_school_id() {
		return _school_id;
	}

	/**
	 * @param _school_id the _school_id to set
	 */
	public void set_school_id(String _school_id) {
		this._school_id = _school_id;
	}

	/**
	 * @return the _school_name
	 */
	public String get_school_name() {
		return _school_name;
	}

	/**
	 * @param _school_name the _school_name to set
	 */
	public void set_school_name(String _school_name) {
		this._school_name = _school_name;
	}

	/**
	 * @return the _class_id
	 */
	public String get_class_id() {
		return _class_id;
	}

	/**
	 * @param _class_id the _class_id to set
	 */
	public void set_class_id(String _class_id) {
		this._class_id = _class_id;
	}

	/**
	 * @return the _cls_capacity
	 */
	public String get_cls_capacity() {
		return _cls_capacity;
	}

	/**
	 * @param _cls_capacity the _cls_capacity to set
	 */
	public void set_cls_capacity(String _cls_capacity) {
		this._cls_capacity = _cls_capacity;
	}

	/**
	 * @return the _advisory_name
	 */
	public String get_advisory_name() {
		return _advisory_name;
	}

	/**
	 * @param _advisory_name the _advisory_name to set
	 */
	public void set_advisory_name(String _advisory_name) {
		this._advisory_name = _advisory_name;
	}

	/**
	 * @return the _row
	 */
	public String get_row() {
		return _row;
	}

	/**
	 * @param _row the _row to set
	 */
	public void set_row(String _row) {
		this._row = _row;
	}

	/**
	 * @return the _column
	 */
	public String get_column() {
		return _column;
	}

	/**
	 * @param _column the _column to set
	 */
	public void set_column(String _column) {
		this._column = _column;
	}

	/**
	 * @return the _subject
	 */
	public String get_subject() {
		return _subject;
	}

	/**
	 * @param _subject the _subject to set
	 */
	public void set_subject(String _subject) {
		this._subject = _subject;
	}

	/**
	 * @return the _priod_config
	 */
	public String get_priod_config() {
		return _priod_config;
	}

	/**
	 * @param _priod_config the _priod_config to set
	 */
	public void set_priod_config(String _priod_config) {
		this._priod_config = _priod_config;
	}

	/**
	 * @return the _day
	 */
	public String get_day() {
		return _day;
	}

	/**
	 * @param _day the _day to set
	 */
	public void set_day(String _day) {
		this._day = _day;
	}

	/**
	 * @return the _priod_count
	 */
	public String get_priod_count() {
		return _priod_count;
	}

	/**
	 * @param _priod_count the _priod_count to set
	 */
	public void set_priod_count(String _priod_count) {
		this._priod_count = _priod_count;
	}

	/**
	 * @return the _priod_id
	 */
	public String get_priod_id() {
		return _priod_id;
	}

	/**
	 * @param _priod_id the _priod_id to set
	 */
	public void set_priod_id(String _priod_id) {
		this._priod_id = _priod_id;
	}

	/**
	 * @return the _priod_start_time
	 */
	public String get_priod_start_time() {
		return _priod_start_time;
	}

	/**
	 * @param _priod_start_time the _priod_start_time to set
	 */
	public void set_priod_start_time(String _priod_start_time) {
		this._priod_start_time = _priod_start_time;
	}

	/**
	 * @return the _priod_end_time
	 */
	public String get_priod_end_time() {
		return _priod_end_time;
	}

	/**
	 * @param _priod_end_time the _priod_end_time to set
	 */
	public void set_priod_end_time(String _priod_end_time) {
		this._priod_end_time = _priod_end_time;
	}

	/**
	 * @return the _event_id
	 */
	public String get_event_id() {
		return _event_id;
	}

	/**
	 * @param _event_id the _event_id to set
	 */
	public void set_event_id(String _event_id) {
		this._event_id = _event_id;
	}

	/**
	 * @return the _event_name
	 */
	public String get_event_name() {
		return _event_name;
	}

	/**
	 * @param _event_name the _event_name to set
	 */
	public void set_event_name(String _event_name) {
		this._event_name = _event_name;
	}

	/**
	 * @return the _event_type
	 */
	public String get_event_type() {
		return _event_type;
	}

	/**
	 * @param _event_type the _event_type to set
	 */
	public void set_event_type(String _event_type) {
		this._event_type = _event_type;
	}

	/**
	 * @return the _event_date
	 */
	public String get_event_date() {
		return _event_date;
	}

	/**
	 * @param _event_date the _event_date to set
	 */
	public void set_event_date(String _event_date) {
		this._event_date = _event_date;
	}

	// school
    public String _school_id;
    public String _school_name;
    
    // class table
   
    public String _class_id;
    public String _cls_capacity;
    public String _advisory_name;
    public String _row;
    public String _column;
    public String _subject;
    
    
    // priod config table
    
    public String _priod_config;
    public String _day;
    public String _priod_count;
    
    // priod table
    public String _priod_id;
    public String _priod_start_time;
    public String _priod_end_time;
    
    // event table
    
    public String _event_id;
    public String _event_name;
    public String _event_type;
    public String _event_date;
    
    
    
    
    
    
    
    
    
    
    
    
    
   // ..............................................................................................
    public beanContact() {
    }

    // constructor
    public beanContact(int id, String name, String _phone_number, String _email) {
	this._id = id;
	this._name = name;
	this._phone_number = _phone_number;
	this._email = _email;

    }

    // constructor
    public beanContact(String name, String _phone_number, String _email) {
	this._name = name;
	this._phone_number = _phone_number;
	this._email = _email;
    }

    // getting ID
    public int getID() {
	return this._id;
    }

    // setting id
    public void setID(int id) {
	this._id = id;
    }

    // getting name
    public String getName() {
	return this._name;
    }

    // setting name
    public void setName(String name) {
	this._name = name;
    }

    // getting phone number
    public String getPhoneNumber() {
	return this._phone_number;
    }

    // setting phone number
    public void setPhoneNumber(String phone_number) {
	this._phone_number = phone_number;
    }

    // getting email
    public String getEmail() {
	return this._email;
    }

    // setting email
    public void setEmail(String email) {
	this._email = email;
    }

}