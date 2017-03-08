package de.ur.mi.qsa_tool.util;

import java.util.ArrayList;

public class WordCounter {
	
	public WordCounter(){
		
	}
	
	public ArrayList<String> getWordsFromLine(String line){
		ArrayList<String> wordsFromLine = new ArrayList<>();
		int lastNotLetter = 0;
		int k = 0;
		String word = "";
		int textLength = line.length();
		for (k = 1; k < textLength; k++) {
			Character d = line.charAt(k);
			if (Character.isWhitespace(d)) {
				 word = line.substring(lastNotLetter, k);
				word = word.toLowerCase();
				word = word.substring(1, word.length());
				int i = 0;
				while (i < word.length()) {
					if (word.charAt(i) == ',' || word.charAt(i) == '.' || word.charAt(i) == '?'
							|| word.charAt(i) == '!' || word.charAt(i) == ';') {
						word = word.substring(0, i) + word.substring(i + 1);
					} else {
						i++;
					}
				}
				wordsFromLine.add(word);
				lastNotLetter = k;
			}	
		}
		wordsFromLine.add(line.substring(lastNotLetter+1,line.length()-1));
		return wordsFromLine;
	}
	
	public ArrayList<String> getWordsFromLine2(String line){
		ArrayList<String> wordsFromLine = new ArrayList<>();
		line = line.trim();
		String[] words = line.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
		for(String word : words){
			wordsFromLine.add(word);
		}
		return wordsFromLine;
	}

}
