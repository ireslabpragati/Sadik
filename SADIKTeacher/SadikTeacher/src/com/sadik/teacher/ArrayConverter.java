package com.sadik.teacher;

public class ArrayConverter {
	
	public static String toCommaSeperated(String[] strArray) {
		String output = "";
		try {
			for(int i=0; i<strArray.length; i++) {
				output += ","+strArray[i];
			}
		return output.substring(1, output.length());
		}
		catch(Exception e) {
			
		}
		return output;
	}

}
