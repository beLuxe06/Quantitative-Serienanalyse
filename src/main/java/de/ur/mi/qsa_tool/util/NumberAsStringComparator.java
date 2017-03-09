package de.ur.mi.qsa_tool.util;

import java.util.Comparator;

public class NumberAsStringComparator implements Comparator<String>  {

	private final static String PERCENTAGE = "%";
	private final static String EMPTY_STRING = "";
	
	@Override
	public int compare(String s1, String s2) {
		
		deleteUnneededChars(s1);
		deleteUnneededChars(s2);
		
		try{
			return Integer.parseInt(s1) < Integer.parseInt(s2) ? -1 : Integer.parseInt(s1) == Integer.parseInt(s2) ? 0 : 1;
		} catch(NumberFormatException e){
			e.printStackTrace();
			return 0;
		}
	}

	private void deleteUnneededChars(String string) {
		if(string.contains(PERCENTAGE)){
			string = string.replaceAll(PERCENTAGE, EMPTY_STRING);
		}
	}
}
