package com.sadik.teacher;

import java.util.ArrayList;
import java.util.Collections;

import com.sadik.interfaces.RemoveViewInterface;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Alerts {

	
	   public static void alertRecurrance(Context context , final TextView textView, String title) {
			
			AlertDialog dialog; 
			
			String textString = textView.getText().toString();
			
			final CharSequence[] items = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
			final boolean[] checked = new boolean[7]; //{true, false, true, true, false, true, false};
		       // arraylist to keep the selected items
		       final ArrayList seletedItems=new ArrayList();
		     
			
			for (int i = 0; i < items.length; i++) {
				
				if(textString.contains(items[i]) || textString.equals(""))
				{
				seletedItems.add(i);
				checked[i] = true;
				}
				else
				{
					checked[i] = false;					
				}
			}
			
	       AlertDialog.Builder builder = new AlertDialog.Builder(context);
	       builder.setTitle(title);
	       builder.setMultiChoiceItems(items, checked,
	               new DialogInterface.OnMultiChoiceClickListener() {
	        @Override
	        public void onClick(DialogInterface dialog, int indexSelected,
	                boolean isChecked) {
	            if (isChecked) {
	                // If the user checked the item, add it to the selected items
	           	 seletedItems.add(indexSelected);
	                
	                
	            } else if (seletedItems.contains(indexSelected)) {
	                // Else, if the item is already in the array, remove it
	                seletedItems.remove(Integer.valueOf(indexSelected));
	            }
	        }
	    })
	     // Set the action buttons
	    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
	        @Override
	        public void onClick(DialogInterface dialog, int id) {
	        	String dayList = "";
	            Collections.sort(seletedItems);
	            for(int i=0;i<seletedItems.size();i++)
	            {
	            	if(i==0)
	            	{
	            		dayList = items[(Integer) seletedItems.get(i)].toString();
	            	}
	            	else
	            	{
	            	dayList += ", "+items[(Integer) seletedItems.get(i)].toString();
	            	}
	            }
	            textView.setText(dayList);
	        }
	    })
	    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
	        @Override
	        public void onClick(DialogInterface dialog, int id) {
	           //  Your code when user clicked on Cancel
	         
	        }
	    });

	       dialog = builder.create();//AlertDialog dialog; create like this outside onClick
	       dialog.show();
	}

	   
	   public static void removeView(Context context, final View view, final LinearLayout linearLayout) {
			
			AlertDialog dialog; 
			
			
	       AlertDialog.Builder builder = new AlertDialog.Builder(context);
	       builder.setTitle(context.getResources().getString(R.string.Do_you_really_want_to_remove_the_item));
	       builder.
	     // Set the action buttons
	    setPositiveButton("OK", new DialogInterface.OnClickListener() {
	        @Override
	        public void onClick(DialogInterface dialog, int id) {
	        	linearLayout.removeView(view);
	        }
	    })
	    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
	        @Override
	        public void onClick(DialogInterface dialog, int id) {
	        	
	        }
	    });

	       dialog = builder.create();//AlertDialog dialog; create like this outside onClick
	       dialog.show();
	}

	   public static void removeView(Context context, final View view, final LinearLayout linearLayout, final RemoveViewInterface interface1) {
			
			AlertDialog dialog; 
			
			
	       AlertDialog.Builder builder = new AlertDialog.Builder(context);
	       builder.setTitle(context.getResources().getString(R.string.Do_you_really_want_to_remove_the_item));
	       builder.
	     // Set the action buttons
	    setPositiveButton("OK", new DialogInterface.OnClickListener() {
	        @Override
	        public void onClick(DialogInterface dialog, int id) {
	        	boolean result = interface1.remove();
	        	if(result)
	        	{
		        	linearLayout.removeView(view);
	        	}
	        }
	    })
	    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
	        @Override
	        public void onClick(DialogInterface dialog, int id) {
	        	
	        }
	    });

	       dialog = builder.create();//AlertDialog dialog; create like this outside onClick
	       dialog.show();
	}

	   
	   
}
