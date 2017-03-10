package de.ur.mi.qsa_tool.model;

public class StringIntegerPair implements Comparable<StringIntegerPair>{
	
	private Integer integer;
	private String string;
	
	public StringIntegerPair(String string, Integer integer){
		this.string = string;
		this.integer = integer;
	}

	public Integer getInteger() {
		return integer;
	}



	public void setInteger(Integer integer) {
		this.integer = integer;
	}



	public String getString() {
		return string;
	}



	public void setString(String string) {
		this.string = string;
	}



	@Override
	public int compareTo(StringIntegerPair o) {

        int firstCompare = o.getInteger().compareTo(integer);

        if (firstCompare != 0) {
           return firstCompare;
        } else {
           return o.getString().compareTo(string);
        }
	}

}
