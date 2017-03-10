package de.ur.mi.qsa_tool.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import de.ur.mi.qsa_tool.gui.model.PersonUI;

public class CSVWriter {

	private Writer writer;
	private static final String SEPERATOR_CSV_DATA = ";";
	private static final String NEW_LINE_SEPERATOR = "\n";

	public CSVWriter() {
		writer = null;
	}

	public void writeCSV(File file, String content) throws Exception {
		try {
			writer = new BufferedWriter(new FileWriter(file));
			writer.write(content);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			writer.flush();
			writer.close();
		}
	}

	public String getCSVStringFromArraysInList(ArrayList<String[]> configurationMatrix) {
		String content = "";
		for(int i = 0; i<configurationMatrix.size(); i++){
			for(int j = 0; j<configurationMatrix.get(0).length; j++){
				content = content.concat(configurationMatrix.get(i)[j] + SEPERATOR_CSV_DATA);
				if(j==configurationMatrix.get(0).length-1){
					content = content.concat(NEW_LINE_SEPERATOR);
				}
			}
		}
		return content;
	}

	public String getCSVStringFromPersonUI(ArrayList<PersonUI> personOverviewStats) {
		String content = "";
		content = getPersonStatsColumns();
		for (PersonUI person : personOverviewStats) {
			String itemAsString = person.getName() + SEPERATOR_CSV_DATA + person.getWordNumbers()
					+ SEPERATOR_CSV_DATA + person.getSpeechNumbers() + SEPERATOR_CSV_DATA
					+ person.getSeasonsPresenceSize() + SEPERATOR_CSV_DATA + person.getAppereanceSeasonsShare() 
					+ SEPERATOR_CSV_DATA + person.getEpisodesPresenceSize() + SEPERATOR_CSV_DATA + person.getAppereanceEpisodesShare()
					+ SEPERATOR_CSV_DATA + person.getScenesPresenceSize() + SEPERATOR_CSV_DATA + person.getAppereanceScenesShare() + NEW_LINE_SEPERATOR;
			content = content.concat(itemAsString);
		}
		return content;
	}

	private String getPersonStatsColumns() {
		String columnNames = "Name:" + SEPERATOR_CSV_DATA
				+ "Wortanzahl:" + SEPERATOR_CSV_DATA + "Replikenanzahl: "
				+ SEPERATOR_CSV_DATA + "Auftritt in Staffeln: " + SEPERATOR_CSV_DATA + "Auftrittanteil in Staffeln: "
				+ SEPERATOR_CSV_DATA + "Auftritt in Episoden: " + SEPERATOR_CSV_DATA + "Auftrittanteil in Episoden: "
				+ SEPERATOR_CSV_DATA + "Auftritt in Szenen: " + SEPERATOR_CSV_DATA + "Auftrittanteil in Szenen: "
				+ NEW_LINE_SEPERATOR;
		return columnNames;
	}

	public String getCSVStringFromReplyLengths(ArrayList<HashMap<Integer, Integer>> replyLengths, ArrayList<String> personNames) {
		String content = "";
		for(int i = 0; i<replyLengths.size(); i++){
			content = content.concat(personNames.get(i) + SEPERATOR_CSV_DATA);
			for(int j = 0; j<replyLengths.get(i).size(); j++){
				Integer count = replyLengths.get(i).get(j);
				if(count == null){
					count = 0;
				}
				content = content.concat(""+ j +" (" + count + ") " + SEPERATOR_CSV_DATA);
				if(j==replyLengths.get(i).size()-1){
					content = content.concat(NEW_LINE_SEPERATOR);
				}
			}
		}
		return content;
	}
	

	public String getCSVStringFromReplyLengths(String[][] wordsForPersons, ArrayList<String> personNames) {
		String content = "";
		for(int i = 0; i<wordsForPersons.length; i++){
			content = content.concat(personNames.get(i) + SEPERATOR_CSV_DATA);
			for(int j = 0; j<wordsForPersons.length; j++){
				String count = wordsForPersons[i][j];
				if(count == null){
					count = "" + 0;
				}
				content = content.concat(""+ count + SEPERATOR_CSV_DATA);
				if(j==wordsForPersons.length-1){
					content = content.concat(NEW_LINE_SEPERATOR);
				}
			}
		}
		return content;
	}

	private String getTitleColumns(ArrayList<String> arrayList) {
		String columnNames = "";
		for(int i = 0; i< arrayList.size(); i++){
			columnNames = columnNames.concat(arrayList.get(i) + SEPERATOR_CSV_DATA);
		}
		columnNames = columnNames.concat(NEW_LINE_SEPERATOR);
		return columnNames;
	}
	
	private String getTitleColumns(String[] array) {
		String columnNames = "";
		for(int i = 0; i< array.length; i++){
			columnNames = columnNames.concat(array[i] + SEPERATOR_CSV_DATA);
		}
		columnNames = columnNames.concat(NEW_LINE_SEPERATOR);
		return columnNames;
	}
	
	public String getCSVStringFromPersonConstellations(Integer[][] personConstellations, ArrayList<String> personNames) {
		String content = "";
		content = getTitleColumns(personNames);
		for(int i = 0; i<personConstellations.length; i++){
			content = content.concat(personNames.get(i) + SEPERATOR_CSV_DATA);
			for(int j = 0; j<personConstellations.length; j++){
				content = content.concat(personNames.get(j) + SEPERATOR_CSV_DATA);
				content = content.concat(""+ personConstellations[i][j] + SEPERATOR_CSV_DATA);
				if(j==personConstellations.length-1){
					content = content.concat(NEW_LINE_SEPERATOR);
				}
			}
		}
		return content;
	}

	public String getCSVStringFromArraysInListWithoutTitles(ArrayList<String[]> configurationMatrix) {
		String content = "";
		for(int i = 1; i<configurationMatrix.size(); i++){
			for(int j = 1; j<configurationMatrix.get(0).length; j++){
				content = content.concat(configurationMatrix.get(i)[j] + SEPERATOR_CSV_DATA);
				if(j==configurationMatrix.get(0).length-1){
					content = content.concat(NEW_LINE_SEPERATOR);
				}
			}
		}
		return content;
	}

	public String getCSVStringFromPersonConstellationsWithoutNames(Integer[][] personConstellations) {
		String content = "";
		for(int i = 0; i<personConstellations.length; i++){
			for(int j = 0; j<personConstellations.length; j++){
				content = content.concat(""+ personConstellations[i][j] + SEPERATOR_CSV_DATA);
				if(j==personConstellations.length-1){
					content = content.concat(NEW_LINE_SEPERATOR);
				}
			}
		}
		return content;
	}

	public String getCSVStringFromPersonNames(ArrayList<String> mostImportantPersonsNames) {
		return getTitleColumns(mostImportantPersonsNames);
	}

	public String getCSVStringFromSceneNumbers(String[] strings) {
		return getTitleColumns(strings);
	}

}
