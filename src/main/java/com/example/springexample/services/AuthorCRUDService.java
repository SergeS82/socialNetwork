package com.example.springexample.services;

import com.example.springexample.dto.AuthorDto;
import com.example.springexample.entity.Author;
import com.example.springexample.services.lib.CRUDService;
import com.example.springexample.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AuthorCRUDService implements CRUDService<AuthorDto> {

    private final AuthorRepository repository;

    @Autowired
    public AuthorCRUDService(AuthorRepository repository) {
        this.repository = repository;
    }

    @Override
    public AuthorDto getById(Integer id) {
      Author author = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
      return mapToDto(author);
    }

    @Override
    public List<AuthorDto> getTop(Integer item, Integer count) {
        throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);
    }
    @Override
    public void create(AuthorDto authorDto) {
        Author author = mapToEntity(authorDto);
        repository.save(mapToEntity(authorDto));
    }
    public Author createAuthor(AuthorDto authorDto) {
        return repository.save(mapToEntity(authorDto));
    }

    @Override
    public void update(AuthorDto authorDto) {
        if (!repository.existsById(authorDto.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        repository.save(mapToEntity(authorDto));
    }

    @Override
    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        repository.deleteById(id);
    }

    public static Author mapToEntity(AuthorDto authorDto) {
        return Author.builder()
                .id(authorDto.getId())
                .firstName(authorDto.getFirstName())
                .lastName(authorDto.getLastName())
                .mail(authorDto.getMail())
                .city(authorDto.getCity())
                .phone(authorDto.getPhone())
                .build();
    }

    public static AuthorDto mapToDto(Author author) {
        return AuthorDto.builder()
                .id(author.getId())
                .firstName(author.getFirstName())
                .lastName(author.getFirstName())
                .mail(author.getMail())
                .city(author.getCity())
                .phone(author.getPhone())
                .build();
    }
}
