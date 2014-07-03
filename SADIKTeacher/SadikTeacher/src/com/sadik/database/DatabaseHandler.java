package com.sadik.database;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.sadik.model.BeanAssignmentDetails;
import com.sadik.model.BeanClassIdsNameArray;
import com.sadik.model.BeanClassInfo;
import com.sadik.model.BeanConfigPriodSetting;
import com.sadik.model.BeanEvent;
import com.sadik.model.BeanGradeCategory;
import com.sadik.model.BeanHoliday;
import com.sadik.model.BeanPriodSetting;
import com.sadik.model.BeanSchool;
import com.sadik.model.BeanSettingAttendanceType;
import com.sadik.model.BeanStudent;
import com.sadik.model.BeanTeacher;
import com.sadik.model.beanContact;
import com.sadik.teacher.ArrayConverter;
import com.sadik.teacher.DateConverter;
import com.sadik.teacher.R;
import com.sadik.teacher.ToastCustom;

public class DatabaseHandler extends SQLiteOpenHelper {

	// All Static variables
	// Database Version
	private static final int DATABASE_VERSION = 1;

	// Database Name
	private static final String DATABASE_NAME = "shadiksManager";

	// Contacts table name
	private static final String TABLE_CONTACTS = "contacts";
	private static final String TABLE_SUBJECT = "TABLE_SUBJECT";
	private static final String TABLE_TEACHER = "TABLE_TEACHER";
	private static final String TABLE_STUDENT = "TABLE_STUDENT";
	private static final String TABLE_GARDIAN_PARENTS = "TABLE_GARDIAN_PARENTS";
	private static final String TABLE_CLASS = "TABLE_CLASS";
	private static final String TABLE_SCHOOL = "TABLE_SCHOOL";
	private static final String TABLE_PRIOD = "TABLE_PRIOD";
	//private static final String TABLE_PRIOD_SCHUDLE = "TABLE_PRIOD_SCHUDLE";
	private static final String TABLE_EVENT = "TABLE_EVENT";
	private static final String TABLE_HOLYDAY = "TABLE_HOLYDAY";
	private static final String TABLE_ATTENDANCE_TYPE = "TABLE_ATTENDANCE_TYPE";
	private static final String TABLE_PRIOD_CONFIG = "TABLE_PRIOD_CONFIG";
	private static final String TABLE_GRADE_TYPE = "TABLE_GRADE_TYPE";
	private static final String TABLE_GRADE_CATEGORY = "TABLE_GRADE_CATEGORY";
	private static final String TABLE_ASSIGNMENT_DETAILS = "TABLE_ASSIGNMENT_DETAILS";

	private static final String GRADE_CATEGORY_ID = "GRADE_CATEGORY_ID";
	private static final String GRADE_CATEGORY_NAME = "GRADE_CATEGORY_NAME";
	private static final String GRADE_CATEGORY_WEIGHTAGE = "GRADE_CATEGORY_WEIGHTAGE";
	// Contacts Table Columns names
	private static final String KEY_ID = "id";
	private static final String KEY_NAME = "name";
	private static final String KEY_PH_NO = "phone_number";
	private static final String KEY_EMAIL = "email";
	// ..................... TABLE FILED ...........................
	private static final String EVENT_ID = "EVENT_ID";
	private static final String EVENT_NAME = "EVENT_NAME";
	private static final String EVNT_DATE = "EVNT_DATE";
	private static final String EVENT_TYPE = "EVENT_TYPE";

	// SCHOOL TABLE FILED

	private static final String SCHOOL_PKEY = "SCHOOL_PKEY";
	private static final String SCHOOL_ID = "SCHOOL_ID";
	//private static final String SCHOOL_NAME = "SCHOOL_NAME";
	private static final String SCHOOL_PERIOD_COUNT = "SCHOOL_PERIOD_COUNT";
	private static final String SCHOOL_EFFECTIVE_DAYS = "SCHOOL_EFFECTIVE_DAYS";
	private static final String SCHOOL_ADDRESS = "SCHOOL_ADDRESS";

	// PRIOD SCHEDULE
	private static final String PRIOD_S_ID = "PRIOD_S_ID";

	private static final String SUBJECT_ID = "SUBJECT_ID";
	private static final String SUBJECT_NAME = "SUBJECT_NAME";
	private static final String s_DAY = "s_DAY";

	// TABLE PRIOD
	private static final String PRIOD_ID = "PRIOD_ID";
	private static final String PRIOD_START_TIME = "PRIOD_START_TIME";
	private static final String PRIOD_END_TIME = "PRIOD_END_TIME";

	// /////////////////// TABLE HOLY DAY
	private static final String H_ID = "H_ID";
	private static final String H_DATE = "H_DATE";
	private static final String H_NAME = "H_NAME";

	// TABLE CLASS
	private static final String CLASS_ID = "CLASS_ID";
	private static final String CAPICITY = "CAPICITY";
	private static final String CLASS_STANDARD = "CLASS_STANDARD";
	private static final String CLASS_SECTION = "CLASS_SECTION";
	private static final String ADVISORY_NAME = "ADVISORY_NAME";
	private static final String ROW = "ROW";
	private static final String COLUMN = "COLUMN";
	private static final String PRIOD_COUNT = "PRIOD_COUNT";

	// TABLE STUDENT

	private static final String STUDENT_PKEY = "STUDENT_PKEY";
	private static final String STUDENT_ID = "STUDENT_ID";
	private static final String S_F_NAME = "S_F_NAME";
	private static final String S_M_NAME = "S_M_NAME";
	private static final String S_L_NAME = "S_L_NAME";
	private static final String S_DOB = "S_DOB";
	private static final String S_RELIGION_NAME = "RELIGION_NAME";
	private static final String S_EMAIL_ID = "S_EMAIL_ID";
	private static final String S_PHONE_NO = "S_PHONE_NO";
	private static final String S_SEX = "S_SEX";
	private static final String S_IMG_PATH = "S_IMG_PATH";
	private static final String S_ADDRESS = "S_ADDRESS";
	private static final String S_FATHERNAME = "S_FATHERNAME";
	private static final String S_FATHERDOB = "S_FATHERDOB";
	private static final String S_MOTHERNAME = "S_MOTHERNAME";
	private static final String S_MOTHERDOB = "S_MOTHERDOB";
	private static final String S_GUARDIANEMAIL = "S_GUARDIANEMAIL";
	private static final String S_GUARDIANPHONE = "S_GUARDIANPHONE";

	// TABLE ATTENDANCE TYPE
	
	private static final String ATTENDANCE_TYPE_ID = "ATTENDANCE_TYPE_ID";
	private static final String ATTENDANCE_TYPE_NAME = "ATTENDANCE_TYPE_NAME";
	private static final String ATTENDANCE_TYPE_COLOR = "ATTENDANCE_TYPE_COLOR";



	// TABLE TEACHER
	private static final String T_ID = "T_id";
	private static final String TEACHER_SCHOOL_ID = "teacher_school_id";
	private static final String TEACHER_ID = "teacher_id";
	private static final String TEACHER_F_NAME = "teacher_f_name";
	private static final String TEACHER_L_NAME = "teacher_l_name";
	private static final String TEACHER_DOB = "teacher_dob";
	private static final String TEACHER_RELIGION = "teacher_religion";
	private static final String TEACHER_MOB_NO = "teacher_mobile_number";
	private static final String TEACHER_EMAIL_ID = "teacher_email_id";
	private static final String TEACHER_ADDRESS = "teacher_address";
	private static final String TEACHER_ADD_PREVIENCE = "teacher_add_previence";
	private static final String TEACHER_ADD_CITY = "teacher_add_city";
	private static final String TEACHER_ADD_DISTRICT = "teacher_add_district";
	private static final String TEACHER_ADD_SUBDTT_VILL = "teacher_add_subDtt_vill";
	private static final String TEACHER_SEX = "teacher_sex";
	private static final String TEACHER_STATUS_DEG = "teacher_status_deg";
	private static final String TEACHER_EMP_STATUS = "teacher_emp_status";
	private static final String TEACHER_DATE_OF_JOINING = "teacher_Date_of_joining";
	private static final String TEACHER_IMG_PATH = "Teacher_img_path";
	
	// Table priod config;

	private static final String PRIOD_CONF_SID = "PRIOD_CONF_SID";
	private static final String PRIODS = "PRIODS";
	private static final String PRI_CLASS = "PRI_CLASS";
	private static final String PRI_SUBJECT = "PRI_SUBJECT";
	private static final String PRI_RECURENCE = "PRI_RECURENCE";
	private static final String RECURENCE_DAY = "RECURENCE_DAY";
	
