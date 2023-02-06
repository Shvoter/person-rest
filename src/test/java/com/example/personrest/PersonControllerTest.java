package com.example.personrest;

import com.example.personrest.controller.PersonController;
import com.example.personrest.dto.PersonDetailsDto;
import com.example.personrest.exception.NullEntityReferenceException;
import com.example.personrest.service.PersonService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


@SpringBootTest
public class PersonControllerTest {

    @Autowired
    private PersonController personController;

    @MockBean
    private PersonService personService;

    @Test
    public void getPersonById_withExistId_shouldReturnPersonDetailsDto() {
        long existId = 1;
        String firstName = "test first name";
        String lastName = "test last name";
        int age = 20;

        PersonDetailsDto expectedPersonDetailsDto = new PersonDetailsDto(firstName, lastName, age);
        Mockito.when(personService.getById(existId)).thenReturn(expectedPersonDetailsDto);

        Assertions.assertEquals(expectedPersonDetailsDto, personController.getPersonById(existId));
    }

    @Test
    public void getPersonById_withNotExistId_shouldTrowNullEntityReferenceException() {
        long notExistId = 2;
        String expectedMessage = "Person with id " + notExistId + " not found.";

        Mockito.when(personService.getById(notExistId))
                .thenThrow(new NullEntityReferenceException(expectedMessage));
        NullEntityReferenceException e =
                Assertions.assertThrows(NullEntityReferenceException.class, () -> personController.getPersonById(notExistId));

        Assertions.assertEquals(expectedMessage, e.getMessage());
    }
}
