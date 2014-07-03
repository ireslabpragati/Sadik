package com.sadik.teacher;

import android.app.Activity;
import android.content.Context;

public class ToastUD {

	
	// Under Development Text
	public static void show(Activity activity)
	{
		ToastCustom.makeText(activity, activity.getString(R.string.Under_Development), 0);
	}
	public static void show(Context activity)
	{
		ToastCustom.makeText(activity, activity.getString(R.string.Under_Development), 0);
	}
	
}
