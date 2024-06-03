package com.example.springexample.services;

import com.example.springexample.dto.AuthorDto;
import com.example.springexample.entity.Author;
import com.example.springexample.repository.AuthorRepository;
import jakarta.persistence.PersistenceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mockito;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ActiveProfiles("test")
class AuthorCRUDServiceTest {

    AuthorRepository authorRepository;
    AuthorCRUDService service;
    AuthorDto authorDtoInsert;
    AuthorDto authorDto1;
    @BeforeEach
    private void init() {
        authorRepository = Mockito.mock(AuthorRepository.class);
        service = new AuthorCRUDService(authorRepository);
//        authorDto1 = AuthorDto.builder()
//                .firstName("Ivan")
//                .lastName("Ivanov")
//                .mail("test@ya.ru")
//                .phone("+79777777777")
//                .sex('M')
//                .city("Moscow")
//                .id(1L)
//                .build();
//        AuthorDto authorDtoInsert = AuthorDto.builder()
//                .firstName("Ivan")
//                .lastName("Ivanov")
//                .mail("test@ya.ru")
//                .phone("+79777777777")
//                .sex('M')
//                .city("Moscow")
//                .build();
    }
    @Test
    void createSuccess() {
        when(authorRepository.save(AuthorCRUDService.mapToEntity(authorDto1))).thenReturn(AuthorCRUDService.mapToEntity(authorDtoInsert));
        //Author result = service.createAuthor(authorDto1);
        //Assertions.assertEquals("����� Ivan �������� � ���� � id = 1",  String.format("����� %s �������� � ���� � id = %s", result.getFirstName(), result.getId()));
    }
    @Test
    void createError() {
        //given
        when(authorRepository.save(AuthorCRUDService.mapToEntity(authorDto1))).thenThrow(PersistenceException.class);
        //when
        Executable executable = () -> service.createAuthor(authorDto1);
        //then
       Assertions.assertThrows(PersistenceException.class, executable);
    }

    @Test
    @DisplayName("Test getById")
    void getById() {
        Long authorId = 1L;
        Author author = AuthorCRUDService.mapToEntity(authorDto1);
        when(authorRepository.findById(authorId)).thenReturn(Optional.of(author));
        AuthorDto authorDto = service.getById(1L);
        Assertions.assertEquals(1, authorDto.getId());
        verify(authorRepository, times(1)).findById(authorId); // ����� ��� ��������
    }
}