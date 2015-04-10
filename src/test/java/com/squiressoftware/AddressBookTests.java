package com.squiressoftware;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.squiressoftware.entities.AddressBook;
import com.squiressoftware.entities.AddressBookImpl;
import com.squiressoftware.entities.Person;
import com.squiressoftware.enums.Sex;

public class AddressBookTests {

	@Test
	public void givenThreeMalesInAddressBook_whenGetCountCalledForMales_thenThreeReturned() throws Exception{
		AddressBook addressBook = new AddressBookImpl("AddressBook1");
		Long count = addressBook.getCount(Sex.MALE);
		assertEquals(new Long("3"), count);
	}

	@Test
	public void givenWesJacksonOldestInAddressBook_whenGetOldestPersonCalled_thenWesReturned()throws Exception{
		AddressBook addressBook = new AddressBookImpl("AddressBook1");
		Person oldestPerson = addressBook.getOldestPerson();
		assertEquals("Wes Jackson", oldestPerson.getFullName());
	}
}
