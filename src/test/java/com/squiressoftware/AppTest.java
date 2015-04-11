package com.squiressoftware;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.squiressoftware.entities.AddressBook;
import com.squiressoftware.entities.AddressBookImpl;


import java.io.IOException;
import java.io.PrintStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RunWith(MockitoJUnitRunner.class)
public class AppTest
{

	@Mock
	PrintStream output;

	@Test
	public void givenInvalidCommand_whenCommandRan_thenOnlyErrorTextDisplayed() throws Exception{
		String inputText = "some junk";
		AddressBook addressBook = new AddressBookImpl(getStringFromClassPath("AddressBook1"));

		App.runCommand(output,inputText , addressBook);

		String errorText = "Unknown command. Please try again";
		verify(output,times(1)).println(anyString());
		verify(output,times(1)).println(errorText);
	}

	@Test
	public void givenTwoFemalesInAddressBook_whenGetCountCalledWithFemaleAsArgument_thenOnlyTwoFoundTextDisplayed() throws Exception{
		String inputText = "getcount female";
		AddressBook addressBook = new AddressBookImpl(getStringFromClassPath("AddressBook1"));

		App.runCommand(output,inputText , addressBook);

		verify(output,times(1)).println(anyString());
		verify(output,times(1)).println("found 2");
	}

	@Test
	public void givenJamesBondNotInAddressBook_whenCompareCalledWithJamesBondAsFirstArgument_thenErrorTextDisplayed() throws Exception{
		String inputText = "compare James Bond Wes Jackson";
		AddressBook addressBook = new AddressBookImpl(getStringFromClassPath("AddressBook1"));

		App.runCommand(output,inputText , addressBook);

		verify(output,times(1)).println(anyString());
		verify(output,times(1)).println("James Bond not found");
	}

	@Test
	public void givenJamesBondNotInAddressBook_whenCompareCalledWithJamesBondAsSecondArgument_thenErrorTextDisplayed() throws Exception{
		String inputText = "compare Wes Jackson James Bond";
		AddressBook addressBook = new AddressBookImpl(getStringFromClassPath("AddressBook1"));

		App.runCommand(output,inputText , addressBook);

		verify(output,times(1)).println(anyString());
		verify(output,times(1)).println("James Bond not found");
	}

	@Test
	public void givenBothPeopleInAddressBook_whenCompareCalled_thenDaysDisplayed() throws Exception{
		String inputText = "compare Wes Jackson Gemma Lane";
		AddressBook addressBook = new AddressBookImpl(getStringFromClassPath("AddressBook1"));

		App.runCommand(output,inputText , addressBook);

		verify(output,times(1)).println(anyString());
		verify(output,times(1)).println("Wes Jackson is 6307 days older than Gemma Lane");
	}

	@Test
	public void givenBothPeopleInAddressBook_whenCompareCalledWithArumentsSwapped_thenDaysDisplayedTheSame() throws Exception{
		String inputText = "compare Gemma Lane Wes Jackson";
		AddressBook addressBook = new AddressBookImpl(getStringFromClassPath("AddressBook1"));

		App.runCommand(output,inputText , addressBook);

		verify(output,times(1)).println(anyString());
		verify(output,times(1)).println("Wes Jackson is 6307 days older than Gemma Lane");
	}

	@Test
	public void givenWesJacksonIsOldest_whenGetOldestCalled_thengivenWesJacksonDisplayed() throws Exception{
		String inputText = "getoldest";
		AddressBook addressBook = new AddressBookImpl(getStringFromClassPath("AddressBook1"));

		App.runCommand(output,inputText , addressBook);

		verify(output,times(1)).println(anyString());
		verify(output,times(1)).println("Wes Jackson is oldest");
	}


	protected String getStringFromClassPath(String jsonLocation) throws URISyntaxException, IOException {
		Path path = Paths.get(getClass().getClassLoader().getResource(jsonLocation).toURI());
		return new String(Files.readAllBytes(path), "UTF-8");
	}

}
