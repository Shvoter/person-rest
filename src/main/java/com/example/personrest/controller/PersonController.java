package com.example.personrest.controller;

import com.example.personrest.dto.PersonDetailsDto;
import com.example.personrest.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/people/{id}")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @GetMapping
    public PersonDetailsDto getPersonById(@PathVariable Long id) {
        return personService.getById(id);
    }
}
