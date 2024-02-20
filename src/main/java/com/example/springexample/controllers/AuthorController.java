package com.example.springexample.controllers;

import com.example.springexample.dto.AuthorDto;
import com.example.springexample.services.AuthorCRUDService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/author")
@RequiredArgsConstructor
@RestController
public class AuthorController {
    private final AuthorCRUDService service;

    @PostMapping
    public void create(@RequestBody AuthorDto authorDto) {
        service.create(authorDto);
    }

    @GetMapping("/{id}")
    public AuthorDto get(@PathVariable Integer id) {
        return service.getById(id);
    }

    @PatchMapping("/{id}")
    public void update(@PathVariable Integer id, @RequestBody AuthorDto authorDto) {
        authorDto.setId(id);
        service.update(authorDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

}
