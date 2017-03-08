package de.ur.mi.qsa_tool.util;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;


public class TxtReader {
	
	public TxtReader(){
		
	}
	
	public String readFile(String filepathAsString) throws Exception{
		File file = new File(filepathAsString);
		Path filepath = file.toPath();
		List<String> lines = Files.readAllLines(filepath, Charset.defaultCharset());
		return getLinesAsSingleString(lines);
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
