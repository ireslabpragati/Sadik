package com.sadik.validation;

import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import java.util.regex.Pattern;

import com.sadik.teacher.DateDifference;

public class Validation {

    // Regular Expression
    // you can change the expression based on your need
    private static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String PHONE_REGEX = "\\d{10}";
    private static final String TIME24HOURS_REGEX = "([01]?[0-9]|2[0-3]):[0-5][0-9]";
    private static final String DATE_REGEX = "(0?[1-9]|1[012])/(0?[1-9]|[12][0-9]|3[01])/((19|20)\\d\\d)";
    private static final String VISA_REGEX = "^4[0-9]{12}(?:[0-9]{3})?$";
    private static final String MASTERCARD_REGEX = "^5[1-5][0-9]{14}$";
    private static final String AMERICANEXPRESS_REGEX = "^3[47][0-9]{13}$";
    private static final String DISCOVER_REGEX = "^6(?:011|5[0-9]{2})[0-9]{12}$";
    private static final String EXPIRYDATE_REGEX = "(?:0[1-9]|1[0-2])[0-9]{2}";
    private static final String URL_REGEX = "^(http:\\/\\/|https:\\/\\/|ftp:\\/\\/|www.)?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?(([0-9]{1,3}\\.){3}[0-9]{1,3}|([0-9a-z_!~*'()-]+\\.)*([0-9a-z][0-9a-z-]{0,61})?[0-9a-z][a-z]{1,6})(:[0-9]{1,4})?((/?)|(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$";

    // Error Messages
    private static final String REQUIRED_MSG = "Required";
    private static final String EMAIL_MSG = "Invalid email";
    private static final String URL_MSG = "Invalid url";
    private static final String PHONE_MSG = "10 Digits";
    private static final String PASS_MATCH = "Password Doesn't Match";
    private static final String USER_AUTHENTICATE = "Username or Password is incorrect.........";
    private static final String TIME_MSG = "HH:MM (24 HOURS)";
    private static final String VISA_MSG = "Invalid VISA";
    private static final String MASTERCARD_MSG = "Invalid MASTERCARD";
    private static final String AMERICANEXPRESS_MSG = "Invalid AMERICANEXPRESS";
    private static final String DISCOVER_MSG = "Invalid DISCOVER";
    private static final String EXPIRYDATE_MSG = "MMYY";
    private static final String DATE_MSG = "DD/MM/YYYY";
    private static final String END_DATE_SMALLER = "End Date Small";

    
    // call this method when you need to check Card validation
    public static boolean isValidCard(String cardType, TextView textView, boolean required) {
    	boolean flag = false;
    	if (cardType.equals("Master Card"))
    		flag = isValid(textView, MASTERCARD_REGEX, MASTERCARD_MSG, required);
    	else if (cardType.equals("Visa"))
    		flag = isValid(textView, VISA_REGEX, VISA_MSG, required);
    	else if (cardType.equals("American Express Card"))
    		flag = isValid(textView, AMERICANEXPRESS_REGEX, AMERICANEXPRESS_MSG, required);
    	else if (cardType.equals("Discover"))
    		flag = isValid(textView, DISCOVER_REGEX, DISCOVER_MSG, required);
    	return flag;
    }

    
    // call this method when you need to check email validation
    public static boolean isExpiryDate(TextView textView, boolean required) {
        return isValid(textView, EXPIRYDATE_REGEX, EXPIRYDATE_MSG, required);
    }

    // call this method when you need to check email validation
    public static boolean isUrlAddress(TextView textView, boolean required) {
        return isValid(textView, URL_REGEX, URL_MSG, required);
    }


    // call this method when you need to check email validation
    public static boolean isEmailAddress(TextView textView, boolean required) {
        return isValid(textView, EMAIL_REGEX, EMAIL_MSG, required);
    }

    // call this method when you need to check phone number validation
    public static boolean isPhoneNumber(TextView textView, boolean required) {
        return isValid(textView, PHONE_REGEX, PHONE_MSG, required);
    }

    // call this method when you need to check time format validation
    public static boolean isTimeFormat(TextView textView, boolean required) {
        return isValid(textView, TIME24HOURS_REGEX, TIME_MSG, required);
    }

    // call this method when you need to check time format validation
    public static boolean isDateFormat(TextView textView, boolean required) {
        return isValid(textView, DATE_REGEX, DATE_MSG, required);
    }

