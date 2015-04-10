package com.squiressoftware.entities;

import com.squiressoftware.enums.Sex;


import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class AddressBookImpl implements AddressBook {

	public AddressBookImpl(String dataLocation) throws IOException, URISyntaxException {
		String addressData = getFileFromClassPath("AddressBook");
		String[] rows = addressData.split(System.lineSeparator());
	}

	private String getFileFromClassPath(String fileName) throws IOException, URISyntaxException {
		Path path = Paths.get(getClass().getClassLoader().getResource(fileName).toURI());
		return new String(Files.readAllBytes(path), "UTF-8");
	}

	@Override
	public long getCount(Sex sex) {
		return 0;
	}

	@Override
	public Person getOldestPerson() {
		return null;
	}

	@Override
	public Person getPersonByName(String fullName) {
		return null;
	}

	@Override
	public List<Person> getAll() {
		return null;
	}
}
