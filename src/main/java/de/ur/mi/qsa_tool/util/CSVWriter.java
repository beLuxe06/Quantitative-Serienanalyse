package de.ur.mi.qsa_tool.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;

public class CSVWriter {

	private Writer writer;
	private static final String SEPERATOR_CSV_DATA = ";";
	private static final String NEW_LINE_SEPERATOR = ";";

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

	public String getCSVStringFrom2DArray(String[][] configurationEpisodeMatrix) {
		String content = "";
		for(int i = 0; i<configurationEpisodeMatrix.length; i++){
			if(i!=0){
				content = content.concat(NEW_LINE_SEPERATOR);
			}
			for(int j = 0; j<configurationEpisodeMatrix.length; j++){
				content = content.concat(configurationEpisodeMatrix[i][j] + SEPERATOR_CSV_DATA);
			}
		}
		return content;
	}

}
