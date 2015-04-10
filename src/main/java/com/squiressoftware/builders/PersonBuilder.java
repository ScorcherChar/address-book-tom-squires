package com.squiressoftware.builders;

import com.squiressoftware.entities.Person;
import com.squiressoftware.enums.Sex;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class PersonBuilder {

	public Person buildFromCSVRow(String data) {
		String[] fields = data.split(",");
		String fullName = fields[0].trim();
		Sex sex = Sex.valueOf(fields[1].trim().toUpperCase());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
		LocalDate dob = LocalDate.parse(fields[2].trim(), formatter);

		//Person probably wasn't born in the future. Assume it was decade before
		if (dob.isAfter(LocalDate.now())) {
			dob.minus(1, ChronoUnit.CENTURIES);
		}

		return new Person(fullName, sex, dob);
	}
}
