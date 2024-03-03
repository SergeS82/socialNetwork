package com.example.springexample.services;

import com.example.springexample.dto.AuthorDto;
import com.example.springexample.entity.Author;
import com.example.springexample.repository.AuthorRepository;
import jakarta.persistence.PersistenceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;

class AuthorCRUDServiceTest {

    @Test
    void createSuccess() {
        //given
        AuthorRepository authorRepository = Mockito.mock(AuthorRepository.class);
        AuthorCRUDService service = new AuthorCRUDService(authorRepository);
        AuthorDto authorDto = AuthorDto.builder()
                .firstName("Ivan")
                .lastName("Ivanov")
                .mail("test@ya.ru")
                .phone("+79777777777")
                .sex('M')
                .city("Moscow")
                .build();
        AuthorDto savedAuthor = AuthorDto.builder()
                .firstName("Ivan")
                .lastName("Ivanov")
                .mail("test@ya.ru")
                .phone("+79777777777")
                .sex('M')
                .city("Moscow")
                .id(1)
                .build();
        Mockito.when(authorRepository.save(AuthorCRUDService.mapToEntity(authorDto))).thenReturn(AuthorCRUDService.mapToEntity(savedAuthor));
        //when
        Author result = service.createAuthor(authorDto);
        //then
        Assertions.assertEquals("Автор Ivan добавлен в базу с id = 1",  String.format("Автор %s добавлен в базу с id = %s", result.getFirstName(), result.getId()));
    }
    @Test
    void createError() {
        //given
        AuthorRepository authorRepository = Mockito.mock(AuthorRepository.class);
        AuthorCRUDService service = new AuthorCRUDService(authorRepository);
        AuthorDto authorDto = AuthorDto.builder()
                .firstName("Ivan")
                .lastName("Ivanov")
                .mail("test@ya.ru")
                .phone("+79777777777")
                .sex('M')
                .city("Moscow")
                .build();
        AuthorDto savedAuthor = AuthorDto.builder()
                .firstName("Ivan")
                .lastName("Ivanov")
                .mail("test@ya.ru")
                .phone("+79777777777")
                .sex('M')
                .city("Moscow")
                .id(1)
                .build();
        Mockito.when(authorRepository.save(AuthorCRUDService.mapToEntity(authorDto))).thenThrow(PersistenceException.class);
        //when
        Executable executable = () -> service.createAuthor(authorDto);
        //then
       Assertions.assertThrows(PersistenceException.class, executable);
    }

}