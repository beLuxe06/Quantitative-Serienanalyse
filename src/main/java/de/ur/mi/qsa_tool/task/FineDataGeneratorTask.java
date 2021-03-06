package de.ur.mi.qsa_tool.task;

import java.util.ArrayList;

import de.ur.mi.qsa_tool.model.Action;
import de.ur.mi.qsa_tool.model.Episode;
import de.ur.mi.qsa_tool.model.Data;
import de.ur.mi.qsa_tool.model.Person;
import de.ur.mi.qsa_tool.model.Scene;
import de.ur.mi.qsa_tool.model.Season;
import de.ur.mi.qsa_tool.util.WordCounter;
import javafx.concurrent.Task;

public class FineDataGeneratorTask extends Task<Data>{

	private Data data;
	private WordCounter wordCounter;
	private ArrayList<Person> personList = new ArrayList<>();
	private ArrayList<String> personNames = new ArrayList<>();
	private ArrayList<Scene> sceneList = new ArrayList<>();
	private ArrayList<Season> seasonList = new ArrayList<>();
	private ArrayList<Episode> episodeList = new ArrayList<>();
	private ArrayList<Action> actionList = new ArrayList<>();
	//private ArrayList<WordCount> wordCounterList = new ArrayList<>();
	
	// private int lineCounter = 0;
	// private int testVariable = 0;
	private int episodeCounter = 1;
	private int seasonCounter = 1;
	private int sceneCounter = 0;
	private int actionCounter = 0;
	// private int personCounter = 0;
		
	// private static final Integer SPEECH_LENGTH_MAX_SIZE = 200;
	private static final char SCENE_STARTER = '[';
	private static final char ACTION_STARTER = '(';
	private static final char EPISODE_STARTER = '-';
	private static final char SEASON_STARTER = '~';
	private static final String MISTAKE = "﻿";
	
	public FineDataGeneratorTask(Data data){
		this.data = data;
		readDataValues();
		wordCounter = new WordCounter();
	}
	
	private void readDataValues() {
		personList = data.getPersonList();
		sceneList = data.getSceneList();
		seasonList = data.getSeasonList();
		episodeList = data.getEpisodeList();
		actionList = data.getActionList();
	}

	@Override
	protected Data call() throws Exception {
		
		processCalculatableData();
		
		try{
			ArrayList<String> fileContent = new ArrayList<String>(getFileContents());
			System.out.println("Raw Data: processed fileContentSize: " + fileContent.size());
			
			for (int i = 0; i<fileContent.size(); i++) {
				String file = fileContent.get(i);
				String[] lines = file.split("\\n");
				System.out.println("lines size: " + lines.length + " of file no.: " + (fileContent.indexOf(file)+1));
				
				for(String line : lines) {
					if(line.contains(MISTAKE)){
						line = line.replaceAll(MISTAKE, "");
					}
					char c = line.charAt(0);
					
					// check for scene
					if (c == SCENE_STARTER) {
						if(sceneCounter<sceneList.size()-1){
							sceneCounter++;
						}
						sceneNameAnalysis(line, sceneList.get(sceneCounter));
					} 
					// check for action
					else if (c == ACTION_STARTER) {
						actionCounter++;
						Action action = new Action(actionCounter);
						actionOutsidePerson(action, line);
						actionList.add(action);
					} 
					// check for episode
					else if (c == EPISODE_STARTER) {
						if(episodeCounter<episodeList.size()){
							episodeCounter++;
						}
					} 
					// check for season
					else if (c == SEASON_STARTER) {
						if(seasonCounter<seasonList.size()){
							seasonCounter++;
						}
					} 
					
					else {
						int charCounter = 0;
						while (c != ':' && charCounter < line.length() - 1) {
							charCounter++;
							c = line.charAt(charCounter);
						}
						// check for person
						String endLine = line.substring(charCounter);
						if (c == ':') {
							String personName = line.substring(0, charCounter);
							if(personNames.contains(personName)){
								Person person = personList.get(personNames.indexOf(personName));
								addPersonToScene(person);
								addIdsToPerson(person);
								Integer sentences = wordCounter.getSentencesFromLine(endLine.substring(1));
								addSentenceToPerson(person, sentences);
								ArrayList<String> words = wordCounter.getWordsFromLine(endLine.substring(1));
								addSpeechAndWordCountToPerson(person, words.size());
								addReplyLengthToPerson(person, words.size());
								addWordCountsToPerson(person, words);
								addWordCountsToScene(words);
							}
						}
						else{
							ArrayList<String> words = wordCounter.getWordsFromLine(endLine);
							addWordCountsToScene(words);
						}
					}
					
				
			}
			
			}
			
		} catch(Exception e){
			e.printStackTrace();
		}
		return data;
	}
	
