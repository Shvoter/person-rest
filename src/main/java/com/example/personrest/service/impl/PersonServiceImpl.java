package com.example.personrest.service.impl;

import com.example.personrest.dto.PersonDetailsDto;
import com.example.personrest.dto.PersonToPersonDetailsDto;
import com.example.personrest.exception.NullEntityReferenceException;
import com.example.personrest.model.Person;
import com.example.personrest.repository.PersonRepository;
import com.example.personrest.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    @Override
    public PersonDetailsDto getById(Long id) {
        Person person = personRepository.findById(id).orElseThrow(
                () -> new NullEntityReferenceException("Person with id " + id + " not found."));
        return PersonToPersonDetailsDto.getPersonDetailsDto(person);
    }
}


