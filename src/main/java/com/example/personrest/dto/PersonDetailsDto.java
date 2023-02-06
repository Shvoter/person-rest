package com.example.personrest.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

@Value
@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
@Getter
@AllArgsConstructor
public class PersonDetailsDto {

    String firstName;

    String lastName;

    int age;
}
