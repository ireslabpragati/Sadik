package com.sadik.interfaces;

import java.util.ArrayList;

import com.sadik.model.BeanClassInfo;
import com.sadik.model.BeanDateValidation;
import com.sadik.model.BeanPriodSetting;
import com.sadik.model.BeanSchool;
import com.sadik.model.BeanStudent;

public interface FragmentInterFace {

	public void startConfigureSchool1Fragment(boolean addmore);
	public void startConfigureSchool2Fragment(BeanSchool beanSchool, ArrayList<BeanClassInfo> beanClassInfos);
	public void startAddHolidaysFragment(BeanDateValidation bean);
	public void startAddEventsFragment(BeanDateValidation bean);
	public void startPeriodFragment();
	public void startConfPeriodFragment(BeanDateValidation bean);
	public void startFragmentSettingGradeType();
	public void startFragmentSettingGradeCategory();
	public void startFragmentSettingAttendance();
	public void startFragmentSettingTeacherProfile();
	public void startFragmentSettingStudentProfile(String StudentPKey, String SchoolPkey);
	public void startFragmentSettingStudentList();
	public void startFragmentSettingGaurdianProfile(BeanStudent beanStudent);
	public void startPeriodSettingFragment(BeanPriodSetting bean);
	public void startFragmentSettingAssignmentDetails(   );
	public void startSeatAllocationFragment();
	
}
