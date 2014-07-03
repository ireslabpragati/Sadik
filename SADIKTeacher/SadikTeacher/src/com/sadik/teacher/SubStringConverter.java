package com.sadik.teacher;

public class SubStringConverter {

	public static String convert(String inputString)
	{
		String output = "";
		String arr[] = inputString.split(",");
		for (int i = 0; i < arr.length; i++) {
			output += ","+arr[i].substring(0, 2);
		}
		if(!output.equals(""))
		{
			return output.substring(1,output.length());
		}
		return output;
	}
	

}
