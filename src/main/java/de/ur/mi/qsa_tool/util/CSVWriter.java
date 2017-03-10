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

	public String getCSVStringFromArraysInList(ArrayList<String[]> configurationEpisodeMatrix) {
		String content = "";
		for(int i = 0; i<configurationEpisodeMatrix.size(); i++){
			for(int j = 0; j<configurationEpisodeMatrix.get(0).length; j++){
				content = content.concat(configurationEpisodeMatrix.get(i)[j] + SEPERATOR_CSV_DATA);
				if(j==configurationEpisodeMatrix.get(0).length-1){
					content = content.concat(NEW_LINE_SEPERATOR);
				}
			}
		}
		return content;
	}

	public String getCSVStringFromPersonUI(ArrayList<PersonUI> personOverviewStats) {
		String content = "";
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

	public String getCSVStringFromReplyLengths(ArrayList<HashMap<Integer, Integer>> replyLengths, ArrayList<String> personNames) {
		String content = "";
		for(int i = 0; i<personNames.size(); i++){
			content = content.concat(personNames.get(i) + SEPERATOR_CSV_DATA);
			for(int j = 0; j<replyLengths.get(i).size(); j++){
				content = content.concat(""+ j +" (" + replyLengths.get(i).get(j).intValue() + ") " + SEPERATOR_CSV_DATA);
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
				content = content.concat(""+ wordsForPersons[i][j] + SEPERATOR_CSV_DATA);
				if(j==wordsForPersons.length-1){
					content = content.concat(NEW_LINE_SEPERATOR);
				}
			}
		}
		return content;
	}

}
