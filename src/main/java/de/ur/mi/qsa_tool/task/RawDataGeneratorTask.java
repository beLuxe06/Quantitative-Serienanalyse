package de.ur.mi.qsa_tool.task;

import java.util.ArrayList;
import java.util.List;

import de.ur.mi.qsa_tool.model.Action;
import de.ur.mi.qsa_tool.model.Episode;
import de.ur.mi.qsa_tool.model.NewData;
import de.ur.mi.qsa_tool.model.Person;
import de.ur.mi.qsa_tool.model.Scene;
import de.ur.mi.qsa_tool.model.Season;
import javafx.concurrent.Task;

public class RawDataGeneratorTask extends Task <NewData>{

	private ArrayList<Person> personList = new ArrayList<>();
	private ArrayList<String> personNames = new ArrayList<>();
	private ArrayList<Scene> sceneList = new ArrayList<>();
	private ArrayList<Episode> episodeList = new ArrayList<>();
	private ArrayList<Season> seasonList = new ArrayList<>();
	private ArrayList<Action> actionList = new ArrayList<>();
	private Integer sceneCounter = 0;
	private Integer actionCounter = 0;
	private Integer episodeCounter = 1;
	private Integer seasonCounter = 1;
	private Integer personCounter = 0;
	private static final char ACTION_STARTER = '(';
	private static final char ACTION_STOPPER = ')';
	private static final char SCENE_STARTER = '[';
	private static final char SCENE_STOPPER = ']';
	private static final char EPISODE_STARTER = '-';
	private static final char SEASON_STARTER = '~';
	boolean fileIsEpisode = false;
	boolean fileIsSeason = true;
	private NewData data;
	
	public RawDataGeneratorTask(NewData data) {
		this.data = data;
	}
	
	@Override
	protected NewData call() throws Exception {
		List<String> fileContent = new ArrayList<String>(data.getCorpus().getFileContents());
		System.out.println("Raw Data: processed fileContentSize: " + fileContent.size());
			if(fileIsEpisode){
				seasonList.add(new Season(seasonCounter));
				for(int i = 0; i< fileContent.size(); i++){
					episodeList.add(new Episode(seasonCounter, episodeCounter));
					episodeCounter++;
				}
			} else if(fileIsSeason){
				for(int i = 0; i< fileContent.size(); i++){
					seasonList.add(new Season(seasonCounter));
					seasonCounter++;
				}
			}
			try {
				for (String file : fileContent) {
					String[] lines = file.split("\\n");
					System.out.println("lines size: " + lines.length + " of file no.: " + fileContent.indexOf(file));
					for(String line : lines) {
						char c = line.charAt(0);
						// check for scene
						if (c == SCENE_STARTER) {
							sceneList.add(new Scene(seasonCounter, episodeCounter, sceneCounter));
							sceneList.get(sceneCounter).setTitle(line);
							sceneCounter++;
						} else if (c == ACTION_STARTER) {
							actionCounter++;
						} else if (c == EPISODE_STARTER) {
							episodeList.add(new Episode(seasonCounter, episodeCounter));
							episodeCounter++;
						} else if (c == SEASON_STARTER) {
							seasonList.add(new Season(seasonCounter));
							seasonCounter++;
						} else {
							int charCounter = 0;
							while (c != ':' && charCounter < line.length() - 1) {
								charCounter++;
								c = line.charAt(charCounter);
							}
							if (c == ':') {
								String personName = line.substring(0, charCounter);
								if(!personNames.contains(personName)){
									personCounter++;
									personNames.add(personName);
									personList.add(new Person(personCounter, personName));
								}
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("XXXXXXXXXXXXXXXXXXXXXXX: " + seasonList.size());
			
			createData();
//			LogicHandler.stats(fileList, personList, sceneList, actionCounter, sceneLineReference, episodeLineReference,
//					seasonLineReference, SCENE_STARTER, ACTION_STARTER, EPISODE_STARTER, SEASON_STARTER, episodeList,
//					seasonList);
		
		return data;
	}

	private void createData() {
		data.setPersonList(personList);
		System.out.println("found persons: " + personList.size());
		data.setEpisodeList(episodeList);
		System.out.println("found episodes: " + episodeList.size());
		data.setSceneList(sceneList);
		System.out.println("found scenes: " + sceneList.size());
		data.setSeasonList(seasonList);
		System.out.println("found seasons: " + seasonList.size());
		data.setActionList(actionList);
		System.out.println("found actions: " + actionList.size());
		System.out.println("creating raw Data...");
	}

}
