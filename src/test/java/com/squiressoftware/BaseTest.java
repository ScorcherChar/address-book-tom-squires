package com.squiressoftware;


import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BaseTest {

	protected String getStringFromClassPath(String jsonLocation) throws URISyntaxException, IOException {
		Path path = Paths.get(getClass().getClassLoader().getResource(jsonLocation).toURI());
		return new String(Files.readAllBytes(path), "UTF-8");
	}
}