	// TABLE_ASSIGNMENT_DETAILS FIELDS
	private static final String Tab_ASSIGNMENT_ID = "Tab_ASSIGNMENT_ID";
	private static final String ASSIGNMENT_SCHOOL_ID = "ASSIGNMENT_SCHOOL_ID";
	private static final String ASSIGNMENT_CLASS = "ASSIGNMENT_CLASS";
	private static final String ASSIGNMENT_SUBJECT = "ASSIGNMENT_SUBJECT";
	private static final String ASSIGNMENT_EXAM_TYPE = "ASSIGNMENT_EXAM_TYPE";
	private static final String ASSIGNMENT_EXAM_ID = "ASSIGNMENT_EXAM_ID";
	private static final String ASSIGNMENT_GRADE_CATEGORY = "ASSIGNMENT_GRADE_CATEGORY";
	private static final String ASSIGNMENT_GRADE_TYPE = "ASSIGNMENT_GRADE_TYPE";
	private static final String ASSIGNMENT_MIN_POINT_KKH = "ASSIGNMENT_MIN_POINT_KKH";
	private static final String ASSIGNMENT_WEIGHT = "ASSIGNMENT_WEIGHT";
	private static final String ASSIGNMENT_DATE = "ASSIGNMENT_DATE";
	private static final String ASSIGNMENT_PRIOD = "ASSIGNMENT_PRIOD";

	private final ArrayList<beanContact> contact_list = new ArrayList<beanContact>();
	private final ArrayList<BeanConfigPriodSetting> config_priod_list = new ArrayList<BeanConfigPriodSetting>();
	
	Context context;

	public DatabaseHandler(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.context = context;
	}

	// Creating Tables
	@Override
	public void onCreate(SQLiteDatabase db) {

		String CREATE_SCHOOL_TABLE = "CREATE TABLE " + TABLE_SCHOOL + "("
				+ SCHOOL_PKEY + " INTEGER PRIMARY KEY AUTOINCREMENT,"+ SCHOOL_ID + " TEXT UNIQUE," + SCHOOL_PERIOD_COUNT + " INTEGER,"
				+ SCHOOL_EFFECTIVE_DAYS + " TEXT," + SCHOOL_ADDRESS + " TEXT" + ")";


		String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
				+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
				+ KEY_PH_NO + " TEXT," + KEY_EMAIL + " TEXT" + ")";

		String CREATE_STUDENT_TABLE = "CREATE TABLE " + TABLE_STUDENT
				+ "("+STUDENT_PKEY+" INTEGER PRIMARY KEY AUTOINCREMENT," + STUDENT_ID+" TEXT,"+ S_F_NAME+" TEXT,"
				+ S_M_NAME+" TEXT," + S_L_NAME+" TEXT," + S_DOB+" TEXT,"+SCHOOL_PKEY +" TEXT,"
				+ S_RELIGION_NAME+" TEXT," + S_ADDRESS+" TEXT," + S_EMAIL_ID+" TEXT,"
				+ S_PHONE_NO+" TEXT," + S_SEX+" TEXT," + S_IMG_PATH+" TEXT,"+ CLASS_ID+" TEXT,"
				+ S_FATHERNAME+" TEXT,"+ S_FATHERDOB+" TEXT,"+ S_MOTHERNAME+" TEXT,"
				+ S_MOTHERDOB+" TEXT,"+ S_GUARDIANEMAIL+" TEXT,"
				+ S_GUARDIANPHONE+" TEXT, FOREIGN KEY("+SCHOOL_PKEY+") REFERENCES "+TABLE_SCHOOL+"("+SCHOOL_PKEY+"))";

		String CREATE_TABLE_GARDIAN_PARENTS = "CREATE TABLE "
				+ TABLE_GARDIAN_PARENTS
				+ "(STUDENT_ID  TEXT,"
				+ "GARDIA_P_ID TEXT PRIMARY KEY,F_NAME TEXT,M_NAME TEXT,L_NAME TEXT,"
				+ "ADDRESS TEXT,EMAIL TEXT,PHONE_NO TEXT,SEX TEXT,P_IMG_PATH TEXT,"
				+ "RELATION_W_CHILD TEXT,ISPARENT  BOOLEAN ,FOREIGN KEY(STUDENT_ID) REFERENCES  STUDENT(STUDENT_ID));";

		// String CREATE_TABLE_EVENT="CREATE TABLE"+
		// TABLE_EVENT+"(EVENT_ID TEXT PRIMARY KEY,EVENT_NAME TEXT,DATE TEXT,EVENT_TYPE TEXT)";
		String CREATE_TABLE_CLASS = "CREATE TABLE "
				+ TABLE_CLASS
				+ "("+ CLASS_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+CAPICITY+" INTEGER,"+ADVISORY_NAME+" TEXT,"
				+CLASS_STANDARD+" TEXT,"+CLASS_SECTION+" TEXT,"
				+ ROW+" INTEGER,"+COLUMN+" INTEGER,"+SCHOOL_PKEY +" TEXT,"+SUBJECT_NAME+" TEXT,"
				+ PRIOD_COUNT+" TEXT, FOREIGN KEY("+SCHOOL_PKEY+") REFERENCES "+TABLE_SCHOOL+"("+SCHOOL_PKEY+"))";

		String CREATE_TABLE_SUBJECT="CREATE TABLE "+TABLE_SUBJECT+"("+SUBJECT_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+SUBJECT_NAME+" TEXT)";
		
		
		String CREATE_EVENT_TABLE = "CREATE TABLE " + TABLE_EVENT + "("
				+ EVENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+SCHOOL_PKEY +" TEXT," + EVENT_NAME + " TEXT,"
				+ EVNT_DATE + " TEXT," + EVENT_TYPE + " TEXT, FOREIGN KEY("+SCHOOL_PKEY+") REFERENCES "+TABLE_SCHOOL+"("+SCHOOL_PKEY+"))";

		String CREATE_HOLY_DAY_TABLE = "CREATE TABLE " + TABLE_HOLYDAY + "("
				+ H_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"+SCHOOL_PKEY +" TEXT," + H_DATE + " TEXT," + H_NAME
				+ " TEXT)";
		
		String CREATE_ATTENDANCE_TYPE_TABLE = "CREATE TABLE " + TABLE_ATTENDANCE_TYPE + "("
				+ ATTENDANCE_TYPE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + SCHOOL_PKEY + " TEXT," + ATTENDANCE_TYPE_NAME + " TEXT,"
				+ ATTENDANCE_TYPE_COLOR + " TEXT , FOREIGN KEY("+SCHOOL_PKEY+") REFERENCES "+TABLE_SCHOOL+"("+SCHOOL_PKEY+"))";
		

		String CREATE_TABLE_GRADE_CATEGORY = "CREATE TABLE "
				+ TABLE_GRADE_CATEGORY
				+ "("+ GRADE_CATEGORY_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+SCHOOL_PKEY +" TEXT,"+GRADE_CATEGORY_NAME+" TEXT,"
				+GRADE_CATEGORY_WEIGHTAGE+" REAL, FOREIGN KEY("+SCHOOL_PKEY+") REFERENCES "+TABLE_SCHOOL+"("+SCHOOL_PKEY+"))";
		
		
		/*
		 * String CREATE_PERIOD_SCHED_TABLE = " CREATE TABLE " +
		 * TABLE_PRIOD_SCHUDLE + "(" + PRIOD_S_ID + " TEXT  PRIMARY KEY," +
		 * CLASS_ID + " TEXT," + SUBJECT_ID + " TEXT," + s_DAY + " TEXT," +
		 * START_TIME + " TEXT," + END_TIME + " TEXT" +")";
		 */

		String CREATE_TABLE_PRIOD = "CREATE TABLE " + TABLE_PRIOD + "("
				+ SCHOOL_PKEY + " TEXT," + PRIOD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + PRIOD_START_TIME
				+ " TEXT," + PRIOD_END_TIME + " TEXT,FOREIGN KEY(" + SCHOOL_PKEY
				+ ") REFERENCES " + TABLE_SCHOOL + "(" + SCHOOL_PKEY + "))";

		String CREATE_TABLE_TEACHER = "CREATE TABLE " + TABLE_TEACHER + "("
				+ SCHOOL_PKEY + " TEXT," + TEACHER_ID + " TEXT PRIMARY KEY ,"
				+ TEACHER_F_NAME + " TEXT," + TEACHER_L_NAME + " TEXT,"
				+ TEACHER_DOB + " TEXT," + TEACHER_MOB_NO + " TEXT,"
				+ TEACHER_EMAIL_ID + " TEXT," + TEACHER_SEX + " TEXT,"
				+ TEACHER_STATUS_DEG + " TEXT," + TEACHER_RELIGION + " TEXT,"
				+ TEACHER_ADD_PREVIENCE + " TEXT," + TEACHER_ADD_CITY
				+ " TEXT," + TEACHER_ADD_DISTRICT + " TEXT,"
				+ TEACHER_ADD_SUBDTT_VILL + " TEXT," + TEACHER_EMP_STATUS
				+ " TEXT," + TEACHER_DATE_OF_JOINING + " TEXT,"
				+ TEACHER_SCHOOL_ID + " TEXT," + TEACHER_IMG_PATH + " TEXT,"
				+ TEACHER_ADDRESS + " TEXT" + ")";

		String CREATE_CONFIG_PRIOD_TABLE = "CREATE TABLE " + TABLE_PRIOD_CONFIG
				+ "(" + PRIOD_CONF_SID + " TEXT," + PRIODS + " TEXT,"
				+ SCHOOL_PKEY + " TEXT," + PRI_CLASS + " TEXT," + PRI_SUBJECT
				+ " TEXT," + PRI_RECURENCE + " TEXT," + RECURENCE_DAY
				+ " TEXT, FOREIGN KEY(" + SCHOOL_PKEY + ") REFERENCES "
				+ TABLE_SCHOOL + "(" + SCHOOL_PKEY + "))";

		// CREATE TABLE ASSIGNMENT STATEMENTS
		String CREATE_TABLE_ASSIGNMENT = "CREATE TABLE "
				+ TABLE_ASSIGNMENT_DETAILS + "(" + Tab_ASSIGNMENT_ID
				+ " INTEGER PRIMARY KEY," + SCHOOL_PKEY + " TEXT, "
				+ ASSIGNMENT_SCHOOL_ID + " TEXT," + ASSIGNMENT_CLASS + " TEXT,"
				+ ASSIGNMENT_SUBJECT + " TEXT," + ASSIGNMENT_EXAM_TYPE
				+ " TEXT," + ASSIGNMENT_EXAM_ID + " TEXT,"
				+ ASSIGNMENT_GRADE_CATEGORY + " TEXT," + ASSIGNMENT_GRADE_TYPE
				+ " TEXT," + ASSIGNMENT_MIN_POINT_KKH + " TEXT,"
				+ ASSIGNMENT_WEIGHT + " TEXT," + ASSIGNMENT_DATE + " TEXT,"
				+ ASSIGNMENT_PRIOD + " TEXT, FOREIGN KEY(" + SCHOOL_PKEY
				+ ") REFERENCES " + TABLE_SCHOOL + "(" + SCHOOL_PKEY + "))";


		db.execSQL(CREATE_SCHOOL_TABLE);
		db.execSQL(CREATE_EVENT_TABLE);
		db.execSQL(CREATE_TABLE_PRIOD);
		db.execSQL(CREATE_HOLY_DAY_TABLE);
		db.execSQL(CREATE_TABLE_CLASS);
		db.execSQL(CREATE_STUDENT_TABLE);
		db.execSQL(CREATE_TABLE_GARDIAN_PARENTS);
		db.execSQL(CREATE_TABLE_TEACHER);
		db.execSQL(CREATE_CONTACTS_TABLE);
		db.execSQL(CREATE_TABLE_SUBJECT);
		db.execSQL(CREATE_ATTENDANCE_TYPE_TABLE);
		db.execSQL(CREATE_CONFIG_PRIOD_TABLE);
		db.execSQL(CREATE_TABLE_GRADE_CATEGORY);
		db.execSQL(CREATE_TABLE_ASSIGNMENT);

	}

