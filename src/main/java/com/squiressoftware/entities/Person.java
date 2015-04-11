package com.squiressoftware.entities;


import com.squiressoftware.enums.Sex;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Person {

	private final String fullName;
	private final Sex sex;
	private final LocalDate birthdate;

	public Person(String fullName, Sex sex, LocalDate birthdate) {
		this.fullName = fullName;
		this.sex = sex;
		this.birthdate = birthdate;
	}

	public Long getAgeDifference(Person otherPerson, ChronoUnit unit) {
		return unit.between(this.getBirthdate(), otherPerson.getBirthdate());
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

	@Override
	public String toString(){
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return String.format("%s :: %s :: %s", fullName, sex, birthdate.format(formatter));
	}
}
