package de.ur.mi.qsa_tool.task;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.security.Permissions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.ur.mi.qsa_tool.model.Action;
import de.ur.mi.qsa_tool.model.Episode;
import de.ur.mi.qsa_tool.model.NewData;
import de.ur.mi.qsa_tool.model.Person;
import de.ur.mi.qsa_tool.model.Scene;
import de.ur.mi.qsa_tool.model.Season;
import javafx.concurrent.Task;

public class FineDataGeneratorTask extends Task<NewData>{

	private NewData data;
	private Integer lineCounter = 0;
	private Integer testVariable = 0;
	private Integer episodeCounter = 0;
	private Integer seasonCounter = 0;
	private ArrayList <Action> actions = new ArrayList <Action>();
	private Integer sceneCounter = 0;
	private Integer actionCounter = 0;
	private List<Map<String, Integer>> wordCounter = new ArrayList<Map<String, Integer>>();
	private static final Integer SPEECH_LENGTH_MAX_SIZE = 200;
	private static final char SCENE_STARTER = '[';
	private static final char ACTION_STARTER = '(';
	private static final char EPISODE_STARTER = '-';
	private static final char SEASON_STARTER = '~';
	
	public FineDataGeneratorTask(NewData data){
		this.data = data;
	}
	
	@Override
	protected NewData call() throws Exception {
		List<String> fileContent = new ArrayList<String>(data.getCorpus().getFileContents());
		System.out.println("FineData: processed fileContentSize: " + fileContent.size());
		int speechCounter[][] = new int [data.getPersonList().size()][data.getSceneList().size()];
		int speechLength [][][] = new int [data.getPersonList().size()][data.getSceneList().size()][SPEECH_LENGTH_MAX_SIZE];
		int sceneWordCounter[][] = new int [data.getPersonList().size()][data.getSceneList().size()];
		Scene[] scenes = data.getSceneList().toArray(new Scene[0]);
		Episode[] episodes = data.getEpisodeList().toArray(new Episode[0]);
		Season[] seasons = data.getSeasonList().toArray(new Season[0]);
		Person[] persons = data.getPersonList().toArray(new Person[0]);
		
		try {
			for (String file : fileContent) {
				String[] lines = file.split("\\n");
				System.out.println("lines size: " + lines.length + " of file no.: " + fileContent.indexOf(file));
				for(String line : lines) { 					
					int wordsPerSpeech = 0;
					int j = 0;
					char c = line.charAt(0);
					int charCounter = 0;
					int length = line.length();
					if (c == SCENE_STARTER) {
						//sceneNameAnalysis(line, scenes[sceneCounter], sceneCounter);
						sceneCounter++;
					} else if(c == ACTION_STARTER){
						Action action = new Action(actionCounter);
						actionOutsidePerson(action, line);
						actions.add(action);
						actionCounter++;
					}  else if(c == EPISODE_STARTER){
						//episodes[episodeCounter].setEndScene(seasonCounter-1);
						//episodes[episodeCounter].setStartScene(seasonCounter);
					}  else if(c == SEASON_STARTER){
						//seasons[seasonCounter].setEndEpisode(episodeCounter-1);
						//episodes[seasonCounter].setStartScene(episodeCounter);
					} 
					while (c != ':' && charCounter < length - 1) {
						charCounter++;
						c = line.charAt(charCounter);
					}
					if (c == ':') {
						String person = "";
						person = line.substring(0, charCounter);
						
						
//						checkPerson(persons, line, person, wordsPerSpeech, data.getPersonList().size(), length,
//								charCounter, scenes, speechCounter, sceneWordCounter, speechLength);
					}
				}
			}
			
			createData(persons, episodes, scenes, seasons);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}

	private void createData(Person[] persons, Episode[] episodes, Scene[] scenes, Season[] seasons) {
		data.setPersonList((ArrayList<Person>) Arrays.asList(persons));
		data.setEpisodeList((ArrayList<Episode>) Arrays.asList(episodes));
		data.setSceneList((ArrayList<Scene>) Arrays.asList(scenes));
		data.setSeasonList((ArrayList<Season>) Arrays.asList(seasons));
		System.out.println("creating FineData...");
	}

//	private void generateStats(Person[] persons, Scene[] scenes, List<Map<String, Integer>> wordCounter, int sceneCounter, int personsTotal, int[][] speechCounter, int[][][] speechLength, int[][] sceneWordCounter) {
//		System.out.println("Logik fertig");
//		StatsGenerator.getWordCounter(personsTotal, persons, wordCounter);
//		System.out.println("WordCounter fertig");
//		StatsGenerator.getAllScenesWordCounter(scenes,sceneCounter);
//		System.out.println("AlleSzenenWörterZähler fertig");
//		//StatsGenerator.getSpeechLengths(personsTotal, persons, sceneCounter,speechLength);
//		System.out.println("Replikenlängen fertig");
//		StatsGenerator.getConfigurationMatrix(persons,scenes);
//		System.out.println("matrix fertig");
//		StatsGenerator.getTimeline(scenes);
//		System.out.println("Timeline fertig");
//		StatsGenerator.getSpeakersDistribution(scenes,persons);
//		StatsGenerator.getCharacterConstellation(5,6,scenes,persons);
//		StatsGenerator.getQuickMatrix(speechCounter,scenes,persons);
//		System.out.println("Statistiken fertig");
//	}

//	private void sceneNameAnalysis(String line, Scene scene, int sceneCounter) {
//		if(line.length()>9){
//			if(line.substring(1,10).toLowerCase().equals("flashback")){
//				scene.setFlashback(true);
//				if(line.length()>14){
//					if(line.substring(11,15).toLowerCase().equals("ends") ||line.substring(11,15).toLowerCase().equals("over")){
//						scene.setFlashback(false);
//						if(sceneCounter>1){
//							//scene.setFlashbackReference(sceneCounter-2);
//						}
//					} else {
//						String s = line.substring(10,line.length());
//						ArrayList <String> w = getWordsFromLine(s);
//						for(int i =0;i<w.size();i++){
//							for(int j=0;j<scene.getSceneNameWords().size();j++){
//								if(w.get(i).equals(scene.getSceneNameWords().add(w.get(i)))){
//									if(!(w.get(i).equals("to"))){
//										scene.setFlashbackReference(j);
//									}
//								}
//							}
//						}
//						for(int i=0;i<w.size();i++){
//							scene.getSceneNameWords().add(w.get(i));
//						}				
//					}
//				}
//			}
//		}
//	}

	private void actionOutsidePerson(Action action, String line) {
		action.setSentence(line.substring(1,(line.length())-1));
	}

	private static ArrayList<String> getWordsFromLine(String s) {
		ArrayList<String> wordList=new ArrayList <String>();
		int lastNotLetter = 0;
		int k = 0;
		String word="";
		int textLength = s.length();
		for (k = 1; k < textLength; k++) {
			Character d = s.charAt(k);
			if (Character.isWhitespace(d)) {
				 word = s.substring(lastNotLetter, k);
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
				wordList.add(word);
				lastNotLetter = k;
			}	
		}
		wordList.add(s.substring(lastNotLetter+1,s.length()-1));
		return wordList;		
	}

	
//	private void checkPerson(Person[] persons, String line, String person, Integer wordsPerSpeech, Integer personsTotal, Integer length, Integer charCounter,
//			Scene[] scenes, int[][] speechCounter, int[][] sceneWordCounter, int[][][] speechLength) {
//		int pos = 0;
//		for (int j = 0; j < personsTotal; j++) {
//	
//			if ((person.equals(persons[j].getName()))) {
//				boolean addPersonToScene = true;
//				for (int z = 0; z < scenes[sceneCounter].getPersons().size(); z++) {
//					if (person.equals(scenes[sceneCounter].getPersons().get(z))) {
//						pos = z;
//						addPersonToScene = false;
//					} 
//				}
//				if (addPersonToScene) {
//					pos = scenes[sceneCounter].getPersons().size();
//					scenes[sceneCounter].getPersons().add(person);
//					scenes[sceneCounter].getPersonWords().add(new HashMap<String, Integer>());			
//				}
//				String text = line.substring(charCounter + 1, length) + " ";
//				int lastNotLetter = 0;
//				int textLength = text.length();
//				//System.out.println("inc"+ persons[j].speechCounter.get(sceneCounter));
//				//persons[j].incrementSpeechCounter(sceneCounter);
//				
//				/*int temp = persons[j].speechCounter.get(sceneCounter);
//				temp++;
//				persons[j].speechCounter.set(sceneCounter,Integer.valueOf(temp));*/
//				
//				speechCounter[j][sceneCounter]++;
//				
//				//persons[j].speechCounter.get(sceneCounter) +=1; ;
//				int k = 0;
//				for (k = 1; k < textLength; k++) {
//					Character d = text.charAt(k);
//					if (Character.isWhitespace(d)) {
//						wordsPerSpeech++;
//						String word = text.substring(lastNotLetter, k);
//						word = word.toLowerCase();
//						word = word.substring(1, word.length());
//						int i = 0;
//						while (i < word.length()) {
//							if (word.charAt(i) == ',' || word.charAt(i) == '.' || word.charAt(i) == '?'
//									|| word.charAt(i) == '!' || word.charAt(i) == ';') {
//								word = word.substring(0, i) + word.substring(i + 1);
//							} else {
//								i++;
//							}
//						}
//						lastNotLetter = k;
//						//persons[j].sceneWordCounter[sceneCounter]++;
//						
//						//Integer count = persons[j].sceneWordCounter.get(sceneCounter);
//						//persons[j].incrementSceneWordCounter(sceneCounter);
//						sceneWordCounter[j][sceneCounter]++;
//						updateWordCounts(word,j,wordCounter);
//						updatePersonWordCounts(pos, scenes,sceneCounter,j,word);
//						 
//					}
//				}
//				if (wordsPerSpeech != 0) {
//					//Integer count = persons[j].speechLength.get(sceneCounter).get(wordsPerSpeech);
//					//persons[j].speechLength.get(sceneCounter).put(wordsPerSpeech, count + 1);
//					speechLength[j][sceneCounter][wordsPerSpeech]++;
//				}
//			} 
//		}
//	}
//	
//	private void updatePersonWordCounts(int pos, Scene[] scenes, int sceneCounter, int j, String word) {
//		 if(scenes[sceneCounter].getPersonWords().get(pos).containsKey(word)){ 
//			 Integer value= scenes[sceneCounter].getPersonWords().get(pos).get(word);
//			 value++;
//			 scenes[sceneCounter].getPersonWords().get(pos).put(word,value); 
//		} else{
//			 scenes[sceneCounter].getPersonWords().get(pos).put(word, 1);
//		 }
//	}

	private void updateWordCounts(String word, int j, List<Map<String, Integer>> wordCounter) {
		if(wordCounter.isEmpty() || j>=wordCounter.size()){
			HashMap<String, Integer> wordCounterForIndex = new HashMap<>();
			wordCounterForIndex.put(word, 1);
			wordCounter.add(wordCounterForIndex);
		}
		else if (wordCounter.get(j).containsKey(word)) {
			Integer value = wordCounter.get(j).get(word);
			value++;
			wordCounter.get(j).put(word, value);
		} else {
			wordCounter.get(j).put(word, 1);
		}
		
			
	}
}
