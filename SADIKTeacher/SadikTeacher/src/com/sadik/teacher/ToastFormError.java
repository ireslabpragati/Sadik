package com.sadik.teacher;

import android.app.Activity;
import android.content.Context;

public class ToastFormError {

	
	// Under Development Text
	public static void show(Activity activity)
	{
		//ToastCustom.makeText(activity, activity.getString(R.string.FORM_CONTAINS_ERROR), 0);
	}
	public static void show(Context activity)
	{
		//ToastCustom.makeText(activity, activity.getString(R.string.FORM_CONTAINS_ERROR), 0);
	}
	
	public static void schoolNotconfigured(Activity activity)
	{
		ToastCustom.makeText(activity, "School is not configured yet, Please configure a school first.", 0);
	}
	public static void schoolNotconfigured(Context activity)
	{
		ToastCustom.makeText(activity, "School is not configured yet, Please configure a school first.", 0);
	}
}
