package com.squiressoftware.entities;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AddressBookImpl implements AddressBook {

	public AddressBookImpl(String dataLocation) throws IOException, URISyntaxException {
		String addressData = getFileFromClassPath("AddressBook");
		String[] rows = addressData.split(System.lineSeparator());
	}

	private String getFileFromClassPath(String fileName) throws IOException, URISyntaxException {
		Path path = Paths.get(getClass().getClassLoader().getResource(fileName).toURI());
		return new String(Files.readAllBytes(path), "UTF-8");
	}
}
