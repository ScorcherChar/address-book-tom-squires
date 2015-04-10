package com.squiressoftware.entities;

import com.squiressoftware.enums.Sex;


import java.util.List;

public interface AddressBook {
	public long getCount(Sex sex);
	public Person getOldestPerson();
	public Person getPersonByName(String fullName);
	public List<Person> getAll();

}