    // call this method when you need to check time format validation
    public static boolean isDateGreater(TextView textView1, TextView textView2, boolean required) {
        if(hasText(textView2)) {
        	if(!DateDifference.isGreater(textView1, textView2)) {
          	   int ecolor = Color.RED; // whatever color you want
           	    ForegroundColorSpan fgcspan = new ForegroundColorSpan(ecolor);
           	    SpannableStringBuilder ssbuilder = new SpannableStringBuilder(END_DATE_SMALLER);
           	    ssbuilder.setSpan(fgcspan, 0, END_DATE_SMALLER.length(), 0);

                	textView2.setError(ssbuilder);
                	
                	return false;

        	}
        	return true;
        }
        return false;
    }
    
    
    // call this method when you need to check time format validation
    public static boolean isDateLesser(TextView textView1, TextView textView2, boolean required) {
        if(isValid(textView2, DATE_REGEX, DATE_MSG, required)) {
        	if(!DateDifference.isGreater(textView1, textView2)) {
          	   int ecolor = Color.RED; // whatever color you want
           	    ForegroundColorSpan fgcspan = new ForegroundColorSpan(ecolor);
           	    SpannableStringBuilder ssbuilder = new SpannableStringBuilder(END_DATE_SMALLER);
           	    ssbuilder.setSpan(fgcspan, 0, END_DATE_SMALLER.length(), 0);

                	textView1.setError(ssbuilder);

        	}
        	return true;
        }
        return false;
    }

    
    // return true if the input field is valid, based on the parameter passed
    public static boolean isValid(TextView textView, String regex, String errMsg, boolean required) {

        String text = textView.getText().toString().trim();
        // clearing the error, if it was previously set by some other values
        textView.setError(null);

        // text required and textView is blank, so return false
        if ( required && !hasText(textView) ) return false;

        // pattern doesn't match so returning false
        if (required && !Pattern.matches(regex, text)) {
     	   int ecolor = Color.RED; // whatever color you want
   	    ForegroundColorSpan fgcspan = new ForegroundColorSpan(ecolor);
   	    SpannableStringBuilder ssbuilder = new SpannableStringBuilder(errMsg);
   	    ssbuilder.setSpan(fgcspan, 0, errMsg.length(), 0);

        	textView.setError(ssbuilder);
            return false;
        };

        return true;
    }

    // check the input field has any text or not
    // return true if it contains text otherwise false
    public static boolean isPasswordMatch(TextView textView1, TextView textView2) {
        String text1 = textView1.getText().toString().trim();
        String text2 = textView2.getText().toString().trim();
 
        // length 0 means there is no text
        if (!text1.equals(text2)) {

      	   int ecolor = Color.RED; // whatever color you want
      	    ForegroundColorSpan fgcspan = new ForegroundColorSpan(ecolor);
      	    SpannableStringBuilder ssbuilder = new SpannableStringBuilder(PASS_MATCH);
      	    ssbuilder.setSpan(fgcspan, 0, PASS_MATCH.length(), 0);
        	
        	textView1.setError(ssbuilder);
            return false;
        }

        return true;
    }
    public static boolean isUserAuthenticate(TextView textView1, TextView textView2, String str1, String str2) {
        String text1 = textView1.getText().toString().trim();
        String text2 = textView2.getText().toString().trim();

        // length 0 means there is no text
        if (!text1.equals(str1) || !text2.equals(str2)) {
       	   int ecolor = Color.RED; // whatever color you want
     	    ForegroundColorSpan fgcspan = new ForegroundColorSpan(ecolor);
     	    SpannableStringBuilder ssbuilder = new SpannableStringBuilder(USER_AUTHENTICATE);
     	    ssbuilder.setSpan(fgcspan, 0, USER_AUTHENTICATE.length(), 0);
            textView1.setError(ssbuilder);
            return false;
        }

        return true;
    }
    public static boolean hasText(TextView textView) {

        String text = textView.getText().toString().trim();
        textView.setError(null);

        // length 0 means there is no text
        if (text.length() == 0) {
        	
        	   int ecolor = Color.RED; // whatever color you want
        	    ForegroundColorSpan fgcspan = new ForegroundColorSpan(ecolor);
        	    SpannableStringBuilder ssbuilder = new SpannableStringBuilder(REQUIRED_MSG);
        	    ssbuilder.setSpan(fgcspan, 0, REQUIRED_MSG.length(), 0);
        	
            textView.setError(ssbuilder);
            return false;
        }

        return true;
    }
    
 
    
}

