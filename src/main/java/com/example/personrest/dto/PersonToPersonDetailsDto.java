package com.example.personrest.dto;

import com.example.personrest.model.Person;

import java.time.LocalDate;

public class PersonToPersonDetailsDto {

    private PersonToPersonDetailsDto() {

    }

    public static PersonDetailsDto getPersonDetailsDto(Person person) {
        int age = LocalDate.now().getYear() - person.getDateOfBirth().getYear();
        return new PersonDetailsDto(person.getFirstName(), person.getLastName(), age);
    }
}
