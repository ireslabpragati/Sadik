package com.sadik.teacher;

import java.util.ArrayList;

public class ArrayListToArrayConverter {

	public String[] convert(ArrayList<String> stock_list)
	{
	    String[] stockArr = new String[stock_list.size()];
	    stockArr = stock_list.toArray(stockArr);
	    
	    return stockArr;
	}	
}
