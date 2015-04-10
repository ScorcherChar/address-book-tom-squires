package com.squiressoftware.entities;

import com.squiressoftware.builders.PersonBuilder;
import com.squiressoftware.enums.Sex;


import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class AddressBookImpl implements AddressBook {

	private PersonBuilder personBuilder = new PersonBuilder();
	private Set<Person> people = new HashSet<>();

	public AddressBookImpl(String dataLocation) throws IOException, URISyntaxException {
		String addressData = getFileFromClassPath(dataLocation);
		parseAndAddToPeopleList(addressData);
	}

	@Override
	public long getCount(Sex sex) {
		return people.stream().filter(person -> person.getSex() == sex).count();
	}

	@Override
	public Person getOldestPerson() {
		return null;
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

	private void parseAndAddToPeopleList(String addressData) {
		String[] rows = addressData.split(System.lineSeparator());
		Arrays.asList(rows).forEach(row -> people.add(personBuilder.buildFromCSVRow(row)));
	}

	private String getFileFromClassPath(String fileName) throws IOException, URISyntaxException {
		Path path = Paths.get(getClass().getClassLoader().getResource(fileName).toURI());
		return new String(Files.readAllBytes(path), "UTF-8");
	}

}
