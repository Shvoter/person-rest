package com.example.personrest;

import com.example.personrest.dto.PersonDetailsDto;
import com.example.personrest.exception.NullEntityReferenceException;
import com.example.personrest.model.Person;
import com.example.personrest.repository.PersonRepository;
import com.example.personrest.service.PersonService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Optional;

@SpringBootTest
public class PersonServiceTest {

    @Autowired
    private PersonService personService;

    @MockBean
    private PersonRepository personRepository;

    @Test
    public void getById_withExistPersonId_shouldReturnPersonDetailsDtoTest() {
        long existId = 1;
        String firstName = "test first name";
        String lastName = "test last name";
        LocalDate dateOfBirth = LocalDate.of(2002, 10, 10);

        Person person = new Person(existId, firstName, lastName, dateOfBirth);
        Mockito.when(personRepository.findById(existId)).thenReturn(Optional.of(person));

        int age = LocalDate.now().getYear() - dateOfBirth.getYear();
        PersonDetailsDto expectedPersonDetailsDto = new PersonDetailsDto(firstName, lastName, age);

        Assertions.assertEquals(expectedPersonDetailsDto, personService.getById(existId));
    }

    @Test
    public void getById_withNotExistPersonId_shouldTrowNullEntityReferenceException() {
        long notExistId = 2;
        String expectedMessage = "Person with id " + notExistId + " not found.";
        Mockito.when(personRepository.findById(notExistId)).thenReturn(Optional.empty());

        NullEntityReferenceException e =
                Assertions.assertThrows(NullEntityReferenceException.class, () -> personService.getById(notExistId));
        Assertions.assertEquals(expectedMessage, e.getMessage());
    }
}
