package de.ur.mi.qsa_tool.task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import de.ur.mi.qsa_tool.model.Action;
import de.ur.mi.qsa_tool.model.Corpus;
import de.ur.mi.qsa_tool.model.Episode;
import de.ur.mi.qsa_tool.model.NewData;
import de.ur.mi.qsa_tool.model.Person;
import de.ur.mi.qsa_tool.model.Scene;
import de.ur.mi.qsa_tool.model.Season;
import de.ur.mi.qsa_tool.model.Stats;
import javafx.concurrent.Task;

public class StatsGeneratorTask extends Task <Stats>{

	private NewData data;
	
	public StatsGeneratorTask(NewData data) {
		this.data = data;
	}
	
	@Override
	protected Stats call() throws Exception {
		
		return new Stats();
	}

	
}
