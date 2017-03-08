package de.ur.mi.qsa_tool.model;

import java.util.ArrayList;
import java.util.HashMap;

public class DiagramRawData extends ArrayList<HashMap<String, String>>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4770910512094102119L;
	
	private ArrayList<HashMap<String, String>> rawData;
	
	public DiagramRawData(ArrayList<HashMap<String, String>> rawData){
		this.rawData = rawData;
	}
	
	public HashMap<String, String> getMetaData(){
		return rawData.get(0);
	}
	
	public String getTitle(){
		return getMetaData().get(0);
	}
	
	public String getXAxisTitle(){
		return getMetaData().get(1);
	}
	
	public String getYAxisTitle(){
		return getMetaData().get(2);
	}
	
	public String getSubListCategory(){
		return getMetaData().get(3);
	}
	
	public ArrayList<String> getSubListTitles(){
		ArrayList<String> subListTitles = new ArrayList<>();
		if(rawData.size()>1){
			for(int i = 0; i < rawData.get(1).values().size(); i++){
				subListTitles.add(rawData.get(1).get(i));
			}
		}
		return subListTitles;
	}
	
	public ArrayList<HashMap<String, String>> getRawData(){
		if(rawData.size()>2){
			return (ArrayList<HashMap<String, String>>) rawData.subList(2, rawData.size());
		}
		else return null;
	}
	
	

}
