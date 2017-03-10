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

	public Integer getSentencesFromLine(String line) {
		Integer sentences = 0;
		for (int d = 1; d < line.length(); d++) {
			if(line.charAt(d)=='.' || line.charAt(d)=='!' || line.charAt(d)=='?'){
				if(line.charAt(d-1)=='.' || line.charAt(d-1)=='!' || line.charAt(d-1)=='?'){
					sentences++;
				}
			}
		}
		return sentences;
	}

}
