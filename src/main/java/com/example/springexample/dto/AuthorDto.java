package com.example.springexample.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthorDto {
    private Integer id;
    private String firstName;
    private String lastName;
    private String mail;
    private String phone;
    private Character sex;
    private String city;
}
