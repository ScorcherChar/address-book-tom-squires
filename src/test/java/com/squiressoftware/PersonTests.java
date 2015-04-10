package com.squiressoftware;

import org.junit.Assert;
import org.junit.Test;

import com.squiressoftware.entities.Person;
import com.squiressoftware.enums.Sex;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class PersonTests {
	@Test
	public void givenBillIs2862DaysOlderThanPaul_whenGetAgeDiffrenceCalled_theCorrectDurationReturned()throws Exception{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		Person bill = new Person("Bill McKnight", Sex.MALE, LocalDate.parse("16/03/1977",formatter));
		Person paul = new Person("Paul Robinson", Sex.MALE, LocalDate.parse("15/01/1985",formatter));

		Long ageDiff = bill.getAgeDifference(paul, ChronoUnit.DAYS);

		Assert.assertEquals(new Long("2862"), ageDiff);
	}
}