	private void addSentenceToPerson(Person person, Integer sentences) {
		person.setSentences(person.getSentences() + sentences);
	}

	private void addSpeechAndWordCountToPerson(Person person, int wordSize) {
		person.setSpeechNumbers(person.getSpeechNumbers()+1);
		person.setWordNumbers(person.getWordNumbers()+wordSize);
	}

	private void addWordCountsToScene(ArrayList<String> words) {
		for(String word : words){
			sceneList.get(sceneCounter).increaseWordCount(word);
		}
	}
	
	private void addWordCountsToPerson(Person person, ArrayList<String> words) {
		for(String word : words){
			person.increaseWordCount(word);
		}
	}
	
	private void addReplyLengthToPerson(Person person, Integer wordCount) {
		person.addLengthToReplyLengths(wordCount);	
	}

	private void addIdsToPerson(Person person) {
		Integer seasonId = seasonList.get(seasonCounter-1).getSeasonId();
		if(!person.getSeasonIdList().contains(seasonId)){
			person.getSeasonIdList().add(seasonId);
		}
		Integer episodeId = episodeList.get(episodeCounter-1).getEpisodeId();
		if(!person.getEpisodeIdList().contains(episodeId)){
			person.getEpisodeIdList().add(episodeId);
		}
		Integer sceneId = sceneList.get(sceneCounter-1).getSceneId();
		if(!person.getSceneIdList().contains(sceneId)){
			person.getSceneIdList().add(sceneId);
		}
		//Just to set DebugPoint
		if((person.getPersonId().getId()==1) && (sceneId % 1150 == 0)){
			int  z = 0;
		}
	}

	private void addPersonToScene(Person person) {
		Scene scene = sceneList.get(sceneCounter-1);
		if(!scene.getPersonIdList().contains(person.getPersonId().getId())){
			scene.getPersonIdList().add(person.getPersonId().getId());
		}
	}

		
		
	private void actionOutsidePerson(Action action, String line) {
		action.setSentence(line.substring(1,(line.length())-1));
	}	
		
	private void sceneNameAnalysis(String line, Scene scene) {
		if(line.length()>9){
			if(line.substring(1,10).toLowerCase().equals("flashback")){
				scene.setFlashback(true);
				if(line.length()>14){
					if(line.substring(11,15).toLowerCase().equals("ends") ||line.substring(11,15).toLowerCase().equals("over")){
						scene.setFlashback(false);
						int index = sceneCounter;
						if(index >= 2){
							scene.setFlashbackReferenceScriptId(sceneList.get(index-2).getScriptId());
						}
						
					} else {
						String lineEnd = line.substring(10,line.length());
						ArrayList <String> words = wordCounter.getWordsFromLine(lineEnd);
						for(int i = 0; i < words.size();i++){
							for(int j = 0; j < scene.getSceneNameWords().size();j++){
								if(words.get(i).equals(scene.getSceneNameWords().get(i))){
									if(!(words.get(i).equals("to"))){
										scene.setFlashbackReferenceScriptId(sceneList.get(j).getScriptId());
									}
								}
							}
						}
						for(int i=0;i<words.size();i++){
							scene.getSceneNameWords().add(words.get(i));
						}				
					}
				}
			}
		}
		
	}

	private void processCalculatableData() {
		getPersonNames();
		getSeasonsEpisodeList();
		getEpisodesSceneList();
	}
	

	private void getEpisodesSceneList() {
		for(Scene scene : sceneList){
			Integer sceneEpisodeId = scene.getEpisodeId();
			for(Episode episode : episodeList){
				if(episode.getEpisodeId().equals(sceneEpisodeId)){
					episode.getSceneIdList().add(scene.getSceneId());
				}
			}
		}
	}

	private void getSeasonsEpisodeList() {
		for(Episode episode : episodeList){
			Integer episodeSeasonId = episode.getSeasonId();
			for(Season season : seasonList){
				if(season.getSeasonId().equals(episodeSeasonId)){
					season.getEpisodeList().add(episode.getEpisodeId());
				}
			}
		}
	}

	private void getPersonNames() {
		for(int i = 0; i < personList.size(); i++){
			personNames.add(personList.get(i).getPersonId().getName());
		}
	}

	private ArrayList<String> getFileContents() {
		ArrayList<String> fileContents = new ArrayList<>();
		for(int i = 0; i < data.getCorpus().getScripts().size(); i++){
			fileContents.add(data.getCorpus().getScripts().get(i).getFileContent());
		}
		return fileContents;
	}
			
			
}
