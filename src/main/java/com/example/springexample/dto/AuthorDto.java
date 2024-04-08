package com.example.springexample.dto;

import com.example.springexample.dto.lib.Dto;
import lombok.Data;

import java.util.Map;

//@Builder не получится использовать т.к. json сериализация ломается
@Data
public class AuthorDto implements Dto {
    private String id;
    private String firstName;
    private String lastName;
    private String mail;
    private String phone;
    private Character sex;
    private String city;


}
