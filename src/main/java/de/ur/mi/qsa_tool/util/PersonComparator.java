package de.ur.mi.qsa_tool.util;

import java.util.Comparator;

import de.ur.mi.qsa_tool.model.Person;

public class PersonComparator implements Comparator<Person> {

	@Override
	public int compare(Person o1, Person o2) {
		int speechNumberPerson1 = o1.getSpeechNumbers();
		int speechNumberPerson2 = o2.getSpeechNumbers();
		return speechNumberPerson2 < speechNumberPerson1 ? -1 : speechNumberPerson2 == speechNumberPerson1 ? 0 : 1;
	}

}
