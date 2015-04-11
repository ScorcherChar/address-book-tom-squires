package com.squiressoftware;

import com.squiressoftware.entities.AddressBook;
import com.squiressoftware.entities.AddressBookImpl;
import com.squiressoftware.entities.Person;
import com.squiressoftware.enums.Sex;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.time.temporal.ChronoUnit;


public class App {
	private static String INTO_TEXT = "Welcome to the address book app. Available commands are as follows";
	private static String COMMANDS_TEXT =
			"Please enter one of the following; exit, getcount male|female, getoldest, compare person1name person2name (will give age difference in days)";

	public static void main(String[] args) {

		String addressDataLocation = args[0];
		if (addressDataLocation == null || addressDataLocation.isEmpty()) {
			System.out.println("Address data location required. Please specify");
			return;
		}

		try {
			System.out.println("Starting app");
			AddressBookImpl addressBook = new AddressBookImpl(Paths.get(addressDataLocation));
			System.out.println(INTO_TEXT);

			while (true) {
				System.out.println(COMMANDS_TEXT);
				BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
				String inputText = bufferRead.readLine();
				if (inputText.equals("exit")) {
					return;
				}
				runCommand(System.out, inputText, addressBook);

			}
		} catch (URISyntaxException | IOException e) {
			System.out.println("Error loading file. Please check file exists is of correct format and app has read permission");
		}
	}

	public static void runCommand(PrintStream output, String inputText, AddressBook addressBook) {

		String[] inputWords = inputText.split(" ");
		String command = inputWords[0];

		for (int i = 0; i < inputWords.length; i++) {
			inputWords[i] = inputWords[i].trim();
		}

		switch (command) {
			case "getcount":
				runGetCount(output, addressBook, inputWords[1]);
				break;
			case "getoldest":
				runGetOldest(output, addressBook);
				break;
			case "compare":
				runCompareCommand(output, addressBook, inputWords);
				break;
			default:
				output.println("Unknown command. Please try again");
				break;
		}
	}

	private static void runGetOldest(PrintStream output, AddressBook addressBook) {

		output.println(String.format("%s is oldest", addressBook.getOldestPerson().getFullName()));
	}

	private static void runGetCount(PrintStream output, AddressBook addressBook, String inputWord) {
		Sex sex = Sex.valueOf(inputWord.toUpperCase());
		Long count = addressBook.getCount(sex);
		output.println(String.format("found %s", count));
	}

	private static void runCompareCommand(PrintStream output, AddressBook addressBook, String[] inputWords) {
		if (inputWords.length != 5) {
			output.println(String.format("Invalid input. Required person1FirstName person1Surname person2FirstName person2Surname"));
			return;
		}
		String person1name = inputWords[1] + " " + inputWords[2];
		Person person1 = addressBook.getPersonByName(person1name);
		if (person1 == null) {
			output.println(String.format("%s not found", person1name));
			return;
		}
		String person2name = inputWords[3] + " " + inputWords[4];
		Person person2 = addressBook.getPersonByName(person2name);
		if (person2 == null) {
			output.println(String.format("%s not found", person2name));
			return;
		}
		Long ageDiff = person1.getAgeDifference(person2, ChronoUnit.DAYS);

		if (ageDiff < 0) {
			output.println(String.format("%s is %s days older than %s", person2.getFullName(), -ageDiff, person1.getFullName()));
		} else {
			output.println(String.format("%s is %s days older than %s", person1.getFullName(), ageDiff, person2.getFullName()));
		}
	}
}

