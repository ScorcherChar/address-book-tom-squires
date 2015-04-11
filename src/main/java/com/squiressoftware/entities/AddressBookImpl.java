package com.squiressoftware.entities;

import com.squiressoftware.builders.PersonBuilder;
import com.squiressoftware.enums.Sex;


import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class AddressBookImpl implements AddressBook {

	private PersonBuilder personBuilder = new PersonBuilder();
	private Set<Person> people = new HashSet<>();

	public AddressBookImpl(Path dataLocation) throws IOException, URISyntaxException {
		String addressData = getFileData(dataLocation);
		parseAndAddToPeopleList(addressData);
	}

	public AddressBookImpl(String addressData) throws IOException, URISyntaxException {
		parseAndAddToPeopleList(addressData);
	}

	@Override
	public long getCount(Sex sex) {
		return people.stream().filter(person -> person.getSex() == sex).count();
	}

	@Override
	public Person getOldestPerson() {
		return people.stream().sorted((p1,p2) -> p1.getBirthdate().compareTo(p2.getBirthdate())).findFirst().get();
	}

	@Override
	public Person getPersonByName(String fullName) {
		Optional<Person> foundPerson = people.stream().filter(person -> person.getFullName().equalsIgnoreCase(fullName)).findFirst();
		return foundPerson.orElse(null);
	}

	@Override
	public Set<Person> getAll() {
		return people;
	}

	//Nb - if csv is invalid this will die ungraciously or give odd results.
	// Data integrity checking was deemed out of scope for this application
	private void parseAndAddToPeopleList(String addressData) {
		String[] rows = addressData.split(System.lineSeparator());
		Arrays.asList(rows).forEach(row -> people.add(personBuilder.buildFromCSVRow(row)));
	}

	private String getFileData(Path location) throws IOException, URISyntaxException {
		return new String(Files.readAllBytes(location), "UTF-8");
	}

}
