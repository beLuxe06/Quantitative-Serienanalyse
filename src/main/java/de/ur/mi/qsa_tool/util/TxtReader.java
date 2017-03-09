package de.ur.mi.qsa_tool.util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;


public class TxtReader {
	
	public TxtReader(){
		
	}
	
	public String readFile(String filepathAsString) {
		File file = new File(filepathAsString);
		Path filepath = file.toPath();
		List<String> lines;
		try {
			lines = Files.readAllLines(filepath, Charset.defaultCharset());
			return getLinesAsSingleString(lines);
		} catch (IOException e) {
			System.out.println("TXTREADER could not read file!");
			e.printStackTrace();
			return null;
		}
	}

	private String getLinesAsSingleString(List<String> lines) {
		StringBuilder stringBuilder = new StringBuilder();
		String linesAsSingleString = "";
		for(String line : lines){
			if(!(line.equals(lines.get(lines.size()-1)))){
				stringBuilder.append(line + "\n");
			}
			else stringBuilder.append(line);
		}
		linesAsSingleString = stringBuilder.toString();
		return linesAsSingleString;
	}

}
