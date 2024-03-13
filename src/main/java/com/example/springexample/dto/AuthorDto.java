package com.example.springexample.dto;

import com.example.springexample.dto.lib.Dto;
import lombok.Data;

import java.util.Map;

//@Builder не получится использовать т.к. json сериализация ломается
@Data
public class AuthorDto implements Dto<AuthorDto> {
    private String id;
    private String firstName;
    private String lastName;
    private String mail;
    private String phone;
    private Character sex;
    private String city;

    @Override
    public AuthorDto fillFromMap(Map<String, String> map) {
        id = map.get("id");
        firstName = map.get("firstName");
        lastName = map.get("lastName");
        mail = map.get("mail");
        phone = map.get("phone");
        sex = map.get("sex").charAt(0);
        city = map.get("city");
        return this;
    }


}
