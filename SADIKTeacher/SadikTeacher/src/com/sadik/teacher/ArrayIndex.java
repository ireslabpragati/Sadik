package com.sadik.teacher;

import android.util.Log;
import android.widget.Spinner;


public class ArrayIndex {
	
public int setSpinnerItem(Spinner spinner, String str) {
		
int a = 0;
try{
	
	while(true) {
		spinner.getItemAtPosition(a);
		a++;
		
	}
}
catch(Exception e) {
	
}
String[] array = new String[a];
for(int i=0; i<a; i++){
	array[i] = spinner.getItemAtPosition(i)+"";
}

for (int i = 0; i < array.length; i++) {
        if (array[i].equalsIgnoreCase(str)) {
        	spinner.setSelection(i);
        	Log.e("spinnervalue "+str, i+" : "+spinner.getSelectedItemPosition());
                return (i);
        }
}
return (-1);
}

public static int getItemIndex(String []strArray, String str) {
	
int a = 0;
for(int i=0; i<strArray.length;i++) {
    if (strArray[i].equalsIgnoreCase(str)) {
    	return i;
    }
}
return -1;

}



}
