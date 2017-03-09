package de.ur.mi.qsa_tool.util;

import java.util.ArrayList;

import de.ur.mi.qsa_tool.gui.model.PersonUI;
import de.ur.mi.qsa_tool.model.Person;

public class ArrayListConverter {
	
	public ArrayListConverter(){
		
	}

	public ArrayList<PersonUI> convertPersonListToPersonStats(ArrayList<Person> personList, Integer numberOfSeasons, Integer numberOfScenes, Integer numberOfEpisodes) {
		ArrayList<PersonUI> personUIList = new ArrayList<>();
		for(Person person : personList){
			PersonUI personUI = new PersonUI(person, numberOfSeasons, numberOfEpisodes, numberOfScenes);
			personUIList.add(personUI);
		}
		return personUIList;
	}
	
	

}
