package com.squiressoftware.entities;


import com.squiressoftware.enums.Sex;


import java.time.LocalDate;

public class Person {

	private final String fullName;
	private final Sex sex;
	private final LocalDate birthdate;

	public Person(String fullName, Sex sex, LocalDate birthdate){
		this.fullName = fullName;
		this.sex = sex;
		this.birthdate = birthdate;
	}

}
