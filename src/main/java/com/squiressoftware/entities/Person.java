package com.squiressoftware.entities;


import com.squiressoftware.enums.Sex;


import java.time.Duration;
import java.time.LocalDate;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Person {

	private final String fullName;
	private final Sex sex;
	private final LocalDate birthdate;

	public Person(String fullName, Sex sex, LocalDate birthdate){
		this.fullName = fullName;
		this.sex = sex;
		this.birthdate = birthdate;
	}

	public Duration getAgeDifference(Person otherPerson){
		throw new NotImplementedException();
	}

	public String getFullName() {
		return fullName;
	}

	public Sex getSex() {
		return sex;
	}

	public LocalDate getBirthdate() {
		return birthdate;
	}
}
