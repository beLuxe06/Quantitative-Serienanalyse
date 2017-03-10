package de.ur.mi.qsa_tool.util;

import java.util.Comparator;

import de.ur.mi.qsa_tool.model.StringIntegerPair;

public class StringIntegerPairComparator implements Comparator<StringIntegerPair>{

	@Override
	public int compare(StringIntegerPair o1, StringIntegerPair o2) {
		
        int firstCompare = o2.getInteger().compareTo(o1.getInteger());

        if (firstCompare != 0) {
           return firstCompare;
        } else {
           return o2.getString().compareTo(o1.getString());
        }
	}
	
	

}
