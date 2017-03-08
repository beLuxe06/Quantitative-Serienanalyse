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

public class FineDataGeneratorTask2 extends Task<NewData>{

	private NewData data;
	private ArrayList<Person> personList;
	private ArrayList<String> personNames = new ArrayList<>();
	private ArrayList<Scene> sceneList;
	private ArrayList<Season> seasonList;
	private ArrayList<Episode> episodeList;
	private ArrayList <Action> actionList;
	
	private Integer lineCounter = 0;
	private Integer testVariable = 0;
	private Integer episodeCounter = 0;
	private Integer seasonCounter = 0;
	private Integer sceneCounter = 0;
	private Integer actionCounter = 0;
	private List<HashMap<String, Integer>> wordCounter = new ArrayList<HashMap<String, Integer>>();
	private static final Integer SPEECH_LENGTH_MAX_SIZE = 200;
	private static final char SCENE_STARTER = '[';
	private static final char ACTION_STARTER = '(';
	private static final char EPISODE_STARTER = '-';
	private static final char SEASON_STARTER = '~';
	
	public FineDataGeneratorTask2(NewData data){
		readDataValues();
	}
	
	private void readDataValues() {
		
	}

	@Override
	protected NewData call() throws Exception {
		
		return data;
	}
}
