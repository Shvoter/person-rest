package com.example.personrest;

import com.example.personrest.dto.PersonDetailsDto;
import com.example.personrest.dto.PersonToPersonDetailsDto;
import com.example.personrest.model.Person;
import com.example.personrest.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PersonRestApplicationTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void getPersonById_withExistId_shouldReturnOkStatusAndPersonDetailDto() throws Exception {
        long existId = 1;
        String url = "/api/people/" + existId;
        Person person = personRepository.findById(existId).get();
        PersonDetailsDto personDetailsDto = PersonToPersonDetailsDto.getPersonDetailsDto(person);

        mvc.perform(MockMvcRequestBuilders.get(url))
                .andExpect(status().isOk())
                .andExpect(PersonData("$", personDetailsDto));
    }

    @Test
    public void getPersonById_withNoExistId_shouldReturnBadRequestStatusAndMessageTest() throws Exception {
        long notExistId = 6L;
        String url = "/api/people/" + notExistId;

        mvc.perform(MockMvcRequestBuilders.get(url))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message")
                        .value("Person with id " + notExistId + " not found."));
    }

    public static ResultMatcher PersonData(String prefix, PersonDetailsDto personDetailsDto) {
        return ResultMatcher.matchAll(
                jsonPath(prefix + ".firstName").value(personDetailsDto.getFirstName()),
                jsonPath(prefix + ".lastName").value(personDetailsDto.getLastName()),
                jsonPath(prefix + ".age").value(personDetailsDto.getAge()));
    }
}
