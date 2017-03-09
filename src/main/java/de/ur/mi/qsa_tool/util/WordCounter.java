package de.ur.mi.qsa_tool.util;

import java.util.ArrayList;

public class WordCounter {
	
	public WordCounter(){
		
	}
	
	public ArrayList<String> getWordsFromLine(String line){
		ArrayList<String> wordsFromLine = new ArrayList<>();
		line = line.trim();
		String[] words = line.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
		for(String word : words){
			wordsFromLine.add(word);
		}
		return wordsFromLine;
	}

}