	// Upgrading database
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Drop older table if existed
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

		// Create tables again
		onCreate(db);
	}
	
	public String insertOrUpdate_School(BeanSchool beanSchool) {

		boolean result = false;
		String seperater = context.getString(R.string.seperater);

		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(SCHOOL_ID, beanSchool.getSchoolId()); 
		values.put(SCHOOL_EFFECTIVE_DAYS, beanSchool.getEffective_Days()); 
		values.put(SCHOOL_PERIOD_COUNT, beanSchool.getPeriod_Count()); 
		values.put(SCHOOL_ADDRESS, beanSchool.getAddress()+seperater + beanSchool.getSubdist()+seperater 
				+ beanSchool.getDistrict() +seperater + beanSchool.getCity()+seperater
				+beanSchool.getState()+seperater+ beanSchool.getZipCode()); 

		try
		{
		if(beanSchool.getSchoolPkey()!=null)
		{
			db.update(TABLE_SCHOOL, values, SCHOOL_PKEY + " = ?",
					new String[] { String.valueOf(beanSchool.getSchoolPkey()) });
			Log.i("query", "update");
		}
		else
		{
			db.insertOrThrow(TABLE_SCHOOL, null, values);
			Log.i("query", "insertOrThrow");
		}
			result = true;
		}
		catch(SQLiteConstraintException e)
		{
			e.printStackTrace();
			result = false;
		}
		db.close();
		String pkey = "";
		if(result)
		{
			pkey= getPrimaryKeybyId(beanSchool.getSchoolId());
		}
		return pkey;
	}
	
	public int Update_School1(BeanSchool beanSchool) {

		boolean result = false;
		String seperater = context.getString(R.string.seperater);

		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(SCHOOL_ID, beanSchool.getSchoolId()); 
		values.put(SCHOOL_EFFECTIVE_DAYS, beanSchool.getEffective_Days()); 
		values.put(SCHOOL_PERIOD_COUNT, beanSchool.getPeriod_Count()); 
		values.put(SCHOOL_ADDRESS, beanSchool.getAddress()+seperater + beanSchool.getSubdist()+seperater 
				+ beanSchool.getDistrict() +seperater + beanSchool.getCity()+seperater
				+beanSchool.getState()+seperater+ beanSchool.getZipCode()); 

		// updating row
		return db.update(TABLE_SCHOOL, values, SCHOOL_PKEY + " = ?",
				new String[] { String.valueOf(beanSchool.getSchoolPkey()) });
	}


	public String Add_School1(BeanSchool beanSchool) {

		boolean result = false;
		String seperater = context.getString(R.string.seperater);
		
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(SCHOOL_ID, beanSchool.getSchoolId()); 
		values.put(SCHOOL_EFFECTIVE_DAYS, beanSchool.getEffective_Days()); 
		values.put(SCHOOL_PERIOD_COUNT, beanSchool.getPeriod_Count()); 
		values.put(SCHOOL_ADDRESS, beanSchool.getAddress()+seperater + beanSchool.getSubdist()+seperater 
				+ beanSchool.getDistrict() +seperater + beanSchool.getCity()+seperater
				+beanSchool.getState()+seperater+ beanSchool.getZipCode()); 
		try
		{
		db.insertOrThrow(TABLE_SCHOOL, null, values);
		result = true;
		}
		catch(SQLiteConstraintException e)
		{
			e.printStackTrace();
			result = false;
		}
		db.close();
		String pkey = "";
		if(result)
		{
			pkey= getPrimaryKeybyId(beanSchool.getSchoolId());
		}
		return pkey;
	}

	public void delete_Class(String[] classIds, String SpKey) {
		SQLiteDatabase db = this.getWritableDatabase();
		//db.delete(TABLE_CLASS, SCHOOL_PKEY + " = ?",
				//new String[] { String.valueOf(SpKey) });
		String query = "DELETE FROM "+TABLE_CLASS+" where "+SCHOOL_PKEY+" = "+SpKey+" AND "+CLASS_ID+" NOT IN ("+ArrayConverter.toCommaSeperated(classIds)+")";
		Log.i("query", query);
		db.execSQL(query);
		db.close();
	}
	
	public void Add_Class1(BeanClassInfo beanClassInfo) {
		
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(CLASS_STANDARD, beanClassInfo.getClassStandard());
		values.put(CLASS_SECTION, beanClassInfo.getSection());
		values.put(SUBJECT_NAME, beanClassInfo.getSubjects());
		values.put(SCHOOL_PKEY, beanClassInfo.getSchoolPkey());
		// Inserting Row
		db.insert(TABLE_CLASS, null, values);
		db.close();
	}

	public void insertOrUpdate_ClassBasicInfo(BeanClassInfo beanClassInfo) {

		boolean result = false;
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(CLASS_STANDARD, beanClassInfo.getClassStandard());
		values.put(CLASS_SECTION, beanClassInfo.getSection());
		values.put(SUBJECT_NAME, beanClassInfo.getSubjects());
		values.put(SCHOOL_PKEY, beanClassInfo.getSchoolPkey());

		try
		{
		if(beanClassInfo.getClassId() ==null || beanClassInfo.getClassId().equalsIgnoreCase(""))
		{
			db.insertOrThrow(TABLE_CLASS, null, values);
			Log.i("query CLASS", "insertOrThrow");
		}
		else
		{
			db.update(TABLE_CLASS, values, CLASS_ID + " = ?",
					new String[] { String.valueOf(beanClassInfo.getClassId()) });
			Log.i("query CLASS", "update "+beanClassInfo.getClassId());
		}
			result = true;
		}
		catch(SQLiteConstraintException e)
		{
			e.printStackTrace();
			result = false;
		}
		db.close();
		
	}

	public void update_ClassCapacityInfo (BeanClassInfo beanClassInfo) {

		boolean result = false;
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(ADVISORY_NAME, beanClassInfo.getAdviserName());
		values.put(CAPICITY, beanClassInfo.getCapacity());
		values.put(ROW, beanClassInfo.getRow());
		values.put(COLUMN, beanClassInfo.getColumn());

		try
		{
			db.update(TABLE_CLASS, values, CLASS_ID + " = ?",
					new String[] { String.valueOf(beanClassInfo.getClassId()) });
			Log.i("query", "update");

			result = true;
		}
		catch(SQLiteConstraintException e)
		{
			e.printStackTrace();
			result = false;
		}
		db.close();
		
	}
	
	public ArrayList<BeanStudent> getAllStudentListData(String SchoolPKey) {
		
		ArrayList<BeanStudent> beanSchools = new ArrayList<BeanStudent>();
		
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_STUDENT, new String[] { STUDENT_ID, S_F_NAME, S_L_NAME, S_DOB
				, S_SEX, S_IMG_PATH, STUDENT_PKEY}, STUDENT_PKEY + "=?",
				new String[] { String.valueOf(SchoolPKey) }, null, null, null, null);
		if (cursor != null)
		{
			while(cursor.moveToNext())
			{
				BeanStudent beanStudent = new BeanStudent();
				beanStudent.setStudent_ID(cursor.getString(0));
				beanStudent.setF_Name(cursor.getString(1));
				beanStudent.setL_Name(cursor.getString(2));
				beanStudent.setDob(cursor.getString(3));
				beanStudent.setGender(cursor.getString(4));
				beanStudent.setImgPath(cursor.getString(5));
				beanStudent.setStudent_Pkey(""+cursor.getInt(6));
				beanSchools.add(beanStudent);
			}
		}

		cursor.close();
		db.close();

		return beanSchools;
	}

	public BeanStudent getStudentCompleteInfo(String StudentPkey) {
		
		BeanStudent beanStudent = new BeanStudent();
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_STUDENT, new String[] { STUDENT_ID, S_F_NAME, S_L_NAME, S_DOB, S_SEX,
				S_IMG_PATH, CLASS_ID, S_RELIGION_NAME, S_EMAIL_ID, S_PHONE_NO, S_FATHERNAME, S_FATHERDOB,
				S_MOTHERNAME, S_MOTHERDOB, S_GUARDIANEMAIL, S_GUARDIANPHONE, S_ADDRESS, STUDENT_PKEY}, STUDENT_PKEY + "=?",
				new String[] { String.valueOf(StudentPkey) }, null, null, null, null);
		if (cursor != null)
		{
			while(cursor.moveToNext())
			{
				beanStudent.setStudent_ID(cursor.getString(0));
				beanStudent.setF_Name(cursor.getString(1));
				beanStudent.setL_Name(cursor.getString(2));
				beanStudent.setDob(cursor.getString(3));
				beanStudent.setGender(cursor.getString(4));
				beanStudent.setImgPath(cursor.getString(5));
				beanStudent.setClass_Id(cursor.getString(6));
				beanStudent.setReligion(cursor.getString(7));
				beanStudent.setEmail(cursor.getString(8));
				beanStudent.setPhone(cursor.getString(9));
				beanStudent.setFatherName(cursor.getString(10));
				beanStudent.setFatherDob(cursor.getString(11));
				beanStudent.setMotherName(cursor.getString(12));
				beanStudent.setMotherDob(cursor.getString(13));
				beanStudent.setGuardianEmail(cursor.getString(14));
				beanStudent.setGuardianPhone(cursor.getString(15));
				beanStudent.setStudent_Pkey(""+cursor.getInt(17));
				
				String addressArray[] = cursor.getString(16).split(context.getResources().getString(R.string.seperater));
				beanStudent.setAddress(addressArray[0]);
				beanStudent.setSubdist(addressArray[1]);
				beanStudent.setDistrict(addressArray[2]);
				beanStudent.setCity(addressArray[3]);
				beanStudent.setState(addressArray[4]);
				
				beanStudent.setResult(true);
			}
		}
		else
		{
			beanStudent.setResult(false);
		}

		cursor.close();
		db.close();

		return beanStudent;
	}

	public boolean insertOrUpdate_Student(BeanStudent beanStudent) {

		boolean result = false;
		String seperater = context.getString(R.string.seperater);

		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		
		values.put(CLASS_ID, beanStudent.getClass_Id()); 
		values.put(STUDENT_ID, beanStudent.getStudent_ID()); 
		values.put(S_F_NAME, beanStudent.getF_Name());
//		values.put(S_M_NAME, beanStudent.getM_Name());
		values.put(S_L_NAME, beanStudent.getL_Name());
		values.put(S_DOB, beanStudent.getDob()); 
		values.put(S_RELIGION_NAME, beanStudent.getReligion()); 
		values.put(S_EMAIL_ID, beanStudent.getEmail()); 
		values.put(S_PHONE_NO, beanStudent.getPhone()); 
		values.put(S_SEX, beanStudent.getGender()); 
		values.put(S_IMG_PATH, beanStudent.getImgPath()); 
		values.put(S_FATHERNAME, beanStudent.getFatherName()); 
		values.put(S_FATHERDOB, beanStudent.getFatherDob()); 
		values.put(S_MOTHERNAME, beanStudent.getMotherName()); 
		values.put(S_MOTHERDOB, beanStudent.getMotherDob()); 
		values.put(S_GUARDIANEMAIL, beanStudent.getGuardianEmail()); 
		values.put(S_GUARDIANPHONE, beanStudent.getGuardianPhone()); 
		values.put(S_ADDRESS, beanStudent.getAddress()+seperater + beanStudent.getSubdist()+seperater 
				+ beanStudent.getDistrict() +seperater + beanStudent.getCity()+seperater
				+beanStudent.getState()); 
		values.put(SCHOOL_PKEY, beanStudent.getSchool_Pkey()); 

		try
		{
		if(beanStudent.getStudent_Pkey() !=null)
		{
			db.update(TABLE_STUDENT, values, STUDENT_PKEY + " = ?",
					new String[] { String.valueOf(beanStudent.getStudent_Pkey()) });
			Log.i("query", "update");
		}
		else
		{
			db.insertOrThrow(TABLE_STUDENT, null, values);
			Log.i("query", "insertOrThrow");
		}
			result = true;
		}
		catch(SQLiteConstraintException e)
		{
			e.printStackTrace();
			result = false;
		}
		
		return result;
	}

	
	public void Add_Contact(beanContact contact) {
		
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_NAME, contact.getName()); // Contact Name
		values.put(KEY_PH_NO, contact.getPhoneNumber()); // Contact Phone
		values.put(KEY_EMAIL, contact.getEmail()); // Contact Email
		// Inserting Row
		db.insert(TABLE_CONTACTS, null, values);
		db.close();
	}
	
	

	// Getting single contact
	public String getPrimaryKeybyId(String id) {
		
		String SpKey = "";
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_SCHOOL, new String[] { SCHOOL_PKEY }, SCHOOL_ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
		{
			if(cursor.moveToFirst())
			{
				SpKey = ""+cursor.getInt(0);
				Log.i("cursor.moveToFirst()", ": true");
			}
			Log.i("cursor", ": true");
		}

		cursor.close();
		db.close();

		return SpKey;
	}
	
	public ArrayList<BeanSchool> getSchoolDatas() {
		
		ArrayList<BeanSchool> beanSchools = new ArrayList<BeanSchool>();
		
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_SCHOOL, new String[] { SCHOOL_PKEY, SCHOOL_ID
				, SCHOOL_PERIOD_COUNT, SCHOOL_EFFECTIVE_DAYS, SCHOOL_ADDRESS}, null,
				null, null, null, null, null);
		if (cursor != null)
		{
			while(cursor.moveToNext())
			{
				BeanSchool beanSchool = new BeanSchool();
				beanSchool.setSchoolPkey(""+cursor.getInt(0));
				beanSchool.setSchoolId(cursor.getString(1));
				beanSchool.setEffective_Days(cursor.getString(3));
				beanSchool.setPeriod_Count(cursor.getInt(2));
				
				String [] addressArray= cursor.getString(4).split(context.getResources().getString(R.string.seperater));
				beanSchool.setAddress(addressArray[0]);
				beanSchool.setSubdist(addressArray[1]);
				beanSchool.setDistrict(addressArray[2]);
				beanSchool.setCity(addressArray[3]);
				beanSchool.setState(addressArray[4]);
				beanSchool.setZipCode(addressArray[5]);

				beanSchool.setResponse(true);
				beanSchools.add(beanSchool);
			}
		}

		cursor.close();
		db.close();

		return beanSchools;
	}

	
	public BeanSchool getSchoolData(String id) {
		
		BeanSchool beanSchool = new BeanSchool();
		String SpKey = "";
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_SCHOOL, new String[] { SCHOOL_PKEY, SCHOOL_ID, 
				SCHOOL_PERIOD_COUNT, SCHOOL_EFFECTIVE_DAYS, SCHOOL_ADDRESS}, SCHOOL_PKEY + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);

		if (cursor != null)
		{
			if(cursor.moveToFirst())
			{
				SpKey = ""+cursor.getInt(0);
				beanSchool.setSchoolPkey(""+cursor.getInt(0));
				beanSchool.setSchoolId(cursor.getString(1));
				beanSchool.setEffective_Days(cursor.getString(3));
				beanSchool.setPeriod_Count(cursor.getInt(2));
				
				String [] addressArray= cursor.getString(4).split(context.getResources().getString(R.string.seperater));
				beanSchool.setAddress(addressArray[0]);
				beanSchool.setSubdist(addressArray[1]);
				beanSchool.setDistrict(addressArray[2]);
				beanSchool.setCity(addressArray[3]);
				beanSchool.setState(addressArray[4]);
				beanSchool.setZipCode(addressArray[5]);
				
				beanSchool.setResponse(true);
			}
			else
			{
				beanSchool.setResponse(false);
			}
		}
		else
		{
			beanSchool.setResponse(false);			
		}

		cursor.close();
		db.close();

		return beanSchool;
	}
			
	public BeanClassIdsNameArray getClassIdsAndNameArrays(String schoolId) {
		
		BeanClassIdsNameArray beanClassIdsNameArray = new BeanClassIdsNameArray();
		
		ArrayList<String> classIds = new ArrayList<String>();
	ArrayList<String> nameNames = new ArrayList<String>();
			SQLiteDatabase db = this.getReadableDatabase();
 // String GrtSchoolId=
			Cursor cursor = db.query(TABLE_CLASS, new String[] { CLASS_ID, ADVISORY_NAME, CLASS_STANDARD, CLASS_SECTION},
					SCHOOL_PKEY + "=?",
					new String[] { String.valueOf(schoolId) }, null, null, null, null);
			if (cursor != null)
			{
				while(cursor.moveToNext())
				{
					classIds.add(cursor.getString(0));
					nameNames.add(cursor.getString(2)+" "+cursor.getString(3));						

				}
			}

			cursor.close();
			db.close();

			String[] classIdArr = new String[classIds.size()];
			classIdArr = classIds.toArray(classIdArr);
			String[] advisoryNameArr = new String[nameNames.size()];
			advisoryNameArr = nameNames.toArray(advisoryNameArr);
			
			beanClassIdsNameArray.setClassIds(classIdArr);
			beanClassIdsNameArray.setClassNames(advisoryNameArr);
			
			return beanClassIdsNameArray;
		}

	// Getting class details   
		public ArrayList<BeanClassInfo> Get_classInfoDetails(String id) {
			
			ArrayList<BeanClassInfo> beanClassInfos = new ArrayList<BeanClassInfo>();
				SQLiteDatabase db = this.getReadableDatabase();
	 // String GrtSchoolId=
				Cursor cursor = db.query(TABLE_CLASS, new String[] { CLASS_ID,
						CLASS_STANDARD,CLASS_SECTION,SUBJECT_NAME,ADVISORY_NAME,CAPICITY,ROW,COLUMN}, SCHOOL_PKEY + "=?",
						new String[] { String.valueOf(id) }, null, null, null, null);
				if (cursor != null)
					while(cursor.moveToNext())
					{
						BeanClassInfo bean = new BeanClassInfo();
						bean.setClassStandard(cursor.getString(1));
						bean.setSection(cursor.getString(2));
						bean.setSubjects(cursor.getString(3));
						bean.setClassId(""+cursor.getInt(0));
						bean.setAdviserName(cursor.getString(4));
						bean.setCapacity(""+cursor.getInt(5));
						bean.setRow(""+cursor.getInt(6));
						bean.setColumn(""+cursor.getInt(7));
						
						beanClassInfos.add(bean);
					}

				cursor.close();
				db.close();

				return beanClassInfos;
			}

	// Getting single contact
	beanContact Get_Contact(int id) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID,
				KEY_NAME, KEY_PH_NO, KEY_EMAIL }, KEY_ID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null)
			cursor.moveToFirst();

		beanContact contact = new beanContact(Integer.parseInt(cursor.getString(0)),
				cursor.getString(1), cursor.getString(2), cursor.getString(3));
		// return contact
		cursor.close();
		db.close();

		return contact;
	}

	// Getting All Contacts
	public ArrayList<beanContact> Get_Contacts() {
		try {
			contact_list.clear();

			// Select All Query
			String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;

			SQLiteDatabase db = this.getWritableDatabase();
			Cursor cursor = db.rawQuery(selectQuery, null);

			// looping through all rows and adding to list
			if (cursor.moveToFirst()) {
				do {
					beanContact contact = new beanContact();
					contact.setID(Integer.parseInt(cursor.getString(0)));
					contact.setName(cursor.getString(1));
					contact.setPhoneNumber(cursor.getString(2));
					contact.setEmail(cursor.getString(3));
					// Adding contact to list
					contact_list.add(contact);
				} while (cursor.moveToNext());
			}

			// return contact list
			cursor.close();
			db.close();
			return contact_list;
		} catch (Exception e) {
			// TODO: handle exception
			Log.e("all_contact", "" + e);
		}

		return contact_list;
	}

	// Updating single contact
	public int Update_Contact(beanContact contact) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_NAME, contact.getName());
		values.put(KEY_PH_NO, contact.getPhoneNumber());
		values.put(KEY_EMAIL, contact.getEmail());

		// updating row
		return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
				new String[] { String.valueOf(contact.getID()) });
	}

	// Deleting single contact
	public void Delete_Contact(int id) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
				new String[] { String.valueOf(id) });
		db.close();
	}

	// Getting contacts Count
	public int Get_Total_Contacts() {
		String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(countQuery, null);
		cursor.close();

		// return count
		return cursor.getCount();
	}
	
	
	public int Add_Atendance_Type(BeanSettingAttendanceType beanSettingAttendanceType) {

		String seperater = context.getString(R.string.seperater);
		
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(SCHOOL_PKEY, beanSettingAttendanceType.getSchoolPkey()); 
		values.put(ATTENDANCE_TYPE_NAME, beanSettingAttendanceType.getAttendance_name()); 
		values.put(ATTENDANCE_TYPE_COLOR, beanSettingAttendanceType.getAttendance_color()); 
		try
		{
		db.insertOrThrow(TABLE_ATTENDANCE_TYPE, null, values);
		return Get_Last_Inserted_RowId(ATTENDANCE_TYPE_ID, TABLE_ATTENDANCE_TYPE);
		}
		catch(SQLiteConstraintException e)
		{
			e.printStackTrace();
		}
		db.close();
		return 0;
	}
	
	
	// Getting All Contacts
	public ArrayList<BeanSettingAttendanceType> Get_AttendanceType(String mSCHOOL_PKEY) {
		ArrayList<BeanSettingAttendanceType> beanSettingAttendanceTypeList= new ArrayList<BeanSettingAttendanceType>();
		try {
			
			BeanSettingAttendanceType beanSettingAttendanceType = new BeanSettingAttendanceType();

			// Select All Query
			String selectQuery = "SELECT  * FROM " + TABLE_ATTENDANCE_TYPE+" where "+SCHOOL_PKEY +" = "+mSCHOOL_PKEY;

			SQLiteDatabase db = this.getWritableDatabase();
			Cursor cursor = db.rawQuery(selectQuery, null);

			// looping through all rows and adding to list
			if (cursor.moveToFirst()) {
				do {
					beanSettingAttendanceType = new BeanSettingAttendanceType();
					beanSettingAttendanceType.setAttendance_id(cursor.getInt(0));
					beanSettingAttendanceType.setSchoolPkey(cursor.getString(1));
					beanSettingAttendanceType.setAttendance_name(cursor.getString(2));
					beanSettingAttendanceType.setAttendance_color(cursor.getString(3));
					// Adding contact to list
					beanSettingAttendanceTypeList.add(beanSettingAttendanceType);
				} while (cursor.moveToNext());
			}

			// return contact list
			cursor.close();
			db.close();
			return beanSettingAttendanceTypeList;
		} catch (Exception e) {
			// TODO: handle exception
			Log.e("all_contact", "" + e);
		}

		return beanSettingAttendanceTypeList;
	}
	
	public boolean Delete_Attendance_Type(int mATTENDANCE_TYPE_ID, String mSCHOOL_PKEY) {
		SQLiteDatabase db = this.getWritableDatabase();
		int i =db.delete(TABLE_ATTENDANCE_TYPE, ATTENDANCE_TYPE_ID + " = ? AND "+ SCHOOL_PKEY + " = ?",
				new String[] { String.valueOf(mATTENDANCE_TYPE_ID), String.valueOf(mSCHOOL_PKEY) });
		db.close();
		if(i>0)
			return true;
		return false;
	}
	
	
	
	
	public int Add_Event(BeanEvent beanEvent) {

		String seperater = context.getString(R.string.seperater);
		
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(SCHOOL_PKEY, beanEvent.getSchoolPkey()); 
		values.put(EVNT_DATE, DateConverter.convertToDatabaseFormat(beanEvent.getEvent_date())); 
		values.put(EVENT_NAME, beanEvent.getEvent_name()); 
		values.put(EVENT_TYPE, beanEvent.getEvent_type()); 
		try
		{
		db.insertOrThrow(TABLE_EVENT, null, values);
		return Get_Last_Inserted_RowId(EVENT_ID, TABLE_EVENT);
		}
		catch(SQLiteConstraintException e)
		{
			e.printStackTrace();
		}
		db.close();
		return 0;
	}
	
	
	// Getting All Contacts
	public ArrayList<BeanEvent> Get_Event(String mSCHOOL_PKEY, String startDate, String endDate) {
		ArrayList<BeanEvent> beanEventList= new ArrayList<BeanEvent>();
		try {
			
			BeanEvent beanEvent = new BeanEvent();

			// Select All Query
			String selectQuery = "SELECT  * FROM " + TABLE_EVENT+" where "+SCHOOL_PKEY +" = "+mSCHOOL_PKEY
					+" AND "+EVNT_DATE+" BETWEEN '"+DateConverter.convertToDatabaseFormat(startDate)+"' AND '"+DateConverter.convertToDatabaseFormat(endDate)+"'";

			SQLiteDatabase db = this.getWritableDatabase();
			Cursor cursor = db.rawQuery(selectQuery, null);

			// looping through all rows and adding to list
			if (cursor.moveToFirst()) {
				do {
					beanEvent = new BeanEvent();
					beanEvent.setEvent_id(cursor.getInt(0));
					beanEvent.setSchoolPkey(cursor.getString(1));
					beanEvent.setEvent_name(cursor.getString(2));
					beanEvent.setEvent_date(DateConverter.convertToScreenFormat(cursor.getString(3)));
					beanEvent.setEvent_type(cursor.getString(4));
					// Adding contact to list
					beanEventList.add(beanEvent);
				} while (cursor.moveToNext());
			}

			// return contact list
			cursor.close();
			db.close();
			return beanEventList;
		} catch (Exception e) {
			// TODO: handle exception
			Log.e("all_contact", "" + e);
		}

		return beanEventList;
	}
	
	public boolean Delete_Event(int mEVENT_ID, String mSCHOOL_PKEY) {
		SQLiteDatabase db = this.getWritableDatabase();
		int i =db.delete(TABLE_EVENT, EVENT_ID + " = ? AND "+ SCHOOL_PKEY + " = ?",
				new String[] { String.valueOf(mEVENT_ID), String.valueOf(mSCHOOL_PKEY) });
		db.close();
		if(i>0)
			return true;
		return false;
	}
	
	
	
	public int Add_Holiday(BeanHoliday beanHoliday) {

		String seperater = context.getString(R.string.seperater);
		
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(SCHOOL_PKEY, beanHoliday.getSchoolPkey()); 
		values.put(H_NAME, beanHoliday.getHoliday_name()); 
		values.put(H_DATE, DateConverter.convertToDatabaseFormat(beanHoliday.getHoliday_date())); 
		try
		{
		db.insertOrThrow(TABLE_HOLYDAY, null, values);
		return Get_Last_Inserted_RowId(H_ID, TABLE_HOLYDAY);
		}
		catch(SQLiteConstraintException e)
		{
			e.printStackTrace();
		}
		db.close();
		return 0;
	}
	
	
	// Getting All Contacts
	public ArrayList<BeanHoliday> Get_Holiday(String mSCHOOL_PKEY, String startDate, String endDate) {
		ArrayList<BeanHoliday> beanHolidayList= new ArrayList<BeanHoliday>();
		try {
			
			BeanHoliday beanHoliday = new BeanHoliday();

			// Select All Query
			String selectQuery = "SELECT  * FROM " + TABLE_HOLYDAY+" where "+SCHOOL_PKEY +" = "+mSCHOOL_PKEY
					+" AND "+H_DATE+" BETWEEN '"+DateConverter.convertToDatabaseFormat(startDate)+"' AND '"+DateConverter.convertToDatabaseFormat(endDate)+"'";

			SQLiteDatabase db = this.getWritableDatabase();
			Cursor cursor = db.rawQuery(selectQuery, null);

			// looping through all rows and adding to list
			if (cursor.moveToFirst()) {
				do {
					beanHoliday = new BeanHoliday();
					beanHoliday.setHoliday_id(cursor.getInt(0));
					beanHoliday.setSchoolPkey(cursor.getString(1));
					beanHoliday.setHoliday_date(DateConverter.convertToScreenFormat(cursor.getString(2)));
					beanHoliday.setHoliday_name(cursor.getString(3));
					// Adding contact to list
					beanHolidayList.add(beanHoliday);
				} while (cursor.moveToNext());
			}

			// return contact list
			cursor.close();
			db.close();
			return beanHolidayList;
		} catch (Exception e) {
			// TODO: handle exception
			Log.e("all_contact", "" + e);
		}

		return beanHolidayList;
	}
	
	public boolean Delete_Holiday(int mH_ID, String mSCHOOL_PKEY) {
		SQLiteDatabase db = this.getWritableDatabase();
		int i =db.delete(TABLE_HOLYDAY, H_ID + " = ? AND "+ SCHOOL_PKEY + " = ?",
				new String[] { String.valueOf(mH_ID), String.valueOf(mSCHOOL_PKEY) });
		db.close();
		if(i>0)
			return true;
		return false;
	}
	

				public int Add_Grade_Category(BeanGradeCategory beanGradeCategory) {

					String seperater = context.getString(R.string.seperater);
					
					SQLiteDatabase db = this.getWritableDatabase();
					ContentValues values = new ContentValues();
					values.put(SCHOOL_PKEY, beanGradeCategory.getSchoolPkey()); 
					values.put(GRADE_CATEGORY_NAME, beanGradeCategory.getGrade_category_name()); 
					values.put(GRADE_CATEGORY_WEIGHTAGE, beanGradeCategory.getGrade_category_percentage()); 
					try
					{
					db.insertOrThrow(TABLE_GRADE_CATEGORY, null, values);
					return Get_Last_Inserted_RowId(GRADE_CATEGORY_ID, TABLE_GRADE_CATEGORY);
					}
					catch(SQLiteConstraintException e)
					{
						e.printStackTrace();
					}
					db.close();
					return 0;
				}
				
				
				// Getting All Contacts
				public ArrayList<BeanGradeCategory> Get_Grade_Category(String mSCHOOL_PKEY) {
					ArrayList<BeanGradeCategory> beanGradeCategoryList= new ArrayList<BeanGradeCategory>();
					try {
						
						BeanGradeCategory beanGradeCategory = new BeanGradeCategory();

						// Select All Query
						String selectQuery = "SELECT  * FROM " + TABLE_GRADE_CATEGORY+" where "+SCHOOL_PKEY +" = "+mSCHOOL_PKEY;

						SQLiteDatabase db = this.getWritableDatabase();
						Cursor cursor = db.rawQuery(selectQuery, null);

						// looping through all rows and adding to list
						if (cursor.moveToFirst()) {
							do {
								beanGradeCategory = new BeanGradeCategory();
								beanGradeCategory.setGrade_category_id(cursor.getInt(0));
								beanGradeCategory.setSchoolPkey(cursor.getString(1));
								beanGradeCategory.setGrade_category_name(cursor.getString(2));
								beanGradeCategory.setGrade_category_percentage(cursor.getFloat(3));
								// Adding contact to list
								beanGradeCategoryList.add(beanGradeCategory);
							} while (cursor.moveToNext());
						}

						// return contact list
						cursor.close();
						db.close();
						return beanGradeCategoryList;
					} catch (Exception e) {
						// TODO: handle exception
						Log.e("all_contact", "" + e);
					}

					return beanGradeCategoryList;
				}
				
				public boolean Delete_Grade_Category(int mGRADE_CATEGORY_ID, String mSCHOOL_PKEY) {
					SQLiteDatabase db = this.getWritableDatabase();
					int i =db.delete(TABLE_GRADE_CATEGORY, GRADE_CATEGORY_ID + " = ? AND "+ SCHOOL_PKEY + " = ?",
							new String[] { String.valueOf(mGRADE_CATEGORY_ID), String.valueOf(mSCHOOL_PKEY) });
					db.close();
					if(i>0)
						return true;
					return false;
				}
				
				
				public int Get_Last_Inserted_RowId(String columnName, String tableName) {
					int lastId = 0;
					try  {
				String selectQuery = "SELECT "+columnName+" from "+tableName+" order by "+columnName+" DESC limit 1";
				SQLiteDatabase db = this.getWritableDatabase();
				Cursor cursor = db.rawQuery(selectQuery, null);
				if (cursor != null && cursor.moveToFirst()) {
				    lastId = cursor.getInt(0); //The 0 is the column index, we only have 1 column, so the index is 0
				}
				cursor.close();
				db.close();
					}
					catch(Exception e) {
						
					}
					
				return lastId;
				}
				
				
				
				/////////////////  Pragati

				// Getting All Contacts
				public ArrayList<BeanSettingAttendanceType> Get_AttendanceType() {
					ArrayList<BeanSettingAttendanceType> beanSettingAttendanceTypeList = new ArrayList<BeanSettingAttendanceType>();
					try {

						BeanSettingAttendanceType beanSettingAttendanceType = new BeanSettingAttendanceType();

						// Select All Query
						String selectQuery = "SELECT  * FROM " + TABLE_ATTENDANCE_TYPE;

						SQLiteDatabase db = this.getWritableDatabase();
						Cursor cursor = db.rawQuery(selectQuery, null);

						// looping through all rows and adding to list
						if (cursor.moveToFirst()) {
							do {
								beanSettingAttendanceType = new BeanSettingAttendanceType();
								beanSettingAttendanceType
										.setAttendance_id(cursor.getInt(0));
								beanSettingAttendanceType.setAttendance_name(cursor
										.getString(1));
								beanSettingAttendanceType.setAttendance_color(cursor
										.getString(2));
								// Adding contact to list
								beanSettingAttendanceTypeList
										.add(beanSettingAttendanceType);
							} while (cursor.moveToNext());
						}

						// return contact list
						cursor.close();
						db.close();
						return beanSettingAttendanceTypeList;
					} catch (Exception e) {
						// TODO: handle exception
						Log.e("all_contact", "" + e);
					}

					return beanSettingAttendanceTypeList;
				}

				
				// Getting All config priod details
				 
				public ArrayList<BeanConfigPriodSetting> Get_ConfigPriod(String SchoolPkey, String startDate, String endDate) {
					ArrayList<BeanConfigPriodSetting> beanConfigPriidList = new ArrayList<BeanConfigPriodSetting>();
					try {

						BeanConfigPriodSetting beanpriod_configSetting = new BeanConfigPriodSetting();

						// Select All Query
						/*String selectQuery = "SELECT  * FROM " + TABLE_PRIOD_CONFIG+" where "+SCHOOL_PKEY+"="+SchoolPkey
								+" AND "+" "+" BETWEEN '"+DateConverter.convertToDatabaseFormat(startDate)+"' AND '"+DateConverter.convertToDatabaseFormat(endDate)+"'";
*/
						String selectQuery = "SELECT  * FROM " + TABLE_PRIOD_CONFIG+" where "+SCHOOL_PKEY+"="+SchoolPkey;
						SQLiteDatabase db = this.getWritableDatabase();
						Cursor cursor = db.rawQuery(selectQuery, null);

						// looping through all rows and adding to list
						if (cursor.moveToFirst()) {
							do {
								beanpriod_configSetting = new BeanConfigPriodSetting();

								beanpriod_configSetting.setPeriods(cursor.getString(1));
								beanpriod_configSetting.setSubject(cursor.getString(4));
								beanpriod_configSetting.setReccuranceDays(cursor
										.getString(6));
								beanpriod_configSetting.setSchool_id(cursor.getString(0));
			                     beanpriod_configSetting.setSCHOOL_PKEY(cursor.getString(2));
								// Adding priod to list
								beanConfigPriidList.add(beanpriod_configSetting);
							} while (cursor.moveToNext());
						}

						// return contact list
						cursor.close();
						db.close();
						return beanConfigPriidList;
					} catch (Exception e) {
						// TODO: handle exception
						Log.e("all_contact", "" + e);
					}

					return beanConfigPriidList;
				}

				public Boolean Delete_ConfigPriod(String school_id) {
					SQLiteDatabase db = this.getWritableDatabase();
					int i = db.delete(TABLE_PRIOD_CONFIG, PRIOD_CONF_SID + " = ?",
							new String[] { String.valueOf(school_id) });
					db.close();
					if (i > 0)
						return true;
					return false;
				}

				// Adding new Teacher profile
				public void Add_TeacherProfile(BeanTeacher beanteacher) {
					SQLiteDatabase db = this.getWritableDatabase();
					ContentValues values = new ContentValues();
					values.put(SCHOOL_PKEY, beanteacher.get_teacher_school_id());
					values.put(TEACHER_ID, beanteacher.get_teacher_id());
					values.put(TEACHER_ADD_CITY, beanteacher.get_teacher_add_city());
					values.put(TEACHER_ADD_DISTRICT, beanteacher.get_teacher_add_district());
					values.put(TEACHER_ADD_PREVIENCE,
							beanteacher.get_teacher_add_previence());
					values.put(TEACHER_ADD_SUBDTT_VILL,
							beanteacher.get_teacher_add_subDtt_vill());
					values.put(TEACHER_ADDRESS, beanteacher.get_teacher_address());
					values.put(TEACHER_DATE_OF_JOINING,
							DateConverter.convertToDatabaseFormat(beanteacher.get_teacher_Date_of_joining()));
					values.put(TEACHER_DOB, DateConverter.convertToDatabaseFormat(beanteacher.get_teacher_dob()));
					values.put(TEACHER_EMAIL_ID, beanteacher.get_teacher_email_id());
					values.put(TEACHER_EMP_STATUS, beanteacher.get_teacher_emp_status());
					values.put(TEACHER_F_NAME, beanteacher.get_teacher_f_name());
					values.put(TEACHER_L_NAME, beanteacher.get_teacher_l_name());
					values.put(TEACHER_MOB_NO, beanteacher.get_teacher_mobile_number());
					values.put(TEACHER_RELIGION, beanteacher.get_teacher_religion());
					values.put(TEACHER_SEX, beanteacher.get_teacher_sex());
					values.put(TEACHER_STATUS_DEG, beanteacher.get_teacher_status_deg());
					// values.put(TEACHER_ID, beanteacher.get_teacher_school_id());
					// values.put(TEACHER_ID, beanteacher.get_teacher_profile_img_path());

					// Inserting Row
					db.insertOrThrow(TABLE_TEACHER, null, values);
					db.close(); // Closing database connection
				}

				// Updating single contact

				public int Update_Teacher_profile(BeanTeacher beanteacher) {
					SQLiteDatabase db = this.getWritableDatabase();

					ContentValues values = new ContentValues();
					values.put(TEACHER_ID, beanteacher.get_teacher_id());
					values.put(SCHOOL_PKEY, beanteacher.get_teacher_school_id());
					values.put(TEACHER_ID, beanteacher.get_teacher_id());
					values.put(TEACHER_ADD_CITY, beanteacher.get_teacher_add_city());
					values.put(TEACHER_ADD_DISTRICT, beanteacher.get_teacher_add_district());
					values.put(TEACHER_ADD_PREVIENCE,
							beanteacher.get_teacher_add_previence());
					values.put(TEACHER_ADD_SUBDTT_VILL,
							beanteacher.get_teacher_add_subDtt_vill());
					values.put(TEACHER_ADDRESS, beanteacher.get_teacher_address());
					values.put(TEACHER_DATE_OF_JOINING,
							DateConverter.convertToDatabaseFormat(beanteacher.get_teacher_Date_of_joining()));
					values.put(TEACHER_DOB, DateConverter.convertToDatabaseFormat(beanteacher.get_teacher_dob()));
					values.put(TEACHER_EMAIL_ID, beanteacher.get_teacher_email_id());
					values.put(TEACHER_EMP_STATUS, beanteacher.get_teacher_emp_status());
					values.put(TEACHER_F_NAME, beanteacher.get_teacher_f_name());
					values.put(TEACHER_L_NAME, beanteacher.get_teacher_l_name());
					values.put(TEACHER_MOB_NO, beanteacher.get_teacher_mobile_number());
					values.put(TEACHER_RELIGION, beanteacher.get_teacher_religion());
					values.put(TEACHER_SEX, beanteacher.get_teacher_sex());
					values.put(TEACHER_STATUS_DEG, beanteacher.get_teacher_status_deg());
					values.put(TEACHER_SCHOOL_ID, beanteacher.get_teacher_school_id());
					values.put(TEACHER_IMG_PATH, beanteacher.get_teacher_profile_img_path());

					// updating row
					return db.update(TABLE_TEACHER, values, null, null);
					/*
					 * return db.update(TABLE_TEACHER, values, TEACHER_ID + " = ?", new
					 * String[] { String.valueOf(beanteacher.get_teacher_id()) });
					 */
				}

				// add priod to db
				public void AddOrUpdatePriodSetting(BeanPriodSetting beanpriodSetting) {

					SQLiteDatabase db = this.getWritableDatabase();
					ContentValues values = new ContentValues();

					values.put(PRIOD_START_TIME, beanpriodSetting.getStart_time());
					values.put(PRIOD_END_TIME, beanpriodSetting.getEnd_time());
			        values.put(SCHOOL_PKEY,beanpriodSetting.get_SCHOOL_PKEY());
					// Inserting Row
			          
			        if(beanpriodSetting.get_SCHOOL_PKEY()!=null & beanpriodSetting.get_SCHOOL_PKEY()==beanpriodSetting.get_SCHOOL_PKEY()){
			        	db.update(TABLE_PRIOD, values, SCHOOL_PKEY + " = ?",
			    				new String[] { String.valueOf(beanpriodSetting.get_SCHOOL_PKEY()) });      	
			            ToastCustom.makeText(context, "Priod Updated", Toast.LENGTH_SHORT);
			        }
			        else{
					db.insert(TABLE_PRIOD, null, values);
					 ToastCustom.makeText(context, "Priod Saved", Toast.LENGTH_SHORT);
			        }
			        db.insert(TABLE_PRIOD, null, values);
			        db.update(TABLE_PRIOD, values, SCHOOL_PKEY + " = ?",
							new String[] { String.valueOf(beanpriodSetting.get_SCHOOL_PKEY()) });
					db.close();
				}

				// //////////////////////////////////// Insert into priod config table

				public void Add_Priod_Config_Setting(
						BeanConfigPriodSetting beanpriodconfigSetting) {

					SQLiteDatabase db = this.getWritableDatabase();
					ContentValues values = new ContentValues();
					values.put(PRIOD_CONF_SID, beanpriodconfigSetting.getSchool_id());
					values.put(PRIODS, beanpriodconfigSetting.getPeriods());
					values.put(PRI_CLASS, beanpriodconfigSetting.getPri_class());
					values.put(PRI_SUBJECT, beanpriodconfigSetting.getSubject());
					values.put(PRI_RECURENCE, beanpriodconfigSetting.getReccurance());
					values.put(RECURENCE_DAY, beanpriodconfigSetting.getReccuranceDays());
			        values.put(SCHOOL_PKEY, beanpriodconfigSetting.getSCHOOL_PKEY());
					// Inserting Row
					try {
						db.insertOrThrow(TABLE_PRIOD_CONFIG, null, values);

					} catch (Exception e) {
						// TODO: handle exception
						Log.d("query", e.toString());

					}

					db.close();
				}
				
				// Adding new assignment
				public void Add_Assignment_details(
						BeanAssignmentDetails beanassignmentDetails) {
					SQLiteDatabase db = this.getWritableDatabase();
					ContentValues values = new ContentValues();

					values.put(ASSIGNMENT_CLASS,
							beanassignmentDetails.get_ASSIGNMENT_CLASS());
					values.put(ASSIGNMENT_DATE,DateConverter.convertToDatabaseFormat( beanassignmentDetails.get_ASSIGNMENT_DATE()));
					values.put(ASSIGNMENT_EXAM_ID,
							beanassignmentDetails.get_ASSIGNMENT_EXAM_ID());
					values.put(ASSIGNMENT_EXAM_TYPE,
							beanassignmentDetails.get_ASSIGNMENT_EXAM_TYPE());
					values.put(ASSIGNMENT_GRADE_CATEGORY,
							beanassignmentDetails.get_ASSIGNMENT_GRADE_CATEGORY());
					values.put(ASSIGNMENT_GRADE_TYPE,
							beanassignmentDetails.get_ASSIGNMENT_GRADE_TYPE());
					values.put(ASSIGNMENT_MIN_POINT_KKH,
							beanassignmentDetails.get_ASSIGNMENT_MIN_POINT_KKH());
					values.put(ASSIGNMENT_PRIOD,
							beanassignmentDetails.get_ASSIGNMENT_PRIOD());
					values.put(ASSIGNMENT_SCHOOL_ID,
							beanassignmentDetails.get_ASSIGNMENT_SCHOOL_ID());
					values.put(ASSIGNMENT_SUBJECT,
							beanassignmentDetails.get_ASSIGNMENT_SUBJECT());
					values.put(ASSIGNMENT_WEIGHT,	beanassignmentDetails.get_ASSIGNMENT_WEIGHT());
					values.put(SCHOOL_PKEY, beanassignmentDetails.get_SCHOOL_PKEY());
				

					// Inserting Row
					db.insertOrThrow(TABLE_ASSIGNMENT_DETAILS, null, values);
					db.close(); // Closing database connection
				}

				public BeanTeacher Get_TeachetProfileDetails() {
					BeanTeacher beaneacherprofile = new BeanTeacher();
					beaneacherprofile.setResponse(false);
					// ArrayList<BeanClassInfo> beanClassInfos = new
					// ArrayList<BeanClassInfo>();
					SQLiteDatabase db = this.getReadableDatabase();
					// String GrtSchoolId=

					Cursor cursor = db.query(TABLE_TEACHER, new String[] { TEACHER_ID,
							TEACHER_F_NAME, TEACHER_L_NAME, TEACHER_SEX, TEACHER_DOB,
							TEACHER_DATE_OF_JOINING, TEACHER_EMP_STATUS, TEACHER_ADDRESS,
							TEACHER_STATUS_DEG, TEACHER_RELIGION, TEACHER_EMAIL_ID,
							TEACHER_MOB_NO }, null, null, null, null, null, null);
					if (cursor != null) {
						while (cursor.moveToNext()) {

							beaneacherprofile.set_teacher_Date_of_joining(DateConverter.convertToScreenFormat( cursor
									.getString(5)));
							beaneacherprofile.set_teacher_dob(DateConverter.convertToScreenFormat( cursor.getString(4)));
							beaneacherprofile.set_teacher_email_id(cursor.getString(10));
							beaneacherprofile.set_teacher_emp_status(cursor.getString(6));
							beaneacherprofile.set_teacher_f_name(cursor.getString(1));
							beaneacherprofile.set_teacher_l_name(cursor.getString(2));
							beaneacherprofile.set_teacher_id(cursor.getString(0));
							beaneacherprofile.set_teacher_address(cursor.getString(7));
							beaneacherprofile.set_teacher_status_deg(cursor.getString(8));
							beaneacherprofile.set_teacher_sex(cursor.getString(3));
							beaneacherprofile.set_teacher_religion(cursor.getString(9));
							beaneacherprofile.set_teacher_mobile_number(cursor
									.getString(11));
							beaneacherprofile.setResponse(true);
						}
					}

					cursor.close();
					db.close();

					return beaneacherprofile;
				}

				public int TeacherRecCount() {
					SQLiteDatabase db = this.getReadableDatabase();
					int numRows = (int) DatabaseUtils.queryNumEntries(db, TABLE_TEACHER);

					return numRows;

				}
				
				// SHOW PRIOD DETAILS ACORDING TO SCHOOL TAB
				 
					public ArrayList<BeanPriodSetting> GetPriods(String SchoolPkey ) {
						ArrayList<BeanPriodSetting> beanPriods = new ArrayList<BeanPriodSetting>();
						try {

							BeanPriodSetting beanpriodsetting = new BeanPriodSetting();

							// Select All Query
							String selectQuery = "SELECT "+PRIOD_START_TIME+","+PRIOD_END_TIME+" FROM " + TABLE_PRIOD+" where "+SCHOOL_PKEY+"="+SchoolPkey  ;

							SQLiteDatabase db = this.getWritableDatabase();
							Cursor cursor = db.rawQuery(selectQuery, null);

							// looping through all rows and adding to list
							if (cursor.moveToFirst()) {
								do {
									beanpriodsetting = new BeanPriodSetting();

									beanpriodsetting.setStart_time(cursor.getString(0));
									beanpriodsetting.setEnd_time(cursor.getString(1));
									 
									// Adding priod to list
				                     beanPriods.add(beanpriodsetting);
								} while (cursor.moveToNext());
							}

							// return contact list
							cursor.close();
							db.close();
							return beanPriods;
						} catch (Exception e) {
							// TODO: handle exception
							Log.e("all_contact", "" + e);
						}

						return beanPriods;
					}
				
				
				
				
}
