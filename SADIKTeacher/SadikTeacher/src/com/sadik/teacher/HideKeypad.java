package com.sadik.teacher;

import android.app.Activity;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;

public class HideKeypad {

	public static void hideSoftKeyboard(Activity activity) {
		
		
	    InputMethodManager inputMethodManager = (InputMethodManager)  activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
	    if(activity.getCurrentFocus()!=null)
	    {
		    inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
	    }
	    
	}
}
