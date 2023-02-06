package com.example.personrest.service;

import com.example.personrest.dto.PersonDetailsDto;

public interface PersonService {

    PersonDetailsDto getById(Long id);
}
