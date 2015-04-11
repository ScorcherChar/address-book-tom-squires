package com.squiressoftware;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import com.squiressoftware.entities.AddressBook;
import com.squiressoftware.entities.AddressBookImpl;
import com.squiressoftware.entities.Person;
import com.squiressoftware.enums.Sex;


import java.time.format.DateTimeFormatter;

public class AddressBookTests extends BaseTest{

	@Test
	public void givenThreeMalesInAddressBook_whenGetCountCalledForMales_thenThreeReturned() throws Exception{
		AddressBook addressBook = new AddressBookImpl(getStringFromClassPath("AddressBook1"));
		Long count = addressBook.getCount(Sex.MALE);
		assertEquals(new Long("3"), count);
	}

	@Test
	public void givenWesJacksonOldestInAddressBook_whenGetOldestPersonCalled_thenWesReturned()throws Exception{
		AddressBook addressBook = new AddressBookImpl(getStringFromClassPath("AddressBook1"));
		Person oldestPerson = addressBook.getOldestPerson();
		assertEquals("Wes Jackson", oldestPerson.getFullName());
	}

	@Test
	public void givenWesJacksonInAddressBook_whenGetPersonCalledForWes_thenWesReturned()throws Exception{
		final String NAME = "Wes Jackson";
		AddressBook addressBook = new AddressBookImpl(getStringFromClassPath("AddressBook1"));
		Person wes = addressBook.getPersonByName(NAME);
		assertEquals(NAME, wes.getFullName());
	}

	@Test
	public void givenWesJacksonInAddressBook_whenGetPersonCalledForWes_thenCorrectAgeGiven()throws Exception{
		final String NAME = "Wes Jackson";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		AddressBook addressBook = new AddressBookImpl(getStringFromClassPath("AddressBook1"));
		Person wes = addressBook.getPersonByName(NAME);
		assertEquals("14/08/1974", wes.getBirthdate().format(formatter));
	}

	@Test
	public void givenJamesBondNotInAddressBook_whenGetPersonCalledForJames_thenNullReturned()throws Exception{
		final String NAME = "James Bond";
		AddressBook addressBook = new AddressBookImpl(getStringFromClassPath("AddressBook1"));
		Person james = addressBook.getPersonByName(NAME);
		assertNull(james);
	}

}
